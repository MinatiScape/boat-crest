package com.coveiot.khidodb.noise;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khidodb.converts.HealthNoiseItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHIDOHealthNoiseDataDao_Impl implements KHIDOHealthNoiseDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7096a;
    public final EntityInsertionAdapter<EntityHealthNoise> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHealthNoise> {
        public a(KHIDOHealthNoiseDataDao_Impl kHIDOHealthNoiseDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthNoise entityHealthNoise) {
            supportSQLiteStatement.bindLong(1, entityHealthNoise.getDay());
            supportSQLiteStatement.bindLong(2, entityHealthNoise.getMonth());
            supportSQLiteStatement.bindLong(3, entityHealthNoise.getYear());
            supportSQLiteStatement.bindLong(4, entityHealthNoise.getStartTime());
            if (entityHealthNoise.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityHealthNoise.getMacAddress());
            }
            supportSQLiteStatement.bindLong(6, entityHealthNoise.getHour());
            supportSQLiteStatement.bindLong(7, entityHealthNoise.getMinute());
            supportSQLiteStatement.bindLong(8, entityHealthNoise.getSecond());
            supportSQLiteStatement.bindLong(9, entityHealthNoise.getAvgNoise());
            supportSQLiteStatement.bindLong(10, entityHealthNoise.getMaxNoise());
            supportSQLiteStatement.bindLong(11, entityHealthNoise.getMinNoise());
            supportSQLiteStatement.bindLong(12, entityHealthNoise.getInterval());
            String fromList = HealthNoiseItemConverter.fromList(entityHealthNoise.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(13);
            } else {
                supportSQLiteStatement.bindString(13, fromList);
            }
            supportSQLiteStatement.bindLong(14, entityHealthNoise.getTimestamp());
            supportSQLiteStatement.bindLong(15, entityHealthNoise.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `health_noise_v3` (`day`,`month`,`year`,`startTime`,`macAddress`,`hour`,`minute`,`second`,`avgNoise`,`maxNoise`,`minNoise`,`interval`,`items`,`timestamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHIDOHealthNoiseDataDao_Impl kHIDOHealthNoiseDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE health_noise_v3 SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHIDOHealthNoiseDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7096a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khidodb.noise.KHIDOHealthNoiseDataDao
    public List<EntityHealthNoise> getAllUnProcessedStepsData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from health_noise_v3  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7096a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7096a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_DAY);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "month");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "year");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "startTime");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_HOUR);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "minute");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "second");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "avgNoise");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "maxNoise");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "minNoise");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "interval");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHealthNoise entityHealthNoise = new EntityHealthNoise(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    int i2 = columnIndexOrThrow;
                    entityHealthNoise.setHour(query.getInt(columnIndexOrThrow6));
                    entityHealthNoise.setMinute(query.getInt(columnIndexOrThrow7));
                    entityHealthNoise.setSecond(query.getInt(columnIndexOrThrow8));
                    entityHealthNoise.setAvgNoise(query.getInt(columnIndexOrThrow9));
                    entityHealthNoise.setMaxNoise(query.getInt(columnIndexOrThrow10));
                    entityHealthNoise.setMinNoise(query.getInt(columnIndexOrThrow11));
                    entityHealthNoise.setInterval(query.getInt(columnIndexOrThrow12));
                    entityHealthNoise.setItems(HealthNoiseItemConverter.fromStr(query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13)));
                    int i3 = columnIndexOrThrow12;
                    int i4 = i;
                    int i5 = columnIndexOrThrow11;
                    entityHealthNoise.setTimestamp(query.getLong(i4));
                    int i6 = columnIndexOrThrow15;
                    entityHealthNoise.setProcessed(query.getInt(i6) != 0);
                    arrayList.add(entityHealthNoise);
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow11 = i5;
                    columnIndexOrThrow12 = i3;
                    i = i4;
                    columnIndexOrThrow = i2;
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

    @Override // com.coveiot.khidodb.noise.KHIDOHealthNoiseDataDao
    public long insert(EntityHealthNoise entityHealthNoise) {
        this.f7096a.assertNotSuspendingTransaction();
        this.f7096a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHealthNoise);
            this.f7096a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7096a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.noise.KHIDOHealthNoiseDataDao
    public void insertAll(List<EntityHealthNoise> list) {
        this.f7096a.assertNotSuspendingTransaction();
        this.f7096a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7096a.setTransactionSuccessful();
        } finally {
            this.f7096a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.noise.KHIDOHealthNoiseDataDao
    public void updateStepsDataProcessedBefore(String str, long j) {
        this.f7096a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7096a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7096a.setTransactionSuccessful();
        } finally {
            this.f7096a.endTransaction();
            this.c.release(acquire);
        }
    }
}
