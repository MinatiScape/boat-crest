package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGDial;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class ea extends TGDial {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f13760a;
    public int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public String g;
    public int h;
    public int i;

    public ea(int i, String str) {
        super(i, str);
        this.f13760a = new ArrayList();
        this.b = -1;
        this.c = 368;
        this.d = 448;
        this.e = 220;
        this.f = DfuException.ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES;
        this.h = 0;
        this.i = 0;
    }
}
