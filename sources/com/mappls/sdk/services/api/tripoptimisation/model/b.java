package com.mappls.sdk.services.api.tripoptimisation.model;

import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes8.dex */
public abstract class b extends TripsWaypoint {
    private final Double distance;
    private final String hint;
    private final String name;
    private final double[] rawLocation;
    private final Integer tripsIndex;
    private final Integer waypointIndex;

    /* renamed from: com.mappls.sdk.services.api.tripoptimisation.model.b$b  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static class C0703b extends TripsWaypoint.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13299a;
        public double[] b;
        public Double c;
        public String d;
        public Integer e;
        public Integer f;

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint.Builder
        public TripsWaypoint build() {
            String str = "";
            if (this.c == null) {
                str = " distance";
            }
            if (this.d == null) {
                str = str + " hint";
            }
            if (this.e == null) {
                str = str + " waypointIndex";
            }
            if (this.f == null) {
                str = str + " tripsIndex";
            }
            if (str.isEmpty()) {
                return new AutoValue_TripsWaypoint(this.f13299a, this.b, this.c, this.d, this.e, this.f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint.Builder
        public TripsWaypoint.Builder distance(@Nullable Double d) {
            Objects.requireNonNull(d, "Null distance");
            this.c = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint.Builder
        public TripsWaypoint.Builder hint(@Nullable String str) {
            Objects.requireNonNull(str, "Null hint");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint.Builder
        public TripsWaypoint.Builder name(@Nullable String str) {
            this.f13299a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint.Builder
        public TripsWaypoint.Builder rawLocation(@Nullable double[] dArr) {
            this.b = dArr;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint.Builder
        public TripsWaypoint.Builder tripsIndex(@Nullable Integer num) {
            Objects.requireNonNull(num, "Null tripsIndex");
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint.Builder
        public TripsWaypoint.Builder waypointIndex(@Nullable Integer num) {
            Objects.requireNonNull(num, "Null waypointIndex");
            this.e = num;
            return this;
        }

        public C0703b() {
        }

        public C0703b(TripsWaypoint tripsWaypoint) {
            this.f13299a = tripsWaypoint.name();
            this.b = tripsWaypoint.rawLocation();
            this.c = tripsWaypoint.distance();
            this.d = tripsWaypoint.hint();
            this.e = tripsWaypoint.waypointIndex();
            this.f = tripsWaypoint.tripsIndex();
        }
    }

    public b(@Nullable String str, @Nullable double[] dArr, Double d, String str2, Integer num, Integer num2) {
        this.name = str;
        this.rawLocation = dArr;
        Objects.requireNonNull(d, "Null distance");
        this.distance = d;
        Objects.requireNonNull(str2, "Null hint");
        this.hint = str2;
        Objects.requireNonNull(num, "Null waypointIndex");
        this.waypointIndex = num;
        Objects.requireNonNull(num2, "Null tripsIndex");
        this.tripsIndex = num2;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint
    public Double distance() {
        return this.distance;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TripsWaypoint) {
            TripsWaypoint tripsWaypoint = (TripsWaypoint) obj;
            String str = this.name;
            if (str != null ? str.equals(tripsWaypoint.name()) : tripsWaypoint.name() == null) {
                if (Arrays.equals(this.rawLocation, tripsWaypoint instanceof b ? ((b) tripsWaypoint).rawLocation : tripsWaypoint.rawLocation()) && this.distance.equals(tripsWaypoint.distance()) && this.hint.equals(tripsWaypoint.hint()) && this.waypointIndex.equals(tripsWaypoint.waypointIndex()) && this.tripsIndex.equals(tripsWaypoint.tripsIndex())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        return (((((((((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ Arrays.hashCode(this.rawLocation)) * 1000003) ^ this.distance.hashCode()) * 1000003) ^ this.hint.hashCode()) * 1000003) ^ this.waypointIndex.hashCode()) * 1000003) ^ this.tripsIndex.hashCode();
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint
    public String hint() {
        return this.hint;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint
    @Nullable
    public String name() {
        return this.name;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint
    @Nullable
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    public double[] rawLocation() {
        return this.rawLocation;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint
    public TripsWaypoint.Builder toBuilder() {
        return new C0703b(this);
    }

    public String toString() {
        return "TripsWaypoint{name=" + this.name + ", rawLocation=" + Arrays.toString(this.rawLocation) + ", distance=" + this.distance + ", hint=" + this.hint + ", waypointIndex=" + this.waypointIndex + ", tripsIndex=" + this.tripsIndex + "}";
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint
    @SerializedName("trips_index")
    public Integer tripsIndex() {
        return this.tripsIndex;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint
    @SerializedName("waypoint_index")
    public Integer waypointIndex() {
        return this.waypointIndex;
    }
}
