package androidx.camera.camera2.internal;

import androidx.camera.core.ZoomState;
import androidx.core.math.MathUtils;
/* loaded from: classes.dex */
public class j2 implements ZoomState {

    /* renamed from: a  reason: collision with root package name */
    public float f570a;
    public final float b;
    public final float c;
    public float d;

    public j2(float f, float f2) {
        this.b = f;
        this.c = f2;
    }

    public final float a(float f) {
        float f2 = this.b;
        float f3 = this.c;
        if (f2 == f3) {
            return 0.0f;
        }
        if (f == f2) {
            return 1.0f;
        }
        if (f == f3) {
            return 0.0f;
        }
        float f4 = 1.0f / f3;
        return ((1.0f / f) - f4) / ((1.0f / f2) - f4);
    }

    public final float b(float f) {
        if (f == 1.0f) {
            return this.b;
        }
        if (f == 0.0f) {
            return this.c;
        }
        float f2 = this.b;
        float f3 = this.c;
        double d = 1.0f / f3;
        return (float) MathUtils.clamp(1.0d / (d + (((1.0f / f2) - d) * f)), f3, f2);
    }

    public void c(float f) throws IllegalArgumentException {
        if (f <= 1.0f && f >= 0.0f) {
            this.d = f;
            this.f570a = b(f);
            return;
        }
        throw new IllegalArgumentException("Requested linearZoom " + f + " is not within valid range [0..1]");
    }

    public void d(float f) throws IllegalArgumentException {
        if (f <= this.b && f >= this.c) {
            this.f570a = f;
            this.d = a(f);
            return;
        }
        throw new IllegalArgumentException("Requested zoomRatio " + f + " is not within valid range [" + this.c + " , " + this.b + "]");
    }

    @Override // androidx.camera.core.ZoomState
    public float getLinearZoom() {
        return this.d;
    }

    @Override // androidx.camera.core.ZoomState
    public float getMaxZoomRatio() {
        return this.b;
    }

    @Override // androidx.camera.core.ZoomState
    public float getMinZoomRatio() {
        return this.c;
    }

    @Override // androidx.camera.core.ZoomState
    public float getZoomRatio() {
        return this.f570a;
    }
}
