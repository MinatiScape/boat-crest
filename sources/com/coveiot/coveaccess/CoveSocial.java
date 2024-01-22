package com.coveiot.coveaccess;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.appreferal.AppReferralModelRes;
import com.coveiot.coveaccess.appreferal.PostAppReferrerDataRes;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequest;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequestResponse;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.fitnessbuddies.FitnessBuddiesAction;
import com.coveiot.coveaccess.fitnessbuddies.model.CancelBuddyRequestResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesGoalSpecificResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesGoalsResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesMessagesResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddyRequestsAndBuddiesResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.HandleBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendFitnessBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendFitnessBuddyRequestResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.SendReactionRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.UnfriendBuddyResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyDetailsModel;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyListModel;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.fitnessbuddies.model.lookup.GetBuddyLookUpResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.lookup.SendFitnessBuddyLookUpRequest;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.SAppReferralModel;
import com.coveiot.coveaccess.model.server.SDeleteBuddiesMessageResponse;
import com.coveiot.coveaccess.model.server.SFitnessBuddiesInviteResponse;
import com.coveiot.coveaccess.model.server.SFitnessBuddiesMessagesResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddiesGoalSpecificResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddiesGoalsResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddiesResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddyRequestsAndBuddiesResponse;
import com.coveiot.coveaccess.model.server.SPostAppRefererDataReq;
import com.coveiot.coveaccess.model.server.SPostAppRefererDataRes;
import com.coveiot.coveaccess.model.server.WatchFaceRequest;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveSocial {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SFitnessBuddiesMessagesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6303a;

        public a(CoveApiListener coveApiListener) {
            this.f6303a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SFitnessBuddiesMessagesResponse> call, Throwable th) {
            this.f6303a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SFitnessBuddiesMessagesResponse> call, Response<SFitnessBuddiesMessagesResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.items != null) {
                this.f6303a.onSuccess(new GetFitnessBuddiesMessagesResponse(response.code(), response.body().data.items));
                return;
            }
            int code = response.code();
            this.f6303a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SAppReferralModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6304a;

        public b(CoveApiListener coveApiListener) {
            this.f6304a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SAppReferralModel> call, Throwable th) {
            this.f6304a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SAppReferralModel> call, Response<SAppReferralModel> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.code() == 200 && response.body() != null) {
                    SAppReferralModel body = response.body();
                    AppReferralModelRes appReferralModelRes = new AppReferralModelRes();
                    if (body.getData() != null) {
                        ArrayList arrayList = new ArrayList();
                        for (SAppReferralModel.DataBean.ReferralTemplatesBean referralTemplatesBean : body.getData().getReferralTemplates()) {
                            AppReferralModelRes.DataBean.ReferralTemplatesBean referralTemplatesBean2 = new AppReferralModelRes.DataBean.ReferralTemplatesBean();
                            referralTemplatesBean2.setInviteImage(referralTemplatesBean.getInviteImage());
                            referralTemplatesBean2.setInviteLink(referralTemplatesBean.getInviteLink());
                            referralTemplatesBean2.setInviteText(referralTemplatesBean.getInviteText());
                            referralTemplatesBean2.setInviteType(referralTemplatesBean.getInviteType());
                            arrayList.add(referralTemplatesBean2);
                        }
                        AppReferralModelRes.DataBean dataBean = new AppReferralModelRes.DataBean();
                        dataBean.setReferralTemplates(arrayList);
                        appReferralModelRes.setData(dataBean);
                        this.f6304a.onSuccess(appReferralModelRes);
                        return;
                    }
                    this.f6304a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                    return;
                }
                this.f6304a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6304a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SPostAppRefererDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6305a;

        public c(CoveApiListener coveApiListener) {
            this.f6305a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPostAppRefererDataRes> call, Throwable th) {
            this.f6305a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPostAppRefererDataRes> call, Response<SPostAppRefererDataRes> response) {
            if (response.isSuccessful()) {
                this.f6305a.onSuccess(new PostAppReferrerDataRes(response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SDeleteBuddiesMessageResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6306a;

        public d(CoveApiListener coveApiListener) {
            this.f6306a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SDeleteBuddiesMessageResponse> call, Throwable th) {
            this.f6306a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SDeleteBuddiesMessageResponse> call, Response<SDeleteBuddiesMessageResponse> response) {
            if (response.isSuccessful()) {
                this.f6306a.onSuccess(response.body());
            } else {
                this.f6306a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<CoinsDataRequestResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6307a;

        public e(CoveApiListener coveApiListener) {
            this.f6307a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CoinsDataRequestResponse> call, Throwable th) {
            this.f6307a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CoinsDataRequestResponse> call, Response<CoinsDataRequestResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                CoinsDataRequestResponse coinsDataRequestResponse = new CoinsDataRequestResponse(response.code());
                coinsDataRequestResponse.data = response.body().data;
                coinsDataRequestResponse.message = response.body().message;
                coinsDataRequestResponse.status = response.body().status;
                this.f6307a.onSuccess(coinsDataRequestResponse);
                return;
            }
            int code = response.code();
            this.f6307a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<SGetFitnessBuddyRequestsAndBuddiesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6308a;

        public f(CoveApiListener coveApiListener) {
            this.f6308a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetFitnessBuddyRequestsAndBuddiesResponse> call, Throwable th) {
            this.f6308a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetFitnessBuddyRequestsAndBuddiesResponse> call, Response<SGetFitnessBuddyRequestsAndBuddiesResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                if (response.body().data != null && response.body().data.buddyRequests != null) {
                    for (Requests requests : response.body().data.buddyRequests) {
                        if (requests.requestStatus.equals("PENDING")) {
                            if (requests.fromUserId == PreferenceManager.getInstance().getUserId().intValue()) {
                                arrayList2.add(requests);
                            } else {
                                arrayList.add(requests);
                            }
                        }
                    }
                }
                this.f6308a.onSuccess(new GetFitnessBuddyRequestsAndBuddiesResponse(response.code(), arrayList2, arrayList, arrayList3));
                return;
            }
            int code = response.code();
            this.f6308a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<GetBuddyListModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6309a;

        public g(CoveApiListener coveApiListener) {
            this.f6309a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetBuddyListModel> call, Throwable th) {
            this.f6309a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetBuddyListModel> call, Response<GetBuddyListModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6309a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6309a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<GetBuddyDetailsModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6310a;

        public h(CoveApiListener coveApiListener) {
            this.f6310a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetBuddyDetailsModel> call, Throwable th) {
            this.f6310a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetBuddyDetailsModel> call, Response<GetBuddyDetailsModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6310a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6310a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<GetBuddyLookUpResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6311a;

        public i(CoveApiListener coveApiListener) {
            this.f6311a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetBuddyLookUpResponse> call, Throwable th) {
            this.f6311a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetBuddyLookUpResponse> call, Response<GetBuddyLookUpResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6311a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6311a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<CommonResponseClass> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6312a;

        public j(CoveApiListener coveApiListener) {
            this.f6312a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6312a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            if (response.isSuccessful()) {
                this.f6312a.onSuccess(response.body());
            } else {
                this.f6312a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class k implements Callback<SFitnessBuddiesInviteResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6313a;

        public k(CoveApiListener coveApiListener) {
            this.f6313a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SFitnessBuddiesInviteResponse> call, Throwable th) {
            this.f6313a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SFitnessBuddiesInviteResponse> call, Response<SFitnessBuddiesInviteResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6313a.onSuccess(new SendFitnessBuddyRequestResponse(response.code(), response.body().data.buddyRequests));
                return;
            }
            int code = response.code();
            this.f6313a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class l implements Callback<SGetFitnessBuddyRequestsAndBuddiesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6314a;

        public l(CoveApiListener coveApiListener) {
            this.f6314a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetFitnessBuddyRequestsAndBuddiesResponse> call, Throwable th) {
            this.f6314a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetFitnessBuddyRequestsAndBuddiesResponse> call, Response<SGetFitnessBuddyRequestsAndBuddiesResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                if (response.body().data != null && response.body().data.buddyRequests != null) {
                    for (Requests requests : response.body().data.buddyRequests) {
                        if (requests.requestStatus.equals("PENDING")) {
                            if (requests.fromUserId == PreferenceManager.getInstance().getUserId().intValue()) {
                                arrayList2.add(requests);
                            } else {
                                arrayList.add(requests);
                            }
                        }
                    }
                }
                this.f6314a.onSuccess(new GetFitnessBuddyRequestsAndBuddiesResponse(response.code(), arrayList2, arrayList, arrayList3));
                return;
            }
            int code = response.code();
            this.f6314a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class m implements Callback<SGetFitnessBuddiesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6315a;

        public m(CoveApiListener coveApiListener) {
            this.f6315a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetFitnessBuddiesResponse> call, Throwable th) {
            this.f6315a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetFitnessBuddiesResponse> call, Response<SGetFitnessBuddiesResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.fitnessBuddies != null) {
                this.f6315a.onSuccess(new GetFitnessBuddiesResponse(response.code(), response.body().data.fitnessBuddies));
                return;
            }
            int code = response.code();
            this.f6315a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class n implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6316a;

        public n(CoveApiListener coveApiListener) {
            this.f6316a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6316a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                this.f6316a.onSuccess(new HandleBuddyRequest(response.code()));
                return;
            }
            int code = response.code();
            this.f6316a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class o implements Callback<SGetFitnessBuddiesGoalsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6317a;

        public o(CoveApiListener coveApiListener) {
            this.f6317a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetFitnessBuddiesGoalsResponse> call, Throwable th) {
            this.f6317a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetFitnessBuddiesGoalsResponse> call, Response<SGetFitnessBuddiesGoalsResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.buddiesGoals != null) {
                this.f6317a.onSuccess(new GetFitnessBuddiesGoalsResponse(response.code(), response.body().data.buddiesGoals));
                return;
            }
            int code = response.code();
            this.f6317a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class p implements Callback<SGetFitnessBuddiesGoalSpecificResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6318a;

        public p(CoveApiListener coveApiListener) {
            this.f6318a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetFitnessBuddiesGoalSpecificResponse> call, Throwable th) {
            this.f6318a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetFitnessBuddiesGoalSpecificResponse> call, Response<SGetFitnessBuddiesGoalSpecificResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.buddiesGoals != null) {
                this.f6318a.onSuccess(new GetFitnessBuddiesGoalSpecificResponse(response.code(), response.body().data.buddiesGoals));
                return;
            }
            int code = response.code();
            this.f6318a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class q implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6319a;

        public q(CoveApiListener coveApiListener) {
            this.f6319a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6319a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                this.f6319a.onSuccess(new HandleBuddyRequest(response.code()));
                return;
            }
            int code = response.code();
            this.f6319a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class r implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6320a;

        public r(CoveApiListener coveApiListener) {
            this.f6320a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6320a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                this.f6320a.onSuccess(new UnfriendBuddyResponse(response.code()));
                return;
            }
            int code = response.code();
            this.f6320a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class s implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6321a;

        public s(CoveApiListener coveApiListener) {
            this.f6321a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6321a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                this.f6321a.onSuccess(new CancelBuddyRequestResponse(response.code()));
                return;
            }
            int code = response.code();
            this.f6321a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(int i2, String str, CoveApiListener<GetBuddyListModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessBuddiesStats(map, i2, str).enqueue(new g(coveApiListener));
    }

    public static void b(CoveApiListener<AppReferralModelRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getAppReferalLinks(map).enqueue(new b(coveApiListener));
    }

    public static void c(@NonNull CoveApiListener<GetFitnessBuddyRequestsAndBuddiesResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map, int i2, String str) {
        CoveApi.getService().getFitnessBuddiesAndRequestsNew(map, i2, str).enqueue(new f(coveApiListener));
    }

    public static void cancelBuddyRequestSent(@NonNull Integer num, @NonNull CoveApiListener<CancelBuddyRequestResponse, CoveApiErrorModel> coveApiListener) {
        g(num, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void d(@NonNull CoinsDataRequest coinsDataRequest, @NonNull CoveApiListener<CoinsDataRequestResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (coinsDataRequest.getRecipients() != null) {
            CoveApi.getService().sendCoinsData(map, coinsDataRequest).enqueue(new e(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void deleteBuddyNudgeMessages(String str, CoveApiListener<SDeleteBuddiesMessageResponse, CoveApiErrorModel> coveApiListener) {
        j(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void deleteWatchFace(WatchFaceRequest watchFaceRequest, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        l(CoveApi.getCustomHeaders(), watchFaceRequest, coveApiListener);
    }

    public static void e(@NonNull SendFitnessBuddyRequest sendFitnessBuddyRequest, @NonNull CoveApiListener<SendFitnessBuddyRequestResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (sendFitnessBuddyRequest.getBuddies() != null) {
            CoveApi.getService().sendFitnessBuddyRequest(map, sendFitnessBuddyRequest).enqueue(new k(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void f(SendFitnessBuddyLookUpRequest sendFitnessBuddyLookUpRequest, CoveApiListener<GetBuddyLookUpResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessBuddiesLookUp(map, sendFitnessBuddyLookUpRequest).enqueue(new i(coveApiListener));
    }

    public static void g(@NonNull Integer num, @NonNull CoveApiListener<CancelBuddyRequestResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().cancelBuddyRequest(map, num.intValue()).enqueue(new s(coveApiListener));
    }

    public static void getAppReferalLinks(CoveApiListener<AppReferralModelRes, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getBuddiesDetails(String str, String str2, CoveApiListener<GetBuddyDetailsModel, CoveApiErrorModel> coveApiListener) {
        k(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getBuddiesLookUp(SendFitnessBuddyLookUpRequest sendFitnessBuddyLookUpRequest, CoveApiListener<GetBuddyLookUpResponse, CoveApiErrorModel> coveApiListener) {
        f(sendFitnessBuddyLookUpRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getBuddiesStats(int i2, String str, CoveApiListener<GetBuddyListModel, CoveApiErrorModel> coveApiListener) {
        a(i2, str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getBuddyMessages(@NonNull CoveApiListener<GetFitnessBuddiesMessagesResponse, CoveApiErrorModel> coveApiListener) {
        m(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessBuddies(@NonNull CoveApiListener<GetFitnessBuddiesResponse, CoveApiErrorModel> coveApiListener) {
        s(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessBuddiesActivitySpecificGoals(String str, @NonNull CoveApiListener<GetFitnessBuddiesGoalSpecificResponse, CoveApiErrorModel> coveApiListener) {
        o(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessBuddiesAndRequests(@NonNull CoveApiListener<GetFitnessBuddyRequestsAndBuddiesResponse, CoveApiErrorModel> coveApiListener) {
        p(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessBuddiesAndRequestsNew(@NonNull CoveApiListener<GetFitnessBuddyRequestsAndBuddiesResponse, CoveApiErrorModel> coveApiListener, int i2, String str) {
        c(coveApiListener, CoveApi.getCustomHeaders(), i2, str);
    }

    public static void getFitnessBuddiesGoals(@NonNull CoveApiListener<GetFitnessBuddiesGoalsResponse, CoveApiErrorModel> coveApiListener) {
        r(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void h(@NonNull Integer num, @NonNull FitnessBuddiesAction fitnessBuddiesAction, @NonNull CoveApiListener<HandleBuddyRequest, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().handleBuddyRequest(map, num.intValue(), fitnessBuddiesAction.getAction()).enqueue(new n(coveApiListener));
    }

    public static void handleBuddyRequest(@NonNull Integer num, @NonNull FitnessBuddiesAction fitnessBuddiesAction, @NonNull CoveApiListener<HandleBuddyRequest, CoveApiErrorModel> coveApiListener) {
        h(num, fitnessBuddiesAction, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void i(@NonNull Integer num, @NonNull SendReactionRequest sendReactionRequest, @NonNull CoveApiListener<HandleBuddyRequest, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().sendReaction(map, num, sendReactionRequest).enqueue(new q(coveApiListener));
    }

    public static void j(String str, CoveApiListener<SDeleteBuddiesMessageResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().deleteBuddyNudgeMessages(map, str).enqueue(new d(coveApiListener));
    }

    public static void k(String str, String str2, CoveApiListener<GetBuddyDetailsModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessBuddiesDetails(map, str, str2).enqueue(new h(coveApiListener));
    }

    public static void l(Map<String, String> map, WatchFaceRequest watchFaceRequest, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().deleteWatchFace(map, watchFaceRequest).enqueue(new j(coveApiListener));
    }

    public static void m(@NonNull CoveApiListener<GetFitnessBuddiesMessagesResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getBuddyMessages(map).enqueue(new a(coveApiListener));
    }

    public static void n(@NonNull Integer num, @NonNull CoveApiListener<UnfriendBuddyResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().unfriendBuddy(map, num.intValue()).enqueue(new r(coveApiListener));
    }

    public static void o(String str, @NonNull CoveApiListener<GetFitnessBuddiesGoalSpecificResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getBuddiesActivitySpecificGoals(map, str).enqueue(new p(coveApiListener));
    }

    public static void p(@NonNull CoveApiListener<GetFitnessBuddyRequestsAndBuddiesResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessBuddiesAndRequests(map).enqueue(new l(coveApiListener));
    }

    public static void postAppReferrerData(String str, CoveApiListener<PostAppReferrerDataRes, CoveApiErrorModel> coveApiListener) {
        q(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void q(String str, CoveApiListener<PostAppReferrerDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SPostAppRefererDataReq sPostAppRefererDataReq = new SPostAppRefererDataReq();
        sPostAppRefererDataReq.setReferrerData(str);
        CoveApi.getService().postApprefererData(map, sPostAppRefererDataReq).enqueue(new c(coveApiListener));
    }

    public static void r(@NonNull CoveApiListener<GetFitnessBuddiesGoalsResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getBuddiesGoals(map).enqueue(new o(coveApiListener));
    }

    public static void s(@NonNull CoveApiListener<GetFitnessBuddiesResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessBuddies(map).enqueue(new m(coveApiListener));
    }

    public static void sendCoinsData(@NonNull CoinsDataRequest coinsDataRequest, @NonNull CoveApiListener<CoinsDataRequestResponse, CoveApiErrorModel> coveApiListener) {
        d(coinsDataRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendFitnessBuddyRequest(@NonNull SendFitnessBuddyRequest sendFitnessBuddyRequest, @NonNull CoveApiListener<SendFitnessBuddyRequestResponse, CoveApiErrorModel> coveApiListener) {
        e(sendFitnessBuddyRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendReaction(@NonNull Integer num, @NonNull SendReactionRequest sendReactionRequest, @NonNull CoveApiListener<HandleBuddyRequest, CoveApiErrorModel> coveApiListener) {
        i(num, sendReactionRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void unfriendBuddy(@NonNull Integer num, @NonNull CoveApiListener<UnfriendBuddyResponse, CoveApiErrorModel> coveApiListener) {
        n(num, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void cancelBuddyRequestSent(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull CoveApiListener<CancelBuddyRequestResponse, CoveApiErrorModel> coveApiListener) {
        g(num, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void deleteBuddyNudgeMessages(HashMap<String, String> hashMap, String str, CoveApiListener<SDeleteBuddiesMessageResponse, CoveApiErrorModel> coveApiListener) {
        j(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void deleteWatchFace(HashMap<String, String> hashMap, WatchFaceRequest watchFaceRequest, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        l(CoveUtil.getRevisedHeaders(hashMap), watchFaceRequest, coveApiListener);
    }

    public static void getAppReferalLinks(HashMap<String, String> hashMap, CoveApiListener<AppReferralModelRes, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getBuddiesDetails(HashMap<String, String> hashMap, String str, String str2, CoveApiListener<GetBuddyDetailsModel, CoveApiErrorModel> coveApiListener) {
        k(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getBuddiesLookUp(HashMap<String, String> hashMap, SendFitnessBuddyLookUpRequest sendFitnessBuddyLookUpRequest, CoveApiListener<GetBuddyLookUpResponse, CoveApiErrorModel> coveApiListener) {
        f(sendFitnessBuddyLookUpRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getBuddiesStats(HashMap<String, String> hashMap, int i2, String str, CoveApiListener<GetBuddyListModel, CoveApiErrorModel> coveApiListener) {
        a(i2, str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getBuddyMessages(HashMap<String, String> hashMap, @NonNull CoveApiListener<GetFitnessBuddiesMessagesResponse, CoveApiErrorModel> coveApiListener) {
        m(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessBuddies(HashMap<String, String> hashMap, @NonNull CoveApiListener<GetFitnessBuddiesResponse, CoveApiErrorModel> coveApiListener) {
        s(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessBuddiesActivitySpecificGoals(HashMap<String, String> hashMap, String str, @NonNull CoveApiListener<GetFitnessBuddiesGoalSpecificResponse, CoveApiErrorModel> coveApiListener) {
        o(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessBuddiesAndRequests(HashMap<String, String> hashMap, @NonNull CoveApiListener<GetFitnessBuddyRequestsAndBuddiesResponse, CoveApiErrorModel> coveApiListener) {
        p(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessBuddiesAndRequestsNew(HashMap<String, String> hashMap, @NonNull CoveApiListener<GetFitnessBuddyRequestsAndBuddiesResponse, CoveApiErrorModel> coveApiListener, int i2, String str) {
        c(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), i2, str);
    }

    public static void getFitnessBuddiesGoals(HashMap<String, String> hashMap, @NonNull CoveApiListener<GetFitnessBuddiesGoalsResponse, CoveApiErrorModel> coveApiListener) {
        r(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void handleBuddyRequest(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull FitnessBuddiesAction fitnessBuddiesAction, @NonNull CoveApiListener<HandleBuddyRequest, CoveApiErrorModel> coveApiListener) {
        h(num, fitnessBuddiesAction, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void postAppReferrerData(HashMap<String, String> hashMap, String str, CoveApiListener<PostAppReferrerDataRes, CoveApiErrorModel> coveApiListener) {
        q(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendCoinsData(HashMap<String, String> hashMap, @NonNull CoinsDataRequest coinsDataRequest, @NonNull CoveApiListener<CoinsDataRequestResponse, CoveApiErrorModel> coveApiListener) {
        d(coinsDataRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendFitnessBuddyRequest(HashMap<String, String> hashMap, @NonNull SendFitnessBuddyRequest sendFitnessBuddyRequest, @NonNull CoveApiListener<SendFitnessBuddyRequestResponse, CoveApiErrorModel> coveApiListener) {
        e(sendFitnessBuddyRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendReaction(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull SendReactionRequest sendReactionRequest, @NonNull CoveApiListener<HandleBuddyRequest, CoveApiErrorModel> coveApiListener) {
        i(num, sendReactionRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void unfriendBuddy(HashMap<String, String> hashMap, @NonNull Integer num, @NonNull CoveApiListener<UnfriendBuddyResponse, CoveApiErrorModel> coveApiListener) {
        n(num, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
