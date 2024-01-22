package com.coveiot.android.remotecommandframework.alexa.jsinterface;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.coveiot.android.remotecommandframework.alexa.jsinterface.model.AuthSessionTokenInfo;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.GetAuthSessionTokenRes;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.CoveEventBusManager;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class CoveJsInterface {
    public static String TAG = "CoveJsInterface";

    /* renamed from: a  reason: collision with root package name */
    public Context f5630a;
    public AlexaJsInterfaceListener b;

    /* loaded from: classes6.dex */
    public class a implements CoveApiListener<GetAuthSessionTokenRes, CoveApiErrorModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f5631a;
        public final /* synthetic */ String b;
        public final /* synthetic */ JSONObject c;
        public final /* synthetic */ JSONObject d;

        public a(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2) {
            this.f5631a = str;
            this.b = str2;
            this.c = jSONObject;
            this.d = jSONObject2;
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
            try {
                this.d.put(NotificationCompat.CATEGORY_STATUS, ExifInterface.LONGITUDE_EAST);
                CoveJsInterface.this.b.onAppOut(new Gson().toJson(this.d));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(GetAuthSessionTokenRes getAuthSessionTokenRes) {
            String str = CoveApiConstants.RESPONSE_STATUS_VALUE_OK;
            try {
                if (!getAuthSessionTokenRes.getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    str = ExifInterface.LONGITUDE_EAST;
                }
                AuthSessionTokenInfo authSessionTokenInfo = new AuthSessionTokenInfo();
                authSessionTokenInfo.setReqId(this.f5631a);
                authSessionTokenInfo.setResType(this.b);
                authSessionTokenInfo.setResVer(this.c.getInt("reqVer"));
                authSessionTokenInfo.setStatus(str);
                if (getAuthSessionTokenRes.getData() != null) {
                    AuthSessionTokenInfo.Data data = new AuthSessionTokenInfo.Data();
                    data.setCtx(getAuthSessionTokenRes.getData().getCtx());
                    data.setAuthSessionToken(getAuthSessionTokenRes.getData().getAuthSessionToken());
                    authSessionTokenInfo.setData(data);
                }
                CoveJsInterface.this.b.onAppOut(new Gson().toJson(authSessionTokenInfo));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public CoveJsInterface(Context context, AlexaJsInterfaceListener alexaJsInterfaceListener) {
        this.f5630a = context;
        this.b = alexaJsInterfaceListener;
        CoveEventBusManager.getInstance().getEventBus().register(context);
    }

    public final void a(String str, String str2) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (r10 == 1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0046, code lost:
        r10 = r6.getJSONObject("data");
        com.coveiot.utils.utility.LogHelper.d(com.coveiot.android.remotecommandframework.alexa.jsinterface.CoveJsInterface.TAG, r10.toString());
        r1 = new com.coveiot.coveaccess.model.server.GetAuthSessionTokenReq();
        r1.setCtx(r10.getString("ctx"));
        r1.setClientKrn(r10.getString("clientKrn"));
        r7.put("resType", r5);
        r7.put("reqId", r4);
        com.coveiot.coveaccess.AuthTokenManager.getAuthSessionToken(r1, new com.coveiot.android.remotecommandframework.alexa.jsinterface.CoveJsInterface.a(r9, r4, r5, r6, r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void appIn(java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.alexa.jsinterface.CoveJsInterface.appIn(java.lang.String):void");
    }

    @JavascriptInterface
    public void webOut(String str) {
        a("webOut", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            Log.d(TAG, jSONObject.toString());
            JSONObject jSONObject2 = new JSONObject();
            String string = jSONObject.getString("resType");
            if (string.equalsIgnoreCase("#INIT")) {
                jSONObject2.put("resType", "#ACK");
                jSONObject2.put("resVer", 1);
                jSONObject2.put("resId", "INIT_0");
                jSONObject2.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                this.b.onAppOut(jSONObject2.toString());
            } else if (string.equalsIgnoreCase("#WIN_CLOSE")) {
                a("WIN_CLOSE", "#WINCLOSE");
                this.b.winClose();
            } else if (string.equalsIgnoreCase("#OPEN")) {
                this.f5630a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(jSONObject.getJSONObject("data").getString("url"))));
            } else if (string.equalsIgnoreCase("ALEXA_SKILL_STATUS")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                Long valueOf = Long.valueOf(jSONObject3.getLong("userDeviceId"));
                if (valueOf != null && !PreferenceManager.getInstance().getUserDeviceID().isEmpty() && Long.parseLong(PreferenceManager.getInstance().getUserDeviceID()) == valueOf.longValue()) {
                    String string2 = jSONObject3.getString(NotificationCompat.CATEGORY_STATUS);
                    if (!string2.isEmpty() && (string2.equalsIgnoreCase("ENABLED") || string2.equalsIgnoreCase("ENABLING") || string2.equalsIgnoreCase("LINKED"))) {
                        SessionManager.getInstance(this.f5630a).setAlexaAccountLinkingStatus(true);
                    }
                }
                if (SessionManager.getInstance(this.f5630a).getAlexaAccountLinkingStatus()) {
                    this.b.onAccountLinkingCompleted(true);
                } else {
                    this.b.onAccountLinkingCompleted(false);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
