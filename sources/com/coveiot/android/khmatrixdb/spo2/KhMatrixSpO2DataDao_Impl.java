package com.coveiot.android.khmatrixdb.spo2;

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
public final class KhMatrixSpO2DataDao_Impl implements KhMatrixSpO2DataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f4629a;
    public final EntityInsertionAdapter<KhMatrixSpO2> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes3.dex */
    public class a extends EntityInsertionAdapter<KhMatrixSpO2> {
        public a(KhMatrixSpO2DataDao_Impl khMatrixSpO2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhMatrixSpO2 khMatrixSpO2) {
            if (khMatrixSpO2.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, khMatrixSpO2.getMMacAddress());
            }
            supportSQLiteStatement.bindLong(2, khMatrixSpO2.getMTime());
            supportSQLiteStatement.bindLong(3, khMatrixSpO2.getMSpO2());
            if (khMatrixSpO2.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, khMatrixSpO2.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(5, khMatrixSpO2.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhMatrixSpO2` (`mMacAddress`,`mTime`,`mSpO2`,`convertedDate`,`isProcessed`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes3.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhMatrixSpO2DataDao_Impl khMatrixSpO2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhMatrixSpO2 SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    public KhMatrixSpO2DataDao_Impl(RoomDatabase roomDatabase) {
        this.f4629a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2DataDao
    public List<KhMatrixSpO2> getAllUnProcessedSp02Data(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixSpO2 WHERE mMacAddress=? and isProcessed == 0 ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4629a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4629a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mSpO2");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixSpO2 khMatrixSpO2 = new KhMatrixSpO2(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                khMatrixSpO2.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                arrayList.add(khMatrixSpO2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2DataDao
    public void insert(KhMatrixSpO2 khMatrixSpO2) {
        this.f4629a.assertNotSuspendingTransaction();
        this.f4629a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhMatrixSpO2>) khMatrixSpO2);
            this.f4629a.setTransactionSuccessful();
        } finally {
            this.f4629a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2DataDao
    public void insertAll(List<KhMatrixSpO2> list) {
        this.f4629a.assertNotSuspendingTransaction();
        this.f4629a.beginTransaction();
        try {
            this.b.insert(list);
            this.f4629a.setTransactionSuccessful();
        } finally {
            this.f4629a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2DataDao
    public void updateSpO2DataProcessedBefore(String str, long j) {
        this.f4629a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f4629a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f4629a.setTransactionSuccessful();
        } finally {
            this.f4629a.endTransaction();
            this.c.release(acquire);
        }
    }
}
