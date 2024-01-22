package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler;
import com.coveiot.android.leonardo.utils.diagnostic.DiagnosticReportHandler;
import com.coveiot.android.leonardo.utils.diagnostic.DiagnosticTestButtonTypeProvider;
import com.coveiot.android.leonardo.utils.diagnostic.DiagnosticTestDisplayColorProvider;
import com.coveiot.android.leonardo.utils.diagnostic.DiagnosticTestSensorTypeProvider;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DiagnosticsTestViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5181a;
    @NotNull
    public MutableLiveData<Boolean> b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @NotNull
    public MutableLiveData<DiagnosticTestModel> d;
    public ViewModelListener mListener;

    public DiagnosticsTestViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5181a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public final void diagnosticFeatureTest(@NotNull Context mContext, @NotNull DiagnosticTestModel diagnosticTestModel) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(diagnosticTestModel, "diagnosticTestModel");
        DiagnosticCommandHandler.Companion.runDiagnosticFeatureTest(mContext, diagnosticTestModel, new DiagnosticCommandHandler.DiagnosticRunTestCommandResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.DiagnosticsTestViewModel$diagnosticFeatureTest$1
            @Override // com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler.DiagnosticRunTestCommandResultListener
            public void onError(@Nullable String str) {
                if (str != null) {
                    DiagnosticsTestViewModel.this.getMListener().onDataFailure(str);
                }
            }

            @Override // com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler.DiagnosticRunTestCommandResultListener
            public void onSuccess(@NotNull DiagnosticTestModel diagnosticTestModel2) {
                Intrinsics.checkNotNullParameter(diagnosticTestModel2, "diagnosticTestModel");
                DiagnosticsTestViewModel.this.getDiagnosticTestResult().postValue(diagnosticTestModel2);
            }
        });
    }

    public final void diagnosticsEnterOrExit(@NotNull Context mContext, boolean z) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        DiagnosticCommandHandler.Companion.sendEnterOrExitCommand(mContext, z, new DiagnosticCommandHandler.DiagnosticEnterExitCommandResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.DiagnosticsTestViewModel$diagnosticsEnterOrExit$1
            @Override // com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler.DiagnosticEnterExitCommandResultListener
            public void onError(@Nullable String str) {
                if (str != null) {
                    DiagnosticsTestViewModel.this.getMListener().onDataFailure(str);
                }
            }

            @Override // com.coveiot.android.leonardo.utils.diagnostic.DiagnosticCommandHandler.DiagnosticEnterExitCommandResultListener
            public void onSuccess(boolean z2) {
                DiagnosticsTestViewModel.this.getDiagnosticState().postValue(Boolean.valueOf(z2));
            }
        });
    }

    @NotNull
    public final List<DiagnosticTestModel> getAllDiagnosticTest() {
        ArrayList arrayList = new ArrayList();
        int id = WatchDiagnosticFeatureType.CHARGING_TEST.getId();
        String string = this.f5181a.getString(R.string.charger_test);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.charger_test)");
        arrayList.add(new DiagnosticTestModel(id, string, null, true, 0, null, 52, null));
        arrayList.addAll(DiagnosticTestDisplayColorProvider.Companion.getDisplayTestModels(this.f5181a));
        int id2 = WatchDiagnosticFeatureType.VIBRATION_TEST.getId();
        String string2 = this.f5181a.getString(R.string.vibration_test);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.vibration_test)");
        arrayList.add(new DiagnosticTestModel(id2, string2, null, false, 0, null, 60, null));
        arrayList.addAll(DiagnosticTestButtonTypeProvider.Companion.getHButtonTestModels(this.f5181a));
        int id3 = WatchDiagnosticFeatureType.TOUCHSCREEN_TEST.getId();
        String string3 = this.f5181a.getString(R.string.touchscreen_test);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.touchscreen_test)");
        arrayList.add(new DiagnosticTestModel(id3, string3, null, false, 0, null, 60, null));
        arrayList.addAll(DiagnosticTestSensorTypeProvider.Companion.getSensorTestModels(this.f5181a));
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f5181a;
    }

    @NotNull
    public final MutableLiveData<Boolean> getDiagnosticState() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<DiagnosticTestModel> getDiagnosticTestResult() {
        return this.d;
    }

    @NotNull
    public final ViewModelListener getMListener() {
        ViewModelListener viewModelListener = this.mListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    @NotNull
    public final MutableLiveData<Boolean> isDiagnosticDownloaded() {
        return this.c;
    }

    public final void sendDiagnosticReport(@NotNull final Context context, @NotNull List<DiagnosticTestModel> diagnosticTestList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(diagnosticTestList, "diagnosticTestList");
        DiagnosticReportHandler.Companion.sendDiagnosticReport(context, diagnosticTestList, new DiagnosticReportHandler.DiagnosticReportDownloadListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.DiagnosticsTestViewModel$sendDiagnosticReport$1
            @Override // com.coveiot.android.leonardo.utils.diagnostic.DiagnosticReportHandler.DiagnosticReportDownloadListener
            public void onError(@Nullable String str) {
                ViewModelListener mListener = DiagnosticsTestViewModel.this.getMListener();
                String string = context.getResources().getString(R.string.something_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ing.something_went_wrong)");
                mListener.onDataFailure(string);
            }

            @Override // com.coveiot.android.leonardo.utils.diagnostic.DiagnosticReportHandler.DiagnosticReportDownloadListener
            public void onSuccess() {
                DiagnosticsTestViewModel.this.isDiagnosticDownloaded().postValue(Boolean.TRUE);
            }
        });
    }

    public final void setDiagnosticDownloaded(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setDiagnosticState(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setDiagnosticTestResult(@NotNull MutableLiveData<DiagnosticTestModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }
}
