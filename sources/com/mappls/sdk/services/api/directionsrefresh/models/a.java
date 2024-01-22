package com.mappls.sdk.services.api.directionsrefresh.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class a extends DirectionsRefreshResponse {
    private final String code;
    private final String message;
    private final DirectionsRoute route;

    /* renamed from: com.mappls.sdk.services.api.directionsrefresh.models.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0673a extends DirectionsRefreshResponse.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13208a;
        public String b;
        public DirectionsRoute c;

        @Override // com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse.Builder
        public DirectionsRefreshResponse build() {
            String str = "";
            if (this.f13208a == null) {
                str = " code";
            }
            if (str.isEmpty()) {
                return new AutoValue_DirectionsRefreshResponse(this.f13208a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse.Builder
        public DirectionsRefreshResponse.Builder code(String str) {
            Objects.requireNonNull(str, "Null code");
            this.f13208a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse.Builder
        public DirectionsRefreshResponse.Builder message(String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse.Builder
        public DirectionsRefreshResponse.Builder route(DirectionsRoute directionsRoute) {
            this.c = directionsRoute;
            return this;
        }
    }

    public a(String str, @Nullable String str2, @Nullable DirectionsRoute directionsRoute) {
        Objects.requireNonNull(str, "Null code");
        this.code = str;
        this.message = str2;
        this.route = directionsRoute;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse
    @NonNull
    public String code() {
        return this.code;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof DirectionsRefreshResponse) {
            DirectionsRefreshResponse directionsRefreshResponse = (DirectionsRefreshResponse) obj;
            if (this.code.equals(directionsRefreshResponse.code()) && ((str = this.message) != null ? str.equals(directionsRefreshResponse.message()) : directionsRefreshResponse.message() == null)) {
                DirectionsRoute directionsRoute = this.route;
                if (directionsRoute == null) {
                    if (directionsRefreshResponse.route() == null) {
                        return true;
                    }
                } else if (directionsRoute.equals(directionsRefreshResponse.route())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.code.hashCode() ^ 1000003) * 1000003;
        String str = this.message;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        DirectionsRoute directionsRoute = this.route;
        return hashCode2 ^ (directionsRoute != null ? directionsRoute.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse
    @Nullable
    public String message() {
        return this.message;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse
    @Nullable
    public DirectionsRoute route() {
        return this.route;
    }

    public String toString() {
        return "DirectionsRefreshResponse{code=" + this.code + ", message=" + this.message + ", route=" + this.route + "}";
    }
}
