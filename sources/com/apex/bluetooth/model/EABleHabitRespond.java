package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleHabitRespond {
    public int id;
    public Result result;

    /* loaded from: classes.dex */
    public enum Result {
        success(0),
        fail(1),
        mem_full(2),
        time_conflict(3);
        
        public int value;

        Result(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getId() {
        return this.id;
    }

    public Result getResult() {
        return this.result;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
