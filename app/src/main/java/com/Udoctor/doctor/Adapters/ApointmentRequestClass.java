package com.Udoctor.doctor.Adapters;

import android.os.Parcel;
import android.os.Parcelable;

public class ApointmentRequestClass implements Parcelable{
    String PatientName,PatientEmail,PatientPhone,PatientImage,patientGender,Uid;

    public ApointmentRequestClass(String Uid,String patientName, String patientEmail, String patientPhone, String patientImage,String patientGender) {
        this.PatientName = patientName;
        this.PatientEmail = patientEmail;
        this.PatientPhone = patientPhone;
        this.PatientImage = patientImage;
        this.patientGender= patientGender;
        this.Uid=Uid;
    }

    public ApointmentRequestClass() {
    }

    protected ApointmentRequestClass(Parcel in) {
        PatientName = in.readString();
        PatientEmail = in.readString();
        PatientPhone = in.readString();
        PatientImage = in.readString();
        patientGender = in.readString();
        Uid = in.readString();
    }

    public static final Creator<ApointmentRequestClass> CREATOR = new Creator<ApointmentRequestClass>() {
        @Override
        public ApointmentRequestClass createFromParcel(Parcel in) {
            return new ApointmentRequestClass(in);
        }

        @Override
        public ApointmentRequestClass[] newArray(int size) {
            return new ApointmentRequestClass[size];
        }
    };

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientEmail() {
        return PatientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }

    public String getPatientPhone() {
        return PatientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        PatientPhone = patientPhone;
    }

    public String getPatientImage() {
        return PatientImage;
    }

    public void setPatientImage(String patientImage) {
        PatientImage = patientImage;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PatientName);
        dest.writeString(PatientEmail);
        dest.writeString(PatientPhone);
        dest.writeString(PatientImage);
        dest.writeString(patientGender);
        dest.writeString(Uid);
    }
}

