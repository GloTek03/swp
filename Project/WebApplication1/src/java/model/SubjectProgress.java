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
public class SubjectProgress {
    private String subjectID;
    private int subjectProgressID;
    private String semester;
    private String subjectProgressName;
    private String description;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    
    private String startS;
    private String startT;
    private String endS;
    private String endT;

    public SubjectProgress(String subjectID, String semester, int subjectProgressID, String subjectProgressName, String description, Date startDate, Time startTime, Date endDate, Time endTime) {
        this.subjectID = subjectID;
        this.semester = semester;
        this.subjectProgressID = subjectProgressID;
        this.subjectProgressName = subjectProgressName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    

    public String getStartS() {
        return startS;
    }

    public void setStartS(String startS) {
        this.startS = startS;
    }

    public String getStartT() {
        return startT;
    }

    public void setStartT(String startT) {
        this.startT = startT;
    }

    public String getEndS() {
        return endS;
    }

    public void setEndS(String endS) {
        this.endS = endS;
    }

    public String getEndT() {
        return endT;
    }

    public void setEndT(String endT) {
        this.endT = endT;
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
    
    

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectProgressID() {
        return subjectProgressID;
    }

    public void setSubjectProgressID(int subjectProgressID) {
        this.subjectProgressID = subjectProgressID;
    }

    public String getSubjectProgressName() {
        return subjectProgressName;
    }

    public void setSubjectProgressName(String subjectProgressName) {
        this.subjectProgressName = subjectProgressName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    
    
}
