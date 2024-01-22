package com.google.firebase.remoteconfig.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
/* loaded from: classes10.dex */
public class FirebaseRemoteConfigInfoImpl implements FirebaseRemoteConfigInfo {

    /* renamed from: a  reason: collision with root package name */
    public final long f11500a;
    public final int b;
    public final FirebaseRemoteConfigSettings c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f11501a;
        public int b;
        public FirebaseRemoteConfigSettings c;

        public Builder a(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
            this.c = firebaseRemoteConfigSettings;
            return this;
        }

        public Builder b(int i) {
            this.b = i;
            return this;
        }

        public FirebaseRemoteConfigInfoImpl build() {
            return new FirebaseRemoteConfigInfoImpl(this.f11501a, this.b, this.c);
        }

        public Builder withLastSuccessfulFetchTimeInMillis(long j) {
            this.f11501a = j;
            return this;
        }

        public Builder() {
        }
    }

    public static Builder a() {
        return new Builder();
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public FirebaseRemoteConfigSettings getConfigSettings() {
        return this.c;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public long getFetchTimeMillis() {
        return this.f11500a;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public int getLastFetchStatus() {
        return this.b;
    }

    public FirebaseRemoteConfigInfoImpl(long j, int i, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.f11500a = j;
        this.b = i;
        this.c = firebaseRemoteConfigSettings;
    }
}
