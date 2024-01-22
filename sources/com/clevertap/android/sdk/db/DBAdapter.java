package com.clevertap.android.sdk.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inbox.CTMessageDAO;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class DBAdapter {
    public static final int DB_UNDEFINED_CODE = -3;
    public static final String d;
    public static final String e;
    public static final String f;
    public static final String g;
    public static final String h;
    public static final String i;
    public static final String j;
    public static final String k;
    public static final String l;
    public static final String m;
    public static final String n;
    public static final String o;
    public static final String p;
    public static final String q;
    public static final String r;
    public static final String s;

    /* renamed from: a  reason: collision with root package name */
    public CleverTapInstanceConfig f2604a;
    public final a b;
    public boolean c;

    /* loaded from: classes2.dex */
    public enum Table {
        EVENTS("events"),
        PROFILE_EVENTS("profileEvents"),
        USER_PROFILES("userProfiles"),
        INBOX_MESSAGES("inboxMessages"),
        PUSH_NOTIFICATIONS("pushNotifications"),
        UNINSTALL_TS("uninstallTimestamp"),
        PUSH_NOTIFICATION_VIEWED("notificationViewed");
        
        private final String tableName;

        Table(String str) {
            this.tableName = str;
        }

        public String getName() {
            return this.tableName;
        }
    }

    /* loaded from: classes2.dex */
    public static class a extends SQLiteOpenHelper {
        public final File h;

        public a(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 3);
            this.h = context.getDatabasePath(str);
        }

        @SuppressLint({"UsableSpace"})
        public boolean a() {
            return !this.h.exists() || Math.max(this.h.getUsableSpace(), 20971520L) >= this.h.length();
        }

        public void b() {
            close();
            this.h.delete();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        @SuppressLint({"SQLiteString"})
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Logger.v("Creating CleverTap DB");
            SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(DBAdapter.d);
            Logger.v("Executing - " + DBAdapter.d);
            compileStatement.execute();
            SQLiteStatement compileStatement2 = sQLiteDatabase.compileStatement(DBAdapter.e);
            Logger.v("Executing - " + DBAdapter.e);
            compileStatement2.execute();
            SQLiteStatement compileStatement3 = sQLiteDatabase.compileStatement(DBAdapter.f);
            Logger.v("Executing - " + DBAdapter.f);
            compileStatement3.execute();
            SQLiteStatement compileStatement4 = sQLiteDatabase.compileStatement(DBAdapter.g);
            Logger.v("Executing - " + DBAdapter.g);
            compileStatement4.execute();
            SQLiteStatement compileStatement5 = sQLiteDatabase.compileStatement(DBAdapter.k);
            Logger.v("Executing - " + DBAdapter.k);
            compileStatement5.execute();
            SQLiteStatement compileStatement6 = sQLiteDatabase.compileStatement(DBAdapter.m);
            Logger.v("Executing - " + DBAdapter.m);
            compileStatement6.execute();
            SQLiteStatement compileStatement7 = sQLiteDatabase.compileStatement(DBAdapter.o);
            Logger.v("Executing - " + DBAdapter.o);
            compileStatement7.execute();
            SQLiteStatement compileStatement8 = sQLiteDatabase.compileStatement(DBAdapter.i);
            Logger.v("Executing - " + DBAdapter.i);
            compileStatement8.execute();
            SQLiteStatement compileStatement9 = sQLiteDatabase.compileStatement(DBAdapter.j);
            Logger.v("Executing - " + DBAdapter.j);
            compileStatement9.execute();
            SQLiteStatement compileStatement10 = sQLiteDatabase.compileStatement(DBAdapter.n);
            Logger.v("Executing - " + DBAdapter.n);
            compileStatement10.execute();
            SQLiteStatement compileStatement11 = sQLiteDatabase.compileStatement(DBAdapter.l);
            Logger.v("Executing - " + DBAdapter.l);
            compileStatement11.execute();
            SQLiteStatement compileStatement12 = sQLiteDatabase.compileStatement(DBAdapter.h);
            Logger.v("Executing - " + DBAdapter.h);
            compileStatement12.execute();
            SQLiteStatement compileStatement13 = sQLiteDatabase.compileStatement(DBAdapter.p);
            Logger.v("Executing - " + DBAdapter.p);
            compileStatement13.execute();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        @SuppressLint({"SQLiteString"})
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Logger.v("Upgrading CleverTap DB to version " + i2);
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(DBAdapter.s);
                Logger.v("Executing - " + DBAdapter.s);
                compileStatement.execute();
                SQLiteStatement compileStatement2 = sQLiteDatabase.compileStatement(DBAdapter.o);
                Logger.v("Executing - " + DBAdapter.o);
                compileStatement2.execute();
                SQLiteStatement compileStatement3 = sQLiteDatabase.compileStatement(DBAdapter.p);
                Logger.v("Executing - " + DBAdapter.p);
                compileStatement3.execute();
                return;
            }
            SQLiteStatement compileStatement4 = sQLiteDatabase.compileStatement(DBAdapter.q);
            Logger.v("Executing - " + DBAdapter.q);
            compileStatement4.execute();
            SQLiteStatement compileStatement5 = sQLiteDatabase.compileStatement(DBAdapter.r);
            Logger.v("Executing - " + DBAdapter.r);
            compileStatement5.execute();
            SQLiteStatement compileStatement6 = sQLiteDatabase.compileStatement(DBAdapter.s);
            Logger.v("Executing - " + DBAdapter.s);
            compileStatement6.execute();
            SQLiteStatement compileStatement7 = sQLiteDatabase.compileStatement(DBAdapter.g);
            Logger.v("Executing - " + DBAdapter.g);
            compileStatement7.execute();
            SQLiteStatement compileStatement8 = sQLiteDatabase.compileStatement(DBAdapter.k);
            Logger.v("Executing - " + DBAdapter.k);
            compileStatement8.execute();
            SQLiteStatement compileStatement9 = sQLiteDatabase.compileStatement(DBAdapter.m);
            Logger.v("Executing - " + DBAdapter.m);
            compileStatement9.execute();
            SQLiteStatement compileStatement10 = sQLiteDatabase.compileStatement(DBAdapter.o);
            Logger.v("Executing - " + DBAdapter.o);
            compileStatement10.execute();
            SQLiteStatement compileStatement11 = sQLiteDatabase.compileStatement(DBAdapter.n);
            Logger.v("Executing - " + DBAdapter.n);
            compileStatement11.execute();
            SQLiteStatement compileStatement12 = sQLiteDatabase.compileStatement(DBAdapter.l);
            Logger.v("Executing - " + DBAdapter.l);
            compileStatement12.execute();
            SQLiteStatement compileStatement13 = sQLiteDatabase.compileStatement(DBAdapter.h);
            Logger.v("Executing - " + DBAdapter.h);
            compileStatement13.execute();
            SQLiteStatement compileStatement14 = sQLiteDatabase.compileStatement(DBAdapter.p);
            Logger.v("Executing - " + DBAdapter.p);
            compileStatement14.execute();
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        Table table = Table.EVENTS;
        sb.append(table.getName());
        sb.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append("data");
        sb.append(" STRING NOT NULL, ");
        sb.append(MapplsLMSDbAdapter.KEY_CREATED_AT);
        sb.append(" INTEGER NOT NULL);");
        d = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("CREATE TABLE ");
        Table table2 = Table.PROFILE_EVENTS;
        sb2.append(table2.getName());
        sb2.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb2.append("data");
        sb2.append(" STRING NOT NULL, ");
        sb2.append(MapplsLMSDbAdapter.KEY_CREATED_AT);
        sb2.append(" INTEGER NOT NULL);");
        e = sb2.toString();
        f = "CREATE TABLE " + Table.USER_PROFILES.getName() + " (_id STRING UNIQUE PRIMARY KEY, data STRING NOT NULL);";
        StringBuilder sb3 = new StringBuilder();
        sb3.append("CREATE TABLE ");
        Table table3 = Table.INBOX_MESSAGES;
        sb3.append(table3.getName());
        sb3.append(" (_id STRING NOT NULL, ");
        sb3.append("data");
        sb3.append(" TEXT NOT NULL, ");
        sb3.append(Constants.KEY_WZRK_PARAMS);
        sb3.append(" TEXT NOT NULL, ");
        sb3.append("campaignId");
        sb3.append(" STRING NOT NULL, ");
        sb3.append(Constants.KEY_TAGS);
        sb3.append(" TEXT NOT NULL, ");
        sb3.append(Constants.KEY_IS_READ);
        sb3.append(" INTEGER NOT NULL DEFAULT 0, ");
        sb3.append("expires");
        sb3.append(" INTEGER NOT NULL, ");
        sb3.append(MapplsLMSDbAdapter.KEY_CREATED_AT);
        sb3.append(" INTEGER NOT NULL, ");
        sb3.append("messageUser");
        sb3.append(" STRING NOT NULL);");
        g = sb3.toString();
        h = "CREATE UNIQUE INDEX IF NOT EXISTS userid_id_idx ON " + table3.getName() + " (messageUser" + Constants.SEPARATOR_COMMA + "_id);";
        StringBuilder sb4 = new StringBuilder();
        sb4.append("CREATE INDEX IF NOT EXISTS time_idx ON ");
        sb4.append(table.getName());
        sb4.append(" (");
        sb4.append(MapplsLMSDbAdapter.KEY_CREATED_AT);
        sb4.append(");");
        i = sb4.toString();
        j = "CREATE INDEX IF NOT EXISTS time_idx ON " + table2.getName() + " (" + MapplsLMSDbAdapter.KEY_CREATED_AT + ");";
        StringBuilder sb5 = new StringBuilder();
        sb5.append("CREATE TABLE ");
        Table table4 = Table.PUSH_NOTIFICATIONS;
        sb5.append(table4.getName());
        sb5.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb5.append("data");
        sb5.append(" STRING NOT NULL, ");
        sb5.append(MapplsLMSDbAdapter.KEY_CREATED_AT);
        sb5.append(" INTEGER NOT NULL,");
        sb5.append(Constants.KEY_IS_READ);
        sb5.append(" INTEGER NOT NULL);");
        k = sb5.toString();
        l = "CREATE INDEX IF NOT EXISTS time_idx ON " + table4.getName() + " (" + MapplsLMSDbAdapter.KEY_CREATED_AT + ");";
        StringBuilder sb6 = new StringBuilder();
        sb6.append("CREATE TABLE ");
        Table table5 = Table.UNINSTALL_TS;
        sb6.append(table5.getName());
        sb6.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb6.append(MapplsLMSDbAdapter.KEY_CREATED_AT);
        sb6.append(" INTEGER NOT NULL);");
        m = sb6.toString();
        n = "CREATE INDEX IF NOT EXISTS time_idx ON " + table5.getName() + " (" + MapplsLMSDbAdapter.KEY_CREATED_AT + ");";
        StringBuilder sb7 = new StringBuilder();
        sb7.append("CREATE TABLE ");
        Table table6 = Table.PUSH_NOTIFICATION_VIEWED;
        sb7.append(table6.getName());
        sb7.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb7.append("data");
        sb7.append(" STRING NOT NULL, ");
        sb7.append(MapplsLMSDbAdapter.KEY_CREATED_AT);
        sb7.append(" INTEGER NOT NULL);");
        o = sb7.toString();
        p = "CREATE INDEX IF NOT EXISTS time_idx ON " + table6.getName() + " (" + MapplsLMSDbAdapter.KEY_CREATED_AT + ");";
        StringBuilder sb8 = new StringBuilder();
        sb8.append("DROP TABLE IF EXISTS ");
        sb8.append(table5.getName());
        q = sb8.toString();
        r = "DROP TABLE IF EXISTS " + table3.getName();
        s = "DROP TABLE IF EXISTS " + table6.getName();
    }

    public DBAdapter(Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this(context, z(cleverTapInstanceConfig));
        this.f2604a = cleverTapInstanceConfig;
    }

    public static String z(CleverTapInstanceConfig cleverTapInstanceConfig) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            return "clevertap";
        }
        return "clevertap_" + cleverTapInstanceConfig.getAccountId();
    }

    public synchronized void A(Table table) {
        a aVar;
        String name = table.getName();
        try {
            this.b.getWritableDatabase().delete(name, null, null);
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error removing all events from table " + name + " Recreating DB");
            v();
            aVar = this.b;
        }
        aVar.close();
    }

    @WorkerThread
    public synchronized int B(JSONObject jSONObject, Table table) {
        a aVar;
        if (!q()) {
            Logger.v("There is not enough space left on the device to store data, data discarded");
            return -2;
        }
        String name = table.getName();
        long j2 = -1;
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", jSONObject.toString());
            contentValues.put(MapplsLMSDbAdapter.KEY_CREATED_AT, Long.valueOf(System.currentTimeMillis()));
            writableDatabase.insert(name, null, contentValues);
            j2 = writableDatabase.compileStatement("SELECT COUNT(*) FROM " + name).simpleQueryForLong();
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error adding data to table " + name + " Recreating DB");
            this.b.b();
            aVar = this.b;
        }
        aVar.close();
        return (int) j2;
    }

    @WorkerThread
    public synchronized boolean deleteMessageForId(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        String name = Table.INBOX_MESSAGES.getName();
        try {
            this.b.getWritableDatabase().delete(name, "_id = ? AND messageUser = ?", new String[]{str, str2});
            this.b.close();
            return true;
        } catch (SQLiteException e2) {
            Logger y = y();
            y.verbose("Error removing stale records from " + name, e2);
            this.b.close();
            return false;
        }
    }

    @WorkerThread
    public synchronized boolean deleteMessagesForIDs(ArrayList<String> arrayList, String str) {
        if (arrayList == null || str == null) {
            return false;
        }
        String name = Table.INBOX_MESSAGES.getName();
        try {
            StringBuilder sb = new StringBuilder();
            if (arrayList.size() > 0) {
                sb.append("?");
                for (int i2 = 0; i2 < arrayList.size() - 1; i2++) {
                    sb.append(", ?");
                }
            }
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size() + 1]);
            strArr[arrayList.size()] = str;
            this.b.getWritableDatabase().delete(name, "_id IN ( " + ((Object) sb) + " ) AND messageUser = ?", strArr);
            this.b.close();
            return true;
        } catch (SQLiteException e2) {
            y().verbose("Error removing stale records from " + name, e2);
            this.b.close();
            return false;
        }
    }

    public synchronized boolean doesPushNotificationIdExist(String str) {
        return str.equals(x(str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0069, code lost:
        if (r10 != null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.lang.String[] fetchPushNotificationIds() {
        /*
            r12 = this;
            monitor-enter(r12)
            boolean r0 = r12.c     // Catch: java.lang.Throwable -> Lac
            r1 = 0
            if (r0 != 0) goto La
            java.lang.String[] r0 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> Lac
            monitor-exit(r12)
            return r0
        La:
            com.clevertap.android.sdk.db.DBAdapter$Table r0 = com.clevertap.android.sdk.db.DBAdapter.Table.PUSH_NOTIFICATIONS     // Catch: java.lang.Throwable -> Lac
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> Lac
            r10 = 0
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lac
            r11.<init>()     // Catch: java.lang.Throwable -> Lac
            com.clevertap.android.sdk.db.DBAdapter$a r2 = r12.b     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            android.database.sqlite.SQLiteDatabase r2 = r2.getReadableDatabase()     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            r4 = 0
            java.lang.String r5 = "isRead =?"
            java.lang.String r3 = "0"
            java.lang.String[] r6 = new java.lang.String[]{r3}     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            r7 = 0
            r8 = 0
            r9 = 0
            r3 = r0
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            if (r10 == 0) goto L64
        L2f:
            boolean r2 = r10.moveToNext()     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            if (r2 == 0) goto L61
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            r2.<init>()     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            java.lang.String r3 = "Fetching PID - "
            r2.append(r3)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            java.lang.String r3 = "data"
            int r3 = r10.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            java.lang.String r3 = r10.getString(r3)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            r2.append(r3)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            com.clevertap.android.sdk.Logger.v(r2)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            java.lang.String r2 = "data"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            java.lang.String r2 = r10.getString(r2)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            r11.add(r2)     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
            goto L2f
        L61:
            r10.close()     // Catch: java.lang.Throwable -> L6f android.database.sqlite.SQLiteException -> L71
        L64:
            com.clevertap.android.sdk.db.DBAdapter$a r0 = r12.b     // Catch: java.lang.Throwable -> Lac
            r0.close()     // Catch: java.lang.Throwable -> Lac
            if (r10 == 0) goto L97
        L6b:
            r10.close()     // Catch: java.lang.Throwable -> Lac
            goto L97
        L6f:
            r0 = move-exception
            goto La1
        L71:
            r2 = move-exception
            com.clevertap.android.sdk.Logger r3 = r12.y()     // Catch: java.lang.Throwable -> L6f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6f
            r4.<init>()     // Catch: java.lang.Throwable -> L6f
            java.lang.String r5 = "Could not fetch records out of database "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6f
            r4.append(r0)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r0 = "."
            r4.append(r0)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L6f
            r3.verbose(r0, r2)     // Catch: java.lang.Throwable -> L6f
            com.clevertap.android.sdk.db.DBAdapter$a r0 = r12.b     // Catch: java.lang.Throwable -> Lac
            r0.close()     // Catch: java.lang.Throwable -> Lac
            if (r10 == 0) goto L97
            goto L6b
        L97:
            java.lang.String[] r0 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> Lac
            java.lang.Object[] r0 = r11.toArray(r0)     // Catch: java.lang.Throwable -> Lac
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Throwable -> Lac
            monitor-exit(r12)
            return r0
        La1:
            com.clevertap.android.sdk.db.DBAdapter$a r1 = r12.b     // Catch: java.lang.Throwable -> Lac
            r1.close()     // Catch: java.lang.Throwable -> Lac
            if (r10 == 0) goto Lab
            r10.close()     // Catch: java.lang.Throwable -> Lac
        Lab:
            throw r0     // Catch: java.lang.Throwable -> Lac
        Lac:
            r0 = move-exception
            monitor-exit(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.db.DBAdapter.fetchPushNotificationIds():java.lang.String[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        if (r12 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
        if (r12 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0076, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized org.json.JSONObject fetchUserProfileById(java.lang.String r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            if (r12 != 0) goto L6
            monitor-exit(r11)
            return r0
        L6:
            com.clevertap.android.sdk.db.DBAdapter$Table r1 = com.clevertap.android.sdk.db.DBAdapter.Table.USER_PROFILES     // Catch: java.lang.Throwable -> L83
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L83
            com.clevertap.android.sdk.db.DBAdapter$a r2 = r11.b     // Catch: java.lang.Throwable -> L49 android.database.sqlite.SQLiteException -> L4e
            android.database.sqlite.SQLiteDatabase r2 = r2.getReadableDatabase()     // Catch: java.lang.Throwable -> L49 android.database.sqlite.SQLiteException -> L4e
            r4 = 0
            java.lang.String r5 = "_id =?"
            r3 = 1
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L49 android.database.sqlite.SQLiteException -> L4e
            r3 = 0
            r6[r3] = r12     // Catch: java.lang.Throwable -> L49 android.database.sqlite.SQLiteException -> L4e
            r7 = 0
            r8 = 0
            r9 = 0
            r3 = r1
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L49 android.database.sqlite.SQLiteException -> L4e
            if (r12 == 0) goto L3e
            boolean r2 = r12.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L3c java.lang.Throwable -> L77
            if (r2 == 0) goto L3e
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: android.database.sqlite.SQLiteException -> L3c org.json.JSONException -> L3e java.lang.Throwable -> L77
            java.lang.String r3 = "data"
            int r3 = r12.getColumnIndex(r3)     // Catch: android.database.sqlite.SQLiteException -> L3c org.json.JSONException -> L3e java.lang.Throwable -> L77
            java.lang.String r3 = r12.getString(r3)     // Catch: android.database.sqlite.SQLiteException -> L3c org.json.JSONException -> L3e java.lang.Throwable -> L77
            r2.<init>(r3)     // Catch: android.database.sqlite.SQLiteException -> L3c org.json.JSONException -> L3e java.lang.Throwable -> L77
            r0 = r2
            goto L3e
        L3c:
            r2 = move-exception
            goto L50
        L3e:
            com.clevertap.android.sdk.db.DBAdapter$a r1 = r11.b     // Catch: java.lang.Throwable -> L83
            r1.close()     // Catch: java.lang.Throwable -> L83
            if (r12 == 0) goto L75
        L45:
            r12.close()     // Catch: java.lang.Throwable -> L83
            goto L75
        L49:
            r12 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
            goto L78
        L4e:
            r2 = move-exception
            r12 = r0
        L50:
            com.clevertap.android.sdk.Logger r3 = r11.y()     // Catch: java.lang.Throwable -> L77
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
            r4.<init>()     // Catch: java.lang.Throwable -> L77
            java.lang.String r5 = "Could not fetch records out of database "
            r4.append(r5)     // Catch: java.lang.Throwable -> L77
            r4.append(r1)     // Catch: java.lang.Throwable -> L77
            java.lang.String r1 = "."
            r4.append(r1)     // Catch: java.lang.Throwable -> L77
            java.lang.String r1 = r4.toString()     // Catch: java.lang.Throwable -> L77
            r3.verbose(r1, r2)     // Catch: java.lang.Throwable -> L77
            com.clevertap.android.sdk.db.DBAdapter$a r1 = r11.b     // Catch: java.lang.Throwable -> L83
            r1.close()     // Catch: java.lang.Throwable -> L83
            if (r12 == 0) goto L75
            goto L45
        L75:
            monitor-exit(r11)
            return r0
        L77:
            r0 = move-exception
        L78:
            com.clevertap.android.sdk.db.DBAdapter$a r1 = r11.b     // Catch: java.lang.Throwable -> L83
            r1.close()     // Catch: java.lang.Throwable -> L83
            if (r12 == 0) goto L82
            r12.close()     // Catch: java.lang.Throwable -> L83
        L82:
            throw r0     // Catch: java.lang.Throwable -> L83
        L83:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.db.DBAdapter.fetchUserProfileById(java.lang.String):org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0035, code lost:
        if (r10 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0060, code lost:
        if (r10 == null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized long getLastUninstallTimestamp() {
        /*
            r13 = this;
            monitor-enter(r13)
            com.clevertap.android.sdk.db.DBAdapter$Table r0 = com.clevertap.android.sdk.db.DBAdapter.Table.UNINSTALL_TS     // Catch: java.lang.Throwable -> L70
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> L70
            r10 = 0
            r11 = 0
            com.clevertap.android.sdk.db.DBAdapter$a r1 = r13.b     // Catch: java.lang.Throwable -> L3b android.database.sqlite.SQLiteException -> L3d
            android.database.sqlite.SQLiteDatabase r1 = r1.getReadableDatabase()     // Catch: java.lang.Throwable -> L3b android.database.sqlite.SQLiteException -> L3d
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r8 = "created_at DESC"
            java.lang.String r9 = "1"
            r2 = r0
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L3b android.database.sqlite.SQLiteException -> L3d
            if (r10 == 0) goto L30
            boolean r1 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L3b android.database.sqlite.SQLiteException -> L3d
            if (r1 == 0) goto L30
            java.lang.String r1 = "created_at"
            int r1 = r10.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L3b android.database.sqlite.SQLiteException -> L3d
            long r11 = r10.getLong(r1)     // Catch: java.lang.Throwable -> L3b android.database.sqlite.SQLiteException -> L3d
        L30:
            com.clevertap.android.sdk.db.DBAdapter$a r0 = r13.b     // Catch: java.lang.Throwable -> L70
            r0.close()     // Catch: java.lang.Throwable -> L70
            if (r10 == 0) goto L63
        L37:
            r10.close()     // Catch: java.lang.Throwable -> L70
            goto L63
        L3b:
            r0 = move-exception
            goto L65
        L3d:
            r1 = move-exception
            com.clevertap.android.sdk.Logger r2 = r13.y()     // Catch: java.lang.Throwable -> L3b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3b
            r3.<init>()     // Catch: java.lang.Throwable -> L3b
            java.lang.String r4 = "Could not fetch records out of database "
            r3.append(r4)     // Catch: java.lang.Throwable -> L3b
            r3.append(r0)     // Catch: java.lang.Throwable -> L3b
            java.lang.String r0 = "."
            r3.append(r0)     // Catch: java.lang.Throwable -> L3b
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L3b
            r2.verbose(r0, r1)     // Catch: java.lang.Throwable -> L3b
            com.clevertap.android.sdk.db.DBAdapter$a r0 = r13.b     // Catch: java.lang.Throwable -> L70
            r0.close()     // Catch: java.lang.Throwable -> L70
            if (r10 == 0) goto L63
            goto L37
        L63:
            monitor-exit(r13)
            return r11
        L65:
            com.clevertap.android.sdk.db.DBAdapter$a r1 = r13.b     // Catch: java.lang.Throwable -> L70
            r1.close()     // Catch: java.lang.Throwable -> L70
            if (r10 == 0) goto L6f
            r10.close()     // Catch: java.lang.Throwable -> L70
        L6f:
            throw r0     // Catch: java.lang.Throwable -> L70
        L70:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.db.DBAdapter.getLastUninstallTimestamp():long");
    }

    @WorkerThread
    public synchronized ArrayList<CTMessageDAO> getMessages(String str) {
        ArrayList<CTMessageDAO> arrayList;
        String name = Table.INBOX_MESSAGES.getName();
        arrayList = new ArrayList<>();
        try {
            try {
                Cursor query = this.b.getWritableDatabase().query(name, null, "messageUser =?", new String[]{str}, null, null, "created_at DESC");
                if (query != null) {
                    while (query.moveToNext()) {
                        CTMessageDAO cTMessageDAO = new CTMessageDAO();
                        cTMessageDAO.setId(query.getString(query.getColumnIndex("_id")));
                        cTMessageDAO.setJsonData(new JSONObject(query.getString(query.getColumnIndex("data"))));
                        cTMessageDAO.setWzrkParams(new JSONObject(query.getString(query.getColumnIndex(Constants.KEY_WZRK_PARAMS))));
                        cTMessageDAO.setDate(query.getLong(query.getColumnIndex(MapplsLMSDbAdapter.KEY_CREATED_AT)));
                        cTMessageDAO.setExpires(query.getLong(query.getColumnIndex("expires")));
                        cTMessageDAO.setRead(query.getInt(query.getColumnIndex(Constants.KEY_IS_READ)));
                        cTMessageDAO.setUserId(query.getString(query.getColumnIndex("messageUser")));
                        cTMessageDAO.setTags(query.getString(query.getColumnIndex(Constants.KEY_TAGS)));
                        cTMessageDAO.setCampaignId(query.getString(query.getColumnIndex("campaignId")));
                        arrayList.add(cTMessageDAO);
                    }
                    query.close();
                }
                this.b.close();
            } catch (JSONException e2) {
                Logger y = y();
                y.verbose("Error retrieving records from " + name, e2.getMessage());
                this.b.close();
                return null;
            }
        } catch (SQLiteException e3) {
            Logger y2 = y();
            y2.verbose("Error retrieving records from " + name, e3);
            this.b.close();
            return null;
        }
        return arrayList;
    }

    @WorkerThread
    public synchronized boolean markReadMessageForId(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        Table table = Table.INBOX_MESSAGES;
        String name = table.getName();
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.KEY_IS_READ, (Integer) 1);
            writableDatabase.update(table.getName(), contentValues, "_id = ? AND messageUser = ?", new String[]{str, str2});
            this.b.close();
            return true;
        } catch (SQLiteException e2) {
            Logger y = y();
            y.verbose("Error removing stale records from " + name, e2);
            this.b.close();
            return false;
        }
    }

    @WorkerThread
    public synchronized boolean markReadMessagesForIds(ArrayList<String> arrayList, String str) {
        if (arrayList == null || str == null) {
            return false;
        }
        String name = Table.INBOX_MESSAGES.getName();
        try {
            StringBuilder sb = new StringBuilder();
            if (arrayList.size() > 0) {
                sb.append("?");
                for (int i2 = 0; i2 < arrayList.size() - 1; i2++) {
                    sb.append(", ?");
                }
            }
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size() + 1]);
            strArr[arrayList.size()] = str;
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.KEY_IS_READ, (Integer) 1);
            writableDatabase.update(Table.INBOX_MESSAGES.getName(), contentValues, "_id IN ( " + ((Object) sb) + " ) AND messageUser = ?", strArr);
            this.b.close();
            return true;
        } catch (SQLiteException e2) {
            y().verbose("Error removing stale records from " + name, e2);
            this.b.close();
            return false;
        }
    }

    @WorkerThread
    public final boolean q() {
        return this.b.a();
    }

    public final void r(Table table, long j2) {
        long currentTimeMillis = (System.currentTimeMillis() - j2) / 1000;
        String name = table.getName();
        try {
            try {
                SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
                writableDatabase.delete(name, "created_at <= " + currentTimeMillis, null);
            } catch (SQLiteException e2) {
                Logger y = y();
                y.verbose("Error removing stale event records from " + name + ". Recreating DB.", e2);
                v();
            }
        } finally {
            this.b.close();
        }
    }

    public synchronized void removeUserProfile(String str) {
        a aVar;
        if (str == null) {
            return;
        }
        String name = Table.USER_PROFILES.getName();
        try {
            this.b.getWritableDatabase().delete(name, "_id = ?", new String[]{str});
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error removing user profile from " + name + " Recreating DB");
            this.b.b();
            aVar = this.b;
        }
        aVar.close();
    }

    public synchronized void s() {
        r(Table.PUSH_NOTIFICATIONS, 0L);
    }

    public synchronized void storePushNotificationId(String str, long j2) {
        a aVar;
        if (str == null) {
            return;
        }
        if (!q()) {
            y().verbose("There is not enough space left on the device to store data, data discarded");
            return;
        }
        String name = Table.PUSH_NOTIFICATIONS.getName();
        if (j2 <= 0) {
            j2 = System.currentTimeMillis() + Constants.DEFAULT_PUSH_TTL;
        }
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", str);
            contentValues.put(MapplsLMSDbAdapter.KEY_CREATED_AT, Long.valueOf(j2));
            contentValues.put(Constants.KEY_IS_READ, (Integer) 0);
            writableDatabase.insert(name, null, contentValues);
            this.c = true;
            Logger.v("Stored PN - " + str + " with TTL - " + j2);
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error adding data to table " + name + " Recreating DB");
            this.b.b();
            aVar = this.b;
        }
        aVar.close();
    }

    public synchronized void storeUninstallTimestamp() {
        a aVar;
        if (!q()) {
            y().verbose("There is not enough space left on the device to store data, data discarded");
            return;
        }
        String name = Table.UNINSTALL_TS.getName();
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MapplsLMSDbAdapter.KEY_CREATED_AT, Long.valueOf(System.currentTimeMillis()));
            writableDatabase.insert(name, null, contentValues);
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error adding data to table " + name + " Recreating DB");
            this.b.b();
            aVar = this.b;
        }
        aVar.close();
    }

    @WorkerThread
    public synchronized long storeUserProfile(String str, JSONObject jSONObject) {
        a aVar;
        long j2 = -1;
        if (str == null) {
            return -1L;
        }
        if (!q()) {
            y().verbose("There is not enough space left on the device to store data, data discarded");
            return -2L;
        }
        String name = Table.USER_PROFILES.getName();
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", jSONObject.toString());
            contentValues.put("_id", str);
            j2 = writableDatabase.insertWithOnConflict(name, null, contentValues, 5);
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error adding data to table " + name + " Recreating DB");
            this.b.b();
            aVar = this.b;
        }
        aVar.close();
        return j2;
    }

    @WorkerThread
    public synchronized void t(String str, Table table) {
        a aVar;
        String name = table.getName();
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            writableDatabase.delete(name, "_id <= " + str, null);
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error removing sent data from table " + name + " Recreating DB");
            v();
            aVar = this.b;
        }
        aVar.close();
    }

    public synchronized void u(Table table) {
        r(table, 432000000L);
    }

    @WorkerThread
    public synchronized void updatePushNotificationIds(String[] strArr) {
        a aVar;
        if (strArr.length == 0) {
            return;
        }
        if (!q()) {
            Logger.v("There is not enough space left on the device to store data, data discarded");
            return;
        }
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.KEY_IS_READ, (Integer) 1);
            StringBuilder sb = new StringBuilder();
            sb.append("?");
            for (int i2 = 0; i2 < strArr.length - 1; i2++) {
                sb.append(", ?");
            }
            writableDatabase.update(Table.PUSH_NOTIFICATIONS.getName(), contentValues, "data IN ( " + sb.toString() + " )", strArr);
            this.c = false;
            aVar = this.b;
        } catch (SQLiteException unused) {
            y().verbose("Error adding data to table " + Table.PUSH_NOTIFICATIONS.getName() + " Recreating DB");
            this.b.b();
            aVar = this.b;
        }
        aVar.close();
    }

    @WorkerThread
    public synchronized void upsertMessages(ArrayList<CTMessageDAO> arrayList) {
        a aVar;
        if (!q()) {
            Logger.v("There is not enough space left on the device to store data, data discarded");
            return;
        }
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            Iterator<CTMessageDAO> it = arrayList.iterator();
            while (it.hasNext()) {
                CTMessageDAO next = it.next();
                ContentValues contentValues = new ContentValues();
                contentValues.put("_id", next.getId());
                contentValues.put("data", next.getJsonData().toString());
                contentValues.put(Constants.KEY_WZRK_PARAMS, next.getWzrkParams().toString());
                contentValues.put("campaignId", next.getCampaignId());
                contentValues.put(Constants.KEY_TAGS, next.getTags());
                contentValues.put(Constants.KEY_IS_READ, Integer.valueOf(next.isRead()));
                contentValues.put("expires", Long.valueOf(next.getExpires()));
                contentValues.put(MapplsLMSDbAdapter.KEY_CREATED_AT, Long.valueOf(next.getDate()));
                contentValues.put("messageUser", next.getUserId());
                writableDatabase.insertWithOnConflict(Table.INBOX_MESSAGES.getName(), null, contentValues, 5);
            }
            aVar = this.b;
        } catch (SQLiteException unused) {
            Logger y = y();
            y.verbose("Error adding data to table " + Table.INBOX_MESSAGES.getName());
            aVar = this.b;
        }
        aVar.close();
    }

    public final void v() {
        this.b.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0099 A[Catch: all -> 0x009d, TryCatch #6 {, blocks: (B:3:0x0001, B:14:0x004b, B:28:0x0084, B:23:0x0077, B:25:0x007e, B:35:0x0092, B:37:0x0099, B:38:0x009c), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized org.json.JSONObject w(com.clevertap.android.sdk.db.DBAdapter.Table r12, int r13) {
        /*
            r11 = this;
            monitor-enter(r11)
            java.lang.String r12 = r12.getName()     // Catch: java.lang.Throwable -> L9d
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L9d
            r9.<init>()     // Catch: java.lang.Throwable -> L9d
            r10 = 0
            com.clevertap.android.sdk.db.DBAdapter$a r0 = r11.b     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L58
            android.database.sqlite.SQLiteDatabase r0 = r0.getReadableDatabase()     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L58
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "created_at ASC"
            java.lang.String r8 = java.lang.String.valueOf(r13)     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L58
            r1 = r12
            android.database.Cursor r13 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L58
            r0 = r10
        L22:
            boolean r1 = r13.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            if (r1 == 0) goto L4b
            boolean r1 = r13.isLast()     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            if (r1 == 0) goto L38
            java.lang.String r0 = "_id"
            int r0 = r13.getColumnIndex(r0)     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            java.lang.String r0 = r13.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
        L38:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L22 android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            java.lang.String r2 = "data"
            int r2 = r13.getColumnIndex(r2)     // Catch: org.json.JSONException -> L22 android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            java.lang.String r2 = r13.getString(r2)     // Catch: org.json.JSONException -> L22 android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            r1.<init>(r2)     // Catch: org.json.JSONException -> L22 android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            r9.put(r1)     // Catch: org.json.JSONException -> L22 android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L90
            goto L22
        L4b:
            com.clevertap.android.sdk.db.DBAdapter$a r12 = r11.b     // Catch: java.lang.Throwable -> L9d
            r12.close()     // Catch: java.lang.Throwable -> L9d
            r13.close()     // Catch: java.lang.Throwable -> L9d
            goto L82
        L54:
            r0 = move-exception
            goto L5a
        L56:
            r12 = move-exception
            goto L92
        L58:
            r0 = move-exception
            r13 = r10
        L5a:
            com.clevertap.android.sdk.Logger r1 = r11.y()     // Catch: java.lang.Throwable -> L90
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L90
            r2.<init>()     // Catch: java.lang.Throwable -> L90
            java.lang.String r3 = "Could not fetch records out of database "
            r2.append(r3)     // Catch: java.lang.Throwable -> L90
            r2.append(r12)     // Catch: java.lang.Throwable -> L90
            java.lang.String r12 = "."
            r2.append(r12)     // Catch: java.lang.Throwable -> L90
            java.lang.String r12 = r2.toString()     // Catch: java.lang.Throwable -> L90
            r1.verbose(r12, r0)     // Catch: java.lang.Throwable -> L90
            com.clevertap.android.sdk.db.DBAdapter$a r12 = r11.b     // Catch: java.lang.Throwable -> L9d
            r12.close()     // Catch: java.lang.Throwable -> L9d
            if (r13 == 0) goto L81
            r13.close()     // Catch: java.lang.Throwable -> L9d
        L81:
            r0 = r10
        L82:
            if (r0 == 0) goto L8e
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch: org.json.JSONException -> L8e java.lang.Throwable -> L9d
            r12.<init>()     // Catch: org.json.JSONException -> L8e java.lang.Throwable -> L9d
            r12.put(r0, r9)     // Catch: org.json.JSONException -> L8e java.lang.Throwable -> L9d
            monitor-exit(r11)
            return r12
        L8e:
            monitor-exit(r11)
            return r10
        L90:
            r12 = move-exception
            r10 = r13
        L92:
            com.clevertap.android.sdk.db.DBAdapter$a r13 = r11.b     // Catch: java.lang.Throwable -> L9d
            r13.close()     // Catch: java.lang.Throwable -> L9d
            if (r10 == 0) goto L9c
            r10.close()     // Catch: java.lang.Throwable -> L9d
        L9c:
            throw r12     // Catch: java.lang.Throwable -> L9d
        L9d:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.db.DBAdapter.w(com.clevertap.android.sdk.db.DBAdapter$Table, int):org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x004c, code lost:
        if (r9 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004e, code lost:
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0077, code lost:
        if (r9 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.lang.String x(java.lang.String r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            com.clevertap.android.sdk.db.DBAdapter$Table r0 = com.clevertap.android.sdk.db.DBAdapter.Table.PUSH_NOTIFICATIONS     // Catch: java.lang.Throwable -> L87
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> L87
            r9 = 0
            java.lang.String r10 = ""
            com.clevertap.android.sdk.db.DBAdapter$a r1 = r11.b     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            android.database.sqlite.SQLiteDatabase r1 = r1.getReadableDatabase()     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            r3 = 0
            java.lang.String r4 = "data =?"
            r2 = 1
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            r2 = 0
            r5[r2] = r12     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            r6 = 0
            r7 = 0
            r8 = 0
            r2 = r0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            if (r9 == 0) goto L33
            boolean r12 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            if (r12 == 0) goto L33
            java.lang.String r12 = "data"
            int r12 = r9.getColumnIndex(r12)     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            java.lang.String r10 = r9.getString(r12)     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
        L33:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            r12.<init>()     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            java.lang.String r1 = "Fetching PID for check - "
            r12.append(r1)     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            r12.append(r10)     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            com.clevertap.android.sdk.Logger.v(r12)     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L54
            com.clevertap.android.sdk.db.DBAdapter$a r12 = r11.b     // Catch: java.lang.Throwable -> L87
            r12.close()     // Catch: java.lang.Throwable -> L87
            if (r9 == 0) goto L7a
        L4e:
            r9.close()     // Catch: java.lang.Throwable -> L87
            goto L7a
        L52:
            r12 = move-exception
            goto L7c
        L54:
            r12 = move-exception
            com.clevertap.android.sdk.Logger r1 = r11.y()     // Catch: java.lang.Throwable -> L52
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52
            r2.<init>()     // Catch: java.lang.Throwable -> L52
            java.lang.String r3 = "Could not fetch records out of database "
            r2.append(r3)     // Catch: java.lang.Throwable -> L52
            r2.append(r0)     // Catch: java.lang.Throwable -> L52
            java.lang.String r0 = "."
            r2.append(r0)     // Catch: java.lang.Throwable -> L52
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L52
            r1.verbose(r0, r12)     // Catch: java.lang.Throwable -> L52
            com.clevertap.android.sdk.db.DBAdapter$a r12 = r11.b     // Catch: java.lang.Throwable -> L87
            r12.close()     // Catch: java.lang.Throwable -> L87
            if (r9 == 0) goto L7a
            goto L4e
        L7a:
            monitor-exit(r11)
            return r10
        L7c:
            com.clevertap.android.sdk.db.DBAdapter$a r0 = r11.b     // Catch: java.lang.Throwable -> L87
            r0.close()     // Catch: java.lang.Throwable -> L87
            if (r9 == 0) goto L86
            r9.close()     // Catch: java.lang.Throwable -> L87
        L86:
            throw r12     // Catch: java.lang.Throwable -> L87
        L87:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.db.DBAdapter.x(java.lang.String):java.lang.String");
    }

    public final Logger y() {
        return this.f2604a.getLogger();
    }

    public DBAdapter(Context context, String str) {
        this.c = true;
        this.b = new a(context, str);
    }
}
