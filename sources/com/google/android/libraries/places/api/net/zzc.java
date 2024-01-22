package com.google.android.libraries.places.api.net;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzc extends FetchPhotoResponse {
    private final Bitmap zza;

    public zzc(Bitmap bitmap) {
        Objects.requireNonNull(bitmap, "Null bitmap");
        this.zza = bitmap;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FetchPhotoResponse) {
            return this.zza.equals(((FetchPhotoResponse) obj).getBitmap());
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPhotoResponse
    @NonNull
    public final Bitmap getBitmap() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(valueOf.length() + 27);
        sb.append("FetchPhotoResponse{bitmap=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
