package com.coveiot.sdk.ble.eventbus;

import com.squareup.otto.Bus;
import java.util.HashMap;
/* loaded from: classes9.dex */
public class BleEventBusManager {
    public static BleEventBusManager c = new BleEventBusManager();

    /* renamed from: a  reason: collision with root package name */
    public Bus f7568a = new BleEventBus();
    public HashMap b = new HashMap();

    public static BleEventBusManager getInstance() {
        return c;
    }

    public Bus getEventBus() {
        return this.f7568a;
    }

    public synchronized boolean isRegistered(Object obj) {
        return this.b.containsKey(obj);
    }
}
