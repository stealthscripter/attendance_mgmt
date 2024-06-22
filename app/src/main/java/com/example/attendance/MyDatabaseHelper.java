package com.example.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "attendance_report";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "report_table";
    private static final String COLUMN_ID = "_id";
    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_NAME = "student_name";
    private static final String ATTENDANCE_STATE = "attendance_state";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE "+TABLE_NAME +" ("+ COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        STUDENT_ID+" TEXT, "+
                        STUDENT_NAME+" TEXT, "+
                        ATTENDANCE_STATE+" TEXT); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    void addAttendanceData(String studId, String studName, String attendanceState){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_ID,studId);
        cv.put(STUDENT_ID,studName);
        cv.put(ATTENDANCE_STATE, attendanceState);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Attendance data Saved", Toast.LENGTH_SHORT).show();
        }


    }
}
