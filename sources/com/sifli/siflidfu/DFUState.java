package com.sifli.siflidfu;
/* loaded from: classes12.dex */
public class DFUState {
    public static final int DFU_STATE_DOWNLOADING = 3;
    public static final int DFU_STATE_FORCE = 8;
    public static final int DFU_STATE_INIT = 1;
    public static final int DFU_STATE_INIT_REBOOT = 2;
    public static final int DFU_STATE_INSTALLED = 5;
    public static final int DFU_STATE_INSTALLING = 4;
    public static final int DFU_STATE_NO_STATE = 0;
    public static final int DFU_STATE_REBOOT_CONNECT = 9;
    public static final int DFU_STATE_REBOOT_CONNECT_RETRY = 10;
    public static final int DFU_STATE_RESUME = 6;
    public static final int DFU_STATE_RESUME_REBOOT = 7;

    /* renamed from: a  reason: collision with root package name */
    public int f13699a = 0;

    public int getState() {
        return this.f13699a;
    }

    public void setState(int i) {
        this.f13699a = i;
    }
}
