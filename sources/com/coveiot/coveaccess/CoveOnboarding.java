package com.coveiot.coveaccess;

import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.constants.CoveApiHeaderConstants;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.model.server.FCMTokenDataReq;
import com.coveiot.coveaccess.model.server.GetOTPServicesRes;
import com.coveiot.coveaccess.model.server.GuestUserSessionDataRes;
import com.coveiot.coveaccess.model.server.OTPAuthReq;
import com.coveiot.coveaccess.model.server.SGetUserByNumberModel;
import com.coveiot.coveaccess.model.server.SModifyPhoneNumberReq;
import com.coveiot.coveaccess.model.server.SPhoneNumberVerificationModel;
import com.coveiot.coveaccess.model.server.SPostReportIssue;
import com.coveiot.coveaccess.model.server.SRegisterNewUserModel;
import com.coveiot.coveaccess.model.server.SRegisterReturningUserModel;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.model.server.SRemoveProfilePicture;
import com.coveiot.coveaccess.model.server.SRemoveUserModel;
import com.coveiot.coveaccess.model.server.SUpdateLocationApiReq;
import com.coveiot.coveaccess.model.server.SUpdateProfilePicModel;
import com.coveiot.coveaccess.model.server.SUpdateRegistrationTokenModel;
import com.coveiot.coveaccess.model.server.SUpdateUserModel;
import com.coveiot.coveaccess.model.server.UpdateLocationApiReq;
import com.coveiot.coveaccess.model.server.UserRemoteConfigResponse;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsReq;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsRes;
import com.coveiot.coveaccess.onboarding.model.FCMRegistrationReq;
import com.coveiot.coveaccess.onboarding.model.FCMRegistrationRes;
import com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes;
import com.coveiot.coveaccess.onboarding.model.ModifyPhoneNumberReq;
import com.coveiot.coveaccess.onboarding.model.ModifyPhoneNumberRes;
import com.coveiot.coveaccess.onboarding.model.PhoneNumberVerificationReq;
import com.coveiot.coveaccess.onboarding.model.PhoneNumberVerificationRes;
import com.coveiot.coveaccess.onboarding.model.PostReportIssueReq;
import com.coveiot.coveaccess.onboarding.model.RegisterExistingUserReq;
import com.coveiot.coveaccess.onboarding.model.RegisterExistingUserRes;
import com.coveiot.coveaccess.onboarding.model.RegisterNewUserReq;
import com.coveiot.coveaccess.onboarding.model.RegisterNewUserRes;
import com.coveiot.coveaccess.onboarding.model.RemoveUserReq;
import com.coveiot.coveaccess.onboarding.model.RemoveUserRes;
import com.coveiot.coveaccess.onboarding.model.ReportIssueRes;
import com.coveiot.coveaccess.onboarding.model.UpdateLocationApiRes;
import com.coveiot.coveaccess.onboarding.model.UpdateProfilePictureReq;
import com.coveiot.coveaccess.onboarding.model.UpdateProfilePictureRes;
import com.coveiot.coveaccess.onboarding.model.UpdateUserReq;
import com.coveiot.coveaccess.onboarding.model.UpdateUserRes;
import com.coveiot.coveaccess.onboarding.model.UserByPhoneNumberReq;
import com.coveiot.coveaccess.onboarding.model.UserByPhoneNumberRes;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveOnboarding {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SRemoveUserModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6266a;

        public a(CoveApiListener coveApiListener) {
            this.f6266a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRemoveUserModel> call, Throwable th) {
            this.f6266a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRemoveUserModel> call, Response<SRemoveUserModel> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    RemoveUserRes removeUserRes = new RemoveUserRes(response.code());
                    removeUserRes.setStatus(response.body().getStatus());
                    removeUserRes.setMessage(response.body().getMessage());
                    this.f6266a.onSuccess(removeUserRes);
                    return;
                }
                int code = response.code();
                this.f6266a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6266a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SUpdateRegistrationTokenModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6267a;

        public b(CoveApiListener coveApiListener) {
            this.f6267a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SUpdateRegistrationTokenModel> call, Throwable th) {
            this.f6267a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SUpdateRegistrationTokenModel> call, Response<SUpdateRegistrationTokenModel> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    FCMRegistrationRes fCMRegistrationRes = new FCMRegistrationRes(response.code());
                    if (response.body() != null) {
                        fCMRegistrationRes.setStatus(response.body().getStatus());
                    }
                    this.f6267a.onSuccess(fCMRegistrationRes);
                    return;
                }
                int code = response.code();
                this.f6267a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6267a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SUpdateProfilePicModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6268a;

        public c(CoveApiListener coveApiListener) {
            this.f6268a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SUpdateProfilePicModel> call, Throwable th) {
            this.f6268a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SUpdateProfilePicModel> call, Response<SUpdateProfilePicModel> response) {
            if (response.isSuccessful() && response.body() != null) {
                UpdateProfilePictureRes updateProfilePictureRes = new UpdateProfilePictureRes(response.code());
                if (response.body().getData() != null) {
                    updateProfilePictureRes.setDpUrl(response.body().getData().getDpUrl());
                }
                this.f6268a.onSuccess(updateProfilePictureRes);
                return;
            }
            int code = response.code();
            this.f6268a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SRemoveProfilePicture> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6269a;

        public d(CoveApiListener coveApiListener) {
            this.f6269a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRemoveProfilePicture> call, Throwable th) {
            this.f6269a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRemoveProfilePicture> call, Response<SRemoveProfilePicture> response) {
            if (response.isSuccessful()) {
                this.f6269a.onSuccess(response.body());
            } else {
                this.f6269a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6270a;

        public e(CoveApiListener coveApiListener) {
            this.f6270a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6270a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6270a.onSuccess(new ModifyPhoneNumberRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6270a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6271a;

        public f(CoveApiListener coveApiListener) {
            this.f6271a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6271a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6271a.onSuccess(new ReportIssueRes(response.code()));
            } else {
                this.f6271a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<SRemoteConfigResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6272a;

        public g(CoveApiListener coveApiListener) {
            this.f6272a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRemoteConfigResponse> call, Throwable th) {
            this.f6272a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRemoteConfigResponse> call, Response<SRemoteConfigResponse> response) {
            if (response.isSuccessful()) {
                this.f6272a.onSuccess(response.body());
            } else {
                this.f6272a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6273a;

        public h(CoveApiListener coveApiListener) {
            this.f6273a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6273a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6273a.onSuccess(new UpdateLocationApiRes(response.code()));
            } else {
                this.f6273a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<AcceptLegalTermsRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6274a;

        public i(CoveApiListener coveApiListener) {
            this.f6274a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AcceptLegalTermsRes> call, Throwable th) {
            this.f6274a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AcceptLegalTermsRes> call, Response<AcceptLegalTermsRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6274a.onSuccess(response.body());
            } else {
                this.f6274a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6275a;

        public j(CoveApiListener coveApiListener) {
            this.f6275a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6275a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6275a.onSuccess(response);
            } else {
                this.f6275a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class k implements Callback<SPhoneNumberVerificationModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6276a;

        public k(CoveApiListener coveApiListener) {
            this.f6276a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPhoneNumberVerificationModel> call, Throwable th) {
            this.f6276a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPhoneNumberVerificationModel> call, Response<SPhoneNumberVerificationModel> response) {
            if (response.isSuccessful()) {
                this.f6276a.onSuccess(new PhoneNumberVerificationRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6276a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class l implements Callback<LegalDocsAcceptedListRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6277a;

        public l(CoveApiListener coveApiListener) {
            this.f6277a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<LegalDocsAcceptedListRes> call, Throwable th) {
            this.f6277a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<LegalDocsAcceptedListRes> call, Response<LegalDocsAcceptedListRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6277a.onSuccess(response.body());
            } else {
                this.f6277a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class m implements Callback<GuestUserSessionDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6278a;

        public m(CoveApiListener coveApiListener) {
            this.f6278a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GuestUserSessionDataRes> call, Throwable th) {
            this.f6278a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GuestUserSessionDataRes> call, Response<GuestUserSessionDataRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6278a.onSuccess(response.body());
            } else {
                this.f6278a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class n implements Callback<CommonResponseClass> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6279a;

        public n(CoveApiListener coveApiListener) {
            this.f6279a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6279a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6279a.onSuccess(response.body());
            } else {
                this.f6279a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class o implements Callback<SRemoteConfigResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6280a;

        public o(CoveApiListener coveApiListener) {
            this.f6280a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRemoteConfigResponse> call, Throwable th) {
            this.f6280a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRemoteConfigResponse> call, Response<SRemoteConfigResponse> response) {
            if (response.isSuccessful()) {
                this.f6280a.onSuccess(response.body());
            } else {
                this.f6280a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class p implements Callback<UserRemoteConfigResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6281a;

        public p(CoveApiListener coveApiListener) {
            this.f6281a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<UserRemoteConfigResponse> call, Throwable th) {
            this.f6281a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<UserRemoteConfigResponse> call, Response<UserRemoteConfigResponse> response) {
            if (response.isSuccessful()) {
                this.f6281a.onSuccess(response.body());
            } else {
                this.f6281a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class q implements Callback<CommonResponseGeneric<GetOTPServicesRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6282a;

        public q(CoveApiListener coveApiListener) {
            this.f6282a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<GetOTPServicesRes>> call, Throwable th) {
            this.f6282a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<GetOTPServicesRes>> call, Response<CommonResponseGeneric<GetOTPServicesRes>> response) {
            if (response.isSuccessful()) {
                this.f6282a.onSuccess(response.body().getData());
                return;
            }
            int code = response.code();
            this.f6282a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class r implements Callback<CommonResponseClass> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6283a;

        public r(CoveApiListener coveApiListener) {
            this.f6283a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6283a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            if (response.isSuccessful()) {
                this.f6283a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6283a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class s implements Callback<SPhoneNumberVerificationModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6284a;

        public s(CoveApiListener coveApiListener) {
            this.f6284a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPhoneNumberVerificationModel> call, Throwable th) {
            this.f6284a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPhoneNumberVerificationModel> call, Response<SPhoneNumberVerificationModel> response) {
            if (response.isSuccessful()) {
                this.f6284a.onSuccess(new PhoneNumberVerificationRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6284a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class t implements Callback<SPhoneNumberVerificationModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6285a;

        public t(CoveApiListener coveApiListener) {
            this.f6285a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPhoneNumberVerificationModel> call, Throwable th) {
            this.f6285a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPhoneNumberVerificationModel> call, Response<SPhoneNumberVerificationModel> response) {
            if (response.isSuccessful()) {
                this.f6285a.onSuccess(new PhoneNumberVerificationRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6285a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class u implements Callback<SGetUserByNumberModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ UserByPhoneNumberReq f6286a;
        public final /* synthetic */ CoveApiListener b;

        public u(UserByPhoneNumberReq userByPhoneNumberReq, CoveApiListener coveApiListener) {
            this.f6286a = userByPhoneNumberReq;
            this.b = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetUserByNumberModel> call, Throwable th) {
            this.b.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetUserByNumberModel> call, Response<SGetUserByNumberModel> response) {
            UserByPhoneNumberRes build;
            if (response.isSuccessful()) {
                SGetUserByNumberModel.DataBean data = response.body().getData();
                boolean z = !response.body().getStatus().equals(CoveApiConstants.RESPONSE_STATUS_VALUE_ERROR);
                if (data != null && z) {
                    build = new UserByPhoneNumberRes.Builder().setCode(response.code()).setExistingUser(z).setId(data.getId()).setName(data.getName()).setFirstName(data.getFirstName()).setLastName(data.getLastName()).setGender(data.getGender()).setMobileNumber(data.getMobileNumber()).setEmailId(data.getEmailId()).setBirthDate(data.getBirthDate()).setDpUrl(data.getDpUrl()).setEmailVerified(data.isEmailVerified()).setSocialMediaId(data.getSocialMediaDetails()).setDpExist(data.isDpExist()).setAxTrackerId(data.getAxTrackerId()).build();
                } else {
                    build = new UserByPhoneNumberRes.Builder().setCode(response.code()).setExistingUser(z).setMobileNumber(this.f6286a.getPhoneNumber()).build();
                }
                this.b.onSuccess(build);
                return;
            }
            int code = response.code();
            this.b.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class v implements Callback<SRegisterReturningUserModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6287a;

        public v(CoveApiListener coveApiListener) {
            this.f6287a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRegisterReturningUserModel> call, Throwable th) {
            this.f6287a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRegisterReturningUserModel> call, Response<SRegisterReturningUserModel> response) {
            if (response.isSuccessful()) {
                String str = response.headers().get(CoveApiHeaderConstants.HTTP_HEADER_SESSION_ID);
                Integer valueOf = Integer.valueOf(response.body().getData().getId());
                PreferenceManager.getInstance().saveSessionId(str);
                PreferenceManager.getInstance().saveUserId(valueOf);
                RegisterExistingUserRes registerExistingUserRes = new RegisterExistingUserRes(response.code());
                if (response.body().getData() != null && response.body().getData().getAppTrackerId() != null) {
                    registerExistingUserRes.setAppTrackerId(response.body().getData().getAppTrackerId());
                }
                this.f6287a.onSuccess(registerExistingUserRes);
                return;
            }
            int code = response.code();
            this.f6287a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class w implements Callback<SRegisterNewUserModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6288a;

        public w(CoveApiListener coveApiListener) {
            this.f6288a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRegisterNewUserModel> call, Throwable th) {
            this.f6288a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRegisterNewUserModel> call, Response<SRegisterNewUserModel> response) {
            if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                String str = response.headers().get(CoveApiHeaderConstants.HTTP_HEADER_SESSION_ID);
                Integer valueOf = Integer.valueOf(response.body().getData().getId());
                PreferenceManager.getInstance().saveSessionId(str);
                PreferenceManager.getInstance().saveUserId(valueOf);
                RegisterNewUserRes registerNewUserRes = new RegisterNewUserRes(response.code());
                try {
                    registerNewUserRes.setUserId(response.body().getData().getId());
                    registerNewUserRes.setDpUrl(response.body().getData().getDpUrl());
                    if (response.body().getData() != null && response.body().getData().getAppTrackerId() != null) {
                        registerNewUserRes.setAppTrackerId(response.body().getData().getAppTrackerId());
                    }
                    if (response.body().getData() != null && response.body().getData().getAxTrackerId() != null) {
                        registerNewUserRes.setAxTrackerId(response.body().getData().getAxTrackerId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.f6288a.onSuccess(registerNewUserRes);
                return;
            }
            int code = response.code();
            this.f6288a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class x implements Callback<SUpdateUserModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6289a;

        public x(CoveApiListener coveApiListener) {
            this.f6289a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SUpdateUserModel> call, Throwable th) {
            this.f6289a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SUpdateUserModel> call, Response<SUpdateUserModel> response) {
            if (response.isSuccessful()) {
                this.f6289a.onSuccess(new UpdateUserRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6289a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<UpdateLocationApiRes, CoveApiErrorModel> coveApiListener, SUpdateLocationApiReq sUpdateLocationApiReq, Map<String, String> map) {
        CoveApi.getService().updateUserLocation(map, sUpdateLocationApiReq).enqueue(new h(coveApiListener));
    }

    public static void acceptTermsAndConditions(@NonNull AcceptLegalTermsReq acceptLegalTermsReq, @NonNull CoveApiListener<AcceptLegalTermsRes, CoveApiErrorModel> coveApiListener) {
        e(acceptLegalTermsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void b(CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        CoveApi.getService().getLegalAcceptedDocs(hashMap, str).enqueue(new l(coveApiListener));
    }

    public static void c(CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2) {
        CoveApi.getService().legalFileDownload(hashMap, str, str2).enqueue(new j(coveApiListener));
    }

    public static void d(CoveApiListener<UserRemoteConfigResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getUserRemoteConfig(map).enqueue(new p(coveApiListener));
    }

    public static void deleteSession(CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        deleteUserSession(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void deleteUserSession(HashMap<String, String> hashMap, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().deleteUserSession(hashMap).enqueue(new n(coveApiListener));
    }

    public static void e(@NonNull AcceptLegalTermsReq acceptLegalTermsReq, CoveApiListener<AcceptLegalTermsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getTermsAndConditionsAcceptance(map, acceptLegalTermsReq).enqueue(new i(coveApiListener));
    }

    public static void f(FCMRegistrationReq fCMRegistrationReq, CoveApiListener<FCMRegistrationRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        if (fCMRegistrationReq != null && fCMRegistrationReq.getUserId() != null && fCMRegistrationReq.getGcmRegKey() != null) {
            CoveApi.getService().uploadRegIdToServer(hashMap, fCMRegistrationReq.getUserId(), fCMRegistrationReq).enqueue(new b(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void g(ModifyPhoneNumberReq modifyPhoneNumberReq, CoveApiListener<ModifyPhoneNumberRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().updateUserMobileNumber(map, new SModifyPhoneNumberReq(modifyPhoneNumberReq.getUserId(), modifyPhoneNumberReq.getMobileNumber(), modifyPhoneNumberReq.getVerificationCode())).enqueue(new e(coveApiListener));
    }

    public static void generateAuthOtp(OTPAuthReq oTPAuthReq, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().generateAuthOtp(CoveApi.getCustomHeaders(), oTPAuthReq).enqueue(new r(coveApiListener));
    }

    public static void getAcceptedLegalDoc(CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel> coveApiListener, String str) {
        b(coveApiListener, CoveApi.getCustomHeaders(), str);
    }

    public static void getGuestUserSessionId(FCMTokenDataReq fCMTokenDataReq, CoveApiListener<GuestUserSessionDataRes, CoveApiErrorModel> coveApiListener) {
        loginByGuestUser(fCMTokenDataReq, CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getOtpServices(String str, CoveApiListener<GetOTPServicesRes, CoveApiErrorModel> coveApiListener) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobileNumber", str);
        CoveApi.getService().getAvailableOtpServices(CoveApi.getCustomHeaders(), jsonObject).enqueue(new q(coveApiListener));
    }

    public static void getRemoteConfiguration(String str, CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener) {
        l(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getUserByPhoneNumber(UserByPhoneNumberReq userByPhoneNumberReq, CoveApiListener<UserByPhoneNumberRes, CoveApiErrorModel> coveApiListener) {
        if (userByPhoneNumberReq != null && userByPhoneNumberReq.getPhoneNumber() != null && userByPhoneNumberReq.getOtp() != null) {
            try {
                String trim = Base64.encodeToString((userByPhoneNumberReq.getPhoneNumber() + ":" + userByPhoneNumberReq.getOtp()).getBytes("UTF-8"), 0).trim();
                PreferenceManager.getInstance().saveAuthToken(trim);
                HashMap hashMap = new HashMap(CoveApi.getCustomHeaders());
                hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, trim);
                CoveApi.getService().getUserByPhoneNumber(hashMap, userByPhoneNumberReq.getPhoneNumber()).enqueue(new u(userByPhoneNumberReq, coveApiListener));
                return;
            } catch (UnsupportedEncodingException unused) {
                throw new ApiException(ErrorConstants.API_PARAM_ERR_INTERNAL);
            }
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void getUserRemoteConfiguration(CoveApiListener<UserRemoteConfigResponse, CoveApiErrorModel> coveApiListener) {
        d(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getV2RemoteConfiguration(String str, CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener) {
        n(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void h(@NonNull PostReportIssueReq postReportIssueReq, @NonNull CoveApiListener<ReportIssueRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SPostReportIssue sPostReportIssue = new SPostReportIssue();
        sPostReportIssue.setPhoneNo(postReportIssueReq.getPhoneNo());
        sPostReportIssue.setFeedback(postReportIssueReq.getFeedback());
        sPostReportIssue.setEmailId(postReportIssueReq.getEmailId());
        CoveApi.getService().reportIssue(map, sPostReportIssue).enqueue(new f(coveApiListener));
    }

    public static void i(RemoveUserReq removeUserReq, CoveApiListener<RemoveUserRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        if (removeUserReq != null && removeUserReq.getUserId() != null && removeUserReq.getMobileNumber() != null) {
            CoveApi.getService().removeUser(hashMap).enqueue(new a(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void j(UpdateProfilePictureReq updateProfilePictureReq, CoveApiListener<UpdateProfilePictureRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().updateDp(hashMap, MultipartBody.Part.createFormData("profilePicture", updateProfilePictureReq.getProfileImageFile().getName(), RequestBody.create(MediaType.parse(com.crrepa.r.a.d), updateProfilePictureReq.getProfileImageFile()))).enqueue(new c(coveApiListener));
    }

    public static void k(UpdateUserReq updateUserReq, CoveApiListener<UpdateUserRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        if (updateUserReq != null && updateUserReq.getUserId() != null) {
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(updateUserReq);
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(updateUserReq.getEmailId())) {
                arrayList.remove("emailId");
            } else {
                arrayList.add("emailId");
            }
            if (!TextUtils.isEmpty(updateUserReq.getGender())) {
                arrayList.remove("gender");
            } else {
                arrayList.add("gender");
            }
            if (!TextUtils.isEmpty(updateUserReq.getBirthDate())) {
                arrayList.remove("birthDate");
            } else {
                arrayList.add("birthDate");
            }
            if (!TextUtils.isEmpty(updateUserReq.getName())) {
                arrayList.remove(AppMeasurementSdk.ConditionalUserProperty.NAME);
            } else {
                arrayList.add(AppMeasurementSdk.ConditionalUserProperty.NAME);
            }
            updateUserReq.set_unset(arrayList);
            CoveApi.getService().updateUser(hashMap, updateUserReq).enqueue(new x(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void l(String str, CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        Call<SRemoteConfigResponse> remoteConfig = CoveApi.getService().getRemoteConfig(map);
        if (!AppUtils.isEmpty(str)) {
            remoteConfig = CoveApi.getService().getRemoteConfig(map, str);
        }
        remoteConfig.enqueue(new g(coveApiListener));
    }

    public static void legalFileDownload(CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener, String str, String str2) {
        c(coveApiListener, CoveApi.getCustomHeaders(), str, str2);
    }

    public static void loginByGuestUser(FCMTokenDataReq fCMTokenDataReq, HashMap<String, String> hashMap, CoveApiListener<GuestUserSessionDataRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().postGuestUserSession(hashMap, fCMTokenDataReq).enqueue(new m(coveApiListener));
    }

    public static void m(CoveApiListener<SRemoveProfilePicture, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().removeProfilePicture(map).enqueue(new d(coveApiListener));
    }

    public static void modifyPhoneNumber(ModifyPhoneNumberReq modifyPhoneNumberReq, CoveApiListener<ModifyPhoneNumberRes, CoveApiErrorModel> coveApiListener) {
        try {
            PreferenceManager.getInstance().saveAuthToken(Base64.encodeToString((modifyPhoneNumberReq.getMobileNumber() + ":" + modifyPhoneNumberReq.getVerificationCode()).getBytes("UTF-8"), 0).trim());
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        g(modifyPhoneNumberReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void n(String str, CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        Call<SRemoteConfigResponse> remoteConfigV2 = CoveApi.getService().getRemoteConfigV2(map, str);
        if (!AppUtils.isEmpty(str)) {
            remoteConfigV2 = CoveApi.getService().getRemoteConfigV2(map, str);
        }
        remoteConfigV2.enqueue(new o(coveApiListener));
    }

    public static void postReportIssue(@NonNull PostReportIssueReq postReportIssueReq, @NonNull CoveApiListener<ReportIssueRes, CoveApiErrorModel> coveApiListener) {
        h(postReportIssueReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void registerExistingUser(RegisterExistingUserReq registerExistingUserReq, CoveApiListener<RegisterExistingUserRes, CoveApiErrorModel> coveApiListener) {
        if (registerExistingUserReq != null && registerExistingUserReq.getUserId() != null) {
            RequestBody create = RequestBody.create(MediaType.parse("text/plain"), new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(registerExistingUserReq));
            HashMap hashMap = new HashMap(CoveApi.getCustomHeaders());
            hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, PreferenceManager.getInstance().getAuthToken());
            Call<SRegisterReturningUserModel> registerExistingUser = CoveApi.getService().registerExistingUser(hashMap, registerExistingUserReq.getUserId(), create);
            if (registerExistingUserReq.getDpFile() != null) {
                registerExistingUser = CoveApi.getService().registerExistingUser(hashMap, registerExistingUserReq.getUserId(), create, MultipartBody.Part.createFormData("profilePicture", registerExistingUserReq.getDpFile().getName(), RequestBody.create(MediaType.parse(com.crrepa.r.a.d), registerExistingUserReq.getDpFile())));
            }
            registerExistingUser.enqueue(new v(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void registerNewUser(RegisterNewUserReq registerNewUserReq, CoveApiListener<RegisterNewUserRes, CoveApiErrorModel> coveApiListener) {
        if (registerNewUserReq != null && registerNewUserReq.getMobileNumber() != null) {
            RequestBody create = RequestBody.create(MediaType.parse("text/plain"), new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(registerNewUserReq));
            MultipartBody.Part createFormData = MultipartBody.Part.createFormData("profilePicture", registerNewUserReq.getDpFile().getName(), RequestBody.create(MediaType.parse(com.crrepa.r.a.d), registerNewUserReq.getDpFile()));
            HashMap hashMap = new HashMap(CoveApi.getCustomHeaders());
            hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, PreferenceManager.getInstance().getAuthToken());
            for (Map.Entry entry : hashMap.entrySet()) {
                PrintStream printStream = System.out;
                printStream.println(((String) entry.getKey()) + MqttTopic.TOPIC_LEVEL_SEPARATOR + ((String) entry.getValue()));
            }
            CoveApi.getService().registerNewUser(hashMap, create, createFormData).enqueue(new w(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void removeProfilePicture(CoveApiListener<SRemoveProfilePicture, CoveApiErrorModel> coveApiListener) {
        m(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void removeUser(RemoveUserReq removeUserReq, CoveApiListener<RemoveUserRes, CoveApiErrorModel> coveApiListener) {
        HashMap hashMap = new HashMap(CoveApi.getCustomHeaders());
        hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, PreferenceManager.getInstance().getAuthToken());
        hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_CLOVENET_ID, removeUserReq.getUserId());
        hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_MOBILE_NUMBER, removeUserReq.getMobileNumber());
        i(removeUserReq, coveApiListener, hashMap);
    }

    public static void saveUserLocation(UpdateLocationApiReq updateLocationApiReq, CoveApiListener<UpdateLocationApiRes, CoveApiErrorModel> coveApiListener) {
        SUpdateLocationApiReq sUpdateLocationApiReq = new SUpdateLocationApiReq();
        sUpdateLocationApiReq.setUserID(PreferenceManager.getInstance().getUserId().toString());
        sUpdateLocationApiReq.setLatitude(updateLocationApiReq.getLatitude() + "");
        sUpdateLocationApiReq.setLongitude(updateLocationApiReq.getLongitude() + "");
        sUpdateLocationApiReq.setPanicCode(updateLocationApiReq.getPanicCode());
        a(coveApiListener, sUpdateLocationApiReq, CoveApi.getCustomHeaders());
    }

    public static void sendUserRegistrationTokenToServer(FCMRegistrationReq fCMRegistrationReq, CoveApiListener<FCMRegistrationRes, CoveApiErrorModel> coveApiListener) {
        f(fCMRegistrationReq, coveApiListener, new HashMap(CoveApi.getCustomHeaders()));
    }

    public static void updateProfilePicture(UpdateProfilePictureReq updateProfilePictureReq, CoveApiListener<UpdateProfilePictureRes, CoveApiErrorModel> coveApiListener) {
        j(updateProfilePictureReq, coveApiListener, new HashMap(CoveApi.getCustomHeaders()));
    }

    public static void updateUser(UpdateUserReq updateUserReq, CoveApiListener<UpdateUserRes, CoveApiErrorModel> coveApiListener) {
        HashMap hashMap = new HashMap(CoveApi.getCustomHeaders());
        hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, PreferenceManager.getInstance().getAuthToken());
        hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_SESSION_ID, PreferenceManager.getInstance().getSessionId());
        k(updateUserReq, coveApiListener, hashMap);
    }

    public static void verifyPhoneNumber(PhoneNumberVerificationReq phoneNumberVerificationReq, CoveApiListener<PhoneNumberVerificationRes, CoveApiErrorModel> coveApiListener) {
        if (phoneNumberVerificationReq != null && phoneNumberVerificationReq.getPhoneNumber() != null) {
            CoveApi.getService().verifyPhoneNumber(CoveApi.getCustomHeaders(), phoneNumberVerificationReq.getPhoneNumber()).enqueue(new k(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void verifyPhoneNumberWithCustomeOtpLenth(PhoneNumberVerificationReq phoneNumberVerificationReq, int i2, CoveApiListener<PhoneNumberVerificationRes, CoveApiErrorModel> coveApiListener) {
        if (phoneNumberVerificationReq != null && phoneNumberVerificationReq.getPhoneNumber() != null) {
            JsonObject jsonObject = new JsonObject();
            try {
                jsonObject.addProperty("mobileNumber", phoneNumberVerificationReq.getPhoneNumber());
                jsonObject.addProperty("otpLength", Integer.valueOf(i2));
                if (phoneNumberVerificationReq.getRecaptchaToken() != null) {
                    jsonObject.addProperty("recaptchaAction", phoneNumberVerificationReq.getRecaptchaAction());
                    jsonObject.addProperty("recaptchaToken", phoneNumberVerificationReq.getRecaptchaToken());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (phoneNumberVerificationReq.getRecaptchaToken() == null) {
                CoveApi.getService().verifyPhoneNumberCustomeOtpLength(CoveApi.getCustomHeaders(), jsonObject).enqueue(new s(coveApiListener));
                return;
            } else {
                CoveApi.getService().verifyPhoneNumberCustomeOtpLengthV2(CoveApi.getCustomHeaders(), jsonObject).enqueue(new t(coveApiListener));
                return;
            }
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void deleteSession(HashMap<String, String> hashMap, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        deleteUserSession(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getAcceptedLegalDoc(CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str);
    }

    public static void getGuestUserSessionId(FCMTokenDataReq fCMTokenDataReq, HashMap<String, String> hashMap, CoveApiListener<GuestUserSessionDataRes, CoveApiErrorModel> coveApiListener) {
        loginByGuestUser(fCMTokenDataReq, CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getUserRemoteConfiguration(CoveApiListener<UserRemoteConfigResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        d(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getV2RemoteConfiguration(String str, CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        n(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void legalFileDownload(CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2) {
        c(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str, str2);
    }

    public static void acceptTermsAndConditions(HashMap<String, String> hashMap, @NonNull AcceptLegalTermsReq acceptLegalTermsReq, @NonNull CoveApiListener<AcceptLegalTermsRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        e(acceptLegalTermsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getRemoteConfiguration(CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener) {
        l(null, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void postReportIssue(HashMap<String, String> hashMap, @NonNull PostReportIssueReq postReportIssueReq, @NonNull CoveApiListener<ReportIssueRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        h(postReportIssueReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void removeProfilePicture(HashMap<String, String> hashMap, CoveApiListener<SRemoveProfilePicture, CoveApiErrorModel> coveApiListener) {
        m(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendUserRegistrationTokenToServer(HashMap<String, String> hashMap, FCMRegistrationReq fCMRegistrationReq, CoveApiListener<FCMRegistrationRes, CoveApiErrorModel> coveApiListener) {
        f(fCMRegistrationReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateProfilePicture(HashMap<String, String> hashMap, UpdateProfilePictureReq updateProfilePictureReq, CoveApiListener<UpdateProfilePictureRes, CoveApiErrorModel> coveApiListener) {
        j(updateProfilePictureReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getRemoteConfiguration(String str, HashMap<String, String> hashMap, CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener) {
        l(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getRemoteConfiguration(HashMap<String, String> hashMap, CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel> coveApiListener) {
        l(null, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void modifyPhoneNumber(HashMap<String, String> hashMap, ModifyPhoneNumberReq modifyPhoneNumberReq, CoveApiListener<ModifyPhoneNumberRes, CoveApiErrorModel> coveApiListener) {
        g(modifyPhoneNumberReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateUser(HashMap<String, String> hashMap, UpdateUserReq updateUserReq, CoveApiListener<UpdateUserRes, CoveApiErrorModel> coveApiListener) {
        HashMap<String, String> revisedHeaders = CoveUtil.getRevisedHeaders(hashMap);
        revisedHeaders.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, PreferenceManager.getInstance().getAuthToken());
        revisedHeaders.put(CoveApiHeaderConstants.HTTP_HEADER_SESSION_ID, PreferenceManager.getInstance().getSessionId());
        k(updateUserReq, coveApiListener, revisedHeaders);
    }

    public static void saveUserLocation(HashMap<String, String> hashMap, UpdateLocationApiReq updateLocationApiReq, CoveApiListener<UpdateLocationApiRes, CoveApiErrorModel> coveApiListener) {
        SUpdateLocationApiReq sUpdateLocationApiReq = new SUpdateLocationApiReq();
        sUpdateLocationApiReq.setUserID(PreferenceManager.getInstance().getUserId().toString());
        sUpdateLocationApiReq.setLatitude(updateLocationApiReq.getLatitude() + "");
        sUpdateLocationApiReq.setLongitude(updateLocationApiReq.getLongitude() + "");
        sUpdateLocationApiReq.setPanicCode(updateLocationApiReq.getPanicCode());
        a(coveApiListener, sUpdateLocationApiReq, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void removeUser(HashMap<String, String> hashMap, RemoveUserReq removeUserReq, CoveApiListener<RemoveUserRes, CoveApiErrorModel> coveApiListener) {
        HashMap<String, String> revisedHeaders = CoveUtil.getRevisedHeaders(hashMap);
        revisedHeaders.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, PreferenceManager.getInstance().getAuthToken());
        revisedHeaders.put(CoveApiHeaderConstants.HTTP_HEADER_CLOVENET_ID, removeUserReq.getUserId());
        revisedHeaders.put(CoveApiHeaderConstants.HTTP_HEADER_MOBILE_NUMBER, removeUserReq.getMobileNumber());
        i(removeUserReq, coveApiListener, revisedHeaders);
    }
}
