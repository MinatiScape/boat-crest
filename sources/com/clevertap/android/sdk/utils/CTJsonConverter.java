package com.clevertap.android.sdk.utils;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.google.android.material.color.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTJsonConverter {
    public static JSONObject displayUnitFromExtras(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String string = bundle.getString(Constants.DISPLAY_UNIT_PREVIEW_PUSH_PAYLOAD_KEY);
        Logger.v("Received Display Unit via push payload: " + string);
        JSONArray jSONArray = new JSONArray();
        jSONObject.put(Constants.DISPLAY_UNIT_JSON_RESPONSE_KEY, jSONArray);
        jSONArray.put(new JSONObject(string));
        return jSONObject;
    }

    public static JSONObject from(DeviceInfo deviceInfo, CoreMetaData coreMetaData, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Location locationFromUser = coreMetaData.getLocationFromUser();
        jSONObject.put("Build", deviceInfo.getBuild() + "");
        jSONObject.put("Version", deviceInfo.getVersionName());
        jSONObject.put("OS Version", deviceInfo.getOsVersion());
        jSONObject.put("SDK Version", deviceInfo.getSdkVersion());
        if (locationFromUser != null) {
            jSONObject.put("Latitude", locationFromUser.getLatitude());
            jSONObject.put("Longitude", locationFromUser.getLongitude());
        }
        if (deviceInfo.getGoogleAdID() != null) {
            jSONObject.put(z2 ? Constants.MULTI_USER_PREFIX + "GoogleAdID" : "GoogleAdID", deviceInfo.getGoogleAdID());
            jSONObject.put("GoogleAdIDLimit", deviceInfo.isLimitAdTrackingEnabled());
        }
        try {
            jSONObject.put(ExifInterface.TAG_MAKE, deviceInfo.getManufacturer());
            jSONObject.put(ExifInterface.TAG_MODEL, deviceInfo.getModel());
            jSONObject.put("Carrier", deviceInfo.getCarrier());
            jSONObject.put("useIP", z);
            jSONObject.put("OS", deviceInfo.getOsName());
            jSONObject.put("wdt", deviceInfo.getWidth());
            jSONObject.put("hgt", deviceInfo.getHeight());
            jSONObject.put("dpi", deviceInfo.getDPI());
            jSONObject.put("dt", DeviceInfo.getDeviceType(deviceInfo.getContext()));
            if (Build.VERSION.SDK_INT >= 28) {
                jSONObject.put("abckt", deviceInfo.getAppBucket());
            }
            if (deviceInfo.getLibrary() != null) {
                jSONObject.put("lib", deviceInfo.getLibrary());
            }
            if (ManifestInfo.getInstance(deviceInfo.getContext()).isSSLPinningEnabled()) {
                jSONObject.put("sslpin", true);
            }
            if (!TextUtils.isEmpty(ManifestInfo.getInstance(deviceInfo.getContext()).getFCMSenderId())) {
                jSONObject.put("fcmsid", true);
            }
            String countryCode = deviceInfo.getCountryCode();
            if (countryCode != null && !countryCode.equals("")) {
                jSONObject.put("cc", countryCode);
            }
            if (z) {
                Boolean isWifiConnected = deviceInfo.isWifiConnected();
                if (isWifiConnected != null) {
                    jSONObject.put("wifi", isWifiConnected);
                }
                Boolean isBluetoothEnabled = deviceInfo.isBluetoothEnabled();
                if (isBluetoothEnabled != null) {
                    jSONObject.put("BluetoothEnabled", isBluetoothEnabled);
                }
                String bluetoothVersion = deviceInfo.getBluetoothVersion();
                if (bluetoothVersion != null) {
                    jSONObject.put("BluetoothVersion", bluetoothVersion);
                }
                String networkType = deviceInfo.getNetworkType();
                if (networkType != null) {
                    jSONObject.put("Radio", networkType);
                }
            }
            jSONObject.put("LIAMC", deviceInfo.getLocalInAppCount());
            for (Map.Entry<String, Integer> entry : coreMetaData.getAllCustomSdkVersions().entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static JSONObject getErrorObject(ValidationResult validationResult) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(c.f10260a, validationResult.getErrorCode());
            jSONObject.put("d", validationResult.getErrorDesc());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static JSONArray getRenderedTargetList(DBAdapter dBAdapter) {
        String[] fetchPushNotificationIds = dBAdapter.fetchPushNotificationIds();
        JSONArray jSONArray = new JSONArray();
        for (String str : fetchPushNotificationIds) {
            Logger.v("RTL IDs -" + str);
            jSONArray.put(str);
        }
        return jSONArray;
    }

    public static JSONObject getWzrkFields(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                JSONObject wzrkFields = getWzrkFields((Bundle) obj);
                Iterator<String> keys = wzrkFields.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject.put(next, wzrkFields.get(next));
                }
            } else if (str.startsWith(Constants.WZRK_PREFIX)) {
                jSONObject.put(str, bundle.get(str));
            }
        }
        return jSONObject;
    }

    public static <T> Object[] toArray(@NonNull JSONArray jSONArray) {
        Object[] objArr = new Object[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                objArr[i] = jSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return objArr;
    }

    public static JSONArray toJsonArray(@NonNull List<?> list) {
        JSONArray jSONArray = new JSONArray();
        for (Object obj : list) {
            if (obj != null) {
                jSONArray.put(obj);
            }
        }
        return jSONArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject toJsonObject(java.lang.String r2, com.clevertap.android.sdk.Logger r3, java.lang.String r4) {
        /*
            if (r2 == 0) goto L21
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L8
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L8
            goto L22
        L8:
            r2 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Error reading guid cache: "
            r0.append(r1)
            java.lang.String r2 = r2.toString()
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            r3.verbose(r4, r2)
        L21:
            r0 = 0
        L22:
            if (r0 == 0) goto L25
            goto L2a
        L25:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
        L2a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.utils.CTJsonConverter.toJsonObject(java.lang.String, com.clevertap.android.sdk.Logger, java.lang.String):org.json.JSONObject");
    }

    public static String toJsonString(Object obj) {
        try {
            return obj.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static ArrayList<?> toList(@NonNull JSONArray jSONArray) {
        ArrayList<?> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static JSONObject getWzrkFields(CTInAppNotification cTInAppNotification) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jsonDescription = cTInAppNotification.getJsonDescription();
        Iterator<String> keys = jsonDescription.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (next.startsWith(Constants.WZRK_PREFIX)) {
                jSONObject.put(next, jsonDescription.get(next));
            }
        }
        return jSONObject;
    }

    public static JSONObject getWzrkFields(CTInboxMessage cTInboxMessage) {
        return cTInboxMessage.getWzrkParams();
    }
}
