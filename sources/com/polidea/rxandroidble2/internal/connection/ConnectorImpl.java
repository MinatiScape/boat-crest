package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.ConnectionSetup;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.internal.connection.ConnectionComponent;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public class ConnectorImpl implements Connector {

    /* renamed from: a  reason: collision with root package name */
    public final ClientOperationQueue f13407a;
    public final ConnectionComponent.Builder b;
    public final Scheduler c;

    @Inject
    public ConnectorImpl(ClientOperationQueue clientOperationQueue, ConnectionComponent.Builder builder, @Named("bluetooth_callbacks") Scheduler scheduler) {
        this.f13407a = clientOperationQueue;
        this.b = builder;
        this.c = scheduler;
    }

    public static /* synthetic */ void e(Set set, Disposable disposable) throws Exception {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((ConnectionSubscriptionWatcher) it.next()).onConnectionSubscribed();
        }
    }

    public static /* synthetic */ void f(Set set) throws Exception {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((ConnectionSubscriptionWatcher) it.next()).onConnectionUnsubscribed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ObservableSource g(ConnectionSetup connectionSetup) throws Exception {
        ConnectionComponent build = this.b.autoConnect(connectionSetup.autoConnect).suppressOperationChecks(connectionSetup.suppressOperationCheck).operationTimeout(connectionSetup.operationTimeout).build();
        final Set<ConnectionSubscriptionWatcher> connectionSubscriptionWatchers = build.connectionSubscriptionWatchers();
        return i(build).mergeWith(h(build)).delaySubscription(d(build)).doOnSubscribe(new Consumer() { // from class: com.polidea.rxandroidble2.internal.connection.b
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ConnectorImpl.e(connectionSubscriptionWatchers, (Disposable) obj);
            }
        }).doFinally(new Action() { // from class: com.polidea.rxandroidble2.internal.connection.a
            @Override // io.reactivex.functions.Action
            public final void run() {
                ConnectorImpl.f(connectionSubscriptionWatchers);
            }
        }).subscribeOn(this.c).unsubscribeOn(this.c);
    }

    public static Observable<RxBleConnection> h(ConnectionComponent connectionComponent) {
        return connectionComponent.gattCallback().observeDisconnect();
    }

    public static Observable<RxBleConnection> i(final ConnectionComponent connectionComponent) {
        Objects.requireNonNull(connectionComponent);
        return Observable.fromCallable(new Callable() { // from class: com.polidea.rxandroidble2.internal.connection.c
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ConnectionComponent.this.rxBleConnection();
            }
        });
    }

    public Observable<BluetoothGatt> d(ConnectionComponent connectionComponent) {
        return this.f13407a.queue(connectionComponent.connectOperation());
    }

    @Override // com.polidea.rxandroidble2.internal.connection.Connector
    public Observable<RxBleConnection> prepareConnection(final ConnectionSetup connectionSetup) {
        return Observable.defer(new Callable() { // from class: com.polidea.rxandroidble2.internal.connection.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ObservableSource g;
                g = ConnectorImpl.this.g(connectionSetup);
                return g;
            }
        });
    }
}
