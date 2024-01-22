package com.coveiot.khsmadb.workout;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KhBleWorkoutDao_Impl implements KhBleWorkoutDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7165a;
    public final EntityInsertionAdapter<KhBleWorkout> b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhBleWorkout> {
        public a(KhBleWorkoutDao_Impl khBleWorkoutDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBleWorkout khBleWorkout) {
            supportSQLiteStatement.bindLong(1, khBleWorkout.getMStart());
            supportSQLiteStatement.bindLong(2, khBleWorkout.getMEnd());
            supportSQLiteStatement.bindLong(3, khBleWorkout.getMDuration());
            supportSQLiteStatement.bindLong(4, khBleWorkout.getMAltitude());
            supportSQLiteStatement.bindLong(5, khBleWorkout.getMAirPressure());
            supportSQLiteStatement.bindLong(6, khBleWorkout.getMSpm());
            supportSQLiteStatement.bindLong(7, khBleWorkout.getMMode());
            supportSQLiteStatement.bindLong(8, khBleWorkout.getMStep());
            supportSQLiteStatement.bindLong(9, khBleWorkout.getMDistance());
            supportSQLiteStatement.bindLong(10, khBleWorkout.getMCalorie());
            supportSQLiteStatement.bindLong(11, khBleWorkout.getMSpeed());
            supportSQLiteStatement.bindLong(12, khBleWorkout.getMPace());
            if (khBleWorkout.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(13);
            } else {
                supportSQLiteStatement.bindString(13, khBleWorkout.getMacAddress());
            }
            supportSQLiteStatement.bindLong(14, khBleWorkout.getMAvgBpm());
            supportSQLiteStatement.bindLong(15, khBleWorkout.getMMaxBpm());
            supportSQLiteStatement.bindLong(16, khBleWorkout.getMMinBpm());
            supportSQLiteStatement.bindLong(17, khBleWorkout.getMUndefined());
            supportSQLiteStatement.bindLong(18, khBleWorkout.getMMaxSpm());
            supportSQLiteStatement.bindLong(19, khBleWorkout.getMMinSpm());
            supportSQLiteStatement.bindLong(20, khBleWorkout.getMMaxPace());
            supportSQLiteStatement.bindLong(21, khBleWorkout.getMMinPace());
            if (khBleWorkout.getStartDate() == null) {
                supportSQLiteStatement.bindNull(22);
            } else {
                supportSQLiteStatement.bindString(22, khBleWorkout.getStartDate());
            }
            if (khBleWorkout.getEndDate() == null) {
                supportSQLiteStatement.bindNull(23);
            } else {
                supportSQLiteStatement.bindString(23, khBleWorkout.getEndDate());
            }
            supportSQLiteStatement.bindLong(24, khBleWorkout.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `khBleWorkout` (`mStart`,`mEnd`,`mDuration`,`mAltitude`,`mAirPressure`,`mSpm`,`mMode`,`mStep`,`mDistance`,`mCalorie`,`mSpeed`,`mPace`,`macAddress`,`mAvgBpm`,`mMaxBpm`,`mMinBpm`,`mUndefined`,`mMaxSpm`,`mMinSpm`,`mMaxPace`,`mMinPace`,`startDate`,`endDate`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    public KhBleWorkoutDao_Impl(RoomDatabase roomDatabase) {
        this.f7165a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.workout.KhBleWorkoutDao
    public List<KhBleWorkout> getUnMarkedWorkoutList(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM khBleWorkout WHERE isProcessed=0 AND macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7165a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7165a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mStart");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mEnd");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mDuration");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mAltitude");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mAirPressure");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "mSpm");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "mStep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "mDistance");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "mCalorie");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "mSpeed");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "mPace");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "mAvgBpm");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "mMaxBpm");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "mMinBpm");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "mUndefined");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "mMaxSpm");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "mMinSpm");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "mMaxPace");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "mMinPace");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "startDate");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "endDate");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    int i2 = query.getInt(columnIndexOrThrow);
                    int i3 = query.getInt(columnIndexOrThrow2);
                    int i4 = query.getInt(columnIndexOrThrow3);
                    int i5 = query.getInt(columnIndexOrThrow4);
                    int i6 = query.getInt(columnIndexOrThrow5);
                    int i7 = query.getInt(columnIndexOrThrow6);
                    int i8 = query.getInt(columnIndexOrThrow7);
                    int i9 = query.getInt(columnIndexOrThrow8);
                    int i10 = query.getInt(columnIndexOrThrow9);
                    int i11 = query.getInt(columnIndexOrThrow10);
                    int i12 = query.getInt(columnIndexOrThrow11);
                    int i13 = query.getInt(columnIndexOrThrow12);
                    String string = query.getString(columnIndexOrThrow13);
                    int i14 = i;
                    int i15 = query.getInt(i14);
                    int i16 = columnIndexOrThrow;
                    int i17 = columnIndexOrThrow15;
                    int i18 = query.getInt(i17);
                    columnIndexOrThrow15 = i17;
                    int i19 = columnIndexOrThrow16;
                    int i20 = query.getInt(i19);
                    columnIndexOrThrow16 = i19;
                    int i21 = columnIndexOrThrow17;
                    int i22 = query.getInt(i21);
                    columnIndexOrThrow17 = i21;
                    int i23 = columnIndexOrThrow18;
                    int i24 = query.getInt(i23);
                    columnIndexOrThrow18 = i23;
                    int i25 = columnIndexOrThrow19;
                    int i26 = query.getInt(i25);
                    columnIndexOrThrow19 = i25;
                    int i27 = columnIndexOrThrow20;
                    int i28 = query.getInt(i27);
                    columnIndexOrThrow20 = i27;
                    int i29 = columnIndexOrThrow21;
                    columnIndexOrThrow21 = i29;
                    KhBleWorkout khBleWorkout = new KhBleWorkout(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, string, i15, i18, i20, i22, i24, i26, i28, query.getInt(i29));
                    int i30 = columnIndexOrThrow22;
                    int i31 = columnIndexOrThrow12;
                    khBleWorkout.setStartDate(query.getString(i30));
                    int i32 = columnIndexOrThrow23;
                    khBleWorkout.setEndDate(query.getString(i32));
                    int i33 = columnIndexOrThrow24;
                    if (query.getInt(i33) != 0) {
                        columnIndexOrThrow24 = i33;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i33;
                        z = false;
                    }
                    khBleWorkout.setProcessed(z);
                    arrayList.add(khBleWorkout);
                    columnIndexOrThrow = i16;
                    i = i14;
                    columnIndexOrThrow23 = i32;
                    columnIndexOrThrow12 = i31;
                    columnIndexOrThrow22 = i30;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.khsmadb.workout.KhBleWorkoutDao
    public void insertAll(List<KhBleWorkout> list) {
        this.f7165a.assertNotSuspendingTransaction();
        this.f7165a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7165a.setTransactionSuccessful();
        } finally {
            this.f7165a.endTransaction();
        }
    }
}
