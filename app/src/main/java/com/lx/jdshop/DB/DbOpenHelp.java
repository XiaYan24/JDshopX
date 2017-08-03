package com.lx.jdshop.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lx.jdshop.cons.DbConst;


/**
 * Created by Xia_焱 on 2017/7/20.
 */

public class DbOpenHelp extends SQLiteOpenHelper {

    public DbOpenHelp(Context context) {
        super(context, DbConst.DB_NAME, null, DbConst.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +DbConst.USER_TABLE+"(" +
                DbConst._ID+" integer primary key autoincrement," +
                DbConst._NAME+" text, " +
                DbConst._PWD+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
