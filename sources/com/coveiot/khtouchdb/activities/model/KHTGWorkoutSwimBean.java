package com.coveiot.khtouchdb.activities.model;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KHTGWorkoutSwimBean {

    /* renamed from: a  reason: collision with root package name */
    public int f7182a;
    public boolean b;
    @Nullable
    public String c;
    public int d;
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
    @Nullable
    public List<KHTGWorkoutSwimItemBean> p;

    public final int getAvgArmpull() {
        return this.n;
    }

    public final int getAvgSwolf() {
        return this.j;
    }

    public final int getCalories() {
        return this.e;
    }

    public final int getConfirmDistance() {
        return this.g;
    }

    @Nullable
    public final String getDate() {
        return this.c;
    }

    public final int getDistance() {
        return this.f;
    }

    public final int getDuration() {
        return this.i;
    }

    public final boolean getHaveMoreData() {
        return this.b;
    }

    public final int getMaxArmpull() {
        return this.o;
    }

    public final int getOffset() {
        return this.f7182a;
    }

    public final int getPoolDistance() {
        return this.m;
    }

    public final int getPosture() {
        return this.l;
    }

    @Nullable
    public final List<KHTGWorkoutSwimItemBean> getSwimItemBeans() {
        return this.p;
    }

    public final int getTotalStrokes() {
        return this.k;
    }

    public final int getTrips() {
        return this.h;
    }

    public final int getType() {
        return this.d;
    }

    public final void setAvgArmpull(int i) {
        this.n = i;
    }

    public final void setAvgSwolf(int i) {
        this.j = i;
    }

    public final void setCalories(int i) {
        this.e = i;
    }

    public final void setConfirmDistance(int i) {
        this.g = i;
    }

    public final void setDate(@Nullable String str) {
        this.c = str;
    }

    public final void setDistance(int i) {
        this.f = i;
    }

    public final void setDuration(int i) {
        this.i = i;
    }

    public final void setHaveMoreData(boolean z) {
        this.b = z;
    }

    public final void setMaxArmpull(int i) {
        this.o = i;
    }

    public final void setOffset(int i) {
        this.f7182a = i;
    }

    public final void setPoolDistance(int i) {
        this.m = i;
    }

    public final void setPosture(int i) {
        this.l = i;
    }

    public final void setSwimItemBeans(@Nullable List<KHTGWorkoutSwimItemBean> list) {
        this.p = list;
    }

    public final void setTotalStrokes(int i) {
        this.k = i;
    }

    public final void setTrips(int i) {
        this.h = i;
    }

    public final void setType(int i) {
        this.d = i;
    }
}
