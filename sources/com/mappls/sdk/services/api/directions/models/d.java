package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.DirectionsError;
/* loaded from: classes11.dex */
public abstract class d extends DirectionsError {
    private final String code;
    private final String message;

    /* loaded from: classes11.dex */
    public static class b extends DirectionsError.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13189a;
        public String b;

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsError.Builder
        public DirectionsError build() {
            return new AutoValue_DirectionsError(this.f13189a, this.b);
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsError.Builder
        public DirectionsError.Builder code(String str) {
            this.f13189a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsError.Builder
        public DirectionsError.Builder message(String str) {
            this.b = str;
            return this;
        }

        public b() {
        }

        public b(DirectionsError directionsError) {
            this.f13189a = directionsError.code();
            this.b = directionsError.message();
        }
    }

    public d(@Nullable String str, @Nullable String str2) {
        this.code = str;
        this.message = str2;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsError
    @Nullable
    public String code() {
        return this.code;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DirectionsError) {
            DirectionsError directionsError = (DirectionsError) obj;
            String str = this.code;
            if (str != null ? str.equals(directionsError.code()) : directionsError.code() == null) {
                String str2 = this.message;
                if (str2 == null) {
                    if (directionsError.message() == null) {
                        return true;
                    }
                } else if (str2.equals(directionsError.message())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.code;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.message;
        return hashCode ^ (str2 != null ? str2.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsError
    @Nullable
    public String message() {
        return this.message;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsError
    public DirectionsError.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "DirectionsError{code=" + this.code + ", message=" + this.message + "}";
    }
}
