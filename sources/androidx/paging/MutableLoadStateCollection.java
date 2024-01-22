package androidx.paging;

import androidx.paging.LoadState;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0014\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002R\"\u0010\u0013\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0017\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\"\u0010\u001b\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012¨\u0006\u001e"}, d2 = {"Landroidx/paging/MutableLoadStateCollection;", "", "Landroidx/paging/LoadStates;", "snapshot", "Landroidx/paging/LoadType;", "loadType", "Landroidx/paging/LoadState;", "get", "type", "state", "", "set", "states", "a", "Landroidx/paging/LoadState;", "getRefresh", "()Landroidx/paging/LoadState;", "setRefresh", "(Landroidx/paging/LoadState;)V", "refresh", "b", "getPrepend", "setPrepend", "prepend", com.google.android.material.color.c.f10260a, "getAppend", "setAppend", "append", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class MutableLoadStateCollection {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public LoadState f1508a;
    @NotNull
    public LoadState b;
    @NotNull
    public LoadState c;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.APPEND.ordinal()] = 2;
            iArr[LoadType.PREPEND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MutableLoadStateCollection() {
        LoadState.NotLoading.Companion companion = LoadState.NotLoading.Companion;
        this.f1508a = companion.getIncomplete$paging_common();
        this.b = companion.getIncomplete$paging_common();
        this.c = companion.getIncomplete$paging_common();
    }

    @NotNull
    public final LoadState get(@NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return this.b;
                }
                throw new NoWhenBranchMatchedException();
            }
            return this.c;
        }
        return this.f1508a;
    }

    @NotNull
    public final LoadState getAppend() {
        return this.c;
    }

    @NotNull
    public final LoadState getPrepend() {
        return this.b;
    }

    @NotNull
    public final LoadState getRefresh() {
        return this.f1508a;
    }

    public final void set(@NotNull LoadType type, @NotNull LoadState state) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            this.f1508a = state;
        } else if (i == 2) {
            this.c = state;
        } else if (i != 3) {
            throw new NoWhenBranchMatchedException();
        } else {
            this.b = state;
        }
    }

    public final void setAppend(@NotNull LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "<set-?>");
        this.c = loadState;
    }

    public final void setPrepend(@NotNull LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "<set-?>");
        this.b = loadState;
    }

    public final void setRefresh(@NotNull LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "<set-?>");
        this.f1508a = loadState;
    }

    @NotNull
    public final LoadStates snapshot() {
        return new LoadStates(this.f1508a, this.b, this.c);
    }

    public final void set(@NotNull LoadStates states) {
        Intrinsics.checkNotNullParameter(states, "states");
        this.f1508a = states.getRefresh();
        this.c = states.getAppend();
        this.b = states.getPrepend();
    }
}
