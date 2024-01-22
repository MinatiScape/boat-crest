package com.coveiot.android.leonardo.dashboard.health.spo2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2Result extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "FragmentSPO2Result";
    public ViewModelActivityDashboardNew m;
    public double n;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public Spo2DeviceType o = Spo2DeviceType.BLUETOOTH;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentSPO2Result newInstance(@NotNull Spo2DeviceType spo2DeviceType, double d) {
            Intrinsics.checkNotNullParameter(spo2DeviceType, "spo2DeviceType");
            FragmentSPO2Result fragmentSPO2Result = new FragmentSPO2Result();
            Bundle bundle = new Bundle();
            bundle.putDouble("spo2_val", d);
            bundle.putSerializable("spo2_device_type", spo2DeviceType);
            fragmentSPO2Result.setArguments(bundle);
            return fragmentSPO2Result;
        }
    }

    /* loaded from: classes3.dex */
    public interface OnLoadSPO2ResultListener {
        void loadBluetoothResultFragment(@NotNull Spo2DeviceType spo2DeviceType, double d);

        void showFailureDialog(@NotNull String str, @NotNull View.OnClickListener onClickListener);
    }

    public static final void l(FragmentSPO2Result this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.n > 0.0d) {
            this$0.m();
            return;
        }
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        Toast.makeText(activity, this$0.getString(R.string.some_thing_went_wrong), 0).show();
        FragmentActivity activity2 = this$0.getActivity();
        if (activity2 != null) {
            activity2.finish();
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentSPO2Result newInstance(@NotNull Spo2DeviceType spo2DeviceType, double d) {
        return Companion.newInstance(spo2DeviceType, d);
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

    public final void m() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        if (AppUtils.isNetConnected(context)) {
            ViewModelActivityDashboardNew viewModelActivityDashboardNew = this.m;
            if (viewModelActivityDashboardNew == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                viewModelActivityDashboardNew = null;
            }
            ViewModelActivityDashboardNew.saveSPO2Value$default(viewModelActivityDashboardNew, this.n, this.o, false, 4, null);
            if (getActivity() != null) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                activity.finish();
                return;
            }
            return;
        }
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
        ((BaseActivity) activity2).showNoInternetMessage();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.n = arguments.getDouble("spo2_val");
            Serializable serializable = arguments.getSerializable("spo2_device_type");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType");
            this.o = (Spo2DeviceType) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_spo2_bluetooth_result, viewGroup, false);
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
        LogHelper.d(TAG, "onViewCreated: called");
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.result));
        this.m = (ViewModelActivityDashboardNew) ViewModelProviders.of(this).get(ViewModelActivityDashboardNew.class);
        ((TextView) _$_findCachedViewById(R.id.spo2_result_val_tv)).setText(this.n + " %");
        ((TextView) _$_findCachedViewById(R.id.spo2_result_done_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2Result.l(FragmentSPO2Result.this, view2);
            }
        });
    }
}
