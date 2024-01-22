package androidx.paging;

import androidx.paging.LoadState;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\"\u0010#J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002J\u001e\u0010\u0006\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tJ\u001a\u0010\u0011\u001a\u00020\u00052\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u000eJ\u001a\u0010\u0012\u001a\u00020\u00052\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u000eR$\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00028\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R(\u0010\u001b\u001a\u0004\u0018\u00010\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017R\u001f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001c8\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Landroidx/paging/MutableCombinedLoadStateCollection;", "", "Landroidx/paging/LoadStates;", "sourceLoadStates", "remoteLoadStates", "", "set", "Landroidx/paging/LoadType;", "type", "", "remote", "Landroidx/paging/LoadState;", "state", "get", "Lkotlin/Function1;", "Landroidx/paging/CombinedLoadStates;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "removeListener", "<set-?>", "f", "Landroidx/paging/LoadStates;", "getSource", "()Landroidx/paging/LoadStates;", "source", "g", "getMediator", "mediator", "Lkotlinx/coroutines/flow/Flow;", "i", "Lkotlinx/coroutines/flow/Flow;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "flow", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class MutableCombinedLoadStateCollection {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1507a;
    @NotNull
    public final CopyOnWriteArrayList<Function1<CombinedLoadStates, Unit>> b = new CopyOnWriteArrayList<>();
    @NotNull
    public LoadState c;
    @NotNull
    public LoadState d;
    @NotNull
    public LoadState e;
    @NotNull
    public LoadStates f;
    @Nullable
    public LoadStates g;
    @NotNull
    public final MutableStateFlow<CombinedLoadStates> h;
    @NotNull
    public final Flow<CombinedLoadStates> i;

    public MutableCombinedLoadStateCollection() {
        LoadState.NotLoading.Companion companion = LoadState.NotLoading.Companion;
        this.c = companion.getIncomplete$paging_common();
        this.d = companion.getIncomplete$paging_common();
        this.e = companion.getIncomplete$paging_common();
        this.f = LoadStates.Companion.getIDLE();
        MutableStateFlow<CombinedLoadStates> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this.h = MutableStateFlow;
        this.i = FlowKt.filterNotNull(MutableStateFlow);
    }

    public final LoadState a(LoadState loadState, LoadState loadState2, LoadState loadState3, LoadState loadState4) {
        return loadState4 == null ? loadState3 : (!(loadState instanceof LoadState.Loading) || ((loadState2 instanceof LoadState.NotLoading) && (loadState4 instanceof LoadState.NotLoading)) || (loadState4 instanceof LoadState.Error)) ? loadState4 : loadState;
    }

    public final void addListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.add(listener);
        CombinedLoadStates b = b();
        if (b == null) {
            return;
        }
        listener.invoke(b);
    }

    public final CombinedLoadStates b() {
        if (this.f1507a) {
            return new CombinedLoadStates(this.c, this.d, this.e, this.f, this.g);
        }
        return null;
    }

    public final void c() {
        LoadState loadState = this.c;
        LoadState refresh = this.f.getRefresh();
        LoadState refresh2 = this.f.getRefresh();
        LoadStates loadStates = this.g;
        this.c = a(loadState, refresh, refresh2, loadStates == null ? null : loadStates.getRefresh());
        LoadState loadState2 = this.d;
        LoadState refresh3 = this.f.getRefresh();
        LoadState prepend = this.f.getPrepend();
        LoadStates loadStates2 = this.g;
        this.d = a(loadState2, refresh3, prepend, loadStates2 == null ? null : loadStates2.getPrepend());
        LoadState loadState3 = this.e;
        LoadState refresh4 = this.f.getRefresh();
        LoadState append = this.f.getAppend();
        LoadStates loadStates3 = this.g;
        this.e = a(loadState3, refresh4, append, loadStates3 != null ? loadStates3.getAppend() : null);
        CombinedLoadStates b = b();
        if (b != null) {
            this.h.setValue(b);
            Iterator<T> it = this.b.iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(b);
            }
        }
    }

    @Nullable
    public final LoadState get(@NotNull LoadType type, boolean z) {
        Intrinsics.checkNotNullParameter(type, "type");
        LoadStates loadStates = z ? this.g : this.f;
        if (loadStates == null) {
            return null;
        }
        return loadStates.get$paging_common(type);
    }

    @NotNull
    public final Flow<CombinedLoadStates> getFlow() {
        return this.i;
    }

    @Nullable
    public final LoadStates getMediator() {
        return this.g;
    }

    @NotNull
    public final LoadStates getSource() {
        return this.f;
    }

    public final void removeListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.remove(listener);
    }

    public final void set(@NotNull LoadStates sourceLoadStates, @Nullable LoadStates loadStates) {
        Intrinsics.checkNotNullParameter(sourceLoadStates, "sourceLoadStates");
        this.f1507a = true;
        this.f = sourceLoadStates;
        this.g = loadStates;
        c();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r4, r5) == false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r4, r5) == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean set(@org.jetbrains.annotations.NotNull androidx.paging.LoadType r4, boolean r5, @org.jetbrains.annotations.NotNull androidx.paging.LoadState r6) {
        /*
            r3 = this;
            java.lang.String r0 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "state"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = 1
            r3.f1507a = r0
            r1 = 0
            if (r5 == 0) goto L29
            androidx.paging.LoadStates r5 = r3.g
            if (r5 != 0) goto L1b
            androidx.paging.LoadStates$Companion r2 = androidx.paging.LoadStates.Companion
            androidx.paging.LoadStates r2 = r2.getIDLE()
            goto L1c
        L1b:
            r2 = r5
        L1c:
            androidx.paging.LoadStates r4 = r2.modifyState$paging_common(r4, r6)
            r3.g = r4
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 != 0) goto L38
            goto L39
        L29:
            androidx.paging.LoadStates r5 = r3.f
            androidx.paging.LoadStates r4 = r5.modifyState$paging_common(r4, r6)
            r3.f = r4
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 != 0) goto L38
            goto L39
        L38:
            r0 = r1
        L39:
            r3.c()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.MutableCombinedLoadStateCollection.set(androidx.paging.LoadType, boolean, androidx.paging.LoadState):boolean");
    }
}
