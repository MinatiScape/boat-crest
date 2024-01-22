package com.coveiot.android.leonardo.dashboard.health.spo2.fragment;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.adapters.SPO2MethodInfoAdapter;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.SPO2ListItem;
import com.coveiot.android.theme.BaseFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2MethodInfo extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSPO2MethodInfo newInstance() {
            return new FragmentSPO2MethodInfo();
        }
    }

    /* loaded from: classes3.dex */
    public static final class ItemDecorator extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        public final int f4744a;
        public final int b;

        public ItemDecorator(int i, int i2) {
            this.f4744a = i;
            this.b = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(outRect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            super.getItemOffsets(outRect, view, parent, state);
            int i = this.f4744a;
            outRect.right = i;
            outRect.left = i;
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = this.b;
            }
            outRect.bottom = this.b;
        }
    }

    public static final void m(FragmentSPO2MethodInfo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.finish();
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

    public final List<SPO2ListItem> l() {
        ArrayList arrayList = new ArrayList();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        String[] stringArray = activity.getResources().getStringArray(R.array.sp02_method_info_title_array);
        Intrinsics.checkNotNullExpressionValue(stringArray, "activity!!.resources.get…_method_info_title_array)");
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        String[] stringArray2 = activity2.getResources().getStringArray(R.array.sp02_method_info_sub_title_array);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "activity!!.resources.get…hod_info_sub_title_array)");
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3);
        TypedArray obtainTypedArray = activity3.getResources().obtainTypedArray(R.array.spo2_method_info_images_array);
        Intrinsics.checkNotNullExpressionValue(obtainTypedArray, "activity!!.resources.obt…method_info_images_array)");
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

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_spo2_method_info, viewGroup, false);
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
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2MethodInfo.m(FragmentSPO2MethodInfo.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.information));
        int i2 = R.id.rv_spo2_method_info;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(getActivity()));
        ((RecyclerView) _$_findCachedViewById(i2)).addItemDecoration(new ItemDecorator(getResources().getDimensionPixelSize(R.dimen.margin_2dp), getResources().getDimensionPixelSize(R.dimen.margin_24dp)));
        FragmentActivity activity = getActivity();
        SPO2MethodInfoAdapter sPO2MethodInfoAdapter = activity != null ? new SPO2MethodInfoAdapter(activity, l()) : null;
        if (sPO2MethodInfoAdapter != null) {
            sPO2MethodInfoAdapter.setItemClickListener(new SPO2MethodInfoAdapter.ItemClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2MethodInfo$onViewCreated$2
                @Override // com.coveiot.android.leonardo.dashboard.health.spo2.adapters.SPO2MethodInfoAdapter.ItemClickListener
                public void onItemClick(@NotNull String itemTitle) {
                    Intrinsics.checkNotNullParameter(itemTitle, "itemTitle");
                }
            });
        }
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(sPO2MethodInfoAdapter);
    }
}
