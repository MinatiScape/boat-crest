package com.clevertap.android.sdk.pushnotification.amp;

import android.app.IntentService;
import android.content.Intent;
import com.clevertap.android.sdk.CleverTapAPI;
/* loaded from: classes2.dex */
public class CTBackgroundIntentService extends IntentService {
    public static final String MAIN_ACTION = "com.clevertap.BG_EVENT";

    public CTBackgroundIntentService() {
        super("CTBackgroundIntentService");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        CleverTapAPI.runBackgroundIntentService(getApplicationContext());
    }
}
