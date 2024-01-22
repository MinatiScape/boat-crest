package com.coveiot.android.theme;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.blankj.utilcode.constant.PermissionConstants;
import com.google.common.net.HttpHeaders;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum CleverTapConstants {
    temp;

    /* loaded from: classes7.dex */
    public enum CustomEventProperties {
        ONBOARD_PERMISSION_BLUETOOTH("Onboard permission bluetooth"),
        ONBOARD_PERMISSION_LOCATION("Onboard permission location"),
        ONBOARD_PERMISSION_NOTIFICATIONS("Onboard permission notifications"),
        ONBOARD_PERMISSION_BATTERY("Onboard permission battery"),
        ONBOARD_PERMISSION_AUTO_START("Onboard permission auto start"),
        ONBOARD_PERMISSION_POWER("Onboard permission power"),
        HP_LOAD_SOURCE("Hp load source"),
        HP_LOAD_USER_STATUS("HP load user status"),
        HP_PAIRED("HP Paired"),
        HP_LOAD_WATCH_BT_STATUS("HP load watch BT status"),
        HP_LOAD_WATCH_BT_CALLING_STATUS("HP load watch BT calling Status"),
        BUTTONTEXT("Buttontext"),
        SOURCE("Source"),
        WATCH_FACE_TYPE("Watch face type"),
        UPDATE_LOCATION("Update location"),
        BOAT_COINS_PAGE_SOURCE("Boat Coins Page Source"),
        VOUCHER_ID("Voucher ID"),
        PARTNER_NAME("Partner Name"),
        PARTNER_VOUCHER_DESCRIPTION("Partner Voucher Description"),
        VOUCHER_EXPIRY("Voucher Expiry"),
        COINS_REDEEMED("Coins Redeemed"),
        VOUCHER_PAGE_VIEW_SOURCE("Voucher page view Source"),
        VOUCHER_REDEEM_PAGE_VIEW_SOURCE("Voucher redeem page view source"),
        ALL_VOUCHER_PAGE_VIEW_SOURCE("All voucher page view Source"),
        MY_VOUCHER_PAGE_VIEW_SOURCE("My voucher page view source"),
        VOUCHER_GENERATE_PAGE_VIEW_SOURCE("Voucher generate page view source"),
        BATTERY_PERCENTAGE("Battery percentage"),
        DETAIL_SREEN("Details Sreen"),
        VIEW_ALL_PARTICIPANT("View All Participant"),
        RESEND_BY("Resend by"),
        QR_NAME("QR name"),
        QR_TAG("QR tag"),
        TYPE("Type"),
        UPLOAD_FROM("Upload from"),
        FAILURE_REASON("Failure reason"),
        TYPE2("Type 2"),
        LOCATION(HttpHeaders.LOCATION),
        SCAN_FAILED_REASON("Scan failed reason"),
        GRIDITEM("Griditem");
        
        @NotNull
        private String value;

        CustomEventProperties(String str) {
            this.value = str;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        public final void setValue(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.value = str;
        }
    }

    /* loaded from: classes7.dex */
    public enum CustomEventValues {
        APP_PUSH("App push"),
        ONBOARDING("Onboarding"),
        GUEST("Guest"),
        LOGGEDIN("Loggedin"),
        NO("No"),
        CONNECTED("connected"),
        DISCONNECTED("disconnected"),
        YES("Yes"),
        NA("NA"),
        MYCREATION("MyCreation"),
        CUSTOM(TypedValues.Custom.NAME),
        SAVE_FACE_MODAL("Save face modal"),
        HP_BANNER("HP Banner"),
        HP_GRID("HP Grid"),
        SETTINGS("Settings"),
        MY_WATCH("My Watch"),
        CLOUD("Cloud"),
        DEFAULT("Default"),
        WATCH_FACES_PAGE("Watch faces page"),
        HP_ICON_TOP("HP Icon Top"),
        PROFILE("Profile"),
        MYWATCH("Mywatch"),
        FITCREW("Fitcrew"),
        HOME("Home"),
        FITNESS("Fitness"),
        NORMAL_JOURNEY("Normal Jounrey"),
        ORGANIC("Organic"),
        FITNESS_CHALLENGE_FITNESS_SCREEN("Fitness Screen"),
        FITNESS_CHALLENGE_DETAILS_PAGE("Details page"),
        FITNESS_CHALLENGE_LISTING_PAGE("Listing page"),
        FITNESS_CHALLENGE_HP("Hp"),
        FITNESS_CHALLENGE_FITNESS_PAGE("FitnessPage"),
        FITNESS_CHALLENGE_PERCENT_OF_CHALLENGE_COMPLETED("Percent of challenge completed"),
        SMS(PermissionConstants.SMS),
        ONCALL("OnCall"),
        WHATSAPP("WhatsApp"),
        HP_QR_CARD("HP QR Card"),
        EDIT_QR_CODE_OPTION("Edit QR Code Option"),
        QR_LIMIT_EXCEEDED("QR limit exceeded"),
        QR_LANDING_PAGE("QR landing page"),
        NEW("New"),
        EDITED_EXISTING("Edited Existing"),
        WATCH_DISCONNECTED("Watch disconnected"),
        INTERNET_DISCONNECTED("Internet disconnected"),
        REPLACED("Replaced"),
        OPTIONS_MENU("Options menu"),
        DETAILS_PAGE("Details Page"),
        WATCH_FEATURES("Watch Features"),
        SYSTEM_UNABLE_TO_DETECT("System unable to detect QR"),
        IDENTITY("identity"),
        WFS("WFS"),
        BOATCOINS("Boatcoins"),
        CHALLENGES("Challenges"),
        QRTRAY("Qrtray"),
        NAVIGATION("Navigation"),
        SMARTALERTS("Smartalerts"),
        SOS("SOS"),
        ACTIV_HEALTH("Activ health"),
        FITNESS_BUDDIES("Fitness Buddies"),
        FITNESS_REPORT("Fitness Report");
        
        @NotNull
        private String value;

        CustomEventValues(String str) {
            this.value = str;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        public final void setValue(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.value = str;
        }
    }

    /* loaded from: classes7.dex */
    public enum EventName {
        PREVIOUS_SCREEN_NAME(""),
        ONBOARD_GETSTARTED_TAPPED("BC_Onboard_Getstarted_Tapped"),
        ONBOARD_MOBILE_OTP_SENT("BC_Onboard_Mobile_Otp_Sent"),
        ONBOARD_LOGIN_SKIPPED("BC_Onboard_Login_Skipped"),
        ONBOARD_TNC_VIEWED("BC_Onboard_Tnc_Viewed"),
        ONBOARD_PRIVACYPOLICY_VIEWED("BC_Onboard_Privacypolicy_Viewed"),
        ONBOARD_OTP_VERIF_SUCCESS("BC_Onboard_Otp_Verif_Success"),
        ONBOARD_OTP_VERIF_FAILED("BC_Onboard_Otp_Verif_Failed"),
        ONBOARD_OTP_RESENT("BC_Onboard_Otp_Resent"),
        ONBOARD_USER_LOGIN("BC_Onboard_User_Login"),
        ONBOARD_USER_REGISTRATION("BC_Onboard_User_Registration"),
        ONBOARD_PAIR_WATCH_NOW("BC_Onboard_Pair_Watch_Now"),
        ONBOARD_SKIP_WATCH_PAIR("BC_Onboard_Skip_Watch_Pair"),
        ONBOARD_APP_PERMISSION_GRANTED("BC_Onboard_App_permission_granted"),
        ONBOARD_WATCH_MODEL_SELECTED("BC_Onboard_Watch_Model_Selected"),
        ONBOARD_NODEVICE_FOUND_SEARCH_AGAIN("BC_Onboard_NoDevice_Found_Search_Again"),
        ONBOARD_WATCH_PAIRED_SUCCESS("BC_Onboard_Watch_Paired_Success"),
        ONBOARD_DEVICE_NOT_PAIRED_TRY_AGAIN("BC_Onboard_Device_not_paired_try_again"),
        ONBOARD_DEVICE_PAIRING_SUPPORT("BC_Onboard_Device_pairing_support"),
        ONBOARD_ACESS_ALL_GRANTED("BC_Onboard_Acess_All_Granted"),
        ONBOARD_HP_VIEWED("BC_Onboard_HP_Viewed"),
        HP_VIEWED("BC_HP_Viewed"),
        WF_PAGE_ACCESSSED("BC_WF_Page_Accessed"),
        WF_UPDATED("BC_WF_Updated"),
        WF_WFS_CTA_TAPPED("BC_WF_WFS_CTA_Tapped"),
        WF_EXIT("BC_WF_Exit"),
        WFS_ACCESSED("BC_WFS_Accessed"),
        WFS_CUSTOM_FACE_SAVED("BC_WFS_Custom_Face_saved"),
        WFS_EXIT("BC_WFS_Exit"),
        BTN_NAV_TAPPED("BC_BTN_NAV_Tapped"),
        BOATCOIN_LANDINGPAGE_VIEWED("BC_Boatcoin_Landingpage_Viewed"),
        BC_CHALLENGE_LANDINGPAGE_VIEWED("BC_Challenge_Landingpage_Viewed"),
        BC_CHALLENGE_DETAILSPAGE_VIEWED("BC_Challenge_Detailspage_Viewed"),
        BC_CHALLENGE_JOIN_SUCCESS("BC_Challenge_Join_Success"),
        BC_CHALLENGE_COMPLETED("BC_Challenge_Completed"),
        BC_CHALLENGE_LEAVE_CHALLENGE_REQUEST("BC_Challenge_Leave_Challenge_Request"),
        BC_CHALLENGE_LEAVE_CHALLENGE_SUCCESS("BC_Challenge_Leave_Challenge_Success"),
        BC_CHALLENGE_CREATE_CHALLENGE_SUCCESS("BC_Challenge_Create_Challenge_Success"),
        BC_CHALLENGE_SHARE_PROFILE_PICADDED("BC_Challenge_Share_Profile_Picadded"),
        BC_CHALLENGE_ADD_PARTICIPANTS("BC_Challenge_Add_Participants"),
        BC_CHALLENGE_VIEW_LEADERBOARD("BC_Challenge_View_Leaderboard"),
        BC_CHALLENGE_SHARE_REQUEST("BC_Challenge_Share_Request"),
        BC_CHALLENGE_SHARE_SUCCESS("BC_Challenge_Share_Success"),
        BC_CHALLENGE_REMOVE_PARTICIPANTS("BC_Challenge_Remove_Participants"),
        BC_PAY_GETSTARTED_TAPPED("BC_Pay_Getstarted_tapped"),
        BC_PAY_BANKSELECTED("BC_Pay_Bankselected"),
        BC_PAY_OPEN_ACCOUNT_TAPPED("BC_Pay_Open_Account_Tapped"),
        BC_PAY_OPENAC_PRIMING_TAPPED("BC_Pay_Openac_Priming_Tapped"),
        BC_PAY_CARD_SCAN_REQUEST("BC_Pay_Card_Scan_Request"),
        BC_PAY_SUPPORTISSUER_TAPPED("BC_Pay_Supportissuer_Tapped"),
        BC_PAY_CARDMANUALENTRY("BC_Pay_CardManualEntry"),
        BC_PAY_CARD_SUBMIT_SUCCESSS("BC_Pay_Card_Submit_Success"),
        BC_PAY_NFC_PAIR_REQUEST("BC_Pay_NFC_Pair_Request"),
        BC_PAY_NFC_PAIR_SUCCESS("BC_Pay_NFC_Pair_Success"),
        BC_PAY_TNC_REQUEST("BC_Pay_Tnc_Request"),
        BC_PAY_TNC_SUCCESS("BC_Pay_Tnc_Success"),
        BC_PAY_TNC_FAILED("BC_Pay_Tnc_failed"),
        BC_PAY_HP_VIEWED("BC_Pay_HP_Viewed"),
        BC_PAY_SETTINGS_TAPPED("BC_Pay_Settings_tapped"),
        BC_PAY_ACTIVATECARD_REQUEST("BC_Pay_Activatecard_Request"),
        BC_PAY_MOBILE_OTP_SENT("BC_Pay_Mobile_OTP_Sent"),
        BC_PAY_OTP_RESENT("BC_Pay_OTP_Resent"),
        BC_PAY_OTP_VERIF_FAILED("BC_Pay_Otp_Verif_Failed"),
        BC_PAY_OTP_VERIF_SUCCESS("BC_Pay_OTP_Verif_Success"),
        BC_PAY_ACTIVATECARD_SUCCESS("BC_Pay_Activatecard_Success"),
        BC_PAY_CONFIG_SUCCESS("BC_Pay_Config_Success"),
        BC_PAY_SUSPENDCARD_REQUEST("BC_Pay_Suspendcard_Request"),
        BC_PAY_SUSPENDCARD_SUCCESS("BC_Pay_Suspendcard_Success"),
        BC_PAY_RESUMECARD_REQUEST("BC_Pay_Resumedcard_Request"),
        BC_PAY_RESUMEDCARD_SUCCESS("BC_Pay_Resumedcard_Success"),
        BC_PAY_SETTINGS_STRAP_VIEWED("BC_Pay_Settings_Strap_Viewed"),
        BC_PAY_DEREGISTEREDSTRAP_REQUEST("BC_Pay_DeregisterStrap_Request"),
        BC_PAY_STRAPNAME_REQUEST("BC_Pay_Straprename_Request"),
        BC_PAY_DEREGISTERSTRAP_SUCCESS("BC_Pay_DeregisterStrap_Success"),
        BC_PAYT_STRAPNAME_SUCCESS("BC_Payt_Straprename_Success"),
        BC_PAY_SETTINGS_CARD_VIEWED("BC_Pay_Settings_Card_Viewed"),
        BC_PAY_CARDDELETE_REQUEST("BC_Pay_CardDelete_Request"),
        BC_PAY_CARDDELETE_SUCCESS("BC_Pay_CardDelete_Success"),
        BC_HP_BTCALLING_CONNECT_RQST("BC_HP_BTCalling_Connect_Rqst"),
        BC_HP_BTCALLING_CONNECT_SUCCESS("BC_HP_BTCalling_Connect_Success"),
        BC_HP_WATCHSYNC_USER_RQST("BC_HP_WatchSync_User_Rqst"),
        BC_HP_WATCHSYNC_SUCCESS("BC_HP_WatchSync_Success"),
        BC_HP_BOATCOINICON_TAPPED("BC_HP_Boatcoinicon_Tapped"),
        BC_HP_PROFILEICON_TAPPED("BC_HP_Profileicon_Tapped"),
        BC_HP_WFBANNER_TAPPED("BC_HP_WFbanner_Tapped"),
        BC_HP_VIEW_FITNESSDASH_TAPPED("BC_HP_View_Fitnessdash_Tapped"),
        BC_HP_EDIT_VITALS_CARD_TAPPED("BC_HP_Edit_Vitals_card_Tapped"),
        BC_HP_TRACK_MORE_VITALS_TAPPED("BC_HP_Track_More_Vitals_Tapped"),
        BC_HP_VIEW_ALL_ACTIVITIES_TAPPED("BC_HP_View_All_Activities_Tapped"),
        BC_HP_ACTIVITIES_TAPPED("BC_HP_Activities_Tapped"),
        BC_HP_BCBANNER_TAPPED("BC_HP_BCbanner_Tapped"),
        BC_HP_BUDDIESBANNER_TAPPED("BC_HP_Buddiesbanner_Tapped"),
        BC_HP_CREWSETUP_TAPPED("BC_HP_Crewsetup_Tapped"),
        BC_HP_SPORTSCORES_TAPPED("BC_HP_Sportscores_Tapped"),
        BC_HP_FITNESSPLAN_TAPPED("BC_HP_fitnessplan_Tapped"),
        BC_HP_CULTBANNER_TAPPED("BC_HP_Cultbanner_Tapped"),
        BC_HP_STUDIOBANNER_TAPPED("BC_HP_Studiobanner_Tapped"),
        BC_HP_SETTINGBANNER_TAPPED("BC_HP_Settingsbanner_Tapped"),
        BC_HP_CHALLENGEVIEWMORE_TAPPED("BC_HP_Challengeviewmore_Tapped"),
        BC_HP_CREATECHALLENGE_TAPPED("BC_HP_CreateChallenge_Tapped"),
        BC_HP_CHALLENGEBNR_TAPPED("BC_HP_Challengebnr_Tapped"),
        BC_QR_GET_STARTED_TAPPED("BC_QR_Get_Started_Tapped"),
        BC_QR_UPLOAD_REQUEST("BC_QR_Upload_Request"),
        BC_QR_WATCHDEMOVIDEO("BC_QR_Watchdemovideo"),
        BC_QR_SCAN_REQUEST("BC_QR_Scan_Request"),
        BC_QR_SCAN_FAILED("BC_QR_Scan_Failed"),
        BC_QR_SCAN_SUCCESS("BC_QR_Scan_Success"),
        BC_QR_PUSHTOWATCH_REQUEST("BC_QR_Pushtowatch_Request"),
        BC_QR_PUSHTOWATCH_SUCCESS("BC_QR_Pushtowatch_Success"),
        BC_QR_PUSHTOWATCH_FAILED("BC_QR_Pushtowatch_Failed"),
        BC_QR_SAVE_REQUEST("BC_QR_Save_Request"),
        BC_QR_SAVE_SUCCESS("BC_QR_Save_Success"),
        BC_QR_SAVE_FAILED("BC_QR_Save_Failed"),
        BC_QR_HP_VIEWED("BC_QR_HP_Viewed"),
        BC_QR_UPLOAD_FAILED("BC_QR_Upload_Failed"),
        BC_QR_MENU_TAPPED("BC_QR_Menu_Tapped"),
        BC_QR_EDIT_REQUEST("BC_QR_Edit_Request"),
        BC_QRCODE_DETAILS_VIEWED("BC_QRcode_Details_Viewed"),
        BC_QR_DELETE_REQUEST("BC_QR_Delete_Request"),
        BC_QR_DELETE_SUCCESS("BC_QR_Delete_Success"),
        BC_HP_GRIDTAPPED("BC_HP_GridTapped");
        
        @NotNull
        private String value;

        EventName(String str) {
            this.value = str;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        public final void setValue(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.value = str;
        }
    }
}
