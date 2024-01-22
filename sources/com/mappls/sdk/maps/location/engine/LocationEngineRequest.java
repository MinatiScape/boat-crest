package com.mappls.sdk.maps.location.engine;
/* loaded from: classes11.dex */
public class LocationEngineRequest {
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 1;
    public static final int PRIORITY_HIGH_ACCURACY = 0;
    public static final int PRIORITY_LOW_POWER = 2;
    public static final int PRIORITY_NO_POWER = 3;

    /* renamed from: a  reason: collision with root package name */
    public final long f12754a;
    public final int b;
    public final float c;
    public final long d;
    public final long e;

    /* loaded from: classes11.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final long f12755a;
        public int b = 0;
        public float c = 0.0f;
        public long d = 0;
        public long e = 0;

        public Builder(long j) {
            this.f12755a = j;
        }

        public LocationEngineRequest build() {
            return new LocationEngineRequest(this);
        }

        public Builder setDisplacement(float f) {
            this.c = f;
            return this;
        }

        public Builder setFastestInterval(long j) {
            this.e = j;
            return this;
        }

        public Builder setMaxWaitTime(long j) {
            this.d = j;
            return this;
        }

        public Builder setPriority(int i) {
            this.b = i;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocationEngineRequest locationEngineRequest = (LocationEngineRequest) obj;
        return this.f12754a == locationEngineRequest.f12754a && this.b == locationEngineRequest.b && Float.compare(locationEngineRequest.c, this.c) == 0 && this.d == locationEngineRequest.d && this.e == locationEngineRequest.e;
    }

    public float getDisplacement() {
        return this.c;
    }

    public long getFastestInterval() {
        return this.e;
    }

    public long getInterval() {
        return this.f12754a;
    }

    public long getMaxWaitTime() {
        return this.d;
    }

    public int getPriority() {
        return this.b;
    }

    public int hashCode() {
        long j = this.f12754a;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + this.b) * 31;
        float f = this.c;
        int floatToIntBits = f != 0.0f ? Float.floatToIntBits(f) : 0;
        long j2 = this.d;
        long j3 = this.e;
        return ((((i + floatToIntBits) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) ((j3 >>> 32) ^ j3));
    }

    public LocationEngineRequest(Builder builder) {
        this.f12754a = builder.f12755a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
    }
}
