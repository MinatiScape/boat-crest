package com.coveiot.android.sos.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sos.R;
/* loaded from: classes7.dex */
public abstract class ActivitySosSettingsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddEmergencyContact;
    @NonNull
    public final Button btnSaveContact;
    @NonNull
    public final ConstraintLayout clContacts;
    @NonNull
    public final ConstraintLayout clEmptyLayout;
    @NonNull
    public final ConstraintLayout clNoContacts;
    @NonNull
    public final RecyclerView rvActiveContactsList;
    @NonNull
    public final SearchView search;
    @NonNull
    public final LinearLayout searchLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvChooseContact;
    @NonNull
    public final TextView tvNoContacts;
    @NonNull
    public final TextView tvSOSInfo;
    @NonNull
    public final TextView tvTNC;

    public ActivitySosSettingsBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, RecyclerView recyclerView, SearchView searchView, LinearLayout linearLayout, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.btnAddEmergencyContact = button;
        this.btnSaveContact = button2;
        this.clContacts = constraintLayout;
        this.clEmptyLayout = constraintLayout2;
        this.clNoContacts = constraintLayout3;
        this.rvActiveContactsList = recyclerView;
        this.search = searchView;
        this.searchLayout = linearLayout;
        this.toolbar = view2;
        this.tvChooseContact = textView;
        this.tvNoContacts = textView2;
        this.tvSOSInfo = textView3;
        this.tvTNC = textView4;
    }

    public static ActivitySosSettingsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySosSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySosSettingsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySosSettingsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sos_settings);
    }

    @NonNull
    @Deprecated
    public static ActivitySosSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySosSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sos_settings, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySosSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySosSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySosSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sos_settings, null, false, obj);
    }
}
