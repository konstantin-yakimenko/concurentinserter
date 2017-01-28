package ru.jakimenko.concurentinserter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.jakimenko.executor.Myexecutor;

public class Main
{
    private static final Logger LOG = LogManager.getLogger(Main.class);
    
    public static void main( String[] args )
    {
        LOG.info("Start tool !");

        try {
            ApplicationContextUnit.load();
            Myexecutor exec = ApplicationContextUnit.getBean(Myexecutor.class);
            exec.execute();
        } catch (Exception e) {
            LOG.error("Error: {}", e);
        }
        
    }

}
