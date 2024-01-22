package com.coveiot.android.leonardo.more;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.SetBandDispalyRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsReq;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBandDisplayViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4864a;
    public final String b;

    public ActivityBandDisplayViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4864a = context;
        this.b = ActivityBandDisplayViewModel.class.getSimpleName();
    }

    public final void callBandDisplayApi(@NotNull List<String> displaylist) {
        Intrinsics.checkNotNullParameter(displaylist, "displaylist");
        if (AppUtils.isNetConnected(this.f4864a)) {
            SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq = new SaveDeviceSpecificParamsReq();
            saveDeviceSpecificParamsReq.setActiveDisplays(displaylist);
            CoveUserAppSettings.saveDeviceSpecificParameters(saveDeviceSpecificParamsReq, new CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.ActivityBandDisplayViewModel$callBandDisplayApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDeviceSpecificParamsRes saveDeviceSpecificParamsRes) {
                    Toast.makeText(ActivityBandDisplayViewModel.this.getContext(), ActivityBandDisplayViewModel.this.getContext().getResources().getString(R.string.success_message), 0).show();
                }
            });
            return;
        }
        Context context = this.f4864a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_your_internet), 0).show();
    }

    @NotNull
    public final Context getContext() {
        return this.f4864a;
    }

    public final String getTAG() {
        return this.b;
    }

    public final void saveBandDisplay(@Nullable final int[] iArr, @NotNull final List<String> displaylist) {
        Intrinsics.checkNotNullParameter(displaylist, "displaylist");
        SetBandDispalyRequest build = new SetBandDispalyRequest.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().build()");
        BleApiManager.getInstance(this.f4864a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandDisplayViewModel$saveBandDisplay$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityBandDisplayViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityBandDisplayViewModel.this.getTAG(), response.toString());
                UserDataManager userDataManager = UserDataManager.getInstance(ActivityBandDisplayViewModel.this.getContext());
                int[] iArr2 = iArr;
                Intrinsics.checkNotNull(iArr2);
                userDataManager.saveDisplayModeSettings(iArr2);
                ActivityBandDisplayViewModel activityBandDisplayViewModel = ActivityBandDisplayViewModel.this;
                List<String> list = displaylist;
                Intrinsics.checkNotNull(list);
                activityBandDisplayViewModel.callBandDisplayApi(list);
            }
        });
    }
}
