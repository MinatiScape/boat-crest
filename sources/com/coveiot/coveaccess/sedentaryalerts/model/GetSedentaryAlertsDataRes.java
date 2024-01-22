package com.coveiot.coveaccess.sedentaryalerts.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetSedentaryAlertsDataRes extends CoveApiResponseBaseModel {
    public List<SedentaryAlertsDataBean> sedentaryAlertsDataBeanList;

    public GetSedentaryAlertsDataRes(int i) {
        super(i);
    }

    public List<SedentaryAlertsDataBean> getSedentaryAlertsDataBeanList() {
        return this.sedentaryAlertsDataBeanList;
    }

    public void setSedentaryAlertsDataBeanList(List<SedentaryAlertsDataBean> list) {
        this.sedentaryAlertsDataBeanList = list;
    }

    public String toString() {
        return "GetSedentaryAlertsDataRes{sedentaryAlertsDataBeanList=" + this.sedentaryAlertsDataBeanList + '}';
    }
}
