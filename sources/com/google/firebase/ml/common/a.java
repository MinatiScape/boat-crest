package com.google.firebase.ml.common;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
/* loaded from: classes10.dex */
public final /* synthetic */ class a implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final ComponentFactory f11374a = new a();

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new FirebaseModelManager(componentContainer.setOf(FirebaseModelManager.RemoteModelManagerRegistration.class));
    }
}
