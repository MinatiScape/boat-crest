package com.coveiot.android.activitymodes.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class WorkoutJsInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "WorkoutJsInterface";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f2880a;
    @NotNull
    public OnJavascriptListener b;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public interface OnJavascriptListener {
        void evaluateJavascript(@Nullable String str);

        void openEditProfile();

        void openLinkInBrowser(@Nullable String str);

        void openNoInternetDialog();

        void updatePlan();

        void updateProfile(@NotNull JSONObject jSONObject);
    }

    public WorkoutJsInterface(@NotNull Context context, @NotNull OnJavascriptListener onJavascriptListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onJavascriptListener, "onJavascriptListener");
        this.f2880a = context;
        this.b = onJavascriptListener;
    }

    @JavascriptInterface
    @RequiresApi(api = 19)
    public final void appIn(@NotNull String reqStr) {
        Intrinsics.checkNotNullParameter(reqStr, "reqStr");
        Log.d(TAG, "appIn: " + reqStr);
        try {
            JSONObject jSONObject = new JSONObject(reqStr);
            JSONObject jSONObject2 = new JSONObject();
            String reqType = jSONObject.getString("reqType");
            Intrinsics.checkNotNullExpressionValue(reqType, "reqType");
            String upperCase = reqType.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            if (Intrinsics.areEqual(upperCase, "GET_X_HEADERS")) {
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
            } else if (Intrinsics.areEqual(upperCase, "UPDATE_USR_PROFILE")) {
                try {
                    this.b.openEditProfile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f2880a;
    }

    @NotNull
    public final OnJavascriptListener getOnJavascriptListener() {
        return this.b;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f2880a = context;
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
                            Context context = this.f2880a;
                            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
                            ((Activity) context).finish();
                            return;
                        }
                        return;
                    case 1082014660:
                        if (str.equals("#TOAST")) {
                            Log.d(TAG, "TOAST");
                            JSONObject jSONObject4 = jSONObject.getJSONObject("data");
                            if (jSONObject4 != null) {
                                jSONObject4.getString(Constants.KEY_ACTION);
                            }
                            this.b.openNoInternetDialog();
                            return;
                        }
                        return;
                    case 1306511130:
                        if (str.equals("USR_PROFILE_UPDATED")) {
                            Log.d(TAG, "USR_PROFILE_UPDATED");
                            if (Intrinsics.areEqual(jSONObject.getString(NotificationCompat.CATEGORY_STATUS), CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                                JSONObject jSONObject5 = jSONObject.getJSONObject("data");
                                OnJavascriptListener onJavascriptListener = this.b;
                                Intrinsics.checkNotNull(jSONObject5);
                                onJavascriptListener.updateProfile(jSONObject5);
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
