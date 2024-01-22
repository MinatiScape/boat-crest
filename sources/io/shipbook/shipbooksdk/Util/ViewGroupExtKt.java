package io.shipbook.shipbooksdk.Util;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.collections.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\" \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Landroid/view/ViewGroup;", "", "Landroid/view/View;", "getViews", "(Landroid/view/ViewGroup;)Ljava/util/List;", "views", "shipbooksdk_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ViewGroupExtKt {
    @NotNull
    public static final List<View> getViews(@NotNull ViewGroup receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        IntRange until = h.until(0, receiver$0.getChildCount());
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(receiver$0.getChildAt(((IntIterator) it).nextInt()));
        }
        return arrayList;
    }
}
