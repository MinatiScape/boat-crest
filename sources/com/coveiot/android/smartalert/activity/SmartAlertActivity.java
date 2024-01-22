package com.coveiot.android.smartalert.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.bleabstract.response.GetSmartAlertConfigResponse;
import com.coveiot.android.smartalert.R;
import com.coveiot.android.smartalert.SmartAlertPreferenceManager;
import com.coveiot.android.smartalert.adapter.AdapterAppList;
import com.coveiot.android.smartalert.util.AppNotificationInterface;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.android.smartalert.viewmodel.SmartAlertViewModel;
import com.coveiot.android.smartalert.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.userdevicesetting.SaveSmartAlertSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.model.SmartAlertSettings;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.covepreferences.data.SmartAlertAppServerConfData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmartAlertActivity extends BaseActivity implements AdapterAppList.SmartAlertClickListener, AppNotificationInterface {
    public SmartAlertViewModel q;
    public AdapterAppList r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = SmartAlertActivity.class.getSimpleName();
    @NotNull
    public ArrayList<AppNotificationData> s = new ArrayList<>();
    @NotNull
    public List<AppNotificationData> t = new ArrayList();
    @NotNull
    public final String u = "enabled_notification_listeners";
    @NotNull
    public final String v = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    public final int w = 101;

    @DebugMetadata(c = "com.coveiot.android.smartalert.activity.SmartAlertActivity$onCreate$1$1", f = "SmartAlertActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Boolean $it;
        public int label;
        public final /* synthetic */ SmartAlertActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Boolean bool, SmartAlertActivity smartAlertActivity, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$it = bool;
            this.this$0 = smartAlertActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            SmartAlertViewModel smartAlertViewModel;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Boolean it = this.$it;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                if (it.booleanValue()) {
                    SaveSmartAlertSettingsReq saveSmartAlertSettingsReq = new SaveSmartAlertSettingsReq();
                    saveSmartAlertSettingsReq.setSmartAlertSettings(new SmartAlertSettings());
                    saveSmartAlertSettingsReq.getSmartAlertSettings().setApps(new ArrayList());
                    Iterator<AppNotificationData> it2 = this.this$0.getAppNotificationDataFromPreference().iterator();
                    while (true) {
                        smartAlertViewModel = null;
                        if (!it2.hasNext()) {
                            break;
                        }
                        AppNotificationData next = it2.next();
                        if (next != null) {
                            String packageName = next.getPackageName();
                            boolean z = true;
                            if (((packageName == null || packageName.length() == 0) ? 1 : null) == null) {
                                SmartAlertUtils.Companion companion = SmartAlertUtils.Companion;
                                SmartAlertActivity smartAlertActivity = this.this$0;
                                String packageName2 = next.getPackageName();
                                Intrinsics.checkNotNullExpressionValue(packageName2, "appData.packageName");
                                SmartAlertAppServerConfData smartAlertServerAppConfigByPackageName = companion.getSmartAlertServerAppConfigByPackageName(smartAlertActivity, packageName2);
                                if (smartAlertServerAppConfigByPackageName != null) {
                                    SmartAlertAppServerConfData.DeviceData deviceData = smartAlertServerAppConfigByPackageName.getDeviceData();
                                    if ((deviceData != null ? deviceData.getAppId() : null) != null) {
                                        String name = smartAlertServerAppConfigByPackageName.getName();
                                        if (((name == null || name.length() == 0) ? 1 : null) == null) {
                                            SmartAlertAppServerConfData.DeviceData deviceData2 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                            if ((deviceData2 != null ? deviceData2.getFontSize() : null) != null) {
                                                SmartAlertAppServerConfData.DeviceData deviceData3 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                                String fontColor = deviceData3 != null ? deviceData3.getFontColor() : null;
                                                if (fontColor != null && fontColor.length() != 0) {
                                                    z = false;
                                                }
                                                if (!z) {
                                                    SmartAlertSettings.App app = new SmartAlertSettings.App();
                                                    app.setName(next.getAppName());
                                                    app.setPackageName(next.getPackageName());
                                                    app.setActive(Boxing.boxBoolean(next.getChecked()));
                                                    saveSmartAlertSettingsReq.getSmartAlertSettings().getApps().add(app);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    SmartAlertViewModel smartAlertViewModel2 = this.this$0.q;
                    if (smartAlertViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        smartAlertViewModel = smartAlertViewModel2;
                    }
                    smartAlertViewModel.saveSmartAlertConfigToServer(saveSmartAlertSettingsReq);
                } else {
                    this.this$0.dismissProgress();
                    SmartAlertActivity smartAlertActivity2 = this.this$0;
                    Toast.makeText(smartAlertActivity2, smartAlertActivity2.getString(R.string.setting_could_not_be_saved), 0).show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes6.dex */
    public static final class b extends Lambda implements Function1<Boolean, Unit> {

        @DebugMetadata(c = "com.coveiot.android.smartalert.activity.SmartAlertActivity$onCreate$3$1", f = "SmartAlertActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Boolean $it;
            public int label;
            public final /* synthetic */ SmartAlertActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SmartAlertActivity smartAlertActivity, Boolean bool, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = smartAlertActivity;
                this.$it = bool;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$it, continuation);
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
                    this.this$0.dismissProgress();
                    Boolean it = this.$it;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    if (it.booleanValue()) {
                        ((Button) this.this$0._$_findCachedViewById(R.id.btnSave)).setEnabled(false);
                        SmartAlertActivity smartAlertActivity = this.this$0;
                        Toast.makeText(smartAlertActivity, smartAlertActivity.getString(R.string.setting_saved_successfully), 0).show();
                    } else {
                        SmartAlertActivity smartAlertActivity2 = this.this$0;
                        Toast.makeText(smartAlertActivity2, smartAlertActivity2.getString(R.string.setting_could_not_be_saved), 0).show();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
            if (SmartAlertActivity.this.isFinishing()) {
                return;
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(SmartAlertActivity.this), Dispatchers.getMain(), null, new a(SmartAlertActivity.this, bool, null), 2, null);
        }
    }

    public static final void A(SmartAlertActivity this$0, GetSmartAlertConfigResponse getSmartAlertConfigResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.p;
        LogHelper.d(str, "smart alert config -> " + getSmartAlertConfigResponse);
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void C(SmartAlertActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = Settings.Secure.getString(this$0.getContentResolver(), this$0.u);
        if (string != null) {
            String packageName = this$0.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                this$0.w();
                return;
            }
        }
        Toast.makeText(this$0, this$0.getString(R.string.notification_access_permission_required_), 1).show();
        this$0.startActivityForResult(new Intent(this$0.v), this$0.w);
    }

    public static final void x(SmartAlertActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(SmartAlertActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing()) {
            return;
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new a(bool, this$0, null), 2, null);
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @NotNull
    public final ArrayList<AppNotificationData> getAppNotificationData() {
        return this.s;
    }

    @NotNull
    public final List<AppNotificationData> getAppNotificationDataFromPreference() {
        return this.t;
    }

    @NotNull
    public final String getENABLE_NOTIFICATION_LISTENERS() {
        return this.u;
    }

    @NotNull
    public final String getNOTIFICATION_SETTING() {
        return this.v;
    }

    public final String getTAG() {
        return this.p;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.smart_alert));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.smartalert.activity.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartAlertActivity.x(SmartAlertActivity.this, view);
            }
        });
    }

    @Override // com.coveiot.android.smartalert.adapter.AdapterAppList.SmartAlertClickListener
    public void knowMoreClick() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        String string;
        super.onActivityResult(i, i2, intent);
        if (i != this.w || (string = Settings.Secure.getString(getContentResolver(), this.u)) == null) {
            return;
        }
        String packageName = getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
            NotificationSettings notificationSettings = NotificationSettings.getInstance();
            notificationSettings.setApp_notifications(true);
            UserDataManager.getInstance(this).saveNotificationsSettings(notificationSettings);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smart_alert);
        initToolbar();
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(SmartAlertViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        SmartAlertViewModel smartAlertViewModel = (SmartAlertViewModel) viewModel;
        this.q = smartAlertViewModel;
        if (smartAlertViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            smartAlertViewModel = null;
        }
        smartAlertViewModel.getSetSmartAlertConfigLiveData().observe(this, new Observer() { // from class: com.coveiot.android.smartalert.activity.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SmartAlertActivity.z(SmartAlertActivity.this, (Boolean) obj);
            }
        });
        SmartAlertViewModel smartAlertViewModel2 = this.q;
        if (smartAlertViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            smartAlertViewModel2 = null;
        }
        smartAlertViewModel2.getGetSmartAlertConfigLiveData().observe(this, new Observer() { // from class: com.coveiot.android.smartalert.activity.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SmartAlertActivity.A(SmartAlertActivity.this, (GetSmartAlertConfigResponse) obj);
            }
        });
        SmartAlertViewModel smartAlertViewModel3 = this.q;
        if (smartAlertViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            smartAlertViewModel3 = null;
        }
        MutableLiveData<Boolean> uploadSmartAlertConfigToServerLiveData = smartAlertViewModel3.getUploadSmartAlertConfigToServerLiveData();
        final b bVar = new b();
        uploadSmartAlertConfigToServerLiveData.observe(this, new Observer() { // from class: com.coveiot.android.smartalert.activity.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SmartAlertActivity.B(Function1.this, obj);
            }
        });
        SmartAlertPreferenceManager.Companion companion = SmartAlertPreferenceManager.Companion;
        List<AppNotificationData> smartAlertAppNotificationsSettings = companion.getInstance(this).getSmartAlertAppNotificationsSettings();
        if (smartAlertAppNotificationsSettings == null) {
            smartAlertAppNotificationsSettings = new ArrayList<>();
        }
        this.t = smartAlertAppNotificationsSettings;
        List<SmartAlertAppServerConfData> smartAlertAppServerConfigData = companion.getInstance(this).getSmartAlertAppServerConfigData();
        if (!(smartAlertAppServerConfigData == null || smartAlertAppServerConfigData.isEmpty())) {
            for (SmartAlertAppServerConfData smartAlertAppServerConfData : smartAlertAppServerConfigData) {
                if (smartAlertAppServerConfData != null) {
                    String packageName = smartAlertAppServerConfData.getPackageName();
                    if (!(packageName == null || packageName.length() == 0) && smartAlertAppServerConfData.getDeviceData() != null) {
                        String name = smartAlertAppServerConfData.getName();
                        if (!(name == null || name.length() == 0)) {
                            ArrayList<AppNotificationData> arrayList = this.s;
                            String name2 = smartAlertAppServerConfData.getName();
                            Intrinsics.checkNotNull(name2);
                            Locale ROOT = Locale.ROOT;
                            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                            String lowerCase = name2.toLowerCase(ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                            if (lowerCase.length() > 0) {
                                StringBuilder sb = new StringBuilder();
                                sb.append((Object) kotlin.text.b.titlecase(lowerCase.charAt(0)));
                                String substring = lowerCase.substring(1);
                                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                                sb.append(substring);
                                lowerCase = sb.toString();
                            }
                            String packageName2 = smartAlertAppServerConfData.getPackageName();
                            String packageName3 = smartAlertAppServerConfData.getPackageName();
                            Intrinsics.checkNotNull(packageName3);
                            arrayList.add(new AppNotificationData(lowerCase, packageName2, y(packageName3), true));
                        }
                    }
                }
            }
        }
        this.r = new AdapterAppList(this, this.s, this, this);
        int i = R.id.rvApplications;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        AdapterAppList adapterAppList = this.r;
        if (adapterAppList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterAppList");
            adapterAppList = null;
        }
        recyclerView.setAdapter(adapterAppList);
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this, 1, false));
        ((Button) _$_findCachedViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.smartalert.activity.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartAlertActivity.C(SmartAlertActivity.this, view);
            }
        });
        String string = Settings.Secure.getString(getContentResolver(), this.u);
        if (string != null) {
            String packageName4 = getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName4, "packageName");
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName4, false, 2, (Object) null)) {
                return;
            }
        }
        Toast.makeText(this, getString(R.string.notification_access_permission_required_), 1).show();
        startActivityForResult(new Intent(this.v), this.w);
    }

    @Override // com.coveiot.android.smartalert.util.AppNotificationInterface
    public void saveAppData(@NotNull List<? extends AppNotificationData> appNotificationData, @NotNull String str, boolean z) {
        AppNotificationData v;
        Intrinsics.checkNotNullParameter(appNotificationData, "appNotificationData");
        Intrinsics.checkNotNullParameter(str, "str");
        ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        if (z) {
            if (y(str) || (v = v(appNotificationData, str)) == null) {
                return;
            }
            v.setChecked(true);
            this.t.add(v);
        } else if (y(str)) {
            int i = 0;
            int i2 = -1;
            for (Object obj : this.t) {
                int i3 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                AppNotificationData appNotificationData2 = (AppNotificationData) obj;
                if (appNotificationData2 != null && Intrinsics.areEqual(appNotificationData2.getPackageName(), str)) {
                    i2 = i;
                }
                i = i3;
            }
            if (i2 != -1) {
                this.t.remove(i2);
            }
        }
    }

    public final void setAppNotificationData(@NotNull ArrayList<AppNotificationData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    public final void setAppNotificationDataFromPreference(@NotNull List<AppNotificationData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.t = list;
    }

    public final AppNotificationData v(List<? extends AppNotificationData> list, String str) {
        for (AppNotificationData appNotificationData : list) {
            if (Intrinsics.areEqual(appNotificationData.getPackageName(), str)) {
                return appNotificationData;
            }
        }
        return null;
    }

    public final void w() {
        SmartAlertViewModel smartAlertViewModel;
        if (AppUtils.isNetConnected(this)) {
            if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                showProgress();
                ArrayList arrayList = new ArrayList();
                Iterator<AppNotificationData> it = this.t.iterator();
                while (true) {
                    smartAlertViewModel = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    AppNotificationData next = it.next();
                    if (next != null) {
                        String packageName = next.getPackageName();
                        boolean z = true;
                        if (((packageName == null || packageName.length() == 0) ? 1 : null) == null) {
                            SmartAlertUtils.Companion companion = SmartAlertUtils.Companion;
                            String packageName2 = next.getPackageName();
                            Intrinsics.checkNotNullExpressionValue(packageName2, "appData.packageName");
                            SmartAlertAppServerConfData smartAlertServerAppConfigByPackageName = companion.getSmartAlertServerAppConfigByPackageName(this, packageName2);
                            if (smartAlertServerAppConfigByPackageName != null) {
                                SmartAlertAppServerConfData.DeviceData deviceData = smartAlertServerAppConfigByPackageName.getDeviceData();
                                if ((deviceData != null ? deviceData.getAppId() : null) != null) {
                                    String name = smartAlertServerAppConfigByPackageName.getName();
                                    if (((name == null || name.length() == 0) ? 1 : null) == null) {
                                        SmartAlertAppServerConfData.DeviceData deviceData2 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                        if ((deviceData2 != null ? deviceData2.getFontSize() : null) != null) {
                                            SmartAlertAppServerConfData.DeviceData deviceData3 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                            String fontColor = deviceData3 != null ? deviceData3.getFontColor() : null;
                                            if (fontColor != null && fontColor.length() != 0) {
                                                z = false;
                                            }
                                            if (!z) {
                                                SmartAlertAppServerConfData.DeviceData deviceData4 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                                Intrinsics.checkNotNull(deviceData4);
                                                Integer appId = deviceData4.getAppId();
                                                Intrinsics.checkNotNull(appId);
                                                int intValue = appId.intValue();
                                                String name2 = smartAlertServerAppConfigByPackageName.getName();
                                                Intrinsics.checkNotNull(name2);
                                                SmartAlertAppServerConfData.DeviceData deviceData5 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                                Intrinsics.checkNotNull(deviceData5);
                                                Integer fontSize = deviceData5.getFontSize();
                                                Intrinsics.checkNotNull(fontSize);
                                                int intValue2 = fontSize.intValue();
                                                SmartAlertAppServerConfData.DeviceData deviceData6 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                                Intrinsics.checkNotNull(deviceData6);
                                                String fontColor2 = deviceData6.getFontColor();
                                                Intrinsics.checkNotNull(fontColor2);
                                                arrayList.add(new SmartAlertAppData(intValue, name2, intValue2, companion.convertHexTo565(fontColor2)));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                SmartAlertPreferenceManager.Companion.getInstance(this).saveSmartAlertAppNotificationsSettings(this.t);
                SmartAlertViewModel smartAlertViewModel2 = this.q;
                if (smartAlertViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    smartAlertViewModel = smartAlertViewModel2;
                }
                smartAlertViewModel.setSmartAlertConfig(arrayList);
                return;
            }
            Toast.makeText(this, getString(R.string.band_not_connected), 0).show();
            return;
        }
        Toast.makeText(this, getString(R.string.please_check_your_internet), 0).show();
    }

    public final boolean y(String str) {
        for (AppNotificationData appNotificationData : this.t) {
            if (appNotificationData != null && Intrinsics.areEqual(appNotificationData.getPackageName(), str)) {
                return true;
            }
        }
        return false;
    }
}
