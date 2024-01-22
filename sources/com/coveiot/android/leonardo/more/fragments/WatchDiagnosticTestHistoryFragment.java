package com.coveiot.android.leonardo.more.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.boat.databinding.FragmentWatchDiagnosticTestHistoryBinding;
import com.coveiot.android.leonardo.model.DiagnosticTestHistoryModel;
import com.coveiot.android.leonardo.more.adapters.DiagnosticTestHistoryAdapter;
import com.coveiot.android.leonardo.more.listeners.DiagnosticHistoryDownloadListener;
import com.coveiot.android.theme.BaseFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WatchDiagnosticTestHistoryFragment extends BaseFragment implements DiagnosticHistoryDownloadListener {
    @Nullable
    public FragmentWatchDiagnosticTestHistoryBinding m;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final DiagnosticTestHistoryAdapter n = new DiagnosticTestHistoryAdapter(this);
    @NotNull
    public ArrayList<DiagnosticTestHistoryModel> o = new ArrayList<>();

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

    @Override // com.coveiot.android.leonardo.more.listeners.DiagnosticHistoryDownloadListener
    public void downloadDiagnosticHistory(@NotNull String downloadUrl) {
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
    }

    public final FragmentWatchDiagnosticTestHistoryBinding k() {
        FragmentWatchDiagnosticTestHistoryBinding fragmentWatchDiagnosticTestHistoryBinding = this.m;
        Intrinsics.checkNotNull(fragmentWatchDiagnosticTestHistoryBinding);
        return fragmentWatchDiagnosticTestHistoryBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentWatchDiagnosticTestHistoryBinding.inflate(inflater, viewGroup, false);
        View root = k().getRoot();
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
        FragmentWatchDiagnosticTestHistoryBinding k = k();
        k.rvDiagnosticHistory.setAdapter(this.n);
        k.rvDiagnosticHistory.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.n.setDiagnosticTestHistoryList(this.o);
    }
}
