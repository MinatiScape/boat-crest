package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.AddPaymentCardActivity;
import com.coveiot.android.tappy.adapter.SelectBankAdapter;
import com.coveiot.android.tappy.customview.ErrorDialog;
import com.coveiot.android.tappy.databinding.FragmentSelectBankBinding;
import com.coveiot.android.tappy.model.SupportedBank;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.TappyCleverTapConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentSupportedBank extends BaseFragment implements SelectBankAdapter.OnBankSelectedListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentSelectBankBinding m;
    @Nullable
    public Long o;
    @Nullable
    public ErrorDialog p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<SupportedBank> n = new ArrayList<>();

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentSupportedBank newInstance(long j) {
            FragmentSupportedBank fragmentSupportedBank = new FragmentSupportedBank();
            fragmentSupportedBank.o = Long.valueOf(j);
            return fragmentSupportedBank;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentSupportedBank newInstance(long j) {
        return Companion.newInstance(j);
    }

    public static final void p(FragmentSupportedBank this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void r(FragmentSupportedBank this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_OPEN_ACCOUNT_TAPPED.getValue(), null);
        this$0.s();
    }

    public static final void t(FragmentSupportedBank this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ErrorDialog errorDialog = this$0.p;
        Intrinsics.checkNotNull(errorDialog);
        errorDialog.dismiss();
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_OPENAC_PRIMING_TAPPED.getValue(), null);
    }

    public static final void u(FragmentSupportedBank this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ErrorDialog errorDialog = this$0.p;
        Intrinsics.checkNotNull(errorDialog);
        errorDialog.dismiss();
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
    public final ArrayList<SupportedBank> getBankList() {
        return this.n;
    }

    @Nullable
    public final ErrorDialog getCreateAccountInfoDialog() {
        return this.p;
    }

    public final void initToolbar() {
        ((TextView) o().toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.select_bank));
        ((TextView) o().toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentSupportedBank.p(FragmentSupportedBank.this, view);
            }
        });
    }

    public final FragmentSelectBankBinding o() {
        FragmentSelectBankBinding fragmentSelectBankBinding = this.m;
        if (fragmentSelectBankBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentSelectBankBinding;
    }

    @Override // com.coveiot.android.tappy.adapter.SelectBankAdapter.OnBankSelectedListener
    public void onBankSelected(@NotNull SupportedBank selectBank) {
        Intrinsics.checkNotNullParameter(selectBank, "selectBank");
        q(selectBank.getBankName());
        Intent intent = new Intent(requireContext(), AddPaymentCardActivity.class);
        intent.putExtra(Constants.END_USER_GLOBAL_ID, this.o);
        intent.putExtra(Constants.BANK_ID, selectBank);
        startActivity(intent);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSelectBankBinding inflate = FragmentSelectBankBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.m = inflate;
        return o().getRoot();
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
        initToolbar();
        this.n.add(new SupportedBank(R.drawable.pink_strap, "IndusBank", "indus123"));
        this.n.add(new SupportedBank(R.drawable.black_strap, "YesBank", "yes123"));
        o().rvBank.setLayoutManager(new LinearLayoutManager(requireContext()));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        o().rvBank.setAdapter(new SelectBankAdapter(requireContext, this.n, this));
        o().tvCreateAccount.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.n1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSupportedBank.r(FragmentSupportedBank.this, view2);
            }
        });
        LogHelper.d("FragmentSupportedBank", String.valueOf(this.o));
    }

    public final void q(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(TappyCleverTapConstants.BANK_NAME.getValue(), str);
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_BANKSELECTED.getValue(), hashMap);
    }

    public final void s() {
        if (this.p == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ErrorDialog errorDialog = new ErrorDialog(requireContext);
            this.p = errorDialog;
            Intrinsics.checkNotNull(errorDialog);
            errorDialog.setCancelable(false);
            ErrorDialog errorDialog2 = this.p;
            Intrinsics.checkNotNull(errorDialog2);
            ((TextView) errorDialog2.findViewById(R.id.tvErrorTitle)).setVisibility(8);
            ErrorDialog errorDialog3 = this.p;
            Intrinsics.checkNotNull(errorDialog3);
            TextView textView = (TextView) errorDialog3.findViewById(R.id.tvErrorMsg);
            textView.setVisibility(0);
            textView.setText(getString(R.string.creat_bank_account_info));
            ErrorDialog errorDialog4 = this.p;
            Intrinsics.checkNotNull(errorDialog4);
            ImageView imageView = (ImageView) errorDialog4.findViewById(R.id.iv_close);
            imageView.setVisibility(0);
            ErrorDialog errorDialog5 = this.p;
            Intrinsics.checkNotNull(errorDialog5);
            Button button = (Button) errorDialog5.findViewById(R.id.bt_ok);
            button.setText(getString(R.string.proceed));
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.k1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentSupportedBank.t(FragmentSupportedBank.this, view);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.m1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentSupportedBank.u(FragmentSupportedBank.this, view);
                }
            });
            ErrorDialog errorDialog6 = this.p;
            Intrinsics.checkNotNull(errorDialog6);
            Window window = errorDialog6.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ErrorDialog errorDialog7 = this.p;
        Boolean valueOf = errorDialog7 != null ? Boolean.valueOf(errorDialog7.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        ErrorDialog errorDialog8 = this.p;
        Intrinsics.checkNotNull(errorDialog8);
        errorDialog8.show();
    }

    public final void setBankList(@NotNull ArrayList<SupportedBank> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.n = arrayList;
    }

    public final void setCreateAccountInfoDialog(@Nullable ErrorDialog errorDialog) {
        this.p = errorDialog;
    }
}
