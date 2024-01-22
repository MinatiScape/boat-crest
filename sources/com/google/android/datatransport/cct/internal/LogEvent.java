package com.google.android.datatransport.cct.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.d;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class LogEvent {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        @NonNull
        public abstract Builder a(@Nullable byte[] bArr);

        @NonNull
        public abstract Builder b(@Nullable String str);

        @NonNull
        public abstract LogEvent build();

        @NonNull
        public abstract Builder setEventCode(@Nullable Integer num);

        @NonNull
        public abstract Builder setEventTimeMs(long j);

        @NonNull
        public abstract Builder setEventUptimeMs(long j);

        @NonNull
        public abstract Builder setNetworkConnectionInfo(@Nullable NetworkConnectionInfo networkConnectionInfo);

        @NonNull
        public abstract Builder setTimezoneOffsetSeconds(long j);
    }

    public static Builder a() {
        return new d.b();
    }

    @NonNull
    public static Builder jsonBuilder(@NonNull String str) {
        return a().b(str);
    }

    @NonNull
    public static Builder protoBuilder(@NonNull byte[] bArr) {
        return a().a(bArr);
    }

    @Nullable
    public abstract Integer getEventCode();

    public abstract long getEventTimeMs();

    public abstract long getEventUptimeMs();

    @Nullable
    public abstract NetworkConnectionInfo getNetworkConnectionInfo();

    @Nullable
    public abstract byte[] getSourceExtension();

    @Nullable
    public abstract String getSourceExtensionJsonProto3();

    public abstract long getTimezoneOffsetSeconds();
}
