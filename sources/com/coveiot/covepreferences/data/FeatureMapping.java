package com.coveiot.covepreferences.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FeatureMapping {
    @SerializedName("jeiliChipModels")

    /* renamed from: a  reason: collision with root package name */
    private List<String> f7024a;
    @SerializedName("indusindBankBranding")
    private IndusindBankBrandingDTO b;

    /* loaded from: classes8.dex */
    public static class IndusindBankBrandingDTO {
        @SerializedName("wellness_crew")

        /* renamed from: a  reason: collision with root package name */
        private List<String> f7025a;

        public List<String> getWellnessCrew() {
            return this.f7025a;
        }

        public void setWellnessCrew(List<String> list) {
            this.f7025a = list;
        }
    }

    public IndusindBankBrandingDTO getIndusindBankBranding() {
        return this.b;
    }

    public List<String> getJeiliChipModels() {
        return this.f7024a;
    }

    public void setIndusindBankBranding(IndusindBankBrandingDTO indusindBankBrandingDTO) {
        this.b = indusindBankBrandingDTO;
    }

    public void setJeiliChipModels(List<String> list) {
        this.f7024a = list;
    }
}
