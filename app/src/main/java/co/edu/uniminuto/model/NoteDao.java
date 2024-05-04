package co.edu.uniminuto.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import co.edu.uniminuto.entity.Note;

public class NoteDao {
    private static ManagerDataBase managerDataBase;
    Context context;
    View view;
    private Note note;





    public NoteDao(Context context) {
        this.context = context;
        this.view = view;
        managerDataBase = new ManagerDataBase(this.context);
    }

  //metodo Insertar nota
    public static void insertNote(Note note, View view) {
        try {
            if (managerDataBase != null) {
                SQLiteDatabase db = managerDataBase.getWritableDatabase();
                if (db != null) {
                    ContentValues values = new ContentValues();
                    values.put("not_title", note.getTitle());
                    values.put("not_descriptions", note.getDescriptions());
                    values.put("not_dateNote", note.getDateNote());
                    values.put("not_status", "A"); // Set the default status to "A" (for active)
                    long cod = db.insert("notes", null, values);
                    Snackbar.make(view, "Se ha agregado una nota con código: " + cod, Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view, "No se pudo acceder a la base de datos", Snackbar.LENGTH_LONG).show();
                }
            } else {
                Snackbar.make(view, "Manager de base de datos no inicializado", Snackbar.LENGTH_LONG).show();
            }
        } catch (android.database.SQLException e) {
            Log.e("DB", "Error al insertar la nota: " + e.getMessage());
            Snackbar.make(view, "Error al insertar la nota", Snackbar.LENGTH_LONG).show();
        }
    }


    //Mostar Notas en Lista

    public ArrayList<Note> getNotaList(){
        ArrayList<Note> lvNotas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = managerDataBase.getReadableDatabase();
            String query = "SELECT * FROM notas WHERE not_status = 'A';";
            cursor = db.rawQuery(query, null);
            if (cursor!= null && cursor.moveToFirst()) {
                int titleIndex = cursor.getColumnIndex("not_title");
                int dateIndex = cursor.getColumnIndex("not_dateNote");
                int descriptionIndex = cursor.getColumnIndex("not_descriptions");
                do {
                    Note nota = new Note();
                    nota.setTitle(cursor.getString(titleIndex));
                    nota.setDateNote(cursor.getString(dateIndex));
                    nota.setDescriptions(cursor.getString(descriptionIndex));
                    lvNotas.add(nota);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            Log.e("DB", "Error al obtener la lista de notas: " + e.getMessage());
        } finally {
            if (cursor!= null) {
                cursor.close();
            }
            if (db!= null) {
                db.close();
            }
        }
        return lvNotas;
    }




    // Método para eliminar una nota por su título
    public void deleteNoteByTitle(String title, View view) {
        try {
            SQLiteDatabase db = managerDataBase.getWritableDatabase();
            if (db != null) {
                int rowsAffected = db.delete("notes", "not_title = ?", new String[]{title});
                if (rowsAffected > 0) {
                    Snackbar.make(view, "Se ha eliminado la nota con éxito", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view, "No se encontró la nota con ese título", Snackbar.LENGTH_LONG).show();
                }
            } else {
                Snackbar.make(view, "No se pudo acceder a la base de datos", Snackbar.LENGTH_LONG).show();
            }
        } catch (android.database.SQLException e) {
            Log.e("DB", "Error al eliminar la nota: " + e.getMessage());
            Snackbar.make(view, "Error al eliminar la nota", Snackbar.LENGTH_LONG).show();
        }
    }




    // Método para buscar una nota por su título
    public Note getNoteByTitleAndStatus(String title) {
        final String QUERY = "SELECT * FROM notes WHERE not_title =? AND not_status = 'A';";
        try (SQLiteDatabase db = managerDataBase.getReadableDatabase();
             Cursor cursor = db.rawQuery(QUERY, new String[]{title})) {
            if (cursor!= null && cursor.moveToFirst()) {
                Note note = new Note();
                int titleIndex = cursor.getColumnIndex("not_title");
                int descriptionsIndex = cursor.getColumnIndex("not_descriptions");
                int dateNoteIndex = cursor.getColumnIndex("not_dateNote");

                if (titleIndex!= -1) {
                    note.setTitle(cursor.getString(titleIndex));
                }
                if (descriptionsIndex!= -1) {
                    note.setDescriptions(cursor.getString(descriptionsIndex));
                }
                if (dateNoteIndex!= -1) {
                    note.setDateNote(cursor.getString(dateNoteIndex));
                }
                return note;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error al obtener la nota: " + e.getMessage(), e);        }
        return null;
    }
}
