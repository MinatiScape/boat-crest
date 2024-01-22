package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGCommand;
import com.touchgui.sdk.bean.TGQuickReply;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public final class n1 implements TGCommand {

    /* renamed from: a  reason: collision with root package name */
    public l1 f13798a;
    public TGCallback b;
    public int c = 0;
    public final m1 d = new m1(this);
    public final /* synthetic */ List e;
    public final /* synthetic */ int f;
    public final /* synthetic */ d2 g;

    public n1(d2 d2Var, ArrayList arrayList, int i) {
        this.g = d2Var;
        this.e = arrayList;
        this.f = i;
    }

    public final void a() {
        if (this.c >= this.e.size()) {
            TGCallback tGCallback = this.b;
            if (tGCallback != null) {
                tGCallback.onSuccess(null);
            }
            this.f13798a = null;
            return;
        }
        d2 d2Var = this.g;
        List list = this.e;
        int i = this.c;
        this.c = i + 1;
        l1 l1Var = new l1(d2Var, d2Var.f13753a, new u5((TGQuickReply) list.get(i), this.f));
        this.f13798a = l1Var;
        l1Var.execute(this.d);
    }

    @Override // com.touchgui.sdk.TGCommand
    public final void cancel() {
        l1 l1Var = this.f13798a;
        if (l1Var != null) {
            l1Var.cancel();
        }
    }

    @Override // com.touchgui.sdk.TGCommand
    public final void execute(TGCallback tGCallback) {
        this.b = tGCallback;
        a();
    }
}
