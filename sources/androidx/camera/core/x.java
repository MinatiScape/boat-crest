package androidx.camera.core;

import android.graphics.Rect;
import android.media.Image;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageProxy;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class x implements ImageProxy {
    @GuardedBy("this")
    public final ImageProxy h;
    @GuardedBy("this")
    public final Set<a> i = new HashSet();

    /* loaded from: classes.dex */
    public interface a {
        void a(ImageProxy imageProxy);
    }

    public x(ImageProxy imageProxy) {
        this.h = imageProxy;
    }

    public synchronized void a(a aVar) {
        this.i.add(aVar);
    }

    public void b() {
        HashSet<a> hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.i);
        }
        for (a aVar : hashSet) {
            aVar.a(this);
        }
    }

    @Override // androidx.camera.core.ImageProxy, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            this.h.close();
        }
        b();
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
        return this.h.getImage();
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public synchronized ImageInfo getImageInfo() {
        return this.h.getImageInfo();
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public synchronized ImageProxy.PlaneProxy[] getPlanes() {
        return this.h.getPlanes();
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
