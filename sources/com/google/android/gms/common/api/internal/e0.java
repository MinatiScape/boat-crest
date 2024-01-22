package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes6.dex */
public final class e0 {

    /* renamed from: a  reason: collision with root package name */
    public final ApiKey f8275a;
    public final Feature b;

    public /* synthetic */ e0(ApiKey apiKey, Feature feature, zabr zabrVar) {
        this.f8275a = apiKey;
        this.b = feature;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != null && (obj instanceof e0)) {
            e0 e0Var = (e0) obj;
            if (Objects.equal(this.f8275a, e0Var.f8275a) && Objects.equal(this.b, e0Var.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f8275a, this.b);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(Constants.KEY_KEY, this.f8275a).add("feature", this.b).toString();
    }
}
