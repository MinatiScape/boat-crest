package com.abupdate.iot_libs.policy;

import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.inter.IParsePolicyListener;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class PolicyConfig {

    /* renamed from: a  reason: collision with root package name */
    public static PolicyConfig f1906a;
    public boolean wifi = true;
    public boolean storage_size = true;
    public boolean storage_path = true;
    public boolean battery = true;
    public boolean check_cycle = true;
    public boolean install_force = true;
    public boolean download_force = true;
    public boolean install_free_time = true;
    public boolean reboot_update_force = true;
    public boolean remind_cycle = true;
    public Map<String, IParsePolicyListener> parsePolicyListenerMap = new HashMap();

    public static PolicyConfig getInstance() {
        if (f1906a == null) {
            synchronized (PolicyConfig.class) {
                if (f1906a == null) {
                    f1906a = new PolicyConfig();
                }
            }
        }
        return f1906a;
    }

    public PolicyConfig parsePolicyYourself(OtaConstants.PolicyType policyType, IParsePolicyListener iParsePolicyListener) {
        this.parsePolicyListenerMap.put(policyType.getType(), iParsePolicyListener);
        return this;
    }

    public PolicyConfig request_battery(boolean z) {
        this.battery = z;
        return this;
    }

    public PolicyConfig request_check_cycle(boolean z) {
        this.check_cycle = z;
        return this;
    }

    public PolicyConfig request_download_force(boolean z) {
        this.download_force = z;
        return this;
    }

    public PolicyConfig request_install_force(boolean z) {
        this.install_force = z;
        return this;
    }

    public PolicyConfig request_install_free_time(boolean z) {
        this.install_free_time = z;
        return this;
    }

    public PolicyConfig request_reboot_update_force(boolean z) {
        this.reboot_update_force = z;
        return this;
    }

    public PolicyConfig request_remind_cycle(boolean z) {
        this.remind_cycle = z;
        return this;
    }

    public PolicyConfig request_storage_path(boolean z) {
        this.storage_path = z;
        return this;
    }

    public PolicyConfig request_storage_size(boolean z) {
        this.storage_size = z;
        return this;
    }

    public PolicyConfig request_wifi(boolean z) {
        this.wifi = z;
        return this;
    }
}
