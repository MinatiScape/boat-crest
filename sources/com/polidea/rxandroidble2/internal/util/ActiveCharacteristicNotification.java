package com.polidea.rxandroidble2.internal.util;

import io.reactivex.Observable;
/* loaded from: classes12.dex */
public class ActiveCharacteristicNotification {
    public final boolean isIndication;
    public final Observable<Observable<byte[]>> notificationObservable;

    public ActiveCharacteristicNotification(Observable<Observable<byte[]>> observable, boolean z) {
        this.notificationObservable = observable;
        this.isIndication = z;
    }
}
