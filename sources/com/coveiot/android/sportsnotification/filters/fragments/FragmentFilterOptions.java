package com.coveiot.android.sportsnotification.filters.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.filters.adapter.AdapterFilterOptions;
import com.coveiot.android.sportsnotification.model.Filters;
import com.coveiot.android.sportsnotification.model.Options;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentFilterOptions extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ViewDataBinding binding;
    @Nullable
    public Filters h;
    @Nullable
    public ArrayList<Integer> i;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFilterOptions newInstance(@Nullable Filters filters, @Nullable ArrayList<Integer> arrayList) {
            FragmentFilterOptions fragmentFilterOptions = new FragmentFilterOptions();
            Bundle bundle = new Bundle();
            bundle.putSerializable("filter", filters);
            bundle.putSerializable("param2", arrayList);
            fragmentFilterOptions.setArguments(bundle);
            return fragmentFilterOptions;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentFilterOptions newInstance(@Nullable Filters filters, @Nullable ArrayList<Integer> arrayList) {
        return Companion.newInstance(filters, arrayList);
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

    @NotNull
    public final ViewDataBinding getBinding() {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding != null) {
            return viewDataBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("filter");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.sportsnotification.model.Filters");
            this.h = (Filters) serializable;
            if (arguments.getSerializable("param2") != null) {
                Serializable serializable2 = arguments.getSerializable("param2");
                Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.Int> }");
                this.i = (ArrayList) serializable2;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_filter_options, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            lay…          false\n        )");
        setBinding(inflate);
        return getBinding().getRoot();
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
        View root = getBinding().getRoot();
        int i = R.id.rcv_filter_options;
        ((RecyclerView) root.findViewById(i)).setLayoutManager(new LinearLayoutManager(requireContext()));
        if (this.h != null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            Filters filters = this.h;
            List<Options> options = filters != null ? filters.getOptions() : null;
            Intrinsics.checkNotNull(options);
            AdapterFilterOptions adapterFilterOptions = new AdapterFilterOptions(requireContext, options, this.i);
            if (getActivity() instanceof AdapterFilterOptions.FilterSelectionListener) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                adapterFilterOptions.setListener((AdapterFilterOptions.FilterSelectionListener) activity);
            }
            ((RecyclerView) getBinding().getRoot().findViewById(i)).setAdapter(adapterFilterOptions);
        }
    }

    public final void setBinding(@NotNull ViewDataBinding viewDataBinding) {
        Intrinsics.checkNotNullParameter(viewDataBinding, "<set-?>");
        this.binding = viewDataBinding;
    }
}
