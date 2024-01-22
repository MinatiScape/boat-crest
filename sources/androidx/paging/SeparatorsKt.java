package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.leanplum.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.e;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ag\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\u00032.\u0010\u0006\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0004H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a?\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0002*\u00020\u00002\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001aK\u0010\u0013\u001a\u00020\u0012\"\b\b\u0000\u0010\u0002*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00112\b\u0010\t\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001am\u0010\u0013\u001a\u00020\u0012\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00028\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00112\b\u0010\t\u001a\u0004\u0018\u00018\u00002\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00032\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0013\u0010\u0017\u001az\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00190\u0018\"\b\b\u0000\u0010\u0002*\u00028\u0001\"\b\b\u0001\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00190\u00182\u0006\u0010\u001b\u001a\u00020\u001a2.\u0010\u0006\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"", "R", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/TransformablePage;", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "generator", "insertInternalSeparators", "(Landroidx/paging/TransformablePage;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "separator", "", "originalPageOffsets", "", "hintOriginalPageOffset", "hintOriginalIndex", "separatorPage", "(Ljava/lang/Object;[III)Landroidx/paging/TransformablePage;", "", "", "addSeparatorPage", "(Ljava/util/List;Ljava/lang/Object;[III)V", "adjacentPageBefore", "adjacentPageAfter", "(Ljava/util/List;Ljava/lang/Object;Landroidx/paging/TransformablePage;Landroidx/paging/TransformablePage;II)V", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "Landroidx/paging/TerminalSeparatorType;", "terminalSeparatorType", "insertEventSeparators", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "paging-common"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class SeparatorsKt {

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    @DebugMetadata(c = "androidx.paging.SeparatorsKt$insertEventSeparators$separatorState$1", f = "Separators.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.s2}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class a<R, T> extends SuspendLambda implements Function3<T, T, Continuation<? super R>, Object> {
        public final /* synthetic */ Function3<T, T, Continuation<? super R>, Object> $generator;
        public /* synthetic */ Object L$0;
        public /* synthetic */ Object L$1;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super a> continuation) {
            super(3, continuation);
            this.$generator = function3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke(obj, obj2, (Continuation) ((Continuation) obj3));
        }

        @Nullable
        public final Object invoke(@Nullable T t, @Nullable T t2, @Nullable Continuation<? super R> continuation) {
            a aVar = new a(this.$generator, continuation);
            aVar.L$0 = t;
            aVar.L$1 = t2;
            return aVar.invokeSuspend(Unit.INSTANCE);
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
                Object obj3 = this.L$1;
                this.L$0 = null;
                this.label = 1;
                obj = this.$generator.invoke(obj2, obj3, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @DebugMetadata(c = "androidx.paging.SeparatorsKt", f = "Separators.kt", i = {0, 0, 0, 0, 0, 0}, l = {81}, m = "insertInternalSeparators", n = {"$this$insertInternalSeparators", "generator", "outputList", "outputIndices", Constants.IAP_ITEM_PARAM, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$2"})
    /* loaded from: classes.dex */
    public static final class b<R, T extends R> extends ContinuationImpl {
        public int I$0;
        public int I$1;
        public int I$2;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public int label;
        public /* synthetic */ Object result;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SeparatorsKt.insertInternalSeparators(null, null, this);
        }
    }

    public static final <T> void addSeparatorPage(@NotNull List<TransformablePage<T>> list, @Nullable T t, @NotNull int[] originalPageOffsets, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(originalPageOffsets, "originalPageOffsets");
        if (t == null) {
            return;
        }
        list.add(separatorPage(t, originalPageOffsets, i, i2));
    }

    @NotNull
    public static final <T extends R, R> Flow<PageEvent<R>> insertEventSeparators(@NotNull final Flow<? extends PageEvent<T>> flow, @NotNull TerminalSeparatorType terminalSeparatorType, @NotNull Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> generator) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(generator, "generator");
        final SeparatorState separatorState = new SeparatorState(terminalSeparatorType, new a(generator, null));
        return new Flow<PageEvent<R>>() { // from class: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1

            /* JADX INFO: Add missing generic type declarations: [T] */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 1, mv = {1, 5, 1})
            /* renamed from: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                public final /* synthetic */ FlowCollector h;
                public final /* synthetic */ SeparatorState i;

                @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
                @DebugMetadata(c = "androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2", f = "Separators.kt", i = {}, l = {137, 137}, m = "emit", n = {}, s = {})
                /* renamed from: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
                    public int label;
                    public /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, SeparatorState separatorState) {
                    this.h = flowCollector;
                    this.i = separatorState;
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x005e A[RETURN] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1 r0 = (androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1 r0 = new androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3c
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5f
                    L2c:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L34:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L53
                    L3c:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.h
                        androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
                        androidx.paging.SeparatorState r2 = r6.i
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r2.n(r7, r0)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r5 = r8
                        r8 = r7
                        r7 = r5
                    L53:
                        r2 = 0
                        r0.L$0 = r2
                        r0.label = r3
                        java.lang.Object r7 = r7.emit(r8, r0)
                        if (r7 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, separatorState), continuation);
                return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00da -> B:31:0x00e3). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <R, T extends R> java.lang.Object insertInternalSeparators(@org.jetbrains.annotations.NotNull androidx.paging.TransformablePage<T> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super T, ? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.TransformablePage<R>> r14) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorsKt.insertInternalSeparators(androidx.paging.TransformablePage, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public static final <T> TransformablePage<T> separatorPage(@NotNull T separator, @NotNull int[] originalPageOffsets, int i, int i2) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(originalPageOffsets, "originalPageOffsets");
        return new TransformablePage<>(originalPageOffsets, e.listOf(separator), i, e.listOf(Integer.valueOf(i2)));
    }

    public static final <R, T extends R> void addSeparatorPage(@NotNull List<TransformablePage<R>> list, @Nullable R r, @Nullable TransformablePage<T> transformablePage, @Nullable TransformablePage<T> transformablePage2, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int[] originalPageOffsets = transformablePage == null ? null : transformablePage.getOriginalPageOffsets();
        int[] originalPageOffsets2 = transformablePage2 != null ? transformablePage2.getOriginalPageOffsets() : null;
        if (originalPageOffsets != null && originalPageOffsets2 != null) {
            originalPageOffsets = CollectionsKt___CollectionsKt.toIntArray(CollectionsKt___CollectionsKt.sorted(ArraysKt___ArraysKt.distinct(ArraysKt___ArraysJvmKt.plus(originalPageOffsets, originalPageOffsets2))));
        } else if (originalPageOffsets == null && originalPageOffsets2 != null) {
            originalPageOffsets = originalPageOffsets2;
        } else if (originalPageOffsets == null || originalPageOffsets2 != null) {
            throw new IllegalArgumentException("Separator page expected adjacentPageBefore or adjacentPageAfter, but both were null.");
        }
        addSeparatorPage(list, r, originalPageOffsets, i, i2);
    }
}
