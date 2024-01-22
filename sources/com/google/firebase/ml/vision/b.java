package com.google.firebase.ml.vision;

import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
/* loaded from: classes10.dex */
public final /* synthetic */ class b implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final ComponentFactory f11416a = new b();

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new FirebaseVision((zzqf) componentContainer.get(zzqf.class));
    }
}
