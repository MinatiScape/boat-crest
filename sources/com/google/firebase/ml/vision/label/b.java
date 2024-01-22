package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes10.dex */
public final class b implements Continuation<List<FirebaseVisionImageLabel>, List<FirebaseVisionImageLabel>> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FirebaseVisionImageLabeler f11461a;

    public b(FirebaseVisionImageLabeler firebaseVisionImageLabeler) {
        this.f11461a = firebaseVisionImageLabeler;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ List<FirebaseVisionImageLabel> then(@NonNull Task<List<FirebaseVisionImageLabel>> task) throws Exception {
        FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions;
        LinkedList linkedList = new LinkedList();
        for (FirebaseVisionImageLabel firebaseVisionImageLabel : task.getResult()) {
            float confidence = firebaseVisionImageLabel.getConfidence();
            firebaseVisionCloudImageLabelerOptions = this.f11461a.k;
            if (Float.compare(confidence, firebaseVisionCloudImageLabelerOptions.getConfidenceThreshold()) >= 0) {
                linkedList.add(firebaseVisionImageLabel);
            }
        }
        return linkedList;
    }
}
