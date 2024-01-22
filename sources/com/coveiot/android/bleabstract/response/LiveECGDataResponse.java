package com.coveiot.android.bleabstract.response;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class LiveECGDataResponse implements Cloneable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Long f3639a;
    @Nullable
    public String b;
    @NotNull
    public List<Integer> c = new ArrayList();
    @NotNull
    public List<Float> d = new ArrayList();
    @Nullable
    public String e;
    @Nullable
    public String f;
    public int g;
    public int h;

    @Nullable
    public final String getDataType() {
        return this.b;
    }

    @Nullable
    public final Long getDate() {
        return this.f3639a;
    }

    public final int getEcgIndex() {
        return this.h;
    }

    public final int getEcgQuality() {
        return this.g;
    }

    @Nullable
    public final String getHeartRateValue() {
        return this.e;
    }

    @Nullable
    public final String getHrvValue() {
        return this.f;
    }

    @NotNull
    public final List<Integer> getQueueEcg() {
        return this.c;
    }

    @NotNull
    public final List<Float> getQueuePpg() {
        return this.d;
    }

    public final void setDataType(@Nullable String str) {
        this.b = str;
    }

    public final void setDate(@Nullable Long l) {
        this.f3639a = l;
    }

    public final void setEcgIndex(int i) {
        this.h = i;
    }

    public final void setEcgQuality(int i) {
        this.g = i;
    }

    public final void setHeartRateValue(@Nullable String str) {
        this.e = str;
    }

    public final void setHrvValue(@Nullable String str) {
        this.f = str;
    }

    public final void setQueueEcg(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.c = list;
    }

    public final void setQueuePpg(@NotNull List<Float> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.d = list;
    }

    @NotNull
    /* renamed from: clone */
    public LiveECGDataResponse m103clone() {
        LiveECGDataResponse liveECGDataResponse = new LiveECGDataResponse();
        liveECGDataResponse.f3639a = this.f3639a;
        liveECGDataResponse.b = this.b;
        liveECGDataResponse.c = new ArrayList(this.c);
        liveECGDataResponse.d = new ArrayList(this.d);
        liveECGDataResponse.e = this.e;
        liveECGDataResponse.f = this.f;
        liveECGDataResponse.g = this.g;
        liveECGDataResponse.h = this.h;
        return liveECGDataResponse;
    }
}
