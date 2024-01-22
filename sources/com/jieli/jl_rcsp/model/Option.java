package com.jieli.jl_rcsp.model;
/* loaded from: classes11.dex */
public class Option {
    private boolean isMultiDevice;

    public static Option createOption() {
        return new Option().setMultiDevice(false);
    }

    public boolean isMultiDevice() {
        return this.isMultiDevice;
    }

    public Option setMultiDevice(boolean z) {
        this.isMultiDevice = z;
        return this;
    }

    public String toString() {
        return "Option{isMultiDevice=" + this.isMultiDevice + '}';
    }
}
