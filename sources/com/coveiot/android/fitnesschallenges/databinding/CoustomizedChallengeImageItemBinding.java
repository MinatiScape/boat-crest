package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class CoustomizedChallengeImageItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clIVBorder;
    @NonNull
    public final CardView cvImage;
    @NonNull
    public final ImageView imageView;

    public CoustomizedChallengeImageItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, CardView cardView, ImageView imageView) {
        super(obj, view, i);
        this.clIVBorder = constraintLayout;
        this.cvImage = cardView;
        this.imageView = imageView;
    }

    public static CoustomizedChallengeImageItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CoustomizedChallengeImageItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CoustomizedChallengeImageItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CoustomizedChallengeImageItemBinding) ViewDataBinding.bind(obj, view, R.layout.coustomized_challenge_image_item);
    }

    @NonNull
    @Deprecated
    public static CoustomizedChallengeImageItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CoustomizedChallengeImageItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.coustomized_challenge_image_item, viewGroup, z, obj);
    }

    @NonNull
    public static CoustomizedChallengeImageItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CoustomizedChallengeImageItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CoustomizedChallengeImageItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.coustomized_challenge_image_item, null, false, obj);
    }
}
