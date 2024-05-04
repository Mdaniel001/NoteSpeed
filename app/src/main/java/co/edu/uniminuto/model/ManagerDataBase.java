package co.edu.uniminuto.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class ManagerDataBase extends SQLiteOpenHelper{
    private static final String DATA_BASE="dbUser";
    private static final int VERSION=1;
    private static final String TABLE_USERS ="users";
    private static  final String TABLE_NOTES = "notes";
    private static  final String TABLE_TASKS = "tasks";

    private static final String CREATE_TABLE_USERS= "CREATE TABLE "+TABLE_USERS+" (use_id INTEGER  " +
            "PRIMARY KEY AUTOINCREMENT NOT NULL, use_names varchar(150),  use_lastNames varchar(150), " +
            " use_email varchar(150),  use_password varchar (250) NOT NULL,use_password_confirm varchar (250)," +
            " use_link_imagen varchar(250), use_status varchar(1) NOT NULL); ";

    private static final String CREATE_TABLE_NOTES= "CREATE TABLE "+TABLE_NOTES+" (not_id INTEGER  " +
            "PRIMARY KEY AUTOINCREMENT NOT NULL, not_title varchar(100),  not_descriptions varchar(500)," +
            "not_dateNote VARCHAR(50), not_user_id VARCHAR(50), not_status varchar(1) NOT NULL); ";

    private static final String CREATE_TABLE_TASKS= "CREATE TABLE "+TABLE_TASKS+" (task_id INTEGER  " +
            "PRIMARY KEY AUTOINCREMENT NOT NULL, task_title varchar(100),task_nameList varchar(100),  task_descriptions varchar(500)," +
            "task_dateTasks VARCHAR(50), task_user_id VARCHAR(50), task_status varchar(1) NOT NULL); ";

    private static final String DELETE_TABLE ="DROP TABLE IF EXISTS "+TABLE_USERS;
    private static final String DELETE_TABLE_NOTES ="DROP TABLE IF EXISTS "+TABLE_NOTES;
    private static final String DELETE_TABLE_TASK ="DROP TABLE IF EXISTS "+TABLE_TASKS;


    public ManagerDataBase(@Nullable Context context){super(context,DATA_BASE,null,VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_NOTES);
        db.execSQL(CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
        db.execSQL(DELETE_TABLE_NOTES);
        onCreate(db);
        db.execSQL(DELETE_TABLE_TASK);
        onCreate(db);
    }

    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE use_email = ? AND use_password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }



}
