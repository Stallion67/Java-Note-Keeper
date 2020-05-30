package com.example.notekeeper;

import android.annotation.SuppressLint;
import android.content.pm.ModuleInfo;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ParcelCreator")
public final class CourseInfo implements Parcelable {
 private final String  mCourseID;
 private final String mTitle;
 private final List<ModuleInfo> mModules;

 public CourseInfo (String courseId, String title, List<ModuleInfo> modules){
     mCourseID=courseId;
     mTitle=title;
     mModules=modules;
 }

 @RequiresApi(api = Build.VERSION_CODES.Q)
 private CourseInfo (Parcel source){
     mCourseID=source.readString();
     mTitle= source.readString();
     mModules= new ArrayList<>();
     source.readTypedList(mModules, ModuleInfo.CREATOR);
 }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCourseID);
        dest.writeString(mTitle);
        dest.writeTypedList(mModules);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CourseInfo> CREATOR = new Creator<CourseInfo>() {
        @Override
        public CourseInfo createFromParcel(Parcel in) {
            return new CourseInfo(in);
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };

    public String getmCourseID (){return mCourseID;}
}
