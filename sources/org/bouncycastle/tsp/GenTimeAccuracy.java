package org.bouncycastle.tsp;

import com.jstyle.blesdk1860.constant.BleConst;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.tsp.Accuracy;
/* loaded from: classes13.dex */
public class GenTimeAccuracy {

    /* renamed from: a  reason: collision with root package name */
    public Accuracy f15379a;

    public GenTimeAccuracy(Accuracy accuracy) {
        this.f15379a = accuracy;
    }

    public final String a(int i) {
        StringBuilder sb;
        String str;
        if (i < 10) {
            sb = new StringBuilder();
            str = "00";
        } else if (i >= 100) {
            return Integer.toString(i);
        } else {
            sb = new StringBuilder();
            str = BleConst.GetDeviceTime;
        }
        sb.append(str);
        sb.append(i);
        return sb.toString();
    }

    public final int b(ASN1Integer aSN1Integer) {
        if (aSN1Integer != null) {
            return aSN1Integer.getValue().intValue();
        }
        return 0;
    }

    public int getMicros() {
        return b(this.f15379a.getMicros());
    }

    public int getMillis() {
        return b(this.f15379a.getMillis());
    }

    public int getSeconds() {
        return b(this.f15379a.getSeconds());
    }

    public String toString() {
        return getSeconds() + "." + a(getMillis()) + a(getMicros());
    }
}
