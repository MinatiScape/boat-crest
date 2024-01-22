package com.google.android.gms.location;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.location.zzdh;
/* loaded from: classes10.dex */
public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    /* loaded from: classes10.dex */
    public static final class Builder {
        public double e;
        public double f;
        public float g;

        /* renamed from: a  reason: collision with root package name */
        public String f10032a = null;
        @TransitionTypes
        public int b = 0;
        public long c = Long.MIN_VALUE;
        public short d = -1;
        public int h = 0;
        public int i = -1;

        @NonNull
        public Geofence build() {
            if (this.f10032a != null) {
                int i = this.b;
                if (i != 0) {
                    if ((i & 4) != 0 && this.i < 0) {
                        throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELL.");
                    }
                    if (this.c != Long.MIN_VALUE) {
                        if (this.d != -1) {
                            if (this.h >= 0) {
                                return new zzdh(this.f10032a, this.b, (short) 1, this.e, this.f, this.g, this.c, this.h, this.i);
                            }
                            throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
                        }
                        throw new IllegalArgumentException("Geofence region not set.");
                    }
                    throw new IllegalArgumentException("Expiration not set.");
                }
                throw new IllegalArgumentException("Transitions types not set.");
            }
            throw new IllegalArgumentException("Request ID not set.");
        }

        @NonNull
        public Builder setCircularRegion(@FloatRange(from = -90.0d, to = 90.0d) double d, @FloatRange(from = -180.0d, to = 180.0d) double d2, @FloatRange(from = 0.0d, fromInclusive = false) float f) {
            boolean z = d >= -90.0d && d <= 90.0d;
            Preconditions.checkArgument(z, "Invalid latitude: " + d);
            boolean z2 = d2 >= -180.0d && d2 <= 180.0d;
            Preconditions.checkArgument(z2, "Invalid longitude: " + d2);
            boolean z3 = f > 0.0f;
            Preconditions.checkArgument(z3, "Invalid radius: " + f);
            this.d = (short) 1;
            this.e = d;
            this.f = d2;
            this.g = f;
            return this;
        }

        @NonNull
        public Builder setExpirationDuration(long j) {
            if (j < 0) {
                this.c = -1L;
            } else {
                this.c = DefaultClock.getInstance().elapsedRealtime() + j;
            }
            return this;
        }

        @NonNull
        public Builder setLoiteringDelay(int i) {
            this.i = i;
            return this;
        }

        @NonNull
        public Builder setNotificationResponsiveness(@IntRange(from = 0) int i) {
            this.h = i;
            return this;
        }

        @NonNull
        public Builder setRequestId(@NonNull String str) {
            this.f10032a = (String) Preconditions.checkNotNull(str, "Request ID can't be set to null");
            return this;
        }

        @NonNull
        public Builder setTransitionTypes(@TransitionTypes int i) {
            this.b = i;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public @interface GeofenceTransition {
    }

    /* loaded from: classes10.dex */
    public @interface TransitionTypes {
    }

    long getExpirationTime();

    double getLatitude();

    int getLoiteringDelay();

    double getLongitude();

    int getNotificationResponsiveness();

    float getRadius();

    @NonNull
    String getRequestId();

    @TransitionTypes
    int getTransitionTypes();
}
