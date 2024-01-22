package com.coveiot.android.tappy;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.databinding.ActivityActivateCardBindingImpl;
import com.coveiot.android.tappy.databinding.ActivityAddNfcStrapBindingImpl;
import com.coveiot.android.tappy.databinding.ActivityAddNfcStrapFtuBindingImpl;
import com.coveiot.android.tappy.databinding.ActivityAddPaymentCardBindingImpl;
import com.coveiot.android.tappy.databinding.ActivityManageRegisterProductsBindingImpl;
import com.coveiot.android.tappy.databinding.ActivityRegisteredProductSummaryBindingImpl;
import com.coveiot.android.tappy.databinding.ActivityTappyTermsAndCondtionsBindingImpl;
import com.coveiot.android.tappy.databinding.CardActionConfirmationPopupBindingImpl;
import com.coveiot.android.tappy.databinding.CardListItemBindingImpl;
import com.coveiot.android.tappy.databinding.ContactPhoneNfcStrapBindingImpl;
import com.coveiot.android.tappy.databinding.CvvPopupBindingImpl;
import com.coveiot.android.tappy.databinding.ErrorPopUpBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentActivateCardBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentAddNfcStrapBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentAddNfcStrapFtuBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentCardDetailsBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentCardManagemntBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentEnterOtpBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentManageRegisteredProduct1BindingImpl;
import com.coveiot.android.tappy.databinding.FragmentRegisteredProductItemNewBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentScanCardBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentSelectBankBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentStepupOptionsBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentStrapManagementBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentSupportedCardTypeBindingImpl;
import com.coveiot.android.tappy.databinding.FragmentTermsAndConditionsBindingImpl;
import com.coveiot.android.tappy.databinding.FriendlyNamePopupBindingImpl;
import com.coveiot.android.tappy.databinding.ReasonPopupBindingImpl;
import com.coveiot.android.tappy.databinding.StrapListItemBindingImpl;
import com.coveiot.android.tappy.databinding.SuccessPopUpBindingImpl;
import com.coveiot.android.tappy.databinding.TappayftuViewpagerLayoutBindingImpl;
import com.coveiot.android.tappy.databinding.TransactionDetailPopupBindingImpl;
import com.coveiot.android.tappy.databinding.TransactionDetailPopupnewBindingImpl;
import com.coveiot.android.tappy.databinding.TransactionListItemNewBindingImpl;
import com.coveiot.android.tappy.databinding.VirtualCardInfoPopupBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes7.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYACTIVATECARD = 1;
    private static final int LAYOUT_ACTIVITYADDNFCSTRAP = 2;
    private static final int LAYOUT_ACTIVITYADDNFCSTRAPFTU = 3;
    private static final int LAYOUT_ACTIVITYADDPAYMENTCARD = 4;
    private static final int LAYOUT_ACTIVITYMANAGEREGISTERPRODUCTS = 5;
    private static final int LAYOUT_ACTIVITYREGISTEREDPRODUCTSUMMARY = 6;
    private static final int LAYOUT_ACTIVITYTAPPYTERMSANDCONDTIONS = 7;
    private static final int LAYOUT_CARDACTIONCONFIRMATIONPOPUP = 8;
    private static final int LAYOUT_CARDLISTITEM = 9;
    private static final int LAYOUT_CONTACTPHONENFCSTRAP = 10;
    private static final int LAYOUT_CVVPOPUP = 11;
    private static final int LAYOUT_ERRORPOPUP = 12;
    private static final int LAYOUT_FRAGMENTACTIVATECARD = 13;
    private static final int LAYOUT_FRAGMENTADDNFCSTRAP = 14;
    private static final int LAYOUT_FRAGMENTADDNFCSTRAPFTU = 15;
    private static final int LAYOUT_FRAGMENTCARDDETAILS = 16;
    private static final int LAYOUT_FRAGMENTCARDMANAGEMNT = 17;
    private static final int LAYOUT_FRAGMENTENTEROTP = 18;
    private static final int LAYOUT_FRAGMENTMANAGEREGISTEREDPRODUCT1 = 19;
    private static final int LAYOUT_FRAGMENTREGISTEREDPRODUCTITEMNEW = 20;
    private static final int LAYOUT_FRAGMENTSCANCARD = 21;
    private static final int LAYOUT_FRAGMENTSELECTBANK = 22;
    private static final int LAYOUT_FRAGMENTSTEPUPOPTIONS = 23;
    private static final int LAYOUT_FRAGMENTSTRAPMANAGEMENT = 24;
    private static final int LAYOUT_FRAGMENTSUPPORTEDCARDTYPE = 25;
    private static final int LAYOUT_FRAGMENTTERMSANDCONDITIONS = 26;
    private static final int LAYOUT_FRIENDLYNAMEPOPUP = 27;
    private static final int LAYOUT_REASONPOPUP = 28;
    private static final int LAYOUT_STRAPLISTITEM = 29;
    private static final int LAYOUT_SUCCESSPOPUP = 30;
    private static final int LAYOUT_TAPPAYFTUVIEWPAGERLAYOUT = 31;
    private static final int LAYOUT_TRANSACTIONDETAILPOPUP = 32;
    private static final int LAYOUT_TRANSACTIONDETAILPOPUPNEW = 33;
    private static final int LAYOUT_TRANSACTIONLISTITEMNEW = 34;
    private static final int LAYOUT_VIRTUALCARDINFOPOPUP = 35;

    /* loaded from: classes7.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f5998a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(21);
            f5998a = sparseArray;
            sparseArray.put(1, "FTUItemCount");
            sparseArray.put(0, "_all");
            sparseArray.put(2, "bindingDataModel1");
            sparseArray.put(3, "firstCardType");
            sparseArray.put(4, "fitnessType");
            sparseArray.put(5, "healthInfo");
            sparseArray.put(6, "isDataAvailable");
            sparseArray.put(7, "isFirstCardDataAvailable");
            sparseArray.put(8, "progress");
            sparseArray.put(9, "selectedItemPosition");
            sparseArray.put(10, "showAlexaConnect");
            sparseArray.put(11, "showFitnessPlan");
            sparseArray.put(12, "showSOSSettings");
            sparseArray.put(13, "showSportScores");
            sparseArray.put(14, "showTapAndPay");
            sparseArray.put(15, "showWellnessCrew");
            sparseArray.put(16, "stepsDataModel");
            sparseArray.put(17, "stepsGoal");
            sparseArray.put(18, "tipsData");
            sparseArray.put(19, "title");
            sparseArray.put(20, "watchName");
        }
    }

    /* loaded from: classes7.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f5999a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(35);
            f5999a = hashMap;
            hashMap.put("layout/activity_activate_card_0", Integer.valueOf(R.layout.activity_activate_card));
            hashMap.put("layout/activity_add_nfc_strap_0", Integer.valueOf(R.layout.activity_add_nfc_strap));
            hashMap.put("layout/activity_add_nfc_strap_ftu_0", Integer.valueOf(R.layout.activity_add_nfc_strap_ftu));
            hashMap.put("layout/activity_add_payment_card_0", Integer.valueOf(R.layout.activity_add_payment_card));
            hashMap.put("layout/activity_manage_register_products_0", Integer.valueOf(R.layout.activity_manage_register_products));
            hashMap.put("layout/activity_registered_product_summary_0", Integer.valueOf(R.layout.activity_registered_product_summary));
            hashMap.put("layout/activity_tappy_terms_and_condtions_0", Integer.valueOf(R.layout.activity_tappy_terms_and_condtions));
            hashMap.put("layout/card_action_confirmation_popup_0", Integer.valueOf(R.layout.card_action_confirmation_popup));
            hashMap.put("layout/card_list_item_0", Integer.valueOf(R.layout.card_list_item));
            hashMap.put("layout/contact_phone_nfc_strap_0", Integer.valueOf(R.layout.contact_phone_nfc_strap));
            hashMap.put("layout/cvv_popup_0", Integer.valueOf(R.layout.cvv_popup));
            hashMap.put("layout/error_pop_up_0", Integer.valueOf(R.layout.error_pop_up));
            hashMap.put("layout/fragment_activate_card_0", Integer.valueOf(R.layout.fragment_activate_card));
            hashMap.put("layout/fragment_add_nfc_strap_0", Integer.valueOf(R.layout.fragment_add_nfc_strap));
            hashMap.put("layout/fragment_add_nfc_strap_ftu_0", Integer.valueOf(R.layout.fragment_add_nfc_strap_ftu));
            hashMap.put("layout/fragment_card_details_0", Integer.valueOf(R.layout.fragment_card_details));
            hashMap.put("layout/fragment_card_managemnt_0", Integer.valueOf(R.layout.fragment_card_managemnt));
            hashMap.put("layout/fragment_enter_otp_0", Integer.valueOf(R.layout.fragment_enter_otp));
            hashMap.put("layout/fragment_manage_registered_product1_0", Integer.valueOf(R.layout.fragment_manage_registered_product1));
            hashMap.put("layout/fragment_registered_product_item_new_0", Integer.valueOf(R.layout.fragment_registered_product_item_new));
            hashMap.put("layout/fragment_scan_card_0", Integer.valueOf(R.layout.fragment_scan_card));
            hashMap.put("layout/fragment_select_bank_0", Integer.valueOf(R.layout.fragment_select_bank));
            hashMap.put("layout/fragment_stepup_options_0", Integer.valueOf(R.layout.fragment_stepup_options));
            hashMap.put("layout/fragment_strap_management_0", Integer.valueOf(R.layout.fragment_strap_management));
            hashMap.put("layout/fragment_supported_card_type_0", Integer.valueOf(R.layout.fragment_supported_card_type));
            hashMap.put("layout/fragment_terms_and_conditions_0", Integer.valueOf(R.layout.fragment_terms_and_conditions));
            hashMap.put("layout/friendly_name_popup_0", Integer.valueOf(R.layout.friendly_name_popup));
            hashMap.put("layout/reason_popup_0", Integer.valueOf(R.layout.reason_popup));
            hashMap.put("layout/strap_list_item_0", Integer.valueOf(R.layout.strap_list_item));
            hashMap.put("layout/success_pop_up_0", Integer.valueOf(R.layout.success_pop_up));
            hashMap.put("layout/tappayftu_viewpager_layout_0", Integer.valueOf(R.layout.tappayftu_viewpager_layout));
            hashMap.put("layout/transaction_detail_popup_0", Integer.valueOf(R.layout.transaction_detail_popup));
            hashMap.put("layout/transaction_detail_popupnew_0", Integer.valueOf(R.layout.transaction_detail_popupnew));
            hashMap.put("layout/transaction_list_item_new_0", Integer.valueOf(R.layout.transaction_list_item_new));
            hashMap.put("layout/virtual_card_info_popup_0", Integer.valueOf(R.layout.virtual_card_info_popup));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(35);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_activate_card, 1);
        sparseIntArray.put(R.layout.activity_add_nfc_strap, 2);
        sparseIntArray.put(R.layout.activity_add_nfc_strap_ftu, 3);
        sparseIntArray.put(R.layout.activity_add_payment_card, 4);
        sparseIntArray.put(R.layout.activity_manage_register_products, 5);
        sparseIntArray.put(R.layout.activity_registered_product_summary, 6);
        sparseIntArray.put(R.layout.activity_tappy_terms_and_condtions, 7);
        sparseIntArray.put(R.layout.card_action_confirmation_popup, 8);
        sparseIntArray.put(R.layout.card_list_item, 9);
        sparseIntArray.put(R.layout.contact_phone_nfc_strap, 10);
        sparseIntArray.put(R.layout.cvv_popup, 11);
        sparseIntArray.put(R.layout.error_pop_up, 12);
        sparseIntArray.put(R.layout.fragment_activate_card, 13);
        sparseIntArray.put(R.layout.fragment_add_nfc_strap, 14);
        sparseIntArray.put(R.layout.fragment_add_nfc_strap_ftu, 15);
        sparseIntArray.put(R.layout.fragment_card_details, 16);
        sparseIntArray.put(R.layout.fragment_card_managemnt, 17);
        sparseIntArray.put(R.layout.fragment_enter_otp, 18);
        sparseIntArray.put(R.layout.fragment_manage_registered_product1, 19);
        sparseIntArray.put(R.layout.fragment_registered_product_item_new, 20);
        sparseIntArray.put(R.layout.fragment_scan_card, 21);
        sparseIntArray.put(R.layout.fragment_select_bank, 22);
        sparseIntArray.put(R.layout.fragment_stepup_options, 23);
        sparseIntArray.put(R.layout.fragment_strap_management, 24);
        sparseIntArray.put(R.layout.fragment_supported_card_type, 25);
        sparseIntArray.put(R.layout.fragment_terms_and_conditions, 26);
        sparseIntArray.put(R.layout.friendly_name_popup, 27);
        sparseIntArray.put(R.layout.reason_popup, 28);
        sparseIntArray.put(R.layout.strap_list_item, 29);
        sparseIntArray.put(R.layout.success_pop_up, 30);
        sparseIntArray.put(R.layout.tappayftu_viewpager_layout, 31);
        sparseIntArray.put(R.layout.transaction_detail_popup, 32);
        sparseIntArray.put(R.layout.transaction_detail_popupnew, 33);
        sparseIntArray.put(R.layout.transaction_list_item_new, 34);
        sparseIntArray.put(R.layout.virtual_card_info_popup, 35);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f5998a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_activate_card_0".equals(tag)) {
                            return new ActivityActivateCardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_activate_card is invalid. Received: " + tag);
                    case 2:
                        if ("layout/activity_add_nfc_strap_0".equals(tag)) {
                            return new ActivityAddNfcStrapBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_add_nfc_strap is invalid. Received: " + tag);
                    case 3:
                        if ("layout/activity_add_nfc_strap_ftu_0".equals(tag)) {
                            return new ActivityAddNfcStrapFtuBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_add_nfc_strap_ftu is invalid. Received: " + tag);
                    case 4:
                        if ("layout/activity_add_payment_card_0".equals(tag)) {
                            return new ActivityAddPaymentCardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_add_payment_card is invalid. Received: " + tag);
                    case 5:
                        if ("layout/activity_manage_register_products_0".equals(tag)) {
                            return new ActivityManageRegisterProductsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_manage_register_products is invalid. Received: " + tag);
                    case 6:
                        if ("layout/activity_registered_product_summary_0".equals(tag)) {
                            return new ActivityRegisteredProductSummaryBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_registered_product_summary is invalid. Received: " + tag);
                    case 7:
                        if ("layout/activity_tappy_terms_and_condtions_0".equals(tag)) {
                            return new ActivityTappyTermsAndCondtionsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_tappy_terms_and_condtions is invalid. Received: " + tag);
                    case 8:
                        if ("layout/card_action_confirmation_popup_0".equals(tag)) {
                            return new CardActionConfirmationPopupBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for card_action_confirmation_popup is invalid. Received: " + tag);
                    case 9:
                        if ("layout/card_list_item_0".equals(tag)) {
                            return new CardListItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for card_list_item is invalid. Received: " + tag);
                    case 10:
                        if ("layout/contact_phone_nfc_strap_0".equals(tag)) {
                            return new ContactPhoneNfcStrapBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for contact_phone_nfc_strap is invalid. Received: " + tag);
                    case 11:
                        if ("layout/cvv_popup_0".equals(tag)) {
                            return new CvvPopupBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for cvv_popup is invalid. Received: " + tag);
                    case 12:
                        if ("layout/error_pop_up_0".equals(tag)) {
                            return new ErrorPopUpBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for error_pop_up is invalid. Received: " + tag);
                    case 13:
                        if ("layout/fragment_activate_card_0".equals(tag)) {
                            return new FragmentActivateCardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_activate_card is invalid. Received: " + tag);
                    case 14:
                        if ("layout/fragment_add_nfc_strap_0".equals(tag)) {
                            return new FragmentAddNfcStrapBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_add_nfc_strap is invalid. Received: " + tag);
                    case 15:
                        if ("layout/fragment_add_nfc_strap_ftu_0".equals(tag)) {
                            return new FragmentAddNfcStrapFtuBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_add_nfc_strap_ftu is invalid. Received: " + tag);
                    case 16:
                        if ("layout/fragment_card_details_0".equals(tag)) {
                            return new FragmentCardDetailsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_card_details is invalid. Received: " + tag);
                    case 17:
                        if ("layout/fragment_card_managemnt_0".equals(tag)) {
                            return new FragmentCardManagemntBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_card_managemnt is invalid. Received: " + tag);
                    case 18:
                        if ("layout/fragment_enter_otp_0".equals(tag)) {
                            return new FragmentEnterOtpBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_enter_otp is invalid. Received: " + tag);
                    case 19:
                        if ("layout/fragment_manage_registered_product1_0".equals(tag)) {
                            return new FragmentManageRegisteredProduct1BindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_manage_registered_product1 is invalid. Received: " + tag);
                    case 20:
                        if ("layout/fragment_registered_product_item_new_0".equals(tag)) {
                            return new FragmentRegisteredProductItemNewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_registered_product_item_new is invalid. Received: " + tag);
                    case 21:
                        if ("layout/fragment_scan_card_0".equals(tag)) {
                            return new FragmentScanCardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_scan_card is invalid. Received: " + tag);
                    case 22:
                        if ("layout/fragment_select_bank_0".equals(tag)) {
                            return new FragmentSelectBankBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_select_bank is invalid. Received: " + tag);
                    case 23:
                        if ("layout/fragment_stepup_options_0".equals(tag)) {
                            return new FragmentStepupOptionsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_stepup_options is invalid. Received: " + tag);
                    case 24:
                        if ("layout/fragment_strap_management_0".equals(tag)) {
                            return new FragmentStrapManagementBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_strap_management is invalid. Received: " + tag);
                    case 25:
                        if ("layout/fragment_supported_card_type_0".equals(tag)) {
                            return new FragmentSupportedCardTypeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_supported_card_type is invalid. Received: " + tag);
                    case 26:
                        if ("layout/fragment_terms_and_conditions_0".equals(tag)) {
                            return new FragmentTermsAndConditionsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_terms_and_conditions is invalid. Received: " + tag);
                    case 27:
                        if ("layout/friendly_name_popup_0".equals(tag)) {
                            return new FriendlyNamePopupBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for friendly_name_popup is invalid. Received: " + tag);
                    case 28:
                        if ("layout/reason_popup_0".equals(tag)) {
                            return new ReasonPopupBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for reason_popup is invalid. Received: " + tag);
                    case 29:
                        if ("layout/strap_list_item_0".equals(tag)) {
                            return new StrapListItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for strap_list_item is invalid. Received: " + tag);
                    case 30:
                        if ("layout/success_pop_up_0".equals(tag)) {
                            return new SuccessPopUpBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for success_pop_up is invalid. Received: " + tag);
                    case 31:
                        if ("layout/tappayftu_viewpager_layout_0".equals(tag)) {
                            return new TappayftuViewpagerLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for tappayftu_viewpager_layout is invalid. Received: " + tag);
                    case 32:
                        if ("layout/transaction_detail_popup_0".equals(tag)) {
                            return new TransactionDetailPopupBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for transaction_detail_popup is invalid. Received: " + tag);
                    case 33:
                        if ("layout/transaction_detail_popupnew_0".equals(tag)) {
                            return new TransactionDetailPopupnewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for transaction_detail_popupnew is invalid. Received: " + tag);
                    case 34:
                        if ("layout/transaction_list_item_new_0".equals(tag)) {
                            return new TransactionListItemNewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for transaction_list_item_new is invalid. Received: " + tag);
                    case 35:
                        if ("layout/virtual_card_info_popup_0".equals(tag)) {
                            return new VirtualCardInfoPopupBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for virtual_card_info_popup is invalid. Received: " + tag);
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f5999a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
