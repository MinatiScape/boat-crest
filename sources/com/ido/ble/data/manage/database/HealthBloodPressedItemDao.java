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
public class HealthBloodPressedItemDao extends AbstractDao<HealthBloodPressedItem, Long> {
    public static final String TABLENAME = "HEALTH_BLOOD_PRESSED_ITEM";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property BloodPressedItemId = new Property(0, Long.class, "bloodPressedItemId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");
        public static final Property Date;
        public static final Property Day;
        public static final Property Dias_blood;
        public static final Property Month;
        public static final Property Offset;
        public static final Property Sys_blood;
        public static final Property Year;

        static {
            Class cls = Integer.TYPE;
            Year = new Property(2, cls, "year", false, "YEAR");
            Month = new Property(3, cls, "month", false, "MONTH");
            Day = new Property(4, cls, WeatherCriteria.UNIT_TYPE_DAY, false, "DAY");
            Offset = new Property(5, cls, TypedValues.CycleType.S_WAVE_OFFSET, false, "OFFSET");
            Dias_blood = new Property(6, cls, "dias_blood", false, "DIAS_BLOOD");
            Sys_blood = new Property(7, cls, "sys_blood", false, "SYS_BLOOD");
            Date = new Property(8, Date.class, "date", false, "DATE");
        }
    }

    public HealthBloodPressedItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthBloodPressedItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_BLOOD_PRESSED_ITEM\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"OFFSET\" INTEGER NOT NULL ,\"DIAS_BLOOD\" INTEGER NOT NULL ,\"SYS_BLOOD\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_BLOOD_PRESSED_ITEM\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthBloodPressedItem healthBloodPressedItem) {
        sQLiteStatement.clearBindings();
        Long bloodPressedItemId = healthBloodPressedItem.getBloodPressedItemId();
        if (bloodPressedItemId != null) {
            sQLiteStatement.bindLong(1, bloodPressedItemId.longValue());
        }
        sQLiteStatement.bindLong(2, healthBloodPressedItem.getDId());
        sQLiteStatement.bindLong(3, healthBloodPressedItem.getYear());
        sQLiteStatement.bindLong(4, healthBloodPressedItem.getMonth());
        sQLiteStatement.bindLong(5, healthBloodPressedItem.getDay());
        sQLiteStatement.bindLong(6, healthBloodPressedItem.getOffset());
        sQLiteStatement.bindLong(7, healthBloodPressedItem.getDias_blood());
        sQLiteStatement.bindLong(8, healthBloodPressedItem.getSys_blood());
        Date date = healthBloodPressedItem.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(9, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthBloodPressedItem healthBloodPressedItem) {
        databaseStatement.clearBindings();
        Long bloodPressedItemId = healthBloodPressedItem.getBloodPressedItemId();
        if (bloodPressedItemId != null) {
            databaseStatement.bindLong(1, bloodPressedItemId.longValue());
        }
        databaseStatement.bindLong(2, healthBloodPressedItem.getDId());
        databaseStatement.bindLong(3, healthBloodPressedItem.getYear());
        databaseStatement.bindLong(4, healthBloodPressedItem.getMonth());
        databaseStatement.bindLong(5, healthBloodPressedItem.getDay());
        databaseStatement.bindLong(6, healthBloodPressedItem.getOffset());
        databaseStatement.bindLong(7, healthBloodPressedItem.getDias_blood());
        databaseStatement.bindLong(8, healthBloodPressedItem.getSys_blood());
        Date date = healthBloodPressedItem.getDate();
        if (date != null) {
            databaseStatement.bindLong(9, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthBloodPressedItem healthBloodPressedItem) {
        if (healthBloodPressedItem != null) {
            return healthBloodPressedItem.getBloodPressedItemId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthBloodPressedItem healthBloodPressedItem) {
        return healthBloodPressedItem.getBloodPressedItemId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthBloodPressedItem readEntity(Cursor cursor, int i) {
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
        return new HealthBloodPressedItem(valueOf, j, i3, i4, i5, i6, i7, i8, cursor.isNull(i9) ? null : new Date(cursor.getLong(i9)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthBloodPressedItem healthBloodPressedItem, int i) {
        int i2 = i + 0;
        healthBloodPressedItem.setBloodPressedItemId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthBloodPressedItem.setDId(cursor.getLong(i + 1));
        healthBloodPressedItem.setYear(cursor.getInt(i + 2));
        healthBloodPressedItem.setMonth(cursor.getInt(i + 3));
        healthBloodPressedItem.setDay(cursor.getInt(i + 4));
        healthBloodPressedItem.setOffset(cursor.getInt(i + 5));
        healthBloodPressedItem.setDias_blood(cursor.getInt(i + 6));
        healthBloodPressedItem.setSys_blood(cursor.getInt(i + 7));
        int i3 = i + 8;
        healthBloodPressedItem.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthBloodPressedItem healthBloodPressedItem, long j) {
        healthBloodPressedItem.setBloodPressedItemId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
