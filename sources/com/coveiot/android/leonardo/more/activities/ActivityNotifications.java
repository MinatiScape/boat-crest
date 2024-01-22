package com.coveiot.android.leonardo.more.activities;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.BatterySaverModeHelper;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.more.ActivityNotificationViewModel;
import com.coveiot.android.leonardo.more.AdapterAppList;
import com.coveiot.android.leonardo.more.AppNotificationInterface;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.smartalert.activity.SmartAlertActivity;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNotifications extends BaseActivity implements AppNotificationInterface, PermissionListener, BatterySaverModeDialogClickCallback, AdapterAppList.SmartAlertClickListener, ViewModelListener {
    public boolean A;
    public boolean B;
    @Nullable
    public List<AppNotificationData> C;
    @Nullable
    public BottomSheetDialogTwoButtons D;
    public AdapterAppList adapterAppList;
    public TextView backArrow;
    public Context mContext;
    public boolean q;
    public boolean r;
    public TextView saveText;
    public TextView title;
    public UserDataManager userDataManager;
    public ActivityNotificationViewModel viewModel;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 909;
    public final int s = TypedValues.TransitionType.TYPE_FROM;
    public final int t = TypedValues.TransitionType.TYPE_INTERPOLATOR;
    public final int u = TypedValues.TransitionType.TYPE_TO;
    public final int v = 703;
    public final int w = TypedValues.TransitionType.TYPE_AUTO_TRANSITION;
    @NotNull
    public ArrayList<AppNotificationData> x = new ArrayList<>();
    @NotNull
    public List<AppNotificationData> y = new ArrayList();
    @NotNull
    public List<AppNotificationData> z = new ArrayList();

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PermissionType.values().length];
            try {
                iArr[PermissionType.PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PermissionType.CALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PermissionType.CONTACTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<Intent, Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<View, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (ActivityNotifications.this.getBoolSaveVisible()) {
                if (!AppUtils.isNetConnected(ActivityNotifications.this)) {
                    ActivityNotifications.this.showNoInternetMessage();
                    return;
                }
                ActivityNotifications.this.setBoolSaveVisible(false);
                NotificationSettings notificationSettings = NotificationSettings.getInstance();
                notificationSettings.setSms_notifications(((SwitchCompat) ActivityNotifications.this._$_findCachedViewById(R.id.sms_notification_value)).isChecked());
                notificationSettings.setCall_notifications(((SwitchCompat) ActivityNotifications.this._$_findCachedViewById(R.id.call_notification_value)).isChecked());
                notificationSettings.setApp_notifications(((SwitchCompat) ActivityNotifications.this._$_findCachedViewById(R.id.app_notification_value)).isChecked());
                ActivityNotifications.this.getUserDataManager().saveNotificationsSettings(notificationSettings);
                ActivityNotifications.this.getViewModel().saveAppNotification();
                ActivityNotifications activityNotifications = ActivityNotifications.this;
                List<AppNotificationData> appNotificationsData = activityNotifications.getUserDataManager().getAppNotificationsData();
                Intrinsics.checkNotNull(appNotificationsData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.AppNotificationData>");
                PreferenceManager.saveAppNotificationData(activityNotifications, (ArrayList) appNotificationsData);
                Application application = ActivityNotifications.this.getApplication();
                Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
                ((GenericBandApplication) application).registerPhoneStateChangeListener(true);
            }
        }
    }

    public static final void N(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public static final void P(ActivityNotifications this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = Settings.Secure.getString(this$0.getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
        if (string != null) {
            String packageName = this$0.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                if (z) {
                    ((RecyclerView) this$0._$_findCachedViewById(R.id.notification_setting_list)).setVisibility(0);
                } else {
                    ((RecyclerView) this$0._$_findCachedViewById(R.id.notification_setting_list)).setVisibility(8);
                }
                this$0.buttonVisible();
            }
        }
        if (!z) {
            ((RecyclerView) this$0._$_findCachedViewById(R.id.notification_setting_list)).setVisibility(8);
        } else {
            this$0.startActivityForResult(new Intent(AppConstants.NOTIFICATION_SETTING.getValue()), this$0.v);
            ((SwitchCompat) this$0._$_findCachedViewById(R.id.app_notification_value)).setChecked(false);
        }
        this$0.buttonVisible();
    }

    public static final void Q(ActivityNotifications this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q = z;
        this$0.checkSmsState();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007c, code lost:
        if (kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r5, (java.lang.CharSequence) r0, false, 2, (java.lang.Object) null) == false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void R(com.coveiot.android.leonardo.more.activities.ActivityNotifications r4, android.widget.CompoundButton r5, boolean r6) {
        /*
            java.lang.String r5 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)
            r4.r = r6
            if (r6 == 0) goto La1
            java.lang.String r5 = "android.permission.READ_CALL_LOG"
            java.lang.String r6 = "android.permission.READ_CONTACTS"
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            java.lang.String r1 = "android.permission.CALL_PHONE"
            java.lang.String[] r1 = new java.lang.String[]{r5, r6, r0, r1}
            java.lang.String[] r1 = com.coveiot.utils.permissions.PermissionUtils.checkPermissionsHasGranted(r4, r1)
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 28
            if (r2 < r3) goto L29
            java.lang.String r1 = "android.permission.ANSWER_PHONE_CALLS"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r0, r1}
            java.lang.String[] r1 = com.coveiot.utils.permissions.PermissionUtils.checkPermissionsHasGranted(r4, r5)
        L29:
            int r5 = r1.length
            r6 = 0
            if (r5 <= 0) goto L5d
            int r5 = com.coveiot.android.boat.R.id.call_notification_value
            android.view.View r5 = r4._$_findCachedViewById(r5)
            androidx.appcompat.widget.SwitchCompat r5 = (androidx.appcompat.widget.SwitchCompat) r5
            r5.setChecked(r6)
            java.lang.String r5 = "permissionNotGiven"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            int r5 = r1.length
            r0 = r6
        L3f:
            if (r0 >= r5) goto L4e
            r2 = r1[r0]
            boolean r2 = androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale(r4, r2)
            if (r2 == 0) goto L4b
            r6 = 1
            goto L4e
        L4b:
            int r0 = r0 + 1
            goto L3f
        L4e:
            if (r6 == 0) goto L54
            r4.k0()
            return
        L54:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r5 = r4.p
            androidx.core.app.ActivityCompat.requestPermissions(r4, r1, r5)
            goto L9d
        L5d:
            android.content.ContentResolver r5 = r4.getContentResolver()
            com.coveiot.android.leonardo.utils.AppConstants r0 = com.coveiot.android.leonardo.utils.AppConstants.ENABLE_NOTIFICATION_LISTENERS
            java.lang.String r0 = r0.getValue()
            java.lang.String r5 = android.provider.Settings.Secure.getString(r5, r0)
            if (r5 == 0) goto L7e
            java.lang.String r0 = r4.getPackageName()
            java.lang.String r1 = "packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r1 = 2
            r2 = 0
            boolean r5 = kotlin.text.StringsKt__StringsKt.contains$default(r5, r0, r6, r1, r2)
            if (r5 != 0) goto L9d
        L7e:
            boolean r5 = r4.r
            if (r5 == 0) goto L9d
            android.content.Intent r5 = new android.content.Intent
            com.coveiot.android.leonardo.utils.AppConstants r0 = com.coveiot.android.leonardo.utils.AppConstants.NOTIFICATION_SETTING
            java.lang.String r0 = r0.getValue()
            r5.<init>(r0)
            int r0 = r4.u
            r4.startActivityForResult(r5, r0)
            int r5 = com.coveiot.android.boat.R.id.call_notification_value
            android.view.View r5 = r4._$_findCachedViewById(r5)
            androidx.appcompat.widget.SwitchCompat r5 = (androidx.appcompat.widget.SwitchCompat) r5
            r5.setChecked(r6)
        L9d:
            r4.buttonVisible()
            goto La4
        La1:
            r4.buttonVisible()
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityNotifications.R(com.coveiot.android.leonardo.more.activities.ActivityNotifications, android.widget.CompoundButton, boolean):void");
    }

    public static final void S(ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.backPress();
    }

    public static final void T(final ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) == null || BleApiManager.getInstance(this$0).getBleApi() == null || !BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$8$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$8$1$onBatterySavingSettingsReceived$1", f = "ActivityNotifications.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ boolean $isEnabled;
                public final /* synthetic */ int $mode;
                public int label;
                public final /* synthetic */ ActivityNotifications this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(boolean z, int i, ActivityNotifications activityNotifications, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$isEnabled = z;
                    this.$mode = i;
                    this.this$0 = activityNotifications;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$isEnabled, this.$mode, this.this$0, continuation);
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
                        if (this.$isEnabled && this.$mode == 1) {
                            ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.sms_notification_value)).setChecked(false);
                            this.this$0.showBatterySaverModeEnabledDialog(null);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityNotifications.this), Dispatchers.getMain(), null, new a(z, i, ActivityNotifications.this, null), 2, null);
            }
        });
    }

    public static final void U(final ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) == null || BleApiManager.getInstance(this$0).getBleApi() == null || !BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$9$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$9$1$onBatterySavingSettingsReceived$1", f = "ActivityNotifications.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ boolean $isEnabled;
                public final /* synthetic */ int $mode;
                public int label;
                public final /* synthetic */ ActivityNotifications this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(boolean z, int i, ActivityNotifications activityNotifications, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$isEnabled = z;
                    this.$mode = i;
                    this.this$0 = activityNotifications;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$isEnabled, this.$mode, this.this$0, continuation);
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
                        if (this.$isEnabled && this.$mode == 1) {
                            ((RecyclerView) this.this$0._$_findCachedViewById(R.id.notification_setting_list)).setVisibility(8);
                            ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.app_notification_value)).setChecked(false);
                            this.this$0.showBatterySaverModeEnabledDialog(null);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityNotifications.this), Dispatchers.getMain(), null, new a(z, i, ActivityNotifications.this, null), 2, null);
            }
        });
    }

    public static final void V(final ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) == null || BleApiManager.getInstance(this$0).getBleApi() == null || !BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$10$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$10$1$onBatterySavingSettingsReceived$1", f = "ActivityNotifications.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ boolean $isEnabled;
                public final /* synthetic */ int $mode;
                public int label;
                public final /* synthetic */ ActivityNotifications this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(boolean z, int i, ActivityNotifications activityNotifications, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$isEnabled = z;
                    this.$mode = i;
                    this.this$0 = activityNotifications;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$isEnabled, this.$mode, this.this$0, continuation);
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
                        if (this.$isEnabled && this.$mode == 1) {
                            ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
                            this.this$0.showBatterySaverModeEnabledDialog(null);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityNotifications.this), Dispatchers.getMain(), null, new a(z, i, ActivityNotifications.this, null), 2, null);
            }
        });
    }

    public static final void W(Ref.ObjectRef dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
    }

    public static final void X(Ref.ObjectRef dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_PHONE_STATE"}, this$0.u);
    }

    public static final void Y(Ref.ObjectRef dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
    }

    public static final void Z(Ref.ObjectRef dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        if (Build.VERSION.SDK_INT >= 28) {
            ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CALL_LOG", "android.permission.ANSWER_PHONE_CALLS"}, this$0.s);
        } else {
            ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CALL_LOG", "android.permission.CALL_PHONE"}, this$0.s);
        }
    }

    public static final void a0(Ref.ObjectRef dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
    }

    public static final void b0(Ref.ObjectRef dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CONTACTS"}, this$0.t);
    }

    public static final void c0(ActivityNotifications this$0, GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
        dialog.dismiss();
    }

    public static final void d0(GenericMessageDialog dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.s);
    }

    public static final void e0(ActivityNotifications this$0, GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
        dialog.dismiss();
    }

    public static final void f0(GenericMessageDialog dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.t);
    }

    public static final void g0(BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void h0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void i0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void l0(ActivityNotifications this$0, GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
        dialog.dismiss();
    }

    public static final void m0(GenericMessageDialog dialog, ActivityNotifications this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.u);
    }

    public final boolean O() {
        int checkCallingOrSelfPermission = checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") & checkCallingOrSelfPermission("android.permission.READ_CONTACTS") & checkCallingOrSelfPermission("android.permission.READ_CALL_LOG") & checkCallingOrSelfPermission("android.permission.CALL_PHONE");
        if (Build.VERSION.SDK_INT >= 28) {
            checkCallingOrSelfPermission = checkCallingOrSelfPermission("android.permission.ANSWER_PHONE_CALLS") & checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") & checkCallingOrSelfPermission("android.permission.READ_CONTACTS") & checkCallingOrSelfPermission("android.permission.READ_CALL_LOG");
        }
        return checkCallingOrSelfPermission == 0;
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

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void askPermission(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        int i = WhenMappings.$EnumSwitchMapping$0[permission.ordinal()];
        if (i == 1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_PHONE_STATE"}, this.u);
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CONTACTS"}, this.t);
        } else if (Build.VERSION.SDK_INT >= 28) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CALL_LOG", "android.permission.ANSWER_PHONE_CALLS"}, this.s);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CALL_LOG", "android.permission.CALL_PHONE"}, this.s);
        }
    }

    public final void backPress() {
        if (this.B) {
            j0();
        } else {
            finish();
        }
    }

    public final void bandDisconnectDialog() {
        String string = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.zf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotifications.N(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void buttonVisible() {
        if (!Boolean.valueOf(((SwitchCompat) _$_findCachedViewById(R.id.call_notification_value)).isChecked()).equals(Boolean.valueOf(getUserDataManager().getNotificationsData().isCall_notifications()))) {
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
            this.B = true;
        } else if (!Boolean.valueOf(((SwitchCompat) _$_findCachedViewById(R.id.sms_notification_value)).isChecked()).equals(Boolean.valueOf(getUserDataManager().getNotificationsData().isSms_notifications()))) {
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
            this.B = true;
        } else if (!Boolean.valueOf(((SwitchCompat) _$_findCachedViewById(R.id.app_notification_value)).isChecked()).equals(Boolean.valueOf(getUserDataManager().getNotificationsData().isApp_notifications()))) {
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
            this.B = true;
        } else if (this.A) {
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
            this.B = true;
        } else {
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(false);
            this.B = false;
        }
    }

    public final void checkCallState() {
        String string = Settings.Secure.getString(getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
        if (string != null) {
            String packageName = getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                buttonVisible();
                return;
            }
        }
        if (this.r) {
            startActivityForResult(new Intent(AppConstants.NOTIFICATION_SETTING.getValue()), this.u);
            ((SwitchCompat) _$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
        }
        buttonVisible();
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void checkPermssion(@NotNull String permission, @NotNull PermissionUtils.PermissionAskListener permissionListener) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(permissionListener, "permissionListener");
        PermissionUtils.checkPermission(this, permission, permissionListener);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0020, code lost:
        if (kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r2, false, 2, (java.lang.Object) null) == false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void checkSmsState() {
        /*
            r5 = this;
            android.content.ContentResolver r0 = r5.getContentResolver()
            com.coveiot.android.leonardo.utils.AppConstants r1 = com.coveiot.android.leonardo.utils.AppConstants.ENABLE_NOTIFICATION_LISTENERS
            java.lang.String r1 = r1.getValue()
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r1)
            r1 = 0
            if (r0 == 0) goto L22
            java.lang.String r2 = r5.getPackageName()
            java.lang.String r3 = "packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r3 = 2
            r4 = 0
            boolean r0 = kotlin.text.StringsKt__StringsKt.contains$default(r0, r2, r1, r3, r4)
            if (r0 != 0) goto L41
        L22:
            boolean r0 = r5.q
            if (r0 == 0) goto L41
            android.content.Intent r0 = new android.content.Intent
            com.coveiot.android.leonardo.utils.AppConstants r2 = com.coveiot.android.leonardo.utils.AppConstants.NOTIFICATION_SETTING
            java.lang.String r2 = r2.getValue()
            r0.<init>(r2)
            int r2 = r5.w
            r5.startActivityForResult(r0, r2)
            int r0 = com.coveiot.android.boat.R.id.sms_notification_value
            android.view.View r0 = r5._$_findCachedViewById(r0)
            androidx.appcompat.widget.SwitchCompat r0 = (androidx.appcompat.widget.SwitchCompat) r0
            r0.setChecked(r1)
        L41:
            r5.buttonVisible()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityNotifications.checkSmsState():void");
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
        return this.z;
    }

    @NotNull
    public final List<AppNotificationData> getAppDataList() {
        return this.y;
    }

    @NotNull
    public final ArrayList<AppNotificationData> getAppNotificationData() {
        return this.x;
    }

    @NotNull
    public final TextView getBackArrow() {
        TextView textView = this.backArrow;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("backArrow");
        return null;
    }

    public final boolean getBoolSave() {
        return this.A;
    }

    public final boolean getBoolSaveVisible() {
        return this.B;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDialog() {
        return this.D;
    }

    @NotNull
    public final Context getMContext() {
        Context context = this.mContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    @Nullable
    public final List<AppNotificationData> getOriginalAppNotificationData() {
        return this.C;
    }

    @NotNull
    public final TextView getSaveText() {
        TextView textView = this.saveText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("saveText");
        return null;
    }

    @Override // android.app.Activity
    @NotNull
    public final TextView getTitle() {
        TextView textView = this.title;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("title");
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
    public final ActivityNotificationViewModel getViewModel() {
        ActivityNotificationViewModel activityNotificationViewModel = this.viewModel;
        if (activityNotificationViewModel != null) {
            return activityNotificationViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void j0() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        if (this.D == null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.D = bottomSheetDialogTwoButtons2;
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotifications$saveChangesDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ActivityNotifications.this.setBoolSaveVisible(false);
                    NotificationSettings notificationSettings = NotificationSettings.getInstance();
                    notificationSettings.setSms_notifications(((SwitchCompat) ActivityNotifications.this._$_findCachedViewById(R.id.sms_notification_value)).isChecked());
                    notificationSettings.setCall_notifications(((SwitchCompat) ActivityNotifications.this._$_findCachedViewById(R.id.call_notification_value)).isChecked());
                    notificationSettings.setApp_notifications(((SwitchCompat) ActivityNotifications.this._$_findCachedViewById(R.id.app_notification_value)).isChecked());
                    ActivityNotifications.this.getUserDataManager().saveNotificationsSettings(notificationSettings);
                    ActivityNotifications.this.getViewModel().saveAppNotification();
                    ActivityNotifications activityNotifications = ActivityNotifications.this;
                    List<AppNotificationData> appNotificationsData = activityNotifications.getUserDataManager().getAppNotificationsData();
                    Intrinsics.checkNotNull(appNotificationsData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.AppNotificationData>");
                    PreferenceManager.saveAppNotificationData(activityNotifications, (ArrayList) appNotificationsData);
                    Application application = ActivityNotifications.this.getApplication();
                    Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
                    ((GenericBandApplication) application).registerPhoneStateChangeListener(true);
                    BottomSheetDialogTwoButtons confirmationDialog = ActivityNotifications.this.getConfirmationDialog();
                    if (confirmationDialog != null) {
                        confirmationDialog.dismiss();
                    }
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.D;
            if (bottomSheetDialogTwoButtons3 != null) {
                String string4 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotifications$saveChangesDialog$2
                    @Override // android.view.View.OnClickListener
                    public void onClick(@Nullable View view) {
                        UserDataManager userDataManager = ActivityNotifications.this.getUserDataManager();
                        if (userDataManager != null) {
                            userDataManager.saveAppNotificationsSettings(ActivityNotifications.this.getOriginalAppNotificationData());
                        }
                        Application application = ActivityNotifications.this.getApplication();
                        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
                        ((GenericBandApplication) application).registerPhoneStateChangeListener(true);
                        BottomSheetDialogTwoButtons confirmationDialog = ActivityNotifications.this.getConfirmationDialog();
                        if (confirmationDialog != null) {
                            confirmationDialog.dismiss();
                        }
                        ActivityNotifications.this.finish();
                    }
                });
            }
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.D;
        Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.D) == null) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void k0() {
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        String string2 = getString(R.string.permission_required_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.permission_required_msg)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this, string, string2);
        ((TextView) genericMessageDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.wf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotifications.l0(ActivityNotifications.this, genericMessageDialog, view);
            }
        });
        ((TextView) genericMessageDialog.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.if
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotifications.m0(GenericMessageDialog.this, this, view);
            }
        });
        if (isFinishing()) {
            return;
        }
        genericMessageDialog.show();
    }

    @Override // com.coveiot.android.leonardo.more.AdapterAppList.SmartAlertClickListener
    public void knowMoreClick() {
        a aVar = a.INSTANCE;
        Intent intent = new Intent(this, SmartAlertActivity.class);
        aVar.invoke((a) intent);
        startActivityForResult(intent, -1, null);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        String string;
        super.onActivityResult(i, i2, intent);
        if (i == this.u) {
            String string2 = Settings.Secure.getString(getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
            if (string2 != null) {
                String packageName = getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt__StringsKt.contains$default((CharSequence) string2, (CharSequence) packageName, false, 2, (Object) null) && PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.READ_CALL_LOG", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE"}).length == 0) {
                    ((SwitchCompat) _$_findCachedViewById(R.id.call_notification_value)).setChecked(true);
                }
            }
        } else if (i == this.v) {
            String string3 = Settings.Secure.getString(getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
            if (string3 != null) {
                String packageName2 = getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                if (StringsKt__StringsKt.contains$default((CharSequence) string3, (CharSequence) packageName2, false, 2, (Object) null)) {
                    ((SwitchCompat) _$_findCachedViewById(R.id.app_notification_value)).setChecked(true);
                }
            }
        } else if (i != this.w || (string = Settings.Secure.getString(getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue())) == null) {
        } else {
            String packageName3 = getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName3, false, 2, (Object) null)) {
                ((SwitchCompat) _$_findCachedViewById(R.id.sms_notification_value)).setChecked(true);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_notifications);
        setViewModel((ActivityNotificationViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityNotificationViewModel.class));
        ActivityNotificationViewModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel);
        viewModel.setMListener(this);
        ActivityNotificationViewModel viewModel2 = getViewModel();
        Intrinsics.checkNotNull(viewModel2);
        viewModel2.setViewModelListener(this);
        UserDataManager userDataManager = UserDataManager.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(userDataManager, "getInstance(this@ActivityNotifications)");
        setUserDataManager(userDataManager);
        this.C = getUserDataManager().getAppNotificationsData();
        setAdapterAppList(new AdapterAppList(this, this.x, this, this));
        ActivityNotificationViewModel viewModel3 = getViewModel();
        Intrinsics.checkNotNull(viewModel3);
        UserDataManager userDataManager2 = getUserDataManager();
        List<AppNotificationData> list = this.z;
        List<AppNotificationData> list2 = this.y;
        ArrayList<AppNotificationData> arrayList = this.x;
        int i = R.id.sms_notification_value;
        SwitchCompat sms_notification_value = (SwitchCompat) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(sms_notification_value, "sms_notification_value");
        int i2 = R.id.call_notification_value;
        SwitchCompat call_notification_value = (SwitchCompat) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(call_notification_value, "call_notification_value");
        int i3 = R.id.app_notification_value;
        SwitchCompat app_notification_value = (SwitchCompat) _$_findCachedViewById(i3);
        Intrinsics.checkNotNullExpressionValue(app_notification_value, "app_notification_value");
        RecyclerView notification_setting_list = (RecyclerView) _$_findCachedViewById(R.id.notification_setting_list);
        Intrinsics.checkNotNullExpressionValue(notification_setting_list, "notification_setting_list");
        viewModel3.initValues(userDataManager2, list, list2, arrayList, sms_notification_value, call_notification_value, app_notification_value, notification_setting_list, getAdapterAppList());
        getViewModel().getShowProgressLiveData().observe(this, new Observer<Boolean>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Boolean bool) {
                if (bool != null) {
                    if (bool.booleanValue()) {
                        ActivityNotifications activityNotifications = ActivityNotifications.this;
                        String string = activityNotifications.getString(R.string.please_wait_loading_apps);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait_loading_apps)");
                        activityNotifications.showSystemProgresswithMsg(string);
                        return;
                    }
                    ActivityNotifications.this.dismissSystemProgress();
                }
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        setSaveText((TextView) findViewById);
        getSaveText().setVisibility(8);
        getSaveText().setText(getResources().getString(R.string.save_camel));
        getSaveText().setAlpha(0.5f);
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<TextView>(R.id.toolbar_back_arrow)");
        setBackArrow((TextView) findViewById2);
        View findViewById3 = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById<TextView>(R.id.toolbar_title)");
        setTitle((TextView) findViewById3);
        getTitle().setText(getResources().getString(R.string.notifications));
        ((SwitchCompat) _$_findCachedViewById(i3)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.sf
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNotifications.P(ActivityNotifications.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.rf
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNotifications.Q(ActivityNotifications.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(i2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.qf
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNotifications.R(ActivityNotifications.this, compoundButton, z);
            }
        });
        Button btn_ok = (Button) _$_findCachedViewById(R.id.btn_ok);
        Intrinsics.checkNotNullExpressionValue(btn_ok, "btn_ok");
        ViewUtilsKt.setSafeOnClickListener(btn_ok, new b());
        getBackArrow().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.tf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotifications.S(ActivityNotifications.this, view);
            }
        });
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$7

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityNotifications$onCreate$7$onBatterySavingSettingsReceived$1", f = "ActivityNotifications.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ boolean $isEnabled;
                    public final /* synthetic */ int $mode;
                    public int label;
                    public final /* synthetic */ ActivityNotifications this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(boolean z, int i, ActivityNotifications activityNotifications, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$isEnabled = z;
                        this.$mode = i;
                        this.this$0 = activityNotifications;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$isEnabled, this.$mode, this.this$0, continuation);
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
                            if (this.$isEnabled && this.$mode == 1) {
                                ((RecyclerView) this.this$0._$_findCachedViewById(R.id.notification_setting_list)).setVisibility(8);
                                ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.sms_notification_value)).setChecked(false);
                                ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
                                ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.app_notification_value)).setChecked(false);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySaverCMDFailed() {
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z, int i4) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityNotifications.this), Dispatchers.getMain(), null, new a(z, i4, ActivityNotifications.this, null), 2, null);
                }
            });
        }
        ((SwitchCompat) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.pf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotifications.T(ActivityNotifications.this, view);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(i3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.uf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotifications.U(ActivityNotifications.this, view);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(i2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ef
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotifications.V(ActivityNotifications.this, view);
            }
        });
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [com.coveiot.android.theme.GenericMessageDialog, T] */
    /* JADX WARN: Type inference failed for: r0v17, types: [com.coveiot.android.theme.GenericMessageDialog, T] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.coveiot.android.theme.GenericMessageDialog, T] */
    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionDenied(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        int i = WhenMappings.$EnumSwitchMapping$0[permission.ordinal()];
        if (i == 1) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            String string = getString(R.string.phone_permission);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.phone_permission)");
            String string2 = getString(R.string.phone_permission_required);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.phone_permission_required)");
            ?? genericMessageDialog = new GenericMessageDialog(this, string, string2);
            objectRef.element = genericMessageDialog;
            ((TextView) ((GenericMessageDialog) genericMessageDialog).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.mf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.W(Ref.ObjectRef.this, this, view);
                }
            });
            ((TextView) ((GenericMessageDialog) objectRef.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.of
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.X(Ref.ObjectRef.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            ((GenericMessageDialog) objectRef.element).show();
        } else if (i == 2) {
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            String string3 = getString(R.string.call_permission);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.call_permission)");
            String string4 = getString(R.string.call_permission_required);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.call_permission_required)");
            ?? genericMessageDialog2 = new GenericMessageDialog(this, string3, string4);
            objectRef2.element = genericMessageDialog2;
            ((TextView) ((GenericMessageDialog) genericMessageDialog2).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.nf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.Y(Ref.ObjectRef.this, this, view);
                }
            });
            ((TextView) ((GenericMessageDialog) objectRef2.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.jf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.Z(Ref.ObjectRef.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            ((GenericMessageDialog) objectRef2.element).show();
        } else if (i != 3) {
        } else {
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            String string5 = getString(R.string.contact_permission);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.contact_permission)");
            String string6 = getString(R.string.contact_permission_required);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.contact_permission_required)");
            ?? genericMessageDialog3 = new GenericMessageDialog(this, string5, string6);
            objectRef3.element = genericMessageDialog3;
            ((TextView) ((GenericMessageDialog) genericMessageDialog3).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.lf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.a0(Ref.ObjectRef.this, this, view);
                }
            });
            ((TextView) ((GenericMessageDialog) objectRef3.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.kf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.b0(Ref.ObjectRef.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            ((GenericMessageDialog) objectRef3.element).show();
        }
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionDisabled(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        int i = WhenMappings.$EnumSwitchMapping$0[permission.ordinal()];
        if (i == 1) {
            k0();
        } else if (i == 2) {
            String string = getString(R.string.call_permission);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.call_permission)");
            String string2 = getString(R.string.call_permission_required);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.call_permission_required)");
            final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this, string, string2);
            ((TextView) genericMessageDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.c0(ActivityNotifications.this, genericMessageDialog, view);
                }
            });
            ((TextView) genericMessageDialog.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.hf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.d0(GenericMessageDialog.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            genericMessageDialog.show();
        } else if (i != 3) {
        } else {
            String string3 = getString(R.string.contact_permission);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.contact_permission)");
            String string4 = getString(R.string.contact_permission_required);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.contact_permission_required)");
            final GenericMessageDialog genericMessageDialog2 = new GenericMessageDialog(this, string3, string4);
            ((TextView) genericMessageDialog2.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.vf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.e0(ActivityNotifications.this, genericMessageDialog2, view);
                }
            });
            ((TextView) genericMessageDialog2.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.gf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.f0(GenericMessageDialog.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            genericMessageDialog2.show();
        }
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionSuccess(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        int i = WhenMappings.$EnumSwitchMapping$0[permission.ordinal()];
        if (i == 1) {
            ((SwitchCompat) _$_findCachedViewById(R.id.call_notification_value)).setChecked(true);
            checkCallState();
        } else if (i == 2) {
            getViewModel().checkContactPermission();
            getViewModel().checkCallPermission();
        } else if (i != 3) {
        } else {
            getViewModel().checkPhonePermission();
        }
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.p) {
            String[] permissionNotGiven = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.READ_CALL_LOG", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE"});
            if (Build.VERSION.SDK_INT >= 28) {
                permissionNotGiven = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.READ_CALL_LOG", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.ANSWER_PHONE_CALLS"});
            }
            int i2 = R.id.call_notification_value;
            SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(permissionNotGiven, "permissionNotGiven");
            switchCompat.setChecked(!(permissionNotGiven.length == 0));
            buttonVisible();
            String string = Settings.Secure.getString(getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
            if (string != null) {
                String packageName = getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                    return;
                }
            }
            if (this.r) {
                startActivityForResult(new Intent(AppConstants.NOTIFICATION_SETTING.getValue()), this.u);
                ((SwitchCompat) _$_findCachedViewById(i2)).setChecked(false);
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (O()) {
            return;
        }
        ((SwitchCompat) _$_findCachedViewById(R.id.call_notification_value)).setChecked(false);
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        boolean isDNDEnabled = PayUtils.INSTANCE.isDNDEnabled(UserDataManager.getInstance(this).getDoNotDisturbData());
        if (!((SwitchCompat) _$_findCachedViewById(R.id.sms_notification_value)).isChecked() && !((SwitchCompat) _$_findCachedViewById(R.id.call_notification_value)).isChecked() && !((SwitchCompat) _$_findCachedViewById(R.id.app_notification_value)).isChecked()) {
            String string = getResources().getString(R.string.success_message);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.success_message)");
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
            String string2 = getResources().getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …R.string.ok\n            )");
            bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ag
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.i0(BottomSheetDialogOneButtonOneTitle.this, this, view);
                }
            });
            bottomSheetDialogOneButtonOneTitle.show();
        } else if (isDNDEnabled) {
            Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
            String string3 = getString(R.string.success_message);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.success_message)");
            String string4 = getString(R.string.turn_off_dnd_enable_notifications);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.turn_…dnd_enable_notifications)");
            final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string3, string4, true);
            bottomSheetDialogImageTitleMessage.setCancelable(false);
            bottomSheetDialogImageTitleMessage.setCancelableOutside(false);
            String string5 = getResources().getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(\n   …ring.ok\n                )");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string5, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.yf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.g0(BottomSheetDialogImageTitleMessage.this, this, view);
                }
            });
            bottomSheetDialogImageTitleMessage.show();
        } else {
            String string6 = getResources().getString(R.string.success_message);
            Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.success_message)");
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = new BottomSheetDialogOneButtonOneTitle(this, string6);
            String string7 = getResources().getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string7, "resources.getString(\n   …ring.ok\n                )");
            bottomSheetDialogOneButtonOneTitle2.setPositiveButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ff
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNotifications.h0(BottomSheetDialogOneButtonOneTitle.this, this, view);
                }
            });
            bottomSheetDialogOneButtonOneTitle2.show();
        }
    }

    @Override // com.coveiot.android.leonardo.more.AppNotificationInterface
    public void saveAppData(@NotNull List<? extends AppNotificationData> appNotificationData, @NotNull String str, boolean z) {
        Intrinsics.checkNotNullParameter(appNotificationData, "appNotificationData");
        Intrinsics.checkNotNullParameter(str, "str");
        List<AppNotificationData> appNotificationsData = getUserDataManager().getAppNotificationsData();
        Intrinsics.checkNotNullExpressionValue(appNotificationsData, "userDataManager.appNotificationsData");
        this.y = appNotificationsData;
        ArrayList<AppNotificationData> appNotificationData2 = PreferenceManager.getAppNotificationData(this);
        Intrinsics.checkNotNullExpressionValue(appNotificationData2, "getAppNotificationData(this@ActivityNotifications)");
        this.z = appNotificationData2;
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            if (kotlin.text.m.equals(str, this.y.get(i).getPackageName(), true)) {
                this.y.get(i).setChecked(z);
            }
        }
        getUserDataManager().saveAppNotificationsSettings(this.y);
        int size2 = getUserDataManager().getAppNotificationsData().size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (!Boolean.valueOf(this.z.get(i2).getChecked()).equals(Boolean.valueOf(getUserDataManager().getAppNotificationsData().get(i2).getChecked()))) {
                this.A = true;
                buttonVisible();
                return;
            }
            this.A = false;
            buttonVisible();
        }
    }

    public final void setAdapterAppList(@NotNull AdapterAppList adapterAppList) {
        Intrinsics.checkNotNullParameter(adapterAppList, "<set-?>");
        this.adapterAppList = adapterAppList;
    }

    public final void setAppData(@NotNull List<AppNotificationData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.z = list;
    }

    public final void setAppDataList(@NotNull List<AppNotificationData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.y = list;
    }

    public final void setAppNotificationData(@NotNull ArrayList<AppNotificationData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.x = arrayList;
    }

    public final void setBackArrow(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backArrow = textView;
    }

    public final void setBoolSave(boolean z) {
        this.A = z;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.B = z;
    }

    public final void setConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.D = bottomSheetDialogTwoButtons;
    }

    public final void setMContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    public final void setOriginalAppNotificationData(@Nullable List<AppNotificationData> list) {
        this.C = list;
    }

    public final void setSaveText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.saveText = textView;
    }

    public final void setTitle(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.title = textView;
    }

    public final void setUserDataManager(@NotNull UserDataManager userDataManager) {
        Intrinsics.checkNotNullParameter(userDataManager, "<set-?>");
        this.userDataManager = userDataManager;
    }

    public final void setViewModel(@NotNull ActivityNotificationViewModel activityNotificationViewModel) {
        Intrinsics.checkNotNullParameter(activityNotificationViewModel, "<set-?>");
        this.viewModel = activityNotificationViewModel;
    }
}
