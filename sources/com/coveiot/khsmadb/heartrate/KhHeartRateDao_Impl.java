package com.coveiot.khsmadb.heartrate;

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
public final class KhHeartRateDao_Impl implements KhHeartRateDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7146a;
    public final EntityInsertionAdapter<KhBleHeartRate> b;
    public final SharedSQLiteStatement c;
    public final SharedSQLiteStatement d;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhBleHeartRate> {
        public a(KhHeartRateDao_Impl khHeartRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBleHeartRate khBleHeartRate) {
            supportSQLiteStatement.bindLong(1, khBleHeartRate.getMTime());
            supportSQLiteStatement.bindLong(2, khBleHeartRate.getMBpm());
            if (khBleHeartRate.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, khBleHeartRate.getMMacAddress());
            }
            if (khBleHeartRate.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, khBleHeartRate.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(5, khBleHeartRate.isProcessed() ? 1L : 0L);
            supportSQLiteStatement.bindLong(6, khBleHeartRate.isProcessedInWorkout() ? 1L : 0L);
            String str = khBleHeartRate.timeStamp;
            if (str == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, str);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhBleHeartRate` (`mTime`,`mBpm`,`mMacAddress`,`convertedDate`,`isProcessed`,`isProcessedInWorkout`,`Timestamp`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhHeartRateDao_Impl khHeartRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleHeartRate SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends SharedSQLiteStatement {
        public c(KhHeartRateDao_Impl khHeartRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleHeartRate SET isProcessedInWorkout = 1 WHERE mMacAddress=? AND mTime<? AND isProcessedInWorkout==0";
        }
    }

    public KhHeartRateDao_Impl(RoomDatabase roomDatabase) {
        this.f7146a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public List<KhBleHeartRate> getAllHeartRateData(String str, boolean z) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleHeartRate where mMacAddress=? and isProcessed=? and mBpm>0 ORDER BY mTime", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, z ? 1L : 0L);
        this.f7146a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7146a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mBpm");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleHeartRate khBleHeartRate = new KhBleHeartRate(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleHeartRate.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleHeartRate.setProcessedInWorkout(query.getInt(columnIndexOrThrow6) != 0);
                khBleHeartRate.timeStamp = query.getString(columnIndexOrThrow7);
                arrayList.add(khBleHeartRate);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public List<KhBleHeartRate> getHeartRateDataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleHeartRate where mMacAddress=? and mTime>=? and mTime<=? ORDER BY mTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f7146a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7146a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mBpm");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleHeartRate khBleHeartRate = new KhBleHeartRate(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleHeartRate.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleHeartRate.setProcessedInWorkout(query.getInt(columnIndexOrThrow6) != 0);
                khBleHeartRate.timeStamp = query.getString(columnIndexOrThrow7);
                arrayList.add(khBleHeartRate);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public List<KhBleHeartRate> getHeartRateDataFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleHeartRate where mMacAddress=? and convertedDate=? ORDER BY mTime", 2);
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
        this.f7146a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7146a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mBpm");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleHeartRate khBleHeartRate = new KhBleHeartRate(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleHeartRate.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleHeartRate.setProcessedInWorkout(query.getInt(columnIndexOrThrow6) != 0);
                khBleHeartRate.timeStamp = query.getString(columnIndexOrThrow7);
                arrayList.add(khBleHeartRate);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public List<KhBleHeartRate> getHeartRateDataListBetweenTime(String str, int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleHeartRate where mMacAddress=? and mTime between ? and ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        acquire.bindLong(3, i2);
        this.f7146a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7146a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mBpm");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleHeartRate khBleHeartRate = new KhBleHeartRate(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleHeartRate.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleHeartRate.setProcessedInWorkout(query.getInt(columnIndexOrThrow6) != 0);
                khBleHeartRate.timeStamp = query.getString(columnIndexOrThrow7);
                arrayList.add(khBleHeartRate);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public List<String> getUniqueDatesForHeartRate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT convertedDate from KhBleHeartRate where mMacAddress=? ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7146a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7146a, acquire, false, null);
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

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public void insert(KhBleHeartRate khBleHeartRate) {
        this.f7146a.assertNotSuspendingTransaction();
        this.f7146a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhBleHeartRate>) khBleHeartRate);
            this.f7146a.setTransactionSuccessful();
        } finally {
            this.f7146a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public void insertAll(List<KhBleHeartRate> list) {
        this.f7146a.assertNotSuspendingTransaction();
        this.f7146a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7146a.setTransactionSuccessful();
        } finally {
            this.f7146a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public void updateHeartRateDataProcessedBefore(String str, long j) {
        this.f7146a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7146a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7146a.setTransactionSuccessful();
        } finally {
            this.f7146a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.heartrate.KhHeartRateDao
    public void updateWorkoutHeartRateDataProcessedBefore(String str, long j) {
        this.f7146a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7146a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7146a.setTransactionSuccessful();
        } finally {
            this.f7146a.endTransaction();
            this.d.release(acquire);
        }
    }
}
