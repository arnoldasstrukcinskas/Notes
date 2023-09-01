package lt.arnoldas.notes.repository;

import static lt.arnoldas.notes.repository.MainDatabase.DATABASE_VERSION;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lt.arnoldas.notes.Note;

@Database(
        entities = {Note.class},
        version = DATABASE_VERSION,
        exportSchema = false        //i ateiti, neeksportuosim
)
public abstract class MainDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "main.db";

    private static MainDatabase instance;

    public static synchronized MainDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context,
                    MainDatabase.class,
                    DATABASE_NAME
            )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract NoteDao noteDao();
}
