package com.mappls.sdk.maps.offline;

import androidx.annotation.Keep;
/* loaded from: classes11.dex */
public class OfflineRegionStatus {

    /* renamed from: a  reason: collision with root package name */
    public int f12814a;
    public final long b;
    public final long c;
    public final long d;
    public final long e;
    public final long f;
    public final boolean g;

    @Keep
    private OfflineRegionStatus(int i, long j, long j2, long j3, long j4, long j5, boolean z) {
        this.f12814a = i;
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = j5;
        this.g = z;
    }

    public long getCompletedResourceCount() {
        return this.b;
    }

    public long getCompletedResourceSize() {
        return this.c;
    }

    public long getCompletedTileCount() {
        return this.d;
    }

    public long getCompletedTileSize() {
        return this.e;
    }

    public int getDownloadState() {
        return this.f12814a;
    }

    public long getRequiredResourceCount() {
        return this.f;
    }

    public boolean isComplete() {
        return this.b >= this.f;
    }

    public boolean isRequiredResourceCountPrecise() {
        return this.g;
    }
}
