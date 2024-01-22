package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.IntersectionLanes;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class h extends IntersectionLanes {
    private final List<String> indications;
    private final Boolean valid;

    /* loaded from: classes11.dex */
    public static class b extends IntersectionLanes.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Boolean f13193a;
        public List<String> b;

        @Override // com.mappls.sdk.services.api.directions.models.IntersectionLanes.Builder
        public IntersectionLanes build() {
            return new AutoValue_IntersectionLanes(this.f13193a, this.b);
        }

        @Override // com.mappls.sdk.services.api.directions.models.IntersectionLanes.Builder
        public IntersectionLanes.Builder indications(@Nullable List<String> list) {
            this.b = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.IntersectionLanes.Builder
        public IntersectionLanes.Builder valid(@Nullable Boolean bool) {
            this.f13193a = bool;
            return this;
        }

        public b() {
        }

        public b(IntersectionLanes intersectionLanes) {
            this.f13193a = intersectionLanes.valid();
            this.b = intersectionLanes.indications();
        }
    }

    public h(@Nullable Boolean bool, @Nullable List<String> list) {
        this.valid = bool;
        this.indications = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof IntersectionLanes) {
            IntersectionLanes intersectionLanes = (IntersectionLanes) obj;
            Boolean bool = this.valid;
            if (bool != null ? bool.equals(intersectionLanes.valid()) : intersectionLanes.valid() == null) {
                List<String> list = this.indications;
                if (list == null) {
                    if (intersectionLanes.indications() == null) {
                        return true;
                    }
                } else if (list.equals(intersectionLanes.indications())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Boolean bool = this.valid;
        int hashCode = ((bool == null ? 0 : bool.hashCode()) ^ 1000003) * 1000003;
        List<String> list = this.indications;
        return hashCode ^ (list != null ? list.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.IntersectionLanes
    @Nullable
    public List<String> indications() {
        return this.indications;
    }

    @Override // com.mappls.sdk.services.api.directions.models.IntersectionLanes
    public IntersectionLanes.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "IntersectionLanes{valid=" + this.valid + ", indications=" + this.indications + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.IntersectionLanes
    @Nullable
    public Boolean valid() {
        return this.valid;
    }
}
