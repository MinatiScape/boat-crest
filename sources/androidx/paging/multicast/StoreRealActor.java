package androidx.paging.multicast;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u0000 \u000f*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u000fB\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u001b\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\t\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Landroidx/paging/multicast/StoreRealActor;", ExifInterface.GPS_DIRECTION_TRUE, "", "", "onClosed", "msg", "handle", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", MqttServiceConstants.SEND_ACTION, Constants.KEY_HIDE_CLOSE, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "<init>", "(Lkotlinx/coroutines/CoroutineScope;)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class StoreRealActor<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final Object d = new Object();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Channel<Object> f1566a;
    @NotNull
    public final CompletableDeferred<Unit> b;
    @NotNull
    public final AtomicBoolean c;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\u0002\u001a\u00020\u00018\u0006@\u0006¢\u0006\f\n\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Landroidx/paging/multicast/StoreRealActor$Companion;", "", "CLOSE_TOKEN", "Ljava/lang/Object;", "getCLOSE_TOKEN", "()Ljava/lang/Object;", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Object getCLOSE_TOKEN() {
            return StoreRealActor.d;
        }
    }

    @DebugMetadata(c = "androidx.paging.multicast.StoreRealActor$1", f = "StoreRealActor.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object L$0;
        public int label;
        public final /* synthetic */ StoreRealActor<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(StoreRealActor<T> storeRealActor, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = storeRealActor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.this$0, continuation);
            aVar.L$0 = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@Nullable Object obj, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                if (obj2 == StoreRealActor.Companion.getCLOSE_TOKEN()) {
                    this.this$0.a();
                } else {
                    this.label = 1;
                    if (this.this$0.handle(obj2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "androidx.paging.multicast.StoreRealActor$2", f = "StoreRealActor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function3<FlowCollector<? super Object>, Throwable, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ StoreRealActor<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(StoreRealActor<T> storeRealActor, Continuation<? super b> continuation) {
            super(3, continuation);
            this.this$0 = storeRealActor;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Object> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<Object>) flowCollector, th, continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull FlowCollector<Object> flowCollector, @Nullable Throwable th, @Nullable Continuation<? super Unit> continuation) {
            return new b(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.a();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "androidx.paging.multicast.StoreRealActor", f = "StoreRealActor.kt", i = {0}, l = {74, 76}, m = Constants.KEY_HIDE_CLOSE, n = {"this"}, s = {"L$0"})
    /* loaded from: classes.dex */
    public static final class c extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ StoreRealActor<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(StoreRealActor<T> storeRealActor, Continuation<? super c> continuation) {
            super(continuation);
            this.this$0 = storeRealActor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.close(this);
        }
    }

    public StoreRealActor(@NotNull CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Channel<Object> Channel$default = ChannelKt.Channel$default(0, null, null, 6, null);
        this.f1566a = Channel$default;
        this.b = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.c = new AtomicBoolean(false);
        FlowKt.launchIn(FlowKt.onCompletion(FlowKt.onEach(FlowKt.consumeAsFlow(Channel$default), new a(this, null)), new b(this, null)), scope);
    }

    public final void a() {
        if (this.c.compareAndSet(false, true)) {
            try {
                onClosed();
            } finally {
                SendChannel.DefaultImpls.close$default(this.f1566a, null, 1, null);
                this.b.complete(Unit.INSTANCE);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005c A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object close(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.paging.multicast.StoreRealActor.c
            if (r0 == 0) goto L13
            r0 = r6
            androidx.paging.multicast.StoreRealActor$c r0 = (androidx.paging.multicast.StoreRealActor.c) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.multicast.StoreRealActor$c r0 = new androidx.paging.multicast.StoreRealActor$c
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5d
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L34:
            java.lang.Object r2 = r0.L$0
            androidx.paging.multicast.StoreRealActor r2 = (androidx.paging.multicast.StoreRealActor) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L3c:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.Channel<java.lang.Object> r6 = r5.f1566a
            java.lang.Object r2 = androidx.paging.multicast.StoreRealActor.d
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r6.send(r2, r0)
            if (r6 != r1) goto L4e
            return r1
        L4e:
            r2 = r5
        L4f:
            kotlinx.coroutines.CompletableDeferred<kotlin.Unit> r6 = r2.b
            r2 = 0
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r6.await(r0)
            if (r6 != r1) goto L5d
            return r1
        L5d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.StoreRealActor.close(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public abstract Object handle(T t, @NotNull Continuation<? super Unit> continuation);

    public void onClosed() {
    }

    @Nullable
    public final Object send(T t, @NotNull Continuation<? super Unit> continuation) {
        Object send = this.f1566a.send(t, continuation);
        return send == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }
}
