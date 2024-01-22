package com.google.android.libraries.places.internal;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class zzx {
    private final RequestQueue zza;
    private final zzal zzb;

    public zzx(RequestQueue requestQueue, zzal zzalVar) {
        this.zza = requestQueue;
        this.zzb = zzalVar;
    }

    public final <HttpJsonResponseT extends zzam<Object, ? extends Object>> Task<HttpJsonResponseT> zza(zzaj<Object, ? extends zzdc> zzajVar, final Class<HttpJsonResponseT> cls) {
        TaskCompletionSource taskCompletionSource;
        String zzd = zzajVar.zzd();
        Map<String, String> zzc = zzajVar.zzc();
        CancellationToken zzb = zzajVar.zzb();
        if (zzb != null) {
            taskCompletionSource = new TaskCompletionSource(zzb);
        } else {
            taskCompletionSource = new TaskCompletionSource();
        }
        final TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        zzab zzabVar = new zzab(this, 0, zzd, null, new Response.Listener(this, cls, taskCompletionSource2) { // from class: com.google.android.libraries.places.internal.zzaa
            private final zzx zza;
            private final Class zzb;
            private final TaskCompletionSource zzc;

            {
                this.zza = this;
                this.zzb = cls;
                this.zzc = taskCompletionSource2;
            }

            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                this.zza.zza(this.zzb, this.zzc, (JSONObject) obj);
            }
        }, new Response.ErrorListener(taskCompletionSource2) { // from class: com.google.android.libraries.places.internal.zzz
            private final TaskCompletionSource zza;

            {
                this.zza = taskCompletionSource2;
            }

            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                try {
                    this.zza.trySetException(zzv.zza(volleyError));
                } catch (Error | RuntimeException e) {
                    zzdk.zza(e);
                    throw e;
                }
            }
        }, zzc);
        if (zzb != null) {
            zzb.onCanceledRequested(zzac.zza(zzabVar));
        }
        this.zza.add(zzabVar);
        return taskCompletionSource2.getTask();
    }

    public final /* synthetic */ void zza(Class cls, TaskCompletionSource taskCompletionSource, JSONObject jSONObject) {
        try {
            try {
                taskCompletionSource.trySetResult((zzam) this.zzb.zza(jSONObject.toString(), cls));
            } catch (zzao e) {
                taskCompletionSource.trySetException(new ApiException(new Status(8, e.getMessage())));
            }
        } catch (Error | RuntimeException e2) {
            zzdk.zza(e2);
            throw e2;
        }
    }
}
