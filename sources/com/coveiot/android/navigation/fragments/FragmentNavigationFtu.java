package com.coveiot.android.navigation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.databinding.FragmentNavigatFtuBinding;
import com.coveiot.android.navigation.interfaces.NavigationFTUScreenChangeListener;
import com.coveiot.android.navigation.models.ScreenType;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentNavigationFtu extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentNavigatFtuBinding h;
    @Nullable
    public NavigationFTUScreenChangeListener i;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentNavigationFtu newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentNavigationFtu fragmentNavigationFtu = new FragmentNavigationFtu();
            fragmentNavigationFtu.setArguments(new Bundle());
            return fragmentNavigationFtu;
        }
    }

    public static final void c(FragmentNavigationFtu this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            NavigationFTUScreenChangeListener navigationFTUScreenChangeListener = this$0.i;
            if (navigationFTUScreenChangeListener != null) {
                navigationFTUScreenChangeListener.onFragmentChanged(ScreenType.DISCLAIMER);
                return;
            }
            return;
        }
        Toast.makeText(this$0.requireContext(), this$0.requireContext().getText(R.string.no_internet), 1).show();
    }

    @JvmStatic
    @NotNull
    public static final FragmentNavigationFtu newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
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

    public final FragmentNavigatFtuBinding b() {
        FragmentNavigatFtuBinding fragmentNavigatFtuBinding = this.h;
        Intrinsics.checkNotNull(fragmentNavigatFtuBinding);
        return fragmentNavigatFtuBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof NavigationFTUScreenChangeListener) {
            this.i = (NavigationFTUScreenChangeListener) context;
            return;
        }
        throw new ClassCastException(context + " must implement FragmentChangeListener");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.h = FragmentNavigatFtuBinding.inflate(inflater, viewGroup, false);
        return b().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        b().btnNext.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentNavigationFtu.c(FragmentNavigationFtu.this, view2);
            }
        });
    }
}
