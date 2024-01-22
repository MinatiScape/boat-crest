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
public class HealthSleepItemDao extends AbstractDao<HealthSleepItem, Long> {
    public static final String TABLENAME = "HEALTH_SLEEP_ITEM";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property Date;
        public static final Property Day;
        public static final Property Month;
        public static final Property OffsetMinute;
        public static final Property SleepStatus;
        public static final Property Year;
        public static final Property SleepDataId = new Property(0, Long.class, "sleepDataId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            OffsetMinute = new Property(5, cls, "offsetMinute", false, "OFFSET_MINUTE");
            SleepStatus = new Property(6, cls, "sleepStatus", false, "SLEEP_STATUS");
            Date = new Property(7, Date.class, "date", false, "DATE");
        }
    }

    public HealthSleepItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthSleepItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_SLEEP_ITEM\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"OFFSET_MINUTE\" INTEGER NOT NULL ,\"SLEEP_STATUS\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_SLEEP_ITEM\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthSleepItem healthSleepItem) {
        sQLiteStatement.clearBindings();
        Long sleepDataId = healthSleepItem.getSleepDataId();
        if (sleepDataId != null) {
            sQLiteStatement.bindLong(1, sleepDataId.longValue());
        }
        sQLiteStatement.bindLong(2, healthSleepItem.getDId());
        sQLiteStatement.bindLong(3, healthSleepItem.getYear());
        sQLiteStatement.bindLong(4, healthSleepItem.getMonth());
        sQLiteStatement.bindLong(5, healthSleepItem.getDay());
        sQLiteStatement.bindLong(6, healthSleepItem.getOffsetMinute());
        sQLiteStatement.bindLong(7, healthSleepItem.getSleepStatus());
        Date date = healthSleepItem.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(8, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthSleepItem healthSleepItem) {
        databaseStatement.clearBindings();
        Long sleepDataId = healthSleepItem.getSleepDataId();
        if (sleepDataId != null) {
            databaseStatement.bindLong(1, sleepDataId.longValue());
        }
        databaseStatement.bindLong(2, healthSleepItem.getDId());
        databaseStatement.bindLong(3, healthSleepItem.getYear());
        databaseStatement.bindLong(4, healthSleepItem.getMonth());
        databaseStatement.bindLong(5, healthSleepItem.getDay());
        databaseStatement.bindLong(6, healthSleepItem.getOffsetMinute());
        databaseStatement.bindLong(7, healthSleepItem.getSleepStatus());
        Date date = healthSleepItem.getDate();
        if (date != null) {
            databaseStatement.bindLong(8, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthSleepItem healthSleepItem) {
        if (healthSleepItem != null) {
            return healthSleepItem.getSleepDataId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthSleepItem healthSleepItem) {
        return healthSleepItem.getSleepDataId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthSleepItem readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = i + 7;
        return new HealthSleepItem(valueOf, j, i3, i4, i5, i6, i7, cursor.isNull(i8) ? null : new Date(cursor.getLong(i8)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthSleepItem healthSleepItem, int i) {
        int i2 = i + 0;
        healthSleepItem.setSleepDataId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthSleepItem.setDId(cursor.getLong(i + 1));
        healthSleepItem.setYear(cursor.getInt(i + 2));
        healthSleepItem.setMonth(cursor.getInt(i + 3));
        healthSleepItem.setDay(cursor.getInt(i + 4));
        healthSleepItem.setOffsetMinute(cursor.getInt(i + 5));
        healthSleepItem.setSleepStatus(cursor.getInt(i + 6));
        int i3 = i + 7;
        healthSleepItem.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthSleepItem healthSleepItem, long j) {
        healthSleepItem.setSleepDataId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}