package com.mappls.sdk.navigation.ui.map.route;

import android.location.Location;
import com.mappls.sdk.navigation.camera.ProgressChangeListener;
import com.mappls.sdk.navigation.camera.RouteInformation;
/* loaded from: classes11.dex */
public class q implements ProgressChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final f f12995a;

    public q(o oVar, f fVar) {
        this.f12995a = fVar;
    }

    @Override // com.mappls.sdk.navigation.camera.ProgressChangeListener
    public void onProgressChange(Location location, RouteInformation routeInformation) {
        this.f12995a.e(routeInformation);
    }
}
