package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.MaxSpeed;
/* loaded from: classes11.dex */
public abstract class k extends MaxSpeed {
    private final Boolean none;
    private final Integer speed;
    private final String unit;
    private final Boolean unknown;

    /* loaded from: classes11.dex */
    public static class b extends MaxSpeed.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f13196a;
        public String b;
        public Boolean c;
        public Boolean d;

        @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed.Builder
        public MaxSpeed build() {
            return new AutoValue_MaxSpeed(this.f13196a, this.b, this.c, this.d);
        }

        @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed.Builder
        public MaxSpeed.Builder none(@Nullable Boolean bool) {
            this.d = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed.Builder
        public MaxSpeed.Builder speed(@Nullable Integer num) {
            this.f13196a = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed.Builder
        public MaxSpeed.Builder unit(@Nullable String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed.Builder
        public MaxSpeed.Builder unknown(@Nullable Boolean bool) {
            this.c = bool;
            return this;
        }

        public b() {
        }

        public b(MaxSpeed maxSpeed) {
            this.f13196a = maxSpeed.speed();
            this.b = maxSpeed.unit();
            this.c = maxSpeed.unknown();
            this.d = maxSpeed.none();
        }
    }

    public k(@Nullable Integer num, @Nullable String str, @Nullable Boolean bool, @Nullable Boolean bool2) {
        this.speed = num;
        this.unit = str;
        this.unknown = bool;
        this.none = bool2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MaxSpeed) {
            MaxSpeed maxSpeed = (MaxSpeed) obj;
            Integer num = this.speed;
            if (num != null ? num.equals(maxSpeed.speed()) : maxSpeed.speed() == null) {
                String str = this.unit;
                if (str != null ? str.equals(maxSpeed.unit()) : maxSpeed.unit() == null) {
                    Boolean bool = this.unknown;
                    if (bool != null ? bool.equals(maxSpeed.unknown()) : maxSpeed.unknown() == null) {
                        Boolean bool2 = this.none;
                        if (bool2 == null) {
                            if (maxSpeed.none() == null) {
                                return true;
                            }
                        } else if (bool2.equals(maxSpeed.none())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.speed;
        int hashCode = ((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003;
        String str = this.unit;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Boolean bool = this.unknown;
        int hashCode3 = (hashCode2 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.none;
        return hashCode3 ^ (bool2 != null ? bool2.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed
    @Nullable
    public Boolean none() {
        return this.none;
    }

    @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed
    @Nullable
    public Integer speed() {
        return this.speed;
    }

    @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed
    public MaxSpeed.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "MaxSpeed{speed=" + this.speed + ", unit=" + this.unit + ", unknown=" + this.unknown + ", none=" + this.none + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed
    @Nullable
    public String unit() {
        return this.unit;
    }

    @Override // com.mappls.sdk.services.api.directions.models.MaxSpeed
    @Nullable
    public Boolean unknown() {
        return this.unknown;
    }
}
