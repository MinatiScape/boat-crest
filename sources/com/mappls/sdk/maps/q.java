package com.mappls.sdk.maps;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.annotations.BaseMarkerOptions;
import com.mappls.sdk.maps.annotations.Marker;
import java.util.List;
/* loaded from: classes11.dex */
public interface q {
    List<Marker> a();

    @NonNull
    List<Marker> b(@NonNull RectF rectF);

    void c();

    List<Marker> d(@NonNull List<? extends BaseMarkerOptions> list, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener);

    void e(@NonNull Marker marker, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener);

    Marker f(@NonNull BaseMarkerOptions baseMarkerOptions, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener);
}
