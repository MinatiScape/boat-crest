package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.LegAnnotation;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class i extends LegAnnotation {
    private final List<Double> baseDuration;
    private final List<String> congestion;
    private final List<Double> distance;
    private final List<Double> duration;
    private final List<MaxSpeed> maxspeed;
    private final List<Long> nodes;
    private final List<Double> speed;
    private final List<Double> speedLimit;
    private final List<String> tollRoad;

    /* loaded from: classes11.dex */
    public static class b extends LegAnnotation.Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<Double> f13194a;
        public List<Double> b;
        public List<Double> c;
        public List<MaxSpeed> d;
        public List<String> e;
        public List<String> f;
        public List<Long> g;
        public List<Double> h;
        public List<Double> i;

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder baseDuration(@Nullable List<Double> list) {
            this.h = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation build() {
            return new AutoValue_LegAnnotation(this.f13194a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder congestion(@Nullable List<String> list) {
            this.e = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder distance(@Nullable List<Double> list) {
            this.f13194a = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder duration(@Nullable List<Double> list) {
            this.b = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder maxspeed(@Nullable List<MaxSpeed> list) {
            this.d = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder nodes(@Nullable List<Long> list) {
            this.g = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder speed(@Nullable List<Double> list) {
            this.c = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder speedLimit(@Nullable List<Double> list) {
            this.i = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation.Builder
        public LegAnnotation.Builder tollRoad(@Nullable List<String> list) {
            this.f = list;
            return this;
        }

        public b() {
        }

        public b(LegAnnotation legAnnotation) {
            this.f13194a = legAnnotation.distance();
            this.b = legAnnotation.duration();
            this.c = legAnnotation.speed();
            this.d = legAnnotation.maxspeed();
            this.e = legAnnotation.congestion();
            this.f = legAnnotation.tollRoad();
            this.g = legAnnotation.nodes();
            this.h = legAnnotation.baseDuration();
            this.i = legAnnotation.speedLimit();
        }
    }

    public i(@Nullable List<Double> list, @Nullable List<Double> list2, @Nullable List<Double> list3, @Nullable List<MaxSpeed> list4, @Nullable List<String> list5, @Nullable List<String> list6, @Nullable List<Long> list7, @Nullable List<Double> list8, @Nullable List<Double> list9) {
        this.distance = list;
        this.duration = list2;
        this.speed = list3;
        this.maxspeed = list4;
        this.congestion = list5;
        this.tollRoad = list6;
        this.nodes = list7;
        this.baseDuration = list8;
        this.speedLimit = list9;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    public List<Double> baseDuration() {
        return this.baseDuration;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    public List<String> congestion() {
        return this.congestion;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    public List<Double> distance() {
        return this.distance;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    public List<Double> duration() {
        return this.duration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LegAnnotation) {
            LegAnnotation legAnnotation = (LegAnnotation) obj;
            List<Double> list = this.distance;
            if (list != null ? list.equals(legAnnotation.distance()) : legAnnotation.distance() == null) {
                List<Double> list2 = this.duration;
                if (list2 != null ? list2.equals(legAnnotation.duration()) : legAnnotation.duration() == null) {
                    List<Double> list3 = this.speed;
                    if (list3 != null ? list3.equals(legAnnotation.speed()) : legAnnotation.speed() == null) {
                        List<MaxSpeed> list4 = this.maxspeed;
                        if (list4 != null ? list4.equals(legAnnotation.maxspeed()) : legAnnotation.maxspeed() == null) {
                            List<String> list5 = this.congestion;
                            if (list5 != null ? list5.equals(legAnnotation.congestion()) : legAnnotation.congestion() == null) {
                                List<String> list6 = this.tollRoad;
                                if (list6 != null ? list6.equals(legAnnotation.tollRoad()) : legAnnotation.tollRoad() == null) {
                                    List<Long> list7 = this.nodes;
                                    if (list7 != null ? list7.equals(legAnnotation.nodes()) : legAnnotation.nodes() == null) {
                                        List<Double> list8 = this.baseDuration;
                                        if (list8 != null ? list8.equals(legAnnotation.baseDuration()) : legAnnotation.baseDuration() == null) {
                                            List<Double> list9 = this.speedLimit;
                                            if (list9 == null) {
                                                if (legAnnotation.speedLimit() == null) {
                                                    return true;
                                                }
                                            } else if (list9.equals(legAnnotation.speedLimit())) {
                                                return true;
                                            }
                                        }
                                    }
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
        List<Double> list = this.distance;
        int hashCode = ((list == null ? 0 : list.hashCode()) ^ 1000003) * 1000003;
        List<Double> list2 = this.duration;
        int hashCode2 = (hashCode ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        List<Double> list3 = this.speed;
        int hashCode3 = (hashCode2 ^ (list3 == null ? 0 : list3.hashCode())) * 1000003;
        List<MaxSpeed> list4 = this.maxspeed;
        int hashCode4 = (hashCode3 ^ (list4 == null ? 0 : list4.hashCode())) * 1000003;
        List<String> list5 = this.congestion;
        int hashCode5 = (hashCode4 ^ (list5 == null ? 0 : list5.hashCode())) * 1000003;
        List<String> list6 = this.tollRoad;
        int hashCode6 = (hashCode5 ^ (list6 == null ? 0 : list6.hashCode())) * 1000003;
        List<Long> list7 = this.nodes;
        int hashCode7 = (hashCode6 ^ (list7 == null ? 0 : list7.hashCode())) * 1000003;
        List<Double> list8 = this.baseDuration;
        int hashCode8 = (hashCode7 ^ (list8 == null ? 0 : list8.hashCode())) * 1000003;
        List<Double> list9 = this.speedLimit;
        return hashCode8 ^ (list9 != null ? list9.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    public List<MaxSpeed> maxspeed() {
        return this.maxspeed;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    public List<Long> nodes() {
        return this.nodes;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    public List<Double> speed() {
        return this.speed;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    @SerializedName(DirectionsCriteria.ANNOTATION_SPEED_LIMIT)
    public List<Double> speedLimit() {
        return this.speedLimit;
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    public LegAnnotation.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "LegAnnotation{distance=" + this.distance + ", duration=" + this.duration + ", speed=" + this.speed + ", maxspeed=" + this.maxspeed + ", congestion=" + this.congestion + ", tollRoad=" + this.tollRoad + ", nodes=" + this.nodes + ", baseDuration=" + this.baseDuration + ", speedLimit=" + this.speedLimit + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.LegAnnotation
    @Nullable
    @SerializedName(DirectionsCriteria.ANNOTATION_TOLL_ROAD)
    public List<String> tollRoad() {
        return this.tollRoad;
    }
}
