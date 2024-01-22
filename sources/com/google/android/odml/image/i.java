package com.google.android.odml.image;

import android.media.Image;
import android.os.Build;
import androidx.annotation.RequiresApi;
@RequiresApi(19)
/* loaded from: classes10.dex */
public final class i implements g {

    /* renamed from: a  reason: collision with root package name */
    public final Image f10448a;
    public final ImageProperties b;

    public i(Image image) {
        int i;
        this.f10448a = image;
        b bVar = new b();
        bVar.b(3);
        int format = image.getFormat();
        if (Build.VERSION.SDK_INT >= 23) {
            if (format == 42) {
                i = 1;
            } else if (format == 41) {
                i = 2;
            }
            bVar.a(i);
            this.b = bVar.c();
        }
        i = format != 35 ? format != 256 ? 0 : 9 : 7;
        bVar.a(i);
        this.b = bVar.c();
    }

    public final Image a() {
        return this.f10448a;
    }

    @Override // com.google.android.odml.image.g
    public final ImageProperties zzb() {
        return this.b;
    }

    @Override // com.google.android.odml.image.g
    public final void zzc() {
        this.f10448a.close();
    }
}
