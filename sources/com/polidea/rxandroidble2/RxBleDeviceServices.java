package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.exceptions.BleCharacteristicNotFoundException;
import com.polidea.rxandroidble2.exceptions.BleDescriptorNotFoundException;
import com.polidea.rxandroidble2.exceptions.BleServiceNotFoundException;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class RxBleDeviceServices {

    /* renamed from: a  reason: collision with root package name */
    public final List<BluetoothGattService> f13377a;

    /* loaded from: classes9.dex */
    public class a implements Predicate<BluetoothGattService> {
        public final /* synthetic */ UUID h;

        public a(RxBleDeviceServices rxBleDeviceServices, UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(BluetoothGattService bluetoothGattService) {
            return bluetoothGattService.getUuid().equals(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Callable<BluetoothGattCharacteristic> {
        public final /* synthetic */ UUID h;

        public b(UUID uuid) {
            this.h = uuid;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public BluetoothGattCharacteristic call() {
            for (BluetoothGattService bluetoothGattService : RxBleDeviceServices.this.f13377a) {
                BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(this.h);
                if (characteristic != null) {
                    return characteristic;
                }
            }
            throw new BleCharacteristicNotFoundException(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Function<BluetoothGattService, BluetoothGattCharacteristic> {
        public final /* synthetic */ UUID h;

        public c(RxBleDeviceServices rxBleDeviceServices, UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public BluetoothGattCharacteristic apply(BluetoothGattService bluetoothGattService) {
            BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(this.h);
            if (characteristic != null) {
                return characteristic;
            }
            throw new BleCharacteristicNotFoundException(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Function<BluetoothGattCharacteristic, BluetoothGattDescriptor> {
        public final /* synthetic */ UUID h;

        public d(RxBleDeviceServices rxBleDeviceServices, UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public BluetoothGattDescriptor apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(this.h);
            if (descriptor != null) {
                return descriptor;
            }
            throw new BleDescriptorNotFoundException(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public class e implements Function<BluetoothGattCharacteristic, BluetoothGattDescriptor> {
        public final /* synthetic */ UUID h;

        public e(RxBleDeviceServices rxBleDeviceServices, UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public BluetoothGattDescriptor apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(this.h);
            if (descriptor != null) {
                return descriptor;
            }
            throw new BleDescriptorNotFoundException(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public class f implements Function<BluetoothGattService, BluetoothGattCharacteristic> {
        public final /* synthetic */ UUID h;

        public f(RxBleDeviceServices rxBleDeviceServices, UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public BluetoothGattCharacteristic apply(BluetoothGattService bluetoothGattService) {
            return bluetoothGattService.getCharacteristic(this.h);
        }
    }

    public RxBleDeviceServices(List<BluetoothGattService> list) {
        this.f13377a = list;
    }

    public List<BluetoothGattService> getBluetoothGattServices() {
        return this.f13377a;
    }

    public Single<BluetoothGattCharacteristic> getCharacteristic(@NonNull UUID uuid) {
        return Single.fromCallable(new b(uuid));
    }

    public Single<BluetoothGattDescriptor> getDescriptor(UUID uuid, UUID uuid2) {
        return getCharacteristic(uuid).map(new d(this, uuid2));
    }

    public Single<BluetoothGattService> getService(@NonNull UUID uuid) {
        return Observable.fromIterable(this.f13377a).filter(new a(this, uuid)).firstElement().switchIfEmpty(Single.error(new BleServiceNotFoundException(uuid)));
    }

    public Single<BluetoothGattCharacteristic> getCharacteristic(@NonNull UUID uuid, @NonNull UUID uuid2) {
        return getService(uuid).map(new c(this, uuid2));
    }

    public Single<BluetoothGattDescriptor> getDescriptor(UUID uuid, UUID uuid2, UUID uuid3) {
        return getService(uuid).map(new f(this, uuid2)).map(new e(this, uuid3));
    }
}
