package com.coveiot.android.respiratoryrate.database.entities;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateData {
    @SerializedName("accuracies")
    @Nullable
    private ArrayList<Float> accuracies;
    @SerializedName("avg")
    @Nullable
    private Integer avg;
    @SerializedName("baseUnit")
    @Nullable
    private String baseUnit;
    @SerializedName("baseUnits")
    @Nullable
    private BaseUnits baseUnits;
    @SerializedName("computedDate")
    @Nullable
    private String computedDate;
    @SerializedName("date")
    @Nullable
    private String date;
    @SerializedName(Constants.PRIORITY_MAX)
    @Nullable
    private Integer max;
    @SerializedName("min")
    @Nullable
    private Integer min;
    @SerializedName("source")
    @Nullable
    private Source source;
    @SerializedName("tzOffset")
    @Nullable
    private String tzOffset;
    @SerializedName("values")
    @Nullable
    private ArrayList<Integer> values;

    /* loaded from: classes6.dex */
    public final class BaseUnits {
        @SerializedName("accuracy")
        @Nullable
        private String accuracy;

        public BaseUnits() {
        }

        @Nullable
        public final String getAccuracy() {
            return this.accuracy;
        }

        public final void setAccuracy(@Nullable String str) {
            this.accuracy = str;
        }
    }

    /* loaded from: classes6.dex */
    public final class Source {
        @SerializedName("identifier")
        @Nullable
        private String identifier;
        @SerializedName("type")
        @Nullable
        private String type;

        public Source() {
        }

        @Nullable
        public final String getIdentifier() {
            return this.identifier;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        public final void setIdentifier(@Nullable String str) {
            this.identifier = str;
        }

        public final void setType(@Nullable String str) {
            this.type = str;
        }
    }

    @Nullable
    public final ArrayList<Float> getAccuracies() {
        return this.accuracies;
    }

    @Nullable
    public final Integer getAvg() {
        return this.avg;
    }

    @Nullable
    public final String getBaseUnit() {
        return this.baseUnit;
    }

    @Nullable
    public final BaseUnits getBaseUnits() {
        return this.baseUnits;
    }

    @Nullable
    public final String getComputedDate() {
        return this.computedDate;
    }

    @Nullable
    public final String getDate() {
        return this.date;
    }

    @Nullable
    public final Integer getMax() {
        return this.max;
    }

    @Nullable
    public final Integer getMin() {
        return this.min;
    }

    @Nullable
    public final Source getSource() {
        return this.source;
    }

    @Nullable
    public final String getTzOffset() {
        return this.tzOffset;
    }

    @Nullable
    public final ArrayList<Integer> getValues() {
        return this.values;
    }

    public final void setAccuracies(@Nullable ArrayList<Float> arrayList) {
        this.accuracies = arrayList;
    }

    public final void setAvg(@Nullable Integer num) {
        this.avg = num;
    }

    public final void setBaseUnit(@Nullable String str) {
        this.baseUnit = str;
    }

    public final void setBaseUnits(@Nullable BaseUnits baseUnits) {
        this.baseUnits = baseUnits;
    }

    public final void setComputedDate(@Nullable String str) {
        this.computedDate = str;
    }

    public final void setDate(@Nullable String str) {
        this.date = str;
    }

    public final void setMax(@Nullable Integer num) {
        this.max = num;
    }

    public final void setMin(@Nullable Integer num) {
        this.min = num;
    }

    public final void setSource(@Nullable Source source) {
        this.source = source;
    }

    public final void setTzOffset(@Nullable String str) {
        this.tzOffset = str;
    }

    public final void setValues(@Nullable ArrayList<Integer> arrayList) {
        this.values = arrayList;
    }
}
