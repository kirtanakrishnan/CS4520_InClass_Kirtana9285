package com.example.inclass_krishnan9285.InClass02;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Result implements Parcelable {
    private String editTextName;
    private String editTextEmail;
    private String device;
    private String mood;
    private int avatarImageResource;
    private int moodImageResource;

    public Result(String editTextName, String editTextEmail, String device, String mood, int avatarImageResource,
                  int moodImageResource) {
        this.editTextName = editTextName;
        this.editTextEmail = editTextEmail;
        this.device = device;
        this.mood = mood;
        this.avatarImageResource = avatarImageResource;
        this.moodImageResource = moodImageResource;
    }

    public void setEditTextName(String editTextName) {
        this.editTextName = editTextName;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setEditTextEmail(String editTextEmail) {
        this.editTextEmail = editTextEmail;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setMoodImageResource(int moodImageResource) {
        this.moodImageResource = moodImageResource;
    }

    public void setAvatarImageResource(int avatarImageResource) {
        this.avatarImageResource = avatarImageResource;
    }

    public Result() {

    }

    protected Result(Parcel in) {
        editTextName = in.readString();
        editTextEmail = in.readString();
        device = in.readString();
        mood = in.readString();
        avatarImageResource = in.readInt();
        moodImageResource = in.readInt();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(editTextName);
        parcel.writeString(editTextEmail);
        parcel.writeString(device);
        parcel.writeString(mood);
        parcel.writeInt(avatarImageResource);
        parcel.writeInt(moodImageResource);

    }

    @Override
    public String toString() {
        return "Result{" +
                "editTextName='" + editTextName + '\'' +
                ", editTextEmail='" + editTextEmail + '\'' +
                ", device='" + device + '\'' +
                ", mood='" + mood + '\'' +
                ", avatarImageResource=" + avatarImageResource +
                ", moodImageResource=" + moodImageResource +
                '}';
    }

    public String getEditTextName() {
        return editTextName;
    }


    public String getEditTextEmail() {
        return editTextEmail;
    }

    public String getDevice() {
        return device;
    }

    public String getMood() {
        return mood;
    }

    public int getAvatarImageResource() {
        return avatarImageResource;
    }

    public int getMoodImageResource() {
        return moodImageResource;
    }


}
