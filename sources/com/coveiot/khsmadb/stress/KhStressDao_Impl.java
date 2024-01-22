package com.coveiot.khsmadb.stress;

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
public final class KhStressDao_Impl implements KhStressDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7159a;
    public final EntityInsertionAdapter<KhBlePressure> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhBlePressure> {
        public a(KhStressDao_Impl khStressDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBlePressure khBlePressure) {
            supportSQLiteStatement.bindLong(1, khBlePressure.getMTime());
            supportSQLiteStatement.bindLong(2, khBlePressure.getMValue());
            if (khBlePressure.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, khBlePressure.getMMacAddress());
            }
            if (khBlePressure.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, khBlePressure.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(5, khBlePressure.isProcessed() ? 1L : 0L);
            String str = khBlePressure.timeStamp;
            if (str == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhBlePressure` (`mTime`,`mValue`,`mMacAddress`,`convertedDate`,`isProcessed`,`Timestamp`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhStressDao_Impl khStressDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE khblepressure SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    public KhStressDao_Impl(RoomDatabase roomDatabase) {
        this.f7159a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public List<KhBlePressure> getAllStressData(String str, boolean z) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from khblepressure where mMacAddress=? and isProcessed=? and mValue>0 ORDER BY mTime", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, z ? 1L : 0L);
        this.f7159a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7159a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mValue");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBlePressure khBlePressure = new KhBlePressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBlePressure.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBlePressure.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBlePressure);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public List<KhBlePressure> getStressDataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from khblepressure where mMacAddress=? and mTime>=? and mTime<=? ORDER BY mTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f7159a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7159a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mValue");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBlePressure khBlePressure = new KhBlePressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBlePressure.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBlePressure.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBlePressure);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public List<KhBlePressure> getStressDataFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from khblepressure where mMacAddress=? and convertedDate=? ORDER BY mTime", 2);
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
        this.f7159a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7159a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mValue");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBlePressure khBlePressure = new KhBlePressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBlePressure.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBlePressure.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBlePressure);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public List<KhBlePressure> getStressDataListBetweenTime(String str, int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from khblepressure where mMacAddress=? and mTime between ? and ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        acquire.bindLong(3, i2);
        this.f7159a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7159a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mValue");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBlePressure khBlePressure = new KhBlePressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBlePressure.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBlePressure.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBlePressure);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public List<String> getUniqueDatesForStress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT convertedDate from khblepressure where mMacAddress=? ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7159a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7159a, acquire, false, null);
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

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public void insert(KhBlePressure khBlePressure) {
        this.f7159a.assertNotSuspendingTransaction();
        this.f7159a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhBlePressure>) khBlePressure);
            this.f7159a.setTransactionSuccessful();
        } finally {
            this.f7159a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public void insertAll(List<KhBlePressure> list) {
        this.f7159a.assertNotSuspendingTransaction();
        this.f7159a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7159a.setTransactionSuccessful();
        } finally {
            this.f7159a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.stress.KhStressDao
    public void updateStressDataProcessedBefore(String str, long j) {
        this.f7159a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7159a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7159a.setTransactionSuccessful();
        } finally {
            this.f7159a.endTransaction();
            this.c.release(acquire);
        }
    }
}
