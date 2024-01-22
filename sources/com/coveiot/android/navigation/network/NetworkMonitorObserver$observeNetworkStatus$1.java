package com.coveiot.android.navigation.network;

import android.net.ConnectivityManager;
import android.net.Network;
import com.coveiot.android.navigation.network.NetworkMonitor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.navigation.network.NetworkMonitorObserver$observeNetworkStatus$1", f = "NetworkMonitorObserver.kt", i = {}, l = {47}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class NetworkMonitorObserver$observeNetworkStatus$1 extends SuspendLambda implements Function2<ProducerScope<? super NetworkMonitor.Status>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ NetworkMonitorObserver this$0;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public final /* synthetic */ NetworkMonitorObserver$observeNetworkStatus$1$callback$1 $callback;
        public final /* synthetic */ NetworkMonitorObserver this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(NetworkMonitorObserver networkMonitorObserver, NetworkMonitorObserver$observeNetworkStatus$1$callback$1 networkMonitorObserver$observeNetworkStatus$1$callback$1) {
            super(0);
            this.this$0 = networkMonitorObserver;
            this.$callback = networkMonitorObserver$observeNetworkStatus$1$callback$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ConnectivityManager connectivityManager;
            connectivityManager = this.this$0.b;
            connectivityManager.unregisterNetworkCallback(this.$callback);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkMonitorObserver$observeNetworkStatus$1(NetworkMonitorObserver networkMonitorObserver, Continuation<? super NetworkMonitorObserver$observeNetworkStatus$1> continuation) {
        super(2, continuation);
        this.this$0 = networkMonitorObserver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        NetworkMonitorObserver$observeNetworkStatus$1 networkMonitorObserver$observeNetworkStatus$1 = new NetworkMonitorObserver$observeNetworkStatus$1(this.this$0, continuation);
        networkMonitorObserver$observeNetworkStatus$1.L$0 = obj;
        return networkMonitorObserver$observeNetworkStatus$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super NetworkMonitor.Status> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NetworkMonitorObserver$observeNetworkStatus$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.net.ConnectivityManager$NetworkCallback, com.coveiot.android.navigation.network.NetworkMonitorObserver$observeNetworkStatus$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ConnectivityManager connectivityManager;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            ?? r1 = new ConnectivityManager.NetworkCallback() { // from class: com.coveiot.android.navigation.network.NetworkMonitorObserver$observeNetworkStatus$1$callback$1

                @DebugMetadata(c = "com.coveiot.android.navigation.network.NetworkMonitorObserver$observeNetworkStatus$1$callback$1$onAvailable$1", f = "NetworkMonitorObserver.kt", i = {}, l = {28}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ProducerScope<NetworkMonitor.Status> $$this$callbackFlow;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public a(ProducerScope<? super NetworkMonitor.Status> producerScope, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$$this$callbackFlow = producerScope;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$$this$callbackFlow, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            ProducerScope<NetworkMonitor.Status> producerScope = this.$$this$callbackFlow;
                            NetworkMonitor.Status status = NetworkMonitor.Status.Available;
                            this.label = 1;
                            if (producerScope.send(status, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.navigation.network.NetworkMonitorObserver$observeNetworkStatus$1$callback$1$onLosing$1", f = "NetworkMonitorObserver.kt", i = {}, l = {33}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ProducerScope<NetworkMonitor.Status> $$this$callbackFlow;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public b(ProducerScope<? super NetworkMonitor.Status> producerScope, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$$this$callbackFlow = producerScope;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.$$this$callbackFlow, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            ProducerScope<NetworkMonitor.Status> producerScope = this.$$this$callbackFlow;
                            NetworkMonitor.Status status = NetworkMonitor.Status.Losing;
                            this.label = 1;
                            if (producerScope.send(status, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.navigation.network.NetworkMonitorObserver$observeNetworkStatus$1$callback$1$onLost$1", f = "NetworkMonitorObserver.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ProducerScope<NetworkMonitor.Status> $$this$callbackFlow;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public c(ProducerScope<? super NetworkMonitor.Status> producerScope, Continuation<? super c> continuation) {
                        super(2, continuation);
                        this.$$this$callbackFlow = producerScope;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new c(this.$$this$callbackFlow, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            ProducerScope<NetworkMonitor.Status> producerScope = this.$$this$callbackFlow;
                            NetworkMonitor.Status status = NetworkMonitor.Status.Lost;
                            this.label = 1;
                            if (producerScope.send(status, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.navigation.network.NetworkMonitorObserver$observeNetworkStatus$1$callback$1$onUnavailable$1", f = "NetworkMonitorObserver.kt", i = {}, l = {43}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ProducerScope<NetworkMonitor.Status> $$this$callbackFlow;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public d(ProducerScope<? super NetworkMonitor.Status> producerScope, Continuation<? super d> continuation) {
                        super(2, continuation);
                        this.$$this$callbackFlow = producerScope;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new d(this.$$this$callbackFlow, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            ProducerScope<NetworkMonitor.Status> producerScope = this.$$this$callbackFlow;
                            NetworkMonitor.Status status = NetworkMonitor.Status.Unavailable;
                            this.label = 1;
                            if (producerScope.send(status, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(@NotNull Network network) {
                    Intrinsics.checkNotNullParameter(network, "network");
                    super.onAvailable(network);
                    ProducerScope<NetworkMonitor.Status> producerScope2 = producerScope;
                    e.e(producerScope2, null, null, new a(producerScope2, null), 3, null);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLosing(@NotNull Network network, int i2) {
                    Intrinsics.checkNotNullParameter(network, "network");
                    super.onLosing(network, i2);
                    ProducerScope<NetworkMonitor.Status> producerScope2 = producerScope;
                    e.e(producerScope2, null, null, new b(producerScope2, null), 3, null);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(@NotNull Network network) {
                    Intrinsics.checkNotNullParameter(network, "network");
                    super.onLost(network);
                    ProducerScope<NetworkMonitor.Status> producerScope2 = producerScope;
                    e.e(producerScope2, null, null, new c(producerScope2, null), 3, null);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onUnavailable() {
                    super.onUnavailable();
                    ProducerScope<NetworkMonitor.Status> producerScope2 = producerScope;
                    e.e(producerScope2, null, null, new d(producerScope2, null), 3, null);
                }
            };
            connectivityManager = this.this$0.b;
            connectivityManager.registerDefaultNetworkCallback(r1);
            a aVar = new a(this.this$0, r1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
