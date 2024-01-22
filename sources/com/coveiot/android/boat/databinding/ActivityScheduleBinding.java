package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityScheduleBinding extends ViewDataBinding {
    @NonNull
    public final TextView btnAddScheduleReminder;
    @NonNull
    public final TextView btnSave;
    @NonNull
    public final Button btnSetScheduleReminder;
    @NonNull
    public final ConstraintLayout clAddReminder;
    @NonNull
    public final ConstraintLayout clBottomButtons;
    @NonNull
    public final ConstraintLayout clReminders;
    @NonNull
    public final ConstraintLayout clSpinner;
    @NonNull
    public final TextView dateTime;
    @NonNull
    public final ConstraintLayout emptyCl;
    @NonNull
    public final EditText etDescription;
    @NonNull
    public final EditText etScheduleDateTime;
    @NonNull
    public final EditText etTitle;
    @NonNull
    public final ImageView ivDropDown;
    @NonNull
    public final ImageView noScheduleImgV;
    @NonNull
    public final TextView noScheduleTv;
    @NonNull
    public final TextView remind;
    @NonNull
    public final RecyclerView rvScheduleList;
    @NonNull
    public final View scheduleDateTimeView;
    @NonNull
    public final Spinner scheduleReminderSpinner;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvDescCharacterCount;
    @NonNull
    public final TextView tvDescription;
    @NonNull
    public final TextView tvEventName;
    @NonNull
    public final TextView tvTitleCharacterCount;

    public ActivityScheduleBinding(Object obj, View view, int i, TextView textView, TextView textView2, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, TextView textView3, ConstraintLayout constraintLayout5, EditText editText, EditText editText2, EditText editText3, ImageView imageView, ImageView imageView2, TextView textView4, TextView textView5, RecyclerView recyclerView, View view2, Spinner spinner, View view3, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        super(obj, view, i);
        this.btnAddScheduleReminder = textView;
        this.btnSave = textView2;
        this.btnSetScheduleReminder = button;
        this.clAddReminder = constraintLayout;
        this.clBottomButtons = constraintLayout2;
        this.clReminders = constraintLayout3;
        this.clSpinner = constraintLayout4;
        this.dateTime = textView3;
        this.emptyCl = constraintLayout5;
        this.etDescription = editText;
        this.etScheduleDateTime = editText2;
        this.etTitle = editText3;
        this.ivDropDown = imageView;
        this.noScheduleImgV = imageView2;
        this.noScheduleTv = textView4;
        this.remind = textView5;
        this.rvScheduleList = recyclerView;
        this.scheduleDateTimeView = view2;
        this.scheduleReminderSpinner = spinner;
        this.toolbar = view3;
        this.tvDescCharacterCount = textView6;
        this.tvDescription = textView7;
        this.tvEventName = textView8;
        this.tvTitleCharacterCount = textView9;
    }

    public static ActivityScheduleBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityScheduleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityScheduleBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityScheduleBinding) ViewDataBinding.bind(obj, view, R.layout.activity_schedule);
    }

    @NonNull
    @Deprecated
    public static ActivityScheduleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityScheduleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_schedule, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityScheduleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityScheduleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityScheduleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_schedule, null, false, obj);
    }
}
