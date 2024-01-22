package com.touchgui.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.touchgui.sdk.TGBleClient;
import com.touchgui.sdk.TGBleManager;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class a extends TGBleManager {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13730a;
    public final ConcurrentHashMap b = new ConcurrentHashMap();
    public final CopyOnWriteArrayList c = new CopyOnWriteArrayList();
    public final boolean d;
    public final int e;
    public final int f;
    public final int g;

    public a(Context context, TGBleManager.Builder builder) {
        this.f13730a = context;
        this.d = builder.disableAutoReconnect;
        this.e = builder.otaRetryCount;
        this.f = builder.maxTimeoutErrorCount;
        this.g = builder.maxReconnectCount;
    }

    @Override // com.touchgui.sdk.TGBleManager
    public final synchronized TGConnection getConnection(String str) {
        com.touchgui.sdk.internal.b0 b0Var = (com.touchgui.sdk.internal.b0) this.b.get(str);
        if (b0Var == null || TextUtils.isEmpty(b0Var.d)) {
            if (b0Var == null) {
                Iterator it = this.b.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    com.touchgui.sdk.internal.b0 b0Var2 = (com.touchgui.sdk.internal.b0) ((Map.Entry) it.next()).getValue();
                    if (TextUtils.isEmpty(b0Var2.d)) {
                        it.remove();
                        b0Var = b0Var2;
                        break;
                    }
                }
            }
            if (b0Var != null) {
                b0Var.d = str;
                this.b.put(str, b0Var);
                return b0Var;
            }
            TGBleClient.Builder newBuilder = TGBleClient.newBuilder(this.f13730a);
            if (this.d) {
                newBuilder.disableAutoReconnect();
            }
            newBuilder.setOtaRetryCount(this.e);
            newBuilder.setMaxTimeoutErrorCount(this.f);
            newBuilder.setMaxReconnectCount(this.g);
            b0Var = newBuilder.innerBuild();
            b0Var.d = str;
            b0Var.addConnectionListener(new com.touchgui.sdk.internal.h(this, str));
            this.b.put(str, b0Var);
        }
        return b0Var;
    }

    @Override // com.touchgui.sdk.TGBleManager
    public final synchronized boolean hasConnection(String str) {
        return this.b.get(str) != null;
    }

    @Override // com.touchgui.sdk.TGBleManager
    public final synchronized void recycleConnection(String str) {
        TGClient tGClient = (TGClient) this.b.get(str);
        if (tGClient != null) {
            tGClient.release();
        }
    }

    @Override // com.touchgui.sdk.TGBleManager
    public final void registerConnectCallback(TGConnectCallback tGConnectCallback) {
        if (this.c.contains(tGConnectCallback)) {
            return;
        }
        this.c.add(tGConnectCallback);
    }

    @Override // com.touchgui.sdk.TGBleManager
    public final void unregisterConnectCallback(TGConnectCallback tGConnectCallback) {
        this.c.remove(tGConnectCallback);
    }
}
