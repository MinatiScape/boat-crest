package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.Util;
/* loaded from: classes2.dex */
public class b implements e {

    /* renamed from: a  reason: collision with root package name */
    public final C0214b f2358a = new C0214b();
    public final d<a, Bitmap> b = new d<>();

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class a implements f {

        /* renamed from: a  reason: collision with root package name */
        public final C0214b f2359a;
        public int b;
        public int c;
        public Bitmap.Config d;

        public a(C0214b c0214b) {
            this.f2359a = c0214b;
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.f
        public void a() {
            this.f2359a.c(this);
        }

        public void b(int i, int i2, Bitmap.Config config) {
            this.b = i;
            this.c = i2;
            this.d = config;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.b == aVar.b && this.c == aVar.c && this.d == aVar.d;
            }
            return false;
        }

        public int hashCode() {
            int i = ((this.b * 31) + this.c) * 31;
            Bitmap.Config config = this.d;
            return i + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return b.a(this.b, this.c, this.d);
        }
    }

    @VisibleForTesting
    /* renamed from: com.bumptech.glide.load.engine.bitmap_recycle.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0214b extends c<a> {
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.c
        /* renamed from: d */
        public a a() {
            return new a(this);
        }

        public a e(int i, int i2, Bitmap.Config config) {
            a b = b();
            b.b(i, i2, config);
            return b;
        }
    }

    public static String a(int i, int i2, Bitmap.Config config) {
        return "[" + i + "x" + i2 + "], " + config;
    }

    public static String b(Bitmap bitmap) {
        return a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        return this.b.a(this.f2358a.e(i, i2, config));
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public String logBitmap(Bitmap bitmap) {
        return b(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public void put(Bitmap bitmap) {
        this.b.d(this.f2358a.e(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public Bitmap removeLast() {
        return this.b.f();
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.b;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public String logBitmap(int i, int i2, Bitmap.Config config) {
        return a(i, i2, config);
    }
}
