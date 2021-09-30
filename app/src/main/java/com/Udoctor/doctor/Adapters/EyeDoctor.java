package com.Udoctor.doctor.Adapters;

public class EyeDoctor {

    String imageEye,nameEye,specialEye;

    public EyeDoctor()
        { }

    public EyeDoctor(String imageEye, String nameEye, String specialEye) {
        this.imageEye = imageEye;
        this.nameEye = nameEye;
        this.specialEye = specialEye;
    }

    public String getImageEye() {
        return imageEye;
    }

    public void setImageEye(String imageEye) {
        this.imageEye = imageEye;
    }

    public String getNameEye() {
        return nameEye;
    }

    public void setNameEye(String nameEye) {
        this.nameEye = nameEye;
    }

    public String getSpecialEye() {
        return specialEye;
    }

    public void setSpecialEye(String specialEye) {
        this.specialEye = specialEye;
    }
}


