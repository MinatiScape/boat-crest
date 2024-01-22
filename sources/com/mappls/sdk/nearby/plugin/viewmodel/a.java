package com.mappls.sdk.nearby.plugin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.mappls.sdk.nearby.plugin.util.d;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;
/* loaded from: classes10.dex */
public final class a implements OnResponseCallback<NearbyAtlasResponse> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f13076a;

    public a(b bVar) {
        this.f13076a = bVar;
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onError(int i, @Nullable String str) {
        MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> c;
        String str2;
        if (i == 1) {
            c = this.f13076a.c();
            str2 = "No Internet Connection";
        } else if (i != 204) {
            c = this.f13076a.c();
            str2 = "Something went wrong";
        } else {
            c = this.f13076a.c();
            str2 = "No result found";
        }
        c.setValue(d.a.a(str2, null));
        Timber.e(str, new Object[0]);
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onSuccess(NearbyAtlasResponse nearbyAtlasResponse) {
        this.f13076a.c().setValue(d.a.a(nearbyAtlasResponse));
    }
}
