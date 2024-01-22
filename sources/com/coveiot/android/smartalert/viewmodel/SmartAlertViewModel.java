package com.coveiot.android.smartalert.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.bleabstract.request.GetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetSmartAlertConfigResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.SaveSmartAlertSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveSmartAlertSettingsRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmartAlertViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5761a;
    public final String b;
    @NotNull
    public final MutableLiveData<Boolean> c;
    @NotNull
    public final MutableLiveData<GetSmartAlertConfigResponse> d;
    @NotNull
    public final MutableLiveData<Boolean> e;

    public SmartAlertViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5761a = context;
        this.b = SmartAlertViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f5761a;
    }

    @NotNull
    public final MutableLiveData<GetSmartAlertConfigResponse> getGetSmartAlertConfigLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSetSmartAlertConfigLiveData() {
        return this.c;
    }

    public final void getSmartAlertConfig() {
        if (BleApiManager.getInstance(this.f5761a).getBleApi().getDeviceSupportedFeatures().isSmartAlertsSupported()) {
            BleApiManager.getInstance(this.f5761a).getBleApi().getData(new GetSmartAlertConfigRequest(), new DataResultListener() { // from class: com.coveiot.android.smartalert.viewmodel.SmartAlertViewModel$getSmartAlertConfig$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = SmartAlertViewModel.this.getTAG();
                    LogHelper.d(tag, "GetSmartAlertConfigRequest error " + error.getErrorMsg());
                    SmartAlertViewModel.this.getGetSmartAlertConfigLiveData().postValue(null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetSmartAlertConfigResponse");
                    GetSmartAlertConfigResponse getSmartAlertConfigResponse = (GetSmartAlertConfigResponse) responseData;
                    String tag = SmartAlertViewModel.this.getTAG();
                    LogHelper.d(tag, "GetSmartAlertConfigRequest onDataResponse " + getSmartAlertConfigResponse);
                    SmartAlertViewModel.this.getGetSmartAlertConfigLiveData().postValue(getSmartAlertConfigResponse);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    LogHelper.d(SmartAlertViewModel.this.getTAG(), "GetSmartAlertConfigRequest progress");
                }
            });
        }
    }

    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<Boolean> getUploadSmartAlertConfigToServerLiveData() {
        return this.e;
    }

    public final void saveSmartAlertConfigToServer(@NotNull SaveSmartAlertSettingsReq saveSmartAlertSettingsReq) {
        Intrinsics.checkNotNullParameter(saveSmartAlertSettingsReq, "saveSmartAlertSettingsReq");
        if (AppUtils.isNetConnected(this.f5761a)) {
            CoveUserDeviceSettings.saveSmartAlertSettings(saveSmartAlertSettingsReq, new CoveApiListener<SaveSmartAlertSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.smartalert.viewmodel.SmartAlertViewModel$saveSmartAlertConfigToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SmartAlertViewModel.this.getUploadSmartAlertConfigToServerLiveData().postValue(Boolean.FALSE);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveSmartAlertSettingsRes saveSmartAlertSettingsRes) {
                    SmartAlertViewModel.this.getUploadSmartAlertConfigToServerLiveData().postValue(Boolean.TRUE);
                }
            });
        } else {
            this.e.postValue(Boolean.FALSE);
        }
    }

    public final void setSmartAlertConfig(@NotNull List<SmartAlertAppData> smartAlertAppDataList) {
        Intrinsics.checkNotNullParameter(smartAlertAppDataList, "smartAlertAppDataList");
        if (BleApiManager.getInstance(this.f5761a).getBleApi().getDeviceSupportedFeatures().isSmartAlertsSupported()) {
            BleApiManager.getInstance(this.f5761a).getBleApi().setUserSettings(new SetSmartAlertConfigRequest(!smartAlertAppDataList.isEmpty(), smartAlertAppDataList), new SettingsResultListener() { // from class: com.coveiot.android.smartalert.viewmodel.SmartAlertViewModel$setSmartAlertConfig$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = SmartAlertViewModel.this.getTAG();
                    LogHelper.d(tag, "SetSmartAlertConfigRequest onSettingsError " + error.getErrorMsg());
                    SmartAlertViewModel.this.getSetSmartAlertConfigLiveData().postValue(Boolean.FALSE);
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(SmartAlertViewModel.this.getTAG(), "SetSmartAlertConfigRequest onSettingsResponse");
                    SmartAlertViewModel.this.getSetSmartAlertConfigLiveData().postValue(Boolean.TRUE);
                }
            });
            return;
        }
        LogHelper.d(this.b, "Smart alert not supported.");
        this.c.postValue(Boolean.FALSE);
    }
}
