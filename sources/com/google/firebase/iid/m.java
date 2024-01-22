package com.google.firebase.iid;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
/* loaded from: classes10.dex */
public final /* synthetic */ class m implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final ComponentFactory f11297a = new m();

    @Override // com.google.firebase.components.ComponentFactory
    public Object create(ComponentContainer componentContainer) {
        return Registrar.lambda$getComponents$1$Registrar(componentContainer);
    }
}
