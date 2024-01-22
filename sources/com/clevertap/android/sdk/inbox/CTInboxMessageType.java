package com.clevertap.android.sdk.inbox;

import androidx.annotation.NonNull;
import androidx.room.FtsOptions;
/* loaded from: classes2.dex */
public enum CTInboxMessageType {
    SimpleMessage(FtsOptions.TOKENIZER_SIMPLE),
    IconMessage("message-icon"),
    CarouselMessage("carousel"),
    CarouselImageMessage("carousel-image");
    
    private final String inboxMessageType;

    CTInboxMessageType(String str) {
        this.inboxMessageType = str;
    }

    public static CTInboxMessageType fromString(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1799711058:
                if (str.equals("carousel-image")) {
                    c = 0;
                    break;
                }
                break;
            case -1332589953:
                if (str.equals("message-icon")) {
                    c = 1;
                    break;
                }
                break;
            case -902286926:
                if (str.equals(FtsOptions.TOKENIZER_SIMPLE)) {
                    c = 2;
                    break;
                }
                break;
            case 2908512:
                if (str.equals("carousel")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return CarouselImageMessage;
            case 1:
                return IconMessage;
            case 2:
                return SimpleMessage;
            case 3:
                return CarouselMessage;
            default:
                return null;
        }
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.inboxMessageType;
    }
}
