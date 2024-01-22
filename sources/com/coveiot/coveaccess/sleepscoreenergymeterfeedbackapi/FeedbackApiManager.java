package com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.ApiException;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SPostRatingReq;
import com.coveiot.coveaccess.model.server.SPostRatingRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class FeedbackApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6720a;

        public a(CoveApiListener coveApiListener) {
            this.f6720a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6720a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                SaveFeedbackQuestionarieReponse saveFeedbackQuestionarieReponse = new SaveFeedbackQuestionarieReponse(response.code());
                saveFeedbackQuestionarieReponse.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveFeedbackQuestionarieReponse.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6720a.onSuccess(saveFeedbackQuestionarieReponse);
                return;
            }
            int code = response.code();
            this.f6720a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<GetFeedbackListResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6721a;

        public b(CoveApiListener coveApiListener) {
            this.f6721a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetFeedbackListResponse> call, Throwable th) {
            this.f6721a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetFeedbackListResponse> call, Response<GetFeedbackListResponse> response) {
            if (response.isSuccessful()) {
                this.f6721a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6721a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<GetFeedbackListResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6722a;

        public c(CoveApiListener coveApiListener) {
            this.f6722a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetFeedbackListResponse> call, Throwable th) {
            this.f6722a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetFeedbackListResponse> call, Response<GetFeedbackListResponse> response) {
            if (response.isSuccessful()) {
                this.f6722a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6722a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SPostRatingRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6723a;

        public d(CoveApiListener coveApiListener) {
            this.f6723a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPostRatingRes> call, Throwable th) {
            this.f6723a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPostRatingRes> call, Response<SPostRatingRes> response) {
            if (response.isSuccessful()) {
                this.f6723a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6723a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void SaveFeedbackData(HashMap<String, String> hashMap, SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest, CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel> coveApiListener) {
        b(saveFeedbackQuestionarieRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void a(SPostRatingReq sPostRatingReq, CoveApiListener<SPostRatingRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postUserRatingFeedback(map, sPostRatingReq).enqueue(new d(coveApiListener));
    }

    public static void b(SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest, CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().saveFeedbackDataToServer(map, saveFeedbackQuestionarieRequest).enqueue(new a(coveApiListener));
    }

    public static void c(String str, CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (!CoveUtil.isEmpty(str)) {
            CoveApi.getService().getFeedbackQuestionarieListByQuentionnaireID(map, str).enqueue(new c(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void d(String str, CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (!CoveUtil.isEmpty(str)) {
            CoveApi.getService().getFeedbackQuestionarieList(map, str).enqueue(new b(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void getFeedbackQuestionnaireList(String str, CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel> coveApiListener) {
        d(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFeedbackQuestionnaireListByQuestionnaireId(String str, CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel> coveApiListener) {
        c(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void postUserRatingAndFeedBack(SPostRatingReq sPostRatingReq, CoveApiListener<SPostRatingRes, CoveApiErrorModel> coveApiListener) {
        a(sPostRatingReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveFeedbackData(SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest, CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel> coveApiListener) {
        b(saveFeedbackQuestionarieRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void postUserRatingAndFeedBack(HashMap<String, String> hashMap, SPostRatingReq sPostRatingReq, CoveApiListener<SPostRatingRes, CoveApiErrorModel> coveApiListener) {
        a(sPostRatingReq, coveApiListener, CoveUtil.getRevisedHeaders(CoveApi.getCustomHeaders()));
    }

    public static void getFeedbackQuestionnaireList(HashMap<String, String> hashMap, String str, CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel> coveApiListener) {
        d(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFeedbackQuestionnaireListByQuestionnaireId(HashMap<String, String> hashMap, String str, CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel> coveApiListener) {
        c(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
