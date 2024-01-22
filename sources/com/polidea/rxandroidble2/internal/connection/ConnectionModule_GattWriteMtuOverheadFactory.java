package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ConnectionModule_GattWriteMtuOverheadFactory implements Factory<Integer> {

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ConnectionModule_GattWriteMtuOverheadFactory f13400a = new ConnectionModule_GattWriteMtuOverheadFactory();
    }

    public static ConnectionModule_GattWriteMtuOverheadFactory create() {
        return a.f13400a;
    }

    public static int gattWriteMtuOverhead() {
        return ConnectionModule.a();
    }

    @Override // bleshadow.javax.inject.Provider
    public Integer get() {
        return Integer.valueOf(gattWriteMtuOverhead());
    }
}
