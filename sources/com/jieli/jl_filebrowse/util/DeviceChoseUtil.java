package com.jieli.jl_filebrowse.util;

import com.jieli.jl_filebrowse.FileBrowseManager;
import com.jieli.jl_filebrowse.bean.SDCardBean;
import com.jieli.jl_rcsp.util.JL_Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class DeviceChoseUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12428a = "DeviceChoseUtil";

    public static List<SDCardBean> getSdOfUsbDev() {
        List<SDCardBean> onlineDev = FileBrowseManager.getInstance().getOnlineDev();
        ArrayList arrayList = new ArrayList();
        for (SDCardBean sDCardBean : onlineDev) {
            if (sDCardBean.getType() == 0 || sDCardBean.getType() == 1) {
                arrayList.add(sDCardBean);
            }
        }
        return arrayList;
    }

    public static SDCardBean getTargetDev() {
        if (FileBrowseManager.getInstance().getOnlineDev().size() < 1) {
            JL_Log.e(f12428a, "no online device");
            return null;
        }
        List<SDCardBean> onlineDev = FileBrowseManager.getInstance().getOnlineDev();
        for (SDCardBean sDCardBean : onlineDev) {
            if (sDCardBean.getType() == 0) {
                return sDCardBean;
            }
        }
        for (SDCardBean sDCardBean2 : onlineDev) {
            if (sDCardBean2.getType() == 1) {
                return sDCardBean2;
            }
        }
        return null;
    }

    public static SDCardBean getTargetDevFlash2First() {
        if (FileBrowseManager.getInstance().getOnlineDev().size() < 1) {
            JL_Log.e(f12428a, "no online device");
            return null;
        }
        for (SDCardBean sDCardBean : FileBrowseManager.getInstance().getOnlineDev()) {
            if (sDCardBean.getType() == 4) {
                return sDCardBean;
            }
        }
        return getTargetDev();
    }
}
