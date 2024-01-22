package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class CharSource {

    /* loaded from: classes10.dex */
    public final class a extends ByteSource {

        /* renamed from: a  reason: collision with root package name */
        public final Charset f10671a;

        public a(Charset charset) {
            this.f10671a = (Charset) Preconditions.checkNotNull(charset);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            if (charset.equals(this.f10671a)) {
                return CharSource.this;
            }
            return super.asCharSource(charset);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return new g(CharSource.this.openStream(), this.f10671a, 8192);
        }

        public String toString() {
            String obj = CharSource.this.toString();
            String valueOf = String.valueOf(this.f10671a);
            StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 15 + valueOf.length());
            sb.append(obj);
            sb.append(".asByteSource(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static class b extends CharSource {
        public static final Splitter b = Splitter.onPattern("\r\n|\n|\r");

        /* renamed from: a  reason: collision with root package name */
        public final CharSequence f10672a;

        /* loaded from: classes10.dex */
        public class a extends AbstractIterator<String> {
            public Iterator<String> j;

            public a() {
                this.j = b.b.split(b.this.f10672a).iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            public String computeNext() {
                if (this.j.hasNext()) {
                    String next = this.j.next();
                    if (this.j.hasNext() || !next.isEmpty()) {
                        return next;
                    }
                }
                return endOfData();
            }
        }

        public b(CharSequence charSequence) {
            this.f10672a = (CharSequence) Preconditions.checkNotNull(charSequence);
        }

        public final Iterator<String> c() {
            return new a();
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() {
            return this.f10672a.length() == 0;
        }

        @Override // com.google.common.io.CharSource
        public long length() {
            return this.f10672a.length();
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            return Optional.of(Long.valueOf(this.f10672a.length()));
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new com.google.common.io.b(this.f10672a);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return this.f10672a.toString();
        }

        @Override // com.google.common.io.CharSource
        public String readFirstLine() {
            Iterator<String> c = c();
            if (c.hasNext()) {
                return c.next();
            }
            return null;
        }

        @Override // com.google.common.io.CharSource
        public ImmutableList<String> readLines() {
            return ImmutableList.copyOf(c());
        }

        public String toString() {
            String truncate = Ascii.truncate(this.f10672a, 30, "...");
            StringBuilder sb = new StringBuilder(String.valueOf(truncate).length() + 17);
            sb.append("CharSource.wrap(");
            sb.append(truncate);
            sb.append(")");
            return sb.toString();
        }

        @Override // com.google.common.io.CharSource
        public <T> T readLines(LineProcessor<T> lineProcessor) throws IOException {
            Iterator<String> c = c();
            while (c.hasNext() && lineProcessor.processLine(c.next())) {
            }
            return lineProcessor.getResult();
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends CharSource {

        /* renamed from: a  reason: collision with root package name */
        public final Iterable<? extends CharSource> f10673a;

        public c(Iterable<? extends CharSource> iterable) {
            this.f10673a = (Iterable) Preconditions.checkNotNull(iterable);
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() throws IOException {
            for (CharSource charSource : this.f10673a) {
                if (!charSource.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.CharSource
        public long length() throws IOException {
            long j = 0;
            for (CharSource charSource : this.f10673a) {
                j += charSource.length();
            }
            return j;
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            long j = 0;
            for (CharSource charSource : this.f10673a) {
                Optional<Long> lengthIfKnown = charSource.lengthIfKnown();
                if (!lengthIfKnown.isPresent()) {
                    return Optional.absent();
                }
                j += lengthIfKnown.get().longValue();
            }
            return Optional.of(Long.valueOf(j));
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new f(this.f10673a.iterator());
        }

        public String toString() {
            String valueOf = String.valueOf(this.f10673a);
            StringBuilder sb = new StringBuilder(valueOf.length() + 19);
            sb.append("CharSource.concat(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends e {
        public static final d c = new d();

        public d() {
            super("");
        }

        @Override // com.google.common.io.CharSource.b
        public String toString() {
            return "CharSource.empty()";
        }
    }

    public static CharSource concat(Iterable<? extends CharSource> iterable) {
        return new c(iterable);
    }

    public static CharSource empty() {
        return d.c;
    }

    public static CharSource wrap(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return new e((String) charSequence);
        }
        return new b(charSequence);
    }

    public final long a(Reader reader) throws IOException {
        long j = 0;
        while (true) {
            long skip = reader.skip(Long.MAX_VALUE);
            if (skip == 0) {
                return j;
            }
            j += skip;
        }
    }

    @Beta
    public ByteSource asByteSource(Charset charset) {
        return new a(charset);
    }

    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) throws IOException {
        Preconditions.checkNotNull(appendable);
        try {
            return CharStreams.copy((Reader) Closer.create().register(openStream()), appendable);
        } finally {
        }
    }

    public boolean isEmpty() throws IOException {
        Optional<Long> lengthIfKnown = lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue() == 0;
        }
        Closer create = Closer.create();
        try {
            return ((Reader) create.register(openStream())).read() == -1;
        } catch (Throwable th) {
            try {
                throw create.rethrow(th);
            } finally {
                create.close();
            }
        }
    }

    @Beta
    public long length() throws IOException {
        Optional<Long> lengthIfKnown = lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue();
        }
        try {
            return a((Reader) Closer.create().register(openStream()));
        } finally {
        }
    }

    @Beta
    public Optional<Long> lengthIfKnown() {
        return Optional.absent();
    }

    public BufferedReader openBufferedStream() throws IOException {
        Reader openStream = openStream();
        if (openStream instanceof BufferedReader) {
            return (BufferedReader) openStream;
        }
        return new BufferedReader(openStream);
    }

    public abstract Reader openStream() throws IOException;

    public String read() throws IOException {
        try {
            return CharStreams.toString((Reader) Closer.create().register(openStream()));
        } finally {
        }
    }

    @NullableDecl
    public String readFirstLine() throws IOException {
        try {
            return ((BufferedReader) Closer.create().register(openBufferedStream())).readLine();
        } finally {
        }
    }

    public ImmutableList<String> readLines() throws IOException {
        try {
            BufferedReader bufferedReader = (BufferedReader) Closer.create().register(openBufferedStream());
            ArrayList newArrayList = Lists.newArrayList();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    newArrayList.add(readLine);
                } else {
                    return ImmutableList.copyOf((Collection) newArrayList);
                }
            }
        } finally {
        }
    }

    /* loaded from: classes10.dex */
    public static class e extends b {
        public e(String str) {
            super(str);
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(Appendable appendable) throws IOException {
            appendable.append(this.f10672a);
            return this.f10672a.length();
        }

        @Override // com.google.common.io.CharSource.b, com.google.common.io.CharSource
        public Reader openStream() {
            return new StringReader((String) this.f10672a);
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(CharSink charSink) throws IOException {
            Closer create;
            Preconditions.checkNotNull(charSink);
            try {
                ((Writer) Closer.create().register(charSink.openStream())).write((String) this.f10672a);
                return this.f10672a.length();
            } finally {
            }
        }
    }

    public static CharSource concat(Iterator<? extends CharSource> it) {
        return concat(ImmutableList.copyOf(it));
    }

    public static CharSource concat(CharSource... charSourceArr) {
        return concat(ImmutableList.copyOf(charSourceArr));
    }

    @CanIgnoreReturnValue
    public long copyTo(CharSink charSink) throws IOException {
        Preconditions.checkNotNull(charSink);
        Closer create = Closer.create();
        try {
            return CharStreams.copy((Reader) create.register(openStream()), (Writer) create.register(charSink.openStream()));
        } finally {
        }
    }

    @CanIgnoreReturnValue
    @Beta
    public <T> T readLines(LineProcessor<T> lineProcessor) throws IOException {
        Preconditions.checkNotNull(lineProcessor);
        try {
            return (T) CharStreams.readLines((Reader) Closer.create().register(openStream()), lineProcessor);
        } finally {
        }
    }
}
