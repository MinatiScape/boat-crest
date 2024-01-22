package com.clevertap.android.sdk.network;

import android.content.Context;
import com.clevertap.android.sdk.events.EventGroup;
import org.json.JSONArray;
/* loaded from: classes2.dex */
public abstract class BaseNetworkManager {
    public abstract void flushDBQueue(Context context, EventGroup eventGroup, String str);

    public abstract int getDelayFrequency();

    public abstract void initHandshake(EventGroup eventGroup, Runnable runnable);

    public abstract boolean needsHandshakeForDomain(EventGroup eventGroup);

    public abstract boolean sendQueue(Context context, EventGroup eventGroup, JSONArray jSONArray, String str);
}
