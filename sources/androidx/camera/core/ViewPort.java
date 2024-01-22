package androidx.camera.core;

import android.util.Rational;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@ExperimentalUseCaseGroup
/* loaded from: classes.dex */
public final class ViewPort {
    public static final int FILL_CENTER = 1;
    public static final int FILL_END = 2;
    public static final int FILL_START = 0;
    public static final int FIT = 3;

    /* renamed from: a  reason: collision with root package name */
    public int f667a;
    @NonNull
    public Rational b;
    public int c;
    public int d;

    @ExperimentalUseCaseGroup
    /* loaded from: classes.dex */
    public static final class Builder {
        public final Rational b;
        public final int c;

        /* renamed from: a  reason: collision with root package name */
        public int f668a = 1;
        public int d = 0;

        public Builder(@NonNull Rational rational, int i) {
            this.b = rational;
            this.c = i;
        }

        @NonNull
        public ViewPort build() {
            Preconditions.checkNotNull(this.b, "The crop aspect ratio must be set.");
            return new ViewPort(this.f668a, this.b, this.c, this.d);
        }

        @NonNull
        public Builder setLayoutDirection(int i) {
            this.d = i;
            return this;
        }

        @NonNull
        public Builder setScaleType(int i) {
            this.f668a = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface LayoutDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ScaleType {
    }

    public ViewPort(int i, @NonNull Rational rational, int i2, int i3) {
        this.f667a = i;
        this.b = rational;
        this.c = i2;
        this.d = i3;
    }

    @NonNull
    public Rational getAspectRatio() {
        return this.b;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getLayoutDirection() {
        return this.d;
    }

    public int getRotation() {
        return this.c;
    }

    public int getScaleType() {
        return this.f667a;
    }
}
