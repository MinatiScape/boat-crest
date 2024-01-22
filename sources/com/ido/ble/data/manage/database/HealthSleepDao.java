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
public class HealthSleepDao extends AbstractDao<HealthSleep, Long> {
    public static final String TABLENAME = "HEALTH_SLEEP";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property AwakeCount;
        public static final Property Date;
        public static final Property Day;
        public static final Property DeepSleepCount;
        public static final Property DeepSleepMinutes;
        public static final Property LightSleepCount;
        public static final Property LightSleepMinutes;
        public static final Property Month;
        public static final Property SleepEndedTimeH;
        public static final Property SleepEndedTimeM;
        public static final Property SleepScore;
        public static final Property TotalSleepMinutes;
        public static final Property Year;
        public static final Property SleepDataId = new Property(0, Long.class, "sleepDataId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            SleepEndedTimeH = new Property(5, cls, "sleepEndedTimeH", false, "SLEEP_ENDED_TIME_H");
            SleepEndedTimeM = new Property(6, cls, "sleepEndedTimeM", false, "SLEEP_ENDED_TIME_M");
            TotalSleepMinutes = new Property(7, cls, "totalSleepMinutes", false, "TOTAL_SLEEP_MINUTES");
            LightSleepCount = new Property(8, cls, "lightSleepCount", false, "LIGHT_SLEEP_COUNT");
            DeepSleepCount = new Property(9, cls, "deepSleepCount", false, "DEEP_SLEEP_COUNT");
            AwakeCount = new Property(10, cls, "awakeCount", false, "AWAKE_COUNT");
            LightSleepMinutes = new Property(11, cls, "lightSleepMinutes", false, "LIGHT_SLEEP_MINUTES");
            DeepSleepMinutes = new Property(12, cls, "deepSleepMinutes", false, "DEEP_SLEEP_MINUTES");
            SleepScore = new Property(13, cls, "sleepScore", false, "SLEEP_SCORE");
            Date = new Property(14, Date.class, "date", false, "DATE");
        }
    }

    public HealthSleepDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthSleepDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_SLEEP\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"SLEEP_ENDED_TIME_H\" INTEGER NOT NULL ,\"SLEEP_ENDED_TIME_M\" INTEGER NOT NULL ,\"TOTAL_SLEEP_MINUTES\" INTEGER NOT NULL ,\"LIGHT_SLEEP_COUNT\" INTEGER NOT NULL ,\"DEEP_SLEEP_COUNT\" INTEGER NOT NULL ,\"AWAKE_COUNT\" INTEGER NOT NULL ,\"LIGHT_SLEEP_MINUTES\" INTEGER NOT NULL ,\"DEEP_SLEEP_MINUTES\" INTEGER NOT NULL ,\"SLEEP_SCORE\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_SLEEP\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthSleep healthSleep) {
        sQLiteStatement.clearBindings();
        Long sleepDataId = healthSleep.getSleepDataId();
        if (sleepDataId != null) {
            sQLiteStatement.bindLong(1, sleepDataId.longValue());
        }
        sQLiteStatement.bindLong(2, healthSleep.getDId());
        sQLiteStatement.bindLong(3, healthSleep.getYear());
        sQLiteStatement.bindLong(4, healthSleep.getMonth());
        sQLiteStatement.bindLong(5, healthSleep.getDay());
        sQLiteStatement.bindLong(6, healthSleep.getSleepEndedTimeH());
        sQLiteStatement.bindLong(7, healthSleep.getSleepEndedTimeM());
        sQLiteStatement.bindLong(8, healthSleep.getTotalSleepMinutes());
        sQLiteStatement.bindLong(9, healthSleep.getLightSleepCount());
        sQLiteStatement.bindLong(10, healthSleep.getDeepSleepCount());
        sQLiteStatement.bindLong(11, healthSleep.getAwakeCount());
        sQLiteStatement.bindLong(12, healthSleep.getLightSleepMinutes());
        sQLiteStatement.bindLong(13, healthSleep.getDeepSleepMinutes());
        sQLiteStatement.bindLong(14, healthSleep.getSleepScore());
        Date date = healthSleep.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(15, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthSleep healthSleep) {
        databaseStatement.clearBindings();
        Long sleepDataId = healthSleep.getSleepDataId();
        if (sleepDataId != null) {
            databaseStatement.bindLong(1, sleepDataId.longValue());
        }
        databaseStatement.bindLong(2, healthSleep.getDId());
        databaseStatement.bindLong(3, healthSleep.getYear());
        databaseStatement.bindLong(4, healthSleep.getMonth());
        databaseStatement.bindLong(5, healthSleep.getDay());
        databaseStatement.bindLong(6, healthSleep.getSleepEndedTimeH());
        databaseStatement.bindLong(7, healthSleep.getSleepEndedTimeM());
        databaseStatement.bindLong(8, healthSleep.getTotalSleepMinutes());
        databaseStatement.bindLong(9, healthSleep.getLightSleepCount());
        databaseStatement.bindLong(10, healthSleep.getDeepSleepCount());
        databaseStatement.bindLong(11, healthSleep.getAwakeCount());
        databaseStatement.bindLong(12, healthSleep.getLightSleepMinutes());
        databaseStatement.bindLong(13, healthSleep.getDeepSleepMinutes());
        databaseStatement.bindLong(14, healthSleep.getSleepScore());
        Date date = healthSleep.getDate();
        if (date != null) {
            databaseStatement.bindLong(15, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthSleep healthSleep) {
        if (healthSleep != null) {
            return healthSleep.getSleepDataId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthSleep healthSleep) {
        return healthSleep.getSleepDataId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthSleep readEntity(Cursor cursor, int i) {
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
        int i17 = i + 14;
        if (cursor.isNull(i17)) {
            date = null;
            i2 = i13;
            i3 = i14;
        } else {
            i2 = i13;
            i3 = i14;
            date = new Date(cursor.getLong(i17));
        }
        return new HealthSleep(valueOf, j, i5, i6, i7, i8, i9, i10, i11, i12, i2, i3, i15, i16, date);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthSleep healthSleep, int i) {
        int i2 = i + 0;
        healthSleep.setSleepDataId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthSleep.setDId(cursor.getLong(i + 1));
        healthSleep.setYear(cursor.getInt(i + 2));
        healthSleep.setMonth(cursor.getInt(i + 3));
        healthSleep.setDay(cursor.getInt(i + 4));
        healthSleep.setSleepEndedTimeH(cursor.getInt(i + 5));
        healthSleep.setSleepEndedTimeM(cursor.getInt(i + 6));
        healthSleep.setTotalSleepMinutes(cursor.getInt(i + 7));
        healthSleep.setLightSleepCount(cursor.getInt(i + 8));
        healthSleep.setDeepSleepCount(cursor.getInt(i + 9));
        healthSleep.setAwakeCount(cursor.getInt(i + 10));
        healthSleep.setLightSleepMinutes(cursor.getInt(i + 11));
        healthSleep.setDeepSleepMinutes(cursor.getInt(i + 12));
        healthSleep.setSleepScore(cursor.getInt(i + 13));
        int i3 = i + 14;
        healthSleep.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthSleep healthSleep, long j) {
        healthSleep.setSleepDataId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
