package com.coveiot.android.leonardo.utils.diagnostic;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.android.bleabstract.request.GetDiagnosticFeatureTestRequest;
import com.coveiot.android.bleabstract.request.SetDiagnosticControlRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetDiagnosticFeatureTestResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DiagnosticCommandHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void runDiagnosticFeatureTest$default(Companion companion, Context context, DiagnosticTestModel diagnosticTestModel, DiagnosticRunTestCommandResultListener diagnosticRunTestCommandResultListener, int i, Object obj) {
            if ((i & 4) != 0) {
                diagnosticRunTestCommandResultListener = null;
            }
            companion.runDiagnosticFeatureTest(context, diagnosticTestModel, diagnosticRunTestCommandResultListener);
        }

        public static /* synthetic */ void sendEnterOrExitCommand$default(Companion companion, Context context, boolean z, DiagnosticEnterExitCommandResultListener diagnosticEnterExitCommandResultListener, int i, Object obj) {
            if ((i & 4) != 0) {
                diagnosticEnterExitCommandResultListener = null;
            }
            companion.sendEnterOrExitCommand(context, z, diagnosticEnterExitCommandResultListener);
        }

        public final void runDiagnosticFeatureTest(@NotNull Context context, @NotNull final DiagnosticTestModel diagnosticTestModel, @Nullable final DiagnosticRunTestCommandResultListener diagnosticRunTestCommandResultListener) {
            BleApi bleApi;
            DeviceSupportedFeatures deviceSupportedFeatures;
            BleApi bleApi2;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(diagnosticTestModel, "diagnosticTestModel");
            BleApiManager bleApiManager = BleApiManager.getInstance(context);
            if (((bleApiManager == null || (bleApi2 = bleApiManager.getBleApi()) == null) ? null : bleApi2.getConnectionStatus()) != ConnectionStatus.CONNECTED) {
                if (diagnosticRunTestCommandResultListener != null) {
                    diagnosticRunTestCommandResultListener.onError(context.getResources().getString(R.string.band_disconnected));
                    return;
                }
                return;
            }
            BleApiManager bleApiManager2 = BleApiManager.getInstance(context);
            if ((bleApiManager2 == null || (bleApi = bleApiManager2.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isDiagnosticTestSupported()) ? false : true) {
                GetDiagnosticFeatureTestRequest.Builder builder = new GetDiagnosticFeatureTestRequest.Builder();
                WatchDiagnosticFeatureType byId = WatchDiagnosticFeatureType.getById(diagnosticTestModel.getTestCode());
                Intrinsics.checkNotNullExpressionValue(byId, "getById(diagnosticTestModel.testCode)");
                builder.setFeatureType(byId);
                int testCode = diagnosticTestModel.getTestCode();
                if (testCode == WatchDiagnosticFeatureType.DISPLAY_TEST.getId()) {
                    if (true ^ diagnosticTestModel.getChildTestCodes().isEmpty()) {
                        builder.setColor(diagnosticTestModel.getChildTestCodes().remove(0).intValue());
                    }
                } else if (testCode == WatchDiagnosticFeatureType.BUTTON_TEST.getId()) {
                    builder.setButtonPosition(diagnosticTestModel.getExtensionTestCode());
                } else if (testCode == WatchDiagnosticFeatureType.SENSOR_TEST.getId()) {
                    builder.setSensorType(diagnosticTestModel.getExtensionTestCode());
                }
                BleApiManager.getInstance(context).getBleApi().getData(builder.build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler$Companion$runDiagnosticFeatureTest$1
                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        DiagnosticTestModel.this.setTestStatus(TestingStatus.FAILED);
                        DiagnosticCommandHandler.DiagnosticRunTestCommandResultListener diagnosticRunTestCommandResultListener2 = diagnosticRunTestCommandResultListener;
                        if (diagnosticRunTestCommandResultListener2 != null) {
                            diagnosticRunTestCommandResultListener2.onSuccess(DiagnosticTestModel.this);
                        }
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        if (response.getResponseData() instanceof GetDiagnosticFeatureTestResponse) {
                            Object responseData = response.getResponseData();
                            Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetDiagnosticFeatureTestResponse");
                            GetDiagnosticFeatureTestResponse getDiagnosticFeatureTestResponse = (GetDiagnosticFeatureTestResponse) responseData;
                            if (!DiagnosticTestModel.this.getChildTestCodes().isEmpty()) {
                                DiagnosticTestModel.this.setTestStatus(TestingStatus.IN_PROGRESS);
                            } else if (DiagnosticTestModel.this.getTestCode() != WatchDiagnosticFeatureType.DISPLAY_TEST.getId() && DiagnosticTestModel.this.getTestCode() != WatchDiagnosticFeatureType.VIBRATION_TEST.getId()) {
                                DiagnosticTestModel.this.setTestStatus(getDiagnosticFeatureTestResponse.getStatus() == 1 ? TestingStatus.PASSED : TestingStatus.FAILED);
                            } else {
                                DiagnosticTestModel.this.setTestStatus(TestingStatus.IN_PROGRESS);
                            }
                            DiagnosticCommandHandler.DiagnosticRunTestCommandResultListener diagnosticRunTestCommandResultListener2 = diagnosticRunTestCommandResultListener;
                            if (diagnosticRunTestCommandResultListener2 != null) {
                                diagnosticRunTestCommandResultListener2.onSuccess(DiagnosticTestModel.this);
                            }
                        }
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onProgressUpdate(@NotNull ProgressData progress) {
                        Intrinsics.checkNotNullParameter(progress, "progress");
                    }
                });
            }
        }

        public final void sendEnterOrExitCommand(@NotNull final Context context, final boolean z, @Nullable final DiagnosticEnterExitCommandResultListener diagnosticEnterExitCommandResultListener) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDiagnosticTestSupported()) {
                BleApiManager.getInstance(context).getBleApi().setUserSettings(new SetDiagnosticControlRequest.Builder().SetDiagnosticProtocolControlRequest(z).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler$Companion$sendEnterOrExitCommand$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        DiagnosticCommandHandler.DiagnosticEnterExitCommandResultListener diagnosticEnterExitCommandResultListener2 = DiagnosticCommandHandler.DiagnosticEnterExitCommandResultListener.this;
                        if (diagnosticEnterExitCommandResultListener2 != null) {
                            diagnosticEnterExitCommandResultListener2.onError(context.getResources().getString(R.string.some_error_occurred_try_again));
                        }
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        DiagnosticCommandHandler.DiagnosticEnterExitCommandResultListener diagnosticEnterExitCommandResultListener2 = DiagnosticCommandHandler.DiagnosticEnterExitCommandResultListener.this;
                        if (diagnosticEnterExitCommandResultListener2 != null) {
                            diagnosticEnterExitCommandResultListener2.onSuccess(z);
                        }
                    }
                });
            } else if (diagnosticEnterExitCommandResultListener != null) {
                diagnosticEnterExitCommandResultListener.onError(context.getResources().getString(R.string.some_error_occurred_try_again));
            }
        }
    }

    /* loaded from: classes5.dex */
    public interface DiagnosticEnterExitCommandResultListener {
        void onError(@Nullable String str);

        void onSuccess(boolean z);
    }

    /* loaded from: classes5.dex */
    public interface DiagnosticRunTestCommandResultListener {
        void onError(@Nullable String str);

        void onSuccess(@NotNull DiagnosticTestModel diagnosticTestModel);
    }
}
