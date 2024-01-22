package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class m extends RouteLeg {
    private final LegAnnotation annotation;
    private final Double distance;
    private final Double duration;
    private final List<LegStep> steps;
    private final String summary;
    private final Double weight;

    /* loaded from: classes11.dex */
    public static class b extends RouteLeg.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13198a;
        public Double b;
        public String c;
        public List<LegStep> d;
        public LegAnnotation e;
        public Double f;

        @Override // com.mappls.sdk.services.api.directions.models.RouteLeg.Builder
        public RouteLeg.Builder annotation(@Nullable LegAnnotation legAnnotation) {
            this.e = legAnnotation;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteLeg.Builder
        public RouteLeg build() {
            return new AutoValue_RouteLeg(this.f13198a, this.b, this.c, this.d, this.e, this.f);
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteLeg.Builder
        public RouteLeg.Builder distance(@Nullable Double d) {
            this.f13198a = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteLeg.Builder
        public RouteLeg.Builder duration(@Nullable Double d) {
            this.b = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteLeg.Builder
        public RouteLeg.Builder steps(@Nullable List<LegStep> list) {
            this.d = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteLeg.Builder
        public RouteLeg.Builder summary(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteLeg.Builder
        public RouteLeg.Builder weight(@Nullable Double d) {
            this.f = d;
            return this;
        }

        public b() {
        }

        public b(RouteLeg routeLeg) {
            this.f13198a = routeLeg.distance();
            this.b = routeLeg.duration();
            this.c = routeLeg.summary();
            this.d = routeLeg.steps();
            this.e = routeLeg.annotation();
            this.f = routeLeg.weight();
        }
    }

    public m(@Nullable Double d, @Nullable Double d2, @Nullable String str, @Nullable List<LegStep> list, @Nullable LegAnnotation legAnnotation, @Nullable Double d3) {
        this.distance = d;
        this.duration = d2;
        this.summary = str;
        this.steps = list;
        this.annotation = legAnnotation;
        this.weight = d3;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteLeg
    @Nullable
    public LegAnnotation annotation() {
        return this.annotation;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteLeg
    @Nullable
    public Double distance() {
        return this.distance;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteLeg
    @Nullable
    public Double duration() {
        return this.duration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RouteLeg) {
            RouteLeg routeLeg = (RouteLeg) obj;
            Double d = this.distance;
            if (d != null ? d.equals(routeLeg.distance()) : routeLeg.distance() == null) {
                Double d2 = this.duration;
                if (d2 != null ? d2.equals(routeLeg.duration()) : routeLeg.duration() == null) {
                    String str = this.summary;
                    if (str != null ? str.equals(routeLeg.summary()) : routeLeg.summary() == null) {
                        List<LegStep> list = this.steps;
                        if (list != null ? list.equals(routeLeg.steps()) : routeLeg.steps() == null) {
                            LegAnnotation legAnnotation = this.annotation;
                            if (legAnnotation != null ? legAnnotation.equals(routeLeg.annotation()) : routeLeg.annotation() == null) {
                                Double d3 = this.weight;
                                if (d3 == null) {
                                    if (routeLeg.weight() == null) {
                                        return true;
                                    }
                                } else if (d3.equals(routeLeg.weight())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Double d = this.distance;
        int hashCode = ((d == null ? 0 : d.hashCode()) ^ 1000003) * 1000003;
        Double d2 = this.duration;
        int hashCode2 = (hashCode ^ (d2 == null ? 0 : d2.hashCode())) * 1000003;
        String str = this.summary;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<LegStep> list = this.steps;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        LegAnnotation legAnnotation = this.annotation;
        int hashCode5 = (hashCode4 ^ (legAnnotation == null ? 0 : legAnnotation.hashCode())) * 1000003;
        Double d3 = this.weight;
        return hashCode5 ^ (d3 != null ? d3.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteLeg
    @Nullable
    public List<LegStep> steps() {
        return this.steps;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteLeg
    @Nullable
    public String summary() {
        return this.summary;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteLeg
    public RouteLeg.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "RouteLeg{distance=" + this.distance + ", duration=" + this.duration + ", summary=" + this.summary + ", steps=" + this.steps + ", annotation=" + this.annotation + ", weight=" + this.weight + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteLeg
    @Nullable
    public Double weight() {
        return this.weight;
    }
}
