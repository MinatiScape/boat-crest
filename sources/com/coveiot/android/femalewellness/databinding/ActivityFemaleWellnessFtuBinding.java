package com.coveiot.android.femalewellness.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.femalewellness.R;
/* loaded from: classes4.dex */
public abstract class ActivityFemaleWellnessFtuBinding extends ViewDataBinding {
    @NonNull
    public final WheelPicker amPmPicker;
    @NonNull
    public final Button btnNext;
    @NonNull
    public final FrameLayout calendarContainer;
    @NonNull
    public final ConstraintLayout clCalendar;
    @NonNull
    public final ConstraintLayout clCommon;
    @NonNull
    public final ConstraintLayout clFTUQuestions;
    @NonNull
    public final ConstraintLayout clMenstrual;
    @NonNull
    public final ConstraintLayout clOvulation;
    @NonNull
    public final ConstraintLayout clRadioQuestionsCycleLength;
    @NonNull
    public final ConstraintLayout clRadioQuestionsInterval;
    @NonNull
    public final ConstraintLayout clReminder;
    @NonNull
    public final ConstraintLayout clReminderTime;
    @NonNull
    public final ConstraintLayout clTopReminder;
    @NonNull
    public final TextView disclaimerText;
    @NonNull
    public final TextView header;
    @NonNull
    public final TextView headerOne;
    @NonNull
    public final TextView headerTwo;
    @NonNull
    public final WheelPicker hourPicker;
    @NonNull
    public final ImageButton ibNextQuestion;
    @NonNull
    public final ImageButton ibPreviousQuestion;
    @NonNull
    public final ImageView imageViewDot1;
    @NonNull
    public final ImageView imageViewDot2;
    @NonNull
    public final ImageView imageViewDot3;
    @NonNull
    public final View infoView;
    @NonNull
    public final ImageView ivImage;
    @Bindable
    public int mCurrentSelection;
    @Bindable
    public int mFtuItemCount;
    @NonNull
    public final WheelPicker minPicker;
    @NonNull
    public final NestedScrollView nestedScroolView;
    @NonNull
    public final RadioGroup radioGroupCycle;
    @NonNull
    public final RadioGroup radioGroupInterval;
    @NonNull
    public final RadioGroup radioGroupMenstrual;
    @NonNull
    public final RadioGroup radioGroupOvulation;
    @NonNull
    public final RadioButton rb20Days;
    @NonNull
    public final RadioButton rb21Days;
    @NonNull
    public final RadioButton rb22Days;
    @NonNull
    public final RadioButton rb23Days;
    @NonNull
    public final RadioButton rb2Days;
    @NonNull
    public final RadioButton rb3Days;
    @NonNull
    public final RadioButton rb4Days;
    @NonNull
    public final RadioButton rb5Days;
    @NonNull
    public final RadioButton rbMenstrual1Days;
    @NonNull
    public final RadioButton rbMenstrual2Days;
    @NonNull
    public final RadioButton rbMenstrual3Days;
    @NonNull
    public final RadioButton rbOvulation1Days;
    @NonNull
    public final RadioButton rbOvulation2Days;
    @NonNull
    public final RadioButton rbOvulation3Days;
    @NonNull
    public final TextView setupText;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvCustomCycleLength;
    @NonNull
    public final TextView tvCustomMenstrualCycleLength;
    @NonNull
    public final TextView tvQuestion;
    @NonNull
    public final View view;

    public ActivityFemaleWellnessFtuBinding(Object obj, View view, int i, WheelPicker wheelPicker, Button button, FrameLayout frameLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, TextView textView, TextView textView2, TextView textView3, TextView textView4, WheelPicker wheelPicker2, ImageButton imageButton, ImageButton imageButton2, ImageView imageView, ImageView imageView2, ImageView imageView3, View view2, ImageView imageView4, WheelPicker wheelPicker3, NestedScrollView nestedScrollView, RadioGroup radioGroup, RadioGroup radioGroup2, RadioGroup radioGroup3, RadioGroup radioGroup4, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioButton radioButton5, RadioButton radioButton6, RadioButton radioButton7, RadioButton radioButton8, RadioButton radioButton9, RadioButton radioButton10, RadioButton radioButton11, RadioButton radioButton12, RadioButton radioButton13, RadioButton radioButton14, TextView textView5, View view3, TextView textView6, TextView textView7, TextView textView8, View view4) {
        super(obj, view, i);
        this.amPmPicker = wheelPicker;
        this.btnNext = button;
        this.calendarContainer = frameLayout;
        this.clCalendar = constraintLayout;
        this.clCommon = constraintLayout2;
        this.clFTUQuestions = constraintLayout3;
        this.clMenstrual = constraintLayout4;
        this.clOvulation = constraintLayout5;
        this.clRadioQuestionsCycleLength = constraintLayout6;
        this.clRadioQuestionsInterval = constraintLayout7;
        this.clReminder = constraintLayout8;
        this.clReminderTime = constraintLayout9;
        this.clTopReminder = constraintLayout10;
        this.disclaimerText = textView;
        this.header = textView2;
        this.headerOne = textView3;
        this.headerTwo = textView4;
        this.hourPicker = wheelPicker2;
        this.ibNextQuestion = imageButton;
        this.ibPreviousQuestion = imageButton2;
        this.imageViewDot1 = imageView;
        this.imageViewDot2 = imageView2;
        this.imageViewDot3 = imageView3;
        this.infoView = view2;
        this.ivImage = imageView4;
        this.minPicker = wheelPicker3;
        this.nestedScroolView = nestedScrollView;
        this.radioGroupCycle = radioGroup;
        this.radioGroupInterval = radioGroup2;
        this.radioGroupMenstrual = radioGroup3;
        this.radioGroupOvulation = radioGroup4;
        this.rb20Days = radioButton;
        this.rb21Days = radioButton2;
        this.rb22Days = radioButton3;
        this.rb23Days = radioButton4;
        this.rb2Days = radioButton5;
        this.rb3Days = radioButton6;
        this.rb4Days = radioButton7;
        this.rb5Days = radioButton8;
        this.rbMenstrual1Days = radioButton9;
        this.rbMenstrual2Days = radioButton10;
        this.rbMenstrual3Days = radioButton11;
        this.rbOvulation1Days = radioButton12;
        this.rbOvulation2Days = radioButton13;
        this.rbOvulation3Days = radioButton14;
        this.setupText = textView5;
        this.toolbar = view3;
        this.tvCustomCycleLength = textView6;
        this.tvCustomMenstrualCycleLength = textView7;
        this.tvQuestion = textView8;
        this.view = view4;
    }

    public static ActivityFemaleWellnessFtuBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityFemaleWellnessFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    public int getCurrentSelection() {
        return this.mCurrentSelection;
    }

    public int getFtuItemCount() {
        return this.mFtuItemCount;
    }

    public abstract void setCurrentSelection(int i);

    public abstract void setFtuItemCount(int i);

    @Deprecated
    public static ActivityFemaleWellnessFtuBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityFemaleWellnessFtuBinding) ViewDataBinding.bind(obj, view, R.layout.activity_female_wellness_ftu);
    }

    @NonNull
    @Deprecated
    public static ActivityFemaleWellnessFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityFemaleWellnessFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_female_wellness_ftu, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityFemaleWellnessFtuBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityFemaleWellnessFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityFemaleWellnessFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_female_wellness_ftu, null, false, obj);
    }
}
