package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.annotations.Polyline;
import com.mappls.sdk.maps.annotations.PolylineOptions;
import java.util.List;
/* loaded from: classes11.dex */
public interface y {
    List<Polyline> a();

    List<Polyline> b(@NonNull List<PolylineOptions> list, @NonNull MapplsMap mapplsMap);

    void c(Polyline polyline);

    Polyline d(@NonNull PolylineOptions polylineOptions, @NonNull MapplsMap mapplsMap);
}
