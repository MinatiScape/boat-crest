package com.coveiot.android.leonardo.threshold;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.constant.PermissionConstants;
import com.coveiot.android.dynamictab.model.ContactSupportInfo;
import com.coveiot.android.leonardo.threshold.model.AlertWebDataModel;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public final class AlertJsInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "AlertJsInterface";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5413a;
    @NotNull
    public OnJavascriptListener b;
    @NotNull
    public final AlertWebDataModel c;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public interface OnJavascriptListener {
        void evaluateJavascript(@Nullable String str);

        @NotNull
        String getLanguage();

        void onHealthQuestionaryComplete();

        void openLinkInBrowser(@Nullable String str);

        void updatePlan();

        void updateProfile(@NotNull JSONObject jSONObject);
    }

    public AlertJsInterface(@NotNull Context context, @NotNull OnJavascriptListener onJavascriptListener, @NotNull AlertWebDataModel alertWebDataModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onJavascriptListener, "onJavascriptListener");
        Intrinsics.checkNotNullParameter(alertWebDataModel, "alertWebDataModel");
        this.f5413a = context;
        this.b = onJavascriptListener;
        this.c = alertWebDataModel;
    }

    @JavascriptInterface
    @RequiresApi(api = 19)
    public final void appIn(@NotNull String reqStr) {
        ContactSupportInfo contactSupportInfo;
        Intrinsics.checkNotNullParameter(reqStr, "reqStr");
        Log.d(TAG, "appIn: " + reqStr);
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
                        Log.d(TAG, "GET_X_HEADERS json:" + jSONObject2);
                        OnJavascriptListener onJavascriptListener = this.b;
                        onJavascriptListener.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
                        break;
                    }
                case -825318447:
                    if (upperCase.equals("INIT_CONTACT") && (contactSupportInfo = (ContactSupportInfo) new Gson().fromJson(jSONObject.toString(), (Class<Object>) ContactSupportInfo.class)) != null && contactSupportInfo.getData() != null && contactSupportInfo.getData().getMethod() != null) {
                        if (m.equals(contactSupportInfo.getData().getMethod(), PermissionConstants.PHONE, true)) {
                            String to = contactSupportInfo.getData().getTo();
                            Intrinsics.checkNotNullExpressionValue(to, "contactSupportInfo.data.to");
                            openPhoneDailerWith(to);
                            break;
                        } else if (m.equals(contactSupportInfo.getData().getMethod(), "EMAIL", true)) {
                            String to2 = contactSupportInfo.getData().getTo();
                            Intrinsics.checkNotNullExpressionValue(to2, "contactSupportInfo.data.to");
                            openEmailClientWith(to2);
                            break;
                        }
                    }
                    break;
                case 120222327:
                    if (!upperCase.equals("HEALTH_BREACH_DETAILS")) {
                        break;
                    } else {
                        jSONObject2.put("resType", "HEALTH_BREACH_DETAILS");
                        jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                        jSONObject2.put("reqId", jSONObject.getString("reqId"));
                        jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                        jSONObject2.put("resId", UUID.randomUUID().toString());
                        jSONObject2.put("data", new JSONObject(new Gson().toJson(this.c.getData())));
                        OnJavascriptListener onJavascriptListener2 = this.b;
                        onJavascriptListener2.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
                        break;
                    }
                case 1496225874:
                    if (!upperCase.equals("#METADATA")) {
                        break;
                    } else {
                        jSONObject2.put("resType", "#METADATA");
                        jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                        jSONObject2.put("reqId", jSONObject.getString("reqId"));
                        jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("lang", this.b.getLanguage());
                        jSONObject2.put("data", jSONObject5);
                        OnJavascriptListener onJavascriptListener3 = this.b;
                        onJavascriptListener3.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
                        break;
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public final AlertWebDataModel getAlertWebDataModel() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f5413a;
    }

    @NotNull
    public final OnJavascriptListener getOnJavascriptListener() {
        return this.b;
    }

    public final void openEmailClientWith(@NotNull String emailId) {
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("plain/text");
        intent.putExtra("android.intent.extra.EMAIL", emailId);
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", "");
        this.f5413a.startActivity(Intent.createChooser(intent, ""));
    }

    public final void openPhoneDailerWith(@NotNull String phoneNumber) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        try {
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + phoneNumber));
            this.f5413a.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5413a = context;
    }

    public final void setOnJavascriptListener(@NotNull OnJavascriptListener onJavascriptListener) {
        Intrinsics.checkNotNullParameter(onJavascriptListener, "<set-?>");
        this.b = onJavascriptListener;
    }

    @JavascriptInterface
    public final void webOut(@NotNull String resStr) {
        String str;
        Intrinsics.checkNotNullParameter(resStr, "resStr");
        Log.d(TAG, "webout:" + resStr);
        try {
            JSONObject jSONObject = new JSONObject(resStr);
            JSONObject jSONObject2 = new JSONObject();
            String string = jSONObject.getString("resType");
            if (string != null) {
                str = string.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toUpperCase()");
            } else {
                str = null;
            }
            if (str != null) {
                switch (str.hashCode()) {
                    case -505309570:
                        if (str.equals("USR_FIT_PLAN_UPDATED")) {
                            Log.d(TAG, "USR_FIT_PLAN_UPDATED");
                            this.b.updatePlan();
                            return;
                        }
                        return;
                    case -291504207:
                        if (str.equals("HEALTH_CHECK_COMPLETED")) {
                            this.b.onHealthQuestionaryComplete();
                            return;
                        }
                        return;
                    case 34575283:
                        if (str.equals("#INIT")) {
                            jSONObject2.put("resType", "#ACK");
                            jSONObject2.put("resVer", 1);
                            jSONObject2.put("resId", "INIT_0");
                            jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                            Log.d(TAG, "Init json:" + jSONObject2);
                            this.b.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
                            return;
                        }
                        return;
                    case 34755821:
                        if (str.equals("#OPEN")) {
                            Log.d(TAG, "OPEN");
                            JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                            this.b.openLinkInBrowser(jSONObject3 != null ? jSONObject3.getString("url") : null);
                            return;
                        }
                        return;
                    case 748180178:
                        if (str.equals("#WIN_CLOSE")) {
                            Log.d(TAG, "#WINCLOSE");
                            Context context = this.f5413a;
                            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
                            ((Activity) context).finish();
                            return;
                        }
                        return;
                    case 1306511130:
                        if (str.equals("USR_PROFILE_UPDATED")) {
                            Log.d(TAG, "USR_PROFILE_UPDATED");
                            if (Intrinsics.areEqual(jSONObject.getString(NotificationCompat.CATEGORY_STATUS), CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                                JSONObject jSONObject4 = jSONObject.getJSONObject("data");
                                OnJavascriptListener onJavascriptListener = this.b;
                                Intrinsics.checkNotNull(jSONObject4);
                                onJavascriptListener.updateProfile(jSONObject4);
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
