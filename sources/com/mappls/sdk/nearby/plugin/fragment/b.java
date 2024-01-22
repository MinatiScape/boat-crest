package com.mappls.sdk.nearby.plugin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.adapter.d;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyFragmentResultListBinding;
import com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class b extends Fragment {
    public static final /* synthetic */ int f = 0;
    public MapplsNearbyFragmentResultListBinding h;
    public com.mappls.sdk.nearby.plugin.viewmodel.b i;
    public d j;
    public NearbyResultViewOption k;
    @Nullable
    public NearbyResultCallback l;

    /* loaded from: classes10.dex */
    public static final class a {
        @NotNull
        public static b a(@NotNull NearbyResultViewOption viewOption) {
            Intrinsics.checkNotNullParameter(viewOption, "viewOption");
            b bVar = new b();
            Bundle bundle = new Bundle();
            bundle.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_RESULT_OPTION_KEY", viewOption);
            bVar.setArguments(bundle);
            return bVar;
        }
    }

    /* renamed from: com.mappls.sdk.nearby.plugin.fragment.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0653b implements d.a {
        public C0653b() {
        }

        @Override // com.mappls.sdk.nearby.plugin.adapter.d.a
        public final void a(@NotNull NearbyAtlasResult nearbyAtlasResult) {
            Intrinsics.checkNotNullParameter(nearbyAtlasResult, "nearbyAtlasResult");
            NearbyResultCallback nearbyResultCallback = b.this.l;
            if (nearbyResultCallback != null) {
                nearbyResultCallback.onNearbyResultClick(nearbyAtlasResult);
            }
        }
    }

    public final void a() {
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.i;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        NearbyAtlasResponse b = bVar.b();
        if ((b != null ? b.getSuggestedLocations() : null) != null) {
            d dVar = this.j;
            if (dVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                dVar = null;
            }
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = this.i;
            if (bVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar2 = null;
            }
            NearbyAtlasResponse b2 = bVar2.b();
            ArrayList<NearbyAtlasResult> suggestedLocations = b2 != null ? b2.getSuggestedLocations() : null;
            Intrinsics.checkNotNull(suggestedLocations);
            dVar.a(suggestedLocations);
        }
    }

    public final void a(@NotNull NearbyResultCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.l = callback;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public final View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.mappls_nearby_fragment_result_list, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…t_list, container, false)");
        MapplsNearbyFragmentResultListBinding mapplsNearbyFragmentResultListBinding = (MapplsNearbyFragmentResultListBinding) inflate;
        this.h = mapplsNearbyFragmentResultListBinding;
        if (mapplsNearbyFragmentResultListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsNearbyFragmentResultListBinding = null;
        }
        View root = mapplsNearbyFragmentResultListBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "mBinding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onSaveInstanceState(@NotNull Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        NearbyResultViewOption nearbyResultViewOption = this.k;
        if (nearbyResultViewOption == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewOption");
            nearbyResultViewOption = null;
        }
        outState.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_RESULT_OPTION_KEY", nearbyResultViewOption);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r4 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0046, code lost:
        r4 = com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.builder().build();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, "builder().build()");
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0037, code lost:
        if (r4 == null) goto L49;
     */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r4, @org.jetbrains.annotations.Nullable android.os.Bundle r5) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.nearby.plugin.fragment.b.onViewCreated(android.view.View, android.os.Bundle):void");
    }
}
