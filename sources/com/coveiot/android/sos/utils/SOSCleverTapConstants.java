package com.coveiot.android.sos.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum SOSCleverTapConstants {
    BC_SOS_LANDINGPAGE_VIEWED("BC_SOS_Landingpage_Viewed"),
    BC_SOS_ADD_CONTACT_TAPPED("BC_SOS_Add_Contact_Tapped"),
    BC_SOS_TNC_VIEWED("BC_SOS_Tnc_Viewed"),
    BC_SOS_CONTACTIMPORT_PROCEED_TAPPED("BC_SOS_Contactimport_Proceed_Tapped"),
    BC_SOS_SELECTCONTACT_PAGELOADED("BC_SOS_SelectContact_Pageloaded"),
    BC_SOS_CONTACT_SELECTED("BC_SOS_Contact_Selected"),
    BC_SOS_SYNC_TAPPED("BC_SOS_Sync_Tapped"),
    BC_SOS_EMERGENCY_CONTACT_ADDED("BC_SOS_Emergency_Contact_Added"),
    BC_SOS_NOTIFY_CONTACT_TAPPED("BC_SOS_Notify_Contact_Tapped"),
    BC_SOS_DISABLE_REQUEST("BC_SOS_Disable_Request"),
    BC_SOS_ENABLE_REQUEST("BC_SOS_Enable_Request"),
    BC_SOS_DELETECONTACT_REQUEST("BC_SOS_Deletecontact_Request"),
    BC_SOS_CHANGECONTACT_REQUEST("BC_SOS_Changecontact_Request"),
    BC_SOS_DELETECONTACT_SUCCESS("BC_SOS_Deletecontact_Success"),
    BC_SOS_CHANGECONTACT_SUCCESS("BC_SOS_Changecontact_Success"),
    SOS_HP_GRID("HP Grid"),
    SOS_HP_BANNER("HP Banner"),
    SOS_WATCH_FEATURES("Watch Features"),
    SOS_MY_WATCH("My Watch"),
    SOS_WATCH_SETTINGS("Watch Settings"),
    SOS_APP_PUSH("App Push");
    
    @NotNull
    private String value;

    SOSCleverTapConstants(String str) {
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
