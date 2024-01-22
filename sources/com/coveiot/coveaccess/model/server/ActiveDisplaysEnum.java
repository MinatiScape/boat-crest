package com.coveiot.coveaccess.model.server;

import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
/* loaded from: classes8.dex */
public enum ActiveDisplaysEnum {
    STEPS("STEPS"),
    SLEEP("SLEEP"),
    SPO2("SPO2"),
    MUSIC("MUSIC"),
    WEATHER("WEATHER"),
    ACTIVITIES("ACTIVITIES"),
    STRESS("STRESS"),
    HR(EcgStyleReportUtil.UserInfoKey.HR),
    OTHERS("OTHERS");
    
    private String activeDisplayType;

    ActiveDisplaysEnum(String str) {
        this.activeDisplayType = str;
    }

    public String getActiveDisplayType() {
        return this.activeDisplayType;
    }
}
