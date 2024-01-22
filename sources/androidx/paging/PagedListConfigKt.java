package androidx.paging;

import androidx.paging.PagedList;
import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\b\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0000¨\u0006\t"}, d2 = {"", "pageSize", "prefetchDistance", "", "enablePlaceholders", "initialLoadSizeHint", "maxSize", "Landroidx/paging/PagedList$Config;", "Config", "paging-common"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PagedListConfigKt {
    public static final /* synthetic */ PagedList.Config Config(int i, int i2, boolean z, int i3, int i4) {
        return new PagedList.Config.Builder().setPageSize(i).setPrefetchDistance(i2).setEnablePlaceholders(z).setInitialLoadSizeHint(i3).setMaxSize(i4).build();
    }

    public static /* synthetic */ PagedList.Config Config$default(int i, int i2, boolean z, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = i;
        }
        if ((i5 & 4) != 0) {
            z = true;
        }
        if ((i5 & 8) != 0) {
            i3 = i * 3;
        }
        if ((i5 & 16) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return Config(i, i2, z, i3, i4);
    }
}
