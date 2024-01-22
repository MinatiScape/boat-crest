package com.polidea.rxandroidble2.internal.connection;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.operations.DisconnectOperation;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
import io.reactivex.internal.functions.Functions;
@ConnectionScope
/* loaded from: classes12.dex */
public class g implements ConnectionSubscriptionWatcher {
    public final ClientOperationQueue h;
    public final DisconnectOperation i;

    @Inject
    public g(ClientOperationQueue clientOperationQueue, DisconnectOperation disconnectOperation) {
        this.h = clientOperationQueue;
        this.i = disconnectOperation;
    }

    @Override // com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionSubscribed() {
    }

    @Override // com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionUnsubscribed() {
        this.h.queue(this.i).subscribe(Functions.emptyConsumer(), Functions.emptyConsumer());
    }
}
