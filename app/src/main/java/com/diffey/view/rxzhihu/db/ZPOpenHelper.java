package com.diffey.view.rxzhihu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by diff on 2016/2/16.
 */
public class ZPOpenHelper extends SQLiteOpenHelper {
    private static ZPOpenHelper zpOpenHelper;

    private ZPOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static ZPOpenHelper getInstance(Context context) {
        if (zpOpenHelper == null) {
            synchronized (ZPOpenHelper.class) {
                if (zpOpenHelper == null) {
                    zpOpenHelper = new ZPOpenHelper(context.getApplicationContext(), DBContant.DB_NAME, null, DBContant.DB_VERSION);
                }
            }
        }
        return zpOpenHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createReadTable = "create table " + DBContant.TABLE_READ + "("
                + DBContant.READ_COL_NID + " text)";
        db.execSQL(createReadTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
