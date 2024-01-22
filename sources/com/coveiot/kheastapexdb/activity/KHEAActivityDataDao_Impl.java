package com.coveiot.kheastapexdb.activity;

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
public final class KHEAActivityDataDao_Impl implements KHEAActivityDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7056a;
    public final EntityInsertionAdapter<EntityEAActivityData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityEAActivityData> {
        public a(KHEAActivityDataDao_Impl kHEAActivityDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityEAActivityData entityEAActivityData) {
            EntityEAActivityData entityEAActivityData2 = entityEAActivityData;
            supportSQLiteStatement.bindLong(1, entityEAActivityData2.getBeginTimestamp());
            if (entityEAActivityData2.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityEAActivityData2.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityEAActivityData2.getActivityType());
            supportSQLiteStatement.bindLong(4, entityEAActivityData2.getEndTimestamp());
            supportSQLiteStatement.bindLong(5, entityEAActivityData2.getSteps());
            supportSQLiteStatement.bindLong(6, entityEAActivityData2.getCalorie());
            supportSQLiteStatement.bindLong(7, entityEAActivityData2.getDistance());
            supportSQLiteStatement.bindLong(8, entityEAActivityData2.getDuration());
            supportSQLiteStatement.bindLong(9, entityEAActivityData2.getTrainingEffectNormal());
            supportSQLiteStatement.bindLong(10, entityEAActivityData2.getTrainingEffectWarmUp());
            supportSQLiteStatement.bindLong(11, entityEAActivityData2.getTrainingEffectFatConsumption());
            supportSQLiteStatement.bindLong(12, entityEAActivityData2.getTrainingEffectAerobic());
            supportSQLiteStatement.bindLong(13, entityEAActivityData2.getTrainingEffectAnaerobic());
            supportSQLiteStatement.bindLong(14, entityEAActivityData2.getTrainingEffectLimit());
            supportSQLiteStatement.bindLong(15, entityEAActivityData2.getAvgHeartRate());
            supportSQLiteStatement.bindDouble(16, entityEAActivityData2.getAvgTemperature());
            supportSQLiteStatement.bindLong(17, entityEAActivityData2.getAvgSpeed());
            supportSQLiteStatement.bindLong(18, entityEAActivityData2.getAvgPace());
            supportSQLiteStatement.bindLong(19, entityEAActivityData2.getAvgStepFreq());
            supportSQLiteStatement.bindLong(20, entityEAActivityData2.getAvgStride());
            supportSQLiteStatement.bindLong(21, entityEAActivityData2.getAvgAltitude());
            supportSQLiteStatement.bindLong(22, entityEAActivityData2.getMaxHeartRate());
            supportSQLiteStatement.bindLong(23, entityEAActivityData2.getMinHeartRate());
            supportSQLiteStatement.bindLong(24, entityEAActivityData2.getTimeStamp());
            supportSQLiteStatement.bindLong(25, entityEAActivityData2.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_ea_activity` (`beginTimestamp`,`macAddress`,`activityType`,`endTimestamp`,`steps`,`calorie`,`distance`,`duration`,`trainingEffectNormal`,`trainingEffectWarmUp`,`trainingEffectFatConsumption`,`trainingEffectAerobic`,`trainingEffectAnaerobic`,`trainingEffectLimit`,`avgHeartRate`,`avgTemperature`,`avgSpeed`,`avgPace`,`avgStepFreq`,`avgStride`,`avgAltitude`,`maxHeartRate`,`minHeartRate`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHEAActivityDataDao_Impl kHEAActivityDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_ea_activity SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHEAActivityDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7056a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.kheastapexdb.activity.KHEAActivityDataDao
    public List<EntityEAActivityData> getAllUnProcessedActivityData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        String string;
        int i2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_ea_activity  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7056a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7056a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "beginTimestamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityType");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "endTimestamp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "steps");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calorie");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "trainingEffectNormal");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "trainingEffectWarmUp");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "trainingEffectFatConsumption");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "trainingEffectAerobic");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "trainingEffectAnaerobic");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "trainingEffectLimit");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "avgHeartRate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "avgTemperature");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFreq");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "avgStride");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avgAltitude");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "maxHeartRate");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "minHeartRate");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    long j = query.getLong(columnIndexOrThrow);
                    if (query.isNull(columnIndexOrThrow2)) {
                        i = columnIndexOrThrow;
                        i2 = columnIndexOrThrow2;
                        string = null;
                    } else {
                        i = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow2);
                        i2 = columnIndexOrThrow2;
                    }
                    EntityEAActivityData entityEAActivityData = new EntityEAActivityData(j, string);
                    entityEAActivityData.setActivityType(query.getInt(columnIndexOrThrow3));
                    entityEAActivityData.setEndTimestamp(query.getLong(columnIndexOrThrow4));
                    entityEAActivityData.setSteps(query.getInt(columnIndexOrThrow5));
                    entityEAActivityData.setCalorie(query.getInt(columnIndexOrThrow6));
                    entityEAActivityData.setDistance(query.getInt(columnIndexOrThrow7));
                    entityEAActivityData.setDuration(query.getInt(columnIndexOrThrow8));
                    entityEAActivityData.setTrainingEffectNormal(query.getInt(columnIndexOrThrow9));
                    entityEAActivityData.setTrainingEffectWarmUp(query.getInt(columnIndexOrThrow10));
                    entityEAActivityData.setTrainingEffectFatConsumption(query.getInt(columnIndexOrThrow11));
                    entityEAActivityData.setTrainingEffectAerobic(query.getInt(columnIndexOrThrow12));
                    entityEAActivityData.setTrainingEffectAnaerobic(query.getInt(columnIndexOrThrow13));
                    int i4 = i3;
                    entityEAActivityData.setTrainingEffectLimit(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityEAActivityData.setAvgHeartRate(query.getInt(i5));
                    i3 = i4;
                    int i6 = columnIndexOrThrow16;
                    entityEAActivityData.setAvgTemperature(query.getFloat(i6));
                    columnIndexOrThrow15 = i5;
                    int i7 = columnIndexOrThrow17;
                    entityEAActivityData.setAvgSpeed(query.getInt(i7));
                    columnIndexOrThrow17 = i7;
                    int i8 = columnIndexOrThrow18;
                    entityEAActivityData.setAvgPace(query.getInt(i8));
                    columnIndexOrThrow18 = i8;
                    int i9 = columnIndexOrThrow19;
                    entityEAActivityData.setAvgStepFreq(query.getInt(i9));
                    columnIndexOrThrow19 = i9;
                    int i10 = columnIndexOrThrow20;
                    entityEAActivityData.setAvgStride(query.getInt(i10));
                    columnIndexOrThrow20 = i10;
                    int i11 = columnIndexOrThrow21;
                    entityEAActivityData.setAvgAltitude(query.getInt(i11));
                    columnIndexOrThrow21 = i11;
                    int i12 = columnIndexOrThrow22;
                    entityEAActivityData.setMaxHeartRate(query.getInt(i12));
                    columnIndexOrThrow22 = i12;
                    int i13 = columnIndexOrThrow23;
                    entityEAActivityData.setMinHeartRate(query.getInt(i13));
                    int i14 = columnIndexOrThrow11;
                    int i15 = columnIndexOrThrow24;
                    entityEAActivityData.setTimeStamp(query.getLong(i15));
                    int i16 = columnIndexOrThrow25;
                    entityEAActivityData.setProcessed(query.getInt(i16) != 0);
                    arrayList2.add(entityEAActivityData);
                    columnIndexOrThrow24 = i15;
                    columnIndexOrThrow25 = i16;
                    arrayList = arrayList2;
                    columnIndexOrThrow11 = i14;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow23 = i13;
                    columnIndexOrThrow = i;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
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

    @Override // com.coveiot.kheastapexdb.activity.KHEAActivityDataDao
    public long insert(EntityEAActivityData entityEAActivityData) {
        this.f7056a.assertNotSuspendingTransaction();
        this.f7056a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityEAActivityData);
            this.f7056a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7056a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.activity.KHEAActivityDataDao
    public void insertAll(List<EntityEAActivityData> list) {
        this.f7056a.assertNotSuspendingTransaction();
        this.f7056a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7056a.setTransactionSuccessful();
        } finally {
            this.f7056a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.activity.KHEAActivityDataDao
    public void updateActivityDataProcessedBefore(String str, long j) {
        this.f7056a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7056a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7056a.setTransactionSuccessful();
        } finally {
            this.f7056a.endTransaction();
            this.c.release(acquire);
        }
    }
}
