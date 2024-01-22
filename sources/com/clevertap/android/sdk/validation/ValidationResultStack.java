package com.clevertap.android.sdk.validation;

import androidx.annotation.RestrictTo;
import java.util.ArrayList;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class ValidationResultStack {
    public static final Object b = new Object();

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<ValidationResult> f2690a = new ArrayList<>();

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ArrayList<ValidationResult> getPendingValidationResults() {
        return this.f2690a;
    }

    public ValidationResult popValidationResult() {
        ValidationResult validationResult;
        synchronized (b) {
            validationResult = null;
            try {
                if (!this.f2690a.isEmpty()) {
                    validationResult = this.f2690a.remove(0);
                }
            } catch (Exception unused) {
            }
        }
        return validationResult;
    }

    public void pushValidationResult(ValidationResult validationResult) {
        synchronized (b) {
            try {
                int size = this.f2690a.size();
                if (size > 50) {
                    ArrayList<ValidationResult> arrayList = new ArrayList<>();
                    for (int i = 10; i < size; i++) {
                        arrayList.add(this.f2690a.get(i));
                    }
                    arrayList.add(validationResult);
                    this.f2690a = arrayList;
                } else {
                    this.f2690a.add(validationResult);
                }
            } catch (Exception unused) {
            }
        }
    }
}
