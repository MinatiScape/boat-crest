package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"sess_id"}, entity = EntityWorkoutSession.class, onDelete = 5, onUpdate = 1, parentColumns = {WorkoutConstants.SESSION_ID})}, indices = {@Index({"sess_id", "seg_id"})}, primaryKeys = {"sess_id", "timestamp"}, tableName = "walk_sample")
/* loaded from: classes2.dex */
public final class WalkSample extends Sample {

    /* renamed from: a  reason: collision with root package name */
    public int f2823a;
    @Nullable
    public String b;
    @ColumnInfo(name = "step_count")
    public int c;
    @Embedded
    @NotNull
    public SampleData d = new SampleData();
    @NonNull
    @ColumnInfo(name = "sess_id")
    public String sess_id;

    public final boolean contains(@NotNull ArrayList<WalkSample> sampleList, long j) {
        Intrinsics.checkNotNullParameter(sampleList, "sampleList");
        int size = sampleList.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            if (this.d.getTimeStamp() / j == sampleList.get(i).d.getTimeStamp() / j) {
                z = true;
            }
        }
        return z;
    }

    @NotNull
    public final SampleData getSampleData() {
        return this.d;
    }

    @Nullable
    public final String getSeg_id() {
        return this.b;
    }

    @NotNull
    public final String getSess_id() {
        String str = this.sess_id;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sess_id");
        return null;
    }

    public final int getStepCount() {
        return this.c;
    }

    public final int get_id() {
        return this.f2823a;
    }

    public final void setSampleData(@NotNull SampleData sampleData) {
        Intrinsics.checkNotNullParameter(sampleData, "<set-?>");
        this.d = sampleData;
    }

    public final void setSeg_id(@Nullable String str) {
        this.b = str;
    }

    public final void setSess_id(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sess_id = str;
    }

    public final void setStepCount(int i) {
        this.c = i;
    }

    public final void set_id(int i) {
        this.f2823a = i;
    }
}
