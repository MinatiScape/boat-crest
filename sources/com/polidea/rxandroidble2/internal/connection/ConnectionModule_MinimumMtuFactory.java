package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ConnectionModule_MinimumMtuFactory implements Factory<Integer> {

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ConnectionModule_MinimumMtuFactory f13401a = new ConnectionModule_MinimumMtuFactory();
    }

    public static ConnectionModule_MinimumMtuFactory create() {
        return a.f13401a;
    }

    public static int minimumMtu() {
        return ConnectionModule.b();
    }

    @Override // bleshadow.javax.inject.Provider
    public Integer get() {
        return Integer.valueOf(minimumMtu());
    }
}
