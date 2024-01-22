package com.google.android.gms.common.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
@KeepForSdk
/* loaded from: classes6.dex */
public class TelemetryLoggingOptions implements Api.ApiOptions.Optional {
    @NonNull
    public static final TelemetryLoggingOptions zaa = builder().build();
    @Nullable
    public final String h;

    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public String f8333a;

        public Builder() {
        }

        public /* synthetic */ Builder(zaac zaacVar) {
        }

        @NonNull
        @KeepForSdk
        public TelemetryLoggingOptions build() {
            return new TelemetryLoggingOptions(this.f8333a, null);
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder setApi(@Nullable String str) {
            this.f8333a = str;
            return this;
        }
    }

    public /* synthetic */ TelemetryLoggingOptions(String str, zaad zaadVar) {
        this.h = str;
    }

    @NonNull
    @KeepForSdk
    public static Builder builder() {
        return new Builder(null);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TelemetryLoggingOptions) {
            return Objects.equal(this.h, ((TelemetryLoggingOptions) obj).h);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    @NonNull
    public final Bundle zaa() {
        Bundle bundle = new Bundle();
        String str = this.h;
        if (str != null) {
            bundle.putString("api", str);
        }
        return bundle;
    }
}
