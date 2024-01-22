package com.mappls.sdk.navigation.util;

import com.mappls.sdk.navigation.h;
/* loaded from: classes11.dex */
public class GPSInfo {
    public int foundSatellites = 0;
    public int usedSatellites = 0;
    public boolean fixed = false;

    public String toString() {
        StringBuilder a2 = h.a("GPSInfo{foundSatellites=");
        a2.append(this.foundSatellites);
        a2.append(", usedSatellites=");
        a2.append(this.usedSatellites);
        a2.append(", fixed=");
        a2.append(this.fixed);
        a2.append('}');
        return a2.toString();
    }
}
