package co.edu.uniminuto.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import co.edu.uniminuto.entity.Note;

public class NoteDao {
    private static ManagerDataBase managerDataBase;
    Context context;
    View view;
    private Note note;








    public NoteDao(Context context, View view) {
        this.context = context;
        this.view = view;
        managerDataBase = new ManagerDataBase(this.context);
    }


    public static void insertNote(Note note) {
        SQLiteDatabase db = managerDataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("not_title", note.getTitle());
        values.put("not_descriptions", note.getDescriptions());
        values.put("not_dateNote", note.getDateNote());
        values.put("not_status", "A"); // Set the default status to "A" (for active)
        db.insert("notes", null, values);
        db.close();



    }


}
