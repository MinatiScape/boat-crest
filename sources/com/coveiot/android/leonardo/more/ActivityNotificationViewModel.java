package com.coveiot.android.leonardo.more;

import android.content.Context;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.smartalert.SmartAlertPreferenceManager;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveNotificationSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveNotificationSettingsRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.covepreferences.data.SmartAlertAppServerConfData;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.szabh.smable3.entity.BleNotification;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNotificationViewModel extends ViewModel {
    public boolean A;
    @Nullable
    public final List<SmartAlertAppServerConfData> B;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4868a;
    public AdapterAppList adapterAppList;
    public List<AppNotificationData> appData;
    public List<AppNotificationData> appDataList;
    public List<AppNotificationData> appNotificationData;
    public SwitchCompat appSwitch;
    public final String b;
    @NotNull
    public MutableLiveData<Boolean> c;
    public SwitchCompat callSwitch;
    @NotNull
    public String[] d;
    @NotNull
    public String[] e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public PermissionListener mListener;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public RecyclerView recyclerView;
    public boolean s;
    public SwitchCompat smsSwitch;
    public boolean t;
    public boolean u;
    public UserDataManager userDataManager;
    public boolean v;
    public ViewModelListener viewModelListener;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.ActivityNotificationViewModel$initValues$1", f = "ActivityNotificationViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                ActivityNotificationViewModel.this.getRecyclerView().setLayoutManager(new LinearLayoutManager(ActivityNotificationViewModel.this.getContext()));
                ActivityNotificationViewModel.this.getRecyclerView().setAdapter(ActivityNotificationViewModel.this.getAdapterAppList());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.ActivityNotificationViewModel$initValues$2", f = "ActivityNotificationViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ActivityNotificationViewModel.this.getSmsSwitch().setChecked(ActivityNotificationViewModel.this.getUserDataManager().getNotificationsData().isSms_notifications());
                ActivityNotificationViewModel.this.getCallSwitch().setChecked(ActivityNotificationViewModel.this.getUserDataManager().getNotificationsData().isCall_notifications());
                ActivityNotificationViewModel.this.getAppSwitch().setChecked(ActivityNotificationViewModel.this.getUserDataManager().getNotificationsData().isApp_notifications());
                if (ActivityNotificationViewModel.this.getUserDataManager().getNotificationsData().isApp_notifications()) {
                    ActivityNotificationViewModel.this.getRecyclerView().setVisibility(0);
                } else {
                    ActivityNotificationViewModel.this.getRecyclerView().setVisibility(8);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.ActivityNotificationViewModel$initValues$3", f = "ActivityNotificationViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            BleApi bleApi;
            DeviceSupportedFeatures deviceSupportedFeatures;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ActivityNotificationViewModel.this.getAppDataList().clear();
                ActivityNotificationViewModel.this.getAppData().clear();
                int length = ActivityNotificationViewModel.this.getApp_list().length;
                for (int i = 0; i < length; i++) {
                    ActivityNotificationViewModel activityNotificationViewModel = ActivityNotificationViewModel.this;
                    int i2 = activityNotificationViewModel.i(activityNotificationViewModel.getApp_list()[i]);
                    AppNotificationData appNotificationData = new AppNotificationData();
                    if (i2 != -1) {
                        appNotificationData.setPackageName(ActivityNotificationViewModel.this.getApp_list()[i]);
                        appNotificationData.setChecked(ActivityNotificationViewModel.this.getUserDataManager().getAppNotificationsData().get(i2).getChecked());
                        appNotificationData.setAppName(ActivityNotificationViewModel.this.getApp_name_list()[i]);
                    } else {
                        appNotificationData.setPackageName(ActivityNotificationViewModel.this.getApp_list()[i]);
                        appNotificationData.setChecked(false);
                        appNotificationData.setAppName(ActivityNotificationViewModel.this.getApp_name_list()[i]);
                    }
                    if (!ActivityNotificationViewModel.this.getAppDataList().contains(appNotificationData)) {
                        BleApiManager bleApiManager = BleApiManager.getInstance(ActivityNotificationViewModel.this.getContext());
                        Boolean boxBoolean = (bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boxing.boxBoolean(deviceSupportedFeatures.isSmartAlertsSupported());
                        Intrinsics.checkNotNull(boxBoolean);
                        if (boxBoolean.booleanValue()) {
                            List<SmartAlertAppServerConfData> smartAlertAppServerConfDataList = ActivityNotificationViewModel.this.getSmartAlertAppServerConfDataList();
                            if (!(smartAlertAppServerConfDataList == null || smartAlertAppServerConfDataList.isEmpty())) {
                                SmartAlertUtils.Companion companion = SmartAlertUtils.Companion;
                                Context context = ActivityNotificationViewModel.this.getContext();
                                String str = ActivityNotificationViewModel.this.getApp_list()[i];
                                Intrinsics.checkNotNullExpressionValue(str, "app_list[i]");
                                if (companion.isSmartAlertSupportedByPackageName(context, str, ActivityNotificationViewModel.this.getSmartAlertAppServerConfDataList())) {
                                    ActivityNotificationViewModel.this.getAppDataList().add(0, appNotificationData);
                                    ActivityNotificationViewModel.this.getAppData().add(0, appNotificationData);
                                } else {
                                    ActivityNotificationViewModel.this.getAppDataList().add(appNotificationData);
                                    ActivityNotificationViewModel.this.getAppData().add(appNotificationData);
                                }
                            }
                        }
                        ActivityNotificationViewModel.this.getAppDataList().add(appNotificationData);
                        ActivityNotificationViewModel.this.getAppData().add(appNotificationData);
                    }
                }
                PreferenceManager.saveAppNotificationData(ActivityNotificationViewModel.this.getContext(), (ArrayList) ActivityNotificationViewModel.this.getAppDataList());
                UserDataManager.getInstance(ActivityNotificationViewModel.this.getContext()).saveAppNotificationsSettings(ActivityNotificationViewModel.this.getAppDataList());
                ActivityNotificationViewModel.this.saveAppData();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.ActivityNotificationViewModel$saveAppData$1", f = "ActivityNotificationViewModel.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.o2}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ActivityNotificationViewModel.this.getAdapterAppList().setAppNotificationData(ActivityNotificationViewModel.this.getAppNotificationData());
                this.label = 1;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            ActivityNotificationViewModel.this.getShowProgressLiveData().setValue(Boxing.boxBoolean(false));
            return Unit.INSTANCE;
        }
    }

    public ActivityNotificationViewModel(@NotNull Context context) {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4868a = context;
        this.b = ActivityNotificationViewModel.class.getCanonicalName();
        this.c = new MutableLiveData<>(Boolean.FALSE);
        String[] stringArray = context.getResources().getStringArray(R.array.app_package_list_1860);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1860)");
        this.d = stringArray;
        String[] stringArray2 = context.getResources().getStringArray(R.array.app_name_list_1860);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1860)");
        this.e = stringArray2;
        BleApiManager bleApiManager = BleApiManager.getInstance(context);
        Boolean valueOf = (bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isSmartAlertsSupported());
        Intrinsics.checkNotNull(valueOf);
        this.B = valueOf.booleanValue() ? SmartAlertPreferenceManager.Companion.getInstance(context).getSmartAlertAppServerConfigData() : null;
    }

    public final void checkCallPermission() {
        getMListener().checkPermssion("android.permission.READ_CALL_LOG", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.ActivityNotificationViewModel$checkCallPermission$permissionListener$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityNotificationViewModel.this.getMListener().askPermission(PermissionType.CALL);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityNotificationViewModel.this.getMListener().onPermissionDisabled(PermissionType.CALL);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityNotificationViewModel.this.getMListener().onPermissionSuccess(PermissionType.CALL);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityNotificationViewModel.this.getMListener().onPermissionDenied(PermissionType.CALL);
            }
        });
    }

    public final void checkContactPermission() {
        getMListener().checkPermssion("android.permission.READ_CONTACTS", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.ActivityNotificationViewModel$checkContactPermission$permissionListener$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityNotificationViewModel.this.getMListener().askPermission(PermissionType.CONTACTS);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityNotificationViewModel.this.getMListener().onPermissionDisabled(PermissionType.CONTACTS);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityNotificationViewModel.this.getMListener().onPermissionSuccess(PermissionType.CONTACTS);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityNotificationViewModel.this.getMListener().onPermissionDenied(PermissionType.CONTACTS);
            }
        });
    }

    public final void checkPhonePermission() {
        getMListener().checkPermssion("android.permission.READ_PHONE_STATE", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.ActivityNotificationViewModel$checkPhonePermission$permissionListener$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityNotificationViewModel.this.getMListener().askPermission(PermissionType.PHONE);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityNotificationViewModel.this.getMListener().onPermissionDisabled(PermissionType.PHONE);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityNotificationViewModel.this.getMListener().onPermissionSuccess(PermissionType.PHONE);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityNotificationViewModel.this.getMListener().onPermissionDenied(PermissionType.PHONE);
            }
        });
    }

    @NotNull
    public final AdapterAppList getAdapterAppList() {
        AdapterAppList adapterAppList = this.adapterAppList;
        if (adapterAppList != null) {
            return adapterAppList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapterAppList");
        return null;
    }

    @NotNull
    public final List<AppNotificationData> getAppData() {
        List<AppNotificationData> list = this.appData;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appData");
        return null;
    }

    @NotNull
    public final List<AppNotificationData> getAppDataList() {
        List<AppNotificationData> list = this.appDataList;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appDataList");
        return null;
    }

    @NotNull
    public final List<AppNotificationData> getAppNotificationData() {
        List<AppNotificationData> list = this.appNotificationData;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appNotificationData");
        return null;
    }

    @NotNull
    public final SwitchCompat getAppSwitch() {
        SwitchCompat switchCompat = this.appSwitch;
        if (switchCompat != null) {
            return switchCompat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appSwitch");
        return null;
    }

    @NotNull
    public final String[] getApp_list() {
        return this.d;
    }

    @NotNull
    public final String[] getApp_name_list() {
        return this.e;
    }

    @NotNull
    public final SwitchCompat getCallSwitch() {
        SwitchCompat switchCompat = this.callSwitch;
        if (switchCompat != null) {
            return switchCompat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("callSwitch");
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f4868a;
    }

    @Nullable
    public final Pair<ArrayList<String>, ArrayList<String>> getInstalledAppsList() {
        return AppListHelper.Companion.loadAppListFromSystem(this.f4868a);
    }

    @NotNull
    public final PermissionListener getMListener() {
        PermissionListener permissionListener = this.mListener;
        if (permissionListener != null) {
            return permissionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    @NotNull
    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        return null;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShowProgressLiveData() {
        return this.c;
    }

    @Nullable
    public final List<SmartAlertAppServerConfData> getSmartAlertAppServerConfDataList() {
        return this.B;
    }

    @NotNull
    public final SwitchCompat getSmsSwitch() {
        SwitchCompat switchCompat = this.smsSwitch;
        if (switchCompat != null) {
            return switchCompat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("smsSwitch");
        return null;
    }

    @NotNull
    public final UserDataManager getUserDataManager() {
        UserDataManager userDataManager = this.userDataManager;
        if (userDataManager != null) {
            return userDataManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("userDataManager");
        return null;
    }

    @NotNull
    public final ViewModelListener getViewModelListener() {
        ViewModelListener viewModelListener = this.viewModelListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelListener");
        return null;
    }

    public final void h() {
        if (AppUtils.isNetConnected(this.f4868a)) {
            List<AppNotificationData> appNotificationsData = getUserDataManager().getAppNotificationsData();
            SaveNotificationSettingsReq saveNotificationSettingsReq = new SaveNotificationSettingsReq();
            SaveNotificationSettingsReq.SmsBean smsBean = new SaveNotificationSettingsReq.SmsBean();
            SaveNotificationSettingsReq.CallBean callBean = new SaveNotificationSettingsReq.CallBean();
            SaveNotificationSettingsReq.AppBean appBean = new SaveNotificationSettingsReq.AppBean();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = appNotificationsData.size();
            int i = 0;
            while (i < size) {
                SaveNotificationSettingsReq.AppBean.Apps apps = new SaveNotificationSettingsReq.AppBean.Apps();
                String packageName = appNotificationsData.get(i).getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "appNotificationData[i].packageName");
                String lowerCase = packageName.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                int i2 = size;
                SaveNotificationSettingsReq saveNotificationSettingsReq2 = saveNotificationSettingsReq;
                String str = null;
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) BleNotification.WE_CHAT, false, 2, (Object) null)) {
                    str = this.f4868a.getResources().getString(R.string.notify_wechat);
                } else {
                    String packageName2 = appNotificationsData.get(i).getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName2, "appNotificationData[i].packageName");
                    String lowerCase2 = packageName2.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) BleNotification.FACEBOOK, false, 2, (Object) null)) {
                        str = this.f4868a.getResources().getString(R.string.notify_facebook);
                    } else {
                        String packageName3 = appNotificationsData.get(i).getPackageName();
                        Intrinsics.checkNotNullExpressionValue(packageName3, "appNotificationData[i].packageName");
                        String lowerCase3 = packageName3.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) "com.whatsapp.w4b", false, 2, (Object) null)) {
                            str = this.f4868a.getResources().getString(R.string.notify_whatsapp_bussiness);
                        } else {
                            String packageName4 = appNotificationsData.get(i).getPackageName();
                            Intrinsics.checkNotNullExpressionValue(packageName4, "appNotificationData[i].packageName");
                            String lowerCase4 = packageName4.toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase4, (CharSequence) BleNotification.WHATS_APP, false, 2, (Object) null)) {
                                str = this.f4868a.getResources().getString(R.string.notify_whatsapp);
                            } else {
                                String packageName5 = appNotificationsData.get(i).getPackageName();
                                Intrinsics.checkNotNullExpressionValue(packageName5, "appNotificationData[i].packageName");
                                String lowerCase5 = packageName5.toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase5, (CharSequence) BleNotification.TWITTER, false, 2, (Object) null)) {
                                    str = this.f4868a.getResources().getString(R.string.notify_twitter);
                                } else {
                                    String packageName6 = appNotificationsData.get(i).getPackageName();
                                    Intrinsics.checkNotNullExpressionValue(packageName6, "appNotificationData[i].packageName");
                                    String lowerCase6 = packageName6.toLowerCase();
                                    Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase6, (CharSequence) BleNotification.QQ, false, 2, (Object) null)) {
                                        str = this.f4868a.getResources().getString(R.string.notify_qq_messenger);
                                    } else {
                                        String packageName7 = appNotificationsData.get(i).getPackageName();
                                        Intrinsics.checkNotNullExpressionValue(packageName7, "appNotificationData[i].packageName");
                                        String lowerCase7 = packageName7.toLowerCase();
                                        Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase7, (CharSequence) "com.o2m.gsk", false, 2, (Object) null)) {
                                            str = this.f4868a.getResources().getString(R.string.notify_qzone);
                                        } else {
                                            String packageName8 = appNotificationsData.get(i).getPackageName();
                                            Intrinsics.checkNotNullExpressionValue(packageName8, "appNotificationData[i].packageName");
                                            String lowerCase8 = packageName8.toLowerCase();
                                            Intrinsics.checkNotNullExpressionValue(lowerCase8, "this as java.lang.String).toLowerCase()");
                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase8, (CharSequence) BleNotification.SNAPCHAT, false, 2, (Object) null)) {
                                                str = this.f4868a.getResources().getString(R.string.notify_snapchat);
                                            } else {
                                                String packageName9 = appNotificationsData.get(i).getPackageName();
                                                Intrinsics.checkNotNullExpressionValue(packageName9, "appNotificationData[i].packageName");
                                                String lowerCase9 = packageName9.toLowerCase();
                                                Intrinsics.checkNotNullExpressionValue(lowerCase9, "this as java.lang.String).toLowerCase()");
                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase9, (CharSequence) BleNotification.SKYPE, false, 2, (Object) null)) {
                                                    str = this.f4868a.getResources().getString(R.string.notify_skype);
                                                } else {
                                                    String packageName10 = appNotificationsData.get(i).getPackageName();
                                                    Intrinsics.checkNotNullExpressionValue(packageName10, "appNotificationData[i].packageName");
                                                    String lowerCase10 = packageName10.toLowerCase();
                                                    Intrinsics.checkNotNullExpressionValue(lowerCase10, "this as java.lang.String).toLowerCase()");
                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase10, (CharSequence) BleNotification.FACEBOOK_MESSENGER, false, 2, (Object) null)) {
                                                        str = this.f4868a.getResources().getString(R.string.notify_fb_messenger);
                                                    } else {
                                                        String packageName11 = appNotificationsData.get(i).getPackageName();
                                                        Intrinsics.checkNotNullExpressionValue(packageName11, "appNotificationData[i].packageName");
                                                        String lowerCase11 = packageName11.toLowerCase();
                                                        Intrinsics.checkNotNullExpressionValue(lowerCase11, "this as java.lang.String).toLowerCase()");
                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase11, (CharSequence) BleNotification.GMAIL, false, 2, (Object) null)) {
                                                            str = this.f4868a.getResources().getString(R.string.notify_gmail);
                                                        } else {
                                                            String packageName12 = appNotificationsData.get(i).getPackageName();
                                                            Intrinsics.checkNotNullExpressionValue(packageName12, "appNotificationData[i].packageName");
                                                            String lowerCase12 = packageName12.toLowerCase();
                                                            Intrinsics.checkNotNullExpressionValue(lowerCase12, "this as java.lang.String).toLowerCase()");
                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase12, (CharSequence) BleNotification.INSTAGRAM, false, 2, (Object) null)) {
                                                                str = this.f4868a.getResources().getString(R.string.notify_instagram);
                                                            } else {
                                                                String packageName13 = appNotificationsData.get(i).getPackageName();
                                                                Intrinsics.checkNotNullExpressionValue(packageName13, "appNotificationData[i].packageName");
                                                                String lowerCase13 = packageName13.toLowerCase();
                                                                Intrinsics.checkNotNullExpressionValue(lowerCase13, "this as java.lang.String).toLowerCase()");
                                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase13, (CharSequence) BleNotification.TELEGRAM, false, 2, (Object) null)) {
                                                                    str = this.f4868a.getResources().getString(R.string.notify_telegram);
                                                                } else {
                                                                    String packageName14 = appNotificationsData.get(i).getPackageName();
                                                                    Intrinsics.checkNotNullExpressionValue(packageName14, "appNotificationData[i].packageName");
                                                                    String lowerCase14 = packageName14.toLowerCase();
                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase14, "this as java.lang.String).toLowerCase()");
                                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase14, (CharSequence) BleNotification.LINKED_IN, false, 2, (Object) null)) {
                                                                        str = this.f4868a.getResources().getString(R.string.notify_linkedin);
                                                                    } else {
                                                                        String packageName15 = appNotificationsData.get(i).getPackageName();
                                                                        Intrinsics.checkNotNullExpressionValue(packageName15, "appNotificationData[i].packageName");
                                                                        String lowerCase15 = packageName15.toLowerCase();
                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase15, "this as java.lang.String).toLowerCase()");
                                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase15, (CharSequence) "com.vkontakte.android", false, 2, (Object) null)) {
                                                                            str = this.f4868a.getResources().getString(R.string.notify_vkclient);
                                                                        } else {
                                                                            String packageName16 = appNotificationsData.get(i).getPackageName();
                                                                            Intrinsics.checkNotNullExpressionValue(packageName16, "appNotificationData[i].packageName");
                                                                            String lowerCase16 = packageName16.toLowerCase();
                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase16, "this as java.lang.String).toLowerCase()");
                                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase16, (CharSequence) BleNotification.LINE, false, 2, (Object) null)) {
                                                                                str = this.f4868a.getResources().getString(R.string.notify_line_messenger);
                                                                            } else {
                                                                                String packageName17 = appNotificationsData.get(i).getPackageName();
                                                                                Intrinsics.checkNotNullExpressionValue(packageName17, "appNotificationData[i].packageName");
                                                                                String lowerCase17 = packageName17.toLowerCase();
                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase17, "this as java.lang.String).toLowerCase()");
                                                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase17, (CharSequence) "com.google.android.calendar", false, 2, (Object) null)) {
                                                                                    str = this.f4868a.getResources().getString(R.string.notify_calendar);
                                                                                } else {
                                                                                    String packageName18 = appNotificationsData.get(i).getPackageName();
                                                                                    Intrinsics.checkNotNullExpressionValue(packageName18, "appNotificationData[i].packageName");
                                                                                    String lowerCase18 = packageName18.toLowerCase();
                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase18, "this as java.lang.String).toLowerCase()");
                                                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase18, (CharSequence) BleNotification.YOUTUBE, false, 2, (Object) null)) {
                                                                                        str = this.f4868a.getResources().getString(R.string.notify_youtube);
                                                                                    } else {
                                                                                        String packageName19 = appNotificationsData.get(i).getPackageName();
                                                                                        Intrinsics.checkNotNullExpressionValue(packageName19, "appNotificationData[i].packageName");
                                                                                        String lowerCase19 = packageName19.toLowerCase();
                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase19, "this as java.lang.String).toLowerCase()");
                                                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase19, (CharSequence) BleNotification.OUT_LOOK, false, 2, (Object) null)) {
                                                                                            str = this.f4868a.getResources().getString(R.string.notify_outlook);
                                                                                        } else {
                                                                                            String packageName20 = appNotificationsData.get(i).getPackageName();
                                                                                            Intrinsics.checkNotNullExpressionValue(packageName20, "appNotificationData[i].packageName");
                                                                                            String lowerCase20 = packageName20.toLowerCase();
                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase20, "this as java.lang.String).toLowerCase()");
                                                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase20, (CharSequence) BleNotification.YAHOO_MAIL, false, 2, (Object) null)) {
                                                                                                str = this.f4868a.getResources().getString(R.string.notify_yahoo_mail);
                                                                                            } else if (!BleApiManager.getInstance(this.f4868a).getBleApi().getDeviceSupportedFeatures().isExtendedNotificationsSupported()) {
                                                                                                str = "UNLISTED_APPS";
                                                                                            } else if (appNotificationsData.get(i).getChecked()) {
                                                                                                SaveNotificationSettingsReq.AppBean.AndroidApps androidApps = new SaveNotificationSettingsReq.AppBean.AndroidApps();
                                                                                                androidApps.setActive(Boolean.valueOf(appNotificationsData.get(i).getChecked()));
                                                                                                androidApps.setPackageX(appNotificationsData.get(i).getPackageName());
                                                                                                arrayList2.add(androidApps);
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!AppUtils.isEmpty(str)) {
                    apps.setActive(Boolean.valueOf(appNotificationsData.get(i).getChecked()));
                    apps.setAppId(str);
                    arrayList.add(apps);
                }
                i++;
                size = i2;
                saveNotificationSettingsReq = saveNotificationSettingsReq2;
            }
            SaveNotificationSettingsReq saveNotificationSettingsReq3 = saveNotificationSettingsReq;
            smsBean.setActive(getUserDataManager().getNotificationsData().isSms_notifications());
            callBean.setActive(getUserDataManager().getNotificationsData().isCall_notifications());
            appBean.setEnableAll(getUserDataManager().getNotificationsData().isApp_notifications());
            appBean.setApps(arrayList);
            if (!arrayList2.isEmpty()) {
                appBean.setAndroidApps(arrayList2);
            }
            saveNotificationSettingsReq3.setCall(callBean);
            saveNotificationSettingsReq3.setSms(smsBean);
            saveNotificationSettingsReq3.setOtherApps(appBean);
            CoveUserAppSettings.saveNotificationSettings(saveNotificationSettingsReq3, new CoveApiListener<SaveNotificationSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.ActivityNotificationViewModel$callNotificationApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ViewModelListener viewModelListener = ActivityNotificationViewModel.this.getViewModelListener();
                    String string = ActivityNotificationViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                    viewModelListener.onDataFailure(string);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveNotificationSettingsRes saveNotificationSettingsRes) {
                    ActivityNotificationViewModel.this.getViewModelListener().onSuccess();
                }
            });
            return;
        }
        ViewModelListener viewModelListener = getViewModelListener();
        String string = this.f4868a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        viewModelListener.onDataFailure(string);
    }

    public final int i(String str) {
        if (getUserDataManager().getAppNotificationsData() == null || str == null) {
            return -1;
        }
        int size = getUserDataManager().getAppNotificationsData().size();
        for (int i = 0; i < size; i++) {
            if (getUserDataManager().getAppNotificationsData().get(i).getPackageName() != null && getUserDataManager().getAppNotificationsData().get(i).getPackageName().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public final void initValues(@NotNull UserDataManager muserDataManager, @NotNull List<AppNotificationData> mappData, @NotNull List<AppNotificationData> mappDataList, @NotNull ArrayList<AppNotificationData> mappNotificationData, @NotNull SwitchCompat msmsSwicth, @NotNull SwitchCompat mcallSwitch, @NotNull SwitchCompat mappSwitch, @NotNull RecyclerView mrecyclerView, @NotNull AdapterAppList madapterList) {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        BleApi bleApi2;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(muserDataManager, "muserDataManager");
        Intrinsics.checkNotNullParameter(mappData, "mappData");
        Intrinsics.checkNotNullParameter(mappDataList, "mappDataList");
        Intrinsics.checkNotNullParameter(mappNotificationData, "mappNotificationData");
        Intrinsics.checkNotNullParameter(msmsSwicth, "msmsSwicth");
        Intrinsics.checkNotNullParameter(mcallSwitch, "mcallSwitch");
        Intrinsics.checkNotNullParameter(mappSwitch, "mappSwitch");
        Intrinsics.checkNotNullParameter(mrecyclerView, "mrecyclerView");
        Intrinsics.checkNotNullParameter(madapterList, "madapterList");
        if (!m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.j1790_device), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.j1810g_device), false)) {
            if (!m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.j1963d_device), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.j1963yh_device), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.j1860_device), false)) {
                if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.j1860_device), false)) {
                    String[] stringArray = this.f4868a.getResources().getStringArray(R.array.app_package_list_1860);
                    Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1860)");
                    this.d = stringArray;
                    String[] stringArray2 = this.f4868a.getResources().getStringArray(R.array.app_name_list_1860);
                    Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1860)");
                    this.e = stringArray2;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.smaf2_device), false)) {
                    String[] stringArray3 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray3;
                    String[] stringArray4 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray4, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray4;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.smas12_device), false)) {
                    String[] stringArray5 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray5, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray5;
                    String[] stringArray6 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray6, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray6;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.sma_wave_genesis_pro), false)) {
                    String[] stringArray7 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray7, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray7;
                    String[] stringArray8 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray8, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray8;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.sma_wave_elevate_pro), false)) {
                    String[] stringArray9 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray9, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray9;
                    String[] stringArray10 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray10, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray10;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.sma_wave_glory_pro), false)) {
                    String[] stringArray11 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray11, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray11;
                    String[] stringArray12 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray12, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray12;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.sma_ultima_vogue), false)) {
                    String[] stringArray13 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray13, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray13;
                    String[] stringArray14 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray14, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray14;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.sma_lunar_seek), false)) {
                    String[] stringArray15 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray15, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray15;
                    String[] stringArray16 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray16, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray16;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.sma_lunar_comet), false)) {
                    String[] stringArray17 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray17, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray17;
                    String[] stringArray18 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray18, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray18;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.sma_lunar_velocity), false)) {
                    String[] stringArray19 = this.f4868a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray19, "context.resources.getStr….app_package_list_sma_f2)");
                    this.d = stringArray19;
                    String[] stringArray20 = this.f4868a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                    Intrinsics.checkNotNullExpressionValue(stringArray20, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    this.e = stringArray20;
                } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.moyangy20_device), false)) {
                    String[] stringArray21 = this.f4868a.getResources().getStringArray(R.array.app_package_list_moyang);
                    Intrinsics.checkNotNullExpressionValue(stringArray21, "context.resources.getStr….app_package_list_moyang)");
                    this.d = stringArray21;
                    String[] stringArray22 = this.f4868a.getResources().getStringArray(R.array.app_name_list_moyang);
                    Intrinsics.checkNotNullExpressionValue(stringArray22, "context.resources.getStr…ray.app_name_list_moyang)");
                    this.e = stringArray22;
                } else {
                    if (!m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.matrix_device), false)) {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (!companion.isRuggedDevice(this.f4868a)) {
                            if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.moyangygpf5_device), false)) {
                                String[] stringArray23 = this.f4868a.getResources().getStringArray(R.array.app_package_list_moyang_gpf5);
                                Intrinsics.checkNotNullExpressionValue(stringArray23, "context.resources.getStr…package_list_moyang_gpf5)");
                                this.d = stringArray23;
                                String[] stringArray24 = this.f4868a.getResources().getStringArray(R.array.app_name_list_moyang_gpf5);
                                Intrinsics.checkNotNullExpressionValue(stringArray24, "context.resources.getStr…pp_name_list_moyang_gpf5)");
                                this.e = stringArray24;
                            } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca0), false)) {
                                String[] stringArray25 = this.f4868a.getResources().getStringArray(R.array.app_package_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray25, "context.resources.getStr…rray.app_package_list_ca)");
                                this.d = stringArray25;
                                String[] stringArray26 = this.f4868a.getResources().getStringArray(R.array.app_name_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray26, "context.resources.getStr…R.array.app_name_list_ca)");
                                this.e = stringArray26;
                            } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca3), false)) {
                                String[] stringArray27 = this.f4868a.getResources().getStringArray(R.array.app_package_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray27, "context.resources.getStr…rray.app_package_list_ca)");
                                this.d = stringArray27;
                                String[] stringArray28 = this.f4868a.getResources().getStringArray(R.array.app_name_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray28, "context.resources.getStr…R.array.app_name_list_ca)");
                                this.e = stringArray28;
                            } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca3_bt), false)) {
                                String[] stringArray29 = this.f4868a.getResources().getStringArray(R.array.app_package_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray29, "context.resources.getStr…rray.app_package_list_ca)");
                                this.d = stringArray29;
                                String[] stringArray30 = this.f4868a.getResources().getStringArray(R.array.app_name_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray30, "context.resources.getStr…R.array.app_name_list_ca)");
                                this.e = stringArray30;
                            } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca2), false)) {
                                String[] stringArray31 = this.f4868a.getResources().getStringArray(R.array.app_package_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray31, "context.resources.getStr…rray.app_package_list_ca)");
                                this.d = stringArray31;
                                String[] stringArray32 = this.f4868a.getResources().getStringArray(R.array.app_name_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray32, "context.resources.getStr…R.array.app_name_list_ca)");
                                this.e = stringArray32;
                            } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.ido_select), false)) {
                                String[] stringArray33 = this.f4868a.getResources().getStringArray(R.array.app_package_list_ido);
                                Intrinsics.checkNotNullExpressionValue(stringArray33, "context.resources.getStr…ray.app_package_list_ido)");
                                this.d = stringArray33;
                                String[] stringArray34 = this.f4868a.getResources().getStringArray(R.array.app_name_list_ido);
                                Intrinsics.checkNotNullExpressionValue(stringArray34, "context.resources.getStr….array.app_name_list_ido)");
                                this.e = stringArray34;
                            } else if (m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.ido_connect), false)) {
                                String[] stringArray35 = this.f4868a.getResources().getStringArray(R.array.app_package_list_ido);
                                Intrinsics.checkNotNullExpressionValue(stringArray35, "context.resources.getStr…ray.app_package_list_ido)");
                                this.d = stringArray35;
                                String[] stringArray36 = this.f4868a.getResources().getStringArray(R.array.app_name_list_ido);
                                Intrinsics.checkNotNullExpressionValue(stringArray36, "context.resources.getStr….array.app_name_list_ido)");
                                this.e = stringArray36;
                            } else if (companion.isTouchELXDevice(this.f4868a)) {
                                String[] stringArray37 = this.f4868a.getResources().getStringArray(R.array.app_package_list_touch);
                                Intrinsics.checkNotNullExpressionValue(stringArray37, "context.resources.getStr…y.app_package_list_touch)");
                                this.d = stringArray37;
                                String[] stringArray38 = this.f4868a.getResources().getStringArray(R.array.app_name_list_touch);
                                Intrinsics.checkNotNullExpressionValue(stringArray38, "context.resources.getStr…rray.app_name_list_touch)");
                                this.e = stringArray38;
                            } else if (companion.isEastApexDevice(this.f4868a)) {
                                String[] stringArray39 = this.f4868a.getResources().getStringArray(R.array.app_package_list_eastapex);
                                Intrinsics.checkNotNullExpressionValue(stringArray39, "context.resources.getStr…pp_package_list_eastapex)");
                                this.d = stringArray39;
                                String[] stringArray40 = this.f4868a.getResources().getStringArray(R.array.app_name_list_eastapex);
                                Intrinsics.checkNotNullExpressionValue(stringArray40, "context.resources.getStr…y.app_name_list_eastapex)");
                                this.e = stringArray40;
                            } else if (!m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca3_wave_cosmos), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca5_wave_beat), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca5_wave_play), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ca5_wave_style), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ulc3_wave_smart), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) && !m.equals(SessionManager.getInstance(this.f4868a).getDeviceType(), this.f4868a.getResources().getString(R.string.cove_ulc2_wave_lync), false)) {
                                BleApiManager bleApiManager = BleApiManager.getInstance(this.f4868a);
                                Boolean valueOf = (bleApiManager == null || (bleApi2 = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures2 = bleApi2.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures2.isExtendedNotificationsSupported());
                                Intrinsics.checkNotNull(valueOf);
                                if (valueOf.booleanValue()) {
                                    Pair<ArrayList<String>, ArrayList<String>> installedAppsList = getInstalledAppsList();
                                    if (installedAppsList != null) {
                                        this.d = (String[]) installedAppsList.getSecond().toArray(new String[0]);
                                        this.e = (String[]) installedAppsList.getFirst().toArray(new String[0]);
                                    }
                                } else {
                                    String[] stringArray41 = this.f4868a.getResources().getStringArray(R.array.app_package_list);
                                    Intrinsics.checkNotNullExpressionValue(stringArray41, "context.resources.getStr…R.array.app_package_list)");
                                    this.d = stringArray41;
                                    String[] stringArray42 = this.f4868a.getResources().getStringArray(R.array.app_name_list);
                                    Intrinsics.checkNotNullExpressionValue(stringArray42, "context.resources.getStr…ay(R.array.app_name_list)");
                                    this.e = stringArray42;
                                }
                            } else {
                                String[] stringArray43 = this.f4868a.getResources().getStringArray(R.array.app_package_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray43, "context.resources.getStr…rray.app_package_list_ca)");
                                this.d = stringArray43;
                                String[] stringArray44 = this.f4868a.getResources().getStringArray(R.array.app_name_list_ca);
                                Intrinsics.checkNotNullExpressionValue(stringArray44, "context.resources.getStr…R.array.app_name_list_ca)");
                                this.e = stringArray44;
                            }
                        }
                    }
                    String[] stringArray45 = this.f4868a.getResources().getStringArray(R.array.app_package_list_matrix);
                    Intrinsics.checkNotNullExpressionValue(stringArray45, "context.resources.getStr….app_package_list_matrix)");
                    this.d = stringArray45;
                    String[] stringArray46 = this.f4868a.getResources().getStringArray(R.array.app_name_list_matrix);
                    Intrinsics.checkNotNullExpressionValue(stringArray46, "context.resources.getStr…ray.app_name_list_matrix)");
                    this.e = stringArray46;
                }
            } else {
                String[] stringArray47 = this.f4868a.getResources().getStringArray(R.array.app_package_list_1963);
                Intrinsics.checkNotNullExpressionValue(stringArray47, "context.resources.getStr…ay.app_package_list_1963)");
                this.d = stringArray47;
                String[] stringArray48 = this.f4868a.getResources().getStringArray(R.array.app_name_list_1963);
                Intrinsics.checkNotNullExpressionValue(stringArray48, "context.resources.getStr…array.app_name_list_1963)");
                this.e = stringArray48;
            }
        } else {
            String[] stringArray49 = this.f4868a.getResources().getStringArray(R.array.app_package_list_1790);
            Intrinsics.checkNotNullExpressionValue(stringArray49, "context.resources.getStr…ay.app_package_list_1790)");
            this.d = stringArray49;
            String[] stringArray50 = this.f4868a.getResources().getStringArray(R.array.app_name_list_1790);
            Intrinsics.checkNotNullExpressionValue(stringArray50, "context.resources.getStr…array.app_name_list_1790)");
            this.e = stringArray50;
        }
        setUserDataManager(muserDataManager);
        setAppData(mappData);
        setAppDataList(mappDataList);
        Object clone = mappNotificationData.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.AppNotificationData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covepreferences.data.AppNotificationData> }");
        setAppNotificationData((ArrayList) clone);
        setSmsSwitch(msmsSwicth);
        setCallSwitch(mcallSwitch);
        setAppSwitch(mappSwitch);
        setRecyclerView(mrecyclerView);
        setAdapterAppList(madapterList);
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
        if (getUserDataManager().getNotificationsData() != null) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
        } else {
            NotificationSettings notificationSettings = NotificationSettings.getInstance();
            notificationSettings.setSms_notifications(false);
            notificationSettings.setCall_notifications(false);
            notificationSettings.setApp_notifications(false);
            getRecyclerView().setVisibility(8);
            getUserDataManager().saveNotificationsSettings(notificationSettings);
        }
        if (AppUtils.isEmpty(getUserDataManager().getAppNotificationsData())) {
            getAppNotificationData().clear();
            setAppDataList(new ArrayList());
            setAppData(new ArrayList());
            int length = this.d.length;
            for (int i = 0; i < length; i++) {
                BleApiManager bleApiManager2 = BleApiManager.getInstance(this.f4868a);
                Boolean valueOf2 = (bleApiManager2 == null || (bleApi = bleApiManager2.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isSmartAlertsSupported());
                Intrinsics.checkNotNull(valueOf2);
                if (valueOf2.booleanValue()) {
                    List<SmartAlertAppServerConfData> list = this.B;
                    if (!(list == null || list.isEmpty())) {
                        AppNotificationData appNotificationData = new AppNotificationData();
                        appNotificationData.setPackageName(this.d[i]);
                        appNotificationData.setChecked(false);
                        appNotificationData.setAppName(this.e[i]);
                        SmartAlertUtils.Companion companion2 = SmartAlertUtils.Companion;
                        Context context = this.f4868a;
                        String str = this.d[i];
                        Intrinsics.checkNotNullExpressionValue(str, "app_list[i]");
                        if (companion2.isSmartAlertSupportedByPackageName(context, str, this.B)) {
                            getAppDataList().add(0, appNotificationData);
                            getAppData().add(0, appNotificationData);
                        } else {
                            getAppDataList().add(appNotificationData);
                            getAppData().add(appNotificationData);
                        }
                    }
                }
                AppNotificationData appNotificationData2 = new AppNotificationData();
                appNotificationData2.setPackageName(this.d[i]);
                appNotificationData2.setChecked(false);
                appNotificationData2.setAppName(this.e[i]);
                getAppDataList().add(appNotificationData2);
                getAppData().add(appNotificationData2);
            }
            getUserDataManager().saveAppNotificationsSettings(getAppDataList());
            PreferenceManager.saveAppNotificationData(this.f4868a, (ArrayList) getAppDataList());
            saveAppData();
            return;
        }
        String appListJSon = new Gson().toJson(this.d);
        Gson gson = new Gson();
        String[] appNotificationsPackageListFromPreference = getUserDataManager().getAppNotificationsPackageListFromPreference();
        Intrinsics.checkNotNull(appNotificationsPackageListFromPreference);
        String appNotificationDataListJson = gson.toJson(appNotificationsPackageListFromPreference);
        Intrinsics.checkNotNullExpressionValue(appListJSon, "appListJSon");
        String md5 = md5(appListJSon);
        Intrinsics.checkNotNullExpressionValue(appNotificationDataListJson, "appNotificationDataListJson");
        if (!m.equals$default(md5, md5(appNotificationDataListJson), false, 2, null)) {
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new c(null), 2, null);
            saveAppData();
            return;
        }
        List<AppNotificationData> appNotificationsData = UserDataManager.getInstance(this.f4868a).getAppNotificationsData();
        Intrinsics.checkNotNullExpressionValue(appNotificationsData, "getInstance(context).appNotificationsData");
        setAppDataList(appNotificationsData);
        List<AppNotificationData> appNotificationsData2 = UserDataManager.getInstance(this.f4868a).getAppNotificationsData();
        Intrinsics.checkNotNullExpressionValue(appNotificationsData2, "getInstance(context).appNotificationsData");
        setAppData(appNotificationsData2);
        PreferenceManager.saveAppNotificationData(this.f4868a, (ArrayList) getAppDataList());
        saveAppData();
    }

    public final boolean isAppInstalled(@NotNull String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.f4868a.getPackageManager().getLaunchIntentForPackage(packageName) != null;
    }

    public final boolean isCalendarEnabled() {
        return this.g;
    }

    public final boolean isCallEnabled() {
        return this.f;
    }

    public final boolean isEmailEnabled() {
        return this.i;
    }

    public final boolean isFacebookEnabled() {
        return this.l;
    }

    public final boolean isInstagramEnabled() {
        return this.m;
    }

    public final boolean isLineEnabled() {
        return this.u;
    }

    public final boolean isLinkedInEnabled() {
        return this.v;
    }

    public final boolean isMessengerEnabled() {
        return this.p;
    }

    public final boolean isOutlookEnabled() {
        return this.y;
    }

    public final boolean isQQEnabled() {
        return this.q;
    }

    public final boolean isQzoneEnabled() {
        return this.r;
    }

    public final boolean isSkypeEnabled() {
        return this.t;
    }

    public final boolean isSmsEnabled() {
        return this.h;
    }

    public final boolean isSnapchatEnabled() {
        return this.s;
    }

    public final boolean isTelegramEnabled() {
        return this.n;
    }

    public final boolean isTwitterEnabled() {
        return this.o;
    }

    public final boolean isVkClientEnabled() {
        return this.w;
    }

    public final boolean isWeChatEnabled() {
        return this.k;
    }

    public final boolean isWhatsAppBussinessEnabled() {
        return this.x;
    }

    public final boolean isWhatsAppEnabled() {
        return this.j;
    }

    public final boolean isYahooEmailEnabled() {
        return this.z;
    }

    public final boolean isYoutubeEnabled() {
        return this.A;
    }

    public final void j() {
        SetCallSmsSocialNotificationRequest callSmsSocialNotificationRequest = new SetCallSmsSocialNotificationRequest.Builder().setAppAlerts(getUserDataManager().getNotificationsData().isCall_notifications(), true, true, true, true, true, true, true, true, true, true, true, true, true, true).setTelegramEnabled(true).setLineEnabled(true).setOtherAppEnabled(true).setWhatsAppBusinessEnabled(true).setCustomEventEnabled(true).setGmailEnabled(true).setNewsEnabled(true).setKaKaoTalkEnabled(true).setOutLookEnabled(true).setYahooEmailEnabled(true).setYoutubeEnabled(true).setLinkedInEnabled(true).build();
        BleApi bleApi = BleApiManager.getInstance(this.f4868a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(callSmsSocialNotificationRequest, "callSmsSocialNotificationRequest");
        bleApi.setUserSettings(callSmsSocialNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.ActivityNotificationViewModel$sendNotificationConfigurationToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    @Nullable
    public final String md5(@NotNull String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            Intrinsics.checkNotNullExpressionValue(messageDigest, "getInstance(MD5)");
            byte[] bytes = s.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            Intrinsics.checkNotNullExpressionValue(digest, "digest.digest()");
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                while (hexString.length() < 2) {
                    hexString = '0' + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final void saveAppData() {
        boolean z;
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        getAppNotificationData().clear();
        List<AppNotificationData> appNotificationsData = getUserDataManager().getAppNotificationsData();
        Intrinsics.checkNotNullExpressionValue(appNotificationsData, "userDataManager.appNotificationsData");
        int size = appNotificationsData.size();
        int i = 0;
        while (true) {
            Boolean bool = null;
            if (i >= size) {
                e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
                return;
            }
            if (appNotificationsData.get(i) != null && appNotificationsData.get(i).getPackageName() != null) {
                String packageName = appNotificationsData.get(i).getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "arr.get(i).getPackageName()");
                if (isAppInstalled(packageName)) {
                    LogHelper.d(this.b, appNotificationsData.get(i).getPackageName() + appNotificationsData.get(i).getChecked() + AppConstants.EMPTY_STRING.getValue());
                    AppNotificationData appNotificationData = new AppNotificationData();
                    appNotificationData.setAppName(appNotificationsData.get(i).getAppName());
                    appNotificationData.setPackageName(appNotificationsData.get(i).getPackageName());
                    appNotificationData.setChecked(appNotificationsData.get(i).getChecked());
                    BleApiManager bleApiManager = BleApiManager.getInstance(this.f4868a);
                    if (bleApiManager != null && (bleApi = bleApiManager.getBleApi()) != null && (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) != null) {
                        bool = Boolean.valueOf(deviceSupportedFeatures.isSmartAlertsSupported());
                    }
                    Intrinsics.checkNotNull(bool);
                    if (bool.booleanValue()) {
                        List<SmartAlertAppServerConfData> list = this.B;
                        if (!(list == null || list.isEmpty())) {
                            Iterator<SmartAlertAppServerConfData> it = this.B.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = false;
                                    break;
                                }
                                SmartAlertAppServerConfData next = it.next();
                                if (next != null && Intrinsics.areEqual(appNotificationData.getPackageName(), next.getPackageName())) {
                                    z = true;
                                    break;
                                }
                            }
                            if (z) {
                                appNotificationData.setSmartAlertSupported(true);
                                getAppNotificationData().add(0, appNotificationData);
                            } else {
                                getAppNotificationData().add(appNotificationData);
                            }
                        }
                    }
                    getAppNotificationData().add(appNotificationData);
                }
            }
            i++;
        }
    }

    public final void saveAppNotification() {
        j();
        NotificationSettings notificationsData = UserDataManager.getInstance(this.f4868a).getNotificationsData();
        this.f = notificationsData.isCall_notifications();
        this.h = notificationsData.isSms_notifications();
        if (!notificationsData.isApp_notifications()) {
            this.i = false;
            this.j = false;
            this.k = false;
            this.l = false;
            this.m = false;
            this.n = false;
            this.o = false;
            this.p = false;
            this.q = false;
            this.r = false;
            this.s = false;
            this.t = false;
            this.u = false;
            this.g = false;
            this.v = false;
            this.w = false;
            this.y = false;
            this.j = false;
            this.z = false;
            this.A = false;
        } else {
            int size = getAppNotificationData().size();
            for (int i = 0; i < size; i++) {
                String packageName = getAppNotificationData().get(i).getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "appNotificationData[i].packageName");
                String lowerCase = packageName.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) BleNotification.WE_CHAT, false, 2, (Object) null)) {
                    this.k = getAppNotificationData().get(i).getChecked();
                } else {
                    String packageName2 = getAppNotificationData().get(i).getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName2, "appNotificationData[i].packageName");
                    String lowerCase2 = packageName2.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) BleNotification.FACEBOOK, false, 2, (Object) null)) {
                        this.l = getAppNotificationData().get(i).getChecked();
                    } else {
                        String packageName3 = getAppNotificationData().get(i).getPackageName();
                        Intrinsics.checkNotNullExpressionValue(packageName3, "appNotificationData[i].packageName");
                        String lowerCase3 = packageName3.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) BleNotification.WHATS_APP, false, 2, (Object) null)) {
                            this.j = getAppNotificationData().get(i).getChecked();
                        } else {
                            String packageName4 = getAppNotificationData().get(i).getPackageName();
                            Intrinsics.checkNotNullExpressionValue(packageName4, "appNotificationData[i].packageName");
                            String lowerCase4 = packageName4.toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase4, (CharSequence) BleNotification.TWITTER, false, 2, (Object) null)) {
                                this.o = getAppNotificationData().get(i).getChecked();
                            } else {
                                String packageName5 = getAppNotificationData().get(i).getPackageName();
                                Intrinsics.checkNotNullExpressionValue(packageName5, "appNotificationData[i].packageName");
                                String lowerCase5 = packageName5.toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase5, (CharSequence) BleNotification.QQ, false, 2, (Object) null)) {
                                    this.q = getAppNotificationData().get(i).getChecked();
                                } else {
                                    String packageName6 = getAppNotificationData().get(i).getPackageName();
                                    Intrinsics.checkNotNullExpressionValue(packageName6, "appNotificationData[i].packageName");
                                    String lowerCase6 = packageName6.toLowerCase();
                                    Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase6, (CharSequence) "com.o2m.gsk", false, 2, (Object) null)) {
                                        this.r = getAppNotificationData().get(i).getChecked();
                                    } else {
                                        String packageName7 = getAppNotificationData().get(i).getPackageName();
                                        Intrinsics.checkNotNullExpressionValue(packageName7, "appNotificationData[i].packageName");
                                        String lowerCase7 = packageName7.toLowerCase();
                                        Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase7, (CharSequence) BleNotification.SNAPCHAT, false, 2, (Object) null)) {
                                            this.s = getAppNotificationData().get(i).getChecked();
                                        } else {
                                            String packageName8 = getAppNotificationData().get(i).getPackageName();
                                            Intrinsics.checkNotNullExpressionValue(packageName8, "appNotificationData[i].packageName");
                                            String lowerCase8 = packageName8.toLowerCase();
                                            Intrinsics.checkNotNullExpressionValue(lowerCase8, "this as java.lang.String).toLowerCase()");
                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase8, (CharSequence) BleNotification.SKYPE, false, 2, (Object) null)) {
                                                this.t = getAppNotificationData().get(i).getChecked();
                                            } else {
                                                String packageName9 = getAppNotificationData().get(i).getPackageName();
                                                Intrinsics.checkNotNullExpressionValue(packageName9, "appNotificationData[i].packageName");
                                                String lowerCase9 = packageName9.toLowerCase();
                                                Intrinsics.checkNotNullExpressionValue(lowerCase9, "this as java.lang.String).toLowerCase()");
                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase9, (CharSequence) BleNotification.FACEBOOK_MESSENGER, false, 2, (Object) null)) {
                                                    this.p = getAppNotificationData().get(i).getChecked();
                                                } else {
                                                    String packageName10 = getAppNotificationData().get(i).getPackageName();
                                                    Intrinsics.checkNotNullExpressionValue(packageName10, "appNotificationData[i].packageName");
                                                    String lowerCase10 = packageName10.toLowerCase();
                                                    Intrinsics.checkNotNullExpressionValue(lowerCase10, "this as java.lang.String).toLowerCase()");
                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase10, (CharSequence) BleNotification.GMAIL, false, 2, (Object) null)) {
                                                        this.i = getAppNotificationData().get(i).getChecked();
                                                    } else {
                                                        String packageName11 = getAppNotificationData().get(i).getPackageName();
                                                        Intrinsics.checkNotNullExpressionValue(packageName11, "appNotificationData[i].packageName");
                                                        String lowerCase11 = packageName11.toLowerCase();
                                                        Intrinsics.checkNotNullExpressionValue(lowerCase11, "this as java.lang.String).toLowerCase()");
                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase11, (CharSequence) BleNotification.INSTAGRAM, false, 2, (Object) null)) {
                                                            this.m = getAppNotificationData().get(i).getChecked();
                                                        } else {
                                                            String packageName12 = getAppNotificationData().get(i).getPackageName();
                                                            Intrinsics.checkNotNullExpressionValue(packageName12, "appNotificationData[i].packageName");
                                                            String lowerCase12 = packageName12.toLowerCase();
                                                            Intrinsics.checkNotNullExpressionValue(lowerCase12, "this as java.lang.String).toLowerCase()");
                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase12, (CharSequence) BleNotification.TELEGRAM, false, 2, (Object) null)) {
                                                                this.n = getAppNotificationData().get(i).getChecked();
                                                            } else {
                                                                String packageName13 = getAppNotificationData().get(i).getPackageName();
                                                                Intrinsics.checkNotNullExpressionValue(packageName13, "appNotificationData[i].packageName");
                                                                String lowerCase13 = packageName13.toLowerCase();
                                                                Intrinsics.checkNotNullExpressionValue(lowerCase13, "this as java.lang.String).toLowerCase()");
                                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase13, (CharSequence) BleNotification.LINKED_IN, false, 2, (Object) null)) {
                                                                    this.v = getAppNotificationData().get(i).getChecked();
                                                                } else {
                                                                    String packageName14 = getAppNotificationData().get(i).getPackageName();
                                                                    Intrinsics.checkNotNullExpressionValue(packageName14, "appNotificationData[i].packageName");
                                                                    String lowerCase14 = packageName14.toLowerCase();
                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase14, "this as java.lang.String).toLowerCase()");
                                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase14, (CharSequence) "com.vkontakte.android", false, 2, (Object) null)) {
                                                                        this.w = getAppNotificationData().get(i).getChecked();
                                                                    } else {
                                                                        String packageName15 = getAppNotificationData().get(i).getPackageName();
                                                                        Intrinsics.checkNotNullExpressionValue(packageName15, "appNotificationData[i].packageName");
                                                                        String lowerCase15 = packageName15.toLowerCase();
                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase15, "this as java.lang.String).toLowerCase()");
                                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase15, (CharSequence) BleNotification.LINE, false, 2, (Object) null)) {
                                                                            this.u = getAppNotificationData().get(i).getChecked();
                                                                        } else {
                                                                            String packageName16 = getAppNotificationData().get(i).getPackageName();
                                                                            Intrinsics.checkNotNullExpressionValue(packageName16, "appNotificationData[i].packageName");
                                                                            String lowerCase16 = packageName16.toLowerCase();
                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase16, "this as java.lang.String).toLowerCase()");
                                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase16, (CharSequence) "com.google.android.calendar", false, 2, (Object) null)) {
                                                                                this.g = getAppNotificationData().get(i).getChecked();
                                                                            } else {
                                                                                String packageName17 = getAppNotificationData().get(i).getPackageName();
                                                                                Intrinsics.checkNotNullExpressionValue(packageName17, "appNotificationData[i].packageName");
                                                                                String lowerCase17 = packageName17.toLowerCase();
                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase17, "this as java.lang.String).toLowerCase()");
                                                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase17, (CharSequence) "com.whatsapp.w4b", false, 2, (Object) null)) {
                                                                                    this.x = getAppNotificationData().get(i).getChecked();
                                                                                } else {
                                                                                    String packageName18 = getAppNotificationData().get(i).getPackageName();
                                                                                    Intrinsics.checkNotNullExpressionValue(packageName18, "appNotificationData[i].packageName");
                                                                                    String lowerCase18 = packageName18.toLowerCase();
                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase18, "this as java.lang.String).toLowerCase()");
                                                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase18, (CharSequence) BleNotification.YOUTUBE, false, 2, (Object) null)) {
                                                                                        this.A = getAppNotificationData().get(i).getChecked();
                                                                                    } else {
                                                                                        String packageName19 = getAppNotificationData().get(i).getPackageName();
                                                                                        Intrinsics.checkNotNullExpressionValue(packageName19, "appNotificationData[i].packageName");
                                                                                        String lowerCase19 = packageName19.toLowerCase();
                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase19, "this as java.lang.String).toLowerCase()");
                                                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase19, (CharSequence) BleNotification.OUT_LOOK, false, 2, (Object) null)) {
                                                                                            this.y = getAppNotificationData().get(i).getChecked();
                                                                                        } else {
                                                                                            String packageName20 = getAppNotificationData().get(i).getPackageName();
                                                                                            Intrinsics.checkNotNullExpressionValue(packageName20, "appNotificationData[i].packageName");
                                                                                            String lowerCase20 = packageName20.toLowerCase();
                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase20, "this as java.lang.String).toLowerCase()");
                                                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase20, (CharSequence) BleNotification.YAHOO_MAIL, false, 2, (Object) null)) {
                                                                                                this.z = getAppNotificationData().get(i).getChecked();
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        h();
    }

    public final void setAdapterAppList(@NotNull AdapterAppList adapterAppList) {
        Intrinsics.checkNotNullParameter(adapterAppList, "<set-?>");
        this.adapterAppList = adapterAppList;
    }

    public final void setAppData(@NotNull List<AppNotificationData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.appData = list;
    }

    public final void setAppDataList(@NotNull List<AppNotificationData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.appDataList = list;
    }

    public final void setAppNotificationData(@NotNull List<AppNotificationData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.appNotificationData = list;
    }

    public final void setAppSwitch(@NotNull SwitchCompat switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "<set-?>");
        this.appSwitch = switchCompat;
    }

    public final void setApp_list(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.d = strArr;
    }

    public final void setApp_name_list(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.e = strArr;
    }

    public final void setCalendarEnabled(boolean z) {
        this.g = z;
    }

    public final void setCallEnabled(boolean z) {
        this.f = z;
    }

    public final void setCallSwitch(@NotNull SwitchCompat switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "<set-?>");
        this.callSwitch = switchCompat;
    }

    public final void setEmailEnabled(boolean z) {
        this.i = z;
    }

    public final void setFacebookEnabled(boolean z) {
        this.l = z;
    }

    public final void setInstagramEnabled(boolean z) {
        this.m = z;
    }

    public final void setLineEnabled(boolean z) {
        this.u = z;
    }

    public final void setLinkedInEnabled(boolean z) {
        this.v = z;
    }

    public final void setMListener(@NotNull PermissionListener permissionListener) {
        Intrinsics.checkNotNullParameter(permissionListener, "<set-?>");
        this.mListener = permissionListener;
    }

    public final void setMessengerEnabled(boolean z) {
        this.p = z;
    }

    public final void setOutlookEnabled(boolean z) {
        this.y = z;
    }

    public final void setQQEnabled(boolean z) {
        this.q = z;
    }

    public final void setQzoneEnabled(boolean z) {
        this.r = z;
    }

    public final void setRecyclerView(@NotNull RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.recyclerView = recyclerView;
    }

    public final void setShowProgressLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setSkypeEnabled(boolean z) {
        this.t = z;
    }

    public final void setSmsEnabled(boolean z) {
        this.h = z;
    }

    public final void setSmsSwitch(@NotNull SwitchCompat switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "<set-?>");
        this.smsSwitch = switchCompat;
    }

    public final void setSnapchatEnabled(boolean z) {
        this.s = z;
    }

    public final void setTelegramEnabled(boolean z) {
        this.n = z;
    }

    public final void setTwitterEnabled(boolean z) {
        this.o = z;
    }

    public final void setUserDataManager(@NotNull UserDataManager userDataManager) {
        Intrinsics.checkNotNullParameter(userDataManager, "<set-?>");
        this.userDataManager = userDataManager;
    }

    public final void setViewModelListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.viewModelListener = viewModelListener;
    }

    public final void setVkClientEnabled(boolean z) {
        this.w = z;
    }

    public final void setWeChatEnabled(boolean z) {
        this.k = z;
    }

    public final void setWhatsAppBussinessEnabled(boolean z) {
        this.x = z;
    }

    public final void setWhatsAppEnabled(boolean z) {
        this.j = z;
    }

    public final void setYahooEmailEnabled(boolean z) {
        this.z = z;
    }

    public final void setYoutubeEnabled(boolean z) {
        this.A = z;
    }
}
