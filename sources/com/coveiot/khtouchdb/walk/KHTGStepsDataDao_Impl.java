package com.coveiot.khtouchdb.walk;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khtouchdb.converter.TGStepsItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHTGStepsDataDao_Impl implements KHTGStepsDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7201a;
    public final EntityInsertionAdapter<EntityTGStepData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityTGStepData> {
        public a(KHTGStepsDataDao_Impl kHTGStepsDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityTGStepData entityTGStepData) {
            if (entityTGStepData.getDate() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityTGStepData.getDate());
            }
            if (entityTGStepData.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityTGStepData.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityTGStepData.getMinuteOffset());
            supportSQLiteStatement.bindLong(4, entityTGStepData.getPerMinute());
            supportSQLiteStatement.bindLong(5, entityTGStepData.getItemCount());
            supportSQLiteStatement.bindLong(6, entityTGStepData.getPacketCount());
            supportSQLiteStatement.bindLong(7, entityTGStepData.getTotalSteps());
            supportSQLiteStatement.bindLong(8, entityTGStepData.getTotalCal());
            supportSQLiteStatement.bindLong(9, entityTGStepData.getTotalDistance());
            supportSQLiteStatement.bindLong(10, entityTGStepData.getTotalActiveTime());
            supportSQLiteStatement.bindLong(11, entityTGStepData.getStandCount());
            String fromList = TGStepsItemConverter.fromList(entityTGStepData.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, fromList);
            }
            supportSQLiteStatement.bindLong(13, entityTGStepData.getTimeStamp());
            supportSQLiteStatement.bindLong(14, entityTGStepData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_tg_steps` (`date`,`macAddress`,`minuteOffset`,`perMinute`,`itemCount`,`packetCount`,`totalSteps`,`totalCal`,`totalDistance`,`totalActiveTime`,`standCount`,`items`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHTGStepsDataDao_Impl kHTGStepsDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE ENTITY_TG_STEPS SET isProcessed = 1 WHERE macAddress=? and timeStamp<? AND isProcessed == 0";
        }
    }

    public KHTGStepsDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7201a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khtouchdb.walk.KHTGStepsDataDao
    public List<EntityTGStepData> getAllUnProcessedStepsData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        String string;
        int i2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from ENTITY_TG_STEPS  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7201a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7201a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "minuteOffset");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "perMinute");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "itemCount");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "packetCount");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "totalSteps");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "totalCal");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "totalDistance");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "totalActiveTime");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "standCount");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                    if (query.isNull(columnIndexOrThrow2)) {
                        i = columnIndexOrThrow;
                        i2 = columnIndexOrThrow2;
                        string = null;
                    } else {
                        i = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow2);
                        i2 = columnIndexOrThrow2;
                    }
                    EntityTGStepData entityTGStepData = new EntityTGStepData(string2, string);
                    entityTGStepData.setMinuteOffset(query.getInt(columnIndexOrThrow3));
                    entityTGStepData.setPerMinute(query.getInt(columnIndexOrThrow4));
                    entityTGStepData.setItemCount(query.getInt(columnIndexOrThrow5));
                    entityTGStepData.setPacketCount(query.getInt(columnIndexOrThrow6));
                    entityTGStepData.setTotalSteps(query.getInt(columnIndexOrThrow7));
                    entityTGStepData.setTotalCal(query.getInt(columnIndexOrThrow8));
                    entityTGStepData.setTotalDistance(query.getInt(columnIndexOrThrow9));
                    entityTGStepData.setTotalActiveTime(query.getInt(columnIndexOrThrow10));
                    entityTGStepData.setStandCount(query.getInt(columnIndexOrThrow11));
                    entityTGStepData.setItems(TGStepsItemConverter.fromStr(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12)));
                    entityTGStepData.setTimeStamp(query.getLong(columnIndexOrThrow13));
                    int i3 = columnIndexOrThrow14;
                    entityTGStepData.setProcessed(query.getInt(i3) != 0);
                    arrayList.add(entityTGStepData);
                    columnIndexOrThrow14 = i3;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow = i;
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

    @Override // com.coveiot.khtouchdb.walk.KHTGStepsDataDao
    public long insert(EntityTGStepData entityTGStepData) {
        this.f7201a.assertNotSuspendingTransaction();
        this.f7201a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityTGStepData);
            this.f7201a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7201a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.walk.KHTGStepsDataDao
    public void insertAll(List<EntityTGStepData> list) {
        this.f7201a.assertNotSuspendingTransaction();
        this.f7201a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7201a.setTransactionSuccessful();
        } finally {
            this.f7201a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.walk.KHTGStepsDataDao
    public void updateStepsDataProcessedBefore(String str, long j) {
        this.f7201a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7201a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7201a.setTransactionSuccessful();
        } finally {
            this.f7201a.endTransaction();
            this.c.release(acquire);
        }
    }
}
