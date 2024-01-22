package org.bouncycastle.math.field;

import java.math.BigInteger;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class b implements PolynomialExtensionField {

    /* renamed from: a  reason: collision with root package name */
    public final FiniteField f15194a;
    public final Polynomial b;

    public b(FiniteField finiteField, Polynomial polynomial) {
        this.f15194a = finiteField;
        this.b = polynomial;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            return this.f15194a.equals(bVar.f15194a) && this.b.equals(bVar.b);
        }
        return false;
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public BigInteger getCharacteristic() {
        return this.f15194a.getCharacteristic();
    }

    @Override // org.bouncycastle.math.field.ExtensionField
    public int getDegree() {
        return this.b.getDegree();
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public int getDimension() {
        return this.f15194a.getDimension() * this.b.getDegree();
    }

    @Override // org.bouncycastle.math.field.PolynomialExtensionField
    public Polynomial getMinimalPolynomial() {
        return this.b;
    }

    @Override // org.bouncycastle.math.field.ExtensionField
    public FiniteField getSubfield() {
        return this.f15194a;
    }

    public int hashCode() {
        return this.f15194a.hashCode() ^ Integers.rotateLeft(this.b.hashCode(), 16);
    }
}
