package com.google.android.gms.common.images;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes6.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public final Uri f8321a;

    public d(Uri uri) {
        this.f8321a = uri;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            return Objects.equal(((d) obj).f8321a, this.f8321a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f8321a);
    }
}
