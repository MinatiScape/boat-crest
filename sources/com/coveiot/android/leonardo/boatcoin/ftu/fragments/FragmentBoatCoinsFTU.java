package com.coveiot.android.leonardo.boatcoin.ftu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentBoatCoinsFTUBinding;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentBoatCoinsFTU extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentBoatCoinsFTUBinding h;
    public int i;
    @Nullable
    public String j;
    @Nullable
    public String k;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentBoatCoinsFTU newInstance(int i, @NotNull String description, @NotNull String description2) {
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(description2, "description2");
            FragmentBoatCoinsFTU fragmentBoatCoinsFTU = new FragmentBoatCoinsFTU();
            fragmentBoatCoinsFTU.i = i;
            fragmentBoatCoinsFTU.j = description;
            fragmentBoatCoinsFTU.k = description2;
            return fragmentBoatCoinsFTU;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentBoatCoinsFTU newInstance(int i, @NotNull String str, @NotNull String str2) {
        return Companion.newInstance(i, str, str2);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final FragmentBoatCoinsFTUBinding b() {
        FragmentBoatCoinsFTUBinding fragmentBoatCoinsFTUBinding = this.h;
        Intrinsics.checkNotNull(fragmentBoatCoinsFTUBinding);
        return fragmentBoatCoinsFTUBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.h = FragmentBoatCoinsFTUBinding.inflate(inflater, viewGroup, false);
        b().tvBoatCoinsDescription.setText(this.j);
        b().tvBoatCoinsDescription2.setText(this.k);
        b().ivBoatCoinsFtu.setImageResource(this.i);
        if (Intrinsics.areEqual(b().tvBoatCoinsDescription.getText(), getString(R.string.earn_boat_coins))) {
            b().bcImageViewDot1.setAlpha(1.0f);
            b().bcImageViewDot2.setAlpha(0.5f);
        } else if (Intrinsics.areEqual(b().tvBoatCoinsDescription.getText(), getString(R.string.redeem_boat_coin))) {
            b().bcImageViewDot1.setAlpha(0.5f);
            b().bcImageViewDot2.setAlpha(1.0f);
        }
        View root = b().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.h = null;
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
    }
}
