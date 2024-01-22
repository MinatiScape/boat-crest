package com.coveiot.android.leonardo.more.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentTroubleshoootingDndBinding;
import com.coveiot.android.leonardo.model.TroubleshootingModel;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootingDndFragment extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentTroubleshoootingDndBinding m;
    @Nullable
    public TroubleshootingModel n;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TroubleshootingDndFragment newInstance(@NotNull TroubleshootingModel clickedItem) {
            Intrinsics.checkNotNullParameter(clickedItem, "clickedItem");
            TroubleshootingDndFragment troubleshootingDndFragment = new TroubleshootingDndFragment();
            troubleshootingDndFragment.setClickedTroubleshootingModel(clickedItem);
            return troubleshootingDndFragment;
        }
    }

    public static final void o(TroubleshootingDndFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentTroubleshoootingDndBinding n = this$0.n();
        AppCompatButton appCompatButton = n != null ? n.cBtnConfirm : null;
        if (appCompatButton == null) {
            return;
        }
        appCompatButton.setEnabled(z);
    }

    public static final void p(TroubleshootingDndFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TroubleshootingModel troubleshootingModel = this$0.n;
        if (troubleshootingModel != null) {
            Intrinsics.checkNotNull(troubleshootingModel);
            this$0.loadTroubleshootTestingFragment(troubleshootingModel);
        }
    }

    public static final void r(TroubleshootingDndFragment this$0, View view) {
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

    @Nullable
    public final TroubleshootingModel getClickedTroubleshootingModel() {
        return this.n;
    }

    public final void loadTroubleshootTestingFragment(@NotNull TroubleshootingModel clickedItem) {
        Intrinsics.checkNotNullParameter(clickedItem, "clickedItem");
        getParentFragmentManager().beginTransaction().replace(R.id.tfragment_container, TroubleshootTestingFragment.Companion.newInstance(clickedItem.getTroubleshootTestCategory().name())).commitAllowingStateLoss();
    }

    public final FragmentTroubleshoootingDndBinding n() {
        FragmentTroubleshoootingDndBinding fragmentTroubleshoootingDndBinding = this.m;
        Intrinsics.checkNotNull(fragmentTroubleshoootingDndBinding);
        return fragmentTroubleshoootingDndBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentTroubleshoootingDndBinding.inflate(inflater, viewGroup, false);
        return n().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        AppCompatButton appCompatButton;
        CheckBox checkBox;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        q();
        FragmentTroubleshoootingDndBinding n = n();
        AppCompatButton appCompatButton2 = n != null ? n.cBtnConfirm : null;
        if (appCompatButton2 != null) {
            appCompatButton2.setEnabled(false);
        }
        FragmentTroubleshoootingDndBinding n2 = n();
        if (n2 != null && (checkBox = n2.cbDnd) != null) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.fragments.z2
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    TroubleshootingDndFragment.o(TroubleshootingDndFragment.this, compoundButton, z);
                }
            });
        }
        FragmentTroubleshoootingDndBinding n3 = n();
        if (n3 == null || (appCompatButton = n3.cBtnConfirm) == null) {
            return;
        }
        appCompatButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.y2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TroubleshootingDndFragment.p(TroubleshootingDndFragment.this, view2);
            }
        });
    }

    public final void q() {
        FragmentTroubleshoootingDndBinding n = n();
        if (n != null) {
            ((TextView) n.toolbar.findViewById(R.id.toolbar_title)).setVisibility(4);
            ((TextView) n.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.x2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TroubleshootingDndFragment.r(TroubleshootingDndFragment.this, view);
                }
            });
        }
    }

    public final void setClickedTroubleshootingModel(@Nullable TroubleshootingModel troubleshootingModel) {
        this.n = troubleshootingModel;
    }
}
