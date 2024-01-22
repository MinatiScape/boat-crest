package com.clevertap.android.sdk.db;

import android.content.Context;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.events.EventGroup;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public abstract class BaseDatabaseManager {
    public abstract void clearQueues(Context context);

    public abstract QueueCursor getQueuedEvents(Context context, int i, QueueCursor queueCursor, EventGroup eventGroup);

    public abstract DBAdapter loadDBAdapter(Context context);

    public abstract void queueEventToDB(Context context, JSONObject jSONObject, int i);

    public abstract void queuePushNotificationViewedEventToDB(Context context, JSONObject jSONObject);
}
