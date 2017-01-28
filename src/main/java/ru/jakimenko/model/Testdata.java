package ru.jakimenko.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author kyyakime
 */
@Entity
@Table(name = "Testdata", schema = "mytest")
public class Testdata implements Serializable {
    
    private Long testdataId;
    private Long testdatatypeId;
    private Long customerId;
    private Long fixResultId;

    @Id
    @GeneratedValue
    @Column(name = "testdata_id", unique = true, nullable = false)
    public Long getId() {
        return testdataId;
    }

    public void setId(Long id) {
        this.testdataId = id;
    }

    @Column(name = "testdatatype_id", nullable = false)
    public Long getTestdatatypeId() {
        return testdatatypeId;
    }

    public void setTestdatatypeId(Long testdatatypeId) {
        this.testdatatypeId = testdatatypeId;
    }
    
    
    @Column(name = "customer_id", nullable = false)
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Column(name = "fix_result_id")
    public Long getFixResultId() {
        return fixResultId;
    }

    public void setFixResultId(Long fixResultId) {
        this.fixResultId = fixResultId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Testdata testdata = (Testdata) o;

        if (testdataId != null ? !testdataId.equals(testdata.testdataId) : testdata.testdataId != null) return false;
        if (testdatatypeId != null ? !testdatatypeId.equals(testdata.testdatatypeId) : testdata.testdatatypeId != null)
            return false;
        if (customerId != null ? !customerId.equals(testdata.customerId) : testdata.customerId != null) return false;
        return fixResultId != null ? fixResultId.equals(testdata.fixResultId) : testdata.fixResultId == null;
    }

    @Override
    public int hashCode() {
        int result = testdataId != null ? testdataId.hashCode() : 0;
        result = 31 * result + (testdatatypeId != null ? testdatatypeId.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (fixResultId != null ? fixResultId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Testdata{" +
                "testdataId=" + testdataId +
                ", testdatatypeId=" + testdatatypeId +
                ", customerId=" + customerId +
                ", fixResultId=" + fixResultId +
                '}';
    }
}
