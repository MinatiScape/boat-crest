package com.mappls.sdk.maps.auth;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.auth.MapplsVectorKey;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsVectorKey {

    /* renamed from: a  reason: collision with root package name */
    public final String f12680a;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsVectorKey.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12681a;

        @Override // com.mappls.sdk.maps.auth.MapplsVectorKey.Builder
        public MapplsVectorKey a() {
            String str = "";
            if (this.f12681a == null) {
                str = " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f12681a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        public MapplsVectorKey.Builder b(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f12681a = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.auth.MapplsVectorKey, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f12680a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsVectorKey) {
            return this.f12680a.equals(((MapplsVectorKey) obj).baseUrl());
        }
        return false;
    }

    public int hashCode() {
        return this.f12680a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "MapplsVectorKey{baseUrl=" + this.f12680a + "}";
    }

    public a(String str) {
        this.f12680a = str;
    }
}
