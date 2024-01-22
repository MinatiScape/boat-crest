package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.ReminderLayoutNewBinding;
/* loaded from: classes3.dex */
public abstract class ActivityDrinkReminderBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final ConstraintLayout clReminder;
    @NonNull
    public final ReminderLayoutNewBinding reminderLayout;
    @NonNull
    public final SwitchCompat switchReminder;
    @NonNull
    public final View toolbar;

    public ActivityDrinkReminderBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ReminderLayoutNewBinding reminderLayoutNewBinding, SwitchCompat switchCompat, View view2) {
        super(obj, view, i);
        this.btnSave = button;
        this.clReminder = constraintLayout;
        this.reminderLayout = reminderLayoutNewBinding;
        this.switchReminder = switchCompat;
        this.toolbar = view2;
    }

    public static ActivityDrinkReminderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDrinkReminderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDrinkReminderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityDrinkReminderBinding) ViewDataBinding.bind(obj, view, R.layout.activity_drink_reminder);
    }

    @NonNull
    @Deprecated
    public static ActivityDrinkReminderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityDrinkReminderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_drink_reminder, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityDrinkReminderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityDrinkReminderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityDrinkReminderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_drink_reminder, null, false, obj);
    }
}
