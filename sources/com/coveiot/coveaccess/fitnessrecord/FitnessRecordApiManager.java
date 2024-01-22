package com.coveiot.coveaccess.fitnessrecord;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.model.AllFitnessRecordsResponse;
import com.coveiot.coveaccess.fitness.model.BpFitnessRecords;
import com.coveiot.coveaccess.fitness.model.FitnessRecords;
import com.coveiot.coveaccess.fitness.model.MensurationSymptomRecordResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SSaveFitnessRecordReq;
import com.coveiot.coveaccess.model.server.SSaveMensurationFitnessRecordReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class FitnessRecordApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6591a;

        public a(CoveApiListener coveApiListener) {
            this.f6591a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6591a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {
                SaveFitnessRecordsResponse saveFitnessRecordsResponse = new SaveFitnessRecordsResponse(response.code());
                saveFitnessRecordsResponse.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveFitnessRecordsResponse.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6591a.onSuccess(saveFitnessRecordsResponse);
                return;
            }
            int code = response.code();
            this.f6591a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<FitnessRecords> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6592a;

        public b(CoveApiListener coveApiListener) {
            this.f6592a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<FitnessRecords> call, Throwable th) {
            this.f6592a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<FitnessRecords> call, Response<FitnessRecords> response) {
            if (response.isSuccessful() && response.body() != null && response.body().getItems() != null) {
                this.f6592a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6592a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<BpFitnessRecords> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6593a;

        public c(CoveApiListener coveApiListener) {
            this.f6593a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<BpFitnessRecords> call, Throwable th) {
            this.f6593a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<BpFitnessRecords> call, Response<BpFitnessRecords> response) {
            if (response.isSuccessful() && response.body() != null && response.body().getItems() != null) {
                this.f6593a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6593a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<MensurationSymptomRecordResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6594a;

        public d(CoveApiListener coveApiListener) {
            this.f6594a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<MensurationSymptomRecordResponse> call, Throwable th) {
            this.f6594a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<MensurationSymptomRecordResponse> call, Response<MensurationSymptomRecordResponse> response) {
            if (response.isSuccessful() && response.code() == 200 && response.body() != null && response.body().getItems() != null) {
                this.f6594a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6594a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6595a;

        public e(CoveApiListener coveApiListener) {
            this.f6595a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6595a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful() && response.code() == 200) {
                SaveFitnessRecordsResponse saveFitnessRecordsResponse = new SaveFitnessRecordsResponse(response.code());
                saveFitnessRecordsResponse.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveFitnessRecordsResponse.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6595a.onSuccess(saveFitnessRecordsResponse);
                return;
            }
            int code = response.code();
            this.f6595a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<AllFitnessRecordsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6596a;

        public f(CoveApiListener coveApiListener) {
            this.f6596a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AllFitnessRecordsResponse> call, Throwable th) {
            this.f6596a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AllFitnessRecordsResponse> call, Response<AllFitnessRecordsResponse> response) {
            if (response.isSuccessful() && response.code() == 200 && response.body() != null && response.body().getAllFitnessRecordModel() != null) {
                this.f6596a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6596a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SSaveFitnessRecordReq sSaveFitnessRecordReq, CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().saveFitnessRecords(map, sSaveFitnessRecordReq).enqueue(new a(coveApiListener));
    }

    public static void b(SSaveMensurationFitnessRecordReq sSaveMensurationFitnessRecordReq, CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().saveMensesSymptomRecords(map, sSaveMensurationFitnessRecordReq).enqueue(new e(coveApiListener));
    }

    public static void c(String str, String str2, @NonNull CoveApiListener<BpFitnessRecords, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getBpFitnessRecords(map, str, str2, EcgStyleReportUtil.UserInfoKey.BP).enqueue(new c(coveApiListener));
    }

    public static void d(String str, String str2, String str3, @NonNull CoveApiListener<AllFitnessRecordsResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getAllFitnessRecords(map, str, str2, str3).enqueue(new f(coveApiListener));
    }

    public static void e(String str, String str2, @NonNull CoveApiListener<MensurationSymptomRecordResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getMensesSymptomRecords(map, str, str2, "MENSES").enqueue(new d(coveApiListener));
    }

    public static void f(String str, String str2, String str3, @NonNull CoveApiListener<FitnessRecords, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessRecords(map, str, str2, str3).enqueue(new b(coveApiListener));
    }

    public static void getAllFitnessRecord(String str, String str2, String str3, @NonNull CoveApiListener<AllFitnessRecordsResponse, CoveApiErrorModel> coveApiListener) {
        d(str, str2, str3, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getBpFitnessRecord(String str, String str2, @NonNull CoveApiListener<BpFitnessRecords, CoveApiErrorModel> coveApiListener) {
        c(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessRecords(String str, String str2, String str3, @NonNull CoveApiListener<FitnessRecords, CoveApiErrorModel> coveApiListener) {
        f(str, str2, str3, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getMensurationSymptomRecord(String str, String str2, @NonNull CoveApiListener<MensurationSymptomRecordResponse, CoveApiErrorModel> coveApiListener) {
        e(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveFitnessRecords(SSaveFitnessRecordReq sSaveFitnessRecordReq, CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel> coveApiListener) {
        if (sSaveFitnessRecordReq != null) {
            a(sSaveFitnessRecordReq, coveApiListener, CoveApi.getCustomHeaders());
        }
    }

    public static void saveMensurationFitnessRecords(SSaveMensurationFitnessRecordReq sSaveMensurationFitnessRecordReq, CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel> coveApiListener) {
        if (sSaveMensurationFitnessRecordReq != null) {
            b(sSaveMensurationFitnessRecordReq, coveApiListener, CoveApi.getCustomHeaders());
        }
    }

    public static void getAllFitnessRecord(String str, String str2, String str3, HashMap<String, String> hashMap, @NonNull CoveApiListener<AllFitnessRecordsResponse, CoveApiErrorModel> coveApiListener) {
        d(str, str2, str3, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getBpFitnessRecord(String str, String str2, HashMap<String, String> hashMap, @NonNull CoveApiListener<BpFitnessRecords, CoveApiErrorModel> coveApiListener) {
        c(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessRecords(String str, String str2, String str3, HashMap<String, String> hashMap, @NonNull CoveApiListener<FitnessRecords, CoveApiErrorModel> coveApiListener) {
        f(str, str2, str3, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getMensurationSymptomRecord(String str, String str2, HashMap<String, String> hashMap, @NonNull CoveApiListener<MensurationSymptomRecordResponse, CoveApiErrorModel> coveApiListener) {
        e(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveFitnessRecords(HashMap<String, String> hashMap, SSaveFitnessRecordReq sSaveFitnessRecordReq, CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel> coveApiListener) {
        if (sSaveFitnessRecordReq != null) {
            a(sSaveFitnessRecordReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
        }
    }

    public static void saveMensurationFitnessRecords(HashMap<String, String> hashMap, SSaveMensurationFitnessRecordReq sSaveMensurationFitnessRecordReq, CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel> coveApiListener) {
        if (sSaveMensurationFitnessRecordReq != null) {
            b(sSaveMensurationFitnessRecordReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
        }
    }
}
