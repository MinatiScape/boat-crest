package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomMessageSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomMessageSettingsRes;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class NudgesSettingsViewModel extends AndroidViewModel {
    @NotNull
    public final String d;
    public DialogListener dialogListener;
    public final Context e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NudgesSettingsViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = "NudgesSettingsViewModel";
        this.e = application.getBaseContext();
    }

    public final void callNudgeSettingsSaveAPI(final boolean z, final boolean z2) {
        SaveCustomMessageSettingsReq saveCustomMessageSettingsReq = new SaveCustomMessageSettingsReq();
        SaveCustomMessageSettingsReq.NudgeMessages nudgeMessages = new SaveCustomMessageSettingsReq.NudgeMessages();
        SaveCustomMessageSettingsReq.NudgeMessages.Nudges nudges = new SaveCustomMessageSettingsReq.NudgeMessages.Nudges();
        nudges.setActive(z);
        nudgeMessages.setNudges(nudges);
        SaveCustomMessageSettingsReq.NudgeMessages.Vibration vibration = new SaveCustomMessageSettingsReq.NudgeMessages.Vibration();
        vibration.setActive(z2);
        nudgeMessages.setVibration(vibration);
        saveCustomMessageSettingsReq.setNudgeMessages(nudgeMessages);
        CoveUserDeviceSettings.saveCustomMessagesSettings(saveCustomMessageSettingsReq, new CoveApiListener<SaveCustomMessageSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.NudgesSettingsViewModel$callNudgeSettingsSaveAPI$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                NudgesSettingsViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.i(NudgesSettingsViewModel.this.getTAG(), "Save CustomMessage onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveCustomMessageSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                NudgesSettingsViewModel.this.saveVibrationToPref(z2);
                NudgesSettingsViewModel.this.saveToPref(z);
                NudgesSettingsViewModel.this.getDialogListener().showSuccessDialog();
                LogHelper.i(NudgesSettingsViewModel.this.getTAG(), "Save CustomMessage onSuccess");
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

    public final void saveToPref(boolean z) {
        KHPerformancePreferenceManager.getInstance(this.e).setIsPerformanceBasedNotificationEnabled(Boolean.valueOf(z));
        KHPerformancePreferenceManager.getInstance(this.e).setIsNudge1Enabled(Boolean.valueOf(z));
        KHPerformancePreferenceManager.getInstance(this.e).setIsNudge2Enabled(Boolean.valueOf(z));
        KHPerformancePreferenceManager.getInstance(this.e).setIsNudge3Enabled(Boolean.valueOf(z));
        KHPerformancePreferenceManager.getInstance(this.e).setSleepScoreNudgeEnabled(Boolean.valueOf(z));
        KHPerformancePreferenceManager.getInstance(this.e).setEnergyScoreNudgeEnabled(Boolean.valueOf(z));
        KHPerformancePreferenceManager.getInstance(this.e).setStressNudgeEnabled(Boolean.valueOf(z));
    }

    public final void saveVibrationToPref(boolean z) {
        KHPerformancePreferenceManager.getInstance(this.e).setIsPerformanceBasedNotificationVibrationEnabled(Boolean.valueOf(z));
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
