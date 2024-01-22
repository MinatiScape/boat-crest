package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.eventbus.EventBus;
import com.squareup.otto.Bus;
import java.util.concurrent.Executor;
@Beta
/* loaded from: classes10.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String str, Executor executor) {
        super(str, executor, a.b(), EventBus.a.f10608a);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(Bus.DEFAULT_IDENTIFIER, executor, a.b(), subscriberExceptionHandler);
    }

    public AsyncEventBus(Executor executor) {
        super(Bus.DEFAULT_IDENTIFIER, executor, a.b(), EventBus.a.f10608a);
    }
}
