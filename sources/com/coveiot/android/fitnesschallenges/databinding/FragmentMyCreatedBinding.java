package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes2.dex */
public abstract class FragmentMyCreatedBinding extends ViewDataBinding {
    @NonNull
    public final EmptyChallengeViewLayoutBinding emptyChallengeView;
    @NonNull
    public final InfoDetailsBinding infoDetails;
    @NonNull
    public final RecyclerView rvChallenges;

    public FragmentMyCreatedBinding(Object obj, View view, int i, EmptyChallengeViewLayoutBinding emptyChallengeViewLayoutBinding, InfoDetailsBinding infoDetailsBinding, RecyclerView recyclerView) {
        super(obj, view, i);
        this.emptyChallengeView = emptyChallengeViewLayoutBinding;
        this.infoDetails = infoDetailsBinding;
        this.rvChallenges = recyclerView;
    }

    public static FragmentMyCreatedBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentMyCreatedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMyCreatedBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentMyCreatedBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_my_created);
    }

    @NonNull
    @Deprecated
    public static FragmentMyCreatedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentMyCreatedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_created, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentMyCreatedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentMyCreatedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentMyCreatedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_created, null, false, obj);
    }
}
