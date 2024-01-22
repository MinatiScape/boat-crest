package androidx.paging;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0003\u000f\u000e\u0010B\u0011\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ;\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u001c\u0010\u0007\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Landroidx/paging/SingleRunner;", "", "", "priority", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "block", "runInIsolation", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "cancelPreviousInEqualPriority", "<init>", "(Z)V", "Companion", "a", "b", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class SingleRunner {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_PRIORITY = 0;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final b f1550a;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Landroidx/paging/SingleRunner$Companion;", "", "", "DEFAULT_PRIORITY", "I", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends CancellationException {
        @NotNull
        private final SingleRunner runner;

        public a(@NotNull SingleRunner runner) {
            Intrinsics.checkNotNullParameter(runner, "runner");
            this.runner = runner;
        }

        @NotNull
        public final SingleRunner getRunner() {
            return this.runner;
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final SingleRunner f1551a;
        public final boolean b;
        @NotNull
        public final Mutex c;
        @Nullable
        public Job d;
        public int e;

        @DebugMetadata(c = "androidx.paging.SingleRunner$Holder", f = "SingleRunner.kt", i = {0, 0, 0}, l = {129}, m = "onFinish", n = {"this", "job", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
        /* loaded from: classes.dex */
        public static final class a extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public int label;
            public /* synthetic */ Object result;

            public a(Continuation<? super a> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return b.this.a(null, this);
            }
        }

        @DebugMetadata(c = "androidx.paging.SingleRunner$Holder", f = "SingleRunner.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {129, 100}, m = "tryEnqueue", n = {"this", "job", "$this$withLock_u24default$iv", "priority", "this", "job", "$this$withLock_u24default$iv", "priority"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"})
        /* renamed from: androidx.paging.SingleRunner$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0170b extends ContinuationImpl {
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public int label;
            public /* synthetic */ Object result;

            public C0170b(Continuation<? super C0170b> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return b.this.b(0, null, this);
            }
        }

        public b(@NotNull SingleRunner singleRunner, boolean z) {
            Intrinsics.checkNotNullParameter(singleRunner, "singleRunner");
            this.f1551a = singleRunner;
            this.b = z;
            this.c = MutexKt.Mutex$default(false, 1, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0059 A[Catch: all -> 0x0061, TryCatch #0 {all -> 0x0061, blocks: (B:18:0x0055, B:20:0x0059, B:21:0x005b), top: B:27:0x0055 }] */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object a(@org.jetbrains.annotations.NotNull kotlinx.coroutines.Job r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.SingleRunner.b.a
                if (r0 == 0) goto L13
                r0 = r7
                androidx.paging.SingleRunner$b$a r0 = (androidx.paging.SingleRunner.b.a) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.paging.SingleRunner$b$a r0 = new androidx.paging.SingleRunner$b$a
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                r4 = 0
                if (r2 == 0) goto L40
                if (r2 != r3) goto L38
                java.lang.Object r6 = r0.L$2
                kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
                java.lang.Object r1 = r0.L$1
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.SingleRunner$b r0 = (androidx.paging.SingleRunner.b) r0
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                r6 = r1
                goto L55
            L38:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L40:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.sync.Mutex r7 = r5.c
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.label = r3
                java.lang.Object r0 = r7.lock(r4, r0)
                if (r0 != r1) goto L54
                return r1
            L54:
                r0 = r5
            L55:
                kotlinx.coroutines.Job r1 = r0.d     // Catch: java.lang.Throwable -> L61
                if (r6 != r1) goto L5b
                r0.d = r4     // Catch: java.lang.Throwable -> L61
            L5b:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L61
                r7.unlock(r4)
                return r6
            L61:
                r6 = move-exception
                r7.unlock(r4)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.b.a(kotlinx.coroutines.Job, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0098  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0099 A[Catch: all -> 0x003c, TryCatch #0 {all -> 0x003c, blocks: (B:13:0x0037, B:45:0x00ae, B:46:0x00b2, B:24:0x0072, B:26:0x0076, B:28:0x007c, B:31:0x0082, B:40:0x0099, B:37:0x008c), top: B:51:0x0023 }] */
        /* JADX WARN: Type inference failed for: r11v0, types: [kotlinx.coroutines.Job, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r11v1, types: [kotlinx.coroutines.sync.Mutex] */
        /* JADX WARN: Type inference failed for: r11v16 */
        /* JADX WARN: Type inference failed for: r11v17 */
        /* JADX WARN: Type inference failed for: r11v4, types: [kotlinx.coroutines.sync.Mutex] */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object b(int r10, @org.jetbrains.annotations.NotNull kotlinx.coroutines.Job r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
            /*
                r9 = this;
                boolean r0 = r12 instanceof androidx.paging.SingleRunner.b.C0170b
                if (r0 == 0) goto L13
                r0 = r12
                androidx.paging.SingleRunner$b$b r0 = (androidx.paging.SingleRunner.b.C0170b) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.paging.SingleRunner$b$b r0 = new androidx.paging.SingleRunner$b$b
                r0.<init>(r12)
            L18:
                java.lang.Object r12 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 0
                r5 = 1
                if (r2 == 0) goto L59
                if (r2 == r5) goto L47
                if (r2 != r3) goto L3f
                int r10 = r0.I$0
                java.lang.Object r11 = r0.L$2
                kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
                java.lang.Object r1 = r0.L$1
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.SingleRunner$b r0 = (androidx.paging.SingleRunner.b) r0
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L3c
                goto Lac
            L3c:
                r10 = move-exception
                goto Lba
            L3f:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r11)
                throw r10
            L47:
                int r10 = r0.I$0
                java.lang.Object r11 = r0.L$2
                kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
                java.lang.Object r2 = r0.L$1
                kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
                java.lang.Object r6 = r0.L$0
                androidx.paging.SingleRunner$b r6 = (androidx.paging.SingleRunner.b) r6
                kotlin.ResultKt.throwOnFailure(r12)
                goto L72
            L59:
                kotlin.ResultKt.throwOnFailure(r12)
                kotlinx.coroutines.sync.Mutex r12 = r9.c
                r0.L$0 = r9
                r0.L$1 = r11
                r0.L$2 = r12
                r0.I$0 = r10
                r0.label = r5
                java.lang.Object r2 = r12.lock(r4, r0)
                if (r2 != r1) goto L6f
                return r1
            L6f:
                r6 = r9
                r2 = r11
                r11 = r12
            L72:
                kotlinx.coroutines.Job r12 = r6.d     // Catch: java.lang.Throwable -> L3c
                if (r12 == 0) goto L89
                boolean r7 = r12.isActive()     // Catch: java.lang.Throwable -> L3c
                if (r7 == 0) goto L89
                int r7 = r6.e     // Catch: java.lang.Throwable -> L3c
                if (r7 < r10) goto L89
                if (r7 != r10) goto L87
                boolean r7 = r6.b     // Catch: java.lang.Throwable -> L3c
                if (r7 == 0) goto L87
                goto L89
            L87:
                r5 = 0
                goto Lb2
            L89:
                if (r12 != 0) goto L8c
                goto L96
            L8c:
                androidx.paging.SingleRunner$a r7 = new androidx.paging.SingleRunner$a     // Catch: java.lang.Throwable -> L3c
                androidx.paging.SingleRunner r8 = r6.f1551a     // Catch: java.lang.Throwable -> L3c
                r7.<init>(r8)     // Catch: java.lang.Throwable -> L3c
                r12.cancel(r7)     // Catch: java.lang.Throwable -> L3c
            L96:
                if (r12 != 0) goto L99
                goto Lae
            L99:
                r0.L$0 = r6     // Catch: java.lang.Throwable -> L3c
                r0.L$1 = r2     // Catch: java.lang.Throwable -> L3c
                r0.L$2 = r11     // Catch: java.lang.Throwable -> L3c
                r0.I$0 = r10     // Catch: java.lang.Throwable -> L3c
                r0.label = r3     // Catch: java.lang.Throwable -> L3c
                java.lang.Object r12 = r12.join(r0)     // Catch: java.lang.Throwable -> L3c
                if (r12 != r1) goto Laa
                return r1
            Laa:
                r1 = r2
                r0 = r6
            Lac:
                r6 = r0
                r2 = r1
            Lae:
                r6.d = r2     // Catch: java.lang.Throwable -> L3c
                r6.e = r10     // Catch: java.lang.Throwable -> L3c
            Lb2:
                java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)     // Catch: java.lang.Throwable -> L3c
                r11.unlock(r4)
                return r10
            Lba:
                r11.unlock(r4)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.b.b(int, kotlinx.coroutines.Job, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @DebugMetadata(c = "androidx.paging.SingleRunner", f = "SingleRunner.kt", i = {0}, l = {49}, m = "runInIsolation", n = {"this"}, s = {"L$0"})
    /* loaded from: classes.dex */
    public static final class c extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SingleRunner.this.runInIsolation(0, null, this);
        }
    }

    @DebugMetadata(c = "androidx.paging.SingleRunner$runInIsolation$2", f = "SingleRunner.kt", i = {0, 1}, l = {53, 59, 61, 61}, m = "invokeSuspend", n = {"myJob", "myJob"}, s = {"L$0", "L$0"})
    /* loaded from: classes.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function1<Continuation<? super Unit>, Object> $block;
        public final /* synthetic */ int $priority;
        private /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public d(int i, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$priority = i;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            d dVar = new d(this.$priority, this.$block, continuation);
            dVar.L$0 = obj;
            return dVar;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:31:0x008d A[RETURN] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v9, types: [kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r3v2, types: [androidx.paging.SingleRunner$b] */
        /* JADX WARN: Type inference failed for: r9v15, types: [androidx.paging.SingleRunner$b] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L3c
                if (r1 == r5) goto L34
                if (r1 == r4) goto L2a
                if (r1 == r3) goto L25
                if (r1 == r2) goto L1c
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1c:
                java.lang.Object r0 = r8.L$0
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                kotlin.ResultKt.throwOnFailure(r9)
                goto La0
            L25:
                kotlin.ResultKt.throwOnFailure(r9)
                goto La1
            L2a:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L32
                goto L7c
            L32:
                r9 = move-exception
                goto L8e
            L34:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                kotlin.ResultKt.throwOnFailure(r9)
                goto L67
            L3c:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
                kotlin.coroutines.CoroutineContext r9 = r9.getCoroutineContext()
                kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.Key
                kotlin.coroutines.CoroutineContext$Element r9 = r9.get(r1)
                if (r9 == 0) goto La4
                kotlinx.coroutines.Job r9 = (kotlinx.coroutines.Job) r9
                androidx.paging.SingleRunner r1 = androidx.paging.SingleRunner.this
                androidx.paging.SingleRunner$b r1 = androidx.paging.SingleRunner.access$getHolder$p(r1)
                int r6 = r8.$priority
                r8.L$0 = r9
                r8.label = r5
                java.lang.Object r1 = r1.b(r6, r9, r8)
                if (r1 != r0) goto L64
                return r0
            L64:
                r7 = r1
                r1 = r9
                r9 = r7
            L67:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto La1
                kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r9 = r8.$block     // Catch: java.lang.Throwable -> L32
                r8.L$0 = r1     // Catch: java.lang.Throwable -> L32
                r8.label = r4     // Catch: java.lang.Throwable -> L32
                java.lang.Object r9 = r9.invoke(r8)     // Catch: java.lang.Throwable -> L32
                if (r9 != r0) goto L7c
                return r0
            L7c:
                androidx.paging.SingleRunner r9 = androidx.paging.SingleRunner.this
                androidx.paging.SingleRunner$b r9 = androidx.paging.SingleRunner.access$getHolder$p(r9)
                r2 = 0
                r8.L$0 = r2
                r8.label = r3
                java.lang.Object r9 = r9.a(r1, r8)
                if (r9 != r0) goto La1
                return r0
            L8e:
                androidx.paging.SingleRunner r3 = androidx.paging.SingleRunner.this
                androidx.paging.SingleRunner$b r3 = androidx.paging.SingleRunner.access$getHolder$p(r3)
                r8.L$0 = r9
                r8.label = r2
                java.lang.Object r1 = r3.a(r1, r8)
                if (r1 != r0) goto L9f
                return r0
            L9f:
                r0 = r9
            La0:
                throw r0
            La1:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            La4:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "Internal error. coroutineScope should've created a job."
                java.lang.String r0 = r0.toString()
                r9.<init>(r0)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.d.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public SingleRunner() {
        this(false, 1, null);
    }

    public SingleRunner(boolean z) {
        this.f1550a = new b(this, z);
    }

    public static /* synthetic */ Object runInIsolation$default(SingleRunner singleRunner, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return singleRunner.runInIsolation(i, function1, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0056  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runInIsolation(int r5, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.paging.SingleRunner.c
            if (r0 == 0) goto L13
            r0 = r7
            androidx.paging.SingleRunner$c r0 = (androidx.paging.SingleRunner.c) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.SingleRunner$c r0 = new androidx.paging.SingleRunner$c
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r5 = r0.L$0
            androidx.paging.SingleRunner r5 = (androidx.paging.SingleRunner) r5
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: androidx.paging.SingleRunner.a -> L2d
            goto L53
        L2d:
            r6 = move-exception
            goto L4d
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.paging.SingleRunner$d r7 = new androidx.paging.SingleRunner$d     // Catch: androidx.paging.SingleRunner.a -> L4b
            r2 = 0
            r7.<init>(r5, r6, r2)     // Catch: androidx.paging.SingleRunner.a -> L4b
            r0.L$0 = r4     // Catch: androidx.paging.SingleRunner.a -> L4b
            r0.label = r3     // Catch: androidx.paging.SingleRunner.a -> L4b
            java.lang.Object r5 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r7, r0)     // Catch: androidx.paging.SingleRunner.a -> L4b
            if (r5 != r1) goto L53
            return r1
        L4b:
            r6 = move-exception
            r5 = r4
        L4d:
            androidx.paging.SingleRunner r7 = r6.getRunner()
            if (r7 != r5) goto L56
        L53:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L56:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.runInIsolation(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public /* synthetic */ SingleRunner(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }
}
