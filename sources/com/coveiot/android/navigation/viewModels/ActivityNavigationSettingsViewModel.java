package com.coveiot.android.navigation.viewModels;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetNavigationConfigurationRequest;
import com.coveiot.android.bleabstract.request.SetNavigationConfigurationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetNavigationConfigurationResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.SaveMapNavigationSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveMapNavigationSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.model.MapNavigationSettings;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationSettingsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5543a;

    public ActivityNavigationSettingsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5543a = context;
    }

    public final void a(boolean z, boolean z2, boolean z3, final Function1<? super Boolean, Unit> function1) {
        MapNavigationSettings mapNavigationSettings = new MapNavigationSettings();
        MapNavigationSettings.AudioSettings audioSettings = new MapNavigationSettings.AudioSettings();
        audioSettings.setActive(z);
        MapNavigationSettings.VibrationSettings vibrationSettings = new MapNavigationSettings.VibrationSettings();
        vibrationSettings.setActive(z2);
        MapNavigationSettings.AodSettings aodSettings = new MapNavigationSettings.AodSettings();
        aodSettings.setActive(z3);
        mapNavigationSettings.setAudioSettings(audioSettings);
        mapNavigationSettings.setVibrationSettings(vibrationSettings);
        mapNavigationSettings.setAodSettings(aodSettings);
        SaveMapNavigationSettingsReq saveMapNavigationSettingsReq = new SaveMapNavigationSettingsReq();
        saveMapNavigationSettingsReq.setMapNavigationSettings(mapNavigationSettings);
        CoveUserDeviceSettings.saveMapNavigationSettings(saveMapNavigationSettingsReq, new CoveApiListener<SaveMapNavigationSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSettingsViewModel$saveNavigationSettingsOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                function1.invoke(Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveMapNavigationSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                if (object.getCode() == 200) {
                    function1.invoke(Boolean.TRUE);
                } else {
                    function1.invoke(Boolean.FALSE);
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5543a;
    }

    public final void getNavigationSettingsFromBand(@NotNull final Function1<? super GetNavigationConfigurationResponse, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BleApiManager.getInstance(this.f5543a).getBleApi().getData(new GetNavigationConfigurationRequest(), new DataResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSettingsViewModel$getNavigationSettingsFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                result.invoke(null);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetNavigationConfigurationResponse");
                result.invoke((GetNavigationConfigurationResponse) responseData);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void saveNavigationSettingsOnBand(final boolean z, final boolean z2, final boolean z3, @NotNull final Function1<? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BleApiManager.getInstance(this.f5543a).getBleApi().setUserSettings(new SetNavigationConfigurationRequest(z, z2, z3), new SettingsResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSettingsViewModel$saveNavigationSettingsOnBand$1

            /* loaded from: classes5.dex */
            public static final class a extends Lambda implements Function1<Boolean, Unit> {
                public final /* synthetic */ Function1<Boolean, Unit> $result;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public a(Function1<? super Boolean, Unit> function1) {
                    super(1);
                    this.$result = function1;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        this.$result.invoke(Boolean.TRUE);
                    } else {
                        this.$result.invoke(Boolean.FALSE);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                result.invoke(Boolean.FALSE);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                this.a(z, z2, z3, new a(result));
            }
        });
    }
}
