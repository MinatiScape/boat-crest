package com.coveiot.android.leonardo.more;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetLiftWristSettingsRequest;
import com.coveiot.android.bleabstract.request.GetWatchTimeInfoRequest;
import com.coveiot.android.bleabstract.request.SetDeviceParametersRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.bleabstract.response.GetWatchtimeInfoResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.OnSuccessListener;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.WatchShortcutUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsReq;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.ShowHideData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBandSettingsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4866a;
    public final String b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @NotNull
    public MutableLiveData<Boolean> d;
    public OnSuccessListener viewModelListener;

    public ActivityBandSettingsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4866a = context;
        this.b = ActivityBandSettingsViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public final void a(ProfileData profileData, final boolean z) {
        String height = profileData.getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
        int parseInt = Integer.parseInt(height);
        String weight = profileData.getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
        SetFitnessInfoRequest.Builder builder = new SetFitnessInfoRequest.Builder(parseInt, Double.parseDouble(weight));
        builder.setStride(ProfileRepository.getInstance().getLatestProfileData(this.f4866a).walkStrideLength);
        builder.setRunStride(ProfileRepository.getInstance().getLatestProfileData(this.f4866a).runStrideLength);
        builder.setUnitType(z ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC);
        String dob = profileData.getDob();
        boolean z2 = false;
        if (!(dob == null || dob.length() == 0)) {
            builder.setAge(AppUtils.getAge(profileData.getDob()));
        }
        String gender = profileData.getGender();
        if (gender == null || gender.length() == 0) {
            z2 = true;
        }
        if (!z2) {
            builder.setMale(m.equals(profileData.getGender(), AppConstants.MALE.getValue(), true));
        }
        SetFitnessInfoRequest builder2 = builder.builder();
        Intrinsics.checkNotNullExpressionValue(builder2, "fitnessInfoRequestBuilder.builder()");
        BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(builder2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$updateUserProfile$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveDistanceUnitPref(Boolean.valueOf(z));
                ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
            }
        });
    }

    public final void callDeviceSpecificSettingsApi(final boolean z) {
        boolean z2 = false;
        if (AppUtils.isNetConnected(this.f4866a)) {
            Boolean handPref = UserDataManager.getInstance(this.f4866a).isRightHandSelected();
            Boolean distanceUnitPref = UserDataManager.getInstance(this.f4866a).isDistanceUnitInMile();
            Boolean hourPref = UserDataManager.getInstance(this.f4866a).isTimeIn12HourFormat();
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f4866a).isLiftWristToViewEnable();
            Boolean temperatureUnitInFahrenheit = UserDataManager.getInstance(this.f4866a).isTemperatureUnitInFahrenheit();
            SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq = new SaveDeviceSpecificParamsReq();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this.f4866a) || companion.isTouchELXDevice(this.f4866a) || companion.isEastApexDevice(this.f4866a)) {
                WatchShortcutUtils watchShortcutUtils = WatchShortcutUtils.INSTANCE;
                List<String> watchShortcuts = watchShortcutUtils.watchShortcuts(this.f4866a);
                if (!(watchShortcuts == null || watchShortcuts.isEmpty())) {
                    saveDeviceSpecificParamsReq.setActiveDisplays(watchShortcutUtils.watchShortcuts(this.f4866a));
                }
            }
            Intrinsics.checkNotNullExpressionValue(handPref, "handPref");
            if (handPref.booleanValue()) {
                saveDeviceSpecificParamsReq.setWearingOn(AppConstants.RIGHT_HAND_WRIST.getValue());
            } else {
                saveDeviceSpecificParamsReq.setWearingOn(AppConstants.LEFT_HAND_WRIST.getValue());
            }
            Intrinsics.checkNotNullExpressionValue(distanceUnitPref, "distanceUnitPref");
            if (distanceUnitPref.booleanValue()) {
                saveDeviceSpecificParamsReq.setDistanceUnit(AppConstants.MILES.getValue());
            } else {
                saveDeviceSpecificParamsReq.setDistanceUnit(AppConstants.KILOMETERS.getValue());
            }
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            if (hourPref.booleanValue()) {
                saveDeviceSpecificParamsReq.setClockFormat(AppConstants.H12.getValue());
            } else {
                saveDeviceSpecificParamsReq.setClockFormat(AppConstants.H24.getValue());
            }
            Intrinsics.checkNotNullExpressionValue(temperatureUnitInFahrenheit, "temperatureUnitInFahrenheit");
            if (temperatureUnitInFahrenheit.booleanValue()) {
                saveDeviceSpecificParamsReq.setTemperatureUnit(AppConstants.FAHRENHEIT.getValue());
            } else {
                saveDeviceSpecificParamsReq.setTemperatureUnit(AppConstants.CELSIUS.getValue());
            }
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            saveDeviceSpecificParamsReq.setLiftWristToView(isLiftWristEnabled.booleanValue());
            saveDeviceSpecificParamsReq.setScreenOrientation(AppConstants.PORTRAIT.getValue());
            ArrayList<Integer> showMenuList = getShowMenuList();
            if (showMenuList == null || showMenuList.isEmpty()) {
                z2 = true;
            }
            if (!z2) {
                ArrayList arrayList = new ArrayList();
                Iterator<Integer> it = getShowMenuList().iterator();
                while (it.hasNext()) {
                    Integer ii = it.next();
                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                    if (companion2.isIDODevice(this.f4866a)) {
                        PayUtils payUtils = PayUtils.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(ii, "ii");
                        String shortcutsActiveDisplayEnumIDO = payUtils.getShortcutsActiveDisplayEnumIDO(ii.intValue(), this.f4866a);
                        Intrinsics.checkNotNull(shortcutsActiveDisplayEnumIDO);
                        arrayList.add(shortcutsActiveDisplayEnumIDO);
                    } else if (companion2.isTouchELXDevice(this.f4866a)) {
                        PayUtils payUtils2 = PayUtils.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(ii, "ii");
                        String shortcutsActiveDisplayEnumTouch = payUtils2.getShortcutsActiveDisplayEnumTouch(ii.intValue(), this.f4866a);
                        Intrinsics.checkNotNull(shortcutsActiveDisplayEnumTouch);
                        arrayList.add(shortcutsActiveDisplayEnumTouch);
                    }
                }
                saveDeviceSpecificParamsReq.setActiveDisplays(arrayList);
            }
            CoveUserAppSettings.saveDeviceSpecificParameters(saveDeviceSpecificParamsReq, new CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$callDeviceSpecificSettingsApi$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    Toast.makeText(ActivityBandSettingsViewModel.this.getContext(), ActivityBandSettingsViewModel.this.getContext().getResources().getString(R.string.failure_message), 0).show();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDeviceSpecificParamsRes saveDeviceSpecificParamsRes) {
                    ActivityBandSettingsViewModel.this.getViewModelListener().onSuccess(z);
                }
            });
            return;
        }
        Context context = this.f4866a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_your_internet), 0).show();
    }

    public final void distanceUnitPreference(final boolean z) {
        if (BleApiManager.getInstance(this.f4866a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            Boolean isRightHandSelected = UserDataManager.getInstance(this.f4866a).isRightHandSelected();
            Boolean hourPref = UserDataManager.getInstance(this.f4866a).isTimeIn12HourFormat();
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f4866a).isLiftWristToViewEnable();
            Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f4866a).isTemperatureUnitInFahrenheit();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue());
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            SetDeviceParametersRequest.Builder distanceUnit = handState.setHourFormat(hourPref.booleanValue()).setDistanceUnit(z);
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            SetDeviceParametersRequest build = distanceUnit.setLiftWristEnable(isLiftWristEnabled.booleanValue()).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…UnitInFahrenheit).build()");
            BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$distanceUnitPreference$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveDistanceUnitPref(Boolean.valueOf(z));
                    ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                }
            });
        } else if (DeviceUtils.Companion.isSmaDevice(this.f4866a)) {
            ProfileData userDetails = SessionManager.getInstance(this.f4866a).getUserDetails();
            if (userDetails != null) {
                a(userDetails, z);
            }
        } else {
            SetDistanceUnitRequest build2 = new SetDistanceUnitRequest.Builder(z).build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder(isUnitInMile).build()");
            BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$distanceUnitPreference$2
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveDistanceUnitPref(Boolean.valueOf(z));
                    ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4866a;
    }

    public final void getHourFormatFromBand() {
        BleApiManager.getInstance(this.f4866a).getBleApi().getData(new GetWatchTimeInfoRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$getHourFormatFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = ActivityBandSettingsViewModel.this.getTAG();
                LogHelper.d(tag, "getHourFormatFromBand error " + error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                String tag = ActivityBandSettingsViewModel.this.getTAG();
                LogHelper.d(tag, "getHourFormatFromBand response " + response.getResponseData());
                if (response.getResponseData() instanceof GetWatchtimeInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetWatchtimeInfoResponse");
                    GetWatchtimeInfoResponse getWatchtimeInfoResponse = (GetWatchtimeInfoResponse) responseData;
                    ActivityBandSettingsViewModel.this.is12HourFormatObserver().postValue(Boolean.valueOf(getWatchtimeInfoResponse.is12hourFormat()));
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveHourFormatPref(Boolean.valueOf(getWatchtimeInfoResponse.is12hourFormat()));
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
                String tag = ActivityBandSettingsViewModel.this.getTAG();
                LogHelper.d(tag, "getHourFormatFromBand progress " + progress);
            }
        });
    }

    public final void getLiftWristFromBand() {
        BleApiManager.getInstance(this.f4866a).getBleApi().getData(new GetLiftWristSettingsRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$getLiftWristFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof GetLiftWristResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLiftWristResponse");
                    GetLiftWristResponse getLiftWristResponse = (GetLiftWristResponse) responseData;
                    ActivityBandSettingsViewModel.this.isLiftWristObserver().postValue(Boolean.valueOf(getLiftWristResponse.isLiftWristEnabled()));
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveLiftWristPref(Boolean.valueOf(getLiftWristResponse.isLiftWristEnabled()));
                    ActivityBandSettingsViewModel.this.getHourFormatFromBand();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final ArrayList<Integer> getShowMenuList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        List<ShowHideData> shortcutsList = UserDataManager.getInstance(this.f4866a).getShortcutsList();
        if (!(shortcutsList == null || shortcutsList.isEmpty())) {
            for (ShowHideData showHideData : UserDataManager.getInstance(this.f4866a).getShortcutsList()) {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (companion.isIDODevice(this.f4866a)) {
                    PayUtils payUtils = PayUtils.INSTANCE;
                    String typeName = showHideData.getTypeName();
                    Intrinsics.checkNotNull(typeName);
                    arrayList.add(Integer.valueOf(payUtils.getShortcutMenuValueIDO(typeName, this.f4866a)));
                } else if (companion.isTouchELXDevice(this.f4866a)) {
                    PayUtils payUtils2 = PayUtils.INSTANCE;
                    String typeName2 = showHideData.getTypeName();
                    Intrinsics.checkNotNull(typeName2);
                    arrayList.add(Integer.valueOf(payUtils2.getShortcutMenuValueTouch(typeName2, this.f4866a)));
                }
            }
        }
        return arrayList;
    }

    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final OnSuccessListener getViewModelListener() {
        OnSuccessListener onSuccessListener = this.viewModelListener;
        if (onSuccessListener != null) {
            return onSuccessListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelListener");
        return null;
    }

    public final void handPreference(final boolean z) {
        if (BleApiManager.getInstance(this.f4866a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            Boolean distanceUnitPref = UserDataManager.getInstance(this.f4866a).isDistanceUnitInMile();
            Boolean hourPref = UserDataManager.getInstance(this.f4866a).isTimeIn12HourFormat();
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f4866a).isLiftWristToViewEnable();
            Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f4866a).isTemperatureUnitInFahrenheit();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!z);
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            SetDeviceParametersRequest.Builder hourFormat = handState.setHourFormat(hourPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(distanceUnitPref, "distanceUnitPref");
            SetDeviceParametersRequest.Builder distanceUnit = hourFormat.setDistanceUnit(distanceUnitPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            SetDeviceParametersRequest build = distanceUnit.setLiftWristEnable(isLiftWristEnabled.booleanValue()).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…UnitInFahrenheit).build()");
            BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$handPreference$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveHandPref(Boolean.valueOf(z));
                    ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                }
            });
            return;
        }
        SetWearingHandRequest build2 = new SetWearingHandRequest.Builder(z).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder(isRightHand).build()");
        BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$handPreference$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveHandPref(Boolean.valueOf(z));
                ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
            }
        });
    }

    public final void hourFormatPreference(final boolean z) {
        Boolean isRightHandSelected = UserDataManager.getInstance(this.f4866a).isRightHandSelected();
        Boolean distanceUnitPref = UserDataManager.getInstance(this.f4866a).isDistanceUnitInMile();
        Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f4866a).isLiftWristToViewEnable();
        Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f4866a).isTemperatureUnitInFahrenheit();
        SetDeviceParametersRequest.Builder hourFormat = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue()).setHourFormat(z);
        Intrinsics.checkNotNullExpressionValue(distanceUnitPref, "distanceUnitPref");
        SetDeviceParametersRequest.Builder distanceUnit = hourFormat.setDistanceUnit(distanceUnitPref.booleanValue());
        Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
        SetDeviceParametersRequest build = distanceUnit.setLiftWristEnable(isLiftWristEnabled.booleanValue()).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…UnitInFahrenheit).build()");
        if (BleApiManager.getInstance(this.f4866a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$hourFormatPreference$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveHourFormatPref(Boolean.valueOf(z));
                    ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                }
            });
            return;
        }
        SetHourFormatRequest build2 = new SetHourFormatRequest.Builder(z).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder(is12Hour).build()");
        BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$hourFormatPreference$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveHourFormatPref(Boolean.valueOf(z));
                ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
            }
        });
    }

    @NotNull
    public final MutableLiveData<Boolean> is12HourFormatObserver() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> isLiftWristObserver() {
        return this.c;
    }

    public final void liftWristToViewPreference(final boolean z) {
        SetLiftWristRequest.Builder builder;
        if (BleApiManager.getInstance(this.f4866a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            Boolean isRightHandSelected = UserDataManager.getInstance(this.f4866a).isRightHandSelected();
            Boolean hourPref = UserDataManager.getInstance(this.f4866a).isTimeIn12HourFormat();
            Boolean isUnitInMile = UserDataManager.getInstance(this.f4866a).isDistanceUnitInMile();
            Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f4866a).isTemperatureUnitInFahrenheit();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue());
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            SetDeviceParametersRequest.Builder hourFormat = handState.setHourFormat(hourPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
            SetDeviceParametersRequest build = hourFormat.setDistanceUnit(isUnitInMile.booleanValue()).setLiftWristEnable(z).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…UnitInFahrenheit).build()");
            BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$liftWristToViewPreference$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveLiftWristPref(Boolean.valueOf(z));
                    boolean isDNDEnabled = PayUtils.INSTANCE.isDNDEnabled(UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).getDoNotDisturbData());
                    if (!z) {
                        ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                    } else if (isDNDEnabled) {
                        ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                    } else {
                        ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                    }
                }
            });
            return;
        }
        if (DeviceUtils.Companion.isMoyangDevice(this.f4866a)) {
            builder = new SetLiftWristRequest.Builder(z);
            builder.setStartHour(UserDataManager.getInstance(this.f4866a).getLiftWristToViewStartHour());
            builder.setStartMinute(UserDataManager.getInstance(this.f4866a).getLiftWristToViewStartMin());
            builder.setEndHour(UserDataManager.getInstance(this.f4866a).getLiftWristToViewEndHour());
            builder.setEndMinute(UserDataManager.getInstance(this.f4866a).getLiftWristToViewEndMin());
        } else {
            builder = new SetLiftWristRequest.Builder(z);
        }
        SetLiftWristRequest build2 = builder.build();
        Intrinsics.checkNotNullExpressionValue(build2, "builder.build()");
        BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$liftWristToViewPreference$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveLiftWristPref(Boolean.valueOf(z));
                ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
            }
        });
    }

    public final void set12HourFormatObserver(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setLiftWristObserver(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setViewModelListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.viewModelListener = onSuccessListener;
    }

    public final void temperatureUnitPreference(final boolean z) {
        if (BleApiManager.getInstance(this.f4866a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            Boolean isRightHandSelected = UserDataManager.getInstance(this.f4866a).isRightHandSelected();
            Boolean hourPref = UserDataManager.getInstance(this.f4866a).isTimeIn12HourFormat();
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f4866a).isLiftWristToViewEnable();
            Boolean isUnitInMile = UserDataManager.getInstance(this.f4866a).isDistanceUnitInMile();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue());
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            SetDeviceParametersRequest.Builder hourFormat = handState.setHourFormat(hourPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
            SetDeviceParametersRequest.Builder distanceUnit = hourFormat.setDistanceUnit(isUnitInMile.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            SetDeviceParametersRequest build = distanceUnit.setLiftWristEnable(isLiftWristEnabled.booleanValue()).setTemperatureUnitInCelsius(!z).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…tureinFahrenheit).build()");
            BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$temperatureUnitPreference$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                    UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveTemperatureUnitPref(Boolean.valueOf(z));
                    ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
                }
            });
            return;
        }
        SetTemperatureUnitRequest build2 = new SetTemperatureUnitRequest.Builder(!z).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder(!isTemperatureinFahrenheit).build()");
        BleApiManager.getInstance(this.f4866a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel$temperatureUnitPreference$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityBandSettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(ActivityBandSettingsViewModel.this.getContext()).saveTemperatureUnitPref(Boolean.valueOf(z));
                ActivityBandSettingsViewModel.this.callDeviceSpecificSettingsApi(false);
            }
        });
    }
}
