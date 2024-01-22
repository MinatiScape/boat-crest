package com.coveiot.coveaccess;

import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sports.SGetSportsUserPrefRes;
import com.coveiot.coveaccess.sports.SportsAuthTokenRequest;
import com.coveiot.coveaccess.sports.SportsTokenRes;
import com.coveiot.coveaccess.sports.SportsUserPrefRequest;
import com.coveiot.coveaccess.sports.SportsUserPrefResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveSports {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SportsTokenRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6322a;

        public a(CoveApiListener coveApiListener) {
            this.f6322a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SportsTokenRes> call, Throwable th) {
            this.f6322a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SportsTokenRes> call, Response<SportsTokenRes> response) {
            if (response.isSuccessful()) {
                this.f6322a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6322a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetSportsUserPrefRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6323a;

        public b(CoveApiListener coveApiListener) {
            this.f6323a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetSportsUserPrefRes> call, Throwable th) {
            this.f6323a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetSportsUserPrefRes> call, Response<SGetSportsUserPrefRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                SGetSportsUserPrefRes sGetSportsUserPrefRes = new SGetSportsUserPrefRes(response.code());
                sGetSportsUserPrefRes.data = response.body().data;
                this.f6323a.onSuccess(sGetSportsUserPrefRes);
                return;
            }
            int code = response.code();
            this.f6323a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SportsUserPrefResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6324a;

        public c(CoveApiListener coveApiListener) {
            this.f6324a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SportsUserPrefResponse> call, Throwable th) {
            this.f6324a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SportsUserPrefResponse> call, Response<SportsUserPrefResponse> response) {
            if (response.isSuccessful()) {
                this.f6324a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6324a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SportsAuthTokenRequest sportsAuthTokenRequest, CoveApiListener<SportsTokenRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSportAuthAccessToken(map, sportsAuthTokenRequest).enqueue(new a(coveApiListener));
    }

    public static void b(SportsUserPrefRequest sportsUserPrefRequest, CoveApiListener<SGetSportsUserPrefRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postSportsUserPreference(map, sportsUserPrefRequest).enqueue(new b(coveApiListener));
    }

    public static void c(Integer num, CoveApiListener<SportsUserPrefResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSportsUserPref(map, num).enqueue(new c(coveApiListener));
    }

    public static void getSportsToken(SportsAuthTokenRequest sportsAuthTokenRequest, CoveApiListener<SportsTokenRes, CoveApiErrorModel> coveApiListener) {
        a(sportsAuthTokenRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getSportsUserPref(Integer num, CoveApiListener<SportsUserPrefResponse, CoveApiErrorModel> coveApiListener) {
        c(num, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void postSportsUserPref(SportsUserPrefRequest sportsUserPrefRequest, CoveApiListener<SGetSportsUserPrefRes, CoveApiErrorModel> coveApiListener) {
        b(sportsUserPrefRequest, coveApiListener, CoveApi.getCustomHeaders());
    }
}
