package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
@SafeParcelable.Class(creator = "FieldCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public final class Field extends AbstractSafeParcelable {
    @NonNull
    public static final Field FIELD_ACCURACY;
    @NonNull
    public static final Field FIELD_ALTITUDE;
    @NonNull
    public static final Field FIELD_AVERAGE;
    @NonNull
    public static final Field FIELD_BPM;
    @NonNull
    public static final Field FIELD_CALORIES;
    @NonNull
    @Deprecated
    public static final Field FIELD_CIRCUMFERENCE;
    @NonNull
    public static final Field FIELD_DISTANCE;
    @NonNull
    public static final Field FIELD_EXERCISE;
    @NonNull
    public static final Field FIELD_FOOD_ITEM;
    @NonNull
    public static final Field FIELD_HEIGHT;
    @NonNull
    public static final Field FIELD_HIGH_LATITUDE;
    @NonNull
    public static final Field FIELD_HIGH_LONGITUDE;
    @NonNull
    public static final Field FIELD_INTENSITY;
    @NonNull
    public static final Field FIELD_LATITUDE;
    @NonNull
    public static final Field FIELD_LONGITUDE;
    @NonNull
    public static final Field FIELD_LOW_LATITUDE;
    @NonNull
    public static final Field FIELD_LOW_LONGITUDE;
    @NonNull
    public static final Field FIELD_MAX;
    @NonNull
    public static final Field FIELD_MEAL_TYPE;
    @NonNull
    public static final Field FIELD_MIN;
    @NonNull
    public static final Field FIELD_NUM_SEGMENTS;
    @NonNull
    public static final Field FIELD_NUTRIENTS;
    @NonNull
    public static final Field FIELD_OCCURRENCES;
    @NonNull
    public static final Field FIELD_PERCENTAGE;
    @NonNull
    public static final Field FIELD_REPETITIONS;
    @NonNull
    public static final Field FIELD_RESISTANCE;
    @NonNull
    public static final Field FIELD_RESISTANCE_TYPE;
    @NonNull
    public static final Field FIELD_REVOLUTIONS;
    @NonNull
    public static final Field FIELD_RPM;
    @NonNull
    public static final Field FIELD_SPEED;
    @NonNull
    public static final Field FIELD_VOLUME;
    @NonNull
    public static final Field FIELD_WATTS;
    @NonNull
    public static final Field FIELD_WEIGHT;
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    public static final int FORMAT_MAP = 4;
    public static final int FORMAT_STRING = 3;
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_SNACK = 4;
    public static final int MEAL_TYPE_UNKNOWN = 0;
    @NonNull
    public static final String NUTRIENT_CALCIUM = "calcium";
    @NonNull
    public static final String NUTRIENT_CALORIES = "calories";
    @NonNull
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    @NonNull
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    @NonNull
    public static final String NUTRIENT_IRON = "iron";
    @NonNull
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    @NonNull
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    @NonNull
    public static final String NUTRIENT_POTASSIUM = "potassium";
    @NonNull
    public static final String NUTRIENT_PROTEIN = "protein";
    @NonNull
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    @NonNull
    public static final String NUTRIENT_SODIUM = "sodium";
    @NonNull
    public static final String NUTRIENT_SUGAR = "sugar";
    @NonNull
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    @NonNull
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    @NonNull
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    @NonNull
    public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
    @NonNull
    public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
    @NonNull
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int RESISTANCE_TYPE_BARBELL = 1;
    public static final int RESISTANCE_TYPE_BODY = 6;
    public static final int RESISTANCE_TYPE_CABLE = 2;
    public static final int RESISTANCE_TYPE_DUMBBELL = 3;
    public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
    public static final int RESISTANCE_TYPE_MACHINE = 5;
    public static final int RESISTANCE_TYPE_UNKNOWN = 0;
    @NonNull
    @ShowFirstParty
    public static final Field zznc;
    @NonNull
    @ShowFirstParty
    public static final Field zznd;
    @NonNull
    @ShowFirstParty
    public static final Field zzne;
    @NonNull
    @ShowFirstParty
    public static final Field zznf;
    @NonNull
    @ShowFirstParty
    public static final Field zzng;
    @NonNull
    @ShowFirstParty
    public static final Field zznh;
    @NonNull
    @ShowFirstParty
    public static final Field zzni;
    @NonNull
    @ShowFirstParty
    public static final Field zznj;
    @NonNull
    @ShowFirstParty
    public static final Field zznk;
    @NonNull
    @ShowFirstParty
    public static final Field zznl;
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getFormat", id = 2)
    public final int i;
    @Nullable
    @SafeParcelable.Field(getter = "isOptional", id = 3)
    public final Boolean j;
    @NonNull
    public static final Parcelable.Creator<Field> CREATOR = new zzr();
    @NonNull
    public static final Field FIELD_ACTIVITY = a("activity");
    @NonNull
    public static final Field FIELD_SLEEP_SEGMENT_TYPE = a("sleep_segment_type");
    @NonNull
    public static final Field FIELD_CONFIDENCE = zze("confidence");
    @NonNull
    public static final Field FIELD_STEPS = a("steps");
    @NonNull
    @Deprecated
    public static final Field FIELD_STEP_LENGTH = zze("step_length");
    @NonNull
    public static final Field FIELD_DURATION = a("duration");
    @NonNull
    @ShowFirstParty
    public static final Field zzmz = zzd("duration");

    static {
        c("activity_duration.ascending");
        c("activity_duration.descending");
        FIELD_BPM = zze("bpm");
        zznc = zze("respiratory_rate");
        FIELD_LATITUDE = zze("latitude");
        FIELD_LONGITUDE = zze("longitude");
        FIELD_ACCURACY = zze("accuracy");
        FIELD_ALTITUDE = b(SavingTrackHelper.TRACK_COL_ALTITUDE);
        FIELD_DISTANCE = zze("distance");
        FIELD_HEIGHT = zze(Property.ICON_TEXT_FIT_HEIGHT);
        FIELD_WEIGHT = zze("weight");
        FIELD_PERCENTAGE = zze("percentage");
        FIELD_SPEED = zze("speed");
        FIELD_RPM = zze("rpm");
        zznd = d("google.android.fitness.GoalV2");
        zzne = d("google.android.fitness.Device");
        FIELD_REVOLUTIONS = a("revolutions");
        FIELD_CALORIES = zze("calories");
        FIELD_WATTS = zze("watts");
        FIELD_VOLUME = zze("volume");
        FIELD_MEAL_TYPE = zzd("meal_type");
        FIELD_FOOD_ITEM = new Field("food_item", 3, Boolean.TRUE);
        FIELD_NUTRIENTS = c("nutrients");
        FIELD_EXERCISE = new Field("exercise", 3);
        FIELD_REPETITIONS = zzd("repetitions");
        FIELD_RESISTANCE = b("resistance");
        FIELD_RESISTANCE_TYPE = zzd("resistance_type");
        FIELD_NUM_SEGMENTS = a("num_segments");
        FIELD_AVERAGE = zze("average");
        FIELD_MAX = zze(Constants.PRIORITY_MAX);
        FIELD_MIN = zze("min");
        FIELD_LOW_LATITUDE = zze("low_latitude");
        FIELD_LOW_LONGITUDE = zze("low_longitude");
        FIELD_HIGH_LATITUDE = zze("high_latitude");
        FIELD_HIGH_LONGITUDE = zze("high_longitude");
        FIELD_OCCURRENCES = a("occurrences");
        zznf = a("sensor_type");
        zzng = new Field("timestamps", 5);
        zznh = new Field("sensor_values", 6);
        FIELD_INTENSITY = zze("intensity");
        zzni = c("activity_confidence");
        zznj = zze("probability");
        zznk = d("google.android.fitness.SleepAttributes");
        zznl = d("google.android.fitness.SleepSchedule");
        FIELD_CIRCUMFERENCE = zze("circumference");
    }

    @ShowFirstParty
    public Field(@NonNull String str, int i) {
        this(str, i, null);
    }

    @ShowFirstParty
    public static Field a(String str) {
        return new Field(str, 1);
    }

    @ShowFirstParty
    public static Field b(String str) {
        return new Field(str, 2, Boolean.TRUE);
    }

    @ShowFirstParty
    public static Field c(String str) {
        return new Field(str, 4);
    }

    @ShowFirstParty
    public static Field d(String str) {
        return new Field(str, 7);
    }

    @NonNull
    @ShowFirstParty
    public static Field zzd(@NonNull String str) {
        return new Field(str, 1, Boolean.TRUE);
    }

    @NonNull
    @ShowFirstParty
    public static Field zze(@NonNull String str) {
        return new Field(str, 2);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Field) {
            Field field = (Field) obj;
            return this.h.equals(field.h) && this.i == field.i;
        }
        return false;
    }

    public final int getFormat() {
        return this.i;
    }

    @NonNull
    public final String getName() {
        return this.h;
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    @Nullable
    public final Boolean isOptional() {
        return this.j;
    }

    @NonNull
    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.h;
        objArr[1] = this.i == 1 ? "i" : "f";
        return String.format("%s(%s)", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, getFormat());
        SafeParcelWriter.writeBooleanObject(parcel, 3, isOptional(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public Field(@NonNull @SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) int i, @Nullable @SafeParcelable.Param(id = 3) Boolean bool) {
        this.h = (String) Preconditions.checkNotNull(str);
        this.i = i;
        this.j = bool;
    }
}
