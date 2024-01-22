package com.google.firebase.ml.common;

import android.content.Context;
import com.google.android.gms.internal.firebase_ml.zzqv;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
/* loaded from: classes10.dex */
public final /* synthetic */ class b implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final ComponentFactory f11375a = new b();

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new zzqv((Context) componentContainer.get(Context.class));
    }
}
