package com.google.firebase.ml.vision;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.ml.vision.automl.internal.zzb;
/* loaded from: classes10.dex */
public final /* synthetic */ class a implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final ComponentFactory f11409a = new a();

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        Preconditions.checkArgument(((zzqf) componentContainer.get(zzqf.class)).zzoh().getName().equals(FirebaseApp.DEFAULT_APP_NAME), "FirebaseAutoMLModelManager doesn't support Nondefault FirebaseApp");
        return new zzb((zzqf) componentContainer.get(zzqf.class), ((zzqg.zza) componentContainer.get(zzqg.zza.class)).get(5));
    }
}
