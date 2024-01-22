package com.mappls.sdk.navigation.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public class LayoutSettingsViewBindingImpl extends LayoutSettingsViewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.setting_view, 1);
        sparseIntArray.put(R.id.toolbar_setting, 2);
        sparseIntArray.put(R.id.close_bottom_sheet, 3);
        sparseIntArray.put(R.id.toolbar_text, 4);
        sparseIntArray.put(R.id.tvMapSetting, 5);
        sparseIntArray.put(R.id.tvMapType, 6);
        sparseIntArray.put(R.id.tv_two_d, 7);
        sparseIntArray.put(R.id.tv_three_d, 8);
        sparseIntArray.put(R.id.reset_map_type, 9);
        sparseIntArray.put(R.id.tv_nav_setting, 10);
        sparseIntArray.put(R.id.tvUnitType, 11);
        sparseIntArray.put(R.id.tv_km_unit, 12);
        sparseIntArray.put(R.id.tv_mile_unit, 13);
        sparseIntArray.put(R.id.tv_junction_view, 14);
        sparseIntArray.put(R.id.sw_junction_view, 15);
        sparseIntArray.put(R.id.tv_navigation_event, 16);
        sparseIntArray.put(R.id.sw_navigation_event, 17);
        sparseIntArray.put(R.id.tv_safety_event, 18);
        sparseIntArray.put(R.id.sw_safety_event, 19);
        sparseIntArray.put(R.id.tv_traffic_event, 20);
        sparseIntArray.put(R.id.sw_traffic_event, 21);
        sparseIntArray.put(R.id.tv_road_cond_event, 22);
        sparseIntArray.put(R.id.sw_road_cond_event, 23);
        sparseIntArray.put(R.id.tv_nav_voice_setting, 24);
        sparseIntArray.put(R.id.tvSoundVolume, 25);
        sparseIntArray.put(R.id.volume_seek_bar, 26);
        sparseIntArray.put(R.id.tv_enable_instruction_during_phone_call, 27);
        sparseIntArray.put(R.id.sw_play_during_phone_call, 28);
        sparseIntArray.put(R.id.tv_play_voice_call, 29);
        sparseIntArray.put(R.id.sw_play_voice_call, 30);
        sparseIntArray.put(R.id.tv_navigation_event_audio, 31);
        sparseIntArray.put(R.id.sw_navigation_event_audio, 32);
        sparseIntArray.put(R.id.tv_safety_event_audio, 33);
        sparseIntArray.put(R.id.sw_safety_event_audio, 34);
        sparseIntArray.put(R.id.tv_traffic_event_audio, 35);
        sparseIntArray.put(R.id.sw_traffic_event_audio, 36);
        sparseIntArray.put(R.id.tv_road_cond_event_audio, 37);
        sparseIntArray.put(R.id.sw_road_cond_event_audio, 38);
        sparseIntArray.put(R.id.tv_interrupt_music, 39);
        sparseIntArray.put(R.id.sw_interrupt_music, 40);
    }

    public LayoutSettingsViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 41, i, j));
    }

    public LayoutSettingsViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[3], (TextView) objArr[9], (RelativeLayout) objArr[1], (Switch) objArr[40], (Switch) objArr[15], (Switch) objArr[17], (Switch) objArr[32], (Switch) objArr[28], (SwitchCompat) objArr[30], (Switch) objArr[23], (SwitchCompat) objArr[38], (Switch) objArr[19], (Switch) objArr[34], (Switch) objArr[21], (SwitchCompat) objArr[36], (CardView) objArr[2], (TextView) objArr[4], (TextView) objArr[27], (TextView) objArr[39], (TextView) objArr[14], (TextView) objArr[12], (TextView) objArr[5], (TextView) objArr[6], (TextView) objArr[13], (TextView) objArr[10], (TextView) objArr[24], (TextView) objArr[16], (TextView) objArr[31], (TextView) objArr[29], (TextView) objArr[22], (TextView) objArr[37], (TextView) objArr[18], (TextView) objArr[33], (TextView) objArr[25], (TextView) objArr[8], (TextView) objArr[20], (TextView) objArr[35], (TextView) objArr[7], (TextView) objArr[11], (SeekBar) objArr[26]);
        this.h = -1L;
        ((CoordinatorLayout) objArr[0]).setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }
}
