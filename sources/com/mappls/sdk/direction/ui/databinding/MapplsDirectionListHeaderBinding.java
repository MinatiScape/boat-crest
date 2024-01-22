package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionListHeaderBinding extends ViewDataBinding {
    @NonNull
    public final TextView directionListDesc;
    @NonNull
    public final TextView directionListPlaceName;
    @NonNull
    public final ImageView imageIcon;
    @NonNull
    public final FrameLayout imageIconContainer;
    @NonNull
    public final RelativeLayout mapplsDirectionSteplistContainer;

    public MapplsDirectionListHeaderBinding(Object obj, View view, int i, TextView textView, TextView textView2, ImageView imageView, FrameLayout frameLayout, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.directionListDesc = textView;
        this.directionListPlaceName = textView2;
        this.imageIcon = imageView;
        this.imageIconContainer = frameLayout;
        this.mapplsDirectionSteplistContainer = relativeLayout;
    }

    public static MapplsDirectionListHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionListHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionListHeaderBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_list_header);
    }

    @NonNull
    public static MapplsDirectionListHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionListHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionListHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionListHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_list_header, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionListHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionListHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_list_header, null, false, obj);
    }
}
