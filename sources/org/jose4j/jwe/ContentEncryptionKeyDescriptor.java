package org.jose4j.jwe;
/* loaded from: classes13.dex */
public class ContentEncryptionKeyDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public final int f15515a;
    public final String b;

    public ContentEncryptionKeyDescriptor(int i, String str) {
        this.f15515a = i;
        this.b = str;
    }

    public String getContentEncryptionKeyAlgorithm() {
        return this.b;
    }

    public int getContentEncryptionKeyByteLength() {
        return this.f15515a;
    }
}
