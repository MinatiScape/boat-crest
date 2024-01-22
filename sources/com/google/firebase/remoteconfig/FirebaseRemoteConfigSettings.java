package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
/* loaded from: classes10.dex */
public class FirebaseRemoteConfigSettings {

    /* renamed from: a  reason: collision with root package name */
    public final long f11479a;
    public final long b;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f11480a = 60;
        public long b = ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS;

        @NonNull
        public FirebaseRemoteConfigSettings build() {
            return new FirebaseRemoteConfigSettings(this);
        }

        public long getFetchTimeoutInSeconds() {
            return this.f11480a;
        }

        public long getMinimumFetchIntervalInSeconds() {
            return this.b;
        }

        @NonNull
        public Builder setFetchTimeoutInSeconds(long j) throws IllegalArgumentException {
            if (j >= 0) {
                this.f11480a = j;
                return this;
            }
            throw new IllegalArgumentException(String.format("Fetch connection timeout has to be a non-negative number. %d is an invalid argument", Long.valueOf(j)));
        }

        @NonNull
        public Builder setMinimumFetchIntervalInSeconds(long j) {
            if (j >= 0) {
                this.b = j;
                return this;
            }
            throw new IllegalArgumentException("Minimum interval between fetches has to be a non-negative number. " + j + " is an invalid argument");
        }
    }

    public long getFetchTimeoutInSeconds() {
        return this.f11479a;
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.b;
    }

    @NonNull
    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.setFetchTimeoutInSeconds(getFetchTimeoutInSeconds());
        builder.setMinimumFetchIntervalInSeconds(getMinimumFetchIntervalInSeconds());
        return builder;
    }

    public FirebaseRemoteConfigSettings(Builder builder) {
        this.f11479a = builder.f11480a;
        this.b = builder.b;
    }
}
