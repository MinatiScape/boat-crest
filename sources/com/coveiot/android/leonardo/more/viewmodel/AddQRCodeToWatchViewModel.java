package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetSocialOrUpiQrCodeRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class AddQRCodeToWatchViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5169a;
    public AddQRCodeToWatchPushListener addQRCodeToWatchPushListener;
    public final String b;

    /* loaded from: classes5.dex */
    public interface AddQRCodeToWatchPushListener {
        void onFailure(@NotNull String str);

        void onSuccess();
    }

    public AddQRCodeToWatchViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5169a = context;
        this.b = AdditionalActivitiesViewModel.class.getSimpleName();
    }

    @NotNull
    public final AddQRCodeToWatchPushListener getAddQRCodeToWatchPushListener() {
        AddQRCodeToWatchPushListener addQRCodeToWatchPushListener = this.addQRCodeToWatchPushListener;
        if (addQRCodeToWatchPushListener != null) {
            return addQRCodeToWatchPushListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("addQRCodeToWatchPushListener");
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f5169a;
    }

    public final String getTAG() {
        return this.b;
    }

    public final void pushQRCodeToWatch(int i, @NotNull String qrCodeMetaData) {
        Intrinsics.checkNotNullParameter(qrCodeMetaData, "qrCodeMetaData");
        if (BleApiManager.getInstance(this.f5169a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.f5169a).getBleApi().setUserSettings(new SetSocialOrUpiQrCodeRequest(i, qrCodeMetaData), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AddQRCodeToWatchViewModel$pushQRCodeToWatch$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    AddQRCodeToWatchViewModel.this.getAddQRCodeToWatchPushListener().onFailure(error.getErrorMsg());
                    String tag = AddQRCodeToWatchViewModel.this.getTAG();
                    LogHelper.d(tag, "pushQRCodeToWatch onSettingsError " + error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    AddQRCodeToWatchViewModel.this.getAddQRCodeToWatchPushListener().onSuccess();
                    String tag = AddQRCodeToWatchViewModel.this.getTAG();
                    LogHelper.d(tag, "pushQRCodeToWatch onSettingsResponse " + response.getResponseData());
                }
            });
            return;
        }
        AddQRCodeToWatchPushListener addQRCodeToWatchPushListener = getAddQRCodeToWatchPushListener();
        String string = this.f5169a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        addQRCodeToWatchPushListener.onFailure(string);
        LogHelper.d(this.b, "pushQRCodeToWatch Watch not connected");
    }

    public final void setAddQRCodeToWatchPushListener(@NotNull AddQRCodeToWatchPushListener addQRCodeToWatchPushListener) {
        Intrinsics.checkNotNullParameter(addQRCodeToWatchPushListener, "<set-?>");
        this.addQRCodeToWatchPushListener = addQRCodeToWatchPushListener;
    }
}
