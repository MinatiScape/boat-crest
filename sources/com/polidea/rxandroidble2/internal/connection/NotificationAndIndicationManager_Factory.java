package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata("com.polidea.rxandroidble2.internal.connection.ConnectionScope")
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class NotificationAndIndicationManager_Factory implements Factory<w> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<byte[]> f13422a;
    public final Provider<byte[]> b;
    public final Provider<byte[]> c;
    public final Provider<BluetoothGatt> d;
    public final Provider<RxBleGattCallback> e;
    public final Provider<f> f;

    public NotificationAndIndicationManager_Factory(Provider<byte[]> provider, Provider<byte[]> provider2, Provider<byte[]> provider3, Provider<BluetoothGatt> provider4, Provider<RxBleGattCallback> provider5, Provider<f> provider6) {
        this.f13422a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
        this.f = provider6;
    }

    public static NotificationAndIndicationManager_Factory create(Provider<byte[]> provider, Provider<byte[]> provider2, Provider<byte[]> provider3, Provider<BluetoothGatt> provider4, Provider<RxBleGattCallback> provider5, Provider<f> provider6) {
        return new NotificationAndIndicationManager_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static w newInstance(byte[] bArr, byte[] bArr2, byte[] bArr3, BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Object obj) {
        return new w(bArr, bArr2, bArr3, bluetoothGatt, rxBleGattCallback, (f) obj);
    }

    @Override // bleshadow.javax.inject.Provider
    public w get() {
        return newInstance(this.f13422a.get(), this.b.get(), this.c.get(), this.d.get(), this.e.get(), this.f.get());
    }
}
