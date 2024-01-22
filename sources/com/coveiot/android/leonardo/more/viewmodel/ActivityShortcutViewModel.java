package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetSupportedShortcutMenuListRequest;
import com.coveiot.android.bleabstract.request.SetShortcutMenuListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetSupportedShortcutMenuListResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.model.ShowHideTypeModel;
import com.coveiot.android.leonardo.more.models.ShowHideListModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.WatchShortcutUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsReq;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ShowHideData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityShortcutViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5159a;
    @NotNull
    public MutableLiveData<ShowHideListModel> b;
    public DialogListener dialogListener;

    public ActivityShortcutViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5159a = context;
        this.b = new MutableLiveData<>();
    }

    public final void b(ArrayList<ShowHideTypeModel> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<ShowHideTypeModel> it = arrayList.iterator();
        while (it.hasNext()) {
            ShowHideTypeModel next = it.next();
            ShowHideData showHideData = new ShowHideData();
            showHideData.setShowOrHideText(next.getShowOrHideText());
            showHideData.setTypeName(next.getTypeName());
            arrayList2.add(showHideData);
        }
        UserDataManager.getInstance(this.f5159a).saveShortcutsList(arrayList2);
    }

    public final void callShortCutsDisplayApi(@NotNull ArrayList<Integer> menuList) {
        Intrinsics.checkNotNullParameter(menuList, "menuList");
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = menuList.iterator();
        while (it.hasNext()) {
            Integer ii = it.next();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this.f5159a)) {
                PayUtils payUtils = PayUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(ii, "ii");
                String shortcutsActiveDisplayEnumIDO = payUtils.getShortcutsActiveDisplayEnumIDO(ii.intValue(), this.f5159a);
                Intrinsics.checkNotNull(shortcutsActiveDisplayEnumIDO);
                arrayList.add(shortcutsActiveDisplayEnumIDO);
            } else if (companion.isTouchELXDevice(this.f5159a)) {
                PayUtils payUtils2 = PayUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(ii, "ii");
                String shortcutsActiveDisplayEnumTouch = payUtils2.getShortcutsActiveDisplayEnumTouch(ii.intValue(), this.f5159a);
                Intrinsics.checkNotNull(shortcutsActiveDisplayEnumTouch);
                arrayList.add(shortcutsActiveDisplayEnumTouch);
            }
        }
        boolean z = false;
        if (AppUtils.isNetConnected(this.f5159a)) {
            Boolean handPref = UserDataManager.getInstance(this.f5159a).isRightHandSelected();
            Boolean distanceUnitPref = UserDataManager.getInstance(this.f5159a).isDistanceUnitInMile();
            Boolean hourPref = UserDataManager.getInstance(this.f5159a).isTimeIn12HourFormat();
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f5159a).isLiftWristToViewEnable();
            Boolean temperatureUnitInFahrenheit = UserDataManager.getInstance(this.f5159a).isTemperatureUnitInFahrenheit();
            SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq = new SaveDeviceSpecificParamsReq();
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            if (companion2.isIDODevice(this.f5159a) || companion2.isTouchELXDevice(this.f5159a) || companion2.isEastApexDevice(this.f5159a)) {
                WatchShortcutUtils watchShortcutUtils = WatchShortcutUtils.INSTANCE;
                List<String> watchShortcuts = watchShortcutUtils.watchShortcuts(this.f5159a);
                if (!((watchShortcuts == null || watchShortcuts.isEmpty()) ? true : true)) {
                    saveDeviceSpecificParamsReq.setActiveDisplays(watchShortcutUtils.watchShortcuts(this.f5159a));
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
            saveDeviceSpecificParamsReq.setActiveDisplays(arrayList);
            CoveUserAppSettings.saveDeviceSpecificParameters(saveDeviceSpecificParamsReq, new CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityShortcutViewModel$callShortCutsDisplayApi$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ActivityShortcutViewModel.this.getDialogListener().onDismiss();
                    ActivityShortcutViewModel.this.getDialogListener().showErrorDialog();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDeviceSpecificParamsRes saveDeviceSpecificParamsRes) {
                    ActivityShortcutViewModel.this.getDialogListener().onDismiss();
                    ActivityShortcutViewModel.this.getDialogListener().showSuccessDialog();
                }
            });
            return;
        }
        Context context = this.f5159a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_your_internet), 0).show();
    }

    @NotNull
    public final Context getContext() {
        return this.f5159a;
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

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    public final void getSupoortedListFromWatch() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ArrayList();
        BleApiManager.getInstance(this.f5159a).getBleApi().getData(new GetSupportedShortcutMenuListRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityShortcutViewModel$getSupoortedListFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivityShortcutViewModel.this.getDialogListener().onDismiss();
                Toast.makeText(ActivityShortcutViewModel.this.getContext(), ActivityShortcutViewModel.this.getContext().getResources().getString(R.string.some_thing_went_wrong), 0).show();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof GetSupportedShortcutMenuListResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData);
                    List<Integer> supportedMenuList = ((GetSupportedShortcutMenuListResponse) responseData).getSupportedMenuList();
                    Object responseData2 = response.getResponseData();
                    Intrinsics.checkNotNull(responseData2);
                    List<Integer> watchMenuList = ((GetSupportedShortcutMenuListResponse) responseData2).getWatchMenuList();
                    if (DeviceUtils.Companion.isTouchELXDevice(ActivityShortcutViewModel.this.getContext())) {
                        supportedMenuList = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{1, 2, 3, 4, 5});
                    }
                    int size = supportedMenuList.size();
                    for (int i = 0; i < size; i++) {
                        String str = null;
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (companion.isIDODevice(ActivityShortcutViewModel.this.getContext())) {
                            str = PayUtils.INSTANCE.getShortcutTypeIDO(supportedMenuList.get(i).intValue(), ActivityShortcutViewModel.this.getContext());
                        } else if (companion.isTouchELXDevice(ActivityShortcutViewModel.this.getContext())) {
                            str = PayUtils.INSTANCE.getShortcutTypeTouch(supportedMenuList.get(i).intValue(), ActivityShortcutViewModel.this.getContext());
                        }
                        if (str != null) {
                            if (watchMenuList.contains(supportedMenuList.get(i))) {
                                objectRef2.element.add(new ShowHideTypeModel(str, Integer.valueOf((int) R.drawable.hide_icon), ActivityShortcutViewModel.this.getContext().getResources().getString(R.string.hide)));
                            } else {
                                objectRef.element.add(new ShowHideTypeModel(str, Integer.valueOf((int) R.drawable.show_icon), ActivityShortcutViewModel.this.getContext().getResources().getString(R.string.show)));
                            }
                        }
                    }
                    ActivityShortcutViewModel.this.b(objectRef2.element);
                    ActivityShortcutViewModel.this.getSupportedListLiveData().postValue(new ShowHideListModel(objectRef2.element, objectRef.element));
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final MutableLiveData<ShowHideListModel> getSupportedListLiveData() {
        return this.b;
    }

    public final void sendShowListToWatch(@NotNull final ArrayList<Integer> menuList, @NotNull final ArrayList<ShowHideTypeModel> showShortsCutsList) {
        Intrinsics.checkNotNullParameter(menuList, "menuList");
        Intrinsics.checkNotNullParameter(showShortsCutsList, "showShortsCutsList");
        BleApiManager.getInstance(this.f5159a).getBleApi().setUserSettings(new SetShortcutMenuListRequest(menuList), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityShortcutViewModel$sendShowListToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivityShortcutViewModel.this.getDialogListener().onDismiss();
                ActivityShortcutViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ActivityShortcutViewModel.this.b(showShortsCutsList);
                ActivityShortcutViewModel.this.callShortCutsDisplayApi(menuList);
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setSupportedListLiveData(@NotNull MutableLiveData<ShowHideListModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }
}
