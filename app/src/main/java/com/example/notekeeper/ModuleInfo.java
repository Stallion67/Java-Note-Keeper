package com.example.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class ModuleInfo implements Parcelable {
    private final String mModuleId;
    private final  String mTitle;
    private boolean mIsComplete = false;

    public ModuleInfo (String moduleId, String title){ this(moduleId, title, false); }
    public ModuleInfo (String moduleId, String title, boolean isComplete){
        mModuleId = moduleId;
        mTitle=title;
        mIsComplete=isComplete;
    }
  private  ModuleInfo(Parcel source){
        mModuleId= source.readString();
        mTitle=source.readString();
        mIsComplete=source.readByte()==1;
  }

    public static final Creator<ModuleInfo> CREATOR = new Creator<ModuleInfo>() {
        @Override
        public ModuleInfo createFromParcel(Parcel in) {
            return new ModuleInfo(in);
        }

        @Override
        public ModuleInfo[] newArray(int size) {
            return new ModuleInfo[size];
        }
    };

    public String getmModuleId(){
        return mModuleId;
  }
  public String getmTitle(){
        return mTitle;
  }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mModuleId);
        dest.writeString(mTitle);
        dest.writeByte((byte) (mIsComplete ? 1 : 0));
    }
}
