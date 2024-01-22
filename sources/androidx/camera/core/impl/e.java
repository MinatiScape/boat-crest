package androidx.camera.core.impl;

import android.util.Size;
import java.util.Objects;
/* loaded from: classes.dex */
public final class e extends SurfaceSizeDefinition {

    /* renamed from: a  reason: collision with root package name */
    public final Size f724a;
    public final Size b;
    public final Size c;

    public e(Size size, Size size2, Size size3) {
        Objects.requireNonNull(size, "Null analysisSize");
        this.f724a = size;
        Objects.requireNonNull(size2, "Null previewSize");
        this.b = size2;
        Objects.requireNonNull(size3, "Null recordSize");
        this.c = size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SurfaceSizeDefinition) {
            SurfaceSizeDefinition surfaceSizeDefinition = (SurfaceSizeDefinition) obj;
            return this.f724a.equals(surfaceSizeDefinition.getAnalysisSize()) && this.b.equals(surfaceSizeDefinition.getPreviewSize()) && this.c.equals(surfaceSizeDefinition.getRecordSize());
        }
        return false;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getAnalysisSize() {
        return this.f724a;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getPreviewSize() {
        return this.b;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getRecordSize() {
        return this.c;
    }

    public int hashCode() {
        return ((((this.f724a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public String toString() {
        return "SurfaceSizeDefinition{analysisSize=" + this.f724a + ", previewSize=" + this.b + ", recordSize=" + this.c + "}";
    }
}
