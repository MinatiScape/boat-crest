package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import androidx.annotation.NonNull;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.UUID;
/* loaded from: classes12.dex */
public final class LongWriteOperationBuilderImpl implements RxBleConnection.LongWriteOperationBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final ConnectionOperationQueue f13417a;
    public final RxBleConnection b;
    public final OperationsProvider c;
    public Single<BluetoothGattCharacteristic> d;
    public PayloadSizeLimitProvider e;
    public RxBleConnection.WriteOperationAckStrategy f = new ImmediateSerializedBatchAckStrategy();
    public RxBleConnection.WriteOperationRetryStrategy g = new NoRetryStrategy();
    public byte[] h;

    /* loaded from: classes12.dex */
    public class a implements Function<RxBleDeviceServices, SingleSource<? extends BluetoothGattCharacteristic>> {
        public final /* synthetic */ UUID h;

        public a(LongWriteOperationBuilderImpl longWriteOperationBuilderImpl, UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<? extends BluetoothGattCharacteristic> apply(RxBleDeviceServices rxBleDeviceServices) throws Exception {
            return rxBleDeviceServices.getCharacteristic(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Function<BluetoothGattCharacteristic, Observable<byte[]>> {
        public b() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<byte[]> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            LongWriteOperationBuilderImpl longWriteOperationBuilderImpl = LongWriteOperationBuilderImpl.this;
            return longWriteOperationBuilderImpl.f13417a.queue(longWriteOperationBuilderImpl.c.provideLongWriteOperation(bluetoothGattCharacteristic, longWriteOperationBuilderImpl.f, longWriteOperationBuilderImpl.g, longWriteOperationBuilderImpl.e, longWriteOperationBuilderImpl.h));
        }
    }

    @Inject
    public LongWriteOperationBuilderImpl(ConnectionOperationQueue connectionOperationQueue, i iVar, RxBleConnection rxBleConnection, OperationsProvider operationsProvider) {
        this.f13417a = connectionOperationQueue;
        this.e = iVar;
        this.b = rxBleConnection;
        this.c = operationsProvider;
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection.LongWriteOperationBuilder
    public Observable<byte[]> build() {
        Single<BluetoothGattCharacteristic> single = this.d;
        if (single != null) {
            if (this.h != null) {
                return single.flatMapObservable(new b());
            }
            throw new IllegalArgumentException("setBytes() needs to be called before build()");
        }
        throw new IllegalArgumentException("setCharacteristicUuid() or setCharacteristic() needs to be called before build()");
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setBytes(@NonNull byte[] bArr) {
        this.h = bArr;
        return this;
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setCharacteristic(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.d = Single.just(bluetoothGattCharacteristic);
        return this;
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setCharacteristicUuid(@NonNull UUID uuid) {
        this.d = this.b.discoverServices().flatMap(new a(this, uuid));
        return this;
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setMaxBatchSize(int i) {
        this.e = new e(i);
        return this;
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setWriteOperationAckStrategy(@NonNull RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy) {
        this.f = writeOperationAckStrategy;
        return this;
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setWriteOperationRetryStrategy(@NonNull RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy) {
        this.g = writeOperationRetryStrategy;
        return this;
    }
}
