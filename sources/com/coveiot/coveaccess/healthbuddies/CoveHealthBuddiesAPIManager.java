package com.coveiot.coveaccess.healthbuddies;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.ApiException;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetHealthBuddiesResponse;
import com.coveiot.coveaccess.model.server.SHealthBuddyResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveHealthBuddiesAPIManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SGetHealthBuddiesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6597a;

        public a(CoveApiListener coveApiListener) {
            this.f6597a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetHealthBuddiesResponse> call, Throwable th) {
            this.f6597a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetHealthBuddiesResponse> call, Response<SGetHealthBuddiesResponse> response) {
            HealthBuddyResponse healthBuddyResponse;
            if (response != null) {
                if (response.isSuccessful() && response.body() != null && response.body().status != null && response.body().status.equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    if (response.body().data != null) {
                        healthBuddyResponse = new HealthBuddyResponse(response.code(), response.body().status, response.body().message, response.body().data.healthBuddies);
                    } else {
                        healthBuddyResponse = new HealthBuddyResponse(response.code(), response.body().status, response.body().message, null);
                    }
                    this.f6597a.onSuccess(healthBuddyResponse);
                    return;
                }
                int code = response.code();
                this.f6597a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6597a.onError(CoveUtil.buildErrorObject(new ApiException(ErrorConstants.API_ERR_GENERIC)));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetHealthBuddiesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6598a;

        public b(CoveApiListener coveApiListener) {
            this.f6598a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetHealthBuddiesResponse> call, Throwable th) {
            this.f6598a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetHealthBuddiesResponse> call, Response<SGetHealthBuddiesResponse> response) {
            if (response != null) {
                if (response.isSuccessful() && response.body() != null && response.body().status != null && response.body().status.equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    if (response.body().data != null && response.body().data.healthBuddies != null) {
                        for (HealthBuddy healthBuddy : response.body().data.healthBuddies) {
                            String str = healthBuddy.requestStatus;
                            if (str != null) {
                                if (str.equals("PENDING")) {
                                    arrayList.add(healthBuddy);
                                } else if (healthBuddy.requestStatus.equals(RequestStatus.EXPIRED)) {
                                    arrayList2.add(healthBuddy);
                                } else if (healthBuddy.requestStatus.equals(RequestStatus.REJECTED)) {
                                    arrayList3.add(healthBuddy);
                                } else if (healthBuddy.requestStatus.equals(RequestStatus.CANCELLED)) {
                                    arrayList4.add(healthBuddy);
                                }
                            }
                        }
                    }
                    this.f6598a.onSuccess(new HealthBuddyLogResponse(response.code(), response.body().status, response.body().message, arrayList, arrayList2, arrayList3, arrayList4));
                    return;
                }
                int code = response.code();
                this.f6598a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6598a.onError(CoveUtil.buildErrorObject(new ApiException(ErrorConstants.API_ERR_GENERIC)));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SGetHealthBuddiesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6599a;

        public c(CoveApiListener coveApiListener) {
            this.f6599a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetHealthBuddiesResponse> call, Throwable th) {
            this.f6599a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetHealthBuddiesResponse> call, Response<SGetHealthBuddiesResponse> response) {
            if (response != null) {
                if (response.isSuccessful() && response.body() != null && response.body().status != null && response.body().status.equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK) && response.body().data != null && response.body().data.healthBuddies != null) {
                    this.f6599a.onSuccess(new HealthBuddyResponse(response.code(), response.body().status, response.body().message, response.body().data.healthBuddies));
                    return;
                }
                int code = response.code();
                this.f6599a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6599a.onError(CoveUtil.buildErrorObject(new ApiException(ErrorConstants.API_ERR_GENERIC)));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SHealthBuddyResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6600a;

        public d(CoveApiListener coveApiListener) {
            this.f6600a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SHealthBuddyResponse> call, Throwable th) {
            this.f6600a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SHealthBuddyResponse> call, Response<SHealthBuddyResponse> response) {
            if (response != null) {
                if (response.isSuccessful() && response.body() != null && response.body().status != null && response.body().status.equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    ArrayList arrayList = new ArrayList();
                    if (response.body().healthBuddy != null) {
                        arrayList.add(response.body().healthBuddy);
                    }
                    this.f6600a.onSuccess(new HealthBuddyResponse(response.code(), response.body().status, response.body().message, arrayList));
                    return;
                }
                int code = response.code();
                this.f6600a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6600a.onError(CoveUtil.buildErrorObject(new ApiException(ErrorConstants.API_ERR_GENERIC)));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<SHealthBuddyResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6601a;

        public e(CoveApiListener coveApiListener) {
            this.f6601a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SHealthBuddyResponse> call, Throwable th) {
            this.f6601a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SHealthBuddyResponse> call, Response<SHealthBuddyResponse> response) {
            if (response != null) {
                if (response.isSuccessful() && response.body() != null && response.body().status != null && response.body().status.equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    ArrayList arrayList = new ArrayList();
                    if (response.body().healthBuddy != null) {
                        arrayList.add(response.body().healthBuddy);
                    }
                    this.f6601a.onSuccess(new HealthBuddyResponse(response.code(), response.body().status, response.body().message, arrayList));
                    return;
                }
                int code = response.code();
                this.f6601a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6601a.onError(CoveUtil.buildErrorObject(new ApiException(ErrorConstants.API_ERR_GENERIC)));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<SHealthBuddyResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6602a;

        public f(CoveApiListener coveApiListener) {
            this.f6602a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SHealthBuddyResponse> call, Throwable th) {
            this.f6602a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SHealthBuddyResponse> call, Response<SHealthBuddyResponse> response) {
            if (response != null) {
                if (response.isSuccessful() && response.body() != null && response.body().status != null && response.body().status.equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    ArrayList arrayList = new ArrayList();
                    if (response.body().healthBuddy != null) {
                        arrayList.add(response.body().healthBuddy);
                    }
                    this.f6602a.onSuccess(new HealthBuddyResponse(response.code(), response.body().status, response.body().message, arrayList));
                    return;
                }
                int code = response.code();
                this.f6602a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6602a.onError(CoveUtil.buildErrorObject(new ApiException(ErrorConstants.API_ERR_GENERIC)));
        }
    }

    public static void a(@NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().cancelHealthBuddyRequest(map, num.intValue()).enqueue(new d(coveApiListener));
    }

    public static void b(@NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getHealthBuddies(map).enqueue(new c(coveApiListener));
    }

    public static void c(@NonNull CoveApiListener<HealthBuddyLogResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getHealthBuddiesRequestLog(map).enqueue(new b(coveApiListener));
    }

    public static void cancelHealthBuddyRequest(@NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        a(num, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void d(@NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().reinviteHealthBuddy(map, num.intValue()).enqueue(new f(coveApiListener));
    }

    public static void e(@NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().removeHealthBuddyRequest(map, num.intValue()).enqueue(new e(coveApiListener));
    }

    public static void f(@NonNull HealthBuddyRequest healthBuddyRequest, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (healthBuddyRequest != null && healthBuddyRequest.getBuddies() != null) {
            CoveApi.getService().sendHealthBuddyRequest(map, healthBuddyRequest).enqueue(new a(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void getHealthBuddies(@NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getHealthBuddiesRequestLog(@NonNull CoveApiListener<HealthBuddyLogResponse, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void reInviteHealthBuddy(@NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        d(num, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void removeHealthBuddyRequest(@NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        e(num, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendHealthBuddyRequest(@NonNull HealthBuddyRequest healthBuddyRequest, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        f(healthBuddyRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void cancelHealthBuddyRequest(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        a(num, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getHealthBuddies(HashMap<String, String> hashMap, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getHealthBuddiesRequestLog(HashMap<String, String> hashMap, @NonNull CoveApiListener<HealthBuddyLogResponse, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void reInviteHealthBuddy(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        d(num, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void removeHealthBuddyRequest(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        e(num, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendHealthBuddyRequest(HashMap<String, String> hashMap, @NonNull HealthBuddyRequest healthBuddyRequest, @NonNull CoveApiListener<HealthBuddyResponse, CoveApiErrorModel> coveApiListener) {
        f(healthBuddyRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
