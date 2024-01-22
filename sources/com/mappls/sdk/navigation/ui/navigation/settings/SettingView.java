package com.mappls.sdk.navigation.ui.navigation.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutSettingsViewBinding;
@Keep
/* loaded from: classes11.dex */
public class SettingView extends CoordinatorLayout {
    public LayoutSettingsViewBinding binding;
    private BottomSheetBehavior mBottomSheetBehavior;
    private com.mappls.sdk.navigation.ui.navigation.settings.a settingsCallback;

    /* loaded from: classes11.dex */
    public class a implements CompoundButton.OnCheckedChangeListener {
        public a(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().setPlayAsVoiceCall(z);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().setNavigationEventEnabled(z);
            SettingView.this.setNavigationEvent(z);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements CompoundButton.OnCheckedChangeListener {
        public c(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().showSafetyEvents(z);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements CompoundButton.OnCheckedChangeListener {
        public d(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().showTrafficEvents(z);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements CompoundButton.OnCheckedChangeListener {
        public e(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().showRoadConditionsEvents(z);
        }
    }

    /* loaded from: classes11.dex */
    public class f implements CompoundButton.OnCheckedChangeListener {
        public f(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().speakSafetyEvents(z);
        }
    }

    /* loaded from: classes11.dex */
    public class g implements CompoundButton.OnCheckedChangeListener {
        public g(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().speakTrafficEvents(z);
        }
    }

    /* loaded from: classes11.dex */
    public class h implements CompoundButton.OnCheckedChangeListener {
        public h(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().speakRoadConditionsEvents(z);
        }
    }

    /* loaded from: classes11.dex */
    public class i implements CompoundButton.OnCheckedChangeListener {
        public i(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().setInterruptMusicForNavigationInstructions(z);
        }
    }

    /* loaded from: classes11.dex */
    public class j implements CompoundButton.OnCheckedChangeListener {
        public j() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().setNavigationEventAudioPromptEnabled(z);
            SettingView.this.setNavigationEventAudio(z);
        }
    }

    /* loaded from: classes11.dex */
    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SettingView.this.mBottomSheetBehavior != null) {
                SettingView.this.mBottomSheetBehavior.setState(5);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SettingView.this.settingsCallback != null) {
                com.mappls.sdk.navigation.ui.navigation.settings.a aVar = SettingView.this.settingsCallback;
                com.mappls.sdk.navigation.ui.utils.a aVar2 = com.mappls.sdk.navigation.ui.utils.a.b;
                aVar.a(aVar2);
                SettingView.this.setMapLayer(aVar2);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SettingView.this.settingsCallback != null) {
                com.mappls.sdk.navigation.ui.navigation.settings.a aVar = SettingView.this.settingsCallback;
                com.mappls.sdk.navigation.ui.utils.a aVar2 = com.mappls.sdk.navigation.ui.utils.a.c;
                aVar.a(aVar2);
                SettingView.this.setMapLayer(aVar2);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class n implements View.OnClickListener {
        public n() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SettingView.this.settingsCallback != null) {
                com.mappls.sdk.navigation.ui.navigation.settings.a aVar = SettingView.this.settingsCallback;
                com.mappls.sdk.navigation.ui.utils.a aVar2 = com.mappls.sdk.navigation.ui.utils.a.f13030a;
                aVar.a(aVar2);
                SettingView.this.setMapLayer(aVar2);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class o implements SeekBar.OnSeekBarChangeListener {
        public o() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (SettingView.this.settingsCallback != null) {
                SettingView.this.settingsCallback.a(seekBar.getProgress());
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /* loaded from: classes11.dex */
    public class p implements View.OnClickListener {
        public p() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MapplsNavigationHelper.getInstance().setMetricSystemToKM(false);
            SettingView.this.updateUnitView();
        }
    }

    /* loaded from: classes11.dex */
    public class q implements View.OnClickListener {
        public q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MapplsNavigationHelper.getInstance().setMetricSystemToKM(true);
            SettingView.this.updateUnitView();
        }
    }

    /* loaded from: classes11.dex */
    public class r implements CompoundButton.OnCheckedChangeListener {
        public r(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().setPlayDuringPhoneCallEnabled(z);
        }
    }

    /* loaded from: classes11.dex */
    public class s implements CompoundButton.OnCheckedChangeListener {
        public s(SettingView settingView) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MapplsNavigationHelper.getInstance().setJunctionViewEnabled(z);
        }
    }

    public SettingView(@NonNull Context context) {
        this(context, null);
    }

    public SettingView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SettingView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.binding = LayoutSettingsViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    private void initView() {
        isMute(MapplsNavigationHelper.getInstance().isMute());
        this.binding.swPlayDuringPhoneCall.setChecked(MapplsNavigationHelper.getInstance().isPlayDuringPhoneCallEnabled());
        this.binding.swSafetyEventAudio.setChecked(MapplsNavigationHelper.getInstance().speakSafetyEvents());
        this.binding.swTrafficEventAudio.setChecked(MapplsNavigationHelper.getInstance().speakTrafficEvents());
        this.binding.swRoadCondEventAudio.setChecked(MapplsNavigationHelper.getInstance().speakRoadConditionsEvents());
        this.binding.swSafetyEvent.setChecked(MapplsNavigationHelper.getInstance().showSafetyEvents());
        this.binding.swTrafficEvent.setChecked(MapplsNavigationHelper.getInstance().showTrafficEvents());
        this.binding.swRoadCondEvent.setChecked(MapplsNavigationHelper.getInstance().showRoadConditionsEvents());
        this.binding.swNavigationEvent.setChecked(MapplsNavigationHelper.getInstance().isNavigationEventEnabled());
        this.binding.swPlayVoiceCall.setChecked(MapplsNavigationHelper.getInstance().isPlayAsVoiceCall());
        this.binding.swJunctionView.setChecked(MapplsNavigationHelper.getInstance().isJunctionViewEnabled());
        this.binding.swInterruptMusic.setChecked(MapplsNavigationHelper.getInstance().isInterruptMusicForNavigationInstructions());
        this.binding.swNavigationEventAudio.setChecked(MapplsNavigationHelper.getInstance().isNavigationEventAudioPromptEnabled());
        updateUnitView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNavigationEvent(boolean z) {
        this.binding.swSafetyEvent.setEnabled(z);
        this.binding.swTrafficEvent.setEnabled(z);
        this.binding.swRoadCondEvent.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNavigationEventAudio(boolean z) {
        this.binding.swRoadCondEventAudio.setEnabled(z);
        this.binding.swTrafficEventAudio.setEnabled(z);
        this.binding.swSafetyEventAudio.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUnitView() {
        this.binding.tvKmUnit.setSelected(MapplsNavigationHelper.getInstance().isCurrentMetricSystemKm());
        this.binding.tvMileUnit.setSelected(!MapplsNavigationHelper.getInstance().isCurrentMetricSystemKm());
    }

    public void hide() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(5);
        }
    }

    public void isMute(boolean z) {
        this.binding.volumeSeekBar.setEnabled(!z);
    }

    public boolean isVisible() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        return bottomSheetBehavior != null && bottomSheetBehavior.getState() == 3;
    }

    public void maxVolume(int i2) {
        this.binding.volumeSeekBar.setMax(i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setClickable(false);
        setFocusableInTouchMode(false);
        toggleTheme();
        initView();
        BottomSheetBehavior from = BottomSheetBehavior.from(this.binding.settingView);
        this.mBottomSheetBehavior = from;
        from.setHideable(true);
        this.mBottomSheetBehavior.setSkipCollapsed(true);
        this.mBottomSheetBehavior.setPeekHeight(0);
        this.mBottomSheetBehavior.setState(5);
        this.binding.closeBottomSheet.setOnClickListener(new k());
        this.binding.tvTwoD.setOnClickListener(new l());
        this.binding.tvThreeD.setOnClickListener(new m());
        this.binding.resetMapType.setOnClickListener(new n());
        this.binding.volumeSeekBar.setOnSeekBarChangeListener(new o());
        this.binding.tvMileUnit.setOnClickListener(new p());
        this.binding.tvKmUnit.setOnClickListener(new q());
        this.binding.swPlayDuringPhoneCall.setOnCheckedChangeListener(new r(this));
        this.binding.swJunctionView.setOnCheckedChangeListener(new s(this));
        this.binding.swPlayVoiceCall.setOnCheckedChangeListener(new a(this));
        this.binding.swNavigationEvent.setOnCheckedChangeListener(new b());
        this.binding.swSafetyEvent.setOnCheckedChangeListener(new c(this));
        this.binding.swTrafficEvent.setOnCheckedChangeListener(new d(this));
        this.binding.swRoadCondEvent.setOnCheckedChangeListener(new e(this));
        this.binding.swSafetyEventAudio.setOnCheckedChangeListener(new f(this));
        this.binding.swTrafficEventAudio.setOnCheckedChangeListener(new g(this));
        this.binding.swRoadCondEventAudio.setOnCheckedChangeListener(new h(this));
        this.binding.swInterruptMusic.setOnCheckedChangeListener(new i(this));
        this.binding.swNavigationEventAudio.setOnCheckedChangeListener(new j());
    }

    public boolean onBackPress() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior == null || bottomSheetBehavior.getState() != 3) {
            return false;
        }
        this.mBottomSheetBehavior.setState(5);
        return true;
    }

    public void setMapLayer(com.mappls.sdk.navigation.ui.utils.a aVar) {
        TextView textView;
        if (aVar == com.mappls.sdk.navigation.ui.utils.a.f13030a) {
            this.binding.tvTwoD.setSelected(false);
        } else if (aVar != com.mappls.sdk.navigation.ui.utils.a.b) {
            if (aVar == com.mappls.sdk.navigation.ui.utils.a.c) {
                this.binding.tvThreeD.setSelected(true);
                textView = this.binding.tvTwoD;
                textView.setSelected(false);
            }
            return;
        } else {
            this.binding.tvTwoD.setSelected(true);
        }
        textView = this.binding.tvThreeD;
        textView.setSelected(false);
    }

    public void setSettingsCallback(com.mappls.sdk.navigation.ui.navigation.settings.a aVar) {
        this.settingsCallback = aVar;
    }

    public void setVolume(int i2) {
        this.binding.volumeSeekBar.setProgress(i2);
    }

    public void show() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(3);
        }
    }

    public void toggleTheme() {
        TextView textView = this.binding.tvTwoD;
        Context context = getContext();
        int i2 = R.attr.navigationViewTwoDThreeDTextColor;
        textView.setTextColor(com.mappls.sdk.navigation.ui.theme.a.c(context, i2));
        this.binding.tvThreeD.setTextColor(com.mappls.sdk.navigation.ui.theme.a.c(getContext(), i2));
        TextView textView2 = this.binding.tvKmUnit;
        Context context2 = getContext();
        int i3 = R.attr.navigationViewKmMileUnitTextColor;
        textView2.setTextColor(com.mappls.sdk.navigation.ui.theme.a.c(context2, i3));
        this.binding.tvMileUnit.setTextColor(com.mappls.sdk.navigation.ui.theme.a.c(getContext(), i3));
        RelativeLayout relativeLayout = this.binding.settingView;
        Context context3 = getContext();
        int i4 = R.attr.navigationViewPrimary;
        relativeLayout.setBackgroundColor(com.mappls.sdk.navigation.ui.theme.a.b(context3, i4));
        this.binding.toolbarSetting.setCardBackgroundColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i4));
        TextView textView3 = this.binding.toolbarText;
        Context context4 = getContext();
        int i5 = R.attr.navigationTextColorPrimary;
        textView3.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(context4, i5));
        this.binding.closeBottomSheet.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewBackButtonDrawable));
        this.binding.tvMapType.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvUnitType.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvSoundVolume.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvEnableInstructionDuringPhoneCall.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvJunctionView.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvPlayVoiceCall.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvNavigationEvent.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvTrafficEvent.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvRoadCondEvent.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvSafetyEvent.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvSafetyEventAudio.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvTrafficEventAudio.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvRoadCondEventAudio.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvInterruptMusic.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvNavigationEventAudio.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvMapSetting.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvNavSetting.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
        this.binding.tvNavVoiceSetting.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i5));
    }
}
