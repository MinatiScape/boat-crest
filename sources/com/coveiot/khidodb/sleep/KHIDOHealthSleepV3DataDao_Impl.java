package com.coveiot.khidodb.sleep;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khidodb.converts.HealthSleepItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHIDOHealthSleepV3DataDao_Impl implements KHIDOHealthSleepV3DataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7100a;
    public final EntityInsertionAdapter<EntityHealthSleepV3> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHealthSleepV3> {
        public a(KHIDOHealthSleepV3DataDao_Impl kHIDOHealthSleepV3DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthSleepV3 entityHealthSleepV3) {
            supportSQLiteStatement.bindLong(1, entityHealthSleepV3.getGet_up_day());
            supportSQLiteStatement.bindLong(2, entityHealthSleepV3.getGet_up_month());
            supportSQLiteStatement.bindLong(3, entityHealthSleepV3.getGet_up_year());
            supportSQLiteStatement.bindLong(4, entityHealthSleepV3.getGet_up_hour());
            supportSQLiteStatement.bindLong(5, entityHealthSleepV3.getGet_up_minte());
            if (entityHealthSleepV3.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, entityHealthSleepV3.getMacAddress());
            }
            supportSQLiteStatement.bindLong(7, entityHealthSleepV3.getData_type());
            supportSQLiteStatement.bindLong(8, entityHealthSleepV3.getFall_asleep_year());
            supportSQLiteStatement.bindLong(9, entityHealthSleepV3.getFall_asleep_month());
            supportSQLiteStatement.bindLong(10, entityHealthSleepV3.getFall_asleep_day());
            supportSQLiteStatement.bindLong(11, entityHealthSleepV3.getFall_asleep_hour());
            supportSQLiteStatement.bindLong(12, entityHealthSleepV3.getFall_asleep_minte());
            supportSQLiteStatement.bindLong(13, entityHealthSleepV3.getTotal_sleep_time_mins());
            supportSQLiteStatement.bindLong(14, entityHealthSleepV3.getWake_mins());
            supportSQLiteStatement.bindLong(15, entityHealthSleepV3.getLight_mins());
            supportSQLiteStatement.bindLong(16, entityHealthSleepV3.getRem_mins());
            supportSQLiteStatement.bindLong(17, entityHealthSleepV3.getDeep_mins());
            supportSQLiteStatement.bindLong(18, entityHealthSleepV3.getWake_count());
            supportSQLiteStatement.bindLong(19, entityHealthSleepV3.getLight_count());
            supportSQLiteStatement.bindLong(20, entityHealthSleepV3.getRem_count());
            supportSQLiteStatement.bindLong(21, entityHealthSleepV3.getDeep_count());
            supportSQLiteStatement.bindLong(22, entityHealthSleepV3.getAwrr_status());
            supportSQLiteStatement.bindLong(23, entityHealthSleepV3.getSleep_score());
            supportSQLiteStatement.bindLong(24, entityHealthSleepV3.getBreath_quality());
            String fromList = HealthSleepItemConverter.fromList(entityHealthSleepV3.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(25);
            } else {
                supportSQLiteStatement.bindString(25, fromList);
            }
            supportSQLiteStatement.bindLong(26, entityHealthSleepV3.getTimestamp());
            supportSQLiteStatement.bindLong(27, entityHealthSleepV3.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `health_sleep_v3` (`get_up_day`,`get_up_month`,`get_up_year`,`get_up_hour`,`get_up_minte`,`macAddress`,`data_type`,`fall_asleep_year`,`fall_asleep_month`,`fall_asleep_day`,`fall_asleep_hour`,`fall_asleep_minte`,`total_sleep_time_mins`,`wake_mins`,`light_mins`,`rem_mins`,`deep_mins`,`wake_count`,`light_count`,`rem_count`,`deep_count`,`awrr_status`,`sleep_score`,`breath_quality`,`items`,`timestamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHIDOHealthSleepV3DataDao_Impl kHIDOHealthSleepV3DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE HEALTH_SLEEP_V3 SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHIDOHealthSleepV3DataDao_Impl(RoomDatabase roomDatabase) {
        this.f7100a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khidodb.sleep.KHIDOHealthSleepV3DataDao
    public List<EntityHealthSleepV3> getAllUnProcessedSleepData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from HEALTH_SLEEP_V3  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7100a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7100a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "get_up_day");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "get_up_month");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "get_up_year");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "get_up_hour");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "get_up_minte");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "data_type");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "fall_asleep_year");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "fall_asleep_month");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fall_asleep_day");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "fall_asleep_hour");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fall_asleep_minte");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "total_sleep_time_mins");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "wake_mins");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "light_mins");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "rem_mins");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "deep_mins");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "wake_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "light_count");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "rem_count");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deep_count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "awrr_status");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "sleep_score");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "breath_quality");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHealthSleepV3 entityHealthSleepV3 = new EntityHealthSleepV3(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    int i3 = columnIndexOrThrow;
                    entityHealthSleepV3.setData_type(query.getInt(columnIndexOrThrow7));
                    entityHealthSleepV3.setFall_asleep_year(query.getInt(columnIndexOrThrow8));
                    entityHealthSleepV3.setFall_asleep_month(query.getInt(columnIndexOrThrow9));
                    entityHealthSleepV3.setFall_asleep_day(query.getInt(columnIndexOrThrow10));
                    entityHealthSleepV3.setFall_asleep_hour(query.getInt(columnIndexOrThrow11));
                    entityHealthSleepV3.setFall_asleep_minte(query.getInt(columnIndexOrThrow12));
                    entityHealthSleepV3.setTotal_sleep_time_mins(query.getInt(columnIndexOrThrow13));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow11;
                    entityHealthSleepV3.setWake_mins(query.getInt(i4));
                    int i6 = columnIndexOrThrow15;
                    entityHealthSleepV3.setLight_mins(query.getInt(i6));
                    int i7 = columnIndexOrThrow16;
                    entityHealthSleepV3.setRem_mins(query.getInt(i7));
                    int i8 = columnIndexOrThrow17;
                    entityHealthSleepV3.setDeep_mins(query.getInt(i8));
                    int i9 = columnIndexOrThrow18;
                    entityHealthSleepV3.setWake_count(query.getInt(i9));
                    int i10 = columnIndexOrThrow19;
                    entityHealthSleepV3.setLight_count(query.getInt(i10));
                    int i11 = columnIndexOrThrow20;
                    entityHealthSleepV3.setRem_count(query.getInt(i11));
                    int i12 = columnIndexOrThrow21;
                    entityHealthSleepV3.setDeep_count(query.getInt(i12));
                    int i13 = columnIndexOrThrow22;
                    entityHealthSleepV3.setAwrr_status(query.getInt(i13));
                    int i14 = columnIndexOrThrow23;
                    entityHealthSleepV3.setSleep_score(query.getInt(i14));
                    int i15 = columnIndexOrThrow24;
                    entityHealthSleepV3.setBreath_quality(query.getInt(i15));
                    int i16 = columnIndexOrThrow25;
                    if (query.isNull(i16)) {
                        i = i15;
                        string = null;
                    } else {
                        string = query.getString(i16);
                        i = i15;
                    }
                    entityHealthSleepV3.setItems(HealthSleepItemConverter.fromStr(string));
                    int i17 = columnIndexOrThrow12;
                    int i18 = columnIndexOrThrow26;
                    entityHealthSleepV3.setTimestamp(query.getLong(i18));
                    int i19 = columnIndexOrThrow27;
                    entityHealthSleepV3.setProcessed(query.getInt(i19) != 0);
                    arrayList.add(entityHealthSleepV3);
                    columnIndexOrThrow27 = i19;
                    columnIndexOrThrow12 = i17;
                    columnIndexOrThrow25 = i16;
                    columnIndexOrThrow11 = i5;
                    columnIndexOrThrow26 = i18;
                    i2 = i4;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow17 = i8;
                    columnIndexOrThrow18 = i9;
                    columnIndexOrThrow19 = i10;
                    columnIndexOrThrow20 = i11;
                    columnIndexOrThrow21 = i12;
                    columnIndexOrThrow22 = i13;
                    columnIndexOrThrow23 = i14;
                    columnIndexOrThrow = i3;
                    columnIndexOrThrow24 = i;
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

    @Override // com.coveiot.khidodb.sleep.KHIDOHealthSleepV3DataDao
    public long insert(EntityHealthSleepV3 entityHealthSleepV3) {
        this.f7100a.assertNotSuspendingTransaction();
        this.f7100a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHealthSleepV3);
            this.f7100a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7100a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.sleep.KHIDOHealthSleepV3DataDao
    public void insertAll(List<EntityHealthSleepV3> list) {
        this.f7100a.assertNotSuspendingTransaction();
        this.f7100a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7100a.setTransactionSuccessful();
        } finally {
            this.f7100a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.sleep.KHIDOHealthSleepV3DataDao
    public void updateSleepDataProcessedBefore(String str, long j) {
        this.f7100a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7100a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7100a.setTransactionSuccessful();
        } finally {
            this.f7100a.endTransaction();
            this.c.release(acquire);
        }
    }
}
