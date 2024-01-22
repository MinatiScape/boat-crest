package com.ido.ble.data.manage.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
public class HealthPressureItemDao extends AbstractDao<HealthPressureItem, Long> {
    public static final String TABLENAME = "HEALTH_PRESSURE_ITEM";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property Date;
        public static final Property Day;
        public static final Property Month;
        public static final Property Offset;
        public static final Property Value;
        public static final Property Year;
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            Offset = new Property(5, cls, TypedValues.CycleType.S_WAVE_OFFSET, false, "OFFSET");
            Value = new Property(6, cls, "value", false, "VALUE");
            Date = new Property(7, Date.class, "date", false, "DATE");
        }
    }

    public HealthPressureItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthPressureItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_PRESSURE_ITEM\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"OFFSET\" INTEGER NOT NULL ,\"VALUE\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_PRESSURE_ITEM\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthPressureItem healthPressureItem) {
        sQLiteStatement.clearBindings();
        Long id = healthPressureItem.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, healthPressureItem.getDId());
        sQLiteStatement.bindLong(3, healthPressureItem.getYear());
        sQLiteStatement.bindLong(4, healthPressureItem.getMonth());
        sQLiteStatement.bindLong(5, healthPressureItem.getDay());
        sQLiteStatement.bindLong(6, healthPressureItem.getOffset());
        sQLiteStatement.bindLong(7, healthPressureItem.getValue());
        Date date = healthPressureItem.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(8, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthPressureItem healthPressureItem) {
        databaseStatement.clearBindings();
        Long id = healthPressureItem.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, healthPressureItem.getDId());
        databaseStatement.bindLong(3, healthPressureItem.getYear());
        databaseStatement.bindLong(4, healthPressureItem.getMonth());
        databaseStatement.bindLong(5, healthPressureItem.getDay());
        databaseStatement.bindLong(6, healthPressureItem.getOffset());
        databaseStatement.bindLong(7, healthPressureItem.getValue());
        Date date = healthPressureItem.getDate();
        if (date != null) {
            databaseStatement.bindLong(8, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthPressureItem healthPressureItem) {
        if (healthPressureItem != null) {
            return healthPressureItem.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthPressureItem healthPressureItem) {
        return healthPressureItem.getId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthPressureItem readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = i + 7;
        return new HealthPressureItem(valueOf, j, i3, i4, i5, i6, i7, cursor.isNull(i8) ? null : new Date(cursor.getLong(i8)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthPressureItem healthPressureItem, int i) {
        int i2 = i + 0;
        healthPressureItem.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthPressureItem.setDId(cursor.getLong(i + 1));
        healthPressureItem.setYear(cursor.getInt(i + 2));
        healthPressureItem.setMonth(cursor.getInt(i + 3));
        healthPressureItem.setDay(cursor.getInt(i + 4));
        healthPressureItem.setOffset(cursor.getInt(i + 5));
        healthPressureItem.setValue(cursor.getInt(i + 6));
        int i3 = i + 7;
        healthPressureItem.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthPressureItem healthPressureItem, long j) {
        healthPressureItem.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
