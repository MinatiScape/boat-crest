package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetDeviceParametersRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DoNotDisturbViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5185a;
    @NotNull
    public final String b;
    public DialogListener dialogListener;

    public DoNotDisturbViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5185a = context;
        this.b = "DoNotDisturbViewModel";
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.lang.Object, com.coveiot.covepreferences.data.DoNotDisturbData] */
    public final void callSaveAndBleApi(final boolean z, final boolean z2, @NotNull String beginTime1, @NotNull String endTime1) {
        List emptyList;
        List emptyList2;
        List emptyList3;
        List emptyList4;
        SetDNDModeRequest build;
        SetDNDModeRequest build2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Intrinsics.checkNotNullParameter(beginTime1, "beginTime1");
        Intrinsics.checkNotNullParameter(endTime1, "endTime1");
        String str = (String) StringsKt__StringsKt.split$default((CharSequence) beginTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str2 = (String) StringsKt__StringsKt.split$default((CharSequence) beginTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split = new Regex(":").split(str, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() == 0) {
                    z6 = true;
                    continue;
                } else {
                    z6 = false;
                    continue;
                }
                if (!z6) {
                    emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        String str3 = ((String[]) emptyList.toArray(new String[0]))[0];
        List<String> split2 = new Regex(":").split(str, 0);
        if (!split2.isEmpty()) {
            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
            while (listIterator2.hasPrevious()) {
                if (listIterator2.previous().length() == 0) {
                    z5 = true;
                    continue;
                } else {
                    z5 = false;
                    continue;
                }
                if (!z5) {
                    emptyList2 = CollectionsKt___CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        final String str4 = ((String[]) emptyList2.toArray(new String[0]))[1];
        String str5 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str6 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split3 = new Regex(":").split(str5, 0);
        if (!split3.isEmpty()) {
            ListIterator<String> listIterator3 = split3.listIterator(split3.size());
            while (listIterator3.hasPrevious()) {
                if (listIterator3.previous().length() == 0) {
                    z4 = true;
                    continue;
                } else {
                    z4 = false;
                    continue;
                }
                if (!z4) {
                    emptyList3 = CollectionsKt___CollectionsKt.take(split3, listIterator3.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList3 = CollectionsKt__CollectionsKt.emptyList();
        String str7 = ((String[]) emptyList3.toArray(new String[0]))[0];
        List<String> split4 = new Regex(":").split(str5, 0);
        if (!split4.isEmpty()) {
            ListIterator<String> listIterator4 = split4.listIterator(split4.size());
            while (listIterator4.hasPrevious()) {
                if (listIterator4.previous().length() == 0) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
                if (!z3) {
                    emptyList4 = CollectionsKt___CollectionsKt.take(split4, listIterator4.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList4 = CollectionsKt__CollectionsKt.emptyList();
        final String str8 = ((String[]) emptyList4.toArray(new String[0]))[1];
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Integer.parseInt(str3);
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = Integer.parseInt(str7);
        if (kotlin.text.m.equals(str2, "am", true)) {
            int i = intRef.element;
            if (i == 12) {
                intRef.element = 0;
            } else {
                intRef.element = i;
            }
        } else if (kotlin.text.m.equals(str2, "pm", true)) {
            int i2 = intRef.element;
            if (i2 == 12) {
                intRef.element = i2;
            } else {
                intRef.element = i2 + 12;
            }
        }
        if (kotlin.text.m.equals(str6, "am", true)) {
            int i3 = intRef2.element;
            if (i3 == 12) {
                intRef2.element = 0;
            } else {
                intRef2.element = i3;
            }
        } else if (kotlin.text.m.equals(str6, "pm", true)) {
            int i4 = intRef2.element;
            if (i4 == 12) {
                intRef2.element = i4;
            } else {
                intRef2.element = i4 + 12;
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, intRef.element);
        calendar.set(12, Integer.parseInt(str4));
        calendar.set(11, intRef2.element);
        calendar.set(12, Integer.parseInt(str8));
        getDialogListener().onShowProgressDialog();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? doNotDisturbData = DoNotDisturbData.getInstance();
        Intrinsics.checkNotNullExpressionValue(doNotDisturbData, "getInstance()");
        objectRef.element = doNotDisturbData;
        ((DoNotDisturbData) doNotDisturbData).setBeggining_time_hour(intRef.element);
        ((DoNotDisturbData) objectRef.element).setBeggining_time_minutes(Integer.parseInt(str4));
        ((DoNotDisturbData) objectRef.element).setEnd_time_hour(intRef2.element);
        ((DoNotDisturbData) objectRef.element).setEnd_time_minutes(Integer.parseInt(str8));
        ((DoNotDisturbData) objectRef.element).setSchedule_on_off(z);
        ((DoNotDisturbData) objectRef.element).setDnd_on_off(z2);
        if (z2) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context context = this.f5185a;
            Intrinsics.checkNotNull(context);
            if (!companion.isCZDevice(context)) {
                Context context2 = this.f5185a;
                Intrinsics.checkNotNull(context2);
                if (!companion.isCADevice(context2)) {
                    Context context3 = this.f5185a;
                    Intrinsics.checkNotNull(context3);
                    if (!companion.isCYDevice(context3)) {
                        Context context4 = this.f5185a;
                        Intrinsics.checkNotNull(context4);
                        if (!companion.isPS1Device(context4)) {
                            Context context5 = this.f5185a;
                            Intrinsics.checkNotNull(context5);
                            if (!companion.isBESDevice(context5)) {
                                build2 = new SetDNDModeRequest.Builder(true, 0, 0, 23, 59).build();
                                Intrinsics.checkNotNullExpressionValue(build2, "{\n                SetDND…9).build();\n            }");
                            }
                        }
                    }
                }
            }
            build = new SetDNDModeRequest.Builder(true, 0, 0, 0, 0).build();
            Intrinsics.checkNotNullExpressionValue(build, "{\n                SetDND…0).build();\n            }");
            build2 = build;
        } else if (z) {
            build2 = new SetDNDModeRequest.Builder(true, intRef.element, Integer.parseInt(str4), intRef2.element, Integer.parseInt(str8)).build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder(\n               …                ).build()");
        } else {
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            Context context6 = this.f5185a;
            Intrinsics.checkNotNull(context6);
            if (!companion2.isCZDevice(context6)) {
                Context context7 = this.f5185a;
                Intrinsics.checkNotNull(context7);
                if (!companion2.isCADevice(context7)) {
                    Context context8 = this.f5185a;
                    Intrinsics.checkNotNull(context8);
                    if (!companion2.isCYDevice(context8)) {
                        Context context9 = this.f5185a;
                        Intrinsics.checkNotNull(context9);
                        if (!companion2.isPS1Device(context9)) {
                            Context context10 = this.f5185a;
                            Intrinsics.checkNotNull(context10);
                            if (!companion2.isBESDevice(context10)) {
                                build2 = new SetDNDModeRequest.Builder(false, 0, 0, 23, 59).build();
                                Intrinsics.checkNotNullExpressionValue(build2, "{\n                SetDND…9).build();\n            }");
                            }
                        }
                    }
                }
            }
            build = new SetDNDModeRequest.Builder(false, 22, 0, 6, 0).build();
            Intrinsics.checkNotNullExpressionValue(build, "{\n                SetDND…0).build();\n            }");
            build2 = build;
        }
        BleApiManager.getInstance(this.f5185a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.DoNotDisturbViewModel$callSaveAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DoNotDisturbViewModel.this.getDialogListener().onDismiss();
                DoNotDisturbViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(DoNotDisturbViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(DoNotDisturbViewModel.this.getContext()).saveDoNotDisturbSettings(objectRef.element);
                DoNotDisturbViewModel.this.getDialogListener().onDismiss();
                SettingsSyncHelper.Companion.getInstance(DoNotDisturbViewModel.this.getContext()).uploadDndToServer(intRef.element, Integer.parseInt(str4), intRef2.element, Integer.parseInt(str8), z, z2);
                DoNotDisturbViewModel.this.getDialogListener().showSuccessDialog();
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5185a;
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
    public final DoNotDisturbData getDonotDisturbDataFromPref() {
        try {
            DoNotDisturbData prefDonotDisturbData = UserDataManager.getInstance(this.f5185a).getDoNotDisturbData();
            if (prefDonotDisturbData == null) {
                DoNotDisturbData doNotDisturbData = DoNotDisturbData.getInstance();
                Intrinsics.checkNotNullExpressionValue(doNotDisturbData, "getInstance()");
                doNotDisturbData.setBeggining_time_hour(22);
                doNotDisturbData.setBeggining_time_minutes(0);
                doNotDisturbData.setEnd_time_minutes(0);
                doNotDisturbData.setEnd_time_hour(6);
                doNotDisturbData.setSchedule_on_off(true);
            }
            Intrinsics.checkNotNullExpressionValue(prefDonotDisturbData, "prefDonotDisturbData");
            return prefDonotDisturbData;
        } catch (Exception unused) {
            DoNotDisturbData doNotDisturbData2 = DoNotDisturbData.getInstance();
            Intrinsics.checkNotNullExpressionValue(doNotDisturbData2, "getInstance()");
            doNotDisturbData2.setBeggining_time_hour(22);
            doNotDisturbData2.setBeggining_time_minutes(0);
            doNotDisturbData2.setEnd_time_minutes(0);
            doNotDisturbData2.setEnd_time_hour(6);
            doNotDisturbData2.setSchedule_on_off(true);
            return doNotDisturbData2;
        }
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void liftWristToViewPreference() {
        SetLiftWristRequest.Builder builder;
        if (BleApiManager.getInstance(this.f5185a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            Boolean isRightHandSelected = UserDataManager.getInstance(this.f5185a).isRightHandSelected();
            Boolean hourPref = UserDataManager.getInstance(this.f5185a).isTimeIn12HourFormat();
            Boolean isUnitInMile = UserDataManager.getInstance(this.f5185a).isDistanceUnitInMile();
            Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f5185a).isTemperatureUnitInFahrenheit();
            final Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f5185a).isLiftWristToViewEnable();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue());
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            SetDeviceParametersRequest.Builder hourFormat = handState.setHourFormat(hourPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
            SetDeviceParametersRequest.Builder distanceUnit = hourFormat.setDistanceUnit(isUnitInMile.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            SetDeviceParametersRequest build = distanceUnit.setLiftWristEnable(isLiftWristEnabled.booleanValue()).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…UnitInFahrenheit).build()");
            BleApiManager.getInstance(this.f5185a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.DoNotDisturbViewModel$liftWristToViewPreference$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(DoNotDisturbViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(DoNotDisturbViewModel.this.getTAG(), response.toString());
                    UserDataManager.getInstance(DoNotDisturbViewModel.this.getContext()).saveLiftWristPref(isLiftWristEnabled);
                }
            });
            return;
        }
        final Boolean isLiftWristToViewOn = UserDataManager.getInstance(this.f5185a).isLiftWristToViewEnable();
        if (DeviceUtils.Companion.isMoyangDevice(this.f5185a)) {
            Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
            builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
            builder.setStartHour(UserDataManager.getInstance(this.f5185a).getLiftWristToViewStartHour());
            builder.setStartMinute(UserDataManager.getInstance(this.f5185a).getLiftWristToViewStartMin());
            builder.setEndHour(UserDataManager.getInstance(this.f5185a).getLiftWristToViewEndHour());
            builder.setEndMinute(UserDataManager.getInstance(this.f5185a).getLiftWristToViewEndMin());
        } else {
            Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
            builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
        }
        SetLiftWristRequest build2 = builder.build();
        Intrinsics.checkNotNullExpressionValue(build2, "builder.build()");
        BleApiManager.getInstance(this.f5185a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.DoNotDisturbViewModel$liftWristToViewPreference$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(DoNotDisturbViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(DoNotDisturbViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(DoNotDisturbViewModel.this.getContext()).saveLiftWristPref(isLiftWristToViewOn);
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
