package com.mappls.sdk.traffic.db;

import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.traffic.db.dao.b;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes8.dex */
public final class BeaconDatabase_Impl extends BeaconDatabase {
    public volatile b b;

    /* loaded from: classes8.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a() {
            super(1);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `locations` (`time` TEXT NOT NULL, `longitude` REAL NOT NULL, `latitude` REAL NOT NULL, `speed` REAL NOT NULL, `heading` REAL NOT NULL, `deviceType` TEXT, `engineState` INTEGER NOT NULL, `altitude` REAL NOT NULL, PRIMARY KEY(`time`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"83874013c8c25fadd1f9f4cbfb9aeb63\")");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `locations`");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (BeaconDatabase_Impl.this.mCallbacks != null) {
                int size = BeaconDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) BeaconDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            BeaconDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            BeaconDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (BeaconDatabase_Impl.this.mCallbacks != null) {
                int size = BeaconDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) BeaconDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(8);
            hashMap.put(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, new TableInfo.Column(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "TEXT", true, 1));
            hashMap.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0));
            hashMap.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0));
            hashMap.put("speed", new TableInfo.Column("speed", "REAL", true, 0));
            hashMap.put("heading", new TableInfo.Column("heading", "REAL", true, 0));
            hashMap.put("deviceType", new TableInfo.Column("deviceType", "TEXT", false, 0));
            hashMap.put("engineState", new TableInfo.Column("engineState", "INTEGER", true, 0));
            hashMap.put(SavingTrackHelper.TRACK_COL_ALTITUDE, new TableInfo.Column(SavingTrackHelper.TRACK_COL_ALTITUDE, "REAL", true, 0));
            TableInfo tableInfo = new TableInfo("locations", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "locations");
            if (tableInfo.equals(read)) {
                return;
            }
            throw new IllegalStateException("Migration didn't properly handle locations(com.mappls.sdk.traffic.db.ProbeLocation).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
        }
    }

    @Override // com.mappls.sdk.traffic.db.BeaconDatabase
    public final com.mappls.sdk.traffic.db.dao.a a() {
        b bVar;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new b(this);
            }
            bVar = this.b;
        }
        return bVar;
    }

    @Override // androidx.room.RoomDatabase
    public final void clearAllTables() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        try {
            beginTransaction();
            writableDatabase.execSQL("DELETE FROM `locations`");
            setTransactionSuccessful();
        } finally {
            endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    public final InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "locations");
    }

    @Override // androidx.room.RoomDatabase
    public final SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(), "83874013c8c25fadd1f9f4cbfb9aeb63", "2dc40ac97f6d90307297d77e6cce2b5c")).build());
    }
}
