package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.TagBundle;
import java.util.Objects;
/* loaded from: classes.dex */
public final class e extends k1 {

    /* renamed from: a  reason: collision with root package name */
    public final TagBundle f681a;
    public final long b;
    public final int c;

    public e(TagBundle tagBundle, long j, int i) {
        Objects.requireNonNull(tagBundle, "Null tagBundle");
        this.f681a = tagBundle;
        this.b = j;
        this.c = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof k1) {
            k1 k1Var = (k1) obj;
            return this.f681a.equals(k1Var.getTagBundle()) && this.b == k1Var.getTimestamp() && this.c == k1Var.getRotationDegrees();
        }
        return false;
    }

    @Override // androidx.camera.core.k1, androidx.camera.core.ImageInfo
    public int getRotationDegrees() {
        return this.c;
    }

    @Override // androidx.camera.core.k1, androidx.camera.core.ImageInfo
    @NonNull
    public TagBundle getTagBundle() {
        return this.f681a;
    }

    @Override // androidx.camera.core.k1, androidx.camera.core.ImageInfo
    public long getTimestamp() {
        return this.b;
    }

    public int hashCode() {
        long j = this.b;
        return ((((this.f681a.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.c;
    }

    public String toString() {
        return "ImmutableImageInfo{tagBundle=" + this.f681a + ", timestamp=" + this.b + ", rotationDegrees=" + this.c + "}";
    }
}
