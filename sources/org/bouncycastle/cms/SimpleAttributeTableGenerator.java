package org.bouncycastle.cms;

import java.util.Map;
import org.bouncycastle.asn1.cms.AttributeTable;
/* loaded from: classes12.dex */
public class SimpleAttributeTableGenerator implements CMSAttributeTableGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final AttributeTable f14550a;

    public SimpleAttributeTableGenerator(AttributeTable attributeTable) {
        this.f14550a = attributeTable;
    }

    @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
    public AttributeTable getAttributes(Map map) {
        return this.f14550a;
    }
}
