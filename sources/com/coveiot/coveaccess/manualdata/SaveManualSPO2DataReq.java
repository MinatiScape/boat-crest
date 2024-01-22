package com.coveiot.coveaccess.manualdata;

import com.coveiot.coveaccess.model.server.SPO2FitnessSessionDataBean;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveManualSPO2DataReq {

    /* renamed from: a  reason: collision with root package name */
    public List<SPO2FitnessSessionDataBean> f6648a;

    public SaveManualSPO2DataReq(List<SPO2FitnessSessionDataBean> list) {
        this.f6648a = list;
    }

    public List<SPO2FitnessSessionDataBean> getManualSPO2DataList() {
        return this.f6648a;
    }
}
