package com.Udoctor.doctor.Models;

public class DoctorUser {
    String profilePic, DoctorName, Doctormail, Doctorpassword, Doctorid, Doctorphone, Doctorgender, DoctorEducation, DoctorAboutSummery, DoctorAddress,type,spelization;


    public DoctorUser(String profilePic, String doctorName, String doctormail, String doctorpassword, String doctorid, String doctorphone, String doctorgender, String doctorEducation, String doctorAboutSummery, String doctorAddress, String type, String spelization) {
        this.profilePic = profilePic;
        DoctorName = doctorName;
        Doctormail = doctormail;
        Doctorpassword = doctorpassword;
        Doctorid = doctorid;
        Doctorphone = doctorphone;
        Doctorgender = doctorgender;
        DoctorEducation = doctorEducation;
        DoctorAboutSummery = doctorAboutSummery;
        DoctorAddress = doctorAddress;
        this.type = type;
        this.spelization = spelization;
    }

    public DoctorUser(){}

    //Signup constructor
    public DoctorUser(String DoctorName, String Doctormail, String Doctorpassword) {
        this.DoctorName = DoctorName;
        this.Doctormail = Doctormail;
        this.Doctorpassword = Doctorpassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctormail() {
        return Doctormail;
    }

    public void setDoctormail(String doctormail) {
        Doctormail = doctormail;
    }

    public String getDoctorpassword() {
        return Doctorpassword;
    }

    public void setDoctorpassword(String doctorpassword) {
        Doctorpassword = doctorpassword;
    }

    public String getDoctorid() {
        return Doctorid;
    }

    public void setDoctorid(String doctorid) {
        Doctorid = doctorid;
    }

    public String getDoctorphone() {
        return Doctorphone;
    }

    public void setDoctorphone(String doctorphone) {
        Doctorphone = doctorphone;
    }

    public String getDoctorgender() {
        return Doctorgender;
    }

    public void setDoctorgender(String doctorgender) {
        Doctorgender = doctorgender;
    }

    public String getDoctorEducation() {
        return DoctorEducation;
    }

    public void setDoctorEducation(String doctorEducation) {
        DoctorEducation = doctorEducation;
    }

    public String getDoctorAboutSummery() {
        return DoctorAboutSummery;
    }

    public void setDoctorAboutSummery(String doctorAboutSummery) {
        DoctorAboutSummery = doctorAboutSummery;
    }

    public String getDoctorAddress() {
        return DoctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        DoctorAddress = doctorAddress;
    }

    public String getSpelization() {
        return spelization;
    }

    public void setSpelization(String spelization) {
        this.spelization = spelization;
    }
}
