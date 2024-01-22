package com.clevertap.android.sdk.response;

import android.content.Context;
import android.util.Log;
import androidx.annotation.WorkerThread;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public abstract class CleverTapResponse {
    @WorkerThread
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        Log.i("CleverTapResponse", "Done processing response!");
    }
}
