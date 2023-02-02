package com.example.inclass_krishnan9285.InClass02;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Avatar implements Parcelable {
    private int imageResource;

    Avatar(int imageResource) {
        this.imageResource = imageResource;
    }

    protected Avatar(Parcel in) {
        imageResource = in.readInt();
    }

    public static final Creator<Avatar> CREATOR = new Creator<Avatar>() {
        @Override
        public Avatar createFromParcel(Parcel in) {
            return new Avatar(in);
        }

        @Override
        public Avatar[] newArray(int size) {
            return new Avatar[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(imageResource);

    }

    @Override
    public String toString() {
        return "Activity{" +
                "imageResource=" + imageResource +
                '}';
    }

    public int getImageResource() {
        return imageResource;
    }
}
