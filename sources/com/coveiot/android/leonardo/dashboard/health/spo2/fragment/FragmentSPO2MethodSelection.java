package com.coveiot.android.leonardo.dashboard.health.spo2.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew;
import com.coveiot.android.leonardo.dashboard.health.spo2.SPO2Dialog;
import com.coveiot.android.leonardo.dashboard.health.spo2.activity.ActivitySPO2MethodInfo;
import com.coveiot.android.leonardo.dashboard.health.spo2.adapters.SPO2MethodSelectionAdapter;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2Result;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.SPO2ListItem;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.textrecognition.base.BaseOcrResponse;
import com.coveiot.textrecognition.base.ResponseCallback;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2MethodSelection extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentSPO2MethodSelection";
    @Nullable
    public OnLoadSPO2MethodSelectionListener n;
    @Nullable
    public FragmentSPO2Result.OnLoadSPO2ResultListener o;
    public ViewModelActivityDashboardNew p;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSPO2MethodSelection newInstance() {
            return new FragmentSPO2MethodSelection();
        }
    }

    /* loaded from: classes3.dex */
    public static final class ItemDecorator extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        public final int f4745a;
        public final int b;

        public ItemDecorator(int i, int i2) {
            this.f4745a = i;
            this.b = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(outRect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            super.getItemOffsets(outRect, view, parent, state);
            int i = this.f4745a;
            outRect.right = i;
            outRect.left = i;
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = this.b;
            }
            outRect.bottom = this.b;
        }
    }

    /* loaded from: classes3.dex */
    public interface OnLoadSPO2MethodSelectionListener {
        void loadBluetoothInstructionFragment();

        void loadOcrScanFragment(@NotNull ResponseCallback<BaseOcrResponse> responseCallback);
    }

    public static final void p(FragmentSPO2MethodSelection this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.onBackPressed();
    }

    public static final void q(FragmentSPO2MethodSelection this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        this$0.startActivity(new Intent(activity, ActivitySPO2MethodInfo.class));
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

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    public final List<SPO2ListItem> m() {
        ArrayList arrayList = new ArrayList();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        String[] stringArray = activity.getResources().getStringArray(R.array.sp02_method_title_array);
        Intrinsics.checkNotNullExpressionValue(stringArray, "activity!!.resources.get….sp02_method_title_array)");
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        String[] stringArray2 = activity2.getResources().getStringArray(R.array.sp02_method_sub_title_array);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "activity!!.resources.get…2_method_sub_title_array)");
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3);
        TypedArray obtainTypedArray = activity3.getResources().obtainTypedArray(R.array.spo2_method_images_array);
        Intrinsics.checkNotNullExpressionValue(obtainTypedArray, "activity!!.resources.obt…spo2_method_images_array)");
        if (stringArray.length == stringArray2.length && stringArray.length == obtainTypedArray.length()) {
            int length = stringArray.length;
            for (int i = 0; i < length; i++) {
                String value = stringArray[i];
                Intrinsics.checkNotNullExpressionValue(value, "value");
                String str = stringArray2[i];
                Intrinsics.checkNotNullExpressionValue(str, "spo2MethodInfoDesc[index]");
                arrayList.add(new SPO2ListItem(value, str, obtainTypedArray.getResourceId(i, 0)));
            }
        }
        return arrayList;
    }

    public final void n(String str) {
        Context context = getContext();
        if (Intrinsics.areEqual(str, context != null ? context.getString(R.string.manual_entry) : null)) {
            SPO2Dialog.Companion companion = SPO2Dialog.Companion;
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            String string = getString(R.string.spo2);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.spo2)");
            companion.showSPO2Dialog(context2, string, new SPO2Dialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2MethodSelection$handleItemClick$1
                @Override // com.coveiot.android.leonardo.dashboard.health.spo2.SPO2Dialog.Companion.SelectionListener
                public void onValueSelected(double d) {
                    ViewModelActivityDashboardNew viewModelActivityDashboardNew;
                    Context context3 = FragmentSPO2MethodSelection.this.getContext();
                    Intrinsics.checkNotNull(context3);
                    if (AppUtils.isNetConnected(context3)) {
                        viewModelActivityDashboardNew = FragmentSPO2MethodSelection.this.p;
                        if (viewModelActivityDashboardNew == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            viewModelActivityDashboardNew = null;
                        }
                        ViewModelActivityDashboardNew.saveSPO2Value$default(viewModelActivityDashboardNew, d, Spo2DeviceType.MANUAL, false, 4, null);
                        if (FragmentSPO2MethodSelection.this.getActivity() != null) {
                            FragmentActivity activity = FragmentSPO2MethodSelection.this.getActivity();
                            Intrinsics.checkNotNull(activity);
                            activity.finish();
                            return;
                        }
                        return;
                    }
                    FragmentActivity activity2 = FragmentSPO2MethodSelection.this.getActivity();
                    Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
                    ((BaseActivity) activity2).showNoInternetMessage();
                }
            });
            return;
        }
        Context context3 = getContext();
        if (Intrinsics.areEqual(str, context3 != null ? context3.getString(R.string.bluetooth_device) : null)) {
            Context context4 = getContext();
            Intrinsics.checkNotNull(context4);
            if (AppUtils.isNetConnected(context4)) {
                OnLoadSPO2MethodSelectionListener onLoadSPO2MethodSelectionListener = this.n;
                if (onLoadSPO2MethodSelectionListener != null) {
                    onLoadSPO2MethodSelectionListener.loadBluetoothInstructionFragment();
                    return;
                }
                return;
            }
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
            ((BaseActivity) activity).showNoInternetMessage();
            return;
        }
        Context context5 = getContext();
        if (Intrinsics.areEqual(str, context5 != null ? context5.getString(R.string.camera_scan) : null)) {
            Context context6 = getContext();
            Intrinsics.checkNotNull(context6);
            if (AppUtils.isNetConnected(context6)) {
                o();
                return;
            }
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
            ((BaseActivity) activity2).showNoInternetMessage();
        }
    }

    public final void o() {
        OnLoadSPO2MethodSelectionListener onLoadSPO2MethodSelectionListener = this.n;
        if (onLoadSPO2MethodSelectionListener != null) {
            onLoadSPO2MethodSelectionListener.loadOcrScanFragment(new FragmentSPO2MethodSelection$loadOcrScanFragment$1(this));
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_spo2_method_selection, viewGroup, false);
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
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2MethodSelection.p(FragmentSPO2MethodSelection.this, view2);
            }
        });
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setImageResource(R.drawable.info_icon_new);
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.measure_spo2));
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2MethodSelection.q(FragmentSPO2MethodSelection.this, view2);
            }
        });
        this.p = (ViewModelActivityDashboardNew) ViewModelProviders.of(this).get(ViewModelActivityDashboardNew.class);
        int i2 = R.id.rv_spo2_mode;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(getActivity()));
        ((RecyclerView) _$_findCachedViewById(i2)).addItemDecoration(new ItemDecorator(getResources().getDimensionPixelSize(R.dimen.margin_2dp), getResources().getDimensionPixelSize(R.dimen.margin_24dp)));
        FragmentActivity activity = getActivity();
        SPO2MethodSelectionAdapter sPO2MethodSelectionAdapter = activity != null ? new SPO2MethodSelectionAdapter(activity, m()) : null;
        if (sPO2MethodSelectionAdapter != null) {
            sPO2MethodSelectionAdapter.setItemClickListener(new SPO2MethodSelectionAdapter.ItemClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2MethodSelection$onViewCreated$3
                @Override // com.coveiot.android.leonardo.dashboard.health.spo2.adapters.SPO2MethodSelectionAdapter.ItemClickListener
                public void onItemClick(@NotNull String itemTitle) {
                    Intrinsics.checkNotNullParameter(itemTitle, "itemTitle");
                    FragmentSPO2MethodSelection.this.n(itemTitle);
                }
            });
        }
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(sPO2MethodSelectionAdapter);
    }

    public final void setListener(@NotNull OnLoadSPO2MethodSelectionListener listener, @NotNull FragmentSPO2Result.OnLoadSPO2ResultListener resultListener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(resultListener, "resultListener");
        this.n = listener;
        this.o = resultListener;
    }
}
