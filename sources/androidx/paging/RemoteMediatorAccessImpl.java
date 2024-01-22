package androidx.paging;

import androidx.paging.AccessorState;
import androidx.paging.LoadState;
import androidx.paging.RemoteMediator;
import com.realsil.sdk.dfu.DfuException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RemoteMediatorAccessImpl<Key, Value> implements RemoteMediatorAccessor<Key, Value> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CoroutineScope f1548a;
    @NotNull
    public final RemoteMediator<Key, Value> b;
    @NotNull
    public final androidx.paging.a<Key, Value> c;
    @NotNull
    public final SingleRunner d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004¨\u0006\b"}, d2 = {"Landroidx/paging/RemoteMediatorAccessImpl$Companion;", "", "", "PRIORITY_APPEND_PREPEND", "I", "PRIORITY_REFRESH", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl", f = "RemoteMediatorAccessor.kt", i = {0}, l = {397}, m = "initialize", n = {"this"}, s = {"L$0"})
    /* loaded from: classes.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super a> continuation) {
            super(continuation);
            this.this$0 = remoteMediatorAccessImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.initialize(this);
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends Lambda implements Function1<AccessorState<Key, Value>, Unit> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
            invoke((AccessorState) ((AccessorState) obj));
            return Unit.INSTANCE;
        }

        public final void invoke(@NotNull AccessorState<Key, Value> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            LoadType loadType = LoadType.APPEND;
            AccessorState.BlockState blockState = AccessorState.BlockState.REQUIRES_REFRESH;
            it.i(loadType, blockState);
            it.i(LoadType.PREPEND, blockState);
        }
    }

    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1", f = "RemoteMediatorAccessor.kt", i = {}, l = {338}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

        @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1", f = "RemoteMediatorAccessor.kt", i = {0}, l = {345}, m = "invokeSuspend", n = {"loadType"}, s = {"L$0"})
        /* loaded from: classes.dex */
        public static final class a extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            public Object L$0;
            public int label;
            public final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

            /* renamed from: androidx.paging.RemoteMediatorAccessImpl$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C0164a extends Lambda implements Function1<AccessorState<Key, Value>, Pair<? extends LoadType, ? extends PagingState<Key, Value>>> {
                public static final C0164a INSTANCE = new C0164a();

                public C0164a() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke((AccessorState) ((AccessorState) obj));
                }

                @Nullable
                public final Pair<LoadType, PagingState<Key, Value>> invoke(@NotNull AccessorState<Key, Value> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.g();
                }
            }

            /* loaded from: classes.dex */
            public static final class b extends Lambda implements Function1<AccessorState<Key, Value>, Unit> {
                public final /* synthetic */ RemoteMediator.MediatorResult $loadResult;
                public final /* synthetic */ LoadType $loadType;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(LoadType loadType, RemoteMediator.MediatorResult mediatorResult) {
                    super(1);
                    this.$loadType = loadType;
                    this.$loadResult = mediatorResult;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke((AccessorState) ((AccessorState) obj));
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull AccessorState<Key, Value> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.c(this.$loadType);
                    if (((RemoteMediator.MediatorResult.Success) this.$loadResult).endOfPaginationReached()) {
                        it.i(this.$loadType, AccessorState.BlockState.COMPLETED);
                    }
                }
            }

            /* renamed from: androidx.paging.RemoteMediatorAccessImpl$c$a$c  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C0165c extends Lambda implements Function1<AccessorState<Key, Value>, Unit> {
                public final /* synthetic */ RemoteMediator.MediatorResult $loadResult;
                public final /* synthetic */ LoadType $loadType;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0165c(LoadType loadType, RemoteMediator.MediatorResult mediatorResult) {
                    super(1);
                    this.$loadType = loadType;
                    this.$loadResult = mediatorResult;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke((AccessorState) ((AccessorState) obj));
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull AccessorState<Key, Value> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.c(this.$loadType);
                    it.j(this.$loadType, new LoadState.Error(((RemoteMediator.MediatorResult.Error) this.$loadResult).getThrowable()));
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super a> continuation) {
                super(1, continuation);
                this.this$0 = remoteMediatorAccessImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Object invoke(@Nullable Continuation<? super Unit> continuation) {
                return ((a) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
            /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
            /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0052 -> B:17:0x0056). Please submit an issue!!! */
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
                    r2 = 1
                    if (r1 == 0) goto L1e
                    if (r1 != r2) goto L16
                    java.lang.Object r1 = r7.L$0
                    androidx.paging.LoadType r1 = (androidx.paging.LoadType) r1
                    kotlin.ResultKt.throwOnFailure(r8)
                    r3 = r1
                    r1 = r0
                    r0 = r7
                    goto L56
                L16:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L1e:
                    kotlin.ResultKt.throwOnFailure(r8)
                    r8 = r7
                L22:
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r1 = r8.this$0
                    androidx.paging.a r1 = androidx.paging.RemoteMediatorAccessImpl.a(r1)
                    androidx.paging.RemoteMediatorAccessImpl$c$a$a r3 = androidx.paging.RemoteMediatorAccessImpl.c.a.C0164a.INSTANCE
                    java.lang.Object r1 = r1.b(r3)
                    kotlin.Pair r1 = (kotlin.Pair) r1
                    if (r1 != 0) goto L35
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                L35:
                    java.lang.Object r3 = r1.component1()
                    androidx.paging.LoadType r3 = (androidx.paging.LoadType) r3
                    java.lang.Object r1 = r1.component2()
                    androidx.paging.PagingState r1 = (androidx.paging.PagingState) r1
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r4 = r8.this$0
                    androidx.paging.RemoteMediator r4 = androidx.paging.RemoteMediatorAccessImpl.c(r4)
                    r8.L$0 = r3
                    r8.label = r2
                    java.lang.Object r1 = r4.load(r3, r1, r8)
                    if (r1 != r0) goto L52
                    return r0
                L52:
                    r6 = r0
                    r0 = r8
                    r8 = r1
                    r1 = r6
                L56:
                    androidx.paging.RemoteMediator$MediatorResult r8 = (androidx.paging.RemoteMediator.MediatorResult) r8
                    boolean r4 = r8 instanceof androidx.paging.RemoteMediator.MediatorResult.Success
                    if (r4 == 0) goto L6b
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r4 = r0.this$0
                    androidx.paging.a r4 = androidx.paging.RemoteMediatorAccessImpl.a(r4)
                    androidx.paging.RemoteMediatorAccessImpl$c$a$b r5 = new androidx.paging.RemoteMediatorAccessImpl$c$a$b
                    r5.<init>(r3, r8)
                    r4.b(r5)
                    goto L7d
                L6b:
                    boolean r4 = r8 instanceof androidx.paging.RemoteMediator.MediatorResult.Error
                    if (r4 == 0) goto L7d
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r4 = r0.this$0
                    androidx.paging.a r4 = androidx.paging.RemoteMediatorAccessImpl.a(r4)
                    androidx.paging.RemoteMediatorAccessImpl$c$a$c r5 = new androidx.paging.RemoteMediatorAccessImpl$c$a$c
                    r5.<init>(r3, r8)
                    r4.b(r5)
                L7d:
                    r8 = r0
                    r0 = r1
                    goto L22
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.c.a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super c> continuation) {
            super(2, continuation);
            this.this$0 = remoteMediatorAccessImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.this$0, continuation);
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
                SingleRunner singleRunner = this.this$0.d;
                a aVar = new a(this.this$0, null);
                this.label = 1;
                if (singleRunner.runInIsolation(1, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1", f = "RemoteMediatorAccessor.kt", i = {0}, l = {DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR}, m = "invokeSuspend", n = {"launchAppendPrepend"}, s = {"L$0"})
    /* loaded from: classes.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public int label;
        public final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

        @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1", f = "RemoteMediatorAccessor.kt", i = {}, l = {273}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes.dex */
        public static final class a extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.BooleanRef $launchAppendPrepend;
            public Object L$0;
            public Object L$1;
            public int label;
            public final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

            /* renamed from: androidx.paging.RemoteMediatorAccessImpl$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C0166a extends Lambda implements Function1<AccessorState<Key, Value>, Boolean> {
                public final /* synthetic */ RemoteMediator.MediatorResult $loadResult;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0166a(RemoteMediator.MediatorResult mediatorResult) {
                    super(1);
                    this.$loadResult = mediatorResult;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                    return invoke((AccessorState) ((AccessorState) obj));
                }

                @NotNull
                public final Boolean invoke(@NotNull AccessorState<Key, Value> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    LoadType loadType = LoadType.REFRESH;
                    it.c(loadType);
                    if (((RemoteMediator.MediatorResult.Success) this.$loadResult).endOfPaginationReached()) {
                        AccessorState.BlockState blockState = AccessorState.BlockState.COMPLETED;
                        it.i(loadType, blockState);
                        it.i(LoadType.PREPEND, blockState);
                        it.i(LoadType.APPEND, blockState);
                        it.d();
                    } else {
                        LoadType loadType2 = LoadType.PREPEND;
                        AccessorState.BlockState blockState2 = AccessorState.BlockState.UNBLOCKED;
                        it.i(loadType2, blockState2);
                        it.i(LoadType.APPEND, blockState2);
                    }
                    it.j(LoadType.PREPEND, null);
                    it.j(LoadType.APPEND, null);
                    return Boolean.valueOf(it.g() != null);
                }
            }

            /* loaded from: classes.dex */
            public static final class b extends Lambda implements Function1<AccessorState<Key, Value>, Boolean> {
                public final /* synthetic */ RemoteMediator.MediatorResult $loadResult;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(RemoteMediator.MediatorResult mediatorResult) {
                    super(1);
                    this.$loadResult = mediatorResult;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                    return invoke((AccessorState) ((AccessorState) obj));
                }

                @NotNull
                public final Boolean invoke(@NotNull AccessorState<Key, Value> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    LoadType loadType = LoadType.REFRESH;
                    it.c(loadType);
                    it.j(loadType, new LoadState.Error(((RemoteMediator.MediatorResult.Error) this.$loadResult).getThrowable()));
                    return Boolean.valueOf(it.g() != null);
                }
            }

            /* loaded from: classes.dex */
            public static final class c extends Lambda implements Function1<AccessorState<Key, Value>, PagingState<Key, Value>> {
                public static final c INSTANCE = new c();

                public c() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke((AccessorState) ((AccessorState) obj));
                }

                @Nullable
                public final PagingState<Key, Value> invoke(@NotNull AccessorState<Key, Value> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.h();
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Ref.BooleanRef booleanRef, Continuation<? super a> continuation) {
                super(1, continuation);
                this.this$0 = remoteMediatorAccessImpl;
                this.$launchAppendPrepend = booleanRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$launchAppendPrepend, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Object invoke(@Nullable Continuation<? super Unit> continuation) {
                return ((a) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl;
                Ref.BooleanRef booleanRef;
                boolean booleanValue;
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PagingState<Key, Value> pagingState = (PagingState) this.this$0.c.b(c.INSTANCE);
                    if (pagingState != null) {
                        remoteMediatorAccessImpl = this.this$0;
                        Ref.BooleanRef booleanRef2 = this.$launchAppendPrepend;
                        RemoteMediator remoteMediator = remoteMediatorAccessImpl.b;
                        LoadType loadType = LoadType.REFRESH;
                        this.L$0 = remoteMediatorAccessImpl;
                        this.L$1 = booleanRef2;
                        this.label = 1;
                        obj = remoteMediator.load(loadType, pagingState, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanRef = booleanRef2;
                    }
                    return Unit.INSTANCE;
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    booleanRef = (Ref.BooleanRef) this.L$1;
                    remoteMediatorAccessImpl = (RemoteMediatorAccessImpl) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                RemoteMediator.MediatorResult mediatorResult = (RemoteMediator.MediatorResult) obj;
                if (mediatorResult instanceof RemoteMediator.MediatorResult.Success) {
                    booleanValue = ((Boolean) remoteMediatorAccessImpl.c.b(new C0166a(mediatorResult))).booleanValue();
                } else if (mediatorResult instanceof RemoteMediator.MediatorResult.Error) {
                    booleanValue = ((Boolean) remoteMediatorAccessImpl.c.b(new b(mediatorResult))).booleanValue();
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                booleanRef.element = booleanValue;
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super d> continuation) {
            super(2, continuation);
            this.this$0 = remoteMediatorAccessImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Ref.BooleanRef booleanRef;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                SingleRunner singleRunner = this.this$0.d;
                a aVar = new a(this.this$0, booleanRef2, null);
                this.L$0 = booleanRef2;
                this.label = 1;
                if (singleRunner.runInIsolation(2, aVar, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef = booleanRef2;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                booleanRef = (Ref.BooleanRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            if (booleanRef.element) {
                this.this$0.e();
            }
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class e extends Lambda implements Function1<AccessorState<Key, Value>, Boolean> {
        public final /* synthetic */ LoadType $loadType;
        public final /* synthetic */ PagingState<Key, Value> $pagingState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(LoadType loadType, PagingState<Key, Value> pagingState) {
            super(1);
            this.$loadType = loadType;
            this.$pagingState = pagingState;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
            return invoke((AccessorState) ((AccessorState) obj));
        }

        @NotNull
        public final Boolean invoke(@NotNull AccessorState<Key, Value> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Boolean.valueOf(it.a(this.$loadType, this.$pagingState));
        }
    }

    /* loaded from: classes.dex */
    public static final class f extends Lambda implements Function1<AccessorState<Key, Value>, Unit> {
        public final /* synthetic */ List<LoadType> $toBeStarted;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(List<LoadType> list) {
            super(1);
            this.$toBeStarted = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
            invoke((AccessorState) ((AccessorState) obj));
            return Unit.INSTANCE;
        }

        public final void invoke(@NotNull AccessorState<Key, Value> accessorState) {
            Intrinsics.checkNotNullParameter(accessorState, "accessorState");
            LoadStates e = accessorState.e();
            boolean z = e.getRefresh() instanceof LoadState.Error;
            accessorState.b();
            if (z) {
                List<LoadType> list = this.$toBeStarted;
                LoadType loadType = LoadType.REFRESH;
                list.add(loadType);
                accessorState.i(loadType, AccessorState.BlockState.UNBLOCKED);
            }
            if (e.getAppend() instanceof LoadState.Error) {
                if (!z) {
                    this.$toBeStarted.add(LoadType.APPEND);
                }
                accessorState.c(LoadType.APPEND);
            }
            if (e.getPrepend() instanceof LoadState.Error) {
                if (!z) {
                    this.$toBeStarted.add(LoadType.PREPEND);
                }
                accessorState.c(LoadType.PREPEND);
            }
        }
    }

    static {
        new Companion(null);
    }

    public RemoteMediatorAccessImpl(@NotNull CoroutineScope scope, @NotNull RemoteMediator<Key, Value> remoteMediator) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(remoteMediator, "remoteMediator");
        this.f1548a = scope;
        this.b = remoteMediator;
        this.c = new androidx.paging.a<>();
        this.d = new SingleRunner(false);
    }

    public final void e() {
        kotlinx.coroutines.e.e(this.f1548a, null, null, new c(this, null), 3, null);
    }

    public final void f() {
        kotlinx.coroutines.e.e(this.f1548a, null, null, new d(this, null), 3, null);
    }

    @Override // androidx.paging.RemoteMediatorAccessor
    @NotNull
    public StateFlow<LoadStates> getState() {
        return this.c.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    @Override // androidx.paging.RemoteMediatorAccessor
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object initialize(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.RemoteMediator.InitializeAction> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.paging.RemoteMediatorAccessImpl.a
            if (r0 == 0) goto L13
            r0 = r5
            androidx.paging.RemoteMediatorAccessImpl$a r0 = (androidx.paging.RemoteMediatorAccessImpl.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.RemoteMediatorAccessImpl$a r0 = new androidx.paging.RemoteMediatorAccessImpl$a
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            androidx.paging.RemoteMediatorAccessImpl r0 = (androidx.paging.RemoteMediatorAccessImpl) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            androidx.paging.RemoteMediator<Key, Value> r5 = r4.b
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.initialize(r0)
            if (r5 != r1) goto L45
            return r1
        L45:
            r0 = r4
        L46:
            r1 = r5
            androidx.paging.RemoteMediator$InitializeAction r1 = (androidx.paging.RemoteMediator.InitializeAction) r1
            androidx.paging.RemoteMediator$InitializeAction r2 = androidx.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
            if (r1 != r2) goto L54
            androidx.paging.a<Key, Value> r0 = r0.c
            androidx.paging.RemoteMediatorAccessImpl$b r1 = androidx.paging.RemoteMediatorAccessImpl.b.INSTANCE
            r0.b(r1)
        L54:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.initialize(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.paging.RemoteMediatorConnection
    public void requestLoad(@NotNull LoadType loadType, @NotNull PagingState<Key, Value> pagingState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(pagingState, "pagingState");
        if (((Boolean) this.c.b(new e(loadType, pagingState))).booleanValue()) {
            if (WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()] == 1) {
                f();
            } else {
                e();
            }
        }
    }

    @Override // androidx.paging.RemoteMediatorConnection
    public void retryFailed(@NotNull PagingState<Key, Value> pagingState) {
        Intrinsics.checkNotNullParameter(pagingState, "pagingState");
        ArrayList<LoadType> arrayList = new ArrayList();
        this.c.b(new f(arrayList));
        for (LoadType loadType : arrayList) {
            requestLoad(loadType, pagingState);
        }
    }
}
