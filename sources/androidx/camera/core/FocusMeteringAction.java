package androidx.camera.core;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class FocusMeteringAction {
    public static final int FLAG_AE = 2;
    public static final int FLAG_AF = 1;
    public static final int FLAG_AWB = 4;

    /* renamed from: a  reason: collision with root package name */
    public final List<MeteringPoint> f622a;
    public final List<MeteringPoint> b;
    public final List<MeteringPoint> c;
    public final long d;

    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List<MeteringPoint> f623a;
        public final List<MeteringPoint> b;
        public final List<MeteringPoint> c;
        public long d;

        public Builder(@NonNull MeteringPoint meteringPoint) {
            this(meteringPoint, 7);
        }

        @NonNull
        public Builder addPoint(@NonNull MeteringPoint meteringPoint) {
            return addPoint(meteringPoint, 7);
        }

        @NonNull
        public FocusMeteringAction build() {
            return new FocusMeteringAction(this);
        }

        @NonNull
        public Builder disableAutoCancel() {
            this.d = 0L;
            return this;
        }

        @NonNull
        public Builder setAutoCancelDuration(@IntRange(from = 1) long j, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 1, "autoCancelDuration must be at least 1");
            this.d = timeUnit.toMillis(j);
            return this;
        }

        public Builder(@NonNull MeteringPoint meteringPoint, int i) {
            this.f623a = new ArrayList();
            this.b = new ArrayList();
            this.c = new ArrayList();
            this.d = 5000L;
            addPoint(meteringPoint, i);
        }

        @NonNull
        public Builder addPoint(@NonNull MeteringPoint meteringPoint, int i) {
            boolean z = false;
            Preconditions.checkArgument(meteringPoint != null, "Point cannot be null.");
            if (i >= 1 && i <= 7) {
                z = true;
            }
            Preconditions.checkArgument(z, "Invalid metering mode " + i);
            if ((i & 1) != 0) {
                this.f623a.add(meteringPoint);
            }
            if ((i & 2) != 0) {
                this.b.add(meteringPoint);
            }
            if ((i & 4) != 0) {
                this.c.add(meteringPoint);
            }
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface MeteringMode {
    }

    public FocusMeteringAction(Builder builder) {
        this.f622a = Collections.unmodifiableList(builder.f623a);
        this.b = Collections.unmodifiableList(builder.b);
        this.c = Collections.unmodifiableList(builder.c);
        this.d = builder.d;
    }

    public long getAutoCancelDurationInMillis() {
        return this.d;
    }

    @NonNull
    public List<MeteringPoint> getMeteringPointsAe() {
        return this.b;
    }

    @NonNull
    public List<MeteringPoint> getMeteringPointsAf() {
        return this.f622a;
    }

    @NonNull
    public List<MeteringPoint> getMeteringPointsAwb() {
        return this.c;
    }

    public boolean isAutoCancelEnabled() {
        return this.d > 0;
    }
}
