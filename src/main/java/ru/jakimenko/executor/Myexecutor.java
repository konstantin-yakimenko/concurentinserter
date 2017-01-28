package ru.jakimenko.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.jakimenko.dao.ToolDAO;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 *
 * @author kyyakime
 */
@Component
public class Myexecutor {
    
    private static final Logger LOG = LogManager.getLogger(Myexecutor.class);

    private final int countTasks = 2000;
    
    private final ThreadPoolTaskExecutor taskExecutor;
    private final SessionFactory sessionFactory;
    private final DataSource dataSource;
    
    private final ToolDAO toolDAO; 
    
    @Autowired
    public Myexecutor(
            ThreadPoolTaskExecutor taskExecutor, 
            SessionFactory sessionFactory, 
            DataSource dataSource,
            ToolDAO toolDAO) {
        this.taskExecutor = taskExecutor;
        this.sessionFactory = sessionFactory;
        this.dataSource = dataSource;
        this.toolDAO = toolDAO;
    }

    public void execute() {
        try {
            LOG.info("Started the migration process.");

            executeItem();
            LOG.info("Created tasks to the processing");

            waitFinishTasks();

            LOG.info("Finished the migration process.");

        } finally {
            taskExecutor.shutdown();
        }
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            LOG.error("getconnection: {}", e);
        }
        return null;
    }
    
    
    private void executeItem() {

        for (int i = 0; i < countTasks; i++) {
            Mytask task = new Mytask();
            Mytaskclose taskclose = new Mytaskclose();
//            Procedtask task = new Procedtask(new toolDAO(sessionFactory), sessionFactory);
//            Taskjdbc task = new Taskjdbc(dataSource);
            taskExecutor.submit(task);
            taskExecutor.submit(taskclose);
        }
        
        
    }

    private void waitFinishTasks() {
        int activeTask = taskExecutor.getActiveCount();
        int oldTask = activeTask;
        while (activeTask > 0) {
            if (activeTask != oldTask) {
                LOG.info(String.format("Actived %d tasks to the processing.", activeTask));
                oldTask = activeTask;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                break;
            }
            activeTask = taskExecutor.getActiveCount();
        }
    }
    
}
