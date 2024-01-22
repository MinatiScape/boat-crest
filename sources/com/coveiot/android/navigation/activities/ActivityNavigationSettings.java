package com.coveiot.android.navigation.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.response.GetNavigationConfigurationResponse;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.activities.ActivityNavigationSettings;
import com.coveiot.android.navigation.databinding.ActivityNavigationSettingsBinding;
import com.coveiot.android.navigation.viewModels.ActivityNavigationSettingsViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import java.util.LinkedHashMap;
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
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationSettings extends BaseActivity {
    public ActivityNavigationSettingsBinding binding;
    public ActivityNavigationSettingsViewModel q;
    @Nullable
    public BottomSheetDialogImageTitleMessage r;
    public boolean s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = ActivityNavigationSettings.class.getSimpleName();

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<GetNavigationConfigurationResponse, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(ActivityNavigationSettings this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.getBottomSheetDialogImageTitleMessage();
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            bottomSheetDialogImageTitleMessage.dismiss();
            this$0.finish();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetNavigationConfigurationResponse getNavigationConfigurationResponse) {
            invoke2(getNavigationConfigurationResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable GetNavigationConfigurationResponse getNavigationConfigurationResponse) {
            if (getNavigationConfigurationResponse != null) {
                ActivityNavigationSettings.this.dismissProgress();
                ActivityNavigationSettings.this.getBinding().switchAudio.setChecked(getNavigationConfigurationResponse.isAudioEnabled());
                ActivityNavigationSettings.this.getBinding().switchHaptic.setChecked(getNavigationConfigurationResponse.isHapticEnabled());
                ActivityNavigationSettings.this.getBinding().switchNavigationAlwaysOnDisplay.setChecked(getNavigationConfigurationResponse.isAODEnabled());
                return;
            }
            ActivityNavigationSettings.this.dismissProgress();
            ActivityNavigationSettings activityNavigationSettings = ActivityNavigationSettings.this;
            Drawable drawable = activityNavigationSettings.getResources().getDrawable(R.drawable.failure_image_img);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…awable.failure_image_img)");
            String string = ActivityNavigationSettings.this.getResources().getString(R.string.failed);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.failed)");
            String string2 = ActivityNavigationSettings.this.getResources().getString(com.coveiot.android.theme.R.string.some_thing_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(com.…ng.some_thing_went_wrong)");
            activityNavigationSettings.C(drawable, string, string2);
            if (ActivityNavigationSettings.this.getBottomSheetDialogImageTitleMessage() != null) {
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = ActivityNavigationSettings.this.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = ActivityNavigationSettings.this.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                String string3 = ActivityNavigationSettings.this.getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.okay)");
                final ActivityNavigationSettings activityNavigationSettings2 = ActivityNavigationSettings.this;
                bottomSheetDialogImageTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.i1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSettings.a.invoke$lambda$0(ActivityNavigationSettings.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = ActivityNavigationSettings.this.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                if (bottomSheetDialogImageTitleMessage3.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = ActivityNavigationSettings.this.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                bottomSheetDialogImageTitleMessage4.show();
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationSettings$saveSettings$1", f = "ActivityNavigationSettings.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isAODEnabled;
        public final /* synthetic */ boolean $isAudioEnabled;
        public final /* synthetic */ boolean $isHapticEnabled;
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<Boolean, Unit> {
            public final /* synthetic */ boolean $isAODEnabled;
            public final /* synthetic */ ActivityNavigationSettings this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityNavigationSettings activityNavigationSettings, boolean z) {
                super(1);
                this.this$0 = activityNavigationSettings;
                this.$isAODEnabled = z;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$0(ActivityNavigationSettings this$0, View view) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.dismiss();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$1(ActivityNavigationSettings this$0, View view) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.dismiss();
                this$0.finish();
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
                    if (this.this$0.isFromNavigation()) {
                        MapplsNavigationHelper.getInstance().setMute(!this.$isAODEnabled);
                    }
                    this.this$0.dismissProgress();
                    ActivityNavigationSettings activityNavigationSettings = this.this$0;
                    Drawable drawable = activityNavigationSettings.getResources().getDrawable(R.drawable.success_image_new_img);
                    Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…le.success_image_new_img)");
                    String string = this.this$0.getResources().getString(R.string.success_);
                    Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.success_)");
                    String string2 = this.this$0.getResources().getString(com.coveiot.android.theme.R.string.settings_saved_successfully);
                    Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(com.…tings_saved_successfully)");
                    activityNavigationSettings.C(drawable, string, string2);
                    if (this.this$0.getBottomSheetDialogImageTitleMessage() != null) {
                        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.this$0.getBottomSheetDialogImageTitleMessage();
                        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                        bottomSheetDialogImageTitleMessage.setCancelable(false);
                        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.this$0.getBottomSheetDialogImageTitleMessage();
                        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                        String string3 = this.this$0.getString(R.string.okay);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.okay)");
                        final ActivityNavigationSettings activityNavigationSettings2 = this.this$0;
                        bottomSheetDialogImageTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.k1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityNavigationSettings.b.a.invoke$lambda$0(ActivityNavigationSettings.this, view);
                            }
                        });
                        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.this$0.getBottomSheetDialogImageTitleMessage();
                        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                        if (bottomSheetDialogImageTitleMessage3.isShowing()) {
                            return;
                        }
                        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.this$0.getBottomSheetDialogImageTitleMessage();
                        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                        bottomSheetDialogImageTitleMessage4.show();
                        return;
                    }
                    return;
                }
                this.this$0.dismissProgress();
                ActivityNavigationSettings activityNavigationSettings3 = this.this$0;
                Drawable drawable2 = activityNavigationSettings3.getResources().getDrawable(R.drawable.failure_image_img);
                Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(R.…awable.failure_image_img)");
                String string4 = this.this$0.getResources().getString(R.string.failed);
                Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.failed)");
                String string5 = this.this$0.getResources().getString(com.coveiot.android.theme.R.string.setting_couldnot_save);
                Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(com.…ng.setting_couldnot_save)");
                activityNavigationSettings3.C(drawable2, string4, string5);
                if (this.this$0.getBottomSheetDialogImageTitleMessage() != null) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this.this$0.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
                    bottomSheetDialogImageTitleMessage5.setCancelable(false);
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = this.this$0.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
                    String string6 = this.this$0.getString(R.string.okay);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.okay)");
                    final ActivityNavigationSettings activityNavigationSettings4 = this.this$0;
                    bottomSheetDialogImageTitleMessage6.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.j1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityNavigationSettings.b.a.invoke$lambda$1(ActivityNavigationSettings.this, view);
                        }
                    });
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage7 = this.this$0.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage7);
                    if (bottomSheetDialogImageTitleMessage7.isShowing()) {
                        return;
                    }
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage8 = this.this$0.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage8);
                    bottomSheetDialogImageTitleMessage8.show();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, boolean z2, boolean z3, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$isAudioEnabled = z;
            this.$isHapticEnabled = z2;
            this.$isAODEnabled = z3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$isAudioEnabled, this.$isHapticEnabled, this.$isAODEnabled, continuation);
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
                ActivityNavigationSettingsViewModel activityNavigationSettingsViewModel = ActivityNavigationSettings.this.q;
                if (activityNavigationSettingsViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityNavigationSettingsViewModel = null;
                }
                boolean z = this.$isAudioEnabled;
                boolean z2 = this.$isHapticEnabled;
                boolean z3 = this.$isAODEnabled;
                activityNavigationSettingsViewModel.saveNavigationSettingsOnBand(z, z2, z3, new a(ActivityNavigationSettings.this, z3));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityNavigationSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (compoundButton.isPressed()) {
            LogHelper.d(this$0.p, "switchHaptic buttonView.isPressed");
            this$0.showProgress();
            this$0.G(this$0.getBinding().switchAudio.isChecked(), z, this$0.getBinding().switchNavigationAlwaysOnDisplay.isChecked());
            return;
        }
        LogHelper.d(this$0.p, "switchHaptic buttonView.isPressed else");
    }

    public static final void B(ActivityNavigationSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (compoundButton.isPressed()) {
            LogHelper.d(this$0.p, "switchNavigationAlwaysOnDisplay buttonView.isPressed");
            this$0.showProgress();
            this$0.G(this$0.getBinding().switchAudio.isChecked(), this$0.getBinding().switchHaptic.isChecked(), z);
            return;
        }
        LogHelper.d(this$0.p, "switchNavigationAlwaysOnDisplay buttonView.isPressed else");
    }

    public static final void E(ActivityNavigationSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void F(ActivityNavigationSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void H(ActivityNavigationSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void I(ActivityNavigationSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void K(ActivityNavigationSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(ActivityNavigationSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (compoundButton.isPressed()) {
            LogHelper.d(this$0.p, "switchAudio buttonView.isPressed");
            this$0.showProgress();
            this$0.G(z, this$0.getBinding().switchHaptic.isChecked(), this$0.getBinding().switchNavigationAlwaysOnDisplay.isChecked());
            return;
        }
        LogHelper.d(this$0.p, "switchAudio buttonView.isPressed else");
    }

    public final void C(Drawable drawable, String str, String str2) {
        this.r = new BottomSheetDialogImageTitleMessage(this, drawable, str, str2, false);
    }

    public final void D() {
        showProgress();
        if (AppUtils.isNetConnected(this) && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            ActivityNavigationSettingsViewModel activityNavigationSettingsViewModel = this.q;
            if (activityNavigationSettingsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityNavigationSettingsViewModel = null;
            }
            activityNavigationSettingsViewModel.getNavigationSettingsFromBand(new a());
        } else if (!AppUtils.isNetConnected(this)) {
            dismissProgress();
            Drawable drawable = getResources().getDrawable(R.drawable.no_internet);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.no_internet)");
            String string = getResources().getString(R.string.no_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.no_internet)");
            String string2 = getResources().getString(R.string.please_ensure_that_you_have_a_stable_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…able_internet_connection)");
            C(drawable, string, string2);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.r;
            if (bottomSheetDialogImageTitleMessage != null) {
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                String string3 = getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.okay)");
                bottomSheetDialogImageTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.e1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSettings.E(ActivityNavigationSettings.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                if (bottomSheetDialogImageTitleMessage3.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                bottomSheetDialogImageTitleMessage4.show();
            }
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            dismissProgress();
            Drawable drawable2 = getResources().getDrawable(com.coveiot.android.theme.R.drawable.ic_bt_disconnected);
            Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(co…wable.ic_bt_disconnected)");
            String string4 = getResources().getString(R.string.device_disconnected);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.device_disconnected)");
            String string5 = getResources().getString(R.string.please_connect_your_device);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.st…ease_connect_your_device)");
            C(drawable2, string4, string5);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this.r;
            if (bottomSheetDialogImageTitleMessage5 != null) {
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
                bottomSheetDialogImageTitleMessage5.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
                String string6 = getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.okay)");
                bottomSheetDialogImageTitleMessage6.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.d1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSettings.F(ActivityNavigationSettings.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage7 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage7);
                if (bottomSheetDialogImageTitleMessage7.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage8 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage8);
                bottomSheetDialogImageTitleMessage8.show();
            }
        }
    }

    public final void G(boolean z, boolean z2, boolean z3) {
        if (AppUtils.isNetConnected(this) && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new b(z, z2, z3, null), 3, null);
        } else if (!AppUtils.isNetConnected(this)) {
            dismissProgress();
            Drawable drawable = getResources().getDrawable(R.drawable.no_internet);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.no_internet)");
            String string = getResources().getString(R.string.no_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.no_internet)");
            String string2 = getResources().getString(R.string.please_ensure_that_you_have_a_stable_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…able_internet_connection)");
            C(drawable, string, string2);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.r;
            if (bottomSheetDialogImageTitleMessage != null) {
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                String string3 = getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.okay)");
                bottomSheetDialogImageTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.c1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSettings.H(ActivityNavigationSettings.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                if (bottomSheetDialogImageTitleMessage3.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                bottomSheetDialogImageTitleMessage4.show();
            }
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            dismissProgress();
            Drawable drawable2 = getResources().getDrawable(com.coveiot.android.theme.R.drawable.ic_bt_disconnected);
            Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(co…wable.ic_bt_disconnected)");
            String string4 = getResources().getString(R.string.device_disconnected);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.device_disconnected)");
            String string5 = getResources().getString(R.string.please_connect_your_device);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.st…ease_connect_your_device)");
            C(drawable2, string4, string5);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this.r;
            if (bottomSheetDialogImageTitleMessage5 != null) {
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
                bottomSheetDialogImageTitleMessage5.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
                String string6 = getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.okay)");
                bottomSheetDialogImageTitleMessage6.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.a1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSettings.I(ActivityNavigationSettings.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage7 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage7);
                if (bottomSheetDialogImageTitleMessage7.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage8 = this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage8);
                bottomSheetDialogImageTitleMessage8.show();
            }
        }
    }

    public final void J() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.navigation_settings));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationSettings.K(ActivityNavigationSettings.this, view);
            }
        });
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
    public final ActivityNavigationSettingsBinding getBinding() {
        ActivityNavigationSettingsBinding activityNavigationSettingsBinding = this.binding;
        if (activityNavigationSettingsBinding != null) {
            return activityNavigationSettingsBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessage() {
        return this.r;
    }

    public final String getTAG() {
        return this.p;
    }

    public final boolean isFromNavigation() {
        return this.s;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityNavigationSettingsBinding inflate = ActivityNavigationSettingsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        this.q = (ActivityNavigationSettingsViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityNavigationSettingsViewModel.class);
        if (getIntent() != null) {
            this.s = getIntent().getBooleanExtra("isFromNavigation", false);
        }
        J();
        D();
        y();
    }

    public final void setBinding(@NotNull ActivityNavigationSettingsBinding activityNavigationSettingsBinding) {
        Intrinsics.checkNotNullParameter(activityNavigationSettingsBinding, "<set-?>");
        this.binding = activityNavigationSettingsBinding;
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.r = bottomSheetDialogImageTitleMessage;
    }

    public final void setFromNavigation(boolean z) {
        this.s = z;
    }

    public final void setTAG(String str) {
        this.p = str;
    }

    public final void y() {
        getBinding().switchAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.navigation.activities.h1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNavigationSettings.z(ActivityNavigationSettings.this, compoundButton, z);
            }
        });
        getBinding().switchHaptic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.navigation.activities.g1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNavigationSettings.A(ActivityNavigationSettings.this, compoundButton, z);
            }
        });
        getBinding().switchNavigationAlwaysOnDisplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.navigation.activities.f1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNavigationSettings.B(ActivityNavigationSettings.this, compoundButton, z);
            }
        });
    }
}
