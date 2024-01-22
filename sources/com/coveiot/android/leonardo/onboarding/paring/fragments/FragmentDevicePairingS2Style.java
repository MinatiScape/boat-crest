package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentDevicePairingS2Style extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentDevicePairingS2Style newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentDevicePairingS2Style fragmentDevicePairingS2Style = new FragmentDevicePairingS2Style();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentDevicePairingS2Style.setArguments(bundle);
            return fragmentDevicePairingS2Style;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentDevicePairingS2Style newInstance(@NotNull String str, @NotNull String str2) {
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

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.smaF2) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s2_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.smaR9) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s12_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.SMA_WAVE_GENESIS_PRO) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s2_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.SMA_WAVE_ELEVATE_PRO) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s2_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.SMA_WAVE_GLORY_PRO) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s2_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.SMA_ULTIMA_VOGUE) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s2_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.SMA_LUNAR_SEEK) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s12_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.SMA_LUNAR_COMET) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s12_style, viewGroup, false);
        }
        if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.SMA_LUNAR_VELOCITY) {
            return inflater.inflate(R.layout.fragment_devcie_pairing_s12_style, viewGroup, false);
        }
        return inflater.inflate(R.layout.fragment_devcie_pairing_s2_style, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
    }
}
