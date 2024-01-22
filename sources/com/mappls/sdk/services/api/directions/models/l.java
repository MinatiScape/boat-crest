package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.RouteClasses;
/* loaded from: classes11.dex */
public abstract class l extends RouteClasses {
    private final Integer ferry;
    private final Integer motorway;
    private final Integer restricted;
    private final Integer toll;
    private final Integer tunnel;

    /* loaded from: classes11.dex */
    public static class a extends RouteClasses.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f13197a;
        public Integer b;
        public Integer c;
        public Integer d;
        public Integer e;

        @Override // com.mappls.sdk.services.api.directions.models.RouteClasses.Builder
        public RouteClasses build() {
            return new AutoValue_RouteClasses(this.f13197a, this.b, this.c, this.d, this.e);
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteClasses.Builder
        public RouteClasses.Builder ferry(@Nullable Integer num) {
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteClasses.Builder
        public RouteClasses.Builder motorway(@Nullable Integer num) {
            this.f13197a = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteClasses.Builder
        public RouteClasses.Builder restricted(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteClasses.Builder
        public RouteClasses.Builder toll(@Nullable Integer num) {
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteClasses.Builder
        public RouteClasses.Builder tunnel(@Nullable Integer num) {
            this.b = num;
            return this;
        }
    }

    public l(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5) {
        this.motorway = num;
        this.tunnel = num2;
        this.toll = num3;
        this.ferry = num4;
        this.restricted = num5;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RouteClasses) {
            RouteClasses routeClasses = (RouteClasses) obj;
            Integer num = this.motorway;
            if (num != null ? num.equals(routeClasses.motorway()) : routeClasses.motorway() == null) {
                Integer num2 = this.tunnel;
                if (num2 != null ? num2.equals(routeClasses.tunnel()) : routeClasses.tunnel() == null) {
                    Integer num3 = this.toll;
                    if (num3 != null ? num3.equals(routeClasses.toll()) : routeClasses.toll() == null) {
                        Integer num4 = this.ferry;
                        if (num4 != null ? num4.equals(routeClasses.ferry()) : routeClasses.ferry() == null) {
                            Integer num5 = this.restricted;
                            if (num5 == null) {
                                if (routeClasses.restricted() == null) {
                                    return true;
                                }
                            } else if (num5.equals(routeClasses.restricted())) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteClasses
    @Nullable
    public Integer ferry() {
        return this.ferry;
    }

    public int hashCode() {
        Integer num = this.motorway;
        int hashCode = ((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003;
        Integer num2 = this.tunnel;
        int hashCode2 = (hashCode ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Integer num3 = this.toll;
        int hashCode3 = (hashCode2 ^ (num3 == null ? 0 : num3.hashCode())) * 1000003;
        Integer num4 = this.ferry;
        int hashCode4 = (hashCode3 ^ (num4 == null ? 0 : num4.hashCode())) * 1000003;
        Integer num5 = this.restricted;
        return hashCode4 ^ (num5 != null ? num5.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteClasses
    @Nullable
    public Integer motorway() {
        return this.motorway;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteClasses
    @Nullable
    public Integer restricted() {
        return this.restricted;
    }

    public String toString() {
        return "RouteClasses{motorway=" + this.motorway + ", tunnel=" + this.tunnel + ", toll=" + this.toll + ", ferry=" + this.ferry + ", restricted=" + this.restricted + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteClasses
    @Nullable
    public Integer toll() {
        return this.toll;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteClasses
    @Nullable
    public Integer tunnel() {
        return this.tunnel;
    }
}
