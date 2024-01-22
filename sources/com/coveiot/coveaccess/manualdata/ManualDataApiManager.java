package com.coveiot.coveaccess.manualdata;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.manualdata.model.ManualBpData;
import com.coveiot.coveaccess.manualdata.model.ManualMixedData;
import com.coveiot.coveaccess.manualdata.model.SManualMixedData;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.FitnessSessionDataBean;
import com.coveiot.coveaccess.model.server.SGetManualBpData;
import com.coveiot.coveaccess.model.server.SGetManualMixedData;
import com.coveiot.coveaccess.model.server.SGetManualSPO2Data;
import com.coveiot.coveaccess.model.server.SPO2FitnessSessionDataBean;
import com.coveiot.coveaccess.model.server.SSaveManualData;
import com.coveiot.coveaccess.model.server.SSaveManualSPO2Data;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class ManualDataApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6640a;

        public a(CoveApiListener coveApiListener) {
            this.f6640a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6640a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {
                SaveManualBpDataRes saveManualBpDataRes = new SaveManualBpDataRes(response.code());
                saveManualBpDataRes.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveManualBpDataRes.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6640a.onSuccess(saveManualBpDataRes);
                return;
            }
            int code = response.code();
            this.f6640a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6641a;

        public b(CoveApiListener coveApiListener) {
            this.f6641a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6641a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {
                SaveManualMixedDataRes saveManualMixedDataRes = new SaveManualMixedDataRes(response.code());
                saveManualMixedDataRes.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveManualMixedDataRes.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6641a.onSuccess(saveManualMixedDataRes);
                return;
            }
            int code = response.code();
            this.f6641a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SGetManualBpData> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6642a;

        public c(CoveApiListener coveApiListener) {
            this.f6642a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetManualBpData> call, Throwable th) {
            this.f6642a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetManualBpData> call, Response<SGetManualBpData> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        ArrayList arrayList = new ArrayList();
                        SGetManualBpData body = response.body();
                        if (body.getData() != null && !AppUtils.isEmpty(body.getData().getFitnessSessionData())) {
                            for (FitnessSessionDataBean fitnessSessionDataBean : body.getData().getFitnessSessionData()) {
                                ManualBpData manualBpData = new ManualBpData();
                                manualBpData.setClientRefId(fitnessSessionDataBean.getClientRefId());
                                manualBpData.setSessionStartDate(fitnessSessionDataBean.getSessionStartDate());
                                manualBpData.setSessionEndDate(fitnessSessionDataBean.getSessionEndDate());
                                manualBpData.setBaseUnit(fitnessSessionDataBean.getBaseUnit());
                                manualBpData.setTotalSampleCount(fitnessSessionDataBean.getTotalSampleCount());
                                manualBpData.setTzOffset(fitnessSessionDataBean.getTzOffset());
                                manualBpData.setValue(fitnessSessionDataBean.getValue());
                                arrayList.add(manualBpData);
                            }
                        }
                        this.f6642a.onSuccess(new GetManualBPDataRes(arrayList));
                        return;
                    }
                    this.f6642a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
                this.f6642a.onError(new CoveApiErrorModel(response.message()));
                return;
            }
            int code = response.code();
            this.f6642a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SGetManualMixedData> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6643a;

        public d(CoveApiListener coveApiListener) {
            this.f6643a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetManualMixedData> call, Throwable th) {
            this.f6643a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetManualMixedData> call, Response<SGetManualMixedData> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        ArrayList arrayList = new ArrayList();
                        SGetManualMixedData body = response.body();
                        if (body.getData() != null && !AppUtils.isEmpty(body.getData().getFitnessSessionData())) {
                            for (FitnessSessionDataBean fitnessSessionDataBean : body.getData().getFitnessSessionData()) {
                                ManualMixedData manualMixedData = new ManualMixedData();
                                manualMixedData.setClientRefId(fitnessSessionDataBean.getClientRefId());
                                manualMixedData.setBaseUnits(fitnessSessionDataBean.getBaseUnitsManual());
                                manualMixedData.setBp(fitnessSessionDataBean.getValue());
                                manualMixedData.setTzOffset(fitnessSessionDataBean.getTzOffset());
                                manualMixedData.setType(fitnessSessionDataBean.getType());
                                manualMixedData.setStressLevel(Integer.valueOf(fitnessSessionDataBean.getStressLevel()));
                                manualMixedData.setHrv(Integer.valueOf(fitnessSessionDataBean.getHrv()));
                                manualMixedData.setVascAging(fitnessSessionDataBean.getVascAging());
                                manualMixedData.setSpo2(fitnessSessionDataBean.getSpo2());
                                manualMixedData.setHr(Integer.valueOf(fitnessSessionDataBean.getHr()));
                                manualMixedData.setSessionStartDate(fitnessSessionDataBean.getSessionStartDate());
                                manualMixedData.setSessionEndDate(fitnessSessionDataBean.getSessionEndDate());
                                manualMixedData.setTotalSampleCount(Integer.valueOf(fitnessSessionDataBean.getTotalSampleCount()));
                                arrayList.add(manualMixedData);
                            }
                        }
                        this.f6643a.onSuccess(new GetManualMixedDataRes(arrayList));
                        return;
                    }
                    this.f6643a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
                this.f6643a.onError(new CoveApiErrorModel(response.message()));
                return;
            }
            int code = response.code();
            this.f6643a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<SGetManualSPO2Data> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6644a;

        public e(CoveApiListener coveApiListener) {
            this.f6644a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetManualSPO2Data> call, Throwable th) {
            this.f6644a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetManualSPO2Data> call, Response<SGetManualSPO2Data> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        ArrayList arrayList = new ArrayList();
                        SGetManualSPO2Data body = response.body();
                        if (body.getData() != null && !AppUtils.isEmpty(body.getData().getFitnessSessionData())) {
                            for (SPO2FitnessSessionDataBean sPO2FitnessSessionDataBean : body.getData().getFitnessSessionData()) {
                                SPO2FitnessSessionDataBean sPO2FitnessSessionDataBean2 = new SPO2FitnessSessionDataBean();
                                sPO2FitnessSessionDataBean2.setClientRefId(sPO2FitnessSessionDataBean.getClientRefId());
                                sPO2FitnessSessionDataBean2.setSessionStartDate(sPO2FitnessSessionDataBean.getSessionStartDate());
                                sPO2FitnessSessionDataBean2.setSessionEndDate(sPO2FitnessSessionDataBean.getSessionEndDate());
                                sPO2FitnessSessionDataBean2.setBaseUnit(sPO2FitnessSessionDataBean.getBaseUnit());
                                sPO2FitnessSessionDataBean2.setTotalSampleCount(sPO2FitnessSessionDataBean.getTotalSampleCount());
                                sPO2FitnessSessionDataBean2.setTzOffset(sPO2FitnessSessionDataBean.getTzOffset());
                                sPO2FitnessSessionDataBean2.setValue(sPO2FitnessSessionDataBean.getValue());
                                arrayList.add(sPO2FitnessSessionDataBean2);
                            }
                        }
                        this.f6644a.onSuccess(new GetManualSPO2DataRes(arrayList));
                        return;
                    }
                    this.f6644a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
                this.f6644a.onError(new CoveApiErrorModel(response.message()));
                return;
            }
            int code = response.code();
            this.f6644a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6645a;

        public f(CoveApiListener coveApiListener) {
            this.f6645a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6645a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {
                SaveManualSPO2DataRes saveManualSPO2DataRes = new SaveManualSPO2DataRes(response.code());
                saveManualSPO2DataRes.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveManualSPO2DataRes.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6645a.onSuccess(saveManualSPO2DataRes);
                return;
            }
            int code = response.code();
            this.f6645a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SaveManualBPDataReq saveManualBPDataReq, CoveApiListener<SaveManualBpDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (saveManualBPDataReq.getManualBpDataList().size() > 0) {
            SSaveManualData sSaveManualData = new SSaveManualData();
            ArrayList arrayList = new ArrayList();
            for (ManualBpData manualBpData : saveManualBPDataReq.getManualBpDataList()) {
                FitnessSessionDataBean fitnessSessionDataBean = new FitnessSessionDataBean();
                fitnessSessionDataBean.setClientRefId(manualBpData.getClientRefId());
                fitnessSessionDataBean.setBaseUnit(manualBpData.getBaseUnit());
                fitnessSessionDataBean.setValue(manualBpData.getValue());
                fitnessSessionDataBean.setTzOffset(manualBpData.getTzOffset());
                fitnessSessionDataBean.setType(EcgStyleReportUtil.UserInfoKey.BP);
                fitnessSessionDataBean.setSessionStartDate(manualBpData.getSessionStartDate());
                fitnessSessionDataBean.setSessionEndDate(manualBpData.getSessionEndDate());
                fitnessSessionDataBean.setTotalSampleCount(manualBpData.getTotalSampleCount());
                arrayList.add(fitnessSessionDataBean);
            }
            sSaveManualData.setFitnessSessionData(arrayList);
            CoveApi.getService().saveManualData(map, sSaveManualData).enqueue(new a(coveApiListener));
        }
    }

    public static void b(SaveManualMixedDataReq saveManualMixedDataReq, CoveApiListener<SaveManualMixedDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (saveManualMixedDataReq.getManualMixedDataList().size() > 0) {
            SManualMixedData sManualMixedData = new SManualMixedData();
            ArrayList arrayList = new ArrayList();
            for (ManualMixedData manualMixedData : saveManualMixedDataReq.getManualMixedDataList()) {
                FitnessSessionDataBean fitnessSessionDataBean = new FitnessSessionDataBean();
                fitnessSessionDataBean.setClientRefId(manualMixedData.getClientRefId());
                fitnessSessionDataBean.setBaseUnitsManual(manualMixedData.getBaseUnits());
                fitnessSessionDataBean.setValue(manualMixedData.getBp());
                fitnessSessionDataBean.setTzOffset(manualMixedData.getTzOffset());
                fitnessSessionDataBean.setType("MIXED");
                fitnessSessionDataBean.setStressLevel(manualMixedData.getStressLevel().intValue());
                fitnessSessionDataBean.setHrv(manualMixedData.getHrv().intValue());
                fitnessSessionDataBean.setVascAging(manualMixedData.getVascAging());
                fitnessSessionDataBean.setSpo2(manualMixedData.getSpo2());
                fitnessSessionDataBean.setHr(manualMixedData.getHr().intValue());
                fitnessSessionDataBean.setSessionStartDate(manualMixedData.getSessionStartDate());
                fitnessSessionDataBean.setSessionEndDate(manualMixedData.getSessionEndDate());
                fitnessSessionDataBean.setTotalSampleCount(manualMixedData.getTotalSampleCount().intValue());
                arrayList.add(fitnessSessionDataBean);
            }
            sManualMixedData.setFitnessSessionData(arrayList);
            CoveApi.getService().saveManualMixedData(map, sManualMixedData).enqueue(new b(coveApiListener));
        }
    }

    public static void c(SaveManualSPO2DataReq saveManualSPO2DataReq, CoveApiListener<SaveManualSPO2DataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (saveManualSPO2DataReq.getManualSPO2DataList().size() > 0) {
            SSaveManualSPO2Data sSaveManualSPO2Data = new SSaveManualSPO2Data();
            sSaveManualSPO2Data.setFitnessSessionData(saveManualSPO2DataReq.getManualSPO2DataList());
            CoveApi.getService().saveManualSPO2Data(map, sSaveManualSPO2Data).enqueue(new f(coveApiListener));
        }
    }

    public static void d(String str, String str2, CoveApiListener<GetManualBPDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getManualData(map, str, str2, EcgStyleReportUtil.UserInfoKey.BP).enqueue(new c(coveApiListener));
    }

    public static void e(String str, String str2, CoveApiListener<GetManualMixedDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getManualMixedData(map, str, str2, "MIXED").enqueue(new d(coveApiListener));
    }

    public static void f(String str, String str2, CoveApiListener<GetManualSPO2DataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getManualSPO2Data(map, str, str2, "SPO2").enqueue(new e(coveApiListener));
    }

    public static void getManualBPFrom(String str, String str2, CoveApiListener<GetManualBPDataRes, CoveApiErrorModel> coveApiListener) {
        d(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getManualMixedFrom(String str, String str2, CoveApiListener<GetManualMixedDataRes, CoveApiErrorModel> coveApiListener) {
        e(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getManualSPO2From(String str, String str2, CoveApiListener<GetManualSPO2DataRes, CoveApiErrorModel> coveApiListener) {
        f(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveBPData(SaveManualBPDataReq saveManualBPDataReq, CoveApiListener<SaveManualBpDataRes, CoveApiErrorModel> coveApiListener) {
        a(saveManualBPDataReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveManualSPO2Data(SaveManualSPO2DataReq saveManualSPO2DataReq, CoveApiListener<SaveManualSPO2DataRes, CoveApiErrorModel> coveApiListener) {
        c(saveManualSPO2DataReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveMixedData(SaveManualMixedDataReq saveManualMixedDataReq, CoveApiListener<SaveManualMixedDataRes, CoveApiErrorModel> coveApiListener) {
        b(saveManualMixedDataReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getManualBPFrom(HashMap<String, String> hashMap, String str, String str2, CoveApiListener<GetManualBPDataRes, CoveApiErrorModel> coveApiListener) {
        d(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getManualMixedFrom(HashMap<String, String> hashMap, String str, String str2, CoveApiListener<GetManualMixedDataRes, CoveApiErrorModel> coveApiListener) {
        e(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getManualSPO2From(HashMap<String, String> hashMap, String str, String str2, CoveApiListener<GetManualSPO2DataRes, CoveApiErrorModel> coveApiListener) {
        f(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveBPData(HashMap<String, String> hashMap, SaveManualBPDataReq saveManualBPDataReq, CoveApiListener<SaveManualBpDataRes, CoveApiErrorModel> coveApiListener) {
        a(saveManualBPDataReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveManualSPO2Data(HashMap<String, String> hashMap, SaveManualSPO2DataReq saveManualSPO2DataReq, CoveApiListener<SaveManualSPO2DataRes, CoveApiErrorModel> coveApiListener) {
        c(saveManualSPO2DataReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveMixedData(HashMap<String, String> hashMap, SaveManualMixedDataReq saveManualMixedDataReq, CoveApiListener<SaveManualMixedDataRes, CoveApiErrorModel> coveApiListener) {
        b(saveManualMixedDataReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
