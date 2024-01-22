package org.bouncycastle.crypto;
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes5.dex */
public abstract class PasswordConverter implements CharToByteConverter {
    private static final /* synthetic */ PasswordConverter[] $VALUES;
    public static final PasswordConverter ASCII;
    public static final PasswordConverter PKCS12;
    public static final PasswordConverter UTF8;

    /* loaded from: classes5.dex */
    public enum a extends PasswordConverter {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // org.bouncycastle.crypto.CharToByteConverter
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS5PasswordToBytes(cArr);
        }

        @Override // org.bouncycastle.crypto.CharToByteConverter
        public String getType() {
            return "ASCII";
        }
    }

    static {
        a aVar = new a("ASCII", 0);
        ASCII = aVar;
        PasswordConverter passwordConverter = new PasswordConverter("UTF8", 1) { // from class: org.bouncycastle.crypto.PasswordConverter.b
            @Override // org.bouncycastle.crypto.CharToByteConverter
            public byte[] convert(char[] cArr) {
                return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(cArr);
            }

            @Override // org.bouncycastle.crypto.CharToByteConverter
            public String getType() {
                return "UTF8";
            }
        };
        UTF8 = passwordConverter;
        PasswordConverter passwordConverter2 = new PasswordConverter("PKCS12", 2) { // from class: org.bouncycastle.crypto.PasswordConverter.c
            @Override // org.bouncycastle.crypto.CharToByteConverter
            public byte[] convert(char[] cArr) {
                return PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
            }

            @Override // org.bouncycastle.crypto.CharToByteConverter
            public String getType() {
                return "PKCS12";
            }
        };
        PKCS12 = passwordConverter2;
        $VALUES = new PasswordConverter[]{aVar, passwordConverter, passwordConverter2};
    }

    private PasswordConverter(String str, int i) {
    }

    public /* synthetic */ PasswordConverter(String str, int i, a aVar) {
        this(str, i);
    }

    public static PasswordConverter valueOf(String str) {
        return (PasswordConverter) Enum.valueOf(PasswordConverter.class, str);
    }

    public static PasswordConverter[] values() {
        return (PasswordConverter[]) $VALUES.clone();
    }
}
