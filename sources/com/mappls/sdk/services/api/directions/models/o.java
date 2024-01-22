package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.StepIntersection;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class o extends StepIntersection {
    private final List<Integer> bearings;
    private final List<String> classes;
    private final List<Boolean> entry;
    private final Integer in;
    private final List<IntersectionLanes> lanes;
    private final Integer out;
    private final double[] rawLocation;

    /* loaded from: classes11.dex */
    public static class b extends StepIntersection.Builder {

        /* renamed from: a  reason: collision with root package name */
        public double[] f13200a;
        public List<Integer> b;
        public List<String> c;
        public List<Boolean> d;
        public Integer e;
        public Integer f;
        public List<IntersectionLanes> g;

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection.Builder bearings(@Nullable List<Integer> list) {
            this.b = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection build() {
            String str = "";
            if (this.f13200a == null) {
                str = " rawLocation";
            }
            if (str.isEmpty()) {
                return new AutoValue_StepIntersection(this.f13200a, this.b, this.c, this.d, this.e, this.f, this.g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection.Builder classes(@Nullable List<String> list) {
            this.c = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection.Builder entry(@Nullable List<Boolean> list) {
            this.d = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection.Builder in(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection.Builder lanes(@Nullable List<IntersectionLanes> list) {
            this.g = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection.Builder out(@Nullable Integer num) {
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepIntersection.Builder
        public StepIntersection.Builder rawLocation(double[] dArr) {
            Objects.requireNonNull(dArr, "Null rawLocation");
            this.f13200a = dArr;
            return this;
        }

        public b() {
        }

        public b(StepIntersection stepIntersection) {
            this.f13200a = stepIntersection.rawLocation();
            this.b = stepIntersection.bearings();
            this.c = stepIntersection.classes();
            this.d = stepIntersection.entry();
            this.e = stepIntersection.in();
            this.f = stepIntersection.out();
            this.g = stepIntersection.lanes();
        }
    }

    public o(double[] dArr, @Nullable List<Integer> list, @Nullable List<String> list2, @Nullable List<Boolean> list3, @Nullable Integer num, @Nullable Integer num2, @Nullable List<IntersectionLanes> list4) {
        Objects.requireNonNull(dArr, "Null rawLocation");
        this.rawLocation = dArr;
        this.bearings = list;
        this.classes = list2;
        this.entry = list3;
        this.in = num;
        this.out = num2;
        this.lanes = list4;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    @Nullable
    public List<Integer> bearings() {
        return this.bearings;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    @Nullable
    public List<String> classes() {
        return this.classes;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    @Nullable
    public List<Boolean> entry() {
        return this.entry;
    }

    public boolean equals(Object obj) {
        List<Integer> list;
        List<String> list2;
        List<Boolean> list3;
        Integer num;
        Integer num2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof StepIntersection) {
            StepIntersection stepIntersection = (StepIntersection) obj;
            if (Arrays.equals(this.rawLocation, stepIntersection instanceof o ? ((o) stepIntersection).rawLocation : stepIntersection.rawLocation()) && ((list = this.bearings) != null ? list.equals(stepIntersection.bearings()) : stepIntersection.bearings() == null) && ((list2 = this.classes) != null ? list2.equals(stepIntersection.classes()) : stepIntersection.classes() == null) && ((list3 = this.entry) != null ? list3.equals(stepIntersection.entry()) : stepIntersection.entry() == null) && ((num = this.in) != null ? num.equals(stepIntersection.in()) : stepIntersection.in() == null) && ((num2 = this.out) != null ? num2.equals(stepIntersection.out()) : stepIntersection.out() == null)) {
                List<IntersectionLanes> list4 = this.lanes;
                if (list4 == null) {
                    if (stepIntersection.lanes() == null) {
                        return true;
                    }
                } else if (list4.equals(stepIntersection.lanes())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (Arrays.hashCode(this.rawLocation) ^ 1000003) * 1000003;
        List<Integer> list = this.bearings;
        int hashCode2 = (hashCode ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List<String> list2 = this.classes;
        int hashCode3 = (hashCode2 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        List<Boolean> list3 = this.entry;
        int hashCode4 = (hashCode3 ^ (list3 == null ? 0 : list3.hashCode())) * 1000003;
        Integer num = this.in;
        int hashCode5 = (hashCode4 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.out;
        int hashCode6 = (hashCode5 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        List<IntersectionLanes> list4 = this.lanes;
        return hashCode6 ^ (list4 != null ? list4.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    @Nullable
    public Integer in() {
        return this.in;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    @Nullable
    public List<IntersectionLanes> lanes() {
        return this.lanes;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    @Nullable
    public Integer out() {
        return this.out;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    @NonNull
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    public double[] rawLocation() {
        return this.rawLocation;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepIntersection
    public StepIntersection.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "StepIntersection{rawLocation=" + Arrays.toString(this.rawLocation) + ", bearings=" + this.bearings + ", classes=" + this.classes + ", entry=" + this.entry + ", in=" + this.in + ", out=" + this.out + ", lanes=" + this.lanes + "}";
    }
}
