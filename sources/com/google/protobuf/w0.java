package com.google.protobuf;
/* loaded from: classes11.dex */
public final class w0 {

    /* loaded from: classes11.dex */
    public static class a implements c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ByteString f11757a;

        public a(ByteString byteString) {
            this.f11757a = byteString;
        }

        @Override // com.google.protobuf.w0.c
        public byte a(int i) {
            return this.f11757a.byteAt(i);
        }

        @Override // com.google.protobuf.w0.c
        public int size() {
            return this.f11757a.size();
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f11758a;

        public b(byte[] bArr) {
            this.f11758a = bArr;
        }

        @Override // com.google.protobuf.w0.c
        public byte a(int i) {
            return this.f11758a[i];
        }

        @Override // com.google.protobuf.w0.c
        public int size() {
            return this.f11758a.length;
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        byte a(int i);

        int size();
    }

    public static String a(ByteString byteString) {
        return b(new a(byteString));
    }

    public static String b(c cVar) {
        StringBuilder sb = new StringBuilder(cVar.size());
        for (int i = 0; i < cVar.size(); i++) {
            byte a2 = cVar.a(i);
            if (a2 == 34) {
                sb.append("\\\"");
            } else if (a2 == 39) {
                sb.append("\\'");
            } else if (a2 != 92) {
                switch (a2) {
                    case 7:
                        sb.append("\\a");
                        continue;
                    case 8:
                        sb.append("\\b");
                        continue;
                    case 9:
                        sb.append("\\t");
                        continue;
                    case 10:
                        sb.append("\\n");
                        continue;
                    case 11:
                        sb.append("\\v");
                        continue;
                    case 12:
                        sb.append("\\f");
                        continue;
                    case 13:
                        sb.append("\\r");
                        continue;
                    default:
                        if (a2 >= 32 && a2 <= 126) {
                            sb.append((char) a2);
                            continue;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((a2 >>> 6) & 3) + 48));
                            sb.append((char) (((a2 >>> 3) & 7) + 48));
                            sb.append((char) ((a2 & 7) + 48));
                            break;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    public static String c(byte[] bArr) {
        return b(new b(bArr));
    }

    public static String d(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static String e(String str) {
        return a(ByteString.copyFromUtf8(str));
    }
}
