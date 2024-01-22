package com.coveiot.android.leonardo.more.activities;

import android.view.View;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.CameraControlRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.CameraSettingsData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ActivityCameraSettings$initClickListeners$2 extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ ActivityCameraSettings this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityCameraSettings$initClickListeners$2(ActivityCameraSettings activityCameraSettings) {
        super(1);
        this.this$0 = activityCameraSettings;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityCameraSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(View view) {
        invoke2(view);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull View it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (this.this$0.getBoolSaveVisible()) {
            if (!AppUtils.isNetConnected(this.this$0)) {
                this.this$0.showNoInternetMessage();
            } else if (BleApiManager.getInstance(this.this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                this.this$0.showProgress();
                final CameraSettingsData companion = CameraSettingsData.Companion.getInstance();
                Intrinsics.checkNotNull(companion);
                companion.setCameraEnabled(this.this$0.r);
                CameraControlRequest cameraControlRequest = new CameraControlRequest(this.this$0.r);
                BleApi bleApi = BleApiManager.getInstance(this.this$0).getBleApi();
                final ActivityCameraSettings activityCameraSettings = this.this$0;
                bleApi.setUserSettings(cameraControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityCameraSettings$initClickListeners$2.1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ActivityCameraSettings.this.dismissProgress();
                        ActivityCameraSettings.this.showErrorDialog();
                        LogHelper.d(ActivityCameraSettings.this.getTAG(), error.toString());
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        ActivityCameraSettings.this.dismissProgress();
                        LogHelper.d(ActivityCameraSettings.this.getTAG(), response.toString());
                        UserDataManager.getInstance(ActivityCameraSettings.this).saveCameraSettings(companion);
                        ActivityCameraSettings.this.showSuccessDialog();
                    }
                });
            } else {
                ActivityCameraSettings activityCameraSettings2 = this.this$0;
                String string = activityCameraSettings2.getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = this.this$0.getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityCameraSettings2, string, string2);
                String string3 = this.this$0.getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                final ActivityCameraSettings activityCameraSettings3 = this.this$0;
                bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityCameraSettings$initClickListeners$2.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityCameraSettings3, view);
                    }
                });
                if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                    return;
                }
                bottomSheetDialogOneButtonTitleMessage.show();
            }
        }
    }
}
