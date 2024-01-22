package com.coveiot.android.crpsdk.eventbus;

import com.squareup.otto.Bus;
import java.util.HashMap;
/* loaded from: classes3.dex */
public class CRPBleEventBusManager {
    public static CRPBleEventBusManager c = new CRPBleEventBusManager();

    /* renamed from: a  reason: collision with root package name */
    public Bus f4118a = new CRPBleEventBus();
    public HashMap b = new HashMap();

    public static CRPBleEventBusManager getInstance() {
        return c;
    }

    public Bus getEventBus() {
        return this.f4118a;
    }

    public synchronized boolean isRegistered(Object obj) {
        return this.b.containsKey(obj);
    }
}
