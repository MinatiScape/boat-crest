package com.ido.ble.data.manage.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
public class HealthActivityDao extends AbstractDao<HealthActivity, Long> {
    public static final String TABLENAME = "HEALTH_ACTIVITY";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property Aerobic_mins;
        public static final Property AnaerobicMins;
        public static final Property Avg_hr_value;
        public static final Property Burn_fat_mins;
        public static final Property Calories;
        public static final Property Date;
        public static final Property Day;
        public static final Property Distance;
        public static final Property Durations;
        public static final Property Hour;
        public static final Property Hr_data_interval_minute;
        public static final Property Hr_data_vlaue_json;
        public static final Property Items_json;
        public static final Property Limit_mins;
        public static final Property Max_hr_value;
        public static final Property Min_hr_value;
        public static final Property Minute;
        public static final Property Month;
        public static final Property Range1;
        public static final Property Range2;
        public static final Property Range3;
        public static final Property Range4;
        public static final Property Range5;
        public static final Property Second;
        public static final Property Step;
        public static final Property Type;
        public static final Property WarmUpMins;
        public static final Property Year;
        public static final Property ActivityId = new Property(0, Long.class, "activityId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            Hour = new Property(5, cls, WeatherCriteria.UNIT_TYPE_HOUR, false, "HOUR");
            Minute = new Property(6, cls, "minute", false, "MINUTE");
            Second = new Property(7, cls, "second", false, "SECOND");
            Hr_data_interval_minute = new Property(8, cls, "hr_data_interval_minute", false, "HR_DATA_INTERVAL_MINUTE");
            Type = new Property(9, cls, "type", false, "TYPE");
            Step = new Property(10, cls, DeviceKey.Step, false, "STEP");
            Durations = new Property(11, cls, "durations", false, "DURATIONS");
            Calories = new Property(12, cls, "calories", false, FitnessChallengeConstants.CALORIES);
            Distance = new Property(13, cls, "distance", false, "DISTANCE");
            Avg_hr_value = new Property(14, cls, "avg_hr_value", false, "AVG_HR_VALUE");
            Max_hr_value = new Property(15, cls, "max_hr_value", false, "MAX_HR_VALUE");
            Min_hr_value = new Property(16, cls, "min_hr_value", false, "MIN_HR_VALUE");
            WarmUpMins = new Property(17, cls, "warmUpMins", false, "WARM_UP_MINS");
            Burn_fat_mins = new Property(18, cls, "burn_fat_mins", false, "BURN_FAT_MINS");
            Aerobic_mins = new Property(19, cls, "aerobic_mins", false, "AEROBIC_MINS");
            AnaerobicMins = new Property(20, cls, "anaerobicMins", false, "ANAEROBIC_MINS");
            Limit_mins = new Property(21, cls, "limit_mins", false, "LIMIT_MINS");
            Range1 = new Property(22, cls, "range1", false, "RANGE1");
            Range2 = new Property(23, cls, "range2", false, "RANGE2");
            Range3 = new Property(24, cls, "range3", false, "RANGE3");
            Range4 = new Property(25, cls, "range4", false, "RANGE4");
            Range5 = new Property(26, cls, "range5", false, "RANGE5");
            Hr_data_vlaue_json = new Property(27, String.class, "hr_data_vlaue_json", false, "HR_DATA_VLAUE_JSON");
            Items_json = new Property(28, String.class, "items_json", false, "ITEMS_JSON");
            Date = new Property(29, Date.class, "date", false, "DATE");
        }
    }

    public HealthActivityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthActivityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_ACTIVITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"HOUR\" INTEGER NOT NULL ,\"MINUTE\" INTEGER NOT NULL ,\"SECOND\" INTEGER NOT NULL ,\"HR_DATA_INTERVAL_MINUTE\" INTEGER NOT NULL ,\"TYPE\" INTEGER NOT NULL ,\"STEP\" INTEGER NOT NULL ,\"DURATIONS\" INTEGER NOT NULL ,\"CALORIES\" INTEGER NOT NULL ,\"DISTANCE\" INTEGER NOT NULL ,\"AVG_HR_VALUE\" INTEGER NOT NULL ,\"MAX_HR_VALUE\" INTEGER NOT NULL ,\"MIN_HR_VALUE\" INTEGER NOT NULL ,\"WARM_UP_MINS\" INTEGER NOT NULL ,\"BURN_FAT_MINS\" INTEGER NOT NULL ,\"AEROBIC_MINS\" INTEGER NOT NULL ,\"ANAEROBIC_MINS\" INTEGER NOT NULL ,\"LIMIT_MINS\" INTEGER NOT NULL ,\"RANGE1\" INTEGER NOT NULL ,\"RANGE2\" INTEGER NOT NULL ,\"RANGE3\" INTEGER NOT NULL ,\"RANGE4\" INTEGER NOT NULL ,\"RANGE5\" INTEGER NOT NULL ,\"HR_DATA_VLAUE_JSON\" TEXT,\"ITEMS_JSON\" TEXT,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_ACTIVITY\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthActivity healthActivity) {
        sQLiteStatement.clearBindings();
        Long activityId = healthActivity.getActivityId();
        if (activityId != null) {
            sQLiteStatement.bindLong(1, activityId.longValue());
        }
        sQLiteStatement.bindLong(2, healthActivity.getDId());
        sQLiteStatement.bindLong(3, healthActivity.getYear());
        sQLiteStatement.bindLong(4, healthActivity.getMonth());
        sQLiteStatement.bindLong(5, healthActivity.getDay());
        sQLiteStatement.bindLong(6, healthActivity.getHour());
        sQLiteStatement.bindLong(7, healthActivity.getMinute());
        sQLiteStatement.bindLong(8, healthActivity.getSecond());
        sQLiteStatement.bindLong(9, healthActivity.getHr_data_interval_minute());
        sQLiteStatement.bindLong(10, healthActivity.getType());
        sQLiteStatement.bindLong(11, healthActivity.getStep());
        sQLiteStatement.bindLong(12, healthActivity.getDurations());
        sQLiteStatement.bindLong(13, healthActivity.getCalories());
        sQLiteStatement.bindLong(14, healthActivity.getDistance());
        sQLiteStatement.bindLong(15, healthActivity.getAvg_hr_value());
        sQLiteStatement.bindLong(16, healthActivity.getMax_hr_value());
        sQLiteStatement.bindLong(17, healthActivity.getMin_hr_value());
        sQLiteStatement.bindLong(18, healthActivity.getWarmUpMins());
        sQLiteStatement.bindLong(19, healthActivity.getBurn_fat_mins());
        sQLiteStatement.bindLong(20, healthActivity.getAerobic_mins());
        sQLiteStatement.bindLong(21, healthActivity.getAnaerobicMins());
        sQLiteStatement.bindLong(22, healthActivity.getLimit_mins());
        sQLiteStatement.bindLong(23, healthActivity.getRange1());
        sQLiteStatement.bindLong(24, healthActivity.getRange2());
        sQLiteStatement.bindLong(25, healthActivity.getRange3());
        sQLiteStatement.bindLong(26, healthActivity.getRange4());
        sQLiteStatement.bindLong(27, healthActivity.getRange5());
        String hr_data_vlaue_json = healthActivity.getHr_data_vlaue_json();
        if (hr_data_vlaue_json != null) {
            sQLiteStatement.bindString(28, hr_data_vlaue_json);
        }
        String items_json = healthActivity.getItems_json();
        if (items_json != null) {
            sQLiteStatement.bindString(29, items_json);
        }
        Date date = healthActivity.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(30, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthActivity healthActivity) {
        databaseStatement.clearBindings();
        Long activityId = healthActivity.getActivityId();
        if (activityId != null) {
            databaseStatement.bindLong(1, activityId.longValue());
        }
        databaseStatement.bindLong(2, healthActivity.getDId());
        databaseStatement.bindLong(3, healthActivity.getYear());
        databaseStatement.bindLong(4, healthActivity.getMonth());
        databaseStatement.bindLong(5, healthActivity.getDay());
        databaseStatement.bindLong(6, healthActivity.getHour());
        databaseStatement.bindLong(7, healthActivity.getMinute());
        databaseStatement.bindLong(8, healthActivity.getSecond());
        databaseStatement.bindLong(9, healthActivity.getHr_data_interval_minute());
        databaseStatement.bindLong(10, healthActivity.getType());
        databaseStatement.bindLong(11, healthActivity.getStep());
        databaseStatement.bindLong(12, healthActivity.getDurations());
        databaseStatement.bindLong(13, healthActivity.getCalories());
        databaseStatement.bindLong(14, healthActivity.getDistance());
        databaseStatement.bindLong(15, healthActivity.getAvg_hr_value());
        databaseStatement.bindLong(16, healthActivity.getMax_hr_value());
        databaseStatement.bindLong(17, healthActivity.getMin_hr_value());
        databaseStatement.bindLong(18, healthActivity.getWarmUpMins());
        databaseStatement.bindLong(19, healthActivity.getBurn_fat_mins());
        databaseStatement.bindLong(20, healthActivity.getAerobic_mins());
        databaseStatement.bindLong(21, healthActivity.getAnaerobicMins());
        databaseStatement.bindLong(22, healthActivity.getLimit_mins());
        databaseStatement.bindLong(23, healthActivity.getRange1());
        databaseStatement.bindLong(24, healthActivity.getRange2());
        databaseStatement.bindLong(25, healthActivity.getRange3());
        databaseStatement.bindLong(26, healthActivity.getRange4());
        databaseStatement.bindLong(27, healthActivity.getRange5());
        String hr_data_vlaue_json = healthActivity.getHr_data_vlaue_json();
        if (hr_data_vlaue_json != null) {
            databaseStatement.bindString(28, hr_data_vlaue_json);
        }
        String items_json = healthActivity.getItems_json();
        if (items_json != null) {
            databaseStatement.bindString(29, items_json);
        }
        Date date = healthActivity.getDate();
        if (date != null) {
            databaseStatement.bindLong(30, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthActivity healthActivity) {
        if (healthActivity != null) {
            return healthActivity.getActivityId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthActivity healthActivity) {
        return healthActivity.getActivityId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthActivity readEntity(Cursor cursor, int i) {
        int i2;
        int i3;
        Date date;
        int i4 = i + 0;
        Long valueOf = cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4));
        long j = cursor.getLong(i + 1);
        int i5 = cursor.getInt(i + 2);
        int i6 = cursor.getInt(i + 3);
        int i7 = cursor.getInt(i + 4);
        int i8 = cursor.getInt(i + 5);
        int i9 = cursor.getInt(i + 6);
        int i10 = cursor.getInt(i + 7);
        int i11 = cursor.getInt(i + 8);
        int i12 = cursor.getInt(i + 9);
        int i13 = cursor.getInt(i + 10);
        int i14 = cursor.getInt(i + 11);
        int i15 = cursor.getInt(i + 12);
        int i16 = cursor.getInt(i + 13);
        int i17 = cursor.getInt(i + 14);
        int i18 = cursor.getInt(i + 15);
        int i19 = cursor.getInt(i + 16);
        int i20 = cursor.getInt(i + 17);
        int i21 = cursor.getInt(i + 18);
        int i22 = cursor.getInt(i + 19);
        int i23 = cursor.getInt(i + 20);
        int i24 = cursor.getInt(i + 21);
        int i25 = cursor.getInt(i + 22);
        int i26 = cursor.getInt(i + 23);
        int i27 = cursor.getInt(i + 24);
        int i28 = cursor.getInt(i + 25);
        int i29 = cursor.getInt(i + 26);
        int i30 = i + 27;
        String string = cursor.isNull(i30) ? null : cursor.getString(i30);
        int i31 = i + 28;
        String string2 = cursor.isNull(i31) ? null : cursor.getString(i31);
        int i32 = i + 29;
        if (cursor.isNull(i32)) {
            date = null;
            i2 = i13;
            i3 = i14;
        } else {
            i2 = i13;
            i3 = i14;
            date = new Date(cursor.getLong(i32));
        }
        return new HealthActivity(valueOf, j, i5, i6, i7, i8, i9, i10, i11, i12, i2, i3, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, string, string2, date);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthActivity healthActivity, int i) {
        int i2 = i + 0;
        healthActivity.setActivityId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthActivity.setDId(cursor.getLong(i + 1));
        healthActivity.setYear(cursor.getInt(i + 2));
        healthActivity.setMonth(cursor.getInt(i + 3));
        healthActivity.setDay(cursor.getInt(i + 4));
        healthActivity.setHour(cursor.getInt(i + 5));
        healthActivity.setMinute(cursor.getInt(i + 6));
        healthActivity.setSecond(cursor.getInt(i + 7));
        healthActivity.setHr_data_interval_minute(cursor.getInt(i + 8));
        healthActivity.setType(cursor.getInt(i + 9));
        healthActivity.setStep(cursor.getInt(i + 10));
        healthActivity.setDurations(cursor.getInt(i + 11));
        healthActivity.setCalories(cursor.getInt(i + 12));
        healthActivity.setDistance(cursor.getInt(i + 13));
        healthActivity.setAvg_hr_value(cursor.getInt(i + 14));
        healthActivity.setMax_hr_value(cursor.getInt(i + 15));
        healthActivity.setMin_hr_value(cursor.getInt(i + 16));
        healthActivity.setWarmUpMins(cursor.getInt(i + 17));
        healthActivity.setBurn_fat_mins(cursor.getInt(i + 18));
        healthActivity.setAerobic_mins(cursor.getInt(i + 19));
        healthActivity.setAnaerobicMins(cursor.getInt(i + 20));
        healthActivity.setLimit_mins(cursor.getInt(i + 21));
        healthActivity.setRange1(cursor.getInt(i + 22));
        healthActivity.setRange2(cursor.getInt(i + 23));
        healthActivity.setRange3(cursor.getInt(i + 24));
        healthActivity.setRange4(cursor.getInt(i + 25));
        healthActivity.setRange5(cursor.getInt(i + 26));
        int i3 = i + 27;
        healthActivity.setHr_data_vlaue_json(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 28;
        healthActivity.setItems_json(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 29;
        healthActivity.setDate(cursor.isNull(i5) ? null : new Date(cursor.getLong(i5)));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(HealthActivity healthActivity, long j) {
        healthActivity.setActivityId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
