package com.abupdate.iot_libs.report;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.DownParamInfo;
import com.abupdate.iot_libs.info.ErrorFileParamInfo;
import com.abupdate.iot_libs.info.PushMessageInfo;
import com.abupdate.iot_libs.info.UpgradeParamInfo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public a f1913a;
    public SQLiteDatabase b;

    public b(Context context) {
        a aVar = new a(context);
        this.f1913a = aVar;
        this.b = aVar.getWritableDatabase();
    }

    public void a(DownParamInfo downParamInfo) {
        this.b.beginTransaction();
        try {
            this.b.execSQL("INSERT INTO report_down VALUES(null, ?, ?, ?, ?,?,?)", new Object[]{downParamInfo.deltaID, downParamInfo.downloadStatus, downParamInfo.downStart, downParamInfo.downEnd, Integer.valueOf(downParamInfo.downSize), downParamInfo.downIp});
            this.b.setTransactionSuccessful();
        } finally {
            this.b.endTransaction();
        }
    }

    public void b(DownParamInfo downParamInfo) {
        this.b.delete("report_down", "down_start_time = ?", new String[]{String.valueOf(downParamInfo.downStart)});
    }

    public List<PushMessageInfo> c() {
        ArrayList arrayList = new ArrayList();
        Cursor a2 = a("push_response");
        while (a2 != null && a2.moveToNext()) {
            PushMessageInfo pushMessageInfo = new PushMessageInfo();
            pushMessageInfo._id = a2.getInt(a2.getColumnIndex("_id"));
            pushMessageInfo.msgId = a2.getString(a2.getColumnIndex("msgId"));
            arrayList.add(pushMessageInfo);
        }
        a2.close();
        return arrayList;
    }

    public List<ErrorFileParamInfo> d() {
        ArrayList arrayList = new ArrayList();
        Cursor a2 = a("report_error_log");
        while (a2 != null && a2.moveToNext()) {
            int i = a2.getInt(a2.getColumnIndex("_id"));
            ErrorFileParamInfo errorFileParamInfo = new ErrorFileParamInfo(DeviceInfo.getInstance().mid, a2.getString(a2.getColumnIndex("delta_id")), a2.getString(a2.getColumnIndex("error_type")), a2.getString(a2.getColumnIndex("upload_file")));
            errorFileParamInfo._id = i;
            arrayList.add(errorFileParamInfo);
        }
        a2.close();
        return arrayList;
    }

    public void b(UpgradeParamInfo upgradeParamInfo) {
        this.b.delete("report_upgrade", "_id = ?", new String[]{String.valueOf(upgradeParamInfo._id)});
    }

    public List<UpgradeParamInfo> b() {
        ArrayList arrayList = new ArrayList();
        Cursor a2 = a("report_upgrade");
        while (a2 != null && a2.moveToNext()) {
            UpgradeParamInfo upgradeParamInfo = new UpgradeParamInfo();
            upgradeParamInfo._id = a2.getInt(a2.getColumnIndex("_id"));
            upgradeParamInfo.deltaID = a2.getString(a2.getColumnIndex("delta_id"));
            upgradeParamInfo.updateStatus = a2.getString(a2.getColumnIndex("updateStatus"));
            arrayList.add(upgradeParamInfo);
        }
        a2.close();
        return arrayList;
    }

    public void a(UpgradeParamInfo upgradeParamInfo) {
        this.b.beginTransaction();
        try {
            this.b.execSQL("INSERT INTO report_upgrade VALUES(null, ?, ?)", new Object[]{upgradeParamInfo.deltaID, upgradeParamInfo.updateStatus});
            this.b.setTransactionSuccessful();
        } finally {
            this.b.endTransaction();
        }
    }

    public void b(PushMessageInfo pushMessageInfo) {
        this.b.beginTransaction();
        try {
            this.b.execSQL("INSERT INTO push_response VALUES(null, ?)", new Object[]{pushMessageInfo.msgId});
            this.b.setTransactionSuccessful();
        } finally {
            this.b.endTransaction();
        }
    }

    public void a(PushMessageInfo pushMessageInfo) {
        this.b.delete("push_response", "_id = ?", new String[]{String.valueOf(pushMessageInfo._id)});
    }

    public void a(ErrorFileParamInfo errorFileParamInfo) {
        this.b.delete("report_error_log", "_id = ?", new String[]{String.valueOf(errorFileParamInfo._id)});
    }

    public List<DownParamInfo> a() {
        ArrayList arrayList = new ArrayList();
        Cursor a2 = a("report_down");
        while (a2 != null && a2.moveToNext()) {
            DownParamInfo downParamInfo = new DownParamInfo();
            downParamInfo._id = a2.getInt(a2.getColumnIndex("_id"));
            downParamInfo.deltaID = a2.getString(a2.getColumnIndex("delta_id"));
            downParamInfo.downloadStatus = a2.getString(a2.getColumnIndex("download_status"));
            downParamInfo.downStart = a2.getString(a2.getColumnIndex("down_start_time"));
            downParamInfo.downEnd = a2.getString(a2.getColumnIndex("down_end_time"));
            downParamInfo.downSize = a2.getInt(a2.getColumnIndex("down_size"));
            downParamInfo.downIp = a2.getString(a2.getColumnIndex("down_ip"));
            arrayList.add(downParamInfo);
        }
        a2.close();
        return arrayList;
    }

    public void b(ErrorFileParamInfo errorFileParamInfo) {
        try {
            this.b.beginTransaction();
            SQLiteDatabase sQLiteDatabase = this.b;
            Object[] objArr = new Object[3];
            objArr[0] = TextUtils.isEmpty(errorFileParamInfo.deltaID) ? "" : errorFileParamInfo.deltaID;
            objArr[1] = errorFileParamInfo.errorType;
            objArr[2] = errorFileParamInfo.uploadFile;
            sQLiteDatabase.execSQL("INSERT INTO report_error_log VALUES(null,?,?,?)", objArr);
            this.b.setTransactionSuccessful();
        } finally {
            this.b.endTransaction();
        }
    }

    public final Cursor a(String str) {
        SQLiteDatabase sQLiteDatabase = this.b;
        return sQLiteDatabase.rawQuery("SELECT * FROM " + str, null);
    }
}
