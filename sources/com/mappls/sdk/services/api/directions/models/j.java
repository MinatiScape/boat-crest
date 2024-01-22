package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class j extends LegStep {
    private final List<BannerInstructions> bannerInstructions;
    private final String destinations;
    private final double distance;
    private final String drivingSide;
    private final double duration;
    private final String geometry;
    private final List<StepIntersection> intersections;
    private final StepManeuver maneuver;
    private final String mode;
    private final String name;
    private final String ref;
    private final String rotaryName;
    private final double weight;

    /* loaded from: classes11.dex */
    public static class b extends LegStep.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13195a;
        public Double b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public StepManeuver h;
        public List<BannerInstructions> i;
        public String j;
        public Double k;
        public List<StepIntersection> l;
        public String m;

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder bannerInstructions(List<BannerInstructions> list) {
            this.i = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep build() {
            String str = "";
            if (this.f13195a == null) {
                str = " distance";
            }
            if (this.b == null) {
                str = str + " duration";
            }
            if (this.f == null) {
                str = str + " mode";
            }
            if (this.h == null) {
                str = str + " maneuver";
            }
            if (this.k == null) {
                str = str + " weight";
            }
            if (str.isEmpty()) {
                return new AutoValue_LegStep(this.f13195a.doubleValue(), this.b.doubleValue(), this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k.doubleValue(), this.l, this.m);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder destinations(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder distance(double d) {
            this.f13195a = Double.valueOf(d);
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder drivingSide(@Nullable String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder duration(double d) {
            this.b = Double.valueOf(d);
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder geometry(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder intersections(List<StepIntersection> list) {
            this.l = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder maneuver(StepManeuver stepManeuver) {
            Objects.requireNonNull(stepManeuver, "Null maneuver");
            this.h = stepManeuver;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder mode(String str) {
            Objects.requireNonNull(str, "Null mode");
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder name(@Nullable String str) {
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder ref(@Nullable String str) {
            this.m = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder rotaryName(@Nullable String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegStep.Builder
        public LegStep.Builder weight(double d) {
            this.k = Double.valueOf(d);
            return this;
        }

        public b() {
        }

        public b(LegStep legStep) {
            this.f13195a = Double.valueOf(legStep.distance());
            this.b = Double.valueOf(legStep.duration());
            this.c = legStep.geometry();
            this.d = legStep.name();
            this.e = legStep.destinations();
            this.f = legStep.mode();
            this.g = legStep.rotaryName();
            this.h = legStep.maneuver();
            this.i = legStep.bannerInstructions();
            this.j = legStep.drivingSide();
            this.k = Double.valueOf(legStep.weight());
            this.l = legStep.intersections();
            this.m = legStep.ref();
        }
    }

    public j(double d, double d2, @Nullable String str, @Nullable String str2, @Nullable String str3, String str4, @Nullable String str5, StepManeuver stepManeuver, @Nullable List<BannerInstructions> list, @Nullable String str6, double d3, @Nullable List<StepIntersection> list2, @Nullable String str7) {
        this.distance = d;
        this.duration = d2;
        this.geometry = str;
        this.name = str2;
        this.destinations = str3;
        Objects.requireNonNull(str4, "Null mode");
        this.mode = str4;
        this.rotaryName = str5;
        Objects.requireNonNull(stepManeuver, "Null maneuver");
        this.maneuver = stepManeuver;
        this.bannerInstructions = list;
        this.drivingSide = str6;
        this.weight = d3;
        this.intersections = list2;
        this.ref = str7;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    @SerializedName("banner_instructions")
    public List<BannerInstructions> bannerInstructions() {
        return this.bannerInstructions;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    public String destinations() {
        return this.destinations;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    public double distance() {
        return this.distance;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    @SerializedName("driving_side")
    public String drivingSide() {
        return this.drivingSide;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    public double duration() {
        return this.duration;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        List<BannerInstructions> list;
        String str5;
        List<StepIntersection> list2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof LegStep) {
            LegStep legStep = (LegStep) obj;
            if (Double.doubleToLongBits(this.distance) == Double.doubleToLongBits(legStep.distance()) && Double.doubleToLongBits(this.duration) == Double.doubleToLongBits(legStep.duration()) && ((str = this.geometry) != null ? str.equals(legStep.geometry()) : legStep.geometry() == null) && ((str2 = this.name) != null ? str2.equals(legStep.name()) : legStep.name() == null) && ((str3 = this.destinations) != null ? str3.equals(legStep.destinations()) : legStep.destinations() == null) && this.mode.equals(legStep.mode()) && ((str4 = this.rotaryName) != null ? str4.equals(legStep.rotaryName()) : legStep.rotaryName() == null) && this.maneuver.equals(legStep.maneuver()) && ((list = this.bannerInstructions) != null ? list.equals(legStep.bannerInstructions()) : legStep.bannerInstructions() == null) && ((str5 = this.drivingSide) != null ? str5.equals(legStep.drivingSide()) : legStep.drivingSide() == null) && Double.doubleToLongBits(this.weight) == Double.doubleToLongBits(legStep.weight()) && ((list2 = this.intersections) != null ? list2.equals(legStep.intersections()) : legStep.intersections() == null)) {
                String str6 = this.ref;
                if (str6 == null) {
                    if (legStep.ref() == null) {
                        return true;
                    }
                } else if (str6.equals(legStep.ref())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    public String geometry() {
        return this.geometry;
    }

    public int hashCode() {
        int doubleToLongBits = (((((int) ((Double.doubleToLongBits(this.distance) >>> 32) ^ Double.doubleToLongBits(this.distance))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.duration) >>> 32) ^ Double.doubleToLongBits(this.duration)))) * 1000003;
        String str = this.geometry;
        int hashCode = (doubleToLongBits ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.name;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.destinations;
        int hashCode3 = (((hashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003) ^ this.mode.hashCode()) * 1000003;
        String str4 = this.rotaryName;
        int hashCode4 = (((hashCode3 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003) ^ this.maneuver.hashCode()) * 1000003;
        List<BannerInstructions> list = this.bannerInstructions;
        int hashCode5 = (hashCode4 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        String str5 = this.drivingSide;
        int hashCode6 = (((hashCode5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.weight) >>> 32) ^ Double.doubleToLongBits(this.weight)))) * 1000003;
        List<StepIntersection> list2 = this.intersections;
        int hashCode7 = (hashCode6 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        String str6 = this.ref;
        return hashCode7 ^ (str6 != null ? str6.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    public List<StepIntersection> intersections() {
        return this.intersections;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @NonNull
    public StepManeuver maneuver() {
        return this.maneuver;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @NonNull
    public String mode() {
        return this.mode;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    public String name() {
        return this.name;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    public String ref() {
        return this.ref;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    @Nullable
    @SerializedName("rotary_name")
    public String rotaryName() {
        return this.rotaryName;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    public LegStep.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "LegStep{distance=" + this.distance + ", duration=" + this.duration + ", geometry=" + this.geometry + ", name=" + this.name + ", destinations=" + this.destinations + ", mode=" + this.mode + ", rotaryName=" + this.rotaryName + ", maneuver=" + this.maneuver + ", bannerInstructions=" + this.bannerInstructions + ", drivingSide=" + this.drivingSide + ", weight=" + this.weight + ", intersections=" + this.intersections + ", ref=" + this.ref + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegStep
    public double weight() {
        return this.weight;
    }
}
