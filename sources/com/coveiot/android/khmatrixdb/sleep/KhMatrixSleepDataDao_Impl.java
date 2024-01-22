package com.coveiot.android.khmatrixdb.sleep;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.khmatrixdb.converter.SleepItemConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
public final class KhMatrixSleepDataDao_Impl implements KhMatrixSleepDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f4623a;
    public final EntityInsertionAdapter<KhMatrixSleepData> b;
    public final EntityInsertionAdapter<KhMatrixSleepDetailData> c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;

    /* loaded from: classes3.dex */
    public class a extends EntityInsertionAdapter<KhMatrixSleepData> {
        public a(KhMatrixSleepDataDao_Impl khMatrixSleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhMatrixSleepData khMatrixSleepData) {
            if (khMatrixSleepData.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, khMatrixSleepData.getMMacAddress());
            }
            supportSQLiteStatement.bindLong(2, khMatrixSleepData.getMTime());
            supportSQLiteStatement.bindLong(3, khMatrixSleepData.getDeepSleep());
            supportSQLiteStatement.bindLong(4, khMatrixSleepData.getLightSleep());
            supportSQLiteStatement.bindLong(5, khMatrixSleepData.getAwake());
            if (khMatrixSleepData.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, khMatrixSleepData.getConvertedDate());
            }
            String fromList = SleepItemConverter.fromList(khMatrixSleepData.getMSleepDetail());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, fromList);
            }
            supportSQLiteStatement.bindLong(8, khMatrixSleepData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhMatrixSleepData` (`mMacAddress`,`mTime`,`deepSleep`,`lightSleep`,`awake`,`convertedDate`,`sleepDetail`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes3.dex */
    public class b extends EntityInsertionAdapter<KhMatrixSleepDetailData> {
        public b(KhMatrixSleepDataDao_Impl khMatrixSleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhMatrixSleepDetailData khMatrixSleepDetailData) {
            if (khMatrixSleepDetailData.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, khMatrixSleepDetailData.getMMacAddress());
            }
            supportSQLiteStatement.bindLong(2, khMatrixSleepDetailData.getMStartTime());
            supportSQLiteStatement.bindLong(3, khMatrixSleepDetailData.getMEndTime());
            supportSQLiteStatement.bindLong(4, khMatrixSleepDetailData.getStatus());
            if (khMatrixSleepDetailData.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, khMatrixSleepDetailData.getConvertedDate());
            }
            if (khMatrixSleepDetailData.getConvertedStartTime() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, khMatrixSleepDetailData.getConvertedStartTime());
            }
            if (khMatrixSleepDetailData.getConvertedEndTime() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, khMatrixSleepDetailData.getConvertedEndTime());
            }
            supportSQLiteStatement.bindLong(8, khMatrixSleepDetailData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhMatrixSleepDetailData` (`mMacAddress`,`mStartTime`,`mEndTime`,`status`,`convertedDate`,`convertedStartTime`,`convertedEndTime`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes3.dex */
    public class c extends SharedSQLiteStatement {
        public c(KhMatrixSleepDataDao_Impl khMatrixSleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhMatrixSleepData SET isProcessed = 1 WHERE mMacAddress=? and mTime<? and isProcessed == 0";
        }
    }

    /* loaded from: classes3.dex */
    public class d extends SharedSQLiteStatement {
        public d(KhMatrixSleepDataDao_Impl khMatrixSleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhMatrixSleepDetailData SET isProcessed = 1 WHERE mMacAddress=? and mStartTime<? and isProcessed == 0";
        }
    }

    public KhMatrixSleepDataDao_Impl(RoomDatabase roomDatabase) {
        this.f4623a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
        this.e = new d(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public List<KhMatrixSleepData> getAllSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixSleepData WHERE mMacAddress=? ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4623a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4623a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "deepSleep");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "lightSleep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "awake");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "sleepDetail");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixSleepData khMatrixSleepData = new KhMatrixSleepData(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                khMatrixSleepData.setMSleepDetail(SleepItemConverter.fromStr(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                khMatrixSleepData.setProcessed(query.getInt(columnIndexOrThrow8) != 0);
                arrayList.add(khMatrixSleepData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public List<KhMatrixSleepData> getAllUnProcessedSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixSleepData WHERE mMacAddress=? and isProcessed == 0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4623a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4623a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "deepSleep");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "lightSleep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "awake");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "sleepDetail");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixSleepData khMatrixSleepData = new KhMatrixSleepData(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                khMatrixSleepData.setMSleepDetail(SleepItemConverter.fromStr(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                khMatrixSleepData.setProcessed(query.getInt(columnIndexOrThrow8) != 0);
                arrayList.add(khMatrixSleepData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public List<KhMatrixSleepDetailData> getAllUnProcessedSleepDetailData(String str) {
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixSleepDetailData WHERE mMacAddress=? and isProcessed == 0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4623a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4623a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mStartTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mEndTime");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedStartTime");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "convertedEndTime");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixSleepDetailData khMatrixSleepDetailData = new KhMatrixSleepDetailData(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                if (query.getInt(columnIndexOrThrow8) == 0) {
                    z = false;
                }
                khMatrixSleepDetailData.setProcessed(z);
                arrayList.add(khMatrixSleepDetailData);
                z = true;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public List<KhMatrixSleepDetailData> getSleepDataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixSleepDetailData where mMacAddress=? and mStartTime>=? and mEndTime<=? ORDER BY mStartTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f4623a.assertNotSuspendingTransaction();
        boolean z = false;
        Cursor query = DBUtil.query(this.f4623a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mStartTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mEndTime");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedStartTime");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "convertedEndTime");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixSleepDetailData khMatrixSleepDetailData = new KhMatrixSleepDetailData(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                if (query.getInt(columnIndexOrThrow8) != 0) {
                    z = true;
                }
                khMatrixSleepDetailData.setProcessed(z);
                arrayList.add(khMatrixSleepDetailData);
                z = false;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public KhMatrixSleepData getSleepDataByTime(long j, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM KhMatrixSleepData WHERE mTime =? and mMacAddress=?", 2);
        boolean z = true;
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f4623a.assertNotSuspendingTransaction();
        KhMatrixSleepData khMatrixSleepData = null;
        String string = null;
        Cursor query = DBUtil.query(this.f4623a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "deepSleep");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "lightSleep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "awake");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "sleepDetail");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            if (query.moveToFirst()) {
                KhMatrixSleepData khMatrixSleepData2 = new KhMatrixSleepData(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                if (!query.isNull(columnIndexOrThrow7)) {
                    string = query.getString(columnIndexOrThrow7);
                }
                khMatrixSleepData2.setMSleepDetail(SleepItemConverter.fromStr(string));
                if (query.getInt(columnIndexOrThrow8) == 0) {
                    z = false;
                }
                khMatrixSleepData2.setProcessed(z);
                khMatrixSleepData = khMatrixSleepData2;
            }
            return khMatrixSleepData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public List<String> getUniqueDatesForSleep(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT convertedDate from KhMatrixSleepDetailData WHERE mMacAddress=? ORDER BY mStartTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4623a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4623a, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.isNull(0) ? null : query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public void insert(KhMatrixSleepData khMatrixSleepData) {
        this.f4623a.assertNotSuspendingTransaction();
        this.f4623a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhMatrixSleepData>) khMatrixSleepData);
            this.f4623a.setTransactionSuccessful();
        } finally {
            this.f4623a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public void insertAll(List<KhMatrixSleepData> list) {
        this.f4623a.assertNotSuspendingTransaction();
        this.f4623a.beginTransaction();
        try {
            this.b.insert(list);
            this.f4623a.setTransactionSuccessful();
        } finally {
            this.f4623a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public void insertAllSleepDetailData(List<KhMatrixSleepDetailData> list) {
        this.f4623a.assertNotSuspendingTransaction();
        this.f4623a.beginTransaction();
        try {
            this.c.insert(list);
            this.f4623a.setTransactionSuccessful();
        } finally {
            this.f4623a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public void updateSleepDataProcessedBefore(String str, long j) {
        this.f4623a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f4623a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f4623a.setTransactionSuccessful();
        } finally {
            this.f4623a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao
    public void updateSleepDetailProcessedBefore(String str, long j) {
        this.f4623a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f4623a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f4623a.setTransactionSuccessful();
        } finally {
            this.f4623a.endTransaction();
            this.e.release(acquire);
        }
    }
}
