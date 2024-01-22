package com.google.mlkit.vision.common;

import android.media.Image;
/* loaded from: classes10.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final Image f11636a;

    public b(Image image) {
        this.f11636a = image;
    }

    public final Image a() {
        return this.f11636a;
    }

    public final Image.Plane[] b() {
        return this.f11636a.getPlanes();
    }
}
