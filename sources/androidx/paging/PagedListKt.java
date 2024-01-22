package androidx.paging;

import androidx.paging.PagedList;
import com.clevertap.android.sdk.Constants;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001as\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\r\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"", "Key", "Value", "Landroidx/paging/DataSource;", "dataSource", "Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "Ljava/util/concurrent/Executor;", "notifyExecutor", "fetchExecutor", "Landroidx/paging/PagedList$BoundaryCallback;", "boundaryCallback", "initialKey", "Landroidx/paging/PagedList;", "PagedList", "(Landroidx/paging/DataSource;Landroidx/paging/PagedList$Config;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Landroidx/paging/PagedList$BoundaryCallback;Ljava/lang/Object;)Landroidx/paging/PagedList;", "paging-common"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PagedListKt {
    @Deprecated(message = "DataSource is deprecated and has been replaced by PagingSource")
    public static final /* synthetic */ PagedList PagedList(DataSource dataSource, PagedList.Config config, Executor notifyExecutor, Executor fetchExecutor, PagedList.BoundaryCallback boundaryCallback, Object obj) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(notifyExecutor, "notifyExecutor");
        Intrinsics.checkNotNullParameter(fetchExecutor, "fetchExecutor");
        return new PagedList.Builder(dataSource, config).setNotifyExecutor(notifyExecutor).setFetchExecutor(fetchExecutor).setBoundaryCallback(boundaryCallback).setInitialKey(obj).build();
    }
}
