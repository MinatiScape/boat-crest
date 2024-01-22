package com.coveiot.khidodb.activities;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khidodb.converts.ActivityDataConvertor;
import com.coveiot.khidodb.converts.HealthSportItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHIDOHealthActivityV3Doa_Impl implements KHIDOHealthActivityV3Doa {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7088a;
    public final EntityInsertionAdapter<EntityHealthActivityV3> b;
    public final EntityInsertionAdapter<EntityHealthSwimV3> c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHealthActivityV3> {
        public a(KHIDOHealthActivityV3Doa_Impl kHIDOHealthActivityV3Doa_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthActivityV3 entityHealthActivityV3) {
            supportSQLiteStatement.bindLong(1, entityHealthActivityV3.getDay());
            supportSQLiteStatement.bindLong(2, entityHealthActivityV3.getMonth());
            supportSQLiteStatement.bindLong(3, entityHealthActivityV3.getYear());
            supportSQLiteStatement.bindLong(4, entityHealthActivityV3.getHour());
            supportSQLiteStatement.bindLong(5, entityHealthActivityV3.getMinute());
            supportSQLiteStatement.bindLong(6, entityHealthActivityV3.getSecond());
            if (entityHealthActivityV3.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, entityHealthActivityV3.getMacAddress());
            }
            supportSQLiteStatement.bindLong(8, entityHealthActivityV3.getHr_data_interval_minute());
            supportSQLiteStatement.bindLong(9, entityHealthActivityV3.getType());
            supportSQLiteStatement.bindLong(10, entityHealthActivityV3.getStep());
            supportSQLiteStatement.bindLong(11, entityHealthActivityV3.getDurations());
            supportSQLiteStatement.bindLong(12, entityHealthActivityV3.getCalories());
            supportSQLiteStatement.bindLong(13, entityHealthActivityV3.getDistance());
            supportSQLiteStatement.bindLong(14, entityHealthActivityV3.getAvg_hr_value());
            supportSQLiteStatement.bindLong(15, entityHealthActivityV3.getMax_hr_value());
            supportSQLiteStatement.bindLong(16, entityHealthActivityV3.getMin_hr_value());
            supportSQLiteStatement.bindLong(17, entityHealthActivityV3.getWarmUpMins());
            supportSQLiteStatement.bindLong(18, entityHealthActivityV3.getWarm_up_time());
            supportSQLiteStatement.bindLong(19, entityHealthActivityV3.getBurn_fat_mins());
            supportSQLiteStatement.bindLong(20, entityHealthActivityV3.getFat_burning_time());
            supportSQLiteStatement.bindLong(21, entityHealthActivityV3.getAerobic_mins());
            supportSQLiteStatement.bindLong(22, entityHealthActivityV3.getAerobic_exercise_time());
            supportSQLiteStatement.bindLong(23, entityHealthActivityV3.getAnaerobicMins());
            supportSQLiteStatement.bindLong(24, entityHealthActivityV3.getAnaerobic_exercise_time());
            supportSQLiteStatement.bindLong(25, entityHealthActivityV3.getLimit_mins());
            supportSQLiteStatement.bindLong(26, entityHealthActivityV3.getExtreme_exercise_time());
            String fromList3 = ActivityDataConvertor.fromList3(entityHealthActivityV3.getHr_data_vlaue());
            if (fromList3 == null) {
                supportSQLiteStatement.bindNull(27);
            } else {
                supportSQLiteStatement.bindString(27, fromList3);
            }
            String fromList = ActivityDataConvertor.fromList(entityHealthActivityV3.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(28);
            } else {
                supportSQLiteStatement.bindString(28, fromList);
            }
            supportSQLiteStatement.bindLong(29, entityHealthActivityV3.getFast_km_speed());
            String fromList2 = ActivityDataConvertor.fromList2(entityHealthActivityV3.getItems_km_speed());
            if (fromList2 == null) {
                supportSQLiteStatement.bindNull(30);
            } else {
                supportSQLiteStatement.bindString(30, fromList2);
            }
            String fromList22 = HealthSportItemConverter.fromList2(entityHealthActivityV3.getFrequency_items());
            if (fromList22 == null) {
                supportSQLiteStatement.bindNull(31);
            } else {
                supportSQLiteStatement.bindString(31, fromList22);
            }
            String fromList23 = HealthSportItemConverter.fromList2(entityHealthActivityV3.getItems_mi_speed());
            if (fromList23 == null) {
                supportSQLiteStatement.bindNull(32);
            } else {
                supportSQLiteStatement.bindString(32, fromList23);
            }
            supportSQLiteStatement.bindLong(33, entityHealthActivityV3.getKm_speed());
            supportSQLiteStatement.bindLong(34, entityHealthActivityV3.getAvg_speed());
            supportSQLiteStatement.bindLong(35, entityHealthActivityV3.getMax_speed());
            supportSQLiteStatement.bindLong(36, entityHealthActivityV3.getAvg_step_frequency());
            supportSQLiteStatement.bindLong(37, entityHealthActivityV3.getMax_step_frequency());
            supportSQLiteStatement.bindLong(38, entityHealthActivityV3.getAvg_step_stride());
            supportSQLiteStatement.bindLong(39, entityHealthActivityV3.getMax_step_stride());
            supportSQLiteStatement.bindLong(40, entityHealthActivityV3.getSport_start_type());
            supportSQLiteStatement.bindLong(41, entityHealthActivityV3.getConnect_app());
            supportSQLiteStatement.bindLong(42, entityHealthActivityV3.getAvg_pace_speed());
            supportSQLiteStatement.bindLong(43, entityHealthActivityV3.getFast_pace_speed());
            supportSQLiteStatement.bindLong(44, entityHealthActivityV3.getTraining_effect());
            supportSQLiteStatement.bindLong(45, entityHealthActivityV3.getVO2max());
            supportSQLiteStatement.bindLong(46, entityHealthActivityV3.getRecovery_time_year());
            supportSQLiteStatement.bindLong(47, entityHealthActivityV3.getRecovery_time_mon());
            supportSQLiteStatement.bindLong(48, entityHealthActivityV3.getRecovery_time_day());
            supportSQLiteStatement.bindLong(49, entityHealthActivityV3.getRecovery_time_hour());
            supportSQLiteStatement.bindLong(50, entityHealthActivityV3.getRecovery_time_min());
            supportSQLiteStatement.bindLong(51, entityHealthActivityV3.getRecovery_time_s());
            String fromList24 = HealthSportItemConverter.fromList2(entityHealthActivityV3.getPace_speed_items());
            if (fromList24 == null) {
                supportSQLiteStatement.bindNull(52);
            } else {
                supportSQLiteStatement.bindString(52, fromList24);
            }
            String fromList25 = HealthSportItemConverter.fromList2(entityHealthActivityV3.getPaddle_number_items());
            if (fromList25 == null) {
                supportSQLiteStatement.bindNull(53);
            } else {
                supportSQLiteStatement.bindString(53, fromList25);
            }
            String fromList26 = HealthSportItemConverter.fromList2(entityHealthActivityV3.getPaddle_frequency_items());
            if (fromList26 == null) {
                supportSQLiteStatement.bindNull(54);
            } else {
                supportSQLiteStatement.bindString(54, fromList26);
            }
            String fromList27 = HealthSportItemConverter.fromList2(entityHealthActivityV3.getTread_frequency_items());
            if (fromList27 == null) {
                supportSQLiteStatement.bindNull(55);
            } else {
                supportSQLiteStatement.bindString(55, fromList27);
            }
            supportSQLiteStatement.bindLong(56, entityHealthActivityV3.getPaddle_number_count());
            supportSQLiteStatement.bindLong(57, entityHealthActivityV3.getPaddle_frequency_count());
            supportSQLiteStatement.bindLong(58, entityHealthActivityV3.getTread_frequency_count());
            supportSQLiteStatement.bindLong(59, entityHealthActivityV3.getEnd_month());
            supportSQLiteStatement.bindLong(60, entityHealthActivityV3.getEnd_day());
            supportSQLiteStatement.bindLong(61, entityHealthActivityV3.getEnd_hour());
            supportSQLiteStatement.bindLong(62, entityHealthActivityV3.getEnd_minute());
            supportSQLiteStatement.bindLong(63, entityHealthActivityV3.getTimestamp());
            supportSQLiteStatement.bindLong(64, entityHealthActivityV3.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_activity_v3` (`day`,`month`,`year`,`hour`,`minute`,`second`,`macAddress`,`hr_data_interval_minute`,`type`,`step`,`durations`,`calories`,`distance`,`avg_hr_value`,`max_hr_value`,`min_hr_value`,`warmUpMins`,`warm_up_time`,`burn_fat_mins`,`fat_burning_time`,`aerobic_mins`,`aerobic_exercise_time`,`anaerobicMins`,`anaerobic_exercise_time`,`limit_mins`,`extreme_exercise_time`,`hr_data_vlaue`,`items`,`fast_km_speed`,`items_km_speed`,`frequency_items`,`items_mi_speed`,`km_speed`,`avg_speed`,`max_speed`,`avg_step_frequency`,`max_step_frequency`,`avg_step_stride`,`max_step_stride`,`sport_start_type`,`connect_app`,`avg_pace_speed`,`fast_pace_speed`,`training_effect`,`vO2max`,`recovery_time_year`,`recovery_time_mon`,`recovery_time_day`,`recovery_time_hour`,`recovery_time_min`,`recovery_time_s`,`pace_speed_items`,`paddle_number_items`,`paddle_frequency_items`,`tread_frequency_items`,`paddle_number_count`,`paddle_frequency_count`,`tread_frequency_count`,`end_month`,`end_day`,`end_hour`,`end_minute`,`timestamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<EntityHealthSwimV3> {
        public b(KHIDOHealthActivityV3Doa_Impl kHIDOHealthActivityV3Doa_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHealthSwimV3 entityHealthSwimV3) {
            supportSQLiteStatement.bindLong(1, entityHealthSwimV3.getDay());
            supportSQLiteStatement.bindLong(2, entityHealthSwimV3.getMonth());
            supportSQLiteStatement.bindLong(3, entityHealthSwimV3.getYear());
            supportSQLiteStatement.bindLong(4, entityHealthSwimV3.getHour());
            supportSQLiteStatement.bindLong(5, entityHealthSwimV3.getMinute());
            supportSQLiteStatement.bindLong(6, entityHealthSwimV3.getSecond());
            if (entityHealthSwimV3.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, entityHealthSwimV3.getMacAddress());
            }
            supportSQLiteStatement.bindLong(8, entityHealthSwimV3.getType());
            supportSQLiteStatement.bindLong(9, entityHealthSwimV3.getDurations());
            supportSQLiteStatement.bindLong(10, entityHealthSwimV3.getCalories());
            supportSQLiteStatement.bindLong(11, entityHealthSwimV3.getDistance());
            supportSQLiteStatement.bindLong(12, entityHealthSwimV3.getTrips());
            supportSQLiteStatement.bindLong(13, entityHealthSwimV3.getAverageSWOLF());
            supportSQLiteStatement.bindLong(14, entityHealthSwimV3.getTotalStrokesNumber());
            supportSQLiteStatement.bindLong(15, entityHealthSwimV3.getSwimmingPosture());
            supportSQLiteStatement.bindLong(16, entityHealthSwimV3.getPoolDistance());
            supportSQLiteStatement.bindLong(17, entityHealthSwimV3.getConfirmDistance());
            String fromList4 = ActivityDataConvertor.fromList4(entityHealthSwimV3.getItems());
            if (fromList4 == null) {
                supportSQLiteStatement.bindNull(18);
            } else {
                supportSQLiteStatement.bindString(18, fromList4);
            }
            supportSQLiteStatement.bindLong(19, entityHealthSwimV3.getAvg_speed());
            supportSQLiteStatement.bindLong(20, entityHealthSwimV3.getAvg_frequency());
            supportSQLiteStatement.bindLong(21, entityHealthSwimV3.getTimestamp());
            supportSQLiteStatement.bindLong(22, entityHealthSwimV3.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_swim_v3` (`day`,`month`,`year`,`hour`,`minute`,`second`,`macAddress`,`type`,`durations`,`calories`,`distance`,`trips`,`averageSWOLF`,`totalStrokesNumber`,`swimmingPosture`,`poolDistance`,`confirmDistance`,`items`,`avg_speed`,`avg_frequency`,`timestamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends SharedSQLiteStatement {
        public c(KHIDOHealthActivityV3Doa_Impl kHIDOHealthActivityV3Doa_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_activity_v3 SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    /* loaded from: classes8.dex */
    public class d extends SharedSQLiteStatement {
        public d(KHIDOHealthActivityV3Doa_Impl kHIDOHealthActivityV3Doa_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_swim_v3 SET isProcessed = 1 WHERE macAddress=? and timestamp<? AND isProcessed == 0";
        }
    }

    public KHIDOHealthActivityV3Doa_Impl(RoomDatabase roomDatabase) {
        this.f7088a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
        this.e = new d(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public List<EntityHealthActivityV3> getAllUnProcessedActivityData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        String string;
        int i;
        String string2;
        String string3;
        int i2;
        String string4;
        String string5;
        String string6;
        int i3;
        String string7;
        String string8;
        String string9;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_activity_v3  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7088a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7088a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_DAY);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "month");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "year");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_HOUR);
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "minute");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "second");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_data_interval_minute");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "type");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.Step);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "durations");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr_value");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "max_hr_value");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "min_hr_value");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "warmUpMins");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "warm_up_time");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "burn_fat_mins");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "fat_burning_time");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "aerobic_mins");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "aerobic_exercise_time");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "anaerobicMins");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "anaerobic_exercise_time");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "limit_mins");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "extreme_exercise_time");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "hr_data_vlaue");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "fast_km_speed");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "items_km_speed");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "frequency_items");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "items_mi_speed");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "km_speed");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "avg_speed");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "max_speed");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "avg_step_frequency");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "max_step_frequency");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "avg_step_stride");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "max_step_stride");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "sport_start_type");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "connect_app");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "avg_pace_speed");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "fast_pace_speed");
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "training_effect");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "vO2max");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "recovery_time_year");
            int columnIndexOrThrow47 = CursorUtil.getColumnIndexOrThrow(query, "recovery_time_mon");
            int columnIndexOrThrow48 = CursorUtil.getColumnIndexOrThrow(query, "recovery_time_day");
            int columnIndexOrThrow49 = CursorUtil.getColumnIndexOrThrow(query, "recovery_time_hour");
            int columnIndexOrThrow50 = CursorUtil.getColumnIndexOrThrow(query, "recovery_time_min");
            int columnIndexOrThrow51 = CursorUtil.getColumnIndexOrThrow(query, "recovery_time_s");
            int columnIndexOrThrow52 = CursorUtil.getColumnIndexOrThrow(query, "pace_speed_items");
            int columnIndexOrThrow53 = CursorUtil.getColumnIndexOrThrow(query, "paddle_number_items");
            int columnIndexOrThrow54 = CursorUtil.getColumnIndexOrThrow(query, "paddle_frequency_items");
            int columnIndexOrThrow55 = CursorUtil.getColumnIndexOrThrow(query, "tread_frequency_items");
            int columnIndexOrThrow56 = CursorUtil.getColumnIndexOrThrow(query, "paddle_number_count");
            int columnIndexOrThrow57 = CursorUtil.getColumnIndexOrThrow(query, "paddle_frequency_count");
            int columnIndexOrThrow58 = CursorUtil.getColumnIndexOrThrow(query, "tread_frequency_count");
            int columnIndexOrThrow59 = CursorUtil.getColumnIndexOrThrow(query, "end_month");
            int columnIndexOrThrow60 = CursorUtil.getColumnIndexOrThrow(query, "end_day");
            int columnIndexOrThrow61 = CursorUtil.getColumnIndexOrThrow(query, "end_hour");
            int columnIndexOrThrow62 = CursorUtil.getColumnIndexOrThrow(query, "end_minute");
            int columnIndexOrThrow63 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow64 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int i4 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHealthActivityV3 entityHealthActivityV3 = new EntityHealthActivityV3(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                int i5 = columnIndexOrThrow;
                entityHealthActivityV3.setHr_data_interval_minute(query.getInt(columnIndexOrThrow8));
                entityHealthActivityV3.setType(query.getInt(columnIndexOrThrow9));
                entityHealthActivityV3.setStep(query.getInt(columnIndexOrThrow10));
                entityHealthActivityV3.setDurations(query.getInt(columnIndexOrThrow11));
                entityHealthActivityV3.setCalories(query.getInt(columnIndexOrThrow12));
                entityHealthActivityV3.setDistance(query.getInt(columnIndexOrThrow13));
                int i6 = i4;
                int i7 = columnIndexOrThrow11;
                entityHealthActivityV3.setAvg_hr_value(query.getInt(i6));
                int i8 = columnIndexOrThrow15;
                entityHealthActivityV3.setMax_hr_value(query.getInt(i8));
                int i9 = columnIndexOrThrow16;
                entityHealthActivityV3.setMin_hr_value(query.getInt(i9));
                int i10 = columnIndexOrThrow17;
                entityHealthActivityV3.setWarmUpMins(query.getInt(i10));
                int i11 = columnIndexOrThrow18;
                entityHealthActivityV3.setWarm_up_time(query.getInt(i11));
                int i12 = columnIndexOrThrow19;
                entityHealthActivityV3.setBurn_fat_mins(query.getInt(i12));
                int i13 = columnIndexOrThrow20;
                entityHealthActivityV3.setFat_burning_time(query.getInt(i13));
                int i14 = columnIndexOrThrow21;
                entityHealthActivityV3.setAerobic_mins(query.getInt(i14));
                int i15 = columnIndexOrThrow22;
                entityHealthActivityV3.setAerobic_exercise_time(query.getInt(i15));
                int i16 = columnIndexOrThrow23;
                entityHealthActivityV3.setAnaerobicMins(query.getInt(i16));
                int i17 = columnIndexOrThrow24;
                entityHealthActivityV3.setAnaerobic_exercise_time(query.getInt(i17));
                int i18 = columnIndexOrThrow25;
                entityHealthActivityV3.setLimit_mins(query.getInt(i18));
                int i19 = columnIndexOrThrow26;
                entityHealthActivityV3.setExtreme_exercise_time(query.getInt(i19));
                int i20 = columnIndexOrThrow27;
                if (query.isNull(i20)) {
                    i = i19;
                    string = null;
                } else {
                    string = query.getString(i20);
                    i = i19;
                }
                entityHealthActivityV3.setHr_data_vlaue(ActivityDataConvertor.fromStr3(string));
                int i21 = columnIndexOrThrow28;
                if (query.isNull(i21)) {
                    columnIndexOrThrow28 = i21;
                    string2 = null;
                } else {
                    string2 = query.getString(i21);
                    columnIndexOrThrow28 = i21;
                }
                entityHealthActivityV3.setItems(ActivityDataConvertor.fromStr(string2));
                columnIndexOrThrow27 = i20;
                int i22 = columnIndexOrThrow29;
                entityHealthActivityV3.setFast_km_speed(query.getInt(i22));
                int i23 = columnIndexOrThrow30;
                if (query.isNull(i23)) {
                    i2 = i22;
                    string3 = null;
                } else {
                    string3 = query.getString(i23);
                    i2 = i22;
                }
                entityHealthActivityV3.setItems_km_speed(ActivityDataConvertor.fromStr2(string3));
                int i24 = columnIndexOrThrow31;
                if (query.isNull(i24)) {
                    columnIndexOrThrow31 = i24;
                    string4 = null;
                } else {
                    string4 = query.getString(i24);
                    columnIndexOrThrow31 = i24;
                }
                entityHealthActivityV3.setFrequency_items(HealthSportItemConverter.fromStr2(string4));
                int i25 = columnIndexOrThrow32;
                if (query.isNull(i25)) {
                    columnIndexOrThrow32 = i25;
                    string5 = null;
                } else {
                    string5 = query.getString(i25);
                    columnIndexOrThrow32 = i25;
                }
                entityHealthActivityV3.setItems_mi_speed(HealthSportItemConverter.fromStr2(string5));
                int i26 = columnIndexOrThrow33;
                entityHealthActivityV3.setKm_speed(query.getInt(i26));
                columnIndexOrThrow33 = i26;
                int i27 = columnIndexOrThrow34;
                entityHealthActivityV3.setAvg_speed(query.getInt(i27));
                columnIndexOrThrow34 = i27;
                int i28 = columnIndexOrThrow35;
                entityHealthActivityV3.setMax_speed(query.getInt(i28));
                columnIndexOrThrow35 = i28;
                int i29 = columnIndexOrThrow36;
                entityHealthActivityV3.setAvg_step_frequency(query.getInt(i29));
                columnIndexOrThrow36 = i29;
                int i30 = columnIndexOrThrow37;
                entityHealthActivityV3.setMax_step_frequency(query.getInt(i30));
                columnIndexOrThrow37 = i30;
                int i31 = columnIndexOrThrow38;
                entityHealthActivityV3.setAvg_step_stride(query.getInt(i31));
                columnIndexOrThrow38 = i31;
                int i32 = columnIndexOrThrow39;
                entityHealthActivityV3.setMax_step_stride(query.getInt(i32));
                columnIndexOrThrow39 = i32;
                int i33 = columnIndexOrThrow40;
                entityHealthActivityV3.setSport_start_type(query.getInt(i33));
                columnIndexOrThrow40 = i33;
                int i34 = columnIndexOrThrow41;
                entityHealthActivityV3.setConnect_app(query.getInt(i34));
                columnIndexOrThrow41 = i34;
                int i35 = columnIndexOrThrow42;
                entityHealthActivityV3.setAvg_pace_speed(query.getInt(i35));
                columnIndexOrThrow42 = i35;
                int i36 = columnIndexOrThrow43;
                entityHealthActivityV3.setFast_pace_speed(query.getInt(i36));
                columnIndexOrThrow43 = i36;
                int i37 = columnIndexOrThrow44;
                entityHealthActivityV3.setTraining_effect(query.getInt(i37));
                columnIndexOrThrow44 = i37;
                int i38 = columnIndexOrThrow45;
                entityHealthActivityV3.setVO2max(query.getInt(i38));
                columnIndexOrThrow45 = i38;
                int i39 = columnIndexOrThrow46;
                entityHealthActivityV3.setRecovery_time_year(query.getInt(i39));
                columnIndexOrThrow46 = i39;
                int i40 = columnIndexOrThrow47;
                entityHealthActivityV3.setRecovery_time_mon(query.getInt(i40));
                columnIndexOrThrow47 = i40;
                int i41 = columnIndexOrThrow48;
                entityHealthActivityV3.setRecovery_time_day(query.getInt(i41));
                columnIndexOrThrow48 = i41;
                int i42 = columnIndexOrThrow49;
                entityHealthActivityV3.setRecovery_time_hour(query.getInt(i42));
                columnIndexOrThrow49 = i42;
                int i43 = columnIndexOrThrow50;
                entityHealthActivityV3.setRecovery_time_min(query.getInt(i43));
                columnIndexOrThrow50 = i43;
                int i44 = columnIndexOrThrow51;
                entityHealthActivityV3.setRecovery_time_s(query.getInt(i44));
                int i45 = columnIndexOrThrow52;
                if (query.isNull(i45)) {
                    i3 = i44;
                    string6 = null;
                } else {
                    string6 = query.getString(i45);
                    i3 = i44;
                }
                entityHealthActivityV3.setPace_speed_items(HealthSportItemConverter.fromStr2(string6));
                int i46 = columnIndexOrThrow53;
                if (query.isNull(i46)) {
                    columnIndexOrThrow53 = i46;
                    string7 = null;
                } else {
                    string7 = query.getString(i46);
                    columnIndexOrThrow53 = i46;
                }
                entityHealthActivityV3.setPaddle_number_items(HealthSportItemConverter.fromStr2(string7));
                int i47 = columnIndexOrThrow54;
                if (query.isNull(i47)) {
                    columnIndexOrThrow54 = i47;
                    string8 = null;
                } else {
                    string8 = query.getString(i47);
                    columnIndexOrThrow54 = i47;
                }
                entityHealthActivityV3.setPaddle_frequency_items(HealthSportItemConverter.fromStr2(string8));
                int i48 = columnIndexOrThrow55;
                if (query.isNull(i48)) {
                    columnIndexOrThrow55 = i48;
                    string9 = null;
                } else {
                    string9 = query.getString(i48);
                    columnIndexOrThrow55 = i48;
                }
                entityHealthActivityV3.setTread_frequency_items(HealthSportItemConverter.fromStr2(string9));
                int i49 = columnIndexOrThrow56;
                entityHealthActivityV3.setPaddle_number_count(query.getInt(i49));
                columnIndexOrThrow56 = i49;
                int i50 = columnIndexOrThrow57;
                entityHealthActivityV3.setPaddle_frequency_count(query.getInt(i50));
                columnIndexOrThrow57 = i50;
                int i51 = columnIndexOrThrow58;
                entityHealthActivityV3.setTread_frequency_count(query.getInt(i51));
                columnIndexOrThrow58 = i51;
                int i52 = columnIndexOrThrow59;
                entityHealthActivityV3.setEnd_month(query.getInt(i52));
                columnIndexOrThrow59 = i52;
                int i53 = columnIndexOrThrow60;
                entityHealthActivityV3.setEnd_day(query.getInt(i53));
                columnIndexOrThrow60 = i53;
                int i54 = columnIndexOrThrow61;
                entityHealthActivityV3.setEnd_hour(query.getInt(i54));
                columnIndexOrThrow61 = i54;
                int i55 = columnIndexOrThrow62;
                entityHealthActivityV3.setEnd_minute(query.getInt(i55));
                int i56 = columnIndexOrThrow12;
                int i57 = columnIndexOrThrow63;
                int i58 = columnIndexOrThrow13;
                entityHealthActivityV3.setTimestamp(query.getLong(i57));
                int i59 = columnIndexOrThrow64;
                entityHealthActivityV3.setProcessed(query.getInt(i59) != 0);
                arrayList.add(entityHealthActivityV3);
                columnIndexOrThrow64 = i59;
                columnIndexOrThrow12 = i56;
                columnIndexOrThrow13 = i58;
                columnIndexOrThrow62 = i55;
                columnIndexOrThrow63 = i57;
                columnIndexOrThrow11 = i7;
                columnIndexOrThrow = i5;
                i4 = i6;
                columnIndexOrThrow15 = i8;
                columnIndexOrThrow16 = i9;
                columnIndexOrThrow17 = i10;
                columnIndexOrThrow18 = i11;
                columnIndexOrThrow19 = i12;
                columnIndexOrThrow20 = i13;
                columnIndexOrThrow21 = i14;
                columnIndexOrThrow22 = i15;
                columnIndexOrThrow23 = i16;
                columnIndexOrThrow24 = i17;
                columnIndexOrThrow25 = i18;
                columnIndexOrThrow26 = i;
                int i60 = i2;
                columnIndexOrThrow30 = i23;
                columnIndexOrThrow29 = i60;
                int i61 = i3;
                columnIndexOrThrow52 = i45;
                columnIndexOrThrow51 = i61;
            }
            query.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public List<EntityHealthSwimV3> getAllUnProcessedSwimActivityData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_swim_v3  WHERE macAddress=? and isProcessed == 0 ORDER BY timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7088a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7088a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_DAY);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "month");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "year");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, WeatherCriteria.UNIT_TYPE_HOUR);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "minute");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "second");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "durations");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "trips");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "averageSWOLF");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokesNumber");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "swimmingPosture");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "poolDistance");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "confirmDistance");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "avg_speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "avg_frequency");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHealthSwimV3 entityHealthSwimV3 = new EntityHealthSwimV3(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                    int i3 = columnIndexOrThrow;
                    entityHealthSwimV3.setType(query.getInt(columnIndexOrThrow8));
                    entityHealthSwimV3.setDurations(query.getInt(columnIndexOrThrow9));
                    entityHealthSwimV3.setCalories(query.getInt(columnIndexOrThrow10));
                    entityHealthSwimV3.setDistance(query.getInt(columnIndexOrThrow11));
                    entityHealthSwimV3.setTrips(query.getInt(columnIndexOrThrow12));
                    entityHealthSwimV3.setAverageSWOLF(query.getInt(columnIndexOrThrow13));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow11;
                    entityHealthSwimV3.setTotalStrokesNumber(query.getInt(i4));
                    int i6 = columnIndexOrThrow15;
                    entityHealthSwimV3.setSwimmingPosture(query.getInt(i6));
                    int i7 = columnIndexOrThrow16;
                    entityHealthSwimV3.setPoolDistance(query.getInt(i7));
                    int i8 = columnIndexOrThrow17;
                    entityHealthSwimV3.setConfirmDistance(query.getInt(i8));
                    int i9 = columnIndexOrThrow18;
                    if (query.isNull(i9)) {
                        i = i9;
                        string = null;
                    } else {
                        string = query.getString(i9);
                        i = i9;
                    }
                    entityHealthSwimV3.setItems(ActivityDataConvertor.fromStr4(string));
                    int i10 = columnIndexOrThrow19;
                    entityHealthSwimV3.setAvg_speed(query.getInt(i10));
                    columnIndexOrThrow19 = i10;
                    int i11 = columnIndexOrThrow20;
                    entityHealthSwimV3.setAvg_frequency(query.getInt(i11));
                    int i12 = columnIndexOrThrow12;
                    int i13 = columnIndexOrThrow21;
                    entityHealthSwimV3.setTimestamp(query.getLong(i13));
                    int i14 = columnIndexOrThrow22;
                    entityHealthSwimV3.setProcessed(query.getInt(i14) != 0);
                    arrayList.add(entityHealthSwimV3);
                    columnIndexOrThrow22 = i14;
                    columnIndexOrThrow12 = i12;
                    columnIndexOrThrow20 = i11;
                    columnIndexOrThrow11 = i5;
                    columnIndexOrThrow21 = i13;
                    i2 = i4;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow17 = i8;
                    columnIndexOrThrow = i3;
                    columnIndexOrThrow18 = i;
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

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public long insert(EntityHealthActivityV3 entityHealthActivityV3) {
        this.f7088a.assertNotSuspendingTransaction();
        this.f7088a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHealthActivityV3);
            this.f7088a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7088a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public void insertAll(List<EntityHealthActivityV3> list) {
        this.f7088a.assertNotSuspendingTransaction();
        this.f7088a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7088a.setTransactionSuccessful();
        } finally {
            this.f7088a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public void insertAllSwimData(List<EntityHealthSwimV3> list) {
        this.f7088a.assertNotSuspendingTransaction();
        this.f7088a.beginTransaction();
        try {
            this.c.insert(list);
            this.f7088a.setTransactionSuccessful();
        } finally {
            this.f7088a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public long insertSwimData(EntityHealthSwimV3 entityHealthSwimV3) {
        this.f7088a.assertNotSuspendingTransaction();
        this.f7088a.beginTransaction();
        try {
            long insertAndReturnId = this.c.insertAndReturnId(entityHealthSwimV3);
            this.f7088a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7088a.endTransaction();
        }
    }

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public void updateASwimActivityDataProcessedBefore(String str, long j) {
        this.f7088a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7088a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7088a.setTransactionSuccessful();
        } finally {
            this.f7088a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa
    public void updateActivityDataProcessedBefore(String str, long j) {
        this.f7088a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7088a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7088a.setTransactionSuccessful();
        } finally {
            this.f7088a.endTransaction();
            this.d.release(acquire);
        }
    }
}
