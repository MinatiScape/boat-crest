package com.polidea.rxandroidble2.internal.util;

import android.bluetooth.BluetoothGattDescriptor;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ByteAssociationUtil {

    /* loaded from: classes12.dex */
    public class a implements Predicate<ByteAssociation<UUID>> {
        public final /* synthetic */ UUID h;

        public a(UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(ByteAssociation<UUID> byteAssociation) {
            return byteAssociation.first.equals(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Function<ByteAssociation<?>, byte[]> {
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public byte[] apply(ByteAssociation<?> byteAssociation) {
            return byteAssociation.second;
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Predicate<ByteAssociation<BluetoothGattDescriptor>> {
        public final /* synthetic */ BluetoothGattDescriptor h;

        public c(BluetoothGattDescriptor bluetoothGattDescriptor) {
            this.h = bluetoothGattDescriptor;
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
            return byteAssociation.first.equals(this.h);
        }
    }

    public static Predicate<? super ByteAssociation<UUID>> characteristicUUIDPredicate(UUID uuid) {
        return new a(uuid);
    }

    public static Predicate<? super ByteAssociation<BluetoothGattDescriptor>> descriptorPredicate(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new c(bluetoothGattDescriptor);
    }

    public static Function<ByteAssociation<?>, byte[]> getBytesFromAssociation() {
        return new b();
    }
}
