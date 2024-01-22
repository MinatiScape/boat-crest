package com.ido.ble.data.manage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ido.ble.event.stat.one.faildata.FailLogInfoDao;
import com.ido.ble.gps.database.HealthGpsDao;
import com.ido.ble.gps.database.HealthGpsItemDao;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
/* loaded from: classes11.dex */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 7;

    /* loaded from: classes11.dex */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 7);
        }

        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 7);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 7");
            DaoMaster.createAllTables(database, false);
        }
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this(new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 7);
        registerDaoClass(HealthActivityDao.class);
        registerDaoClass(HealthBloodPressedDao.class);
        registerDaoClass(HealthBloodPressedItemDao.class);
        registerDaoClass(HealthHeartRateDao.class);
        registerDaoClass(HealthHeartRateItemDao.class);
        registerDaoClass(HealthHeartRateSecondDao.class);
        registerDaoClass(HealthPressureDao.class);
        registerDaoClass(HealthPressureItemDao.class);
        registerDaoClass(HealthSleepDao.class);
        registerDaoClass(HealthSleepItemDao.class);
        registerDaoClass(HealthSpO2Dao.class);
        registerDaoClass(HealthSpO2ItemDao.class);
        registerDaoClass(HealthSportDao.class);
        registerDaoClass(HealthSportItemDao.class);
        registerDaoClass(HealthSwimmingDao.class);
        registerDaoClass(FailLogInfoDao.class);
        registerDaoClass(HealthGpsDao.class);
        registerDaoClass(HealthGpsItemDao.class);
    }

    public static void createAllTables(Database database, boolean z) {
        HealthActivityDao.createTable(database, z);
        HealthBloodPressedDao.createTable(database, z);
        HealthBloodPressedItemDao.createTable(database, z);
        HealthHeartRateDao.createTable(database, z);
        HealthHeartRateItemDao.createTable(database, z);
        HealthHeartRateSecondDao.createTable(database, z);
        HealthPressureDao.createTable(database, z);
        HealthPressureItemDao.createTable(database, z);
        HealthSleepDao.createTable(database, z);
        HealthSleepItemDao.createTable(database, z);
        HealthSpO2Dao.createTable(database, z);
        HealthSpO2ItemDao.createTable(database, z);
        HealthSportDao.createTable(database, z);
        HealthSportItemDao.createTable(database, z);
        HealthSwimmingDao.createTable(database, z);
        FailLogInfoDao.a(database, z);
        HealthGpsDao.createTable(database, z);
        HealthGpsItemDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        HealthActivityDao.dropTable(database, z);
        HealthBloodPressedDao.dropTable(database, z);
        HealthBloodPressedItemDao.dropTable(database, z);
        HealthHeartRateDao.dropTable(database, z);
        HealthHeartRateItemDao.dropTable(database, z);
        HealthHeartRateSecondDao.dropTable(database, z);
        HealthPressureDao.dropTable(database, z);
        HealthPressureItemDao.dropTable(database, z);
        HealthSleepDao.dropTable(database, z);
        HealthSleepItemDao.dropTable(database, z);
        HealthSpO2Dao.dropTable(database, z);
        HealthSpO2ItemDao.dropTable(database, z);
        HealthSportDao.dropTable(database, z);
        HealthSportItemDao.dropTable(database, z);
        HealthSwimmingDao.dropTable(database, z);
        FailLogInfoDao.b(database, z);
        HealthGpsDao.dropTable(database, z);
        HealthGpsItemDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
