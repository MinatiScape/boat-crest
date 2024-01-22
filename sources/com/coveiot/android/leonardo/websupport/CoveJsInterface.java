package com.coveiot.android.leonardo.websupport;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.net.MailTo;
import com.blankj.utilcode.constant.PermissionConstants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinContacts;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.websupport.model.ContactSupportInfo;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public final class CoveJsInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String d = "CoveJsInterface";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5465a;
    @NotNull
    public OnJavascriptListener b;
    @NotNull
    public FirebaseAnalytics c;

    /* loaded from: classes5.dex */
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

    /* loaded from: classes5.dex */
    public interface OnJavascriptListener {
        void evaluateJavascript(@Nullable String str);

        void openLinkInBrowser(@Nullable String str);
    }

    public CoveJsInterface(@NotNull Context context, @NotNull OnJavascriptListener onJavascriptListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onJavascriptListener, "onJavascriptListener");
        this.f5465a = context;
        this.b = onJavascriptListener;
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(firebaseAnalytics, "getInstance(context)");
        this.c = firebaseAnalytics;
    }

    public final void a(String str) {
        Object systemService = ContextCompat.getSystemService(this.f5465a, ClipboardManager.class);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipData newPlainText = ClipData.newPlainText("", str);
        Intrinsics.checkNotNullExpressionValue(newPlainText, "newPlainText(\"\", text)");
        ((ClipboardManager) systemService).setPrimaryClip(newPlainText);
        Toast.makeText(this.f5465a, "Copied to Clipboard", 0).show();
    }

    @JavascriptInterface
    public final void appIn(@NotNull String reqStr) {
        ContactSupportInfo contactSupportInfo;
        Intrinsics.checkNotNullParameter(reqStr, "reqStr");
        LogHelper.d("appIn", reqStr);
        try {
            JSONObject jSONObject = new JSONObject(reqStr);
            JSONObject jSONObject2 = new JSONObject();
            String reqType = jSONObject.getString("reqType");
            Intrinsics.checkNotNullExpressionValue(reqType, "reqType");
            String upperCase = reqType.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            int hashCode = upperCase.hashCode();
            if (hashCode != -1714631498) {
                if (hashCode != -825318447) {
                    if (hashCode == 963351859 && upperCase.equals("#CLIPBOARD_WRITE")) {
                        LogHelper.d(d, "inside COPY_TO_CLIPBOARD");
                        try {
                            String text = jSONObject.getJSONObject("data").getString("text");
                            Intrinsics.checkNotNullExpressionValue(text, "text");
                            a(text);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (upperCase.equals("INIT_CONTACT") && (contactSupportInfo = (ContactSupportInfo) new Gson().fromJson(jSONObject.toString(), (Class<Object>) ContactSupportInfo.class)) != null && contactSupportInfo.getData() != null && contactSupportInfo.getData().getMethod() != null) {
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
            } else if (upperCase.equals("GET_X_HEADERS")) {
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
                String str = d;
                LogHelper.d(str, "GET_X_HEADERS json:" + jSONObject2);
                OnJavascriptListener onJavascriptListener = this.b;
                onJavascriptListener.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject2 + HexStringBuilder.COMMENT_END_CHAR);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
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
        return this.f5465a;
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

    public final void openContactScreen(int i) {
        Intent intent = new Intent(this.f5465a, ActivityBoatCoinContacts.class);
        intent.putExtra("coin", i);
        this.f5465a.startActivity(intent);
    }

    public final void openEmailClientWith(@NotNull String emailId) {
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME + emailId));
        this.f5465a.startActivity(Intent.createChooser(intent, ""));
    }

    public final void openPhoneDailerWith(@NotNull String phoneNumber) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        try {
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + phoneNumber));
            this.f5465a.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5465a = context;
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
                case -883454084:
                    if (upperCase.equals("#RELOAD")) {
                        LogHelper.d(d, "inside RELOAD");
                        try {
                            String url = jSONObject.getJSONObject("data").getString("url");
                            AppNavigator.Companion companion = AppNavigator.Companion;
                            Context context = this.f5465a;
                            Intrinsics.checkNotNullExpressionValue(url, "url");
                            companion.navigateToBoatCoinsWebViewer(context, url, BoatCoinsScreenCaller.NULL);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
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
                            JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                            if (jSONObject3 == null || jSONObject3.isNull("eventName")) {
                                return;
                            }
                            HashMap<String, Object> hashMap = new HashMap<>();
                            if (!jSONObject3.isNull("eventProp")) {
                                JSONObject jSONObject4 = jSONObject3.getJSONObject("eventProp");
                                CleverTapConstants.CustomEventProperties customEventProperties = CleverTapConstants.CustomEventProperties.VOUCHER_ID;
                                if (!jSONObject4.isNull(customEventProperties.getValue())) {
                                    hashMap.put(customEventProperties.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties2 = CleverTapConstants.CustomEventProperties.PARTNER_NAME;
                                if (!jSONObject4.isNull(customEventProperties2.getValue())) {
                                    hashMap.put(customEventProperties2.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties2.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties3 = CleverTapConstants.CustomEventProperties.PARTNER_VOUCHER_DESCRIPTION;
                                if (!jSONObject4.isNull(customEventProperties3.getValue())) {
                                    hashMap.put(customEventProperties3.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties3.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties4 = CleverTapConstants.CustomEventProperties.VOUCHER_EXPIRY;
                                if (!jSONObject4.isNull(customEventProperties4.getValue())) {
                                    Date parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH).parse(jSONObject3.getJSONObject("eventProp").getString(customEventProperties4.getValue()));
                                    Intrinsics.checkNotNullExpressionValue(parse, "inputFormat.parse(dateString)");
                                    hashMap.put(customEventProperties4.getValue(), parse);
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties5 = CleverTapConstants.CustomEventProperties.COINS_REDEEMED;
                                if (!jSONObject4.isNull(customEventProperties5.getValue())) {
                                    hashMap.put(customEventProperties5.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties5.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties6 = CleverTapConstants.CustomEventProperties.BOAT_COINS_PAGE_SOURCE;
                                if (!jSONObject4.isNull(customEventProperties6.getValue())) {
                                    hashMap.put(customEventProperties6.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties6.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties7 = CleverTapConstants.CustomEventProperties.VOUCHER_PAGE_VIEW_SOURCE;
                                if (!jSONObject4.isNull(customEventProperties7.getValue())) {
                                    hashMap.put(customEventProperties7.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties7.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties8 = CleverTapConstants.CustomEventProperties.VOUCHER_REDEEM_PAGE_VIEW_SOURCE;
                                if (!jSONObject4.isNull(customEventProperties8.getValue())) {
                                    hashMap.put(customEventProperties8.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties8.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties9 = CleverTapConstants.CustomEventProperties.ALL_VOUCHER_PAGE_VIEW_SOURCE;
                                if (!jSONObject4.isNull(customEventProperties9.getValue())) {
                                    hashMap.put(customEventProperties9.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties9.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties10 = CleverTapConstants.CustomEventProperties.MY_VOUCHER_PAGE_VIEW_SOURCE;
                                if (!jSONObject4.isNull(customEventProperties10.getValue())) {
                                    hashMap.put(customEventProperties10.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties10.getValue()));
                                }
                                CleverTapConstants.CustomEventProperties customEventProperties11 = CleverTapConstants.CustomEventProperties.VOUCHER_GENERATE_PAGE_VIEW_SOURCE;
                                if (!jSONObject4.isNull(customEventProperties11.getValue())) {
                                    hashMap.put(customEventProperties11.getValue(), jSONObject3.getJSONObject("eventProp").getString(customEventProperties11.getValue()));
                                }
                            }
                            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                            String string3 = jSONObject3.getString("eventName");
                            Intrinsics.checkNotNullExpressionValue(string3, "dataJSONObject.getString(\"eventName\")");
                            companion2.logAnalyticsEvent(string3, hashMap);
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
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
                        return;
                    }
                    return;
                case 34755821:
                    if (upperCase.equals("#OPEN")) {
                        LogHelper.d(d, "inside OPEN");
                        try {
                            this.b.openLinkInBrowser(jSONObject.getJSONObject("data").getString("url"));
                            return;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 494239258:
                    if (upperCase.equals("ANLY_LOG_EVENT")) {
                        LogHelper.d(d, "inside ANLY_LOG_EVENT");
                        try {
                            String string4 = jSONObject.getJSONObject("data").getString("eventName");
                            Intrinsics.checkNotNullExpressionValue(string4, "jsonObject.getJSONObject…\").getString(\"eventName\")");
                            String jSONObject5 = jSONObject.getJSONObject("data").getJSONObject("eventProp").toString();
                            Intrinsics.checkNotNullExpressionValue(jSONObject5, "jsonObject.getJSONObject…t(\"eventProp\").toString()");
                            logEvent(string4, jSONObject5);
                            return;
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 748180178:
                    if (upperCase.equals("#WIN_CLOSE")) {
                        LogHelper.d(d, "#WINCLOSE");
                        Context context2 = this.f5465a;
                        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type android.app.Activity");
                        ((Activity) context2).finish();
                        return;
                    }
                    return;
                case 1082014660:
                    if (upperCase.equals("#TOAST")) {
                        LogHelper.d(d, "inside TOAST");
                        try {
                            String string5 = jSONObject.getJSONObject("data").getString("text");
                            String duration = jSONObject.getJSONObject("data").getString("duration");
                            Context context3 = this.f5465a;
                            Intrinsics.checkNotNullExpressionValue(duration, "duration");
                            Toast.makeText(context3, string5, Integer.parseInt(duration)).show();
                            return;
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 1894861843:
                    if (upperCase.equals("GIFT_COINS")) {
                        LogHelper.d(d, "inside GIFT_COINS");
                        try {
                            openContactScreen(jSONObject.getJSONObject("data").getInt("coins"));
                            return;
                        } catch (Exception e7) {
                            e7.printStackTrace();
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        } catch (JSONException e8) {
            e8.printStackTrace();
        }
        e8.printStackTrace();
    }
}
