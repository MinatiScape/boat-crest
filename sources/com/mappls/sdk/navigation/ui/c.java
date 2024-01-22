package com.mappls.sdk.navigation.ui;

import android.location.Location;
import com.mappls.sdk.navigation.camera.ProgressChangeListener;
import com.mappls.sdk.navigation.camera.RouteInformation;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public CopyOnWriteArrayList<ProgressChangeListener> f12969a = new CopyOnWriteArrayList<>();

    public void a(Location location, RouteInformation routeInformation) {
        Iterator<ProgressChangeListener> it = this.f12969a.iterator();
        while (it.hasNext()) {
            it.next().onProgressChange(location, routeInformation);
        }
    }

    public void a(ProgressChangeListener progressChangeListener) {
        if (this.f12969a.contains(progressChangeListener)) {
            return;
        }
        this.f12969a.add(progressChangeListener);
    }

    public void b(ProgressChangeListener progressChangeListener) {
        if (progressChangeListener == null) {
            this.f12969a.clear();
        } else if (this.f12969a.contains(progressChangeListener)) {
            this.f12969a.remove(progressChangeListener);
        }
    }
}
