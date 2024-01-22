package com.coveiot.android.dynamictab.sports;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.constant.PermissionConstants;
import com.coveiot.android.dynamictab.AppActionInterface;
import com.coveiot.android.dynamictab.DynamicTabWebActivity;
import com.coveiot.android.dynamictab.cricketmodels.OnAnimationBarClick;
import com.coveiot.android.dynamictab.model.ContactSupportInfo;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.CoveEventBusManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes4.dex */
public class CoveJsInterface {
    public static final String TAG = "CoveJsInterface";

    /* renamed from: a  reason: collision with root package name */
    public Context f4330a;
    public OnAnimationBarClick b;
    public AppActionInterface c;
    public FirebaseAnalytics d;
    public String e;

    /* loaded from: classes4.dex */
    public interface LocationResultListener {
        void onLocationReceived(Location location);

        void permissionDenied();
    }

    /* loaded from: classes4.dex */
    public class a implements Runnable {
        public final /* synthetic */ JSONObject h;

        /* renamed from: com.coveiot.android.dynamictab.sports.CoveJsInterface$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class C0267a implements ValueCallback<String> {
            public C0267a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                CoveJsInterface.this.a("onReceiveValue", str);
            }
        }

        public a(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + this.h.toString() + ")", new C0267a());
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Runnable {
        public final /* synthetic */ JSONObject h;

        /* loaded from: classes4.dex */
        public class a implements ValueCallback<String> {
            public a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                CoveJsInterface.this.a("onReceiveValue", str);
            }
        }

        public b(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + this.h.toString() + ")", new a());
        }
    }

    /* loaded from: classes4.dex */
    public class c implements Runnable {
        public final /* synthetic */ JSONObject h;

        /* loaded from: classes4.dex */
        public class a implements ValueCallback<String> {
            public a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                CoveJsInterface.this.a("onReceiveValue", str);
            }
        }

        public c(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + this.h.toString() + ")", new a());
        }
    }

    /* loaded from: classes4.dex */
    public class d implements Runnable {
        public final /* synthetic */ JSONObject h;

        /* loaded from: classes4.dex */
        public class a implements ValueCallback<String> {
            public a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                CoveJsInterface.this.a("onReceiveValue", str);
            }
        }

        public d(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + this.h.toString() + ")", new a());
        }
    }

    /* loaded from: classes4.dex */
    public class e implements LocationResultListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ JSONObject f4335a;
        public final /* synthetic */ JSONObject b;

        /* loaded from: classes4.dex */
        public class a implements Runnable {

            /* renamed from: com.coveiot.android.dynamictab.sports.CoveJsInterface$e$a$a  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            public class C0268a implements ValueCallback<String> {
                public C0268a() {
                }

                @Override // android.webkit.ValueCallback
                /* renamed from: a */
                public void onReceiveValue(String str) {
                    CoveJsInterface.this.a("onReceiveValue", str);
                }
            }

            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                WebView webView = DynamicTabWebActivity.mWebView;
                webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + e.this.f4335a.toString() + ")", new C0268a());
            }
        }

        /* loaded from: classes4.dex */
        public class b implements Runnable {

            /* loaded from: classes4.dex */
            public class a implements ValueCallback<String> {
                public a() {
                }

                @Override // android.webkit.ValueCallback
                /* renamed from: a */
                public void onReceiveValue(String str) {
                    CoveJsInterface.this.a("onReceiveValue", str);
                }
            }

            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                WebView webView = DynamicTabWebActivity.mWebView;
                webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + e.this.f4335a.toString() + ")", new a());
            }
        }

        public e(JSONObject jSONObject, JSONObject jSONObject2) {
            this.f4335a = jSONObject;
            this.b = jSONObject2;
        }

        @Override // com.coveiot.android.dynamictab.sports.CoveJsInterface.LocationResultListener
        public void onLocationReceived(Location location) {
            try {
                this.f4335a.put("resType", "#CURRENT_LOCATION");
                this.f4335a.put("resVer", this.b.getInt("reqVer"));
                this.f4335a.put("reqId", this.b.getString("reqId"));
                this.f4335a.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                JSONObject jSONObject = new JSONObject();
                ArrayList arrayList = new ArrayList();
                arrayList.add(Double.valueOf(location.getLongitude()));
                arrayList.add(Double.valueOf(location.getLatitude()));
                jSONObject.put("coordinate", new JSONArray((Collection<?>) arrayList));
                this.f4335a.put("data", jSONObject);
                CoveJsInterface.this.a("json", this.f4335a.toString());
                DynamicTabWebActivity.mWebView.post(new a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // com.coveiot.android.dynamictab.sports.CoveJsInterface.LocationResultListener
        public void permissionDenied() {
            try {
                this.f4335a.put("resType", "#CURRENT_LOCATION");
                this.f4335a.put("resVer", this.b.getInt("reqVer"));
                this.f4335a.put("reqId", this.b.getString("reqId"));
                this.f4335a.put(NotificationCompat.CATEGORY_STATUS, "ER");
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", "PERMISSION_DENIED");
                this.f4335a.put("data", jSONObject);
                CoveJsInterface.this.a("json", this.f4335a.toString());
                DynamicTabWebActivity.mWebView.post(new b());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class f implements Runnable {
        public final /* synthetic */ JSONObject h;

        /* loaded from: classes4.dex */
        public class a implements ValueCallback<String> {
            public a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                CoveJsInterface.this.a("onReceiveValue", str);
            }
        }

        public f(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.lang.Runnable
        @RequiresApi(api = 19)
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + this.h.toString() + ")", new a());
        }
    }

    @SuppressLint({"MissingPermission"})
    public CoveJsInterface(Context context) {
        this.f4330a = context;
        this.d = FirebaseAnalytics.getInstance(context);
        CoveEventBusManager.getInstance().getEventBus().register(context);
    }

    public final void a(String str, String str2) {
    }

    @JavascriptInterface
    @RequiresApi(api = 19)
    public void appIn(String str) {
        a("appIn", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            String string = jSONObject.getString("reqType");
            char c2 = 65535;
            switch (string.hashCode()) {
                case -1714631498:
                    if (string.equals("GET_X_HEADERS")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -825318447:
                    if (string.equals("INIT_CONTACT")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -481868290:
                    if (string.equals("#CURRENT_LOCATION")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 120222327:
                    if (string.equals("HEALTH_BREACH_DETAILS")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 311579064:
                    if (string.equals("PAY_REQUEST")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 1259284856:
                    if (string.equals("SELF_ASSESSMENT_DETAILS")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 1496225874:
                    if (string.equals("#METADATA")) {
                        c2 = 5;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    jSONObject2.put("resType", "GET_X_HEADERS");
                    jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                    jSONObject2.put("reqId", jSONObject.getString("reqId"));
                    jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                    JSONObject jSONObject3 = new JSONObject((Map<?, ?>) CoveApi.getCustomHeaders());
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("headers", jSONObject3);
                    jSONObject2.put("data", jSONObject4);
                    a("json", jSONObject2.toString());
                    DynamicTabWebActivity.mWebView.post(new a(jSONObject2));
                    return;
                case 1:
                    ContactSupportInfo contactSupportInfo = (ContactSupportInfo) new Gson().fromJson(jSONObject.toString(), (Class<Object>) ContactSupportInfo.class);
                    if (contactSupportInfo == null || contactSupportInfo.getData() == null || contactSupportInfo.getData().getMethod() == null) {
                        return;
                    }
                    if (contactSupportInfo.getData().getMethod().equalsIgnoreCase(PermissionConstants.PHONE)) {
                        openPhoneDailerWith(contactSupportInfo.getData().getTo());
                        return;
                    } else if (contactSupportInfo.getData().getMethod().equalsIgnoreCase("EMAIL")) {
                        openEmailClientWith(contactSupportInfo.getData().getTo());
                        return;
                    } else {
                        return;
                    }
                case 2:
                    jSONObject2.put("resType", "HEALTH_BREACH_DETAILS");
                    jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                    jSONObject2.put("reqId", jSONObject.getString("reqId"));
                    jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                    jSONObject2.put("resId", UUID.randomUUID().toString());
                    jSONObject2.put("data", new JSONObject(this.e));
                    DynamicTabWebActivity.mWebView.post(new b(jSONObject2));
                    return;
                case 3:
                    jSONObject2.put("resType", "SELF_ASSESSMENT_DETAILS");
                    jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                    jSONObject2.put("reqId", jSONObject.getString("reqId"));
                    jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                    jSONObject2.put("resId", UUID.randomUUID().toString());
                    jSONObject2.put("data", new JSONObject(this.e));
                    DynamicTabWebActivity.mWebView.post(new c(jSONObject2));
                    return;
                case 4:
                    jSONObject2.put("resType", "PAY_STATUS");
                    jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                    jSONObject2.put("reqId", jSONObject.getString("reqId"));
                    a("json", jSONObject2.toString());
                    ((DynamicTabWebActivity) this.f4330a).requestMoneyUpi(jSONObject2, jSONObject);
                    return;
                case 5:
                    jSONObject2.put("resType", "#METADATA");
                    jSONObject2.put("resVer", jSONObject.getInt("reqVer"));
                    jSONObject2.put("reqId", jSONObject.getString("reqId"));
                    jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("lang", this.c.getLanguage());
                    jSONObject2.put("data", jSONObject5);
                    a("json", jSONObject2.toString());
                    DynamicTabWebActivity.mWebView.post(new d(jSONObject2));
                    return;
                case 6:
                    this.c.onRequestLocation(new e(jSONObject2, jSONObject));
                    return;
                default:
                    return;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public final Bundle c(String str) {
        if (TextUtils.isEmpty(str)) {
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof String) {
                    bundle.putString(next, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(next, ((Double) obj).doubleValue());
                } else {
                    Log.w("CoveJsInterface", "Value for key " + next + " not one of [String, Integer, Double]");
                }
            }
            return bundle;
        } catch (JSONException e2) {
            Log.w("CoveJsInterface", "Failed to parse JSON, returning empty Bundle.", e2);
            return new Bundle();
        }
    }

    public String getAlertWebModelString() {
        return this.e;
    }

    public void logEvent(String str, String str2) {
        a("logEvent:", str);
        this.d.logEvent(str, c(str2));
    }

    public void openEmailClientWith(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("plain/text");
        intent.putExtra("android.intent.extra.EMAIL", str);
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", "");
        this.f4330a.startActivity(Intent.createChooser(intent, ""));
    }

    public void openPhoneDailerWith(String str) {
        try {
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + str));
            this.f4330a.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setAlertWebModelString(String str) {
        this.e = str;
    }

    public void setAppInteractionListner(AppActionInterface appActionInterface) {
        this.c = appActionInterface;
    }

    public void setUserProperty(String str, String str2) {
        a("setUserProperty", "setUserProperty:" + str);
        this.d.setUserProperty(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0130 A[Catch: JSONException -> 0x017d, TryCatch #2 {JSONException -> 0x017d, blocks: (B:3:0x0011, B:5:0x0031, B:6:0x0055, B:8:0x005d, B:9:0x006d, B:12:0x0075, B:16:0x0098, B:17:0x009d, B:19:0x00a3, B:23:0x00c9, B:24:0x00ce, B:26:0x00d8, B:28:0x00e4, B:30:0x00ea, B:51:0x0124, B:52:0x012a, B:53:0x0130, B:37:0x00ff, B:40:0x0108, B:43:0x0112, B:54:0x0136, B:56:0x013e, B:64:0x015e, B:59:0x0152, B:65:0x016f, B:67:0x0177, B:20:0x00a8, B:13:0x007a), top: B:76:0x0011, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015e A[Catch: JSONException -> 0x017d, TryCatch #2 {JSONException -> 0x017d, blocks: (B:3:0x0011, B:5:0x0031, B:6:0x0055, B:8:0x005d, B:9:0x006d, B:12:0x0075, B:16:0x0098, B:17:0x009d, B:19:0x00a3, B:23:0x00c9, B:24:0x00ce, B:26:0x00d8, B:28:0x00e4, B:30:0x00ea, B:51:0x0124, B:52:0x012a, B:53:0x0130, B:37:0x00ff, B:40:0x0108, B:43:0x0112, B:54:0x0136, B:56:0x013e, B:64:0x015e, B:59:0x0152, B:65:0x016f, B:67:0x0177, B:20:0x00a8, B:13:0x007a), top: B:76:0x0011, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void webOut(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dynamictab.sports.CoveJsInterface.webOut(java.lang.String):void");
    }

    @SuppressLint({"MissingPermission"})
    public CoveJsInterface(Context context, OnAnimationBarClick onAnimationBarClick) {
        this.f4330a = context;
        this.b = onAnimationBarClick;
        this.d = FirebaseAnalytics.getInstance(context);
        CoveEventBusManager.getInstance().getEventBus().register(context);
    }
}
