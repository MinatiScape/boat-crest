package com.coveiot.android.leonardo.more.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.SelectedAppDataForQrCodePush;
import com.coveiot.android.leonardo.more.adapters.QrCodeAppListAdapter;
import com.coveiot.android.leonardo.more.fragments.AddQRCodeToWatchFragment;
import com.coveiot.android.theme.BaseFragment;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SelectAppToPushQRCodeFragment extends BaseFragment implements AppClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public String m;
    @Nullable
    public QrCodeAppListAdapter n;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SelectAppToPushQRCodeFragment newInstance(@Nullable String str) {
            SelectAppToPushQRCodeFragment selectAppToPushQRCodeFragment = new SelectAppToPushQRCodeFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", str);
            selectAppToPushQRCodeFragment.setArguments(bundle);
            return selectAppToPushQRCodeFragment;
        }
    }

    public SelectAppToPushQRCodeFragment() {
        Intrinsics.checkNotNullExpressionValue(SelectAppToPushQRCodeFragment.class.getSimpleName(), "javaClass.simpleName");
    }

    public static final void l(SelectAppToPushQRCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }

    @JvmStatic
    @NotNull
    public static final SelectAppToPushQRCodeFragment newInstance(@Nullable String str) {
        return Companion.newInstance(str);
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

    public final void initListeners() {
        ((TextView) _$_findCachedViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectAppToPushQRCodeFragment.l(SelectAppToPushQRCodeFragment.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.fragments.AppClickListener
    public void onClick(@NotNull SelectedAppDataForQrCodePush appItem) {
        Intrinsics.checkNotNullParameter(appItem, "appItem");
        FragmentTransaction beginTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        AddQRCodeToWatchFragment.Companion companion = AddQRCodeToWatchFragment.Companion;
        beginTransaction.replace(R.id.fragment_container_add_qr_code, companion.newInstance(appItem)).addToBackStack(companion.toString()).commit();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_select_app_to_push_q_r_code, viewGroup, false);
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
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("type");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type kotlin.String");
            this.m = (String) serializable;
        }
        setUpView(this.m);
        initListeners();
    }

    public final void setUpView(@Nullable String str) {
        ((TextView) _$_findCachedViewById(R.id.card_type)).setText(str);
        List listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new SelectedAppDataForQrCodePush[]{new SelectedAppDataForQrCodePush(R.drawable.ic_fb_app_icon, "Facebook", str, 36), new SelectedAppDataForQrCodePush(R.drawable.ic_whatsapp_app_icon, "whatsApp", str, 37), new SelectedAppDataForQrCodePush(R.drawable.ic_twitter_app_icon, "Twitter", str, 38), new SelectedAppDataForQrCodePush(R.drawable.ic_instagram_app_icon, "Instagram", str, 39), new SelectedAppDataForQrCodePush(R.drawable.ic_skype_app_icon, "Skype", str, 43)});
        List listOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new SelectedAppDataForQrCodePush[]{new SelectedAppDataForQrCodePush(R.drawable.ic_paytm_app_icon, "Paytm", str, 5), new SelectedAppDataForQrCodePush(R.drawable.ic_phonepe_app_icon, "PhonePe", str, 6), new SelectedAppDataForQrCodePush(R.drawable.ic_gpay_app_icon, "Gpay", str, 7), new SelectedAppDataForQrCodePush(R.drawable.ic_bhim_app_icon, "BHIM", str, 8)});
        if (Intrinsics.areEqual(str, getString(R.string.wallet))) {
            ((TextView) _$_findCachedViewById(R.id.tv_card_select_text)).setText(getString(R.string.select_the_payment_method));
            ((TextView) _$_findCachedViewById(R.id.tv_qr_code_instructions)).setVisibility(0);
            this.n = new QrCodeAppListAdapter(listOf2, this);
        } else if (Intrinsics.areEqual(str, getString(R.string.business_card))) {
            ((TextView) _$_findCachedViewById(R.id.tv_card_select_text)).setText(getString(R.string.select_the_platform));
            ((TextView) _$_findCachedViewById(R.id.tv_qr_code_instructions)).setVisibility(8);
            this.n = new QrCodeAppListAdapter(listOf, this);
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rv_app_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.n);
    }
}
