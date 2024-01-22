package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import com.jieli.jl_rcsp.constant.Command;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.i;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SeparatorState<R, T extends R> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final TerminalSeparatorType f1549a;
    @NotNull
    public final Function3<T, T, Continuation<? super R>, Object> b;
    @NotNull
    public final List<TransformablePage<T>> c;
    public boolean d;
    public boolean e;
    @NotNull
    public final MutableLoadStateCollection f;
    @Nullable
    public LoadStates g;
    public int h;
    public int i;
    public boolean j;
    public boolean k;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TerminalSeparatorType.values().length];
            iArr[TerminalSeparatorType.FULLY_COMPLETE.ordinal()] = 1;
            iArr[TerminalSeparatorType.SOURCE_COMPLETE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<TransformablePage<T>, Boolean> {
        public final /* synthetic */ IntRange $pageOffsetsToDrop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(IntRange intRange) {
            super(1);
            this.$pageOffsetsToDrop = intRange;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
            return invoke((TransformablePage) ((TransformablePage) obj));
        }

        @NotNull
        public final Boolean invoke(@NotNull TransformablePage<T> stash) {
            Intrinsics.checkNotNullParameter(stash, "stash");
            int[] originalPageOffsets = stash.getOriginalPageOffsets();
            IntRange intRange = this.$pageOffsetsToDrop;
            int length = originalPageOffsets.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (intRange.contains(originalPageOffsets[i])) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            return Boolean.valueOf(z);
        }
    }

    @DebugMetadata(c = "androidx.paging.SeparatorState", f = "Separators.kt", i = {0, 1}, l = {Command.CMD_GET_LOW_LATENCY_SETTINGS, 215}, m = "onEvent", n = {"this", "this"}, s = {"L$0", "L$0"})
    /* loaded from: classes.dex */
    public static final class b extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ SeparatorState<R, T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(SeparatorState<R, T> separatorState, Continuation<? super b> continuation) {
            super(continuation);
            this.this$0 = separatorState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.n(null, this);
        }
    }

    @DebugMetadata(c = "androidx.paging.SeparatorState", f = "Separators.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9}, l = {305, 368, 380, 386, 398, com.veryfit.multi.nativeprotocol.b.A1, 429, 438, 451, 462}, m = "onInsert", n = {"this", "event", "this", "event", "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "pageAfter", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "lastStash", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "iterator$iv", "page", "pageBefore", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "iterator$iv", "page", "pageBefore", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "pageAfter", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "eventTerminatesEnd", "eventEmpty", "pageIndex", "this", "event", "outList", "stashOutList", "pageBefore"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
    /* loaded from: classes.dex */
    public static final class c extends ContinuationImpl {
        public int I$0;
        public int I$1;
        public int I$2;
        public int I$3;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public Object L$6;
        public Object L$7;
        public Object L$8;
        public Object L$9;
        public boolean Z$0;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ SeparatorState<R, T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(SeparatorState<R, T> separatorState, Continuation<? super c> continuation) {
            super(continuation);
            this.this$0 = separatorState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.o(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SeparatorState(@NotNull TerminalSeparatorType terminalSeparatorType, @NotNull Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> generator) {
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(generator, "generator");
        this.f1549a = terminalSeparatorType;
        this.b = generator;
        this.c = new ArrayList();
        this.f = new MutableLoadStateCollection();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final PageEvent.Insert<R> a(@NotNull PageEvent.Insert<T> insert) {
        Intrinsics.checkNotNullParameter(insert, "<this>");
        return insert;
    }

    public final boolean b() {
        return this.d;
    }

    public final boolean c() {
        return this.j;
    }

    @NotNull
    public final Function3<T, T, Continuation<? super R>, Object> d() {
        return this.b;
    }

    public final boolean e() {
        return this.k;
    }

    @Nullable
    public final LoadStates f() {
        return this.g;
    }

    @NotNull
    public final List<TransformablePage<T>> g() {
        return this.c;
    }

    public final int h() {
        return this.i;
    }

    public final int i() {
        return this.h;
    }

    @NotNull
    public final MutableLoadStateCollection j() {
        return this.f;
    }

    public final boolean k() {
        return this.e;
    }

    @NotNull
    public final TerminalSeparatorType l() {
        return this.f1549a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final PageEvent.Drop<R> m(@NotNull PageEvent.Drop<T> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.f.set(event.getLoadType(), LoadState.NotLoading.Companion.getIncomplete$paging_common());
        LoadType loadType = event.getLoadType();
        LoadType loadType2 = LoadType.PREPEND;
        if (loadType == loadType2) {
            this.h = event.getPlaceholdersRemaining();
            this.k = false;
        } else if (event.getLoadType() == LoadType.APPEND) {
            this.i = event.getPlaceholdersRemaining();
            this.j = false;
        }
        if (this.c.isEmpty()) {
            if (event.getLoadType() == loadType2) {
                this.e = false;
            } else {
                this.d = false;
            }
        }
        i.removeAll((List) this.c, (Function1) new a(new IntRange(event.getMinPageOffset(), event.getMaxPageOffset())));
        return event;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object n(@org.jetbrains.annotations.NotNull androidx.paging.PageEvent<T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.paging.SeparatorState.b
            if (r0 == 0) goto L13
            r0 = r7
            androidx.paging.SeparatorState$b r0 = (androidx.paging.SeparatorState.b) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.SeparatorState$b r0 = new androidx.paging.SeparatorState$b
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r6 = r0.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L76
        L30:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L38:
            java.lang.Object r6 = r0.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L55
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            boolean r7 = r6 instanceof androidx.paging.PageEvent.Insert
            if (r7 == 0) goto L58
            androidx.paging.PageEvent$Insert r6 = (androidx.paging.PageEvent.Insert) r6
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r7 = r5.o(r6, r0)
            if (r7 != r1) goto L54
            return r1
        L54:
            r6 = r5
        L55:
            androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
            goto L78
        L58:
            boolean r7 = r6 instanceof androidx.paging.PageEvent.Drop
            if (r7 == 0) goto L64
            androidx.paging.PageEvent$Drop r6 = (androidx.paging.PageEvent.Drop) r6
            androidx.paging.PageEvent$Drop r7 = r5.m(r6)
            r6 = r5
            goto L78
        L64:
            boolean r7 = r6 instanceof androidx.paging.PageEvent.LoadStateUpdate
            if (r7 == 0) goto Lb3
            androidx.paging.PageEvent$LoadStateUpdate r6 = (androidx.paging.PageEvent.LoadStateUpdate) r6
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = r5.p(r6, r0)
            if (r7 != r1) goto L75
            return r1
        L75:
            r6 = r5
        L76:
            androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
        L78:
            boolean r0 = r6.b()
            if (r0 == 0) goto L95
            java.util.List r0 = r6.g()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L89
            goto L95
        L89:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "deferred endTerm, page stash should be empty"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L95:
            boolean r0 = r6.k()
            if (r0 == 0) goto Lb2
            java.util.List r6 = r6.g()
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto La6
            goto Lb2
        La6:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "deferred startTerm, page stash should be empty"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        Lb2:
            return r7
        Lb3:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.n(androidx.paging.PageEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04a6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x04b5  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x05a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x05d1  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x063d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x06ae  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x06da A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x06ed  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x06ef  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x06f7  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0768  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0779  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x079f  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x07e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:235:0x07e2  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x07e8  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x07f0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x07f3  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x07fa  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0802  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x080a  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0863  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x086e  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0877  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0895  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x089d  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x08d1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0305  */
    /* JADX WARN: Type inference failed for: r1v59, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v6, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r6v14, types: [java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:139:0x04a7 -> B:140:0x04b0). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:206:0x06db -> B:207:0x06de). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:235:0x07e2 -> B:236:0x07e3). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object o(@org.jetbrains.annotations.NotNull androidx.paging.PageEvent.Insert<T> r32, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PageEvent.Insert<R>> r33) {
        /*
            Method dump skipped, instructions count: 2296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.o(androidx.paging.PageEvent$Insert, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Object p(@NotNull PageEvent.LoadStateUpdate<T> loadStateUpdate, @NotNull Continuation<? super PageEvent<R>> continuation) {
        LoadStates f = f();
        if (Intrinsics.areEqual(j().snapshot(), loadStateUpdate.getSource()) && Intrinsics.areEqual(f, loadStateUpdate.getMediator())) {
            return loadStateUpdate;
        }
        j().set(loadStateUpdate.getSource());
        t(loadStateUpdate.getMediator());
        if (loadStateUpdate.getMediator() != null && loadStateUpdate.getMediator().getPrepend().getEndOfPaginationReached()) {
            if (!Intrinsics.areEqual(f == null ? null : f.getPrepend(), loadStateUpdate.getMediator().getPrepend())) {
                return o(PageEvent.Insert.Companion.Prepend(CollectionsKt__CollectionsKt.emptyList(), i(), loadStateUpdate.getSource(), loadStateUpdate.getMediator()), continuation);
            }
        }
        if (loadStateUpdate.getMediator() == null || !loadStateUpdate.getMediator().getAppend().getEndOfPaginationReached()) {
            return loadStateUpdate;
        }
        return !Intrinsics.areEqual(f != null ? f.getAppend() : null, loadStateUpdate.getMediator().getAppend()) ? o(PageEvent.Insert.Companion.Append(CollectionsKt__CollectionsKt.emptyList(), h(), loadStateUpdate.getSource(), loadStateUpdate.getMediator()), continuation) : loadStateUpdate;
    }

    public final void q(boolean z) {
        this.d = z;
    }

    public final void r(boolean z) {
        this.j = z;
    }

    public final void s(boolean z) {
        this.k = z;
    }

    public final void t(@Nullable LoadStates loadStates) {
        this.g = loadStates;
    }

    public final void u(int i) {
        this.i = i;
    }

    public final void v(int i) {
        this.h = i;
    }

    public final void w(boolean z) {
        this.e = z;
    }

    public final <T> boolean x(@NotNull PageEvent.Insert<T> insert, @NotNull TerminalSeparatorType terminalSeparatorType) {
        LoadState append;
        Intrinsics.checkNotNullParameter(insert, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.PREPEND) {
            return this.d;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[terminalSeparatorType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getSourceLoadStates().getAppend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        }
        if (insert.getSourceLoadStates().getAppend().getEndOfPaginationReached()) {
            LoadStates mediatorLoadStates = insert.getMediatorLoadStates();
            if (!((mediatorLoadStates == null || (append = mediatorLoadStates.getAppend()) == null || append.getEndOfPaginationReached()) ? false : true)) {
                return true;
            }
        }
        return false;
    }

    public final <T> boolean y(@NotNull PageEvent.Insert<T> insert, @NotNull TerminalSeparatorType terminalSeparatorType) {
        LoadState prepend;
        Intrinsics.checkNotNullParameter(insert, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.APPEND) {
            return this.e;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[terminalSeparatorType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getSourceLoadStates().getPrepend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        }
        if (insert.getSourceLoadStates().getPrepend().getEndOfPaginationReached()) {
            LoadStates mediatorLoadStates = insert.getMediatorLoadStates();
            if (!((mediatorLoadStates == null || (prepend = mediatorLoadStates.getPrepend()) == null || prepend.getEndOfPaginationReached()) ? false : true)) {
                return true;
            }
        }
        return false;
    }

    public final <T> TransformablePage<T> z(TransformablePage<T> transformablePage) {
        Integer num;
        int[] originalPageOffsets = transformablePage.getOriginalPageOffsets();
        List listOf = CollectionsKt__CollectionsKt.listOf(CollectionsKt___CollectionsKt.first((List) transformablePage.getData()), CollectionsKt___CollectionsKt.last((List) transformablePage.getData()));
        int hintOriginalPageOffset = transformablePage.getHintOriginalPageOffset();
        Integer[] numArr = new Integer[2];
        List<Integer> hintOriginalIndices = transformablePage.getHintOriginalIndices();
        numArr[0] = Integer.valueOf((hintOriginalIndices == null || (num = (Integer) CollectionsKt___CollectionsKt.first((List<? extends Object>) hintOriginalIndices)) == null) ? 0 : num.intValue());
        List<Integer> hintOriginalIndices2 = transformablePage.getHintOriginalIndices();
        Integer num2 = hintOriginalIndices2 == null ? null : (Integer) CollectionsKt___CollectionsKt.last((List<? extends Object>) hintOriginalIndices2);
        numArr[1] = Integer.valueOf(num2 == null ? CollectionsKt__CollectionsKt.getLastIndex(transformablePage.getData()) : num2.intValue());
        return new TransformablePage<>(originalPageOffsets, listOf, hintOriginalPageOffset, CollectionsKt__CollectionsKt.listOf((Object[]) numArr));
    }
}
