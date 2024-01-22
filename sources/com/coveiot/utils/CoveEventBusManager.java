package com.coveiot.utils;

import com.squareup.otto.Bus;
/* loaded from: classes9.dex */
public class CoveEventBusManager {
    public static final CoveEventBusManager b = new CoveEventBusManager();

    /* renamed from: a  reason: collision with root package name */
    public Bus f7606a = new Bus();

    public static CoveEventBusManager getInstance() {
        return b;
    }

    public Bus getEventBus() {
        return this.f7606a;
    }
}
