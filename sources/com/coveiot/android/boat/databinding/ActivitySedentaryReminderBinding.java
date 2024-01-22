package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.ReminderLayoutNewBinding;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes3.dex */
public abstract class ActivitySedentaryReminderBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final ConstraintLayout clReminder;
    @NonNull
    public final ConstraintLayout clRepeatLayout;
    @Bindable
    public Boolean mShowRepeatLayout;
    @NonNull
    public final ReminderLayoutNewBinding reminderLayout;
    @NonNull
    public final RepeatLayoutNewBinding repeatLayout;
    @NonNull
    public final SwitchCompat switchReminder;
    @NonNull
    public final View toolbar;

    public ActivitySedentaryReminderBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ReminderLayoutNewBinding reminderLayoutNewBinding, RepeatLayoutNewBinding repeatLayoutNewBinding, SwitchCompat switchCompat, View view2) {
        super(obj, view, i);
        this.btnSave = button;
        this.clReminder = constraintLayout;
        this.clRepeatLayout = constraintLayout2;
        this.reminderLayout = reminderLayoutNewBinding;
        this.repeatLayout = repeatLayoutNewBinding;
        this.switchReminder = switchCompat;
        this.toolbar = view2;
    }

    public static ActivitySedentaryReminderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySedentaryReminderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getShowRepeatLayout() {
        return this.mShowRepeatLayout;
    }

    public abstract void setShowRepeatLayout(@Nullable Boolean bool);

    @Deprecated
    public static ActivitySedentaryReminderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySedentaryReminderBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sedentary_reminder);
    }

    @NonNull
    @Deprecated
    public static ActivitySedentaryReminderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySedentaryReminderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sedentary_reminder, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySedentaryReminderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySedentaryReminderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySedentaryReminderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sedentary_reminder, null, false, obj);
    }
}
