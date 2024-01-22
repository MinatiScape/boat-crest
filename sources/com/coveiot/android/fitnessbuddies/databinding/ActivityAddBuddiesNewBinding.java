package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes4.dex */
public abstract class ActivityAddBuddiesNewBinding extends ViewDataBinding {
    @NonNull
    public final Button btnCreateChallenge;
    @NonNull
    public final ConstraintLayout clActiveContacts;
    @NonNull
    public final ConstraintLayout clInactiveContacts;
    @NonNull
    public final ConstraintLayout clMenu;
    @NonNull
    public final ConstraintLayout clRootLayout;
    @NonNull
    public final ConstraintLayout fitnessChallengeViews;
    @NonNull
    public final InfoDetailsBinding infoDetails;
    @NonNull
    public final ImageButton refresh;
    @NonNull
    public final RecyclerView rvActiveContactsList;
    @NonNull
    public final RecyclerView rvInActiveContactsList;
    @NonNull
    public final SearchView search;
    @NonNull
    public final LinearLayout searchLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvActiveHeader;
    @NonNull
    public final TextView tvAddParticipant;
    @NonNull
    public final TextView tvInactiveHeader;
    @NonNull
    public final TextView tvRemoveParticipant;
    @NonNull
    public final View view;

    public ActivityAddBuddiesNewBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, InfoDetailsBinding infoDetailsBinding, ImageButton imageButton, RecyclerView recyclerView, RecyclerView recyclerView2, SearchView searchView, LinearLayout linearLayout, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view3) {
        super(obj, view, i);
        this.btnCreateChallenge = button;
        this.clActiveContacts = constraintLayout;
        this.clInactiveContacts = constraintLayout2;
        this.clMenu = constraintLayout3;
        this.clRootLayout = constraintLayout4;
        this.fitnessChallengeViews = constraintLayout5;
        this.infoDetails = infoDetailsBinding;
        this.refresh = imageButton;
        this.rvActiveContactsList = recyclerView;
        this.rvInActiveContactsList = recyclerView2;
        this.search = searchView;
        this.searchLayout = linearLayout;
        this.toolbar = view2;
        this.tvActiveHeader = textView;
        this.tvAddParticipant = textView2;
        this.tvInactiveHeader = textView3;
        this.tvRemoveParticipant = textView4;
        this.view = view3;
    }

    public static ActivityAddBuddiesNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddBuddiesNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddBuddiesNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddBuddiesNewBinding) ViewDataBinding.bind(obj, view, R.layout.activity_add_buddies_new);
    }

    @NonNull
    @Deprecated
    public static ActivityAddBuddiesNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddBuddiesNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_buddies_new, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddBuddiesNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddBuddiesNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddBuddiesNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_buddies_new, null, false, obj);
    }
}
