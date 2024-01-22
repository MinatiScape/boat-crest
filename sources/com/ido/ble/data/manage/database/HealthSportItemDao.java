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
public class HealthSportItemDao extends AbstractDao<HealthSportItem, Long> {
    public static final String TABLENAME = "HEALTH_SPORT_ITEM";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property ActiveTime;
        public static final Property Calory;
        public static final Property Date;
        public static final Property Day;
        public static final Property Distance;
        public static final Property Month;
        public static final Property StepCount;
        public static final Property Year;
        public static final Property SportDataId = new Property(0, Long.class, "sportDataId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            StepCount = new Property(5, cls, "stepCount", false, "STEP_COUNT");
            ActiveTime = new Property(6, cls, "activeTime", false, "ACTIVE_TIME");
            Calory = new Property(7, cls, "calory", false, "CALORY");
            Distance = new Property(8, cls, "distance", false, "DISTANCE");
            Date = new Property(9, Date.class, "date", false, "DATE");
        }
    }

    public HealthSportItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthSportItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_SPORT_ITEM\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"STEP_COUNT\" INTEGER NOT NULL ,\"ACTIVE_TIME\" INTEGER NOT NULL ,\"CALORY\" INTEGER NOT NULL ,\"DISTANCE\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_SPORT_ITEM\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthSportItem healthSportItem) {
        sQLiteStatement.clearBindings();
        Long sportDataId = healthSportItem.getSportDataId();
        if (sportDataId != null) {
            sQLiteStatement.bindLong(1, sportDataId.longValue());
        }
        sQLiteStatement.bindLong(2, healthSportItem.getDId());
        sQLiteStatement.bindLong(3, healthSportItem.getYear());
        sQLiteStatement.bindLong(4, healthSportItem.getMonth());
        sQLiteStatement.bindLong(5, healthSportItem.getDay());
        sQLiteStatement.bindLong(6, healthSportItem.getStepCount());
        sQLiteStatement.bindLong(7, healthSportItem.getActiveTime());
        sQLiteStatement.bindLong(8, healthSportItem.getCalory());
        sQLiteStatement.bindLong(9, healthSportItem.getDistance());
        Date date = healthSportItem.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(10, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthSportItem healthSportItem) {
        databaseStatement.clearBindings();
        Long sportDataId = healthSportItem.getSportDataId();
        if (sportDataId != null) {
            databaseStatement.bindLong(1, sportDataId.longValue());
        }
        databaseStatement.bindLong(2, healthSportItem.getDId());
        databaseStatement.bindLong(3, healthSportItem.getYear());
        databaseStatement.bindLong(4, healthSportItem.getMonth());
        databaseStatement.bindLong(5, healthSportItem.getDay());
        databaseStatement.bindLong(6, healthSportItem.getStepCount());
        databaseStatement.bindLong(7, healthSportItem.getActiveTime());
        databaseStatement.bindLong(8, healthSportItem.getCalory());
        databaseStatement.bindLong(9, healthSportItem.getDistance());
        Date date = healthSportItem.getDate();
        if (date != null) {
            databaseStatement.bindLong(10, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthSportItem healthSportItem) {
        if (healthSportItem != null) {
            return healthSportItem.getSportDataId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthSportItem healthSportItem) {
        return healthSportItem.getSportDataId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthSportItem readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = cursor.getInt(i + 7);
        int i9 = cursor.getInt(i + 8);
        int i10 = i + 9;
        return new HealthSportItem(valueOf, j, i3, i4, i5, i6, i7, i8, i9, cursor.isNull(i10) ? null : new Date(cursor.getLong(i10)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthSportItem healthSportItem, int i) {
        int i2 = i + 0;
        healthSportItem.setSportDataId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthSportItem.setDId(cursor.getLong(i + 1));
        healthSportItem.setYear(cursor.getInt(i + 2));
        healthSportItem.setMonth(cursor.getInt(i + 3));
        healthSportItem.setDay(cursor.getInt(i + 4));
        healthSportItem.setStepCount(cursor.getInt(i + 5));
        healthSportItem.setActiveTime(cursor.getInt(i + 6));
        healthSportItem.setCalory(cursor.getInt(i + 7));
        healthSportItem.setDistance(cursor.getInt(i + 8));
        int i3 = i + 9;
        healthSportItem.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthSportItem healthSportItem, long j) {
        healthSportItem.setSportDataId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
