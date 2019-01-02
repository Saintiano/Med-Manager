package com.example.saint.medmanager.firebase_model;

public class AddDoctors {

    private String userId;
    private String doctor_userName;
    private String doctor_Name;
    private String doctorate_position;
    private String doctor_specialization;
    private String doctor_hospitalDepartment;
    private String doctor_briefDescription;
    private String doctor_Email;
    private String doctor_phoneNumber;
    private String doctor_imageUrl;

    public AddDoctors(String userId, String doctor_userName, String doctor_Name, String doctorate_position, String doctor_specialization, String doctor_hospitalDepartment, String doctor_briefDescription, String doctor_Email, String doctor_phoneNumber, String doctor_imageUrl) {
        this.userId = userId;
        this.doctor_userName = doctor_userName;
        this.doctor_Name = doctor_Name;
        this.doctorate_position = doctorate_position;
        this.doctor_specialization = doctor_specialization;
        this.doctor_hospitalDepartment = doctor_hospitalDepartment;
        this.doctor_briefDescription = doctor_briefDescription;
        this.doctor_Email = doctor_Email;
        this.doctor_phoneNumber = doctor_phoneNumber;
        this.doctor_imageUrl = doctor_imageUrl;
    }

    public AddDoctors() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctor_userName() {
        return doctor_userName;
    }

    public void setDoctor_userName(String doctor_userName) {
        this.doctor_userName = doctor_userName;
    }

    public String getDoctor_Name() {
        return doctor_Name;
    }

    public void setDoctor_Name(String doctor_Name) {
        this.doctor_Name = doctor_Name;
    }

    public String getDoctorate_position() {
        return doctorate_position;
    }

    public void setDoctorate_position(String doctorate_position) {
        this.doctorate_position = doctorate_position;
    }

    public String getDoctor_specialization() {
        return doctor_specialization;
    }

    public void setDoctor_specialization(String doctor_specialization) {
        this.doctor_specialization = doctor_specialization;
    }

    public String getDoctor_hospitalDepartment() {
        return doctor_hospitalDepartment;
    }

    public void setDoctor_hospitalDepartment(String doctor_hospitalDepartment) {
        this.doctor_hospitalDepartment = doctor_hospitalDepartment;
    }

    public String getDoctor_briefDescription() {
        return doctor_briefDescription;
    }

    public void setDoctor_briefDescription(String doctor_briefDescription) {
        this.doctor_briefDescription = doctor_briefDescription;
    }

    public String getDoctor_Email() {
        return doctor_Email;
    }

    public void setDoctor_Email(String doctor_Email) {
        this.doctor_Email = doctor_Email;
    }

    public String getDoctor_phoneNumber() {
        return doctor_phoneNumber;
    }

    public void setDoctor_phoneNumber(String doctor_phoneNumber) {
        this.doctor_phoneNumber = doctor_phoneNumber;
    }

    public String getDoctor_imageUrl() {
        return doctor_imageUrl;
    }

    public void setDoctor_imageUrl(String doctor_imageUrl) {
        this.doctor_imageUrl = doctor_imageUrl;
    }
}
