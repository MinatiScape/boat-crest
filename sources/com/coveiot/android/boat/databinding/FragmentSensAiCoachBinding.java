package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentSensAiCoachBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView categoryRecyclerView;
    @NonNull
    public final EditText etSearchVideo;
    @NonNull
    public final FrameLayout fragmentContainerCultFitLastVideo;
    @NonNull
    public final TextView header;
    @NonNull
    public final TextView tvNoData;
    @NonNull
    public final RecyclerView videosRecyclerView;

    public FragmentSensAiCoachBinding(Object obj, View view, int i, RecyclerView recyclerView, EditText editText, FrameLayout frameLayout, TextView textView, TextView textView2, RecyclerView recyclerView2) {
        super(obj, view, i);
        this.categoryRecyclerView = recyclerView;
        this.etSearchVideo = editText;
        this.fragmentContainerCultFitLastVideo = frameLayout;
        this.header = textView;
        this.tvNoData = textView2;
        this.videosRecyclerView = recyclerView2;
    }

    public static FragmentSensAiCoachBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSensAiCoachBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSensAiCoachBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSensAiCoachBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_sens_ai_coach);
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiCoachBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSensAiCoachBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_coach, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSensAiCoachBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiCoachBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSensAiCoachBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_coach, null, false, obj);
    }
}
