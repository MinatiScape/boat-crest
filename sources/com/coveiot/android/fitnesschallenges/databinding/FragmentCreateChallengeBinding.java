package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes2.dex */
public abstract class FragmentCreateChallengeBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddParticipants;
    @NonNull
    public final Button btnCreateChallenge;
    @NonNull
    public final CheckBox cbAgreeTerms;
    @NonNull
    public final ConstraintLayout clBottomViews;
    @NonNull
    public final ConstraintLayout clCalorieGoal;
    @NonNull
    public final ConstraintLayout clChallengeGoalType;
    @NonNull
    public final ConstraintLayout clConditionsLayout;
    @NonNull
    public final ConstraintLayout clDistanceGoal;
    @NonNull
    public final ConstraintLayout clEndTime;
    @NonNull
    public final InfoDetailsBinding clInfo;
    @NonNull
    public final ConstraintLayout clStartTime;
    @NonNull
    public final ConstraintLayout clTopParticipantImageView;
    @NonNull
    public final ConstraintLayout clTopParticipantLayout;
    @NonNull
    public final ConstraintLayout constraintLayout1;
    @NonNull
    public final EditText editChallengeName;
    @NonNull
    public final EditText editDesc;
    @NonNull
    public final ConstraintLayout editDetailsLayout;
    @NonNull
    public final RecyclerView imageRecyclerView;
    @NonNull
    public final ConstraintLayout imageRvLayout;
    @NonNull
    public final ImageView ivTopParticipant1;
    @NonNull
    public final ImageView ivTopParticipant2;
    @NonNull
    public final ImageView ivTopParticipant3;
    @NonNull
    public final ConstraintLayout participantsLayout;
    @NonNull
    public final TextView pickerCalorie;
    @NonNull
    public final TextView pickerDistance;
    @NonNull
    public final RadioGroup radioGroup;
    @NonNull
    public final AppCompatRadioButton rbCalorieGoal;
    @NonNull
    public final AppCompatRadioButton rbGoalDistance;
    @NonNull
    public final ConstraintLayout reminderLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView totalParticipantsTv;
    @NonNull
    public final TextView tvChallengeDescCount;
    @NonNull
    public final TextView tvChallengeDescription;
    @NonNull
    public final TextView tvChallengeName;
    @NonNull
    public final TextView tvCondition;
    @NonNull
    public final TextView tvEditChallengeNameCount;
    @NonNull
    public final TextView tvEndDate;
    @NonNull
    public final TextView tvEndDateTitle;
    @NonNull
    public final TextView tvGoal;
    @NonNull
    public final TextView tvStartDate;
    @NonNull
    public final TextView tvStartDateTitle;
    @NonNull
    public final TextView tvViewAllParticipants;

    public FragmentCreateChallengeBinding(Object obj, View view, int i, Button button, Button button2, CheckBox checkBox, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, InfoDetailsBinding infoDetailsBinding, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, EditText editText, EditText editText2, ConstraintLayout constraintLayout11, RecyclerView recyclerView, ConstraintLayout constraintLayout12, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout13, TextView textView, TextView textView2, RadioGroup radioGroup, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, ConstraintLayout constraintLayout14, View view2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14) {
        super(obj, view, i);
        this.btnAddParticipants = button;
        this.btnCreateChallenge = button2;
        this.cbAgreeTerms = checkBox;
        this.clBottomViews = constraintLayout;
        this.clCalorieGoal = constraintLayout2;
        this.clChallengeGoalType = constraintLayout3;
        this.clConditionsLayout = constraintLayout4;
        this.clDistanceGoal = constraintLayout5;
        this.clEndTime = constraintLayout6;
        this.clInfo = infoDetailsBinding;
        this.clStartTime = constraintLayout7;
        this.clTopParticipantImageView = constraintLayout8;
        this.clTopParticipantLayout = constraintLayout9;
        this.constraintLayout1 = constraintLayout10;
        this.editChallengeName = editText;
        this.editDesc = editText2;
        this.editDetailsLayout = constraintLayout11;
        this.imageRecyclerView = recyclerView;
        this.imageRvLayout = constraintLayout12;
        this.ivTopParticipant1 = imageView;
        this.ivTopParticipant2 = imageView2;
        this.ivTopParticipant3 = imageView3;
        this.participantsLayout = constraintLayout13;
        this.pickerCalorie = textView;
        this.pickerDistance = textView2;
        this.radioGroup = radioGroup;
        this.rbCalorieGoal = appCompatRadioButton;
        this.rbGoalDistance = appCompatRadioButton2;
        this.reminderLayout = constraintLayout14;
        this.toolbar = view2;
        this.totalParticipantsTv = textView3;
        this.tvChallengeDescCount = textView4;
        this.tvChallengeDescription = textView5;
        this.tvChallengeName = textView6;
        this.tvCondition = textView7;
        this.tvEditChallengeNameCount = textView8;
        this.tvEndDate = textView9;
        this.tvEndDateTitle = textView10;
        this.tvGoal = textView11;
        this.tvStartDate = textView12;
        this.tvStartDateTitle = textView13;
        this.tvViewAllParticipants = textView14;
    }

    public static FragmentCreateChallengeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCreateChallengeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentCreateChallengeBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_create_challenge);
    }

    @NonNull
    @Deprecated
    public static FragmentCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentCreateChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_create_challenge, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentCreateChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_create_challenge, null, false, obj);
    }
}
