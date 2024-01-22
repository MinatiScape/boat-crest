package com.coveiot.android.customreminders.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes3.dex */
public abstract class MedicineReminderItemBinding extends ViewDataBinding {
    @NonNull
    public final TextView addTimeInfo;
    @NonNull
    public final ConstraintLayout clEndTime;
    @NonNull
    public final ConstraintLayout clStartTime;
    @NonNull
    public final CardView cvMedicine;
    @NonNull
    public final Button doneButton;
    @NonNull
    public final EditText edtReminderName;
    @NonNull
    public final TextView endTimeTitle;
    @Bindable
    public Boolean mIsInEditMode;
    @NonNull
    public final RecyclerView rcvTimeInfos;
    @NonNull
    public final LinearLayout reminderLayout;
    @NonNull
    public final TextView reminderTimeLabel;
    @NonNull
    public final RepeatLayoutNewBinding repeatLayout;
    @NonNull
    public final Spinner spNotifyMe;
    @NonNull
    public final TextView startTimeTitle;
    @NonNull
    public final TextView tvCharactersLeft;
    @NonNull
    public final TextView tvEndDate;
    @NonNull
    public final TextView tvMedicineNameLable;
    @NonNull
    public final TextView tvNotifyMeLabel;
    @NonNull
    public final TextView tvStartDate;

    public MedicineReminderItemBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, CardView cardView, Button button, EditText editText, TextView textView2, RecyclerView recyclerView, LinearLayout linearLayout, TextView textView3, RepeatLayoutNewBinding repeatLayoutNewBinding, Spinner spinner, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        super(obj, view, i);
        this.addTimeInfo = textView;
        this.clEndTime = constraintLayout;
        this.clStartTime = constraintLayout2;
        this.cvMedicine = cardView;
        this.doneButton = button;
        this.edtReminderName = editText;
        this.endTimeTitle = textView2;
        this.rcvTimeInfos = recyclerView;
        this.reminderLayout = linearLayout;
        this.reminderTimeLabel = textView3;
        this.repeatLayout = repeatLayoutNewBinding;
        this.spNotifyMe = spinner;
        this.startTimeTitle = textView4;
        this.tvCharactersLeft = textView5;
        this.tvEndDate = textView6;
        this.tvMedicineNameLable = textView7;
        this.tvNotifyMeLabel = textView8;
        this.tvStartDate = textView9;
    }

    public static MedicineReminderItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MedicineReminderItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getIsInEditMode() {
        return this.mIsInEditMode;
    }

    public abstract void setIsInEditMode(@Nullable Boolean bool);

    @Deprecated
    public static MedicineReminderItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MedicineReminderItemBinding) ViewDataBinding.bind(obj, view, R.layout.medicine_reminder_item);
    }

    @NonNull
    @Deprecated
    public static MedicineReminderItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MedicineReminderItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.medicine_reminder_item, viewGroup, z, obj);
    }

    @NonNull
    public static MedicineReminderItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MedicineReminderItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MedicineReminderItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.medicine_reminder_item, null, false, obj);
    }
}
