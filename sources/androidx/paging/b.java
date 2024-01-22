package androidx.paging;

import com.realsil.sdk.dfu.DfuException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class b<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final FlattenedPageEventStorage<T> f1564a = new FlattenedPageEventStorage<>();
    @NotNull
    public final Mutex b = MutexKt.Mutex$default(false, 1, null);
    public int c = -1;

    @DebugMetadata(c = "androidx.paging.FlattenedPageController", f = "CachedPageEventFlow.kt", i = {0, 0}, l = {DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS}, m = "getStateAsEvents", n = {"this", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1"})
    /* loaded from: classes.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ b<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(b<T> bVar, Continuation<? super a> continuation) {
            super(continuation);
            this.this$0 = bVar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.a(this);
        }
    }

    @DebugMetadata(c = "androidx.paging.FlattenedPageController", f = "CachedPageEventFlow.kt", i = {0, 0, 0}, l = {DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS}, m = "record", n = {"this", "event", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: androidx.paging.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0171b extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ b<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0171b(b<T> bVar, Continuation<? super C0171b> continuation) {
            super(continuation);
            this.this$0 = bVar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.b(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0072 A[Catch: all -> 0x008e, TryCatch #0 {all -> 0x008e, blocks: (B:18:0x004e, B:19:0x006c, B:21:0x0072, B:23:0x007a, B:24:0x007d), top: B:31:0x004e }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends kotlin.collections.IndexedValue<? extends androidx.paging.PageEvent<T>>>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.paging.b.a
            if (r0 == 0) goto L13
            r0 = r9
            androidx.paging.b$a r0 = (androidx.paging.b.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.b$a r0 = new androidx.paging.b$a
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 != r4) goto L32
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            androidx.paging.b r0 = (androidx.paging.b) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L4e
        L32:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L3a:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.sync.Mutex r9 = r8.b
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r0 = r9.lock(r3, r0)
            if (r0 != r1) goto L4c
            return r1
        L4c:
            r0 = r8
            r1 = r9
        L4e:
            androidx.paging.FlattenedPageEventStorage<T> r9 = r0.f1564a     // Catch: java.lang.Throwable -> L8e
            java.util.List r9 = r9.getAsEvents()     // Catch: java.lang.Throwable -> L8e
            int r0 = r0.c     // Catch: java.lang.Throwable -> L8e
            int r2 = r9.size()     // Catch: java.lang.Throwable -> L8e
            int r0 = r0 - r2
            int r0 = r0 + r4
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L8e
            r4 = 10
            int r4 = kotlin.collections.f.collectionSizeOrDefault(r9, r4)     // Catch: java.lang.Throwable -> L8e
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L8e
            r4 = 0
            java.util.Iterator r9 = r9.iterator()     // Catch: java.lang.Throwable -> L8e
        L6c:
            boolean r5 = r9.hasNext()     // Catch: java.lang.Throwable -> L8e
            if (r5 == 0) goto L8a
            java.lang.Object r5 = r9.next()     // Catch: java.lang.Throwable -> L8e
            int r6 = r4 + 1
            if (r4 >= 0) goto L7d
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()     // Catch: java.lang.Throwable -> L8e
        L7d:
            androidx.paging.PageEvent r5 = (androidx.paging.PageEvent) r5     // Catch: java.lang.Throwable -> L8e
            kotlin.collections.IndexedValue r7 = new kotlin.collections.IndexedValue     // Catch: java.lang.Throwable -> L8e
            int r4 = r4 + r0
            r7.<init>(r4, r5)     // Catch: java.lang.Throwable -> L8e
            r2.add(r7)     // Catch: java.lang.Throwable -> L8e
            r4 = r6
            goto L6c
        L8a:
            r1.unlock(r3)
            return r2
        L8e:
            r9 = move-exception
            r1.unlock(r3)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.b.a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull kotlin.collections.IndexedValue<? extends androidx.paging.PageEvent<T>> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.paging.b.C0171b
            if (r0 == 0) goto L13
            r0 = r7
            androidx.paging.b$b r0 = (androidx.paging.b.C0171b) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.b$b r0 = new androidx.paging.b$b
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 != r4) goto L38
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r1 = r0.L$1
            kotlin.collections.IndexedValue r1 = (kotlin.collections.IndexedValue) r1
            java.lang.Object r0 = r0.L$0
            androidx.paging.b r0 = (androidx.paging.b) r0
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
            kotlinx.coroutines.sync.Mutex r7 = r5.b
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r4
            java.lang.Object r0 = r7.lock(r3, r0)
            if (r0 != r1) goto L54
            return r1
        L54:
            r0 = r5
        L55:
            int r1 = r6.getIndex()     // Catch: java.lang.Throwable -> L6c
            r0.c = r1     // Catch: java.lang.Throwable -> L6c
            androidx.paging.FlattenedPageEventStorage<T> r0 = r0.f1564a     // Catch: java.lang.Throwable -> L6c
            java.lang.Object r6 = r6.getValue()     // Catch: java.lang.Throwable -> L6c
            androidx.paging.PageEvent r6 = (androidx.paging.PageEvent) r6     // Catch: java.lang.Throwable -> L6c
            r0.add(r6)     // Catch: java.lang.Throwable -> L6c
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L6c
            r7.unlock(r3)
            return r6
        L6c:
            r6 = move-exception
            r7.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.b.b(kotlin.collections.IndexedValue, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
