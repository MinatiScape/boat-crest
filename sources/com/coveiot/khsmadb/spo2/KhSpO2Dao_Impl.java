package com.coveiot.khsmadb.spo2;

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
public final class KhSpO2Dao_Impl implements KhSpO2Dao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7156a;
    public final EntityInsertionAdapter<KhBleSpO2> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhBleSpO2> {
        public a(KhSpO2Dao_Impl khSpO2Dao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBleSpO2 khBleSpO2) {
            supportSQLiteStatement.bindLong(1, khBleSpO2.getMTime());
            supportSQLiteStatement.bindLong(2, khBleSpO2.getMValue());
            if (khBleSpO2.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, khBleSpO2.getMMacAddress());
            }
            if (khBleSpO2.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, khBleSpO2.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(5, khBleSpO2.isProcessed() ? 1L : 0L);
            String str = khBleSpO2.timeStamp;
            if (str == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhBleSpO2` (`mTime`,`mValue`,`mMacAddress`,`convertedDate`,`isProcessed`,`Timestamp`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhSpO2Dao_Impl khSpO2Dao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleSpO2 SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    public KhSpO2Dao_Impl(RoomDatabase roomDatabase) {
        this.f7156a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.spo2.KhSpO2Dao
    public List<KhBleSpO2> getAllSpO2Data(String str, boolean z) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleSpO2 where mMacAddress=? and isProcessed=? and mValue>0 ORDER BY mTime", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, z ? 1L : 0L);
        this.f7156a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7156a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mValue");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleSpO2 khBleSpO2 = new KhBleSpO2(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleSpO2.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleSpO2.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBleSpO2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.spo2.KhSpO2Dao
    public List<KhBleSpO2> getSpO2DataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleSpO2 where mMacAddress=? and mTime>=? and mTime<=? ORDER BY mTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f7156a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7156a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mValue");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleSpO2 khBleSpO2 = new KhBleSpO2(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleSpO2.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleSpO2.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBleSpO2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.spo2.KhSpO2Dao
    public List<KhBleSpO2> getSpO2DataFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleSpO2 where mMacAddress=? and convertedDate=? ORDER BY mTime", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.f7156a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7156a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mValue");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleSpO2 khBleSpO2 = new KhBleSpO2(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleSpO2.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleSpO2.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBleSpO2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.spo2.KhSpO2Dao
    public void insert(KhBleSpO2 khBleSpO2) {
        this.f7156a.assertNotSuspendingTransaction();
        this.f7156a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhBleSpO2>) khBleSpO2);
            this.f7156a.setTransactionSuccessful();
        } finally {
            this.f7156a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.spo2.KhSpO2Dao
    public void insertAll(List<KhBleSpO2> list) {
        this.f7156a.assertNotSuspendingTransaction();
        this.f7156a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7156a.setTransactionSuccessful();
        } finally {
            this.f7156a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.spo2.KhSpO2Dao
    public void updateSpO2DataProcessedBefore(String str, long j) {
        this.f7156a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7156a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7156a.setTransactionSuccessful();
        } finally {
            this.f7156a.endTransaction();
            this.c.release(acquire);
        }
    }
}
