package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes2.dex */
public abstract class ActivityAutoRecogSettingsWithOnekBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final CheckBox cb12pm4pm;
    @NonNull
    public final CheckBox cb4pm9pm;
    @NonNull
    public final CheckBox cb7am12pm;
    @NonNull
    public final CheckBox cb9pm7am;
    @NonNull
    public final LinearLayout cvSmartMode;
    @NonNull
    public final RepeatLayoutNewBinding repeatLayout;
    @NonNull
    public final SwitchCompat swSmartMode;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvSmartMode;
    @NonNull
    public final TextView tvSmartModeDesc;

    public ActivityAutoRecogSettingsWithOnekBinding(Object obj, View view, int i, Button button, CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, LinearLayout linearLayout, RepeatLayoutNewBinding repeatLayoutNewBinding, SwitchCompat switchCompat, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnSave = button;
        this.cb12pm4pm = checkBox;
        this.cb4pm9pm = checkBox2;
        this.cb7am12pm = checkBox3;
        this.cb9pm7am = checkBox4;
        this.cvSmartMode = linearLayout;
        this.repeatLayout = repeatLayoutNewBinding;
        this.swSmartMode = switchCompat;
        this.toolbar = view2;
        this.tvSmartMode = textView;
        this.tvSmartModeDesc = textView2;
    }

    public static ActivityAutoRecogSettingsWithOnekBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAutoRecogSettingsWithOnekBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAutoRecogSettingsWithOnekBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAutoRecogSettingsWithOnekBinding) ViewDataBinding.bind(obj, view, R.layout.activity_auto_recog_settings_with_onek);
    }

    @NonNull
    @Deprecated
    public static ActivityAutoRecogSettingsWithOnekBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAutoRecogSettingsWithOnekBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_auto_recog_settings_with_onek, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAutoRecogSettingsWithOnekBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAutoRecogSettingsWithOnekBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAutoRecogSettingsWithOnekBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_auto_recog_settings_with_onek, null, false, obj);
    }
}
