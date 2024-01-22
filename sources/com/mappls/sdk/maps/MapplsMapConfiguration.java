package com.mappls.sdk.maps;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public class MapplsMapConfiguration {
    private static final MapplsMapConfiguration OUR_INSTANCE = new MapplsMapConfiguration();
    private boolean isShowLastSelectedStyle = true;
    private boolean usingRasterStyle = false;
    private boolean allowOtherUrls = false;
    private boolean developerShowingSplash = false;
    private boolean isEnablePromotion = false;

    public static MapplsMapConfiguration getInstance() {
        return OUR_INSTANCE;
    }

    public boolean isAllowOtherUrls() {
        return this.allowOtherUrls;
    }

    public boolean isDeveloperShowingSplash() {
        return this.developerShowingSplash;
    }

    public boolean isEnablePromotion() {
        return this.isEnablePromotion;
    }

    public boolean isShowLastSelectedStyle() {
        return this.isShowLastSelectedStyle;
    }

    public boolean isUsingRasterStyle() {
        return this.usingRasterStyle;
    }

    public void setAllowOtherUrls(boolean z) {
        this.allowOtherUrls = z;
    }

    public void setDeveloperShowingSplash(boolean z) {
        this.developerShowingSplash = z;
    }

    public void setEnablePromotion(boolean z) {
        this.isEnablePromotion = z;
    }

    public void setShowLastSelectedStyle(boolean z) {
        this.isShowLastSelectedStyle = z;
    }

    public void setUsingRasterStyle(boolean z) {
        this.usingRasterStyle = z;
    }
}
