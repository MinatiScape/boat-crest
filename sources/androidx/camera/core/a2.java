package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class a2 extends x {
    public final ImageInfo j;
    @Nullable
    public Rect k;
    public final int l;
    public final int m;

    public a2(ImageProxy imageProxy, ImageInfo imageInfo) {
        this(imageProxy, null, imageInfo);
    }

    @Override // androidx.camera.core.x, androidx.camera.core.ImageProxy
    @NonNull
    public synchronized Rect getCropRect() {
        if (this.k == null) {
            return new Rect(0, 0, getWidth(), getHeight());
        }
        return new Rect(this.k);
    }

    @Override // androidx.camera.core.x, androidx.camera.core.ImageProxy
    public synchronized int getHeight() {
        return this.m;
    }

    @Override // androidx.camera.core.x, androidx.camera.core.ImageProxy
    @NonNull
    public ImageInfo getImageInfo() {
        return this.j;
    }

    @Override // androidx.camera.core.x, androidx.camera.core.ImageProxy
    public synchronized int getWidth() {
        return this.l;
    }

    @Override // androidx.camera.core.x, androidx.camera.core.ImageProxy
    public synchronized void setCropRect(@Nullable Rect rect) {
        if (rect != null) {
            Rect rect2 = new Rect(rect);
            if (!rect2.intersect(0, 0, getWidth(), getHeight())) {
                rect2.setEmpty();
            }
            rect = rect2;
        }
        this.k = rect;
    }

    public a2(ImageProxy imageProxy, @Nullable Size size, ImageInfo imageInfo) {
        super(imageProxy);
        if (size == null) {
            this.l = super.getWidth();
            this.m = super.getHeight();
        } else {
            this.l = size.getWidth();
            this.m = size.getHeight();
        }
        this.j = imageInfo;
    }
}
