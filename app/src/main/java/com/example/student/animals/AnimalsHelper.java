package com.example.student.animals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalsHelper extends SQLiteOpenHelper {
   public static final String database = "animals.db";
   public static final int version = 1;

    public AnimalsHelper(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        AnimalsTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        AnimalsTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
