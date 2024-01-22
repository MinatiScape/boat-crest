package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class FragmentParticipantsListBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clRootLayout;
    @NonNull
    public final EmptyChallengeViewLayoutBinding emptyChallengeView;
    @NonNull
    public final RecyclerView rvParticipantList;

    public FragmentParticipantsListBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, EmptyChallengeViewLayoutBinding emptyChallengeViewLayoutBinding, RecyclerView recyclerView) {
        super(obj, view, i);
        this.clRootLayout = constraintLayout;
        this.emptyChallengeView = emptyChallengeViewLayoutBinding;
        this.rvParticipantList = recyclerView;
    }

    public static FragmentParticipantsListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentParticipantsListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentParticipantsListBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentParticipantsListBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_participants_list);
    }

    @NonNull
    @Deprecated
    public static FragmentParticipantsListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentParticipantsListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_participants_list, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentParticipantsListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentParticipantsListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentParticipantsListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_participants_list, null, false, obj);
    }
}
