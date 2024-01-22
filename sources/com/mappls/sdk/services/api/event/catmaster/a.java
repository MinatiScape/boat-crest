package com.mappls.sdk.services.api.event.catmaster;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.event.catmaster.MapplsCategoryMaster;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class a extends MapplsCategoryMaster {

    /* renamed from: a  reason: collision with root package name */
    public final String f13217a;

    /* loaded from: classes2.dex */
    public static final class b extends MapplsCategoryMaster.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13218a;

        @Override // com.mappls.sdk.services.api.event.catmaster.MapplsCategoryMaster.Builder
        public MapplsCategoryMaster autoBuild() {
            String str = "";
            if (this.f13218a == null) {
                str = " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f13218a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.event.catmaster.MapplsCategoryMaster.Builder
        public MapplsCategoryMaster.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13218a = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.event.catmaster.MapplsCategoryMaster, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13217a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsCategoryMaster) {
            return this.f13217a.equals(((MapplsCategoryMaster) obj).baseUrl());
        }
        return false;
    }

    public int hashCode() {
        return this.f13217a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "MapplsCategoryMaster{baseUrl=" + this.f13217a + "}";
    }

    public a(String str) {
        this.f13217a = str;
    }
}
