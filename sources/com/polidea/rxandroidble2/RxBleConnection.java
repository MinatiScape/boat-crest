package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.internal.Priority;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public interface RxBleConnection {
    public static final int GATT_MTU_MAXIMUM = 515;
    public static final int GATT_MTU_MINIMUM = 23;
    public static final int GATT_READ_MTU_OVERHEAD = 1;
    public static final int GATT_WRITE_MTU_OVERHEAD = 3;

    @RequiresApi(21)
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface ConnectionPriority {
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public interface Connector {
        Single<RxBleConnection> prepareConnection(boolean z);
    }

    /* loaded from: classes9.dex */
    public interface LongWriteOperationBuilder {
        Observable<byte[]> build();

        LongWriteOperationBuilder setBytes(@NonNull byte[] bArr);

        LongWriteOperationBuilder setCharacteristic(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic);

        LongWriteOperationBuilder setCharacteristicUuid(@NonNull UUID uuid);

        LongWriteOperationBuilder setMaxBatchSize(@IntRange(from = 1, to = 512) int i);

        LongWriteOperationBuilder setWriteOperationAckStrategy(@NonNull WriteOperationAckStrategy writeOperationAckStrategy);

        LongWriteOperationBuilder setWriteOperationRetryStrategy(@NonNull WriteOperationRetryStrategy writeOperationRetryStrategy);
    }

    /* loaded from: classes9.dex */
    public enum RxBleConnectionState {
        CONNECTING("CONNECTING"),
        CONNECTED("CONNECTED"),
        DISCONNECTED("DISCONNECTED"),
        DISCONNECTING("DISCONNECTING");
        
        private final String description;

        RxBleConnectionState(String str) {
            this.description = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "RxBleConnectionState{" + this.description + '}';
        }
    }

    /* loaded from: classes9.dex */
    public interface WriteOperationAckStrategy extends ObservableTransformer<Boolean, Boolean> {
    }

    /* loaded from: classes9.dex */
    public interface WriteOperationRetryStrategy extends ObservableTransformer<LongWriteFailure, LongWriteFailure> {

        /* loaded from: classes9.dex */
        public static class LongWriteFailure {

            /* renamed from: a  reason: collision with root package name */
            public final int f13376a;
            public final BleGattException b;

            public LongWriteFailure(int i, BleGattException bleGattException) {
                this.f13376a = i;
                this.b = bleGattException;
            }

            public int getBatchIndex() {
                return this.f13376a;
            }

            public BleGattException getCause() {
                return this.b;
            }
        }
    }

    LongWriteOperationBuilder createNewLongWriteBuilder();

    Single<RxBleDeviceServices> discoverServices();

    Single<RxBleDeviceServices> discoverServices(@IntRange(from = 1) long j, @NonNull TimeUnit timeUnit);

    @Deprecated
    Single<BluetoothGattCharacteristic> getCharacteristic(@NonNull UUID uuid);

    int getMtu();

    @RequiresApi(26)
    Observable<ConnectionParameters> observeConnectionParametersUpdates();

    <T> Observable<T> queue(@NonNull RxBleCustomOperation<T> rxBleCustomOperation);

    <T> Observable<T> queue(@NonNull RxBleCustomOperation<T> rxBleCustomOperation, Priority priority);

    Single<byte[]> readCharacteristic(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Single<byte[]> readCharacteristic(@NonNull UUID uuid);

    Single<byte[]> readDescriptor(@NonNull BluetoothGattDescriptor bluetoothGattDescriptor);

    Single<byte[]> readDescriptor(@NonNull UUID uuid, @NonNull UUID uuid2, @NonNull UUID uuid3);

    Single<Integer> readRssi();

    @RequiresApi(21)
    Completable requestConnectionPriority(int i, @IntRange(from = 1) long j, @NonNull TimeUnit timeUnit);

    @RequiresApi(21)
    Single<Integer> requestMtu(@IntRange(from = 23, to = 515) int i);

    Observable<Observable<byte[]>> setupIndication(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<Observable<byte[]>> setupIndication(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupIndication(@NonNull UUID uuid);

    Observable<Observable<byte[]>> setupIndication(@NonNull UUID uuid, @NonNull NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupNotification(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<Observable<byte[]>> setupNotification(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupNotification(@NonNull UUID uuid);

    Observable<Observable<byte[]>> setupNotification(@NonNull UUID uuid, @NonNull NotificationSetupMode notificationSetupMode);

    Single<byte[]> writeCharacteristic(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr);

    Single<byte[]> writeCharacteristic(@NonNull UUID uuid, @NonNull byte[] bArr);

    Completable writeDescriptor(@NonNull BluetoothGattDescriptor bluetoothGattDescriptor, @NonNull byte[] bArr);

    Completable writeDescriptor(@NonNull UUID uuid, @NonNull UUID uuid2, @NonNull UUID uuid3, @NonNull byte[] bArr);
}
