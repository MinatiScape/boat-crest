package no.nordicsemi.android.error;

import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes12.dex */
public final class LegacyDfuError {
    public static final int CRC_ERROR = 5;
    public static final int DATA_SIZE_EXCEEDS_LIMIT = 4;
    public static final int INVALID_STATE = 2;
    public static final int NOT_SUPPORTED = 3;
    public static final int OPERATION_FAILED = 6;

    public static String parse(int i) {
        switch (i) {
            case 258:
                return "INVALID STATE";
            case 259:
                return "NOT SUPPORTED";
            case 260:
                return "DATA SIZE EXCEEDS LIMIT";
            case 261:
                return "INVALID CRC ERROR";
            case DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS /* 262 */:
                return "OPERATION FAILED";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }
}
