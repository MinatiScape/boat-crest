package com.coveiot.khidodb.spo2;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khidodb.converts.HealthHeartRateItemConverter;
import com.coveiot.khidodb.converts.HealthSpo2ItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHHealthSpo2DataDao_Impl implements KHHealthSpo2DataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7104a;
    public final EntityInsertionAdapter<EntityHealthSpo2> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHealthSpo2> {
        public a(KHHealthSpo2DataDao_Impl kHHealthSpo2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthSpo2 entityHealthSpo2) {
            supportSQLiteStatement.bindLong(1, entityHealthSpo2.getDay());
            supportSQLiteStatement.bindLong(2, entityHealthSpo2.getMonth());
            supportSQLiteStatement.bindLong(3, entityHealthSpo2.getYear());
            supportSQLiteStatement.bindLong(4, entityHealthSpo2.getStartTime());
            if (entityHealthSpo2.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityHealthSpo2.getMacAddress());
            }
            String fromList4 = HealthHeartRateItemConverter.fromList4(entityHealthSpo2.getDate());
            if (fromList4 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, fromList4);
            }
            supportSQLiteStatement.bindLong(7, entityHealthSpo2.getTimestamp());
            supportSQLiteStatement.bindLong(8, entityHealthSpo2.isProcessed() ? 1L : 0L);
            String fromList = HealthSpo2ItemConverter.fromList(entityHealthSpo2.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, fromList);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_health_spo2` (`day`,`month`,`year`,`startTime`,`macAddress`,`date`,`timestamp`,`isProcessed`,`items`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHHealthSpo2DataDao_Impl kHHealthSpo2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_health_spo2 SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHHealthSpo2DataDao_Impl(RoomDatabase roomDatabase) {
        this.f7104a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khidodb.spo2.KHHealthSpo2DataDao
    public List<EntityHealthSpo2> getAllUnProcessedSpo2Data(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_health_spo2  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7104a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7104a, acquire, false, null);
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
                EntityHealthSpo2 entityHealthSpo2 = new EntityHealthSpo2(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                entityHealthSpo2.setDate(HealthHeartRateItemConverter.fromStr4(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                int i = columnIndexOrThrow;
                entityHealthSpo2.setTimestamp(query.getLong(columnIndexOrThrow7));
                entityHealthSpo2.setProcessed(query.getInt(columnIndexOrThrow8) != 0);
                entityHealthSpo2.setItems(HealthSpo2ItemConverter.fromStr(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9)));
                arrayList.add(entityHealthSpo2);
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khidodb.spo2.KHHealthSpo2DataDao
    public long insert(EntityHealthSpo2 entityHealthSpo2) {
        this.f7104a.assertNotSuspendingTransaction();
        this.f7104a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHealthSpo2);
            this.f7104a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7104a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.spo2.KHHealthSpo2DataDao
    public void insertAll(List<EntityHealthSpo2> list) {
        this.f7104a.assertNotSuspendingTransaction();
        this.f7104a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7104a.setTransactionSuccessful();
        } finally {
            this.f7104a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.spo2.KHHealthSpo2DataDao
    public void updateSpo2DataProcessedBefore(String str, long j) {
        this.f7104a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7104a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7104a.setTransactionSuccessful();
        } finally {
            this.f7104a.endTransaction();
            this.c.release(acquire);
        }
    }
}
