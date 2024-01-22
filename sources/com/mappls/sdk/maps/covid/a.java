package com.mappls.sdk.maps.covid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.covid.CovidAnnotationOption;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends CovidAnnotationOption {

    /* renamed from: a  reason: collision with root package name */
    public final Integer f12705a;
    public final Float b;
    public final Integer c;

    /* loaded from: classes11.dex */
    public static final class b extends CovidAnnotationOption.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f12706a;
        public Float b;
        public Integer c;

        @Override // com.mappls.sdk.maps.covid.CovidAnnotationOption.Builder
        public CovidAnnotationOption build() {
            String str = "";
            if (this.f12706a == null) {
                str = " color";
            }
            if (str.isEmpty()) {
                return new a(this.f12706a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.maps.covid.CovidAnnotationOption.Builder
        public CovidAnnotationOption.Builder color(Integer num) {
            Objects.requireNonNull(num, "Null color");
            this.f12706a = num;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.CovidAnnotationOption.Builder
        public CovidAnnotationOption.Builder opacity(Float f) {
            this.b = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.CovidAnnotationOption.Builder
        public CovidAnnotationOption.Builder outlineColor(Integer num) {
            this.c = num;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.covid.CovidAnnotationOption
    @NonNull
    public Integer b() {
        return this.f12705a;
    }

    @Override // com.mappls.sdk.maps.covid.CovidAnnotationOption
    @Nullable
    public Float c() {
        return this.b;
    }

    @Override // com.mappls.sdk.maps.covid.CovidAnnotationOption
    @Nullable
    public Integer d() {
        return this.c;
    }

    public boolean equals(Object obj) {
        Float f;
        if (obj == this) {
            return true;
        }
        if (obj instanceof CovidAnnotationOption) {
            CovidAnnotationOption covidAnnotationOption = (CovidAnnotationOption) obj;
            if (this.f12705a.equals(covidAnnotationOption.b()) && ((f = this.b) != null ? f.equals(covidAnnotationOption.c()) : covidAnnotationOption.c() == null)) {
                Integer num = this.c;
                if (num == null) {
                    if (covidAnnotationOption.d() == null) {
                        return true;
                    }
                } else if (num.equals(covidAnnotationOption.d())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.f12705a.hashCode() ^ 1000003) * 1000003;
        Float f = this.b;
        int hashCode2 = (hashCode ^ (f == null ? 0 : f.hashCode())) * 1000003;
        Integer num = this.c;
        return hashCode2 ^ (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "CovidAnnotationOption{color=" + this.f12705a + ", opacity=" + this.b + ", outlineColor=" + this.c + "}";
    }

    public a(Integer num, @Nullable Float f, @Nullable Integer num2) {
        this.f12705a = num;
        this.b = f;
        this.c = num2;
    }
}
