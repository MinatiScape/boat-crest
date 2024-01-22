package androidx.camera.core;

import android.graphics.Rect;
import android.media.Image;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.TagBundle;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public final class a implements ImageProxy {
    @GuardedBy("this")
    public final Image h;
    @GuardedBy("this")
    public final C0113a[] i;
    public final ImageInfo j;

    /* renamed from: androidx.camera.core.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0113a implements ImageProxy.PlaneProxy {
        @GuardedBy("this")

        /* renamed from: a  reason: collision with root package name */
        public final Image.Plane f669a;

        public C0113a(Image.Plane plane) {
            this.f669a = plane;
        }

        @Override // androidx.camera.core.ImageProxy.PlaneProxy
        @NonNull
        public synchronized ByteBuffer getBuffer() {
            return this.f669a.getBuffer();
        }

        @Override // androidx.camera.core.ImageProxy.PlaneProxy
        public synchronized int getPixelStride() {
            return this.f669a.getPixelStride();
        }

        @Override // androidx.camera.core.ImageProxy.PlaneProxy
        public synchronized int getRowStride() {
            return this.f669a.getRowStride();
        }
    }

    public a(Image image) {
        this.h = image;
        Image.Plane[] planes = image.getPlanes();
        if (planes != null) {
            this.i = new C0113a[planes.length];
            for (int i = 0; i < planes.length; i++) {
                this.i[i] = new C0113a(planes[i]);
            }
        } else {
            this.i = new C0113a[0];
        }
        this.j = k1.a(TagBundle.emptyBundle(), image.getTimestamp(), 0);
    }

    @Override // androidx.camera.core.ImageProxy, java.lang.AutoCloseable
    public synchronized void close() {
        this.h.close();
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public synchronized Rect getCropRect() {
        return this.h.getCropRect();
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized int getFormat() {
        return this.h.getFormat();
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized int getHeight() {
        return this.h.getHeight();
    }

    @Override // androidx.camera.core.ImageProxy
    @ExperimentalGetImage
    public synchronized Image getImage() {
        return this.h;
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public ImageInfo getImageInfo() {
        return this.j;
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public synchronized ImageProxy.PlaneProxy[] getPlanes() {
        return this.i;
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized int getWidth() {
        return this.h.getWidth();
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized void setCropRect(@Nullable Rect rect) {
        this.h.setCropRect(rect);
    }
}
