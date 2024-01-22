package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleBindInfo {
    public int bind_mod;
    public BindingOps e_ops;
    public String user_id;

    /* loaded from: classes.dex */
    public enum BindingOps {
        normal_begin(0),
        qr_code_begin(1),
        end(2);
        
        public int value;

        BindingOps(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getBind_mod() {
        return this.bind_mod;
    }

    public BindingOps getE_ops() {
        return this.e_ops;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setBind_mod(int i) {
        this.bind_mod = i;
    }

    public void setE_ops(BindingOps bindingOps) {
        this.e_ops = bindingOps;
    }

    public void setUser_id(String str) {
        this.user_id = str;
    }
}
