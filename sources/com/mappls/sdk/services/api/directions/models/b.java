package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.BannerInstructions;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class b extends BannerInstructions {
    private final double distanceAlongGeometry;
    private final BannerText primary;
    private final Integer secondary;
    private final BannerText sub;

    /* renamed from: com.mappls.sdk.services.api.directions.models.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0671b extends BannerInstructions.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13187a;
        public Integer b;
        public BannerText c;
        public BannerText d;

        @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions.Builder
        public BannerInstructions build() {
            String str = "";
            if (this.f13187a == null) {
                str = " distanceAlongGeometry";
            }
            if (this.c == null) {
                str = str + " primary";
            }
            if (str.isEmpty()) {
                return new AutoValue_BannerInstructions(this.f13187a.doubleValue(), this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions.Builder
        public BannerInstructions.Builder distanceAlongGeometry(double d) {
            this.f13187a = Double.valueOf(d);
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions.Builder
        public BannerInstructions.Builder primary(BannerText bannerText) {
            Objects.requireNonNull(bannerText, "Null primary");
            this.c = bannerText;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions.Builder
        public BannerInstructions.Builder secondary(@Nullable Integer num) {
            this.b = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions.Builder
        public BannerInstructions.Builder sub(@Nullable BannerText bannerText) {
            this.d = bannerText;
            return this;
        }

        public C0671b() {
        }

        public C0671b(BannerInstructions bannerInstructions) {
            this.f13187a = Double.valueOf(bannerInstructions.distanceAlongGeometry());
            this.b = bannerInstructions.secondary();
            this.c = bannerInstructions.primary();
            this.d = bannerInstructions.sub();
        }
    }

    public b(double d, @Nullable Integer num, BannerText bannerText, @Nullable BannerText bannerText2) {
        this.distanceAlongGeometry = d;
        this.secondary = num;
        Objects.requireNonNull(bannerText, "Null primary");
        this.primary = bannerText;
        this.sub = bannerText2;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions
    @SerializedName("distance_along_geometry")
    public double distanceAlongGeometry() {
        return this.distanceAlongGeometry;
    }

    public boolean equals(Object obj) {
        Integer num;
        if (obj == this) {
            return true;
        }
        if (obj instanceof BannerInstructions) {
            BannerInstructions bannerInstructions = (BannerInstructions) obj;
            if (Double.doubleToLongBits(this.distanceAlongGeometry) == Double.doubleToLongBits(bannerInstructions.distanceAlongGeometry()) && ((num = this.secondary) != null ? num.equals(bannerInstructions.secondary()) : bannerInstructions.secondary() == null) && this.primary.equals(bannerInstructions.primary())) {
                BannerText bannerText = this.sub;
                if (bannerText == null) {
                    if (bannerInstructions.sub() == null) {
                        return true;
                    }
                } else if (bannerText.equals(bannerInstructions.sub())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int doubleToLongBits = (((int) ((Double.doubleToLongBits(this.distanceAlongGeometry) >>> 32) ^ Double.doubleToLongBits(this.distanceAlongGeometry))) ^ 1000003) * 1000003;
        Integer num = this.secondary;
        int hashCode = (((doubleToLongBits ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.primary.hashCode()) * 1000003;
        BannerText bannerText = this.sub;
        return hashCode ^ (bannerText != null ? bannerText.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions
    @NonNull
    public BannerText primary() {
        return this.primary;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions
    @Nullable
    public Integer secondary() {
        return this.secondary;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions
    @Nullable
    public BannerText sub() {
        return this.sub;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerInstructions
    public BannerInstructions.Builder toBuilder() {
        return new C0671b(this);
    }

    public String toString() {
        return "BannerInstructions{distanceAlongGeometry=" + this.distanceAlongGeometry + ", secondary=" + this.secondary + ", primary=" + this.primary + ", sub=" + this.sub + "}";
    }
}
