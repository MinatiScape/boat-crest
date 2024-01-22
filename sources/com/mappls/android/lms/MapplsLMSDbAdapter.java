package com.mappls.android.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.mappls.android.util.MPLog;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
class MapplsLMSDbAdapter {
    private static final String ANONYMOUS_PEOPLE_TIME_INDEX;
    public static final int AUTOMATIC_DATA_COLUMN_INDEX = 3;
    public static final int CREATED_AT_COLUMN_INDEX = 2;
    private static final String CREATE_ANONYMOUS_PEOPLE_TABLE;
    private static final String CREATE_EVENTS_TABLE;
    private static final String CREATE_FCM_EVENTS_TABLE;
    private static final String CREATE_GROUPS_TABLE;
    private static final String CREATE_PEOPLE_TABLE;
    private static final String DATABASE_NAME = "mappls_svgsfse";
    private static final int DATABASE_VERSION = 10;
    public static final int DATA_COLUMN_INDEX = 1;
    public static final int DB_OUT_OF_MEMORY_ERROR = -2;
    public static final int DB_UNDEFINED_CODE = -3;
    public static final int DB_UPDATE_ERROR = -1;
    private static final String EVENTS_TIME_INDEX;
    private static final String FCM_EVENTS_TIME_INDEX;
    private static final String GROUPS_TIME_INDEX;
    public static final int ID_COLUMN_INDEX = 0;
    public static final String KEY_AUTOMATIC_DATA = "automatic_data";
    public static final String KEY_CREATED_AT = "created_at";
    public static final String KEY_DATA = "data";
    public static final String KEY_TOKEN = "token";
    private static final String LOGTAG = "MapplsLMSAPI.Database";
    private static final String PEOPLE_TIME_INDEX;
    public static final int TOKEN_COLUMN_INDEX = 4;
    private static final Map<Context, MapplsLMSDbAdapter> sInstances = new HashMap();
    private final MPDatabaseHelper mDb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class MPDatabaseHelper extends SQLiteOpenHelper {
        private final MapplsLMSConfig mConfig;
        private final File mDatabaseFile;

        public MPDatabaseHelper(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 10);
            this.mDatabaseFile = context.getDatabasePath(str);
            this.mConfig = MapplsLMSConfig.getInstance(context);
        }

        public boolean aboveMemThreshold() {
            if (this.mDatabaseFile.exists()) {
                return this.mDatabaseFile.length() > Math.max(this.mDatabaseFile.getUsableSpace(), (long) this.mConfig.getMinimumDatabaseLimit()) || this.mDatabaseFile.length() > ((long) this.mConfig.getMaximumDatabaseLimit());
            }
            return false;
        }

        public void deleteDatabase() {
            close();
            this.mDatabaseFile.delete();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            MPLog.v(MapplsLMSDbAdapter.LOGTAG, "Creating a new Mappls Analytics events DB");
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_EVENTS_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_FCM_EVENTS_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_PEOPLE_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_GROUPS_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_ANONYMOUS_PEOPLE_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.EVENTS_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.FCM_EVENTS_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.PEOPLE_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.GROUPS_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.ANONYMOUS_PEOPLE_TIME_INDEX);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            MPLog.v(MapplsLMSDbAdapter.LOGTAG, "Upgrading app, replacing Mappls LMS events DB");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.EVENTS.getName());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.FCM_EVENTS.getName());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.PEOPLE.getName());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.GROUPS.getName());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.ANONYMOUS_PEOPLE.getName());
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_EVENTS_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_FCM_EVENTS_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_PEOPLE_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_GROUPS_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.CREATE_ANONYMOUS_PEOPLE_TABLE);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.EVENTS_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.FCM_EVENTS_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.PEOPLE_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.GROUPS_TIME_INDEX);
            sQLiteDatabase.execSQL(MapplsLMSDbAdapter.ANONYMOUS_PEOPLE_TIME_INDEX);
        }
    }

    /* loaded from: classes11.dex */
    public enum Table {
        EVENTS("events"),
        FCM_EVENTS("fcm_events"),
        PEOPLE("people"),
        ANONYMOUS_PEOPLE("anonymous_people"),
        GROUPS("groups");
        
        private final String mTableName;

        Table(String str) {
            this.mTableName = str;
        }

        public String getName() {
            return this.mTableName;
        }
    }

    static {
        Table table;
        Table table2;
        Table table3;
        Table table4;
        StringBuilder sb = new StringBuilder("CREATE TABLE ");
        sb.append(Table.EVENTS.getName());
        sb.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, data STRING NOT NULL, created_at INTEGER NOT NULL, automatic_data INTEGER DEFAULT 0, token STRING NOT NULL DEFAULT '')");
        CREATE_EVENTS_TABLE = sb.toString();
        CREATE_FCM_EVENTS_TABLE = "CREATE TABLE " + Table.FCM_EVENTS.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, data STRING NOT NULL, created_at INTEGER NOT NULL, automatic_data INTEGER DEFAULT 0, token STRING NOT NULL DEFAULT '')";
        StringBuilder sb2 = new StringBuilder("CREATE TABLE ");
        sb2.append(Table.PEOPLE.getName());
        sb2.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, data STRING NOT NULL, created_at INTEGER NOT NULL, automatic_data INTEGER DEFAULT 0, token STRING NOT NULL DEFAULT '')");
        CREATE_PEOPLE_TABLE = sb2.toString();
        StringBuilder sb3 = new StringBuilder("CREATE TABLE ");
        sb3.append(Table.GROUPS.getName());
        sb3.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, data STRING NOT NULL, created_at INTEGER NOT NULL, automatic_data INTEGER DEFAULT 0, token STRING NOT NULL DEFAULT '')");
        CREATE_GROUPS_TABLE = sb3.toString();
        StringBuilder sb4 = new StringBuilder("CREATE TABLE ");
        sb4.append(Table.ANONYMOUS_PEOPLE.getName());
        sb4.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, data STRING NOT NULL, created_at INTEGER NOT NULL, automatic_data INTEGER DEFAULT 0, token STRING NOT NULL DEFAULT '')");
        CREATE_ANONYMOUS_PEOPLE_TABLE = sb4.toString();
        EVENTS_TIME_INDEX = "CREATE INDEX IF NOT EXISTS time_idx ON " + table.getName() + " (created_at);";
        FCM_EVENTS_TIME_INDEX = "CREATE INDEX IF NOT EXISTS time_idx ON " + table.getName() + " (created_at);";
        PEOPLE_TIME_INDEX = "CREATE INDEX IF NOT EXISTS time_idx ON " + table2.getName() + " (created_at);";
        GROUPS_TIME_INDEX = "CREATE INDEX IF NOT EXISTS time_idx ON " + table3.getName() + " (created_at);";
        ANONYMOUS_PEOPLE_TIME_INDEX = "CREATE INDEX IF NOT EXISTS time_idx ON " + table4.getName() + " (created_at);";
    }

    public MapplsLMSDbAdapter(Context context) {
        this(context, DATABASE_NAME);
    }

    public MapplsLMSDbAdapter(Context context, String str) {
        this.mDb = new MPDatabaseHelper(context, str);
    }

    public static MapplsLMSDbAdapter getInstance(Context context) {
        MapplsLMSDbAdapter mapplsLMSDbAdapter;
        Map<Context, MapplsLMSDbAdapter> map = sInstances;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (map.containsKey(applicationContext)) {
                mapplsLMSDbAdapter = map.get(applicationContext);
            } else {
                mapplsLMSDbAdapter = new MapplsLMSDbAdapter(applicationContext);
                map.put(applicationContext, mapplsLMSDbAdapter);
            }
        }
        return mapplsLMSDbAdapter;
    }

    public boolean aboveMemThreshold() {
        return this.mDb.aboveMemThreshold();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x006e, code lost:
        if (r3 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0083, code lost:
        if (r3 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0085, code lost:
        r3.close();
        r3 = r3;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int addJSON(java.lang.String r9, java.lang.String r10, com.mappls.android.lms.MapplsLMSDbAdapter.Table r11) {
        /*
            r8 = this;
            java.lang.String r0 = "SELECT COUNT(*) FROM "
            boolean r1 = r8.aboveMemThreshold()
            java.lang.String r2 = "MapplsLMSAPI.Database"
            if (r1 == 0) goto L11
            java.lang.String r9 = "There is not enough space left on the device or the data was over the maximum size limit so it was discarded"
            com.mappls.android.util.MPLog.e(r2, r9)
            r9 = -2
            return r9
        L11:
            java.lang.String r11 = r11.getName()
            r1 = -1
            r3 = 0
            com.mappls.android.lms.MapplsLMSDbAdapter$MPDatabaseHelper r4 = r8.mDb     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            android.database.sqlite.SQLiteDatabase r4 = r4.getWritableDatabase()     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            android.content.ContentValues r5 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            r5.<init>()     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.String r6 = "data"
            r5.put(r6, r9)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.String r9 = "created_at"
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            r5.put(r9, r6)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.String r9 = "token"
            r5.put(r9, r10)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            r4.insert(r11, r3, r5)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            r9.<init>(r0)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            r9.append(r11)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.String r11 = " WHERE token='"
            r9.append(r11)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            r9.append(r10)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.String r10 = "'"
            r9.append(r10)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            android.database.Cursor r9 = r4.rawQuery(r9, r3)     // Catch: java.lang.Throwable -> L67 java.lang.OutOfMemoryError -> L69 android.database.sqlite.SQLiteException -> L71
            r9.moveToFirst()     // Catch: java.lang.OutOfMemoryError -> L65 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8e
            r10 = 0
            int r1 = r9.getInt(r10)     // Catch: java.lang.OutOfMemoryError -> L65 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8e
            r9.close()
            goto L88
        L65:
            r3 = r9
            goto L69
        L67:
            r10 = move-exception
            goto L90
        L69:
            java.lang.String r9 = "Out of memory when adding Mappls Analytics data to table"
            com.mappls.android.util.MPLog.e(r2, r9)     // Catch: java.lang.Throwable -> L67
            if (r3 == 0) goto L88
            goto L85
        L71:
            r9 = r3
        L72:
            java.lang.String r10 = "Could not add Mappls Analytics data to table"
            com.mappls.android.util.MPLog.e(r2, r10)     // Catch: java.lang.Throwable -> L8e
            if (r9 == 0) goto L7d
            r9.close()     // Catch: java.lang.Throwable -> L8e
            goto L7e
        L7d:
            r3 = r9
        L7e:
            com.mappls.android.lms.MapplsLMSDbAdapter$MPDatabaseHelper r9 = r8.mDb     // Catch: java.lang.Throwable -> L67
            r9.deleteDatabase()     // Catch: java.lang.Throwable -> L67
            if (r3 == 0) goto L88
        L85:
            r3.close()
        L88:
            com.mappls.android.lms.MapplsLMSDbAdapter$MPDatabaseHelper r9 = r8.mDb
            r9.close()
            return r1
        L8e:
            r10 = move-exception
            r3 = r9
        L90:
            if (r3 == 0) goto L95
            r3.close()
        L95:
            com.mappls.android.lms.MapplsLMSDbAdapter$MPDatabaseHelper r9 = r8.mDb
            r9.close()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.MapplsLMSDbAdapter.addJSON(java.lang.String, java.lang.String, com.mappls.android.lms.MapplsLMSDbAdapter$Table):int");
    }

    public void cleanupAllEvents(Table table, String str) {
        String name = table.getName();
        try {
            try {
                SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
                writableDatabase.delete(name, "token = '" + str + "'", null);
            } catch (SQLiteException e) {
                MPLog.e(LOGTAG, "Could not clean timed-out Mappls Analytics records from " + name + ". Re-initializing database.", e);
                this.mDb.deleteDatabase();
            }
        } finally {
            this.mDb.close();
        }
    }

    public void cleanupEvents(long j, Table table) {
        String name = table.getName();
        try {
            try {
                SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
                writableDatabase.delete(name, "created_at <= " + j, null);
            } catch (SQLiteException e) {
                MPLog.e(LOGTAG, "Could not clean timed-out Mappls Analytics records from " + name + ". Re-initializing database.", e);
                this.mDb.deleteDatabase();
            }
        } finally {
            this.mDb.close();
        }
    }

    public void cleanupEvents(String str, Table table, String str2) {
        String name = table.getName();
        try {
            try {
                SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
                writableDatabase.delete(name, new StringBuffer("_id <= " + str + " AND token = '" + str2 + "'").toString(), null);
            } catch (SQLiteException e) {
                MPLog.e(LOGTAG, "Could not clean sent Mappls Analytics records from " + name + ". Re-initializing database.", e);
                this.mDb.deleteDatabase();
            } catch (Exception e2) {
                MPLog.e(LOGTAG, "Unknown exception. Could not clean sent Mappls Analytics records from " + name + ".Re-initializing database.", e2);
                this.mDb.deleteDatabase();
            }
        } finally {
            this.mDb.close();
        }
    }

    public void deleteDB() {
        this.mDb.deleteDatabase();
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x014a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0158 A[ADDED_TO_REGION, ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String[] generateDataString(com.mappls.android.lms.MapplsLMSDbAdapter.Table r17, java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.MapplsLMSDbAdapter.generateDataString(com.mappls.android.lms.MapplsLMSDbAdapter$Table, java.lang.String):java.lang.String[]");
    }

    public File getDatabaseFile() {
        return this.mDb.mDatabaseFile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0145  */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int pushAnonymousUpdatesToPeopleDb(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.MapplsLMSDbAdapter.pushAnonymousUpdatesToPeopleDb(java.lang.String, java.lang.String):int");
    }

    public int rewriteEventDataWithProperties(Map<String, String> map, String str) {
        Cursor cursor;
        SQLiteDatabase writableDatabase;
        if (aboveMemThreshold()) {
            MPLog.e(LOGTAG, "There is not enough space left on the device or the data was over the maximum size limit so it was discarded");
            return -2;
        }
        int i = 0;
        Cursor cursor2 = null;
        try {
            try {
                writableDatabase = this.mDb.getWritableDatabase();
                cursor = writableDatabase.rawQuery(new StringBuffer("SELECT * FROM " + Table.EVENTS.getName() + " WHERE token = '" + str + "'").toString(), null);
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    writableDatabase.beginTransaction();
                    int i2 = 0;
                    while (cursor.moveToNext()) {
                        try {
                            try {
                                try {
                                    ContentValues contentValues = new ContentValues();
                                    JSONObject jSONObject = new JSONObject(cursor.getString(cursor.getColumnIndex("data") >= 0 ? cursor.getColumnIndex("data") : 1));
                                    JSONObject jSONObject2 = jSONObject.getJSONObject("properties");
                                    for (Map.Entry<String, String> entry : map.entrySet()) {
                                        jSONObject2.put(entry.getKey(), entry.getValue());
                                    }
                                    jSONObject.put("properties", jSONObject2);
                                    contentValues.put("data", jSONObject.toString());
                                    int i3 = cursor.getInt(cursor.getColumnIndex("_id") >= 0 ? cursor.getColumnIndex("_id") : 0);
                                    writableDatabase.update(Table.EVENTS.getName(), contentValues, "_id = " + i3, null);
                                    i2++;
                                } catch (JSONException unused) {
                                }
                            } finally {
                                writableDatabase.endTransaction();
                            }
                        } catch (SQLiteException e) {
                            e = e;
                            i = i2;
                            MPLog.e(LOGTAG, "Could not re-write events history. Re-initializing database.", e);
                            if (cursor != null) {
                                cursor.close();
                            } else {
                                cursor2 = cursor;
                            }
                            this.mDb.deleteDatabase();
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            this.mDb.close();
                            return i;
                        }
                    }
                    writableDatabase.setTransactionSuccessful();
                    cursor.close();
                    this.mDb.close();
                    return i2;
                } catch (SQLiteException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                this.mDb.close();
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        }
    }
}
