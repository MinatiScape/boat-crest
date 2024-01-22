package com.polidea.rxandroidble2.internal.connection;

import com.polidea.rxandroidble2.ConnectionSetup;
import com.polidea.rxandroidble2.RxBleConnection;
import io.reactivex.Observable;
/* loaded from: classes12.dex */
public interface Connector {
    Observable<RxBleConnection> prepareConnection(ConnectionSetup connectionSetup);
}
