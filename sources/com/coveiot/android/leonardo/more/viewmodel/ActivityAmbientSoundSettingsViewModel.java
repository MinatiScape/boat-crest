package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.SetAmbientSoundLevelControlRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.SaveAmbientSoundSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAmbientSoundSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.model.AmbientSoundSettings;
import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAmbientSoundSettingsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5138a;
    public DialogListener dialogListener;

    public ActivityAmbientSoundSettingsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5138a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f5138a;
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

    public final void senDataToServer(boolean z) {
        SaveAmbientSoundSettingsReq saveAmbientSoundSettingsReq = new SaveAmbientSoundSettingsReq();
        AmbientSoundSettings ambientSoundSettings = new AmbientSoundSettings();
        ambientSoundSettings.setActive(z);
        saveAmbientSoundSettingsReq.setAmbientSoundSettings(ambientSoundSettings);
        CoveUserDeviceSettings.saveAmbientSoundSettings(saveAmbientSoundSettingsReq, new CoveApiListener<SaveAmbientSoundSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityAmbientSoundSettingsViewModel$senDataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ActivityAmbientSoundSettingsViewModel.this.getDialogListener().onDismiss();
                ActivityAmbientSoundSettingsViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveAmbientSoundSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                ActivityAmbientSoundSettingsViewModel.this.getDialogListener().onDismiss();
                if (object.getCode() == 200) {
                    ActivityAmbientSoundSettingsViewModel.this.getDialogListener().showSuccessDialog();
                } else {
                    ActivityAmbientSoundSettingsViewModel.this.getDialogListener().showErrorDialog();
                }
            }
        });
    }

    public final void setAmbientSoundSettingsToWatch(final boolean z) {
        BleApiManager.getInstance(this.f5138a).getBleApi().setUserSettings(new SetAmbientSoundLevelControlRequest.Builder(z).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityAmbientSoundSettingsViewModel$setAmbientSoundSettingsToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivityAmbientSoundSettingsViewModel.this.getDialogListener().onDismiss();
                ActivityAmbientSoundSettingsViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(ActivityAmbientSoundSettingsViewModel.this.getContext()).saveAmbientSoundSettingsEnabled(Boolean.valueOf(z));
                ActivityAmbientSoundSettingsViewModel.this.senDataToServer(z);
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
