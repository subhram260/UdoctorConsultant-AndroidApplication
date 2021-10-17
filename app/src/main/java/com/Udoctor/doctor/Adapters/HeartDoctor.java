package com.Udoctor.doctor.Adapters;

import android.os.Parcel;
import android.os.Parcelable;

public class HeartDoctor implements Parcelable {
    String imageHeart,nameHeart,specialHeart;

    public HeartDoctor()
    { }
    public HeartDoctor(String imageHeart, String nameHeart, String specialHeart) {
        this.imageHeart = imageHeart;
        this.nameHeart = nameHeart;
        this.specialHeart = specialHeart;
    }

    protected HeartDoctor(Parcel in) {
        imageHeart = in.readString();
        nameHeart = in.readString();
        specialHeart = in.readString();
    }

    public static final Creator<HeartDoctor> CREATOR = new Creator<HeartDoctor>() {
        @Override
        public HeartDoctor createFromParcel(Parcel in) {
            return new HeartDoctor(in);
        }

        @Override
        public HeartDoctor[] newArray(int size) {
            return new HeartDoctor[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageHeart);
        dest.writeString(nameHeart);
        dest.writeString(specialHeart);
    }
}
