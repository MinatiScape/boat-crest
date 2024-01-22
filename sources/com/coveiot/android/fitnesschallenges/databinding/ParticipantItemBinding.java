package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
/* loaded from: classes2.dex */
public abstract class ParticipantItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView buddiesDisplayPic;
    @NonNull
    public final TextView buddiesText;
    @NonNull
    public final CheckBox cbParticipant;
    @NonNull
    public final ConstraintLayout clEnd;
    @NonNull
    public final TextView displayName;
    @NonNull
    public final TextView displayNumber;
    @Bindable
    public GetAllParticipantsFitnessChallengeRes.ParticipantsDetails mParticipantItem;
    @NonNull
    public final RelativeLayout rootLayoutGeneric;
    @NonNull
    public final TextView tvBuddiesInvite;

    public ParticipantItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, CheckBox checkBox, ConstraintLayout constraintLayout, TextView textView2, TextView textView3, RelativeLayout relativeLayout, TextView textView4) {
        super(obj, view, i);
        this.buddiesDisplayPic = imageView;
        this.buddiesText = textView;
        this.cbParticipant = checkBox;
        this.clEnd = constraintLayout;
        this.displayName = textView2;
        this.displayNumber = textView3;
        this.rootLayoutGeneric = relativeLayout;
        this.tvBuddiesInvite = textView4;
    }

    public static ParticipantItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public GetAllParticipantsFitnessChallengeRes.ParticipantsDetails getParticipantItem() {
        return this.mParticipantItem;
    }

    public abstract void setParticipantItem(@Nullable GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participantsDetails);

    @Deprecated
    public static ParticipantItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ParticipantItemBinding) ViewDataBinding.bind(obj, view, R.layout.participant_item);
    }

    @NonNull
    @Deprecated
    public static ParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ParticipantItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.participant_item, viewGroup, z, obj);
    }

    @NonNull
    public static ParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ParticipantItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.participant_item, null, false, obj);
    }
}
