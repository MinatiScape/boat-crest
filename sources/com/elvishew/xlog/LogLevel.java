package com.elvishew.xlog;

import androidx.exifinterface.media.ExifInterface;
import com.coveiot.coveaccess.constants.CoveApiConstants;
/* loaded from: classes9.dex */
public class LogLevel {
    public static final int ALL = Integer.MIN_VALUE;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = Integer.MAX_VALUE;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    public static String getLevelName(int i) {
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i != 6) {
                            if (i < 2) {
                                return "VERBOSE-" + (2 - i);
                            }
                            return "ERROR+" + (i - 6);
                        }
                        return CoveApiConstants.RESPONSE_STATUS_VALUE_ERROR;
                    }
                    return "WARN";
                }
                return "INFO";
            }
            return "DEBUG";
        }
        return "VERBOSE";
    }

    public static String getShortLevelName(int i) {
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i != 6) {
                            if (i < 2) {
                                return "V-" + (2 - i);
                            }
                            return "E+" + (i - 6);
                        }
                        return ExifInterface.LONGITUDE_EAST;
                    }
                    return ExifInterface.LONGITUDE_WEST;
                }
                return "I";
            }
            return "D";
        }
        return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
    }
}
