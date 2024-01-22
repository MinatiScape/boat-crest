package androidx.camera.core;

import androidx.annotation.GuardedBy;
/* loaded from: classes.dex */
public final class c2 extends x {
    @GuardedBy("this")
    public boolean j;

    public c2(ImageProxy imageProxy) {
        super(imageProxy);
        this.j = false;
    }

    @Override // androidx.camera.core.x, androidx.camera.core.ImageProxy, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.j) {
            this.j = true;
            super.close();
        }
    }
}
