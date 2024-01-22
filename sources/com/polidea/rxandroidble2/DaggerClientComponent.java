package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.ContentResolver;
import android.content.Context;
import android.location.LocationManager;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.DelegateFactory;
import bleshadow.dagger.internal.DoubleCheck;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.InstanceFactory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.SetBuilder;
import bleshadow.javax.inject.Provider;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.helpers.LocationServicesOkObservable;
import com.polidea.rxandroidble2.helpers.LocationServicesOkObservable_Factory;
import com.polidea.rxandroidble2.internal.DeviceComponent;
import com.polidea.rxandroidble2.internal.DeviceModule_ProvideBluetoothDeviceFactory;
import com.polidea.rxandroidble2.internal.DeviceModule_ProvideConnectionStateChangeListenerFactory;
import com.polidea.rxandroidble2.internal.DeviceModule_ProvideConnectionStateRelayFactory;
import com.polidea.rxandroidble2.internal.DeviceModule_ProvidesConnectTimeoutConfFactory;
import com.polidea.rxandroidble2.internal.DeviceModule_ProvidesDisconnectTimeoutConfFactory;
import com.polidea.rxandroidble2.internal.RxBleDeviceImpl_Factory;
import com.polidea.rxandroidble2.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble2.internal.RxBleDeviceProvider_Factory;
import com.polidea.rxandroidble2.internal.cache.DeviceComponentCache;
import com.polidea.rxandroidble2.internal.cache.DeviceComponentCache_Factory;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider_Factory;
import com.polidea.rxandroidble2.internal.connection.ConnectionComponent;
import com.polidea.rxandroidble2.internal.connection.ConnectionModule_GattWriteMtuOverheadFactory;
import com.polidea.rxandroidble2.internal.connection.ConnectionModule_MinimumMtuFactory;
import com.polidea.rxandroidble2.internal.connection.ConnectionModule_ProvideBluetoothGattFactory;
import com.polidea.rxandroidble2.internal.connection.ConnectionModule_ProvideCharacteristicPropertiesParserFactory;
import com.polidea.rxandroidble2.internal.connection.ConnectionModule_ProvideIllegalOperationHandlerFactory;
import com.polidea.rxandroidble2.internal.connection.ConnectionModule_ProvidesOperationTimeoutConfFactory;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher;
import com.polidea.rxandroidble2.internal.connection.ConnectorImpl;
import com.polidea.rxandroidble2.internal.connection.ConnectorImpl_Factory;
import com.polidea.rxandroidble2.internal.connection.DescriptorWriter_Factory;
import com.polidea.rxandroidble2.internal.connection.DisconnectAction_Factory;
import com.polidea.rxandroidble2.internal.connection.DisconnectionRouter_Factory;
import com.polidea.rxandroidble2.internal.connection.IllegalOperationChecker;
import com.polidea.rxandroidble2.internal.connection.IllegalOperationChecker_Factory;
import com.polidea.rxandroidble2.internal.connection.IllegalOperationHandler;
import com.polidea.rxandroidble2.internal.connection.IllegalOperationMessageCreator;
import com.polidea.rxandroidble2.internal.connection.IllegalOperationMessageCreator_Factory;
import com.polidea.rxandroidble2.internal.connection.LoggingIllegalOperationHandler;
import com.polidea.rxandroidble2.internal.connection.LoggingIllegalOperationHandler_Factory;
import com.polidea.rxandroidble2.internal.connection.LongWriteOperationBuilderImpl;
import com.polidea.rxandroidble2.internal.connection.LongWriteOperationBuilderImpl_Factory;
import com.polidea.rxandroidble2.internal.connection.MtuBasedPayloadSizeLimit_Factory;
import com.polidea.rxandroidble2.internal.connection.MtuWatcher_Factory;
import com.polidea.rxandroidble2.internal.connection.NativeCallbackDispatcher_Factory;
import com.polidea.rxandroidble2.internal.connection.NotificationAndIndicationManager_Factory;
import com.polidea.rxandroidble2.internal.connection.RxBleConnectionImpl;
import com.polidea.rxandroidble2.internal.connection.RxBleConnectionImpl_Factory;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback_Factory;
import com.polidea.rxandroidble2.internal.connection.ServiceDiscoveryManager_Factory;
import com.polidea.rxandroidble2.internal.connection.ThrowingIllegalOperationHandler;
import com.polidea.rxandroidble2.internal.connection.ThrowingIllegalOperationHandler_Factory;
import com.polidea.rxandroidble2.internal.logger.LoggerUtilBluetoothServices;
import com.polidea.rxandroidble2.internal.logger.LoggerUtilBluetoothServices_Factory;
import com.polidea.rxandroidble2.internal.operations.ConnectOperation;
import com.polidea.rxandroidble2.internal.operations.ConnectOperation_Factory;
import com.polidea.rxandroidble2.internal.operations.DisconnectOperation;
import com.polidea.rxandroidble2.internal.operations.DisconnectOperation_Factory;
import com.polidea.rxandroidble2.internal.operations.OperationsProviderImpl;
import com.polidea.rxandroidble2.internal.operations.OperationsProviderImpl_Factory;
import com.polidea.rxandroidble2.internal.operations.ReadRssiOperation;
import com.polidea.rxandroidble2.internal.operations.ReadRssiOperation_Factory;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble2.internal.scan.AndroidScanObjectsConverter;
import com.polidea.rxandroidble2.internal.scan.AndroidScanObjectsConverter_Factory;
import com.polidea.rxandroidble2.internal.scan.BackgroundScannerImpl;
import com.polidea.rxandroidble2.internal.scan.BackgroundScannerImpl_Factory;
import com.polidea.rxandroidble2.internal.scan.InternalScanResultCreator;
import com.polidea.rxandroidble2.internal.scan.InternalScanResultCreator_Factory;
import com.polidea.rxandroidble2.internal.scan.InternalToExternalScanResultConverter;
import com.polidea.rxandroidble2.internal.scan.InternalToExternalScanResultConverter_Factory;
import com.polidea.rxandroidble2.internal.scan.IsConnectableChecker;
import com.polidea.rxandroidble2.internal.scan.IsConnectableCheckerApi18_Factory;
import com.polidea.rxandroidble2.internal.scan.IsConnectableCheckerApi26_Factory;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi18;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi18_Factory;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi24;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi24_Factory;
import com.polidea.rxandroidble2.internal.scan.ScanSettingsEmulator;
import com.polidea.rxandroidble2.internal.scan.ScanSettingsEmulator_Factory;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi18;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi18_Factory;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi21;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi21_Factory;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi23;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi23_Factory;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueueImpl;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueueImpl_Factory;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueueImpl;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueueImpl_Factory;
import com.polidea.rxandroidble2.internal.util.BleConnectionCompat;
import com.polidea.rxandroidble2.internal.util.BluetoothManagerWrapper;
import com.polidea.rxandroidble2.internal.util.BluetoothManagerWrapper_Factory;
import com.polidea.rxandroidble2.internal.util.CheckerConnectPermission;
import com.polidea.rxandroidble2.internal.util.CheckerConnectPermission_Factory;
import com.polidea.rxandroidble2.internal.util.CheckerLocationProvider;
import com.polidea.rxandroidble2.internal.util.CheckerLocationProvider_Factory;
import com.polidea.rxandroidble2.internal.util.CheckerPermission;
import com.polidea.rxandroidble2.internal.util.CheckerPermission_Factory;
import com.polidea.rxandroidble2.internal.util.CheckerScanPermission;
import com.polidea.rxandroidble2.internal.util.CheckerScanPermission_Factory;
import com.polidea.rxandroidble2.internal.util.ClientStateObservable;
import com.polidea.rxandroidble2.internal.util.ClientStateObservable_Factory;
import com.polidea.rxandroidble2.internal.util.LocationServicesOkObservableApi23Factory;
import com.polidea.rxandroidble2.internal.util.LocationServicesOkObservableApi23Factory_Factory;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi18_Factory;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi23;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi23_Factory;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi31;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi31_Factory;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper_Factory;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser_Factory;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import java.util.Set;
import java.util.concurrent.ExecutorService;
@DaggerGenerated
/* loaded from: classes9.dex */
public final class DaggerClientComponent {

    /* loaded from: classes9.dex */
    public static final class b implements ClientComponent.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Context f13361a;

        public b() {
        }

        @Override // com.polidea.rxandroidble2.ClientComponent.Builder
        /* renamed from: a */
        public b applicationContext(Context context) {
            this.f13361a = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        @Override // com.polidea.rxandroidble2.ClientComponent.Builder
        public ClientComponent build() {
            Preconditions.checkBuilderRequirement(this.f13361a, Context.class);
            return new c(this.f13361a);
        }
    }

    /* loaded from: classes9.dex */
    public static final class c implements ClientComponent {
        public Provider<DeviceComponentCache> A;
        public Provider<DeviceComponent.Builder> B;
        public Provider<RxBleDeviceProvider> C;
        public Provider<IsConnectableChecker> D;
        public Provider<InternalScanResultCreator> E;
        public Provider<ScanSettingsEmulator> F;
        public Provider<ScanSetupBuilderImplApi18> G;
        public Provider<AndroidScanObjectsConverter> H;
        public Provider<ScanSetupBuilderImplApi21> I;
        public Provider<ScanSetupBuilderImplApi23> J;
        public Provider<ScanSetupBuilder> K;
        public Provider<ScanPreconditionsVerifierApi18> L;
        public Provider<ScanPreconditionsVerifierApi24> M;
        public Provider<ScanPreconditionsVerifier> N;
        public Provider<InternalToExternalScanResultConverter> O;
        public Provider<Scheduler> P;
        public Provider<ExecutorService> Q;
        public Provider<ClientComponent.ClientComponentFinalizer> R;
        public Provider<BackgroundScannerImpl> S;
        public Provider<String[][]> T;
        public Provider<CheckerConnectPermission> U;
        public Provider<i> V;
        public Provider<RxBleClient> W;

        /* renamed from: a  reason: collision with root package name */
        public final Context f13362a;
        public final c b;
        public Provider<Context> c;
        public Provider<ContentResolver> d;
        public Provider<LocationManager> e;
        public Provider<CheckerLocationProvider> f;
        public Provider<CheckerPermission> g;
        public Provider<Integer> h;
        public Provider<Boolean> i;
        public Provider<String[][]> j;
        public Provider<CheckerScanPermission> k;
        public Provider<Boolean> l;
        public Provider<LocationServicesStatusApi23> m;
        public Provider<LocationServicesStatusApi31> n;
        public Provider<BluetoothManager> o;
        public Provider<BluetoothManagerWrapper> p;
        public Provider<RxBleAdapterWrapper> q;
        public Provider<ExecutorService> r;
        public Provider<Scheduler> s;
        public Provider<ClientOperationQueueImpl> t;
        public Provider<ClientOperationQueue> u;
        public Provider<RxBleAdapterStateObservable> v;
        public Provider<LocationServicesStatus> w;
        public Provider<LocationServicesOkObservableApi23Factory> x;
        public Provider<Observable<Boolean>> y;
        public Provider<ClientStateObservable> z;

        /* loaded from: classes9.dex */
        public class a implements Provider<DeviceComponent.Builder> {
            public a() {
            }

            @Override // bleshadow.javax.inject.Provider
            /* renamed from: a */
            public DeviceComponent.Builder get() {
                return new f(c.this.b);
            }
        }

        public final void l(Context context) {
            Factory create = InstanceFactory.create(context);
            this.c = create;
            this.d = ClientComponent_ClientModule_ProvideContentResolverFactory.create(create);
            ClientComponent_ClientModule_ProvideLocationManagerFactory create2 = ClientComponent_ClientModule_ProvideLocationManagerFactory.create(this.c);
            this.e = create2;
            this.f = CheckerLocationProvider_Factory.create(this.d, create2);
            this.g = DoubleCheck.provider(CheckerPermission_Factory.create(this.c));
            this.h = ClientComponent_ClientModule_ProvideTargetSdkFactory.create(this.c);
            this.i = DoubleCheck.provider(ClientComponent_ClientModule_ProvideIsNearbyPermissionNeverForLocationFactory.create(this.c));
            ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory create3 = ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.h, this.i);
            this.j = create3;
            this.k = DoubleCheck.provider(CheckerScanPermission_Factory.create(this.g, create3));
            this.l = ClientComponent_ClientModule_ProvideIsAndroidWearFactory.create(this.c, ClientComponent_ClientModule_ProvideDeviceSdkFactory.create());
            this.m = LocationServicesStatusApi23_Factory.create(this.f, this.k, this.h, ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.l);
            this.n = LocationServicesStatusApi31_Factory.create(this.f, this.k, this.l, this.i);
            ClientComponent_ClientModule_ProvideBluetoothManagerFactory create4 = ClientComponent_ClientModule_ProvideBluetoothManagerFactory.create(this.c);
            this.o = create4;
            this.p = BluetoothManagerWrapper_Factory.create(create4);
            this.q = RxBleAdapterWrapper_Factory.create(ClientComponent_ClientModule_ProvideBluetoothAdapterFactory.create());
            Provider<ExecutorService> provider = DoubleCheck.provider(ClientComponent_ClientModule_ProvideBluetoothInteractionExecutorServiceFactory.create());
            this.r = provider;
            Provider<Scheduler> provider2 = DoubleCheck.provider(ClientComponent_ClientModule_ProvideBluetoothInteractionSchedulerFactory.create(provider));
            this.s = provider2;
            ClientOperationQueueImpl_Factory create5 = ClientOperationQueueImpl_Factory.create(provider2);
            this.t = create5;
            this.u = DoubleCheck.provider(create5);
            this.v = RxBleAdapterStateObservable_Factory.create(this.c);
            ClientComponent_ClientModule_ProvideLocationServicesStatusFactory create6 = ClientComponent_ClientModule_ProvideLocationServicesStatusFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), LocationServicesStatusApi18_Factory.create(), this.m, this.n);
            this.w = create6;
            this.x = LocationServicesOkObservableApi23Factory_Factory.create(this.c, create6);
            ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory create7 = ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.x);
            this.y = create7;
            this.z = ClientStateObservable_Factory.create(this.q, this.v, create7, this.w, ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
            this.A = DoubleCheck.provider(DeviceComponentCache_Factory.create());
            a aVar = new a();
            this.B = aVar;
            this.C = DoubleCheck.provider(RxBleDeviceProvider_Factory.create(this.A, aVar));
            this.D = DoubleCheck.provider(ClientComponent_ClientModule_ProvideIsConnectableCheckerFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), IsConnectableCheckerApi18_Factory.create(), IsConnectableCheckerApi26_Factory.create()));
            this.E = DoubleCheck.provider(InternalScanResultCreator_Factory.create(ScanRecordParser_Factory.create(), this.D));
            ScanSettingsEmulator_Factory create8 = ScanSettingsEmulator_Factory.create(ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
            this.F = create8;
            this.G = ScanSetupBuilderImplApi18_Factory.create(this.q, this.E, create8);
            AndroidScanObjectsConverter_Factory create9 = AndroidScanObjectsConverter_Factory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create());
            this.H = create9;
            this.I = ScanSetupBuilderImplApi21_Factory.create(this.q, this.E, this.F, create9);
            this.J = ScanSetupBuilderImplApi23_Factory.create(this.q, this.E, this.F, this.H);
            this.K = DoubleCheck.provider(ClientComponent_ClientModule_ProvideScanSetupProviderFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.G, this.I, this.J));
            ScanPreconditionsVerifierApi18_Factory create10 = ScanPreconditionsVerifierApi18_Factory.create(this.q, this.w);
            this.L = create10;
            this.M = ScanPreconditionsVerifierApi24_Factory.create(create10, ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
            this.N = ClientComponent_ClientModule_ProvideScanPreconditionVerifierFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.L, this.M);
            this.O = InternalToExternalScanResultConverter_Factory.create(this.C);
            this.P = DoubleCheck.provider(ClientComponent_ClientModule_ProvideBluetoothCallbacksSchedulerFactory.create());
            Provider<ExecutorService> provider3 = DoubleCheck.provider(ClientComponent_ClientModule_ProvideConnectionQueueExecutorServiceFactory.create());
            this.Q = provider3;
            this.R = ClientComponent_ClientModule_ProvideFinalizationCloseableFactory.create(this.r, this.P, provider3);
            this.S = BackgroundScannerImpl_Factory.create(this.q, this.H, this.E, this.O);
            ClientComponent_ClientModule_ProvideRecommendedConnectRuntimePermissionNamesFactory create11 = ClientComponent_ClientModule_ProvideRecommendedConnectRuntimePermissionNamesFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.h);
            this.T = create11;
            this.U = DoubleCheck.provider(CheckerConnectPermission_Factory.create(this.g, create11));
            RxBleClientImpl_Factory create12 = RxBleClientImpl_Factory.create(this.p, this.q, this.u, this.v, ScanRecordParser_Factory.create(), this.w, this.z, this.C, this.K, this.N, this.O, this.s, this.R, this.S, this.k, this.U);
            this.V = create12;
            this.W = DoubleCheck.provider(create12);
        }

        @Override // com.polidea.rxandroidble2.ClientComponent
        public LocationServicesOkObservable locationServicesOkObservable() {
            return LocationServicesOkObservable_Factory.newInstance(o());
        }

        public final LocationServicesOkObservableApi23Factory m() {
            return LocationServicesOkObservableApi23Factory_Factory.newInstance(this.f13362a, n());
        }

        public final LocationServicesStatus n() {
            return ClientComponent_ClientModule_ProvideLocationServicesStatusFactory.provideLocationServicesStatus(ClientComponent.ClientModule.i(), LocationServicesStatusApi18_Factory.create(), this.m, this.n);
        }

        public final Observable<Boolean> o() {
            return ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory.provideLocationServicesOkObservable(ClientComponent.ClientModule.i(), m());
        }

        public final RxBleAdapterWrapper p() {
            return new RxBleAdapterWrapper(ClientComponent.ClientModule.a());
        }

        @Override // com.polidea.rxandroidble2.ClientComponent
        public RxBleClient rxBleClient() {
            return this.W.get();
        }

        public c(Context context) {
            this.b = this;
            this.f13362a = context;
            l(context);
        }
    }

    /* loaded from: classes9.dex */
    public static final class d implements ConnectionComponent.Builder {

        /* renamed from: a  reason: collision with root package name */
        public final c f13364a;
        public final g b;
        public Boolean c;
        public Boolean d;
        public Timeout e;

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent.Builder
        /* renamed from: a */
        public d autoConnect(boolean z) {
            this.c = (Boolean) Preconditions.checkNotNull(Boolean.valueOf(z));
            return this;
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent.Builder
        /* renamed from: b */
        public d operationTimeout(Timeout timeout) {
            this.e = (Timeout) Preconditions.checkNotNull(timeout);
            return this;
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent.Builder
        public ConnectionComponent build() {
            Preconditions.checkBuilderRequirement(this.c, Boolean.class);
            Preconditions.checkBuilderRequirement(this.d, Boolean.class);
            Preconditions.checkBuilderRequirement(this.e, Timeout.class);
            return new e(this.f13364a, this.b, this.c, this.d, this.e);
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent.Builder
        /* renamed from: c */
        public d suppressOperationChecks(boolean z) {
            this.d = (Boolean) Preconditions.checkNotNull(Boolean.valueOf(z));
            return this;
        }

        public d(c cVar, g gVar) {
            this.f13364a = cVar;
            this.b = gVar;
        }
    }

    /* loaded from: classes9.dex */
    public static final class e implements ConnectionComponent {
        public Provider<DisconnectOperation> A;
        public Provider B;

        /* renamed from: a  reason: collision with root package name */
        public final Boolean f13365a;
        public final c b;
        public final g c;
        public Provider<BluetoothGattProvider> d;
        public Provider e;
        public Provider<RxBleGattCallback> f;
        public Provider<ConnectionOperationQueueImpl> g;
        public Provider<BluetoothGatt> h;
        public Provider<LoggerUtilBluetoothServices> i;
        public Provider<Timeout> j;
        public Provider<TimeoutConfiguration> k;
        public Provider<ReadRssiOperation> l;
        public Provider<OperationsProviderImpl> m;
        public Provider n;
        public Provider o;
        public Provider p;
        public Provider q;
        public Provider<RxBleConnectionImpl> r;
        public Provider s;
        public Provider<LongWriteOperationBuilderImpl> t;
        public Provider<Boolean> u;
        public Provider<IllegalOperationMessageCreator> v;
        public Provider<LoggingIllegalOperationHandler> w;
        public Provider<ThrowingIllegalOperationHandler> x;
        public Provider<IllegalOperationHandler> y;
        public Provider<IllegalOperationChecker> z;

        public final BleConnectionCompat a() {
            return new BleConnectionCompat(this.b.f13362a);
        }

        public final void b(Boolean bool, Boolean bool2, Timeout timeout) {
            this.d = DoubleCheck.provider(BluetoothGattProvider_Factory.create());
            this.e = DoubleCheck.provider(DisconnectionRouter_Factory.create(this.c.d, this.b.q, this.b.v));
            this.f = DoubleCheck.provider(RxBleGattCallback_Factory.create(this.b.P, this.d, this.e, NativeCallbackDispatcher_Factory.create()));
            this.g = DoubleCheck.provider(ConnectionOperationQueueImpl_Factory.create(this.c.d, this.e, this.b.Q, this.b.s));
            this.h = ConnectionModule_ProvideBluetoothGattFactory.create(this.d);
            this.i = LoggerUtilBluetoothServices_Factory.create(ConnectionModule_ProvideCharacteristicPropertiesParserFactory.create());
            this.j = InstanceFactory.create(timeout);
            ConnectionModule_ProvidesOperationTimeoutConfFactory create = ConnectionModule_ProvidesOperationTimeoutConfFactory.create(ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create(), this.j);
            this.k = create;
            this.l = ReadRssiOperation_Factory.create(this.f, this.h, create);
            OperationsProviderImpl_Factory create2 = OperationsProviderImpl_Factory.create(this.f, this.h, this.i, this.k, this.b.s, ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create(), this.l);
            this.m = create2;
            this.n = DoubleCheck.provider(ServiceDiscoveryManager_Factory.create(this.g, this.h, create2));
            this.o = DoubleCheck.provider(DescriptorWriter_Factory.create(this.g, this.m));
            this.p = DoubleCheck.provider(NotificationAndIndicationManager_Factory.create(ClientComponent_ClientModule_ProvideEnableNotificationValueFactory.create(), ClientComponent_ClientModule_ProvideEnableIndicationValueFactory.create(), ClientComponent_ClientModule_ProvideDisableNotificationValueFactory.create(), this.h, this.f, this.o));
            this.q = DoubleCheck.provider(MtuWatcher_Factory.create(this.f, ConnectionModule_MinimumMtuFactory.create()));
            DelegateFactory delegateFactory = new DelegateFactory();
            this.r = delegateFactory;
            Provider provider = DoubleCheck.provider(MtuBasedPayloadSizeLimit_Factory.create(delegateFactory, ConnectionModule_GattWriteMtuOverheadFactory.create()));
            this.s = provider;
            this.t = LongWriteOperationBuilderImpl_Factory.create(this.g, provider, this.r, this.m);
            this.u = InstanceFactory.create(bool2);
            IllegalOperationMessageCreator_Factory create3 = IllegalOperationMessageCreator_Factory.create(ConnectionModule_ProvideCharacteristicPropertiesParserFactory.create());
            this.v = create3;
            this.w = LoggingIllegalOperationHandler_Factory.create(create3);
            ThrowingIllegalOperationHandler_Factory create4 = ThrowingIllegalOperationHandler_Factory.create(this.v);
            this.x = create4;
            ConnectionModule_ProvideIllegalOperationHandlerFactory create5 = ConnectionModule_ProvideIllegalOperationHandlerFactory.create(this.u, this.w, create4);
            this.y = create5;
            this.z = IllegalOperationChecker_Factory.create(create5);
            DelegateFactory.setDelegate(this.r, DoubleCheck.provider(RxBleConnectionImpl_Factory.create(this.g, this.f, this.h, this.n, this.p, this.q, this.o, this.m, this.t, this.b.s, this.z)));
            this.A = DisconnectOperation_Factory.create(this.f, this.d, this.c.d, this.b.o, this.b.s, this.c.k, this.c.j);
            this.B = DoubleCheck.provider(DisconnectAction_Factory.create(this.b.u, this.A));
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent
        public ConnectOperation connectOperation() {
            return ConnectOperation_Factory.newInstance(this.c.h(), a(), this.f.get(), this.d.get(), this.c.j(), this.f13365a.booleanValue(), (ConnectionStateChangeListener) this.c.j.get());
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent
        public Set<ConnectionSubscriptionWatcher> connectionSubscriptionWatchers() {
            return SetBuilder.newSetBuilder(3).add((ConnectionSubscriptionWatcher) this.q.get()).add((ConnectionSubscriptionWatcher) this.B.get()).add(this.g.get()).build();
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent
        public RxBleGattCallback gattCallback() {
            return this.f.get();
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionComponent
        public RxBleConnection rxBleConnection() {
            return this.r.get();
        }

        public e(c cVar, g gVar, Boolean bool, Boolean bool2, Timeout timeout) {
            this.b = cVar;
            this.c = gVar;
            this.f13365a = bool;
            b(bool, bool2, timeout);
        }
    }

    /* loaded from: classes9.dex */
    public static final class f implements DeviceComponent.Builder {

        /* renamed from: a  reason: collision with root package name */
        public final c f13366a;
        public String b;

        @Override // com.polidea.rxandroidble2.internal.DeviceComponent.Builder
        /* renamed from: a */
        public f macAddress(String str) {
            this.b = (String) Preconditions.checkNotNull(str);
            return this;
        }

        @Override // com.polidea.rxandroidble2.internal.DeviceComponent.Builder
        public DeviceComponent build() {
            Preconditions.checkBuilderRequirement(this.b, String.class);
            return new g(this.f13366a, this.b);
        }

        public f(c cVar) {
            this.f13366a = cVar;
        }
    }

    /* loaded from: classes9.dex */
    public static final class g implements DeviceComponent {

        /* renamed from: a  reason: collision with root package name */
        public final String f13367a;
        public final c b;
        public final g c;
        public Provider<String> d;
        public Provider<BluetoothDevice> e;
        public Provider<ConnectionComponent.Builder> f;
        public Provider<ConnectorImpl> g;
        public Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> h;
        public Provider i;
        public Provider<ConnectionStateChangeListener> j;
        public Provider<TimeoutConfiguration> k;

        /* loaded from: classes9.dex */
        public class a implements Provider<ConnectionComponent.Builder> {
            public a() {
            }

            @Override // bleshadow.javax.inject.Provider
            /* renamed from: a */
            public ConnectionComponent.Builder get() {
                return new d(g.this.b, g.this.c);
            }
        }

        public final BluetoothDevice h() {
            return DeviceModule_ProvideBluetoothDeviceFactory.provideBluetoothDevice(this.f13367a, this.b.p());
        }

        public final void i(String str) {
            Factory create = InstanceFactory.create(str);
            this.d = create;
            this.e = DeviceModule_ProvideBluetoothDeviceFactory.create(create, this.b.q);
            this.f = new a();
            this.g = ConnectorImpl_Factory.create(this.b.u, this.f, this.b.P);
            Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider = DoubleCheck.provider(DeviceModule_ProvideConnectionStateRelayFactory.create());
            this.h = provider;
            this.i = DoubleCheck.provider(RxBleDeviceImpl_Factory.create(this.e, this.g, provider, this.b.U));
            this.j = DoubleCheck.provider(DeviceModule_ProvideConnectionStateChangeListenerFactory.create(this.h));
            this.k = DeviceModule_ProvidesDisconnectTimeoutConfFactory.create(ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
        }

        public final TimeoutConfiguration j() {
            return DeviceModule_ProvidesConnectTimeoutConfFactory.providesConnectTimeoutConf(ClientComponent_ClientModule_ProvideComputationSchedulerFactory.provideComputationScheduler());
        }

        @Override // com.polidea.rxandroidble2.internal.DeviceComponent
        public RxBleDevice provideDevice() {
            return (RxBleDevice) this.i.get();
        }

        public g(c cVar, String str) {
            this.c = this;
            this.b = cVar;
            this.f13367a = str;
            i(str);
        }
    }

    public static ClientComponent.Builder builder() {
        return new b();
    }
}
