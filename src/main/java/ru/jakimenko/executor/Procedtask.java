package ru.jakimenko.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import ru.jakimenko.dao.ToolDAO;

import javax.persistence.ParameterMode;
import java.time.ZonedDateTime;
import java.util.concurrent.Callable;


/**
 *
 * @author kyyakime
 */
public class Procedtask implements Callable<Integer> {

    private static final Logger LOG = LogManager.getLogger(Mytask.class);
    private final ToolDAO toolDAO;
    private final SessionFactory sessionFactory;

    public Procedtask(ToolDAO toolDAO, SessionFactory sessionFactory) {
        this.toolDAO = toolDAO;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer call() throws Exception {

        Long id = (long) ZonedDateTime.now().getSecond();
        
        Session session = sessionFactory.openSession();
        try {
            ProcedureCall query = session.createStoredProcedureCall("mytest.setTestdata");
            query.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(id.intValue());
            query.registerParameter(2, Long.class, ParameterMode.IN).bindValue(id);
            ProcedureOutputs procedureOutputs = query.getOutputs();
            LOG.info("query end of execute | res = {}", procedureOutputs);
        } finally {
            session.close();
        }
        return 0;
    }

}
