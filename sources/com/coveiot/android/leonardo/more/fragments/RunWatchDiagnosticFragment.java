package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentRunWatchDiagnosticsBinding;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.more.adapters.DiagnosticTestListingAdapter;
import com.coveiot.android.leonardo.more.viewmodel.DiagnosticsTestViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class RunWatchDiagnosticFragment extends BaseFragment implements ViewModelListener {
    @Nullable
    public FragmentRunWatchDiagnosticsBinding m;
    public boolean n;
    public DiagnosticsTestViewModel p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final DiagnosticTestListingAdapter o = new DiagnosticTestListingAdapter();

    public static final void n(RunWatchDiagnosticFragment this$0, FragmentRunWatchDiagnosticsBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (this$0.n && BleApiManager.getInstance(this$0.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            this_apply.btnRunDiagnostic.setClickable(false);
            this$0.showProgress(true);
            DiagnosticsTestViewModel diagnosticsTestViewModel = this$0.p;
            if (diagnosticsTestViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                diagnosticsTestViewModel = null;
            }
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            diagnosticsTestViewModel.diagnosticsEnterOrExit(requireContext, true);
            return;
        }
        this_apply.btnRunDiagnostic.setClickable(true);
        this$0.p(this_apply);
    }

    public static final void o(RunWatchDiagnosticFragment this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            FragmentKt.findNavController(this$0).navigate(R.id.runDiagnosticTestingFragment);
            this$0.n = false;
            return;
        }
        this$0.m().btnRunDiagnostic.setClickable(true);
        Toast.makeText(this$0.requireContext(), "Failed", 0).show();
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

    public final FragmentRunWatchDiagnosticsBinding m() {
        FragmentRunWatchDiagnosticsBinding fragmentRunWatchDiagnosticsBinding = this.m;
        Intrinsics.checkNotNull(fragmentRunWatchDiagnosticsBinding);
        return fragmentRunWatchDiagnosticsBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentRunWatchDiagnosticsBinding.inflate(inflater, viewGroup, false);
        View root = m().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (isAdded()) {
            Toast.makeText(requireContext(), message, 0).show();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        DiagnosticsTestViewModel diagnosticsTestViewModel = (DiagnosticsTestViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(DiagnosticsTestViewModel.class);
        this.p = diagnosticsTestViewModel;
        DiagnosticsTestViewModel diagnosticsTestViewModel2 = null;
        if (diagnosticsTestViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            diagnosticsTestViewModel = null;
        }
        diagnosticsTestViewModel.setMListener(this);
        final FragmentRunWatchDiagnosticsBinding m = m();
        m.rvDiagnosticTesting.setLayoutManager(new LinearLayoutManager(requireActivity()));
        DiagnosticTestListingAdapter diagnosticTestListingAdapter = this.o;
        DiagnosticsTestViewModel diagnosticsTestViewModel3 = this.p;
        if (diagnosticsTestViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            diagnosticsTestViewModel3 = null;
        }
        diagnosticTestListingAdapter.setDiagnosticTestList(diagnosticsTestViewModel3.getAllDiagnosticTest());
        m().rvDiagnosticTesting.setAdapter(this.o);
        m.btnRunDiagnostic.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.r1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RunWatchDiagnosticFragment.n(RunWatchDiagnosticFragment.this, m, view2);
            }
        });
        DiagnosticsTestViewModel diagnosticsTestViewModel4 = this.p;
        if (diagnosticsTestViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            diagnosticsTestViewModel2 = diagnosticsTestViewModel4;
        }
        diagnosticsTestViewModel2.getDiagnosticState().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.more.fragments.s1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RunWatchDiagnosticFragment.o(RunWatchDiagnosticFragment.this, (Boolean) obj);
            }
        });
    }

    public final void p(FragmentRunWatchDiagnosticsBinding fragmentRunWatchDiagnosticsBinding) {
        if (fragmentRunWatchDiagnosticsBinding.clInstructions.getVisibility() == 0) {
            ConstraintLayout clInstructions = fragmentRunWatchDiagnosticsBinding.clInstructions;
            Intrinsics.checkNotNullExpressionValue(clInstructions, "clInstructions");
            gone(clInstructions);
            ConstraintLayout clTests = fragmentRunWatchDiagnosticsBinding.clTests;
            Intrinsics.checkNotNullExpressionValue(clTests, "clTests");
            visible(clTests);
            this.n = true;
            fragmentRunWatchDiagnosticsBinding.btnRunDiagnostic.setText(getString(R.string.start));
        }
    }
}
