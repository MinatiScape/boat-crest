package androidx.navigation;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class NavOptions {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1450a;
    @IdRes
    public int b;
    public boolean c;
    @AnimRes
    @AnimatorRes
    public int d;
    @AnimRes
    @AnimatorRes
    public int e;
    @AnimRes
    @AnimatorRes
    public int f;
    @AnimRes
    @AnimatorRes
    public int g;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f1451a;
        public boolean c;
        @IdRes
        public int b = -1;
        @AnimRes
        @AnimatorRes
        public int d = -1;
        @AnimRes
        @AnimatorRes
        public int e = -1;
        @AnimRes
        @AnimatorRes
        public int f = -1;
        @AnimRes
        @AnimatorRes
        public int g = -1;

        @NonNull
        public NavOptions build() {
            return new NavOptions(this.f1451a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        @NonNull
        public Builder setEnterAnim(@AnimRes @AnimatorRes int i) {
            this.d = i;
            return this;
        }

        @NonNull
        public Builder setExitAnim(@AnimRes @AnimatorRes int i) {
            this.e = i;
            return this;
        }

        @NonNull
        public Builder setLaunchSingleTop(boolean z) {
            this.f1451a = z;
            return this;
        }

        @NonNull
        public Builder setPopEnterAnim(@AnimRes @AnimatorRes int i) {
            this.f = i;
            return this;
        }

        @NonNull
        public Builder setPopExitAnim(@AnimRes @AnimatorRes int i) {
            this.g = i;
            return this;
        }

        @NonNull
        public Builder setPopUpTo(@IdRes int i, boolean z) {
            this.b = i;
            this.c = z;
            return this;
        }
    }

    public NavOptions(boolean z, @IdRes int i, boolean z2, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        this.f1450a = z;
        this.b = i;
        this.c = z2;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NavOptions.class != obj.getClass()) {
            return false;
        }
        NavOptions navOptions = (NavOptions) obj;
        return this.f1450a == navOptions.f1450a && this.b == navOptions.b && this.c == navOptions.c && this.d == navOptions.d && this.e == navOptions.e && this.f == navOptions.f && this.g == navOptions.g;
    }

    @AnimRes
    @AnimatorRes
    public int getEnterAnim() {
        return this.d;
    }

    @AnimRes
    @AnimatorRes
    public int getExitAnim() {
        return this.e;
    }

    @AnimRes
    @AnimatorRes
    public int getPopEnterAnim() {
        return this.f;
    }

    @AnimRes
    @AnimatorRes
    public int getPopExitAnim() {
        return this.g;
    }

    @IdRes
    public int getPopUpTo() {
        return this.b;
    }

    public int hashCode() {
        return ((((((((((((shouldLaunchSingleTop() ? 1 : 0) * 31) + getPopUpTo()) * 31) + (isPopUpToInclusive() ? 1 : 0)) * 31) + getEnterAnim()) * 31) + getExitAnim()) * 31) + getPopEnterAnim()) * 31) + getPopExitAnim();
    }

    public boolean isPopUpToInclusive() {
        return this.c;
    }

    public boolean shouldLaunchSingleTop() {
        return this.f1450a;
    }
}
