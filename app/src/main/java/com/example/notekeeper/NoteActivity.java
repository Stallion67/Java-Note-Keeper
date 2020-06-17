package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static  final String NOTE_POSITION ="com.example.notekeeper.NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private NoteInfo mNote;
    private Spinner mSpinnerCourses;
    private EditText mTextNoteTitle;
    private EditText mTextNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSpinnerCourses = findViewById(R.id.spinner_courses);

        //importing  courses
        List<CourseInfo> courses= DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adaptarCourses= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);

        adaptarCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerCourses.setAdapter(adaptarCourses);


         readDisplayStateValue(); //External Methord

        //References to Edit Text on the note
        mTextNoteTitle = findViewById(R.id.text_note_title);
        mTextNoteText = findViewById(R.id.text_note_text);

        //External Methord to refernce spinner ans edit tex
        displayNote(mSpinnerCourses, mTextNoteTitle, mTextNoteText);

    }


    @Override
    protected void onPause() {
        super.onPause();
        saveNote();
    }

    private void saveNote() { //This class takes wales fro fields onthe screen and passes them on
             mNote.setCourse((CourseInfo) mSpinnerCourses.getSelectedItem());
             // getiing selecting item
            mNote.setTitle(mTextNoteTitle.getText().toString());
            mNote.setText(mTextNoteText.getText().toString());
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
       //getting list of courses from data manger
        List<CourseInfo> courses =DataManager.getInstance().getCourses();
        int courseIndex =courses.indexOf(mNote.getmCourse()); // determinning index of course
        spinnerCourses.setSelection(courseIndex); //setting spinner
        //seting each of member Values
        textNoteTitle.setText(mNote.getTitle());
        textNoteText.setText(mNote.getText());
    }

    private void readDisplayStateValue(){
        Intent intent = getIntent();
//        mNote = intent.getParcelableExtra(NOTE_POSITION);// Line of codee modified to below
       int  position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
//       mIsNewNote=position ==POSITION_NOT_SET;
//        if(!mIsNewNote)
//            mNote =DataManager.getInstance().getNotes().get(position);

        if (mIsNewNote){
            createNewNote();
        }
        else {
            mNote =DataManager.getInstance().getNotes().get(position);

        }

    }

    private void createNewNote() {
        DataManager dm = DataManager.getInstance();
        mNotePosition = dm.createNewNote();
        mNote = dm.getNotes().get(mNotePostion); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_mail) {
            SendEmail();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SendEmail() {
        CourseInfo course =(CourseInfo) mSpinnerCourses.getSelectedItem();
        String subject = mTextNoteTitle.getText().toString();
        String  text = "Checkout what I learned in the Pluralsight course \""+ course.getTitle()+"\"\n" + mTextNoteText.getText();
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfr2022");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        startActivity(intent);

    }
}
