package com.coveiot.android.activitymodes.activity1k.db;

import android.os.Build;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao;
import com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao_Impl;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes2.dex */
public final class PhysicalActivityDatabase_Impl extends PhysicalActivityDatabase {
    public volatile PhysicalActivitiesDao d;

    /* loaded from: classes2.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_activity_category` (`categoryId` INTEGER, `title` TEXT, `description` TEXT, `iconUrl` TEXT, `deviceIconModels` TEXT, PRIMARY KEY(`categoryId`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_physical_activities` (`categoryId` INTEGER NOT NULL, `activityId` INTEGER NOT NULL, `fwActId` INTEGER NOT NULL, `cal_func` TEXT, `inbuilt` INTEGER NOT NULL, `activityCode` TEXT, `cpaCode` TEXT, `shortTitle` TEXT, `titleInMetric` TEXT, `titleInImperial` TEXT, `dvcTitleInMetric` TEXT, `dvcTitleInImperial` TEXT, `descInMetric` TEXT, `descInImperial` TEXT, `defaultMets` REAL, `metrics` TEXT, `defaultActivityName` TEXT, `defaultCategoryIcon` INTEGER, `defaultActivityIcon` INTEGER, `iconUrl` TEXT, `deviceIconModels` TEXT, PRIMARY KEY(`categoryId`, `activityId`), FOREIGN KEY(`categoryId`) REFERENCES `entity_activity_category`(`categoryId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9665fa0a00f004210cb67d0e416b254b')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_activity_category`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_physical_activities`");
            if (PhysicalActivityDatabase_Impl.this.mCallbacks != null) {
                int size = PhysicalActivityDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) PhysicalActivityDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (PhysicalActivityDatabase_Impl.this.mCallbacks != null) {
                int size = PhysicalActivityDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) PhysicalActivityDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            PhysicalActivityDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            supportSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
            PhysicalActivityDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (PhysicalActivityDatabase_Impl.this.mCallbacks != null) {
                int size = PhysicalActivityDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) PhysicalActivityDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(5);
            hashMap.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", false, 1, null, 1));
            hashMap.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, 1));
            hashMap.put(SavingTrackHelper.POINT_COL_DESCRIPTION, new TableInfo.Column(SavingTrackHelper.POINT_COL_DESCRIPTION, "TEXT", false, 0, null, 1));
            hashMap.put("iconUrl", new TableInfo.Column("iconUrl", "TEXT", false, 0, null, 1));
            hashMap.put("deviceIconModels", new TableInfo.Column("deviceIconModels", "TEXT", false, 0, null, 1));
            TableInfo tableInfo = new TableInfo("entity_activity_category", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "entity_activity_category");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_activity_category(com.coveiot.android.activitymodes.activity1k.db.entity.EntityActivityCategory).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(21);
            hashMap2.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", true, 1, null, 1));
            hashMap2.put("activityId", new TableInfo.Column("activityId", "INTEGER", true, 2, null, 1));
            hashMap2.put("fwActId", new TableInfo.Column("fwActId", "INTEGER", true, 0, null, 1));
            hashMap2.put("cal_func", new TableInfo.Column("cal_func", "TEXT", false, 0, null, 1));
            hashMap2.put("inbuilt", new TableInfo.Column("inbuilt", "INTEGER", true, 0, null, 1));
            hashMap2.put("activityCode", new TableInfo.Column("activityCode", "TEXT", false, 0, null, 1));
            hashMap2.put("cpaCode", new TableInfo.Column("cpaCode", "TEXT", false, 0, null, 1));
            hashMap2.put("shortTitle", new TableInfo.Column("shortTitle", "TEXT", false, 0, null, 1));
            hashMap2.put("titleInMetric", new TableInfo.Column("titleInMetric", "TEXT", false, 0, null, 1));
            hashMap2.put("titleInImperial", new TableInfo.Column("titleInImperial", "TEXT", false, 0, null, 1));
            hashMap2.put("dvcTitleInMetric", new TableInfo.Column("dvcTitleInMetric", "TEXT", false, 0, null, 1));
            hashMap2.put("dvcTitleInImperial", new TableInfo.Column("dvcTitleInImperial", "TEXT", false, 0, null, 1));
            hashMap2.put("descInMetric", new TableInfo.Column("descInMetric", "TEXT", false, 0, null, 1));
            hashMap2.put("descInImperial", new TableInfo.Column("descInImperial", "TEXT", false, 0, null, 1));
            hashMap2.put("defaultMets", new TableInfo.Column("defaultMets", "REAL", false, 0, null, 1));
            hashMap2.put("metrics", new TableInfo.Column("metrics", "TEXT", false, 0, null, 1));
            hashMap2.put("defaultActivityName", new TableInfo.Column("defaultActivityName", "TEXT", false, 0, null, 1));
            hashMap2.put("defaultCategoryIcon", new TableInfo.Column("defaultCategoryIcon", "INTEGER", false, 0, null, 1));
            hashMap2.put("defaultActivityIcon", new TableInfo.Column("defaultActivityIcon", "INTEGER", false, 0, null, 1));
            hashMap2.put("iconUrl", new TableInfo.Column("iconUrl", "TEXT", false, 0, null, 1));
            hashMap2.put("deviceIconModels", new TableInfo.Column("deviceIconModels", "TEXT", false, 0, null, 1));
            HashSet hashSet = new HashSet(1);
            hashSet.add(new TableInfo.ForeignKey("entity_activity_category", "CASCADE", "NO ACTION", Arrays.asList("categoryId"), Arrays.asList("categoryId")));
            TableInfo tableInfo2 = new TableInfo("entity_physical_activities", hashMap2, hashSet, new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "entity_physical_activities");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_physical_activities(com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            return new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        boolean z = Build.VERSION.SDK_INT >= 21;
        if (!z) {
            try {
                writableDatabase.execSQL("PRAGMA foreign_keys = FALSE");
            } finally {
                super.endTransaction();
                if (!z) {
                    writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
                }
                writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
                if (!writableDatabase.inTransaction()) {
                    writableDatabase.execSQL("VACUUM");
                }
            }
        }
        super.beginTransaction();
        if (z) {
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        writableDatabase.execSQL("DELETE FROM `entity_activity_category`");
        writableDatabase.execSQL("DELETE FROM `entity_physical_activities`");
        super.setTransactionSuccessful();
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "entity_activity_category", "entity_physical_activities");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(14), "9665fa0a00f004210cb67d0e416b254b", "885d41dd4bcda90250fe863f8e93314d")).build());
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.PhysicalActivityDatabase
    public PhysicalActivitiesDao physicalActivityDao() {
        PhysicalActivitiesDao physicalActivitiesDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new PhysicalActivitiesDao_Impl(this);
            }
            physicalActivitiesDao = this.d;
        }
        return physicalActivitiesDao;
    }
}
