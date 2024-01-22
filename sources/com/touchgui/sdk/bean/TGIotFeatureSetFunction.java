package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes12.dex */
public class TGIotFeatureSetFunction extends TGIotFunction {
    private int currentIndex;
    @Nullable
    private List<IotFeature> features;

    /* loaded from: classes12.dex */
    public static class IotFeature {
        private String name;
        private String value;

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }

    public TGIotFeatureSetFunction() {
        super(5);
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    @Nullable
    public List<IotFeature> getFeatures() {
        return this.features;
    }

    public void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public void setFeatures(@Nullable List<IotFeature> list) {
        this.features = list;
    }
}
