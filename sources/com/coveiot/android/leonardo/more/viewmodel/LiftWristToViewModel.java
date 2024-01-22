package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetLiftWristSettingsRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
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
public final class LiftWristToViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5188a;
    @NotNull
    public final String b;
    public DialogListener dialogListener;

    public LiftWristToViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5188a = context;
        this.b = "LiftWristToViewModel";
    }

    public final boolean a(GetLiftWristResponse getLiftWristResponse) {
        return getLiftWristResponse.getStartHour() == 0 && getLiftWristResponse.getEndHour() == 0 && getLiftWristResponse.getStartMinute() == 0 && getLiftWristResponse.getEndMinute() == 0;
    }

    public final void b(GetLiftWristResponse getLiftWristResponse) {
        UserDataManager.getInstance(this.f5188a).saveLiftWristPref(Boolean.valueOf(getLiftWristResponse.isLiftWristEnabled()));
        if (a(getLiftWristResponse)) {
            UserDataManager.getInstance(this.f5188a).saveLiftWristPref(Boolean.valueOf(getLiftWristResponse.isLiftWristEnabled()));
            UserDataManager.getInstance(this.f5188a).saveScheduleLiftWristPref(Boolean.FALSE);
        } else {
            UserDataManager.getInstance(this.f5188a).saveScheduleLiftWristPref(Boolean.valueOf(getLiftWristResponse.isLiftWristEnabled()));
            UserDataManager.getInstance(this.f5188a).saveLiftWristPref(Boolean.FALSE);
        }
        UserDataManager.getInstance(this.f5188a).saveLiftWristToViewStartHour(Integer.valueOf(getLiftWristResponse.getStartHour()));
        UserDataManager.getInstance(this.f5188a).saveLiftWristToViewStartMin(Integer.valueOf(getLiftWristResponse.getStartMinute()));
        UserDataManager.getInstance(this.f5188a).saveLiftWristToViewEndHour(Integer.valueOf(getLiftWristResponse.getEndHour()));
        UserDataManager.getInstance(this.f5188a).saveLiftWristToViewEndMin(Integer.valueOf(getLiftWristResponse.getEndMinute()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void callSaveAndBleApi(final boolean z, final boolean z2, @NotNull String beginTime1, @NotNull String endTime1) {
        List emptyList;
        List emptyList2;
        List emptyList3;
        List emptyList4;
        SetLiftWristRequest build;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Intrinsics.checkNotNullParameter(beginTime1, "beginTime1");
        Intrinsics.checkNotNullParameter(endTime1, "endTime1");
        boolean z7 = false;
        String str = (String) StringsKt__StringsKt.split$default((CharSequence) beginTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        boolean z8 = true;
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
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
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
        objectRef.element = ((String[]) emptyList2.toArray(new String[0]))[1];
        String str4 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str5 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split3 = new Regex(":").split(str4, 0);
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
        String str6 = ((String[]) emptyList3.toArray(new String[0]))[0];
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        List<String> split4 = new Regex(":").split(str4, 0);
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
        objectRef2.element = ((String[]) emptyList4.toArray(new String[0]))[1];
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Integer.parseInt(str3);
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = Integer.parseInt(str6);
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
        if (kotlin.text.m.equals(str5, "am", true)) {
            int i3 = intRef2.element;
            if (i3 == 12) {
                intRef2.element = 0;
            } else {
                intRef2.element = i3;
            }
        } else if (kotlin.text.m.equals(str5, "pm", true)) {
            int i4 = intRef2.element;
            if (i4 == 12) {
                intRef2.element = i4;
            } else {
                intRef2.element = i4 + 12;
            }
        }
        if (z) {
            intRef.element = 0;
            objectRef.element = BleConst.GetDeviceTime;
            intRef2.element = 0;
            objectRef2.element = BleConst.GetDeviceTime;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, intRef.element);
        calendar.set(12, Integer.parseInt((String) objectRef.element));
        calendar.set(11, intRef2.element);
        calendar.set(12, Integer.parseInt((String) objectRef2.element));
        getDialogListener().onShowProgressDialog();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isCYDevice(this.f5188a) || companion.isCADevice(this.f5188a) || companion.isPS1Device(this.f5188a) || companion.isBESDevice(this.f5188a)) {
            if (z || z2) {
                z7 = true;
            }
            SetLiftWristRequest.Builder builder = new SetLiftWristRequest.Builder(z7);
            builder.setStartHour(intRef.element);
            builder.setStartMinute(Integer.parseInt((String) objectRef.element));
            builder.setEndHour(intRef2.element);
            builder.setEndMinute(Integer.parseInt((String) objectRef2.element));
            build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        } else if (!z && !z2) {
            if (!z && !z2) {
                z8 = false;
            }
            SetLiftWristRequest.Builder builder2 = new SetLiftWristRequest.Builder(z8);
            builder2.setStartHour(0);
            builder2.setStartMinute(0);
            builder2.setEndHour(23);
            builder2.setEndMinute(59);
            build = builder2.build();
            Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        } else {
            if (z || z2) {
                z7 = true;
            }
            SetLiftWristRequest.Builder builder3 = new SetLiftWristRequest.Builder(z7);
            builder3.setStartHour(intRef.element);
            builder3.setStartMinute(Integer.parseInt((String) objectRef.element));
            builder3.setEndHour(intRef2.element);
            builder3.setEndMinute(Integer.parseInt((String) objectRef2.element));
            build = builder3.build();
            Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        }
        BleApiManager.getInstance(this.f5188a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.LiftWristToViewModel$callSaveAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LiftWristToViewModel.this.getDialogListener().onDismiss();
                LiftWristToViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(LiftWristToViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(LiftWristToViewModel.this.getContext()).saveLiftWristPref(Boolean.valueOf(z));
                UserDataManager.getInstance(LiftWristToViewModel.this.getContext()).saveScheduleLiftWristPref(Boolean.valueOf(z2));
                UserDataManager.getInstance(LiftWristToViewModel.this.getContext()).saveLiftWristToViewStartHour(Integer.valueOf(intRef.element));
                UserDataManager.getInstance(LiftWristToViewModel.this.getContext()).saveLiftWristToViewStartMin(Integer.valueOf(Integer.parseInt(objectRef.element)));
                UserDataManager.getInstance(LiftWristToViewModel.this.getContext()).saveLiftWristToViewEndHour(Integer.valueOf(intRef2.element));
                UserDataManager.getInstance(LiftWristToViewModel.this.getContext()).saveLiftWristToViewEndMin(Integer.valueOf(Integer.parseInt(objectRef2.element)));
                LiftWristToViewModel.this.getDialogListener().onDismiss();
                SettingsSyncHelper.Companion.getInstance(LiftWristToViewModel.this.getContext()).uploadLiftWristToServer(intRef.element, Integer.parseInt(objectRef.element), intRef2.element, Integer.parseInt(objectRef2.element), z || z2);
                LiftWristToViewModel.this.getDialogListener().showSuccessDialog();
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5188a;
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

    public final void getLiftWristViewSettingsFromWatch(@NotNull final SuccessResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BleApiManager.getInstance(this.f5188a).getBleApi().getData(new GetLiftWristSettingsRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.LiftWristToViewModel$getLiftWristViewSettingsFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SuccessResultListener.this.onError(error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() == null || !(response.getResponseData() instanceof GetLiftWristResponse)) {
                    return;
                }
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLiftWristResponse");
                GetLiftWristResponse getLiftWristResponse = (GetLiftWristResponse) responseData;
                this.b(getLiftWristResponse);
                SettingsSyncHelper.Companion.getInstance(this.getContext()).uploadLiftWristToServer(getLiftWristResponse.getStartHour(), getLiftWristResponse.getStartMinute(), getLiftWristResponse.getEndHour(), getLiftWristResponse.getEndMinute(), getLiftWristResponse.isLiftWristEnabled());
                SuccessResultListener.this.onSuccess();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
