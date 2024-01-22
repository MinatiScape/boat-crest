package com.coveiot.android.leonardo.more.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.BatterySaverModeHelper;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.viewmodel.AutoHrViewModel;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.Arrays;
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
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAutoHrSettings extends BaseActivity implements DialogListener, BatterySaverModeDialogClickCallback {
    public AutoHrViewModel autoHrViewModel;
    public TextView backArrow;
    public boolean q;
    public int s;
    public TextView saveText;
    public boolean t;
    public TextView titleText;
    @Nullable
    public BottomSheetDialogTwoButtons u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = ActivityAutoHrSettings.class.getSimpleName();
    public int r = 30;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
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
            if (ActivityAutoHrSettings.this.isAutoHrSwitchEnabled() != ((SwitchCompat) ActivityAutoHrSettings.this._$_findCachedViewById(R.id.switch_auto_hr)).isChecked()) {
                ActivityAutoHrSettings.this.x();
            } else if (ActivityAutoHrSettings.this.getAutoHrIntervalValue() != ActivityAutoHrSettings.this.getAutoHrDefaultInterval()) {
                ActivityAutoHrSettings.this.x();
            } else {
                ActivityAutoHrSettings activityAutoHrSettings = ActivityAutoHrSettings.this;
                Toast.makeText(activityAutoHrSettings, activityAutoHrSettings.getResources().getString(R.string.no_changes), 0).show();
            }
        }
    }

    public static final void C(final ActivityAutoHrSettings this$0, CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getBleApi() != null && BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoHrSettings$setOnCheckChangeListenerForAutohr$1$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityAutoHrSettings$setOnCheckChangeListenerForAutohr$1$1$onBatterySavingSettingsReceived$1", f = "ActivityAutoHrSettings.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ boolean $b;
                    public final /* synthetic */ boolean $isEnabled;
                    public int label;
                    public final /* synthetic */ ActivityAutoHrSettings this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(boolean z, ActivityAutoHrSettings activityAutoHrSettings, boolean z2, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$isEnabled = z;
                        this.this$0 = activityAutoHrSettings;
                        this.$b = z2;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$isEnabled, this.this$0, this.$b, continuation);
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
                            if (this.$isEnabled) {
                                ActivityAutoHrSettings activityAutoHrSettings = this.this$0;
                                int i = R.id.switch_auto_hr;
                                ((SwitchCompat) activityAutoHrSettings._$_findCachedViewById(i)).setOnCheckedChangeListener(null);
                                ((SwitchCompat) this.this$0._$_findCachedViewById(i)).setChecked(false);
                                ActivityAutoHrSettings activityAutoHrSettings2 = this.this$0;
                                activityAutoHrSettings2.showBatterySaverModeEnabledDialog(activityAutoHrSettings2);
                            } else {
                                if (this.$b) {
                                    if (!DeviceUtils.Companion.isMatrixDevice(this.this$0)) {
                                        ((LinearLayout) this.this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(0);
                                        this.this$0.I();
                                    } else {
                                        ((LinearLayout) this.this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
                                    }
                                    ((TextView) this.this$0._$_findCachedViewById(R.id.select_frequency)).setAlpha(1.0f);
                                    ActivityAutoHrSettings activityAutoHrSettings3 = this.this$0;
                                    int i2 = R.id.mins_5_radiobutton;
                                    ((RadioButton) activityAutoHrSettings3._$_findCachedViewById(i2)).setEnabled(true);
                                    ActivityAutoHrSettings activityAutoHrSettings4 = this.this$0;
                                    int i3 = R.id.mins_10_radiobutton;
                                    ((RadioButton) activityAutoHrSettings4._$_findCachedViewById(i3)).setEnabled(true);
                                    ActivityAutoHrSettings activityAutoHrSettings5 = this.this$0;
                                    int i4 = R.id.mins_15_radiobutton;
                                    ((RadioButton) activityAutoHrSettings5._$_findCachedViewById(i4)).setEnabled(true);
                                    ActivityAutoHrSettings activityAutoHrSettings6 = this.this$0;
                                    int i5 = R.id.mins_30_radiobutton;
                                    ((RadioButton) activityAutoHrSettings6._$_findCachedViewById(i5)).setEnabled(true);
                                    ActivityAutoHrSettings activityAutoHrSettings7 = this.this$0;
                                    int i6 = R.id.mins_60_radiobutton;
                                    ((RadioButton) activityAutoHrSettings7._$_findCachedViewById(i6)).setEnabled(true);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i2)).setAlpha(1.0f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i3)).setAlpha(1.0f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i4)).setAlpha(1.0f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i5)).setAlpha(1.0f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i6)).setAlpha(1.0f);
                                } else {
                                    ((LinearLayout) this.this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
                                    ActivityAutoHrSettings activityAutoHrSettings8 = this.this$0;
                                    int i7 = R.id.mins_5_radiobutton;
                                    ((RadioButton) activityAutoHrSettings8._$_findCachedViewById(i7)).setEnabled(false);
                                    ActivityAutoHrSettings activityAutoHrSettings9 = this.this$0;
                                    int i8 = R.id.mins_10_radiobutton;
                                    ((RadioButton) activityAutoHrSettings9._$_findCachedViewById(i8)).setEnabled(false);
                                    ActivityAutoHrSettings activityAutoHrSettings10 = this.this$0;
                                    int i9 = R.id.mins_15_radiobutton;
                                    ((RadioButton) activityAutoHrSettings10._$_findCachedViewById(i9)).setEnabled(false);
                                    ActivityAutoHrSettings activityAutoHrSettings11 = this.this$0;
                                    int i10 = R.id.mins_30_radiobutton;
                                    ((RadioButton) activityAutoHrSettings11._$_findCachedViewById(i10)).setEnabled(false);
                                    ActivityAutoHrSettings activityAutoHrSettings12 = this.this$0;
                                    int i11 = R.id.mins_60_radiobutton;
                                    ((RadioButton) activityAutoHrSettings12._$_findCachedViewById(i11)).setEnabled(false);
                                    ((TextView) this.this$0._$_findCachedViewById(R.id.select_frequency)).setAlpha(0.35f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i7)).setAlpha(0.35f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i8)).setAlpha(0.35f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i9)).setAlpha(0.35f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i10)).setAlpha(0.35f);
                                    ((RadioButton) this.this$0._$_findCachedViewById(i11)).setAlpha(0.35f);
                                }
                                ActivityAutoHrSettings activityAutoHrSettings13 = this.this$0;
                                activityAutoHrSettings13.setAutoHrDefaultInterval(activityAutoHrSettings13.getAutoHrIntervalValue());
                                this.this$0.A();
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySaverCMDFailed() {
                    Toast.makeText(ActivityAutoHrSettings.this, (int) R.string.somethings_went_wrong, 0).show();
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z2, int i) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityAutoHrSettings.this), Dispatchers.getMain(), null, new a(z2, ActivityAutoHrSettings.this, z, null), 2, null);
                }
            });
            return;
        }
        if (z) {
            if (!DeviceUtils.Companion.isMatrixDevice(this$0)) {
                ((LinearLayout) this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(0);
                this$0.I();
            } else {
                ((LinearLayout) this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
            }
            ((TextView) this$0._$_findCachedViewById(R.id.select_frequency)).setAlpha(1.0f);
            int i = R.id.mins_5_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i)).setEnabled(true);
            int i2 = R.id.mins_10_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i2)).setEnabled(true);
            int i3 = R.id.mins_15_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i3)).setEnabled(true);
            int i4 = R.id.mins_30_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i4)).setEnabled(true);
            int i5 = R.id.mins_60_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i5)).setEnabled(true);
            ((RadioButton) this$0._$_findCachedViewById(i)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i2)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i3)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i4)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i5)).setAlpha(1.0f);
        } else {
            ((LinearLayout) this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
            int i6 = R.id.mins_5_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i6)).setEnabled(false);
            int i7 = R.id.mins_10_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i7)).setEnabled(false);
            int i8 = R.id.mins_15_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i8)).setEnabled(false);
            int i9 = R.id.mins_30_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i9)).setEnabled(false);
            int i10 = R.id.mins_60_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i10)).setEnabled(false);
            ((TextView) this$0._$_findCachedViewById(R.id.select_frequency)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i6)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i7)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i8)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i9)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i10)).setAlpha(0.35f);
        }
        this$0.r = this$0.s;
        this$0.A();
    }

    public static final void E(ActivityAutoHrSettings this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        switch (i) {
            case R.id.mins_10_radiobutton /* 2131364622 */:
                this$0.r = 10;
                break;
            case R.id.mins_15_radiobutton /* 2131364623 */:
                this$0.r = 15;
                break;
            case R.id.mins_30_radiobutton /* 2131364624 */:
                this$0.r = 30;
                break;
            case R.id.mins_5_radiobutton /* 2131364625 */:
                this$0.r = 5;
                break;
            case R.id.mins_60_radiobutton /* 2131364626 */:
                this$0.r = 60;
                break;
        }
        this$0.A();
    }

    public static final void F(ActivityAutoHrSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void G(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoHrSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void H(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoHrSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void J(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        bottomSheetDialogImageTitleMessage.dismiss();
    }

    public static final void y(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityAutoHrSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public final void A() {
        if (this.t != ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).isChecked()) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            getSaveText().setAlpha(1.0f);
        } else if (this.s != this.r) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            getSaveText().setAlpha(1.0f);
        } else {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(false);
            getSaveText().setAlpha(0.5f);
        }
    }

    public final void B() {
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.j0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoHrSettings.C(ActivityAutoHrSettings.this, compoundButton, z);
            }
        });
    }

    public final void D() {
        getSaveText().setEnabled(false);
        getSaveText().setAlpha(0.5f);
        this.s = UserDataManager.getInstance(this).getAutoHrInterval();
        boolean isAutoHrEnabled = UserDataManager.getInstance(this).isAutoHrEnabled();
        this.t = isAutoHrEnabled;
        if (isAutoHrEnabled) {
            if (!DeviceUtils.Companion.isMatrixDevice(this)) {
                ((LinearLayout) _$_findCachedViewById(R.id.interval_layout)).setVisibility(0);
            } else {
                ((LinearLayout) _$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
            }
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).setChecked(true);
            ((TextView) _$_findCachedViewById(R.id.select_frequency)).setAlpha(1.0f);
            int i = R.id.mins_5_radiobutton;
            ((RadioButton) _$_findCachedViewById(i)).setEnabled(true);
            int i2 = R.id.mins_10_radiobutton;
            ((RadioButton) _$_findCachedViewById(i2)).setEnabled(true);
            int i3 = R.id.mins_15_radiobutton;
            ((RadioButton) _$_findCachedViewById(i3)).setEnabled(true);
            int i4 = R.id.mins_30_radiobutton;
            ((RadioButton) _$_findCachedViewById(i4)).setEnabled(true);
            int i5 = R.id.mins_60_radiobutton;
            ((RadioButton) _$_findCachedViewById(i5)).setEnabled(true);
            ((RadioButton) _$_findCachedViewById(i)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i2)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i3)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i4)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i5)).setAlpha(1.0f);
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.select_frequency)).setAlpha(0.35f);
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).setChecked(false);
            int i6 = R.id.mins_5_radiobutton;
            ((RadioButton) _$_findCachedViewById(i6)).setEnabled(false);
            int i7 = R.id.mins_10_radiobutton;
            ((RadioButton) _$_findCachedViewById(i7)).setEnabled(false);
            int i8 = R.id.mins_15_radiobutton;
            ((RadioButton) _$_findCachedViewById(i8)).setEnabled(false);
            int i9 = R.id.mins_30_radiobutton;
            ((RadioButton) _$_findCachedViewById(i9)).setEnabled(false);
            int i10 = R.id.mins_60_radiobutton;
            ((RadioButton) _$_findCachedViewById(i10)).setEnabled(false);
            ((RadioButton) _$_findCachedViewById(i6)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i7)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i8)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i9)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i10)).setAlpha(0.35f);
        }
        B();
        ((RadioGroup) _$_findCachedViewById(R.id.radioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.k0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i11) {
                ActivityAutoHrSettings.E(ActivityAutoHrSettings.this, radioGroup, i11);
            }
        });
        int i11 = this.s;
        if (i11 == 5) {
            ((RadioButton) _$_findCachedViewById(R.id.mins_5_radiobutton)).setChecked(true);
        } else if (i11 == 10) {
            ((RadioButton) _$_findCachedViewById(R.id.mins_10_radiobutton)).setChecked(true);
        } else if (i11 == 15) {
            ((RadioButton) _$_findCachedViewById(R.id.mins_15_radiobutton)).setChecked(true);
        } else if (i11 == 30) {
            ((RadioButton) _$_findCachedViewById(R.id.mins_30_radiobutton)).setChecked(true);
        } else if (i11 != 60) {
            ((RadioButton) _$_findCachedViewById(R.id.mins_30_radiobutton)).setChecked(true);
        } else {
            ((RadioButton) _$_findCachedViewById(R.id.mins_60_radiobutton)).setChecked(true);
        }
        Button btnSave = (Button) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        ViewUtilsKt.setSafeOnClickListener(btnSave, new a());
        getBackArrow().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoHrSettings.F(ActivityAutoHrSettings.this, view);
            }
        });
        if (this.t && DeviceUtils.Companion.isMoyangDevice(this)) {
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).setVisibility(4);
        }
    }

    public final void I() {
        Drawable drawable = getResources().getDrawable(R.drawable.warning_image);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.warning_image)");
        String string = getString(R.string.warning_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.warning_message)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, "", true);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoHrSettings.J(BottomSheetDialogImageTitleMessage.this, view);
            }
        });
        bottomSheetDialogImageTitleMessage.show();
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

    public final void backPress() {
        if (this.t == ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).isChecked() && this.s == this.r) {
            super.onBackPressed();
        } else {
            z();
        }
    }

    public final int getAutoHrDefaultInterval() {
        return this.r;
    }

    public final int getAutoHrIntervalValue() {
        return this.s;
    }

    @NotNull
    public final AutoHrViewModel getAutoHrViewModel() {
        AutoHrViewModel autoHrViewModel = this.autoHrViewModel;
        if (autoHrViewModel != null) {
            return autoHrViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoHrViewModel");
        return null;
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

    public final boolean getBoolSaveVisible() {
        return this.q;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDialog() {
        return this.u;
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

    @NotNull
    public final TextView getTitleText() {
        TextView textView = this.titleText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titleText");
        return null;
    }

    public final void init() {
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        setSaveText((TextView) findViewById);
        View findViewById2 = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<TextView>(R.id.toolbar_title)");
        setTitleText((TextView) findViewById2);
        getSaveText().setText(getResources().getString(R.string.save_camel));
        int i = R.id.interval_layout;
        ((LinearLayout) _$_findCachedViewById(i)).setVisibility(0);
        if (SessionManager.getInstance(this).getDeviceType().equals(getString(R.string.j1810g_device))) {
            ((TextView) _$_findCachedViewById(R.id.autohr_desc)).setText(getString(R.string.track_feature2));
            getTitleText().setText(getResources().getString(R.string.auto_hr_tracker2));
            ((TextView) _$_findCachedViewById(R.id.autohr_switch_title)).setText(getString(R.string.auto_hr_temperature));
        } else if (DeviceUtils.Companion.isMatrixDevice(this)) {
            getTitleText().setText(getResources().getString(R.string.auto_hr_tracker));
            ((TextView) _$_findCachedViewById(R.id.autohr_switch_title)).setText(getString(R.string.auto_hr));
            ((TextView) _$_findCachedViewById(R.id.autohr_desc)).setText(getString(R.string.track_feature));
            ((LinearLayout) _$_findCachedViewById(i)).setVisibility(8);
        } else {
            getTitleText().setText(getResources().getString(R.string.auto_hr_tracker));
            ((TextView) _$_findCachedViewById(R.id.autohr_switch_title)).setText(getString(R.string.auto_hr));
            ((TextView) _$_findCachedViewById(R.id.autohr_desc)).setText(getString(R.string.track_feature));
        }
        getSaveText().setTextColor(getResources().getColor(R.color.colorPrimary));
        getSaveText().setVisibility(8);
        View findViewById3 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById<TextView>(R.id.toolbar_back_arrow)");
        setBackArrow((TextView) findViewById3);
        D();
    }

    public final boolean isAutoHrSwitchEnabled() {
        return this.t;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_hr_tracker);
        setAutoHrViewModel((AutoHrViewModel) ViewModelProviders.of(this).get(AutoHrViewModel.class));
        getAutoHrViewModel().setDialogListener(this);
        init();
        if (BleApiManager.getInstance(this) == null || BleApiManager.getInstance(this).getBleApi() == null || !BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        showProgress();
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).setOnCheckedChangeListener(null);
        BatterySaverModeHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoHrSettings$onCreate$1
            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
                ActivityAutoHrSettings.this.dismissProgress();
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                if (z) {
                    ((SwitchCompat) ActivityAutoHrSettings.this._$_findCachedViewById(R.id.switch_auto_hr)).setChecked(false);
                }
                ActivityAutoHrSettings.this.B();
                ActivityAutoHrSettings.this.dismissProgress();
            }
        });
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
        if (isFinishing()) {
            return;
        }
        B();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setAutoHrDefaultInterval(int i) {
        this.r = i;
    }

    public final void setAutoHrIntervalValue(int i) {
        this.s = i;
    }

    public final void setAutoHrSwitchEnabled(boolean z) {
        this.t = z;
    }

    public final void setAutoHrViewModel(@NotNull AutoHrViewModel autoHrViewModel) {
        Intrinsics.checkNotNullParameter(autoHrViewModel, "<set-?>");
        this.autoHrViewModel = autoHrViewModel;
    }

    public final void setBackArrow(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backArrow = textView;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.q = z;
    }

    public final void setConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.u = bottomSheetDialogTwoButtons;
    }

    public final void setSaveText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.saveText = textView;
    }

    public final void setTitleText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titleText = textView;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoHrSettings.G(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoHrSettings.H(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void x() {
        LogHelper.d(this.p, String.valueOf(this.r));
        if (!AppUtils.isNetConnected(this)) {
            showNoInternetMessage();
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (DeviceUtils.Companion.isMatrixDevice(this)) {
                this.r = 10;
            }
            getAutoHrViewModel().callSaveAndBleApi(this.r, ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_hr)).isChecked());
        } else {
            String string = getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
            String string2 = getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
            final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoHrSettings.y(BottomSheetDialogOneButtonTitleMessage.this, this, view);
                }
            });
            bottomSheetDialogOneButtonTitleMessage.show();
        }
    }

    public final void z() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        if (this.u == null) {
            String string = getString(R.string.auto_hr);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.auto_hr)");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = getString(R.string.save_changes_hr);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes_hr)");
            String format = String.format(string2, Arrays.copyOf(new Object[]{getString(R.string.auto_hr)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(this, string, format, false, 8, null);
            this.u = bottomSheetDialogTwoButtons2;
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoHrSettings$saveChangesDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ActivityAutoHrSettings.this.setBoolSaveVisible(false);
                    ActivityAutoHrSettings.this.x();
                    BottomSheetDialogTwoButtons confirmationDialog = ActivityAutoHrSettings.this.getConfirmationDialog();
                    if (confirmationDialog != null) {
                        confirmationDialog.dismiss();
                    }
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.u;
            if (bottomSheetDialogTwoButtons3 != null) {
                String string4 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoHrSettings$saveChangesDialog$2
                    @Override // android.view.View.OnClickListener
                    public void onClick(@Nullable View view) {
                        BottomSheetDialogTwoButtons confirmationDialog = ActivityAutoHrSettings.this.getConfirmationDialog();
                        if (confirmationDialog != null) {
                            confirmationDialog.dismiss();
                        }
                        ActivityAutoHrSettings.this.finish();
                    }
                });
            }
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.u;
        Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.u) == null) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }
}
