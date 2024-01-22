package com.theartofdev.edmodo.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.theartofdev.edmodo.cropper.b;
import java.lang.ref.WeakReference;
/* loaded from: classes12.dex */
public final class a extends AsyncTask<Void, Void, C0733a> {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference<CropImageView> f13724a;
    public final Bitmap b;
    public final Uri c;
    public final Context d;
    public final float[] e;
    public final int f;
    public final int g;
    public final int h;
    public final boolean i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    public final boolean n;
    public final boolean o;
    public final CropImageView.RequestSizeOptions p;
    public final Uri q;
    public final Bitmap.CompressFormat r;
    public final int s;

    public a(CropImageView cropImageView, Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, int i4, int i5, boolean z2, boolean z3, CropImageView.RequestSizeOptions requestSizeOptions, Uri uri, Bitmap.CompressFormat compressFormat, int i6) {
        this.f13724a = new WeakReference<>(cropImageView);
        this.d = cropImageView.getContext();
        this.b = bitmap;
        this.e = fArr;
        this.c = null;
        this.f = i;
        this.i = z;
        this.j = i2;
        this.k = i3;
        this.l = i4;
        this.m = i5;
        this.n = z2;
        this.o = z3;
        this.p = requestSizeOptions;
        this.q = uri;
        this.r = compressFormat;
        this.s = i6;
        this.g = 0;
        this.h = 0;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public C0733a doInBackground(Void... voidArr) {
        b.a g;
        try {
            if (isCancelled()) {
                return null;
            }
            Uri uri = this.c;
            if (uri != null) {
                g = b.d(this.d, uri, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o);
            } else {
                Bitmap bitmap = this.b;
                if (bitmap != null) {
                    g = b.g(bitmap, this.e, this.f, this.i, this.j, this.k, this.n, this.o);
                } else {
                    return new C0733a((Bitmap) null, 1);
                }
            }
            Bitmap y = b.y(g.f13727a, this.l, this.m, this.p);
            Uri uri2 = this.q;
            if (uri2 == null) {
                return new C0733a(y, g.b);
            }
            b.C(this.d, y, uri2, this.r, this.s);
            if (y != null) {
                y.recycle();
            }
            return new C0733a(this.q, g.b);
        } catch (Exception e) {
            return new C0733a(e, this.q != null);
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: b */
    public void onPostExecute(C0733a c0733a) {
        Bitmap bitmap;
        CropImageView cropImageView;
        if (c0733a != null) {
            boolean z = false;
            if (!isCancelled() && (cropImageView = this.f13724a.get()) != null) {
                z = true;
                cropImageView.i(c0733a);
            }
            if (z || (bitmap = c0733a.f13725a) == null) {
                return;
            }
            bitmap.recycle();
        }
    }

    /* renamed from: com.theartofdev.edmodo.cropper.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static final class C0733a {

        /* renamed from: a  reason: collision with root package name */
        public final Bitmap f13725a;
        public final Uri b;
        public final Exception c;
        public final int d;

        public C0733a(Bitmap bitmap, int i) {
            this.f13725a = bitmap;
            this.b = null;
            this.c = null;
            this.d = i;
        }

        public C0733a(Uri uri, int i) {
            this.f13725a = null;
            this.b = uri;
            this.c = null;
            this.d = i;
        }

        public C0733a(Exception exc, boolean z) {
            this.f13725a = null;
            this.b = null;
            this.c = exc;
            this.d = 1;
        }
    }

    public a(CropImageView cropImageView, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3, CropImageView.RequestSizeOptions requestSizeOptions, Uri uri2, Bitmap.CompressFormat compressFormat, int i8) {
        this.f13724a = new WeakReference<>(cropImageView);
        this.d = cropImageView.getContext();
        this.c = uri;
        this.e = fArr;
        this.f = i;
        this.i = z;
        this.j = i4;
        this.k = i5;
        this.g = i2;
        this.h = i3;
        this.l = i6;
        this.m = i7;
        this.n = z2;
        this.o = z3;
        this.p = requestSizeOptions;
        this.q = uri2;
        this.r = compressFormat;
        this.s = i8;
        this.b = null;
    }
}
