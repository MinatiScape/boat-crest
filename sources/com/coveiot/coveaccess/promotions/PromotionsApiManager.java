package com.coveiot.coveaccess.promotions;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SPromotionalOffers;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class PromotionsApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SPromotionalOffers> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6683a;

        public a(CoveApiListener coveApiListener) {
            this.f6683a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPromotionalOffers> call, Throwable th) {
            this.f6683a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPromotionalOffers> call, Response<SPromotionalOffers> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6683a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6683a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<SPromotionalOffers, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getPromotionalOffers(hashMap).enqueue(new a(coveApiListener));
    }

    public static void getPromotionsList(CoveApiListener<SPromotionalOffers, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getPromotionsList(CoveApiListener<SPromotionalOffers, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
