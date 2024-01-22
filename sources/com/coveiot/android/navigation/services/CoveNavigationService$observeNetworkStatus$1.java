package com.coveiot.android.navigation.services;

import com.coveiot.android.navigation.interfaces.CoveNavigationListener;
import com.coveiot.android.navigation.network.NetworkMonitor;
import com.coveiot.android.navigation.utils.CoveNavigationCallBacks;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.navigation.services.CoveNavigationService$observeNetworkStatus$1", f = "CoveNavigationService.kt", i = {}, l = {549}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class CoveNavigationService$observeNetworkStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ CoveNavigationService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoveNavigationService$observeNetworkStatus$1(CoveNavigationService coveNavigationService, Continuation<? super CoveNavigationService$observeNetworkStatus$1> continuation) {
        super(2, continuation);
        this.this$0 = coveNavigationService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new CoveNavigationService$observeNetworkStatus$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((CoveNavigationService$observeNetworkStatus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        NetworkMonitor q;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            q = this.this$0.q();
            Flow<NetworkMonitor.Status> observeNetworkStatus = q.observeNetworkStatus();
            final CoveNavigationService coveNavigationService = this.this$0;
            FlowCollector<? super NetworkMonitor.Status> flowCollector = new FlowCollector() { // from class: com.coveiot.android.navigation.services.CoveNavigationService$observeNetworkStatus$1.1

                /* renamed from: com.coveiot.android.navigation.services.CoveNavigationService$observeNetworkStatus$1$1$WhenMappings */
                /* loaded from: classes5.dex */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[NetworkMonitor.Status.values().length];
                        try {
                            iArr[NetworkMonitor.Status.Lost.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[NetworkMonitor.Status.Available.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* renamed from: com.coveiot.android.navigation.services.CoveNavigationService$observeNetworkStatus$1$1$a */
                /* loaded from: classes5.dex */
                public static final class a extends Lambda implements Function1<Boolean, Unit> {
                    public static final a INSTANCE = new a();

                    public a() {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                    }
                }

                /* renamed from: com.coveiot.android.navigation.services.CoveNavigationService$observeNetworkStatus$1$1$b */
                /* loaded from: classes5.dex */
                public static final class b extends Lambda implements Function1<Boolean, Unit> {
                    public static final b INSTANCE = new b();

                    public b() {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                    }
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                @Nullable
                /* renamed from: a */
                public final Object emit(@NotNull NetworkMonitor.Status status, @NotNull Continuation<? super Unit> continuation) {
                    CoveNavigationListener coveNavigationListener;
                    CoveNavigationListener coveNavigationListener2;
                    int i2 = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
                    if (i2 == 1) {
                        coveNavigationListener = CoveNavigationService.this.i;
                        if (coveNavigationListener != null) {
                            coveNavigationListener.onCommonEvents(CoveNavigationCallBacks.OnInternetLost);
                        }
                        CoveNavigationService.this.setNavigationStatusOnBand(1, a.INSTANCE);
                    } else if (i2 == 2) {
                        coveNavigationListener2 = CoveNavigationService.this.i;
                        if (coveNavigationListener2 != null) {
                            coveNavigationListener2.onCommonEvents(CoveNavigationCallBacks.OnInternetAvailable);
                        }
                        CoveNavigationService.this.setNavigationStatusOnBand(2, b.INSTANCE);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (observeNetworkStatus.collect(flowCollector, this) == coroutine_suspended) {
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
