package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
/* loaded from: classes3.dex */
public abstract class AlarmListRowNewBinding extends ViewDataBinding {
    @NonNull
    public final TextView alarmAmPm;
    @NonNull
    public final ImageView alarmImage;
    @NonNull
    public final EditText alarmName;
    @NonNull
    public final TextView alarmRepeat;
    @NonNull
    public final LinearLayout alarmSegment;
    @NonNull
    public final Switch alarmSwitch;
    @NonNull
    public final TextView alarmTime;
    @NonNull
    public final ConstraintLayout containerLayout;
    @NonNull
    public final View divider;
    @NonNull
    public final ImageView expandCollapseArrow;
    @NonNull
    public final TextView friday;
    @Bindable
    public AlarmInfo mAlarmInfo;
    @Bindable
    public AlarmListAdapterNew.AlarmEventHandler mListener;
    @Bindable
    public Boolean mShouldEnableClick;
    @Bindable
    public Boolean mShouldExpandAlarm;
    @NonNull
    public final TextView monday;
    @NonNull
    public final TextView repeatLabel;
    @NonNull
    public final ConstraintLayout repeatLayout;
    @NonNull
    public final LinearLayout repeatSelectionLayout;
    @NonNull
    public final TextView saturday;
    @NonNull
    public final Button saveAlarmBtn;
    @NonNull
    public final TextView sunday;
    @NonNull
    public final TextView thursday;
    @NonNull
    public final TextView tuesday;
    @NonNull
    public final TextView wednesday;

    public AlarmListRowNewBinding(Object obj, View view, int i, TextView textView, ImageView imageView, EditText editText, TextView textView2, LinearLayout linearLayout, Switch r11, TextView textView3, ConstraintLayout constraintLayout, View view2, ImageView imageView2, TextView textView4, TextView textView5, TextView textView6, ConstraintLayout constraintLayout2, LinearLayout linearLayout2, TextView textView7, Button button, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        super(obj, view, i);
        this.alarmAmPm = textView;
        this.alarmImage = imageView;
        this.alarmName = editText;
        this.alarmRepeat = textView2;
        this.alarmSegment = linearLayout;
        this.alarmSwitch = r11;
        this.alarmTime = textView3;
        this.containerLayout = constraintLayout;
        this.divider = view2;
        this.expandCollapseArrow = imageView2;
        this.friday = textView4;
        this.monday = textView5;
        this.repeatLabel = textView6;
        this.repeatLayout = constraintLayout2;
        this.repeatSelectionLayout = linearLayout2;
        this.saturday = textView7;
        this.saveAlarmBtn = button;
        this.sunday = textView8;
        this.thursday = textView9;
        this.tuesday = textView10;
        this.wednesday = textView11;
    }

    public static AlarmListRowNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlarmListRowNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public AlarmInfo getAlarmInfo() {
        return this.mAlarmInfo;
    }

    @Nullable
    public AlarmListAdapterNew.AlarmEventHandler getListener() {
        return this.mListener;
    }

    @Nullable
    public Boolean getShouldEnableClick() {
        return this.mShouldEnableClick;
    }

    @Nullable
    public Boolean getShouldExpandAlarm() {
        return this.mShouldExpandAlarm;
    }

    public abstract void setAlarmInfo(@Nullable AlarmInfo alarmInfo);

    public abstract void setListener(@Nullable AlarmListAdapterNew.AlarmEventHandler alarmEventHandler);

    public abstract void setShouldEnableClick(@Nullable Boolean bool);

    public abstract void setShouldExpandAlarm(@Nullable Boolean bool);

    @Deprecated
    public static AlarmListRowNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AlarmListRowNewBinding) ViewDataBinding.bind(obj, view, R.layout.alarm_list_row_new);
    }

    @NonNull
    @Deprecated
    public static AlarmListRowNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AlarmListRowNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alarm_list_row_new, viewGroup, z, obj);
    }

    @NonNull
    public static AlarmListRowNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AlarmListRowNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AlarmListRowNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alarm_list_row_new, null, false, obj);
    }
}
