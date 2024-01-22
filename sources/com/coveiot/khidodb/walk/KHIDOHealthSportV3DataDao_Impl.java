package com.coveiot.khidodb.walk;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khidodb.converts.HealthSportItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHIDOHealthSportV3DataDao_Impl implements KHIDOHealthSportV3DataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7112a;
    public final EntityInsertionAdapter<EntityHealthSportV3> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHealthSportV3> {
        public a(KHIDOHealthSportV3DataDao_Impl kHIDOHealthSportV3DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthSportV3 entityHealthSportV3) {
            supportSQLiteStatement.bindLong(1, entityHealthSportV3.getDay());
            supportSQLiteStatement.bindLong(2, entityHealthSportV3.getMonth());
            supportSQLiteStatement.bindLong(3, entityHealthSportV3.getYear());
            supportSQLiteStatement.bindLong(4, entityHealthSportV3.getStart_time());
            if (entityHealthSportV3.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityHealthSportV3.getMacAddress());
            }
            supportSQLiteStatement.bindLong(6, entityHealthSportV3.getHour());
            supportSQLiteStatement.bindLong(7, entityHealthSportV3.getMinute());
            supportSQLiteStatement.bindLong(8, entityHealthSportV3.getSecond());
            supportSQLiteStatement.bindLong(9, entityHealthSportV3.getPer_minute());
            supportSQLiteStatement.bindLong(10, entityHealthSportV3.getTotal_step());
            supportSQLiteStatement.bindLong(11, entityHealthSportV3.getTotal_rest_activity_calories());
            supportSQLiteStatement.bindLong(12, entityHealthSportV3.getTotal_distances());
            supportSQLiteStatement.bindLong(13, entityHealthSportV3.getTotal_active_time());
            supportSQLiteStatement.bindLong(14, entityHealthSportV3.getTotal_activity_calories());
            supportSQLiteStatement.bindLong(15, entityHealthSportV3.getItem_count());
            String fromList2 = HealthSportItemConverter.fromList2(entityHealthSportV3.getWear_flag_array());
            if (fromList2 == null) {
                supportSQLiteStatement.bindNull(16);
            } else {
                supportSQLiteStatement.bindString(16, fromList2);
            }
            String fromList22 = HealthSportItemConverter.fromList2(entityHealthSportV3.getType());
            if (fromList22 == null) {
                supportSQLiteStatement.bindNull(17);
            } else {
                supportSQLiteStatement.bindString(17, fromList22);
            }
            String fromList = HealthSportItemConverter.fromList(entityHealthSportV3.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(18);
            } else {
                supportSQLiteStatement.bindString(18, fromList);
            }
            supportSQLiteStatement.bindLong(19, entityHealthSportV3.getWalk_goal_time());
            supportSQLiteStatement.bindLong(20, entityHealthSportV3.getTimestamp());
            supportSQLiteStatement.bindLong(21, entityHealthSportV3.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `health_sport_v3` (`day`,`month`,`year`,`start_time`,`macAddress`,`hour`,`minute`,`second`,`per_minute`,`total_step`,`total_rest_activity_calories`,`total_distances`,`total_active_time`,`total_activity_calories`,`item_count`,`wear_flag_array`,`type`,`items`,`walk_goal_time`,`timestamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHIDOHealthSportV3DataDao_Impl kHIDOHealthSportV3DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE health_sport_v3 SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHIDOHealthSportV3DataDao_Impl(RoomDatabase roomDatabase) {
        this.f7112a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khidodb.walk.KHIDOHealthSportV3DataDao
    public List<EntityHealthSportV3> getAllUnProcessedStepsData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        String string2;
        String string3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from health_sport_v3  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7112a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7112a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_DAY);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "month");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "year");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_HOUR);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "minute");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "second");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "per_minute");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "total_step");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "total_rest_activity_calories");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "total_distances");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "total_active_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "total_activity_calories");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "item_count");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "wear_flag_array");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "type");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "walk_goal_time");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHealthSportV3 entityHealthSportV3 = new EntityHealthSportV3(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    int i3 = columnIndexOrThrow;
                    entityHealthSportV3.setHour(query.getInt(columnIndexOrThrow6));
                    entityHealthSportV3.setMinute(query.getInt(columnIndexOrThrow7));
                    entityHealthSportV3.setSecond(query.getInt(columnIndexOrThrow8));
                    entityHealthSportV3.setPer_minute(query.getInt(columnIndexOrThrow9));
                    entityHealthSportV3.setTotal_step(query.getInt(columnIndexOrThrow10));
                    entityHealthSportV3.setTotal_rest_activity_calories(query.getInt(columnIndexOrThrow11));
                    entityHealthSportV3.setTotal_distances(query.getInt(columnIndexOrThrow12));
                    entityHealthSportV3.setTotal_active_time(query.getInt(columnIndexOrThrow13));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow11;
                    entityHealthSportV3.setTotal_activity_calories(query.getInt(i4));
                    int i6 = columnIndexOrThrow15;
                    entityHealthSportV3.setItem_count(query.getInt(i6));
                    int i7 = columnIndexOrThrow16;
                    if (query.isNull(i7)) {
                        i = i7;
                        string = null;
                    } else {
                        string = query.getString(i7);
                        i = i7;
                    }
                    entityHealthSportV3.setWear_flag_array(HealthSportItemConverter.fromStr2(string));
                    int i8 = columnIndexOrThrow17;
                    if (query.isNull(i8)) {
                        columnIndexOrThrow17 = i8;
                        string2 = null;
                    } else {
                        string2 = query.getString(i8);
                        columnIndexOrThrow17 = i8;
                    }
                    entityHealthSportV3.setType(HealthSportItemConverter.fromStr2(string2));
                    int i9 = columnIndexOrThrow18;
                    if (query.isNull(i9)) {
                        columnIndexOrThrow18 = i9;
                        string3 = null;
                    } else {
                        string3 = query.getString(i9);
                        columnIndexOrThrow18 = i9;
                    }
                    entityHealthSportV3.setItems(HealthSportItemConverter.fromStr(string3));
                    int i10 = columnIndexOrThrow19;
                    entityHealthSportV3.setWalk_goal_time(query.getInt(i10));
                    int i11 = columnIndexOrThrow12;
                    int i12 = columnIndexOrThrow20;
                    int i13 = columnIndexOrThrow13;
                    entityHealthSportV3.setTimestamp(query.getLong(i12));
                    int i14 = columnIndexOrThrow21;
                    entityHealthSportV3.setProcessed(query.getInt(i14) != 0);
                    arrayList.add(entityHealthSportV3);
                    columnIndexOrThrow21 = i14;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow13 = i13;
                    columnIndexOrThrow19 = i10;
                    columnIndexOrThrow20 = i12;
                    columnIndexOrThrow11 = i5;
                    columnIndexOrThrow = i3;
                    i2 = i4;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i;
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

    @Override // com.coveiot.khidodb.walk.KHIDOHealthSportV3DataDao
    public long insert(EntityHealthSportV3 entityHealthSportV3) {
        this.f7112a.assertNotSuspendingTransaction();
        this.f7112a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHealthSportV3);
            this.f7112a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7112a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.walk.KHIDOHealthSportV3DataDao
    public void insertAll(List<EntityHealthSportV3> list) {
        this.f7112a.assertNotSuspendingTransaction();
        this.f7112a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7112a.setTransactionSuccessful();
        } finally {
            this.f7112a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.walk.KHIDOHealthSportV3DataDao
    public void updateStepsDataProcessedBefore(String str, long j) {
        this.f7112a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7112a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7112a.setTransactionSuccessful();
        } finally {
            this.f7112a.endTransaction();
            this.c.release(acquire);
        }
    }
}
