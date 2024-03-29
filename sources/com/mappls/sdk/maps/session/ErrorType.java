package com.mappls.sdk.maps.session;

import androidx.constraintlayout.core.motion.utils.TypedValues;
/* loaded from: classes11.dex */
public class ErrorType {
    public static AuthenticationError PERMISSION_ERROR = new AuthenticationError(1, "API Permission error");
    public static AuthenticationError UNKNOWN_ERROR = new AuthenticationError(-1, "Unknown error");
    public static AuthenticationError SERVER_ERROR = new AuthenticationError(0, "Server error");
    public static AuthenticationError NETWORK_ERROR = new AuthenticationError(2, "Network error");
    public static AuthenticationError DUPLICATE_SESSION_ERROR = new AuthenticationError(TypedValues.TransitionType.TYPE_DURATION, "Session already active on Another device");
    public static AuthenticationError INVALID_MAC_ERROR = new AuthenticationError(400, "Invalid MAC ID");
    public static AuthenticationError INVALID_MAC_DEV_ERROR = new AuthenticationError(4, "Invalid MAC ID");
    public static AuthenticationError BLUETOOTH_ERROR = new AuthenticationError(3, "Not connected to bluetooth");
    public static AuthenticationError START_NAVIGATION_ALREADY_IN_PROGRESS_ERROR = new AuthenticationError(100, "Start navigation request already in progress.");
}
