package com.google.firebase.heartbeatinfo;

import com.google.firebase.components.Component;
/* loaded from: classes10.dex */
public class HeartBeatConsumerComponent {

    /* loaded from: classes10.dex */
    public class a implements HeartBeatConsumer {
    }

    public static Component<?> create() {
        return Component.intoSet(new a(), HeartBeatConsumer.class);
    }
}
