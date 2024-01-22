package com.coveiot.coveaccess.fitness.config.models.responsemodel;

import java.io.Serializable;
/* loaded from: classes8.dex */
public final class DefaultFitnessConfigDataResponse implements Serializable {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private String defaultHeight;
        private String defaultHeightUnit;
        private String defaultRunningStrideLength;
        private String defaultWalkingStrideLength;
        private String defaultWeight;
        private String defaultWeightUnit;
        private String uvSuggestionsFile;

        public String getDefaultHeight() {
            return this.defaultHeight;
        }

        public String getDefaultHeightUnit() {
            return this.defaultHeightUnit;
        }

        public String getDefaultRunningStrideLength() {
            return this.defaultRunningStrideLength;
        }

        public String getDefaultWalkingStrideLength() {
            return this.defaultWalkingStrideLength;
        }

        public String getDefaultWeight() {
            return this.defaultWeight;
        }

        public String getDefaultWeightUnit() {
            return this.defaultWeightUnit;
        }

        public String getUvSuggestionsFile() {
            return this.uvSuggestionsFile;
        }

        public void setDefaultHeight(String str) {
            this.defaultHeight = str;
        }

        public void setDefaultHeightUnit(String str) {
            this.defaultHeightUnit = str;
        }

        public void setDefaultRunningStrideLength(String str) {
            this.defaultRunningStrideLength = str;
        }

        public void setDefaultWalkingStrideLength(String str) {
            this.defaultWalkingStrideLength = str;
        }

        public void setDefaultWeight(String str) {
            this.defaultWeight = str;
        }

        public void setDefaultWeightUnit(String str) {
            this.defaultWeightUnit = str;
        }

        public void setUvSuggestionsFile(String str) {
            this.uvSuggestionsFile = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
