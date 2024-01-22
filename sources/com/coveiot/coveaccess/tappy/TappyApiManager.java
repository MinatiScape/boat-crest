package com.coveiot.coveaccess.tappy;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveApiService;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.tappy.model.SAcceptTermsAndGenerateTokenRequest;
import com.coveiot.coveaccess.tappy.model.SAcceptTermsAndGenerateTokenResponse;
import com.coveiot.coveaccess.tappy.model.SApduCommand;
import com.coveiot.coveaccess.tappy.model.SConfirmProvisioningRequest;
import com.coveiot.coveaccess.tappy.model.SConfirmProvisioningResponse;
import com.coveiot.coveaccess.tappy.model.SDeletePaymentInstrumentRequest;
import com.coveiot.coveaccess.tappy.model.SDeletePaymentInstrumentResponse;
import com.coveiot.coveaccess.tappy.model.SDeletePaymentInstrumentTokensRequest;
import com.coveiot.coveaccess.tappy.model.SDeletePaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SDeleteRegisteredProductRequest;
import com.coveiot.coveaccess.tappy.model.SDeleteRegisteredProductResponse;
import com.coveiot.coveaccess.tappy.model.SDeleteUserRequest;
import com.coveiot.coveaccess.tappy.model.SDeleteUserResponse;
import com.coveiot.coveaccess.tappy.model.SDeviceInfo;
import com.coveiot.coveaccess.tappy.model.SEncryptionKey;
import com.coveiot.coveaccess.tappy.model.SErrorLogRequest;
import com.coveiot.coveaccess.tappy.model.SErrorLogResponse;
import com.coveiot.coveaccess.tappy.model.SGetAllProducts;
import com.coveiot.coveaccess.tappy.model.SGetAllRegisteredDeviceByUserRequest;
import com.coveiot.coveaccess.tappy.model.SGetEncryptionKeyRequest;
import com.coveiot.coveaccess.tappy.model.SGetPostPersoCommandsRequest;
import com.coveiot.coveaccess.tappy.model.SGetProductDetailsByProductId;
import com.coveiot.coveaccess.tappy.model.SGetProductDetailsBySerialNumber;
import com.coveiot.coveaccess.tappy.model.SGetStepUpOptions;
import com.coveiot.coveaccess.tappy.model.SGetTAndCRequest;
import com.coveiot.coveaccess.tappy.model.SGetTAndCResponse;
import com.coveiot.coveaccess.tappy.model.SGetTokenPersoScript;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsByTransactionIdRequest;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsByTransactionIdResponse;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsRequest;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsResponse;
import com.coveiot.coveaccess.tappy.model.SGetUserByEmailRequest;
import com.coveiot.coveaccess.tappy.model.SGetUserRegisteredPaymentInstrumentDetailsByUserId;
import com.coveiot.coveaccess.tappy.model.SGetUserRegisteredProductByUserIdAndPRegId;
import com.coveiot.coveaccess.tappy.model.SGetUserRegisteredProductDetailsByUserId;
import com.coveiot.coveaccess.tappy.model.SInstallFoundationToSecureElementRequest;
import com.coveiot.coveaccess.tappy.model.SInstallFoundationToSecureElementResponse;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrument;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SProduct;
import com.coveiot.coveaccess.tappy.model.SPutPostPersoCommandsExecutedRequest;
import com.coveiot.coveaccess.tappy.model.SPutPostPersoCommandsExecutedResponse;
import com.coveiot.coveaccess.tappy.model.SPutStepUpOptionsRequest;
import com.coveiot.coveaccess.tappy.model.SPutStepUpOptionsResponse;
import com.coveiot.coveaccess.tappy.model.SRegisterDeviceRequest;
import com.coveiot.coveaccess.tappy.model.SRegisterProductRequest;
import com.coveiot.coveaccess.tappy.model.SRegisterUserRequest;
import com.coveiot.coveaccess.tappy.model.SRegisteredProduct;
import com.coveiot.coveaccess.tappy.model.SResumePaymentInstrumentTokensRequest;
import com.coveiot.coveaccess.tappy.model.SResumePaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SSECardPersoScript;
import com.coveiot.coveaccess.tappy.model.SSendPaymentInstrumentTokensRequest;
import com.coveiot.coveaccess.tappy.model.SStepUpRequest;
import com.coveiot.coveaccess.tappy.model.SSuspendPaymentInstrumentTokensRequest;
import com.coveiot.coveaccess.tappy.model.SSuspendPaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SUpdateRegisteredProductFriendlyNameRequest;
import com.coveiot.coveaccess.tappy.model.SUserDataResponse;
import com.coveiot.coveaccess.tappy.model.SUserDataResponseNew;
import com.coveiot.coveaccess.tappy.model.SValidateOTPRequest;
import com.coveiot.coveaccess.tappy.model.SValidateOTPResponse;
import com.coveiot.coveaccess.tappy.model.TappyCoveApiErrorModel;
import com.coveiot.coveaccess.tappy.model.TappyErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class TappyApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6758a;

        public a(CoveApiListener coveApiListener) {
            this.f6758a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            this.f6758a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful()) {
                this.f6758a.onSuccess(null);
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6758a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6758a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class a0 implements Callback<SRegisteredProduct> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6759a;

        public a0(CoveApiListener coveApiListener) {
            this.f6759a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRegisteredProduct> call, Throwable th) {
            this.f6759a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRegisteredProduct> call, Response<SRegisteredProduct> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6759a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6759a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6759a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SInstallFoundationToSecureElementResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6760a;

        public b(CoveApiListener coveApiListener) {
            this.f6760a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SInstallFoundationToSecureElementResponse> call, Throwable th) {
            this.f6760a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SInstallFoundationToSecureElementResponse> call, Response<SInstallFoundationToSecureElementResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6760a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6760a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6760a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b0 implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6761a;

        public b0(CoveApiListener coveApiListener) {
            this.f6761a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            this.f6761a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful()) {
                this.f6761a.onSuccess(null);
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6761a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6761a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<List<SPaymentInstrument>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6762a;

        public c(CoveApiListener coveApiListener) {
            this.f6762a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<List<SPaymentInstrument>> call, Throwable th) {
            this.f6762a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<List<SPaymentInstrument>> call, Response<List<SPaymentInstrument>> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6762a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6762a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6762a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c0 implements Callback<SGetTransactionDetailsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6763a;

        public c0(CoveApiListener coveApiListener) {
            this.f6763a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetTransactionDetailsResponse> call, Throwable th) {
            this.f6763a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetTransactionDetailsResponse> call, Response<SGetTransactionDetailsResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6763a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6763a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6763a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<List<SRegisteredProduct>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6764a;

        public d(CoveApiListener coveApiListener) {
            this.f6764a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<List<SRegisteredProduct>> call, Throwable th) {
            this.f6764a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<List<SRegisteredProduct>> call, Response<List<SRegisteredProduct>> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6764a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6764a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6764a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d0 implements Callback<SGetTransactionDetailsByTransactionIdResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6765a;

        public d0(CoveApiListener coveApiListener) {
            this.f6765a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetTransactionDetailsByTransactionIdResponse> call, Throwable th) {
            this.f6765a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetTransactionDetailsByTransactionIdResponse> call, Response<SGetTransactionDetailsByTransactionIdResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6765a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6765a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6765a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<CommonResponseClass> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6766a;

        public e(CoveApiListener coveApiListener) {
            this.f6766a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6766a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6766a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6766a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6766a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class e0 implements Callback<SSuspendPaymentInstrumentTokensResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6767a;

        public e0(CoveApiListener coveApiListener) {
            this.f6767a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SSuspendPaymentInstrumentTokensResponse> call, Throwable th) {
            this.f6767a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SSuspendPaymentInstrumentTokensResponse> call, Response<SSuspendPaymentInstrumentTokensResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6767a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6767a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6767a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<SPaymentInstrumentTokensResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6768a;

        public f(CoveApiListener coveApiListener) {
            this.f6768a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPaymentInstrumentTokensResponse> call, Throwable th) {
            this.f6768a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPaymentInstrumentTokensResponse> call, Response<SPaymentInstrumentTokensResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6768a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6768a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6768a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class f0 implements Callback<CommonResponseGeneric<SUserDataResponseNew>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6769a;

        public f0(CoveApiListener coveApiListener) {
            this.f6769a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<SUserDataResponseNew>> call, Throwable th) {
            this.f6769a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<SUserDataResponseNew>> call, Response<CommonResponseGeneric<SUserDataResponseNew>> response) {
            TappyErrorModel tappyErrorModel;
            int code = response.code();
            if (response.isSuccessful() && response.body() != null) {
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6769a.onSuccess(response.body().getData());
                    return;
                } else {
                    this.f6769a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                    return;
                }
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6769a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6769a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<SUserDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6770a;

        public g(CoveApiListener coveApiListener) {
            this.f6770a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SUserDataResponse> call, Throwable th) {
            this.f6770a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SUserDataResponse> call, Response<SUserDataResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6770a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6770a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6770a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class g0 implements Callback<SResumePaymentInstrumentTokensResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6771a;

        public g0(CoveApiListener coveApiListener) {
            this.f6771a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SResumePaymentInstrumentTokensResponse> call, Throwable th) {
            this.f6771a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SResumePaymentInstrumentTokensResponse> call, Response<SResumePaymentInstrumentTokensResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6771a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6771a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6771a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<SAcceptTermsAndGenerateTokenResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6772a;

        public h(CoveApiListener coveApiListener) {
            this.f6772a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SAcceptTermsAndGenerateTokenResponse> call, Throwable th) {
            this.f6772a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SAcceptTermsAndGenerateTokenResponse> call, Response<SAcceptTermsAndGenerateTokenResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6772a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6772a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6772a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class h0 implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6773a;

        public h0(CoveApiListener coveApiListener) {
            this.f6773a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            this.f6773a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful()) {
                this.f6773a.onSuccess(null);
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6773a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6773a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<SUserDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6774a;

        public i(CoveApiListener coveApiListener) {
            this.f6774a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SUserDataResponse> call, Throwable th) {
            this.f6774a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SUserDataResponse> call, Response<SUserDataResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6774a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6774a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6774a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class i0 implements Callback<SGetTAndCResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6775a;

        public i0(CoveApiListener coveApiListener) {
            this.f6775a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetTAndCResponse> call, Throwable th) {
            this.f6775a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetTAndCResponse> call, Response<SGetTAndCResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6775a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6775a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6775a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<SEncryptionKey> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6776a;

        public j(CoveApiListener coveApiListener) {
            this.f6776a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SEncryptionKey> call, Throwable th) {
            this.f6776a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SEncryptionKey> call, Response<SEncryptionKey> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6776a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6776a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6776a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class j0 implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6777a;

        public j0(CoveApiListener coveApiListener) {
            this.f6777a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            this.f6777a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful()) {
                this.f6777a.onSuccess(null);
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6777a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6777a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class k implements Callback<SRegisteredProduct> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6778a;

        public k(CoveApiListener coveApiListener) {
            this.f6778a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRegisteredProduct> call, Throwable th) {
            this.f6778a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRegisteredProduct> call, Response<SRegisteredProduct> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6778a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6778a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6778a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class l implements Callback<SSECardPersoScript> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6779a;

        public l(CoveApiListener coveApiListener) {
            this.f6779a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SSECardPersoScript> call, Throwable th) {
            this.f6779a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SSECardPersoScript> call, Response<SSECardPersoScript> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6779a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6779a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6779a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class m implements Callback<List<SProduct>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6780a;

        public m(CoveApiListener coveApiListener) {
            this.f6780a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<List<SProduct>> call, Throwable th) {
            this.f6780a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<List<SProduct>> call, Response<List<SProduct>> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6780a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6780a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6780a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class n implements Callback<SConfirmProvisioningResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6781a;

        public n(CoveApiListener coveApiListener) {
            this.f6781a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SConfirmProvisioningResponse> call, Throwable th) {
            this.f6781a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SConfirmProvisioningResponse> call, Response<SConfirmProvisioningResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6781a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6781a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6781a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class o implements Callback<SProduct> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6782a;

        public o(CoveApiListener coveApiListener) {
            this.f6782a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SProduct> call, Throwable th) {
            this.f6782a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SProduct> call, Response<SProduct> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6782a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6782a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6782a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class p implements Callback<SDeletePaymentInstrumentTokensResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6783a;

        public p(CoveApiListener coveApiListener) {
            this.f6783a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SDeletePaymentInstrumentTokensResponse> call, Throwable th) {
            this.f6783a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SDeletePaymentInstrumentTokensResponse> call, Response<SDeletePaymentInstrumentTokensResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6783a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6783a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6783a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class q implements Callback<SProduct> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6784a;

        public q(CoveApiListener coveApiListener) {
            this.f6784a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SProduct> call, Throwable th) {
            this.f6784a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SProduct> call, Response<SProduct> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6784a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6784a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6784a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class r implements Callback<List<SApduCommand>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6785a;

        public r(CoveApiListener coveApiListener) {
            this.f6785a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<List<SApduCommand>> call, Throwable th) {
            this.f6785a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<List<SApduCommand>> call, Response<List<SApduCommand>> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6785a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6785a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6785a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class s implements Callback<SRegisteredProduct> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6786a;

        public s(CoveApiListener coveApiListener) {
            this.f6786a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SRegisteredProduct> call, Throwable th) {
            this.f6786a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SRegisteredProduct> call, Response<SRegisteredProduct> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6786a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6786a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6786a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class t implements Callback<SPutPostPersoCommandsExecutedResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6787a;

        public t(CoveApiListener coveApiListener) {
            this.f6787a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPutPostPersoCommandsExecutedResponse> call, Throwable th) {
            this.f6787a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPutPostPersoCommandsExecutedResponse> call, Response<SPutPostPersoCommandsExecutedResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6787a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6787a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6787a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class u implements Callback<SUserDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6788a;

        public u(CoveApiListener coveApiListener) {
            this.f6788a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SUserDataResponse> call, Throwable th) {
            this.f6788a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SUserDataResponse> call, Response<SUserDataResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6788a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6788a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6788a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class v implements Callback<List<SStepUpRequest>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6789a;

        public v(CoveApiListener coveApiListener) {
            this.f6789a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<List<SStepUpRequest>> call, Throwable th) {
            this.f6789a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<List<SStepUpRequest>> call, Response<List<SStepUpRequest>> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6789a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6789a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6789a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class w implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6790a;

        public w(CoveApiListener coveApiListener) {
            this.f6790a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            this.f6790a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful()) {
                this.f6790a.onSuccess(null);
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6790a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6790a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class x implements Callback<SValidateOTPResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6791a;

        public x(CoveApiListener coveApiListener) {
            this.f6791a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SValidateOTPResponse> call, Throwable th) {
            this.f6791a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SValidateOTPResponse> call, Response<SValidateOTPResponse> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6791a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6791a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6791a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class y implements Callback<List<SDeviceInfo>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6792a;

        public y(CoveApiListener coveApiListener) {
            this.f6792a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<List<SDeviceInfo>> call, Throwable th) {
            this.f6792a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<List<SDeviceInfo>> call, Response<List<SDeviceInfo>> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6792a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6792a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6792a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class z implements Callback<SDeviceInfo> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6793a;

        public z(CoveApiListener coveApiListener) {
            this.f6793a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SDeviceInfo> call, Throwable th) {
            this.f6793a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SDeviceInfo> call, Response<SDeviceInfo> response) {
            TappyErrorModel tappyErrorModel;
            if (response.isSuccessful() && response.body() != null) {
                this.f6793a.onSuccess(response.body());
                return;
            }
            try {
                tappyErrorModel = (TappyErrorModel) new Gson().fromJson(response.errorBody().string(), (Class<Object>) TappyErrorModel.class);
            } catch (Exception e) {
                e.printStackTrace();
                tappyErrorModel = null;
            }
            if (tappyErrorModel == null) {
                this.f6793a.onError(new TappyCoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code(), null, null));
            } else {
                this.f6793a.onError(new TappyCoveApiErrorModel(tappyErrorModel.getMessage(), tappyErrorModel.getHttpStatusCode() == null ? 0 : tappyErrorModel.getHttpStatusCode().intValue(), tappyErrorModel.getCode(), tappyErrorModel.getGuid()));
            }
        }
    }

    public static void A(HashMap<String, String> hashMap, SRegisterDeviceRequest sRegisterDeviceRequest, CoveApiListener<SDeviceInfo, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.registerNewTappyDevice(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Devices/" + sRegisterDeviceRequest.getEndUserId(), sRegisterDeviceRequest.getDeviceInfo()).enqueue(new z(coveApiListener));
    }

    public static void B(HashMap<String, String> hashMap, SRegisterProductRequest sRegisterProductRequest, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.registerNewTappyProduct(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/RegisteredProducts/" + sRegisterProductRequest.getEndUserId(), sRegisterProductRequest).enqueue(new k(coveApiListener));
    }

    public static void C(HashMap<String, String> hashMap, SRegisterUserRequest sRegisterUserRequest, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.registerNewTappyUser(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Users", sRegisterUserRequest).enqueue(new u(coveApiListener));
    }

    public static void D(HashMap<String, String> hashMap, SResumePaymentInstrumentTokensRequest sResumePaymentInstrumentTokensRequest, CoveApiListener<SResumePaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.resumePaymentInstrumentToken(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sResumePaymentInstrumentTokensRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sResumePaymentInstrumentTokensRequest.getPaymentInstrumentTokenId() + "/Resume", sResumePaymentInstrumentTokensRequest).enqueue(new g0(coveApiListener));
    }

    public static void E(HashMap<String, String> hashMap, SSendPaymentInstrumentTokensRequest sSendPaymentInstrumentTokensRequest, CoveApiListener<SPaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.sendPaymentInstrumentTokenByUserId(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sSendPaymentInstrumentTokensRequest.getEndUserId(), sSendPaymentInstrumentTokensRequest).enqueue(new f(coveApiListener));
    }

    public static void F(HashMap<String, String> hashMap, SSuspendPaymentInstrumentTokensRequest sSuspendPaymentInstrumentTokensRequest, CoveApiListener<SSuspendPaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.suspendPaymentInstrumentToken(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sSuspendPaymentInstrumentTokensRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sSuspendPaymentInstrumentTokensRequest.getPaymentInstrumentTokenId() + "/Suspend", sSuspendPaymentInstrumentTokensRequest).enqueue(new e0(coveApiListener));
    }

    public static void G(HashMap<String, String> hashMap, SUpdateRegisteredProductFriendlyNameRequest sUpdateRegisteredProductFriendlyNameRequest, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.updateRegisteredProductFriendlyName(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/RegisteredProducts/" + sUpdateRegisteredProductFriendlyNameRequest.getUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sUpdateRegisteredProductFriendlyNameRequest.getProductRegistrationId(), sUpdateRegisteredProductFriendlyNameRequest).enqueue(new a0(coveApiListener));
    }

    public static void H(HashMap<String, String> hashMap, SValidateOTPRequest sValidateOTPRequest, CoveApiListener<SValidateOTPResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.validateOtp(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sValidateOTPRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sValidateOTPRequest.getPaymentInstrumentTokenId() + "/StepUpOptions/ValidateOTP", sValidateOTPRequest).enqueue(new x(coveApiListener));
    }

    public static void I(HashMap<String, String> hashMap, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyUserInfo(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "UserDetails").enqueue(new i(coveApiListener));
    }

    public static void J(HashMap<String, String> hashMap, CoveApiListener<SUserDataResponseNew, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.registerNewTappyUserNew(hashMap, CoveApi.getInstance().getTappyApiUrl() + "v1/user").enqueue(new f0(coveApiListener));
    }

    public static void a(HashMap<String, String> hashMap, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.deleteTappyUserNew(hashMap, CoveApi.getInstance().getTappyApiUrl() + "v1/user").enqueue(new e(coveApiListener));
    }

    public static void acceptTermsAndGenerateToken(SAcceptTermsAndGenerateTokenRequest sAcceptTermsAndGenerateTokenRequest, CoveApiListener<SAcceptTermsAndGenerateTokenResponse, CoveApiErrorModel> coveApiListener) {
        b(CoveApi.getCustomHeaders(), sAcceptTermsAndGenerateTokenRequest, coveApiListener);
    }

    public static void b(HashMap<String, String> hashMap, SAcceptTermsAndGenerateTokenRequest sAcceptTermsAndGenerateTokenRequest, CoveApiListener<SAcceptTermsAndGenerateTokenResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.acceptTermsAndGenerateToken(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sAcceptTermsAndGenerateTokenRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sAcceptTermsAndGenerateTokenRequest.getId() + "/AcceptTermsAndGenerateToken", sAcceptTermsAndGenerateTokenRequest).enqueue(new h(coveApiListener));
    }

    public static void c(HashMap<String, String> hashMap, SConfirmProvisioningRequest sConfirmProvisioningRequest, CoveApiListener<SConfirmProvisioningResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.confirmProvisioning(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sConfirmProvisioningRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sConfirmProvisioningRequest.getPaymentInstrumentTokenId() + "/ConfirmProvisioning", sConfirmProvisioningRequest).enqueue(new n(coveApiListener));
    }

    public static void confirmProvisioning(SConfirmProvisioningRequest sConfirmProvisioningRequest, CoveApiListener<SConfirmProvisioningResponse, CoveApiErrorModel> coveApiListener) {
        c(CoveApi.getCustomHeaders(), sConfirmProvisioningRequest, coveApiListener);
    }

    public static void d(HashMap<String, String> hashMap, SDeletePaymentInstrumentRequest sDeletePaymentInstrumentRequest, CoveApiListener<SDeletePaymentInstrumentResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.deletePaymentInstrument(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstruments/" + sDeletePaymentInstrumentRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sDeletePaymentInstrumentRequest.getPaymentInstrumentId(), sDeletePaymentInstrumentRequest.isDeleteTokens()).enqueue(new j0(coveApiListener));
    }

    public static void deleteEndUser(SDeleteUserRequest sDeleteUserRequest, CoveApiListener<SDeleteUserResponse, CoveApiErrorModel> coveApiListener) {
        g(CoveApi.getCustomHeaders(), sDeleteUserRequest, coveApiListener);
    }

    public static void deletePaymentInstrument(SDeletePaymentInstrumentRequest sDeletePaymentInstrumentRequest, CoveApiListener<SDeletePaymentInstrumentResponse, CoveApiErrorModel> coveApiListener) {
        d(CoveApi.getCustomHeaders(), sDeletePaymentInstrumentRequest, coveApiListener);
    }

    public static void deletePaymentInstrumentTokens(SDeletePaymentInstrumentTokensRequest sDeletePaymentInstrumentTokensRequest, CoveApiListener<SDeletePaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        e(CoveApi.getCustomHeaders(), sDeletePaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void deleteRegisteredProduct(SDeleteRegisteredProductRequest sDeleteRegisteredProductRequest, CoveApiListener<SDeleteRegisteredProductResponse, CoveApiErrorModel> coveApiListener) {
        f(CoveApi.getCustomHeaders(), sDeleteRegisteredProductRequest, coveApiListener);
    }

    public static void deleteTappyUserNew(CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        a(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void e(HashMap<String, String> hashMap, SDeletePaymentInstrumentTokensRequest sDeletePaymentInstrumentTokensRequest, CoveApiListener<SDeletePaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.deletePaymentInstrumentTokens(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sDeletePaymentInstrumentTokensRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sDeletePaymentInstrumentTokensRequest.getPaymentInstrumentTokenId(), sDeletePaymentInstrumentTokensRequest.getReason()).enqueue(new p(coveApiListener));
    }

    public static void f(HashMap<String, String> hashMap, SDeleteRegisteredProductRequest sDeleteRegisteredProductRequest, CoveApiListener<SDeleteRegisteredProductResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.deleteRegisteredProduct(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/RegisteredProducts/" + sDeleteRegisteredProductRequest.getUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sDeleteRegisteredProductRequest.getProductRegistrationId()).enqueue(new b0(coveApiListener));
    }

    public static void g(HashMap<String, String> hashMap, SDeleteUserRequest sDeleteUserRequest, CoveApiListener<SDeleteUserResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.deleteTappyUser(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Users/" + sDeleteUserRequest.getEndUserId()).enqueue(new a(coveApiListener));
    }

    public static void getAllProducts(SGetAllProducts sGetAllProducts, CoveApiListener<List<SProduct>, CoveApiErrorModel> coveApiListener) {
        i(CoveApi.getCustomHeaders(), sGetAllProducts, coveApiListener);
    }

    public static void getGetEncryptionKey(SGetEncryptionKeyRequest sGetEncryptionKeyRequest, CoveApiListener<SEncryptionKey, CoveApiErrorModel> coveApiListener) {
        k(CoveApi.getCustomHeaders(), sGetEncryptionKeyRequest, coveApiListener);
    }

    public static void getPaymentInstrumentByUserId(SGetUserRegisteredPaymentInstrumentDetailsByUserId sGetUserRegisteredPaymentInstrumentDetailsByUserId, CoveApiListener<List<SPaymentInstrument>, CoveApiErrorModel> coveApiListener) {
        u(CoveApi.getCustomHeaders(), sGetUserRegisteredPaymentInstrumentDetailsByUserId, coveApiListener);
    }

    public static void getPostPersoCommands(SGetPostPersoCommandsRequest sGetPostPersoCommandsRequest, CoveApiListener<List<SApduCommand>, CoveApiErrorModel> coveApiListener) {
        l(CoveApi.getCustomHeaders(), sGetPostPersoCommandsRequest, coveApiListener);
    }

    public static void getProductDetailsByProductId(SGetProductDetailsByProductId sGetProductDetailsByProductId, CoveApiListener<SProduct, CoveApiErrorModel> coveApiListener) {
        m(CoveApi.getCustomHeaders(), sGetProductDetailsByProductId, coveApiListener);
    }

    public static void getProductDetailsBySerialNumber(SGetProductDetailsBySerialNumber sGetProductDetailsBySerialNumber, CoveApiListener<SProduct, CoveApiErrorModel> coveApiListener) {
        n(CoveApi.getCustomHeaders(), sGetProductDetailsBySerialNumber, coveApiListener);
    }

    public static void getProductDetailsByUserIdAndProductId(SGetUserRegisteredProductByUserIdAndPRegId sGetUserRegisteredProductByUserIdAndPRegId, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        v(CoveApi.getCustomHeaders(), sGetUserRegisteredProductByUserIdAndPRegId, coveApiListener);
    }

    public static void getStepUpOptions(SGetStepUpOptions sGetStepUpOptions, CoveApiListener<List<SStepUpRequest>, CoveApiErrorModel> coveApiListener) {
        o(CoveApi.getCustomHeaders(), sGetStepUpOptions, coveApiListener);
    }

    public static void getTAndC(HashMap<String, String> hashMap, SGetTAndCRequest sGetTAndCRequest, CoveApiListener<SGetTAndCResponse, CoveApiErrorModel> coveApiListener) {
        p(CoveUtil.getRevisedHeaders(hashMap), sGetTAndCRequest, coveApiListener);
    }

    public static void getTokenPersoScript(SGetTokenPersoScript sGetTokenPersoScript, CoveApiListener<SSECardPersoScript, CoveApiErrorModel> coveApiListener) {
        q(CoveApi.getCustomHeaders(), sGetTokenPersoScript, coveApiListener);
    }

    public static void getTransactionDetails(SGetTransactionDetailsRequest sGetTransactionDetailsRequest, CoveApiListener<SGetTransactionDetailsResponse, CoveApiErrorModel> coveApiListener) {
        s(CoveApi.getCustomHeaders(), sGetTransactionDetailsRequest, coveApiListener);
    }

    public static void getTransactionDetailsByTransactionId(SGetTransactionDetailsByTransactionIdRequest sGetTransactionDetailsByTransactionIdRequest, CoveApiListener<SGetTransactionDetailsByTransactionIdResponse, CoveApiErrorModel> coveApiListener) {
        r(CoveApi.getCustomHeaders(), sGetTransactionDetailsByTransactionIdRequest, coveApiListener);
    }

    public static void getUserAllRegisteredDevice(SGetAllRegisteredDeviceByUserRequest sGetAllRegisteredDeviceByUserRequest, CoveApiListener<List<SDeviceInfo>, CoveApiErrorModel> coveApiListener) {
        j(CoveApi.getCustomHeaders(), sGetAllRegisteredDeviceByUserRequest, coveApiListener);
    }

    public static void getUserByEmail(SGetUserByEmailRequest sGetUserByEmailRequest, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        t(CoveApi.getCustomHeaders(), sGetUserByEmailRequest, coveApiListener);
    }

    public static void getUserInfo(CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        I(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getUserRegisteredProductByUserId(SGetUserRegisteredProductDetailsByUserId sGetUserRegisteredProductDetailsByUserId, CoveApiListener<List<SRegisteredProduct>, CoveApiErrorModel> coveApiListener) {
        w(CoveApi.getCustomHeaders(), sGetUserRegisteredProductDetailsByUserId, coveApiListener);
    }

    public static void h(HashMap<String, String> hashMap, SErrorLogRequest sErrorLogRequest, CoveApiListener<SErrorLogResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.logTappyError(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/System/LogError", sErrorLogRequest).enqueue(new h0(coveApiListener));
    }

    public static void i(HashMap<String, String> hashMap, SGetAllProducts sGetAllProducts, CoveApiListener<List<SProduct>, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyAllProducts(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Products", sGetAllProducts.isIncludeDeleted()).enqueue(new m(coveApiListener));
    }

    public static void installFoundationToSecureElement(SInstallFoundationToSecureElementRequest sInstallFoundationToSecureElementRequest, CoveApiListener<SInstallFoundationToSecureElementResponse, CoveApiErrorModel> coveApiListener) {
        x(CoveApi.getCustomHeaders(), sInstallFoundationToSecureElementRequest, coveApiListener);
    }

    public static void j(HashMap<String, String> hashMap, SGetAllRegisteredDeviceByUserRequest sGetAllRegisteredDeviceByUserRequest, CoveApiListener<List<SDeviceInfo>, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyUserDeviceInfo(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Devices/" + sGetAllRegisteredDeviceByUserRequest.getEndUserId()).enqueue(new y(coveApiListener));
    }

    public static void k(HashMap<String, String> hashMap, SGetEncryptionKeyRequest sGetEncryptionKeyRequest, CoveApiListener<SEncryptionKey, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getEncryptionKey(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentNetworks/" + sGetEncryptionKeyRequest.getPaymentNetworkId() + "/EncryptionKey").enqueue(new j(coveApiListener));
    }

    public static void l(HashMap<String, String> hashMap, SGetPostPersoCommandsRequest sGetPostPersoCommandsRequest, CoveApiListener<List<SApduCommand>, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getPostPersoCommands(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sGetPostPersoCommandsRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sGetPostPersoCommandsRequest.getPaymentInstrumentTokenId(), sGetPostPersoCommandsRequest.getInitUpdateResponse()).enqueue(new r(coveApiListener));
    }

    public static void logError(SErrorLogRequest sErrorLogRequest, CoveApiListener<SErrorLogResponse, CoveApiErrorModel> coveApiListener) {
        h(CoveApi.getCustomHeaders(), sErrorLogRequest, coveApiListener);
    }

    public static void m(HashMap<String, String> hashMap, SGetProductDetailsByProductId sGetProductDetailsByProductId, CoveApiListener<SProduct, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyProductDetailsByProductId(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Products/" + sGetProductDetailsByProductId.getProductId()).enqueue(new o(coveApiListener));
    }

    public static void n(HashMap<String, String> hashMap, SGetProductDetailsBySerialNumber sGetProductDetailsBySerialNumber, CoveApiListener<SProduct, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyProductDetailsBySerialNumber(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Products/SerialNumbers/" + sGetProductDetailsBySerialNumber.getSerialNumber()).enqueue(new q(coveApiListener));
    }

    public static void o(HashMap<String, String> hashMap, SGetStepUpOptions sGetStepUpOptions, CoveApiListener<List<SStepUpRequest>, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getStepUpOptions(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sGetStepUpOptions.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sGetStepUpOptions.getPaymentInstrumentTokenId() + "/StepUpOptions", sGetStepUpOptions.getDeviceId()).enqueue(new v(coveApiListener));
    }

    public static void p(HashMap<String, String> hashMap, SGetTAndCRequest sGetTAndCRequest, CoveApiListener<SGetTAndCResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyTAndC(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sGetTAndCRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sGetTAndCRequest.getPaymentInstrumentTokenId() + "/TermsAndConditions").enqueue(new i0(coveApiListener));
    }

    public static void postPersoCommandsExecuted(SPutPostPersoCommandsExecutedRequest sPutPostPersoCommandsExecutedRequest, CoveApiListener<SPutPostPersoCommandsExecutedResponse, CoveApiErrorModel> coveApiListener) {
        y(CoveApi.getCustomHeaders(), sPutPostPersoCommandsExecutedRequest, coveApiListener);
    }

    public static void putStepUpOptions(SPutStepUpOptionsRequest sPutStepUpOptionsRequest, CoveApiListener<SPutStepUpOptionsResponse, CoveApiErrorModel> coveApiListener) {
        z(CoveApi.getCustomHeaders(), sPutStepUpOptionsRequest, coveApiListener);
    }

    public static void q(HashMap<String, String> hashMap, SGetTokenPersoScript sGetTokenPersoScript, CoveApiListener<SSECardPersoScript, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTokenPersoScript(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sGetTokenPersoScript.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sGetTokenPersoScript.getPaymentInstrumentTokenId() + "/TokenPersoScript").enqueue(new l(coveApiListener));
    }

    public static void r(HashMap<String, String> hashMap, SGetTransactionDetailsByTransactionIdRequest sGetTransactionDetailsByTransactionIdRequest, CoveApiListener<SGetTransactionDetailsByTransactionIdResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyTransactionDetailsById(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sGetTransactionDetailsByTransactionIdRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sGetTransactionDetailsByTransactionIdRequest.getPaymentInstrumentTokenId() + "/Transactions/" + sGetTransactionDetailsByTransactionIdRequest.getTransactionId()).enqueue(new d0(coveApiListener));
    }

    public static void registerNeDevice(SRegisterDeviceRequest sRegisterDeviceRequest, CoveApiListener<SDeviceInfo, CoveApiErrorModel> coveApiListener) {
        A(CoveApi.getCustomHeaders(), sRegisterDeviceRequest, coveApiListener);
    }

    public static void registerNewProduct(SRegisterProductRequest sRegisterProductRequest, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        B(CoveApi.getCustomHeaders(), sRegisterProductRequest, coveApiListener);
    }

    public static void registerNewTappyUser(CoveApiListener<SUserDataResponseNew, CoveApiErrorModel> coveApiListener) {
        J(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void registerNewUser(SRegisterUserRequest sRegisterUserRequest, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        C(CoveApi.getCustomHeaders(), sRegisterUserRequest, coveApiListener);
    }

    public static void resumePaymentInstrumentTokens(SResumePaymentInstrumentTokensRequest sResumePaymentInstrumentTokensRequest, CoveApiListener<SResumePaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        D(CoveApi.getCustomHeaders(), sResumePaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void s(HashMap<String, String> hashMap, SGetTransactionDetailsRequest sGetTransactionDetailsRequest, CoveApiListener<SGetTransactionDetailsResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyTransactionDetails(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sGetTransactionDetailsRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sGetTransactionDetailsRequest.getPaymentInstrumentTokenId()).enqueue(new c0(coveApiListener));
    }

    public static void sendPaymentInstrumentTokenByUserId(SSendPaymentInstrumentTokensRequest sSendPaymentInstrumentTokensRequest, CoveApiListener<SPaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        E(CoveApi.getCustomHeaders(), sSendPaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void suspendPaymentInstrumentTokens(SSuspendPaymentInstrumentTokensRequest sSuspendPaymentInstrumentTokensRequest, CoveApiListener<SSuspendPaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        F(CoveApi.getCustomHeaders(), sSuspendPaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void t(HashMap<String, String> hashMap, SGetUserByEmailRequest sGetUserByEmailRequest, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyUserByEmail(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/Users/ByEmail", sGetUserByEmailRequest.getEmail(), sGetUserByEmailRequest.isIncludeAddress(), sGetUserByEmailRequest.isIncludeCustomFieldAnswers()).enqueue(new g(coveApiListener));
    }

    public static void u(HashMap<String, String> hashMap, SGetUserRegisteredPaymentInstrumentDetailsByUserId sGetUserRegisteredPaymentInstrumentDetailsByUserId, CoveApiListener<List<SPaymentInstrument>, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getPaymentInstrumentByUerId(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstruments/" + sGetUserRegisteredPaymentInstrumentDetailsByUserId.getUserId()).enqueue(new c(coveApiListener));
    }

    public static void updateRegisteredProductFriendlyName(SUpdateRegisteredProductFriendlyNameRequest sUpdateRegisteredProductFriendlyNameRequest, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        G(CoveApi.getCustomHeaders(), sUpdateRegisteredProductFriendlyNameRequest, coveApiListener);
    }

    public static void v(HashMap<String, String> hashMap, SGetUserRegisteredProductByUserIdAndPRegId sGetUserRegisteredProductByUserIdAndPRegId, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyRegisteredProductDetailsByUserIdAndPRegId(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/RegisteredProducts/" + sGetUserRegisteredProductByUserIdAndPRegId.getUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sGetUserRegisteredProductByUserIdAndPRegId.getProductId()).enqueue(new s(coveApiListener));
    }

    public static void validateOtp(SValidateOTPRequest sValidateOTPRequest, CoveApiListener<SValidateOTPResponse, CoveApiErrorModel> coveApiListener) {
        H(CoveApi.getCustomHeaders(), sValidateOTPRequest, coveApiListener);
    }

    public static void w(HashMap<String, String> hashMap, SGetUserRegisteredProductDetailsByUserId sGetUserRegisteredProductDetailsByUserId, CoveApiListener<List<SRegisteredProduct>, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.getTappyRegisteredProductDetailsByUserId(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/RegisteredProducts/" + sGetUserRegisteredProductDetailsByUserId.getUserId()).enqueue(new d(coveApiListener));
    }

    public static void x(HashMap<String, String> hashMap, SInstallFoundationToSecureElementRequest sInstallFoundationToSecureElementRequest, CoveApiListener<SInstallFoundationToSecureElementResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.installFoundationToSecureElement(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/TSM/SecureCommands/InstallFoundation", sInstallFoundationToSecureElementRequest).enqueue(new b(coveApiListener));
    }

    public static void y(HashMap<String, String> hashMap, SPutPostPersoCommandsExecutedRequest sPutPostPersoCommandsExecutedRequest, CoveApiListener<SPutPostPersoCommandsExecutedResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.postPersoCommandsExecuted(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sPutPostPersoCommandsExecutedRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sPutPostPersoCommandsExecutedRequest.getPaymentInstrumentTokenId() + "/PostPersoCommandsExecuted").enqueue(new t(coveApiListener));
    }

    public static void z(HashMap<String, String> hashMap, SPutStepUpOptionsRequest sPutStepUpOptionsRequest, CoveApiListener<SPutStepUpOptionsResponse, CoveApiErrorModel> coveApiListener) {
        CoveApiService service = CoveApi.getService();
        service.putStepUpOptions(hashMap, CoveApi.getInstance().getTappyProxyApiUrl() + "v1/PaymentInstrumentTokens/" + sPutStepUpOptionsRequest.getEndUserId() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sPutStepUpOptionsRequest.getPaymentInstrumentTokenId() + "/StepUpOptions", sPutStepUpOptionsRequest).enqueue(new w(coveApiListener));
    }

    public static void acceptTermsAndGenerateToken(HashMap<String, String> hashMap, SAcceptTermsAndGenerateTokenRequest sAcceptTermsAndGenerateTokenRequest, CoveApiListener<SAcceptTermsAndGenerateTokenResponse, CoveApiErrorModel> coveApiListener) {
        b(CoveUtil.getRevisedHeaders(hashMap), sAcceptTermsAndGenerateTokenRequest, coveApiListener);
    }

    public static void confirmProvisioning(HashMap<String, String> hashMap, SConfirmProvisioningRequest sConfirmProvisioningRequest, CoveApiListener<SConfirmProvisioningResponse, CoveApiErrorModel> coveApiListener) {
        c(CoveUtil.getRevisedHeaders(hashMap), sConfirmProvisioningRequest, coveApiListener);
    }

    public static void deleteEndUser(HashMap<String, String> hashMap, SDeleteUserRequest sDeleteUserRequest, CoveApiListener<SDeleteUserResponse, CoveApiErrorModel> coveApiListener) {
        g(CoveUtil.getRevisedHeaders(hashMap), sDeleteUserRequest, coveApiListener);
    }

    public static void deletePaymentInstrument(HashMap<String, String> hashMap, SDeletePaymentInstrumentRequest sDeletePaymentInstrumentRequest, CoveApiListener<SDeletePaymentInstrumentResponse, CoveApiErrorModel> coveApiListener) {
        d(CoveUtil.getRevisedHeaders(hashMap), sDeletePaymentInstrumentRequest, coveApiListener);
    }

    public static void deletePaymentInstrumentTokens(HashMap<String, String> hashMap, SDeletePaymentInstrumentTokensRequest sDeletePaymentInstrumentTokensRequest, CoveApiListener<SDeletePaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        e(CoveUtil.getRevisedHeaders(hashMap), sDeletePaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void deleteRegisteredProduct(HashMap<String, String> hashMap, SDeleteRegisteredProductRequest sDeleteRegisteredProductRequest, CoveApiListener<SDeleteRegisteredProductResponse, CoveApiErrorModel> coveApiListener) {
        f(CoveUtil.getRevisedHeaders(hashMap), sDeleteRegisteredProductRequest, coveApiListener);
    }

    public static void deleteTappyUserNew(HashMap<String, String> hashMap, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        a(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getAllProducts(HashMap<String, String> hashMap, SGetAllProducts sGetAllProducts, CoveApiListener<List<SProduct>, CoveApiErrorModel> coveApiListener) {
        i(CoveUtil.getRevisedHeaders(hashMap), sGetAllProducts, coveApiListener);
    }

    public static void getGetEncryptionKey(HashMap<String, String> hashMap, SGetEncryptionKeyRequest sGetEncryptionKeyRequest, CoveApiListener<SEncryptionKey, CoveApiErrorModel> coveApiListener) {
        k(CoveUtil.getRevisedHeaders(hashMap), sGetEncryptionKeyRequest, coveApiListener);
    }

    public static void getPaymentInstrumentByUserId(HashMap<String, String> hashMap, SGetUserRegisteredPaymentInstrumentDetailsByUserId sGetUserRegisteredPaymentInstrumentDetailsByUserId, CoveApiListener<List<SPaymentInstrument>, CoveApiErrorModel> coveApiListener) {
        u(CoveUtil.getRevisedHeaders(hashMap), sGetUserRegisteredPaymentInstrumentDetailsByUserId, coveApiListener);
    }

    public static void getPostPersoCommands(HashMap<String, String> hashMap, SGetPostPersoCommandsRequest sGetPostPersoCommandsRequest, CoveApiListener<List<SApduCommand>, CoveApiErrorModel> coveApiListener) {
        l(CoveUtil.getRevisedHeaders(hashMap), sGetPostPersoCommandsRequest, coveApiListener);
    }

    public static void getProductDetailsByProductId(HashMap<String, String> hashMap, SGetProductDetailsByProductId sGetProductDetailsByProductId, CoveApiListener<SProduct, CoveApiErrorModel> coveApiListener) {
        m(CoveUtil.getRevisedHeaders(hashMap), sGetProductDetailsByProductId, coveApiListener);
    }

    public static void getProductDetailsBySerialNumber(HashMap<String, String> hashMap, SGetProductDetailsBySerialNumber sGetProductDetailsBySerialNumber, CoveApiListener<SProduct, CoveApiErrorModel> coveApiListener) {
        n(CoveUtil.getRevisedHeaders(hashMap), sGetProductDetailsBySerialNumber, coveApiListener);
    }

    public static void getProductDetailsByUserIdAndProductId(HashMap<String, String> hashMap, SGetUserRegisteredProductByUserIdAndPRegId sGetUserRegisteredProductByUserIdAndPRegId, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        v(CoveUtil.getRevisedHeaders(hashMap), sGetUserRegisteredProductByUserIdAndPRegId, coveApiListener);
    }

    public static void getStepUpOptions(HashMap<String, String> hashMap, SGetStepUpOptions sGetStepUpOptions, CoveApiListener<List<SStepUpRequest>, CoveApiErrorModel> coveApiListener) {
        o(CoveUtil.getRevisedHeaders(hashMap), sGetStepUpOptions, coveApiListener);
    }

    public static void getTAndC(SGetTAndCRequest sGetTAndCRequest, CoveApiListener<SGetTAndCResponse, CoveApiErrorModel> coveApiListener) {
        p(CoveApi.getCustomHeaders(), sGetTAndCRequest, coveApiListener);
    }

    public static void getTokenPersoScript(HashMap<String, String> hashMap, SGetTokenPersoScript sGetTokenPersoScript, CoveApiListener<SSECardPersoScript, CoveApiErrorModel> coveApiListener) {
        q(CoveUtil.getRevisedHeaders(hashMap), sGetTokenPersoScript, coveApiListener);
    }

    public static void getTransactionDetails(HashMap<String, String> hashMap, SGetTransactionDetailsRequest sGetTransactionDetailsRequest, CoveApiListener<SGetTransactionDetailsResponse, CoveApiErrorModel> coveApiListener) {
        s(CoveUtil.getRevisedHeaders(hashMap), sGetTransactionDetailsRequest, coveApiListener);
    }

    public static void getTransactionDetailsByTransactionId(HashMap<String, String> hashMap, SGetTransactionDetailsByTransactionIdRequest sGetTransactionDetailsByTransactionIdRequest, CoveApiListener<SGetTransactionDetailsByTransactionIdResponse, CoveApiErrorModel> coveApiListener) {
        r(CoveUtil.getRevisedHeaders(hashMap), sGetTransactionDetailsByTransactionIdRequest, coveApiListener);
    }

    public static void getUserAllRegisteredDevice(HashMap<String, String> hashMap, SGetAllRegisteredDeviceByUserRequest sGetAllRegisteredDeviceByUserRequest, CoveApiListener<List<SDeviceInfo>, CoveApiErrorModel> coveApiListener) {
        j(CoveUtil.getRevisedHeaders(hashMap), sGetAllRegisteredDeviceByUserRequest, coveApiListener);
    }

    public static void getUserByEmail(HashMap<String, String> hashMap, SGetUserByEmailRequest sGetUserByEmailRequest, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        t(CoveUtil.getRevisedHeaders(hashMap), sGetUserByEmailRequest, coveApiListener);
    }

    public static void getUserInfo(HashMap<String, String> hashMap, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        I(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getUserRegisteredProductByUserId(HashMap<String, String> hashMap, SGetUserRegisteredProductDetailsByUserId sGetUserRegisteredProductDetailsByUserId, CoveApiListener<List<SRegisteredProduct>, CoveApiErrorModel> coveApiListener) {
        w(CoveUtil.getRevisedHeaders(hashMap), sGetUserRegisteredProductDetailsByUserId, coveApiListener);
    }

    public static void installFoundationToSecureElement(HashMap<String, String> hashMap, SInstallFoundationToSecureElementRequest sInstallFoundationToSecureElementRequest, CoveApiListener<SInstallFoundationToSecureElementResponse, CoveApiErrorModel> coveApiListener) {
        x(CoveUtil.getRevisedHeaders(hashMap), sInstallFoundationToSecureElementRequest, coveApiListener);
    }

    public static void logError(HashMap<String, String> hashMap, SErrorLogRequest sErrorLogRequest, CoveApiListener<SErrorLogResponse, CoveApiErrorModel> coveApiListener) {
        h(CoveUtil.getRevisedHeaders(hashMap), sErrorLogRequest, coveApiListener);
    }

    public static void postPersoCommandsExecuted(HashMap<String, String> hashMap, SPutPostPersoCommandsExecutedRequest sPutPostPersoCommandsExecutedRequest, CoveApiListener<SPutPostPersoCommandsExecutedResponse, CoveApiErrorModel> coveApiListener) {
        y(CoveUtil.getRevisedHeaders(hashMap), sPutPostPersoCommandsExecutedRequest, coveApiListener);
    }

    public static void putStepUpOptions(HashMap<String, String> hashMap, SPutStepUpOptionsRequest sPutStepUpOptionsRequest, CoveApiListener<SPutStepUpOptionsResponse, CoveApiErrorModel> coveApiListener) {
        z(CoveUtil.getRevisedHeaders(hashMap), sPutStepUpOptionsRequest, coveApiListener);
    }

    public static void registerNeDevice(HashMap<String, String> hashMap, SRegisterDeviceRequest sRegisterDeviceRequest, CoveApiListener<SDeviceInfo, CoveApiErrorModel> coveApiListener) {
        A(CoveUtil.getRevisedHeaders(hashMap), sRegisterDeviceRequest, coveApiListener);
    }

    public static void registerNewProduct(HashMap<String, String> hashMap, SRegisterProductRequest sRegisterProductRequest, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        B(CoveUtil.getRevisedHeaders(hashMap), sRegisterProductRequest, coveApiListener);
    }

    public static void registerNewTappyUser(HashMap<String, String> hashMap, CoveApiListener<SUserDataResponseNew, CoveApiErrorModel> coveApiListener) {
        J(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void registerNewUser(HashMap<String, String> hashMap, SRegisterUserRequest sRegisterUserRequest, CoveApiListener<SUserDataResponse, CoveApiErrorModel> coveApiListener) {
        C(CoveUtil.getRevisedHeaders(hashMap), sRegisterUserRequest, coveApiListener);
    }

    public static void resumePaymentInstrumentTokens(HashMap<String, String> hashMap, SResumePaymentInstrumentTokensRequest sResumePaymentInstrumentTokensRequest, CoveApiListener<SResumePaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        D(CoveUtil.getRevisedHeaders(hashMap), sResumePaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void sendPaymentInstrumentTokenByUserId(HashMap<String, String> hashMap, SSendPaymentInstrumentTokensRequest sSendPaymentInstrumentTokensRequest, CoveApiListener<SPaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        E(CoveUtil.getRevisedHeaders(hashMap), sSendPaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void suspendPaymentInstrumentTokens(HashMap<String, String> hashMap, SSuspendPaymentInstrumentTokensRequest sSuspendPaymentInstrumentTokensRequest, CoveApiListener<SSuspendPaymentInstrumentTokensResponse, CoveApiErrorModel> coveApiListener) {
        F(CoveUtil.getRevisedHeaders(hashMap), sSuspendPaymentInstrumentTokensRequest, coveApiListener);
    }

    public static void updateRegisteredProductFriendlyName(HashMap<String, String> hashMap, SUpdateRegisteredProductFriendlyNameRequest sUpdateRegisteredProductFriendlyNameRequest, CoveApiListener<SRegisteredProduct, CoveApiErrorModel> coveApiListener) {
        G(CoveUtil.getRevisedHeaders(hashMap), sUpdateRegisteredProductFriendlyNameRequest, coveApiListener);
    }

    public static void validateOtp(HashMap<String, String> hashMap, SValidateOTPRequest sValidateOTPRequest, CoveApiListener<SValidateOTPResponse, CoveApiErrorModel> coveApiListener) {
        H(CoveUtil.getRevisedHeaders(hashMap), sValidateOTPRequest, coveApiListener);
    }
}
