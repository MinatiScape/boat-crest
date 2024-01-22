package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.StressTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.AutoStressAlert;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoStressSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoStressSettingsRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.covepreferences.data.StressConfiguration;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes5.dex */
public final class AutoStressSettingsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5176a;
    @NotNull
    public final String b;
    public int c;
    public int d;
    public DialogListener dialogListener;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;

    public AutoStressSettingsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5176a = context;
        this.b = "AutoStressSettingsViewModel";
        this.d = 1;
        this.e = 2;
        this.f = 3;
        this.g = 4;
        this.h = 5;
        this.i = 6;
    }

    public final boolean a(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r7v7, types: [com.coveiot.covepreferences.data.AutoStressSettingsData, T, java.lang.Object] */
    public final void callSaveStressSettingsAndBleApi(int i, boolean z, @NotNull String beginTime1, @NotNull String endTime1, @NotNull boolean[] isDaySelected, boolean z2) {
        List emptyList;
        List emptyList2;
        List emptyList3;
        List emptyList4;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Intrinsics.checkNotNullParameter(beginTime1, "beginTime1");
        Intrinsics.checkNotNullParameter(endTime1, "endTime1");
        Intrinsics.checkNotNullParameter(isDaySelected, "isDaySelected");
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
        String str4 = ((String[]) emptyList2.toArray(new String[0]))[1];
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
        String str8 = ((String[]) emptyList4.toArray(new String[0]))[1];
        int parseInt = Integer.parseInt(str3);
        int parseInt2 = Integer.parseInt(str7);
        if (kotlin.text.m.equals(str2, "am", true)) {
            if (parseInt == 12) {
                parseInt = 0;
            }
        } else if (kotlin.text.m.equals(str2, "pm", true) && parseInt != 12) {
            parseInt += 12;
        }
        if (kotlin.text.m.equals(str6, "am", true)) {
            if (parseInt2 == 12) {
                parseInt2 = 0;
            }
        } else if (kotlin.text.m.equals(str6, "pm", true) && parseInt2 != 12) {
            parseInt2 += 12;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, parseInt);
        calendar.set(12, Integer.parseInt(str4));
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, parseInt2);
        calendar.set(12, Integer.parseInt(str8));
        if (Math.abs(calendar.getTimeInMillis() - timeInMillis) < i * 60 * 1000) {
            Context context = this.f5176a;
            Toast.makeText(context, context.getString(R.string.sedentary_reminder_set_value), 0).show();
            return;
        }
        getDialogListener().onShowProgressDialog();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? autoStressSettingsData = AutoStressSettingsData.getInstance();
        Intrinsics.checkNotNullExpressionValue(autoStressSettingsData, "getInstance()");
        objectRef.element = autoStressSettingsData;
        ((AutoStressSettingsData) autoStressSettingsData).setBeggining_time_hour(parseInt);
        ((AutoStressSettingsData) objectRef.element).setBeggining_time_minutes(Integer.parseInt(str4));
        ((AutoStressSettingsData) objectRef.element).setEnd_time_hour(parseInt2);
        ((AutoStressSettingsData) objectRef.element).setEnd_time_minutes(Integer.parseInt(str8));
        ((AutoStressSettingsData) objectRef.element).setRemind_in(i);
        ((AutoStressSettingsData) objectRef.element).setAutoStress(z);
        ((AutoStressSettingsData) objectRef.element).setSunday(isDaySelected[0]);
        ((AutoStressSettingsData) objectRef.element).setMonday(isDaySelected[1]);
        ((AutoStressSettingsData) objectRef.element).setTuesday(isDaySelected[2]);
        ((AutoStressSettingsData) objectRef.element).setWednesday(isDaySelected[3]);
        ((AutoStressSettingsData) objectRef.element).setThrusday(isDaySelected[4]);
        ((AutoStressSettingsData) objectRef.element).setFriday(isDaySelected[5]);
        ((AutoStressSettingsData) objectRef.element).setSaturday(isDaySelected[6]);
        ((AutoStressSettingsData) objectRef.element).setHighStressReminder(z2);
        String value = AppConstants.EMPTY_STRING.getValue();
        int length = isDaySelected.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == this.c) {
                value = isDaySelected[i2] ? value + this.f5176a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i2 == this.d) {
                value = isDaySelected[i2] ? value + this.f5176a.getResources().getString(R.string.M) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i2 == this.e) {
                value = isDaySelected[i2] ? value + this.f5176a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i2 == this.f) {
                value = isDaySelected[i2] ? value + this.f5176a.getResources().getString(R.string.W) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i2 == this.g) {
                value = isDaySelected[i2] ? value + this.f5176a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i2 == this.h) {
                value = isDaySelected[i2] ? value + this.f5176a.getResources().getString(R.string.F) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i2 == this.i) {
                value = isDaySelected[i2] ? value + this.f5176a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
            }
        }
        ((AutoStressSettingsData) objectRef.element).setWeeks(value);
        BleApiManager.getInstance(this.f5176a).getBleApi().setUserSettings(new StressTimeIntervalRequest.Builder(i).setStartHour(((AutoStressSettingsData) objectRef.element).getBeggining_time_hour()).setEndHour(((AutoStressSettingsData) objectRef.element).getEnd_time_hour()).setStartMin(((AutoStressSettingsData) objectRef.element).getBeggining_time_minutes()).setEndMin(((AutoStressSettingsData) objectRef.element).getEnd_time_minutes()).setEnabled(((AutoStressSettingsData) objectRef.element).getAutoStress()).setHighThresholdRemindEnabled(((AutoStressSettingsData) objectRef.element).isHighStressReminder()).setSundayEnabled(((AutoStressSettingsData) objectRef.element).isSunday()).setMondayEnabled(((AutoStressSettingsData) objectRef.element).isMonday()).setTuesdayEnabled(((AutoStressSettingsData) objectRef.element).isTuesday()).setWednesdayEnabled(((AutoStressSettingsData) objectRef.element).isWednesday()).setThursdayEnabled(((AutoStressSettingsData) objectRef.element).isThrusday()).setFridayEnabled(((AutoStressSettingsData) objectRef.element).isFriday()).setSaturdayEnabled(((AutoStressSettingsData) objectRef.element).isSaturday()).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel$callSaveStressSettingsAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                AutoStressSettingsViewModel.this.getDialogListener().onDismiss();
                AutoStressSettingsViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(AutoStressSettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(AutoStressSettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(AutoStressSettingsViewModel.this.getContext()).saveAutoStressSettings(objectRef.element);
                AutoStressSettingsViewModel.this.sendDataToServer(objectRef.element);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5176a;
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

    public final int getPosition0() {
        return this.c;
    }

    public final int getPosition1() {
        return this.d;
    }

    public final int getPosition2() {
        return this.e;
    }

    public final int getPosition3() {
        return this.f;
    }

    public final int getPosition4() {
        return this.g;
    }

    public final int getPosition5() {
        return this.h;
    }

    public final int getPosition6() {
        return this.i;
    }

    @NotNull
    public final AutoStressSettingsData getStressSettingsDataFromPref() {
        try {
            AutoStressSettingsData prefStressSettingsData = UserDataManager.getInstance(this.f5176a).getAutoStressSettingsData();
            if (prefStressSettingsData == null) {
                AutoStressSettingsData autoStressSettingsData = AutoStressSettingsData.getInstance();
                Intrinsics.checkNotNullExpressionValue(autoStressSettingsData, "getInstance()");
                autoStressSettingsData.setRemind_in(60);
                autoStressSettingsData.setBeggining_time_hour(9);
                autoStressSettingsData.setBeggining_time_minutes(0);
                autoStressSettingsData.setEnd_time_minutes(0);
                autoStressSettingsData.setEnd_time_hour(18);
            }
            Intrinsics.checkNotNullExpressionValue(prefStressSettingsData, "prefStressSettingsData");
            return prefStressSettingsData;
        } catch (Exception unused) {
            AutoStressSettingsData autoStressSettingsData2 = AutoStressSettingsData.getInstance();
            Intrinsics.checkNotNullExpressionValue(autoStressSettingsData2, "getInstance()");
            autoStressSettingsData2.setRemind_in(60);
            autoStressSettingsData2.setBeggining_time_hour(9);
            autoStressSettingsData2.setBeggining_time_minutes(0);
            autoStressSettingsData2.setEnd_time_minutes(0);
            autoStressSettingsData2.setEnd_time_hour(18);
            return autoStressSettingsData2;
        }
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void sendDataToServer(@NotNull AutoStressSettingsData autoStressSettingsData) {
        String valueOf;
        String valueOf2;
        Intrinsics.checkNotNullParameter(autoStressSettingsData, "autoStressSettingsData");
        SaveAutoStressSettingsReq saveAutoStressSettingsReq = new SaveAutoStressSettingsReq();
        AutoStressAlert autoStressAlert = new AutoStressAlert();
        if (autoStressSettingsData.getEnd_time_minutes() < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append('0');
            sb.append(autoStressSettingsData.getEnd_time_minutes());
            valueOf = sb.toString();
        } else {
            valueOf = String.valueOf(autoStressSettingsData.getEnd_time_minutes());
        }
        if (autoStressSettingsData.getBeggining_time_minutes() < 10) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(autoStressSettingsData.getBeggining_time_minutes());
            valueOf2 = sb2.toString();
        } else {
            valueOf2 = String.valueOf(autoStressSettingsData.getBeggining_time_minutes());
        }
        autoStressAlert.setRepeat(Boolean.valueOf(a(new boolean[]{autoStressSettingsData.isSunday(), autoStressSettingsData.isMonday(), autoStressSettingsData.isTuesday(), autoStressSettingsData.isWednesday(), autoStressSettingsData.isThrusday(), autoStressSettingsData.isFriday(), autoStressSettingsData.isSaturday()})));
        autoStressAlert.setActive(Boolean.valueOf(autoStressSettingsData.isHighStressReminder()));
        PayUtils payUtils = PayUtils.INSTANCE;
        autoStressAlert.setInterval(payUtils.getConvertedInterval(autoStressSettingsData.getRemind_in()));
        if (autoStressSettingsData.getEnd_time_hour() < 10) {
            autoStressAlert.setEndTime('0' + autoStressSettingsData.getEnd_time_hour() + ':' + valueOf + ":00");
        } else {
            autoStressAlert.setEndTime(autoStressSettingsData.getEnd_time_hour() + ':' + valueOf + ":00");
        }
        if (autoStressSettingsData.getBeggining_time_hour() < 10) {
            autoStressAlert.setStartTime('0' + autoStressSettingsData.getBeggining_time_hour() + ':' + valueOf2 + ":00");
        } else {
            autoStressAlert.setStartTime(autoStressSettingsData.getBeggining_time_hour() + ':' + valueOf2 + ":00");
        }
        autoStressAlert.setWeekDays(autoStressSettingsData.getWeeks());
        saveAutoStressSettingsReq.setActive(Boolean.valueOf(autoStressSettingsData.getAutoStress()));
        saveAutoStressSettingsReq.setInterval(payUtils.getConvertedInterval(autoStressSettingsData.getMeasuringInterval()));
        saveAutoStressSettingsReq.setAlert(autoStressAlert);
        CoveUserDeviceSettings.saveAutoStressSettings(saveAutoStressSettingsReq, new CoveApiListener<SaveAutoStressSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel$sendDataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Log.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save auto stress error");
                AutoStressSettingsViewModel.this.getDialogListener().onDismiss();
                AutoStressSettingsViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveAutoStressSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                if (object.getCode() == 200) {
                    AutoStressSettingsViewModel.this.getDialogListener().onDismiss();
                    AutoStressSettingsViewModel.this.getDialogListener().showSuccessDialog();
                    Log.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save auto stress successfully");
                    return;
                }
                AutoStressSettingsViewModel.this.getDialogListener().onDismiss();
                AutoStressSettingsViewModel.this.getDialogListener().showErrorDialog();
                Log.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save auto stress error");
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setPosition0(int i) {
        this.c = i;
    }

    public final void setPosition1(int i) {
        this.d = i;
    }

    public final void setPosition2(int i) {
        this.e = i;
    }

    public final void setPosition3(int i) {
        this.f = i;
    }

    public final void setPosition4(int i) {
        this.g = i;
    }

    public final void setPosition5(int i) {
        this.h = i;
    }

    public final void setPosition6(int i) {
        this.i = i;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.coveiot.covepreferences.data.AutoStressSettingsData, T, java.lang.Object] */
    public final void callSaveStressSettingsAndBleApi(int i, boolean z) {
        getDialogListener().onShowProgressDialog();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? autoStressSettingsData = AutoStressSettingsData.getInstance();
        Intrinsics.checkNotNullExpressionValue(autoStressSettingsData, "getInstance()");
        objectRef.element = autoStressSettingsData;
        ((AutoStressSettingsData) autoStressSettingsData).setMeasuringInterval(i);
        ((AutoStressSettingsData) objectRef.element).setAutoStress(z);
        StressConfiguration autoStressConfig = SessionManager.getInstance(this.f5176a).getAutoStressConfig();
        PayUtils payUtils = PayUtils.INSTANCE;
        String baselineTime = autoStressConfig.getStress().getBaselineTime();
        Intrinsics.checkNotNullExpressionValue(baselineTime, "stressConfiguration.stress.baselineTime");
        long convertIntervalToMilliSeconds = payUtils.convertIntervalToMilliSeconds(baselineTime);
        String readinessTime = autoStressConfig.getStress().getReadinessTime();
        Intrinsics.checkNotNullExpressionValue(readinessTime, "stressConfiguration.stress.readinessTime");
        long convertIntervalToMilliSeconds2 = payUtils.convertIntervalToMilliSeconds(readinessTime);
        StressTimeIntervalRequest.Builder enabled = new StressTimeIntervalRequest.Builder(i).setEnabled(true);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        StressTimeIntervalRequest.Builder highStressThreshold = enabled.setBaseLineTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds)).setReadinessTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds2)).setHighStressThreshold(autoStressConfig.getStress().getAlert().getThreshold().intValue());
        Integer maxAllowed = autoStressConfig.getStress().getAlert().getMaxAllowed();
        Intrinsics.checkNotNullExpressionValue(maxAllowed, "stressConfiguration.stress.alert.maxAllowed");
        BleApiManager.getInstance(this.f5176a).getBleApi().setUserSettings(highStressThreshold.setLimit(maxAllowed.intValue()).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel$callSaveStressSettingsAndBleApi$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                AutoStressSettingsViewModel.this.getDialogListener().onDismiss();
                AutoStressSettingsViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(AutoStressSettingsViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(AutoStressSettingsViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(AutoStressSettingsViewModel.this.getContext()).saveAutoStressSettings(objectRef.element);
                AutoStressSettingsViewModel.this.sendDataToServer(objectRef.element);
            }
        });
    }
}
