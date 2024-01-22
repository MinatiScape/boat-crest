package com.coveiot.android.leonardo.sp02.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.leonardo.sp02.database.entities.SPO2Record;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes5.dex */
public final class SPO2Dao_Impl implements SPO2Dao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f5409a;
    public final EntityInsertionAdapter<SPO2Record> b;

    /* loaded from: classes5.dex */
    public class a extends EntityInsertionAdapter<SPO2Record> {
        public a(SPO2Dao_Impl sPO2Dao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, SPO2Record sPO2Record) {
            supportSQLiteStatement.bindLong(1, sPO2Record.timeStamp);
            supportSQLiteStatement.bindDouble(2, sPO2Record.value);
            String str = sPO2Record.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            String str2 = sPO2Record.timeZoneOffSet;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str2);
            }
            supportSQLiteStatement.bindLong(5, sPO2Record.isLevelIntepreTation ? 1L : 0L);
            supportSQLiteStatement.bindLong(6, sPO2Record.isSyncedToServer ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `spo2_records` (`timeStamp`,`value`,`date`,`timeZoneOffSet`,`isLevelIntepreTation`,`isSyncedToServer`) VALUES (?,?,?,?,?,?)";
        }
    }

    public SPO2Dao_Impl(RoomDatabase roomDatabase) {
        this.f5409a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.android.leonardo.sp02.database.dao.SPO2Dao
    public SPO2Record getLatestSpo2Record(String str) {
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from spo2_records where date=? ORDER BY timeStamp DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5409a.assertNotSuspendingTransaction();
        SPO2Record sPO2Record = null;
        Cursor query = DBUtil.query(this.f5409a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "value");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timeZoneOffSet");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isLevelIntepreTation");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSyncedToServer");
            if (query.moveToFirst()) {
                sPO2Record = new SPO2Record();
                sPO2Record.timeStamp = query.getLong(columnIndexOrThrow);
                sPO2Record.value = query.getDouble(columnIndexOrThrow2);
                sPO2Record.date = query.getString(columnIndexOrThrow3);
                sPO2Record.timeZoneOffSet = query.getString(columnIndexOrThrow4);
                sPO2Record.isLevelIntepreTation = query.getInt(columnIndexOrThrow5) != 0;
                if (query.getInt(columnIndexOrThrow6) == 0) {
                    z = false;
                }
                sPO2Record.isSyncedToServer = z;
            }
            return sPO2Record;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.leonardo.sp02.database.dao.SPO2Dao
    public List<SPO2Record> getUnSyncedSPO2Recods() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from spo2_records where isSyncedToServer=0", 0);
        this.f5409a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5409a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "value");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timeZoneOffSet");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isLevelIntepreTation");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSyncedToServer");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SPO2Record sPO2Record = new SPO2Record();
                sPO2Record.timeStamp = query.getLong(columnIndexOrThrow);
                sPO2Record.value = query.getDouble(columnIndexOrThrow2);
                sPO2Record.date = query.getString(columnIndexOrThrow3);
                sPO2Record.timeZoneOffSet = query.getString(columnIndexOrThrow4);
                boolean z = true;
                sPO2Record.isLevelIntepreTation = query.getInt(columnIndexOrThrow5) != 0;
                if (query.getInt(columnIndexOrThrow6) == 0) {
                    z = false;
                }
                sPO2Record.isSyncedToServer = z;
                arrayList.add(sPO2Record);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.leonardo.sp02.database.dao.SPO2Dao
    public void insert(SPO2Record sPO2Record) {
        this.f5409a.assertNotSuspendingTransaction();
        this.f5409a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<SPO2Record>) sPO2Record);
            this.f5409a.setTransactionSuccessful();
        } finally {
            this.f5409a.endTransaction();
        }
    }
}
