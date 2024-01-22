package com.mappls.android.lms;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.event.stat.one.d;
import com.mappls.android.util.MPLog;
import com.skyfishjy.library.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class MapplsLMSManager {
    private static final String FLUTTER_ACTIVITY = "io.flutter.embedding.android.FlutterActivity";
    private static final String REACT_NATIVE_ACTIVITY = "com.facebook.react.ReactApplication";
    private static MapplsLMSManager sInstance;
    private String clientId;
    private final Context context;
    private Location currentLocation;
    public double latitude = 0.0d;
    public double longitude = 0.0d;
    private final MapplsLMSAPI mMapplsLMS;
    private String mapVersion;
    private String projectCode;
    private String restApiVersion;
    private String userId;
    private String userName;

    private MapplsLMSManager(Context context, String str) {
        this.context = context;
        MapplsLMSAPI mapplsLMSAPI = MapplsLMSAPI.getInstance(context, str, false);
        this.mMapplsLMS = mapplsLMSAPI;
        mapplsLMSAPI.setEnableLogging(false);
    }

    public static synchronized MapplsLMSManager getInstance() {
        MapplsLMSManager mapplsLMSManager;
        synchronized (MapplsLMSManager.class) {
            if (!isInitialised()) {
                MPLog.e("MapplsLMS", "Mappls LMS SDK not Initialised");
            }
            mapplsLMSManager = sInstance;
        }
        return mapplsLMSManager;
    }

    private JSONObject getMetaDataField(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_model", Build.MODEL);
        jSONObject.put("device_manufacturer", Build.MANUFACTURER);
        jSONObject.put("os_version", Build.VERSION.SDK_INT);
        jSONObject.put("os_name", Constants.KEY_ANDROID);
        jSONObject.put("map_sdk_version", this.mapVersion);
        jSONObject.put("api_core_version", this.restApiVersion);
        jSONObject.put("api_kit_version", this.restApiVersion);
        jSONObject.put("me_version", BuildConfig.VERSION_NAME);
        Context context = this.context;
        if (context != null) {
            jSONObject.put(d.j, context.getApplicationInfo().loadLabel(this.context.getPackageManager()).toString());
            try {
                jSONObject.put("app_version", this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (str != null) {
            jSONObject.put("sdk_name", str);
        }
        if (str2 != null) {
            jSONObject.put(d.k, str2);
        }
        return jSONObject;
    }

    public static synchronized void initialize(Context context) {
        synchronized (MapplsLMSManager.class) {
            if (!isInitialised()) {
                String str = isOnClasspath(REACT_NATIVE_ACTIVITY) ? "mappls-rn-android-sdk" : "mappls-android-sdk";
                if (isOnClasspath(FLUTTER_ACTIVITY)) {
                    str = "mappls-flutter-android-sdk";
                }
                sInstance = new MapplsLMSManager(context, str);
            }
        }
    }

    public static synchronized void initialize(Context context, String str) {
        synchronized (MapplsLMSManager.class) {
            if (!isInitialised()) {
                sInstance = new MapplsLMSManager(context, str);
            }
        }
    }

    public static synchronized boolean isInitialised() {
        boolean z;
        synchronized (MapplsLMSManager.class) {
            z = sInstance != null;
        }
        return z;
    }

    private static boolean isOnClasspath(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private String stackTraceToString(Throwable th) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void add(String str, String str2, String str3, JSONObject jSONObject) {
        try {
            if (this.mMapplsLMS == null) {
                return;
            }
            JSONObject commonFields = getCommonFields(str2, str3);
            commonFields.put("url", jSONObject);
            this.mMapplsLMS.track(str, commonFields);
        } catch (Exception unused) {
        }
    }

    public JSONObject getCommonFields(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        if (this.latitude != 0.0d && this.longitude != 0.0d) {
            jSONObject.put("map_center", this.latitude + Constants.SEPARATOR_COMMA + this.longitude);
        }
        if (this.currentLocation != null) {
            jSONObject.put(FirebaseAnalytics.Param.LOCATION, this.currentLocation.getLatitude() + Constants.SEPARATOR_COMMA + this.currentLocation.getLongitude());
        }
        String str3 = this.userId;
        if (str3 != null) {
            jSONObject.put("user_id", str3);
        }
        String str4 = this.userName;
        if (str4 != null) {
            jSONObject.put("user_name", str4);
        }
        String str5 = this.projectCode;
        if (str5 != null) {
            jSONObject.put("referer", str5);
        }
        String str6 = this.clientId;
        if (str6 != null) {
            jSONObject.put("client_id", str6);
        }
        Context context = this.context;
        if (context != null) {
            try {
                jSONObject.put("domain", context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).packageName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        jSONObject.put("meta_data", getMetaDataField(str, str2));
        return jSONObject;
    }

    public void handledExceptions(String str, String str2, String str3, Throwable th) {
        try {
            if (this.mMapplsLMS == null) {
                return;
            }
            JSONObject commonFields = getCommonFields(str2, str3);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$method_name", str);
            jSONObject.put(AutomaticEvents.APP_EXCEPTION_REASON, th.toString());
            jSONObject.put("$error_stack_trace", stackTraceToString(th));
            commonFields.put("url", jSONObject.toString());
            this.mMapplsLMS.track(AutomaticEvents.APP_EXCEPTION, commonFields);
        } catch (Exception unused) {
        }
    }

    public void setBaseUrl(String str) {
        MapplsLMSAPI mapplsLMSAPI = this.mMapplsLMS;
        if (mapplsLMSAPI == null) {
            return;
        }
        mapplsLMSAPI.setBaseUrl(str);
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public void setMapCenter(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public void setMapVersion(String str) {
        this.mapVersion = str;
    }

    public void setProjectCode(String str) {
        this.projectCode = str;
    }

    public void setProxy(String str, Integer num) {
        MapplsLMSAPI.host = str;
        MapplsLMSAPI.port = num;
    }

    public void setRestApiVersion(String str) {
        this.restApiVersion = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public void trackScreen(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || !isInitialised()) {
            return;
        }
        try {
            if (this.mMapplsLMS == null) {
                return;
            }
            JSONObject commonFields = getCommonFields(str2, str3);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(FirebaseAnalytics.Param.SCREEN_NAME, str);
            jSONObject.put(FirebaseAnalytics.Param.SCREEN_CLASS, str4);
            commonFields.put("url", jSONObject);
            this.mMapplsLMS.track("screen", commonFields);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
