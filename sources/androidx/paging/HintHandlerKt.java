package androidx.paging;

import androidx.paging.ViewportHint;
import com.coveiot.android.leonardo.utils.MusicConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001e\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¨\u0006\u0006"}, d2 = {"Landroidx/paging/ViewportHint;", MusicConstants.CMDPREVIOUS, "Landroidx/paging/LoadType;", "loadType", "", "shouldPrioritizeOver", "paging-common"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class HintHandlerKt {
    public static final boolean shouldPrioritizeOver(@NotNull ViewportHint viewportHint, @Nullable ViewportHint viewportHint2, @NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(viewportHint, "<this>");
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        if (viewportHint2 != null && (!(viewportHint2 instanceof ViewportHint.Initial) || !(viewportHint instanceof ViewportHint.Access))) {
            if ((viewportHint instanceof ViewportHint.Initial) && (viewportHint2 instanceof ViewportHint.Access)) {
                return false;
            }
            if (viewportHint.getOriginalPageOffsetFirst() == viewportHint2.getOriginalPageOffsetFirst() && viewportHint.getOriginalPageOffsetLast() == viewportHint2.getOriginalPageOffsetLast() && viewportHint2.presentedItemsBeyondAnchor$paging_common(loadType) <= viewportHint.presentedItemsBeyondAnchor$paging_common(loadType)) {
                return false;
            }
        }
        return true;
    }
}
