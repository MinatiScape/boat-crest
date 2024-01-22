package androidx.paging;

import androidx.annotation.RestrictTo;
import androidx.paging.LoadState;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u0000 (2\u00020\u0001:\u0001(B\u001f\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\u0004¢\u0006\u0004\b&\u0010'J&\u0010\u0007\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0002H\u0087\bø\u0001\u0000J\u001f\u0010\f\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u0004HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0017HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0013\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\u0014\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!R\u0019\u0010\u0015\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b$\u0010\u001f\u001a\u0004\b%\u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006)"}, d2 = {"Landroidx/paging/LoadStates;", "", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "", "op", "forEach", "loadType", "newState", "modifyState$paging_common", "(Landroidx/paging/LoadType;Landroidx/paging/LoadState;)Landroidx/paging/LoadStates;", "modifyState", "get$paging_common", "(Landroidx/paging/LoadType;)Landroidx/paging/LoadState;", "get", "component1", "component2", "component3", "refresh", "prepend", "append", Constants.COPY_TYPE, "", "toString", "", "hashCode", FitnessActivities.OTHER, "", "equals", "a", "Landroidx/paging/LoadState;", "getRefresh", "()Landroidx/paging/LoadState;", "b", "getPrepend", com.google.android.material.color.c.f10260a, "getAppend", "<init>", "(Landroidx/paging/LoadState;Landroidx/paging/LoadState;Landroidx/paging/LoadState;)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class LoadStates {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final LoadStates d;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final LoadState f1506a;
    @NotNull
    public final LoadState b;
    @NotNull
    public final LoadState c;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Landroidx/paging/LoadStates$Companion;", "", "Landroidx/paging/LoadStates;", "IDLE", "Landroidx/paging/LoadStates;", "getIDLE", "()Landroidx/paging/LoadStates;", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final LoadStates getIDLE() {
            return LoadStates.d;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.APPEND.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.REFRESH.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        LoadState.NotLoading.Companion companion = LoadState.NotLoading.Companion;
        d = new LoadStates(companion.getIncomplete$paging_common(), companion.getIncomplete$paging_common(), companion.getIncomplete$paging_common());
    }

    public LoadStates(@NotNull LoadState refresh, @NotNull LoadState prepend, @NotNull LoadState append) {
        Intrinsics.checkNotNullParameter(refresh, "refresh");
        Intrinsics.checkNotNullParameter(prepend, "prepend");
        Intrinsics.checkNotNullParameter(append, "append");
        this.f1506a = refresh;
        this.b = prepend;
        this.c = append;
    }

    public static /* synthetic */ LoadStates copy$default(LoadStates loadStates, LoadState loadState, LoadState loadState2, LoadState loadState3, int i, Object obj) {
        if ((i & 1) != 0) {
            loadState = loadStates.f1506a;
        }
        if ((i & 2) != 0) {
            loadState2 = loadStates.b;
        }
        if ((i & 4) != 0) {
            loadState3 = loadStates.c;
        }
        return loadStates.copy(loadState, loadState2, loadState3);
    }

    @NotNull
    public final LoadState component1() {
        return this.f1506a;
    }

    @NotNull
    public final LoadState component2() {
        return this.b;
    }

    @NotNull
    public final LoadState component3() {
        return this.c;
    }

    @NotNull
    public final LoadStates copy(@NotNull LoadState refresh, @NotNull LoadState prepend, @NotNull LoadState append) {
        Intrinsics.checkNotNullParameter(refresh, "refresh");
        Intrinsics.checkNotNullParameter(prepend, "prepend");
        Intrinsics.checkNotNullParameter(append, "append");
        return new LoadStates(refresh, prepend, append);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LoadStates) {
            LoadStates loadStates = (LoadStates) obj;
            return Intrinsics.areEqual(this.f1506a, loadStates.f1506a) && Intrinsics.areEqual(this.b, loadStates.b) && Intrinsics.areEqual(this.c, loadStates.c);
        }
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void forEach(@NotNull Function2<? super LoadType, ? super LoadState, Unit> op) {
        Intrinsics.checkNotNullParameter(op, "op");
        op.invoke(LoadType.REFRESH, getRefresh());
        op.invoke(LoadType.PREPEND, getPrepend());
        op.invoke(LoadType.APPEND, getAppend());
    }

    @NotNull
    public final LoadState get$paging_common(@NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return this.f1506a;
                }
                throw new NoWhenBranchMatchedException();
            }
            return this.b;
        }
        return this.c;
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
        return this.f1506a;
    }

    public int hashCode() {
        return (((this.f1506a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    @NotNull
    public final LoadStates modifyState$paging_common(@NotNull LoadType loadType, @NotNull LoadState newState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(newState, "newState");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return copy$default(this, newState, null, null, 6, null);
                }
                throw new NoWhenBranchMatchedException();
            }
            return copy$default(this, null, newState, null, 5, null);
        }
        return copy$default(this, null, null, newState, 3, null);
    }

    @NotNull
    public String toString() {
        return "LoadStates(refresh=" + this.f1506a + ", prepend=" + this.b + ", append=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
