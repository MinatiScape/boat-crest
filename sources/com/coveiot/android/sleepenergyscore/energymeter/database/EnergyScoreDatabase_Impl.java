package com.coveiot.android.sleepenergyscore.energymeter.database;

import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao;
import com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao_Impl;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes6.dex */
public final class EnergyScoreDatabase_Impl extends EnergyScoreDatabase {
    public volatile EnergyScoreDao e;

    /* loaded from: classes6.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `energy_score_table` (`mDate` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `data` TEXT, `feedbackList` TEXT, `message` TEXT, `questionnaireId` TEXT, `lastSyncedDate` INTEGER NOT NULL, `status` TEXT, `isSyncedWithServer` INTEGER NOT NULL, PRIMARY KEY(`macAddress`, `mDate`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b9baae59eaf92ee739f7de9b3ffa3146')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `energy_score_table`");
            if (EnergyScoreDatabase_Impl.this.mCallbacks != null) {
                int size = EnergyScoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) EnergyScoreDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (EnergyScoreDatabase_Impl.this.mCallbacks != null) {
                int size = EnergyScoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) EnergyScoreDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            EnergyScoreDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            EnergyScoreDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (EnergyScoreDatabase_Impl.this.mCallbacks != null) {
                int size = EnergyScoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) EnergyScoreDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(9);
            hashMap.put("mDate", new TableInfo.Column("mDate", "TEXT", true, 2, null, 1));
            hashMap.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 1, null, 1));
            hashMap.put("data", new TableInfo.Column("data", "TEXT", false, 0, null, 1));
            hashMap.put("feedbackList", new TableInfo.Column("feedbackList", "TEXT", false, 0, null, 1));
            hashMap.put(Constants.KEY_MESSAGE, new TableInfo.Column(Constants.KEY_MESSAGE, "TEXT", false, 0, null, 1));
            hashMap.put("questionnaireId", new TableInfo.Column("questionnaireId", "TEXT", false, 0, null, 1));
            hashMap.put("lastSyncedDate", new TableInfo.Column("lastSyncedDate", "INTEGER", true, 0, null, 1));
            hashMap.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "TEXT", false, 0, null, 1));
            hashMap.put("isSyncedWithServer", new TableInfo.Column("isSyncedWithServer", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("energy_score_table", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "energy_score_table");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "energy_score_table(com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            return new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `energy_score_table`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "energy_score_table");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(4), "b9baae59eaf92ee739f7de9b3ffa3146", "56d8d2fc9bb4e085b4658d135eb7226f")).build());
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreDatabase
    public EnergyScoreDao energyScoreDao() {
        EnergyScoreDao energyScoreDao;
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = new EnergyScoreDao_Impl(this);
            }
            energyScoreDao = this.e;
        }
        return energyScoreDao;
    }
}
