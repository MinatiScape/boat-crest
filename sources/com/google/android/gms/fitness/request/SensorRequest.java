package com.google.android.gms.fitness.request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class SensorRequest {
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final DataSource f8454a;
    @Nullable
    public final DataType b;
    public final long c;
    public final long d;
    public final long e;
    public final int f;
    public final long g;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public DataSource f8455a;
        public DataType b;
        public long c = -1;
        public long d = 0;
        public long e = 0;
        public boolean f = false;
        public int g = 2;
        public long h = Long.MAX_VALUE;

        @NonNull
        public SensorRequest build() {
            DataSource dataSource;
            boolean z = false;
            Preconditions.checkState((this.f8455a == null && this.b == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.b;
            if (dataType == null || (dataSource = this.f8455a) == null || dataType.equals(dataSource.getDataType())) {
                z = true;
            }
            Preconditions.checkState(z, "Specified data type is incompatible with specified data source");
            return new SensorRequest(this);
        }

        @NonNull
        public Builder setAccuracyMode(int i) {
            if (i != 1 && i != 3) {
                i = 2;
            }
            this.g = i;
            return this;
        }

        @NonNull
        public Builder setDataSource(@NonNull DataSource dataSource) {
            this.f8455a = dataSource;
            return this;
        }

        @NonNull
        public Builder setDataType(@NonNull DataType dataType) {
            this.b = dataType;
            return this;
        }

        @NonNull
        public Builder setFastestRate(int i, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative interval");
            this.f = true;
            this.d = timeUnit.toMicros(i);
            return this;
        }

        @NonNull
        public Builder setMaxDeliveryLatency(int i, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative delivery interval");
            this.e = timeUnit.toMicros(i);
            return this;
        }

        @NonNull
        public Builder setSamplingRate(long j, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Cannot use a negative sampling interval");
            long micros = timeUnit.toMicros(j);
            this.c = micros;
            if (!this.f) {
                this.d = micros / 2;
            }
            return this;
        }

        @NonNull
        public Builder setTimeout(long j, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid time out value specified: %d", Long.valueOf(j));
            Preconditions.checkArgument(timeUnit != null, "Invalid time unit specified");
            this.h = timeUnit.toMicros(j);
            return this;
        }
    }

    public SensorRequest(Builder builder) {
        this.f8454a = builder.f8455a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.g;
        this.g = builder.h;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SensorRequest) {
            SensorRequest sensorRequest = (SensorRequest) obj;
            return Objects.equal(this.f8454a, sensorRequest.f8454a) && Objects.equal(this.b, sensorRequest.b) && this.c == sensorRequest.c && this.d == sensorRequest.d && this.e == sensorRequest.e && this.f == sensorRequest.f && this.g == sensorRequest.g;
        }
        return false;
    }

    public int getAccuracyMode() {
        return this.f;
    }

    @Nullable
    public DataSource getDataSource() {
        return this.f8454a;
    }

    @Nullable
    public DataType getDataType() {
        return this.b;
    }

    public long getFastestRate(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.d, TimeUnit.MICROSECONDS);
    }

    public long getMaxDeliveryLatency(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.e, TimeUnit.MICROSECONDS);
    }

    public long getSamplingRate(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.c, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.f8454a, this.b, Long.valueOf(this.c), Long.valueOf(this.d), Long.valueOf(this.e), Integer.valueOf(this.f), Long.valueOf(this.g));
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.f8454a).add(DeviceKey.DataType, this.b).add("samplingRateMicros", Long.valueOf(this.c)).add("deliveryLatencyMicros", Long.valueOf(this.e)).add("timeOutMicros", Long.valueOf(this.g)).toString();
    }

    public final long zzy() {
        return this.g;
    }
}
