package com.coveiot.android.leonardo.sensai.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.boat.databinding.FragmentSensaiFilterOptionsBinding;
import com.coveiot.android.leonardo.sensai.adapter.SensAIFilterOptionsAdapter;
import com.coveiot.android.leonardo.sensai.model.SensAIFilter;
import com.coveiot.android.leonardo.sensai.model.SensAIFilterOptions;
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
/* loaded from: classes5.dex */
public final class FragmentSensAIFilterOptions extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentSensaiFilterOptionsBinding h;
    @Nullable
    public SensAIFilter i;
    @Nullable
    public ArrayList<String> j;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentSensAIFilterOptions newInstance() {
            return new FragmentSensAIFilterOptions();
        }

        @NotNull
        public final FragmentSensAIFilterOptions newInstance(@Nullable SensAIFilter sensAIFilter, @Nullable ArrayList<String> arrayList) {
            FragmentSensAIFilterOptions fragmentSensAIFilterOptions = new FragmentSensAIFilterOptions();
            Bundle bundle = new Bundle();
            bundle.putSerializable("filter", sensAIFilter);
            bundle.putSerializable("param2", arrayList);
            fragmentSensAIFilterOptions.setArguments(bundle);
            return fragmentSensAIFilterOptions;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentSensAIFilterOptions newInstance() {
        return Companion.newInstance();
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

    public final FragmentSensaiFilterOptionsBinding b() {
        FragmentSensaiFilterOptionsBinding fragmentSensaiFilterOptionsBinding = this.h;
        if (fragmentSensaiFilterOptionsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentSensaiFilterOptionsBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("filter");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.sensai.model.SensAIFilter");
            this.i = (SensAIFilter) serializable;
            if (arguments.getSerializable("param2") != null) {
                Serializable serializable2 = arguments.getSerializable("param2");
                Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
                this.j = (ArrayList) serializable2;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSensaiFilterOptionsBinding inflate = FragmentSensaiFilterOptionsBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.h = inflate;
        return b().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        SensAIFilterOptionsAdapter sensAIFilterOptionsAdapter;
        List<SensAIFilterOptions> filters;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        b().rcvFilterOptions.setLayoutManager(new LinearLayoutManager(requireContext()));
        SensAIFilter sensAIFilter = this.i;
        if (sensAIFilter == null || (filters = sensAIFilter.getFilters()) == null) {
            sensAIFilterOptionsAdapter = null;
        } else {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            sensAIFilterOptionsAdapter = new SensAIFilterOptionsAdapter(requireContext, filters, this.j);
        }
        if ((getActivity() instanceof SensAIFilterOptionsAdapter.FilterSelectionListener) && sensAIFilterOptionsAdapter != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.sensai.adapter.SensAIFilterOptionsAdapter.FilterSelectionListener");
            sensAIFilterOptionsAdapter.setListener((SensAIFilterOptionsAdapter.FilterSelectionListener) requireActivity);
        }
        b().rcvFilterOptions.setAdapter(sensAIFilterOptionsAdapter);
    }
}
