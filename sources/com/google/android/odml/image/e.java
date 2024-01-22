package com.google.android.odml.image;

import android.graphics.Bitmap;
/* loaded from: classes10.dex */
public final class e implements g {

    /* renamed from: a  reason: collision with root package name */
    public final Bitmap f10446a;
    public final ImageProperties b;

    public e(Bitmap bitmap) {
        this.f10446a = bitmap;
        b bVar = new b();
        int i = d.f10445a[bitmap.getConfig().ordinal()];
        bVar.a(i != 1 ? i != 2 ? 0 : 1 : 8);
        bVar.b(1);
        this.b = bVar.c();
    }

    public final Bitmap a() {
        return this.f10446a;
    }

    @Override // com.google.android.odml.image.g
    public final ImageProperties zzb() {
        return this.b;
    }

    @Override // com.google.android.odml.image.g
    public final void zzc() {
        this.f10446a.recycle();
    }
}
