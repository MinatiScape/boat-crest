package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveAutoHRSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAutoHRSettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AutoHrViewModel extends AndroidViewModel {
    @NotNull
    public final String d;
    public DialogListener dialogListener;
    public final Context e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoHrViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = "AutoHrViewModel";
        this.e = application.getBaseContext();
    }

    public static final void d(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public final void b(BleBaseError bleBaseError) {
        getDialogListener().onDismiss();
        getDialogListener().showErrorDialog();
    }

    public final void c(int i, boolean z) {
        if (AppUtils.isNetConnected(this.e)) {
            SaveAutoHRSettingReq saveAutoHRSettingReq = new SaveAutoHRSettingReq();
            saveAutoHRSettingReq.setActive(Boolean.valueOf(z));
            if (i == 60) {
                saveAutoHRSettingReq.setInterval("01:00:00");
            } else if (i < 10) {
                saveAutoHRSettingReq.setInterval("00:0" + i + ":00");
            } else {
                saveAutoHRSettingReq.setInterval("00:" + i + ":00");
            }
            CoveUserAppSettings.saveAutoHeartRateInterval(saveAutoHRSettingReq, new CoveApiListener<SaveAutoHRSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoHrViewModel$uploadAutoHrToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    AutoHrViewModel.this.getDialogListener().onDismiss();
                    Toast.makeText(AutoHrViewModel.this.getContext(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null, 0).show();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveAutoHRSettingsRes saveAutoHRSettingsRes) {
                    AutoHrViewModel.this.getDialogListener().onDismiss();
                    AutoHrViewModel.this.getDialogListener().showSuccessDialog();
                }
            });
            return;
        }
        getDialogListener().onDismiss();
        Context context = this.e;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String string = this.e.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = this.e.getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoHrViewModel.d(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void callSaveAndBleApi(final int i, final boolean z) {
        getDialogListener().onShowProgressDialog();
        BleApiManager.getInstance(this.e).getBleApi().setUserSettings(new HeartRateTimeIntervalRequest.Builder(z ? i : 0).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoHrViewModel$callSaveAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                AutoHrViewModel.this.b(error);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(AutoHrViewModel.this.getContext()).saveAutoHrEnabled(z);
                UserDataManager.getInstance(AutoHrViewModel.this.getContext()).saveAutoHrInterval(i);
                AutoHrViewModel.this.c(i, z);
            }
        });
    }

    public final Context getContext() {
        return this.e;
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
    public final String getTAG() {
        return this.d;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
