package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapplsGetStyle;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class f extends MapplsGetStyle {

    /* renamed from: a  reason: collision with root package name */
    public final String f12723a;
    public final String b;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsGetStyle.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12724a;
        public String b;

        @Override // com.mappls.sdk.maps.MapplsGetStyle.Builder
        public MapplsGetStyle a() {
            String str = "";
            if (this.f12724a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " logoResolution";
            }
            if (str.isEmpty()) {
                return new f(this.f12724a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.maps.MapplsGetStyle.Builder
        public MapplsGetStyle.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f12724a = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.MapplsGetStyle.Builder
        public MapplsGetStyle.Builder logoResolution(String str) {
            Objects.requireNonNull(str, "Null logoResolution");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.MapplsGetStyle
    @NonNull
    public String b() {
        return this.b;
    }

    @Override // com.mappls.sdk.maps.MapplsGetStyle, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f12723a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsGetStyle) {
            MapplsGetStyle mapplsGetStyle = (MapplsGetStyle) obj;
            return this.f12723a.equals(mapplsGetStyle.baseUrl()) && this.b.equals(mapplsGetStyle.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f12723a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "MapplsGetStyle{baseUrl=" + this.f12723a + ", logoResolution=" + this.b + "}";
    }

    public f(String str, String str2) {
        this.f12723a = str;
        this.b = str2;
    }
}
