package com.coveiot.android.leonardo.more.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentWatchDiagnosticDashboardBinding;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covepreferences.SessionManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WatchDiagnosticDashboardFragment extends BaseFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentWatchDiagnosticDashboardBinding m;

    public static final void m(WatchDiagnosticDashboardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
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

    public final void initToolbar() {
        ((TextView) l().toolBar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.c3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchDiagnosticDashboardFragment.m(WatchDiagnosticDashboardFragment.this, view);
            }
        });
    }

    public final FragmentWatchDiagnosticDashboardBinding l() {
        FragmentWatchDiagnosticDashboardBinding fragmentWatchDiagnosticDashboardBinding = this.m;
        Intrinsics.checkNotNull(fragmentWatchDiagnosticDashboardBinding);
        return fragmentWatchDiagnosticDashboardBinding;
    }

    public final void n(String str, Fragment fragment) {
        ((TextView) l().toolBar.findViewById(R.id.toolbar_title)).setText(str);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.diagnosticContainer, fragment, (String) null).commit();
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentWatchDiagnosticDashboardBinding.inflate(inflater, viewGroup, false);
        View root = l().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initToolbar();
        l();
        Boolean isDiagnosticTestSelected = SessionManager.getInstance(requireContext()).getIsDiagnosticTestSelected();
        Intrinsics.checkNotNullExpressionValue(isDiagnosticTestSelected, "getInstance(requireConte….isDiagnosticTestSelected");
        if (isDiagnosticTestSelected.booleanValue()) {
            String string = getString(R.string.run_watch_diagnostics);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.run_watch_diagnostics)");
            n(string, new RunWatchDiagnosticFragment());
            return;
        }
        String string2 = getString(R.string.watch_diagnostics_test_history);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.watch_diagnostics_test_history)");
        n(string2, new WatchDiagnosticTestHistoryFragment());
    }
}
