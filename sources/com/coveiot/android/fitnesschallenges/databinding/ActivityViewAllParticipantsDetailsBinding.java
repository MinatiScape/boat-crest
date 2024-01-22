package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes2.dex */
public abstract class ActivityViewAllParticipantsDetailsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnRemoveParticipnats;
    @NonNull
    public final ConstraintLayout clMenu;
    @NonNull
    public final ConstraintLayout fitnessChallengeViews;
    @NonNull
    public final InfoDetailsBinding infoDetails;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvAddParticipant;
    @NonNull
    public final TextView tvRemoveParticipant;
    @NonNull
    public final View view;
    @NonNull
    public final ViewPager viewPager;

    public ActivityViewAllParticipantsDetailsBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, InfoDetailsBinding infoDetailsBinding, TabLayout tabLayout, View view2, TextView textView, TextView textView2, View view3, ViewPager viewPager) {
        super(obj, view, i);
        this.btnRemoveParticipnats = button;
        this.clMenu = constraintLayout;
        this.fitnessChallengeViews = constraintLayout2;
        this.infoDetails = infoDetailsBinding;
        this.tabLayout = tabLayout;
        this.toolbar = view2;
        this.tvAddParticipant = textView;
        this.tvRemoveParticipant = textView2;
        this.view = view3;
        this.viewPager = viewPager;
    }

    public static ActivityViewAllParticipantsDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityViewAllParticipantsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityViewAllParticipantsDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityViewAllParticipantsDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_view_all_participants_details);
    }

    @NonNull
    @Deprecated
    public static ActivityViewAllParticipantsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityViewAllParticipantsDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_view_all_participants_details, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityViewAllParticipantsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityViewAllParticipantsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityViewAllParticipantsDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_view_all_participants_details, null, false, obj);
    }
}
