package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class e extends DirectionsResponse {
    private final String code;
    private final String message;
    private final List<DirectionsRoute> routes;
    private final String sessionId;
    private final String uuid;
    private final List<DirectionsWaypoint> waypoints;

    /* loaded from: classes11.dex */
    public static class b extends DirectionsResponse.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13190a;
        public String b;
        public List<DirectionsWaypoint> c;
        public List<DirectionsRoute> d;
        public String e;
        public String f;

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public DirectionsResponse autoBuild() {
            String str = "";
            if (this.f13190a == null) {
                str = " code";
            }
            if (this.d == null) {
                str = str + " routes";
            }
            if (str.isEmpty()) {
                return new AutoValue_DirectionsResponse(this.f13190a, this.b, this.c, this.d, this.e, this.f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public DirectionsResponse.Builder code(String str) {
            Objects.requireNonNull(str, "Null code");
            this.f13190a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public DirectionsResponse.Builder message(@Nullable String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public DirectionsResponse.Builder routes(List<DirectionsRoute> list) {
            Objects.requireNonNull(list, "Null routes");
            this.d = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public DirectionsResponse.Builder sessionId(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public DirectionsResponse.Builder uuid(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public DirectionsResponse.Builder waypoints(@Nullable List<DirectionsWaypoint> list) {
            this.c = list;
            return this;
        }

        public b() {
        }

        public b(DirectionsResponse directionsResponse) {
            this.f13190a = directionsResponse.code();
            this.b = directionsResponse.message();
            this.c = directionsResponse.waypoints();
            this.d = directionsResponse.routes();
            this.e = directionsResponse.uuid();
            this.f = directionsResponse.sessionId();
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse.Builder
        public List<DirectionsRoute> routes() {
            List<DirectionsRoute> list = this.d;
            if (list != null) {
                return list;
            }
            throw new IllegalStateException("Property \"routes\" has not been set");
        }
    }

    public e(String str, @Nullable String str2, @Nullable List<DirectionsWaypoint> list, List<DirectionsRoute> list2, @Nullable String str3, @Nullable String str4) {
        Objects.requireNonNull(str, "Null code");
        this.code = str;
        this.message = str2;
        this.waypoints = list;
        Objects.requireNonNull(list2, "Null routes");
        this.routes = list2;
        this.uuid = str3;
        this.sessionId = str4;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse
    @NonNull
    public String code() {
        return this.code;
    }

    public boolean equals(Object obj) {
        String str;
        List<DirectionsWaypoint> list;
        String str2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof DirectionsResponse) {
            DirectionsResponse directionsResponse = (DirectionsResponse) obj;
            if (this.code.equals(directionsResponse.code()) && ((str = this.message) != null ? str.equals(directionsResponse.message()) : directionsResponse.message() == null) && ((list = this.waypoints) != null ? list.equals(directionsResponse.waypoints()) : directionsResponse.waypoints() == null) && this.routes.equals(directionsResponse.routes()) && ((str2 = this.uuid) != null ? str2.equals(directionsResponse.uuid()) : directionsResponse.uuid() == null)) {
                String str3 = this.sessionId;
                if (str3 == null) {
                    if (directionsResponse.sessionId() == null) {
                        return true;
                    }
                } else if (str3.equals(directionsResponse.sessionId())) {
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
        List<DirectionsWaypoint> list = this.waypoints;
        int hashCode3 = (((hashCode2 ^ (list == null ? 0 : list.hashCode())) * 1000003) ^ this.routes.hashCode()) * 1000003;
        String str2 = this.uuid;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.sessionId;
        return hashCode4 ^ (str3 != null ? str3.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse
    @Nullable
    public String message() {
        return this.message;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse
    @NonNull
    public List<DirectionsRoute> routes() {
        return this.routes;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse
    @Nullable
    @SerializedName("sessionId")
    public String sessionId() {
        return this.sessionId;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse
    public DirectionsResponse.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "DirectionsResponse{code=" + this.code + ", message=" + this.message + ", waypoints=" + this.waypoints + ", routes=" + this.routes + ", uuid=" + this.uuid + ", sessionId=" + this.sessionId + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse
    @Nullable
    @SerializedName("routeId")
    public String uuid() {
        return this.uuid;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsResponse
    @Nullable
    public List<DirectionsWaypoint> waypoints() {
        return this.waypoints;
    }
}
