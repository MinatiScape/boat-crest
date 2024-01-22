package kotlinx.coroutines.flow;

import com.goodix.ble.libcomx.util.AccessLock;
import com.realsil.sdk.dfu.DfuException;
import java.util.concurrent.CancellationException;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChildCancelledException;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import org.bouncycastle.math.Primes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final /* synthetic */ class j {

    /* loaded from: classes12.dex */
    public static final class a<T> extends Lambda implements Function1<T, Long> {
        public final /* synthetic */ long $timeoutMillis;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(long j) {
            super(1);
            this.$timeoutMillis = j;
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Long invoke(T t) {
            return Long.valueOf(this.$timeoutMillis);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Long invoke(Object obj) {
            return invoke((a<T>) obj);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends Lambda implements Function1<T, Long> {
        public final /* synthetic */ Function1<T, Duration> $timeout;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public b(Function1<? super T, Duration> function1) {
            super(1);
            this.$timeout = function1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Long invoke(Object obj) {
            return invoke((b<T>) obj);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Long invoke(T t) {
            return Long.valueOf(DelayKt.m726toDelayMillisLRDsOJo(this.$timeout.invoke(t).m644unboximpl()));
        }
    }

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {222, AccessLock.EVT_LOCK_ACQUIRED}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "timeoutMillis", "downstream", "values", "lastValue", "timeoutMillis"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    /* loaded from: classes12.dex */
    public static final class c<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Flow<T> $this_debounceInternal;
        public final /* synthetic */ Function1<T, Long> $timeoutMillisSelector;
        private /* synthetic */ Object L$0;
        public /* synthetic */ Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;

        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$1", f = "Delay.kt", i = {}, l = {233}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes12.dex */
        public static final class a extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            public final /* synthetic */ FlowCollector<T> $downstream;
            public final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(FlowCollector<? super T> flowCollector, Ref.ObjectRef<Object> objectRef, Continuation<? super a> continuation) {
                super(1, continuation);
                this.$downstream = flowCollector;
                this.$lastValue = objectRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
                return new a(this.$downstream, this.$lastValue, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Object invoke(@Nullable Continuation<? super Unit> continuation) {
                return ((a) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowCollector<T> flowCollector = this.$downstream;
                    Symbol symbol = NullSurrogateKt.NULL;
                    T t = this.$lastValue.element;
                    if (t == symbol) {
                        t = null;
                    }
                    this.label = 1;
                    if (flowCollector.emit(t, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                this.$lastValue.element = null;
                return Unit.INSTANCE;
            }
        }

        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$2", f = "Delay.kt", i = {0}, l = {243}, m = "invokeSuspend", n = {"$this$onFailure$iv"}, s = {"L$0"})
        /* loaded from: classes12.dex */
        public static final class b extends SuspendLambda implements Function2<ChannelResult<? extends Object>, Continuation<? super Unit>, Object> {
            public final /* synthetic */ FlowCollector<T> $downstream;
            public final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
            public /* synthetic */ Object L$0;
            public Object L$1;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public b(Ref.ObjectRef<Object> objectRef, FlowCollector<? super T> flowCollector, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$lastValue = objectRef;
                this.$downstream = flowCollector;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                b bVar = new b(this.$lastValue, this.$downstream, continuation);
                bVar.L$0 = obj;
                return bVar;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(ChannelResult<? extends Object> channelResult, Continuation<? super Unit> continuation) {
                return m762invokeWpGqRn0(channelResult.m752unboximpl(), continuation);
            }

            @Nullable
            /* renamed from: invoke-WpGqRn0 */
            public final Object m762invokeWpGqRn0(@NotNull Object obj, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(ChannelResult.m740boximpl(obj), continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Ref.ObjectRef<Object> objectRef;
                Ref.ObjectRef<Object> objectRef2;
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    T t = (T) ((ChannelResult) this.L$0).m752unboximpl();
                    objectRef = this.$lastValue;
                    boolean z = t instanceof ChannelResult.Failed;
                    if (!z) {
                        objectRef.element = t;
                    }
                    FlowCollector<T> flowCollector = this.$downstream;
                    if (z) {
                        Throwable m744exceptionOrNullimpl = ChannelResult.m744exceptionOrNullimpl(t);
                        if (m744exceptionOrNullimpl == null) {
                            Object obj2 = objectRef.element;
                            if (obj2 != null) {
                                if (obj2 == NullSurrogateKt.NULL) {
                                    obj2 = null;
                                }
                                this.L$0 = t;
                                this.L$1 = objectRef;
                                this.label = 1;
                                if (flowCollector.emit(obj2, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                objectRef2 = objectRef;
                            }
                            objectRef.element = (T) NullSurrogateKt.DONE;
                        } else {
                            throw m744exceptionOrNullimpl;
                        }
                    }
                    return Unit.INSTANCE;
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef2 = (Ref.ObjectRef) this.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                objectRef = objectRef2;
                objectRef.element = (T) NullSurrogateKt.DONE;
                return Unit.INSTANCE;
            }
        }

        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$values$1", f = "Delay.kt", i = {}, l = {Primes.SMALL_FACTOR_LIMIT}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: kotlinx.coroutines.flow.j$c$c */
        /* loaded from: classes12.dex */
        public static final class C0892c extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Flow<T> $this_debounceInternal;
            private /* synthetic */ Object L$0;
            public int label;

            /* renamed from: kotlinx.coroutines.flow.j$c$c$a */
            /* loaded from: classes12.dex */
            public static final class a<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ ProducerScope<Object> h;

                @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$values$1$1", f = "Delay.kt", i = {}, l = {Primes.SMALL_FACTOR_LIMIT}, m = "emit", n = {}, s = {})
                /* renamed from: kotlinx.coroutines.flow.j$c$c$a$a */
                /* loaded from: classes12.dex */
                public static final class C0893a extends ContinuationImpl {
                    public int label;
                    public /* synthetic */ Object result;
                    public final /* synthetic */ a<T> this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public C0893a(a<? super T> aVar, Continuation<? super C0893a> continuation) {
                        super(continuation);
                        this.this$0 = aVar;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return this.this$0.emit(null, this);
                    }
                }

                public a(ProducerScope<Object> producerScope) {
                    this.h = producerScope;
                }

                /* JADX WARN: Removed duplicated region for block: B:32:0x0023  */
                /* JADX WARN: Removed duplicated region for block: B:36:0x0031  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(T r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof kotlinx.coroutines.flow.j.c.C0892c.a.C0893a
                        if (r0 == 0) goto L13
                        r0 = r6
                        kotlinx.coroutines.flow.j$c$c$a$a r0 = (kotlinx.coroutines.flow.j.c.C0892c.a.C0893a) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        kotlinx.coroutines.flow.j$c$c$a$a r0 = new kotlinx.coroutines.flow.j$c$c$a$a
                        r0.<init>(r4, r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L43
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlinx.coroutines.channels.ProducerScope<java.lang.Object> r6 = r4.h
                        if (r5 != 0) goto L3a
                        kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
                    L3a:
                        r0.label = r3
                        java.lang.Object r5 = r6.send(r5, r0)
                        if (r5 != r1) goto L43
                        return r1
                    L43:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.j.c.C0892c.a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C0892c(Flow<? extends T> flow, Continuation<? super C0892c> continuation) {
                super(2, continuation);
                this.$this_debounceInternal = flow;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                C0892c c0892c = new C0892c(this.$this_debounceInternal, continuation);
                c0892c.L$0 = obj;
                return c0892c;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
                return invoke2((ProducerScope<Object>) producerScope, continuation);
            }

            @Nullable
            /* renamed from: invoke */
            public final Object invoke2(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0892c) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<T> flow = this.$this_debounceInternal;
                    a aVar = new a((ProducerScope) this.L$0);
                    this.label = 1;
                    if (flow.collect(aVar, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public c(Function1<? super T, Long> function1, Flow<? extends T> flow, Continuation<? super c> continuation) {
            super(3, continuation);
            this.$timeoutMillisSelector = function1;
            this.$this_debounceInternal = flow;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
            return invoke(coroutineScope, (FlowCollector) ((FlowCollector) obj), continuation);
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super T> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            c cVar = new c(this.$timeoutMillisSelector, this.$this_debounceInternal, continuation);
            cVar.L$0 = coroutineScope;
            cVar.L$1 = flowCollector;
            return cVar.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(13:61|26|30|31|(3:33|(1:41)(1:37)|(2:39|40))|42|43|44|(1:46)|47|48|(1:50)|(1:52)(1:53)) */
        /* JADX WARN: Code restructure failed: missing block: B:120:0x0117, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:121:0x0118, code lost:
            r13.handleBuilderException(r0);
         */
        /* JADX WARN: Removed duplicated region for block: B:103:0x00d6  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x0100 A[Catch: all -> 0x0117, TryCatch #0 {all -> 0x0117, blocks: (B:115:0x00fc, B:117:0x0100, B:118:0x010a), top: B:130:0x00fc }] */
        /* JADX WARN: Removed duplicated region for block: B:124:0x0125  */
        /* JADX WARN: Removed duplicated region for block: B:126:0x012a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:127:0x012b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:127:0x012b -> B:77:0x0072). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
            /*
                Method dump skipped, instructions count: 308
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.j.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3", f = "Delay.kt", i = {0, 1, 2}, l = {314, 316, 317}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$0"})
    /* loaded from: classes12.dex */
    public static final class d extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ long $delayMillis;
        public final /* synthetic */ long $initialDelayMillis;
        private /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(long j, long j2, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$initialDelayMillis = j;
            this.$delayMillis = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            d dVar = new d(this.$initialDelayMillis, this.$delayMillis, continuation);
            dVar.L$0 = obj;
            return dVar;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super Unit> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0050 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x005d A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x005b -> B:36:0x0040). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L11
                if (r1 == r3) goto L21
                if (r1 != r2) goto L19
            L11:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3f
            L19:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L21:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                r8 = r7
                goto L51
            L2a:
                kotlin.ResultKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                r1 = r8
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                long r5 = r7.$initialDelayMillis
                r7.L$0 = r1
                r7.label = r4
                java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r7)
                if (r8 != r0) goto L3f
                return r0
            L3f:
                r8 = r7
            L40:
                kotlinx.coroutines.channels.SendChannel r4 = r1.getChannel()
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                r8.L$0 = r1
                r8.label = r3
                java.lang.Object r4 = r4.send(r5, r8)
                if (r4 != r0) goto L51
                return r0
            L51:
                long r4 = r8.$delayMillis
                r8.L$0 = r1
                r8.label = r2
                java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r8)
                if (r4 != r0) goto L40
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.j.d.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", f = "Delay.kt", i = {0, 0, 0, 0}, l = {com.veryfit.multi.nativeprotocol.b.s1}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "ticker"}, s = {"L$0", "L$1", "L$2", "L$3"})
    /* loaded from: classes12.dex */
    public static final class e<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ long $periodMillis;
        public final /* synthetic */ Flow<T> $this_sample;
        private /* synthetic */ Object L$0;
        public /* synthetic */ Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;

        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$1", f = "Delay.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes12.dex */
        public static final class a extends SuspendLambda implements Function2<ChannelResult<? extends Object>, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
            public final /* synthetic */ ReceiveChannel<Unit> $ticker;
            public /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Ref.ObjectRef<Object> objectRef, ReceiveChannel<Unit> receiveChannel, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$lastValue = objectRef;
                this.$ticker = receiveChannel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.$lastValue, this.$ticker, continuation);
                aVar.L$0 = obj;
                return aVar;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(ChannelResult<? extends Object> channelResult, Continuation<? super Unit> continuation) {
                return m763invokeWpGqRn0(channelResult.m752unboximpl(), continuation);
            }

            @Nullable
            /* renamed from: invoke-WpGqRn0 */
            public final Object m763invokeWpGqRn0(@NotNull Object obj, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(ChannelResult.m740boximpl(obj), continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    T t = (T) ((ChannelResult) this.L$0).m752unboximpl();
                    Ref.ObjectRef<Object> objectRef = this.$lastValue;
                    boolean z = t instanceof ChannelResult.Failed;
                    if (!z) {
                        objectRef.element = t;
                    }
                    ReceiveChannel<Unit> receiveChannel = this.$ticker;
                    if (z) {
                        Throwable m744exceptionOrNullimpl = ChannelResult.m744exceptionOrNullimpl(t);
                        if (m744exceptionOrNullimpl == null) {
                            receiveChannel.cancel((CancellationException) new ChildCancelledException());
                            objectRef.element = (T) NullSurrogateKt.DONE;
                        } else {
                            throw m744exceptionOrNullimpl;
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$2", f = "Delay.kt", i = {}, l = {300}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes12.dex */
        public static final class b extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
            public final /* synthetic */ FlowCollector<T> $downstream;
            public final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public b(Ref.ObjectRef<Object> objectRef, FlowCollector<? super T> flowCollector, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$lastValue = objectRef;
                this.$downstream = flowCollector;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.$lastValue, this.$downstream, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull Unit unit, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef<Object> objectRef = this.$lastValue;
                    Object obj2 = objectRef.element;
                    if (obj2 == null) {
                        return Unit.INSTANCE;
                    }
                    objectRef.element = null;
                    FlowCollector<T> flowCollector = this.$downstream;
                    if (obj2 == NullSurrogateKt.NULL) {
                        obj2 = null;
                    }
                    this.label = 1;
                    if (flowCollector.emit(obj2, this) == coroutine_suspended) {
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

        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1", f = "Delay.kt", i = {}, l = {DfuException.ERROR_ENTER_OTA_MODE_FAILED}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes12.dex */
        public static final class c extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Flow<T> $this_sample;
            private /* synthetic */ Object L$0;
            public int label;

            /* loaded from: classes12.dex */
            public static final class a<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ ProducerScope<Object> h;

                @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1$1", f = "Delay.kt", i = {}, l = {DfuException.ERROR_ENTER_OTA_MODE_FAILED}, m = "emit", n = {}, s = {})
                /* renamed from: kotlinx.coroutines.flow.j$e$c$a$a */
                /* loaded from: classes12.dex */
                public static final class C0894a extends ContinuationImpl {
                    public int label;
                    public /* synthetic */ Object result;
                    public final /* synthetic */ a<T> this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public C0894a(a<? super T> aVar, Continuation<? super C0894a> continuation) {
                        super(continuation);
                        this.this$0 = aVar;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return this.this$0.emit(null, this);
                    }
                }

                public a(ProducerScope<Object> producerScope) {
                    this.h = producerScope;
                }

                /* JADX WARN: Removed duplicated region for block: B:32:0x0023  */
                /* JADX WARN: Removed duplicated region for block: B:36:0x0031  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(T r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof kotlinx.coroutines.flow.j.e.c.a.C0894a
                        if (r0 == 0) goto L13
                        r0 = r6
                        kotlinx.coroutines.flow.j$e$c$a$a r0 = (kotlinx.coroutines.flow.j.e.c.a.C0894a) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        kotlinx.coroutines.flow.j$e$c$a$a r0 = new kotlinx.coroutines.flow.j$e$c$a$a
                        r0.<init>(r4, r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L43
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlinx.coroutines.channels.ProducerScope<java.lang.Object> r6 = r4.h
                        if (r5 != 0) goto L3a
                        kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
                    L3a:
                        r0.label = r3
                        java.lang.Object r5 = r6.send(r5, r0)
                        if (r5 != r1) goto L43
                        return r1
                    L43:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.j.e.c.a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public c(Flow<? extends T> flow, Continuation<? super c> continuation) {
                super(2, continuation);
                this.$this_sample = flow;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                c cVar = new c(this.$this_sample, continuation);
                cVar.L$0 = obj;
                return cVar;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
                return invoke2((ProducerScope<Object>) producerScope, continuation);
            }

            @Nullable
            /* renamed from: invoke */
            public final Object invoke2(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
                return ((c) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<T> flow = this.$this_sample;
                    a aVar = new a((ProducerScope) this.L$0);
                    this.label = 1;
                    if (flow.collect(aVar, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public e(long j, Flow<? extends T> flow, Continuation<? super e> continuation) {
            super(3, continuation);
            this.$periodMillis = j;
            this.$this_sample = flow;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
            return invoke(coroutineScope, (FlowCollector) ((FlowCollector) obj), continuation);
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super T> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            e eVar = new e(this.$periodMillis, this.$this_sample, continuation);
            eVar.L$0 = coroutineScope;
            eVar.L$1 = flowCollector;
            return eVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ReceiveChannel g;
            FlowCollector flowCollector;
            ReceiveChannel receiveChannel;
            Ref.ObjectRef objectRef;
            ReceiveChannel receiveChannel2;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                ReceiveChannel produce$default = ProduceKt.produce$default(coroutineScope, null, -1, new c(this.$this_sample, null), 1, null);
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                g = j.g(coroutineScope, this.$periodMillis, 0L, 2, null);
                flowCollector = (FlowCollector) this.L$1;
                receiveChannel = produce$default;
                objectRef = objectRef2;
                receiveChannel2 = g;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                receiveChannel2 = (ReceiveChannel) this.L$3;
                objectRef = (Ref.ObjectRef) this.L$2;
                receiveChannel = (ReceiveChannel) this.L$1;
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (objectRef.element != NullSurrogateKt.DONE) {
                this.L$0 = flowCollector;
                this.L$1 = receiveChannel;
                this.L$2 = objectRef;
                this.L$3 = receiveChannel2;
                this.label = 1;
                SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(this);
                try {
                    selectBuilderImpl.invoke(receiveChannel.getOnReceiveCatching(), new a(objectRef, receiveChannel2, null));
                    selectBuilderImpl.invoke(receiveChannel2.getOnReceive(), new b(objectRef, flowCollector, null));
                } catch (Throwable th) {
                    selectBuilderImpl.handleBuilderException(th);
                }
                Object result = selectBuilderImpl.getResult();
                if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(this);
                    continue;
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @FlowPreview
    @NotNull
    public static final <T> Flow<T> a(@NotNull Flow<? extends T> flow, long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            return i == 0 ? flow : e(flow, new a(j));
        }
        throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
    }

    @FlowPreview
    @OverloadResolutionByLambdaReturnType
    @NotNull
    public static final <T> Flow<T> b(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Long> function1) {
        return e(flow, function1);
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> c(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt.debounce(flow, DelayKt.m726toDelayMillisLRDsOJo(j));
    }

    @FlowPreview
    @JvmName(name = "debounceDuration")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T> Flow<T> d(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Duration> function1) {
        return e(flow, new b(function1));
    }

    public static final <T> Flow<T> e(Flow<? extends T> flow, Function1<? super T, Long> function1) {
        return FlowCoroutineKt.scopedFlow(new c(function1, flow, null));
    }

    @NotNull
    public static final ReceiveChannel<Unit> f(@NotNull CoroutineScope coroutineScope, long j, long j2) {
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j + " ms").toString());
        }
        if (j2 >= 0) {
            return ProduceKt.produce$default(coroutineScope, null, 0, new d(j2, j, null), 1, null);
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j2 + " ms").toString());
    }

    public static /* synthetic */ ReceiveChannel g(CoroutineScope coroutineScope, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        return FlowKt.fixedPeriodTicker(coroutineScope, j, j2);
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> h(@NotNull Flow<? extends T> flow, long j) {
        if (j > 0) {
            return FlowCoroutineKt.scopedFlow(new e(j, flow, null));
        }
        throw new IllegalArgumentException("Sample period should be positive".toString());
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> i(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt.sample(flow, DelayKt.m726toDelayMillisLRDsOJo(j));
    }
}
