package com.coveiot.android.leonardo.utils.diagnostic;

import android.content.Context;
import android.os.Build;
import com.coveiot.android.bleabstract.models.HButtonType;
import com.coveiot.android.bleabstract.models.SensorType;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.diagnostic.DiagnosticReportHandler;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.diagnostics.DiagnosticApiManager;
import com.coveiot.coveaccess.diagnostics.model.Device;
import com.coveiot.coveaccess.diagnostics.model.DiagnosticResultRequest;
import com.coveiot.coveaccess.diagnostics.model.ResultType;
import com.coveiot.coveaccess.diagnostics.model.TestSummary;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
/* loaded from: classes5.dex */
public final class DiagnosticReportHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void sendDiagnosticReport$default(Companion companion, Context context, List list, DiagnosticReportDownloadListener diagnosticReportDownloadListener, int i, Object obj) {
            if ((i & 4) != 0) {
                diagnosticReportDownloadListener = null;
            }
            companion.sendDiagnosticReport(context, list, diagnosticReportDownloadListener);
        }

        public final void sendDiagnosticReport(@NotNull final Context context, @NotNull List<DiagnosticTestModel> diagnosticTestList, @Nullable final DiagnosticReportDownloadListener diagnosticReportDownloadListener) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(diagnosticTestList, "diagnosticTestList");
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            DiagnosticResultRequest diagnosticResultRequest = new DiagnosticResultRequest();
            diagnosticResultRequest.setTzOffset(PayUtils.INSTANCE.getTimeZoneOffset());
            Device device = new Device();
            device.setBtMacAddress(bleDeviceInfo.getMacAddress());
            device.setBtName(bleDeviceInfo.getmDeviceName());
            String string = context.getString(R.string.cove_client_id);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.cove_client_id)");
            device.setCustomerId(Integer.valueOf(Integer.parseInt(string)));
            device.setFirmwareVersion(bleDeviceInfo.getFirmwareRevision());
            device.setModelNumber(bleDeviceInfo.getmModelNumber());
            diagnosticResultRequest.setDevice(device);
            TestSummary testSummary = new TestSummary();
            for (DiagnosticTestModel diagnosticTestModel : diagnosticTestList) {
                if (diagnosticTestModel.getTestCode() == WatchDiagnosticFeatureType.CHARGING_TEST.getId()) {
                    if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                        testSummary.setCharger(ResultType.PASSED.getResultType());
                    } else {
                        testSummary.setCharger(ResultType.FAILED.getResultType());
                    }
                } else if (diagnosticTestModel.getTestCode() == WatchDiagnosticFeatureType.DISPLAY_TEST.getId()) {
                    if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                        testSummary.setDisplay(ResultType.PASSED.getResultType());
                    } else {
                        testSummary.setDisplay(ResultType.FAILED.getResultType());
                    }
                } else if (diagnosticTestModel.getTestCode() == WatchDiagnosticFeatureType.VIBRATION_TEST.getId()) {
                    if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                        testSummary.setVibration(ResultType.PASSED.getResultType());
                    } else {
                        testSummary.setVibration(ResultType.FAILED.getResultType());
                    }
                } else if (diagnosticTestModel.getTestCode() == WatchDiagnosticFeatureType.BUTTON_TEST.getId()) {
                    if (diagnosticTestModel.getExtensionTestCode() == HButtonType.H2.getPosition()) {
                        if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                            testSummary.setButton(ResultType.PASSED.getResultType());
                        } else {
                            testSummary.setButton(ResultType.FAILED.getResultType());
                        }
                    } else if (diagnosticTestModel.getExtensionTestCode() == HButtonType.H3.getPosition()) {
                        if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                            testSummary.setButton(ResultType.PASSED.getResultType());
                        } else {
                            testSummary.setButton(ResultType.FAILED.getResultType());
                        }
                    } else if (diagnosticTestModel.getExtensionTestCode() == HButtonType.H4.getPosition()) {
                        if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                            testSummary.setButton(ResultType.PASSED.getResultType());
                        } else {
                            testSummary.setButton(ResultType.FAILED.getResultType());
                        }
                    } else if (diagnosticTestModel.getExtensionTestCode() == HButtonType.H5.getPosition()) {
                        if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                            testSummary.setButton(ResultType.PASSED.getResultType());
                        } else {
                            testSummary.setButton(ResultType.FAILED.getResultType());
                        }
                    } else if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                        testSummary.setButton(ResultType.PASSED.getResultType());
                    } else {
                        testSummary.setButton(ResultType.FAILED.getResultType());
                    }
                } else if (diagnosticTestModel.getTestCode() == WatchDiagnosticFeatureType.TOUCHSCREEN_TEST.getId()) {
                    if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                        testSummary.setTouchscreen(ResultType.PASSED.getResultType());
                    } else {
                        testSummary.setTouchscreen(ResultType.FAILED.getResultType());
                    }
                } else if (diagnosticTestModel.getTestCode() == WatchDiagnosticFeatureType.SENSOR_TEST.getId()) {
                    if (diagnosticTestModel.getExtensionTestCode() == SensorType.ACCELEROMETER.getType()) {
                        if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                            testSummary.setAccelerometer(ResultType.PASSED.getResultType());
                        } else {
                            testSummary.setAccelerometer(ResultType.FAILED.getResultType());
                        }
                    } else if (diagnosticTestModel.getExtensionTestCode() == SensorType.PPG.getType()) {
                        if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                            testSummary.setPpg(ResultType.PASSED.getResultType());
                        } else {
                            testSummary.setPpg(ResultType.FAILED.getResultType());
                        }
                    } else if (diagnosticTestModel.getExtensionTestCode() == SensorType.TEMPERATURE.getType()) {
                        if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                            testSummary.setTemperature(ResultType.PASSED.getResultType());
                        } else {
                            testSummary.setTemperature(ResultType.FAILED.getResultType());
                        }
                    } else if (diagnosticTestModel.getTestStatus() == TestingStatus.PASSED) {
                        ResultType resultType = ResultType.PASSED;
                        testSummary.setAccelerometer(resultType.getResultType());
                        testSummary.setPpg(resultType.getResultType());
                        testSummary.setTemperature(resultType.getResultType());
                    } else {
                        ResultType resultType2 = ResultType.FAILED;
                        testSummary.setAccelerometer(resultType2.getResultType());
                        testSummary.setPpg(resultType2.getResultType());
                        testSummary.setTemperature(resultType2.getResultType());
                    }
                }
            }
            diagnosticResultRequest.setTestSummary(testSummary);
            DiagnosticApiManager.saveDiagnosticTestResult(diagnosticResultRequest, new CoveApiListener<Response<ResponseBody>, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.utils.diagnostic.DiagnosticReportHandler$Companion$sendDiagnosticReport$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    DiagnosticReportHandler.DiagnosticReportDownloadListener diagnosticReportDownloadListener2 = diagnosticReportDownloadListener;
                    if (diagnosticReportDownloadListener2 != null) {
                        diagnosticReportDownloadListener2.onError(context.getResources().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable Response<ResponseBody> response) {
                    ResponseBody body;
                    Headers headers;
                    String str;
                    String substringAfter$default = (response == null || (headers = response.headers()) == null || (str = headers.get(HttpHeaders.CONTENT_DISPOSITION)) == null) ? null : StringsKt__StringsKt.substringAfter$default(str, "filename=", (String) null, 2, (Object) null);
                    if (Build.VERSION.SDK_INT >= 29) {
                        PayUtils payUtils = PayUtils.INSTANCE;
                        Context context2 = context;
                        body = response != null ? response.body() : null;
                        Intrinsics.checkNotNull(body);
                        payUtils.saveFileUsingMediaStore(context2, body.byteStream(), substringAfter$default);
                    } else {
                        PayUtils payUtils2 = PayUtils.INSTANCE;
                        Context context3 = context;
                        body = response != null ? response.body() : null;
                        Intrinsics.checkNotNull(body);
                        payUtils2.saveFile(context3, body.byteStream(), substringAfter$default);
                    }
                    DiagnosticReportHandler.DiagnosticReportDownloadListener diagnosticReportDownloadListener2 = diagnosticReportDownloadListener;
                    if (diagnosticReportDownloadListener2 != null) {
                        diagnosticReportDownloadListener2.onSuccess();
                    }
                }
            });
        }
    }

    /* loaded from: classes5.dex */
    public interface DiagnosticReportDownloadListener {
        void onError(@Nullable String str);

        void onSuccess();
    }
}
