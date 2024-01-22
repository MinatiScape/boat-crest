package com.mappls.sdk.nearby.plugin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyFragmentMapBinding;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class a extends Fragment {
    public MapplsNearbyFragmentMapBinding h;
    @Nullable
    public NearbyResultCallback i;

    public static final void b(a this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NearbyResultCallback nearbyResultCallback = this$0.i;
        if (nearbyResultCallback != null) {
            nearbyResultCallback.onLocationClick();
        }
    }

    public final void a(@NotNull NearbyResultCallback nearbyResultCallback) {
        Intrinsics.checkNotNullParameter(nearbyResultCallback, "nearbyResultCallback");
        this.i = nearbyResultCallback;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public final View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.mappls_nearby_fragment_map, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…nt_map, container, false)");
        MapplsNearbyFragmentMapBinding mapplsNearbyFragmentMapBinding = (MapplsNearbyFragmentMapBinding) inflate;
        this.h = mapplsNearbyFragmentMapBinding;
        if (mapplsNearbyFragmentMapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsNearbyFragmentMapBinding = null;
        }
        View root = mapplsNearbyFragmentMapBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "mBinding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        MapplsNearbyFragmentMapBinding mapplsNearbyFragmentMapBinding = this.h;
        if (mapplsNearbyFragmentMapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsNearbyFragmentMapBinding = null;
        }
        mapplsNearbyFragmentMapBinding.mapplsNearbyUserLocationButton.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.fragment.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                a.b(a.this, view2);
            }
        });
    }
}
