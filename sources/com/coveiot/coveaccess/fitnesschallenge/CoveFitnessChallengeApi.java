package com.coveiot.coveaccess.fitnesschallenge;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitnesschallenge.model.AddParticipantsReq;
import com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeReq;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.FitnessChallengeStatsReq;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.coveiot.coveaccess.fitnesschallenge.model.JoinChallengeReq;
import com.coveiot.coveaccess.fitnesschallenge.model.RemoveParticipantsReq;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveFitnessChallengeApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6534a;

        public a(CoveApiListener coveApiListener) {
            this.f6534a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6534a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6534a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6534a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6535a;

        public b(CoveApiListener coveApiListener) {
            this.f6535a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6535a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6535a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6535a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<CommonResponseGeneric<BuddiesChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6536a;

        public c(CoveApiListener coveApiListener) {
            this.f6536a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Throwable th) {
            this.f6536a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Response<CommonResponseGeneric<BuddiesChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6536a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6536a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6537a;

        public d(CoveApiListener coveApiListener) {
            this.f6537a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6537a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6537a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6537a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6538a;

        public e(CoveApiListener coveApiListener) {
            this.f6538a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6538a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6538a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6538a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6539a;

        public f(CoveApiListener coveApiListener) {
            this.f6539a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6539a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6539a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            try {
                JSONObject jSONObject = new JSONObject(response.errorBody().string());
                if (jSONObject.getString("code") != null && jSONObject.getString("code").equalsIgnoreCase("ERROR_FIT_FL_100400")) {
                    this.f6539a.onError(new CoveApiErrorModel(jSONObject.getString(Constants.KEY_MESSAGE)));
                } else {
                    this.f6539a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                }
            } catch (Exception unused) {
                this.f6539a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<CommonResponseClass> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6540a;

        public g(CoveApiListener coveApiListener) {
            this.f6540a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6540a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6540a.onSuccess(response.body());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6540a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<CommonResponseGeneric<GetAllParticipantsFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6541a;

        public h(CoveApiListener coveApiListener) {
            this.f6541a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<GetAllParticipantsFitnessChallengeRes>> call, Throwable th) {
            this.f6541a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<GetAllParticipantsFitnessChallengeRes>> call, Response<CommonResponseGeneric<GetAllParticipantsFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6541a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6541a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<CommonResponseGeneric<GetChallengeStartNEndDateRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6542a;

        public i(CoveApiListener coveApiListener) {
            this.f6542a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<GetChallengeStartNEndDateRes>> call, Throwable th) {
            this.f6542a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<GetChallengeStartNEndDateRes>> call, Response<CommonResponseGeneric<GetChallengeStartNEndDateRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6542a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6542a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6543a;

        public j(CoveApiListener coveApiListener) {
            this.f6543a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6543a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6543a.onSuccess(response);
            } else {
                this.f6543a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class k implements Callback<CommonResponseGeneric<BuddiesChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6544a;

        public k(CoveApiListener coveApiListener) {
            this.f6544a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Throwable th) {
            this.f6544a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Response<CommonResponseGeneric<BuddiesChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6544a.onSuccess(response.body());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6544a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class l implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6545a;

        public l(CoveApiListener coveApiListener) {
            this.f6545a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6545a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6545a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            try {
                JSONObject jSONObject = new JSONObject(response.errorBody().string());
                if (jSONObject.getString("code") != null && jSONObject.getString("code").equalsIgnoreCase("ERROR_FIT_FL_100400")) {
                    this.f6545a.onError(new CoveApiErrorModel(jSONObject.getString(Constants.KEY_MESSAGE)));
                } else {
                    this.f6545a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                }
            } catch (Exception unused) {
                this.f6545a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class m implements Callback<CommonResponseGeneric<BuddiesChallengeDetail>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6546a;

        public m(CoveApiListener coveApiListener) {
            this.f6546a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<BuddiesChallengeDetail>> call, Throwable th) {
            this.f6546a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<BuddiesChallengeDetail>> call, Response<CommonResponseGeneric<BuddiesChallengeDetail>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6546a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6546a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class n implements Callback<CommonResponseGeneric<BuddiesChallengeDetail>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6547a;

        public n(CoveApiListener coveApiListener) {
            this.f6547a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<BuddiesChallengeDetail>> call, Throwable th) {
            this.f6547a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<BuddiesChallengeDetail>> call, Response<CommonResponseGeneric<BuddiesChallengeDetail>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6547a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6547a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class o implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6548a;

        public o(CoveApiListener coveApiListener) {
            this.f6548a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6548a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6548a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6548a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class p implements Callback<CommonResponseGeneric<BannerImagesRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6549a;

        public p(CoveApiListener coveApiListener) {
            this.f6549a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<BannerImagesRes>> call, Throwable th) {
            this.f6549a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<BannerImagesRes>> call, Response<CommonResponseGeneric<BannerImagesRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6549a.onSuccess(response.body().getData().getItems());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6549a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class q implements Callback<CommonResponseGeneric<CreateFitnessChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6550a;

        public q(CoveApiListener coveApiListener) {
            this.f6550a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Throwable th) {
            this.f6550a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<CreateFitnessChallengeRes>> call, Response<CommonResponseGeneric<CreateFitnessChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6550a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6550a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class r implements Callback<CommonResponseGeneric<BuddiesChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6551a;

        public r(CoveApiListener coveApiListener) {
            this.f6551a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Throwable th) {
            this.f6551a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Response<CommonResponseGeneric<BuddiesChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6551a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6551a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class s implements Callback<CommonResponseGeneric<BuddiesChallengeRes>> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6552a;

        public s(CoveApiListener coveApiListener) {
            this.f6552a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Throwable th) {
            this.f6552a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<BuddiesChallengeRes>> call, Response<CommonResponseGeneric<BuddiesChallengeRes>> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6552a.onSuccess(response.body().getData().getItems());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6552a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(Map<String, String> map, @NonNull CoveApiListener<List<BannerImagesRes.Item>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getBannerImageForChallengeCreation(map).enqueue(new p(coveApiListener));
    }

    public static void addParticipantsForChallenge(AddParticipantsReq addParticipantsReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        b(CoveApi.getCustomHeaders(), addParticipantsReq, coveApiListener);
    }

    public static void b(Map<String, String> map, AddParticipantsReq addParticipantsReq, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().addParticipants(map, addParticipantsReq).enqueue(new o(coveApiListener));
    }

    public static void c(Map<String, String> map, CreateFitnessChallengeReq createFitnessChallengeReq, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().createNewBuddiesChallenge(map, createFitnessChallengeReq).enqueue(new l(coveApiListener));
    }

    public static void createNewBuddiesChallenge(CreateFitnessChallengeReq createFitnessChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        c(CoveApi.getCustomHeaders(), createFitnessChallengeReq, coveApiListener);
    }

    public static void createNewGlobalFitnessChallenge(CreateFitnessChallengeReq createFitnessChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        l(CoveApi.getCustomHeaders(), createFitnessChallengeReq, coveApiListener);
    }

    public static void d(Map<String, String> map, FitnessChallengeStatsReq fitnessChallengeStatsReq, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().postFitnessChallenge(map, fitnessChallengeStatsReq).enqueue(new g(coveApiListener));
    }

    public static void deleteBuddyFitnessChallenge(Object obj, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        g(CoveApi.getCustomHeaders(), obj, coveApiListener);
    }

    public static void downloadFCLeaderboardReport(Object obj, CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener) {
        n(CoveApi.getCustomHeaders(), obj, coveApiListener);
    }

    public static void e(Map<String, String> map, JoinChallengeReq joinChallengeReq, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().joinChallenge(map, joinChallengeReq).enqueue(new a(coveApiListener));
    }

    public static void editBuddyFitnessChallenge(Object obj, CreateFitnessChallengeReq createFitnessChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        h(CoveApi.getCustomHeaders(), obj, createFitnessChallengeReq, coveApiListener);
    }

    public static void f(Map<String, String> map, RemoveParticipantsReq removeParticipantsReq, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().removeParticipants(map, removeParticipantsReq).enqueue(new e(coveApiListener));
    }

    public static void g(Map<String, String> map, Object obj, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().deleteBuddyChallenge(map, obj).enqueue(new b(coveApiListener));
    }

    public static void getAllFitnessChallengeParticipants(Object obj, String str, String str2, int i2, int i3, CoveApiListener<GetAllParticipantsFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        i(CoveApi.getCustomHeaders(), obj, str, str2, i2, i3, coveApiListener);
    }

    public static void getBannersForChallengeCreation(CoveApiListener<List<BannerImagesRes.Item>, CoveApiErrorModel> coveApiListener) {
        a(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getChallengesByType(String str, int i2, int i3, CoveApiListener<CommonResponseGeneric<BuddiesChallengeRes>, CoveApiErrorModel> coveApiListener) {
        j(CoveApi.getCustomHeaders(), str, i2, i3, coveApiListener);
    }

    public static void getDashboardChallenges(CoveApiListener<List<BuddiesChallengeRes.Item>, CoveApiErrorModel> coveApiListener) {
        k(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getDetailsOfBuddyChallenge(Object obj, CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel> coveApiListener) {
        q(CoveApi.getCustomHeaders(), obj, coveApiListener);
    }

    public static void getDetailsOfGlobalChallenge(Object obj, CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel> coveApiListener) {
        r(CoveApi.getCustomHeaders(), obj, coveApiListener);
    }

    public static void getFitnessChallengeDates(CoveApiListener<GetChallengeStartNEndDateRes, CoveApiErrorModel> coveApiListener) {
        p(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getMyAchievementDetail(int i2, int i3, CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel> coveApiListener) {
        getMyAchievementDetailL(CoveApi.getCustomHeaders(), i2, i3, coveApiListener);
    }

    public static void getMyAchievementDetailL(Map<String, String> map, int i2, int i3, @NonNull CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getMyAchievement(map, i2, i3).enqueue(new c(coveApiListener));
    }

    public static void getMyFitnessChallenges(String str, int i2, int i3, CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel> coveApiListener) {
        o(CoveApi.getCustomHeaders(), str, i2, i3, coveApiListener);
    }

    public static void h(Map<String, String> map, Object obj, CreateFitnessChallengeReq createFitnessChallengeReq, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().editBuddyChallenge(map, obj, createFitnessChallengeReq).enqueue(new f(coveApiListener));
    }

    public static void i(Map<String, String> map, Object obj, String str, String str2, int i2, int i3, @NonNull CoveApiListener<GetAllParticipantsFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getAllParticipants(map, obj, str, str2, i2, i3).enqueue(new h(coveApiListener));
    }

    public static void j(Map<String, String> map, String str, int i2, int i3, @NonNull CoveApiListener<CommonResponseGeneric<BuddiesChallengeRes>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getChallenges(map, str, i2, i3).enqueue(new k(coveApiListener));
    }

    public static void joinFitnessChallenge(JoinChallengeReq joinChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        e(CoveApi.getCustomHeaders(), joinChallengeReq, coveApiListener);
    }

    public static void k(Map<String, String> map, @NonNull CoveApiListener<List<BuddiesChallengeRes.Item>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getDashboardFitnessChallenge(map).enqueue(new s(coveApiListener));
    }

    public static void l(Map<String, String> map, CreateFitnessChallengeReq createFitnessChallengeReq, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().createNewGlobalChallenge(map, createFitnessChallengeReq).enqueue(new q(coveApiListener));
    }

    public static void leaveFitnessChallenge(JoinChallengeReq joinChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        m(CoveApi.getCustomHeaders(), joinChallengeReq, coveApiListener);
    }

    public static void m(Map<String, String> map, JoinChallengeReq joinChallengeReq, @NonNull CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().leaveBuddyOrGlobalChallenge(map, joinChallengeReq).enqueue(new d(coveApiListener));
    }

    public static void n(Map<String, String> map, Object obj, @NonNull CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().downloadLeaderboardReport(map, obj).enqueue(new j(coveApiListener));
    }

    public static void o(Map<String, String> map, String str, int i2, int i3, @NonNull CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getMyChallenges(map, str, Integer.valueOf(i2), Integer.valueOf(i3)).enqueue(new r(coveApiListener));
    }

    public static void p(Map<String, String> map, @NonNull CoveApiListener<GetChallengeStartNEndDateRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getChallengeDates(map).enqueue(new i(coveApiListener));
    }

    public static void postFitnessChallengeDetails(FitnessChallengeStatsReq fitnessChallengeStatsReq, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        d(CoveApi.getCustomHeaders(), fitnessChallengeStatsReq, coveApiListener);
    }

    public static void q(Map<String, String> map, Object obj, @NonNull CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getBuddyChallengeDetails(map, obj).enqueue(new m(coveApiListener));
    }

    public static void r(Map<String, String> map, Object obj, @NonNull CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getGlobalChallengeDetails(map, obj).enqueue(new n(coveApiListener));
    }

    public static void removeParticipantsFromChallenge(RemoveParticipantsReq removeParticipantsReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        f(CoveApi.getCustomHeaders(), removeParticipantsReq, coveApiListener);
    }

    public static void addParticipantsForChallenge(HashMap<String, String> hashMap, AddParticipantsReq addParticipantsReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        b(CoveUtil.getRevisedHeaders(hashMap), addParticipantsReq, coveApiListener);
    }

    public static void createNewBuddiesChallenge(HashMap<String, String> hashMap, CreateFitnessChallengeReq createFitnessChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        c(CoveUtil.getRevisedHeaders(hashMap), createFitnessChallengeReq, coveApiListener);
    }

    public static void createNewGlobalFitnessChallenge(HashMap<String, String> hashMap, CreateFitnessChallengeReq createFitnessChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        l(CoveUtil.getRevisedHeaders(hashMap), createFitnessChallengeReq, coveApiListener);
    }

    public static void deleteBuddyFitnessChallenge(HashMap<String, String> hashMap, Object obj, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        g(CoveUtil.getRevisedHeaders(hashMap), obj, coveApiListener);
    }

    public static void downloadFCLeaderboardReport(HashMap<String, String> hashMap, Object obj, CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener) {
        n(CoveUtil.getRevisedHeaders(hashMap), obj, coveApiListener);
    }

    public static void editBuddyFitnessChallenge(HashMap<String, String> hashMap, Object obj, CreateFitnessChallengeReq createFitnessChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        h(CoveUtil.getRevisedHeaders(hashMap), obj, createFitnessChallengeReq, coveApiListener);
    }

    public static void getAllFitnessChallengeParticipants(HashMap<String, String> hashMap, Object obj, String str, String str2, int i2, int i3, CoveApiListener<GetAllParticipantsFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        i(CoveUtil.getRevisedHeaders(hashMap), obj, str, str2, i2, i3, coveApiListener);
    }

    public static void getBannersForChallengeCreation(HashMap<String, String> hashMap, CoveApiListener<List<BannerImagesRes.Item>, CoveApiErrorModel> coveApiListener) {
        a(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getChallengesByType(HashMap<String, String> hashMap, String str, int i2, int i3, CoveApiListener<CommonResponseGeneric<BuddiesChallengeRes>, CoveApiErrorModel> coveApiListener) {
        j(CoveUtil.getRevisedHeaders(hashMap), str, i2, i3, coveApiListener);
    }

    public static void getDashboardChallenges(HashMap<String, String> hashMap, CoveApiListener<List<BuddiesChallengeRes.Item>, CoveApiErrorModel> coveApiListener) {
        k(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getDetailsOfBuddyChallenge(HashMap<String, String> hashMap, Object obj, CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel> coveApiListener) {
        q(CoveUtil.getRevisedHeaders(hashMap), obj, coveApiListener);
    }

    public static void getDetailsOfGlobalChallenge(HashMap<String, String> hashMap, Object obj, CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel> coveApiListener) {
        r(CoveUtil.getRevisedHeaders(hashMap), obj, coveApiListener);
    }

    public static void getFitnessChallengeDates(HashMap<String, String> hashMap, CoveApiListener<GetChallengeStartNEndDateRes, CoveApiErrorModel> coveApiListener) {
        p(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getMyAchievementDetail(HashMap<String, String> hashMap, int i2, int i3, CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel> coveApiListener) {
        getMyAchievementDetailL(CoveUtil.getRevisedHeaders(hashMap), i2, i3, coveApiListener);
    }

    public static void getMyFitnessChallenges(HashMap<String, String> hashMap, String str, int i2, int i3, CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel> coveApiListener) {
        o(CoveUtil.getRevisedHeaders(hashMap), str, i2, i3, coveApiListener);
    }

    public static void joinFitnessChallenge(HashMap<String, String> hashMap, JoinChallengeReq joinChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        e(CoveUtil.getRevisedHeaders(hashMap), joinChallengeReq, coveApiListener);
    }

    public static void leaveFitnessChallenge(HashMap<String, String> hashMap, JoinChallengeReq joinChallengeReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        m(CoveUtil.getRevisedHeaders(hashMap), joinChallengeReq, coveApiListener);
    }

    public static void postFitnessChallengeDetails(HashMap<String, String> hashMap, FitnessChallengeStatsReq fitnessChallengeStatsReq, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        d(CoveUtil.getRevisedHeaders(hashMap), fitnessChallengeStatsReq, coveApiListener);
    }

    public static void removeParticipantsFromChallenge(HashMap<String, String> hashMap, RemoveParticipantsReq removeParticipantsReq, CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel> coveApiListener) {
        f(CoveUtil.getRevisedHeaders(hashMap), removeParticipantsReq, coveApiListener);
    }
}
