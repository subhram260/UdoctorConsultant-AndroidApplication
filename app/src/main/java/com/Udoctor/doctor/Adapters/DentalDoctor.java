package com.Udoctor.doctor.Adapters;

public class DentalDoctor {
    String imageDental,nameDental,specialDental;

    DentalDoctor(){};

    public DentalDoctor(String imageDental, String nameDental, String specialDental) {
        this.imageDental = imageDental;
        this.nameDental = nameDental;
        this.specialDental = specialDental;
    }

    public String getImageDental() {
        return imageDental;
    }

    public void setImageDental(String imageDental) {
        this.imageDental = imageDental;
    }

    public String getNameDental() {
        return nameDental;
    }

    public void setNameDental(String nameDental) {
        this.nameDental = nameDental;
    }

    public String getSpecialDental() {
        return specialDental;
    }

    public void setSpecialDental(String specialDental) {
        this.specialDental = specialDental;
    }
}
