package com.google.android.libraries.places.internal;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzad {
    private final RequestQueue zza;

    public zzad(RequestQueue requestQueue) {
        this.zza = requestQueue;
    }

    public final <HttpPhotoResponseT extends zzam<Object, ? extends Object>> Task<HttpPhotoResponseT> zza(zzaj<Object, ?> zzajVar, final zzan<HttpPhotoResponseT> zzanVar) {
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
        zzah zzahVar = new zzah(this, zzd, new Response.Listener(zzanVar, taskCompletionSource2) { // from class: com.google.android.libraries.places.internal.zzag
            private final zzan zza;
            private final TaskCompletionSource zzb;

            {
                this.zza = zzanVar;
                this.zzb = taskCompletionSource2;
            }

            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                zzan zzanVar2 = this.zza;
                TaskCompletionSource taskCompletionSource3 = this.zzb;
                try {
                    zzanVar2.zza((Bitmap) obj);
                    taskCompletionSource3.trySetResult(zzanVar2.zza());
                } catch (Error | RuntimeException e) {
                    zzdk.zza(e);
                    throw e;
                }
            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, new Response.ErrorListener(taskCompletionSource2) { // from class: com.google.android.libraries.places.internal.zzaf
            private final TaskCompletionSource zza;

            {
                this.zza = taskCompletionSource2;
            }

            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                ApiException zza;
                TaskCompletionSource taskCompletionSource3 = this.zza;
                try {
                    NetworkResponse networkResponse = volleyError.networkResponse;
                    if (networkResponse != null) {
                        int i = networkResponse.statusCode;
                        if (i == 400) {
                            zza = new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "The provided parameters are invalid (did you include a max width or height?)."));
                        } else if (i == 403) {
                            zza = new ApiException(new Status((int) PlacesStatusCodes.REQUEST_DENIED, "The provided API key is invalid."));
                        }
                        taskCompletionSource3.trySetException(zza);
                    }
                    zza = zzv.zza(volleyError);
                    taskCompletionSource3.trySetException(zza);
                } catch (Error | RuntimeException e) {
                    zzdk.zza(e);
                    throw e;
                }
            }
        }, zzc);
        if (zzb != null) {
            zzb.onCanceledRequested(zzai.zza(zzahVar));
        }
        this.zza.add(zzahVar);
        return taskCompletionSource2.getTask();
    }
}
