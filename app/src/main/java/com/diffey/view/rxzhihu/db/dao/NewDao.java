package com.diffey.view.rxzhihu.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diffey.view.rxzhihu.db.DBContant;
import com.diffey.view.rxzhihu.db.ZPOpenHelper;
import com.diffey.view.rxzhihu.db.bean.NewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diff on 2016/2/16.
 */
public class NewDao {
    private ZPOpenHelper zpOpenHelper;

    public NewDao(Context context) {
        zpOpenHelper = ZPOpenHelper.getInstance(context);
    }

    public void addNewBean(NewBean newBean) {
        SQLiteDatabase db = zpOpenHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContant.READ_COL_NID, newBean.getId());
        db.insert(DBContant.TABLE_READ, null, contentValues);
        db.close();
    }

    public List<NewBean> getAllNewBeans() {
        List<NewBean> list = new ArrayList<>();
        SQLiteDatabase db = zpOpenHelper.getReadableDatabase();
        String rawQuery = "select * from " + DBContant.TABLE_READ;
        Cursor cursor = db.rawQuery(rawQuery, null);
        if (cursor.moveToFirst()) {
            do {
                NewBean newBean = new NewBean(cursor.getInt(0));
                list.add(newBean);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
