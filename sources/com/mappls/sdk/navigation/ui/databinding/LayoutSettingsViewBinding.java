package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutSettingsViewBinding extends ViewDataBinding {
    @NonNull
    public final ImageView closeBottomSheet;
    @NonNull
    public final TextView resetMapType;
    @NonNull
    public final RelativeLayout settingView;
    @NonNull
    public final Switch swInterruptMusic;
    @NonNull
    public final Switch swJunctionView;
    @NonNull
    public final Switch swNavigationEvent;
    @NonNull
    public final Switch swNavigationEventAudio;
    @NonNull
    public final Switch swPlayDuringPhoneCall;
    @NonNull
    public final SwitchCompat swPlayVoiceCall;
    @NonNull
    public final Switch swRoadCondEvent;
    @NonNull
    public final SwitchCompat swRoadCondEventAudio;
    @NonNull
    public final Switch swSafetyEvent;
    @NonNull
    public final Switch swSafetyEventAudio;
    @NonNull
    public final Switch swTrafficEvent;
    @NonNull
    public final SwitchCompat swTrafficEventAudio;
    @NonNull
    public final CardView toolbarSetting;
    @NonNull
    public final TextView toolbarText;
    @NonNull
    public final TextView tvEnableInstructionDuringPhoneCall;
    @NonNull
    public final TextView tvInterruptMusic;
    @NonNull
    public final TextView tvJunctionView;
    @NonNull
    public final TextView tvKmUnit;
    @NonNull
    public final TextView tvMapSetting;
    @NonNull
    public final TextView tvMapType;
    @NonNull
    public final TextView tvMileUnit;
    @NonNull
    public final TextView tvNavSetting;
    @NonNull
    public final TextView tvNavVoiceSetting;
    @NonNull
    public final TextView tvNavigationEvent;
    @NonNull
    public final TextView tvNavigationEventAudio;
    @NonNull
    public final TextView tvPlayVoiceCall;
    @NonNull
    public final TextView tvRoadCondEvent;
    @NonNull
    public final TextView tvRoadCondEventAudio;
    @NonNull
    public final TextView tvSafetyEvent;
    @NonNull
    public final TextView tvSafetyEventAudio;
    @NonNull
    public final TextView tvSoundVolume;
    @NonNull
    public final TextView tvThreeD;
    @NonNull
    public final TextView tvTrafficEvent;
    @NonNull
    public final TextView tvTrafficEventAudio;
    @NonNull
    public final TextView tvTwoD;
    @NonNull
    public final TextView tvUnitType;
    @NonNull
    public final SeekBar volumeSeekBar;

    public LayoutSettingsViewBinding(Object obj, View view, int i, ImageView imageView, TextView textView, RelativeLayout relativeLayout, Switch r9, Switch r10, Switch r11, Switch r12, Switch r13, SwitchCompat switchCompat, Switch r15, SwitchCompat switchCompat2, Switch r17, Switch r18, Switch r19, SwitchCompat switchCompat3, CardView cardView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, SeekBar seekBar) {
        super(obj, view, i);
        this.closeBottomSheet = imageView;
        this.resetMapType = textView;
        this.settingView = relativeLayout;
        this.swInterruptMusic = r9;
        this.swJunctionView = r10;
        this.swNavigationEvent = r11;
        this.swNavigationEventAudio = r12;
        this.swPlayDuringPhoneCall = r13;
        this.swPlayVoiceCall = switchCompat;
        this.swRoadCondEvent = r15;
        this.swRoadCondEventAudio = switchCompat2;
        this.swSafetyEvent = r17;
        this.swSafetyEventAudio = r18;
        this.swTrafficEvent = r19;
        this.swTrafficEventAudio = switchCompat3;
        this.toolbarSetting = cardView;
        this.toolbarText = textView2;
        this.tvEnableInstructionDuringPhoneCall = textView3;
        this.tvInterruptMusic = textView4;
        this.tvJunctionView = textView5;
        this.tvKmUnit = textView6;
        this.tvMapSetting = textView7;
        this.tvMapType = textView8;
        this.tvMileUnit = textView9;
        this.tvNavSetting = textView10;
        this.tvNavVoiceSetting = textView11;
        this.tvNavigationEvent = textView12;
        this.tvNavigationEventAudio = textView13;
        this.tvPlayVoiceCall = textView14;
        this.tvRoadCondEvent = textView15;
        this.tvRoadCondEventAudio = textView16;
        this.tvSafetyEvent = textView17;
        this.tvSafetyEventAudio = textView18;
        this.tvSoundVolume = textView19;
        this.tvThreeD = textView20;
        this.tvTrafficEvent = textView21;
        this.tvTrafficEventAudio = textView22;
        this.tvTwoD = textView23;
        this.tvUnitType = textView24;
        this.volumeSeekBar = seekBar;
    }

    public static LayoutSettingsViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSettingsViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutSettingsViewBinding) ViewDataBinding.bind(obj, view, R.layout.layout_settings_view);
    }

    @NonNull
    public static LayoutSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutSettingsViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_settings_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutSettingsViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_settings_view, null, false, obj);
    }
}
