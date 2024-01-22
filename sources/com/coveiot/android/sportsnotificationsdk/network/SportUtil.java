package com.coveiot.android.sportsnotificationsdk.network;

import com.coveiot.coveaccess.constants.ErrorConstants;
/* loaded from: classes7.dex */
public class SportUtil {
    public static SportErrorModel buildErrorObject(Throwable th) {
        return new SportErrorModel(th.getMessage() != null ? th.getMessage() : ErrorConstants.GENERIC_ERROR);
    }
}
