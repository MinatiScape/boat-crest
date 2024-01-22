package com.mappls.sdk.services.api.directions;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.WalkingOptions;
/* loaded from: classes11.dex */
public abstract class a extends WalkingOptions {

    /* renamed from: a  reason: collision with root package name */
    public final Double f13165a;
    public final Double b;
    public final Double c;

    /* renamed from: com.mappls.sdk.services.api.directions.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0668a extends WalkingOptions.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13166a;
        public Double b;
        public Double c;

        @Override // com.mappls.sdk.services.api.directions.WalkingOptions.Builder
        public WalkingOptions.Builder alleyBias(@Nullable Double d) {
            this.c = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.WalkingOptions.Builder
        public WalkingOptions build() {
            return new AutoValue_WalkingOptions(this.f13166a, this.b, this.c);
        }

        @Override // com.mappls.sdk.services.api.directions.WalkingOptions.Builder
        public WalkingOptions.Builder walkingSpeed(@Nullable Double d) {
            this.f13166a = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.WalkingOptions.Builder
        public WalkingOptions.Builder walkwayBias(@Nullable Double d) {
            this.b = d;
            return this;
        }
    }

    public a(@Nullable Double d, @Nullable Double d2, @Nullable Double d3) {
        this.f13165a = d;
        this.b = d2;
        this.c = d3;
    }

    @Override // com.mappls.sdk.services.api.directions.WalkingOptions
    @Nullable
    @SerializedName("alley_bias")
    public Double alleyBias() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WalkingOptions) {
            WalkingOptions walkingOptions = (WalkingOptions) obj;
            Double d = this.f13165a;
            if (d != null ? d.equals(walkingOptions.walkingSpeed()) : walkingOptions.walkingSpeed() == null) {
                Double d2 = this.b;
                if (d2 != null ? d2.equals(walkingOptions.walkwayBias()) : walkingOptions.walkwayBias() == null) {
                    Double d3 = this.c;
                    if (d3 == null) {
                        if (walkingOptions.alleyBias() == null) {
                            return true;
                        }
                    } else if (d3.equals(walkingOptions.alleyBias())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Double d = this.f13165a;
        int hashCode = ((d == null ? 0 : d.hashCode()) ^ 1000003) * 1000003;
        Double d2 = this.b;
        int hashCode2 = (hashCode ^ (d2 == null ? 0 : d2.hashCode())) * 1000003;
        Double d3 = this.c;
        return hashCode2 ^ (d3 != null ? d3.hashCode() : 0);
    }

    public String toString() {
        return "WalkingOptions{walkingSpeed=" + this.f13165a + ", walkwayBias=" + this.b + ", alleyBias=" + this.c + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.WalkingOptions
    @Nullable
    @SerializedName("walking_speed")
    public Double walkingSpeed() {
        return this.f13165a;
    }

    @Override // com.mappls.sdk.services.api.directions.WalkingOptions
    @Nullable
    @SerializedName("walkway_bias")
    public Double walkwayBias() {
        return this.b;
    }
}
