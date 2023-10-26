/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Quoc Trumg
 */
public class SystemSetting {
    private int setting_id;
    private String setting_name;
    private String setting_type;
    private int display_order;
    private boolean setting_status;

    public SystemSetting() {
    }

    public SystemSetting(String setting_name, String setting_type, boolean setting_status) {
        this.setting_name = setting_name;
        this.setting_type = setting_type;
        this.setting_status = setting_status;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }
    
    

    public String getSetting_name() {
        return setting_name;
    }

    public void setSetting_name(String setting_name) {
        this.setting_name = setting_name;
    }

    public String getSetting_type() {
        return setting_type;
    }

    public void setSetting_type(String setting_type) {
        this.setting_type = setting_type;
    }

    public boolean isSetting_status() {
        return setting_status;
    }

    public void setSetting_status(boolean setting_status) {
        this.setting_status = setting_status;
    }

}
