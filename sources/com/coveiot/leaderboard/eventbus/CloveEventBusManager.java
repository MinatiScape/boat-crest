package com.coveiot.leaderboard.eventbus;

import com.squareup.otto.Bus;
/* loaded from: classes9.dex */
public class CloveEventBusManager {
    public static CloveEventBusManager b = new CloveEventBusManager();

    /* renamed from: a  reason: collision with root package name */
    public Bus f7208a = new CloveEventBus();

    public static CloveEventBusManager getInstance() {
        return b;
    }

    public Bus getEventBus() {
        return this.f7208a;
    }
}
