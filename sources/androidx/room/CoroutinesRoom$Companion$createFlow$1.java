package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [R] */
@DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", f = "CoroutinesRoom.kt", i = {0, 0, 0, 0, 0}, l = {75}, m = "invokeSuspend", n = {"$this$flow", "observerChannel", "observer", "flowContext", "queryContext"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes.dex */
public final class CoroutinesRoom$Companion$createFlow$1<R> extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Callable $callable;
    public final /* synthetic */ RoomDatabase $db;
    public final /* synthetic */ boolean $inTransaction;
    public final /* synthetic */ String[] $tableNames;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;
    private FlowCollector p$;

    @DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", f = "CoroutinesRoom.kt", i = {0, 1, 1, 1}, l = {80, 82}, m = "invokeSuspend", n = {"$this$withContext", "$this$withContext", "signal", "result"}, s = {"L$0", "L$0", "L$1", "L$3"})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ CoroutineContext $flowContext;
        public final /* synthetic */ CoroutinesRoom$Companion$createFlow$1$observer$1 $observer;
        public final /* synthetic */ Channel $observerChannel;
        public final /* synthetic */ FlowCollector $this_flow;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        private CoroutineScope p$;

        @DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1", f = "CoroutinesRoom.kt", i = {0}, l = {82}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
        /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0178a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Object $result;
            public Object L$0;
            public int label;
            private CoroutineScope p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0178a(Object obj, Continuation continuation) {
                super(2, continuation);
                this.$result = obj;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C0178a c0178a = new C0178a(this.$result, completion);
                c0178a.p$ = (CoroutineScope) obj;
                return c0178a;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C0178a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.p$;
                    FlowCollector flowCollector = a.this.$this_flow;
                    Object obj2 = this.$result;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (flowCollector.emit(obj2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(FlowCollector flowCollector, CoroutinesRoom$Companion$createFlow$1$observer$1 coroutinesRoom$Companion$createFlow$1$observer$1, Channel channel, CoroutineContext coroutineContext, Continuation continuation) {
            super(2, continuation);
            this.$this_flow = flowCollector;
            this.$observer = coroutinesRoom$Companion$createFlow$1$observer$1;
            this.$observerChannel = channel;
            this.$flowContext = coroutineContext;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            a aVar = new a(this.$this_flow, this.$observer, this.$observerChannel, this.$flowContext, completion);
            aVar.p$ = (CoroutineScope) obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x005a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0066 A[Catch: all -> 0x009f, TRY_LEAVE, TryCatch #1 {all -> 0x009f, blocks: (B:17:0x004e, B:21:0x005e, B:23:0x0066), top: B:36:0x004e }] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x008d -> B:36:0x004e). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L35
                if (r1 == r3) goto L27
                if (r1 != r2) goto L1f
                java.lang.Object r1 = r11.L$2
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r4 = r11.L$1
                kotlin.Unit r4 = (kotlin.Unit) r4
                java.lang.Object r4 = r11.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> La1
                r12 = r4
                goto L4d
            L1f:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L27:
                java.lang.Object r1 = r11.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r4 = r11.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> La1
                r5 = r4
                r4 = r11
                goto L5e
            L35:
                kotlin.ResultKt.throwOnFailure(r12)
                kotlinx.coroutines.CoroutineScope r12 = r11.p$
                androidx.room.CoroutinesRoom$Companion$createFlow$1 r1 = androidx.room.CoroutinesRoom$Companion$createFlow$1.this
                androidx.room.RoomDatabase r1 = r1.$db
                androidx.room.InvalidationTracker r1 = r1.getInvalidationTracker()
                androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1 r4 = r11.$observer
                r1.addObserver(r4)
                kotlinx.coroutines.channels.Channel r1 = r11.$observerChannel     // Catch: java.lang.Throwable -> La1
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> La1
            L4d:
                r4 = r11
            L4e:
                r4.L$0 = r12     // Catch: java.lang.Throwable -> L9f
                r4.L$1 = r1     // Catch: java.lang.Throwable -> L9f
                r4.label = r3     // Catch: java.lang.Throwable -> L9f
                java.lang.Object r5 = r1.hasNext(r4)     // Catch: java.lang.Throwable -> L9f
                if (r5 != r0) goto L5b
                return r0
            L5b:
                r10 = r5
                r5 = r12
                r12 = r10
            L5e:
                java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch: java.lang.Throwable -> L9f
                boolean r12 = r12.booleanValue()     // Catch: java.lang.Throwable -> L9f
                if (r12 == 0) goto L8f
                java.lang.Object r12 = r1.next()     // Catch: java.lang.Throwable -> L9f
                kotlin.Unit r12 = (kotlin.Unit) r12     // Catch: java.lang.Throwable -> L9f
                androidx.room.CoroutinesRoom$Companion$createFlow$1 r6 = androidx.room.CoroutinesRoom$Companion$createFlow$1.this     // Catch: java.lang.Throwable -> L9f
                java.util.concurrent.Callable r6 = r6.$callable     // Catch: java.lang.Throwable -> L9f
                java.lang.Object r6 = r6.call()     // Catch: java.lang.Throwable -> L9f
                kotlin.coroutines.CoroutineContext r7 = r4.$flowContext     // Catch: java.lang.Throwable -> L9f
                androidx.room.CoroutinesRoom$Companion$createFlow$1$a$a r8 = new androidx.room.CoroutinesRoom$Companion$createFlow$1$a$a     // Catch: java.lang.Throwable -> L9f
                r9 = 0
                r8.<init>(r6, r9)     // Catch: java.lang.Throwable -> L9f
                r4.L$0 = r5     // Catch: java.lang.Throwable -> L9f
                r4.L$1 = r12     // Catch: java.lang.Throwable -> L9f
                r4.L$2 = r1     // Catch: java.lang.Throwable -> L9f
                r4.L$3 = r6     // Catch: java.lang.Throwable -> L9f
                r4.label = r2     // Catch: java.lang.Throwable -> L9f
                java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r4)     // Catch: java.lang.Throwable -> L9f
                if (r12 != r0) goto L8d
                return r0
            L8d:
                r12 = r5
                goto L4e
            L8f:
                androidx.room.CoroutinesRoom$Companion$createFlow$1 r12 = androidx.room.CoroutinesRoom$Companion$createFlow$1.this
                androidx.room.RoomDatabase r12 = r12.$db
                androidx.room.InvalidationTracker r12 = r12.getInvalidationTracker()
                androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1 r0 = r4.$observer
                r12.removeObserver(r0)
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            L9f:
                r12 = move-exception
                goto La3
            La1:
                r12 = move-exception
                r4 = r11
            La3:
                androidx.room.CoroutinesRoom$Companion$createFlow$1 r0 = androidx.room.CoroutinesRoom$Companion$createFlow$1.this
                androidx.room.RoomDatabase r0 = r0.$db
                androidx.room.InvalidationTracker r0 = r0.getInvalidationTracker()
                androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1 r1 = r4.$observer
                r0.removeObserver(r1)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.CoroutinesRoom$Companion$createFlow$1.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$createFlow$1(String[] strArr, boolean z, RoomDatabase roomDatabase, Callable callable, Continuation continuation) {
        super(2, continuation);
        this.$tableNames = strArr;
        this.$inTransaction = z;
        this.$db = roomDatabase;
        this.$callable = callable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.$tableNames, this.$inTransaction, this.$db, this.$callable, completion);
        coroutinesRoom$Companion$createFlow$1.p$ = (FlowCollector) obj;
        return coroutinesRoom$Companion$createFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((CoroutinesRoom$Companion$createFlow$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object, androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.p$;
            final Channel Channel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
            final String[] strArr = this.$tableNames;
            ?? r10 = new InvalidationTracker.Observer(this, strArr) { // from class: androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1
                @Override // androidx.room.InvalidationTracker.Observer
                public void onInvalidated(@NotNull Set<String> tables) {
                    Intrinsics.checkParameterIsNotNull(tables, "tables");
                    Channel$default.offer(Unit.INSTANCE);
                }
            };
            Channel$default.offer(Unit.INSTANCE);
            CoroutineContext context = getContext();
            CoroutineDispatcher transactionDispatcher = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
            a aVar = new a(flowCollector, r10, Channel$default, context, null);
            this.L$0 = flowCollector;
            this.L$1 = Channel$default;
            this.L$2 = r10;
            this.L$3 = context;
            this.L$4 = transactionDispatcher;
            this.label = 1;
            if (BuildersKt.withContext(transactionDispatcher, aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) this.L$4;
            CoroutineContext coroutineContext = (CoroutineContext) this.L$3;
            CoroutinesRoom$Companion$createFlow$1$observer$1 coroutinesRoom$Companion$createFlow$1$observer$1 = (CoroutinesRoom$Companion$createFlow$1$observer$1) this.L$2;
            Channel channel = (Channel) this.L$1;
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
