package org.apache.commons.codec.net;

import java.nio.ByteBuffer;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
/* loaded from: classes12.dex */
public class PercentCodec implements BinaryEncoder, BinaryDecoder {

    /* renamed from: a  reason: collision with root package name */
    public final BitSet f14379a;
    public final boolean b;
    public int c;
    public int d;

    public PercentCodec() {
        this.f14379a = new BitSet();
        this.c = Integer.MAX_VALUE;
        this.d = Integer.MIN_VALUE;
        this.b = false;
        g((byte) 37);
    }

    public final boolean a(byte b) {
        return !i(b) || (f(b) && this.f14379a.get(b));
    }

    public final boolean b(byte[] bArr) {
        for (byte b : bArr) {
            if (b == 32) {
                return true;
            }
        }
        return false;
    }

    public final byte[] c(byte[] bArr, int i, boolean z) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (byte b : bArr) {
            if (z && a(b)) {
                if (b < 0) {
                    b = (byte) (b + 256);
                }
                char b2 = b.b(b >> 4);
                char b3 = b.b(b);
                allocate.put((byte) 37);
                allocate.put((byte) b2);
                allocate.put((byte) b3);
            } else if (this.b && b == 32) {
                allocate.put((byte) 43);
            } else {
                allocate.put(b);
            }
        }
        return allocate.array();
    }

    public final int d(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            i += bArr[i] == 37 ? 3 : 1;
            i2++;
        }
        return i2;
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) throws DecoderException {
        if (bArr == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(d(bArr));
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == 37) {
                int i2 = i + 1;
                try {
                    int a2 = b.a(bArr[i2]);
                    i = i2 + 1;
                    allocate.put((byte) ((a2 << 4) + b.a(bArr[i])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid percent decoding: ", e);
                }
            } else if (this.b && b == 43) {
                allocate.put((byte) 32);
            } else {
                allocate.put(b);
            }
            i++;
        }
        return allocate.array();
    }

    public final int e(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i += a(b) ? 3 : 1;
        }
        return i;
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) throws EncoderException {
        if (bArr == null) {
            return null;
        }
        int e = e(bArr);
        boolean z = e != bArr.length;
        return (z || (this.b && b(bArr))) ? c(bArr, e, z) : bArr;
    }

    public final boolean f(byte b) {
        return b >= this.c && b <= this.d;
    }

    public final void g(byte b) {
        this.f14379a.set(b);
        if (b < this.c) {
            this.c = b;
        }
        if (b > this.d) {
            this.d = b;
        }
    }

    public final void h(byte[] bArr) {
        if (bArr != null) {
            for (byte b : bArr) {
                g(b);
            }
        }
        g((byte) 37);
    }

    public final boolean i(byte b) {
        return b >= 0;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent encoded");
    }

    public PercentCodec(byte[] bArr, boolean z) {
        this.f14379a = new BitSet();
        this.c = Integer.MAX_VALUE;
        this.d = Integer.MIN_VALUE;
        this.b = z;
        h(bArr);
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent decoded");
    }
}
