package com.google.i18n.phonenumbers;

import java.util.Set;
@Deprecated
/* loaded from: classes10.dex */
public class ShortNumberUtil {

    /* loaded from: classes10.dex */
    public enum ShortNumberCost {
        TOLL_FREE,
        STANDARD_RATE,
        PREMIUM_RATE,
        UNKNOWN_COST
    }

    public boolean connectsToEmergencyNumber(String str, String str2) {
        return ShortNumberInfo.getInstance().connectsToEmergencyNumber(str, str2);
    }

    public Set<String> getSupportedRegions() {
        return ShortNumberInfo.getInstance().d();
    }

    public boolean isEmergencyNumber(String str, String str2) {
        return ShortNumberInfo.getInstance().isEmergencyNumber(str, str2);
    }
}
