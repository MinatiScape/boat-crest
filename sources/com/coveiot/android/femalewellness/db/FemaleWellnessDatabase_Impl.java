package com.coveiot.android.femalewellness.db;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao;
import com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao_Impl;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes4.dex */
public final class FemaleWellnessDatabase_Impl extends FemaleWellnessDatabase {
    public volatile FemaleWellnessSymptomsDao b;

    /* loaded from: classes4.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `female_wellness_record_symptoms` (`date` TEXT NOT NULL, `flow` TEXT, `pain` TEXT, `mood` TEXT, `symptoms` TEXT, `isActive` INTEGER NOT NULL, `cycleStartDate` TEXT, `cycleLength` INTEGER, `periodLength` INTEGER, `pmsLength` INTEGER, `phase` TEXT, PRIMARY KEY(`date`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a9272f67f481ad46847808aac18f86b3')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `female_wellness_record_symptoms`");
            if (FemaleWellnessDatabase_Impl.this.mCallbacks != null) {
                int size = FemaleWellnessDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) FemaleWellnessDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (FemaleWellnessDatabase_Impl.this.mCallbacks != null) {
                int size = FemaleWellnessDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) FemaleWellnessDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            FemaleWellnessDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            FemaleWellnessDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (FemaleWellnessDatabase_Impl.this.mCallbacks != null) {
                int size = FemaleWellnessDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) FemaleWellnessDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(11);
            hashMap.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap.put("flow", new TableInfo.Column("flow", "TEXT", false, 0, null, 1));
            hashMap.put("pain", new TableInfo.Column("pain", "TEXT", false, 0, null, 1));
            hashMap.put("mood", new TableInfo.Column("mood", "TEXT", false, 0, null, 1));
            hashMap.put("symptoms", new TableInfo.Column("symptoms", "TEXT", false, 0, null, 1));
            hashMap.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, 1));
            hashMap.put("cycleStartDate", new TableInfo.Column("cycleStartDate", "TEXT", false, 0, null, 1));
            hashMap.put("cycleLength", new TableInfo.Column("cycleLength", "INTEGER", false, 0, null, 1));
            hashMap.put("periodLength", new TableInfo.Column("periodLength", "INTEGER", false, 0, null, 1));
            hashMap.put("pmsLength", new TableInfo.Column("pmsLength", "INTEGER", false, 0, null, 1));
            hashMap.put(TypedValues.CycleType.S_WAVE_PHASE, new TableInfo.Column(TypedValues.CycleType.S_WAVE_PHASE, "TEXT", false, 0, null, 1));
            TableInfo tableInfo = new TableInfo("female_wellness_record_symptoms", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "female_wellness_record_symptoms");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "female_wellness_record_symptoms(com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
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
            writableDatabase.execSQL("DELETE FROM `female_wellness_record_symptoms`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "female_wellness_record_symptoms");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(1), "a9272f67f481ad46847808aac18f86b3", "0bca644a67208de5140bf8db9565d36e")).build());
    }

    @Override // com.coveiot.android.femalewellness.db.FemaleWellnessDatabase
    public FemaleWellnessSymptomsDao symptomsDao() {
        FemaleWellnessSymptomsDao femaleWellnessSymptomsDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new FemaleWellnessSymptomsDao_Impl(this);
            }
            femaleWellnessSymptomsDao = this.b;
        }
        return femaleWellnessSymptomsDao;
    }
}
