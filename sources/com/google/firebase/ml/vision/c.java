package com.google.firebase.ml.vision;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.vision.automl.FirebaseAutoMLRemoteModel;
import com.google.firebase.ml.vision.automl.internal.zzb;
/* loaded from: classes10.dex */
public final /* synthetic */ class c implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final ComponentFactory f11435a = new c();

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new FirebaseModelManager.RemoteModelManagerRegistration(FirebaseAutoMLRemoteModel.class, componentContainer.getProvider(zzb.class));
    }
}
