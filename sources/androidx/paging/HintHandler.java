package androidx.paging;

import androidx.paging.ViewportHint;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0005R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000b8F@\u0006¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Landroidx/paging/HintHandler;", "", "Landroidx/paging/LoadType;", "loadType", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/ViewportHint;", "hintFor", "viewportHint", "", "forceSetHint", "processHint", "Landroidx/paging/ViewportHint$Access;", "getLastAccessHint", "()Landroidx/paging/ViewportHint$Access;", "lastAccessHint", "<init>", "()V", "a", "b", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class HintHandler {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final b f1491a = new b(this);

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.PREPEND.ordinal()] = 1;
            iArr[LoadType.APPEND.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes.dex */
    public final class a {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public ViewportHint f1492a;
        @NotNull
        public final MutableSharedFlow<ViewportHint> b;

        public a(HintHandler this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.b = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2, null);
        }

        @NotNull
        public final Flow<ViewportHint> a() {
            return this.b;
        }

        @Nullable
        public final ViewportHint b() {
            return this.f1492a;
        }

        public final void c(@Nullable ViewportHint viewportHint) {
            this.f1492a = viewportHint;
            if (viewportHint != null) {
                this.b.tryEmit(viewportHint);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class b {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final a f1493a;
        @NotNull
        public final a b;
        @Nullable
        public ViewportHint.Access c;
        @NotNull
        public final ReentrantLock d;
        public final /* synthetic */ HintHandler e;

        public b(HintHandler this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.e = this$0;
            this.f1493a = new a(this$0);
            this.b = new a(this$0);
            this.d = new ReentrantLock();
        }

        @NotNull
        public final Flow<ViewportHint> a() {
            return this.b.a();
        }

        @Nullable
        public final ViewportHint.Access b() {
            return this.c;
        }

        @NotNull
        public final Flow<ViewportHint> c() {
            return this.f1493a.a();
        }

        public final void d(@Nullable ViewportHint.Access access, @NotNull Function2<? super a, ? super a, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            ReentrantLock reentrantLock = this.d;
            reentrantLock.lock();
            if (access != null) {
                try {
                    this.c = access;
                } finally {
                    reentrantLock.unlock();
                }
            }
            block.invoke(this.f1493a, this.b);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends Lambda implements Function2<a, a, Unit> {
        public final /* synthetic */ LoadType $loadType;
        public final /* synthetic */ ViewportHint $viewportHint;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(LoadType loadType, ViewportHint viewportHint) {
            super(2);
            this.$loadType = loadType;
            this.$viewportHint = viewportHint;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(a aVar, a aVar2) {
            invoke2(aVar, aVar2);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull a prependHint, @NotNull a appendHint) {
            Intrinsics.checkNotNullParameter(prependHint, "prependHint");
            Intrinsics.checkNotNullParameter(appendHint, "appendHint");
            if (this.$loadType == LoadType.PREPEND) {
                prependHint.c(this.$viewportHint);
            } else {
                appendHint.c(this.$viewportHint);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class d extends Lambda implements Function2<a, a, Unit> {
        public final /* synthetic */ ViewportHint $viewportHint;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(ViewportHint viewportHint) {
            super(2);
            this.$viewportHint = viewportHint;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(a aVar, a aVar2) {
            invoke2(aVar, aVar2);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull a prependHint, @NotNull a appendHint) {
            Intrinsics.checkNotNullParameter(prependHint, "prependHint");
            Intrinsics.checkNotNullParameter(appendHint, "appendHint");
            if (HintHandlerKt.shouldPrioritizeOver(this.$viewportHint, prependHint.b(), LoadType.PREPEND)) {
                prependHint.c(this.$viewportHint);
            }
            if (HintHandlerKt.shouldPrioritizeOver(this.$viewportHint, appendHint.b(), LoadType.APPEND)) {
                appendHint.c(this.$viewportHint);
            }
        }
    }

    public final void forceSetHint(@NotNull LoadType loadType, @NotNull ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        if (loadType == LoadType.PREPEND || loadType == LoadType.APPEND) {
            this.f1491a.d(null, new c(loadType, viewportHint));
            return;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("invalid load type for reset: ", loadType).toString());
    }

    @Nullable
    public final ViewportHint.Access getLastAccessHint() {
        return this.f1491a.b();
    }

    @NotNull
    public final Flow<ViewportHint> hintFor(@NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return this.f1491a.a();
            }
            throw new IllegalArgumentException("invalid load type for hints");
        }
        return this.f1491a.c();
    }

    public final void processHint(@NotNull ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        this.f1491a.d(viewportHint instanceof ViewportHint.Access ? (ViewportHint.Access) viewportHint : null, new d(viewportHint));
    }
}
