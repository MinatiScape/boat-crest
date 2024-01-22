package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public final class Funnels {

    /* loaded from: classes10.dex */
    public enum a implements Funnel<byte[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.byteArrayFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(byte[] bArr, PrimitiveSink primitiveSink) {
            primitiveSink.putBytes(bArr);
        }
    }

    /* loaded from: classes10.dex */
    public enum b implements Funnel<Integer> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.integerFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Integer num, PrimitiveSink primitiveSink) {
            primitiveSink.putInt(num.intValue());
        }
    }

    /* loaded from: classes10.dex */
    public enum c implements Funnel<Long> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.longFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Long l, PrimitiveSink primitiveSink) {
            primitiveSink.putLong(l.longValue());
        }
    }

    /* loaded from: classes10.dex */
    public static class d<E> implements Funnel<Iterable<? extends E>>, Serializable {
        private final Funnel<E> elementFunnel;

        public d(Funnel<E> funnel) {
            this.elementFunnel = (Funnel) Preconditions.checkNotNull(funnel);
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof d) {
                return this.elementFunnel.equals(((d) obj).elementFunnel);
            }
            return false;
        }

        @Override // com.google.common.hash.Funnel
        public /* bridge */ /* synthetic */ void funnel(Object obj, PrimitiveSink primitiveSink) {
            funnel((Iterable) ((Iterable) obj), primitiveSink);
        }

        public int hashCode() {
            return d.class.hashCode() ^ this.elementFunnel.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.elementFunnel);
            StringBuilder sb = new StringBuilder(valueOf.length() + 26);
            sb.append("Funnels.sequentialFunnel(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }

        public void funnel(Iterable<? extends E> iterable, PrimitiveSink primitiveSink) {
            for (E e : iterable) {
                this.elementFunnel.funnel(e, primitiveSink);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class e extends OutputStream {
        public final PrimitiveSink h;

        public e(PrimitiveSink primitiveSink) {
            this.h = (PrimitiveSink) Preconditions.checkNotNull(primitiveSink);
        }

        public String toString() {
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 24);
            sb.append("Funnels.asOutputStream(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            this.h.putByte((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            this.h.putBytes(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            this.h.putBytes(bArr, i, i2);
        }
    }

    /* loaded from: classes10.dex */
    public static class f implements Funnel<CharSequence>, Serializable {
        private final Charset charset;

        /* loaded from: classes10.dex */
        public static class a implements Serializable {
            private static final long serialVersionUID = 0;
            private final String charsetCanonicalName;

            public a(Charset charset) {
                this.charsetCanonicalName = charset.name();
            }

            private Object readResolve() {
                return Funnels.stringFunnel(Charset.forName(this.charsetCanonicalName));
            }
        }

        public f(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof f) {
                return this.charset.equals(((f) obj).charset);
            }
            return false;
        }

        public int hashCode() {
            return f.class.hashCode() ^ this.charset.hashCode();
        }

        public String toString() {
            String name = this.charset.name();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 22);
            sb.append("Funnels.stringFunnel(");
            sb.append(name);
            sb.append(")");
            return sb.toString();
        }

        public Object writeReplace() {
            return new a(this.charset);
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(CharSequence charSequence, PrimitiveSink primitiveSink) {
            primitiveSink.putString(charSequence, this.charset);
        }
    }

    /* loaded from: classes10.dex */
    public enum g implements Funnel<CharSequence> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.unencodedCharsFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(CharSequence charSequence, PrimitiveSink primitiveSink) {
            primitiveSink.putUnencodedChars(charSequence);
        }
    }

    public static OutputStream asOutputStream(PrimitiveSink primitiveSink) {
        return new e(primitiveSink);
    }

    public static Funnel<byte[]> byteArrayFunnel() {
        return a.INSTANCE;
    }

    public static Funnel<Integer> integerFunnel() {
        return b.INSTANCE;
    }

    public static Funnel<Long> longFunnel() {
        return c.INSTANCE;
    }

    public static <E> Funnel<Iterable<? extends E>> sequentialFunnel(Funnel<E> funnel) {
        return new d(funnel);
    }

    public static Funnel<CharSequence> stringFunnel(Charset charset) {
        return new f(charset);
    }

    public static Funnel<CharSequence> unencodedCharsFunnel() {
        return g.INSTANCE;
    }
}
