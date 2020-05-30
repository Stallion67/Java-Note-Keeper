package com.example.notekeeper;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;


    private List<CourseInfo> mCourses= new ArrayList<>();
    private List<NoteInfo> mNotes = new ArrayList<>();

    static DataManager getInstance(){
        if (ourInstance==null){
            ourInstance = new DataManager();
        //    ourInstance.initializeCourses();
           // ourInstance.initializeExampleNotes();
        }
        return ourInstance;
    }

    public String getCurrentUserName(){return "Ben Okello";}

    public String getCurrentUSerEmail(){return "mwakaben@gmail.com";}

    public List<CourseInfo> getCourses(){return mCourses;}//retun a list of fcourses

    public  CourseInfo getCourse (String id){
        for (CourseInfo course:mCourses){
       //     if(id.equals(course.getourseID()))
                return course;
        }
        return null;
    }

    public List<NoteInfo> getNotes() {
        return null;
    }
}
