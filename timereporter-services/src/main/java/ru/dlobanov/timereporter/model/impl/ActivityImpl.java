package ru.dlobanov.timereporter.model.impl;

import ru.dlobanov.timereporter.model.Activity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name = "activity")
public class ActivityImpl implements Activity {

    public enum ActivityType {
        BUG_FIX,
        DEVELOPMENT,
        ILLNESS,
        VACATION,
        OTHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Min(1)
    @Max(40)
    private int spentTime;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private ActivityType activityType;

    @Size(max = 31, message = "Bug id max lenght is 31 characters")
    private String bugId;

    @Size(max = 255, message = "Activity name max length is 255 characters")
    private String name;

    @Size(max = 4000, message = "Description max length is 4000 characters")
    private String description;

    @ManyToOne
    private WeeklyReportImpl report;

    public ActivityImpl() {
    }

    public ActivityImpl(WeeklyReportImpl report, ActivityType activityType, String name, String description, int spentTime) {
        super();
        this.report = report;
        this.activityType = activityType;
        this.name = name;
        this.description = description;
        this.spentTime = spentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeeklyReportImpl getReport() {
        return report;
    }

    public void setReport(WeeklyReportImpl report) {
        this.report = report;
    }
}
