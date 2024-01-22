package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts;
import com.coveiot.android.tappy.activity.AddNfcStrapFtuActivity;
import com.coveiot.android.tappy.adapter.RegisteredStrapAdapter;
import com.coveiot.android.tappy.customview.CardActionConfirmationDialog;
import com.coveiot.android.tappy.customview.FriendlyNameDialog;
import com.coveiot.android.tappy.databinding.FragmentStrapManagementBinding;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.TappyCleverTapConstants;
import com.coveiot.android.tappy.viewmodel.ManageViewModel;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.HashMap;
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
public final class StrapManagementFragment extends BaseFragment implements RegisteredStrapAdapter.StrapItemClickListener, SuccessResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentStrapManagementBinding m;
    @Nullable
    public Integer n;
    @Nullable
    public String o;
    public ManageViewModel p;
    public NfcStrapViewModel q;
    public RegisteredStrapAdapter r;
    public boolean s;
    @Nullable
    public Integer t;
    public long v;
    @Nullable
    public CardActionConfirmationDialog w;
    @Nullable
    public FriendlyNameDialog x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<RegStrapBeanInfo> u = new ArrayList<>();

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final StrapManagementFragment newInstance(long j) {
            StrapManagementFragment strapManagementFragment = new StrapManagementFragment();
            strapManagementFragment.setGlobalUserId(j);
            return strapManagementFragment;
        }
    }

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<Intent, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.putExtra(Constants.END_USER_GLOBAL_ID, StrapManagementFragment.this.getGlobalUserId());
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<List<? extends RegStrapBeanInfo>, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends RegStrapBeanInfo> list) {
            invoke2((List<RegStrapBeanInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<RegStrapBeanInfo> list) {
            RegisteredStrapAdapter registeredStrapAdapter = null;
            if (!(list == null || list.isEmpty())) {
                StrapManagementFragment.this.u.clear();
                for (RegStrapBeanInfo regStrapBeanInfo : list) {
                    if (!StrapManagementFragment.this.u.contains(regStrapBeanInfo)) {
                        StrapManagementFragment.this.u.add(regStrapBeanInfo);
                    }
                }
                RegisteredStrapAdapter registeredStrapAdapter2 = StrapManagementFragment.this.r;
                if (registeredStrapAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("strapAdapter");
                    registeredStrapAdapter2 = null;
                }
                registeredStrapAdapter2.setData(StrapManagementFragment.this.u);
                RecyclerView recyclerView = StrapManagementFragment.this.s().strapRv;
                RegisteredStrapAdapter registeredStrapAdapter3 = StrapManagementFragment.this.r;
                if (registeredStrapAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("strapAdapter");
                    registeredStrapAdapter3 = null;
                }
                recyclerView.setAdapter(registeredStrapAdapter3);
                RegisteredStrapAdapter registeredStrapAdapter4 = StrapManagementFragment.this.r;
                if (registeredStrapAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("strapAdapter");
                } else {
                    registeredStrapAdapter = registeredStrapAdapter4;
                }
                registeredStrapAdapter.notifyDataSetChanged();
                StrapManagementFragment.this.F();
                return;
            }
            StrapManagementFragment.this.u.clear();
            RegisteredStrapAdapter registeredStrapAdapter5 = StrapManagementFragment.this.r;
            if (registeredStrapAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("strapAdapter");
            } else {
                registeredStrapAdapter = registeredStrapAdapter5;
            }
            registeredStrapAdapter.notifyDataSetChanged();
            StrapManagementFragment.this.F();
        }
    }

    public static final void A(StrapManagementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CardActionConfirmationDialog cardActionConfirmationDialog = this$0.w;
        Intrinsics.checkNotNull(cardActionConfirmationDialog);
        cardActionConfirmationDialog.dismiss();
        this$0.w = null;
    }

    public static final void C(StrapManagementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FriendlyNameDialog friendlyNameDialog = this$0.x;
        Intrinsics.checkNotNull(friendlyNameDialog);
        friendlyNameDialog.dismiss();
    }

    public static final void D(EditText editText, StrapManagementFragment this$0, TextView textView, long j, long j2, View view) {
        ManageViewModel manageViewModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            Toast.makeText(this$0.requireActivity(), textView.getText().toString(), 1).show();
            return;
        }
        FriendlyNameDialog friendlyNameDialog = this$0.x;
        Intrinsics.checkNotNull(friendlyNameDialog);
        friendlyNameDialog.dismiss();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        this$0.s = true;
        this$0.o = editText.getText().toString();
        ManageViewModel manageViewModel2 = this$0.p;
        if (manageViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        } else {
            manageViewModel = manageViewModel2;
        }
        manageViewModel.updateRegisteredProductFriendlyName(editText.getText().toString(), j, j2);
        if (this$0.getActivity() instanceof ActivityManageRegisteredProducts) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts");
            ((ActivityManageRegisteredProducts) activity).refresh();
        }
    }

    public static final boolean E(Ref.BooleanRef isExpanding, EditText editText, StrapManagementFragment this$0, Ref.IntRef initialHeight, View view, MotionEvent motionEvent) {
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
                } else if (isExpanding.element && (height = editText.getHeight() + ((int) motionEvent.getY())) >= this$0.r(48)) {
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
    public static final StrapManagementFragment newInstance(long j) {
        return Companion.newInstance(j);
    }

    public static /* synthetic */ void u(StrapManagementFragment strapManagementFragment, CleverTapConstants.EventName eventName, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        strapManagementFragment.t(eventName, num);
    }

    public static final void v(StrapManagementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.getContext())) {
            this$0.showNoInternetMessage();
        } else {
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            a aVar = new a();
            Intent intent = new Intent(requireContext, AddNfcStrapFtuActivity.class);
            aVar.invoke((a) intent);
            requireContext.startActivity(intent, null);
        }
        u(this$0, CleverTapConstants.EventName.BC_PAY_NFC_PAIR_REQUEST, null, 2, null);
    }

    public static final void w(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(StrapManagementFragment this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CardActionConfirmationDialog cardActionConfirmationDialog = this$0.w;
        if (cardActionConfirmationDialog != null) {
            cardActionConfirmationDialog.dismiss();
        }
        ManageViewModel manageViewModel = null;
        this$0.w = null;
        this$0.n = Integer.valueOf(i);
        BaseFragment.showProgress$default(this$0, false, 1, null);
        ManageViewModel manageViewModel2 = this$0.p;
        if (manageViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
        } else {
            manageViewModel = manageViewModel2;
        }
        Long endUserID = this$0.u.get(i).getEndUserID();
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        Long endUserProductRegistrationID = this$0.u.get(i).getEndUserProductRegistrationID();
        Intrinsics.checkNotNull(endUserProductRegistrationID);
        manageViewModel.deleteRegisteredProduct(longValue, endUserProductRegistrationID.longValue());
    }

    public final void B(final long j, final long j2) {
        FriendlyNameDialog friendlyNameDialog;
        FriendlyNameDialog friendlyNameDialog2 = this.x;
        if (friendlyNameDialog2 != null) {
            Boolean valueOf = friendlyNameDialog2 != null ? Boolean.valueOf(friendlyNameDialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (friendlyNameDialog = this.x) != null) {
                friendlyNameDialog.dismiss();
            }
            this.x = null;
        }
        if (this.x == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FriendlyNameDialog friendlyNameDialog3 = new FriendlyNameDialog(requireActivity);
            this.x = friendlyNameDialog3;
            Intrinsics.checkNotNull(friendlyNameDialog3);
            friendlyNameDialog3.setCancelable(false);
            FriendlyNameDialog friendlyNameDialog4 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog4);
            friendlyNameDialog4.setCanceledOnTouchOutside(true);
            FriendlyNameDialog friendlyNameDialog5 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog5);
            Button button = (Button) friendlyNameDialog5.findViewById(R.id.btnSave);
            button.setText(getString(R.string.save));
            FriendlyNameDialog friendlyNameDialog6 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog6);
            final EditText editText = (EditText) friendlyNameDialog6.findViewById(R.id.edittext_strapName);
            FriendlyNameDialog friendlyNameDialog7 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog7);
            ImageView imageView = (ImageView) friendlyNameDialog7.findViewById(R.id.iv_close);
            imageView.setVisibility(8);
            FriendlyNameDialog friendlyNameDialog8 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog8);
            final TextView textView = (TextView) friendlyNameDialog8.findViewById(R.id.tvFriendlyNameCount);
            FriendlyNameDialog friendlyNameDialog9 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog9);
            final TextView textView2 = (TextView) friendlyNameDialog9.findViewById(R.id.tv_giveName);
            FriendlyNameDialog friendlyNameDialog10 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog10);
            ImageView imageView2 = (ImageView) friendlyNameDialog10.findViewById(R.id.ivExpandEdTv);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = editText.getHeight();
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.r1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StrapManagementFragment.C(StrapManagementFragment.this, view);
                }
            });
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.p1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StrapManagementFragment.D(editText, this, textView2, j, j2, view);
                }
            });
            imageView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.tappy.fragment.u1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean E;
                    E = StrapManagementFragment.E(Ref.BooleanRef.this, editText, this, intRef, view, motionEvent);
                    return E;
                }
            });
            FriendlyNameDialog friendlyNameDialog11 = this.x;
            Intrinsics.checkNotNull(friendlyNameDialog11);
            Window window = friendlyNameDialog11.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
            editText.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.tappy.fragment.StrapManagementFragment$showFriendlyNameDialog$4
                @Override // android.text.TextWatcher
                public void afterTextChanged(@Nullable Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                    int length = charSequence != null ? charSequence.length() : 0;
                    StringBuilder sb = new StringBuilder();
                    sb.append(length);
                    sb.append('/');
                    sb.append(50);
                    textView.setText(sb.toString());
                }
            });
        }
        FriendlyNameDialog friendlyNameDialog12 = this.x;
        Boolean valueOf2 = friendlyNameDialog12 != null ? Boolean.valueOf(friendlyNameDialog12.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue()) {
            return;
        }
        FriendlyNameDialog friendlyNameDialog13 = this.x;
        Intrinsics.checkNotNull(friendlyNameDialog13);
        friendlyNameDialog13.show();
        FriendlyNameDialog friendlyNameDialog14 = this.x;
        Intrinsics.checkNotNull(friendlyNameDialog14);
        Window window2 = friendlyNameDialog14.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    public final void F() {
        boolean isEmpty = this.u.isEmpty();
        s().strapRv.setVisibility(isEmpty ? 8 : 0);
        s().emptyStrapView.setVisibility(isEmpty ? 0 : 8);
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

    @Override // com.coveiot.android.tappy.adapter.RegisteredStrapAdapter.StrapItemClickListener
    public void editStrapName(@NotNull RegStrapBeanInfo regStrapBeanInfo, int i) {
        Intrinsics.checkNotNullParameter(regStrapBeanInfo, "regStrapBeanInfo");
        Long endUserID = regStrapBeanInfo.getEndUserID();
        if (endUserID != null) {
            long longValue = endUserID.longValue();
            Long endUserProductRegistrationID = regStrapBeanInfo.getEndUserProductRegistrationID();
            if (endUserProductRegistrationID != null) {
                B(longValue, endUserProductRegistrationID.longValue());
            }
        }
        this.t = Integer.valueOf(i);
        t(CleverTapConstants.EventName.BC_PAY_STRAPNAME_REQUEST, Integer.valueOf(i));
    }

    public final long getGlobalUserId() {
        return this.v;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentStrapManagementBinding.inflate(inflater, viewGroup, false);
        return s().getRoot();
    }

    @Override // com.coveiot.android.tappy.adapter.RegisteredStrapAdapter.StrapItemClickListener
    public void onDeleteClicked(int i) {
        if (!AppUtils.isNetConnected(requireContext())) {
            showNoInternetMessage();
        } else {
            boolean z = false;
            if (i >= 0 && i < this.u.size()) {
                z = true;
            }
            if (z) {
                y(i);
            }
        }
        t(CleverTapConstants.EventName.BC_PAY_DEREGISTEREDSTRAP_REQUEST, Integer.valueOf(i));
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (str != null) {
            BaseFragment.showCommonMessageDialog$default(this, str, false, 2, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        t(CleverTapConstants.EventName.BC_PAY_DEREGISTERSTRAP_SUCCESS, this.n);
        Integer num = this.n;
        if (num != null) {
            x(num.intValue());
        }
        this.n = null;
        if (this.s) {
            t(CleverTapConstants.EventName.BC_PAYT_STRAPNAME_SUCCESS, this.t);
        }
        dismissProgress();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        NfcStrapViewModel nfcStrapViewModel = null;
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_SETTINGS_STRAP_VIEWED.getValue(), null);
        s().btnAddStrap.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.s1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                StrapManagementFragment.v(StrapManagementFragment.this, view2);
            }
        });
        s().strapRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.r = new RegisteredStrapAdapter(requireContext, this);
        RecyclerView recyclerView = s().strapRv;
        RegisteredStrapAdapter registeredStrapAdapter = this.r;
        if (registeredStrapAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("strapAdapter");
            registeredStrapAdapter = null;
        }
        recyclerView.setAdapter(registeredStrapAdapter);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        ManageViewModel manageViewModel = (ManageViewModel) new ViewModelProvider(this, new ViewModelFactory(requireContext2)).get(ManageViewModel.class);
        this.p = manageViewModel;
        if (manageViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        }
        manageViewModel.setMListener(this);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        NfcStrapViewModel nfcStrapViewModel2 = (NfcStrapViewModel) new ViewModelProvider(requireActivity).get(NfcStrapViewModel.class);
        this.q = nfcStrapViewModel2;
        if (nfcStrapViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            nfcStrapViewModel = nfcStrapViewModel2;
        }
        LiveData<List<RegStrapBeanInfo>> registeredStrapInfoList = nfcStrapViewModel.getRegisteredStrapInfoList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        registeredStrapInfoList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.tappy.fragment.v1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StrapManagementFragment.w(Function1.this, obj);
            }
        });
    }

    public final int r(int i) {
        return (int) (i * getResources().getDisplayMetrics().density);
    }

    public final FragmentStrapManagementBinding s() {
        FragmentStrapManagementBinding fragmentStrapManagementBinding = this.m;
        Intrinsics.checkNotNull(fragmentStrapManagementBinding);
        return fragmentStrapManagementBinding;
    }

    public final void setGlobalUserId(long j) {
        this.v = j;
    }

    public final void t(CleverTapConstants.EventName eventName, Integer num) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (eventName == CleverTapConstants.EventName.BC_PAY_NFC_PAIR_REQUEST) {
            hashMap.put(TappyCleverTapConstants.PAIRLOCATION.getValue(), TappyCleverTapConstants.SETTINGS_STRAP_MGMNT_PAGE.getValue());
        } else {
            boolean z = false;
            if (eventName == CleverTapConstants.EventName.BC_PAYT_STRAPNAME_SUCCESS && num != null) {
                RegStrapBeanInfo regStrapBeanInfo = this.u.get(num.intValue());
                Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "regStrapBeanInfoList[position]");
                RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                if (regStrapBeanInfo2.getDeviceId() != null) {
                    String value = TappyCleverTapConstants.STRAP_ID.getValue();
                    Long deviceId = regStrapBeanInfo2.getDeviceId();
                    Intrinsics.checkNotNull(deviceId);
                    hashMap.put(value, deviceId);
                }
                String friendlyName = regStrapBeanInfo2.getFriendlyName();
                if (friendlyName == null || friendlyName.length() == 0) {
                    z = true;
                }
                if (!z) {
                    String value2 = TappyCleverTapConstants.STRAP_NAME.getValue();
                    String friendlyName2 = regStrapBeanInfo2.getFriendlyName();
                    Intrinsics.checkNotNull(friendlyName2);
                    hashMap.put(value2, friendlyName2);
                }
                if (this.o != null) {
                    String value3 = TappyCleverTapConstants.NEW_STRAP_NAME.getValue();
                    String str = this.o;
                    Intrinsics.checkNotNull(str);
                    hashMap.put(value3, str);
                }
                this.t = null;
            } else if (num != null) {
                RegStrapBeanInfo regStrapBeanInfo3 = this.u.get(num.intValue());
                Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo3, "regStrapBeanInfoList[position]");
                RegStrapBeanInfo regStrapBeanInfo4 = regStrapBeanInfo3;
                LogHelper.i("StrapDetails", "StrapId " + regStrapBeanInfo4.getDeviceId() + ", " + regStrapBeanInfo4.getFriendlyName() + ", " + regStrapBeanInfo4);
                if (regStrapBeanInfo4.getDeviceId() != null) {
                    String value4 = TappyCleverTapConstants.STRAP_ID.getValue();
                    Long deviceId2 = regStrapBeanInfo4.getDeviceId();
                    Intrinsics.checkNotNull(deviceId2);
                    hashMap.put(value4, deviceId2);
                }
                String friendlyName3 = regStrapBeanInfo4.getFriendlyName();
                if (friendlyName3 == null || friendlyName3.length() == 0) {
                    z = true;
                }
                if (!z) {
                    String value5 = TappyCleverTapConstants.STRAP_NAME.getValue();
                    String friendlyName4 = regStrapBeanInfo4.getFriendlyName();
                    Intrinsics.checkNotNull(friendlyName4);
                    hashMap.put(value5, friendlyName4);
                }
            }
        }
        DeviceUtils.Companion.logAnalyticsEvent(eventName.getValue(), hashMap);
    }

    public final void x(int i) {
        boolean z = false;
        if (i >= 0 && i < this.u.size()) {
            z = true;
        }
        if (z) {
            this.u.remove(i);
            RegisteredStrapAdapter registeredStrapAdapter = this.r;
            RegisteredStrapAdapter registeredStrapAdapter2 = null;
            if (registeredStrapAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("strapAdapter");
                registeredStrapAdapter = null;
            }
            registeredStrapAdapter.setData(this.u);
            RegisteredStrapAdapter registeredStrapAdapter3 = this.r;
            if (registeredStrapAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("strapAdapter");
            } else {
                registeredStrapAdapter2 = registeredStrapAdapter3;
            }
            registeredStrapAdapter2.notifyDataSetChanged();
            F();
            dismissProgress();
        }
    }

    public final void y(final int i) {
        if (this.w == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            CardActionConfirmationDialog cardActionConfirmationDialog = new CardActionConfirmationDialog(requireActivity);
            this.w = cardActionConfirmationDialog;
            Intrinsics.checkNotNull(cardActionConfirmationDialog);
            cardActionConfirmationDialog.setCancelable(false);
            CardActionConfirmationDialog cardActionConfirmationDialog2 = this.w;
            Intrinsics.checkNotNull(cardActionConfirmationDialog2);
            CardActionConfirmationDialog cardActionConfirmationDialog3 = this.w;
            Intrinsics.checkNotNull(cardActionConfirmationDialog3);
            ((Button) cardActionConfirmationDialog2.findViewById(R.id.btn_yes)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.t1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StrapManagementFragment.z(StrapManagementFragment.this, i, view);
                }
            });
            ((Button) cardActionConfirmationDialog3.findViewById(R.id.btn_No)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.q1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StrapManagementFragment.A(StrapManagementFragment.this, view);
                }
            });
            CardActionConfirmationDialog cardActionConfirmationDialog4 = this.w;
            Intrinsics.checkNotNull(cardActionConfirmationDialog4);
            Window window = cardActionConfirmationDialog4.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        CardActionConfirmationDialog cardActionConfirmationDialog5 = this.w;
        Boolean valueOf = cardActionConfirmationDialog5 != null ? Boolean.valueOf(cardActionConfirmationDialog5.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        CardActionConfirmationDialog cardActionConfirmationDialog6 = this.w;
        Intrinsics.checkNotNull(cardActionConfirmationDialog6);
        cardActionConfirmationDialog6.show();
        CardActionConfirmationDialog cardActionConfirmationDialog7 = this.w;
        Intrinsics.checkNotNull(cardActionConfirmationDialog7);
        Window window2 = cardActionConfirmationDialog7.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        CardActionConfirmationDialog cardActionConfirmationDialog8 = this.w;
        Intrinsics.checkNotNull(cardActionConfirmationDialog8);
        Window window3 = cardActionConfirmationDialog8.getWindow();
        if (window3 != null) {
            window3.setGravity(17);
        }
    }
}
