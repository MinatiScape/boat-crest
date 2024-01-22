package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class NavArgument {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final NavType f1433a;
    public final boolean b;
    public final boolean c;
    @Nullable
    public final Object d;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public NavType<?> f1434a;
        @Nullable
        public Object c;
        public boolean b = false;
        public boolean d = false;

        @NonNull
        public NavArgument build() {
            if (this.f1434a == null) {
                this.f1434a = NavType.b(this.c);
            }
            return new NavArgument(this.f1434a, this.b, this.c, this.d);
        }

        @NonNull
        public Builder setDefaultValue(@Nullable Object obj) {
            this.c = obj;
            this.d = true;
            return this;
        }

        @NonNull
        public Builder setIsNullable(boolean z) {
            this.b = z;
            return this;
        }

        @NonNull
        public Builder setType(@NonNull NavType<?> navType) {
            this.f1434a = navType;
            return this;
        }
    }

    public NavArgument(@NonNull NavType<?> navType, boolean z, @Nullable Object obj, boolean z2) {
        if (!navType.isNullableAllowed() && z) {
            throw new IllegalArgumentException(navType.getName() + " does not allow nullable values");
        } else if (!z && z2 && obj == null) {
            throw new IllegalArgumentException("Argument with type " + navType.getName() + " has null value but is not nullable.");
        } else {
            this.f1433a = navType;
            this.b = z;
            this.d = obj;
            this.c = z2;
        }
    }

    public void a(@NonNull String str, @NonNull Bundle bundle) {
        if (this.c) {
            this.f1433a.put(bundle, str, this.d);
        }
    }

    public boolean b(@NonNull String str, @NonNull Bundle bundle) {
        if (!this.b && bundle.containsKey(str) && bundle.get(str) == null) {
            return false;
        }
        try {
            this.f1433a.get(bundle, str);
            return true;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NavArgument.class != obj.getClass()) {
            return false;
        }
        NavArgument navArgument = (NavArgument) obj;
        if (this.b == navArgument.b && this.c == navArgument.c && this.f1433a.equals(navArgument.f1433a)) {
            Object obj2 = this.d;
            return obj2 != null ? obj2.equals(navArgument.d) : navArgument.d == null;
        }
        return false;
    }

    @Nullable
    public Object getDefaultValue() {
        return this.d;
    }

    @NonNull
    public NavType<?> getType() {
        return this.f1433a;
    }

    public int hashCode() {
        int hashCode = ((((this.f1433a.hashCode() * 31) + (this.b ? 1 : 0)) * 31) + (this.c ? 1 : 0)) * 31;
        Object obj = this.d;
        return hashCode + (obj != null ? obj.hashCode() : 0);
    }

    public boolean isDefaultValuePresent() {
        return this.c;
    }

    public boolean isNullable() {
        return this.b;
    }
}
