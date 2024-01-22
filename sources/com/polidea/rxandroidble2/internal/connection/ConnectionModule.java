package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.Module;
import bleshadow.dagger.Provides;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.Timeout;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
import io.reactivex.Scheduler;
@Module
/* loaded from: classes12.dex */
public abstract class ConnectionModule {
    public static final String OPERATION_TIMEOUT = "operation-timeout";

    @Named("GATT_WRITE_MTU_OVERHEAD")
    @Provides
    public static int a() {
        return 3;
    }

    @Named("GATT_MTU_MINIMUM")
    @Provides
    public static int b() {
        return 23;
    }

    @Provides
    public static BluetoothGatt c(BluetoothGattProvider bluetoothGattProvider) {
        return bluetoothGattProvider.getBluetoothGatt();
    }

    @Provides
    public static CharacteristicPropertiesParser d() {
        return new CharacteristicPropertiesParser(1, 2, 4, 8, 16, 32, 64);
    }

    @Provides
    public static IllegalOperationHandler e(@Named("suppressOperationChecks") boolean z, Provider<LoggingIllegalOperationHandler> provider, Provider<ThrowingIllegalOperationHandler> provider2) {
        if (z) {
            return provider.get();
        }
        return provider2.get();
    }

    @Named("operation-timeout")
    @Provides
    public static TimeoutConfiguration f(@Named("timeout") Scheduler scheduler, Timeout timeout) {
        return new TimeoutConfiguration(timeout.timeout, timeout.timeUnit, scheduler);
    }
}
