package androidx.paging;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0015\u001a\u00020\u000b\u0012\u0006\u0010\u0018\u001a\u00020\u000b\u0012\u0006\u0010\u001b\u001a\u00020\u000b\u0012\u0006\u0010!\u001a\u00020\u001c\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b%\u0010&J\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J/\u0010\u0010\u001a\u00020\f2\u001e\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\tH\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0018\u001a\u00020\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0017\u0010\u0014R\u0019\u0010\u001b\u001a\u00020\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u001a\u0010\u0014R\u0019\u0010!\u001a\u00020\u001c8\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001b\u0010$\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010 ¨\u0006'"}, d2 = {"Landroidx/paging/CombinedLoadStates;", "", FitnessActivities.OTHER, "", "equals", "", "hashCode", "", "toString", "Lkotlin/Function3;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "", "op", "forEach$paging_common", "(Lkotlin/jvm/functions/Function3;)V", "forEach", "a", "Landroidx/paging/LoadState;", "getRefresh", "()Landroidx/paging/LoadState;", "refresh", "b", "getPrepend", "prepend", com.google.android.material.color.c.f10260a, "getAppend", "append", "Landroidx/paging/LoadStates;", "d", "Landroidx/paging/LoadStates;", "getSource", "()Landroidx/paging/LoadStates;", "source", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getMediator", "mediator", "<init>", "(Landroidx/paging/LoadState;Landroidx/paging/LoadState;Landroidx/paging/LoadState;Landroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class CombinedLoadStates {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final LoadState f1477a;
    @NotNull
    public final LoadState b;
    @NotNull
    public final LoadState c;
    @NotNull
    public final LoadStates d;
    @Nullable
    public final LoadStates e;

    public CombinedLoadStates(@NotNull LoadState refresh, @NotNull LoadState prepend, @NotNull LoadState append, @NotNull LoadStates source, @Nullable LoadStates loadStates) {
        Intrinsics.checkNotNullParameter(refresh, "refresh");
        Intrinsics.checkNotNullParameter(prepend, "prepend");
        Intrinsics.checkNotNullParameter(append, "append");
        Intrinsics.checkNotNullParameter(source, "source");
        this.f1477a = refresh;
        this.b = prepend;
        this.c = append;
        this.d = source;
        this.e = loadStates;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(CombinedLoadStates.class, obj == null ? null : obj.getClass())) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type androidx.paging.CombinedLoadStates");
            CombinedLoadStates combinedLoadStates = (CombinedLoadStates) obj;
            return Intrinsics.areEqual(this.f1477a, combinedLoadStates.f1477a) && Intrinsics.areEqual(this.b, combinedLoadStates.b) && Intrinsics.areEqual(this.c, combinedLoadStates.c) && Intrinsics.areEqual(this.d, combinedLoadStates.d) && Intrinsics.areEqual(this.e, combinedLoadStates.e);
        }
        return false;
    }

    public final void forEach$paging_common(@NotNull Function3<? super LoadType, ? super Boolean, ? super LoadState, Unit> op) {
        Intrinsics.checkNotNullParameter(op, "op");
        LoadStates loadStates = this.d;
        LoadType loadType = LoadType.REFRESH;
        LoadState refresh = loadStates.getRefresh();
        Boolean bool = Boolean.FALSE;
        op.invoke(loadType, bool, refresh);
        LoadType loadType2 = LoadType.PREPEND;
        op.invoke(loadType2, bool, loadStates.getPrepend());
        LoadType loadType3 = LoadType.APPEND;
        op.invoke(loadType3, bool, loadStates.getAppend());
        LoadStates loadStates2 = this.e;
        if (loadStates2 == null) {
            return;
        }
        LoadState refresh2 = loadStates2.getRefresh();
        Boolean bool2 = Boolean.TRUE;
        op.invoke(loadType, bool2, refresh2);
        op.invoke(loadType2, bool2, loadStates2.getPrepend());
        op.invoke(loadType3, bool2, loadStates2.getAppend());
    }

    @NotNull
    public final LoadState getAppend() {
        return this.c;
    }

    @Nullable
    public final LoadStates getMediator() {
        return this.e;
    }

    @NotNull
    public final LoadState getPrepend() {
        return this.b;
    }

    @NotNull
    public final LoadState getRefresh() {
        return this.f1477a;
    }

    @NotNull
    public final LoadStates getSource() {
        return this.d;
    }

    public int hashCode() {
        int hashCode = ((((((this.f1477a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31;
        LoadStates loadStates = this.e;
        return hashCode + (loadStates == null ? 0 : loadStates.hashCode());
    }

    @NotNull
    public String toString() {
        return "CombinedLoadStates(refresh=" + this.f1477a + ", prepend=" + this.b + ", append=" + this.c + ", source=" + this.d + ", mediator=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ CombinedLoadStates(LoadState loadState, LoadState loadState2, LoadState loadState3, LoadStates loadStates, LoadStates loadStates2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(loadState, loadState2, loadState3, loadStates, (i & 16) != 0 ? null : loadStates2);
    }
}
