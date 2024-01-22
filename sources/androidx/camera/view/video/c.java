package androidx.camera.view.video;

import android.net.Uri;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class c extends OutputFileResults {

    /* renamed from: a  reason: collision with root package name */
    public final Uri f832a;

    public c(@Nullable Uri uri) {
        this.f832a = uri;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OutputFileResults) {
            Uri uri = this.f832a;
            Uri savedUri = ((OutputFileResults) obj).getSavedUri();
            return uri == null ? savedUri == null : uri.equals(savedUri);
        }
        return false;
    }

    @Override // androidx.camera.view.video.OutputFileResults
    @Nullable
    public Uri getSavedUri() {
        return this.f832a;
    }

    public int hashCode() {
        Uri uri = this.f832a;
        return (uri == null ? 0 : uri.hashCode()) ^ 1000003;
    }

    public String toString() {
        return "OutputFileResults{savedUri=" + this.f832a + "}";
    }
}
