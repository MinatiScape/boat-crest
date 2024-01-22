package com.coveiot.android.respiratoryrate.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.PPGTypes;
import com.coveiot.android.bleabstract.request.SetAutomaticPPGConfigRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.listener.DialogListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.SaveRespiratoryRateSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveRespiratoryRateSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.model.RespiratoryRateSettings;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import com.coveiot.utils.utility.AppUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateSettingsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5680a;
    public DialogListener dialogListener;

    public RespiratoryRateSettingsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5680a = context;
    }

    public final void b(boolean z) {
        if (AppUtils.isNetConnected(this.f5680a)) {
            SaveRespiratoryRateSettingsReq saveRespiratoryRateSettingsReq = new SaveRespiratoryRateSettingsReq();
            RespiratoryRateSettings respiratoryRateSettings = new RespiratoryRateSettings();
            respiratoryRateSettings.setActive(z);
            saveRespiratoryRateSettingsReq.setRespiratoryRateSettings(respiratoryRateSettings);
            CoveUserDeviceSettings.saveRespiratoryRateSettings(saveRespiratoryRateSettingsReq, new CoveApiListener<SaveRespiratoryRateSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateSettingsViewModel$sendRespiratoryRateSettingsToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    RespiratoryRateSettingsViewModel.this.getDialogListener().onDismiss();
                    Toast.makeText(RespiratoryRateSettingsViewModel.this.getContext(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null, 0).show();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveRespiratoryRateSettingsRes saveRespiratoryRateSettingsRes) {
                    RespiratoryRateSettingsViewModel.this.getDialogListener().onDismiss();
                    RespiratoryRateSettingsViewModel.this.getDialogListener().showSuccessDialog();
                }
            });
            return;
        }
        getDialogListener().onDismiss();
        getDialogListener().showErrorDialog(this.f5680a.getString(R.string.please_check_your_internet));
    }

    @NotNull
    public final Context getContext() {
        return this.f5680a;
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

    public final void sendSettingsToWatch(final boolean z) {
        int duration;
        int i;
        int i2;
        int i3;
        RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfig = SessionManager.getInstance(this.f5680a).getRespiratoryRateRemoteConfig();
        PPGTypes pPGTypes = PPGTypes.PPG;
        if (z) {
            String startTime = respiratoryRateRemoteConfig.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime, "respiratoryRateConfPref.startTime");
            List split$default = StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null);
            int parseInt = (Integer.parseInt((String) split$default.get(0)) * 60) + Integer.parseInt((String) split$default.get(1));
            String endTime = respiratoryRateRemoteConfig.getEndTime();
            Intrinsics.checkNotNullExpressionValue(endTime, "respiratoryRateConfPref.endTime");
            List split$default2 = StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null);
            int parseInt2 = (Integer.parseInt((String) split$default2.get(0)) * 60) + Integer.parseInt((String) split$default2.get(1));
            String interval = respiratoryRateRemoteConfig.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval, "respiratoryRateConfPref.interval");
            List split$default3 = StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null);
            int parseInt3 = (Integer.parseInt((String) split$default3.get(0)) * 60) + Integer.parseInt((String) split$default3.get(1));
            duration = respiratoryRateRemoteConfig.getDuration();
            i = parseInt;
            i2 = parseInt2;
            i3 = parseInt3;
        } else {
            i2 = 0;
            i3 = 60;
            duration = 60;
            i = 0;
        }
        SetAutomaticPPGConfigRequest setAutomaticPPGConfigRequest = new SetAutomaticPPGConfigRequest.Builder(pPGTypes, i, i2, i3, duration).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5680a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(setAutomaticPPGConfigRequest, "setAutomaticPPGConfigRequest");
        bleApi.setUserSettings(setAutomaticPPGConfigRequest, new SettingsResultListener() { // from class: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateSettingsViewModel$sendSettingsToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(RespiratoryRateSettingsViewModel.this.getContext()).saveRespiratoryRateFeatureEnabled(z);
                RespiratoryRateSettingsViewModel.this.b(z);
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
