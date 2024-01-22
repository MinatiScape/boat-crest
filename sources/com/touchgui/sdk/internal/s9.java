package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGEventListener;
import com.touchgui.sdk.bean.TGQuickReply;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class s9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t9 f13823a;

    public s9(t9 t9Var) {
        this.f13823a = t9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        String str;
        TGQuickReply tGQuickReply = (TGQuickReply) obj;
        if (tGQuickReply != null) {
            if (tGQuickReply.getReplyTo() == null && (str = this.f13823a.f13826a.E) != null) {
                tGQuickReply.setReplyTo(str);
            }
            Iterator it = this.f13823a.b.iterator();
            while (it.hasNext()) {
                ((TGEventListener) it.next()).onQuickReply(tGQuickReply);
            }
            this.f13823a.f13826a.E = null;
        }
    }
}
