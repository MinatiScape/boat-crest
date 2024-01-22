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
public class HealthBloodPressedDao extends AbstractDao<HealthBloodPressed, Long> {
    public static final String TABLENAME = "HEALTH_BLOOD_PRESSED";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property BloodPressedId = new Property(0, Long.class, "bloodPressedId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");
        public static final Property Date;
        public static final Property Day;
        public static final Property Max_bp;
        public static final Property Minute_offset;
        public static final Property Month;
        public static final Property Sleep_avg_bp;
        public static final Property Year;

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            Max_bp = new Property(5, cls, "max_bp", false, "MAX_BP");
            Minute_offset = new Property(6, cls, "minute_offset", false, "MINUTE_OFFSET");
            Sleep_avg_bp = new Property(7, cls, "sleep_avg_bp", false, "SLEEP_AVG_BP");
            Date = new Property(8, Date.class, "date", false, "DATE");
        }
    }

    public HealthBloodPressedDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthBloodPressedDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_BLOOD_PRESSED\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"MAX_BP\" INTEGER NOT NULL ,\"MINUTE_OFFSET\" INTEGER NOT NULL ,\"SLEEP_AVG_BP\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_BLOOD_PRESSED\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthBloodPressed healthBloodPressed) {
        sQLiteStatement.clearBindings();
        Long bloodPressedId = healthBloodPressed.getBloodPressedId();
        if (bloodPressedId != null) {
            sQLiteStatement.bindLong(1, bloodPressedId.longValue());
        }
        sQLiteStatement.bindLong(2, healthBloodPressed.getDId());
        sQLiteStatement.bindLong(3, healthBloodPressed.getYear());
        sQLiteStatement.bindLong(4, healthBloodPressed.getMonth());
        sQLiteStatement.bindLong(5, healthBloodPressed.getDay());
        sQLiteStatement.bindLong(6, healthBloodPressed.getMax_bp());
        sQLiteStatement.bindLong(7, healthBloodPressed.getMinute_offset());
        sQLiteStatement.bindLong(8, healthBloodPressed.getSleep_avg_bp());
        Date date = healthBloodPressed.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(9, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthBloodPressed healthBloodPressed) {
        databaseStatement.clearBindings();
        Long bloodPressedId = healthBloodPressed.getBloodPressedId();
        if (bloodPressedId != null) {
            databaseStatement.bindLong(1, bloodPressedId.longValue());
        }
        databaseStatement.bindLong(2, healthBloodPressed.getDId());
        databaseStatement.bindLong(3, healthBloodPressed.getYear());
        databaseStatement.bindLong(4, healthBloodPressed.getMonth());
        databaseStatement.bindLong(5, healthBloodPressed.getDay());
        databaseStatement.bindLong(6, healthBloodPressed.getMax_bp());
        databaseStatement.bindLong(7, healthBloodPressed.getMinute_offset());
        databaseStatement.bindLong(8, healthBloodPressed.getSleep_avg_bp());
        Date date = healthBloodPressed.getDate();
        if (date != null) {
            databaseStatement.bindLong(9, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthBloodPressed healthBloodPressed) {
        if (healthBloodPressed != null) {
            return healthBloodPressed.getBloodPressedId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthBloodPressed healthBloodPressed) {
        return healthBloodPressed.getBloodPressedId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthBloodPressed readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = cursor.getInt(i + 7);
        int i9 = i + 8;
        return new HealthBloodPressed(valueOf, j, i3, i4, i5, i6, i7, i8, cursor.isNull(i9) ? null : new Date(cursor.getLong(i9)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthBloodPressed healthBloodPressed, int i) {
        int i2 = i + 0;
        healthBloodPressed.setBloodPressedId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthBloodPressed.setDId(cursor.getLong(i + 1));
        healthBloodPressed.setYear(cursor.getInt(i + 2));
        healthBloodPressed.setMonth(cursor.getInt(i + 3));
        healthBloodPressed.setDay(cursor.getInt(i + 4));
        healthBloodPressed.setMax_bp(cursor.getInt(i + 5));
        healthBloodPressed.setMinute_offset(cursor.getInt(i + 6));
        healthBloodPressed.setSleep_avg_bp(cursor.getInt(i + 7));
        int i3 = i + 8;
        healthBloodPressed.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthBloodPressed healthBloodPressed, long j) {
        healthBloodPressed.setBloodPressedId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
