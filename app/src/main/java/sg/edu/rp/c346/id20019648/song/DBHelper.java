package sg.edu.rp.c346.id20019648.song;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Collection;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "songs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String CONTENT_SINGER = "singer";
    private static final String COLUMN_STAR = "star";
    private static final String COLUMN_YEAR = "year";
    private static final String TABLE_SONG = "songs";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, " + COLUMN_STAR + " TEXT, " + COLUMN_YEAR + " TEXT, " + CONTENT_SINGER  + " TEXT ) ";
        db.execSQL(sql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN  module_name TEXT ");
        onCreate(db);
    }

    public void insertSong(String title, String singers, int year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTENT_SINGER, singers);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STAR, stars);

        db.insert(TABLE_SONG, null, values);
        db.close();


    }

    // retrieve/show all records
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();

        String sql = "SELECT " + COLUMN_ID + ","
                + CONTENT_SINGER + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getAllSongByStars(int stars) {
        ArrayList<Song> notes = new ArrayList<Song>();

        String[] columns = {"_id", "song_content"};
        String condition = COLUMN_TITLE + " Like ?";
        //keyword is what user entered
        String[] args = {"%" + stars + "%"};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        //iterate through the dataset
        cursor.close();
        db.close();
        return notes;
    }

    public int updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();

        //SET statement in SQL
        ContentValues values = new ContentValues();
        String title = Song.getTitle;
        values.put(COLUMN_TITLE, title);

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(Song.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);

        db.close();
        return result;
    }


    public int deleteSong(int id) {
        String condition = COLUMN_ID + " = ?";
        String[] parameters = {String.valueOf(id)};
        SQLiteDatabase db = this.getWritableDatabase();
        //1st parameter is table name, 2nd is WHERE condition, 3rd replaces all ? in 2nd parameter
        int rowsDeleted = db.delete(TABLE_SONG, condition, parameters);
        return rowsDeleted;
    }
}