package ru.jakimenko.executor;

import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 *
 * @author kyyakime
 */
public class Taskjdbc implements Callable<Integer>  {

    private static final Logger LOG = LogManager.getLogger(Taskjdbc.class);

    private String sql = "{ call USRDICTS.setTestdata(?, ?) }";
    
    private final DataSource ds;

    public Taskjdbc(DataSource ds) {
        this.ds = ds;
    }
    
    @Override
    public Integer call() throws Exception {

        Long id = (long) ZonedDateTime.now().getSecond();
        
        Connection cnn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cnn = ds.getConnection();
            
            cs = cnn.prepareCall(sql);
            cs.setLong(1, id);
            cs.setLong(2, id);
            LOG.trace("Query: " + cs);
            cs.execute();
            
            return 0;
        } catch (Exception ex) {
            LOG.error("Error: {} | class: {} | trace: {}",ex.getMessage(), ex.getClass(), Arrays.toString(ex.getStackTrace()));
            throw ex;
        } finally {
            DbUtils.closeQuietly(cnn, cs, rs);
        }        
    }

}
