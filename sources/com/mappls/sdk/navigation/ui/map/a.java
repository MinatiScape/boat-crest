package com.mappls.sdk.navigation.ui.map;

import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final MapplsMap f12972a;
    public final int[] b;
    public int[] c;

    public a(MapView mapView, MapplsMap mapplsMap) {
        this.f12972a = mapplsMap;
        this.b = c(mapView);
    }

    public void a() {
        int[] iArr = this.c;
        if (iArr == null) {
            this.c = null;
            b(this.b);
            return;
        }
        this.c = iArr;
        b(iArr);
    }

    public void b(int[] iArr) {
        this.f12972a.setPadding(iArr[0], iArr[1], iArr[2], iArr[3]);
    }

    public final int[] c(MapView mapView) {
        return new int[]{0, (mapView.getHeight() - (((int) mapView.getContext().getResources().getDimension(R.dimen.summary_bottomsheet_height)) * 4)) - (((int) mapView.getContext().getResources().getDimension(R.dimen.wayname_view_height)) * 2), 0, 0};
    }
}
