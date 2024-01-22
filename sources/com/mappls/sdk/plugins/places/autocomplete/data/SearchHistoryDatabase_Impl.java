package com.mappls.sdk.plugins.places.autocomplete.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.mappls.sdk.plugins.places.autocomplete.data.dao.d;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class SearchHistoryDatabase_Impl extends SearchHistoryDatabase {
    public volatile d c;

    /* loaded from: classes10.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a() {
            super(3);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `searchhistory` (`placeId` TEXT NOT NULL, `eLocation` TEXT, `timestamp` INTEGER, `place_name` TEXT, `place_address` TEXT, `alternate_name` TEXT, PRIMARY KEY(`placeId`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd9a5611494bf00f3cf9c33f76ea0f315')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `searchhistory`");
            if (SearchHistoryDatabase_Impl.this.mCallbacks != null) {
                int size = SearchHistoryDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SearchHistoryDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (SearchHistoryDatabase_Impl.this.mCallbacks != null) {
                int size = SearchHistoryDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SearchHistoryDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            SearchHistoryDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            SearchHistoryDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (SearchHistoryDatabase_Impl.this.mCallbacks != null) {
                int size = SearchHistoryDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SearchHistoryDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(6);
            hashMap.put("placeId", new TableInfo.Column("placeId", "TEXT", true, 1, null, 1));
            hashMap.put("eLocation", new TableInfo.Column("eLocation", "TEXT", false, 0, null, 1));
            hashMap.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", false, 0, null, 1));
            hashMap.put("place_name", new TableInfo.Column("place_name", "TEXT", false, 0, null, 1));
            hashMap.put("place_address", new TableInfo.Column("place_address", "TEXT", false, 0, null, 1));
            hashMap.put("alternate_name", new TableInfo.Column("alternate_name", "TEXT", false, 0, null, 1));
            TableInfo tableInfo = new TableInfo("searchhistory", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "searchhistory");
            if (tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(true, null);
            }
            return new RoomOpenHelper.ValidationResult(false, "searchhistory(com.mappls.sdk.plugins.places.autocomplete.data.entity.SearchHistoryEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
        }
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.data.SearchHistoryDatabase
    public final com.mappls.sdk.plugins.places.autocomplete.data.dao.a b() {
        d dVar;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new d(this);
            }
            dVar = this.c;
        }
        return dVar;
    }

    @Override // androidx.room.RoomDatabase
    public final void clearAllTables() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        try {
            beginTransaction();
            writableDatabase.execSQL("DELETE FROM `searchhistory`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "searchhistory");
    }

    @Override // androidx.room.RoomDatabase
    public final SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(), "d9a5611494bf00f3cf9c33f76ea0f315", "03959fc8ff643196f0b69b520ca2862c")).build());
    }

    public final List<Migration> getAutoMigrations(@NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    public final Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public final Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(com.mappls.sdk.plugins.places.autocomplete.data.dao.a.class, Collections.emptyList());
        return hashMap;
    }
}
