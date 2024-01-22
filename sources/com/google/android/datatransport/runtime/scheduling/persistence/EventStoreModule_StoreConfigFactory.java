package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class EventStoreModule_StoreConfigFactory implements Factory<c> {

    /* loaded from: classes6.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final EventStoreModule_StoreConfigFactory f8133a = new EventStoreModule_StoreConfigFactory();
    }

    public static EventStoreModule_StoreConfigFactory create() {
        return a.f8133a;
    }

    public static c storeConfig() {
        return (c) Preconditions.checkNotNull(EventStoreModule.c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public c get() {
        return storeConfig();
    }
}
