package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.directions.models.BannerComponents;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class a extends BannerComponents {
    private final String text;
    private final String type;

    /* loaded from: classes11.dex */
    public static class b extends BannerComponents.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13186a;
        public String b;

        @Override // com.mappls.sdk.services.api.directions.models.BannerComponents.Builder
        public BannerComponents build() {
            String str = "";
            if (this.f13186a == null) {
                str = " text";
            }
            if (this.b == null) {
                str = str + " type";
            }
            if (str.isEmpty()) {
                return new AutoValue_BannerComponents(this.f13186a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerComponents.Builder
        public BannerComponents.Builder text(String str) {
            Objects.requireNonNull(str, "Null text");
            this.f13186a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerComponents.Builder
        public BannerComponents.Builder type(String str) {
            Objects.requireNonNull(str, "Null type");
            this.b = str;
            return this;
        }

        public b() {
        }

        public b(BannerComponents bannerComponents) {
            this.f13186a = bannerComponents.text();
            this.b = bannerComponents.type();
        }
    }

    public a(String str, String str2) {
        Objects.requireNonNull(str, "Null text");
        this.text = str;
        Objects.requireNonNull(str2, "Null type");
        this.type = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BannerComponents) {
            BannerComponents bannerComponents = (BannerComponents) obj;
            return this.text.equals(bannerComponents.text()) && this.type.equals(bannerComponents.type());
        }
        return false;
    }

    public int hashCode() {
        return ((this.text.hashCode() ^ 1000003) * 1000003) ^ this.type.hashCode();
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerComponents
    @NonNull
    public String text() {
        return this.text;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerComponents
    public BannerComponents.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "BannerComponents{text=" + this.text + ", type=" + this.type + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerComponents
    @NonNull
    public String type() {
        return this.type;
    }
}
