package androidx.paging;

import androidx.annotation.CheckResult;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.PagingSource;
import androidx.paging.ViewportHint;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.e;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001:\u0001IB\u0011\b\u0002\u0012\u0006\u0010F\u001a\u00020E¢\u0006\u0004\bG\u0010HJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\nJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\nJ-\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u000f\u0010\u0010J,\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rH\u0007J\u0014\u0010\u0019\u001a\u00020\u00182\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u0016J\u001e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00162\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aJ%\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0004\b \u0010!R.\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\r0#8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R$\u0010.\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00068\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R(\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001a0/8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R$\u0010:\u001a\u0002052\u0006\u0010)\u001a\u0002058\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0016\u0010<\u001a\u00020\u00068@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b;\u0010-R$\u0010A\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00068@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b>\u0010-\"\u0004\b?\u0010@R$\u0010D\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00068@@@X\u0080\u000e¢\u0006\f\u001a\u0004\bB\u0010-\"\u0004\bC\u0010@¨\u0006J"}, d2 = {"Landroidx/paging/PageFetcherSnapshotState;", "", "Key", "Value", "Landroidx/paging/LoadType;", "loadType", "", "generationId$paging_common", "(Landroidx/paging/LoadType;)I", "generationId", "Lkotlinx/coroutines/flow/Flow;", "consumePrependGenerationIdAsFlow", "consumeAppendGenerationIdAsFlow", "Landroidx/paging/PagingSource$LoadResult$Page;", "Landroidx/paging/PageEvent;", "toPageEvent$paging_common", "(Landroidx/paging/PagingSource$LoadResult$Page;Landroidx/paging/LoadType;)Landroidx/paging/PageEvent;", "toPageEvent", "loadId", "page", "", "insert", "Landroidx/paging/PageEvent$Drop;", "event", "", "drop", "Landroidx/paging/ViewportHint;", "hint", "dropEventOrNull", "Landroidx/paging/ViewportHint$Access;", "viewportHint", "Landroidx/paging/PagingState;", "currentPagingState$paging_common", "(Landroidx/paging/ViewportHint$Access;)Landroidx/paging/PagingState;", "currentPagingState", "", com.google.android.material.color.c.f10260a, "Ljava/util/List;", "getPages$paging_common", "()Ljava/util/List;", "pages", "<set-?>", "d", "I", "getInitialPageIndex$paging_common", "()I", "initialPageIndex", "", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Ljava/util/Map;", "getFailedHintsByLoadType$paging_common", "()Ljava/util/Map;", "failedHintsByLoadType", "Landroidx/paging/MutableLoadStateCollection;", "l", "Landroidx/paging/MutableLoadStateCollection;", "getSourceLoadStates$paging_common", "()Landroidx/paging/MutableLoadStateCollection;", "sourceLoadStates", "getStorageCount$paging_common", "storageCount", "value", "getPlaceholdersBefore$paging_common", "setPlaceholdersBefore$paging_common", "(I)V", "placeholdersBefore", "getPlaceholdersAfter$paging_common", "setPlaceholdersAfter$paging_common", "placeholdersAfter", "Landroidx/paging/PagingConfig;", Constants.KEY_CONFIG, "<init>", "(Landroidx/paging/PagingConfig;)V", "Holder", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PageFetcherSnapshotState<Key, Value> {
    @NotNull

    /* renamed from: a */
    public final PagingConfig f1518a;
    @NotNull
    public final List<PagingSource.LoadResult.Page<Key, Value>> b;
    @NotNull
    public final List<PagingSource.LoadResult.Page<Key, Value>> c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    @NotNull
    public final Channel<Integer> i;
    @NotNull
    public final Channel<Integer> j;
    @NotNull
    public final Map<LoadType, ViewportHint> k;
    @NotNull
    public MutableLoadStateCollection l;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u0001*\b\b\u0003\u0010\u0003*\u00020\u00012\u00020\u0001B\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010JH\u0010\u000b\u001a\u00028\u0004\"\u0004\b\u0004\u0010\u00042-\u0010\n\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00040\u0005H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Landroidx/paging/PageFetcherSnapshotState$Holder;", "", "Key", "Value", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/Function1;", "Landroidx/paging/PageFetcherSnapshotState;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "state", "block", "withLock", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/PagingConfig;", Constants.KEY_CONFIG, "<init>", "(Landroidx/paging/PagingConfig;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Holder<Key, Value> {
        @NotNull

        /* renamed from: a */
        public final PagingConfig f1519a;
        @NotNull
        public final Mutex b;
        @NotNull
        public final PageFetcherSnapshotState<Key, Value> c;

        @DebugMetadata(c = "androidx.paging.PageFetcherSnapshotState$Holder", f = "PageFetcherSnapshotState.kt", i = {0, 0, 0}, l = {403}, m = "withLock", n = {"this", "block", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
        /* loaded from: classes.dex */
        public static final class a<T> extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public int label;
            public /* synthetic */ Object result;
            public final /* synthetic */ Holder<Key, Value> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Holder<Key, Value> holder, Continuation<? super a> continuation) {
                super(continuation);
                this.this$0 = holder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.withLock(null, this);
            }
        }

        public Holder(@NotNull PagingConfig config) {
            Intrinsics.checkNotNullParameter(config, "config");
            this.f1519a = config;
            this.b = MutexKt.Mutex$default(false, 1, null);
            this.c = new PageFetcherSnapshotState<>(config, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0040  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final <T> java.lang.Object withLock(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super androidx.paging.PageFetcherSnapshotState<Key, Value>, ? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.PageFetcherSnapshotState.Holder.a
                if (r0 == 0) goto L13
                r0 = r7
                androidx.paging.PageFetcherSnapshotState$Holder$a r0 = (androidx.paging.PageFetcherSnapshotState.Holder.a) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.paging.PageFetcherSnapshotState$Holder$a r0 = new androidx.paging.PageFetcherSnapshotState$Holder$a
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
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.PageFetcherSnapshotState$Holder r0 = (androidx.paging.PageFetcherSnapshotState.Holder) r0
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                r6 = r1
                goto L57
            L38:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L40:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.sync.Mutex r7 = access$getLock$p(r5)
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.label = r4
                java.lang.Object r0 = r7.lock(r3, r0)
                if (r0 != r1) goto L56
                return r1
            L56:
                r0 = r5
            L57:
                androidx.paging.PageFetcherSnapshotState r0 = access$getState$p(r0)     // Catch: java.lang.Throwable -> L69
                java.lang.Object r6 = r6.invoke(r0)     // Catch: java.lang.Throwable -> L69
                kotlin.jvm.internal.InlineMarker.finallyStart(r4)
                r7.unlock(r3)
                kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
                return r6
            L69:
                r6 = move-exception
                kotlin.jvm.internal.InlineMarker.finallyStart(r4)
                r7.unlock(r3)
                kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshotState.Holder.withLock(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.APPEND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshotState$consumeAppendGenerationIdAsFlow$1", f = "PageFetcherSnapshotState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ PageFetcherSnapshotState<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = pageFetcherSnapshotState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super Integer> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.j.mo12trySendJP2dKIU(Boxing.boxInt(this.this$0.h));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshotState$consumePrependGenerationIdAsFlow$1", f = "PageFetcherSnapshotState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ PageFetcherSnapshotState<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = pageFetcherSnapshotState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super Integer> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.i.mo12trySendJP2dKIU(Boxing.boxInt(this.this$0.g));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public PageFetcherSnapshotState(PagingConfig pagingConfig) {
        this.f1518a = pagingConfig;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        this.c = arrayList;
        this.i = ChannelKt.Channel$default(-1, null, null, 6, null);
        this.j = ChannelKt.Channel$default(-1, null, null, 6, null);
        this.k = new LinkedHashMap();
        MutableLoadStateCollection mutableLoadStateCollection = new MutableLoadStateCollection();
        mutableLoadStateCollection.set(LoadType.REFRESH, LoadState.Loading.INSTANCE);
        Unit unit = Unit.INSTANCE;
        this.l = mutableLoadStateCollection;
    }

    public /* synthetic */ PageFetcherSnapshotState(PagingConfig pagingConfig, DefaultConstructorMarker defaultConstructorMarker) {
        this(pagingConfig);
    }

    @NotNull
    public final Flow<Integer> consumeAppendGenerationIdAsFlow() {
        return FlowKt.onStart(FlowKt.consumeAsFlow(this.j), new a(this, null));
    }

    @NotNull
    public final Flow<Integer> consumePrependGenerationIdAsFlow() {
        return FlowKt.onStart(FlowKt.consumeAsFlow(this.i), new b(this, null));
    }

    @NotNull
    public final PagingState<Key, Value> currentPagingState$paging_common(@Nullable ViewportHint.Access access) {
        Integer valueOf;
        int size;
        List list = CollectionsKt___CollectionsKt.toList(this.c);
        if (access == null) {
            valueOf = null;
        } else {
            int placeholdersBefore$paging_common = getPlaceholdersBefore$paging_common();
            int i = -getInitialPageIndex$paging_common();
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(getPages$paging_common()) - getInitialPageIndex$paging_common();
            int pageOffset = access.getPageOffset();
            if (i < pageOffset) {
                int i2 = i;
                while (true) {
                    int i3 = i2 + 1;
                    if (i2 > lastIndex) {
                        size = this.f1518a.pageSize;
                    } else {
                        size = getPages$paging_common().get(i2 + getInitialPageIndex$paging_common()).getData().size();
                    }
                    placeholdersBefore$paging_common += size;
                    if (i3 >= pageOffset) {
                        break;
                    }
                    i2 = i3;
                }
            }
            int indexInPage = placeholdersBefore$paging_common + access.getIndexInPage();
            if (access.getPageOffset() < i) {
                indexInPage -= this.f1518a.pageSize;
            }
            valueOf = Integer.valueOf(indexInPage);
        }
        return new PagingState<>(list, valueOf, this.f1518a, getPlaceholdersBefore$paging_common());
    }

    public final void drop(@NotNull PageEvent.Drop<Value> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getPageCount() <= this.c.size()) {
            this.k.remove(event.getLoadType());
            this.l.set(event.getLoadType(), LoadState.NotLoading.Companion.getIncomplete$paging_common());
            int i = WhenMappings.$EnumSwitchMapping$0[event.getLoadType().ordinal()];
            if (i != 2) {
                if (i == 3) {
                    int pageCount = event.getPageCount();
                    for (int i2 = 0; i2 < pageCount; i2++) {
                        this.b.remove(getPages$paging_common().size() - 1);
                    }
                    setPlaceholdersAfter$paging_common(event.getPlaceholdersRemaining());
                    int i3 = this.h + 1;
                    this.h = i3;
                    this.j.mo12trySendJP2dKIU(Integer.valueOf(i3));
                    return;
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus("cannot drop ", event.getLoadType()));
            }
            int pageCount2 = event.getPageCount();
            for (int i4 = 0; i4 < pageCount2; i4++) {
                this.b.remove(0);
            }
            this.d -= event.getPageCount();
            setPlaceholdersBefore$paging_common(event.getPlaceholdersRemaining());
            int i5 = this.g + 1;
            this.g = i5;
            this.i.mo12trySendJP2dKIU(Integer.valueOf(i5));
            return;
        }
        throw new IllegalStateException(("invalid drop count. have " + getPages$paging_common().size() + " but wanted to drop " + event.getPageCount()).toString());
    }

    @Nullable
    public final PageEvent.Drop<Value> dropEventOrNull(@NotNull LoadType loadType, @NotNull ViewportHint hint) {
        int lastIndex;
        int lastIndex2;
        int size;
        int presentedItemsAfter;
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(hint, "hint");
        PageEvent.Drop<Value> drop = null;
        if (this.f1518a.maxSize != Integer.MAX_VALUE && this.c.size() > 2 && getStorageCount$paging_common() > this.f1518a.maxSize) {
            int i = 0;
            if (loadType != LoadType.REFRESH) {
                int i2 = 0;
                int i3 = 0;
                while (i2 < this.c.size() && getStorageCount$paging_common() - i3 > this.f1518a.maxSize) {
                    int[] iArr = WhenMappings.$EnumSwitchMapping$0;
                    if (iArr[loadType.ordinal()] == 2) {
                        size = this.c.get(i2).getData().size();
                    } else {
                        List<PagingSource.LoadResult.Page<Key, Value>> list = this.c;
                        size = list.get(CollectionsKt__CollectionsKt.getLastIndex(list) - i2).getData().size();
                    }
                    if (iArr[loadType.ordinal()] == 2) {
                        presentedItemsAfter = hint.getPresentedItemsBefore();
                    } else {
                        presentedItemsAfter = hint.getPresentedItemsAfter();
                    }
                    if ((presentedItemsAfter - i3) - size < this.f1518a.prefetchDistance) {
                        break;
                    }
                    i3 += size;
                    i2++;
                }
                if (i2 != 0) {
                    int[] iArr2 = WhenMappings.$EnumSwitchMapping$0;
                    if (iArr2[loadType.ordinal()] == 2) {
                        lastIndex = -this.d;
                    } else {
                        lastIndex = (CollectionsKt__CollectionsKt.getLastIndex(this.c) - this.d) - (i2 - 1);
                    }
                    if (iArr2[loadType.ordinal()] == 2) {
                        lastIndex2 = (i2 - 1) - this.d;
                    } else {
                        lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(this.c) - this.d;
                    }
                    if (this.f1518a.enablePlaceholders) {
                        i = (loadType == LoadType.PREPEND ? getPlaceholdersBefore$paging_common() : getPlaceholdersAfter$paging_common()) + i3;
                    }
                    drop = new PageEvent.Drop<>(loadType, lastIndex, lastIndex2, i);
                }
                return drop;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("Drop LoadType must be PREPEND or APPEND, but got ", loadType).toString());
        }
        return null;
    }

    public final int generationId$paging_common(@NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return this.h;
                }
                throw new NoWhenBranchMatchedException();
            }
            return this.g;
        }
        throw new IllegalArgumentException("Cannot get loadId for loadType: REFRESH");
    }

    @NotNull
    public final Map<LoadType, ViewportHint> getFailedHintsByLoadType$paging_common() {
        return this.k;
    }

    public final int getInitialPageIndex$paging_common() {
        return this.d;
    }

    @NotNull
    public final List<PagingSource.LoadResult.Page<Key, Value>> getPages$paging_common() {
        return this.c;
    }

    public final int getPlaceholdersAfter$paging_common() {
        if (this.f1518a.enablePlaceholders) {
            return this.f;
        }
        return 0;
    }

    public final int getPlaceholdersBefore$paging_common() {
        if (this.f1518a.enablePlaceholders) {
            return this.e;
        }
        return 0;
    }

    @NotNull
    public final MutableLoadStateCollection getSourceLoadStates$paging_common() {
        return this.l;
    }

    public final int getStorageCount$paging_common() {
        Iterator<T> it = this.c.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((PagingSource.LoadResult.Page) it.next()).getData().size();
        }
        return i;
    }

    @CheckResult
    public final boolean insert(int i, @NotNull LoadType loadType, @NotNull PagingSource.LoadResult.Page<Key, Value> page) {
        int itemsBefore;
        int itemsAfter;
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(page, "page");
        int i2 = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    if (!this.c.isEmpty()) {
                        if (i != this.h) {
                            return false;
                        }
                        this.b.add(page);
                        if (page.getItemsAfter() == Integer.MIN_VALUE) {
                            itemsAfter = h.coerceAtLeast(getPlaceholdersAfter$paging_common() - page.getData().size(), 0);
                        } else {
                            itemsAfter = page.getItemsAfter();
                        }
                        setPlaceholdersAfter$paging_common(itemsAfter);
                        this.k.remove(LoadType.APPEND);
                    } else {
                        throw new IllegalStateException("should've received an init before append".toString());
                    }
                }
            } else if (!this.c.isEmpty()) {
                if (i != this.g) {
                    return false;
                }
                this.b.add(0, page);
                this.d++;
                if (page.getItemsBefore() == Integer.MIN_VALUE) {
                    itemsBefore = h.coerceAtLeast(getPlaceholdersBefore$paging_common() - page.getData().size(), 0);
                } else {
                    itemsBefore = page.getItemsBefore();
                }
                setPlaceholdersBefore$paging_common(itemsBefore);
                this.k.remove(LoadType.PREPEND);
            } else {
                throw new IllegalStateException("should've received an init before prepend".toString());
            }
        } else if (!this.c.isEmpty()) {
            throw new IllegalStateException("cannot receive multiple init calls".toString());
        } else {
            if (i == 0) {
                this.b.add(page);
                this.d = 0;
                setPlaceholdersAfter$paging_common(page.getItemsAfter());
                setPlaceholdersBefore$paging_common(page.getItemsBefore());
            } else {
                throw new IllegalStateException("init loadId must be the initial value, 0".toString());
            }
        }
        return true;
    }

    public final void setPlaceholdersAfter$paging_common(int i) {
        if (i == Integer.MIN_VALUE) {
            i = 0;
        }
        this.f = i;
    }

    public final void setPlaceholdersBefore$paging_common(int i) {
        if (i == Integer.MIN_VALUE) {
            i = 0;
        }
        this.e = i;
    }

    @NotNull
    public final PageEvent<Value> toPageEvent$paging_common(@NotNull PagingSource.LoadResult.Page<Key, Value> page, @NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(page, "<this>");
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i = iArr[loadType.ordinal()];
        int i2 = 0;
        if (i != 1) {
            if (i == 2) {
                i2 = 0 - this.d;
            } else if (i != 3) {
                throw new NoWhenBranchMatchedException();
            } else {
                i2 = (this.c.size() - this.d) - 1;
            }
        }
        List listOf = e.listOf(new TransformablePage(i2, page.getData()));
        int i3 = iArr[loadType.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 == 3) {
                    return PageEvent.Insert.Companion.Append(listOf, getPlaceholdersAfter$paging_common(), this.l.snapshot(), null);
                }
                throw new NoWhenBranchMatchedException();
            }
            return PageEvent.Insert.Companion.Prepend(listOf, getPlaceholdersBefore$paging_common(), this.l.snapshot(), null);
        }
        return PageEvent.Insert.Companion.Refresh(listOf, getPlaceholdersBefore$paging_common(), getPlaceholdersAfter$paging_common(), this.l.snapshot(), null);
    }
}
