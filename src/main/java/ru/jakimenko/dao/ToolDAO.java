package ru.jakimenko.dao;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jakimenko.model.Testdata;
import ru.jakimenko.model.Testdataclosed;

/**
CREATE DATABASE  IF NOT EXISTS `mytest` / *!40100 DEFAULT CHARACTER SET utf8 * /;
USE `mytest`;

CREATE TABLE IF NOT EXISTS mytest.`Testdata` (
    `testdata_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `testdatatype_id` int(11) NOT NULL,
    `customer_id` bigint(20) NOT NULL,
    PRIMARY KEY (`testdata_id`),
    KEY `idx_customer_id_testdatatype_id` (`customer_id`,`testdatatype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20550945 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS mytest.`Testdataclosed` (
 `testdataclosed_id` bigint(20) NOT NULL AUTO_INCREMENT,
 `testdata_id` bigint(20) NOT NULL,
 `testdatatype_id` int(11) NOT NULL,
 `customer_id` bigint(20) NOT NULL,
 `fix_description` varchar(150) NULL,
 `fix_result_id` tinyint(4) DEFAULT NULL,
 PRIMARY KEY (`testdataclosed_id`),
 KEY `idx_customer_id_testdatatype_id` (`customer_id`,`testdatatype_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=20000000 DEFAULT CHARSET=utf8;
 *
 * @author kyyakime
 */
@Component
public class ToolDAO {
    
    private final SessionFactory sessionFactory;

    @Autowired
    public ToolDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private final static String SQL_GET_TESTDATA_BY_CRITERIA
            = "select * from mytest.Testdata t "
            + "    where t.customer_id = :customerId"
            + "    and t.testdatatype_id = :testdatatypeId";

    /**
     *
     * @param customerId
     * @param testdatatypeId
     * @return
     * @throws Exception
     */
    public Testdata getTestdata(Long customerId, Integer testdatatypeId) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createSQLQuery(SQL_GET_TESTDATA_BY_CRITERIA)
                    .addEntity("t", Testdata.class)
                    .setParameter("customerId", customerId)
                    .setParameter("testdatatypeId", testdatatypeId);
            return (Testdata) query.list().get(0); // query.uniqueResult();
        } finally {
            session.close();
        }
    }

    public void closeTestdata(Testdata testdata, String fixDescription, Long fixResultid) throws Exception {
        Testdataclosed close = new Testdataclosed(testdata);
        close.setFixDescription(fixDescription);
        close.setFixResultId(fixResultid);

        Session session = sessionFactory.openSession();
        try {
            Transaction tx = session.beginTransaction();

            Testdataclosed testdataclosedSession = (Testdataclosed) session.merge(close);
            session.saveOrUpdate(testdataclosedSession);

            Testdata testdataSession = (Testdata) session.merge(testdata);
            session.delete(testdataSession);

            session.flush();
            tx.commit();

        } finally {
            session.close();
        }
    }

    /**
     *
     * @param copy
     * @return
     * @throws HibernateException
     */
    public Long openCopy(Testdataclosed copy) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            Testdataclosed testdataSession = (Testdataclosed) session.merge(copy);
            session.saveOrUpdate(testdataSession);
            session.flush();
            copy.setTestdataclosedId(testdataSession.getTestdataclosedId());
            return copy.getTestdataclosedId();
        } finally {
            session.close();
        }
    }

    /**
     *
     * @param testdata
     * @return
     * @throws HibernateException
     */
    public Long saveTestdata(Testdata testdata) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            Testdata testdataSession = (Testdata) session.merge(testdata);
            session.saveOrUpdate(testdataSession);
            session.flush();
            testdata.setId(testdataSession.getId());
            return testdata.getId();
        } finally {
            session.close();
        }
    }

}
