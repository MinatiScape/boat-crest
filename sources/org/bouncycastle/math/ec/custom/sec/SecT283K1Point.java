package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class SecT283K1Point extends ECPoint.AbstractF2m {
    public SecT283K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecT283K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = z;
    }

    public SecT283K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        ECFieldElement eCFieldElement;
        ECFieldElement eCFieldElement2;
        ECFieldElement eCFieldElement3;
        ECFieldElement eCFieldElement4;
        ECFieldElement eCFieldElement5;
        ECFieldElement eCFieldElement6;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement7 = this.x;
        ECFieldElement rawXCoord = eCPoint.getRawXCoord();
        if (eCFieldElement7.isZero()) {
            return rawXCoord.isZero() ? curve.getInfinity() : eCPoint.add(this);
        }
        ECFieldElement eCFieldElement8 = this.y;
        ECFieldElement eCFieldElement9 = this.zs[0];
        ECFieldElement rawYCoord = eCPoint.getRawYCoord();
        ECFieldElement zCoord = eCPoint.getZCoord(0);
        boolean isOne = eCFieldElement9.isOne();
        if (isOne) {
            eCFieldElement = rawXCoord;
            eCFieldElement2 = rawYCoord;
        } else {
            eCFieldElement = rawXCoord.multiply(eCFieldElement9);
            eCFieldElement2 = rawYCoord.multiply(eCFieldElement9);
        }
        boolean isOne2 = zCoord.isOne();
        if (isOne2) {
            eCFieldElement3 = eCFieldElement8;
        } else {
            eCFieldElement7 = eCFieldElement7.multiply(zCoord);
            eCFieldElement3 = eCFieldElement8.multiply(zCoord);
        }
        ECFieldElement add = eCFieldElement3.add(eCFieldElement2);
        ECFieldElement add2 = eCFieldElement7.add(eCFieldElement);
        if (add2.isZero()) {
            return add.isZero() ? twice() : curve.getInfinity();
        }
        if (rawXCoord.isZero()) {
            ECPoint normalize = normalize();
            ECFieldElement xCoord = normalize.getXCoord();
            ECFieldElement yCoord = normalize.getYCoord();
            ECFieldElement divide = yCoord.add(rawYCoord).divide(xCoord);
            eCFieldElement4 = divide.square().add(divide).add(xCoord);
            if (eCFieldElement4.isZero()) {
                return new SecT283K1Point(curve, eCFieldElement4, curve.getB(), this.withCompression);
            }
            ECFieldElement add3 = divide.multiply(xCoord.add(eCFieldElement4)).add(eCFieldElement4).add(yCoord).divide(eCFieldElement4).add(eCFieldElement4);
            eCFieldElement6 = curve.fromBigInteger(ECConstants.ONE);
            eCFieldElement5 = add3;
        } else {
            ECFieldElement square = add2.square();
            ECFieldElement multiply = add.multiply(eCFieldElement7);
            ECFieldElement multiply2 = add.multiply(eCFieldElement);
            ECFieldElement multiply3 = multiply.multiply(multiply2);
            if (multiply3.isZero()) {
                return new SecT283K1Point(curve, multiply3, curve.getB(), this.withCompression);
            }
            ECFieldElement multiply4 = add.multiply(square);
            ECFieldElement multiply5 = !isOne2 ? multiply4.multiply(zCoord) : multiply4;
            ECFieldElement squarePlusProduct = multiply2.add(square).squarePlusProduct(multiply5, eCFieldElement8.add(eCFieldElement9));
            if (!isOne) {
                multiply5 = multiply5.multiply(eCFieldElement9);
            }
            eCFieldElement4 = multiply3;
            eCFieldElement5 = squarePlusProduct;
            eCFieldElement6 = multiply5;
        }
        return new SecT283K1Point(curve, eCFieldElement4, eCFieldElement5, new ECFieldElement[]{eCFieldElement6}, this.withCompression);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint detach() {
        return new SecT283K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public boolean getCompressionYTilde() {
        ECFieldElement rawXCoord = getRawXCoord();
        return (rawXCoord.isZero() || getRawYCoord().testBitZero() == rawXCoord.testBitZero()) ? false : true;
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECFieldElement getYCoord() {
        ECFieldElement eCFieldElement = this.x;
        ECFieldElement eCFieldElement2 = this.y;
        if (isInfinity() || eCFieldElement.isZero()) {
            return eCFieldElement2;
        }
        ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
        ECFieldElement eCFieldElement3 = this.zs[0];
        return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return this;
        }
        ECFieldElement eCFieldElement2 = this.y;
        ECFieldElement eCFieldElement3 = this.zs[0];
        return new SecT283K1Point(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return curve.getInfinity();
        }
        ECFieldElement eCFieldElement2 = this.y;
        ECFieldElement eCFieldElement3 = this.zs[0];
        boolean isOne = eCFieldElement3.isOne();
        ECFieldElement square = isOne ? eCFieldElement3 : eCFieldElement3.square();
        ECFieldElement add = isOne ? eCFieldElement2.square().add(eCFieldElement2) : eCFieldElement2.add(eCFieldElement3).multiply(eCFieldElement2);
        if (add.isZero()) {
            return new SecT283K1Point(curve, add, curve.getB(), this.withCompression);
        }
        ECFieldElement square2 = add.square();
        ECFieldElement multiply = isOne ? add : add.multiply(square);
        ECFieldElement square3 = eCFieldElement2.add(eCFieldElement).square();
        if (!isOne) {
            eCFieldElement3 = square.square();
        }
        return new SecT283K1Point(curve, square2, square3.add(add).add(square).multiply(square3).add(eCFieldElement3).add(square2).add(multiply), new ECFieldElement[]{multiply}, this.withCompression);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return twice();
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return eCPoint;
        }
        ECFieldElement rawXCoord = eCPoint.getRawXCoord();
        ECFieldElement zCoord = eCPoint.getZCoord(0);
        if (rawXCoord.isZero() || !zCoord.isOne()) {
            return twice().add(eCPoint);
        }
        ECFieldElement eCFieldElement2 = this.y;
        ECFieldElement eCFieldElement3 = this.zs[0];
        ECFieldElement rawYCoord = eCPoint.getRawYCoord();
        ECFieldElement square = eCFieldElement.square();
        ECFieldElement square2 = eCFieldElement2.square();
        ECFieldElement square3 = eCFieldElement3.square();
        ECFieldElement add = square2.add(eCFieldElement2.multiply(eCFieldElement3));
        ECFieldElement addOne = rawYCoord.addOne();
        ECFieldElement multiplyPlusProduct = addOne.multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
        ECFieldElement multiply = rawXCoord.multiply(square3);
        ECFieldElement square4 = multiply.add(add).square();
        if (square4.isZero()) {
            return multiplyPlusProduct.isZero() ? eCPoint.twice() : curve.getInfinity();
        } else if (multiplyPlusProduct.isZero()) {
            return new SecT283K1Point(curve, multiplyPlusProduct, curve.getB(), this.withCompression);
        } else {
            ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
            ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
            return new SecT283K1Point(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3}, this.withCompression);
        }
    }
}