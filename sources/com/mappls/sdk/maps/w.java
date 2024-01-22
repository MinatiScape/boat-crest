package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.annotations.Polygon;
import com.mappls.sdk.maps.annotations.PolygonOptions;
import java.util.List;
/* loaded from: classes11.dex */
public interface w {
    List<Polygon> a();

    List<Polygon> b(@NonNull List<PolygonOptions> list, @NonNull MapplsMap mapplsMap);

    void c(Polygon polygon);

    Polygon d(@NonNull PolygonOptions polygonOptions, @NonNull MapplsMap mapplsMap);
}
