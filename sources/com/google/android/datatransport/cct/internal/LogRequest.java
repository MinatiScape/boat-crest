package com.google.android.datatransport.cct.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.e;
import com.google.auto.value.AutoValue;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;
@AutoValue
/* loaded from: classes6.dex */
public abstract class LogRequest {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        @NonNull
        public abstract Builder a(@Nullable Integer num);

        @NonNull
        public abstract Builder b(@Nullable String str);

        @NonNull
        public abstract LogRequest build();

        @NonNull
        public abstract Builder setClientInfo(@Nullable ClientInfo clientInfo);

        @NonNull
        public abstract Builder setLogEvents(@Nullable List<LogEvent> list);

        @NonNull
        public abstract Builder setQosTier(@Nullable QosTier qosTier);

        @NonNull
        public abstract Builder setRequestTimeMs(long j);

        @NonNull
        public abstract Builder setRequestUptimeMs(long j);

        @NonNull
        public Builder setSource(int i) {
            return a(Integer.valueOf(i));
        }

        @NonNull
        public Builder setSource(@NonNull String str) {
            return b(str);
        }
    }

    @NonNull
    public static Builder builder() {
        return new e.b();
    }

    @Nullable
    public abstract ClientInfo getClientInfo();

    @Nullable
    @Encodable.Field(name = "logEvent")
    public abstract List<LogEvent> getLogEvents();

    @Nullable
    public abstract Integer getLogSource();

    @Nullable
    public abstract String getLogSourceName();

    @Nullable
    public abstract QosTier getQosTier();

    public abstract long getRequestTimeMs();

    public abstract long getRequestUptimeMs();
}
