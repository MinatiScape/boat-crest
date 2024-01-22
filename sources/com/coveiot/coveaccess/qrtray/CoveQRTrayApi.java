package com.coveiot.coveaccess.qrtray;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.mediauplaod.model.MediaListBean;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.model.server.SMediaUploadResponse;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodeIDsReq;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveReq;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveQRTrayApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<CommonResponseGeneric<QRTrayCategoriesRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6684a;

        public a(CoveApiListener coveApiListener) {
            this.f6684a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<QRTrayCategoriesRes>> call, Throwable th) {
            this.f6684a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<QRTrayCategoriesRes>> call, Response<CommonResponseGeneric<QRTrayCategoriesRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6684a.onSuccess(response.body().getData());
                        return;
                    } else {
                        this.f6684a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6684a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<CommonResponseGeneric<QRTraySaveRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6685a;

        public b(CoveApiListener coveApiListener) {
            this.f6685a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<QRTraySaveRes>> call, Throwable th) {
            this.f6685a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<QRTraySaveRes>> call, Response<CommonResponseGeneric<QRTraySaveRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6685a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6685a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6685a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<CommonResponseGeneric<QRTraySaveRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6686a;

        public c(CoveApiListener coveApiListener) {
            this.f6686a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<QRTraySaveRes>> call, Throwable th) {
            this.f6686a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<QRTraySaveRes>> call, Response<CommonResponseGeneric<QRTraySaveRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6686a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6686a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6686a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<CommonResponseClass> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6687a;

        public d(CoveApiListener coveApiListener) {
            this.f6687a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6687a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6687a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6687a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6687a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<CommonResponseGeneric<QRTrayCodesRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6688a;

        public e(CoveApiListener coveApiListener) {
            this.f6688a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<QRTrayCodesRes>> call, Throwable th) {
            this.f6688a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<QRTrayCodesRes>> call, Response<CommonResponseGeneric<QRTrayCodesRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6688a.onSuccess(response.body().getData());
                        return;
                    } else {
                        this.f6688a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6688a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<SMediaUploadResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6689a;

        public f(CoveApiListener coveApiListener) {
            this.f6689a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SMediaUploadResponse> call, Throwable th) {
            this.f6689a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SMediaUploadResponse> call, Response<SMediaUploadResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6689a.onSuccess(response.body().getData());
                return;
            }
            int code = response.code();
            this.f6689a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(Map<String, String> map, @NonNull CoveApiListener<QRTrayCategoriesRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getCategories(map).enqueue(new a(coveApiListener));
    }

    public static void b(Map<String, String> map, QRTrayCodeIDsReq qRTrayCodeIDsReq, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().deleteQRCodes(map, qRTrayCodeIDsReq).enqueue(new d(coveApiListener));
    }

    public static void c(Map<String, String> map, QRTraySaveReq qRTraySaveReq, @NonNull CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().saveQRTrayCode(map, qRTraySaveReq).enqueue(new b(coveApiListener));
    }

    public static void d(Map<String, String> map, File file, @NonNull CoveApiListener<MediaListBean, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().uploadImage(map, MultipartBody.Part.createFormData("sourceFile", file.getName(), RequestBody.create(MediaType.parse(com.crrepa.r.a.d), file)), RequestBody.create(MediaType.parse("text/plain"), "USR_DEVICE_QR_IMAGE")).enqueue(new f(coveApiListener));
    }

    public static void deleteQRTray(QRTrayCodeIDsReq qRTrayCodeIDsReq, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        b(CoveApi.getCustomHeaders(), qRTrayCodeIDsReq, coveApiListener);
    }

    public static void e(Map<String, String> map, Object obj, QRTraySaveReq qRTraySaveReq, @NonNull CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().editQRTray(map, qRTraySaveReq, obj).enqueue(new c(coveApiListener));
    }

    public static void editQRTrayCode(QRTraySaveReq qRTraySaveReq, Object obj, CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel> coveApiListener) {
        e(CoveApi.getCustomHeaders(), obj, qRTraySaveReq, coveApiListener);
    }

    public static void f(Map<String, String> map, @NonNull CoveApiListener<QRTrayCodesRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getQRCodes(map).enqueue(new e(coveApiListener));
    }

    public static void getQRTrayCategories(CoveApiListener<QRTrayCategoriesRes, CoveApiErrorModel> coveApiListener) {
        a(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getQRTrayCodes(CoveApiListener<QRTrayCodesRes, CoveApiErrorModel> coveApiListener) {
        f(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void saveQRTrayCode(QRTraySaveReq qRTraySaveReq, CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel> coveApiListener) {
        c(CoveApi.getCustomHeaders(), qRTraySaveReq, coveApiListener);
    }

    public static void uploadQRTrayCode(File file, CoveApiListener<MediaListBean, CoveApiErrorModel> coveApiListener) {
        d(CoveApi.getCustomHeaders(), file, coveApiListener);
    }

    public static void deleteQRTray(HashMap<String, String> hashMap, QRTrayCodeIDsReq qRTrayCodeIDsReq, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        b(CoveUtil.getRevisedHeaders(hashMap), qRTrayCodeIDsReq, coveApiListener);
    }

    public static void editQRTrayCode(HashMap<String, String> hashMap, Object obj, QRTraySaveReq qRTraySaveReq, CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel> coveApiListener) {
        e(CoveUtil.getRevisedHeaders(hashMap), obj, qRTraySaveReq, coveApiListener);
    }

    public static void getQRTrayCategories(HashMap<String, String> hashMap, CoveApiListener<QRTrayCategoriesRes, CoveApiErrorModel> coveApiListener) {
        a(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getQRTrayCodes(HashMap<String, String> hashMap, CoveApiListener<QRTrayCodesRes, CoveApiErrorModel> coveApiListener) {
        f(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void saveQRTrayCode(HashMap<String, String> hashMap, QRTraySaveReq qRTraySaveReq, CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel> coveApiListener) {
        c(CoveUtil.getRevisedHeaders(hashMap), qRTraySaveReq, coveApiListener);
    }

    public static void uploadQRTrayCode(HashMap<String, String> hashMap, File file, CoveApiListener<MediaListBean, CoveApiErrorModel> coveApiListener) {
        d(CoveUtil.getRevisedHeaders(hashMap), file, coveApiListener);
    }
}
