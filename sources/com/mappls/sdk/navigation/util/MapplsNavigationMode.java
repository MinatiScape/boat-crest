package com.mappls.sdk.navigation.util;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public enum MapplsNavigationMode {
    ONLINE("Online"),
    OFFLINE("Offline"),
    AUTOMATIC("Automatic");
    
    private String mode;

    MapplsNavigationMode(String str) {
        this.mode = str;
    }
}
