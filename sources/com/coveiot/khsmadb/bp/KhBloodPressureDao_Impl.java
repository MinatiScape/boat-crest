package com.coveiot.khsmadb.bp;

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
public final class KhBloodPressureDao_Impl implements KhBloodPressureDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7140a;
    public final EntityInsertionAdapter<KhBleBloodPressure> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhBleBloodPressure> {
        public a(KhBloodPressureDao_Impl khBloodPressureDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBleBloodPressure khBleBloodPressure) {
            supportSQLiteStatement.bindLong(1, khBleBloodPressure.getMTime());
            supportSQLiteStatement.bindLong(2, khBleBloodPressure.getMSystolic());
            supportSQLiteStatement.bindLong(3, khBleBloodPressure.getMDiastolic());
            if (khBleBloodPressure.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, khBleBloodPressure.getMMacAddress());
            }
            if (khBleBloodPressure.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, khBleBloodPressure.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(6, khBleBloodPressure.isProcessed() ? 1L : 0L);
            String str = khBleBloodPressure.timeStamp;
            if (str == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, str);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhBleBloodPressure` (`mTime`,`mSystolic`,`mDiastolic`,`mMacAddress`,`convertedDate`,`isProcessed`,`Timestamp`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhBloodPressureDao_Impl khBloodPressureDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleBloodPressure SET isProcessed = 1 WHERE mMacAddress=? AND mTime<? AND isProcessed == 0";
        }
    }

    public KhBloodPressureDao_Impl(RoomDatabase roomDatabase) {
        this.f7140a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.bp.KhBloodPressureDao
    public List<KhBleBloodPressure> getAllBloodPressureData(String str, boolean z) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleBloodPressure where mMacAddress=? and isProcessed=? ORDER BY mTime", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, z ? 1L : 0L);
        this.f7140a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7140a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mSystolic");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mDiastolic");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleBloodPressure khBleBloodPressure = new KhBleBloodPressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5));
                khBleBloodPressure.setProcessed(query.getInt(columnIndexOrThrow6) != 0);
                khBleBloodPressure.timeStamp = query.getString(columnIndexOrThrow7);
                arrayList.add(khBleBloodPressure);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.bp.KhBloodPressureDao
    public List<KhBleBloodPressure> getBloodPressureDataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleBloodPressure where mMacAddress=? and mTime>=? and mTime<=? ORDER BY mTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f7140a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7140a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mSystolic");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mDiastolic");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleBloodPressure khBleBloodPressure = new KhBleBloodPressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5));
                khBleBloodPressure.setProcessed(query.getInt(columnIndexOrThrow6) != 0);
                khBleBloodPressure.timeStamp = query.getString(columnIndexOrThrow7);
                arrayList.add(khBleBloodPressure);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.bp.KhBloodPressureDao
    public List<KhBleBloodPressure> getBloodPressureDataFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleBloodPressure where mMacAddress=? and convertedDate=? ORDER BY mTime", 2);
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
        this.f7140a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7140a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mSystolic");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mDiastolic");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleBloodPressure khBleBloodPressure = new KhBleBloodPressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5));
                khBleBloodPressure.setProcessed(query.getInt(columnIndexOrThrow6) != 0);
                khBleBloodPressure.timeStamp = query.getString(columnIndexOrThrow7);
                arrayList.add(khBleBloodPressure);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.bp.KhBloodPressureDao
    public List<String> getUniqueDatesForBloodPressure(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT convertedDate from KhBleBloodPressure where mMacAddress=? ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7140a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7140a, acquire, false, null);
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

    @Override // com.coveiot.khsmadb.bp.KhBloodPressureDao
    public void insert(KhBleBloodPressure khBleBloodPressure) {
        this.f7140a.assertNotSuspendingTransaction();
        this.f7140a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhBleBloodPressure>) khBleBloodPressure);
            this.f7140a.setTransactionSuccessful();
        } finally {
            this.f7140a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.bp.KhBloodPressureDao
    public void insertAll(List<KhBleBloodPressure> list) {
        this.f7140a.assertNotSuspendingTransaction();
        this.f7140a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7140a.setTransactionSuccessful();
        } finally {
            this.f7140a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.bp.KhBloodPressureDao
    public void updateBloodPressureDataProcessedBefore(String str, long j) {
        this.f7140a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7140a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7140a.setTransactionSuccessful();
        } finally {
            this.f7140a.endTransaction();
            this.c.release(acquire);
        }
    }
}
