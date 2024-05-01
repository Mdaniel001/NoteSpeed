package co.edu.uniminuto.model;

import android.content.Context;
import android.view.View;

import co.edu.uniminuto.entity.Note;

public class NoteDao {
    private ManagerDataBase managerDataBase;
    Context context;
    View view;
    private Note note;

    public NoteDao(Context context, View view) {
        this.context = context;
        this.view = view;
        managerDataBase = new ManagerDataBase(this.context);
    }
}
