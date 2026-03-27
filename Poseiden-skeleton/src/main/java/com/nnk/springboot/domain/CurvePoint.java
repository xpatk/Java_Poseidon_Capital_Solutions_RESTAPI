package com.nnk.springboot.domain;
import jakarta.validation.constraints.DecimalMin;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curvepoint")
    private Integer id;

    @NotNull(message ="Curve ID is required")
    @Column(name = "curveid")
    private Integer curveId;

    @Column(name = "asofdate")
    private Timestamp asOfDate;

    @NotNull(message = "Term is required")
    @DecimalMin("0.0")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "Value is required")
    @DecimalMin("0.0")
    @Column(name = "value")
    private Double value;

    @Column(name = "creationdate")
    private Timestamp creationDate;

    // constructor
    public CurvePoint() {
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public Double getValue() {
        return value;
    }

    public Double getTerm() {
        return term;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

}
