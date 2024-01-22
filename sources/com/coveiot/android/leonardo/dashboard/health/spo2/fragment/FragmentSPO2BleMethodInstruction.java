package com.coveiot.android.leonardo.dashboard.health.spo2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleDeviceScan;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2BleMethodInstruction extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentSPO2BleDeviceScan.OnLoadSPO2BleDeviceSelectionListener m;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSPO2BleMethodInstruction newInstance() {
            return new FragmentSPO2BleMethodInstruction();
        }
    }

    public static final void m(FragmentSPO2BleMethodInstruction this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.onBackPressed();
    }

    public static final void n(FragmentSPO2BleMethodInstruction this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentSPO2BleDeviceScan.OnLoadSPO2BleDeviceSelectionListener onLoadSPO2BleDeviceSelectionListener = this$0.m;
        if (onLoadSPO2BleDeviceSelectionListener != null) {
            onLoadSPO2BleDeviceSelectionListener.loadBleDeviceSelectionFragment();
        }
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

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_spo2_bluetooth_method_instruction, viewGroup, false);
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
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2BleMethodInstruction.m(FragmentSPO2BleMethodInstruction.this, view2);
            }
        });
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setVisibility(4);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.instructions));
        ((Button) _$_findCachedViewById(R.id.start_measurement_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2BleMethodInstruction.n(FragmentSPO2BleMethodInstruction.this, view2);
            }
        });
    }

    public final void setListener(@NotNull FragmentSPO2BleDeviceScan.OnLoadSPO2BleDeviceSelectionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.m = listener;
    }
}
