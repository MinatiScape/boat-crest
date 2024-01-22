package com.coveiot.kheastapexdb.walk;

import androidx.room.Entity;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Entity(primaryKeys = {"epochTimeStamp", DeviceKey.MacAddress}, tableName = "entity_ea_steps")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b,\u0010-R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\"\u0010\u001a\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\"\u0010\u001d\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0012\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\"\u0010 \u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\"\u0010#\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010\u0004\u001a\u0004\b$\u0010\u0006\"\u0004\b%\u0010\bR\"\u0010'\u001a\u00020&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b'\u0010)\"\u0004\b*\u0010+¨\u0006."}, d2 = {"Lcom/coveiot/kheastapexdb/walk/EntityEAStepsData;", "", "", "epochTimeStamp", "J", "getEpochTimeStamp", "()J", "setEpochTimeStamp", "(J)V", "", DeviceKey.MacAddress, "Ljava/lang/String;", "getMacAddress", "()Ljava/lang/String;", "setMacAddress", "(Ljava/lang/String;)V", "", "steps", "I", "getSteps", "()I", "setSteps", "(I)V", "calorie", "getCalorie", "setCalorie", "distance", "getDistance", "setDistance", "duration", "getDuration", "setDuration", "avgHeartRate", "getAvgHeartRate", "setAvgHeartRate", "timeStamp", "getTimeStamp", "setTimeStamp", "", "isProcessed", "Z", "()Z", "setProcessed", "(Z)V", "<init>", "(JLjava/lang/String;)V", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class EntityEAStepsData {

    /* renamed from: a  reason: collision with root package name */
    public long f7076a;
    @NotNull
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public long h;
    public boolean i;

    public EntityEAStepsData(long j, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7076a = j;
        this.b = macAddress;
    }

    public final int getAvgHeartRate() {
        return this.g;
    }

    public final int getCalorie() {
        return this.d;
    }

    public final int getDistance() {
        return this.e;
    }

    public final int getDuration() {
        return this.f;
    }

    public final long getEpochTimeStamp() {
        return this.f7076a;
    }

    @NotNull
    public final String getMacAddress() {
        return this.b;
    }

    public final int getSteps() {
        return this.c;
    }

    public final long getTimeStamp() {
        return this.h;
    }

    public final boolean isProcessed() {
        return this.i;
    }

    public final void setAvgHeartRate(int i) {
        this.g = i;
    }

    public final void setCalorie(int i) {
        this.d = i;
    }

    public final void setDistance(int i) {
        this.e = i;
    }

    public final void setDuration(int i) {
        this.f = i;
    }

    public final void setEpochTimeStamp(long j) {
        this.f7076a = j;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setProcessed(boolean z) {
        this.i = z;
    }

    public final void setSteps(int i) {
        this.c = i;
    }

    public final void setTimeStamp(long j) {
        this.h = j;
    }
}
