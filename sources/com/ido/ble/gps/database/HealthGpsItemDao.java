package com.ido.ble.gps.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.ble.data.manage.database.DaoSession;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
public class HealthGpsItemDao extends AbstractDao<HealthGpsItem, Long> {
    public static final String TABLENAME = "HEALTH_GPS_ITEM";

    /* loaded from: classes11.dex */
    public static class Properties {
        public static final Property HealthGpsItemId = new Property(0, Long.class, "healthGpsItemId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");
        public static final Property Longitude = new Property(2, Double.class, "longitude", false, "LONGITUDE");
        public static final Property Latitude = new Property(3, Double.class, "latitude", false, "LATITUDE");
        public static final Property Date = new Property(4, Long.class, "date", false, "DATE");
    }

    public HealthGpsItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthGpsItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HEALTH_GPS_ITEM\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"LONGITUDE\" REAL,\"LATITUDE\" REAL,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_GPS_ITEM\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthGpsItem healthGpsItem) {
        sQLiteStatement.clearBindings();
        Long healthGpsItemId = healthGpsItem.getHealthGpsItemId();
        if (healthGpsItemId != null) {
            sQLiteStatement.bindLong(1, healthGpsItemId.longValue());
        }
        sQLiteStatement.bindLong(2, healthGpsItem.getDId());
        Double longitude = healthGpsItem.getLongitude();
        if (longitude != null) {
            sQLiteStatement.bindDouble(3, longitude.doubleValue());
        }
        Double latitude = healthGpsItem.getLatitude();
        if (latitude != null) {
            sQLiteStatement.bindDouble(4, latitude.doubleValue());
        }
        Long date = healthGpsItem.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(5, date.longValue());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthGpsItem healthGpsItem) {
        databaseStatement.clearBindings();
        Long healthGpsItemId = healthGpsItem.getHealthGpsItemId();
        if (healthGpsItemId != null) {
            databaseStatement.bindLong(1, healthGpsItemId.longValue());
        }
        databaseStatement.bindLong(2, healthGpsItem.getDId());
        Double longitude = healthGpsItem.getLongitude();
        if (longitude != null) {
            databaseStatement.bindDouble(3, longitude.doubleValue());
        }
        Double latitude = healthGpsItem.getLatitude();
        if (latitude != null) {
            databaseStatement.bindDouble(4, latitude.doubleValue());
        }
        Long date = healthGpsItem.getDate();
        if (date != null) {
            databaseStatement.bindLong(5, date.longValue());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthGpsItem healthGpsItem) {
        if (healthGpsItem != null) {
            return healthGpsItem.getHealthGpsItemId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthGpsItem healthGpsItem) {
        return healthGpsItem.getHealthGpsItemId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthGpsItem readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        Double valueOf2 = cursor.isNull(i3) ? null : Double.valueOf(cursor.getDouble(i3));
        int i4 = i + 3;
        int i5 = i + 4;
        return new HealthGpsItem(valueOf, j, valueOf2, cursor.isNull(i4) ? null : Double.valueOf(cursor.getDouble(i4)), cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthGpsItem healthGpsItem, int i) {
        int i2 = i + 0;
        healthGpsItem.setHealthGpsItemId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthGpsItem.setDId(cursor.getLong(i + 1));
        int i3 = i + 2;
        healthGpsItem.setLongitude(cursor.isNull(i3) ? null : Double.valueOf(cursor.getDouble(i3)));
        int i4 = i + 3;
        healthGpsItem.setLatitude(cursor.isNull(i4) ? null : Double.valueOf(cursor.getDouble(i4)));
        int i5 = i + 4;
        healthGpsItem.setDate(cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)));
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
    public final Long updateKeyAfterInsert(HealthGpsItem healthGpsItem, long j) {
        healthGpsItem.setHealthGpsItemId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
