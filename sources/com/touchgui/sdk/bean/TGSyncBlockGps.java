package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGSyncBlockGps {
    @Nullable
    private TGSyncGps gpsData;
    private int pkgIndex;
    private int totalPkg;

    @Nullable
    public TGSyncGps getGpsData() {
        return this.gpsData;
    }

    public int getPkgIndex() {
        return this.pkgIndex;
    }

    public int getTotalPkg() {
        return this.totalPkg;
    }

    public boolean isFirstBlock() {
        return this.totalPkg > 1 && this.pkgIndex == 1;
    }

    public boolean isHaveMoreData() {
        TGSyncGps tGSyncGps = this.gpsData;
        return tGSyncGps != null && tGSyncGps.isHaveMoreData();
    }

    public boolean isLastBlock() {
        int i = this.totalPkg;
        if (i != 1) {
            return i > 1 && i == this.pkgIndex;
        }
        return true;
    }

    public void setGpsData(@Nullable TGSyncGps tGSyncGps) {
        this.gpsData = tGSyncGps;
    }

    public void setPkgIndex(int i) {
        this.pkgIndex = i;
    }

    public void setTotalPkg(int i) {
        this.totalPkg = i;
    }
}
