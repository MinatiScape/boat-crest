package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import io.reactivex.Completable;
@ConnectionScope
/* loaded from: classes12.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public final ConnectionOperationQueue f13432a;
    public final OperationsProvider b;

    @Inject
    public f(ConnectionOperationQueue connectionOperationQueue, OperationsProvider operationsProvider) {
        this.f13432a = connectionOperationQueue;
        this.b = operationsProvider;
    }

    public Completable a(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return this.f13432a.queue(this.b.provideWriteDescriptor(bluetoothGattDescriptor, bArr)).ignoreElements();
    }
}
