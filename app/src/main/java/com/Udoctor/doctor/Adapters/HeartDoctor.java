package com.Udoctor.doctor.Adapters;

public class HeartDoctor {
    String imageHeart,nameHeart,specialHeart;

    public HeartDoctor()
    { }
    public HeartDoctor(String imageHeart, String nameHeart, String specialHeart) {
        this.imageHeart = imageHeart;
        this.nameHeart = nameHeart;
        this.specialHeart = specialHeart;
    }

    public String getImageHeart() {
        return imageHeart;
    }

    public void setImageHeart(String imageHeart) {
        this.imageHeart = imageHeart;
    }

    public String getNameHeart() {
        return nameHeart;
    }

    public void setNameHeart(String nameHeart) {
        this.nameHeart = nameHeart;
    }

    public String getSpecialHeart() {
        return specialHeart;
    }

    public void setSpecialHeart(String specialHeart) {
        this.specialHeart = specialHeart;
    }
}
