package com.coveiot.android.dashboard2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.dashboard2.databinding.SetupWatchLayoutBinding;
import com.coveiot.android.dashboard2.model.SetupYourWatchDataModel;
import com.coveiot.android.dashboard2.model.SetupYourWatchOption;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SetupYourWatchItemFragment extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public SetupWatchLayoutBinding m;
    @Nullable
    public SetupYourWatchDataModel n;
    @Nullable
    public SetupYourWatchItemSelectedListener o;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SetupYourWatchItemFragment newInstance(@NotNull SetupYourWatchDataModel setupYourWatchDataModel, @Nullable SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener) {
            Intrinsics.checkNotNullParameter(setupYourWatchDataModel, "setupYourWatchDataModel");
            SetupYourWatchItemFragment setupYourWatchItemFragment = new SetupYourWatchItemFragment();
            setupYourWatchItemFragment.n = setupYourWatchDataModel;
            setupYourWatchItemFragment.o = setupYourWatchItemSelectedListener;
            return setupYourWatchItemFragment;
        }
    }

    /* loaded from: classes4.dex */
    public interface SetupYourWatchItemSelectedListener {
        void onSetupYourWatchItemSelected(@NotNull SetupYourWatchOption setupYourWatchOption);
    }

    public static final void m(SetupYourWatchItemFragment this$0, View view) {
        SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener;
        SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener2;
        SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener3;
        SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener4;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SetupYourWatchDataModel setupYourWatchDataModel = this$0.n;
        String option = setupYourWatchDataModel != null ? setupYourWatchDataModel.getOption() : null;
        if (option != null) {
            switch (option.hashCode()) {
                case -617127870:
                    if (option.equals("WATCH_FACE_CHANGE") && (setupYourWatchItemSelectedListener = this$0.o) != null) {
                        setupYourWatchItemSelectedListener.onSetupYourWatchItemSelected(SetupYourWatchOption.WATCH_FACE_CHANGE);
                        return;
                    }
                    return;
                case -147392920:
                    if (!option.equals("WATCH_FACE_STUDIO") || (setupYourWatchItemSelectedListener2 = this$0.o) == null) {
                        return;
                    }
                    setupYourWatchItemSelectedListener2.onSetupYourWatchItemSelected(SetupYourWatchOption.WATCH_FACE_STUDIO);
                    return;
                case 234808755:
                    if (option.equals("WATCH_SETTINGS") && (setupYourWatchItemSelectedListener3 = this$0.o) != null) {
                        setupYourWatchItemSelectedListener3.onSetupYourWatchItemSelected(SetupYourWatchOption.WATCH_SETTINGS);
                        return;
                    }
                    return;
                case 505652983:
                    if (option.equals("SPORT_LIVE") && (setupYourWatchItemSelectedListener4 = this$0.o) != null) {
                        setupYourWatchItemSelectedListener4.onSetupYourWatchItemSelected(SetupYourWatchOption.SPORT_LIVE);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final SetupYourWatchItemFragment newInstance(@NotNull SetupYourWatchDataModel setupYourWatchDataModel, @Nullable SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener) {
        return Companion.newInstance(setupYourWatchDataModel, setupYourWatchItemSelectedListener);
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

    public final SetupWatchLayoutBinding l() {
        SetupWatchLayoutBinding setupWatchLayoutBinding = this.m;
        Intrinsics.checkNotNull(setupWatchLayoutBinding);
        return setupWatchLayoutBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = SetupWatchLayoutBinding.inflate(inflater, viewGroup, false);
        return l().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        View root;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ImageView imageView = l().ivWatchBackground;
        SetupYourWatchDataModel setupYourWatchDataModel = this.n;
        imageView.setImageDrawable(setupYourWatchDataModel != null ? setupYourWatchDataModel.getDrawable() : null);
        ConstraintLayout constraintLayout = l().clBgContainer;
        SetupYourWatchDataModel setupYourWatchDataModel2 = this.n;
        constraintLayout.setBackground(setupYourWatchDataModel2 != null ? setupYourWatchDataModel2.getBackgroundDrawable() : null);
        TextView textView = l().tvInfo;
        SetupYourWatchDataModel setupYourWatchDataModel3 = this.n;
        textView.setText(setupYourWatchDataModel3 != null ? setupYourWatchDataModel3.getInfoText() : null);
        SetupYourWatchDataModel setupYourWatchDataModel4 = this.n;
        String headerTextCenter = setupYourWatchDataModel4 != null ? setupYourWatchDataModel4.getHeaderTextCenter() : null;
        boolean z = true;
        if (headerTextCenter == null || headerTextCenter.length() == 0) {
            l().tvWatchSettingHeader.setVisibility(8);
        } else {
            TextView textView2 = l().tvWatchSettingHeader;
            SetupYourWatchDataModel setupYourWatchDataModel5 = this.n;
            textView2.setText(setupYourWatchDataModel5 != null ? setupYourWatchDataModel5.getHeaderTextCenter() : null);
            l().tvWatchSettingHeader.setVisibility(0);
        }
        SetupYourWatchDataModel setupYourWatchDataModel6 = this.n;
        String headerTextEnd = setupYourWatchDataModel6 != null ? setupYourWatchDataModel6.getHeaderTextEnd() : null;
        if (headerTextEnd != null && headerTextEnd.length() != 0) {
            z = false;
        }
        if (z) {
            l().tvWatchSettingRightHeader.setVisibility(8);
        } else {
            TextView textView3 = l().tvWatchSettingRightHeader;
            SetupYourWatchDataModel setupYourWatchDataModel7 = this.n;
            textView3.setText(setupYourWatchDataModel7 != null ? setupYourWatchDataModel7.getHeaderTextEnd() : null);
            l().tvWatchSettingRightHeader.setVisibility(0);
        }
        SetupWatchLayoutBinding l = l();
        if (l == null || (root = l.getRoot()) == null) {
            return;
        }
        root.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.x1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SetupYourWatchItemFragment.m(SetupYourWatchItemFragment.this, view2);
            }
        });
    }
}
