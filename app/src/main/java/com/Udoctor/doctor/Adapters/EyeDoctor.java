package com.Udoctor.doctor.Adapters;

import android.os.Parcel;
import android.os.Parcelable;

public class EyeDoctor implements Parcelable {

    String imageEye,nameEye,specialEye,did;

    public EyeDoctor()
        { }

    public EyeDoctor(String did,String imageEye, String nameEye, String specialEye) {
        this.imageEye = imageEye;
        this.nameEye = nameEye;
        this.specialEye = specialEye;
        this.did=did;
    }

    protected EyeDoctor(Parcel in) {
        imageEye = in.readString();
        nameEye = in.readString();
        specialEye = in.readString();
        did = in.readString();
    }

    public static final Creator<EyeDoctor> CREATOR = new Creator<EyeDoctor>() {
        @Override
        public EyeDoctor createFromParcel(Parcel in) {
            return new EyeDoctor(in);
        }

        @Override
        public EyeDoctor[] newArray(int size) {
            return new EyeDoctor[size];
        }
    };

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

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageEye);
        dest.writeString(nameEye);
        dest.writeString(specialEye);
        dest.writeString(did);
    }
}


