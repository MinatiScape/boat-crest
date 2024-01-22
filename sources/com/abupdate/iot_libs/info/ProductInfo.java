package com.abupdate.iot_libs.info;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.security.FotaException;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.trace.Trace;
/* loaded from: classes.dex */
public class ProductInfo {

    /* renamed from: a  reason: collision with root package name */
    public static ProductInfo f1897a;
    public String productId;
    public String productSecret;

    public static ProductInfo getInstance() {
        if (f1897a == null) {
            synchronized (ProductInfo.class) {
                if (f1897a == null) {
                    f1897a = new ProductInfo();
                }
            }
        }
        return f1897a;
    }

    public final void a() {
        this.productId = SPFTool.getString(SPFTool.KEY_PRODUCT_ID, "");
        this.productSecret = SPFTool.getString(SPFTool.KEY_PRODUCT_SECRET, "");
    }

    public void init() throws FotaException {
        Object obj;
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = OtaAgentPolicy.sCx.getPackageManager().getApplicationInfo(OtaAgentPolicy.sCx.getPackageName(), 128);
            Object obj2 = null;
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                obj = null;
            } else {
                obj2 = bundle.get("fota_configuration_product_id");
                obj = applicationInfo.metaData.get("fota_configuration_product_secret");
            }
            if (obj2 == null && obj == null) {
                a();
                return;
            }
            String str = (String) obj2;
            String str2 = (String) obj;
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && str.startsWith("string/") && str2.startsWith("string/")) {
                this.productId = str.replace("string/", "").trim();
                this.productSecret = str2.replace("string/", "").trim();
                return;
            }
            throw new FotaException(203);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new FotaException(e);
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            throw new FotaException(203, e2);
        }
    }

    public boolean isProductValid() {
        boolean z;
        if (TextUtils.isEmpty(this.productSecret)) {
            Trace.d("ProductInfo", "isValid() product_secret = null");
            z = false;
        } else {
            z = true;
        }
        if (TextUtils.isEmpty(this.productId)) {
            Trace.d("ProductInfo", "isValid() product id = null");
            return false;
        }
        return z;
    }

    public void reset() {
        Trace.d("ProductInfo", "product info reset");
        this.productId = "";
        this.productSecret = "";
        SPFTool.putString(SPFTool.KEY_PRODUCT_ID, "");
        SPFTool.putString(SPFTool.KEY_PRODUCT_SECRET, "");
    }

    public void setAndStoreProductInfo(String str, String str2) {
        SPFTool.putString(SPFTool.KEY_PRODUCT_ID, str);
        SPFTool.putString(SPFTool.KEY_PRODUCT_SECRET, str2);
        getInstance().productId = str;
        getInstance().productSecret = str2;
    }
}
