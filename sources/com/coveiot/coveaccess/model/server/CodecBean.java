package com.coveiot.coveaccess.model.server;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class CodecBean {
    @SerializedName("moderate")
    private NormalBean moderate;
    @SerializedName(Constants.PRIORITY_NORMAL)
    private NormalBean normal;
    @SerializedName("overstress")
    private NormalBean overstress;
    @SerializedName("relax")
    private RelaxBean relax;

    public NormalBean getModerate() {
        return this.moderate;
    }

    public NormalBean getNormal() {
        return this.normal;
    }

    public NormalBean getOverstress() {
        return this.overstress;
    }

    public RelaxBean getRelax() {
        return this.relax;
    }

    public void setModerate(NormalBean normalBean) {
        this.moderate = normalBean;
    }

    public void setNormal(NormalBean normalBean) {
        this.normal = normalBean;
    }

    public void setOverstress(NormalBean normalBean) {
        this.overstress = normalBean;
    }

    public void setRelax(RelaxBean relaxBean) {
        this.relax = relaxBean;
    }
}
