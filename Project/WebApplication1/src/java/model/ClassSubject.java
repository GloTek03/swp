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
public class ClassSubject {
    private int classID;
    private int subjectID;
    private String semester;
    private Date startDate;
    private Date endDate;
    private String className;
    private String subjectName;
    
    private Time startTime;
    private Time endTime;
    
    private String startS;
    private String endS;
    
    private String startT;
    private String endT;
    
    private String subjectDescription;

    public ClassSubject(int classID, String semester, int subjectID, Date startDate, Date endDate) {
        this.classID = classID;
        this.semester = semester;
        this.subjectID = subjectID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getSemester() {
        return semester;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public String getStartS() {
        return startS;
    }

    public void setStartS(String startS) {
        this.startS = startS;
    }

    public String getEndS() {
        return endS;
    }

    public void setEndS(String endS) {
        this.endS = endS;
    }

    public String getStartT() {
        return startT;
    }

    public void setStartT(String startT) {
        this.startT = startT;
    }

    public String getEndT() {
        return endT;
    }

    public void setEndT(String endT) {
        this.endT = endT;
    }
    
    

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    

    public ClassSubject(int classID, int subjectID) {
        this.classID = classID;
        this.subjectID = subjectID;
    }

    public ClassSubject(int classID, int subjectID, String semester, Date startDate, Date endDate, Time startTime, Time endTime, String className, String subjectName, String subjectDescription) {
        this.classID = classID;
        this.subjectID = subjectID;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.className = className;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }
    
    
    
    
    

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
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
