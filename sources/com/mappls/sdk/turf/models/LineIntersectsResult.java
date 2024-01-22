package com.mappls.sdk.turf.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
@Keep
/* loaded from: classes8.dex */
public class LineIntersectsResult {
    private final Double horizontalIntersection;
    private final boolean onLine1;
    private final boolean onLine2;
    private final Double verticalIntersection;

    /* loaded from: classes8.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13319a;
        public Double b;
        public Boolean c;
        public Boolean d;

        public LineIntersectsResult build() {
            String str = "";
            if (this.c == null) {
                str = " onLine1";
            }
            if (this.d == null) {
                str = str + " onLine2";
            }
            if (str.isEmpty()) {
                return new LineIntersectsResult(this.f13319a, this.b, this.c.booleanValue(), this.d.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        public Builder horizontalIntersection(@Nullable Double d) {
            this.f13319a = d;
            return this;
        }

        public Builder onLine1(boolean z) {
            this.c = Boolean.valueOf(z);
            return this;
        }

        public Builder onLine2(boolean z) {
            this.d = Boolean.valueOf(z);
            return this;
        }

        public Builder verticalIntersection(@Nullable Double d) {
            this.b = d;
            return this;
        }

        public Builder() {
            Boolean bool = Boolean.FALSE;
            this.c = bool;
            this.d = bool;
        }

        public Builder(LineIntersectsResult lineIntersectsResult) {
            Boolean bool = Boolean.FALSE;
            this.c = bool;
            this.d = bool;
            this.f13319a = lineIntersectsResult.horizontalIntersection();
            this.b = lineIntersectsResult.verticalIntersection();
            this.c = Boolean.valueOf(lineIntersectsResult.onLine1());
            this.d = Boolean.valueOf(lineIntersectsResult.onLine2());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LineIntersectsResult) {
            LineIntersectsResult lineIntersectsResult = (LineIntersectsResult) obj;
            Double d = this.horizontalIntersection;
            if (d != null ? d.equals(lineIntersectsResult.horizontalIntersection()) : lineIntersectsResult.horizontalIntersection() == null) {
                Double d2 = this.verticalIntersection;
                if (d2 != null ? d2.equals(lineIntersectsResult.verticalIntersection()) : lineIntersectsResult.verticalIntersection() == null) {
                    if (this.onLine1 == lineIntersectsResult.onLine1() && this.onLine2 == lineIntersectsResult.onLine2()) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Double d = this.horizontalIntersection;
        int hashCode = ((d == null ? 0 : d.hashCode()) ^ 1000003) * 1000003;
        Double d2 = this.verticalIntersection;
        return ((((hashCode ^ (d2 != null ? d2.hashCode() : 0)) * 1000003) ^ (this.onLine1 ? 1231 : 1237)) * 1000003) ^ (this.onLine2 ? 1231 : 1237);
    }

    @Nullable
    public Double horizontalIntersection() {
        return this.horizontalIntersection;
    }

    public boolean onLine1() {
        return this.onLine1;
    }

    public boolean onLine2() {
        return this.onLine2;
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public String toString() {
        return "LineIntersectsResult{horizontalIntersection=" + this.horizontalIntersection + ", verticalIntersection=" + this.verticalIntersection + ", onLine1=" + this.onLine1 + ", onLine2=" + this.onLine2 + "}";
    }

    @Nullable
    public Double verticalIntersection() {
        return this.verticalIntersection;
    }

    private LineIntersectsResult(@Nullable Double d, @Nullable Double d2, boolean z, boolean z2) {
        this.horizontalIntersection = d;
        this.verticalIntersection = d2;
        this.onLine1 = z;
        this.onLine2 = z2;
    }
}
