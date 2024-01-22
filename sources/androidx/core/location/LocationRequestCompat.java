package androidx.core.location;

import android.annotation.SuppressLint;
import android.location.LocationRequest;
import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.util.TimeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class LocationRequestCompat {
    public static final long PASSIVE_INTERVAL = Long.MAX_VALUE;
    public static final int QUALITY_BALANCED_POWER_ACCURACY = 102;
    public static final int QUALITY_HIGH_ACCURACY = 100;
    public static final int QUALITY_LOW_POWER = 104;

    /* renamed from: a  reason: collision with root package name */
    public final int f1068a;
    public final long b;
    public final long c;
    public final long d;
    public final int e;
    public final float f;
    public final long g;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface Quality {
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static Class<?> f1070a;
        public static Method b;
        public static Method c;
        public static Method d;
        public static Method e;
        public static Method f;

        public static Object a(LocationRequestCompat locationRequestCompat, String str) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    if (f1070a == null) {
                        f1070a = Class.forName("android.location.LocationRequest");
                    }
                    if (b == null) {
                        Method declaredMethod = f1070a.getDeclaredMethod("createFromDeprecatedProvider", String.class, Long.TYPE, Float.TYPE, Boolean.TYPE);
                        b = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    Object invoke = b.invoke(null, str, Long.valueOf(locationRequestCompat.getIntervalMillis()), Float.valueOf(locationRequestCompat.getMinUpdateDistanceMeters()), Boolean.FALSE);
                    if (invoke == null) {
                        return null;
                    }
                    if (c == null) {
                        Method declaredMethod2 = f1070a.getDeclaredMethod("setQuality", Integer.TYPE);
                        c = declaredMethod2;
                        declaredMethod2.setAccessible(true);
                    }
                    c.invoke(invoke, Integer.valueOf(locationRequestCompat.getQuality()));
                    if (d == null) {
                        Method declaredMethod3 = f1070a.getDeclaredMethod("setFastestInterval", Long.TYPE);
                        d = declaredMethod3;
                        declaredMethod3.setAccessible(true);
                    }
                    d.invoke(invoke, Long.valueOf(locationRequestCompat.getMinUpdateIntervalMillis()));
                    if (locationRequestCompat.getMaxUpdates() < Integer.MAX_VALUE) {
                        if (e == null) {
                            Method declaredMethod4 = f1070a.getDeclaredMethod("setNumUpdates", Integer.TYPE);
                            e = declaredMethod4;
                            declaredMethod4.setAccessible(true);
                        }
                        e.invoke(invoke, Integer.valueOf(locationRequestCompat.getMaxUpdates()));
                    }
                    if (locationRequestCompat.getDurationMillis() < Long.MAX_VALUE) {
                        if (f == null) {
                            Method declaredMethod5 = f1070a.getDeclaredMethod("setExpireIn", Long.TYPE);
                            f = declaredMethod5;
                            declaredMethod5.setAccessible(true);
                        }
                        f.invoke(invoke, Long.valueOf(locationRequestCompat.getDurationMillis()));
                    }
                    return invoke;
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                }
            }
            return null;
        }
    }

    @RequiresApi(31)
    /* loaded from: classes.dex */
    public static class b {
        @DoNotInline
        public static LocationRequest a(LocationRequestCompat locationRequestCompat) {
            return new LocationRequest.Builder(locationRequestCompat.getIntervalMillis()).setQuality(locationRequestCompat.getQuality()).setMinUpdateIntervalMillis(locationRequestCompat.getMinUpdateIntervalMillis()).setDurationMillis(locationRequestCompat.getDurationMillis()).setMaxUpdates(locationRequestCompat.getMaxUpdates()).setMinUpdateDistanceMeters(locationRequestCompat.getMinUpdateDistanceMeters()).setMaxUpdateDelayMillis(locationRequestCompat.getMaxUpdateDelayMillis()).build();
        }
    }

    public LocationRequestCompat(long j, int i, long j2, int i2, long j3, float f, long j4) {
        this.b = j;
        this.f1068a = i;
        this.c = j3;
        this.d = j2;
        this.e = i2;
        this.f = f;
        this.g = j4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocationRequestCompat) {
            LocationRequestCompat locationRequestCompat = (LocationRequestCompat) obj;
            return this.f1068a == locationRequestCompat.f1068a && this.b == locationRequestCompat.b && this.c == locationRequestCompat.c && this.d == locationRequestCompat.d && this.e == locationRequestCompat.e && Float.compare(locationRequestCompat.f, this.f) == 0 && this.g == locationRequestCompat.g;
        }
        return false;
    }

    @IntRange(from = 1)
    public long getDurationMillis() {
        return this.d;
    }

    @IntRange(from = 0)
    public long getIntervalMillis() {
        return this.b;
    }

    @IntRange(from = 0)
    public long getMaxUpdateDelayMillis() {
        return this.g;
    }

    @IntRange(from = 1, to = 2147483647L)
    public int getMaxUpdates() {
        return this.e;
    }

    @FloatRange(from = 0.0d, to = 3.4028234663852886E38d)
    public float getMinUpdateDistanceMeters() {
        return this.f;
    }

    @IntRange(from = 0)
    public long getMinUpdateIntervalMillis() {
        long j = this.c;
        return j == -1 ? this.b : j;
    }

    public int getQuality() {
        return this.f1068a;
    }

    public int hashCode() {
        long j = this.b;
        long j2 = this.c;
        return (((this.f1068a * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    @NonNull
    @RequiresApi(31)
    public LocationRequest toLocationRequest() {
        return b.a(this);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        if (this.b != Long.MAX_VALUE) {
            sb.append("@");
            TimeUtils.formatDuration(this.b, sb);
            int i = this.f1068a;
            if (i == 100) {
                sb.append(" HIGH_ACCURACY");
            } else if (i == 102) {
                sb.append(" BALANCED");
            } else if (i == 104) {
                sb.append(" LOW_POWER");
            }
        } else {
            sb.append("PASSIVE");
        }
        if (this.d != Long.MAX_VALUE) {
            sb.append(", duration=");
            TimeUtils.formatDuration(this.d, sb);
        }
        if (this.e != Integer.MAX_VALUE) {
            sb.append(", maxUpdates=");
            sb.append(this.e);
        }
        long j = this.c;
        if (j != -1 && j < this.b) {
            sb.append(", minUpdateInterval=");
            TimeUtils.formatDuration(this.c, sb);
        }
        if (this.f > 0.0d) {
            sb.append(", minUpdateDistance=");
            sb.append(this.f);
        }
        if (this.g / 2 > this.b) {
            sb.append(", maxUpdateDelay=");
            TimeUtils.formatDuration(this.g, sb);
        }
        sb.append(']');
        return sb.toString();
    }

    @Nullable
    @RequiresApi(19)
    @SuppressLint({"NewApi"})
    public LocationRequest toLocationRequest(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 31) {
            return toLocationRequest();
        }
        return (LocationRequest) a.a(this, str);
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f1069a;
        public int b;
        public long c;
        public int d;
        public long e;
        public float f;
        public long g;

        public Builder(long j) {
            setIntervalMillis(j);
            this.b = 102;
            this.c = Long.MAX_VALUE;
            this.d = Integer.MAX_VALUE;
            this.e = -1L;
            this.f = 0.0f;
            this.g = 0L;
        }

        @NonNull
        public LocationRequestCompat build() {
            Preconditions.checkState((this.f1069a == Long.MAX_VALUE && this.e == -1) ? false : true, "passive location requests must have an explicit minimum update interval");
            long j = this.f1069a;
            return new LocationRequestCompat(j, this.b, this.c, this.d, Math.min(this.e, j), this.f, this.g);
        }

        @NonNull
        public Builder clearMinUpdateIntervalMillis() {
            this.e = -1L;
            return this;
        }

        @NonNull
        public Builder setDurationMillis(@IntRange(from = 1) long j) {
            this.c = Preconditions.checkArgumentInRange(j, 1L, Long.MAX_VALUE, "durationMillis");
            return this;
        }

        @NonNull
        public Builder setIntervalMillis(@IntRange(from = 0) long j) {
            this.f1069a = Preconditions.checkArgumentInRange(j, 0L, Long.MAX_VALUE, "intervalMillis");
            return this;
        }

        @NonNull
        public Builder setMaxUpdateDelayMillis(@IntRange(from = 0) long j) {
            this.g = j;
            this.g = Preconditions.checkArgumentInRange(j, 0L, Long.MAX_VALUE, "maxUpdateDelayMillis");
            return this;
        }

        @NonNull
        public Builder setMaxUpdates(@IntRange(from = 1, to = 2147483647L) int i) {
            this.d = Preconditions.checkArgumentInRange(i, 1, Integer.MAX_VALUE, "maxUpdates");
            return this;
        }

        @NonNull
        public Builder setMinUpdateDistanceMeters(@FloatRange(from = 0.0d, to = 3.4028234663852886E38d) float f) {
            this.f = f;
            this.f = Preconditions.checkArgumentInRange(f, 0.0f, Float.MAX_VALUE, "minUpdateDistanceMeters");
            return this;
        }

        @NonNull
        public Builder setMinUpdateIntervalMillis(@IntRange(from = 0) long j) {
            this.e = Preconditions.checkArgumentInRange(j, 0L, Long.MAX_VALUE, "minUpdateIntervalMillis");
            return this;
        }

        @NonNull
        public Builder setQuality(int i) {
            Preconditions.checkArgument(i == 104 || i == 102 || i == 100, "quality must be a defined QUALITY constant, not %d", Integer.valueOf(i));
            this.b = i;
            return this;
        }

        public Builder(@NonNull LocationRequestCompat locationRequestCompat) {
            this.f1069a = locationRequestCompat.b;
            this.b = locationRequestCompat.f1068a;
            this.c = locationRequestCompat.d;
            this.d = locationRequestCompat.e;
            this.e = locationRequestCompat.c;
            this.f = locationRequestCompat.f;
            this.g = locationRequestCompat.g;
        }
    }
}
