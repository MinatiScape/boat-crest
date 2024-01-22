package com.google.android.gms.fitness;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public final class FitnessOptions implements GoogleSignInOptionsExtension {
    public static final int ACCESS_READ = 0;
    public static final int ACCESS_WRITE = 1;

    /* renamed from: a  reason: collision with root package name */
    public final Set<Scope> f8421a;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Set<Scope> f8422a;

        public Builder() {
            this.f8422a = new HashSet();
        }

        @NonNull
        public final Builder accessActivitySessions(int i) {
            if (i == 0) {
                this.f8422a.add(new Scope("https://www.googleapis.com/auth/fitness.activity.read"));
            } else if (i == 1) {
                this.f8422a.add(new Scope("https://www.googleapis.com/auth/fitness.activity.write"));
            } else {
                throw new IllegalArgumentException("valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            }
            return this;
        }

        @NonNull
        public final Builder accessSleepSessions(int i) {
            Preconditions.checkArgument(i == 0 || i == 1, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            if (i == 0) {
                this.f8422a.add(new Scope("https://www.googleapis.com/auth/fitness.sleep.read"));
            } else if (i == 1) {
                this.f8422a.add(new Scope("https://www.googleapis.com/auth/fitness.sleep.write"));
            }
            return this;
        }

        @NonNull
        public final Builder addDataType(@NonNull DataType dataType) {
            return addDataType(dataType, 0);
        }

        @NonNull
        public final FitnessOptions build() {
            return new FitnessOptions(this);
        }

        @NonNull
        public final Builder addDataType(@NonNull DataType dataType, int i) {
            Preconditions.checkArgument(i == 0 || i == 1, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            String zzk = dataType.zzk();
            String zzl = dataType.zzl();
            if (i == 0 && zzk != null) {
                this.f8422a.add(new Scope(zzk));
            } else if (i == 1 && zzl != null) {
                this.f8422a.add(new Scope(zzl));
            }
            return this;
        }
    }

    public FitnessOptions(Builder builder) {
        this.f8421a = builder.f8422a;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessOptions) {
            return this.f8421a.equals(((FitnessOptions) obj).f8421a);
        }
        return false;
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
    public final int getExtensionType() {
        return 3;
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
    @NonNull
    public final List<Scope> getImpliedScopes() {
        return new ArrayList(this.f8421a);
    }

    public final int hashCode() {
        return Objects.hashCode(this.f8421a);
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
    @NonNull
    public final Bundle toBundle() {
        return new Bundle();
    }
}
