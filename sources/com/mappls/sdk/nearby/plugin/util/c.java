package com.mappls.sdk.nearby.plugin.util;

import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
@SourceDebugExtension({"SMAP\nNearbyUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NearbyUtils.kt\ncom/mappls/sdk/nearby/plugin/util/NearbyUtils\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,40:1\n1855#2,2:41\n1855#2,2:43\n*S KotlinDebug\n*F\n+ 1 NearbyUtils.kt\ncom/mappls/sdk/nearby/plugin/util/NearbyUtils\n*L\n15#1:41,2\n23#1:43,2\n*E\n"})
/* loaded from: classes10.dex */
public final class c {
    @NotNull
    public static String a(@NotNull Object[] tokens) {
        Intrinsics.checkNotNullParameter(";", "deliminator");
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        StringBuilder sb = new StringBuilder();
        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append((CharSequence) ";");
            }
            sb.append(tokens[i]);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    @NotNull
    public static ArrayList a(@NotNull List points) {
        Intrinsics.checkNotNullParameter(points, "points");
        ArrayList arrayList = new ArrayList();
        Iterator it = points.iterator();
        while (it.hasNext()) {
            Point point = (Point) it.next();
            arrayList.add(new LatLng(point.latitude(), point.longitude()));
        }
        return arrayList;
    }
}
