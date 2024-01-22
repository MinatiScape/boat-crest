package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleRemindRespond {
    public int id;
    public RemindRespondResult remindRespondResult;

    /* loaded from: classes.dex */
    public enum RemindRespondResult {
        success(0),
        fail(1),
        mem_full(2),
        time_conflict(3);
        
        public int value;

        RemindRespondResult(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getId() {
        return this.id;
    }

    public RemindRespondResult getRemindRespondOps() {
        return this.remindRespondResult;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setRemindRespondResult(RemindRespondResult remindRespondResult) {
        this.remindRespondResult = remindRespondResult;
    }
}
