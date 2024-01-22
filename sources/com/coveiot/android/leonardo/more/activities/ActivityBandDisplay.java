package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.ActivityBandDisplayViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.covepreferences.UserDataManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBandDisplay extends BaseActivity {
    public ActivityBandDisplayViewModel mViewModel;
    @Nullable
    public int[] p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final List<String> q = new ArrayList();

    public static final void B(ActivityBandDisplay this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void C(ActivityBandDisplay this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void D(ActivityBandDisplay this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void E(ActivityBandDisplay this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void F(ActivityBandDisplay this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void G(ActivityBandDisplay this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void H(ActivityBandDisplay this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void y(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public final void A() {
        int[] displayModeData = UserDataManager.getInstance(this).getDisplayModeData();
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_heart_rate)).setChecked(displayModeData[2] == 1);
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_blood_pressure)).setChecked(displayModeData[3] == 1);
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_activity_mode)).setChecked(displayModeData[4] == 1);
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_phone_finder)).setChecked(displayModeData[5] == 1);
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_stress_level)).setChecked(displayModeData[7] == 1);
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_music)).setChecked(displayModeData[6] == 1);
    }

    public final void I() {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            z();
        } else {
            bandDisconnectDialog();
        }
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
        super.onBackPressed();
    }

    public final void bandDisconnectDialog() {
        String string = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandDisplay.y(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @NotNull
    public final ActivityBandDisplayViewModel getMViewModel() {
        ActivityBandDisplayViewModel activityBandDisplayViewModel = this.mViewModel;
        if (activityBandDisplayViewModel != null) {
            return activityBandDisplayViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        return null;
    }

    public final void initToolbar() {
        setMViewModel((ActivityBandDisplayViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityBandDisplayViewModel.class));
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.band_display));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandDisplay.B(ActivityBandDisplay.this, view);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_heart_rate)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.v2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityBandDisplay.C(ActivityBandDisplay.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_blood_pressure)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.w2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityBandDisplay.D(ActivityBandDisplay.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_activity_mode)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.a3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityBandDisplay.E(ActivityBandDisplay.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_phone_finder)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.x2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityBandDisplay.F(ActivityBandDisplay.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_stress_level)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.y2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityBandDisplay.G(ActivityBandDisplay.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_music)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.z2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityBandDisplay.H(ActivityBandDisplay.this, compoundButton, z);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_band_display);
        initToolbar();
        A();
    }

    public final void setMViewModel(@NotNull ActivityBandDisplayViewModel activityBandDisplayViewModel) {
        Intrinsics.checkNotNullParameter(activityBandDisplayViewModel, "<set-?>");
        this.mViewModel = activityBandDisplayViewModel;
    }

    public final void z() {
        int i = R.id.switch_heart_rate;
        boolean isChecked = ((SwitchCompat) _$_findCachedViewById(i)).isChecked();
        int i2 = R.id.switch_blood_pressure;
        boolean isChecked2 = ((SwitchCompat) _$_findCachedViewById(i2)).isChecked();
        int i3 = R.id.switch_activity_mode;
        boolean isChecked3 = ((SwitchCompat) _$_findCachedViewById(i3)).isChecked();
        int i4 = R.id.switch_phone_finder;
        boolean isChecked4 = ((SwitchCompat) _$_findCachedViewById(i4)).isChecked();
        int i5 = R.id.switch_stress_level;
        boolean isChecked5 = ((SwitchCompat) _$_findCachedViewById(i5)).isChecked();
        int i6 = R.id.switch_music;
        this.p = new int[]{1, 1, isChecked ? 1 : 0, isChecked2 ? 1 : 0, isChecked3 ? 1 : 0, isChecked5 ? 1 : 0, ((SwitchCompat) _$_findCachedViewById(i6)).isChecked() ? 1 : 0, isChecked4 ? 1 : 0};
        this.q.clear();
        if (((SwitchCompat) _$_findCachedViewById(i)).isChecked()) {
            List<String> list = this.q;
            String string = getResources().getString(R.string.dis_heartrate);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.dis_heartrate)");
            list.add(string);
        }
        if (((SwitchCompat) _$_findCachedViewById(i2)).isChecked()) {
            List<String> list2 = this.q;
            String string2 = getResources().getString(R.string.dis_bp);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.dis_bp)");
            list2.add(string2);
        }
        if (((SwitchCompat) _$_findCachedViewById(i3)).isChecked()) {
            List<String> list3 = this.q;
            String string3 = getResources().getString(R.string.dis_activity_mode);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.dis_activity_mode)");
            list3.add(string3);
        }
        if (((SwitchCompat) _$_findCachedViewById(i4)).isChecked()) {
            List<String> list4 = this.q;
            String string4 = getResources().getString(R.string.dis_phone_finder);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.dis_phone_finder)");
            list4.add(string4);
        }
        if (((SwitchCompat) _$_findCachedViewById(i5)).isChecked()) {
            List<String> list5 = this.q;
            String string5 = getResources().getString(R.string.dis_stress_level);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.dis_stress_level)");
            list5.add(string5);
        }
        if (((SwitchCompat) _$_findCachedViewById(i6)).isChecked()) {
            List<String> list6 = this.q;
            String string6 = getResources().getString(R.string.dis_music);
            Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.dis_music)");
            list6.add(string6);
        }
        getMViewModel().saveBandDisplay(this.p, this.q);
    }
}
