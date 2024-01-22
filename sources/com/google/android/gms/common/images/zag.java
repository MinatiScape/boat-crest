package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
/* loaded from: classes6.dex */
public abstract class zag {

    /* renamed from: a  reason: collision with root package name */
    public final d f8322a;
    public int zab;

    public zag(Uri uri, int i) {
        this.zab = 0;
        this.f8322a = new d(uri);
        this.zab = i;
    }

    public final void a(Context context, zam zamVar, boolean z) {
        int i = this.zab;
        zaa(i != 0 ? context.getResources().getDrawable(i) : null, z, false, false);
    }

    public final void b(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), false, false, true);
    }

    public abstract void zaa(@Nullable Drawable drawable, boolean z, boolean z2, boolean z3);
}
