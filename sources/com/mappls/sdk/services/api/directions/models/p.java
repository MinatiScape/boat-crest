package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.StepManeuver;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class p extends StepManeuver {
    private final Double bearingAfter;
    private final Double bearingBefore;
    private final Double degree;
    private final Integer exit;
    private final String instruction;
    private final Integer maneuverId;
    private final String modifier;
    private final double[] rawLocation;
    private final String shortInstruction;
    private final String type;

    /* loaded from: classes11.dex */
    public static class b extends StepManeuver.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13201a;
        public double[] b;
        public Double c;
        public Double d;
        public String e;
        public String f;
        public Integer g;
        public String h;
        public String i;
        public Integer j;

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder bearingAfter(@Nullable Double d) {
            this.d = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder bearingBefore(@Nullable Double d) {
            this.c = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver build() {
            String str = "";
            if (this.b == null) {
                str = " rawLocation";
            }
            if (str.isEmpty()) {
                return new AutoValue_StepManeuver(this.f13201a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder degree(@Nullable Double d) {
            this.f13201a = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder exit(@Nullable Integer num) {
            this.j = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder instruction(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder maneuverId(@Nullable Integer num) {
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder modifier(@Nullable String str) {
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder rawLocation(double[] dArr) {
            Objects.requireNonNull(dArr, "Null rawLocation");
            this.b = dArr;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder shortInstruction(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.StepManeuver.Builder
        public StepManeuver.Builder type(@Nullable String str) {
            this.h = str;
            return this;
        }

        public b() {
        }

        public b(StepManeuver stepManeuver) {
            this.f13201a = stepManeuver.degree();
            this.b = stepManeuver.rawLocation();
            this.c = stepManeuver.bearingBefore();
            this.d = stepManeuver.bearingAfter();
            this.e = stepManeuver.instruction();
            this.f = stepManeuver.shortInstruction();
            this.g = stepManeuver.maneuverId();
            this.h = stepManeuver.type();
            this.i = stepManeuver.modifier();
            this.j = stepManeuver.exit();
        }
    }

    public p(@Nullable Double d, double[] dArr, @Nullable Double d2, @Nullable Double d3, @Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable Integer num2) {
        this.degree = d;
        Objects.requireNonNull(dArr, "Null rawLocation");
        this.rawLocation = dArr;
        this.bearingBefore = d2;
        this.bearingAfter = d3;
        this.instruction = str;
        this.shortInstruction = str2;
        this.maneuverId = num;
        this.type = str3;
        this.modifier = str4;
        this.exit = num2;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    @SerializedName("bearing_after")
    public Double bearingAfter() {
        return this.bearingAfter;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    @SerializedName("bearing_before")
    public Double bearingBefore() {
        return this.bearingBefore;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    @SerializedName("degree")
    public Double degree() {
        return this.degree;
    }

    public boolean equals(Object obj) {
        Double d;
        Double d2;
        String str;
        String str2;
        Integer num;
        String str3;
        String str4;
        if (obj == this) {
            return true;
        }
        if (obj instanceof StepManeuver) {
            StepManeuver stepManeuver = (StepManeuver) obj;
            Double d3 = this.degree;
            if (d3 != null ? d3.equals(stepManeuver.degree()) : stepManeuver.degree() == null) {
                if (Arrays.equals(this.rawLocation, stepManeuver instanceof p ? ((p) stepManeuver).rawLocation : stepManeuver.rawLocation()) && ((d = this.bearingBefore) != null ? d.equals(stepManeuver.bearingBefore()) : stepManeuver.bearingBefore() == null) && ((d2 = this.bearingAfter) != null ? d2.equals(stepManeuver.bearingAfter()) : stepManeuver.bearingAfter() == null) && ((str = this.instruction) != null ? str.equals(stepManeuver.instruction()) : stepManeuver.instruction() == null) && ((str2 = this.shortInstruction) != null ? str2.equals(stepManeuver.shortInstruction()) : stepManeuver.shortInstruction() == null) && ((num = this.maneuverId) != null ? num.equals(stepManeuver.maneuverId()) : stepManeuver.maneuverId() == null) && ((str3 = this.type) != null ? str3.equals(stepManeuver.type()) : stepManeuver.type() == null) && ((str4 = this.modifier) != null ? str4.equals(stepManeuver.modifier()) : stepManeuver.modifier() == null)) {
                    Integer num2 = this.exit;
                    if (num2 == null) {
                        if (stepManeuver.exit() == null) {
                            return true;
                        }
                    } else if (num2.equals(stepManeuver.exit())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    public Integer exit() {
        return this.exit;
    }

    public int hashCode() {
        Double d = this.degree;
        int hashCode = ((((d == null ? 0 : d.hashCode()) ^ 1000003) * 1000003) ^ Arrays.hashCode(this.rawLocation)) * 1000003;
        Double d2 = this.bearingBefore;
        int hashCode2 = (hashCode ^ (d2 == null ? 0 : d2.hashCode())) * 1000003;
        Double d3 = this.bearingAfter;
        int hashCode3 = (hashCode2 ^ (d3 == null ? 0 : d3.hashCode())) * 1000003;
        String str = this.instruction;
        int hashCode4 = (hashCode3 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.shortInstruction;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Integer num = this.maneuverId;
        int hashCode6 = (hashCode5 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str3 = this.type;
        int hashCode7 = (hashCode6 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.modifier;
        int hashCode8 = (hashCode7 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        Integer num2 = this.exit;
        return hashCode8 ^ (num2 != null ? num2.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    public String instruction() {
        return this.instruction;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    @SerializedName("maneuver_id")
    public Integer maneuverId() {
        return this.maneuverId;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    public String modifier() {
        return this.modifier;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @NonNull
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    public double[] rawLocation() {
        return this.rawLocation;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    @SerializedName("short_instruction")
    public String shortInstruction() {
        return this.shortInstruction;
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    public StepManeuver.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "StepManeuver{degree=" + this.degree + ", rawLocation=" + Arrays.toString(this.rawLocation) + ", bearingBefore=" + this.bearingBefore + ", bearingAfter=" + this.bearingAfter + ", instruction=" + this.instruction + ", shortInstruction=" + this.shortInstruction + ", maneuverId=" + this.maneuverId + ", type=" + this.type + ", modifier=" + this.modifier + ", exit=" + this.exit + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.StepManeuver
    @Nullable
    public String type() {
        return this.type;
    }
}
