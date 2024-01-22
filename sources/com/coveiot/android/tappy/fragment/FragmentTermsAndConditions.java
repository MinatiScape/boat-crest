package com.coveiot.android.tappy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.activity.TermsAndConditionsActivity;
import com.coveiot.android.tappy.databinding.FragmentTermsAndConditionsBinding;
import com.coveiot.android.tappy.utils.TappyCleverTapConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentTermsAndConditions extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentTermsAndConditionsBinding m;
    @Nullable
    public String n;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentTermsAndConditions newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentTermsAndConditions fragmentTermsAndConditions = new FragmentTermsAndConditions();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentTermsAndConditions.setArguments(bundle);
            return fragmentTermsAndConditions;
        }
    }

    public static final void n(FragmentTermsAndConditions this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.m();
        if (this$0.getActivity() instanceof TermsAndConditionsActivity) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.TermsAndConditionsActivity");
            ((TermsAndConditionsActivity) activity).acceptTermsAndConditions();
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentTermsAndConditions newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
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

    public final FragmentTermsAndConditionsBinding l() {
        FragmentTermsAndConditionsBinding fragmentTermsAndConditionsBinding = this.m;
        Intrinsics.checkNotNull(fragmentTermsAndConditionsBinding);
        return fragmentTermsAndConditionsBinding;
    }

    public final void m() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(TappyCleverTapConstants.STRAP_STATUS.getValue(), TappyCleverTapConstants.CONNECTED.getValue());
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_TNC_REQUEST.getValue(), hashMap);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.n = arguments.getString("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentTermsAndConditionsBinding.inflate(inflater, viewGroup, false);
        return l().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    @RequiresApi(26)
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        l().tvTermsAndConditions.setText(this.n);
        l().btnSelect.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.o1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentTermsAndConditions.n(FragmentTermsAndConditions.this, view2);
            }
        });
    }
}
