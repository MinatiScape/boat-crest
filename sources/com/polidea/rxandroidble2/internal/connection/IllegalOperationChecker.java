package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.BleIllegalOperationException;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
/* loaded from: classes12.dex */
public class IllegalOperationChecker {

    /* renamed from: a  reason: collision with root package name */
    public final IllegalOperationHandler f13412a;

    /* loaded from: classes12.dex */
    public class a implements Action {
        public final /* synthetic */ BluetoothGattCharacteristic h;
        public final /* synthetic */ int i;

        public a(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            this.h = bluetoothGattCharacteristic;
            this.i = i;
        }

        @Override // io.reactivex.functions.Action
        public void run() {
            BleIllegalOperationException handleMismatchData;
            int properties = this.h.getProperties();
            int i = this.i;
            if ((properties & i) == 0 && (handleMismatchData = IllegalOperationChecker.this.f13412a.handleMismatchData(this.h, i)) != null) {
                throw handleMismatchData;
            }
        }
    }

    @Inject
    public IllegalOperationChecker(IllegalOperationHandler illegalOperationHandler) {
        this.f13412a = illegalOperationHandler;
    }

    public Completable checkAnyPropertyMatches(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        return Completable.fromAction(new a(bluetoothGattCharacteristic, i));
    }
}
