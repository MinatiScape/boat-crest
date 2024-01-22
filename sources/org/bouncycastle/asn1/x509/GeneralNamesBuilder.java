package org.bouncycastle.asn1.x509;

import java.util.Vector;
/* loaded from: classes12.dex */
public class GeneralNamesBuilder {

    /* renamed from: a  reason: collision with root package name */
    public Vector f14429a = new Vector();

    public GeneralNamesBuilder addName(GeneralName generalName) {
        this.f14429a.addElement(generalName);
        return this;
    }

    public GeneralNamesBuilder addNames(GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i = 0; i != names.length; i++) {
            this.f14429a.addElement(names[i]);
        }
        return this;
    }

    public GeneralNames build() {
        int size = this.f14429a.size();
        GeneralName[] generalNameArr = new GeneralName[size];
        for (int i = 0; i != size; i++) {
            generalNameArr[i] = (GeneralName) this.f14429a.elementAt(i);
        }
        return new GeneralNames(generalNameArr);
    }
}
