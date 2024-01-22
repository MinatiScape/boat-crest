package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata
/* loaded from: classes12.dex */
public final class NativeCallbackDispatcher_Factory implements Factory<l> {

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final NativeCallbackDispatcher_Factory f13421a = new NativeCallbackDispatcher_Factory();
    }

    public static NativeCallbackDispatcher_Factory create() {
        return a.f13421a;
    }

    public static l newInstance() {
        return new l();
    }

    @Override // bleshadow.javax.inject.Provider
    public l get() {
        return newInstance();
    }
}
