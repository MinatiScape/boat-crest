package androidx.paging;

import androidx.paging.LoadState;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArrayDeque;
import kotlin.collections.i;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class AccessorState<Key, Value> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final BlockState[] f1469a;
    @NotNull
    public final LoadState.Error[] b;
    @NotNull
    public final ArrayDeque<PendingRequest<Key, Value>> c;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/paging/AccessorState$BlockState;", "", "<init>", "(Ljava/lang/String;I)V", "UNBLOCKED", FitnessChallengeConstants.COMPLETED, "REQUIRES_REFRESH", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public enum BlockState {
        UNBLOCKED,
        COMPLETED,
        REQUIRES_REFRESH
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u0001*\b\b\u0003\u0010\u0003*\u00020\u00012\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\n¢\u0006\u0004\b\u0012\u0010\u0013R\u0019\u0010\t\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR.\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/paging/AccessorState$PendingRequest;", "", "Key", "Value", "Landroidx/paging/LoadType;", "a", "Landroidx/paging/LoadType;", "getLoadType", "()Landroidx/paging/LoadType;", "loadType", "Landroidx/paging/PagingState;", "b", "Landroidx/paging/PagingState;", "getPagingState", "()Landroidx/paging/PagingState;", "setPagingState", "(Landroidx/paging/PagingState;)V", "pagingState", "<init>", "(Landroidx/paging/LoadType;Landroidx/paging/PagingState;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class PendingRequest<Key, Value> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LoadType f1470a;
        @NotNull
        public PagingState<Key, Value> b;

        public PendingRequest(@NotNull LoadType loadType, @NotNull PagingState<Key, Value> pagingState) {
            Intrinsics.checkNotNullParameter(loadType, "loadType");
            Intrinsics.checkNotNullParameter(pagingState, "pagingState");
            this.f1470a = loadType;
            this.b = pagingState;
        }

        @NotNull
        public final LoadType getLoadType() {
            return this.f1470a;
        }

        @NotNull
        public final PagingState<Key, Value> getPagingState() {
            return this.b;
        }

        public final void setPagingState(@NotNull PagingState<Key, Value> pagingState) {
            Intrinsics.checkNotNullParameter(pagingState, "<set-?>");
            this.b = pagingState;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[BlockState.values().length];
            iArr[BlockState.COMPLETED.ordinal()] = 1;
            iArr[BlockState.REQUIRES_REFRESH.ordinal()] = 2;
            iArr[BlockState.UNBLOCKED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[LoadType.values().length];
            iArr2[LoadType.REFRESH.ordinal()] = 1;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<PendingRequest<Key, Value>, Boolean> {
        public final /* synthetic */ LoadType $loadType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(LoadType loadType) {
            super(1);
            this.$loadType = loadType;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
            return invoke((PendingRequest) ((PendingRequest) obj));
        }

        @NotNull
        public final Boolean invoke(@NotNull PendingRequest<Key, Value> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Boolean.valueOf(it.getLoadType() == this.$loadType);
        }
    }

    public AccessorState() {
        int length = LoadType.values().length;
        BlockState[] blockStateArr = new BlockState[length];
        for (int i = 0; i < length; i++) {
            blockStateArr[i] = BlockState.UNBLOCKED;
        }
        this.f1469a = blockStateArr;
        int length2 = LoadType.values().length;
        LoadState.Error[] errorArr = new LoadState.Error[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            errorArr[i2] = null;
        }
        this.b = errorArr;
        this.c = new ArrayDeque<>();
    }

    public final boolean a(@NotNull LoadType loadType, @NotNull PagingState<Key, Value> pagingState) {
        PendingRequest<Key, Value> pendingRequest;
        boolean z;
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(pagingState, "pagingState");
        Iterator<PendingRequest<Key, Value>> it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                pendingRequest = null;
                break;
            }
            pendingRequest = it.next();
            if (pendingRequest.getLoadType() == loadType) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        PendingRequest<Key, Value> pendingRequest2 = pendingRequest;
        if (pendingRequest2 != null) {
            pendingRequest2.setPagingState(pagingState);
            return false;
        }
        BlockState blockState = this.f1469a[loadType.ordinal()];
        if (blockState == BlockState.REQUIRES_REFRESH && loadType != LoadType.REFRESH) {
            this.c.add(new PendingRequest<>(loadType, pagingState));
            return false;
        } else if (blockState == BlockState.UNBLOCKED || loadType == LoadType.REFRESH) {
            LoadType loadType2 = LoadType.REFRESH;
            if (loadType == loadType2) {
                j(loadType2, null);
            }
            if (this.b[loadType.ordinal()] == null) {
                return this.c.add(new PendingRequest<>(loadType, pagingState));
            }
            return false;
        } else {
            return false;
        }
    }

    public final void b() {
        int length = this.b.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            this.b[i] = null;
            if (i2 > length) {
                return;
            }
            i = i2;
        }
    }

    public final void c(@NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        i.removeAll((List) this.c, (Function1) new a(loadType));
    }

    public final void d() {
        this.c.clear();
    }

    @NotNull
    public final LoadStates e() {
        return new LoadStates(f(LoadType.REFRESH), f(LoadType.PREPEND), f(LoadType.APPEND));
    }

    public final LoadState f(LoadType loadType) {
        boolean z;
        BlockState blockState = this.f1469a[loadType.ordinal()];
        ArrayDeque<PendingRequest<Key, Value>> arrayDeque = this.c;
        boolean z2 = false;
        if (!(arrayDeque instanceof Collection) || !arrayDeque.isEmpty()) {
            Iterator<PendingRequest<Key, Value>> it = arrayDeque.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().getLoadType() == loadType) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    z2 = true;
                    break;
                }
            }
        }
        if (z2 && blockState != BlockState.REQUIRES_REFRESH) {
            return LoadState.Loading.INSTANCE;
        }
        LoadState.Error error = this.b[loadType.ordinal()];
        if (error == null) {
            int i = WhenMappings.$EnumSwitchMapping$0[blockState.ordinal()];
            if (i == 1) {
                if (WhenMappings.$EnumSwitchMapping$1[loadType.ordinal()] == 1) {
                    return LoadState.NotLoading.Companion.getIncomplete$paging_common();
                }
                return LoadState.NotLoading.Companion.getComplete$paging_common();
            } else if (i != 2) {
                if (i == 3) {
                    return LoadState.NotLoading.Companion.getIncomplete$paging_common();
                }
                throw new NoWhenBranchMatchedException();
            } else {
                return LoadState.NotLoading.Companion.getIncomplete$paging_common();
            }
        }
        return error;
    }

    @Nullable
    public final Pair<LoadType, PagingState<Key, Value>> g() {
        PendingRequest<Key, Value> pendingRequest;
        boolean z;
        Iterator<PendingRequest<Key, Value>> it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                pendingRequest = null;
                break;
            }
            pendingRequest = it.next();
            PendingRequest<Key, Value> pendingRequest2 = pendingRequest;
            if (pendingRequest2.getLoadType() == LoadType.REFRESH || this.f1469a[pendingRequest2.getLoadType().ordinal()] != BlockState.UNBLOCKED) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                break;
            }
        }
        PendingRequest<Key, Value> pendingRequest3 = pendingRequest;
        if (pendingRequest3 == null) {
            return null;
        }
        return TuplesKt.to(pendingRequest3.getLoadType(), pendingRequest3.getPagingState());
    }

    @Nullable
    public final PagingState<Key, Value> h() {
        PendingRequest<Key, Value> pendingRequest;
        boolean z;
        Iterator<PendingRequest<Key, Value>> it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                pendingRequest = null;
                break;
            }
            pendingRequest = it.next();
            if (pendingRequest.getLoadType() == LoadType.REFRESH) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        PendingRequest<Key, Value> pendingRequest2 = pendingRequest;
        if (pendingRequest2 == null) {
            return null;
        }
        return pendingRequest2.getPagingState();
    }

    public final void i(@NotNull LoadType loadType, @NotNull BlockState state) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(state, "state");
        this.f1469a[loadType.ordinal()] = state;
    }

    public final void j(@NotNull LoadType loadType, @Nullable LoadState.Error error) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        this.b[loadType.ordinal()] = error;
    }
}
