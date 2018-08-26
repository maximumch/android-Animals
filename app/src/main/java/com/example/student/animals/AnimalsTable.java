package com.example.student.animals;

import android.database.sqlite.SQLiteDatabase;

class AnimalsTable {
    public static final String TABLE_ANIMALS = "animals";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANIMAL = "animal";

    public static final String CREATE = " create table animals  " +
            "  (  _id integer primary key autoincrement,        " +
            "     animal text not null )   ;   ";



    public static void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
        populate(sqLiteDatabase);
    }

    private static void populate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" insert into animals  (animal)  values ('sparrow')");
        sqLiteDatabase.execSQL(" insert into animals  (animal)  values ('cow')");
        sqLiteDatabase.execSQL(" insert into animals  (animal)  values ('birdy')");
        sqLiteDatabase.execSQL(" insert into animals  (animal)  values ('doggy')");
        sqLiteDatabase.execSQL(" insert into animals  (animal)  values ('kitty')");
        sqLiteDatabase.execSQL(" insert into animals  (animal)  values ('elephant')");
        sqLiteDatabase.execSQL(" insert into animals  (animal)  values ('horse')");
    }

    public static void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
}
