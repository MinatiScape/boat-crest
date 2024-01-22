package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"sess_id"}, entity = EntityWorkoutSession.class, onDelete = 5, onUpdate = 1, parentColumns = {WorkoutConstants.SESSION_ID})}, indices = {@Index({"sess_id"})}, tableName = "workout_session_segment")
/* loaded from: classes2.dex */
public final class EntityWorkoutSessionSegment {
    @NonNull
    @ColumnInfo(name = "sess_id")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f2811a;
    @ColumnInfo(name = "start_time")
    public long b;
    @ColumnInfo(name = "end_time")
    public long c;
    @ColumnInfo(name = "segment_duration")
    public int d;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "segment_id")
    public String segment_id;

    public final long getEnd_time() {
        return this.c;
    }

    public final int getSegment_duration() {
        return this.d;
    }

    @NotNull
    public final String getSegment_id() {
        String str = this.segment_id;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("segment_id");
        return null;
    }

    @Nullable
    public final String getSess_id() {
        return this.f2811a;
    }

    public final long getStart_time() {
        return this.b;
    }

    public final void setEnd_time(long j) {
        this.c = j;
    }

    public final void setSegment_duration(int i) {
        this.d = i;
    }

    public final void setSegment_id(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.segment_id = str;
    }

    public final void setSess_id(@Nullable String str) {
        this.f2811a = str;
    }

    public final void setStart_time(long j) {
        this.b = j;
    }
}
