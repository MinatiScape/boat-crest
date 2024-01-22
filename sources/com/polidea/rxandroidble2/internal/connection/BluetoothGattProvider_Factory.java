package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
@DaggerGenerated
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@QualifierMetadata
/* loaded from: classes12.dex */
public final class BluetoothGattProvider_Factory implements Factory<BluetoothGattProvider> {

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final BluetoothGattProvider_Factory f13399a = new BluetoothGattProvider_Factory();
    }

    public static BluetoothGattProvider_Factory create() {
        return a.f13399a;
    }

    public static BluetoothGattProvider newInstance() {
        return new BluetoothGattProvider();
    }

    @Override // bleshadow.javax.inject.Provider
    public BluetoothGattProvider get() {
        return newInstance();
    }
}
