package com.coveiot.coveaccess;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalResponse;
import com.coveiot.coveaccess.fitness.model.DeleteFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.DeleteFitnessGoalResponse;
import com.coveiot.coveaccess.fitness.model.GetFitnessGoalResponse;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SCreateFitnessGoalResponse;
import com.coveiot.coveaccess.model.server.SDeleteFitnessGoalResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessGoalResponse;
import com.coveiot.coveaccess.model.server.SUpdateFitnessGoalResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveFitness {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SCreateFitnessGoalResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6261a;

        public a(CoveApiListener coveApiListener) {
            this.f6261a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SCreateFitnessGoalResponse> call, Throwable th) {
            this.f6261a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SCreateFitnessGoalResponse> call, Response<SCreateFitnessGoalResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                this.f6261a.onSuccess(new CreateFitnessGoalResponse(response.code(), response.body().data));
                return;
            }
            int code = response.code();
            this.f6261a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SUpdateFitnessGoalResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6262a;

        public b(CoveApiListener coveApiListener) {
            this.f6262a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SUpdateFitnessGoalResponse> call, Throwable th) {
            this.f6262a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SUpdateFitnessGoalResponse> call, Response<SUpdateFitnessGoalResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                this.f6262a.onSuccess(new UpdateFitnessGoalResponse(response.code(), response.body().data));
                return;
            }
            int code = response.code();
            this.f6262a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SGetFitnessGoalResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6263a;

        public c(CoveApiListener coveApiListener) {
            this.f6263a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetFitnessGoalResponse> call, Throwable th) {
            this.f6263a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetFitnessGoalResponse> call, Response<SGetFitnessGoalResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                this.f6263a.onSuccess(new GetFitnessGoalResponse(response.code(), response.body().data));
                return;
            }
            int code = response.code();
            this.f6263a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SDeleteFitnessGoalResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6264a;

        public d(CoveApiListener coveApiListener) {
            this.f6264a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SDeleteFitnessGoalResponse> call, Throwable th) {
            this.f6264a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SDeleteFitnessGoalResponse> call, Response<SDeleteFitnessGoalResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().message != null) {
                this.f6264a.onSuccess(new DeleteFitnessGoalResponse(response.message(), response.isSuccessful()));
                return;
            }
            int code = response.code();
            this.f6264a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(@NonNull CoveApiListener<GetFitnessGoalResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessGoal(map).enqueue(new c(coveApiListener));
    }

    public static void b(@NonNull CreateFitnessGoalRequest createFitnessGoalRequest, @NonNull CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().createFitnessGoal(map, createFitnessGoalRequest).enqueue(new a(coveApiListener));
    }

    public static void c(@NonNull DeleteFitnessGoalRequest deleteFitnessGoalRequest, @NonNull CoveApiListener<DeleteFitnessGoalResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().deleteFitnessGoal(map, deleteFitnessGoalRequest.getGoalId()).enqueue(new d(coveApiListener));
    }

    public static void createFitnessGoal(@NonNull CreateFitnessGoalRequest createFitnessGoalRequest, @NonNull CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        b(createFitnessGoalRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void d(@NonNull Integer num, @NonNull UpdateFitnessGoalRequest updateFitnessGoalRequest, @NonNull CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().updateFitnessGoal(map, num.intValue(), updateFitnessGoalRequest).enqueue(new b(coveApiListener));
    }

    public static void deleteFitnessGoal(@NonNull DeleteFitnessGoalRequest deleteFitnessGoalRequest, @NonNull CoveApiListener<DeleteFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        c(deleteFitnessGoalRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessGoal(@NonNull CoveApiListener<GetFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void updateFitnessGoal(@NonNull Integer num, @NonNull UpdateFitnessGoalRequest updateFitnessGoalRequest, @NonNull CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        d(num, updateFitnessGoalRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void createFitnessGoal(HashMap<String, String> hashMap, @NonNull CreateFitnessGoalRequest createFitnessGoalRequest, @NonNull CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        b(createFitnessGoalRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void deleteFitnessGoal(HashMap<String, String> hashMap, @NonNull DeleteFitnessGoalRequest deleteFitnessGoalRequest, @NonNull CoveApiListener<DeleteFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        c(deleteFitnessGoalRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessGoal(HashMap<String, String> hashMap, @NonNull CoveApiListener<GetFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateFitnessGoal(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull UpdateFitnessGoalRequest updateFitnessGoalRequest, @NonNull CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel> coveApiListener) {
        d(num, updateFitnessGoalRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
