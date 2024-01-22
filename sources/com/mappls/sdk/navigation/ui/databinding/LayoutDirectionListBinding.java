package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutDirectionListBinding extends ViewDataBinding {
    @NonNull
    public final TextView btnShowMap;
    @NonNull
    public final ImageView closeBottomSheet;
    @NonNull
    public final RelativeLayout directionListView;
    @NonNull
    public final RecyclerView rvDirectionList;
    @NonNull
    public final CardView toolbarDirection;
    @NonNull
    public final TextView toolbarText;

    public LayoutDirectionListBinding(Object obj, View view, int i, TextView textView, ImageView imageView, RelativeLayout relativeLayout, RecyclerView recyclerView, CardView cardView, TextView textView2) {
        super(obj, view, i);
        this.btnShowMap = textView;
        this.closeBottomSheet = imageView;
        this.directionListView = relativeLayout;
        this.rvDirectionList = recyclerView;
        this.toolbarDirection = cardView;
        this.toolbarText = textView2;
    }

    public static LayoutDirectionListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDirectionListBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutDirectionListBinding) ViewDataBinding.bind(obj, view, R.layout.layout_direction_list);
    }

    @NonNull
    public static LayoutDirectionListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutDirectionListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutDirectionListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutDirectionListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_direction_list, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutDirectionListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutDirectionListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_direction_list, null, false, obj);
    }
}
