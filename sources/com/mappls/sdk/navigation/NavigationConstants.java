package com.mappls.sdk.navigation;

import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InterruptedIOException;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
/* loaded from: classes11.dex */
public interface NavigationConstants {
    public static final int CONNECTION_ERROR = 0;
    public static final String DEEP_LINK_ACTION_OPEN_ROOT_SCREEN = "com.mappls.app.navigation.car.OpenRootScreen";
    public static final int PERMANENT_ERROR = 2;
    public static final int TEMPORARY_ERROR = 1;
    public static final int UI_HANDLER_LOCATION_SERVICE = 5000;
    public static final int UI_HANDLER_MAP_CONTROLS = 4000;
    public static final int UI_HANDLER_MAP_VIEW = 3000;
    public static final int UI_HANDLER_PROGRESS = 6000;
    public static final int UI_HANDLER_SEARCH = 7000;
    public static final String URI_HOST = "car_navigation";
    public static final String URI_SCHEME = "com.mappls.app.maps";

    @NonNull
    static Uri createDeepLinkUri(@NonNull String str) {
        return Uri.fromParts(URI_SCHEME, URI_HOST, str);
    }

    static int getFailureType(Throwable th) {
        if ((th instanceof NoRouteToHostException) || (th instanceof UnknownHostException) || (th instanceof SocketException) || (th instanceof ProtocolException) || (th instanceof SSLException)) {
            return 0;
        }
        return th instanceof InterruptedIOException ? 1 : 2;
    }
}
