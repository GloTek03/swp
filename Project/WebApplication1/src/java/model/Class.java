/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author acer
 */
public class Class {
    private int classID;
    private String className;
    private String majorID;
    private String majorName;
    private int activate;
    private Subject s;
    private SystemSetting semester;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private Account trainer;

    public Class(int classID, String className, String majorID) {
        this.classID = classID;
        this.className = className;
        this.majorID = majorID;
    }

    public Class(int classID, String className, String majorID, int activate, SystemSetting semester, String majorName, Subject s) {
        this.classID = classID;
        this.className = className;
        this.majorID = majorID;
        this.majorName = majorName;
        this.s = s;
        this.activate = activate;
        this.semester = semester;
    }

    public Class() {
    }
    
    

    public Class(int classID, String className, String majorID, String majorName) {
        this.classID = classID;
        this.className = className;
        this.majorID = majorID;
        this.majorName = majorName;
    }

    public Class(int classID, String className, String majorID, String majorName, int activate, Subject s, SystemSetting semester, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.classID = classID;
        this.className = className;
        this.majorID = majorID;
        this.majorName = majorName;
        this.activate = activate;
        this.s = s;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Account getTrainer() {
        return trainer;
    }

    public void setTrainer(Account trainer) {
        this.trainer = trainer;
    }
    
    

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    
    

    public Subject getS() {
        return s;
    }

    public void setS(Subject s) {
        this.s = s;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public SystemSetting getSemester() {
        return semester;
    }

    public void setSemester(SystemSetting semester) {
        this.semester = semester;
    }
    
    

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajorID() {
        return majorID;
    }

    public void setMajorID(String majorID) {
        this.majorID = majorID;
    }



    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    
    
}
