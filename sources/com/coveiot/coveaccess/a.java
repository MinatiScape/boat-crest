package com.coveiot.coveaccess;

import android.content.Context;
import android.content.Intent;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.server.SRefreshTokenRequest;
import com.coveiot.coveaccess.model.server.SRefreshTokenResponse;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import java.io.IOException;
/* loaded from: classes8.dex */
public class a {
    public static a b;

    /* renamed from: a  reason: collision with root package name */
    public Context f6376a;

    public a(Context context) {
        this.f6376a = context;
    }

    public static a a(Context context) {
        if (b == null) {
            b = new a(context);
        }
        return b;
    }

    public String b() {
        SRefreshTokenResponse sRefreshTokenResponse;
        if (System.currentTimeMillis() - PreferenceManager.getInstance().getTokenRefreshTime().longValue() < PreferenceManager.getInstance().getTokenExpiryDuration().longValue()) {
            return PreferenceManager.getInstance().getAccessToken();
        }
        String refreshToken = PreferenceManager.getInstance().getRefreshToken();
        SRefreshTokenRequest sRefreshTokenRequest = new SRefreshTokenRequest();
        sRefreshTokenRequest.setGrantType("refresh_token");
        sRefreshTokenRequest.setRefreshToken(refreshToken);
        String str = null;
        try {
            sRefreshTokenResponse = CoveApi.getService().refreshAccessToken(CoveApi.getCustomHeaders(), sRefreshTokenRequest).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            sRefreshTokenResponse = null;
        }
        if (sRefreshTokenResponse != null && sRefreshTokenResponse.getStatus() != null && sRefreshTokenResponse.getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
            str = sRefreshTokenResponse.getData().getAccessToken();
            PreferenceManager.getInstance().saveAccessToken(sRefreshTokenResponse.getData().getAccessToken());
            String refreshToken2 = sRefreshTokenResponse.getData().getRefreshToken();
            if (refreshToken2 != null && refreshToken2.length() > 0) {
                PreferenceManager.getInstance().saveRefreshToken(refreshToken2);
            }
            PreferenceManager.getInstance().saveTokenExpiryDuration(sRefreshTokenResponse.getData().getExpiresIn());
            PreferenceManager.getInstance().saveTokenRefreshTime(System.currentTimeMillis());
        } else {
            PreferenceManager.getInstance().clearLoginDetails();
            this.f6376a.sendBroadcast(new Intent("session_expiry_brodcast"));
        }
        return str;
    }
}
