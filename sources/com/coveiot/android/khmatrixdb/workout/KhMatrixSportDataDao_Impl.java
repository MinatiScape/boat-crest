package com.coveiot.android.khmatrixdb.workout;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.khmatrixdb.converter.SportItemConverter;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
public final class KhMatrixSportDataDao_Impl implements KhMatrixSportDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f4635a;
    public final EntityInsertionAdapter<KhMatrixSportData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes3.dex */
    public class a extends EntityInsertionAdapter<KhMatrixSportData> {
        public a(KhMatrixSportDataDao_Impl khMatrixSportDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhMatrixSportData khMatrixSportData) {
            if (khMatrixSportData.getSportId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, khMatrixSportData.getSportId());
            }
            if (khMatrixSportData.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, khMatrixSportData.getMMacAddress());
            }
            supportSQLiteStatement.bindLong(3, khMatrixSportData.getMTime());
            supportSQLiteStatement.bindLong(4, khMatrixSportData.getSportType());
            supportSQLiteStatement.bindLong(5, khMatrixSportData.getStep());
            supportSQLiteStatement.bindLong(6, khMatrixSportData.getDuration());
            supportSQLiteStatement.bindDouble(7, khMatrixSportData.getDistance());
            supportSQLiteStatement.bindDouble(8, khMatrixSportData.getCalories());
            if (khMatrixSportData.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, khMatrixSportData.getConvertedDate());
            }
            String fromList = SportItemConverter.fromList(khMatrixSportData.getMSportItemDetails());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, fromList);
            }
            supportSQLiteStatement.bindLong(11, khMatrixSportData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhMatrixSportData` (`sportId`,`mMacAddress`,`mTime`,`sportType`,`step`,`duration`,`distance`,`calories`,`convertedDate`,`sportItemDetails`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes3.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhMatrixSportDataDao_Impl khMatrixSportDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhMatrixSportData SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    public KhMatrixSportDataDao_Impl(RoomDatabase roomDatabase) {
        this.f4635a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.android.khmatrixdb.workout.KhMatrixSportDataDao
    public List<KhMatrixSportData> getAllUnProcessedSportsData(String str) {
        String string;
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhMatrixSportData WHERE mMacAddress=? and isProcessed == 0 ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4635a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4635a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "sportId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.sportType);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.Step);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "sportItemDetails");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhMatrixSportData khMatrixSportData = new KhMatrixSportData(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getFloat(columnIndexOrThrow7), query.getFloat(columnIndexOrThrow8), query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                if (query.isNull(columnIndexOrThrow10)) {
                    i = columnIndexOrThrow;
                    string = null;
                } else {
                    string = query.getString(columnIndexOrThrow10);
                    i = columnIndexOrThrow;
                }
                khMatrixSportData.setMSportItemDetails(SportItemConverter.fromStr(string));
                khMatrixSportData.setProcessed(query.getInt(columnIndexOrThrow11) != 0);
                arrayList.add(khMatrixSportData);
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.workout.KhMatrixSportDataDao
    public void insert(KhMatrixSportData khMatrixSportData) {
        this.f4635a.assertNotSuspendingTransaction();
        this.f4635a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhMatrixSportData>) khMatrixSportData);
            this.f4635a.setTransactionSuccessful();
        } finally {
            this.f4635a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.workout.KhMatrixSportDataDao
    public void insertAll(List<KhMatrixSportData> list) {
        this.f4635a.assertNotSuspendingTransaction();
        this.f4635a.beginTransaction();
        try {
            this.b.insert(list);
            this.f4635a.setTransactionSuccessful();
        } finally {
            this.f4635a.endTransaction();
        }
    }

    @Override // com.coveiot.android.khmatrixdb.workout.KhMatrixSportDataDao
    public void updateSportsDataProcessedBefore(String str, long j) {
        this.f4635a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f4635a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f4635a.setTransactionSuccessful();
        } finally {
            this.f4635a.endTransaction();
            this.c.release(acquire);
        }
    }
}
