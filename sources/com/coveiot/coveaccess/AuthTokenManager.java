package com.coveiot.coveaccess;

import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.GetAuthSessionTokenReq;
import com.coveiot.coveaccess.model.server.GetAuthSessionTokenRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class AuthTokenManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<GetAuthSessionTokenRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6253a;

        public a(CoveApiListener coveApiListener) {
            this.f6253a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetAuthSessionTokenRes> call, Throwable th) {
            this.f6253a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetAuthSessionTokenRes> call, Response<GetAuthSessionTokenRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6253a.onSuccess(response.body());
            } else {
                this.f6253a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    public static void a(GetAuthSessionTokenReq getAuthSessionTokenReq, CoveApiListener<GetAuthSessionTokenRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getAuthSessionToken(hashMap, getAuthSessionTokenReq).enqueue(new a(coveApiListener));
    }

    public static void getAuthSessionToken(GetAuthSessionTokenReq getAuthSessionTokenReq, CoveApiListener<GetAuthSessionTokenRes, CoveApiErrorModel> coveApiListener) {
        a(getAuthSessionTokenReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getAuthSessionToken(GetAuthSessionTokenReq getAuthSessionTokenReq, HashMap<String, String> hashMap, CoveApiListener<GetAuthSessionTokenRes, CoveApiErrorModel> coveApiListener) {
        a(getAuthSessionTokenReq, coveApiListener, hashMap);
    }
}
