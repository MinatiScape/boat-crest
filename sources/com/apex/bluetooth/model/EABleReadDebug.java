package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleReadDebug {
    public String log;
    public int mem_addr;
    public int mem_data_len;
    public DebugType type;

    /* loaded from: classes.dex */
    public enum DebugType {
        error_log(0),
        mem_log(1),
        hr_log_on(2),
        hr_log_off(3),
        error_debug(4);
        
        public int value;

        DebugType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public String getLog() {
        return this.log;
    }

    public int getMem_addr() {
        return this.mem_addr;
    }

    public int getMem_data_len() {
        return this.mem_data_len;
    }

    public DebugType getType() {
        return this.type;
    }

    public void setLog(String str) {
        this.log = str;
    }

    public void setMem_addr(int i) {
        this.mem_addr = i;
    }

    public void setMem_data_len(int i) {
        this.mem_data_len = i;
    }

    public void setType(DebugType debugType) {
        this.type = debugType;
    }
}
