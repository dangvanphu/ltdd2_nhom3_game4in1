package com.example.pc.ltdd2_game.data_access;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pc.ltdd2_game.data_models.ItemContinue;

import java.util.ArrayList;


public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "luu_diem_continute";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "item_continue";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "userName";
    private static final String COLUMN_SCORE = "score";

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_SCORE + " INTEGER)"

        );
    }
    public void saveDatabase(ArrayList<ItemContinue> items){
        SQLiteDatabase db = getWritableDatabase();
        for(ItemContinue item : items){
            ContentValues values =  new ContentValues();
            values.put(COLUMN_NAME, item.getUserName());
            values.put(COLUMN_SCORE, item.getScore());
            db.insert(TABLE_NAME,null,values);
        }
        db.close();
    }
    public void loadDatabase(ArrayList<ItemContinue> items)
    {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {COLUMN_NAME,COLUMN_SCORE},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int score = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SCORE)));

                items.add(new ItemContinue(name,score));
            }while (cursor.moveToNext());
        }

        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
