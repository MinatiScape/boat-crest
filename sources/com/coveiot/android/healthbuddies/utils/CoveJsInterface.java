package com.coveiot.android.healthbuddies.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.net.MailTo;
import com.blankj.utilcode.constant.PermissionConstants;
import com.coveiot.android.healthbuddies.model.ContactSupportInfo;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public final class CoveJsInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String d = "CoveJsInterface";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f4583a;
    @NotNull
    public OnJavascriptListener b;
    @NotNull
    public FirebaseAnalytics c;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getTAG() {
            return CoveJsInterface.d;
        }
    }

    /* loaded from: classes3.dex */
    public interface OnJavascriptListener {
        void evaluateJavascript(@Nullable String str);
    }

    public CoveJsInterface(@NotNull Context context, @NotNull OnJavascriptListener onJavascriptListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onJavascriptListener, "onJavascriptListener");
        this.f4583a = context;
        this.b = onJavascriptListener;
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(firebaseAnalytics, "getInstance(context)");
        this.c = firebaseAnalytics;
    }

    @JavascriptInterface
    @RequiresApi(api = 19)
    public final void appIn(@NotNull String reqStr) {
        ContactSupportInfo contactSupportInfo;
        Intrinsics.checkNotNullParameter(reqStr, "reqStr");
        LogHelper.d("appIn", reqStr);
        try {
            JSONObject jSONObject = new JSONObject(reqStr);
            new JSONObject();
            String reqType = jSONObject.getString("reqType");
            Intrinsics.checkNotNullExpressionValue(reqType, "reqType");
            String upperCase = reqType.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            if (Intrinsics.areEqual(upperCase, "INIT_CONTACT") && (contactSupportInfo = (ContactSupportInfo) new Gson().fromJson(jSONObject.toString(), (Class<Object>) ContactSupportInfo.class)) != null && contactSupportInfo.getData() != null && contactSupportInfo.getData().getMethod() != null) {
                if (m.equals(contactSupportInfo.getData().getMethod(), PermissionConstants.PHONE, true)) {
                    String to = contactSupportInfo.getData().getTo();
                    Intrinsics.checkNotNullExpressionValue(to, "contactSupportInfo.data.to");
                    openPhoneDailerWith(to);
                } else if (m.equals(contactSupportInfo.getData().getMethod(), "EMAIL", true)) {
                    String to2 = contactSupportInfo.getData().getTo();
                    Intrinsics.checkNotNullExpressionValue(to2, "contactSupportInfo.data.to");
                    openEmailClientWith(to2);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public final Bundle bundleFromJson(@NotNull String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        if (TextUtils.isEmpty(json)) {
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(json);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof String) {
                    bundle.putString(next, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle.putInt(next, ((Number) obj).intValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(next, ((Number) obj).doubleValue());
                } else {
                    String str = d;
                    Log.w(str, "Value for key " + next + " not one of [String, Integer, Double]");
                }
            }
            return bundle;
        } catch (JSONException e) {
            Log.w(d, "Failed to parse JSON, returning empty Bundle.", e);
            return new Bundle();
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4583a;
    }

    @NotNull
    public final FirebaseAnalytics getMAnalytics() {
        return this.c;
    }

    @NotNull
    public final OnJavascriptListener getOnJavascriptListener() {
        return this.b;
    }

    public final void logEvent(@NotNull String name, @NotNull String jsonParams) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(jsonParams, "jsonParams");
        LogHelper.d("logEvent:", name);
        this.c.logEvent(name, bundleFromJson(jsonParams));
    }

    public final void openEmailClientWith(@NotNull String emailId) {
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME + emailId));
        this.f4583a.startActivity(Intent.createChooser(intent, ""));
    }

    public final void openPhoneDailerWith(@NotNull String phoneNumber) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        try {
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + phoneNumber));
            this.f4583a.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f4583a = context;
    }

    public final void setMAnalytics(@NotNull FirebaseAnalytics firebaseAnalytics) {
        Intrinsics.checkNotNullParameter(firebaseAnalytics, "<set-?>");
        this.c = firebaseAnalytics;
    }

    public final void setOnJavascriptListener(@NotNull OnJavascriptListener onJavascriptListener) {
        Intrinsics.checkNotNullParameter(onJavascriptListener, "<set-?>");
        this.b = onJavascriptListener;
    }

    public final void setUserProperty(@NotNull String name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        LogHelper.d("setUserProperty", "setUserProperty:" + name);
        this.c.setUserProperty(name, value);
    }

    @JavascriptInterface
    public final void webOut(@NotNull String resStr) {
        Intrinsics.checkNotNullParameter(resStr, "resStr");
        LogHelper.d("webOut", resStr);
        try {
            JSONObject jSONObject = new JSONObject(resStr);
            JSONObject jSONObject2 = new JSONObject();
            String resType = jSONObject.getString("resType");
            Intrinsics.checkNotNullExpressionValue(resType, "resType");
            String upperCase = resType.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            switch (upperCase.hashCode()) {
                case -310667773:
                    if (upperCase.equals("ANLY_SET_USR_PROPS")) {
                        LogHelper.d(d, "inside ANLY_SET_USR_PROPS");
                        try {
                            String string = jSONObject.getJSONObject("data").getJSONObject("userProp").getString("kh_webapp_agent");
                            Intrinsics.checkNotNullExpressionValue(string, "jsonObject.getJSONObject…String(\"kh_webapp_agent\")");
                            setUserProperty("kh_webapp_agent", string);
                            String string2 = jSONObject.getJSONObject("data").getJSONObject("userProp").getString("kh_webapp_id");
                            Intrinsics.checkNotNullExpressionValue(string2, "jsonObject.getJSONObject…getString(\"kh_webapp_id\")");
                            setUserProperty("kh_webapp_id", string2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                case 34575283:
                    if (upperCase.equals("#INIT")) {
                        jSONObject2.put("resType", "#ACK");
                        jSONObject2.put("resVer", 1);
                        jSONObject2.put("resId", "INIT_0");
                        jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                        String str = d;
                        LogHelper.d(str, "Init json:" + jSONObject2);
                        OnJavascriptListener onJavascriptListener = this.b;
                        onJavascriptListener.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
                    }
                    return;
                case 494239258:
                    if (upperCase.equals("ANLY_LOG_EVENT")) {
                        LogHelper.d(d, "inside ANLY_LOG_EVENT");
                        try {
                            String string3 = jSONObject.getJSONObject("data").getString("eventName");
                            Intrinsics.checkNotNullExpressionValue(string3, "jsonObject.getJSONObject…\").getString(\"eventName\")");
                            String jSONObject3 = jSONObject.getJSONObject("data").getJSONObject("eventProp").toString();
                            Intrinsics.checkNotNullExpressionValue(jSONObject3, "jsonObject.getJSONObject…t(\"eventProp\").toString()");
                            logEvent(string3, jSONObject3);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    return;
                case 748180178:
                    if (upperCase.equals("#WIN_CLOSE")) {
                        LogHelper.d(d, "#WINCLOSE");
                        Context context = this.f4583a;
                        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
                        ((Activity) context).finish();
                    }
                    return;
                default:
                    return;
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }
}
