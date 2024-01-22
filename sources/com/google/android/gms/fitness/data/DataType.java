package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "DataTypeCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public final class DataType extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_CALORIES_EXPENDED;
    @NonNull
    public static final DataType AGGREGATE_DISTANCE_DELTA;
    @NonNull
    public static final DataType AGGREGATE_HEART_POINTS;
    @NonNull
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_HEIGHT_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_HYDRATION;
    @NonNull
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX;
    @NonNull
    public static final DataType AGGREGATE_MOVE_MINUTES;
    @NonNull
    public static final DataType AGGREGATE_NUTRITION_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_POWER_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_SPEED_SUMMARY;
    @NonNull
    public static final DataType AGGREGATE_STEP_COUNT_DELTA;
    @NonNull
    public static final DataType AGGREGATE_WEIGHT_SUMMARY;
    @NonNull
    public static final Parcelable.Creator<DataType> CREATOR = new zzm();
    @NonNull
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
    @NonNull
    public static final DataType TYPE_ACTIVITY_SEGMENT;
    @NonNull
    public static final DataType TYPE_BASAL_METABOLIC_RATE;
    @NonNull
    public static final DataType TYPE_BODY_FAT_PERCENTAGE;
    @NonNull
    public static final DataType TYPE_CALORIES_EXPENDED;
    @NonNull
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE;
    @NonNull
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE;
    @NonNull
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION;
    @NonNull
    public static final DataType TYPE_CYCLING_WHEEL_RPM;
    @NonNull
    public static final DataType TYPE_DISTANCE_DELTA;
    @NonNull
    public static final DataType TYPE_HEART_POINTS;
    @NonNull
    public static final DataType TYPE_HEART_RATE_BPM;
    @NonNull
    public static final DataType TYPE_HEIGHT;
    @NonNull
    public static final DataType TYPE_HYDRATION;
    @NonNull
    public static final DataType TYPE_LOCATION_SAMPLE;
    @NonNull
    @Deprecated
    public static final DataType TYPE_LOCATION_TRACK;
    @NonNull
    public static final DataType TYPE_MOVE_MINUTES;
    @NonNull
    public static final DataType TYPE_NUTRITION;
    @NonNull
    public static final DataType TYPE_POWER_SAMPLE;
    @NonNull
    public static final DataType TYPE_SLEEP_SEGMENT;
    @NonNull
    public static final DataType TYPE_SPEED;
    @NonNull
    public static final DataType TYPE_STEP_COUNT_CADENCE;
    @NonNull
    @ShowFirstParty
    @KeepName
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE;
    @NonNull
    public static final DataType TYPE_STEP_COUNT_DELTA;
    @NonNull
    public static final DataType TYPE_WEIGHT;
    @NonNull
    public static final DataType TYPE_WORKOUT_EXERCISE;
    @NonNull
    @ShowFirstParty
    public static final DataType zzmd;
    @NonNull
    @ShowFirstParty
    public static final DataType zzme;
    @NonNull
    @ShowFirstParty
    public static final DataType zzmf;
    @NonNull
    public static final DataType zzmg;
    @NonNull
    @ShowFirstParty
    public static final DataType zzmh;
    @NonNull
    @ShowFirstParty
    public static final DataType zzmi;
    @NonNull
    @ShowFirstParty
    public static final DataType zzmj;
    @NonNull
    @Deprecated
    public static final DataType zzmk;
    @NonNull
    @Deprecated
    public static final DataType zzml;
    @NonNull
    @ShowFirstParty
    public static final DataType zzmm;
    @NonNull
    @ShowFirstParty
    public static final DataType zzmn;
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getFields", id = 2)
    public final List<Field> i;
    @Nullable
    @SafeParcelable.Field(getter = "getReadScope", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getWriteScope", id = 4)
    public final String k;

    static {
        Field field = Field.FIELD_STEPS;
        DataType dataType = new DataType("com.google.step_count.delta", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field);
        TYPE_STEP_COUNT_DELTA = dataType;
        TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field);
        Field field2 = Field.FIELD_RPM;
        TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field2);
        zzmd = new DataType("com.google.internal.goal", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zznd);
        Field field3 = Field.FIELD_ACTIVITY;
        TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field3);
        TYPE_SLEEP_SEGMENT = new DataType("com.google.sleep.segment", 2, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.FIELD_SLEEP_SEGMENT_TYPE);
        Field field4 = Field.FIELD_CALORIES;
        DataType dataType2 = new DataType("com.google.calories.expended", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field4);
        TYPE_CALORIES_EXPENDED = dataType2;
        TYPE_BASAL_METABOLIC_RATE = new DataType("com.google.calories.bmr", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field4);
        TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_WATTS);
        zzme = new DataType("com.google.sensor.events", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zznf, Field.zzng, Field.zznh);
        TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", 1, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.FIELD_BPM);
        zzmf = new DataType("com.google.respiratory_rate", 1, "https://www.googleapis.com/auth/fitness.respiratory_rate.read", "https://www.googleapis.com/auth/fitness.respiratory_rate.write", Field.zznc);
        Field field5 = Field.FIELD_LATITUDE;
        Field field6 = Field.FIELD_LONGITUDE;
        Field field7 = Field.FIELD_ACCURACY;
        Field field8 = Field.FIELD_ALTITUDE;
        TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", field5, field6, field7, field8);
        TYPE_LOCATION_TRACK = new DataType("com.google.location.track", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", field5, field6, field7, field8);
        Field field9 = Field.FIELD_DISTANCE;
        DataType dataType3 = new DataType("com.google.distance.delta", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", field9);
        TYPE_DISTANCE_DELTA = dataType3;
        zzmg = new DataType("com.google.distance.cumulative", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", field9);
        TYPE_SPEED = new DataType("com.google.speed", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_SPEED);
        Field field10 = Field.FIELD_REVOLUTIONS;
        TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", field10);
        TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", field2);
        TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field10);
        TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field2);
        TYPE_HEIGHT = new DataType("com.google.height", 1, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_HEIGHT);
        TYPE_WEIGHT = new DataType("com.google.weight", 1, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_WEIGHT);
        TYPE_BODY_FAT_PERCENTAGE = new DataType("com.google.body.fat.percentage", 1, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_PERCENTAGE);
        Field field11 = Field.FIELD_NUTRIENTS;
        Field field12 = Field.FIELD_MEAL_TYPE;
        TYPE_NUTRITION = new DataType("com.google.nutrition", 1, "https://www.googleapis.com/auth/fitness.nutrition.read", "https://www.googleapis.com/auth/fitness.nutrition.write", field11, field12, Field.FIELD_FOOD_ITEM);
        DataType dataType4 = new DataType("com.google.hydration", 1, "https://www.googleapis.com/auth/fitness.nutrition.read", "https://www.googleapis.com/auth/fitness.nutrition.write", Field.FIELD_VOLUME);
        TYPE_HYDRATION = dataType4;
        TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.zzmz, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE);
        Field field13 = Field.FIELD_DURATION;
        DataType dataType5 = new DataType("com.google.active_minutes", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field13);
        TYPE_MOVE_MINUTES = dataType5;
        AGGREGATE_MOVE_MINUTES = dataType5;
        zzmh = new DataType("com.google.device_on_body", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zznj);
        zzmi = new DataType("com.google.internal.primary_device", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzne);
        AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field3, field13, Field.FIELD_NUM_SEGMENTS);
        Field field14 = Field.FIELD_AVERAGE;
        Field field15 = Field.FIELD_MAX;
        Field field16 = Field.FIELD_MIN;
        AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", 2, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", field14, field15, field16);
        AGGREGATE_STEP_COUNT_DELTA = dataType;
        AGGREGATE_DISTANCE_DELTA = dataType3;
        AGGREGATE_CALORIES_EXPENDED = dataType2;
        Field field17 = Field.FIELD_INTENSITY;
        TYPE_HEART_POINTS = new DataType("com.google.heart_minutes", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field17);
        AGGREGATE_HEART_POINTS = new DataType("com.google.heart_minutes.summary", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field17, field13);
        AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", 2, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", field14, field15, field16);
        AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
        AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field14, field15, field16);
        AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", field14, field15, field16);
        AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", 2, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", field14, field15, field16);
        AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", 2, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", field14, field15, field16);
        AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", 2, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", field14, field15, field16);
        AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", 2, "https://www.googleapis.com/auth/fitness.nutrition.read", "https://www.googleapis.com/auth/fitness.nutrition.write", field11, field12);
        AGGREGATE_HYDRATION = dataType4;
        zzmj = new DataType("com.google.activity.samples", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzni);
        DataType dataType6 = new DataType("com.google.calories.consumed", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", field4);
        zzmk = dataType6;
        zzml = dataType6;
        zzmm = new DataType("com.google.internal.sleep_attributes", 2, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.zznk);
        zzmn = new DataType("com.google.internal.sleep_schedule", 1, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.zznl);
    }

    @ShowFirstParty
    public DataType(@NonNull String str, int i, @Nullable String str2, @Nullable String str3, @NonNull Field... fieldArr) {
        this.h = str;
        this.i = Collections.unmodifiableList(Arrays.asList(fieldArr));
        this.j = str2;
        this.k = str3;
    }

    @NonNull
    @Deprecated
    public static List<DataType> getAggregatesForInput(@NonNull DataType dataType) {
        DataType aggregateType = dataType.getAggregateType();
        if (aggregateType == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(aggregateType);
    }

    @NonNull
    public static String getMimeType(@NonNull DataType dataType) {
        String valueOf = String.valueOf(dataType.getName());
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataType) {
            DataType dataType = (DataType) obj;
            return this.h.equals(dataType.h) && this.i.equals(dataType.i);
        }
        return false;
    }

    @Nullable
    public final DataType getAggregateType() {
        return zzb.zzlc.get(this);
    }

    @NonNull
    public final List<Field> getFields() {
        return this.i;
    }

    @NonNull
    public final String getName() {
        return this.h;
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final int indexOf(@NonNull Field field) {
        int indexOf = this.i.indexOf(field);
        Preconditions.checkArgument(indexOf >= 0, "%s not a field of %s", field, this);
        return indexOf;
    }

    @NonNull
    public final String toString() {
        return String.format("DataType{%s%s}", this.h, this.i);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    @ShowFirstParty
    public final String zzk() {
        return this.j;
    }

    @Nullable
    @ShowFirstParty
    public final String zzl() {
        return this.k;
    }

    @NonNull
    @ShowFirstParty
    public final String zzm() {
        return this.h.startsWith("com.google.") ? this.h.substring(11) : this.h;
    }

    @SafeParcelable.Constructor
    public DataType(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) List<Field> list, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) String str3) {
        this.h = str;
        this.i = Collections.unmodifiableList(list);
        this.j = str2;
        this.k = str3;
    }
}
