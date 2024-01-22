package androidx.camera.view.video;

import android.location.Location;
import androidx.annotation.Nullable;
import androidx.camera.view.video.Metadata;
/* loaded from: classes.dex */
public final class a extends Metadata {

    /* renamed from: a  reason: collision with root package name */
    public final Location f829a;

    /* loaded from: classes.dex */
    public static final class b extends Metadata.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Location f830a;

        @Override // androidx.camera.view.video.Metadata.Builder
        public Metadata build() {
            return new a(this.f830a);
        }

        @Override // androidx.camera.view.video.Metadata.Builder
        public Metadata.Builder setLocation(@Nullable Location location) {
            this.f830a = location;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Metadata) {
            Location location = this.f829a;
            Location location2 = ((Metadata) obj).getLocation();
            return location == null ? location2 == null : location.equals(location2);
        }
        return false;
    }

    @Override // androidx.camera.view.video.Metadata
    @Nullable
    public Location getLocation() {
        return this.f829a;
    }

    public int hashCode() {
        Location location = this.f829a;
        return (location == null ? 0 : location.hashCode()) ^ 1000003;
    }

    public String toString() {
        return "Metadata{location=" + this.f829a + "}";
    }

    public a(@Nullable Location location) {
        this.f829a = location;
    }
}
