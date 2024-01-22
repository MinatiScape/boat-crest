package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
@Immutable
/* loaded from: classes10.dex */
public abstract class b extends c {
    public final HashFunction[] h;

    /* loaded from: classes10.dex */
    public class a implements Hasher {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Hasher[] f10652a;

        public a(Hasher[] hasherArr) {
            this.f10652a = hasherArr;
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            return b.this.b(this.f10652a);
        }

        @Override // com.google.common.hash.Hasher
        public <T> Hasher putObject(T t, Funnel<? super T> funnel) {
            for (Hasher hasher : this.f10652a) {
                hasher.putObject(t, funnel);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBoolean(boolean z) {
            for (Hasher hasher : this.f10652a) {
                hasher.putBoolean(z);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b) {
            for (Hasher hasher : this.f10652a) {
                hasher.putByte(b);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putChar(char c) {
            for (Hasher hasher : this.f10652a) {
                hasher.putChar(c);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putDouble(double d) {
            for (Hasher hasher : this.f10652a) {
                hasher.putDouble(d);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putFloat(float f) {
            for (Hasher hasher : this.f10652a) {
                hasher.putFloat(f);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putInt(int i) {
            for (Hasher hasher : this.f10652a) {
                hasher.putInt(i);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putLong(long j) {
            for (Hasher hasher : this.f10652a) {
                hasher.putLong(j);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putShort(short s) {
            for (Hasher hasher : this.f10652a) {
                hasher.putShort(s);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putString(CharSequence charSequence, Charset charset) {
            for (Hasher hasher : this.f10652a) {
                hasher.putString(charSequence, charset);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putUnencodedChars(CharSequence charSequence) {
            for (Hasher hasher : this.f10652a) {
                hasher.putUnencodedChars(charSequence);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr) {
            for (Hasher hasher : this.f10652a) {
                hasher.putBytes(bArr);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int i, int i2) {
            for (Hasher hasher : this.f10652a) {
                hasher.putBytes(bArr, i, i2);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            Hasher[] hasherArr;
            int position = byteBuffer.position();
            for (Hasher hasher : this.f10652a) {
                l.c(byteBuffer, position);
                hasher.putBytes(byteBuffer);
            }
            return this;
        }
    }

    public b(HashFunction... hashFunctionArr) {
        for (HashFunction hashFunction : hashFunctionArr) {
            Preconditions.checkNotNull(hashFunction);
        }
        this.h = hashFunctionArr;
    }

    public final Hasher a(Hasher[] hasherArr) {
        return new a(hasherArr);
    }

    public abstract HashCode b(Hasher[] hasherArr);

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        int length = this.h.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i = 0; i < length; i++) {
            hasherArr[i] = this.h[i].newHasher();
        }
        return a(hasherArr);
    }

    @Override // com.google.common.hash.c, com.google.common.hash.HashFunction
    public Hasher newHasher(int i) {
        Preconditions.checkArgument(i >= 0);
        int length = this.h.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i2 = 0; i2 < length; i2++) {
            hasherArr[i2] = this.h[i2].newHasher(i);
        }
        return a(hasherArr);
    }
}
