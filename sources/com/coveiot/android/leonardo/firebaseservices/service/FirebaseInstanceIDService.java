package com.coveiot.android.leonardo.firebaseservices.service;

import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
/* loaded from: classes2.dex */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    public static final String h = FirebaseInstanceIDService.class.getName();

    public final void a(String str) {
        SessionManager.getInstance(this).saveRegistrationToken(str);
    }

    @Override // com.google.firebase.iid.FirebaseInstanceIdService
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        String str = h;
        LogHelper.d(str, "Refreshed token: " + token);
        a(token);
    }
}
