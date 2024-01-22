package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapplsCovidLayerList;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class d extends MapplsCovidLayerList {

    /* renamed from: a  reason: collision with root package name */
    public final String f12719a;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsCovidLayerList.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12720a;

        @Override // com.mappls.sdk.maps.MapplsCovidLayerList.Builder
        public MapplsCovidLayerList autoBuild() {
            String str = "";
            if (this.f12720a == null) {
                str = " baseUrl";
            }
            if (str.isEmpty()) {
                return new d(this.f12720a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.maps.MapplsCovidLayerList.Builder
        public MapplsCovidLayerList.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f12720a = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.MapplsCovidLayerList, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f12719a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsCovidLayerList) {
            return this.f12719a.equals(((MapplsCovidLayerList) obj).baseUrl());
        }
        return false;
    }

    public int hashCode() {
        return this.f12719a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "MapplsCovidLayerList{baseUrl=" + this.f12719a + "}";
    }

    public d(String str) {
        this.f12719a = str;
    }
}
