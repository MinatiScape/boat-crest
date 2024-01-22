package com.polidea.rxandroidble2.internal.serialization;

import com.polidea.rxandroidble2.exceptions.BleException;
/* loaded from: classes12.dex */
public interface ConnectionOperationQueue extends ClientOperationQueue {
    void terminate(BleException bleException);
}
