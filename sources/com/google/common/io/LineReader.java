package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class LineReader {

    /* renamed from: a  reason: collision with root package name */
    public final Readable f10684a;
    @NullableDecl
    public final Reader b;
    public final CharBuffer c;
    public final char[] d;
    public final Queue<String> e;
    public final d f;

    /* loaded from: classes10.dex */
    public class a extends d {
        public a() {
        }

        @Override // com.google.common.io.d
        public void d(String str, String str2) {
            LineReader.this.e.add(str);
        }
    }

    public LineReader(Readable readable) {
        CharBuffer c = CharStreams.c();
        this.c = c;
        this.d = c.array();
        this.e = new ArrayDeque();
        this.f = new a();
        this.f10684a = (Readable) Preconditions.checkNotNull(readable);
        this.b = readable instanceof Reader ? (Reader) readable : null;
    }

    @CanIgnoreReturnValue
    public String readLine() throws IOException {
        int read;
        while (true) {
            if (this.e.peek() != null) {
                break;
            }
            c.a(this.c);
            Reader reader = this.b;
            if (reader != null) {
                char[] cArr = this.d;
                read = reader.read(cArr, 0, cArr.length);
            } else {
                read = this.f10684a.read(this.c);
            }
            if (read == -1) {
                this.f.b();
                break;
            }
            this.f.a(this.d, 0, read);
        }
        return this.e.poll();
    }
}
