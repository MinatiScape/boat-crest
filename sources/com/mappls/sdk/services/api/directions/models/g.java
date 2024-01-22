package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class g extends DirectionsWaypoint {
    private final Double distance;
    private final String hint;
    private final String name;
    private final double[] rawLocation;

    /* loaded from: classes11.dex */
    public static class b extends DirectionsWaypoint.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13192a;
        public double[] b;
        public Double c;
        public String d;

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint.Builder
        public DirectionsWaypoint build() {
            String str = "";
            if (this.c == null) {
                str = " distance";
            }
            if (this.d == null) {
                str = str + " hint";
            }
            if (str.isEmpty()) {
                return new AutoValue_DirectionsWaypoint(this.f13192a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint.Builder
        public DirectionsWaypoint.Builder distance(@Nullable Double d) {
            Objects.requireNonNull(d, "Null distance");
            this.c = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint.Builder
        public DirectionsWaypoint.Builder hint(@Nullable String str) {
            Objects.requireNonNull(str, "Null hint");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint.Builder
        public DirectionsWaypoint.Builder name(@Nullable String str) {
            this.f13192a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint.Builder
        public DirectionsWaypoint.Builder rawLocation(@Nullable double[] dArr) {
            this.b = dArr;
            return this;
        }

        public b() {
        }

        public b(DirectionsWaypoint directionsWaypoint) {
            this.f13192a = directionsWaypoint.name();
            this.b = directionsWaypoint.rawLocation();
            this.c = directionsWaypoint.distance();
            this.d = directionsWaypoint.hint();
        }
    }

    public g(@Nullable String str, @Nullable double[] dArr, Double d, String str2) {
        this.name = str;
        this.rawLocation = dArr;
        Objects.requireNonNull(d, "Null distance");
        this.distance = d;
        Objects.requireNonNull(str2, "Null hint");
        this.hint = str2;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint
    public Double distance() {
        return this.distance;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DirectionsWaypoint) {
            DirectionsWaypoint directionsWaypoint = (DirectionsWaypoint) obj;
            String str = this.name;
            if (str != null ? str.equals(directionsWaypoint.name()) : directionsWaypoint.name() == null) {
                if (Arrays.equals(this.rawLocation, directionsWaypoint instanceof g ? ((g) directionsWaypoint).rawLocation : directionsWaypoint.rawLocation()) && this.distance.equals(directionsWaypoint.distance()) && this.hint.equals(directionsWaypoint.hint())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        return (((((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ Arrays.hashCode(this.rawLocation)) * 1000003) ^ this.distance.hashCode()) * 1000003) ^ this.hint.hashCode();
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint
    public String hint() {
        return this.hint;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint
    @Nullable
    public String name() {
        return this.name;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint
    @Nullable
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    public double[] rawLocation() {
        return this.rawLocation;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsWaypoint
    public DirectionsWaypoint.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "DirectionsWaypoint{name=" + this.name + ", rawLocation=" + Arrays.toString(this.rawLocation) + ", distance=" + this.distance + ", hint=" + this.hint + "}";
    }
}
