package com.coveiot.khtouchdb.sleep;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khtouchdb.converter.TGSleepItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHTGSleepDataDao_Impl implements KHTGSleepDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7189a;
    public final EntityInsertionAdapter<EntityTGSleepData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityTGSleepData> {
        public a(KHTGSleepDataDao_Impl kHTGSleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityTGSleepData entityTGSleepData) {
            if (entityTGSleepData.getDate() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityTGSleepData.getDate());
            }
            supportSQLiteStatement.bindLong(2, entityTGSleepData.getEndHour());
            supportSQLiteStatement.bindLong(3, entityTGSleepData.getEndMinute());
            if (entityTGSleepData.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityTGSleepData.getMacAddress());
            }
            supportSQLiteStatement.bindLong(5, entityTGSleepData.getTotalMinute());
            supportSQLiteStatement.bindLong(6, entityTGSleepData.getSleepMinute());
            supportSQLiteStatement.bindLong(7, entityTGSleepData.getItemCount());
            supportSQLiteStatement.bindLong(8, entityTGSleepData.getPacketCount());
            supportSQLiteStatement.bindLong(9, entityTGSleepData.getLightCount());
            supportSQLiteStatement.bindLong(10, entityTGSleepData.getDeepCount());
            supportSQLiteStatement.bindLong(11, entityTGSleepData.getWakeCount());
            supportSQLiteStatement.bindLong(12, entityTGSleepData.getEyeMoveCount());
            supportSQLiteStatement.bindLong(13, entityTGSleepData.getLightMinute());
            supportSQLiteStatement.bindLong(14, entityTGSleepData.getDeepMinute());
            supportSQLiteStatement.bindLong(15, entityTGSleepData.getEyeMoveMinute());
            supportSQLiteStatement.bindLong(16, entityTGSleepData.getSleepScore());
            String fromList = TGSleepItemConverter.fromList(entityTGSleepData.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(17);
            } else {
                supportSQLiteStatement.bindString(17, fromList);
            }
            supportSQLiteStatement.bindLong(18, entityTGSleepData.getTimeStamp());
            supportSQLiteStatement.bindLong(19, entityTGSleepData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_tg_sleep` (`date`,`endHour`,`endMinute`,`macAddress`,`totalMinute`,`sleepMinute`,`itemCount`,`packetCount`,`lightCount`,`deepCount`,`wakeCount`,`eyeMoveCount`,`lightMinute`,`deepMinute`,`eyeMoveMinute`,`sleepScore`,`items`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHTGSleepDataDao_Impl kHTGSleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE ENTITY_TG_SLEEP SET isProcessed = 1 WHERE macAddress=? and timeStamp<? AND isProcessed == 0";
        }
    }

    public KHTGSleepDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7189a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khtouchdb.sleep.KHTGSleepDataDao
    public List<EntityTGSleepData> getAllUnProcessedSleepData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        int i2;
        String string2;
        int i3;
        String string3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from ENTITY_TG_SLEEP  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7189a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7189a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "endHour");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "endMinute");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "totalMinute");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "sleepMinute");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "itemCount");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "packetCount");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "lightCount");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "deepCount");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "wakeCount");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "eyeMoveCount");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "lightMinute");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "deepMinute");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "eyeMoveMinute");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "sleepScore");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow;
                        string = null;
                    } else {
                        string = query.getString(columnIndexOrThrow);
                        i = columnIndexOrThrow;
                    }
                    int i5 = query.getInt(columnIndexOrThrow2);
                    int i6 = columnIndexOrThrow2;
                    int i7 = query.getInt(columnIndexOrThrow3);
                    if (query.isNull(columnIndexOrThrow4)) {
                        i2 = columnIndexOrThrow3;
                        i3 = columnIndexOrThrow4;
                        string2 = null;
                    } else {
                        i2 = columnIndexOrThrow3;
                        string2 = query.getString(columnIndexOrThrow4);
                        i3 = columnIndexOrThrow4;
                    }
                    EntityTGSleepData entityTGSleepData = new EntityTGSleepData(string, i5, i7, string2);
                    entityTGSleepData.setTotalMinute(query.getInt(columnIndexOrThrow5));
                    entityTGSleepData.setSleepMinute(query.getInt(columnIndexOrThrow6));
                    entityTGSleepData.setItemCount(query.getInt(columnIndexOrThrow7));
                    entityTGSleepData.setPacketCount(query.getInt(columnIndexOrThrow8));
                    entityTGSleepData.setLightCount(query.getInt(columnIndexOrThrow9));
                    entityTGSleepData.setDeepCount(query.getInt(columnIndexOrThrow10));
                    entityTGSleepData.setWakeCount(query.getInt(columnIndexOrThrow11));
                    entityTGSleepData.setEyeMoveCount(query.getInt(columnIndexOrThrow12));
                    entityTGSleepData.setLightMinute(query.getInt(columnIndexOrThrow13));
                    int i8 = i4;
                    entityTGSleepData.setDeepMinute(query.getInt(i8));
                    int i9 = columnIndexOrThrow15;
                    entityTGSleepData.setEyeMoveMinute(query.getInt(i9));
                    int i10 = columnIndexOrThrow16;
                    entityTGSleepData.setSleepScore(query.getInt(i10));
                    int i11 = columnIndexOrThrow17;
                    if (query.isNull(i11)) {
                        i4 = i8;
                        string3 = null;
                    } else {
                        string3 = query.getString(i11);
                        i4 = i8;
                    }
                    entityTGSleepData.setItems(TGSleepItemConverter.fromStr(string3));
                    int i12 = columnIndexOrThrow11;
                    int i13 = columnIndexOrThrow12;
                    int i14 = columnIndexOrThrow18;
                    entityTGSleepData.setTimeStamp(query.getLong(i14));
                    int i15 = columnIndexOrThrow19;
                    entityTGSleepData.setProcessed(query.getInt(i15) != 0);
                    arrayList.add(entityTGSleepData);
                    columnIndexOrThrow18 = i14;
                    columnIndexOrThrow19 = i15;
                    columnIndexOrThrow17 = i11;
                    columnIndexOrThrow11 = i12;
                    columnIndexOrThrow12 = i13;
                    columnIndexOrThrow = i;
                    columnIndexOrThrow4 = i3;
                    columnIndexOrThrow3 = i2;
                    columnIndexOrThrow15 = i9;
                    columnIndexOrThrow16 = i10;
                    columnIndexOrThrow2 = i6;
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

    @Override // com.coveiot.khtouchdb.sleep.KHTGSleepDataDao
    public long insert(EntityTGSleepData entityTGSleepData) {
        this.f7189a.assertNotSuspendingTransaction();
        this.f7189a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityTGSleepData);
            this.f7189a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7189a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.sleep.KHTGSleepDataDao
    public void insertAll(List<EntityTGSleepData> list) {
        this.f7189a.assertNotSuspendingTransaction();
        this.f7189a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7189a.setTransactionSuccessful();
        } finally {
            this.f7189a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.sleep.KHTGSleepDataDao
    public void updateSleepDataProcessedBefore(String str, long j) {
        this.f7189a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7189a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7189a.setTransactionSuccessful();
        } finally {
            this.f7189a.endTransaction();
            this.c.release(acquire);
        }
    }
}
