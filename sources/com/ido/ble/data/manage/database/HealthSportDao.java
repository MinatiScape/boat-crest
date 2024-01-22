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
public class HealthSportDao extends AbstractDao<HealthSport, Long> {
    public static final String TABLENAME = "HEALTH_SPORT";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property Date;
        public static final Property Day;
        public static final Property Month;
        public static final Property StartTime;
        public static final Property TimeSpace;
        public static final Property TotalActiveTime;
        public static final Property TotalCalory;
        public static final Property TotalDistance;
        public static final Property TotalStepCount;
        public static final Property Year;
        public static final Property SportDataId = new Property(0, Long.class, "sportDataId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            TotalStepCount = new Property(5, cls, "totalStepCount", false, "TOTAL_STEP_COUNT");
            TotalCalory = new Property(6, cls, "totalCalory", false, "TOTAL_CALORY");
            TotalDistance = new Property(7, cls, "totalDistance", false, "TOTAL_DISTANCE");
            TotalActiveTime = new Property(8, cls, "totalActiveTime", false, "TOTAL_ACTIVE_TIME");
            StartTime = new Property(9, cls, "startTime", false, "START_TIME");
            TimeSpace = new Property(10, cls, "timeSpace", false, "TIME_SPACE");
            Date = new Property(11, Date.class, "date", false, "DATE");
        }
    }

    public HealthSportDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthSportDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_SPORT\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"TOTAL_STEP_COUNT\" INTEGER NOT NULL ,\"TOTAL_CALORY\" INTEGER NOT NULL ,\"TOTAL_DISTANCE\" INTEGER NOT NULL ,\"TOTAL_ACTIVE_TIME\" INTEGER NOT NULL ,\"START_TIME\" INTEGER NOT NULL ,\"TIME_SPACE\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_SPORT\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthSport healthSport) {
        sQLiteStatement.clearBindings();
        Long sportDataId = healthSport.getSportDataId();
        if (sportDataId != null) {
            sQLiteStatement.bindLong(1, sportDataId.longValue());
        }
        sQLiteStatement.bindLong(2, healthSport.getDId());
        sQLiteStatement.bindLong(3, healthSport.getYear());
        sQLiteStatement.bindLong(4, healthSport.getMonth());
        sQLiteStatement.bindLong(5, healthSport.getDay());
        sQLiteStatement.bindLong(6, healthSport.getTotalStepCount());
        sQLiteStatement.bindLong(7, healthSport.getTotalCalory());
        sQLiteStatement.bindLong(8, healthSport.getTotalDistance());
        sQLiteStatement.bindLong(9, healthSport.getTotalActiveTime());
        sQLiteStatement.bindLong(10, healthSport.getStartTime());
        sQLiteStatement.bindLong(11, healthSport.getTimeSpace());
        Date date = healthSport.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(12, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthSport healthSport) {
        databaseStatement.clearBindings();
        Long sportDataId = healthSport.getSportDataId();
        if (sportDataId != null) {
            databaseStatement.bindLong(1, sportDataId.longValue());
        }
        databaseStatement.bindLong(2, healthSport.getDId());
        databaseStatement.bindLong(3, healthSport.getYear());
        databaseStatement.bindLong(4, healthSport.getMonth());
        databaseStatement.bindLong(5, healthSport.getDay());
        databaseStatement.bindLong(6, healthSport.getTotalStepCount());
        databaseStatement.bindLong(7, healthSport.getTotalCalory());
        databaseStatement.bindLong(8, healthSport.getTotalDistance());
        databaseStatement.bindLong(9, healthSport.getTotalActiveTime());
        databaseStatement.bindLong(10, healthSport.getStartTime());
        databaseStatement.bindLong(11, healthSport.getTimeSpace());
        Date date = healthSport.getDate();
        if (date != null) {
            databaseStatement.bindLong(12, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthSport healthSport) {
        if (healthSport != null) {
            return healthSport.getSportDataId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthSport healthSport) {
        return healthSport.getSportDataId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthSport readEntity(Cursor cursor, int i) {
        int i2;
        int i3;
        int i4 = i + 0;
        Date date = null;
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
        int i14 = i + 11;
        if (cursor.isNull(i14)) {
            i2 = i11;
            i3 = i12;
        } else {
            i2 = i11;
            i3 = i12;
            date = new Date(cursor.getLong(i14));
        }
        return new HealthSport(valueOf, j, i5, i6, i7, i8, i9, i10, i2, i3, i13, date);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthSport healthSport, int i) {
        int i2 = i + 0;
        healthSport.setSportDataId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthSport.setDId(cursor.getLong(i + 1));
        healthSport.setYear(cursor.getInt(i + 2));
        healthSport.setMonth(cursor.getInt(i + 3));
        healthSport.setDay(cursor.getInt(i + 4));
        healthSport.setTotalStepCount(cursor.getInt(i + 5));
        healthSport.setTotalCalory(cursor.getInt(i + 6));
        healthSport.setTotalDistance(cursor.getInt(i + 7));
        healthSport.setTotalActiveTime(cursor.getInt(i + 8));
        healthSport.setStartTime(cursor.getInt(i + 9));
        healthSport.setTimeSpace(cursor.getInt(i + 10));
        int i3 = i + 11;
        healthSport.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthSport healthSport, long j) {
        healthSport.setSportDataId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
