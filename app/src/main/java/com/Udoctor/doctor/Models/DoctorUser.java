package com.Udoctor.doctor.Models;

public class DoctorUser {
    String Did,profilePic, DoctorName, Doctormail, Doctorpassword, Doctorid, Doctorphone, Doctorgender, DoctorEducation, DoctorAboutSummery, DoctorAddress,type,spelization;


    public DoctorUser(String Did,String profilePic, String doctorName, String doctormail, String doctorpassword, String doctorid, String doctorphone, String doctorgender, String doctorEducation, String doctorAboutSummery, String doctorAddress, String type, String spelization) {
        this.profilePic = profilePic;
        this.DoctorName = doctorName;
        this.Doctormail = doctormail;
        this.Doctorpassword = doctorpassword;
        this.Doctorid = doctorid;
        this.Doctorphone = doctorphone;
        this.Doctorgender = doctorgender;
        this.DoctorEducation = doctorEducation;
        this.DoctorAboutSummery = doctorAboutSummery;
        this.DoctorAddress = doctorAddress;
        this.type = type;
        this.spelization = spelization;
        this.Did = Did;
    }

    public DoctorUser(){}

    //Signup constructor
    public DoctorUser(String Did,String DoctorName, String Doctormail, String Doctorpassword) {
        this.DoctorName = DoctorName;
        this.Doctormail = Doctormail;
        this.Doctorpassword = Doctorpassword;
        this.Did = Did;
    }

    public String getDid() {
        return Did;
    }

    public void setDid(String did) {
        Did = did;
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
