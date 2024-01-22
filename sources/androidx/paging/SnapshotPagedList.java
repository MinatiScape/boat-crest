package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0015\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0005\u001a\u00020\u0004H\u0016J\"\u0010\n\u001a\u00020\u00042\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u001c\u0010\u0011\u001a\u00020\u000e8\u0016@\u0016X\u0096D¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\u00020\u000e8\u0016@\u0016X\u0096D¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Landroidx/paging/SnapshotPagedList;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PagedList;", "", "detach", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "callback", "dispatchCurrentLoadState", "", FirebaseAnalytics.Param.INDEX, "loadAroundInternal", "", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "Z", "isImmutable", "()Z", "s", "isDetached", "getLastKey", "()Ljava/lang/Object;", "lastKey", "pagedList", "<init>", "(Landroidx/paging/PagedList;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class SnapshotPagedList<T> extends PagedList<T> {
    @NotNull
    public final PagedList<T> q;
    public final boolean r;
    public final boolean s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotPagedList(@NotNull PagedList<T> pagedList) {
        super(pagedList.getPagingSource(), pagedList.getCoroutineScope$paging_common(), pagedList.getNotifyDispatcher$paging_common(), pagedList.getStorage$paging_common().snapshot(), pagedList.getConfig());
        Intrinsics.checkNotNullParameter(pagedList, "pagedList");
        this.q = pagedList;
        this.r = true;
        this.s = true;
    }

    @Override // androidx.paging.PagedList
    public void detach() {
    }

    @Override // androidx.paging.PagedList
    public void dispatchCurrentLoadState(@NotNull Function2<? super LoadType, ? super LoadState, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    @Override // androidx.paging.PagedList
    @Nullable
    public Object getLastKey() {
        return this.q.getLastKey();
    }

    @Override // androidx.paging.PagedList
    public boolean isDetached() {
        return this.s;
    }

    @Override // androidx.paging.PagedList
    public boolean isImmutable() {
        return this.r;
    }

    @Override // androidx.paging.PagedList
    public void loadAroundInternal(int i) {
    }
}
