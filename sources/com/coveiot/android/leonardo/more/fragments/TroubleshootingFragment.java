package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentTroubleshootingBinding;
import com.coveiot.android.leonardo.model.TroubleshootingModel;
import com.coveiot.android.leonardo.more.adapters.TroubleshootingAdapter;
import com.coveiot.android.leonardo.more.models.TroubleshootTestCategory;
import com.coveiot.android.leonardo.more.viewmodel.TroubleshootTestingViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.szabh.smable3.entity.BleNotificationSettings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootingFragment extends BaseFragment implements TroubleshootingAdapter.OnItemClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<TroubleshootingModel> m = new ArrayList();
    @Nullable
    public FragmentTroubleshootingBinding n;
    public TroubleshootingModel o;

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TroubleshootTestCategory.values().length];
            try {
                iArr[TroubleshootTestCategory.SMS_NOTIFICATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TroubleshootTestCategory.APP_NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TroubleshootTestCategory.CALL_NOTIFICATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TroubleshootTestCategory.BLUETOOTH_CALLING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void p(TroubleshootingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().finish();
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

    public final void initData() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        this.m.clear();
        List<TroubleshootingModel> list = this.m;
        String string = getString(R.string.connectivity_title_case);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connectivity_title_case)");
        list.add(new TroubleshootingModel(string, TroubleshootTestCategory.CONNECTIVITY));
        List<TroubleshootingModel> list2 = this.m;
        String string2 = getString(R.string.sms_notification_title_case);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sms_notification_title_case)");
        list2.add(new TroubleshootingModel(string2, TroubleshootTestCategory.SMS_NOTIFICATION));
        List<TroubleshootingModel> list3 = this.m;
        String string3 = getString(R.string.app_notification_title_case);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.app_notification_title_case)");
        list3.add(new TroubleshootingModel(string3, TroubleshootTestCategory.APP_NOTIFICATION));
        List<TroubleshootingModel> list4 = this.m;
        String string4 = getString(R.string.call_notification_title_case);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.call_notification_title_case)");
        list4.add(new TroubleshootingModel(string4, TroubleshootTestCategory.CALL_NOTIFICATION));
        BleApi bleApi = BleApiManager.getInstance(getContext()).getBleApi();
        boolean z = true;
        if (!((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isBTCallingSupported()) ? false : true)) {
            BleApi bleApi2 = BleApiManager.getInstance(getContext()).getBleApi();
            if (bleApi2 == null || (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
                z = false;
            }
            if (!z) {
                return;
            }
        }
        List<TroubleshootingModel> list5 = this.m;
        String string5 = getString(R.string.bluetooth_calling_title_case);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.bluetooth_calling_title_case)");
        list5.add(new TroubleshootingModel(string5, TroubleshootTestCategory.BLUETOOTH_CALLING));
    }

    public final FragmentTroubleshootingBinding l() {
        FragmentTroubleshootingBinding fragmentTroubleshootingBinding = this.n;
        Intrinsics.checkNotNull(fragmentTroubleshootingBinding);
        return fragmentTroubleshootingBinding;
    }

    public final void loadTroubleshootTestingFragment(@NotNull TroubleshootingModel clickedItem) {
        Intrinsics.checkNotNullParameter(clickedItem, "clickedItem");
        getParentFragmentManager().beginTransaction().replace(R.id.tfragment_container, TroubleshootTestingFragment.Companion.newInstance(clickedItem.getTroubleshootTestCategory().name())).addToBackStack(null).commit();
    }

    public final String m() {
        StringBuilder sb = new StringBuilder();
        sb.append("trouble ");
        TroubleshootingModel troubleshootingModel = this.o;
        TroubleshootingModel troubleshootingModel2 = null;
        if (troubleshootingModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemClicked");
            troubleshootingModel = null;
        }
        sb.append(troubleshootingModel);
        LogHelper.i("TroubleShootFragment", sb.toString());
        TroubleshootingModel troubleshootingModel3 = this.o;
        if (troubleshootingModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemClicked");
        } else {
            troubleshootingModel2 = troubleshootingModel3;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[troubleshootingModel2.getTroubleshootTestCategory().ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "bluetooth_calling" : NotificationCompat.CATEGORY_CALL : "app" : BleNotificationSettings.SMS;
    }

    public final void n() {
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_TROUBLESHOOT_SELECT.getValue());
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.HELP_FEEDBACK_SCREEN.getValue());
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.TROUBlESHOOTING.getValue());
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(FirebaseEventParams.MetaData.CV_TROUBLESHOOT_TYPE.getValue(), m());
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void o() {
        ((TextView) l().toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.troubleshooting));
        ((TextView) l().toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.b3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TroubleshootingFragment.p(TroubleshootingFragment.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentTroubleshootingBinding.inflate(inflater, viewGroup, false);
        return l().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.TroubleshootingAdapter.OnItemClickListener
    public void onItemClick(@NotNull TroubleshootingModel item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.o = item;
        n();
        if (item.getTroubleshootTestCategory() == TroubleshootTestCategory.CONNECTIVITY) {
            getParentFragmentManager().beginTransaction().replace(R.id.tfragment_container, FragmentTroubleShootConnectivity.Companion.newInstance()).addToBackStack(null).commit();
        } else if (AppUtils.isBluetoothEnabled(requireActivity())) {
            if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (!BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isDndSupported()) {
                    getParentFragmentManager().beginTransaction().replace(R.id.tfragment_container, TroubleshootingDndFragment.Companion.newInstance(item)).addToBackStack(null).commit();
                    return;
                }
                loadTroubleshootTestingFragment(item);
                return;
            }
            Toast.makeText(requireActivity(), getString(R.string.your_watch_disconnected), 1).show();
        } else {
            Toast.makeText(requireActivity(), (int) R.string.bluetooth_off_message, 0).show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        TroubleshootTestingViewModel troubleshootTestingViewModel = (TroubleshootTestingViewModel) new ViewModelProvider(requireActivity).get(TroubleshootTestingViewModel.class);
        o();
        initData();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv_troubleshoot_items);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        TroubleshootingAdapter troubleshootingAdapter = new TroubleshootingAdapter(requireContext, this.m);
        troubleshootingAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(troubleshootingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
