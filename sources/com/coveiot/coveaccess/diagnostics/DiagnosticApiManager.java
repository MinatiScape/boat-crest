package com.coveiot.coveaccess.diagnostics;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.diagnostics.model.Device;
import com.coveiot.coveaccess.diagnostics.model.DiagnosticResultRequest;
import com.coveiot.coveaccess.diagnostics.model.TestSummary;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class DiagnosticApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6475a;

        public a(CoveApiListener coveApiListener) {
            this.f6475a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6475a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6475a.onSuccess(response);
            } else {
                this.f6475a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    public static void a(DiagnosticResultRequest diagnosticResultRequest, CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        DiagnosticResultRequest diagnosticResultRequest2 = new DiagnosticResultRequest();
        diagnosticResultRequest2.setTzOffset(diagnosticResultRequest.getTzOffset());
        if (diagnosticResultRequest.getDevice() != null) {
            Device device = new Device();
            device.setBtMacAddress(diagnosticResultRequest.getDevice().getBtMacAddress());
            device.setBtName(diagnosticResultRequest.getDevice().getBtName());
            device.setCustomerId(diagnosticResultRequest.getDevice().getCustomerId());
            device.setFirmwareVersion(diagnosticResultRequest.getDevice().getFirmwareVersion());
            device.setModelNumber(diagnosticResultRequest.getDevice().getModelNumber());
            diagnosticResultRequest2.setDevice(device);
        }
        if (diagnosticResultRequest.getTestSummary() != null) {
            TestSummary testSummary = new TestSummary();
            if (diagnosticResultRequest.getTestSummary().getCharger() != null) {
                testSummary.setCharger(diagnosticResultRequest.getTestSummary().getCharger());
            }
            if (diagnosticResultRequest.getTestSummary().getDisplay() != null) {
                testSummary.setDisplay(diagnosticResultRequest.getTestSummary().getDisplay());
            }
            if (diagnosticResultRequest.getTestSummary().getVibration() != null) {
                testSummary.setVibration(diagnosticResultRequest.getTestSummary().getVibration());
            }
            if (diagnosticResultRequest.getTestSummary().getButton() != null) {
                testSummary.setButton(diagnosticResultRequest.getTestSummary().getButton());
            }
            if (diagnosticResultRequest.getTestSummary().getTouchscreen() != null) {
                testSummary.setTouchscreen(diagnosticResultRequest.getTestSummary().getTouchscreen());
            }
            if (diagnosticResultRequest.getTestSummary().getAccelerometer() != null) {
                testSummary.setAccelerometer(diagnosticResultRequest.getTestSummary().getAccelerometer());
            }
            if (diagnosticResultRequest.getTestSummary().getTemperature() != null) {
                testSummary.setTemperature(diagnosticResultRequest.getTestSummary().getTemperature());
            }
            if (diagnosticResultRequest.getTestSummary().getPpg() != null) {
                testSummary.setPpg(diagnosticResultRequest.getTestSummary().getPpg());
            }
            diagnosticResultRequest2.setTestSummary(testSummary);
        }
        CoveApi.getService().diagnosticReportDownload(map, diagnosticResultRequest2).enqueue(new a(coveApiListener));
    }

    public static void saveDiagnosticTestResult(DiagnosticResultRequest diagnosticResultRequest, CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener) {
        a(diagnosticResultRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveDiagnosticTestResult(HashMap<String, String> hashMap, DiagnosticResultRequest diagnosticResultRequest, CoveApiListener<Response<ResponseBody>, CoveApiErrorModel> coveApiListener) {
        a(diagnosticResultRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
