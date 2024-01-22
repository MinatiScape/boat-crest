package com.google.common.io;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.RsaJsonWebKey;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class Files {

    /* renamed from: a  reason: collision with root package name */
    public static final SuccessorsFunction<File> f10679a = new b();

    /* loaded from: classes10.dex */
    public class a implements LineProcessor<List<String>> {

        /* renamed from: a  reason: collision with root package name */
        public final List<String> f10680a = Lists.newArrayList();

        @Override // com.google.common.io.LineProcessor
        /* renamed from: a */
        public List<String> getResult() {
            return this.f10680a;
        }

        @Override // com.google.common.io.LineProcessor
        public boolean processLine(String str) {
            this.f10680a.add(str);
            return true;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements SuccessorsFunction<File> {
        @Override // com.google.common.graph.SuccessorsFunction
        /* renamed from: a */
        public Iterable<File> successors(File file) {
            File[] listFiles;
            if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                return Collections.unmodifiableList(Arrays.asList(listFiles));
            }
            return ImmutableList.of();
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends ByteSink {

        /* renamed from: a  reason: collision with root package name */
        public final File f10681a;
        public final ImmutableSet<FileWriteMode> b;

        public /* synthetic */ c(File file, FileWriteMode[] fileWriteModeArr, a aVar) {
            this(file, fileWriteModeArr);
        }

        @Override // com.google.common.io.ByteSink
        /* renamed from: a */
        public FileOutputStream openStream() throws IOException {
            return new FileOutputStream(this.f10681a, this.b.contains(FileWriteMode.APPEND));
        }

        public String toString() {
            String valueOf = String.valueOf(this.f10681a);
            String valueOf2 = String.valueOf(this.b);
            StringBuilder sb = new StringBuilder(valueOf.length() + 20 + valueOf2.length());
            sb.append("Files.asByteSink(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            sb.append(")");
            return sb.toString();
        }

        public c(File file, FileWriteMode... fileWriteModeArr) {
            this.f10681a = (File) Preconditions.checkNotNull(file);
            this.b = ImmutableSet.copyOf(fileWriteModeArr);
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends ByteSource {

        /* renamed from: a  reason: collision with root package name */
        public final File f10682a;

        public /* synthetic */ d(File file, a aVar) {
            this(file);
        }

        @Override // com.google.common.io.ByteSource
        /* renamed from: b */
        public FileInputStream openStream() throws IOException {
            return new FileInputStream(this.f10682a);
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws IOException {
            try {
                FileInputStream fileInputStream = (FileInputStream) Closer.create().register(openStream());
                return ByteStreams.e(fileInputStream, fileInputStream.getChannel().size());
            } finally {
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            if (this.f10682a.isFile()) {
                return this.f10682a.length();
            }
            throw new FileNotFoundException(this.f10682a.toString());
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            if (this.f10682a.isFile()) {
                return Optional.of(Long.valueOf(this.f10682a.length()));
            }
            return Optional.absent();
        }

        public String toString() {
            String valueOf = String.valueOf(this.f10682a);
            StringBuilder sb = new StringBuilder(valueOf.length() + 20);
            sb.append("Files.asByteSource(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }

        public d(File file) {
            this.f10682a = (File) Preconditions.checkNotNull(file);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class e implements Predicate<File> {
        public static final e IS_DIRECTORY = new a("IS_DIRECTORY", 0);
        public static final e IS_FILE = new b("IS_FILE", 1);
        private static final /* synthetic */ e[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends e {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Files.isDirectory()";
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isDirectory();
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends e {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Files.isFile()";
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isFile();
            }
        }

        private static /* synthetic */ e[] $values() {
            return new e[]{IS_DIRECTORY, IS_FILE};
        }

        private e(String str, int i) {
        }

        public static e valueOf(String str) {
            return (e) Enum.valueOf(e.class, str);
        }

        public static e[] values() {
            return (e[]) $VALUES.clone();
        }

        public /* synthetic */ e(String str, int i, a aVar) {
            this(str, i);
        }
    }

    public static MappedByteBuffer a(File file, FileChannel.MapMode mapMode, long j) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mapMode);
        Closer create = Closer.create();
        try {
            FileChannel fileChannel = (FileChannel) create.register(((RandomAccessFile) create.register(new RandomAccessFile(file, mapMode == FileChannel.MapMode.READ_ONLY ? RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME : "rw"))).getChannel());
            if (j == -1) {
                j = fileChannel.size();
            }
            return fileChannel.map(mapMode, 0L, j);
        } finally {
        }
    }

    @Beta
    @Deprecated
    public static void append(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, FileWriteMode.APPEND).write(charSequence);
    }

    public static ByteSink asByteSink(File file, FileWriteMode... fileWriteModeArr) {
        return new c(file, fileWriteModeArr, null);
    }

    public static ByteSource asByteSource(File file) {
        return new d(file, null);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode... fileWriteModeArr) {
        return asByteSink(file, fileWriteModeArr).asCharSink(charset);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    @Beta
    public static void copy(File file, OutputStream outputStream) throws IOException {
        asByteSource(file).copyTo(outputStream);
    }

    @Beta
    public static void createParentDirs(File file) throws IOException {
        Preconditions.checkNotNull(file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile == null) {
            return;
        }
        parentFile.mkdirs();
        if (parentFile.isDirectory()) {
            return;
        }
        String valueOf = String.valueOf(file);
        StringBuilder sb = new StringBuilder(valueOf.length() + 39);
        sb.append("Unable to create parent directories of ");
        sb.append(valueOf);
        throw new IOException(sb.toString());
    }

    @Beta
    @Deprecated
    public static File createTempDir() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(21);
        sb.append(currentTimeMillis);
        sb.append("-");
        String sb2 = sb.toString();
        for (int i = 0; i < 10000; i++) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 11);
            sb3.append(sb2);
            sb3.append(i);
            File file2 = new File(file, sb3.toString());
            if (file2.mkdir()) {
                return file2;
            }
        }
        StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 66 + String.valueOf(sb2).length());
        sb4.append("Failed to create directory within 10000 attempts (tried ");
        sb4.append(sb2);
        sb4.append("0 to ");
        sb4.append(sb2);
        sb4.append(9999);
        sb4.append(HexStringBuilder.COMMENT_END_CHAR);
        throw new IllegalStateException(sb4.toString());
    }

    @Beta
    public static boolean equal(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        if (file == file2 || file.equals(file2)) {
            return true;
        }
        long length = file.length();
        long length2 = file2.length();
        if (length == 0 || length2 == 0 || length == length2) {
            return asByteSource(file).contentEquals(asByteSource(file2));
        }
        return false;
    }

    @Beta
    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(f10679a);
    }

    @Beta
    public static String getFileExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : name.substring(lastIndexOf + 1);
    }

    @Beta
    public static String getNameWithoutExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? name : name.substring(0, lastIndexOf);
    }

    @Beta
    @Deprecated
    public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
        return asByteSource(file).hash(hashFunction);
    }

    @Beta
    public static Predicate<File> isDirectory() {
        return e.IS_DIRECTORY;
    }

    @Beta
    public static Predicate<File> isFile() {
        return e.IS_FILE;
    }

    @Beta
    public static MappedByteBuffer map(File file) throws IOException {
        Preconditions.checkNotNull(file);
        return map(file, FileChannel.MapMode.READ_ONLY);
    }

    @Beta
    public static void move(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        if (file.renameTo(file2)) {
            return;
        }
        copy(file, file2);
        if (file.delete()) {
            return;
        }
        if (!file2.delete()) {
            String valueOf = String.valueOf(file2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 17);
            sb.append("Unable to delete ");
            sb.append(valueOf);
            throw new IOException(sb.toString());
        }
        String valueOf2 = String.valueOf(file);
        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 17);
        sb2.append("Unable to delete ");
        sb2.append(valueOf2);
        throw new IOException(sb2.toString());
    }

    @Beta
    public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    @Beta
    public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    @Beta
    @Deprecated
    @CanIgnoreReturnValue
    public static <T> T readBytes(File file, ByteProcessor<T> byteProcessor) throws IOException {
        return (T) asByteSource(file).read(byteProcessor);
    }

    @Beta
    @Deprecated
    public static String readFirstLine(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).readFirstLine();
    }

    @Beta
    public static List<String> readLines(File file, Charset charset) throws IOException {
        return (List) asCharSource(file, charset).readLines(new a());
    }

    @Beta
    public static String simplifyPath(String str) {
        Preconditions.checkNotNull(str);
        if (str.length() == 0) {
            return ".";
        }
        Iterable<String> split = Splitter.on('/').omitEmptyStrings().split(str);
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            str2.hashCode();
            if (!str2.equals(".")) {
                if (!str2.equals("..")) {
                    arrayList.add(str2);
                } else if (arrayList.size() > 0 && !((String) arrayList.get(arrayList.size() - 1)).equals("..")) {
                    arrayList.remove(arrayList.size() - 1);
                } else {
                    arrayList.add("..");
                }
            }
        }
        String join = Joiner.on('/').join(arrayList);
        if (str.charAt(0) == '/') {
            String valueOf = String.valueOf(join);
            join = valueOf.length() != 0 ? MqttTopic.TOPIC_LEVEL_SEPARATOR.concat(valueOf) : new String(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        }
        while (join.startsWith("/../")) {
            join = join.substring(3);
        }
        return join.equals("/..") ? MqttTopic.TOPIC_LEVEL_SEPARATOR : "".equals(join) ? "." : join;
    }

    @Beta
    public static byte[] toByteArray(File file) throws IOException {
        return asByteSource(file).read();
    }

    @Beta
    @Deprecated
    public static String toString(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).read();
    }

    @Beta
    public static void touch(File file) throws IOException {
        Preconditions.checkNotNull(file);
        if (file.createNewFile() || file.setLastModified(System.currentTimeMillis())) {
            return;
        }
        String valueOf = String.valueOf(file);
        StringBuilder sb = new StringBuilder(valueOf.length() + 38);
        sb.append("Unable to update modification time of ");
        sb.append(valueOf);
        throw new IOException(sb.toString());
    }

    @Beta
    public static void write(byte[] bArr, File file) throws IOException {
        asByteSink(file, new FileWriteMode[0]).write(bArr);
    }

    @Beta
    public static void copy(File file, File file2) throws IOException {
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        asByteSource(file).copyTo(asByteSink(file2, new FileWriteMode[0]));
    }

    @Beta
    @Deprecated
    public static void write(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, new FileWriteMode[0]).write(charSequence);
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode) throws IOException {
        return a(file, mapMode, -1L);
    }

    @Beta
    @Deprecated
    @CanIgnoreReturnValue
    public static <T> T readLines(File file, Charset charset, LineProcessor<T> lineProcessor) throws IOException {
        return (T) asCharSource(file, charset).readLines(lineProcessor);
    }

    @Beta
    @Deprecated
    public static void copy(File file, Charset charset, Appendable appendable) throws IOException {
        asCharSource(file, charset).copyTo(appendable);
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode, long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "size (%s) may not be negative", j);
        return a(file, mapMode, j);
    }
}
