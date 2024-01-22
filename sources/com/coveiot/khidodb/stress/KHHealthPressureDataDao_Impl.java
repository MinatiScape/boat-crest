package com.coveiot.khidodb.stress;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khidodb.converts.HealthHeartRateItemConverter;
import com.coveiot.khidodb.converts.HealthPressureItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHHealthPressureDataDao_Impl implements KHHealthPressureDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7108a;
    public final EntityInsertionAdapter<EntityHealthPressure> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHealthPressure> {
        public a(KHHealthPressureDataDao_Impl kHHealthPressureDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthPressure entityHealthPressure) {
            supportSQLiteStatement.bindLong(1, entityHealthPressure.getDay());
            supportSQLiteStatement.bindLong(2, entityHealthPressure.getMonth());
            supportSQLiteStatement.bindLong(3, entityHealthPressure.getYear());
            supportSQLiteStatement.bindLong(4, entityHealthPressure.getStartTime());
            if (entityHealthPressure.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityHealthPressure.getMacAddress());
            }
            String fromList4 = HealthHeartRateItemConverter.fromList4(entityHealthPressure.getDate());
            if (fromList4 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, fromList4);
            }
            supportSQLiteStatement.bindLong(7, entityHealthPressure.getTimestamp());
            supportSQLiteStatement.bindLong(8, entityHealthPressure.isProcessed() ? 1L : 0L);
            String fromList = HealthPressureItemConverter.fromList(entityHealthPressure.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, fromList);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_health_pressure` (`day`,`month`,`year`,`startTime`,`macAddress`,`date`,`timestamp`,`isProcessed`,`items`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHHealthPressureDataDao_Impl kHHealthPressureDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_health_pressure SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHHealthPressureDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7108a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khidodb.stress.KHHealthPressureDataDao
    public List<EntityHealthPressure> getAllUnProcessedPressureData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_health_pressure  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7108a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7108a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_DAY);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "month");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "year");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "startTime");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHealthPressure entityHealthPressure = new EntityHealthPressure(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                entityHealthPressure.setDate(HealthHeartRateItemConverter.fromStr4(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                int i = columnIndexOrThrow;
                entityHealthPressure.setTimestamp(query.getLong(columnIndexOrThrow7));
                entityHealthPressure.setProcessed(query.getInt(columnIndexOrThrow8) != 0);
                entityHealthPressure.setItems(HealthPressureItemConverter.fromStr(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9)));
                arrayList.add(entityHealthPressure);
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khidodb.stress.KHHealthPressureDataDao
    public long insert(EntityHealthPressure entityHealthPressure) {
        this.f7108a.assertNotSuspendingTransaction();
        this.f7108a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHealthPressure);
            this.f7108a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7108a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.stress.KHHealthPressureDataDao
    public void insertAll(List<EntityHealthPressure> list) {
        this.f7108a.assertNotSuspendingTransaction();
        this.f7108a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7108a.setTransactionSuccessful();
        } finally {
            this.f7108a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.stress.KHHealthPressureDataDao
    public void updatePressureDataProcessedBefore(String str, long j) {
        this.f7108a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7108a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7108a.setTransactionSuccessful();
        } finally {
            this.f7108a.endTransaction();
            this.c.release(acquire);
        }
    }
}
