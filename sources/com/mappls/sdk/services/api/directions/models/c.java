package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.BannerText;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class c extends BannerText {
    private final List<BannerComponents> components;
    private final Double degrees;
    private final String drivingSide;
    private final String modifier;
    private final String text;
    private final String type;

    /* loaded from: classes11.dex */
    public static class b extends BannerText.Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<BannerComponents> f13188a;
        public String b;
        public String c;
        public Double d;
        public String e;
        public String f;

        @Override // com.mappls.sdk.services.api.directions.models.BannerText.Builder
        public BannerText build() {
            String str = "";
            if (this.b == null) {
                str = " text";
            }
            if (str.isEmpty()) {
                return new AutoValue_BannerText(this.f13188a, this.b, this.c, this.d, this.e, this.f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerText.Builder
        public BannerText.Builder components(List<BannerComponents> list) {
            this.f13188a = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerText.Builder
        public BannerText.Builder degrees(Double d) {
            this.d = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerText.Builder
        public BannerText.Builder drivingSide(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerText.Builder
        public BannerText.Builder modifier(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerText.Builder
        public BannerText.Builder text(String str) {
            Objects.requireNonNull(str, "Null text");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.BannerText.Builder
        public BannerText.Builder type(@Nullable String str) {
            this.f = str;
            return this;
        }

        public b() {
        }

        public b(BannerText bannerText) {
            this.f13188a = bannerText.components();
            this.b = bannerText.text();
            this.c = bannerText.modifier();
            this.d = bannerText.degrees();
            this.e = bannerText.drivingSide();
            this.f = bannerText.type();
        }
    }

    public c(@Nullable List<BannerComponents> list, String str, @Nullable String str2, @Nullable Double d, @Nullable String str3, @Nullable String str4) {
        this.components = list;
        Objects.requireNonNull(str, "Null text");
        this.text = str;
        this.modifier = str2;
        this.degrees = d;
        this.drivingSide = str3;
        this.type = str4;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerText
    @Nullable
    public List<BannerComponents> components() {
        return this.components;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerText
    @Nullable
    public Double degrees() {
        return this.degrees;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerText
    @Nullable
    @SerializedName("driving_side")
    public String drivingSide() {
        return this.drivingSide;
    }

    public boolean equals(Object obj) {
        String str;
        Double d;
        String str2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof BannerText) {
            BannerText bannerText = (BannerText) obj;
            List<BannerComponents> list = this.components;
            if (list != null ? list.equals(bannerText.components()) : bannerText.components() == null) {
                if (this.text.equals(bannerText.text()) && ((str = this.modifier) != null ? str.equals(bannerText.modifier()) : bannerText.modifier() == null) && ((d = this.degrees) != null ? d.equals(bannerText.degrees()) : bannerText.degrees() == null) && ((str2 = this.drivingSide) != null ? str2.equals(bannerText.drivingSide()) : bannerText.drivingSide() == null)) {
                    String str3 = this.type;
                    if (str3 == null) {
                        if (bannerText.type() == null) {
                            return true;
                        }
                    } else if (str3.equals(bannerText.type())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<BannerComponents> list = this.components;
        int hashCode = ((((list == null ? 0 : list.hashCode()) ^ 1000003) * 1000003) ^ this.text.hashCode()) * 1000003;
        String str = this.modifier;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Double d = this.degrees;
        int hashCode3 = (hashCode2 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        String str2 = this.drivingSide;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.type;
        return hashCode4 ^ (str3 != null ? str3.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerText
    @Nullable
    public String modifier() {
        return this.modifier;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerText
    @NonNull
    public String text() {
        return this.text;
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerText
    public BannerText.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "BannerText{components=" + this.components + ", text=" + this.text + ", modifier=" + this.modifier + ", degrees=" + this.degrees + ", drivingSide=" + this.drivingSide + ", type=" + this.type + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.BannerText
    @Nullable
    public String type() {
        return this.type;
    }
}
