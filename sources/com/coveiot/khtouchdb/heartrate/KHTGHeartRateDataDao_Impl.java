package com.coveiot.khtouchdb.heartrate;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khtouchdb.converter.TGHeartRateItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHTGHeartRateDataDao_Impl implements KHTGHeartRateDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7185a;
    public final EntityInsertionAdapter<EntityTGHeartRateData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityTGHeartRateData> {
        public a(KHTGHeartRateDataDao_Impl kHTGHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityTGHeartRateData entityTGHeartRateData) {
            if (entityTGHeartRateData.getDate() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityTGHeartRateData.getDate());
            }
            if (entityTGHeartRateData.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityTGHeartRateData.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityTGHeartRateData.getMinuteOffset());
            supportSQLiteStatement.bindLong(4, entityTGHeartRateData.getSilentHr());
            supportSQLiteStatement.bindLong(5, entityTGHeartRateData.getItemCount());
            supportSQLiteStatement.bindLong(6, entityTGHeartRateData.getPacketCount());
            supportSQLiteStatement.bindLong(7, entityTGHeartRateData.getBurnFatThreshold());
            supportSQLiteStatement.bindLong(8, entityTGHeartRateData.getBurnFatMinutes());
            supportSQLiteStatement.bindLong(9, entityTGHeartRateData.getAerobicThreshold());
            supportSQLiteStatement.bindLong(10, entityTGHeartRateData.getAerobicMinutes());
            supportSQLiteStatement.bindLong(11, entityTGHeartRateData.getLimitThreshold());
            supportSQLiteStatement.bindLong(12, entityTGHeartRateData.getLimitMinutes());
            supportSQLiteStatement.bindLong(13, entityTGHeartRateData.getWarmUpThreshold());
            supportSQLiteStatement.bindLong(14, entityTGHeartRateData.getWarmUpMinutes());
            supportSQLiteStatement.bindLong(15, entityTGHeartRateData.getAnaerobicThreshold());
            supportSQLiteStatement.bindLong(16, entityTGHeartRateData.getAnaerobicMinutes());
            String fromList = TGHeartRateItemConverter.fromList(entityTGHeartRateData.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(17);
            } else {
                supportSQLiteStatement.bindString(17, fromList);
            }
            supportSQLiteStatement.bindLong(18, entityTGHeartRateData.getTimeStamp());
            supportSQLiteStatement.bindLong(19, entityTGHeartRateData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_tg_heart_rate` (`date`,`macAddress`,`minuteOffset`,`silentHr`,`itemCount`,`packetCount`,`burnFatThreshold`,`burnFatMinutes`,`aerobicThreshold`,`aerobicMinutes`,`limitThreshold`,`limitMinutes`,`warmUpThreshold`,`warmUpMinutes`,`anaerobicThreshold`,`anaerobicMinutes`,`items`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHTGHeartRateDataDao_Impl kHTGHeartRateDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE ENTITY_TG_HEART_RATE SET isProcessed = 1 WHERE macAddress=? AND isProcessed == 0 AND timeStamp<?";
        }
    }

    public KHTGHeartRateDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7185a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khtouchdb.heartrate.KHTGHeartRateDataDao
    public List<EntityTGHeartRateData> getAllUnProcessedStepsData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        String string;
        int i2;
        String string2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from ENTITY_TG_HEART_RATE  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7185a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7185a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "minuteOffset");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "silentHr");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "itemCount");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "packetCount");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "burnFatThreshold");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "burnFatMinutes");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "aerobicThreshold");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "aerobicMinutes");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "limitThreshold");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "limitMinutes");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "warmUpThreshold");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "warmUpMinutes");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "anaerobicThreshold");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "anaerobicMinutes");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string3 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                    if (query.isNull(columnIndexOrThrow2)) {
                        i = columnIndexOrThrow;
                        i2 = columnIndexOrThrow2;
                        string = null;
                    } else {
                        i = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow2);
                        i2 = columnIndexOrThrow2;
                    }
                    EntityTGHeartRateData entityTGHeartRateData = new EntityTGHeartRateData(string3, string);
                    entityTGHeartRateData.setMinuteOffset(query.getInt(columnIndexOrThrow3));
                    entityTGHeartRateData.setSilentHr(query.getInt(columnIndexOrThrow4));
                    entityTGHeartRateData.setItemCount(query.getInt(columnIndexOrThrow5));
                    entityTGHeartRateData.setPacketCount(query.getInt(columnIndexOrThrow6));
                    entityTGHeartRateData.setBurnFatThreshold(query.getInt(columnIndexOrThrow7));
                    entityTGHeartRateData.setBurnFatMinutes(query.getInt(columnIndexOrThrow8));
                    entityTGHeartRateData.setAerobicThreshold(query.getInt(columnIndexOrThrow9));
                    entityTGHeartRateData.setAerobicMinutes(query.getInt(columnIndexOrThrow10));
                    entityTGHeartRateData.setLimitThreshold(query.getInt(columnIndexOrThrow11));
                    entityTGHeartRateData.setLimitMinutes(query.getInt(columnIndexOrThrow12));
                    entityTGHeartRateData.setWarmUpThreshold(query.getInt(columnIndexOrThrow13));
                    int i5 = i4;
                    entityTGHeartRateData.setWarmUpMinutes(query.getInt(i5));
                    i4 = i5;
                    int i6 = columnIndexOrThrow15;
                    entityTGHeartRateData.setAnaerobicThreshold(query.getInt(i6));
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    entityTGHeartRateData.setAnaerobicMinutes(query.getInt(i7));
                    int i8 = columnIndexOrThrow17;
                    if (query.isNull(i8)) {
                        i3 = i7;
                        string2 = null;
                    } else {
                        string2 = query.getString(i8);
                        i3 = i7;
                    }
                    entityTGHeartRateData.setItems(TGHeartRateItemConverter.fromStr(string2));
                    int i9 = columnIndexOrThrow11;
                    int i10 = columnIndexOrThrow18;
                    int i11 = columnIndexOrThrow12;
                    entityTGHeartRateData.setTimeStamp(query.getLong(i10));
                    int i12 = columnIndexOrThrow19;
                    entityTGHeartRateData.setProcessed(query.getInt(i12) != 0);
                    arrayList.add(entityTGHeartRateData);
                    columnIndexOrThrow19 = i12;
                    columnIndexOrThrow11 = i9;
                    columnIndexOrThrow16 = i3;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow18 = i10;
                    columnIndexOrThrow17 = i8;
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

    @Override // com.coveiot.khtouchdb.heartrate.KHTGHeartRateDataDao
    public long insert(EntityTGHeartRateData entityTGHeartRateData) {
        this.f7185a.assertNotSuspendingTransaction();
        this.f7185a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityTGHeartRateData);
            this.f7185a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7185a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.heartrate.KHTGHeartRateDataDao
    public void insertAll(List<EntityTGHeartRateData> list) {
        this.f7185a.assertNotSuspendingTransaction();
        this.f7185a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7185a.setTransactionSuccessful();
        } finally {
            this.f7185a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.heartrate.KHTGHeartRateDataDao
    public void updateStepsDataProcessedBefore(String str, long j) {
        this.f7185a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7185a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7185a.setTransactionSuccessful();
        } finally {
            this.f7185a.endTransaction();
            this.c.release(acquire);
        }
    }
}
