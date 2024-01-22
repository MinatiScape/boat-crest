package com.coveiot.coveaccess.edgegateway;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.edgegateway.model.EGLoginResponse;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateDeviceHeartBeatReq;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateDeviceHeartBeatRes;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateHeartBeatRequest;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateHeartBeatResponse;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateInfoRequest;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateInfoResponse;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateSessionRequest;
import com.coveiot.coveaccess.edgegateway.model.EGUpdateSessionResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.GenericResponse;
import com.coveiot.coveaccess.model.server.SEGLoginRequest;
import com.coveiot.coveaccess.model.server.SEGLoginResponse;
import com.coveiot.coveaccess.model.server.SEgGetUserDetails;
import com.coveiot.coveaccess.model.server.SEgLogoutRequest;
import com.coveiot.coveaccess.model.server.SEgLogoutResponse;
import com.coveiot.coveaccess.model.server.SGetAuthUserInfo;
import com.coveiot.coveaccess.model.server.SGetEGInfo;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class EGApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SEGLoginResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6497a;

        public a(CoveApiListener coveApiListener) {
            this.f6497a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SEGLoginResponse> call, Throwable th) {
            this.f6497a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SEGLoginResponse> call, Response<SEGLoginResponse> response) {
            if (!response.isSuccessful() || response.body() == null) {
                return;
            }
            EGLoginResponse eGLoginResponse = new EGLoginResponse(response.code());
            eGLoginResponse.setMessage(response.body().getMessage());
            if (response.body().getData() != null && response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                PreferenceManager.getInstance().saveAccessToken(response.body().getData().getAccessToken());
                PreferenceManager.getInstance().saveRefreshToken(response.body().getData().getRefreshToken());
                PreferenceManager.getInstance().saveTokenExpiryDuration(Long.valueOf(response.body().getData().getExpiresIn()));
                PreferenceManager.getInstance().saveTokenRefreshTime(System.currentTimeMillis());
                this.f6497a.onSuccess(eGLoginResponse);
                return;
            }
            int code = response.code();
            this.f6497a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SEgLogoutResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6498a;

        public b(CoveApiListener coveApiListener) {
            this.f6498a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SEgLogoutResponse> call, Throwable th) {
            this.f6498a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SEgLogoutResponse> call, Response<SEgLogoutResponse> response) {
            if (response.isSuccessful()) {
                this.f6498a.onSuccess(response.body());
                PreferenceManager.getInstance().clearLoginDetails();
                return;
            }
            int code = response.code();
            this.f6498a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SGetEGInfo> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6499a;

        public c(CoveApiListener coveApiListener) {
            this.f6499a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetEGInfo> call, Throwable th) {
            this.f6499a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetEGInfo> call, Response<SGetEGInfo> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6499a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6499a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<GenericResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6500a;

        public d(CoveApiListener coveApiListener) {
            this.f6500a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GenericResponse> call, Throwable th) {
            this.f6500a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                EGUpdateInfoResponse eGUpdateInfoResponse = new EGUpdateInfoResponse(response.code());
                eGUpdateInfoResponse.setMessage(response.body().getMessage());
                eGUpdateInfoResponse.setStatus(response.body().getStatus());
                this.f6500a.onSuccess(eGUpdateInfoResponse);
                return;
            }
            int code = response.code();
            this.f6500a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<GenericResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6501a;

        public e(CoveApiListener coveApiListener) {
            this.f6501a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GenericResponse> call, Throwable th) {
            this.f6501a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                EGUpdateSessionResponse eGUpdateSessionResponse = new EGUpdateSessionResponse(response.code());
                eGUpdateSessionResponse.setMessage(response.body().getMessage());
                eGUpdateSessionResponse.setStatus(response.body().getStatus());
                this.f6501a.onSuccess(eGUpdateSessionResponse);
                return;
            }
            int code = response.code();
            this.f6501a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<GenericResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6502a;

        public f(CoveApiListener coveApiListener) {
            this.f6502a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GenericResponse> call, Throwable th) {
            this.f6502a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                EGUpdateHeartBeatResponse eGUpdateHeartBeatResponse = new EGUpdateHeartBeatResponse(response.code());
                eGUpdateHeartBeatResponse.setMessage(response.body().getMessage());
                eGUpdateHeartBeatResponse.setStatus(response.body().getStatus());
                this.f6502a.onSuccess(eGUpdateHeartBeatResponse);
                return;
            }
            int code = response.code();
            this.f6502a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<GenericResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6503a;

        public g(CoveApiListener coveApiListener) {
            this.f6503a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GenericResponse> call, Throwable th) {
            this.f6503a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                EGUpdateDeviceHeartBeatRes eGUpdateDeviceHeartBeatRes = new EGUpdateDeviceHeartBeatRes(response.code());
                eGUpdateDeviceHeartBeatRes.setMessage(response.body().getMessage());
                eGUpdateDeviceHeartBeatRes.setStatus(response.body().getStatus());
                this.f6503a.onSuccess(eGUpdateDeviceHeartBeatRes);
                return;
            }
            int code = response.code();
            this.f6503a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<SEgGetUserDetails> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6504a;

        public h(CoveApiListener coveApiListener) {
            this.f6504a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SEgGetUserDetails> call, Throwable th) {
            this.f6504a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SEgGetUserDetails> call, Response<SEgGetUserDetails> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6504a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6504a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<SGetAuthUserInfo> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6505a;

        public i(CoveApiListener coveApiListener) {
            this.f6505a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetAuthUserInfo> call, Throwable th) {
            this.f6505a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetAuthUserInfo> call, Response<SGetAuthUserInfo> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6505a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6505a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<SGetAuthUserInfo, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getAuthUserInfo(hashMap).enqueue(new i(coveApiListener));
    }

    public static void b(EGUpdateDeviceHeartBeatReq eGUpdateDeviceHeartBeatReq, CoveApiListener<EGUpdateDeviceHeartBeatRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().postEgDeviceHeartBeat(hashMap, eGUpdateDeviceHeartBeatReq.getUpdateEgDeviceHeartbeat()).enqueue(new g(coveApiListener));
    }

    public static void c(EGUpdateHeartBeatRequest eGUpdateHeartBeatRequest, CoveApiListener<EGUpdateHeartBeatResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().postEgHeartBeatInfo(hashMap, eGUpdateHeartBeatRequest.getUpdateEgHeartBeat()).enqueue(new f(coveApiListener));
    }

    public static void d(EGUpdateInfoRequest eGUpdateInfoRequest, CoveApiListener<EGUpdateInfoResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().updateEgInfo(hashMap, eGUpdateInfoRequest.getUpdateEgInfo()).enqueue(new d(coveApiListener));
    }

    public static void e(EGUpdateSessionRequest eGUpdateSessionRequest, CoveApiListener<EGUpdateSessionResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().updateEgSession(hashMap, eGUpdateSessionRequest.getSUpdateEgSession()).enqueue(new e(coveApiListener));
    }

    public static void f(SEGLoginRequest sEGLoginRequest, HashMap<String, String> hashMap, CoveApiListener<EGLoginResponse, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().login(hashMap, sEGLoginRequest).enqueue(new a(coveApiListener));
    }

    public static void g(CoveApiListener<SGetEGInfo, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getEGInfo(hashMap).enqueue(new c(coveApiListener));
    }

    public static void getAuthUserInfo(CoveApiListener<SGetAuthUserInfo, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getEGInfo(CoveApiListener<SGetEGInfo, CoveApiErrorModel> coveApiListener) {
        g(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getEgUserDetails(CoveApiListener<SEgGetUserDetails, CoveApiErrorModel> coveApiListener) {
        h(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void h(CoveApiListener<SEgGetUserDetails, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getEgUserDetails(hashMap).enqueue(new h(coveApiListener));
    }

    public static void i(CoveApiListener<SEgLogoutResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        SEgLogoutRequest sEgLogoutRequest = new SEgLogoutRequest();
        sEgLogoutRequest.setToken(PreferenceManager.getInstance().getRefreshToken());
        CoveApi.getService().logout(hashMap, sEgLogoutRequest).enqueue(new b(coveApiListener));
    }

    public static void login(SEGLoginRequest sEGLoginRequest, CoveApiListener<EGLoginResponse, CoveApiErrorModel> coveApiListener) {
        f(sEGLoginRequest, CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void logout(CoveApiListener<SEgLogoutResponse, CoveApiErrorModel> coveApiListener) {
        i(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void updateEgDeviceHeartBeat(EGUpdateDeviceHeartBeatReq eGUpdateDeviceHeartBeatReq, CoveApiListener<EGUpdateDeviceHeartBeatRes, CoveApiErrorModel> coveApiListener) {
        b(eGUpdateDeviceHeartBeatReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void updateEgHeartBeat(EGUpdateHeartBeatRequest eGUpdateHeartBeatRequest, CoveApiListener<EGUpdateHeartBeatResponse, CoveApiErrorModel> coveApiListener) {
        c(eGUpdateHeartBeatRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void updateEgInfo(EGUpdateInfoRequest eGUpdateInfoRequest, CoveApiListener<EGUpdateInfoResponse, CoveApiErrorModel> coveApiListener) {
        d(eGUpdateInfoRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void updateEgSession(EGUpdateSessionRequest eGUpdateSessionRequest, CoveApiListener<EGUpdateSessionResponse, CoveApiErrorModel> coveApiListener) {
        e(eGUpdateSessionRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getAuthUserInfo(HashMap<String, String> hashMap, CoveApiListener<SGetAuthUserInfo, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getEGInfo(HashMap<String, String> hashMap, CoveApiListener<SGetEGInfo, CoveApiErrorModel> coveApiListener) {
        g(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getEgUserDetails(HashMap<String, String> hashMap, CoveApiListener<SEgGetUserDetails, CoveApiErrorModel> coveApiListener) {
        h(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void login(HashMap<String, String> hashMap, SEGLoginRequest sEGLoginRequest, CoveApiListener<EGLoginResponse, CoveApiErrorModel> coveApiListener) {
        f(sEGLoginRequest, CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void logout(HashMap<String, String> hashMap, CoveApiListener<SEgLogoutResponse, CoveApiErrorModel> coveApiListener) {
        i(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateEgDeviceHeartBeat(HashMap<String, String> hashMap, EGUpdateDeviceHeartBeatReq eGUpdateDeviceHeartBeatReq, CoveApiListener<EGUpdateDeviceHeartBeatRes, CoveApiErrorModel> coveApiListener) {
        b(eGUpdateDeviceHeartBeatReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateEgHeartBeat(HashMap<String, String> hashMap, EGUpdateHeartBeatRequest eGUpdateHeartBeatRequest, CoveApiListener<EGUpdateHeartBeatResponse, CoveApiErrorModel> coveApiListener) {
        c(eGUpdateHeartBeatRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateEgInfo(HashMap<String, String> hashMap, EGUpdateInfoRequest eGUpdateInfoRequest, CoveApiListener<EGUpdateInfoResponse, CoveApiErrorModel> coveApiListener) {
        d(eGUpdateInfoRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateEgSession(HashMap<String, String> hashMap, EGUpdateSessionRequest eGUpdateSessionRequest, CoveApiListener<EGUpdateSessionResponse, CoveApiErrorModel> coveApiListener) {
        e(eGUpdateSessionRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
