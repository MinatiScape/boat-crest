package com.google.firebase.crashlytics.internal.settings.network;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.network.HttpGetRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import com.google.firebase.crashlytics.internal.settings.model.SettingsRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class DefaultSettingsSpiCall implements SettingsSpiCall {

    /* renamed from: a  reason: collision with root package name */
    public final String f11259a;
    public final HttpRequestFactory b;
    public final Logger c;

    public DefaultSettingsSpiCall(String str, HttpRequestFactory httpRequestFactory) {
        this(str, httpRequestFactory, Logger.getLogger());
    }

    public final HttpGetRequest a(HttpGetRequest httpGetRequest, SettingsRequest settingsRequest) {
        b(httpGetRequest, "X-CRASHLYTICS-GOOGLE-APP-ID", settingsRequest.googleAppId);
        b(httpGetRequest, "X-CRASHLYTICS-API-CLIENT-TYPE", Constants.KEY_ANDROID);
        b(httpGetRequest, "X-CRASHLYTICS-API-CLIENT-VERSION", CrashlyticsCore.getVersion());
        b(httpGetRequest, HttpHeaders.ACCEPT, "application/json");
        b(httpGetRequest, "X-CRASHLYTICS-DEVICE-MODEL", settingsRequest.deviceModel);
        b(httpGetRequest, "X-CRASHLYTICS-OS-BUILD-VERSION", settingsRequest.osBuildVersion);
        b(httpGetRequest, "X-CRASHLYTICS-OS-DISPLAY-VERSION", settingsRequest.osDisplayVersion);
        b(httpGetRequest, "X-CRASHLYTICS-INSTALLATION-ID", settingsRequest.installIdProvider.getCrashlyticsInstallId());
        return httpGetRequest;
    }

    public final void b(HttpGetRequest httpGetRequest, String str, String str2) {
        if (str2 != null) {
            httpGetRequest.header(str, str2);
        }
    }

    public final JSONObject c(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            Logger logger = this.c;
            logger.w("Failed to parse settings JSON from " + this.f11259a, e);
            Logger logger2 = this.c;
            logger2.w("Settings response " + str);
            return null;
        }
    }

    public HttpGetRequest createHttpGetRequest(Map<String, String> map) {
        HttpGetRequest buildHttpGetRequest = this.b.buildHttpGetRequest(this.f11259a, map);
        return buildHttpGetRequest.header(HttpHeaders.USER_AGENT, "Crashlytics Android SDK/" + CrashlyticsCore.getVersion()).header("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    public final Map<String, String> d(SettingsRequest settingsRequest) {
        HashMap hashMap = new HashMap();
        hashMap.put("build_version", settingsRequest.buildVersion);
        hashMap.put("display_version", settingsRequest.displayVersion);
        hashMap.put("source", Integer.toString(settingsRequest.source));
        String str = settingsRequest.instanceId;
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    public JSONObject e(HttpResponse httpResponse) {
        int code = httpResponse.code();
        Logger logger = this.c;
        logger.v("Settings response code was: " + code);
        if (f(code)) {
            return c(httpResponse.body());
        }
        Logger logger2 = this.c;
        logger2.e("Settings request failed; (status: " + code + ") from " + this.f11259a);
        return null;
    }

    public boolean f(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.network.SettingsSpiCall
    public JSONObject invoke(SettingsRequest settingsRequest, boolean z) {
        if (z) {
            try {
                Map<String, String> d = d(settingsRequest);
                HttpGetRequest a2 = a(createHttpGetRequest(d), settingsRequest);
                Logger logger = this.c;
                logger.d("Requesting settings from " + this.f11259a);
                Logger logger2 = this.c;
                logger2.v("Settings query params were: " + d);
                return e(a2.execute());
            } catch (IOException e) {
                this.c.e("Settings request failed.", e);
                return null;
            }
        }
        throw new RuntimeException("An invalid data collection token was used.");
    }

    public DefaultSettingsSpiCall(String str, HttpRequestFactory httpRequestFactory, Logger logger) {
        if (str != null) {
            this.c = logger;
            this.b = httpRequestFactory;
            this.f11259a = str;
            return;
        }
        throw new IllegalArgumentException("url must not be null.");
    }
}
