package com.coveiot.android.bleabstract.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ECGResultResponse implements Cloneable, Serializable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3595a;
    @Nullable
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    @NotNull
    public List<Integer> k = new ArrayList();
    @NotNull
    public List<Float> l = new ArrayList();
    @Nullable
    public String m;

    public final int getAvBlockValue() {
        return this.i;
    }

    public final int getBreathValue() {
        return this.h;
    }

    @Nullable
    public final String getEndDateTime() {
        return this.b;
    }

    public final int getHeartRateValue() {
        return this.d;
    }

    public final int getHighBpValue() {
        return this.f;
    }

    public final int getHrvValue() {
        return this.c;
    }

    public final int getLowBpValue() {
        return this.g;
    }

    @Nullable
    public final String getMMacAddress() {
        return this.m;
    }

    public final int getMoodValue() {
        return this.j;
    }

    @NotNull
    public final List<Integer> getQueueEcg() {
        return this.k;
    }

    @NotNull
    public final List<Float> getQueuePpg() {
        return this.l;
    }

    @Nullable
    public final String getStartDateTime() {
        return this.f3595a;
    }

    public final int getStressValue() {
        return this.e;
    }

    public final void setAvBlockValue(int i) {
        this.i = i;
    }

    public final void setBreathValue(int i) {
        this.h = i;
    }

    public final void setEndDateTime(@Nullable String str) {
        this.b = str;
    }

    public final void setHeartRateValue(int i) {
        this.d = i;
    }

    public final void setHighBpValue(int i) {
        this.f = i;
    }

    public final void setHrvValue(int i) {
        this.c = i;
    }

    public final void setLowBpValue(int i) {
        this.g = i;
    }

    public final void setMMacAddress(@Nullable String str) {
        this.m = str;
    }

    public final void setMoodValue(int i) {
        this.j = i;
    }

    public final void setQueueEcg(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.k = list;
    }

    public final void setQueuePpg(@NotNull List<Float> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.l = list;
    }

    public final void setStartDateTime(@Nullable String str) {
        this.f3595a = str;
    }

    public final void setStressValue(int i) {
        this.e = i;
    }

    @NotNull
    /* renamed from: clone */
    public ECGResultResponse m102clone() {
        ECGResultResponse eCGResultResponse = new ECGResultResponse();
        eCGResultResponse.f3595a = this.f3595a;
        eCGResultResponse.b = this.b;
        eCGResultResponse.c = this.c;
        eCGResultResponse.d = this.d;
        eCGResultResponse.e = this.e;
        eCGResultResponse.f = this.f;
        eCGResultResponse.g = this.g;
        eCGResultResponse.h = this.h;
        eCGResultResponse.i = this.i;
        eCGResultResponse.j = this.j;
        eCGResultResponse.k = new ArrayList(this.k);
        eCGResultResponse.l = new ArrayList(this.l);
        eCGResultResponse.m = this.m;
        return eCGResultResponse;
    }
}
