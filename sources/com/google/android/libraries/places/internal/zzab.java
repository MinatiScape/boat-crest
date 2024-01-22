package com.google.android.libraries.places.internal;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes10.dex */
final class zzab extends JsonObjectRequest {
    private final /* synthetic */ Map zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzab(zzx zzxVar, int i, String str, JSONObject jSONObject, Response.Listener listener, Response.ErrorListener errorListener, Map map) {
        super(0, str, null, listener, errorListener);
        this.zza = map;
    }

    @Override // com.android.volley.Request
    public final Map<String, String> getHeaders() {
        return this.zza;
    }
}
