package ru.jakimenko.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.jakimenko.concurentinserter.ApplicationContextUnit;
import ru.jakimenko.dao.ToolDAO;
import ru.jakimenko.model.Testdata;

import java.time.ZonedDateTime;
import java.util.concurrent.Callable;

/**
 *
 * @author kyyakime
 */
public class Mytask implements Callable<Integer> {

    private static final Logger LOG = LogManager.getLogger(Mytask.class);

    private final ToolDAO toolDAO;
    private Total total;

    public Mytask() {
        ApplicationContextUnit.load();
        this.toolDAO = ApplicationContextUnit.getBean(ToolDAO.class);
    }

    @Override
    public Integer call() throws Exception {
        try {
            Testdata problem = new Testdata();
        
            Long id = (long) ZonedDateTime.now().getSecond();
        
            problem.setCustomerId(id);
            problem.setTestdatatypeId(id);
            Long newId = toolDAO.saveTestdata(problem);
            LOG.info("new Id = {}", newId);
        } catch (Exception e) {
            LOG.error("ERROR in close problem | message: {}, cause: {}, cause class: {}", 
                    e.getMessage(), 
                    e.getCause(),
                    e.getCause().getClass());
        }
        return 0;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Total getTotal() {
        return total;
    }

    public int calc(int i) {
        return 2 * total.calc(i);
    }
}
