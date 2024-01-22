package com.abupdate.mqtt_libs.mqtt_service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.abupdate.mqtt_libs.mqtt_service.c;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import java.util.Iterator;
import java.util.UUID;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes.dex */
public class b implements com.abupdate.mqtt_libs.mqtt_service.c {

    /* renamed from: a  reason: collision with root package name */
    public SQLiteDatabase f1933a = null;
    public c b;
    public MqttTraceHandler c;

    /* loaded from: classes.dex */
    public class a implements Iterator<c.a> {
        public Cursor h;
        public boolean i;
        public final String[] j;
        public final /* synthetic */ String k;

        public a(String str) {
            this.k = str;
            String[] strArr = {str};
            this.j = strArr;
            b.this.f1933a = b.this.b.getWritableDatabase();
            if (str == null) {
                this.h = b.this.f1933a.query("MqttArrivedMessageTable", null, null, null, null, null, "mtimestamp ASC");
            } else {
                this.h = b.this.f1933a.query("MqttArrivedMessageTable", null, "clientHandle=?", strArr, null, null, "mtimestamp ASC");
            }
            this.i = this.h.moveToFirst();
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public c.a next() {
            Cursor cursor = this.h;
            String string = cursor.getString(cursor.getColumnIndex(MqttServiceConstants.MESSAGE_ID));
            Cursor cursor2 = this.h;
            String string2 = cursor2.getString(cursor2.getColumnIndex(MqttServiceConstants.CLIENT_HANDLE));
            Cursor cursor3 = this.h;
            String string3 = cursor3.getString(cursor3.getColumnIndex(MqttServiceConstants.DESTINATION_NAME));
            Cursor cursor4 = this.h;
            byte[] blob = cursor4.getBlob(cursor4.getColumnIndex(MqttServiceConstants.PAYLOAD));
            Cursor cursor5 = this.h;
            int i = cursor5.getInt(cursor5.getColumnIndex(MqttServiceConstants.QOS));
            Cursor cursor6 = this.h;
            boolean parseBoolean = Boolean.parseBoolean(cursor6.getString(cursor6.getColumnIndex(MqttServiceConstants.RETAINED)));
            Cursor cursor7 = this.h;
            boolean parseBoolean2 = Boolean.parseBoolean(cursor7.getString(cursor7.getColumnIndex(MqttServiceConstants.DUPLICATE)));
            d dVar = new d(b.this, blob);
            dVar.setQos(i);
            dVar.setRetained(parseBoolean);
            dVar.setDuplicate(parseBoolean2);
            this.i = this.h.moveToNext();
            return new C0196b(b.this, string, string2, string3, dVar);
        }

        public void finalize() throws Throwable {
            this.h.close();
            super.finalize();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.i) {
                this.h.close();
            }
            return this.i;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: com.abupdate.mqtt_libs.mqtt_service.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0196b implements c.a {

        /* renamed from: a  reason: collision with root package name */
        public String f1934a;
        public String b;
        public MqttMessage c;

        public C0196b(b bVar, String str, String str2, String str3, MqttMessage mqttMessage) {
            this.f1934a = str;
            this.b = str3;
            this.c = mqttMessage;
        }

        @Override // com.abupdate.mqtt_libs.mqtt_service.c.a
        public String a() {
            return this.f1934a;
        }

        @Override // com.abupdate.mqtt_libs.mqtt_service.c.a
        public String b() {
            return this.b;
        }

        @Override // com.abupdate.mqtt_libs.mqtt_service.c.a
        public MqttMessage c() {
            return this.c;
        }
    }

    /* loaded from: classes.dex */
    public static class c extends SQLiteOpenHelper {
        public MqttTraceHandler h;

        public c(MqttTraceHandler mqttTraceHandler, Context context) {
            super(context, "mqttAndroidService.db", (SQLiteDatabase.CursorFactory) null, 1);
            this.h = null;
            this.h = mqttTraceHandler;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            MqttTraceHandler mqttTraceHandler = this.h;
            mqttTraceHandler.traceDebug("MQTTDatabaseHelper", "onCreate {CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);}");
            try {
                sQLiteDatabase.execSQL("CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);");
                this.h.traceDebug("MQTTDatabaseHelper", "created the table");
            } catch (SQLException e) {
                this.h.traceException("MQTTDatabaseHelper", "onCreate", e);
                throw e;
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.h.traceDebug("MQTTDatabaseHelper", "onUpgrade");
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS MqttArrivedMessageTable");
                onCreate(sQLiteDatabase);
                this.h.traceDebug("MQTTDatabaseHelper", "onUpgrade complete");
            } catch (SQLException e) {
                this.h.traceException("MQTTDatabaseHelper", "onUpgrade", e);
                throw e;
            }
        }
    }

    /* loaded from: classes.dex */
    public class d extends MqttMessage {
        public d(b bVar, byte[] bArr) {
            super(bArr);
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.MqttMessage
        public void setDuplicate(boolean z) {
            super.setDuplicate(z);
        }
    }

    public b(MqttService mqttService, Context context) {
        this.b = null;
        this.c = null;
        this.c = mqttService;
        this.b = new c(this.c, context);
        this.c.traceDebug("DatabaseMessageStore", "DatabaseMessageStore<init> complete");
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.c
    public String a(String str, String str2, MqttMessage mqttMessage) {
        this.f1933a = this.b.getWritableDatabase();
        MqttTraceHandler mqttTraceHandler = this.c;
        mqttTraceHandler.traceDebug("DatabaseMessageStore", "storeArrived{" + str + "}, {" + mqttMessage.toString() + "}");
        byte[] payload = mqttMessage.getPayload();
        int qos = mqttMessage.getQos();
        boolean isRetained = mqttMessage.isRetained();
        boolean isDuplicate = mqttMessage.isDuplicate();
        ContentValues contentValues = new ContentValues();
        String uuid = UUID.randomUUID().toString();
        contentValues.put(MqttServiceConstants.MESSAGE_ID, uuid);
        contentValues.put(MqttServiceConstants.CLIENT_HANDLE, str);
        contentValues.put(MqttServiceConstants.DESTINATION_NAME, str2);
        contentValues.put(MqttServiceConstants.PAYLOAD, payload);
        contentValues.put(MqttServiceConstants.QOS, Integer.valueOf(qos));
        contentValues.put(MqttServiceConstants.RETAINED, Boolean.valueOf(isRetained));
        contentValues.put(MqttServiceConstants.DUPLICATE, Boolean.valueOf(isDuplicate));
        contentValues.put("mtimestamp", Long.valueOf(System.currentTimeMillis()));
        try {
            this.f1933a.insertOrThrow("MqttArrivedMessageTable", null, contentValues);
            int e = e(str);
            MqttTraceHandler mqttTraceHandler2 = this.c;
            mqttTraceHandler2.traceDebug("DatabaseMessageStore", "storeArrived: inserted message with id of {" + uuid + "} - Number of messages in database for this clientHandle = " + e);
            return uuid;
        } catch (SQLException e2) {
            this.c.traceException("DatabaseMessageStore", "onUpgrade", e2);
            throw e2;
        }
    }

    public final int e(String str) {
        Cursor query = this.f1933a.query("MqttArrivedMessageTable", new String[]{MqttServiceConstants.MESSAGE_ID}, "clientHandle=?", new String[]{str}, null, null, null);
        int i = query.moveToFirst() ? query.getInt(0) : 0;
        query.close();
        return i;
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.c
    public void b(String str) {
        int delete;
        this.f1933a = this.b.getWritableDatabase();
        String[] strArr = {str};
        if (str == null) {
            this.c.traceDebug("DatabaseMessageStore", "clearArrivedMessages: clearing the table");
            delete = this.f1933a.delete("MqttArrivedMessageTable", null, null);
        } else {
            MqttTraceHandler mqttTraceHandler = this.c;
            mqttTraceHandler.traceDebug("DatabaseMessageStore", "clearArrivedMessages: clearing the table of " + str + " messages");
            delete = this.f1933a.delete("MqttArrivedMessageTable", "clientHandle=?", strArr);
        }
        MqttTraceHandler mqttTraceHandler2 = this.c;
        mqttTraceHandler2.traceDebug("DatabaseMessageStore", "clearArrivedMessages: rows affected = " + delete);
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.c
    public boolean a(String str, String str2) {
        this.f1933a = this.b.getWritableDatabase();
        MqttTraceHandler mqttTraceHandler = this.c;
        mqttTraceHandler.traceDebug("DatabaseMessageStore", "discardArrived{" + str + "}, {" + str2 + "}");
        try {
            int delete = this.f1933a.delete("MqttArrivedMessageTable", "messageId=? AND clientHandle=?", new String[]{str2, str});
            if (delete != 1) {
                MqttTraceHandler mqttTraceHandler2 = this.c;
                mqttTraceHandler2.traceError("DatabaseMessageStore", "discardArrived - Error deleting message {" + str2 + "} from database: Rows affected = " + delete);
                return false;
            }
            int e = e(str);
            MqttTraceHandler mqttTraceHandler3 = this.c;
            mqttTraceHandler3.traceDebug("DatabaseMessageStore", "discardArrived - Message deleted successfully. - messages in db for this clientHandle " + e);
            return true;
        } catch (SQLException e2) {
            this.c.traceException("DatabaseMessageStore", "discardArrived", e2);
            throw e2;
        }
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.c
    public Iterator<c.a> a(String str) {
        return new a(str);
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.c
    public void a() {
        SQLiteDatabase sQLiteDatabase = this.f1933a;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }
}
