package com.coveiot.android.navigation.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.navigation.db.dao.RecentSearchHistoryDao;
import com.coveiot.android.navigation.db.dao.RecentSearchHistoryDao_Impl;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes5.dex */
public final class RecentSearchHistoryDatabase_Impl extends RecentSearchHistoryDatabase {
    public volatile RecentSearchHistoryDao b;

    /* loaded from: classes5.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_navigation_recent_search_history` (`placeName` TEXT NOT NULL, `placeAddress` TEXT NOT NULL, `distance` REAL NOT NULL, `orderIndex` INTEGER NOT NULL, `type` TEXT NOT NULL, `mapplsPin` TEXT NOT NULL, `timeStamp` INTEGER NOT NULL, PRIMARY KEY(`mapplsPin`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '73c9fc55321b85884228b10537ca26d4')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_navigation_recent_search_history`");
            if (RecentSearchHistoryDatabase_Impl.this.mCallbacks != null) {
                int size = RecentSearchHistoryDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) RecentSearchHistoryDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (RecentSearchHistoryDatabase_Impl.this.mCallbacks != null) {
                int size = RecentSearchHistoryDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) RecentSearchHistoryDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            RecentSearchHistoryDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            RecentSearchHistoryDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (RecentSearchHistoryDatabase_Impl.this.mCallbacks != null) {
                int size = RecentSearchHistoryDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) RecentSearchHistoryDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(7);
            hashMap.put("placeName", new TableInfo.Column("placeName", "TEXT", true, 0, null, 1));
            hashMap.put("placeAddress", new TableInfo.Column("placeAddress", "TEXT", true, 0, null, 1));
            hashMap.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, 1));
            hashMap.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 0, null, 1));
            hashMap.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, 1));
            hashMap.put("mapplsPin", new TableInfo.Column("mapplsPin", "TEXT", true, 1, null, 1));
            hashMap.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("entity_navigation_recent_search_history", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "entity_navigation_recent_search_history");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_navigation_recent_search_history(com.coveiot.android.navigation.db.entity.EntityRecentSearchHistory).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
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
            writableDatabase.execSQL("DELETE FROM `entity_navigation_recent_search_history`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "entity_navigation_recent_search_history");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(1), "73c9fc55321b85884228b10537ca26d4", "b4425acd1b3b58f90d4aa84b882f3284")).build());
    }

    @Override // com.coveiot.android.navigation.db.RecentSearchHistoryDatabase
    public RecentSearchHistoryDao recentSearchHistoryDao() {
        RecentSearchHistoryDao recentSearchHistoryDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new RecentSearchHistoryDao_Impl(this);
            }
            recentSearchHistoryDao = this.b;
        }
        return recentSearchHistoryDao;
    }
}
