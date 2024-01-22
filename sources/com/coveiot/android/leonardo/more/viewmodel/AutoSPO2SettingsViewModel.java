package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.Spo2TimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoSpo2SettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoSpo2SettingsRes;
import com.coveiot.coveaccess.userdevicesetting.model.AutoSpo2Settings;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoSPO2SettingsData;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AutoSPO2SettingsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5174a;
    @NotNull
    public final String b;
    public DialogListener dialogListener;

    public AutoSPO2SettingsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5174a = context;
        this.b = "AutoSPO2SettingsViewModel";
    }

    public final void callSaveSpo2SettingsAndBleApi(final boolean z) {
        getDialogListener().onShowProgressDialog();
        final AutoSPO2SettingsData companion = AutoSPO2SettingsData.Companion.getInstance();
        Intrinsics.checkNotNull(companion);
        companion.setAutoSpO2(z);
        BleApiManager.getInstance(this.f5174a).getBleApi().setUserSettings(new Spo2TimeIntervalRequest.Builder(z ? 10 : 0).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoSPO2SettingsViewModel$callSaveSpo2SettingsAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                AutoSPO2SettingsViewModel.this.getDialogListener().onDismiss();
                AutoSPO2SettingsViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(AutoSPO2SettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(AutoSPO2SettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(AutoSPO2SettingsViewModel.this.getContext()).saveAutoSPO2Settings(companion);
                AutoSPO2SettingsViewModel.this.senDataToServer(z);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5174a;
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
    public final AutoSPO2SettingsData getSPo2SettingsDataFromPref() {
        try {
            AutoSPO2SettingsData autoSPO2SettingsData = UserDataManager.getInstance(this.f5174a).getAutoSPO2SettingsData();
            if (autoSPO2SettingsData == null) {
                AutoSPO2SettingsData companion = AutoSPO2SettingsData.Companion.getInstance();
                Intrinsics.checkNotNull(companion);
                companion.setAutoSpO2(false);
            }
            Intrinsics.checkNotNullExpressionValue(autoSPO2SettingsData, "{\n            val prefSt…essSettingsData\n        }");
            return autoSPO2SettingsData;
        } catch (Exception unused) {
            AutoSPO2SettingsData companion2 = AutoSPO2SettingsData.Companion.getInstance();
            Intrinsics.checkNotNull(companion2);
            companion2.setAutoSpO2(false);
            return companion2;
        }
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void senDataToServer(boolean z) {
        SaveAutoSpo2SettingsReq saveAutoSpo2SettingsReq = new SaveAutoSpo2SettingsReq();
        AutoSpo2Settings autoSpo2Settings = new AutoSpo2Settings();
        autoSpo2Settings.setActive(z);
        saveAutoSpo2SettingsReq.setAutoSPO2Settings(autoSpo2Settings);
        CoveUserDeviceSettings.saveAutoSpo2Settings(saveAutoSpo2SettingsReq, new CoveApiListener<SaveAutoSpo2SettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoSPO2SettingsViewModel$senDataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                AutoSPO2SettingsViewModel.this.getDialogListener().onDismiss();
                AutoSPO2SettingsViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveAutoSpo2SettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                AutoSPO2SettingsViewModel.this.getDialogListener().onDismiss();
                if (object.getCode() == 200) {
                    AutoSPO2SettingsViewModel.this.getDialogListener().showSuccessDialog();
                } else {
                    AutoSPO2SettingsViewModel.this.getDialogListener().showErrorDialog();
                }
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
