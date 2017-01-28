package ru.jakimenko.model;

import javax.persistence.*;

/**
 * Created by konst on 28.01.17.
 */
@Entity
@Table(name = "Testdataclosed", schema = "mytest")
public class Testdataclosed {

    private long testdataclosedId;
    private long testdataId;
    private long testdatatypeId;
    private long customerId;
    private String fixDescription;
    private Long fixResultId;

    public Testdataclosed() {
    }

    public Testdataclosed(Testdata source) {
        this.testdataId = source.getId();
        this.testdatatypeId = source.getTestdatatypeId();
        this.customerId = source.getCustomerId();
        this.fixResultId = source.getFixResultId();
    }

    @Id
    @GeneratedValue
    @Column(name = "testdataclosed_id")
    public long getTestdataclosedId() {
        return testdataclosedId;
    }

    public void setTestdataclosedId(long testdataclosedId) {
        this.testdataclosedId = testdataclosedId;
    }

    @Basic
    @Column(name = "testdata_id")
    public long getTestdataId() {
        return testdataId;
    }

    public void setTestdataId(long testdataId) {
        this.testdataId = testdataId;
    }

    @Basic
    @Column(name = "testdatatype_id")
    public Long getTestdatatypeId() {
        return testdatatypeId;
    }

    public void setTestdatatypeId(Long testdatatypeId) {
        this.testdatatypeId = testdatatypeId;
    }

    @Basic
    @Column(name = "customer_id")
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "fix_description")
    public String getFixDescription() {
        return fixDescription;
    }

    public void setFixDescription(String fixDescription) {
        this.fixDescription = fixDescription;
    }

    @Basic
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

        Testdataclosed that = (Testdataclosed) o;

        if (testdataclosedId != that.testdataclosedId) return false;
        if (testdataId != that.testdataId) return false;
        if (testdatatypeId != that.testdatatypeId) return false;
        if (customerId != that.customerId) return false;
        if (fixDescription != null ? !fixDescription.equals(that.fixDescription) : that.fixDescription != null)
            return false;
        return fixResultId != null ? fixResultId.equals(that.fixResultId) : that.fixResultId == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (testdataclosedId ^ (testdataclosedId >>> 32));
        result = 31 * result + (int) (testdataId ^ (testdataId >>> 32));
        result = 31 * result + (int) (testdatatypeId ^ (testdatatypeId >>> 32));
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (fixDescription != null ? fixDescription.hashCode() : 0);
        result = 31 * result + (fixResultId != null ? fixResultId.hashCode() : 0);
        return result;
    }
}
