package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.AddPaymentCardActivity;
import com.coveiot.android.tappy.databinding.FragmentCardDetailsBinding;
import com.coveiot.android.tappy.nfc.NfcUtility;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.CreditCardNumberFormattingTextWatcher;
import com.coveiot.android.tappy.utils.LocalAPDUCommands;
import com.coveiot.android.tappy.utils.TappyCleverTapConstants;
import com.coveiot.android.tappy.utils.Utils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.CleverTapConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentCardDetails extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentCardDetailsBinding m;
    @Nullable
    public String n;
    @Nullable
    public String o;
    @Nullable
    public String p;
    @Nullable
    public String q;
    @Nullable
    public String r;
    public boolean t;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle v;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String s = "FragmentCardDetails";
    @NotNull
    public final FragmentCardDetails$textWatcher$1 u = new TextWatcher() { // from class: com.coveiot.android.tappy.fragment.FragmentCardDetails$textWatcher$1
        @Override // android.text.TextWatcher
        public void afterTextChanged(@Nullable Editable editable) {
            FragmentCardDetailsBinding m;
            m = FragmentCardDetails.this.m();
            m.btnSaveCard.setEnabled(FragmentCardDetails.this.areAllFieldsFilled());
            FragmentCardDetails.this.setEnterDetailsManually(true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        }
    };

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentCardDetails newInstance$default(Companion companion, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                str3 = null;
            }
            if ((i & 8) != 0) {
                str4 = null;
            }
            if ((i & 16) != 0) {
                str5 = null;
            }
            return companion.newInstance(str, str2, str3, str4, str5);
        }

        @JvmStatic
        @NotNull
        public final FragmentCardDetails newInstance(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
            FragmentCardDetails fragmentCardDetails = new FragmentCardDetails();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.CARD_NUMBER, str);
            bundle.putString(Constants.CARD_HOLDER_NAME, str2);
            bundle.putString(Constants.CARD_EXPIRY_MONTH, str3);
            bundle.putString(Constants.CARD_EXPIRY_YEAR, str4);
            bundle.putString(Constants.CVV, str5);
            fragmentCardDetails.setArguments(bundle);
            return fragmentCardDetails;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentCardDetails newInstance(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        return Companion.newInstance(str, str2, str3, str4, str5);
    }

    public static final void o(FragmentCardDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NfcUtility.Companion companion = NfcUtility.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.hasNFC(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (companion.isNFCOn(requireContext2)) {
                Utils.Companion companion2 = Utils.Companion;
                if (companion2.isValidCardNumber(StringsKt__StringsKt.trim(kotlin.text.m.replace$default(String.valueOf(this$0.m().edtCardNumber.getText()), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null)).toString())) {
                    this$0.n();
                    if (!(String.valueOf(this$0.m().edtCardName.getText()).length() == 0)) {
                        String valueOf = String.valueOf(this$0.m().edtExpiryMm.getText());
                        if (valueOf.length() >= 5) {
                            String substring = valueOf.substring(0, 2);
                            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                            if (companion2.isValidExpiryMonth(substring)) {
                                String substring2 = valueOf.substring(3, 5);
                                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                                if (companion2.isValidExpiryYear(substring2)) {
                                    if (companion2.isValidCvv(String.valueOf(this$0.m().edtCvv.getText()))) {
                                        if (this$0.getActivity() instanceof AddPaymentCardActivity) {
                                            FragmentActivity activity = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            ((AddPaymentCardActivity) activity).setCardNumber(StringsKt__StringsKt.trim(kotlin.text.m.replace$default(String.valueOf(this$0.m().edtCardNumber.getText()), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null)).toString());
                                            FragmentActivity activity2 = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            ((AddPaymentCardActivity) activity2).setCardHolderName(StringsKt__StringsKt.trim(String.valueOf(this$0.m().edtCardName.getText())).toString());
                                            String obj = StringsKt__StringsKt.trim(String.valueOf(this$0.m().edtExpiryMm.getText())).toString();
                                            FragmentActivity activity3 = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            String substring3 = obj.substring(0, 2);
                                            Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                                            ((AddPaymentCardActivity) activity3).setExpiryMonth(substring3);
                                            FragmentActivity activity4 = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            if (((AddPaymentCardActivity) activity4).getPaymentNetworkId() == LocalAPDUCommands.PAYMENTNETWORK_VISA) {
                                                FragmentActivity activity5 = this$0.getActivity();
                                                Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                                String substring4 = obj.substring(3, 5);
                                                Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                                                ((AddPaymentCardActivity) activity5).setExpiryYear(companion2.get4DigitYear(substring4));
                                            } else {
                                                FragmentActivity activity6 = this$0.getActivity();
                                                Intrinsics.checkNotNull(activity6, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                                String substring5 = obj.substring(3, 5);
                                                Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
                                                ((AddPaymentCardActivity) activity6).setExpiryYear(substring5);
                                            }
                                            FragmentActivity activity7 = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity7, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            ((AddPaymentCardActivity) activity7).setCvv(StringsKt__StringsKt.trim(String.valueOf(this$0.m().edtCvv.getText())).toString());
                                            FragmentActivity activity8 = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity8, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            ((AddPaymentCardActivity) activity8).setCameraCaptured(false);
                                            FragmentActivity activity9 = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity9, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            ((AddPaymentCardActivity) activity9).setDeviceScannning(true);
                                            FragmentActivity activity10 = this$0.getActivity();
                                            Intrinsics.checkNotNull(activity10, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
                                            ((AddPaymentCardActivity) activity10).loadContactPhoneNfcStrapFragment();
                                            return;
                                        }
                                        return;
                                    }
                                    Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.invalid_cvv), 1).show();
                                    return;
                                }
                                Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.invalid_card_expiry_year), 1).show();
                                return;
                            }
                        }
                        Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.invalid_card_expiry_month), 1).show();
                        return;
                    }
                    Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.invalid_card_holder_name), 1).show();
                    return;
                }
                Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.invalid_card_number), 1).show();
                return;
            }
            this$0.q();
            return;
        }
        String string = this$0.getString(R.string.nfc_not_supported);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.nfc_not_supported)");
        BaseFragment.showCommonMessageDialog$default(this$0, string, false, 2, null);
    }

    public static final void r(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, FragmentCardDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.startActivity(new Intent("android.settings.NFC_SETTINGS"));
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

    public final boolean areAllFieldsFilled() {
        boolean z;
        boolean z2;
        boolean z3;
        String valueOf = String.valueOf(m().edtCardName.getText());
        String valueOf2 = String.valueOf(m().edtExpiryMm.getText());
        Utils.Companion companion = Utils.Companion;
        boolean isValidCvv = companion.isValidCvv(String.valueOf(m().edtCvv.getText()));
        if (StringsKt__StringsKt.trim(kotlin.text.m.replace$default(String.valueOf(m().edtCardNumber.getText()), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null)).toString().length() == 16) {
            z2 = companion.isValidCardNumber(StringsKt__StringsKt.trim(kotlin.text.m.replace$default(String.valueOf(m().edtCardNumber.getText()), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null)).toString());
            if (valueOf2.length() >= 5) {
                String substring = valueOf2.substring(0, 2);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                z3 = companion.isValidExpiryMonth(substring);
                String substring2 = valueOf2.substring(3, 5);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                z = companion.isValidExpiryYear(substring2);
            } else {
                z = false;
                z3 = false;
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        return !(valueOf.length() == 0) && z2 && z3 && z && isValidCvv;
    }

    public final boolean getEnterDetailsManually() {
        return this.t;
    }

    @NotNull
    public final String getTAG() {
        return this.s;
    }

    public final FragmentCardDetailsBinding m() {
        FragmentCardDetailsBinding fragmentCardDetailsBinding = this.m;
        Intrinsics.checkNotNull(fragmentCardDetailsBinding);
        return fragmentCardDetailsBinding;
    }

    public final void n() {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (this.t) {
            hashMap.put(TappyCleverTapConstants.CARD_SUBMISSION.getValue(), TappyCleverTapConstants.MANUAL_ENTRY.getValue());
        } else {
            hashMap.put(TappyCleverTapConstants.CARD_SUBMISSION.getValue(), TappyCleverTapConstants.SCAN_CARD.getValue());
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_CARD_SUBMIT_SUCCESSS.getValue(), hashMap);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.n = arguments.getString(Constants.CARD_NUMBER);
            this.o = arguments.getString(Constants.CARD_HOLDER_NAME);
            this.p = arguments.getString(Constants.CARD_EXPIRY_MONTH);
            this.q = arguments.getString(Constants.CARD_EXPIRY_YEAR);
            this.r = arguments.getString(Constants.CVV);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentCardDetailsBinding.inflate(inflater, viewGroup, false);
        return m().getRoot();
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
        m().edtCardNumber.addTextChangedListener(new CreditCardNumberFormattingTextWatcher());
        String str = this.n;
        boolean z = false;
        if (!(str == null || str.length() == 0)) {
            m().edtCardNumber.setText(this.n);
        }
        String str2 = this.o;
        if (!(str2 == null || str2.length() == 0)) {
            m().edtCardName.setText(this.o);
        }
        String str3 = this.p;
        if (!(str3 == null || str3.length() == 0)) {
            String str4 = this.q;
            if (!(str4 == null || str4.length() == 0)) {
                m().edtExpiryMm.setText(this.p + this.q);
            }
        }
        String str5 = this.r;
        if (str5 == null || str5.length() == 0) {
            z = true;
        }
        if (!z) {
            m().edtCvv.setText(this.r);
        }
        m().edtCardName.addTextChangedListener(this.u);
        m().edtCardNumber.addTextChangedListener(this.u);
        m().edtExpiryMm.addTextChangedListener(this.u);
        m().edtExpiryYy.addTextChangedListener(this.u);
        m().edtCvv.addTextChangedListener(this.u);
        m().btnSaveCard.addTextChangedListener(this.u);
        p();
        m().btnSaveCard.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCardDetails.o(FragmentCardDetails.this, view2);
            }
        });
    }

    public final void p() {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        m().edtExpiryMm.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.tappy.fragment.FragmentCardDetails$requestFocusandCardType$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                FragmentCardDetailsBinding m;
                FragmentCardDetailsBinding m2;
                FragmentCardDetailsBinding m3;
                FragmentCardDetailsBinding m4;
                if (editable != null) {
                    m = FragmentCardDetails.this.m();
                    m.edtExpiryMm.removeTextChangedListener(this);
                    if (editable.length() >= 2 && !booleanRef.element) {
                        StringBuilder sb = new StringBuilder(editable);
                        sb.insert(2, MqttTopic.TOPIC_LEVEL_SEPARATOR);
                        booleanRef.element = true;
                        m3 = FragmentCardDetails.this.m();
                        m3.edtExpiryMm.setText(sb);
                        m4 = FragmentCardDetails.this.m();
                        m4.edtExpiryMm.setSelection(sb.length());
                    } else if (editable.length() < 2) {
                        booleanRef.element = false;
                    }
                    m2 = FragmentCardDetails.this.m();
                    m2.edtExpiryMm.addTextChangedListener(this);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                FragmentCardDetailsBinding m;
                FragmentCardDetailsBinding m2;
                m = FragmentCardDetails.this.m();
                if (String.valueOf(m.edtExpiryMm.getText()).length() >= 5) {
                    m2 = FragmentCardDetails.this.m();
                    m2.edtCvv.requestFocus();
                }
            }
        });
        m().edtCardNumber.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.tappy.fragment.FragmentCardDetails$requestFocusandCardType$2
            /* JADX WARN: Code restructure failed: missing block: B:8:0x000d, code lost:
                if ((r8.length() == 0) == false) goto L7;
             */
            @Override // android.text.TextWatcher
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void afterTextChanged(@org.jetbrains.annotations.Nullable android.text.Editable r8) {
                /*
                    r7 = this;
                    r0 = 1
                    r1 = 0
                    if (r8 == 0) goto L10
                    int r2 = r8.length()
                    if (r2 != 0) goto Lc
                    r2 = r0
                    goto Ld
                Lc:
                    r2 = r1
                Ld:
                    if (r2 != 0) goto L10
                    goto L11
                L10:
                    r0 = r1
                L11:
                    if (r0 == 0) goto L80
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r0 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    com.coveiot.android.tappy.databinding.FragmentCardDetailsBinding r0 = com.coveiot.android.tappy.fragment.FragmentCardDetails.access$getBinding(r0)
                    android.widget.ImageView r0 = r0.imgCardTypeLogo
                    r0.setVisibility(r1)
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r0 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    com.coveiot.android.tappy.databinding.FragmentCardDetailsBinding r0 = com.coveiot.android.tappy.fragment.FragmentCardDetails.access$getBinding(r0)
                    android.view.View r0 = r0.viewCard
                    r0.setVisibility(r1)
                    com.coveiot.android.tappy.utils.Utils$Companion r0 = com.coveiot.android.tappy.utils.Utils.Companion
                    java.lang.String r1 = r8.toString()
                    r4 = 0
                    r5 = 4
                    r6 = 0
                    java.lang.String r2 = " "
                    java.lang.String r3 = ""
                    java.lang.String r8 = kotlin.text.m.replace$default(r1, r2, r3, r4, r5, r6)
                    int r8 = r0.getCardType(r8)
                    int r0 = com.coveiot.android.tappy.utils.LocalAPDUCommands.PAYMENTNETWORK_VISA
                    java.lang.String r1 = "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity"
                    if (r8 != r0) goto L62
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    androidx.fragment.app.FragmentActivity r8 = r8.getActivity()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r1)
                    com.coveiot.android.tappy.activity.AddPaymentCardActivity r8 = (com.coveiot.android.tappy.activity.AddPaymentCardActivity) r8
                    int r0 = com.coveiot.android.tappy.utils.LocalAPDUCommands.PAYMENTNETWORK_VISA
                    r8.setPaymentNetworkId(r0)
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    com.coveiot.android.tappy.databinding.FragmentCardDetailsBinding r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.access$getBinding(r8)
                    android.widget.ImageView r8 = r8.imgCardTypeLogo
                    int r0 = com.coveiot.android.tappy.R.drawable.ic_visa_logo
                    r8.setImageResource(r0)
                    goto L98
                L62:
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    androidx.fragment.app.FragmentActivity r8 = r8.getActivity()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r1)
                    com.coveiot.android.tappy.activity.AddPaymentCardActivity r8 = (com.coveiot.android.tappy.activity.AddPaymentCardActivity) r8
                    int r0 = com.coveiot.android.tappy.utils.LocalAPDUCommands.PAYMENTNETWORK_MASTERCARD
                    r8.setPaymentNetworkId(r0)
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    com.coveiot.android.tappy.databinding.FragmentCardDetailsBinding r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.access$getBinding(r8)
                    android.widget.ImageView r8 = r8.imgCardTypeLogo
                    int r0 = com.coveiot.android.tappy.R.drawable.ic_master_card_logo_new
                    r8.setImageResource(r0)
                    goto L98
                L80:
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    com.coveiot.android.tappy.databinding.FragmentCardDetailsBinding r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.access$getBinding(r8)
                    android.widget.ImageView r8 = r8.imgCardTypeLogo
                    r0 = 8
                    r8.setVisibility(r0)
                    com.coveiot.android.tappy.fragment.FragmentCardDetails r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.this
                    com.coveiot.android.tappy.databinding.FragmentCardDetailsBinding r8 = com.coveiot.android.tappy.fragment.FragmentCardDetails.access$getBinding(r8)
                    android.view.View r8 = r8.viewCard
                    r8.setVisibility(r0)
                L98:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.fragment.FragmentCardDetails$requestFocusandCardType$2.afterTextChanged(android.text.Editable):void");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                FragmentCardDetailsBinding m;
                FragmentCardDetailsBinding m2;
                m = FragmentCardDetails.this.m();
                if (StringsKt__StringsKt.trim(kotlin.text.m.replace$default(String.valueOf(m.edtCardNumber.getText()), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null)).toString().length() == 16) {
                    m2 = FragmentCardDetails.this.m();
                    m2.edtExpiryMm.requestFocus();
                }
            }
        });
        m().edtCvv.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.tappy.fragment.FragmentCardDetails$requestFocusandCardType$3
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                FragmentCardDetailsBinding m;
                Utils.Companion companion = Utils.Companion;
                m = FragmentCardDetails.this.m();
                if (companion.isValidCvv(String.valueOf(m.edtCvv.getText()))) {
                    FragmentCardDetails.this.hideKeyBoard();
                }
            }
        });
    }

    public final void q() {
        if (this.v == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.please_turn_on_nfc);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_turn_on_nfc)");
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
            this.v = bottomSheetDialogOneButtonOneTitle;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentCardDetails.r(BottomSheetDialogOneButtonOneTitle.this, this, view);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.v;
        Boolean valueOf = bottomSheetDialogOneButtonOneTitle2 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle2.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.v;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle3);
        bottomSheetDialogOneButtonOneTitle3.show();
    }

    public final void setEnterDetailsManually(boolean z) {
        this.t = z;
    }
}
