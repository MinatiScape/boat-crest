package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public abstract class CommandInterpreter<I, O> {

    /* renamed from: a  reason: collision with root package name */
    public I f5628a;

    public CommandInterpreter(I i) {
        this.f5628a = i;
    }

    @Nullable
    public abstract O getBleRequestObject();

    public final I getSCommandObject() {
        return this.f5628a;
    }

    public final void setSCommandObject(I i) {
        this.f5628a = i;
    }
}
