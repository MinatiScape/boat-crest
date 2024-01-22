package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class m0 extends ByteString {
    public static final int[] minLengthByDepth = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private static final long serialVersionUID = 1;
    private final ByteString left;
    private final int leftLength;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    /* loaded from: classes10.dex */
    public class a extends ByteString.c {
        public final c h;
        public ByteString.ByteIterator i = b();

        public a() {
            this.h = new c(m0.this, null);
        }

        /* JADX WARN: Type inference failed for: r0v5, types: [com.google.crypto.tink.shaded.protobuf.ByteString$ByteIterator] */
        public final ByteString.ByteIterator b() {
            if (this.h.hasNext()) {
                return this.h.next().iterator2();
            }
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i != null;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            ByteString.ByteIterator byteIterator = this.i;
            if (byteIterator != null) {
                byte nextByte = byteIterator.nextByte();
                if (!this.i.hasNext()) {
                    this.i = b();
                }
                return nextByte;
            }
            throw new NoSuchElementException();
        }
    }

    /* loaded from: classes10.dex */
    public static final class c implements Iterator<ByteString.h> {
        public final ArrayDeque<m0> h;
        public ByteString.h i;

        public /* synthetic */ c(ByteString byteString, a aVar) {
            this(byteString);
        }

        public final ByteString.h a(ByteString byteString) {
            while (byteString instanceof m0) {
                m0 m0Var = (m0) byteString;
                this.h.push(m0Var);
                byteString = m0Var.left;
            }
            return (ByteString.h) byteString;
        }

        public final ByteString.h b() {
            ByteString.h a2;
            do {
                ArrayDeque<m0> arrayDeque = this.h;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    return null;
                }
                a2 = a(this.h.pop().right);
            } while (a2.isEmpty());
            return a2;
        }

        @Override // java.util.Iterator
        /* renamed from: c */
        public ByteString.h next() {
            ByteString.h hVar = this.i;
            if (hVar != null) {
                this.i = b();
                return hVar;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i != null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public c(ByteString byteString) {
            if (byteString instanceof m0) {
                m0 m0Var = (m0) byteString;
                ArrayDeque<m0> arrayDeque = new ArrayDeque<>(m0Var.getTreeDepth());
                this.h = arrayDeque;
                arrayDeque.push(m0Var);
                this.i = a(m0Var.left);
                return;
            }
            this.h = null;
            this.i = (ByteString.h) byteString;
        }
    }

    public /* synthetic */ m0(ByteString byteString, ByteString byteString2, a aVar) {
        this(byteString, byteString2);
    }

    public static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString.size() + byteString2.size();
        if (size < 128) {
            return concatenateBytes(byteString, byteString2);
        }
        if (byteString instanceof m0) {
            m0 m0Var = (m0) byteString;
            if (m0Var.right.size() + byteString2.size() < 128) {
                return new m0(m0Var.left, concatenateBytes(m0Var.right, byteString2));
            } else if (m0Var.left.getTreeDepth() > m0Var.right.getTreeDepth() && m0Var.getTreeDepth() > byteString2.getTreeDepth()) {
                return new m0(m0Var.left, new m0(m0Var.right, byteString2));
            }
        }
        if (size < minLength(Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1)) {
            return new b(null).b(byteString, byteString2);
        }
        return new m0(byteString, byteString2);
    }

    private static ByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[size + size2];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return ByteString.wrap(bArr);
    }

    private boolean equalsFragments(ByteString byteString) {
        boolean equalsRange;
        c cVar = new c(this, null);
        ByteString.h next = cVar.next();
        c cVar2 = new c(byteString, null);
        ByteString.h next2 = cVar2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = next.size() - i;
            int size2 = next2.size() - i2;
            int min = Math.min(size, size2);
            if (i == 0) {
                equalsRange = next.equalsRange(next2, i2, min);
            } else {
                equalsRange = next2.equalsRange(next, i, min);
            }
            if (!equalsRange) {
                return false;
            }
            i3 += min;
            int i4 = this.totalLength;
            if (i3 >= i4) {
                if (i3 == i4) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (min == size) {
                i = 0;
                next = cVar.next();
            } else {
                i += min;
                next = next;
            }
            if (min == size2) {
                next2 = cVar2.next();
                i2 = 0;
            } else {
                i2 += min;
            }
        }
    }

    public static int minLength(int i) {
        int[] iArr = minLengthByDepth;
        if (i >= iArr.length) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    public static m0 newInstanceForTest(ByteString byteString, ByteString byteString2) {
        return new m0(byteString, byteString2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList();
        c cVar = new c(this, null);
        while (cVar.hasNext()) {
            arrayList.add(cVar.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte byteAt(int i) {
        ByteString.checkIndex(i, this.totalLength);
        return internalByteAt(i);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        this.left.copyTo(byteBuffer);
        this.right.copyTo(byteBuffer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            this.left.copyToInternal(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.right.copyToInternal(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.left.copyToInternal(bArr, i, i2, i6);
            this.right.copyToInternal(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (this.totalLength != byteString.size()) {
                return false;
            }
            if (this.totalLength == 0) {
                return true;
            }
            int peekCachedHashCode = peekCachedHashCode();
            int peekCachedHashCode2 = byteString.peekCachedHashCode();
            if (peekCachedHashCode == 0 || peekCachedHashCode2 == 0 || peekCachedHashCode == peekCachedHashCode2) {
                return equalsFragments(byteString);
            }
            return false;
        }
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int getTreeDepth() {
        return this.treeDepth;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte internalByteAt(int i) {
        int i2 = this.leftLength;
        if (i < i2) {
            return this.left.internalByteAt(i);
        }
        return this.right.internalByteAt(i - i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isBalanced() {
        return this.totalLength >= minLength(this.treeDepth);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isValidUtf8() {
        int partialIsValidUtf8 = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        ByteString byteString = this.right;
        return byteString.partialIsValidUtf8(partialIsValidUtf8, 0, byteString.size()) == 0;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(new d());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public InputStream newInput() {
        return new d();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialHash(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialHash(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialHash(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialHash(this.left.partialHash(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialIsValidUtf8(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialIsValidUtf8(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialIsValidUtf8(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteString substring(int i, int i2) {
        int checkRange = ByteString.checkRange(i, i2, this.totalLength);
        if (checkRange == 0) {
            return ByteString.EMPTY;
        }
        if (checkRange == this.totalLength) {
            return this;
        }
        int i3 = this.leftLength;
        if (i2 <= i3) {
            return this.left.substring(i, i2);
        }
        if (i >= i3) {
            return this.right.substring(i - i3, i2 - i3);
        }
        return new m0(this.left.substring(i), this.right.substring(0, i2 - this.leftLength));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public String toStringInternal(Charset charset) {
        return new String(toByteArray(), charset);
    }

    public Object writeReplace() {
        return ByteString.wrap(toByteArray());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(OutputStream outputStream) throws IOException {
        this.left.writeTo(outputStream);
        this.right.writeTo(outputStream);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException {
        int i3 = i + i2;
        int i4 = this.leftLength;
        if (i3 <= i4) {
            this.left.writeToInternal(outputStream, i, i2);
        } else if (i >= i4) {
            this.right.writeToInternal(outputStream, i - i4, i2);
        } else {
            int i5 = i4 - i;
            this.left.writeToInternal(outputStream, i, i5);
            this.right.writeToInternal(outputStream, 0, i2 - i5);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeToReverse(ByteOutput byteOutput) throws IOException {
        this.right.writeToReverse(byteOutput);
        this.left.writeToReverse(byteOutput);
    }

    /* loaded from: classes10.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final ArrayDeque<ByteString> f10987a;

        public b() {
            this.f10987a = new ArrayDeque<>();
        }

        public final ByteString b(ByteString byteString, ByteString byteString2) {
            c(byteString);
            c(byteString2);
            ByteString pop = this.f10987a.pop();
            while (!this.f10987a.isEmpty()) {
                pop = new m0(this.f10987a.pop(), pop, null);
            }
            return pop;
        }

        public final void c(ByteString byteString) {
            if (byteString.isBalanced()) {
                e(byteString);
            } else if (byteString instanceof m0) {
                m0 m0Var = (m0) byteString;
                c(m0Var.left);
                c(m0Var.right);
            } else {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + byteString.getClass());
            }
        }

        public final int d(int i) {
            int binarySearch = Arrays.binarySearch(m0.minLengthByDepth, i);
            return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
        }

        public final void e(ByteString byteString) {
            int d = d(byteString.size());
            int minLength = m0.minLength(d + 1);
            if (!this.f10987a.isEmpty() && this.f10987a.peek().size() < minLength) {
                int minLength2 = m0.minLength(d);
                ByteString pop = this.f10987a.pop();
                while (!this.f10987a.isEmpty() && this.f10987a.peek().size() < minLength2) {
                    pop = new m0(this.f10987a.pop(), pop, null);
                }
                m0 m0Var = new m0(pop, byteString, null);
                while (!this.f10987a.isEmpty()) {
                    if (this.f10987a.peek().size() >= m0.minLength(d(m0Var.size()) + 1)) {
                        break;
                    }
                    m0Var = new m0(this.f10987a.pop(), m0Var, null);
                }
                this.f10987a.push(m0Var);
                return;
            }
            this.f10987a.push(byteString);
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    private m0(ByteString byteString, ByteString byteString2) {
        this.left = byteString;
        this.right = byteString2;
        int size = byteString.size();
        this.leftLength = size;
        this.totalLength = size + byteString2.size();
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString, java.lang.Iterable
    /* renamed from: iterator */
    public Iterator<Byte> iterator2() {
        return new a();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(ByteOutput byteOutput) throws IOException {
        this.left.writeTo(byteOutput);
        this.right.writeTo(byteOutput);
    }

    /* loaded from: classes10.dex */
    public class d extends InputStream {
        public c h;
        public ByteString.h i;
        public int j;
        public int k;
        public int l;
        public int m;

        public d() {
            b();
        }

        public final void a() {
            if (this.i != null) {
                int i = this.k;
                int i2 = this.j;
                if (i == i2) {
                    this.l += i2;
                    this.k = 0;
                    if (this.h.hasNext()) {
                        ByteString.h next = this.h.next();
                        this.i = next;
                        this.j = next.size();
                        return;
                    }
                    this.i = null;
                    this.j = 0;
                }
            }
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return m0.this.size() - (this.l + this.k);
        }

        public final void b() {
            c cVar = new c(m0.this, null);
            this.h = cVar;
            ByteString.h next = cVar.next();
            this.i = next;
            this.j = next.size();
            this.k = 0;
            this.l = 0;
        }

        public final int c(byte[] bArr, int i, int i2) {
            int i3 = i2;
            while (i3 > 0) {
                a();
                if (this.i == null) {
                    break;
                }
                int min = Math.min(this.j - this.k, i3);
                if (bArr != null) {
                    this.i.copyTo(bArr, this.k, i, min);
                    i += min;
                }
                this.k += min;
                i3 -= min;
            }
            return i2 - i3;
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.m = this.l + this.k;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            Objects.requireNonNull(bArr);
            if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
                int c = c(bArr, i, i2);
                if (c == 0) {
                    return -1;
                }
                return c;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            b();
            c(null, 0, this.m);
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            if (j >= 0) {
                if (j > 2147483647L) {
                    j = 2147483647L;
                }
                return c(null, 0, (int) j);
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            a();
            ByteString.h hVar = this.i;
            if (hVar == null) {
                return -1;
            }
            int i = this.k;
            this.k = i + 1;
            return hVar.byteAt(i) & 255;
        }
    }
}
