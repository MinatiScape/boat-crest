package com.jieli.jl_rcsp.model.device;
/* loaded from: classes11.dex */
public class WatchSettingInfo {
    private int mask;

    public WatchSettingInfo(int i) {
        this.mask = i;
    }

    public boolean isSupport(int i) {
        return ((this.mask >> i) & 1) == 1;
    }

    public void setMask(int i) {
        this.mask = i;
    }
}
