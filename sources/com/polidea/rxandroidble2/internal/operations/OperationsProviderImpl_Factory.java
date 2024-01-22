package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtilBluetoothServices;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class OperationsProviderImpl_Factory implements Factory<OperationsProviderImpl> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleGattCallback> f13456a;
    public final Provider<BluetoothGatt> b;
    public final Provider<LoggerUtilBluetoothServices> c;
    public final Provider<TimeoutConfiguration> d;
    public final Provider<Scheduler> e;
    public final Provider<Scheduler> f;
    public final Provider<ReadRssiOperation> g;

    public OperationsProviderImpl_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<LoggerUtilBluetoothServices> provider3, Provider<TimeoutConfiguration> provider4, Provider<Scheduler> provider5, Provider<Scheduler> provider6, Provider<ReadRssiOperation> provider7) {
        this.f13456a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
        this.f = provider6;
        this.g = provider7;
    }

    public static OperationsProviderImpl_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<LoggerUtilBluetoothServices> provider3, Provider<TimeoutConfiguration> provider4, Provider<Scheduler> provider5, Provider<Scheduler> provider6, Provider<ReadRssiOperation> provider7) {
        return new OperationsProviderImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static OperationsProviderImpl newInstance(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, LoggerUtilBluetoothServices loggerUtilBluetoothServices, TimeoutConfiguration timeoutConfiguration, Scheduler scheduler, Scheduler scheduler2, Provider<ReadRssiOperation> provider) {
        return new OperationsProviderImpl(rxBleGattCallback, bluetoothGatt, loggerUtilBluetoothServices, timeoutConfiguration, scheduler, scheduler2, provider);
    }

    @Override // bleshadow.javax.inject.Provider
    public OperationsProviderImpl get() {
        return newInstance(this.f13456a.get(), this.b.get(), this.c.get(), this.d.get(), this.e.get(), this.f.get(), this.g);
    }
}
