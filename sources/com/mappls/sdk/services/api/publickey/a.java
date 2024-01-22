package com.mappls.sdk.services.api.publickey;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.publickey.MapplsPublicKey;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class a extends MapplsPublicKey {

    /* renamed from: a  reason: collision with root package name */
    public final String f13261a;

    /* loaded from: classes7.dex */
    public static final class b extends MapplsPublicKey.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13262a;

        @Override // com.mappls.sdk.services.api.publickey.MapplsPublicKey.Builder
        public MapplsPublicKey a() {
            String str = "";
            if (this.f13262a == null) {
                str = " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f13262a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.publickey.MapplsPublicKey.Builder
        public MapplsPublicKey.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13262a = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.publickey.MapplsPublicKey, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13261a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsPublicKey) {
            return this.f13261a.equals(((MapplsPublicKey) obj).baseUrl());
        }
        return false;
    }

    public int hashCode() {
        return this.f13261a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "MapplsPublicKey{baseUrl=" + this.f13261a + "}";
    }

    public a(String str) {
        this.f13261a = str;
    }
}
