package org.eclipse.paho.android.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.UUID;
import org.eclipse.paho.android.service.MessageStore;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/* loaded from: classes13.dex */
public class DatabaseMessageStore implements MessageStore {

    /* renamed from: a  reason: collision with root package name */
    public SQLiteDatabase f15428a = null;
    public MQTTDatabaseHelper b;
    public MqttTraceHandler c;

    /* loaded from: classes13.dex */
    public class DbStoredData implements MessageStore.StoredMessage {

        /* renamed from: a  reason: collision with root package name */
        public String f15429a;
        public String b;
        public String c;
        public MqttMessage d;

        public DbStoredData(String str, String str2, String str3, MqttMessage mqttMessage) {
            this.f15429a = str;
            this.c = str3;
            this.d = mqttMessage;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public String getClientHandle() {
            return this.b;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public MqttMessage getMessage() {
            return this.d;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public String getMessageId() {
            return this.f15429a;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public String getTopic() {
            return this.c;
        }
    }

    /* loaded from: classes13.dex */
    public static class MQTTDatabaseHelper extends SQLiteOpenHelper {
        public MqttTraceHandler h;

        public MQTTDatabaseHelper(MqttTraceHandler mqttTraceHandler, Context context) {
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

    /* loaded from: classes13.dex */
    public class MqttMessageHack extends MqttMessage {
        public MqttMessageHack(byte[] bArr) {
            super(bArr);
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttMessage
        public void setDuplicate(boolean z) {
            super.setDuplicate(z);
        }
    }

    public DatabaseMessageStore(MqttService mqttService, Context context) {
        this.b = null;
        this.c = null;
        this.c = mqttService;
        this.b = new MQTTDatabaseHelper(this.c, context);
        this.c.traceDebug("DatabaseMessageStore", "DatabaseMessageStore<init> complete");
    }

    public final int a(String str) {
        Cursor query = this.f15428a.query("MqttArrivedMessageTable", new String[]{MqttServiceConstants.MESSAGE_ID}, "clientHandle=?", new String[]{str}, null, null, null);
        int i = query.moveToFirst() ? query.getInt(0) : 0;
        query.close();
        return i;
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public void clearArrivedMessages(String str) {
        int delete;
        this.f15428a = this.b.getWritableDatabase();
        String[] strArr = {str};
        if (str == null) {
            this.c.traceDebug("DatabaseMessageStore", "clearArrivedMessages: clearing the table");
            delete = this.f15428a.delete("MqttArrivedMessageTable", null, null);
        } else {
            MqttTraceHandler mqttTraceHandler = this.c;
            mqttTraceHandler.traceDebug("DatabaseMessageStore", "clearArrivedMessages: clearing the table of " + str + " messages");
            delete = this.f15428a.delete("MqttArrivedMessageTable", "clientHandle=?", strArr);
        }
        MqttTraceHandler mqttTraceHandler2 = this.c;
        mqttTraceHandler2.traceDebug("DatabaseMessageStore", "clearArrivedMessages: rows affected = " + delete);
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public void close() {
        SQLiteDatabase sQLiteDatabase = this.f15428a;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public boolean discardArrived(String str, String str2) {
        this.f15428a = this.b.getWritableDatabase();
        MqttTraceHandler mqttTraceHandler = this.c;
        mqttTraceHandler.traceDebug("DatabaseMessageStore", "discardArrived{" + str + "}, {" + str2 + "}");
        try {
            int delete = this.f15428a.delete("MqttArrivedMessageTable", "messageId=? AND clientHandle=?", new String[]{str2, str});
            if (delete == 1) {
                int a2 = a(str);
                MqttTraceHandler mqttTraceHandler2 = this.c;
                mqttTraceHandler2.traceDebug("DatabaseMessageStore", "discardArrived - Message deleted successfully. - messages in db for this clientHandle " + a2);
                return true;
            }
            MqttTraceHandler mqttTraceHandler3 = this.c;
            mqttTraceHandler3.traceError("DatabaseMessageStore", "discardArrived - Error deleting message {" + str2 + "} from database: Rows affected = " + delete);
            return false;
        } catch (SQLException e) {
            this.c.traceException("DatabaseMessageStore", "discardArrived", e);
            throw e;
        }
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public Iterator<MessageStore.StoredMessage> getAllArrivedMessages(String str) {
        return new Iterator<MessageStore.StoredMessage>(str) { // from class: org.eclipse.paho.android.service.DatabaseMessageStore.1
            public Cursor h;
            public boolean i;
            public final /* synthetic */ String val$clientHandle;

            {
                this.val$clientHandle = str;
                String[] strArr = {str};
                DatabaseMessageStore.this.f15428a = DatabaseMessageStore.this.b.getWritableDatabase();
                this.h = str == null ? DatabaseMessageStore.this.f15428a.query("MqttArrivedMessageTable", null, null, null, null, null, "mtimestamp ASC") : DatabaseMessageStore.this.f15428a.query("MqttArrivedMessageTable", null, "clientHandle=?", strArr, null, null, "mtimestamp ASC");
                this.i = this.h.moveToFirst();
            }

            public void finalize() {
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
            public MessageStore.StoredMessage next() {
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
                MqttMessageHack mqttMessageHack = new MqttMessageHack(blob);
                mqttMessageHack.setQos(i);
                mqttMessageHack.setRetained(parseBoolean);
                mqttMessageHack.setDuplicate(parseBoolean2);
                this.i = this.h.moveToNext();
                return new DbStoredData(string, string2, string3, mqttMessageHack);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public String storeArrived(String str, String str2, MqttMessage mqttMessage) {
        this.f15428a = this.b.getWritableDatabase();
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
            this.f15428a.insertOrThrow("MqttArrivedMessageTable", null, contentValues);
            int a2 = a(str);
            MqttTraceHandler mqttTraceHandler2 = this.c;
            mqttTraceHandler2.traceDebug("DatabaseMessageStore", "storeArrived: inserted message with id of {" + uuid + "} - Number of messages in database for this clientHandle = " + a2);
            return uuid;
        } catch (SQLException e) {
            this.c.traceException("DatabaseMessageStore", "onUpgrade", e);
            throw e;
        }
    }
}
