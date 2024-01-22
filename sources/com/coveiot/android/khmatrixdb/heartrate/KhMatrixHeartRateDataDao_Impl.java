package com.coveiot.android.khmatrixdb.heartrate;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
public final class KhMatrixHeartRateDataDao_Impl implements KhMatrixHeartRateDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f4621a;
    public final EntityInsertionAdapter<KhMatrixHeartRate> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes3.dex */
    public class a extends EntityInsertionAdapter<KhMatrixHeartRate> {
        public a(KhMatrixHeartRateDataDao_Impl khMatrixHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhMatrixHeartRate khMatrixHeartRate) {
            if (khMatrixHeartRate.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, khMatrixHeartRate.getMMacAddress());
            }
            supportSQLiteStatement.bindLong(2, khMatrixHeartRate.getMTime());
            supportSQLiteStatement.bindLong(3, khMatrixHeartRate.getMBpm());
            if (khMatrixHeartRate.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, khMatrixHeartRate.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(5, khMatrixHeartRate.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhMatrixHeartRate` (`mMacAddress`,`mTime`,`mBpm`,`convertedDate`,`isProcessed`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes3.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhMatrixHeartRateDataDao_Impl khMatrixHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhMatrixHeartRate SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    public KhMatrixHeartRateDataDao_Impl(RoomDatabase roomDatabase) {
        this.f4621a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRateDataDao
    public List<KhMatrixHeartRate> getAllUnProcessedHRData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixHeartRate WHERE mMacAddress=? and isProcessed == 0 ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4621a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4621a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mBpm");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixHeartRate khMatrixHeartRate = new KhMatrixHeartRate(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                khMatrixHeartRate.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                arrayList.add(khMatrixHeartRate);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRateDataDao
    public void insert(KhMatrixHeartRate khMatrixHeartRate) {
        this.f4621a.assertNotSuspendingTransaction();
        this.f4621a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhMatrixHeartRate>) khMatrixHeartRate);
            this.f4621a.setTransactionSuccessful();
        } finally {
            this.f4621a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRateDataDao
    public void insertAll(List<KhMatrixHeartRate> list) {
        this.f4621a.assertNotSuspendingTransaction();
        this.f4621a.beginTransaction();
        try {
            this.b.insert(list);
            this.f4621a.setTransactionSuccessful();
        } finally {
            this.f4621a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRateDataDao
    public void updateHeartRateDataProcessedBefore(String str, long j) {
        this.f4621a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f4621a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f4621a.setTransactionSuccessful();
        } finally {
            this.f4621a.endTransaction();
            this.c.release(acquire);
        }
    }
}
