package com.clevertap.android.sdk.db;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.events.EventGroup;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DBManager extends BaseDatabaseManager {

    /* renamed from: a  reason: collision with root package name */
    public DBAdapter f2605a;
    public final CTLockManager b;
    public final CleverTapInstanceConfig c;

    public DBManager(CleverTapInstanceConfig cleverTapInstanceConfig, CTLockManager cTLockManager) {
        this.c = cleverTapInstanceConfig;
        this.b = cTLockManager;
    }

    public final void a(Context context) {
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_FIRST_TS), 0);
    }

    public final void b(Context context) {
        SharedPreferences.Editor edit = StorageHelper.getPreferences(context, Constants.NAMESPACE_IJ).edit();
        edit.clear();
        StorageHelper.persist(edit);
    }

    public final void c(Context context) {
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_LAST_TS), 0);
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public void clearQueues(Context context) {
        synchronized (this.b.getEventLock()) {
            DBAdapter loadDBAdapter = loadDBAdapter(context);
            loadDBAdapter.A(DBAdapter.Table.EVENTS);
            loadDBAdapter.A(DBAdapter.Table.PROFILE_EVENTS);
            d(context);
        }
    }

    public final void d(Context context) {
        b(context);
        a(context);
        c(context);
    }

    public QueueCursor e(Context context, int i, QueueCursor queueCursor) {
        return f(context, DBAdapter.Table.PUSH_NOTIFICATION_VIEWED, i, queueCursor);
    }

    public QueueCursor f(Context context, DBAdapter.Table table, int i, QueueCursor queueCursor) {
        QueueCursor i2;
        synchronized (this.b.getEventLock()) {
            DBAdapter loadDBAdapter = loadDBAdapter(context);
            if (queueCursor != null) {
                table = queueCursor.b();
            }
            if (queueCursor != null) {
                loadDBAdapter.t(queueCursor.a(), queueCursor.b());
            }
            QueueCursor queueCursor2 = new QueueCursor();
            queueCursor2.e(table);
            i2 = i(loadDBAdapter.w(table, i), queueCursor2);
        }
        return i2;
    }

    public QueueCursor g(Context context, int i, QueueCursor queueCursor) {
        QueueCursor queueCursor2;
        synchronized (this.b.getEventLock()) {
            DBAdapter.Table table = DBAdapter.Table.EVENTS;
            QueueCursor f = f(context, table, i, queueCursor);
            queueCursor2 = null;
            if (f.isEmpty().booleanValue() && f.b().equals(table)) {
                f = f(context, DBAdapter.Table.PROFILE_EVENTS, i, null);
            }
            if (!f.isEmpty().booleanValue()) {
                queueCursor2 = f;
            }
        }
        return queueCursor2;
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public QueueCursor getQueuedEvents(Context context, int i, QueueCursor queueCursor, EventGroup eventGroup) {
        if (eventGroup == EventGroup.PUSH_NOTIFICATION_VIEWED) {
            this.c.getLogger().verbose(this.c.getAccountId(), "Returning Queued Notification Viewed events");
            return e(context, i, queueCursor);
        }
        this.c.getLogger().verbose(this.c.getAccountId(), "Returning Queued events");
        return g(context, i, queueCursor);
    }

    @WorkerThread
    public final void h(Context context, JSONObject jSONObject, DBAdapter.Table table) {
        synchronized (this.b.getEventLock()) {
            if (loadDBAdapter(context).B(jSONObject, table) > 0) {
                Logger logger = this.c.getLogger();
                String accountId = this.c.getAccountId();
                logger.debug(accountId, "Queued event: " + jSONObject.toString());
                Logger logger2 = this.c.getLogger();
                String accountId2 = this.c.getAccountId();
                logger2.verbose(accountId2, "Queued event to DB table " + table + ": " + jSONObject.toString());
            }
        }
    }

    public QueueCursor i(JSONObject jSONObject, QueueCursor queueCursor) {
        if (jSONObject == null) {
            return queueCursor;
        }
        Iterator<String> keys = jSONObject.keys();
        if (keys.hasNext()) {
            String next = keys.next();
            queueCursor.d(next);
            try {
                queueCursor.c(jSONObject.getJSONArray(next));
            } catch (JSONException unused) {
                queueCursor.d(null);
                queueCursor.c(null);
            }
        }
        return queueCursor;
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    @WorkerThread
    public DBAdapter loadDBAdapter(Context context) {
        if (this.f2605a == null) {
            DBAdapter dBAdapter = new DBAdapter(context, this.c);
            this.f2605a = dBAdapter;
            dBAdapter.u(DBAdapter.Table.EVENTS);
            this.f2605a.u(DBAdapter.Table.PROFILE_EVENTS);
            this.f2605a.u(DBAdapter.Table.PUSH_NOTIFICATION_VIEWED);
            this.f2605a.s();
        }
        return this.f2605a;
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    @WorkerThread
    public void queueEventToDB(Context context, JSONObject jSONObject, int i) {
        DBAdapter.Table table;
        if (i == 3) {
            table = DBAdapter.Table.PROFILE_EVENTS;
        } else {
            table = DBAdapter.Table.EVENTS;
        }
        h(context, jSONObject, table);
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    @WorkerThread
    public void queuePushNotificationViewedEventToDB(Context context, JSONObject jSONObject) {
        h(context, jSONObject, DBAdapter.Table.PUSH_NOTIFICATION_VIEWED);
    }
}
