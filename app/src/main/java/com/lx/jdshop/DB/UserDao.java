package com.lx.jdshop.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lx.jdshop.cons.DbConst;


/**
 * Created by Xia_焱 on 2017/7/20.
 */

//数据表中只有一个用户
public class UserDao {
    private DbOpenHelp dbOpenHelp;

    public UserDao(Context ctx) {
        dbOpenHelp = new DbOpenHelp(ctx);
    }

    // 保存数据
    public boolean SaveUser(String name, String pwd) {
        SQLiteDatabase db = dbOpenHelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbConst._NAME, name);
        values.put(DbConst._PWD, pwd);
        long insert = db.insert(DbConst.USER_TABLE, null, values);
        return insert != -1;
    }

    // 清空数据
    public void ClearUser() {
        SQLiteDatabase db = dbOpenHelp.getWritableDatabase();
        db.delete(DbConst.USER_TABLE, null, null);
    }

    //获取最后登录的用户
    public UserInfo LastUsers() {
        SQLiteDatabase db = dbOpenHelp.getWritableDatabase();
        Cursor cursor = db.query(DbConst.USER_TABLE, new String[] {
                DbConst._NAME, DbConst._PWD }, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            String pwd = cursor.getString(1);
            return new UserInfo(name, pwd);
        }
        return null;
    }

    public class UserInfo {
        public String name;
        public String pwd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public UserInfo(String name, String pwd) {
            this.name = name;
            this.pwd = pwd;
        }
    }
}
