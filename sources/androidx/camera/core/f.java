package androidx.camera.core;

import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.camera.core.SurfaceRequest;
import java.util.Objects;
/* loaded from: classes.dex */
public final class f extends SurfaceRequest.Result {

    /* renamed from: a  reason: collision with root package name */
    public final int f684a;
    public final Surface b;

    public f(int i, Surface surface) {
        this.f684a = i;
        Objects.requireNonNull(surface, "Null surface");
        this.b = surface;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SurfaceRequest.Result) {
            SurfaceRequest.Result result = (SurfaceRequest.Result) obj;
            return this.f684a == result.getResultCode() && this.b.equals(result.getSurface());
        }
        return false;
    }

    @Override // androidx.camera.core.SurfaceRequest.Result
    public int getResultCode() {
        return this.f684a;
    }

    @Override // androidx.camera.core.SurfaceRequest.Result
    @NonNull
    public Surface getSurface() {
        return this.b;
    }

    public int hashCode() {
        return ((this.f684a ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "Result{resultCode=" + this.f684a + ", surface=" + this.b + "}";
    }
}
