package com.polidea.rxandroidble2;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.ContentResolver;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import bleshadow.dagger.BindsInstance;
import bleshadow.dagger.Component;
import bleshadow.dagger.Module;
import bleshadow.dagger.Provides;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.polidea.rxandroidble2.helpers.LocationServicesOkObservable;
import com.polidea.rxandroidble2.internal.DeviceComponent;
import com.polidea.rxandroidble2.internal.scan.IsConnectableChecker;
import com.polidea.rxandroidble2.internal.scan.IsConnectableCheckerApi18;
import com.polidea.rxandroidble2.internal.scan.IsConnectableCheckerApi26;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi18;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifierApi24;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi18;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi21;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilderImplApi23;
import com.polidea.rxandroidble2.internal.serialization.RxBleThreadFactory;
import com.polidea.rxandroidble2.internal.util.LocationServicesOkObservableApi23Factory;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi18;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi23;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatusApi31;
import com.polidea.rxandroidble2.internal.util.ObservableUtil;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Component(modules = {ClientModule.class})
@ClientScope
/* loaded from: classes9.dex */
public interface ClientComponent {

    /* loaded from: classes9.dex */
    public static class BluetoothConstants {
        public static final String DISABLE_NOTIFICATION_VALUE = "disable-notification-value";
        public static final String ENABLE_INDICATION_VALUE = "enable-indication-value";
        public static final String ENABLE_NOTIFICATION_VALUE = "enable-notification-value";
    }

    @Component.Builder
    /* loaded from: classes9.dex */
    public interface Builder {
        @BindsInstance
        Builder applicationContext(Context context);

        ClientComponent build();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes9.dex */
    public interface ClientComponentFinalizer {
        void onFinalize();
    }

    @Module(subcomponents = {DeviceComponent.class})
    /* loaded from: classes9.dex */
    public static abstract class ClientModule {

        /* loaded from: classes9.dex */
        public class a implements ClientComponentFinalizer {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ExecutorService f13335a;
            public final /* synthetic */ Scheduler b;
            public final /* synthetic */ ExecutorService c;

            public a(ExecutorService executorService, Scheduler scheduler, ExecutorService executorService2) {
                this.f13335a = executorService;
                this.b = scheduler;
                this.c = executorService2;
            }

            @Override // com.polidea.rxandroidble2.ClientComponent.ClientComponentFinalizer
            public void onFinalize() {
                this.f13335a.shutdown();
                this.b.shutdown();
                this.c.shutdown();
            }
        }

        @Nullable
        @Provides
        public static BluetoothAdapter a() {
            return BluetoothAdapter.getDefaultAdapter();
        }

        @Named(NamedSchedulers.BLUETOOTH_CALLBACKS)
        @ClientScope
        @Provides
        public static Scheduler b() {
            return RxJavaPlugins.createSingleScheduler(new RxBleThreadFactory());
        }

        @Named(NamedExecutors.BLUETOOTH_INTERACTION)
        @ClientScope
        @Provides
        public static ExecutorService c() {
            return Executors.newSingleThreadExecutor();
        }

        @Named(NamedSchedulers.BLUETOOTH_INTERACTION)
        @ClientScope
        @Provides
        public static Scheduler d(@Named("executor_bluetooth_interaction") ExecutorService executorService) {
            return Schedulers.from(executorService);
        }

        @Provides
        public static BluetoothManager e(Context context) {
            return (BluetoothManager) context.getSystemService("bluetooth");
        }

        @Named(NamedSchedulers.COMPUTATION)
        @Provides
        public static Scheduler f() {
            return Schedulers.computation();
        }

        @Named(NamedExecutors.CONNECTION_QUEUE)
        @ClientScope
        @Provides
        public static ExecutorService g() {
            return Executors.newCachedThreadPool();
        }

        @Provides
        public static ContentResolver h(Context context) {
            return context.getContentResolver();
        }

        @Named(PlatformConstants.INT_DEVICE_SDK)
        @Provides
        public static int i() {
            return Build.VERSION.SDK_INT;
        }

        @Named(BluetoothConstants.DISABLE_NOTIFICATION_VALUE)
        @Provides
        public static byte[] j() {
            return BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
        }

        @Named(BluetoothConstants.ENABLE_INDICATION_VALUE)
        @Provides
        public static byte[] k() {
            return BluetoothGattDescriptor.ENABLE_INDICATION_VALUE;
        }

        @Named(BluetoothConstants.ENABLE_NOTIFICATION_VALUE)
        @Provides
        public static byte[] l() {
            return BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
        }

        @Provides
        public static ClientComponentFinalizer m(@Named("executor_bluetooth_interaction") ExecutorService executorService, @Named("bluetooth_callbacks") Scheduler scheduler, @Named("executor_connection_queue") ExecutorService executorService2) {
            return new a(executorService, scheduler, executorService2);
        }

        @Named(PlatformConstants.BOOL_IS_ANDROID_WEAR)
        @SuppressLint({"InlinedApi"})
        @Provides
        public static boolean n(Context context, @Named("device-sdk") int i) {
            return i >= 20 && context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        }

        @ClientScope
        @Provides
        public static IsConnectableChecker o(@Named("device-sdk") int i, Provider<IsConnectableCheckerApi18> provider, Provider<IsConnectableCheckerApi26> provider2) {
            if (i < 26) {
                return provider.get();
            }
            return provider2.get();
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
            if ((r4.requestedPermissionsFlags[r1] & 65536) == 0) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:?, code lost:
            return false;
         */
        @bleshadow.javax.inject.Named(com.polidea.rxandroidble2.ClientComponent.PlatformConstants.BOOL_IS_NEARBY_PERMISSION_NEVER_FOR_LOCATION)
        @com.polidea.rxandroidble2.ClientScope
        @bleshadow.dagger.Provides
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static boolean p(android.content.Context r4) {
            /*
                r0 = 0
                android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                java.lang.String r4 = r4.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                r2 = 4096(0x1000, float:5.74E-42)
                android.content.pm.PackageInfo r4 = r1.getPackageInfo(r4, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                r1 = r0
            L10:
                java.lang.String[] r2 = r4.requestedPermissions     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                int r3 = r2.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                if (r1 >= r3) goto L35
                java.lang.String r3 = "android.permission.BLUETOOTH_SCAN"
                r2 = r2[r1]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                boolean r2 = r3.equals(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                if (r2 != 0) goto L22
                int r1 = r1 + 1
                goto L10
            L22:
                int[] r4 = r4.requestedPermissionsFlags     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                r4 = r4[r1]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
                r1 = 65536(0x10000, float:9.18355E-41)
                r4 = r4 & r1
                if (r4 == 0) goto L2c
                r0 = 1
            L2c:
                return r0
            L2d:
                r4 = move-exception
                java.lang.Object[] r1 = new java.lang.Object[r0]
                java.lang.String r2 = "Could not find application PackageInfo"
                com.polidea.rxandroidble2.internal.RxBleLog.e(r4, r2, r1)
            L35:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.polidea.rxandroidble2.ClientComponent.ClientModule.p(android.content.Context):boolean");
        }

        @Provides
        public static LocationManager q(Context context) {
            return (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        }

        @Named(NamedBooleanObservables.LOCATION_SERVICES_OK)
        @Provides
        public static Observable<Boolean> r(@Named("device-sdk") int i, LocationServicesOkObservableApi23Factory locationServicesOkObservableApi23Factory) {
            if (i < 23) {
                return ObservableUtil.justOnNext(Boolean.TRUE);
            }
            return locationServicesOkObservableApi23Factory.get();
        }

        @Provides
        public static LocationServicesStatus s(@Named("device-sdk") int i, Provider<LocationServicesStatusApi18> provider, Provider<LocationServicesStatusApi23> provider2, Provider<LocationServicesStatusApi31> provider3) {
            if (i < 23) {
                return provider.get();
            }
            if (i < 31) {
                return provider2.get();
            }
            return provider3.get();
        }

        @Named(PlatformConstants.STRING_ARRAY_CONNECT_PERMISSIONS)
        @Provides
        public static String[][] t(@Named("device-sdk") int i, @Named("target-sdk") int i2) {
            return Math.min(i, i2) < 31 ? new String[0] : new String[][]{new String[]{"android.permission.BLUETOOTH_CONNECT"}};
        }

        @Named(PlatformConstants.STRING_ARRAY_SCAN_PERMISSIONS)
        @Provides
        public static String[][] u(@Named("device-sdk") int i, @Named("target-sdk") int i2, @Named("nearby-permission-never-for-location") boolean z) {
            int min = Math.min(i, i2);
            return min < 23 ? new String[0] : min < 29 ? new String[][]{new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}} : min < 31 ? new String[][]{new String[]{"android.permission.ACCESS_FINE_LOCATION"}} : z ? new String[][]{new String[]{"android.permission.BLUETOOTH_SCAN"}} : new String[][]{new String[]{"android.permission.BLUETOOTH_SCAN"}, new String[]{"android.permission.ACCESS_FINE_LOCATION"}};
        }

        @Provides
        public static ScanPreconditionsVerifier v(@Named("device-sdk") int i, Provider<ScanPreconditionsVerifierApi18> provider, Provider<ScanPreconditionsVerifierApi24> provider2) {
            if (i < 24) {
                return provider.get();
            }
            return provider2.get();
        }

        @ClientScope
        @Provides
        public static ScanSetupBuilder w(@Named("device-sdk") int i, Provider<ScanSetupBuilderImplApi18> provider, Provider<ScanSetupBuilderImplApi21> provider2, Provider<ScanSetupBuilderImplApi23> provider3) {
            if (i < 21) {
                return provider.get();
            }
            if (i < 23) {
                return provider2.get();
            }
            return provider3.get();
        }

        @Named(PlatformConstants.INT_TARGET_SDK)
        @Provides
        public static int x(Context context) {
            try {
                return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).targetSdkVersion;
            } catch (Throwable unused) {
                return Integer.MAX_VALUE;
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class NamedBooleanObservables {
        public static final String LOCATION_SERVICES_OK = "location-ok-boolean-observable";
    }

    /* loaded from: classes9.dex */
    public static class NamedExecutors {
        public static final String BLUETOOTH_INTERACTION = "executor_bluetooth_interaction";
        public static final String CONNECTION_QUEUE = "executor_connection_queue";
    }

    /* loaded from: classes9.dex */
    public static class NamedSchedulers {
        public static final String BLUETOOTH_CALLBACKS = "bluetooth_callbacks";
        public static final String BLUETOOTH_INTERACTION = "bluetooth_interaction";
        public static final String COMPUTATION = "computation";
        public static final String TIMEOUT = "timeout";
    }

    /* loaded from: classes9.dex */
    public static class PlatformConstants {
        public static final String BOOL_IS_ANDROID_WEAR = "android-wear";
        public static final String BOOL_IS_NEARBY_PERMISSION_NEVER_FOR_LOCATION = "nearby-permission-never-for-location";
        public static final String INT_DEVICE_SDK = "device-sdk";
        public static final String INT_TARGET_SDK = "target-sdk";
        public static final String STRING_ARRAY_CONNECT_PERMISSIONS = "connect-permissions";
        public static final String STRING_ARRAY_SCAN_PERMISSIONS = "scan-permissions";
    }

    LocationServicesOkObservable locationServicesOkObservable();

    RxBleClient rxBleClient();
}
