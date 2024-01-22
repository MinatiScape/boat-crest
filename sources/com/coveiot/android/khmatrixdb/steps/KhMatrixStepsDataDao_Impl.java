package com.coveiot.android.khmatrixdb.steps;

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
public final class KhMatrixStepsDataDao_Impl implements KhMatrixStepsDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f4632a;
    public final EntityInsertionAdapter<KhMatrixStepsData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes3.dex */
    public class a extends EntityInsertionAdapter<KhMatrixStepsData> {
        public a(KhMatrixStepsDataDao_Impl khMatrixStepsDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhMatrixStepsData khMatrixStepsData) {
            if (khMatrixStepsData.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, khMatrixStepsData.getMMacAddress());
            }
            supportSQLiteStatement.bindLong(2, khMatrixStepsData.getMTime());
            supportSQLiteStatement.bindLong(3, khMatrixStepsData.getSteps());
            supportSQLiteStatement.bindDouble(4, khMatrixStepsData.getDistance());
            supportSQLiteStatement.bindDouble(5, khMatrixStepsData.getCalories());
            if (khMatrixStepsData.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, khMatrixStepsData.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(7, khMatrixStepsData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhMatrixStepsData` (`mMacAddress`,`mTime`,`steps`,`distance`,`calories`,`convertedDate`,`isProcessed`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes3.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhMatrixStepsDataDao_Impl khMatrixStepsDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhMatrixStepsData SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    public KhMatrixStepsDataDao_Impl(RoomDatabase roomDatabase) {
        this.f4632a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.android.khmatrixdb.steps.KhMatrixStepsDataDao
    public List<KhMatrixStepsData> getAllUnProcessedStepsData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixStepsData WHERE mMacAddress=? and isProcessed == 0 ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4632a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4632a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "steps");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixStepsData khMatrixStepsData = new KhMatrixStepsData(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getFloat(columnIndexOrThrow4), query.getFloat(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                khMatrixStepsData.setProcessed(query.getInt(columnIndexOrThrow7) != 0);
                arrayList.add(khMatrixStepsData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.steps.KhMatrixStepsDataDao
    public void insert(KhMatrixStepsData khMatrixStepsData) {
        this.f4632a.assertNotSuspendingTransaction();
        this.f4632a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhMatrixStepsData>) khMatrixStepsData);
            this.f4632a.setTransactionSuccessful();
        } finally {
            this.f4632a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.steps.KhMatrixStepsDataDao
    public void insertAll(List<KhMatrixStepsData> list) {
        this.f4632a.assertNotSuspendingTransaction();
        this.f4632a.beginTransaction();
        try {
            this.b.insert(list);
            this.f4632a.setTransactionSuccessful();
        } finally {
            this.f4632a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.steps.KhMatrixStepsDataDao
    public void updateStepsDataProcessedBefore(String str, long j) {
        this.f4632a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f4632a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f4632a.setTransactionSuccessful();
        } finally {
            this.f4632a.endTransaction();
            this.c.release(acquire);
        }
    }
}
