package com.mappls.sdk.maps.promo;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.promo.MapplsPromo;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsPromo {

    /* renamed from: a  reason: collision with root package name */
    public final String f12819a;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsPromo.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12820a;

        @Override // com.mappls.sdk.maps.promo.MapplsPromo.Builder
        public MapplsPromo a() {
            String str = "";
            if (this.f12820a == null) {
                str = " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f12820a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        public MapplsPromo.Builder b(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f12820a = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.promo.MapplsPromo, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f12819a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsPromo) {
            return this.f12819a.equals(((MapplsPromo) obj).baseUrl());
        }
        return false;
    }

    public int hashCode() {
        return this.f12819a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "MapplsPromo{baseUrl=" + this.f12819a + "}";
    }

    public a(String str) {
        this.f12819a = str;
    }
}
