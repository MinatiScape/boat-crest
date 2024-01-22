package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveAutoTemperatureSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAutoTemperatureSettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AutoTemperatureViewModel extends AndroidViewModel {
    @NotNull
    public final String d;
    public DialogListener dialogListener;
    public final Context e;
    @NotNull
    public final MutableLiveData<String> f;
    @NotNull
    public final MutableLiveData<String> g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoTemperatureViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = "AutoTemperatureViewModel";
        this.e = application.getBaseContext();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
    }

    public final void a(BleBaseError bleBaseError) {
        getDialogListener().onDismiss();
        getDialogListener().showErrorDialog();
    }

    public final void b(int i, boolean z) {
        if (AppUtils.isNetConnected(this.e)) {
            SaveAutoTemperatureSettingReq saveAutoTemperatureSettingReq = new SaveAutoTemperatureSettingReq();
            saveAutoTemperatureSettingReq.setActive(Boolean.valueOf(z));
            if (i == 60) {
                saveAutoTemperatureSettingReq.setInterval("01:00:00");
            } else if (i < 10) {
                saveAutoTemperatureSettingReq.setInterval("00:0" + i + ":00");
            } else if (i > 60) {
                saveAutoTemperatureSettingReq.setInterval('0' + (i / 60) + ":0" + (i % 60) + ":00");
            }
            CoveUserAppSettings.saveAutoTemperatureInterval(saveAutoTemperatureSettingReq, new CoveApiListener<SaveAutoTemperatureSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoTemperatureViewModel$uploadAutoTemperatureToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    AutoTemperatureViewModel.this.getDialogListener().onDismiss();
                    if (coveApiErrorModel != null) {
                        AutoTemperatureViewModel autoTemperatureViewModel = AutoTemperatureViewModel.this;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "it.msg");
                        autoTemperatureViewModel.setToastMessage(msg);
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveAutoTemperatureSettingsRes saveAutoTemperatureSettingsRes) {
                    AutoTemperatureViewModel.this.getDialogListener().onDismiss();
                    AutoTemperatureViewModel.this.getDialogListener().showSuccessDialog();
                }
            });
            return;
        }
        getDialogListener().onDismiss();
        String string = this.e.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        setDialogMessage(string);
    }

    public final void callSaveAndBleApi(final int i, final boolean z) {
        getDialogListener().onShowProgressDialog();
        BleApiManager.getInstance(this.e).getBleApi().setUserSettings(new TemperatureTimeIntervalRequest.Builder(z ? i : 0).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoTemperatureViewModel$callSaveAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                AutoTemperatureViewModel.this.a(error);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(AutoTemperatureViewModel.this.getContext()).saveAutoTemperatureEnabled(Boolean.valueOf(z));
                UserDataManager.getInstance(AutoTemperatureViewModel.this.getContext()).saveAutoTemperatureInterval(Integer.valueOf(i));
                AutoTemperatureViewModel.this.b(i, z);
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

    public final void setDialogMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.g.setValue(message);
    }

    public final void setToastMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.f.setValue(message);
    }

    @Nullable
    public final LiveData<String> showDialogMessage() {
        return this.g;
    }

    @Nullable
    public final LiveData<String> showToastMessage() {
        return this.f;
    }
}
