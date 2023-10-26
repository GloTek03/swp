/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author acer
 */
public class Lesson {
    private int lessonID;
    private Chapter chapter;
    private String lessonName;
    private SystemSetting lessonType;
    private int displayOrder;
    private String videoLink;
    private String description;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private int status;
    private Quiz quiz;
    private Assignment asm;
    private boolean achieve;
    //private List<Material> material;

    public Lesson() {
    }

    public Lesson(int lessonID, Chapter chapter, String lessonName, SystemSetting lessonType, int displayOrder, String videoLink, String description, Account createdBy, Timestamp createdAt, Account updatedBy, Timestamp updatedAt, int status, Quiz quiz, Assignment asm) {
        this.lessonID = lessonID;
        this.chapter = chapter;
        this.lessonName = lessonName;
        this.lessonType = lessonType;
        this.displayOrder = displayOrder;
        this.videoLink = videoLink;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
        this.quiz = quiz;
        this.asm = asm;
    }

    public boolean isAchieve() {
        return achieve;
    }

    public void setAchieve(boolean achieve) {
        this.achieve = achieve;
    }

    

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public SystemSetting getLessonType() {
        return lessonType;
    }

    public void setLessonType(SystemSetting lessonType) {
        this.lessonType = lessonType;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Assignment getAsm() {
        return asm;
    }

    public void setAsm(Assignment asm) {
        this.asm = asm;
    }

    
    

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Account getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Account updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
