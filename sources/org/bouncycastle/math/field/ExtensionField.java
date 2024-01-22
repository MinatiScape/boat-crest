package org.bouncycastle.math.field;
/* loaded from: classes13.dex */
public interface ExtensionField extends FiniteField {
    int getDegree();

    FiniteField getSubfield();
}
