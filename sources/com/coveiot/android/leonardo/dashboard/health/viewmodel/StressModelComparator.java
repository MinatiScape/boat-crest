package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import com.coveiot.android.leonardo.dashboard.health.model.DataBean;
import java.util.Comparator;
/* loaded from: classes3.dex */
public class StressModelComparator implements Comparator<DataBean> {
    @Override // java.util.Comparator
    public int compare(DataBean dataBean, DataBean dataBean2) {
        if (dataBean != null && dataBean2 != null) {
            if (dataBean.getPercentage() < dataBean2.getPercentage()) {
                return 1;
            }
            if (dataBean.getPercentage() == dataBean2.getPercentage() && dataBean.getIndex() != null && dataBean2.getIndex() != null && dataBean.getIndex().intValue() < dataBean2.getIndex().intValue()) {
                return 1;
            }
        }
        return -1;
    }
}
