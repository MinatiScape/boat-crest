package org.slf4j.event;

import com.coveiot.coveaccess.constants.CoveApiConstants;
/* loaded from: classes13.dex */
public enum Level {
    ERROR(40, CoveApiConstants.RESPONSE_STATUS_VALUE_ERROR),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");
    
    private int levelInt;
    private String levelStr;

    Level(int i, String str) {
        this.levelInt = i;
        this.levelStr = str;
    }

    public int toInt() {
        return this.levelInt;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.levelStr;
    }
}
