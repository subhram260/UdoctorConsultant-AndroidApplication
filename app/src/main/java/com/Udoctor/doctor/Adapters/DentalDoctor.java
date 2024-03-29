package com.Udoctor.doctor.Adapters;

import android.os.Parcel;
import android.os.Parcelable;

public class DentalDoctor implements Parcelable {
    String imageDental,nameDental,specialDental,did;

    public DentalDoctor(){};

    public DentalDoctor(String did,String imageDental, String nameDental, String specialDental) {
        this.imageDental = imageDental;
        this.nameDental = nameDental;
        this.specialDental = specialDental;
        this.did=did;
    }

    protected DentalDoctor(Parcel in) {
        imageDental = in.readString();
        nameDental = in.readString();
        specialDental = in.readString();
        did = in.readString();
    }

    public static final Creator<DentalDoctor> CREATOR = new Creator<DentalDoctor>() {
        @Override
        public DentalDoctor createFromParcel(Parcel in) {
            return new DentalDoctor(in);
        }

        @Override
        public DentalDoctor[] newArray(int size) {
            return new DentalDoctor[size];
        }
    };

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
        dest.writeString(imageDental);
        dest.writeString(nameDental);
        dest.writeString(specialDental);
        dest.writeString(did);
    }
}
