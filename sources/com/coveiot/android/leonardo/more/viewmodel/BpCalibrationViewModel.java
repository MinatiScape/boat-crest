package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BpCalibrationDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BpCalibrationData;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class BpCalibrationViewModel extends AndroidViewModel {
    public final Context d;
    public DialogListener dialogListener;
    public final String e;
    public ViewModelListener mListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BpCalibrationViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = application.getBaseContext();
        this.e = BpCalibrationViewModel.class.getSimpleName();
    }

    public final void calibrateBp(@NotNull final BpCalibrationData bpCalibrationData) {
        Intrinsics.checkNotNullParameter(bpCalibrationData, "bpCalibrationData");
        getDialogListener().onShowProgressDialog();
        BleApiManager.getInstance(this.d).getBleApi().setUserSettings(new BpCalibrationDataRequest.Builder().setDbpCalculatingSign(bpCalibrationData.getCalculatingSignDbp()).setDbp(bpCalibrationData.getDbp()).setSbpCalculatingSign(bpCalibrationData.getCalculatingSignSbp()).setSbp(bpCalibrationData.getSbp()).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.BpCalibrationViewModel$calibrateBp$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BpCalibrationViewModel.this.getDialogListener().onDismiss();
                BpCalibrationViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(BpCalibrationViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(BpCalibrationViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(BpCalibrationViewModel.this.getContext()).saveBpCalibrationSettings(bpCalibrationData);
                BpCalibrationViewModel.this.getDialogListener().onDismiss();
                Drawable drawable = BpCalibrationViewModel.this.getContext().getResources().getDrawable(R.drawable.success_fw_icon);
                Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…drawable.success_fw_icon)");
                drawable.setTint(BpCalibrationViewModel.this.getContext().getColor(R.color.colorPrimary));
                BpCalibrationViewModel.this.getDialogListener().showSuccessDialog();
            }
        });
    }

    public final Context getContext() {
        return this.d;
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
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

    public final String getTAG() {
        return this.e;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }
}
