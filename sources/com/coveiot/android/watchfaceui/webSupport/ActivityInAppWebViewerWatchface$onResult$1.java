package com.coveiot.android.watchfaceui.webSupport;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
/* loaded from: classes8.dex */
public final class ActivityInAppWebViewerWatchface$onResult$1 implements PickerDialog.Companion.SelectionPickerListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityInAppWebViewerWatchface f6163a;
    public final /* synthetic */ String b;
    public final /* synthetic */ int c;
    public final /* synthetic */ String d;
    public final /* synthetic */ String e;

    public ActivityInAppWebViewerWatchface$onResult$1(ActivityInAppWebViewerWatchface activityInAppWebViewerWatchface, String str, int i, String str2, String str3) {
        this.f6163a = activityInAppWebViewerWatchface;
        this.b = str;
        this.c = i;
        this.d = str2;
        this.e = str3;
    }

    public static final void c(String str, int i, String str2, String value, int i2, String str3, final ActivityInAppWebViewerWatchface this$0) {
        Intrinsics.checkNotNullParameter(value, "$value");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("resType", str);
        jSONObject.put("resVer", i);
        jSONObject.put("resId", str2);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("option", value);
        jSONObject2.put(FirebaseAnalytics.Param.INDEX, i2);
        jSONObject.put("selected", jSONObject2);
        jSONObject.put("type", str3);
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(mWebView);
        mWebView.evaluateJavascript("javascript:CoveJsInterface.appOut( " + jSONObject + HexStringBuilder.COMMENT_END_CHAR, new ValueCallback() { // from class: com.coveiot.android.watchfaceui.webSupport.q
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewerWatchface$onResult$1.d(ActivityInAppWebViewerWatchface.this, (String) obj);
            }
        });
    }

    public static final void d(ActivityInAppWebViewerWatchface this$0, String str) {
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionPickerListener
    public void onPickerValueSelected(@NotNull final String value, final int i) {
        Intrinsics.checkNotNullParameter(value, "value");
        new ArrayList().add(value);
        final ActivityInAppWebViewerWatchface activityInAppWebViewerWatchface = this.f6163a;
        final String str = this.b;
        final int i2 = this.c;
        final String str2 = this.d;
        final String str3 = this.e;
        activityInAppWebViewerWatchface.runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.webSupport.r
            @Override // java.lang.Runnable
            public final void run() {
                ActivityInAppWebViewerWatchface$onResult$1.c(str, i2, str2, value, i, str3, activityInAppWebViewerWatchface);
            }
        });
    }
}
