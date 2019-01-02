package com.example.saint.medmanager.firebase_model;

public class AddMedication {

    String userId;
    String drug_name;
    String description;
    String frequency;
    String day;
    String month;

    public AddMedication() {

    }

    public AddMedication(String userId, String drug_name, String description, String frequency, String day, String month) {
        this.userId = userId;
        this.drug_name = drug_name;
        this.description = description;
        this.frequency = frequency;
        this.day = day;
        this.month = month;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
