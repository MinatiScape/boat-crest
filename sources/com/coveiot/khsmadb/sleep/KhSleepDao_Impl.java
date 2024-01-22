package com.coveiot.khsmadb.sleep;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KhSleepDao_Impl implements KhSleepDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7152a;
    public final EntityInsertionAdapter<KhBleSleep> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhBleSleep> {
        public a(KhSleepDao_Impl khSleepDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBleSleep khBleSleep) {
            supportSQLiteStatement.bindLong(1, khBleSleep.getMTime());
            supportSQLiteStatement.bindLong(2, khBleSleep.getMMode());
            supportSQLiteStatement.bindLong(3, khBleSleep.getMSoft());
            supportSQLiteStatement.bindLong(4, khBleSleep.getMStrong());
            if (khBleSleep.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, khBleSleep.getMMacAddress());
            }
            if (khBleSleep.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, khBleSleep.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(7, khBleSleep.isProcessed() ? 1L : 0L);
            String str = khBleSleep.timeStamp;
            if (str == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, str);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhBleSleep` (`mTime`,`mMode`,`mSoft`,`mStrong`,`mMacAddress`,`convertedDate`,`isProcessed`,`Timestamp`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhSleepDao_Impl khSleepDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleSleep SET isProcessed = 1 WHERE mMacAddress=? and mTime<? and isProcessed == 0";
        }
    }

    public KhSleepDao_Impl(RoomDatabase roomDatabase) {
        this.f7152a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.sleep.KhSleepDao
    public List<KhBleSleep> getAllSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleSleep WHERE mMacAddress=? ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7152a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7152a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mSoft");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mStrong");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleSleep khBleSleep = new KhBleSleep(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6));
                khBleSleep.setProcessed(query.getInt(columnIndexOrThrow7) != 0);
                khBleSleep.timeStamp = query.getString(columnIndexOrThrow8);
                arrayList.add(khBleSleep);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.sleep.KhSleepDao
    public List<KhBleSleep> getSleepDataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleSleep where mMacAddress=? and mTime>=? and mTime<=? ORDER BY mTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f7152a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7152a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mSoft");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mStrong");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleSleep khBleSleep = new KhBleSleep(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6));
                khBleSleep.setProcessed(query.getInt(columnIndexOrThrow7) != 0);
                khBleSleep.timeStamp = query.getString(columnIndexOrThrow8);
                arrayList.add(khBleSleep);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.sleep.KhSleepDao
    public List<String> getUniqueDatesForSleep(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT convertedDate from KhBleSleep WHERE mMacAddress=? ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7152a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7152a, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.sleep.KhSleepDao
    public void insert(KhBleSleep khBleSleep) {
        this.f7152a.assertNotSuspendingTransaction();
        this.f7152a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhBleSleep>) khBleSleep);
            this.f7152a.setTransactionSuccessful();
        } finally {
            this.f7152a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.sleep.KhSleepDao
    public void insertAll(List<KhBleSleep> list) {
        this.f7152a.assertNotSuspendingTransaction();
        this.f7152a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7152a.setTransactionSuccessful();
        } finally {
            this.f7152a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.sleep.KhSleepDao
    public void updateSleepDataProcessedBefore(String str, long j) {
        this.f7152a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7152a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7152a.setTransactionSuccessful();
        } finally {
            this.f7152a.endTransaction();
            this.c.release(acquire);
        }
    }
}
