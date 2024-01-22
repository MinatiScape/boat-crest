package com.ido.ble.gps.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.ble.data.manage.database.DaoSession;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
public class HealthGpsDao extends AbstractDao<HealthGps, Long> {
    public static final String TABLENAME = "HEALTH_GPS";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property HealthGpsId = new Property(0, Long.class, "healthGpsId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");
        public static final Property Year = new Property(2, Integer.class, "year", false, "YEAR");
        public static final Property Month = new Property(3, Integer.class, "month", false, "MONTH");
        public static final Property Day = new Property(4, Integer.class, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
        public static final Property Hour = new Property(5, Integer.class, WeatherCriteria.UNIT_TYPE_HOUR, false, "HOUR");
        public static final Property Minute = new Property(6, Integer.class, "minute", false, "MINUTE");
        public static final Property Second = new Property(7, Integer.class, "second", false, "SECOND");
        public static final Property Data_interval = new Property(8, Integer.class, "data_interval", false, "DATA_INTERVAL");
        public static final Property Date = new Property(9, Long.class, "date", false, "DATE");
    }

    public HealthGpsDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthGpsDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_GPS\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER,\"MONTH\" INTEGER,\"DAY\" INTEGER,\"HOUR\" INTEGER,\"MINUTE\" INTEGER,\"SECOND\" INTEGER,\"DATA_INTERVAL\" INTEGER,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_GPS\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthGps healthGps) {
        sQLiteStatement.clearBindings();
        Long healthGpsId = healthGps.getHealthGpsId();
        if (healthGpsId != null) {
            sQLiteStatement.bindLong(1, healthGpsId.longValue());
        }
        sQLiteStatement.bindLong(2, healthGps.getDId());
        Integer year = healthGps.getYear();
        if (year != null) {
            sQLiteStatement.bindLong(3, year.intValue());
        }
        Integer month = healthGps.getMonth();
        if (month != null) {
            sQLiteStatement.bindLong(4, month.intValue());
        }
        Integer day = healthGps.getDay();
        if (day != null) {
            sQLiteStatement.bindLong(5, day.intValue());
        }
        Integer hour = healthGps.getHour();
        if (hour != null) {
            sQLiteStatement.bindLong(6, hour.intValue());
        }
        Integer minute = healthGps.getMinute();
        if (minute != null) {
            sQLiteStatement.bindLong(7, minute.intValue());
        }
        Integer second = healthGps.getSecond();
        if (second != null) {
            sQLiteStatement.bindLong(8, second.intValue());
        }
        Integer data_interval = healthGps.getData_interval();
        if (data_interval != null) {
            sQLiteStatement.bindLong(9, data_interval.intValue());
        }
        Long date = healthGps.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(10, date.longValue());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthGps healthGps) {
        databaseStatement.clearBindings();
        Long healthGpsId = healthGps.getHealthGpsId();
        if (healthGpsId != null) {
            databaseStatement.bindLong(1, healthGpsId.longValue());
        }
        databaseStatement.bindLong(2, healthGps.getDId());
        Integer year = healthGps.getYear();
        if (year != null) {
            databaseStatement.bindLong(3, year.intValue());
        }
        Integer month = healthGps.getMonth();
        if (month != null) {
            databaseStatement.bindLong(4, month.intValue());
        }
        Integer day = healthGps.getDay();
        if (day != null) {
            databaseStatement.bindLong(5, day.intValue());
        }
        Integer hour = healthGps.getHour();
        if (hour != null) {
            databaseStatement.bindLong(6, hour.intValue());
        }
        Integer minute = healthGps.getMinute();
        if (minute != null) {
            databaseStatement.bindLong(7, minute.intValue());
        }
        Integer second = healthGps.getSecond();
        if (second != null) {
            databaseStatement.bindLong(8, second.intValue());
        }
        Integer data_interval = healthGps.getData_interval();
        if (data_interval != null) {
            databaseStatement.bindLong(9, data_interval.intValue());
        }
        Long date = healthGps.getDate();
        if (date != null) {
            databaseStatement.bindLong(10, date.longValue());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthGps healthGps) {
        if (healthGps != null) {
            return healthGps.getHealthGpsId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthGps healthGps) {
        return healthGps.getHealthGpsId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthGps readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        Integer valueOf2 = cursor.isNull(i3) ? null : Integer.valueOf(cursor.getInt(i3));
        int i4 = i + 3;
        Integer valueOf3 = cursor.isNull(i4) ? null : Integer.valueOf(cursor.getInt(i4));
        int i5 = i + 4;
        Integer valueOf4 = cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5));
        int i6 = i + 5;
        Integer valueOf5 = cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6));
        int i7 = i + 6;
        Integer valueOf6 = cursor.isNull(i7) ? null : Integer.valueOf(cursor.getInt(i7));
        int i8 = i + 7;
        Integer valueOf7 = cursor.isNull(i8) ? null : Integer.valueOf(cursor.getInt(i8));
        int i9 = i + 8;
        int i10 = i + 9;
        return new HealthGps(valueOf, j, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, cursor.isNull(i9) ? null : Integer.valueOf(cursor.getInt(i9)), cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthGps healthGps, int i) {
        int i2 = i + 0;
        healthGps.setHealthGpsId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthGps.setDId(cursor.getLong(i + 1));
        int i3 = i + 2;
        healthGps.setYear(cursor.isNull(i3) ? null : Integer.valueOf(cursor.getInt(i3)));
        int i4 = i + 3;
        healthGps.setMonth(cursor.isNull(i4) ? null : Integer.valueOf(cursor.getInt(i4)));
        int i5 = i + 4;
        healthGps.setDay(cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5)));
        int i6 = i + 5;
        healthGps.setHour(cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)));
        int i7 = i + 6;
        healthGps.setMinute(cursor.isNull(i7) ? null : Integer.valueOf(cursor.getInt(i7)));
        int i8 = i + 7;
        healthGps.setSecond(cursor.isNull(i8) ? null : Integer.valueOf(cursor.getInt(i8)));
        int i9 = i + 8;
        healthGps.setData_interval(cursor.isNull(i9) ? null : Integer.valueOf(cursor.getInt(i9)));
        int i10 = i + 9;
        healthGps.setDate(cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
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
    public final Long updateKeyAfterInsert(HealthGps healthGps, long j) {
        healthGps.setHealthGpsId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
