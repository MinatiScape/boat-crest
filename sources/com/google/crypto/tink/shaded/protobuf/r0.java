package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class r0 {

    /* loaded from: classes10.dex */
    public class a implements b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ByteString f10992a;

        public a(ByteString byteString) {
            this.f10992a = byteString;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.r0.b
        public byte a(int i) {
            return this.f10992a.byteAt(i);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.r0.b
        public int size() {
            return this.f10992a.size();
        }
    }

    /* loaded from: classes10.dex */
    public interface b {
        byte a(int i);

        int size();
    }

    public static String a(ByteString byteString) {
        return b(new a(byteString));
    }

    public static String b(b bVar) {
        StringBuilder sb = new StringBuilder(bVar.size());
        for (int i = 0; i < bVar.size(); i++) {
            byte a2 = bVar.a(i);
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

    public static String c(String str) {
        return a(ByteString.copyFromUtf8(str));
    }
}
