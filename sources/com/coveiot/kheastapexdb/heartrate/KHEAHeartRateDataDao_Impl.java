package com.coveiot.kheastapexdb.heartrate;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHEAHeartRateDataDao_Impl implements KHEAHeartRateDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7060a;
    public final EntityInsertionAdapter<EntityEAHeartRateData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityEAHeartRateData> {
        public a(KHEAHeartRateDataDao_Impl kHEAHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityEAHeartRateData entityEAHeartRateData) {
            EntityEAHeartRateData entityEAHeartRateData2 = entityEAHeartRateData;
            supportSQLiteStatement.bindLong(1, entityEAHeartRateData2.getEpochTimeStamp());
            if (entityEAHeartRateData2.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityEAHeartRateData2.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityEAHeartRateData2.getHrValue());
            supportSQLiteStatement.bindLong(4, entityEAHeartRateData2.getTimeStamp());
            supportSQLiteStatement.bindLong(5, entityEAHeartRateData2.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_ea_heart_rate` (`epochTimeStamp`,`macAddress`,`hrValue`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHEAHeartRateDataDao_Impl kHEAHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_ea_heart_rate SET isProcessed = 1 WHERE macAddress=? AND isProcessed == 0 AND timeStamp<?";
        }
    }

    public KHEAHeartRateDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7060a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.kheastapexdb.heartrate.KHEAHeartRateDataDao
    public List<EntityEAHeartRateData> getAllUnProcessedHRData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_ea_heart_rate  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7060a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7060a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "epochTimeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "hrValue");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityEAHeartRateData entityEAHeartRateData = new EntityEAHeartRateData(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                entityEAHeartRateData.setHrValue(query.getInt(columnIndexOrThrow3));
                entityEAHeartRateData.setTimeStamp(query.getLong(columnIndexOrThrow4));
                entityEAHeartRateData.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                arrayList.add(entityEAHeartRateData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.kheastapexdb.heartrate.KHEAHeartRateDataDao
    public long insert(EntityEAHeartRateData entityEAHeartRateData) {
        this.f7060a.assertNotSuspendingTransaction();
        this.f7060a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityEAHeartRateData);
            this.f7060a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7060a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.heartrate.KHEAHeartRateDataDao
    public void insertAll(List<EntityEAHeartRateData> list) {
        this.f7060a.assertNotSuspendingTransaction();
        this.f7060a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7060a.setTransactionSuccessful();
        } finally {
            this.f7060a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.heartrate.KHEAHeartRateDataDao
    public void updateHRDataProcessedBefore(String str, long j) {
        this.f7060a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7060a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7060a.setTransactionSuccessful();
        } finally {
            this.f7060a.endTransaction();
            this.c.release(acquire);
        }
    }
}
