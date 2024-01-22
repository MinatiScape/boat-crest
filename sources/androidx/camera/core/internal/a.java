package androidx.camera.core.internal;
/* loaded from: classes.dex */
public final class a extends ImmutableZoomState {

    /* renamed from: a  reason: collision with root package name */
    public final float f756a;
    public final float b;
    public final float c;
    public final float d;

    public a(float f, float f2, float f3, float f4) {
        this.f756a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImmutableZoomState) {
            ImmutableZoomState immutableZoomState = (ImmutableZoomState) obj;
            return Float.floatToIntBits(this.f756a) == Float.floatToIntBits(immutableZoomState.getZoomRatio()) && Float.floatToIntBits(this.b) == Float.floatToIntBits(immutableZoomState.getMaxZoomRatio()) && Float.floatToIntBits(this.c) == Float.floatToIntBits(immutableZoomState.getMinZoomRatio()) && Float.floatToIntBits(this.d) == Float.floatToIntBits(immutableZoomState.getLinearZoom());
        }
        return false;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getLinearZoom() {
        return this.d;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getMaxZoomRatio() {
        return this.b;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getMinZoomRatio() {
        return this.c;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getZoomRatio() {
        return this.f756a;
    }

    public int hashCode() {
        return ((((((Float.floatToIntBits(this.f756a) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.b)) * 1000003) ^ Float.floatToIntBits(this.c)) * 1000003) ^ Float.floatToIntBits(this.d);
    }

    public String toString() {
        return "ImmutableZoomState{zoomRatio=" + this.f756a + ", maxZoomRatio=" + this.b + ", minZoomRatio=" + this.c + ", linearZoom=" + this.d + "}";
    }
}
