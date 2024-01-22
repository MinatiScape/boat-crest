package com.ido.ble.data.manage.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
public class HealthHeartRateDao extends AbstractDao<HealthHeartRate, Long> {
    public static final String TABLENAME = "HEALTH_HEART_RATE";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property Aerobic_mins;
        public static final Property Aerobic_threshold;
        public static final Property AnaerobicMins;
        public static final Property AnaerobicThreshold;
        public static final Property Burn_fat_mins;
        public static final Property Burn_fat_threshold;
        public static final Property Date;
        public static final Property Day;
        public static final Property Limit_mins;
        public static final Property Limit_threshold;
        public static final Property Month;
        public static final Property Range1;
        public static final Property Range2;
        public static final Property Range3;
        public static final Property Range4;
        public static final Property Range5;
        public static final Property SilentHeart;
        public static final Property StartTime;
        public static final Property UserMaxHr;
        public static final Property WarmUpMins;
        public static final Property WarmUpThreshold;
        public static final Property Year;
        public static final Property RateDataId = new Property(0, Long.class, "rateDataId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            StartTime = new Property(5, cls, "startTime", false, "START_TIME");
            SilentHeart = new Property(6, cls, "silentHeart", false, "SILENT_HEART");
            WarmUpThreshold = new Property(7, cls, "warmUpThreshold", false, "WARM_UP_THRESHOLD");
            Burn_fat_threshold = new Property(8, cls, "burn_fat_threshold", false, "BURN_FAT_THRESHOLD");
            Aerobic_threshold = new Property(9, cls, "aerobic_threshold", false, "AEROBIC_THRESHOLD");
            AnaerobicThreshold = new Property(10, cls, "anaerobicThreshold", false, "ANAEROBIC_THRESHOLD");
            Limit_threshold = new Property(11, cls, "limit_threshold", false, "LIMIT_THRESHOLD");
            WarmUpMins = new Property(12, cls, "warmUpMins", false, "WARM_UP_MINS");
            Burn_fat_mins = new Property(13, cls, "burn_fat_mins", false, "BURN_FAT_MINS");
            Aerobic_mins = new Property(14, cls, "aerobic_mins", false, "AEROBIC_MINS");
            AnaerobicMins = new Property(15, cls, "anaerobicMins", false, "ANAEROBIC_MINS");
            Limit_mins = new Property(16, cls, "limit_mins", false, "LIMIT_MINS");
            UserMaxHr = new Property(17, cls, "UserMaxHr", false, "USER_MAX_HR");
            Range1 = new Property(18, cls, "Range1", false, "RANGE1");
            Range2 = new Property(19, cls, "Range2", false, "RANGE2");
            Range3 = new Property(20, cls, "Range3", false, "RANGE3");
            Range4 = new Property(21, cls, "Range4", false, "RANGE4");
            Range5 = new Property(22, cls, "Range5", false, "RANGE5");
            Date = new Property(23, Date.class, "date", false, "DATE");
        }
    }

    public HealthHeartRateDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthHeartRateDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_HEART_RATE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"START_TIME\" INTEGER NOT NULL ,\"SILENT_HEART\" INTEGER NOT NULL ,\"WARM_UP_THRESHOLD\" INTEGER NOT NULL ,\"BURN_FAT_THRESHOLD\" INTEGER NOT NULL ,\"AEROBIC_THRESHOLD\" INTEGER NOT NULL ,\"ANAEROBIC_THRESHOLD\" INTEGER NOT NULL ,\"LIMIT_THRESHOLD\" INTEGER NOT NULL ,\"WARM_UP_MINS\" INTEGER NOT NULL ,\"BURN_FAT_MINS\" INTEGER NOT NULL ,\"AEROBIC_MINS\" INTEGER NOT NULL ,\"ANAEROBIC_MINS\" INTEGER NOT NULL ,\"LIMIT_MINS\" INTEGER NOT NULL ,\"USER_MAX_HR\" INTEGER NOT NULL ,\"RANGE1\" INTEGER NOT NULL ,\"RANGE2\" INTEGER NOT NULL ,\"RANGE3\" INTEGER NOT NULL ,\"RANGE4\" INTEGER NOT NULL ,\"RANGE5\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_HEART_RATE\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthHeartRate healthHeartRate) {
        sQLiteStatement.clearBindings();
        Long rateDataId = healthHeartRate.getRateDataId();
        if (rateDataId != null) {
            sQLiteStatement.bindLong(1, rateDataId.longValue());
        }
        sQLiteStatement.bindLong(2, healthHeartRate.getDId());
        sQLiteStatement.bindLong(3, healthHeartRate.getYear());
        sQLiteStatement.bindLong(4, healthHeartRate.getMonth());
        sQLiteStatement.bindLong(5, healthHeartRate.getDay());
        sQLiteStatement.bindLong(6, healthHeartRate.getStartTime());
        sQLiteStatement.bindLong(7, healthHeartRate.getSilentHeart());
        sQLiteStatement.bindLong(8, healthHeartRate.getWarmUpThreshold());
        sQLiteStatement.bindLong(9, healthHeartRate.getBurn_fat_threshold());
        sQLiteStatement.bindLong(10, healthHeartRate.getAerobic_threshold());
        sQLiteStatement.bindLong(11, healthHeartRate.getAnaerobicThreshold());
        sQLiteStatement.bindLong(12, healthHeartRate.getLimit_threshold());
        sQLiteStatement.bindLong(13, healthHeartRate.getWarmUpMins());
        sQLiteStatement.bindLong(14, healthHeartRate.getBurn_fat_mins());
        sQLiteStatement.bindLong(15, healthHeartRate.getAerobic_mins());
        sQLiteStatement.bindLong(16, healthHeartRate.getAnaerobicMins());
        sQLiteStatement.bindLong(17, healthHeartRate.getLimit_mins());
        sQLiteStatement.bindLong(18, healthHeartRate.getUserMaxHr());
        sQLiteStatement.bindLong(19, healthHeartRate.getRange1());
        sQLiteStatement.bindLong(20, healthHeartRate.getRange2());
        sQLiteStatement.bindLong(21, healthHeartRate.getRange3());
        sQLiteStatement.bindLong(22, healthHeartRate.getRange4());
        sQLiteStatement.bindLong(23, healthHeartRate.getRange5());
        Date date = healthHeartRate.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(24, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthHeartRate healthHeartRate) {
        databaseStatement.clearBindings();
        Long rateDataId = healthHeartRate.getRateDataId();
        if (rateDataId != null) {
            databaseStatement.bindLong(1, rateDataId.longValue());
        }
        databaseStatement.bindLong(2, healthHeartRate.getDId());
        databaseStatement.bindLong(3, healthHeartRate.getYear());
        databaseStatement.bindLong(4, healthHeartRate.getMonth());
        databaseStatement.bindLong(5, healthHeartRate.getDay());
        databaseStatement.bindLong(6, healthHeartRate.getStartTime());
        databaseStatement.bindLong(7, healthHeartRate.getSilentHeart());
        databaseStatement.bindLong(8, healthHeartRate.getWarmUpThreshold());
        databaseStatement.bindLong(9, healthHeartRate.getBurn_fat_threshold());
        databaseStatement.bindLong(10, healthHeartRate.getAerobic_threshold());
        databaseStatement.bindLong(11, healthHeartRate.getAnaerobicThreshold());
        databaseStatement.bindLong(12, healthHeartRate.getLimit_threshold());
        databaseStatement.bindLong(13, healthHeartRate.getWarmUpMins());
        databaseStatement.bindLong(14, healthHeartRate.getBurn_fat_mins());
        databaseStatement.bindLong(15, healthHeartRate.getAerobic_mins());
        databaseStatement.bindLong(16, healthHeartRate.getAnaerobicMins());
        databaseStatement.bindLong(17, healthHeartRate.getLimit_mins());
        databaseStatement.bindLong(18, healthHeartRate.getUserMaxHr());
        databaseStatement.bindLong(19, healthHeartRate.getRange1());
        databaseStatement.bindLong(20, healthHeartRate.getRange2());
        databaseStatement.bindLong(21, healthHeartRate.getRange3());
        databaseStatement.bindLong(22, healthHeartRate.getRange4());
        databaseStatement.bindLong(23, healthHeartRate.getRange5());
        Date date = healthHeartRate.getDate();
        if (date != null) {
            databaseStatement.bindLong(24, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthHeartRate healthHeartRate) {
        if (healthHeartRate != null) {
            return healthHeartRate.getRateDataId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthHeartRate healthHeartRate) {
        return healthHeartRate.getRateDataId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthHeartRate readEntity(Cursor cursor, int i) {
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
        int i26 = i + 23;
        if (cursor.isNull(i26)) {
            date = null;
            i2 = i13;
            i3 = i14;
        } else {
            i2 = i13;
            i3 = i14;
            date = new Date(cursor.getLong(i26));
        }
        return new HealthHeartRate(valueOf, j, i5, i6, i7, i8, i9, i10, i11, i12, i2, i3, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, date);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthHeartRate healthHeartRate, int i) {
        int i2 = i + 0;
        healthHeartRate.setRateDataId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthHeartRate.setDId(cursor.getLong(i + 1));
        healthHeartRate.setYear(cursor.getInt(i + 2));
        healthHeartRate.setMonth(cursor.getInt(i + 3));
        healthHeartRate.setDay(cursor.getInt(i + 4));
        healthHeartRate.setStartTime(cursor.getInt(i + 5));
        healthHeartRate.setSilentHeart(cursor.getInt(i + 6));
        healthHeartRate.setWarmUpThreshold(cursor.getInt(i + 7));
        healthHeartRate.setBurn_fat_threshold(cursor.getInt(i + 8));
        healthHeartRate.setAerobic_threshold(cursor.getInt(i + 9));
        healthHeartRate.setAnaerobicThreshold(cursor.getInt(i + 10));
        healthHeartRate.setLimit_threshold(cursor.getInt(i + 11));
        healthHeartRate.setWarmUpMins(cursor.getInt(i + 12));
        healthHeartRate.setBurn_fat_mins(cursor.getInt(i + 13));
        healthHeartRate.setAerobic_mins(cursor.getInt(i + 14));
        healthHeartRate.setAnaerobicMins(cursor.getInt(i + 15));
        healthHeartRate.setLimit_mins(cursor.getInt(i + 16));
        healthHeartRate.setUserMaxHr(cursor.getInt(i + 17));
        healthHeartRate.setRange1(cursor.getInt(i + 18));
        healthHeartRate.setRange2(cursor.getInt(i + 19));
        healthHeartRate.setRange3(cursor.getInt(i + 20));
        healthHeartRate.setRange4(cursor.getInt(i + 21));
        healthHeartRate.setRange5(cursor.getInt(i + 22));
        int i3 = i + 23;
        healthHeartRate.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthHeartRate healthHeartRate, long j) {
        healthHeartRate.setRateDataId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
