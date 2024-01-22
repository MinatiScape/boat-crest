package com.coveiot.android.watchfaceui.webSupport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import androidx.core.app.NotificationCompat;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.R;
import com.coveiot.android.watchfaceui.activities.ActivityWatchFace;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes8.dex */
public final class JSInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String k = "JsInterface";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Activity f6164a;
    @NotNull
    public OnJavascriptListener b;
    @NotNull
    public NavigationListener c;
    @NotNull
    public OnPickerResult d;
    @NotNull
    public OnCameraEvent e;
    @NotNull
    public OnApplyNowClicked f;
    public boolean g;
    public boolean h;
    @Nullable
    public String i;
    @NotNull
    public FirebaseAnalytics j;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getTAG() {
            return JSInterface.k;
        }
    }

    /* loaded from: classes8.dex */
    public interface NavigationListener {
        void onBackPress();
    }

    /* loaded from: classes8.dex */
    public interface OnApplyNowClicked {
        void onClicked(@NotNull String str, @NotNull String str2, @NotNull String str3);
    }

    /* loaded from: classes8.dex */
    public interface OnCameraEvent {
        void onCamEvent(@Nullable JSONObject jSONObject);
    }

    /* loaded from: classes8.dex */
    public interface OnJavascriptListener {
        void evaluateJavascript(@Nullable String str);
    }

    /* loaded from: classes8.dex */
    public interface OnPickerResult {
        void onResult(@Nullable JSONObject jSONObject);
    }

    public JSInterface(@NotNull Activity context, @NotNull OnJavascriptListener onJavascriptListener, @NotNull NavigationListener navigationListener, @NotNull OnPickerResult onPickerResult, @NotNull OnCameraEvent onCameraEvent, @NotNull OnApplyNowClicked onApplyNowClicked, boolean z, boolean z2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onJavascriptListener, "onJavascriptListener");
        Intrinsics.checkNotNullParameter(navigationListener, "navigationListener");
        Intrinsics.checkNotNullParameter(onPickerResult, "onPickerResult");
        Intrinsics.checkNotNullParameter(onCameraEvent, "onCameraEvent");
        Intrinsics.checkNotNullParameter(onApplyNowClicked, "onApplyNowClicked");
        this.f6164a = context;
        this.b = onJavascriptListener;
        this.c = navigationListener;
        this.d = onPickerResult;
        this.e = onCameraEvent;
        this.f = onApplyNowClicked;
        this.g = z;
        this.h = z2;
        this.i = str;
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(firebaseAnalytics, "getInstance(context)");
        this.j = firebaseAnalytics;
    }

    public static final void c(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, JSInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        Activity activity = this$0.f6164a;
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
        ((BaseActivity) activity).showProgress();
        Activity activity2 = this$0.f6164a;
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type android.app.Activity");
        activity2.finish();
    }

    @JavascriptInterface
    public final void appIn(@NotNull String reqStr) {
        Intrinsics.checkNotNullParameter(reqStr, "reqStr");
        LogHelper.d("appIn", reqStr);
        try {
            JSONObject jSONObject = new JSONObject(reqStr);
            JSONObject jSONObject2 = new JSONObject();
            String reqType = jSONObject.getString("reqType");
            Intrinsics.checkNotNullExpressionValue(reqType, "reqType");
            String upperCase = reqType.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            switch (upperCase.hashCode()) {
                case -1714631498:
                    if (!upperCase.equals("GET_X_HEADERS")) {
                        break;
                    } else {
                        jSONObject2.put("resType", "GET_X_HEADERS");
                        jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                        jSONObject2.put("reqId", jSONObject.getString("reqId"));
                        jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                        HashMap<String, String> customHeaders = CoveApi.getCustomHeaders();
                        Intrinsics.checkNotNull(customHeaders, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                        JSONObject jSONObject3 = new JSONObject((Map<?, ?>) customHeaders);
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("headers", jSONObject3);
                        jSONObject2.put("data", jSONObject4);
                        String str = k;
                        LogHelper.d(str, "GET_X_HEADERS json:" + jSONObject2);
                        OnJavascriptListener onJavascriptListener = this.b;
                        onJavascriptListener.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
                        break;
                    }
                case -1351208866:
                    if (!upperCase.equals("REQUEST_PICKER")) {
                        break;
                    } else {
                        this.d.onResult(jSONObject);
                        break;
                    }
                case -1284371202:
                    if (!upperCase.equals("REQUEST_BACKGROUND")) {
                        break;
                    } else {
                        this.e.onCamEvent(jSONObject);
                        break;
                    }
                case -1262019691:
                    if (!upperCase.equals("WF_APPLY_ON_DEVICE")) {
                        break;
                    } else {
                        JSONObject jSONObject5 = jSONObject.getJSONObject("data");
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put("reqId", jSONObject.getString("reqId"));
                        jSONObject6.put("resType", reqType);
                        jSONObject6.put("resVer", jSONObject.getInt("reqVer"));
                        jSONObject6.put(NotificationCompat.CATEGORY_STATUS, "ER");
                        if (AppUtils.isNetConnected(this.f6164a)) {
                            if (BleApiManager.getInstance(this.f6164a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                                OnApplyNowClicked onApplyNowClicked = this.f;
                                String string = jSONObject5.getString("uid");
                                Intrinsics.checkNotNullExpressionValue(string, "data.getString(\"uid\")");
                                String string2 = jSONObject5.getString("faceId");
                                Intrinsics.checkNotNullExpressionValue(string2, "data.getString(\"faceId\")");
                                String string3 = jSONObject5.getString("faceType");
                                Intrinsics.checkNotNullExpressionValue(string3, "data.getString(\"faceType\")");
                                onApplyNowClicked.onClicked(string, string2, string3);
                                break;
                            } else {
                                OnJavascriptListener onJavascriptListener2 = this.b;
                                onJavascriptListener2.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject6 + HexStringBuilder.COMMENT_END_CHAR);
                                b();
                                break;
                            }
                        } else {
                            Activity activity = this.f6164a;
                            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
                            ((BaseActivity) activity).showNoInternetMessage();
                            Activity activity2 = this.f6164a;
                            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type android.app.Activity");
                            activity2.finish();
                            break;
                        }
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final void b() {
        Activity activity = this.f6164a;
        Drawable drawable = activity.getResources().getDrawable(R.drawable.failure_image_img);
        Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…ilure_image_img\n        )");
        String string = this.f6164a.getString(com.coveiot.android.watchfaceui.R.string.please_connect);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(com.co….R.string.please_connect)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(activity, drawable, string, "", true);
        String string2 = this.f6164a.getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.ok)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.webSupport.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JSInterface.c(BottomSheetDialogImageTitleMessage.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessage.show();
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
                    String str = k;
                    Log.w(str, "Value for key " + next + " not one of [String, Integer, Double]");
                }
            }
            return bundle;
        } catch (JSONException e) {
            Log.w(k, "Failed to parse JSON, returning empty Bundle.", e);
            return new Bundle();
        }
    }

    @NotNull
    public final Activity getContext() {
        return this.f6164a;
    }

    public final boolean getFromDashboard() {
        return this.g;
    }

    public final boolean getFromNotification() {
        return this.h;
    }

    @NotNull
    public final FirebaseAnalytics getMAnalytics() {
        return this.j;
    }

    @NotNull
    public final NavigationListener getNavigationListener() {
        return this.c;
    }

    @Nullable
    public final String getNotifIdentifier() {
        return this.i;
    }

    @NotNull
    public final OnApplyNowClicked getOnApplyNowClicked() {
        return this.f;
    }

    @NotNull
    public final OnCameraEvent getOnCameraEvent() {
        return this.e;
    }

    @NotNull
    public final OnJavascriptListener getOnJavascriptListener() {
        return this.b;
    }

    @NotNull
    public final OnPickerResult getOnPickerResult() {
        return this.d;
    }

    public final void logEvent(@NotNull String name, @NotNull String jsonParams) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(jsonParams, "jsonParams");
        LogHelper.d("logEvent:", name);
        this.j.logEvent(name, bundleFromJson(jsonParams));
    }

    public final void navigateToActivity(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!Utils.isCYDevice(context)) {
            Utils utils = Utils.INSTANCE;
            if (!utils.isCADevice(context) && !utils.isCZDevice(context)) {
                ((Activity) context).finish();
                return;
            }
        }
        Intent intent = new Intent(context, ActivityWatchFace.class);
        intent.setFlags(67108864);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    @JavascriptInterface
    public final void result(@NotNull String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        String str = k;
        LogHelper.d(str, "Res: " + json);
        try {
            JSONObject jSONObject = new JSONObject(json);
            if (jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)) {
                Intrinsics.checkNotNullExpressionValue(jSONObject.getString("file"), "`object`.getString(\"file\")");
                Intrinsics.checkNotNullExpressionValue(jSONObject.getString("preview"), "`object`.getString(\"preview\")");
                return;
            }
            Log.e(str, "Status: false");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final void setContext(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.f6164a = activity;
    }

    public final void setFromDashboard(boolean z) {
        this.g = z;
    }

    public final void setFromNotification(boolean z) {
        this.h = z;
    }

    public final void setMAnalytics(@NotNull FirebaseAnalytics firebaseAnalytics) {
        Intrinsics.checkNotNullParameter(firebaseAnalytics, "<set-?>");
        this.j = firebaseAnalytics;
    }

    public final void setNavigationListener(@NotNull NavigationListener navigationListener) {
        Intrinsics.checkNotNullParameter(navigationListener, "<set-?>");
        this.c = navigationListener;
    }

    public final void setNotifIdentifier(@Nullable String str) {
        this.i = str;
    }

    public final void setOnApplyNowClicked(@NotNull OnApplyNowClicked onApplyNowClicked) {
        Intrinsics.checkNotNullParameter(onApplyNowClicked, "<set-?>");
        this.f = onApplyNowClicked;
    }

    public final void setOnCameraEvent(@NotNull OnCameraEvent onCameraEvent) {
        Intrinsics.checkNotNullParameter(onCameraEvent, "<set-?>");
        this.e = onCameraEvent;
    }

    public final void setOnJavascriptListener(@NotNull OnJavascriptListener onJavascriptListener) {
        Intrinsics.checkNotNullParameter(onJavascriptListener, "<set-?>");
        this.b = onJavascriptListener;
    }

    public final void setOnPickerResult(@NotNull OnPickerResult onPickerResult) {
        Intrinsics.checkNotNullParameter(onPickerResult, "<set-?>");
        this.d = onPickerResult;
    }

    public final void setUserProperty(@NotNull String name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        LogHelper.d("setUserProperty", "setUserProperty:" + name);
        this.j.setUserProperty(name, value);
    }

    @JavascriptInterface
    public final void webIn(@NotNull String reqStr) {
        Intrinsics.checkNotNullParameter(reqStr, "reqStr");
        LogHelper.d("webIn", reqStr);
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
                case -1958432627:
                    if (upperCase.equals("WF_DIY_CREATED")) {
                        try {
                            if (this.g) {
                                Activity activity = this.f6164a;
                                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type android.app.Activity");
                                this.f6164a.setResult(-1, activity.getIntent());
                                Activity activity2 = this.f6164a;
                                Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type android.app.Activity");
                                activity2.finish();
                            } else {
                                navigateToActivity(this.f6164a);
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                case -310667773:
                    if (upperCase.equals("ANLY_SET_USR_PROPS")) {
                        LogHelper.d(k, "inside ANLY_SET_USR_PROPS");
                        try {
                            String string = jSONObject.getJSONObject("data").getJSONObject("userProp").getString("kh_webapp_agent");
                            Intrinsics.checkNotNullExpressionValue(string, "jsonObject.getJSONObject…String(\"kh_webapp_agent\")");
                            setUserProperty("kh_webapp_agent", string);
                            String string2 = jSONObject.getJSONObject("data").getJSONObject("userProp").getString("kh_webapp_id");
                            Intrinsics.checkNotNullExpressionValue(string2, "jsonObject.getJSONObject…getString(\"kh_webapp_id\")");
                            setUserProperty("kh_webapp_id", string2);
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    return;
                case -222011733:
                    if (upperCase.equals("CLEVER_TAP_LOG_EVENT")) {
                        try {
                            AnalyticsLog analyticsLog = new AnalyticsLog();
                            JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                            if (jSONObject3 == null || jSONObject3.isNull("eventName")) {
                                return;
                            }
                            HashMap<String, Object> hashMap = new HashMap<>();
                            String string3 = jSONObject3.getString("eventName");
                            Intrinsics.checkNotNullExpressionValue(string3, "dataJSONObject.getString(\"eventName\")");
                            analyticsLog.setEventName(string3);
                            if (!jSONObject3.isNull("eventProp")) {
                                JSONObject jSONObject4 = jSONObject3.getJSONObject("eventProp");
                                CleverTapConstants.CustomEventProperties customEventProperties = CleverTapConstants.CustomEventProperties.WATCH_FACE_TYPE;
                                if (!jSONObject4.isNull(customEventProperties.getValue())) {
                                    hashMap.put(customEventProperties.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties2 = CleverTapConstants.CustomEventProperties.UPDATE_LOCATION;
                                if (!jSONObject4.isNull(customEventProperties2.getValue())) {
                                    hashMap.put(customEventProperties2.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties2.getValue()));
                                }
                            }
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            hashMap.putAll(companion.getDeviceId(this.f6164a));
                            hashMap.putAll(companion.getWatchDetails(this.f6164a));
                            String string4 = jSONObject3.getString("eventName");
                            Intrinsics.checkNotNullExpressionValue(string4, "dataJSONObject.getString(\"eventName\")");
                            companion.logAnalyticsEvent(string4, hashMap);
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 2030823:
                    upperCase.equals("BACK");
                    return;
                case 34575283:
                    if (upperCase.equals("#INIT")) {
                        jSONObject2.put("resType", "#ACK");
                        jSONObject2.put("resVer", 1);
                        jSONObject2.put("resId", "INIT_0");
                        jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                        if (this.h && this.i != null) {
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("cv_notif_identifier", this.i);
                            jSONObject2.put("data", jSONObject5);
                        }
                        String str = k;
                        LogHelper.d(str, "Init json:" + jSONObject2);
                        OnJavascriptListener onJavascriptListener = this.b;
                        onJavascriptListener.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
                        return;
                    }
                    return;
                case 494239258:
                    if (upperCase.equals("ANLY_LOG_EVENT")) {
                        LogHelper.d(k, "inside ANLY_LOG_EVENT");
                        try {
                            String string5 = jSONObject.getJSONObject("data").getString("eventName");
                            Intrinsics.checkNotNullExpressionValue(string5, "jsonObject.getJSONObject…\").getString(\"eventName\")");
                            String jSONObject6 = jSONObject.getJSONObject("data").getJSONObject("eventProp").toString();
                            Intrinsics.checkNotNullExpressionValue(jSONObject6, "jsonObject.getJSONObject…t(\"eventProp\").toString()");
                            logEvent(string5, jSONObject6);
                            return;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 748180178:
                    if (upperCase.equals("#WIN_CLOSE")) {
                        LogHelper.d(k, "#WINCLOSE");
                        Activity activity3 = this.f6164a;
                        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type android.app.Activity");
                        activity3.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (JSONException e5) {
            e5.printStackTrace();
        }
        e5.printStackTrace();
    }
}
