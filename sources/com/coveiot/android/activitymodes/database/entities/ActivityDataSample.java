package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"sessionID"}, entity = EntityWorkoutSession.class, onDelete = 5, onUpdate = 1, parentColumns = {WorkoutConstants.SESSION_ID})}, indices = {@Index({"sessionID", "segmentID"})}, primaryKeys = {"sessionID", "timestamp"}, tableName = "activity_data_sample")
/* loaded from: classes2.dex */
public final class ActivityDataSample extends Sample {

    /* renamed from: a  reason: collision with root package name */
    public int f2801a;
    @Nullable
    public String b;
    @NonNull
    @ColumnInfo(name = "activityType")
    @Nullable
    public String c;
    @NonNull
    @ColumnInfo(name = "timestamp")
    public long d;
    @ColumnInfo(name = "step_count")
    public int e;
    @ColumnInfo(name = "calories")
    public float f;
    @ColumnInfo(name = "distance")
    public int g;
    @ColumnInfo(name = "hrValue")
    public int h;
    @ColumnInfo(name = "speedValue")
    public float i;
    @ColumnInfo(name = "latitude")
    public double j;
    @ColumnInfo(name = "longitude")
    public double k;
    @NonNull
    @ColumnInfo(name = "sessionID")
    public String sessionID;

    public final boolean contains(@NotNull ArrayList<ActivityDataSample> sampleList, long j) {
        Intrinsics.checkNotNullParameter(sampleList, "sampleList");
        int size = sampleList.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            if (this.d / j == sampleList.get(i).d / j) {
                z = true;
            }
        }
        return z;
    }

    @Nullable
    public final String getActivityType() {
        return this.c;
    }

    public final float getCalories() {
        return this.f;
    }

    public final int getDistance() {
        return this.g;
    }

    public final int getHrValue() {
        return this.h;
    }

    public final double getLatitude() {
        return this.j;
    }

    public final double getLongitude() {
        return this.k;
    }

    @Nullable
    public final String getSegmentID() {
        return this.b;
    }

    @NotNull
    public final String getSessionID() {
        String str = this.sessionID;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sessionID");
        return null;
    }

    public final float getSpeedValue() {
        return this.i;
    }

    public final int getStepCount() {
        return this.e;
    }

    public final long getTimeStamp() {
        return this.d;
    }

    public final int get_id() {
        return this.f2801a;
    }

    public final void setActivityType(@Nullable String str) {
        this.c = str;
    }

    public final void setCalories(float f) {
        this.f = f;
    }

    public final void setDistance(int i) {
        this.g = i;
    }

    public final void setHrValue(int i) {
        this.h = i;
    }

    public final void setLatitude(double d) {
        this.j = d;
    }

    public final void setLongitude(double d) {
        this.k = d;
    }

    public final void setSegmentID(@Nullable String str) {
        this.b = str;
    }

    public final void setSessionID(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sessionID = str;
    }

    public final void setSpeedValue(float f) {
        this.i = f;
    }

    public final void setStepCount(int i) {
        this.e = i;
    }

    public final void setTimeStamp(long j) {
        this.d = j;
    }

    public final void set_id(int i) {
        this.f2801a = i;
    }
}
