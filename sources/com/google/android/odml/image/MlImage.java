package com.google.android.odml.image;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class MlImage implements Closeable {
    public static final int IMAGE_FORMAT_ALPHA = 8;
    public static final int IMAGE_FORMAT_JPEG = 9;
    public static final int IMAGE_FORMAT_NV12 = 3;
    public static final int IMAGE_FORMAT_NV21 = 4;
    public static final int IMAGE_FORMAT_RGB = 2;
    public static final int IMAGE_FORMAT_RGBA = 1;
    public static final int IMAGE_FORMAT_UNKNOWN = 0;
    public static final int IMAGE_FORMAT_YUV_420_888 = 7;
    public static final int IMAGE_FORMAT_YV12 = 5;
    public static final int IMAGE_FORMAT_YV21 = 6;
    public static final int STORAGE_TYPE_BITMAP = 1;
    public static final int STORAGE_TYPE_BYTEBUFFER = 2;
    public static final int STORAGE_TYPE_MEDIA_IMAGE = 3;
    public final g h;
    public final int i;
    public final Rect j;
    public final int k;
    public final int l;
    public int m;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface ImageFormat {
    }

    /* loaded from: classes10.dex */
    public static final class Internal {

        /* renamed from: a  reason: collision with root package name */
        public final MlImage f10442a;

        public /* synthetic */ Internal(MlImage mlImage, j jVar) {
            this.f10442a = mlImage;
        }

        public void acquire() {
            this.f10442a.d();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface StorageType {
    }

    public MlImage(g gVar, int i, Rect rect, long j, int i2, int i3) {
        this.h = gVar;
        this.i = i;
        Rect rect2 = new Rect();
        this.j = rect2;
        rect2.set(rect);
        this.k = i2;
        this.l = i3;
        this.m = 1;
    }

    public static void c(int i) {
        if (i == 0 || i == 90 || i == 180 || i == 270) {
            return;
        }
        StringBuilder sb = new StringBuilder(68);
        sb.append("Rotation value ");
        sb.append(i);
        sb.append(" is not valid. Use only 0, 90, 180 or 270.");
        throw new IllegalArgumentException(sb.toString());
    }

    public final g a() {
        return this.h;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        int i = this.m - 1;
        this.m = i;
        if (i == 0) {
            this.h.zzc();
        }
    }

    public final synchronized void d() {
        this.m++;
    }

    @NonNull
    public List<ImageProperties> getContainedImageProperties() {
        return Collections.singletonList(this.h.zzb());
    }

    public int getHeight() {
        return this.l;
    }

    @NonNull
    public Internal getInternal() {
        return new Internal(this, null);
    }

    public int getRotation() {
        return this.i;
    }

    public int getWidth() {
        return this.k;
    }
}
