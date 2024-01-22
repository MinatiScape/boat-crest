package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bleshadow.dagger.Lazy;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.operations.LegacyScanOperation;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResultLegacy;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier;
import com.polidea.rxandroidble2.internal.scan.ScanSetup;
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
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class i extends RxBleClient {

    /* renamed from: a  reason: collision with root package name */
    public final ClientOperationQueue f13382a;
    public final ScanRecordParser b;
    public final RxBleDeviceProvider c;
    public final ScanSetupBuilder d;
    public final ScanPreconditionsVerifier e;
    public final Function<RxBleInternalScanResult, ScanResult> f;
    public final ClientComponent.ClientComponentFinalizer g;
    public final Scheduler h;
    public final Map<Set<UUID>, Observable<RxBleScanResult>> i = new HashMap();
    public final BluetoothManagerWrapper j;
    public final RxBleAdapterWrapper k;
    public final Observable<RxBleAdapterStateObservable.BleAdapterState> l;
    public final LocationServicesStatus m;
    public final Lazy<ClientStateObservable> n;
    public final BackgroundScanner o;
    public final CheckerScanPermission p;
    public final CheckerConnectPermission q;

    @Inject
    public i(BluetoothManagerWrapper bluetoothManagerWrapper, RxBleAdapterWrapper rxBleAdapterWrapper, ClientOperationQueue clientOperationQueue, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, ScanRecordParser scanRecordParser, LocationServicesStatus locationServicesStatus, Lazy<ClientStateObservable> lazy, RxBleDeviceProvider rxBleDeviceProvider, ScanSetupBuilder scanSetupBuilder, ScanPreconditionsVerifier scanPreconditionsVerifier, Function<RxBleInternalScanResult, ScanResult> function, @Named("bluetooth_interaction") Scheduler scheduler, ClientComponent.ClientComponentFinalizer clientComponentFinalizer, BackgroundScanner backgroundScanner, CheckerScanPermission checkerScanPermission, CheckerConnectPermission checkerConnectPermission) {
        this.f13382a = clientOperationQueue;
        this.j = bluetoothManagerWrapper;
        this.k = rxBleAdapterWrapper;
        this.l = observable;
        this.b = scanRecordParser;
        this.m = locationServicesStatus;
        this.n = lazy;
        this.c = rxBleDeviceProvider;
        this.d = scanSetupBuilder;
        this.e = scanPreconditionsVerifier;
        this.f = function;
        this.h = scheduler;
        this.g = clientComponentFinalizer;
        this.o = backgroundScanner;
        this.p = checkerScanPermission;
        this.q = checkerConnectPermission;
    }

    public static /* synthetic */ boolean m(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) throws Exception {
        return bleAdapterState != RxBleAdapterStateObservable.BleAdapterState.STATE_ON;
    }

    public static /* synthetic */ MaybeSource n(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) throws Exception {
        return Maybe.error(new BleScanException(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(Set set) throws Exception {
        synchronized (this.i) {
            this.i.remove(set);
        }
    }

    public static /* synthetic */ void p(RxBleScanResult rxBleScanResult) throws Exception {
        RxBleLog.i("%s", rxBleScanResult);
    }

    public static /* synthetic */ void q(ScanResult scanResult) throws Exception {
        if (RxBleLog.getShouldLogScannedPeripherals()) {
            RxBleLog.i("%s", scanResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ObservableSource r(ScanSettings scanSettings, ScanFilter[] scanFilterArr) throws Exception {
        this.e.verify(scanSettings.shouldCheckLocationProviderState());
        ScanSetup build = this.d.build(scanSettings, scanFilterArr);
        return this.f13382a.queue(build.scanOperation).unsubscribeOn(this.h).compose(build.scanOperationBehaviourEmulatorTransformer).map(this.f).doOnNext(new Consumer() { // from class: com.polidea.rxandroidble2.c
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                i.q((ScanResult) obj);
            }
        }).mergeWith(h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ObservableSource s(UUID[] uuidArr) throws Exception {
        this.e.verify(true);
        return l(uuidArr);
    }

    public void finalize() throws Throwable {
        this.g.onFinalize();
        super.finalize();
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public BackgroundScanner getBackgroundScanner() {
        return this.o;
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public RxBleDevice getBleDevice(@NonNull String str) {
        k();
        return this.c.getBleDevice(str);
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public Set<RxBleDevice> getBondedDevices() {
        k();
        HashSet hashSet = new HashSet();
        for (BluetoothDevice bluetoothDevice : this.k.getBondedDevices()) {
            hashSet.add(getBleDevice(bluetoothDevice.getAddress()));
        }
        return hashSet;
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public Set<RxBleDevice> getConnectedPeripherals() {
        HashSet hashSet = new HashSet();
        for (BluetoothDevice bluetoothDevice : this.j.getConnectedPeripherals()) {
            hashSet.add(getBleDevice(bluetoothDevice.getAddress()));
        }
        return hashSet;
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public String[] getRecommendedConnectRuntimePermissions() {
        return this.q.getRecommendedConnectRuntimePermissions();
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public String[] getRecommendedScanRuntimePermissions() {
        return this.p.getRecommendedScanRuntimePermissions();
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public RxBleClient.State getState() {
        if (!this.k.hasBluetoothAdapter()) {
            return RxBleClient.State.BLUETOOTH_NOT_AVAILABLE;
        }
        if (!this.m.isLocationPermissionOk()) {
            return RxBleClient.State.LOCATION_PERMISSION_NOT_GRANTED;
        }
        if (!this.k.isBluetoothEnabled()) {
            return RxBleClient.State.BLUETOOTH_NOT_ENABLED;
        }
        if (!this.m.isLocationProviderOk()) {
            return RxBleClient.State.LOCATION_SERVICES_NOT_ENABLED;
        }
        return RxBleClient.State.READY;
    }

    public <T> Observable<T> h() {
        return this.l.filter(new Predicate() { // from class: com.polidea.rxandroidble2.f
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                boolean m;
                m = i.m((RxBleAdapterStateObservable.BleAdapterState) obj);
                return m;
            }
        }).firstElement().flatMap(new Function() { // from class: com.polidea.rxandroidble2.e
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                MaybeSource n;
                n = i.n((RxBleAdapterStateObservable.BleAdapterState) obj);
                return n;
            }
        }).toObservable();
    }

    public RxBleScanResult i(RxBleInternalScanResultLegacy rxBleInternalScanResultLegacy) {
        return new RxBleScanResult(getBleDevice(rxBleInternalScanResultLegacy.getBluetoothDevice().getAddress()), rxBleInternalScanResultLegacy.getRssi(), rxBleInternalScanResultLegacy.getScanRecord());
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public boolean isConnectRuntimePermissionGranted() {
        return this.q.isConnectRuntimePermissionGranted();
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public boolean isScanRuntimePermissionGranted() {
        return this.p.isScanRuntimePermissionGranted();
    }

    public final Observable<RxBleScanResult> j(@Nullable UUID[] uuidArr) {
        final Set<UUID> t = t(uuidArr);
        return this.f13382a.queue(new LegacyScanOperation(uuidArr, this.k, this.b)).doFinally(new Action() { // from class: com.polidea.rxandroidble2.a
            @Override // io.reactivex.functions.Action
            public final void run() {
                i.this.o(t);
            }
        }).mergeWith(h()).map(new Function() { // from class: com.polidea.rxandroidble2.d
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return i.this.i((RxBleInternalScanResultLegacy) obj);
            }
        }).doOnNext(new Consumer() { // from class: com.polidea.rxandroidble2.b
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                i.p((RxBleScanResult) obj);
            }
        }).share();
    }

    public final void k() {
        if (!this.k.hasBluetoothAdapter()) {
            throw new UnsupportedOperationException("RxAndroidBle library needs a BluetoothAdapter to be available in the system to work. If this is a test on an emulator then you can use 'https://github.com/Polidea/RxAndroidBle/tree/master/mockrxandroidble'");
        }
    }

    public Observable<RxBleScanResult> l(@Nullable UUID[] uuidArr) {
        Observable<RxBleScanResult> observable;
        Set<UUID> t = t(uuidArr);
        synchronized (this.i) {
            observable = this.i.get(t);
            if (observable == null) {
                observable = j(uuidArr);
                this.i.put(t, observable);
            }
        }
        return observable;
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public Observable<RxBleClient.State> observeStateChanges() {
        return this.n.get();
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    public Observable<ScanResult> scanBleDevices(final ScanSettings scanSettings, final ScanFilter... scanFilterArr) {
        return Observable.defer(new Callable() { // from class: com.polidea.rxandroidble2.g
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ObservableSource r;
                r = i.this.r(scanSettings, scanFilterArr);
                return r;
            }
        });
    }

    public final Set<UUID> t(@Nullable UUID[] uuidArr) {
        if (uuidArr == null) {
            uuidArr = new UUID[0];
        }
        return new HashSet(Arrays.asList(uuidArr));
    }

    @Override // com.polidea.rxandroidble2.RxBleClient
    @Deprecated
    public Observable<RxBleScanResult> scanBleDevices(@Nullable final UUID... uuidArr) {
        return Observable.defer(new Callable() { // from class: com.polidea.rxandroidble2.h
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ObservableSource s;
                s = i.this.s(uuidArr);
                return s;
            }
        });
    }
}
