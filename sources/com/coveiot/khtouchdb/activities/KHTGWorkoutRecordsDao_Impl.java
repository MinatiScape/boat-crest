package com.coveiot.khtouchdb.activities;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khtouchdb.converter.TGActivityConverter;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHTGWorkoutRecordsDao_Impl implements KHTGWorkoutRecordsDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7171a;
    public final EntityInsertionAdapter<EntityTGWorkoutRecord> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityTGWorkoutRecord> {
        public a(KHTGWorkoutRecordsDao_Impl kHTGWorkoutRecordsDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityTGWorkoutRecord entityTGWorkoutRecord) {
            if (entityTGWorkoutRecord.getDate() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityTGWorkoutRecord.getDate());
            }
            if (entityTGWorkoutRecord.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityTGWorkoutRecord.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityTGWorkoutRecord.getDuration());
            supportSQLiteStatement.bindLong(4, entityTGWorkoutRecord.getType());
            supportSQLiteStatement.bindLong(5, entityTGWorkoutRecord.getStep());
            supportSQLiteStatement.bindLong(6, entityTGWorkoutRecord.getCalories());
            supportSQLiteStatement.bindLong(7, entityTGWorkoutRecord.getDistance());
            supportSQLiteStatement.bindLong(8, entityTGWorkoutRecord.getAvgHr());
            supportSQLiteStatement.bindLong(9, entityTGWorkoutRecord.getMaxHr());
            supportSQLiteStatement.bindLong(10, entityTGWorkoutRecord.getMinHr());
            supportSQLiteStatement.bindLong(11, entityTGWorkoutRecord.getRelax());
            supportSQLiteStatement.bindLong(12, entityTGWorkoutRecord.getWarmUp());
            supportSQLiteStatement.bindLong(13, entityTGWorkoutRecord.getFatBurning());
            supportSQLiteStatement.bindLong(14, entityTGWorkoutRecord.getAerobicExercise());
            supportSQLiteStatement.bindLong(15, entityTGWorkoutRecord.getAnaerobicExercise());
            supportSQLiteStatement.bindLong(16, entityTGWorkoutRecord.getExtremeExercise());
            supportSQLiteStatement.bindLong(17, entityTGWorkoutRecord.getAvgStrideFrequency());
            supportSQLiteStatement.bindLong(18, entityTGWorkoutRecord.getAvgStrideLength());
            supportSQLiteStatement.bindLong(19, entityTGWorkoutRecord.getAvgSpeed());
            supportSQLiteStatement.bindLong(20, entityTGWorkoutRecord.getMaxSpeed());
            supportSQLiteStatement.bindLong(21, entityTGWorkoutRecord.getMinSpeed());
            supportSQLiteStatement.bindLong(22, entityTGWorkoutRecord.getAvgPaceSecs());
            supportSQLiteStatement.bindLong(23, entityTGWorkoutRecord.getMaxPace());
            supportSQLiteStatement.bindLong(24, entityTGWorkoutRecord.getMinPace());
            supportSQLiteStatement.bindLong(25, entityTGWorkoutRecord.getPaddleNum());
            supportSQLiteStatement.bindLong(26, entityTGWorkoutRecord.getPaddleFrq());
            supportSQLiteStatement.bindLong(27, entityTGWorkoutRecord.getBoxingNum());
            supportSQLiteStatement.bindLong(28, entityTGWorkoutRecord.getAvgSkipFrq());
            supportSQLiteStatement.bindLong(29, entityTGWorkoutRecord.getSkipNum());
            supportSQLiteStatement.bindLong(30, entityTGWorkoutRecord.getDumbbellNum());
            String fromList = TGActivityConverter.fromList(entityTGWorkoutRecord.getEventItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(31);
            } else {
                supportSQLiteStatement.bindString(31, fromList);
            }
            String fromList2 = TGActivityConverter.fromList2(entityTGWorkoutRecord.getHeartRateItems());
            if (fromList2 == null) {
                supportSQLiteStatement.bindNull(32);
            } else {
                supportSQLiteStatement.bindString(32, fromList2);
            }
            String fromList3 = TGActivityConverter.fromList3(entityTGWorkoutRecord.getPaceItems());
            if (fromList3 == null) {
                supportSQLiteStatement.bindNull(33);
            } else {
                supportSQLiteStatement.bindString(33, fromList3);
            }
            String fromList4 = TGActivityConverter.fromList4(entityTGWorkoutRecord.getRowingItems());
            if (fromList4 == null) {
                supportSQLiteStatement.bindNull(34);
            } else {
                supportSQLiteStatement.bindString(34, fromList4);
            }
            String fromList5 = TGActivityConverter.fromList5(entityTGWorkoutRecord.getGpsData());
            if (fromList5 == null) {
                supportSQLiteStatement.bindNull(35);
            } else {
                supportSQLiteStatement.bindString(35, fromList5);
            }
            String fromObj = TGActivityConverter.fromObj(entityTGWorkoutRecord.getSwimData());
            if (fromObj == null) {
                supportSQLiteStatement.bindNull(36);
            } else {
                supportSQLiteStatement.bindString(36, fromObj);
            }
            String fromList52 = TGActivityConverter.fromList5(entityTGWorkoutRecord.getFootballFieldGpsData());
            if (fromList52 == null) {
                supportSQLiteStatement.bindNull(37);
            } else {
                supportSQLiteStatement.bindString(37, fromList52);
            }
            String fromObj2 = TGActivityConverter.fromObj2(entityTGWorkoutRecord.getFootballAvgPace());
            if (fromObj2 == null) {
                supportSQLiteStatement.bindNull(38);
            } else {
                supportSQLiteStatement.bindString(38, fromObj2);
            }
            String fromObj3 = TGActivityConverter.fromObj3(entityTGWorkoutRecord.getRealTimeData());
            if (fromObj3 == null) {
                supportSQLiteStatement.bindNull(39);
            } else {
                supportSQLiteStatement.bindString(39, fromObj3);
            }
            String fromObj4 = TGActivityConverter.fromObj4(entityTGWorkoutRecord.getKeepTrack());
            if (fromObj4 == null) {
                supportSQLiteStatement.bindNull(40);
            } else {
                supportSQLiteStatement.bindString(40, fromObj4);
            }
            supportSQLiteStatement.bindLong(41, entityTGWorkoutRecord.getTimeStamp());
            supportSQLiteStatement.bindLong(42, entityTGWorkoutRecord.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_tg_workout_record` (`date`,`macAddress`,`duration`,`type`,`step`,`calories`,`distance`,`avgHr`,`maxHr`,`minHr`,`relax`,`warmUp`,`fatBurning`,`aerobicExercise`,`anaerobicExercise`,`extremeExercise`,`avgStrideFrequency`,`avgStrideLength`,`avgSpeed`,`maxSpeed`,`minSpeed`,`avgPaceSecs`,`maxPace`,`minPace`,`paddleNum`,`paddleFrq`,`boxingNum`,`avgSkipFrq`,`skipNum`,`dumbbellNum`,`eventItems`,`heartRateItems`,`paceItems`,`rowingItems`,`gpsData`,`swimData`,`footballFieldGpsData`,`footballAvgPace`,`realTimeData`,`keepTrack`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHTGWorkoutRecordsDao_Impl kHTGWorkoutRecordsDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_tg_workout_record SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHTGWorkoutRecordsDao_Impl(RoomDatabase roomDatabase) {
        this.f7171a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khtouchdb.activities.KHTGWorkoutRecordsDao
    public List<EntityTGWorkoutRecord> getAllUnProcessedActivityData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        String string;
        int i2;
        String string2;
        int i3;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        String string9;
        String string10;
        String string11;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_tg_workout_record  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7171a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7171a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.Step);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "avgHr");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "maxHr");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "minHr");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "relax");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "warmUp");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "fatBurning");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "aerobicExercise");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "anaerobicExercise");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "extremeExercise");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideFrequency");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "minSpeed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "avgPaceSecs");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "paddleNum");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "paddleFrq");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "boxingNum");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "avgSkipFrq");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "skipNum");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "dumbbellNum");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "eventItems");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "heartRateItems");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "paceItems");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "rowingItems");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "gpsData");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "swimData");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "footballFieldGpsData");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "footballAvgPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "realTimeData");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "keepTrack");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string12 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                    if (query.isNull(columnIndexOrThrow2)) {
                        i = columnIndexOrThrow;
                        i2 = columnIndexOrThrow2;
                        string = null;
                    } else {
                        i = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow2);
                        i2 = columnIndexOrThrow2;
                    }
                    EntityTGWorkoutRecord entityTGWorkoutRecord = new EntityTGWorkoutRecord(string12, string);
                    entityTGWorkoutRecord.setDuration(query.getInt(columnIndexOrThrow3));
                    entityTGWorkoutRecord.setType(query.getInt(columnIndexOrThrow4));
                    entityTGWorkoutRecord.setStep(query.getInt(columnIndexOrThrow5));
                    entityTGWorkoutRecord.setCalories(query.getInt(columnIndexOrThrow6));
                    entityTGWorkoutRecord.setDistance(query.getInt(columnIndexOrThrow7));
                    entityTGWorkoutRecord.setAvgHr(query.getInt(columnIndexOrThrow8));
                    entityTGWorkoutRecord.setMaxHr(query.getInt(columnIndexOrThrow9));
                    entityTGWorkoutRecord.setMinHr(query.getInt(columnIndexOrThrow10));
                    entityTGWorkoutRecord.setRelax(query.getInt(columnIndexOrThrow11));
                    entityTGWorkoutRecord.setWarmUp(query.getInt(columnIndexOrThrow12));
                    entityTGWorkoutRecord.setFatBurning(query.getInt(columnIndexOrThrow13));
                    int i5 = i4;
                    entityTGWorkoutRecord.setAerobicExercise(query.getInt(i5));
                    i4 = i5;
                    int i6 = columnIndexOrThrow15;
                    entityTGWorkoutRecord.setAnaerobicExercise(query.getInt(i6));
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    entityTGWorkoutRecord.setExtremeExercise(query.getInt(i7));
                    columnIndexOrThrow16 = i7;
                    int i8 = columnIndexOrThrow17;
                    entityTGWorkoutRecord.setAvgStrideFrequency(query.getInt(i8));
                    columnIndexOrThrow17 = i8;
                    int i9 = columnIndexOrThrow18;
                    entityTGWorkoutRecord.setAvgStrideLength(query.getInt(i9));
                    columnIndexOrThrow18 = i9;
                    int i10 = columnIndexOrThrow19;
                    entityTGWorkoutRecord.setAvgSpeed(query.getInt(i10));
                    columnIndexOrThrow19 = i10;
                    int i11 = columnIndexOrThrow20;
                    entityTGWorkoutRecord.setMaxSpeed(query.getInt(i11));
                    columnIndexOrThrow20 = i11;
                    int i12 = columnIndexOrThrow21;
                    entityTGWorkoutRecord.setMinSpeed(query.getInt(i12));
                    columnIndexOrThrow21 = i12;
                    int i13 = columnIndexOrThrow22;
                    entityTGWorkoutRecord.setAvgPaceSecs(query.getInt(i13));
                    columnIndexOrThrow22 = i13;
                    int i14 = columnIndexOrThrow23;
                    entityTGWorkoutRecord.setMaxPace(query.getInt(i14));
                    columnIndexOrThrow23 = i14;
                    int i15 = columnIndexOrThrow24;
                    entityTGWorkoutRecord.setMinPace(query.getInt(i15));
                    columnIndexOrThrow24 = i15;
                    int i16 = columnIndexOrThrow25;
                    entityTGWorkoutRecord.setPaddleNum(query.getInt(i16));
                    columnIndexOrThrow25 = i16;
                    int i17 = columnIndexOrThrow26;
                    entityTGWorkoutRecord.setPaddleFrq(query.getInt(i17));
                    columnIndexOrThrow26 = i17;
                    int i18 = columnIndexOrThrow27;
                    entityTGWorkoutRecord.setBoxingNum(query.getInt(i18));
                    columnIndexOrThrow27 = i18;
                    int i19 = columnIndexOrThrow28;
                    entityTGWorkoutRecord.setAvgSkipFrq(query.getInt(i19));
                    columnIndexOrThrow28 = i19;
                    int i20 = columnIndexOrThrow29;
                    entityTGWorkoutRecord.setSkipNum(query.getInt(i20));
                    columnIndexOrThrow29 = i20;
                    int i21 = columnIndexOrThrow30;
                    entityTGWorkoutRecord.setDumbbellNum(query.getInt(i21));
                    int i22 = columnIndexOrThrow31;
                    if (query.isNull(i22)) {
                        i3 = i21;
                        string2 = null;
                    } else {
                        string2 = query.getString(i22);
                        i3 = i21;
                    }
                    entityTGWorkoutRecord.setEventItems(TGActivityConverter.fromStr(string2));
                    int i23 = columnIndexOrThrow32;
                    if (query.isNull(i23)) {
                        columnIndexOrThrow32 = i23;
                        string3 = null;
                    } else {
                        string3 = query.getString(i23);
                        columnIndexOrThrow32 = i23;
                    }
                    entityTGWorkoutRecord.setHeartRateItems(TGActivityConverter.fromStr2(string3));
                    int i24 = columnIndexOrThrow33;
                    if (query.isNull(i24)) {
                        columnIndexOrThrow33 = i24;
                        string4 = null;
                    } else {
                        string4 = query.getString(i24);
                        columnIndexOrThrow33 = i24;
                    }
                    entityTGWorkoutRecord.setPaceItems(TGActivityConverter.fromStr3(string4));
                    int i25 = columnIndexOrThrow34;
                    if (query.isNull(i25)) {
                        columnIndexOrThrow34 = i25;
                        string5 = null;
                    } else {
                        string5 = query.getString(i25);
                        columnIndexOrThrow34 = i25;
                    }
                    entityTGWorkoutRecord.setRowingItems(TGActivityConverter.fromStr4(string5));
                    int i26 = columnIndexOrThrow35;
                    if (query.isNull(i26)) {
                        columnIndexOrThrow35 = i26;
                        string6 = null;
                    } else {
                        string6 = query.getString(i26);
                        columnIndexOrThrow35 = i26;
                    }
                    entityTGWorkoutRecord.setGpsData(TGActivityConverter.fromStr5(string6));
                    int i27 = columnIndexOrThrow36;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow36 = i27;
                        string7 = null;
                    } else {
                        string7 = query.getString(i27);
                        columnIndexOrThrow36 = i27;
                    }
                    entityTGWorkoutRecord.setSwimData(TGActivityConverter.fromStr7(string7));
                    int i28 = columnIndexOrThrow37;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow37 = i28;
                        string8 = null;
                    } else {
                        string8 = query.getString(i28);
                        columnIndexOrThrow37 = i28;
                    }
                    entityTGWorkoutRecord.setFootballFieldGpsData(TGActivityConverter.fromStr5(string8));
                    int i29 = columnIndexOrThrow38;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow38 = i29;
                        string9 = null;
                    } else {
                        string9 = query.getString(i29);
                        columnIndexOrThrow38 = i29;
                    }
                    entityTGWorkoutRecord.setFootballAvgPace(TGActivityConverter.fromStr8(string9));
                    int i30 = columnIndexOrThrow39;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow39 = i30;
                        string10 = null;
                    } else {
                        string10 = query.getString(i30);
                        columnIndexOrThrow39 = i30;
                    }
                    entityTGWorkoutRecord.setRealTimeData(TGActivityConverter.fromStr9(string10));
                    int i31 = columnIndexOrThrow40;
                    if (query.isNull(i31)) {
                        columnIndexOrThrow40 = i31;
                        string11 = null;
                    } else {
                        string11 = query.getString(i31);
                        columnIndexOrThrow40 = i31;
                    }
                    entityTGWorkoutRecord.setKeepTrack(TGActivityConverter.fromStr10(string11));
                    int i32 = columnIndexOrThrow11;
                    int i33 = columnIndexOrThrow41;
                    int i34 = columnIndexOrThrow12;
                    entityTGWorkoutRecord.setTimeStamp(query.getLong(i33));
                    int i35 = columnIndexOrThrow42;
                    entityTGWorkoutRecord.setProcessed(query.getInt(i35) != 0);
                    arrayList.add(entityTGWorkoutRecord);
                    columnIndexOrThrow42 = i35;
                    columnIndexOrThrow11 = i32;
                    columnIndexOrThrow30 = i3;
                    columnIndexOrThrow12 = i34;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow41 = i33;
                    columnIndexOrThrow31 = i22;
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

    @Override // com.coveiot.khtouchdb.activities.KHTGWorkoutRecordsDao
    public long insert(EntityTGWorkoutRecord entityTGWorkoutRecord) {
        this.f7171a.assertNotSuspendingTransaction();
        this.f7171a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityTGWorkoutRecord);
            this.f7171a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7171a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.activities.KHTGWorkoutRecordsDao
    public void insertAll(List<EntityTGWorkoutRecord> list) {
        this.f7171a.assertNotSuspendingTransaction();
        this.f7171a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7171a.setTransactionSuccessful();
        } finally {
            this.f7171a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.activities.KHTGWorkoutRecordsDao
    public void updateActivityDataProcessedBefore(String str, long j) {
        this.f7171a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7171a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7171a.setTransactionSuccessful();
        } finally {
            this.f7171a.endTransaction();
            this.c.release(acquire);
        }
    }
}
