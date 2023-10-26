/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class SubjectSetting {
    private int settingID;
    private String subjectID;
    private String settingGroup;
    private String settingName;
    private String settingValue;
    private int displayOrder;
    private String description;
    private Subject s;
    private int status;

    public SubjectSetting(int settingID, String subjectID, String settingGroup, String settingName, String settingValue, int displayOrder, String description, Subject s, int status) {
        this.settingID = settingID;
        this.subjectID = subjectID;
        this.settingGroup = settingGroup;
        this.settingName = settingName;
        this.settingValue = settingValue;
        this.displayOrder = displayOrder;
        this.description = description;
        this.s = s;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

    public Subject getS() {
        return s;
    }

    public void setS(Subject s) {
        this.s = s;
    }
    
    
    
    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSettingGroup() {
        return settingGroup;
    }

    public void setSettingGroup(String settingGroup) {
        this.settingGroup = settingGroup;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
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
    
    
}
