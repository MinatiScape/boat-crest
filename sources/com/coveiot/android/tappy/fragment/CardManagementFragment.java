package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.adapter.RegisteredCardAdapter;
import com.coveiot.android.tappy.customview.CardActionConfirmationDialog;
import com.coveiot.android.tappy.customview.ReasonInputDialog;
import com.coveiot.android.tappy.databinding.FragmentCardManagemntBinding;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenStatus;
import com.coveiot.android.tappy.model.RegCardBeanInfo;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.utils.Action;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.TappyCleverTapConstants;
import com.coveiot.android.tappy.viewmodel.ManageViewModel;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CardManagementFragment extends BaseFragment implements RegisteredCardAdapter.CardItemClickListener, SuccessResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentCardManagemntBinding m;
    @Nullable
    public Integer p;
    @Nullable
    public Integer q;
    @Nullable
    public Integer r;
    public ManageViewModel s;
    public RegisteredCardAdapter t;
    public NfcStrapViewModel u;
    public long v;
    public long w;
    @Nullable
    public CardActionConfirmationDialog x;
    @Nullable
    public ReasonInputDialog y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final ArrayList<RegStrapBeanInfo> n = new ArrayList<>();
    @NotNull
    public ArrayList<RegStrapBeanInfo> o = new ArrayList<>();

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final CardManagementFragment newInstance(long j) {
            CardManagementFragment cardManagementFragment = new CardManagementFragment();
            cardManagementFragment.setGlobalUserId(j);
            return cardManagementFragment;
        }
    }

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Action.values().length];
            try {
                iArr[Action.delete.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Action.suspend.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Action.resume.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<List<? extends RegStrapBeanInfo>, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends RegStrapBeanInfo> list) {
            invoke2((List<RegStrapBeanInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<RegStrapBeanInfo> list) {
            boolean z = false;
            RegisteredCardAdapter registeredCardAdapter = null;
            if (!(list == null || list.isEmpty())) {
                CardManagementFragment.this.o.clear();
                CardManagementFragment.this.o.addAll(list);
                CardManagementFragment.this.n.clear();
                Iterator it = CardManagementFragment.this.o.iterator();
                while (it.hasNext()) {
                    RegStrapBeanInfo regStrapBeanInfo = (RegStrapBeanInfo) it.next();
                    if (regStrapBeanInfo.isCardAdded()) {
                        CardManagementFragment.this.n.add(regStrapBeanInfo);
                    }
                }
                ArrayList arrayList = CardManagementFragment.this.n;
                if (arrayList == null || arrayList.isEmpty()) {
                    z = true;
                }
                if (!z) {
                    RegisteredCardAdapter registeredCardAdapter2 = CardManagementFragment.this.t;
                    if (registeredCardAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cardAdapter");
                    } else {
                        registeredCardAdapter = registeredCardAdapter2;
                    }
                    registeredCardAdapter.updateData(CardManagementFragment.this.n);
                    CardManagementFragment.this.C();
                    return;
                }
                CardManagementFragment.this.C();
                return;
            }
            CardManagementFragment.this.n.clear();
            RegisteredCardAdapter registeredCardAdapter3 = CardManagementFragment.this.t;
            if (registeredCardAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardAdapter");
            } else {
                registeredCardAdapter = registeredCardAdapter3;
            }
            registeredCardAdapter.notifyDataSetChanged();
            CardManagementFragment.this.C();
        }
    }

    public static final void A(CardManagementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReasonInputDialog reasonInputDialog = this$0.y;
        Intrinsics.checkNotNull(reasonInputDialog);
        reasonInputDialog.dismiss();
        this$0.y = null;
    }

    public static final boolean B(Ref.BooleanRef isExpanding, EditText editText, CardManagementFragment this$0, Ref.IntRef initialHeight, View view, MotionEvent motionEvent) {
        int height;
        Intrinsics.checkNotNullParameter(isExpanding, "$isExpanding");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(initialHeight, "$initialHeight");
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return false;
                    }
                } else if (isExpanding.element && (height = editText.getHeight() + ((int) motionEvent.getY())) >= this$0.q(48)) {
                    editText.getLayoutParams().height = height;
                    editText.requestLayout();
                }
            }
            isExpanding.element = false;
            editText.getLayoutParams().height = initialHeight.element;
            editText.requestLayout();
        } else {
            isExpanding.element = true;
        }
        return true;
    }

    @JvmStatic
    @NotNull
    public static final CardManagementFragment newInstance(long j) {
        return Companion.newInstance(j);
    }

    public static final void t(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void w(CardManagementFragment this$0, Action action, int i, View view) {
        ManageViewModel manageViewModel;
        RegCardBeanInfo regCardBeanInfo;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(action, "$action");
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showNoInternetMessage();
            return;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
        Long l = null;
        if (i2 == 1) {
            CardActionConfirmationDialog cardActionConfirmationDialog = this$0.x;
            if (cardActionConfirmationDialog != null) {
                cardActionConfirmationDialog.dismiss();
            }
            this$0.x = null;
            this$0.y(Action.delete, i);
        } else if (i2 == 2) {
            CardActionConfirmationDialog cardActionConfirmationDialog2 = this$0.x;
            if (cardActionConfirmationDialog2 != null) {
                cardActionConfirmationDialog2.dismiss();
            }
            this$0.x = null;
            this$0.y(Action.suspend, i);
        } else if (i2 != 3) {
        } else {
            CardActionConfirmationDialog cardActionConfirmationDialog3 = this$0.x;
            if (cardActionConfirmationDialog3 != null) {
                cardActionConfirmationDialog3.dismiss();
            }
            this$0.x = null;
            BaseFragment.showProgress$default(this$0, false, 1, null);
            ManageViewModel manageViewModel2 = this$0.s;
            if (manageViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                manageViewModel = null;
            } else {
                manageViewModel = manageViewModel2;
            }
            RegStrapBeanInfo regStrapBeanInfo = this$0.n.get(i);
            Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
            Intrinsics.checkNotNull(endUserID);
            long longValue = endUserID.longValue();
            RegStrapBeanInfo regStrapBeanInfo2 = this$0.n.get(i);
            if (regStrapBeanInfo2 != null && (regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo()) != null) {
                l = regCardBeanInfo.getPaymentInstrumentTokenId();
            }
            Intrinsics.checkNotNull(l);
            ManageViewModel.resumePaymentInstrumentTokens$default(manageViewModel, longValue, l.longValue(), null, 4, null);
            this$0.q = Integer.valueOf(i);
        }
    }

    public static final void x(CardManagementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CardActionConfirmationDialog cardActionConfirmationDialog = this$0.x;
        Intrinsics.checkNotNull(cardActionConfirmationDialog);
        cardActionConfirmationDialog.dismiss();
        this$0.x = null;
    }

    public static final void z(EditText editText, CardManagementFragment this$0, TextView textView, Action action, int i, View view) {
        ManageViewModel manageViewModel;
        RegCardBeanInfo regCardBeanInfo;
        ManageViewModel manageViewModel2;
        RegCardBeanInfo regCardBeanInfo2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(action, "$action");
        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            Toast.makeText(this$0.requireActivity(), textView.getText().toString(), 1).show();
            return;
        }
        ReasonInputDialog reasonInputDialog = this$0.y;
        Intrinsics.checkNotNull(reasonInputDialog);
        reasonInputDialog.dismiss();
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showNoInternetMessage();
            return;
        }
        String name = action.name();
        Long l = null;
        if (Intrinsics.areEqual(name, "delete")) {
            ManageViewModel manageViewModel3 = this$0.s;
            if (manageViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                manageViewModel2 = null;
            } else {
                manageViewModel2 = manageViewModel3;
            }
            RegStrapBeanInfo regStrapBeanInfo = this$0.n.get(i);
            Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
            Intrinsics.checkNotNull(endUserID);
            long longValue = endUserID.longValue();
            RegStrapBeanInfo regStrapBeanInfo2 = this$0.n.get(i);
            Long paymentInstrumentTokenId = (regStrapBeanInfo2 == null || (regCardBeanInfo2 = regStrapBeanInfo2.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo2.getPaymentInstrumentTokenId();
            Intrinsics.checkNotNull(paymentInstrumentTokenId);
            manageViewModel2.deletePaymentInstrumentTokens(longValue, paymentInstrumentTokenId.longValue(), editText.getText().toString());
            this$0.r = Integer.valueOf(i);
            BaseFragment.showProgress$default(this$0, false, 1, null);
        } else if (Intrinsics.areEqual(name, "suspend")) {
            BaseFragment.showProgress$default(this$0, false, 1, null);
            ManageViewModel manageViewModel4 = this$0.s;
            if (manageViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                manageViewModel = null;
            } else {
                manageViewModel = manageViewModel4;
            }
            RegStrapBeanInfo regStrapBeanInfo3 = this$0.n.get(i);
            Long endUserID2 = regStrapBeanInfo3 != null ? regStrapBeanInfo3.getEndUserID() : null;
            Intrinsics.checkNotNull(endUserID2);
            long longValue2 = endUserID2.longValue();
            RegStrapBeanInfo regStrapBeanInfo4 = this$0.n.get(i);
            if (regStrapBeanInfo4 != null && (regCardBeanInfo = regStrapBeanInfo4.getRegCardBeanInfo()) != null) {
                l = regCardBeanInfo.getPaymentInstrumentTokenId();
            }
            Intrinsics.checkNotNull(l);
            manageViewModel.suspendPaymentInstrumentTokens(longValue2, l.longValue(), editText.getText().toString());
            this$0.p = Integer.valueOf(i);
        }
    }

    public final void C() {
        ArrayList<RegStrapBeanInfo> arrayList = this.n;
        if (!(arrayList == null || arrayList.isEmpty())) {
            r().cardRv.setVisibility(0);
            r().emptyCardView.setVisibility(8);
            return;
        }
        r().emptyCardView.setVisibility(0);
        r().cardRv.setVisibility(8);
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

    @Nullable
    public final CardActionConfirmationDialog getCardActionConfirmationDialog() {
        return this.x;
    }

    @Nullable
    public final Integer getCardDeletePosition() {
        return this.r;
    }

    @Nullable
    public final Integer getCardResumePosition() {
        return this.q;
    }

    @Nullable
    public final Integer getCardSuspendPosition() {
        return this.p;
    }

    public final long getDeviceId() {
        return this.w;
    }

    public final long getGlobalUserId() {
        return this.v;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentCardManagemntBinding.inflate(inflater, viewGroup, false);
        return r().getRoot();
    }

    @Override // com.coveiot.android.tappy.adapter.RegisteredCardAdapter.CardItemClickListener
    public void onDeleteClicked(int i) {
        if (!AppUtils.isNetConnected(requireContext())) {
            showNoInternetMessage();
        } else {
            v(Action.delete, i);
        }
        s(CleverTapConstants.EventName.BC_PAY_CARDDELETE_REQUEST, i);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        dismissProgress();
        if (str != null) {
            Toast.makeText(requireContext(), str, 1).show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.coveiot.android.tappy.adapter.RegisteredCardAdapter.CardItemClickListener
    public void onResumeClicked(int i) {
        if (!AppUtils.isNetConnected(requireContext())) {
            showNoInternetMessage();
        } else {
            RegCardBeanInfo regCardBeanInfo = this.n.get(i).getRegCardBeanInfo();
            if (Intrinsics.areEqual(regCardBeanInfo != null ? regCardBeanInfo.getStatus() : null, PaymentInstrumentTokenStatus.SUSPENDED.getStatus())) {
                v(Action.resume, i);
            } else {
                Toast.makeText(getContext(), getString(R.string.card_is_not_activated), 0).show();
            }
        }
        s(CleverTapConstants.EventName.BC_PAY_RESUMECARD_REQUEST, i);
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        Integer num = this.r;
        if (num != null) {
            CleverTapConstants.EventName eventName = CleverTapConstants.EventName.BC_PAY_CARDDELETE_SUCCESS;
            Intrinsics.checkNotNull(num);
            s(eventName, num.intValue());
            Toast.makeText(requireActivity(), getString(R.string.your_card_deleted_successfully), 1).show();
        } else {
            Integer num2 = this.p;
            if (num2 != null) {
                CleverTapConstants.EventName eventName2 = CleverTapConstants.EventName.BC_PAY_SUSPENDCARD_SUCCESS;
                Intrinsics.checkNotNull(num2);
                s(eventName2, num2.intValue());
                Toast.makeText(requireActivity(), getString(R.string.your_card_suspended_successfully), 1).show();
            } else {
                CleverTapConstants.EventName eventName3 = CleverTapConstants.EventName.BC_PAY_RESUMEDCARD_SUCCESS;
                Integer num3 = this.q;
                Intrinsics.checkNotNull(num3);
                s(eventName3, num3.intValue());
                Toast.makeText(requireActivity(), getString(R.string.your_card_resumed_successfully), 1).show();
            }
        }
        Integer num4 = this.r;
        if (num4 != null) {
            u(num4.intValue());
        }
        this.r = null;
        Integer num5 = this.p;
        if (num5 != null) {
            int intValue = num5.intValue();
            RegisteredCardAdapter registeredCardAdapter = this.t;
            if (registeredCardAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardAdapter");
                registeredCardAdapter = null;
            }
            registeredCardAdapter.updateBtnResumeSuspend(Action.suspend, intValue);
        }
        this.p = null;
        Integer num6 = this.q;
        if (num6 != null) {
            int intValue2 = num6.intValue();
            RegisteredCardAdapter registeredCardAdapter2 = this.t;
            if (registeredCardAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardAdapter");
                registeredCardAdapter2 = null;
            }
            registeredCardAdapter2.updateBtnResumeSuspend(Action.resume, intValue2);
        }
        this.q = null;
        dismissProgress();
    }

    @Override // com.coveiot.android.tappy.adapter.RegisteredCardAdapter.CardItemClickListener
    public void onSuspendClicked(int i) {
        if (!AppUtils.isNetConnected(requireContext())) {
            showNoInternetMessage();
        } else {
            RegCardBeanInfo regCardBeanInfo = this.n.get(i).getRegCardBeanInfo();
            if (Intrinsics.areEqual(regCardBeanInfo != null ? regCardBeanInfo.getStatus() : null, PaymentInstrumentTokenStatus.ACTIVE.getStatus())) {
                v(Action.suspend, i);
            } else {
                Toast.makeText(getContext(), getString(R.string.card_is_not_activated), 0).show();
            }
        }
        s(CleverTapConstants.EventName.BC_PAY_SUSPENDCARD_REQUEST, i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ManageViewModel manageViewModel = (ManageViewModel) new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(ManageViewModel.class);
        this.s = manageViewModel;
        NfcStrapViewModel nfcStrapViewModel = null;
        if (manageViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        }
        manageViewModel.setMListener(this);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.u = (NfcStrapViewModel) new ViewModelProvider(requireActivity).get(NfcStrapViewModel.class);
        r().cardRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.t = new RegisteredCardAdapter(requireContext2, this);
        RecyclerView recyclerView = r().cardRv;
        RegisteredCardAdapter registeredCardAdapter = this.t;
        if (registeredCardAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardAdapter");
            registeredCardAdapter = null;
        }
        recyclerView.setAdapter(registeredCardAdapter);
        NfcStrapViewModel nfcStrapViewModel2 = this.u;
        if (nfcStrapViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            nfcStrapViewModel = nfcStrapViewModel2;
        }
        LiveData<List<RegStrapBeanInfo>> registeredStrapInfoList = nfcStrapViewModel.getRegisteredStrapInfoList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        registeredStrapInfoList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.tappy.fragment.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CardManagementFragment.t(Function1.this, obj);
            }
        });
    }

    public final int q(int i) {
        return (int) (i * getResources().getDisplayMetrics().density);
    }

    public final FragmentCardManagemntBinding r() {
        FragmentCardManagemntBinding fragmentCardManagemntBinding = this.m;
        Intrinsics.checkNotNull(fragmentCardManagemntBinding);
        return fragmentCardManagemntBinding;
    }

    public final void s(CleverTapConstants.EventName eventName, int i) {
        HashMap<String, Object> hashMap = new HashMap<>();
        RegStrapBeanInfo regStrapBeanInfo = this.n.get(i);
        Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredCardInfoList[position]");
        RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
        hashMap.put(TappyCleverTapConstants.REQUEST_LOCATION.getValue(), TappyCleverTapConstants.CARD_MGMNT.getValue());
        RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
        Intrinsics.checkNotNull(regCardBeanInfo);
        if (regCardBeanInfo.getLast4() != null) {
            RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo2.getRegCardBeanInfo();
            Intrinsics.checkNotNull(regCardBeanInfo2);
            String last4 = regCardBeanInfo2.getLast4();
            if (last4 != null) {
                hashMap.put(TappyCleverTapConstants.CARD_NOS.getValue(), last4);
            }
        }
        String friendlyName = regStrapBeanInfo2.getFriendlyName();
        boolean z = false;
        if (!(friendlyName == null || friendlyName.length() == 0)) {
            String value = TappyCleverTapConstants.CARD_LINKED_WITH.getValue();
            String friendlyName2 = regStrapBeanInfo2.getFriendlyName();
            Intrinsics.checkNotNull(friendlyName2);
            hashMap.put(value, friendlyName2);
        }
        RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo2.getRegCardBeanInfo();
        Intrinsics.checkNotNull(regCardBeanInfo3);
        String paymentNetworkName = regCardBeanInfo3.getPaymentNetworkName();
        if (paymentNetworkName == null || paymentNetworkName.length() == 0) {
            z = true;
        }
        if (!z) {
            RegCardBeanInfo regCardBeanInfo4 = regStrapBeanInfo2.getRegCardBeanInfo();
            Intrinsics.checkNotNull(regCardBeanInfo4);
            String paymentNetworkName2 = regCardBeanInfo4.getPaymentNetworkName();
            Intrinsics.checkNotNull(paymentNetworkName2);
            if (Intrinsics.areEqual(paymentNetworkName2, Constants.MASTERCARD)) {
                hashMap.put(TappyCleverTapConstants.CARD_TYPE.getValue(), TappyCleverTapConstants.MASTER.getValue());
            } else {
                hashMap.put(TappyCleverTapConstants.CARD_TYPE.getValue(), TappyCleverTapConstants.VISA.getValue());
            }
        }
        hashMap.put(TappyCleverTapConstants.INITIATED_BY.getValue(), TappyCleverTapConstants.USER.getValue());
        DeviceUtils.Companion.logAnalyticsEvent(eventName.getValue(), hashMap);
    }

    public final void setCardActionConfirmationDialog(@Nullable CardActionConfirmationDialog cardActionConfirmationDialog) {
        this.x = cardActionConfirmationDialog;
    }

    public final void setCardDeletePosition(@Nullable Integer num) {
        this.r = num;
    }

    public final void setCardResumePosition(@Nullable Integer num) {
        this.q = num;
    }

    public final void setCardSuspendPosition(@Nullable Integer num) {
        this.p = num;
    }

    public final void setDeviceId(long j) {
        this.w = j;
    }

    public final void setGlobalUserId(long j) {
        this.v = j;
    }

    public final void u(int i) {
        if (i < 0 || i >= this.n.size()) {
            return;
        }
        this.n.remove(i);
        RegisteredCardAdapter registeredCardAdapter = this.t;
        if (registeredCardAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardAdapter");
            registeredCardAdapter = null;
        }
        registeredCardAdapter.updateData(this.n);
        RecyclerView.Adapter adapter = r().cardRv.getAdapter();
        if (adapter != null) {
            adapter.notifyItemRemoved(i);
        }
        if (!this.n.isEmpty()) {
            r().cardRv.setVisibility(0);
            r().emptyCardView.setVisibility(8);
            return;
        }
        r().cardRv.setVisibility(8);
        r().emptyCardView.setVisibility(0);
    }

    public final void v(final Action action, final int i) {
        CardActionConfirmationDialog cardActionConfirmationDialog;
        CardActionConfirmationDialog cardActionConfirmationDialog2 = this.x;
        if (cardActionConfirmationDialog2 != null) {
            Boolean valueOf = cardActionConfirmationDialog2 != null ? Boolean.valueOf(cardActionConfirmationDialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (cardActionConfirmationDialog = this.x) != null) {
                cardActionConfirmationDialog.dismiss();
            }
            this.x = null;
        }
        if (this.x == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            CardActionConfirmationDialog cardActionConfirmationDialog3 = new CardActionConfirmationDialog(requireActivity);
            this.x = cardActionConfirmationDialog3;
            Intrinsics.checkNotNull(cardActionConfirmationDialog3);
            cardActionConfirmationDialog3.setCancelable(false);
            CardActionConfirmationDialog cardActionConfirmationDialog4 = this.x;
            Intrinsics.checkNotNull(cardActionConfirmationDialog4);
            TextView textView = (TextView) cardActionConfirmationDialog4.findViewById(R.id.tv_DeregisterStrap);
            CardActionConfirmationDialog cardActionConfirmationDialog5 = this.x;
            Intrinsics.checkNotNull(cardActionConfirmationDialog5);
            TextView textView2 = (TextView) cardActionConfirmationDialog5.findViewById(R.id.tvByProceeding);
            CardActionConfirmationDialog cardActionConfirmationDialog6 = this.x;
            Intrinsics.checkNotNull(cardActionConfirmationDialog6);
            TextView textView3 = (TextView) cardActionConfirmationDialog6.findViewById(R.id.tvAreYouSure);
            int i2 = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
            if (i2 == 1) {
                textView.setText(getString(R.string.delete_card));
                textView2.setText(getString(R.string.proceeding_this_strap_will_be_deleted));
                textView3.setText(getString(R.string.are_you_sure_you_want_to_delete_card));
            } else if (i2 == 2) {
                textView.setText(getString(R.string.suspend_card_q));
                textView2.setText(getString(R.string.proceeding_this_strap_will_be_suspended));
                textView3.setText(getString(R.string.are_you_sure_you_want_to_suspend_card));
            } else if (i2 == 3) {
                textView.setText(getString(R.string.resume_card_q));
                textView2.setText(getString(R.string.proceeding_this_strap_will_be_resumed));
                textView3.setText(getString(R.string.are_you_sure_you_want_to_resume_card));
            }
            CardActionConfirmationDialog cardActionConfirmationDialog7 = this.x;
            Intrinsics.checkNotNull(cardActionConfirmationDialog7);
            CardActionConfirmationDialog cardActionConfirmationDialog8 = this.x;
            Intrinsics.checkNotNull(cardActionConfirmationDialog8);
            ((TextView) cardActionConfirmationDialog7.findViewById(R.id.btn_yes)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CardManagementFragment.w(CardManagementFragment.this, action, i, view);
                }
            });
            ((TextView) cardActionConfirmationDialog8.findViewById(R.id.btn_No)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CardManagementFragment.x(CardManagementFragment.this, view);
                }
            });
            CardActionConfirmationDialog cardActionConfirmationDialog9 = this.x;
            Intrinsics.checkNotNull(cardActionConfirmationDialog9);
            Window window = cardActionConfirmationDialog9.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        CardActionConfirmationDialog cardActionConfirmationDialog10 = this.x;
        Boolean valueOf2 = cardActionConfirmationDialog10 != null ? Boolean.valueOf(cardActionConfirmationDialog10.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue()) {
            return;
        }
        CardActionConfirmationDialog cardActionConfirmationDialog11 = this.x;
        Intrinsics.checkNotNull(cardActionConfirmationDialog11);
        cardActionConfirmationDialog11.show();
        CardActionConfirmationDialog cardActionConfirmationDialog12 = this.x;
        Intrinsics.checkNotNull(cardActionConfirmationDialog12);
        Window window2 = cardActionConfirmationDialog12.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    public final void y(final Action action, final int i) {
        ReasonInputDialog reasonInputDialog;
        ReasonInputDialog reasonInputDialog2 = this.y;
        if (reasonInputDialog2 != null) {
            Boolean valueOf = reasonInputDialog2 != null ? Boolean.valueOf(reasonInputDialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (reasonInputDialog = this.y) != null) {
                reasonInputDialog.dismiss();
            }
            this.y = null;
        }
        if (this.y == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ReasonInputDialog reasonInputDialog3 = new ReasonInputDialog(requireActivity);
            this.y = reasonInputDialog3;
            Intrinsics.checkNotNull(reasonInputDialog3);
            reasonInputDialog3.setCancelable(false);
            ReasonInputDialog reasonInputDialog4 = this.y;
            Intrinsics.checkNotNull(reasonInputDialog4);
            Button button = (Button) reasonInputDialog4.findViewById(R.id.btnSave);
            ReasonInputDialog reasonInputDialog5 = this.y;
            Intrinsics.checkNotNull(reasonInputDialog5);
            TextView textView = (TextView) reasonInputDialog5.findViewById(R.id.btnCancel);
            ReasonInputDialog reasonInputDialog6 = this.y;
            Intrinsics.checkNotNull(reasonInputDialog6);
            final EditText editText = (EditText) reasonInputDialog6.findViewById(R.id.edittext_reason);
            ReasonInputDialog reasonInputDialog7 = this.y;
            Intrinsics.checkNotNull(reasonInputDialog7);
            final TextView textView2 = (TextView) reasonInputDialog7.findViewById(R.id.tv_giveName);
            ReasonInputDialog reasonInputDialog8 = this.y;
            Intrinsics.checkNotNull(reasonInputDialog8);
            ImageView imageView = (ImageView) reasonInputDialog8.findViewById(R.id.ivExpandEdTv);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = q(60);
            String name = action.name();
            if (Intrinsics.areEqual(name, "delete")) {
                int i2 = R.string.delete;
                button.setText(getString(i2));
                textView2.setText(getString(R.string.give_a_reason_to) + ' ' + getString(i2));
            } else if (Intrinsics.areEqual(name, "suspend")) {
                int i3 = R.string.suspend;
                button.setText(getString(i3));
                textView2.setText(getString(R.string.give_a_reason_to) + ' ' + getString(i3));
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CardManagementFragment.z(editText, this, textView2, action, i, view);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CardManagementFragment.A(CardManagementFragment.this, view);
                }
            });
            imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.tappy.fragment.e
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean B;
                    B = CardManagementFragment.B(Ref.BooleanRef.this, editText, this, intRef, view, motionEvent);
                    return B;
                }
            });
            ReasonInputDialog reasonInputDialog9 = this.y;
            Intrinsics.checkNotNull(reasonInputDialog9);
            Window window = reasonInputDialog9.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ReasonInputDialog reasonInputDialog10 = this.y;
        Boolean valueOf2 = reasonInputDialog10 != null ? Boolean.valueOf(reasonInputDialog10.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue()) {
            return;
        }
        ReasonInputDialog reasonInputDialog11 = this.y;
        Intrinsics.checkNotNull(reasonInputDialog11);
        reasonInputDialog11.show();
        ReasonInputDialog reasonInputDialog12 = this.y;
        Intrinsics.checkNotNull(reasonInputDialog12);
        Window window2 = reasonInputDialog12.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }
}
