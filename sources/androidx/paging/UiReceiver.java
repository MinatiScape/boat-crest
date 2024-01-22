package androidx.paging;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0006\u001a\u00020\u0004H&J\b\u0010\u0007\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Landroidx/paging/UiReceiver;", "", "Landroidx/paging/ViewportHint;", "viewportHint", "", "accessHint", "retry", "refresh", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public interface UiReceiver {
    void accessHint(@NotNull ViewportHint viewportHint);

    void refresh();

    void retry();
}
