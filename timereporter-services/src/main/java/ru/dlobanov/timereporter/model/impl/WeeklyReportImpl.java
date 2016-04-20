package ru.dlobanov.timereporter.model.impl;

import ru.dlobanov.timereporter.model.WeeklyReport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "public", name = "report")
public class WeeklyReportImpl implements WeeklyReport, Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotNull
    private int year;
    
    @NotNull
    private int week;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "report", fetch = FetchType.EAGER)
    private List<ActivityImpl> activities;
    
    @NotNull
    @ManyToOne
    private EmployeeImpl employee;
    
    @NotNull
    private Date creationDate;
    
    private Date modificationDate;
    
    private int version;
    
    public WeeklyReportImpl() {
    }

    public WeeklyReportImpl(EmployeeImpl employee, int year, int week) {
        super();
        this.employee = employee;
        this.year = year;
        this.week = week;
        this.creationDate = new Date();
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getWeek() {
        return week;
    }

    public List<ActivityImpl> getActivities() {
        return activities;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public int getVersion() {
        return version;
    }
    
    public EmployeeImpl getEmployee() {
        return employee;
    }
    
    public void addActivity(ActivityImpl activity) {
        if ( activities == null ) {
            activities = new ArrayList<ActivityImpl>();
        }
        activities.add(activity);
        activity.setReport(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setActivities(List<ActivityImpl> activities) {
        this.activities = activities;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    public void setEmployee(EmployeeImpl employee) {
        this.employee = employee;
    }
}
