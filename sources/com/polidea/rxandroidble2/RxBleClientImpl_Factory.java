package com.polidea.rxandroidble2;

import bleshadow.dagger.Lazy;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.DoubleCheck;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
import com.polidea.rxandroidble2.internal.util.BluetoothManagerWrapper;
import com.polidea.rxandroidble2.internal.util.CheckerConnectPermission;
import com.polidea.rxandroidble2.internal.util.CheckerScanPermission;
import com.polidea.rxandroidble2.internal.util.ClientStateObservable;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import com.polidea.rxandroidble2.scan.BackgroundScanner;
import com.polidea.rxandroidble2.scan.ScanResult;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class RxBleClientImpl_Factory implements Factory<i> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<BluetoothManagerWrapper> f13375a;
    public final Provider<RxBleAdapterWrapper> b;
    public final Provider<ClientOperationQueue> c;
    public final Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> d;
    public final Provider<ScanRecordParser> e;
    public final Provider<LocationServicesStatus> f;
    public final Provider<ClientStateObservable> g;
    public final Provider<RxBleDeviceProvider> h;
    public final Provider<ScanSetupBuilder> i;
    public final Provider<ScanPreconditionsVerifier> j;
    public final Provider<Function<RxBleInternalScanResult, ScanResult>> k;
    public final Provider<Scheduler> l;
    public final Provider<ClientComponent.ClientComponentFinalizer> m;
    public final Provider<BackgroundScanner> n;
    public final Provider<CheckerScanPermission> o;
    public final Provider<CheckerConnectPermission> p;

    public RxBleClientImpl_Factory(Provider<BluetoothManagerWrapper> provider, Provider<RxBleAdapterWrapper> provider2, Provider<ClientOperationQueue> provider3, Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> provider4, Provider<ScanRecordParser> provider5, Provider<LocationServicesStatus> provider6, Provider<ClientStateObservable> provider7, Provider<RxBleDeviceProvider> provider8, Provider<ScanSetupBuilder> provider9, Provider<ScanPreconditionsVerifier> provider10, Provider<Function<RxBleInternalScanResult, ScanResult>> provider11, Provider<Scheduler> provider12, Provider<ClientComponent.ClientComponentFinalizer> provider13, Provider<BackgroundScanner> provider14, Provider<CheckerScanPermission> provider15, Provider<CheckerConnectPermission> provider16) {
        this.f13375a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
        this.f = provider6;
        this.g = provider7;
        this.h = provider8;
        this.i = provider9;
        this.j = provider10;
        this.k = provider11;
        this.l = provider12;
        this.m = provider13;
        this.n = provider14;
        this.o = provider15;
        this.p = provider16;
    }

    public static RxBleClientImpl_Factory create(Provider<BluetoothManagerWrapper> provider, Provider<RxBleAdapterWrapper> provider2, Provider<ClientOperationQueue> provider3, Provider<Observable<RxBleAdapterStateObservable.BleAdapterState>> provider4, Provider<ScanRecordParser> provider5, Provider<LocationServicesStatus> provider6, Provider<ClientStateObservable> provider7, Provider<RxBleDeviceProvider> provider8, Provider<ScanSetupBuilder> provider9, Provider<ScanPreconditionsVerifier> provider10, Provider<Function<RxBleInternalScanResult, ScanResult>> provider11, Provider<Scheduler> provider12, Provider<ClientComponent.ClientComponentFinalizer> provider13, Provider<BackgroundScanner> provider14, Provider<CheckerScanPermission> provider15, Provider<CheckerConnectPermission> provider16) {
        return new RxBleClientImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16);
    }

    public static i newInstance(BluetoothManagerWrapper bluetoothManagerWrapper, RxBleAdapterWrapper rxBleAdapterWrapper, ClientOperationQueue clientOperationQueue, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, ScanRecordParser scanRecordParser, LocationServicesStatus locationServicesStatus, Lazy<ClientStateObservable> lazy, RxBleDeviceProvider rxBleDeviceProvider, ScanSetupBuilder scanSetupBuilder, ScanPreconditionsVerifier scanPreconditionsVerifier, Function<RxBleInternalScanResult, ScanResult> function, Scheduler scheduler, ClientComponent.ClientComponentFinalizer clientComponentFinalizer, BackgroundScanner backgroundScanner, CheckerScanPermission checkerScanPermission, CheckerConnectPermission checkerConnectPermission) {
        return new i(bluetoothManagerWrapper, rxBleAdapterWrapper, clientOperationQueue, observable, scanRecordParser, locationServicesStatus, lazy, rxBleDeviceProvider, scanSetupBuilder, scanPreconditionsVerifier, function, scheduler, clientComponentFinalizer, backgroundScanner, checkerScanPermission, checkerConnectPermission);
    }

    @Override // bleshadow.javax.inject.Provider
    public i get() {
        return newInstance(this.f13375a.get(), this.b.get(), this.c.get(), this.d.get(), this.e.get(), this.f.get(), DoubleCheck.lazy(this.g), this.h.get(), this.i.get(), this.j.get(), this.k.get(), this.l.get(), this.m.get(), this.n.get(), this.o.get(), this.p.get());
    }
}
