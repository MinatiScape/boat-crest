package com.coveiot.kheastapexdb.walk;

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
public final class KHEAStepsDataDao_Impl implements KHEAStepsDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7077a;
    public final EntityInsertionAdapter<EntityEAStepsData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityEAStepsData> {
        public a(KHEAStepsDataDao_Impl kHEAStepsDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityEAStepsData entityEAStepsData) {
            EntityEAStepsData entityEAStepsData2 = entityEAStepsData;
            supportSQLiteStatement.bindLong(1, entityEAStepsData2.getEpochTimeStamp());
            if (entityEAStepsData2.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityEAStepsData2.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityEAStepsData2.getSteps());
            supportSQLiteStatement.bindLong(4, entityEAStepsData2.getCalorie());
            supportSQLiteStatement.bindLong(5, entityEAStepsData2.getDistance());
            supportSQLiteStatement.bindLong(6, entityEAStepsData2.getDuration());
            supportSQLiteStatement.bindLong(7, entityEAStepsData2.getAvgHeartRate());
            supportSQLiteStatement.bindLong(8, entityEAStepsData2.getTimeStamp());
            supportSQLiteStatement.bindLong(9, entityEAStepsData2.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_ea_steps` (`epochTimeStamp`,`macAddress`,`steps`,`calorie`,`distance`,`duration`,`avgHeartRate`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHEAStepsDataDao_Impl kHEAStepsDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_ea_steps SET isProcessed = 1 WHERE macAddress=? and timeStamp<? AND isProcessed == 0";
        }
    }

    public KHEAStepsDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7077a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.kheastapexdb.walk.KHEAStepsDataDao
    public List<EntityEAStepsData> getAllUnProcessedStepsData(String str) {
        String string;
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_ea_steps  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7077a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7077a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "epochTimeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "steps");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "calorie");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "avgHeartRate");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                long j = query.getLong(columnIndexOrThrow);
                if (query.isNull(columnIndexOrThrow2)) {
                    i = columnIndexOrThrow;
                    string = null;
                } else {
                    string = query.getString(columnIndexOrThrow2);
                    i = columnIndexOrThrow;
                }
                EntityEAStepsData entityEAStepsData = new EntityEAStepsData(j, string);
                entityEAStepsData.setSteps(query.getInt(columnIndexOrThrow3));
                entityEAStepsData.setCalorie(query.getInt(columnIndexOrThrow4));
                entityEAStepsData.setDistance(query.getInt(columnIndexOrThrow5));
                entityEAStepsData.setDuration(query.getInt(columnIndexOrThrow6));
                entityEAStepsData.setAvgHeartRate(query.getInt(columnIndexOrThrow7));
                entityEAStepsData.setTimeStamp(query.getLong(columnIndexOrThrow8));
                entityEAStepsData.setProcessed(query.getInt(columnIndexOrThrow9) != 0);
                arrayList.add(entityEAStepsData);
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.kheastapexdb.walk.KHEAStepsDataDao
    public long insert(EntityEAStepsData entityEAStepsData) {
        this.f7077a.assertNotSuspendingTransaction();
        this.f7077a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityEAStepsData);
            this.f7077a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7077a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.walk.KHEAStepsDataDao
    public void insertAll(List<EntityEAStepsData> list) {
        this.f7077a.assertNotSuspendingTransaction();
        this.f7077a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7077a.setTransactionSuccessful();
        } finally {
            this.f7077a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.walk.KHEAStepsDataDao
    public void updateStepsDataProcessedBefore(String str, long j) {
        this.f7077a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7077a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7077a.setTransactionSuccessful();
        } finally {
            this.f7077a.endTransaction();
            this.c.release(acquire);
        }
    }
}
