package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes6.dex */
public class COSEAlgorithmIdentifier implements Parcelable {
    @NonNull
    public static final Parcelable.Creator<COSEAlgorithmIdentifier> CREATOR = new g();
    @NonNull
    public final Algorithm h;

    /* loaded from: classes6.dex */
    public static class UnsupportedAlgorithmIdentifierException extends Exception {
        public UnsupportedAlgorithmIdentifierException(int i) {
            super("Algorithm with COSE value " + i + " not supported");
        }
    }

    public COSEAlgorithmIdentifier(@NonNull Algorithm algorithm) {
        this.h = (Algorithm) Preconditions.checkNotNull(algorithm);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static COSEAlgorithmIdentifier fromCoseValue(int i) throws UnsupportedAlgorithmIdentifierException {
        EC2Algorithm[] values;
        RSAAlgorithm rSAAlgorithm;
        if (i == RSAAlgorithm.LEGACY_RS1.getAlgoValue()) {
            rSAAlgorithm = RSAAlgorithm.RS1;
        } else {
            RSAAlgorithm[] values2 = RSAAlgorithm.values();
            int length = values2.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    RSAAlgorithm rSAAlgorithm2 = values2[i2];
                    if (rSAAlgorithm2.getAlgoValue() == i) {
                        rSAAlgorithm = rSAAlgorithm2;
                        break;
                    }
                    i2++;
                } else {
                    for (RSAAlgorithm rSAAlgorithm3 : EC2Algorithm.values()) {
                        if (rSAAlgorithm3.getAlgoValue() == i) {
                            rSAAlgorithm = rSAAlgorithm3;
                        }
                    }
                    throw new UnsupportedAlgorithmIdentifierException(i);
                }
            }
        }
        return new COSEAlgorithmIdentifier(rSAAlgorithm);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@NonNull Object obj) {
        return (obj instanceof COSEAlgorithmIdentifier) && this.h.getAlgoValue() == ((COSEAlgorithmIdentifier) obj).h.getAlgoValue();
    }

    public int hashCode() {
        return Objects.hashCode(this.h);
    }

    public int toCoseValue() {
        return this.h.getAlgoValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(this.h.getAlgoValue());
    }
}
