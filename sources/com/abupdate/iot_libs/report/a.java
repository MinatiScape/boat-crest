package com.abupdate.iot_libs.report;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/* loaded from: classes.dex */
public class a extends SQLiteOpenHelper {
    public a(Context context) {
        super(context, "fota_sdk.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS report_down(_id INTEGER PRIMARY KEY AUTOINCREMENT, delta_id VARCHAR, download_status VARCHAR, down_start_time VARCHAR, down_end_time VARCHAR,down_size INTEGER,down_ip VARCHAR)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS report_upgrade(_id INTEGER PRIMARY KEY AUTOINCREMENT, delta_id VARCHAR, updateStatus VARCHAR )");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS push_response(_id INTEGER PRIMARY KEY AUTOINCREMENT, msgId VARCHAR)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS report_error_log(_id INTEGER PRIMARY KEY AUTOINCREMENT, delta_id VARCHAR, error_type VARCHAR, upload_file VARCHAR)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table IF EXISTS report_down");
        sQLiteDatabase.execSQL("drop table IF EXISTS report_upgrade");
        sQLiteDatabase.execSQL("drop table IF EXISTS push_response");
        sQLiteDatabase.execSQL("drop table IF EXISTS report_error_log");
        onCreate(sQLiteDatabase);
    }
}
