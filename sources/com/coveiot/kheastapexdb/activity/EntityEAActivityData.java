package com.coveiot.kheastapexdb.activity;

import androidx.room.Entity;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Entity(primaryKeys = {"beginTimestamp", DeviceKey.MacAddress}, tableName = "entity_ea_activity")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b*\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b`\u0010aR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\"\u0010\u001a\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\"\u0010\u001d\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0012\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\"\u0010 \u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\"\u0010#\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010\u0012\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\"\u0010&\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0012\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R\"\u0010)\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010\u0012\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010\u0016R\"\u0010,\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010\u0012\u001a\u0004\b-\u0010\u0014\"\u0004\b.\u0010\u0016R\"\u0010/\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u0010\u0012\u001a\u0004\b0\u0010\u0014\"\u0004\b1\u0010\u0016R\"\u00102\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u0010\u0012\u001a\u0004\b3\u0010\u0014\"\u0004\b4\u0010\u0016R\"\u00105\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u0010\u0012\u001a\u0004\b6\u0010\u0014\"\u0004\b7\u0010\u0016R\"\u00108\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u0010\u0012\u001a\u0004\b9\u0010\u0014\"\u0004\b:\u0010\u0016R\"\u0010<\u001a\u00020;8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010B\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bB\u0010\u0012\u001a\u0004\bC\u0010\u0014\"\u0004\bD\u0010\u0016R\"\u0010E\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010\u0012\u001a\u0004\bF\u0010\u0014\"\u0004\bG\u0010\u0016R\"\u0010H\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u0010\u0012\u001a\u0004\bI\u0010\u0014\"\u0004\bJ\u0010\u0016R\"\u0010K\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u0010\u0012\u001a\u0004\bL\u0010\u0014\"\u0004\bM\u0010\u0016R\"\u0010N\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u0010\u0012\u001a\u0004\bO\u0010\u0014\"\u0004\bP\u0010\u0016R\"\u0010Q\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u0010\u0012\u001a\u0004\bR\u0010\u0014\"\u0004\bS\u0010\u0016R\"\u0010T\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bT\u0010\u0012\u001a\u0004\bU\u0010\u0014\"\u0004\bV\u0010\u0016R\"\u0010W\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bW\u0010\u0004\u001a\u0004\bX\u0010\u0006\"\u0004\bY\u0010\bR\"\u0010[\u001a\u00020Z8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b[\u0010\\\u001a\u0004\b[\u0010]\"\u0004\b^\u0010_¨\u0006b"}, d2 = {"Lcom/coveiot/kheastapexdb/activity/EntityEAActivityData;", "", "", "beginTimestamp", "J", "getBeginTimestamp", "()J", "setBeginTimestamp", "(J)V", "", DeviceKey.MacAddress, "Ljava/lang/String;", "getMacAddress", "()Ljava/lang/String;", "setMacAddress", "(Ljava/lang/String;)V", "", "activityType", "I", "getActivityType", "()I", "setActivityType", "(I)V", "endTimestamp", "getEndTimestamp", "setEndTimestamp", "steps", "getSteps", "setSteps", "calorie", "getCalorie", "setCalorie", "distance", "getDistance", "setDistance", "duration", "getDuration", "setDuration", "trainingEffectNormal", "getTrainingEffectNormal", "setTrainingEffectNormal", "trainingEffectWarmUp", "getTrainingEffectWarmUp", "setTrainingEffectWarmUp", "trainingEffectFatConsumption", "getTrainingEffectFatConsumption", "setTrainingEffectFatConsumption", "trainingEffectAerobic", "getTrainingEffectAerobic", "setTrainingEffectAerobic", "trainingEffectAnaerobic", "getTrainingEffectAnaerobic", "setTrainingEffectAnaerobic", "trainingEffectLimit", "getTrainingEffectLimit", "setTrainingEffectLimit", "avgHeartRate", "getAvgHeartRate", "setAvgHeartRate", "", "avgTemperature", WeatherCriteria.UNIT_FARENHEIT, "getAvgTemperature", "()F", "setAvgTemperature", "(F)V", "avgSpeed", "getAvgSpeed", "setAvgSpeed", "avgPace", "getAvgPace", "setAvgPace", "avgStepFreq", "getAvgStepFreq", "setAvgStepFreq", "avgStride", "getAvgStride", "setAvgStride", "avgAltitude", "getAvgAltitude", "setAvgAltitude", "maxHeartRate", "getMaxHeartRate", "setMaxHeartRate", "minHeartRate", "getMinHeartRate", "setMinHeartRate", "timeStamp", "getTimeStamp", "setTimeStamp", "", "isProcessed", "Z", "()Z", "setProcessed", "(Z)V", "<init>", "(JLjava/lang/String;)V", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class EntityEAActivityData {

    /* renamed from: a  reason: collision with root package name */
    public long f7055a;
    @NotNull
    public String b;
    public int c;
    public long d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public float p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public long x;
    public boolean y;

    public EntityEAActivityData(long j, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7055a = j;
        this.b = macAddress;
    }

    public final int getActivityType() {
        return this.c;
    }

    public final int getAvgAltitude() {
        return this.u;
    }

    public final int getAvgHeartRate() {
        return this.o;
    }

    public final int getAvgPace() {
        return this.r;
    }

    public final int getAvgSpeed() {
        return this.q;
    }

    public final int getAvgStepFreq() {
        return this.s;
    }

    public final int getAvgStride() {
        return this.t;
    }

    public final float getAvgTemperature() {
        return this.p;
    }

    public final long getBeginTimestamp() {
        return this.f7055a;
    }

    public final int getCalorie() {
        return this.f;
    }

    public final int getDistance() {
        return this.g;
    }

    public final int getDuration() {
        return this.h;
    }

    public final long getEndTimestamp() {
        return this.d;
    }

    @NotNull
    public final String getMacAddress() {
        return this.b;
    }

    public final int getMaxHeartRate() {
        return this.v;
    }

    public final int getMinHeartRate() {
        return this.w;
    }

    public final int getSteps() {
        return this.e;
    }

    public final long getTimeStamp() {
        return this.x;
    }

    public final int getTrainingEffectAerobic() {
        return this.l;
    }

    public final int getTrainingEffectAnaerobic() {
        return this.m;
    }

    public final int getTrainingEffectFatConsumption() {
        return this.k;
    }

    public final int getTrainingEffectLimit() {
        return this.n;
    }

    public final int getTrainingEffectNormal() {
        return this.i;
    }

    public final int getTrainingEffectWarmUp() {
        return this.j;
    }

    public final boolean isProcessed() {
        return this.y;
    }

    public final void setActivityType(int i) {
        this.c = i;
    }

    public final void setAvgAltitude(int i) {
        this.u = i;
    }

    public final void setAvgHeartRate(int i) {
        this.o = i;
    }

    public final void setAvgPace(int i) {
        this.r = i;
    }

    public final void setAvgSpeed(int i) {
        this.q = i;
    }

    public final void setAvgStepFreq(int i) {
        this.s = i;
    }

    public final void setAvgStride(int i) {
        this.t = i;
    }

    public final void setAvgTemperature(float f) {
        this.p = f;
    }

    public final void setBeginTimestamp(long j) {
        this.f7055a = j;
    }

    public final void setCalorie(int i) {
        this.f = i;
    }

    public final void setDistance(int i) {
        this.g = i;
    }

    public final void setDuration(int i) {
        this.h = i;
    }

    public final void setEndTimestamp(long j) {
        this.d = j;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setMaxHeartRate(int i) {
        this.v = i;
    }

    public final void setMinHeartRate(int i) {
        this.w = i;
    }

    public final void setProcessed(boolean z) {
        this.y = z;
    }

    public final void setSteps(int i) {
        this.e = i;
    }

    public final void setTimeStamp(long j) {
        this.x = j;
    }

    public final void setTrainingEffectAerobic(int i) {
        this.l = i;
    }

    public final void setTrainingEffectAnaerobic(int i) {
        this.m = i;
    }

    public final void setTrainingEffectFatConsumption(int i) {
        this.k = i;
    }

    public final void setTrainingEffectLimit(int i) {
        this.n = i;
    }

    public final void setTrainingEffectNormal(int i) {
        this.i = i;
    }

    public final void setTrainingEffectWarmUp(int i) {
        this.j = i;
    }
}
