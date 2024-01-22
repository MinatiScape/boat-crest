package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.CountDownTimer;
import android.provider.Settings;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.model.TroubleshootingTestModel;
import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.more.models.TroubleshootTestCategory;
import com.coveiot.android.leonardo.more.models.TroubleshootTestType;
import com.coveiot.android.leonardo.more.models.TroubleshootTestUpdateModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AclBT3LastReceivedConnectionStatus;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootTestingViewModel extends AndroidViewModel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String g = "WATCH_DISCONNECTED";
    @NotNull
    public static final String h = "WATCH_BT3_NOT_PAIRED";
    @NotNull
    public static final String i = "WATCH_BT3_PAIRED_NOT_CONNECTED";
    @NotNull
    public final String d;
    @NotNull
    public MutableLiveData<TroubleshootTestUpdateModel> e;
    @Nullable
    public CountDownTimer f;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getWATCH_BT3_NOT_PAIRED() {
            return TroubleshootTestingViewModel.h;
        }

        @NotNull
        public final String getWATCH_BT3_PAIRED_NOT_CONNECTED() {
            return TroubleshootTestingViewModel.i;
        }

        @NotNull
        public final String getWATCH_DISCONNECTED() {
            return TroubleshootTestingViewModel.g;
        }
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TroubleshootTestCategory.values().length];
            try {
                iArr[TroubleshootTestCategory.SMS_NOTIFICATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TroubleshootTestCategory.APP_NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TroubleshootTestCategory.CALL_NOTIFICATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TroubleshootTestCategory.BLUETOOTH_CALLING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TroubleshootTestingViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = "TroubleshootTestingViewModel";
        this.e = new MutableLiveData<>();
    }

    public final void checkAppNotificationSettingsOnApp(@NotNull Context context, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            NotificationSettings notificationsData = UserDataManager.getInstance(context).getNotificationsData();
            if (notificationsData != null && notificationsData.isApp_notifications()) {
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.APP_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.PASSED, null, 4, null));
                return;
            } else {
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.APP_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.FAILED, null, 4, null));
                return;
            }
        }
        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.APP_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.FAILED, g));
    }

    public final void checkBT3ConnectionStatus(@NotNull Context context, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Boolean bool = null;
        ConnectionStatus connectionStatus = bleApi != null ? bleApi.getConnectionStatus() : null;
        ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
        if (connectionStatus == connectionStatus2) {
            String macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
            BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
            if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null) {
                bool = Boolean.valueOf(deviceSupportedFeatures.isKahaBTCallSupported());
            }
            Intrinsics.checkNotNull(bool);
            if (bool.booleanValue()) {
                macAddress = UserDataManager.getInstance(context).getConnectedBTCallDeviceMac();
            } else {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (companion.isTouchELXDevice(context)) {
                    macAddress = PreferenceManagerAbstract.getInstance(context).getConnectedDeviceBT3MacAddress();
                } else if (companion.isEastApexDevice(context)) {
                    macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
                }
            }
            if (macAddress == null) {
                macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
            }
            Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(macAddress, context);
            if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12) {
                if (DeviceUtils.Companion.isOPPDevice(context)) {
                    AclBT3LastReceivedConnectionStatus aclBt3LastReceivedConnectionStatus = SessionManager.getInstance(context).getAclBt3LastReceivedConnectionStatus();
                    if (aclBt3LastReceivedConnectionStatus != null && (aclBt3LastReceivedConnectionStatus.getConnectionStatus() != connectionStatus2 || !Intrinsics.areEqual(aclBt3LastReceivedConnectionStatus.getMacAddress(), macAddress))) {
                        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.BT3_CONNECTIVITY_STATUS, TestingStatus.FAILED, i));
                        return;
                    } else {
                        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.BT3_CONNECTIVITY_STATUS, TestingStatus.PASSED, null, 4, null));
                        return;
                    }
                }
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.BT3_CONNECTIVITY_STATUS, TestingStatus.PASSED, null, 4, null));
                return;
            }
            this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.BT3_CONNECTIVITY_STATUS, TestingStatus.FAILED, h));
            return;
        }
        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.BT3_CONNECTIVITY_STATUS, TestingStatus.FAILED, g));
    }

    public final void checkCallNotificationSettingsOnApp(@NotNull Context context, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            NotificationSettings notificationsData = UserDataManager.getInstance(context).getNotificationsData();
            if (notificationsData != null && notificationsData.isCall_notifications()) {
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.CALL_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.PASSED, null, 4, null));
                return;
            } else {
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.CALL_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.FAILED, null, 4, null));
                return;
            }
        }
        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.CALL_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.FAILED, g));
    }

    public final void checkDnd(@NotNull Context context, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            DoNotDisturbData doNotDisturbData = UserDataManager.getInstance(context).getDoNotDisturbData();
            if (doNotDisturbData != null && !doNotDisturbData.isDnd_on_off() && !doNotDisturbData.isSchedule_on_off()) {
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.DND, TestingStatus.PASSED, null, 4, null));
                return;
            }
            MutableLiveData<TroubleshootTestUpdateModel> mutableLiveData = this.e;
            TroubleshootTestType troubleshootTestType = TroubleshootTestType.DND;
            TestingStatus testingStatus = TestingStatus.FAILED;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = context.getString(R.string.dnd_should_be_disabled_to_receive_);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…_be_disabled_to_receive_)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{getTroubleshootCategoryName(context, troubleshootTestCategory.name())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            mutableLiveData.postValue(new TroubleshootTestUpdateModel(troubleshootTestType, testingStatus, format));
            return;
        }
        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.DND, TestingStatus.FAILED, g));
    }

    public final void checkNotificationAccessAvailable(@NotNull final Context context, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            String string = Settings.Secure.getString(context.getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
            if (string != null) {
                String packageName = context.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
                if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                    NotificationManager.getInstance().notifyOnTroubleshootAppNotificationTest(context, Integer.MAX_VALUE - kotlin.ranges.h.random(new IntRange(1, 10), Random.Default), context.getString(R.string.test_app_notification_1));
                    CountDownTimer countDownTimer = new CountDownTimer() { // from class: com.coveiot.android.leonardo.more.viewmodel.TroubleshootTestingViewModel$checkNotificationAccessAvailable$1

                        @DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.TroubleshootTestingViewModel$checkNotificationAccessAvailable$1$onFinish$1", f = "TroubleshootTestingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        /* loaded from: classes5.dex */
                        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            public final /* synthetic */ Context $context;
                            public int label;
                            public final /* synthetic */ TroubleshootTestingViewModel this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public a(TroubleshootTestingViewModel troubleshootTestingViewModel, Context context, Continuation<? super a> continuation) {
                                super(2, continuation);
                                this.this$0 = troubleshootTestingViewModel;
                                this.$context = context;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @NotNull
                            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                return new a(this.this$0, this.$context, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            @Nullable
                            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @Nullable
                            public final Object invokeSuspend(@NotNull Object obj) {
                                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                if (this.label == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.this$0.getTroubleshootTestResultLiveData().postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.APP_NOTIFICATION_ACCESS, TestingStatus.FAILED, this.$context.getString(R.string.turn_off_again_on_notification_access_)));
                                    return Unit.INSTANCE;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(10000L, 1000L);
                        }

                        @Override // android.os.CountDownTimer
                        public void onFinish() {
                            LogHelper.d(TroubleshootTestingViewModel.this.getTAG(), "CountdownTimer->onFinish");
                            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(TroubleshootTestingViewModel.this), null, null, new a(TroubleshootTestingViewModel.this, context, null), 3, null);
                        }

                        @Override // android.os.CountDownTimer
                        public void onTick(long j) {
                        }
                    };
                    this.f = countDownTimer;
                    countDownTimer.start();
                    return;
                }
            }
            this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.APP_NOTIFICATION_ACCESS, TestingStatus.FAILED, null, 4, null));
            return;
        }
        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.APP_NOTIFICATION_ACCESS, TestingStatus.FAILED, g));
    }

    public final void checkSmsNotificationSettingsOnApp(@NotNull Context context, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            NotificationSettings notificationsData = UserDataManager.getInstance(context).getNotificationsData();
            if (notificationsData != null && notificationsData.isSms_notifications()) {
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.SMS_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.PASSED, null, 4, null));
                return;
            } else {
                this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.SMS_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.FAILED, null, 4, null));
                return;
            }
        }
        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.SMS_NOTIFICATION_SETTINGS_ON_APP, TestingStatus.FAILED, g));
    }

    @NotNull
    public final String getTAG() {
        return this.d;
    }

    @Nullable
    public final CountDownTimer getTTimer() {
        return this.f;
    }

    @Nullable
    public final TroubleshootTestCategory getTroubleshootCategory(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        switch (name.hashCode()) {
            case -1804919247:
                if (name.equals("SMS_NOTIFICATION")) {
                    return TroubleshootTestCategory.SMS_NOTIFICATION;
                }
                break;
            case -1031458829:
                if (name.equals("BLUETOOTH_CALLING")) {
                    return TroubleshootTestCategory.BLUETOOTH_CALLING;
                }
                break;
            case -54373961:
                if (name.equals("CONNECTIVITY")) {
                    return TroubleshootTestCategory.CONNECTIVITY;
                }
                break;
            case 539125353:
                if (name.equals("APP_NOTIFICATION")) {
                    return TroubleshootTestCategory.APP_NOTIFICATION;
                }
                break;
            case 1303556396:
                if (name.equals("CALL_NOTIFICATION")) {
                    return TroubleshootTestCategory.CALL_NOTIFICATION;
                }
                break;
        }
        return null;
    }

    @Nullable
    public final String getTroubleshootCategoryName(@NotNull Context context, @NotNull String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        switch (name.hashCode()) {
            case -1804919247:
                if (name.equals("SMS_NOTIFICATION")) {
                    return context.getString(R.string.sms_notification_title_case);
                }
                break;
            case -1031458829:
                if (name.equals("BLUETOOTH_CALLING")) {
                    return context.getString(R.string.bluetooth_calling_title_case);
                }
                break;
            case -54373961:
                if (name.equals("CONNECTIVITY")) {
                    return context.getString(R.string.connectivity_title_case);
                }
                break;
            case 539125353:
                if (name.equals("APP_NOTIFICATION")) {
                    return context.getString(R.string.app_notification_title_case);
                }
                break;
            case 1303556396:
                if (name.equals("CALL_NOTIFICATION")) {
                    return context.getString(R.string.call_notification_title_case);
                }
                break;
        }
        return null;
    }

    @Nullable
    public final List<TroubleshootingTestModel> getTroubleshootTestModels(@NotNull Context context, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        ArrayList arrayList = new ArrayList();
        int i2 = WhenMappings.$EnumSwitchMapping$0[troubleshootTestCategory.ordinal()];
        if (i2 == 1) {
            if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDndSupported()) {
                int size = arrayList.size() + 1;
                TroubleshootTestType troubleshootTestType = TroubleshootTestType.DND;
                String string = context.getResources().getString(R.string.check_dnd_settings);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…tring.check_dnd_settings)");
                arrayList.add(new TroubleshootingTestModel(size, troubleshootTestType, string, TestingStatus.NOT_STARTED, context.getResources().getString(R.string.dnd_should_be_disabled_to_receive_sms)));
            }
            int size2 = arrayList.size() + 1;
            TroubleshootTestType troubleshootTestType2 = TroubleshootTestType.SMS_NOTIFICATION_SETTINGS_ON_APP;
            String string2 = context.getResources().getString(R.string.check_sms_notification_settings);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ms_notification_settings)");
            TestingStatus testingStatus = TestingStatus.NOT_STARTED;
            arrayList.add(new TroubleshootingTestModel(size2, troubleshootTestType2, string2, testingStatus, context.getResources().getString(R.string.sms_notification_should_be_enabled_on_the_app)));
            int size3 = arrayList.size() + 1;
            TroubleshootTestType troubleshootTestType3 = TroubleshootTestType.APP_NOTIFICATION_ACCESS;
            String string3 = context.getResources().getString(R.string.check_app_notifiaction_Acess_settings);
            Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…ifiaction_Acess_settings)");
            arrayList.add(new TroubleshootingTestModel(size3, troubleshootTestType3, string3, testingStatus, context.getResources().getString(R.string.trun_off_and_again_on_notificiation_access)));
        } else if (i2 == 2) {
            if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDndSupported()) {
                int size4 = arrayList.size() + 1;
                TroubleshootTestType troubleshootTestType4 = TroubleshootTestType.DND;
                String string4 = context.getResources().getString(R.string.check_dnd_settings);
                Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getStr…tring.check_dnd_settings)");
                arrayList.add(new TroubleshootingTestModel(size4, troubleshootTestType4, string4, TestingStatus.NOT_STARTED, null, 16, null));
            }
            int size5 = arrayList.size() + 1;
            TroubleshootTestType troubleshootTestType5 = TroubleshootTestType.APP_NOTIFICATION_SETTINGS_ON_APP;
            String string5 = context.getResources().getString(R.string.check_app_notification_settings);
            Intrinsics.checkNotNullExpressionValue(string5, "context.resources.getStr…pp_notification_settings)");
            TestingStatus testingStatus2 = TestingStatus.NOT_STARTED;
            arrayList.add(new TroubleshootingTestModel(size5, troubleshootTestType5, string5, testingStatus2, null, 16, null));
            TroubleshootTestType troubleshootTestType6 = TroubleshootTestType.APP_NOTIFICATION_ACCESS;
            String string6 = context.getResources().getString(R.string.check_app_notifiaction_Acess_settings);
            Intrinsics.checkNotNullExpressionValue(string6, "context.resources.getStr…ifiaction_Acess_settings)");
            arrayList.add(new TroubleshootingTestModel(arrayList.size() + 1, troubleshootTestType6, string6, testingStatus2, null, 16, null));
        } else if (i2 == 3) {
            if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDndSupported()) {
                int size6 = arrayList.size() + 1;
                TroubleshootTestType troubleshootTestType7 = TroubleshootTestType.DND;
                String string7 = context.getResources().getString(R.string.check_dnd_settings);
                Intrinsics.checkNotNullExpressionValue(string7, "context.resources.getStr…tring.check_dnd_settings)");
                arrayList.add(new TroubleshootingTestModel(size6, troubleshootTestType7, string7, TestingStatus.NOT_STARTED, null, 16, null));
            }
            int size7 = arrayList.size() + 1;
            TroubleshootTestType troubleshootTestType8 = TroubleshootTestType.CALL_NOTIFICATION_SETTINGS_ON_APP;
            String string8 = context.getResources().getString(R.string.check_call_notification_settings);
            Intrinsics.checkNotNullExpressionValue(string8, "context.resources.getStr…ll_notification_settings)");
            TestingStatus testingStatus3 = TestingStatus.NOT_STARTED;
            arrayList.add(new TroubleshootingTestModel(size7, troubleshootTestType8, string8, testingStatus3, null, 16, null));
            TroubleshootTestType troubleshootTestType9 = TroubleshootTestType.APP_NOTIFICATION_ACCESS;
            String string9 = context.getResources().getString(R.string.check_app_notifiaction_Acess_settings);
            Intrinsics.checkNotNullExpressionValue(string9, "context.resources.getStr…ifiaction_Acess_settings)");
            arrayList.add(new TroubleshootingTestModel(arrayList.size() + 1, troubleshootTestType9, string9, testingStatus3, null, 16, null));
        } else if (i2 == 4) {
            if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDndSupported()) {
                int size8 = arrayList.size() + 1;
                TroubleshootTestType troubleshootTestType10 = TroubleshootTestType.DND;
                String string10 = context.getResources().getString(R.string.check_dnd_settings);
                Intrinsics.checkNotNullExpressionValue(string10, "context.resources.getStr…tring.check_dnd_settings)");
                arrayList.add(new TroubleshootingTestModel(size8, troubleshootTestType10, string10, TestingStatus.NOT_STARTED, null, 16, null));
            }
            int size9 = arrayList.size() + 1;
            TroubleshootTestType troubleshootTestType11 = TroubleshootTestType.CALL_NOTIFICATION_SETTINGS_ON_APP;
            String string11 = context.getResources().getString(R.string.check_call_notification_settings);
            Intrinsics.checkNotNullExpressionValue(string11, "context.resources.getStr…ll_notification_settings)");
            TestingStatus testingStatus4 = TestingStatus.NOT_STARTED;
            arrayList.add(new TroubleshootingTestModel(size9, troubleshootTestType11, string11, testingStatus4, null, 16, null));
            int size10 = arrayList.size() + 1;
            TroubleshootTestType troubleshootTestType12 = TroubleshootTestType.APP_NOTIFICATION_ACCESS;
            String string12 = context.getResources().getString(R.string.check_app_notifiaction_Acess_settings);
            Intrinsics.checkNotNullExpressionValue(string12, "context.resources.getStr…ifiaction_Acess_settings)");
            arrayList.add(new TroubleshootingTestModel(size10, troubleshootTestType12, string12, testingStatus4, null, 16, null));
            TroubleshootTestType troubleshootTestType13 = TroubleshootTestType.BT3_CONNECTIVITY_STATUS;
            String string13 = context.getResources().getString(R.string.check_bt_connectivity_settings);
            Intrinsics.checkNotNullExpressionValue(string13, "context.resources.getStr…bt_connectivity_settings)");
            arrayList.add(new TroubleshootingTestModel(arrayList.size() + 1, troubleshootTestType13, string13, testingStatus4, null, 16, null));
        }
        return arrayList;
    }

    @NotNull
    public final MutableLiveData<TroubleshootTestUpdateModel> getTroubleshootTestResultLiveData() {
        return this.e;
    }

    public final void setTTimer(@Nullable CountDownTimer countDownTimer) {
        this.f = countDownTimer;
    }

    public final void setTroubleshootTestResultLiveData(@NotNull MutableLiveData<TroubleshootTestUpdateModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void updateOnNotificationAccessReceived() {
        CountDownTimer countDownTimer = this.f;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.f = null;
        this.e.postValue(new TroubleshootTestUpdateModel(TroubleshootTestType.APP_NOTIFICATION_ACCESS, TestingStatus.PASSED, null, 4, null));
    }
}
