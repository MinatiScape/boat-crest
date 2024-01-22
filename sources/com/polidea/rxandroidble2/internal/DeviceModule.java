package com.polidea.rxandroidble2.internal;

import android.bluetooth.BluetoothDevice;
import bleshadow.dagger.Module;
import bleshadow.dagger.Provides;
import bleshadow.javax.inject.Named;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.internal.connection.ConnectionComponent;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import io.reactivex.Scheduler;
import java.util.concurrent.TimeUnit;
@Module(subcomponents = {ConnectionComponent.class})
/* loaded from: classes9.dex */
public abstract class DeviceModule {
    public static final String CONNECT_TIMEOUT = "connect-timeout";
    public static final String DISCONNECT_TIMEOUT = "disconnect-timeout";
    public static final String MAC_ADDRESS = "mac-address";
    public static final String OPERATION_TIMEOUT = "operation-timeout";

    /* loaded from: classes9.dex */
    public class a implements ConnectionStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BehaviorRelay f13383a;

        public a(BehaviorRelay behaviorRelay) {
            this.f13383a = behaviorRelay;
        }

        @Override // com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener
        public void onConnectionStateChange(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
            this.f13383a.accept(rxBleConnectionState);
        }
    }

    @Provides
    public static BluetoothDevice a(@Named("mac-address") String str, RxBleAdapterWrapper rxBleAdapterWrapper) {
        return rxBleAdapterWrapper.getRemoteDevice(str);
    }

    @DeviceScope
    @Provides
    public static ConnectionStateChangeListener b(BehaviorRelay<RxBleConnection.RxBleConnectionState> behaviorRelay) {
        return new a(behaviorRelay);
    }

    @DeviceScope
    @Provides
    public static BehaviorRelay<RxBleConnection.RxBleConnectionState> c() {
        return BehaviorRelay.createDefault(RxBleConnection.RxBleConnectionState.DISCONNECTED);
    }

    @Named(CONNECT_TIMEOUT)
    @Provides
    public static TimeoutConfiguration d(@Named("timeout") Scheduler scheduler) {
        return new TimeoutConfiguration(35L, TimeUnit.SECONDS, scheduler);
    }

    @Named(DISCONNECT_TIMEOUT)
    @Provides
    public static TimeoutConfiguration e(@Named("timeout") Scheduler scheduler) {
        return new TimeoutConfiguration(10L, TimeUnit.SECONDS, scheduler);
    }
}
