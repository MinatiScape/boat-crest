package com.mappls.sdk.services.api.tripoptimisation.model;

import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse;
import java.util.List;
import java.util.Objects;
/* loaded from: classes8.dex */
public abstract class a extends TripOptimisationResponse {
    private final String code;
    private final List<DirectionsRoute> trips;
    private final List<TripsWaypoint> waypoints;

    /* loaded from: classes8.dex */
    public static class b extends TripOptimisationResponse.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13298a;
        public List<DirectionsRoute> b;
        public List<TripsWaypoint> c;

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse.Builder
        public TripOptimisationResponse build() {
            String str = "";
            if (this.f13298a == null) {
                str = " code";
            }
            if (this.b == null) {
                str = str + " trips";
            }
            if (this.c == null) {
                str = str + " waypoints";
            }
            if (str.isEmpty()) {
                return new AutoValue_TripOptimisationResponse(this.f13298a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse.Builder
        public TripOptimisationResponse.Builder code(String str) {
            Objects.requireNonNull(str, "Null code");
            this.f13298a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse.Builder
        public TripOptimisationResponse.Builder trips(List<DirectionsRoute> list) {
            Objects.requireNonNull(list, "Null trips");
            this.b = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse.Builder
        public TripOptimisationResponse.Builder waypoints(@Nullable List<TripsWaypoint> list) {
            Objects.requireNonNull(list, "Null waypoints");
            this.c = list;
            return this;
        }

        public b() {
        }

        public b(TripOptimisationResponse tripOptimisationResponse) {
            this.f13298a = tripOptimisationResponse.code();
            this.b = tripOptimisationResponse.trips();
            this.c = tripOptimisationResponse.waypoints();
        }
    }

    public a(String str, List<DirectionsRoute> list, List<TripsWaypoint> list2) {
        Objects.requireNonNull(str, "Null code");
        this.code = str;
        Objects.requireNonNull(list, "Null trips");
        this.trips = list;
        Objects.requireNonNull(list2, "Null waypoints");
        this.waypoints = list2;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse
    public String code() {
        return this.code;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TripOptimisationResponse) {
            TripOptimisationResponse tripOptimisationResponse = (TripOptimisationResponse) obj;
            return this.code.equals(tripOptimisationResponse.code()) && this.trips.equals(tripOptimisationResponse.trips()) && this.waypoints.equals(tripOptimisationResponse.waypoints());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.code.hashCode() ^ 1000003) * 1000003) ^ this.trips.hashCode()) * 1000003) ^ this.waypoints.hashCode();
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse
    public TripOptimisationResponse.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "TripOptimisationResponse{code=" + this.code + ", trips=" + this.trips + ", waypoints=" + this.waypoints + "}";
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse
    public List<DirectionsRoute> trips() {
        return this.trips;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse
    public List<TripsWaypoint> waypoints() {
        return this.waypoints;
    }
}
