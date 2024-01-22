package com.coveiot.android.tappy.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum TappyCleverTapConstants {
    BANK_NAME("Bank name"),
    CARD_SUBMISSION("Card Submission"),
    SCAN_CARD("Scan card"),
    MANUAL_ENTRY("Manual Entry"),
    PAIRLOCATION("Pairlocation"),
    PAIR_NFC_LANDING_PAGE("Pair NFC landing page"),
    STRAP_ID("Strap ID"),
    STRAP_NAME("Strap name"),
    STRAP_STATUS("Strap status"),
    CONNECTED("Connected"),
    DISCONNECTED("disconnected"),
    CARD_STATUS("Card Status"),
    ADDED("Added"),
    NOT_ADDED("Not Added"),
    FAILED("failed"),
    SUCCESSFUL("successful"),
    CARD_SUCCESSFULLY_TOKENIZED("Card successfully tokenized"),
    REQUEST_LOCATION("Request location"),
    CARD_NOS("Card Nos"),
    CARD_LINKED_WITH("Card Linked With"),
    CARD_TYPE("Card Type"),
    INITIATED_BY("Initiated_by"),
    USER("User"),
    SYSTEM("system"),
    BOATPAY_HP("boAtpay HP"),
    SETTINGS_STRAP_MGMNT_PAGE("Settings Strap Mgmnt Page"),
    NEW_STRAP_NAME("New strap name"),
    CARD_MGMNT("Card Mgmnt"),
    MASTER("Master"),
    VISA("Visa");
    
    @NotNull
    private String value;

    TappyCleverTapConstants(String str) {
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
