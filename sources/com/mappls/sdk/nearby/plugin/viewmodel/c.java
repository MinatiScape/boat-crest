package com.mappls.sdk.nearby.plugin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.mappls.sdk.nearby.plugin.util.d;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;
/* loaded from: classes10.dex */
public final class c implements OnResponseCallback<NearbyAtlasResponse> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f13078a;

    public c(d dVar) {
        this.f13078a = dVar;
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onError(int i, @Nullable String str) {
        MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> d;
        String str2;
        if (i == 1) {
            d = this.f13078a.d();
            str2 = "No Internet Connection";
        } else if (i != 204) {
            d = this.f13078a.d();
            str2 = "Something went wrong";
        } else {
            d = this.f13078a.d();
            str2 = "No result found";
        }
        d.setValue(d.a.a(str2, null));
        Timber.e(str, new Object[0]);
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onSuccess(NearbyAtlasResponse nearbyAtlasResponse) {
        this.f13078a.d().setValue(d.a.a(nearbyAtlasResponse));
    }
}
