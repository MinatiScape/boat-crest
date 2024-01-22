package com.coveiot.khidodb.heartrate;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khidodb.converts.HealthHeartRateItemConverter;
import com.coveiot.khidodb.converts.HealthSportItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHIDOHealthHeartRateDataDao_Impl implements KHIDOHealthHeartRateDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7090a;
    public final EntityInsertionAdapter<EntityHealthHeartRateSecond> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHealthHeartRateSecond> {
        public a(KHIDOHealthHeartRateDataDao_Impl kHIDOHealthHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthHeartRateSecond entityHealthHeartRateSecond) {
            supportSQLiteStatement.bindLong(1, entityHealthHeartRateSecond.getDay());
            supportSQLiteStatement.bindLong(2, entityHealthHeartRateSecond.getMonth());
            supportSQLiteStatement.bindLong(3, entityHealthHeartRateSecond.getYear());
            supportSQLiteStatement.bindLong(4, entityHealthHeartRateSecond.getStartTime());
            if (entityHealthHeartRateSecond.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityHealthHeartRateSecond.getMacAddress());
            }
            String fromList = HealthHeartRateItemConverter.fromList(entityHealthHeartRateSecond.getHrInterval());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, fromList);
            }
            String fromList2 = HealthHeartRateItemConverter.fromList2(entityHealthHeartRateSecond.getItems());
            if (fromList2 == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, fromList2);
            }
            String fromList3 = HealthHeartRateItemConverter.fromList3(entityHealthHeartRateSecond.getHr_data());
            if (fromList3 == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, fromList3);
            }
            supportSQLiteStatement.bindLong(9, entityHealthHeartRateSecond.getSilentHR());
            if (entityHealthHeartRateSecond.getDataId() == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindLong(10, entityHealthHeartRateSecond.getDataId().longValue());
            }
            supportSQLiteStatement.bindLong(11, entityHealthHeartRateSecond.getDId());
            String fromList4 = HealthHeartRateItemConverter.fromList4(entityHealthHeartRateSecond.getDate());
            if (fromList4 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, fromList4);
            }
            supportSQLiteStatement.bindLong(13, entityHealthHeartRateSecond.getFive_min_max_data());
            supportSQLiteStatement.bindLong(14, entityHealthHeartRateSecond.getFive_min_min_data());
            supportSQLiteStatement.bindLong(15, entityHealthHeartRateSecond.getFive_min_avg_data());
            String fromList22 = HealthSportItemConverter.fromList2(entityHealthHeartRateSecond.getFive_min_data());
            if (fromList22 == null) {
                supportSQLiteStatement.bindNull(16);
            } else {
                supportSQLiteStatement.bindString(16, fromList22);
            }
            supportSQLiteStatement.bindLong(17, entityHealthHeartRateSecond.getHr_data_count());
            supportSQLiteStatement.bindLong(18, entityHealthHeartRateSecond.getTimestamp());
            supportSQLiteStatement.bindLong(19, entityHealthHeartRateSecond.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `health_heartrate_v3` (`day`,`month`,`year`,`startTime`,`macAddress`,`hrInterval`,`items`,`hr_data`,`silentHR`,`dataId`,`dId`,`date`,`five_min_max_data`,`five_min_min_data`,`five_min_avg_data`,`five_min_data`,`hr_data_count`,`timestamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHIDOHealthHeartRateDataDao_Impl kHIDOHealthHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE HEALTH_HEARTRATE_V3 SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHIDOHealthHeartRateDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7090a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khidodb.heartrate.KHIDOHealthHeartRateDataDao
    public List<EntityHealthHeartRateSecond> getAllUnProcessedStepsData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        String string2;
        int i2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from HEALTH_HEARTRATE_V3  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7090a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7090a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_DAY);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "month");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "year");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "startTime");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "hrInterval");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_data");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "silentHR");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "dataId");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "five_min_max_data");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "five_min_min_data");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "five_min_avg_data");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "five_min_data");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "hr_data_count");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHealthHeartRateSecond entityHealthHeartRateSecond = new EntityHealthHeartRateSecond(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    if (query.isNull(columnIndexOrThrow6)) {
                        i = columnIndexOrThrow;
                        string = null;
                    } else {
                        string = query.getString(columnIndexOrThrow6);
                        i = columnIndexOrThrow;
                    }
                    entityHealthHeartRateSecond.setHrInterval(HealthHeartRateItemConverter.fromStr(string));
                    entityHealthHeartRateSecond.setItems(HealthHeartRateItemConverter.fromStr2(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                    entityHealthHeartRateSecond.setHr_data(HealthHeartRateItemConverter.fromStr3(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8)));
                    entityHealthHeartRateSecond.setSilentHR(query.getInt(columnIndexOrThrow9));
                    entityHealthHeartRateSecond.setDataId(query.isNull(columnIndexOrThrow10) ? null : Long.valueOf(query.getLong(columnIndexOrThrow10)));
                    int i4 = columnIndexOrThrow2;
                    int i5 = columnIndexOrThrow3;
                    entityHealthHeartRateSecond.setDId(query.getLong(columnIndexOrThrow11));
                    entityHealthHeartRateSecond.setDate(HealthHeartRateItemConverter.fromStr4(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12)));
                    entityHealthHeartRateSecond.setFive_min_max_data(query.getInt(columnIndexOrThrow13));
                    int i6 = i3;
                    entityHealthHeartRateSecond.setFive_min_min_data(query.getInt(i6));
                    int i7 = columnIndexOrThrow15;
                    entityHealthHeartRateSecond.setFive_min_avg_data(query.getInt(i7));
                    int i8 = columnIndexOrThrow16;
                    if (query.isNull(i8)) {
                        i2 = i8;
                        string2 = null;
                    } else {
                        string2 = query.getString(i8);
                        i2 = i8;
                    }
                    entityHealthHeartRateSecond.setFive_min_data(HealthSportItemConverter.fromStr2(string2));
                    int i9 = columnIndexOrThrow11;
                    int i10 = columnIndexOrThrow17;
                    entityHealthHeartRateSecond.setHr_data_count(query.getInt(i10));
                    int i11 = columnIndexOrThrow12;
                    int i12 = columnIndexOrThrow18;
                    int i13 = columnIndexOrThrow13;
                    entityHealthHeartRateSecond.setTimestamp(query.getLong(i12));
                    int i14 = columnIndexOrThrow19;
                    entityHealthHeartRateSecond.setProcessed(query.getInt(i14) != 0);
                    arrayList.add(entityHealthHeartRateSecond);
                    columnIndexOrThrow19 = i14;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow13 = i13;
                    columnIndexOrThrow17 = i10;
                    columnIndexOrThrow18 = i12;
                    columnIndexOrThrow11 = i9;
                    columnIndexOrThrow16 = i2;
                    columnIndexOrThrow = i;
                    i3 = i6;
                    columnIndexOrThrow2 = i4;
                    columnIndexOrThrow15 = i7;
                    columnIndexOrThrow3 = i5;
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

    @Override // com.coveiot.khidodb.heartrate.KHIDOHealthHeartRateDataDao
    public long insert(EntityHealthHeartRateSecond entityHealthHeartRateSecond) {
        this.f7090a.assertNotSuspendingTransaction();
        this.f7090a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHealthHeartRateSecond);
            this.f7090a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7090a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.heartrate.KHIDOHealthHeartRateDataDao
    public void insertAll(List<EntityHealthHeartRateSecond> list) {
        this.f7090a.assertNotSuspendingTransaction();
        this.f7090a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7090a.setTransactionSuccessful();
        } finally {
            this.f7090a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.heartrate.KHIDOHealthHeartRateDataDao
    public void updateStepsDataProcessedBefore(String str, long j) {
        this.f7090a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7090a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7090a.setTransactionSuccessful();
        } finally {
            this.f7090a.endTransaction();
            this.c.release(acquire);
        }
    }
}
