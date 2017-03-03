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
public class Mytaskclose implements Callable<Integer> {
    
    private static final Logger LOG = LogManager.getLogger(Mytask.class);

    private final ToolDAO toolDAO;

    public Mytaskclose() {
        ApplicationContextUnit.load();
        this.toolDAO = ApplicationContextUnit.getBean(ToolDAO.class);
    }

    @Override
    public Integer call() throws Exception {
        try {
            Long id = (long) ZonedDateTime.now().getSecond();
            Testdata testdata = toolDAO.getTestdata(id, id.intValue());
            toolDAO.closeTestdata(testdata, "bla bla", 7L);
            LOG.info("close testdata with id = {}", id);
        } catch (Exception e) {
            LOG.error("ERROR in close problem | message: {}, cause: {}, cause class: {}", 
                    e.getMessage(), 
                    e.getCause(),
                    e.getCause().getClass());
        }
        return 0;
    }
}
