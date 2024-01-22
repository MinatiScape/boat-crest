package org.checkerframework.checker.signedness;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import kotlin.UShort;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes13.dex */
public final class SignednessUtil {
    public SignednessUtil() {
        throw new Error("Do not instantiate");
    }

    public static BigInteger a(long j) {
        if (j >= 0) {
            return BigInteger.valueOf(j);
        }
        return BigInteger.valueOf(toUnsignedLong((int) (j >>> 32))).shiftLeft(32).add(BigInteger.valueOf(toUnsignedLong((int) j)));
    }

    public static byte byteFromDouble(double d) {
        return (byte) d;
    }

    public static byte byteFromFloat(float f) {
        return (byte) f;
    }

    public static int compareUnsigned(long j, long j2) {
        return Long.compare(j - Long.MIN_VALUE, j2 - Long.MIN_VALUE);
    }

    public static byte getUnsigned(ByteBuffer byteBuffer) {
        return byteBuffer.get();
    }

    public static int getUnsignedInt(ByteBuffer byteBuffer) {
        return byteBuffer.getInt();
    }

    public static short getUnsignedShort(ByteBuffer byteBuffer) {
        return byteBuffer.getShort();
    }

    public static int intFromDouble(double d) {
        return (int) d;
    }

    public static int intFromFloat(float f) {
        return (int) f;
    }

    public static long longFromDouble(double d) {
        return (long) d;
    }

    public static long longFromFloat(float f) {
        return f;
    }

    public static ByteBuffer putUnsigned(ByteBuffer byteBuffer, byte b) {
        return byteBuffer.put(b);
    }

    public static ByteBuffer putUnsignedInt(ByteBuffer byteBuffer, int i) {
        return byteBuffer.putInt(i);
    }

    public static ByteBuffer putUnsignedLong(ByteBuffer byteBuffer, int i, long j) {
        return byteBuffer.putLong(i, j);
    }

    public static ByteBuffer putUnsignedShort(ByteBuffer byteBuffer, short s) {
        return byteBuffer.putShort(s);
    }

    public static void readFullyUnsigned(RandomAccessFile randomAccessFile, byte[] bArr) throws IOException {
        randomAccessFile.readFully(bArr);
    }

    public static int readUnsigned(RandomAccessFile randomAccessFile, byte[] bArr, int i, int i2) throws IOException {
        return randomAccessFile.read(bArr, i, i2);
    }

    public static byte readUnsignedByte(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readByte();
    }

    public static char readUnsignedChar(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readChar();
    }

    public static int readUnsignedInt(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readInt();
    }

    public static long readUnsignedLong(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readLong();
    }

    public static short readUnsignedShort(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readShort();
    }

    public static short shortFromDouble(double d) {
        return (short) d;
    }

    public static short shortFromFloat(float f) {
        return (short) f;
    }

    public static double toDouble(byte b) {
        return a(toUnsignedLong(b)).doubleValue();
    }

    public static float toFloat(byte b) {
        return a(toUnsignedLong(b)).floatValue();
    }

    public static int toUnsignedInt(byte b) {
        return b & 255;
    }

    public static int toUnsignedInt(char c) {
        return c & 255;
    }

    public static int toUnsignedInt(short s) {
        return s & UShort.MAX_VALUE;
    }

    public static long toUnsignedLong(byte b) {
        return b & 255;
    }

    public static long toUnsignedLong(char c) {
        return c & 255;
    }

    public static long toUnsignedLong(int i) {
        return i & 4294967295L;
    }

    public static long toUnsignedLong(short s) {
        return s & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    public static short toUnsignedShort(byte b) {
        return (short) (b & 255);
    }

    public static short toUnsignedShort(char c) {
        return (short) (c & 255);
    }

    public static String toUnsignedString(long j) {
        return a(j).toString();
    }

    public static ByteBuffer wrapUnsigned(byte[] bArr) {
        return ByteBuffer.wrap(bArr);
    }

    public static void writeUnsigned(RandomAccessFile randomAccessFile, byte[] bArr, int i, int i2) throws IOException {
        randomAccessFile.write(bArr, i, i2);
    }

    public static void writeUnsignedByte(RandomAccessFile randomAccessFile, byte b) throws IOException {
        randomAccessFile.writeByte(toUnsignedInt(b));
    }

    public static void writeUnsignedChar(RandomAccessFile randomAccessFile, char c) throws IOException {
        randomAccessFile.writeChar(toUnsignedInt(c));
    }

    public static void writeUnsignedInt(RandomAccessFile randomAccessFile, int i) throws IOException {
        randomAccessFile.writeInt(i);
    }

    public static void writeUnsignedLong(RandomAccessFile randomAccessFile, long j) throws IOException {
        randomAccessFile.writeLong(j);
    }

    public static void writeUnsignedShort(RandomAccessFile randomAccessFile, short s) throws IOException {
        randomAccessFile.writeShort(toUnsignedInt(s));
    }

    public static int compareUnsigned(int i, int i2) {
        return Integer.compare(i - 2147483648, i2 - 2147483648);
    }

    public static byte getUnsigned(ByteBuffer byteBuffer, int i) {
        return byteBuffer.get(i);
    }

    public static ByteBuffer putUnsigned(ByteBuffer byteBuffer, int i, byte b) {
        return byteBuffer.put(i, b);
    }

    public static ByteBuffer putUnsignedInt(ByteBuffer byteBuffer, int i, int i2) {
        return byteBuffer.putInt(i, i2);
    }

    public static ByteBuffer putUnsignedShort(ByteBuffer byteBuffer, int i, short s) {
        return byteBuffer.putShort(i, s);
    }

    public static double toDouble(short s) {
        return a(toUnsignedLong(s)).doubleValue();
    }

    public static float toFloat(short s) {
        return a(toUnsignedLong(s)).floatValue();
    }

    public static String toUnsignedString(long j, int i) {
        return a(j).toString(i);
    }

    public static ByteBuffer wrapUnsigned(byte[] bArr, int i, int i2) {
        return ByteBuffer.wrap(bArr, i, i2);
    }

    public static int compareUnsigned(short s, short s2) {
        return compareUnsigned(toUnsignedInt(s), toUnsignedInt(s2));
    }

    public static ByteBuffer getUnsigned(ByteBuffer byteBuffer, byte[] bArr, int i, int i2) {
        return byteBuffer.get(bArr, i, i2);
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int i) {
        return intBuffer.put(i);
    }

    public static double toDouble(int i) {
        return a(toUnsignedLong(i)).doubleValue();
    }

    public static float toFloat(int i) {
        return a(toUnsignedLong(i)).floatValue();
    }

    public static String toUnsignedString(int i) {
        return Long.toString(toUnsignedLong(i));
    }

    public static int compareUnsigned(byte b, byte b2) {
        return compareUnsigned(toUnsignedInt(b), toUnsignedInt(b2));
    }

    public static int getUnsigned(IntBuffer intBuffer, int i) {
        return intBuffer.get(i);
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int i, int i2) {
        return intBuffer.put(i, i2);
    }

    public static double toDouble(long j) {
        return a(j).doubleValue();
    }

    public static float toFloat(long j) {
        return a(j).floatValue();
    }

    public static String toUnsignedString(int i, int i2) {
        return Long.toString(toUnsignedLong(i), i2);
    }

    public static void getUnsigned(ByteBuffer byteBuffer, byte[] bArr) {
        byteBuffer.get(bArr);
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int[] iArr) {
        return intBuffer.put(iArr);
    }

    public static String toUnsignedString(short s) {
        return Long.toString(toUnsignedLong(s));
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int[] iArr, int i, int i2) {
        return intBuffer.put(iArr, i, i2);
    }

    public static String toUnsignedString(short s, int i) {
        return Long.toString(toUnsignedLong(s), i);
    }

    public static String toUnsignedString(byte b) {
        return Long.toString(toUnsignedLong(b));
    }

    public static String toUnsignedString(byte b, int i) {
        return Long.toString(toUnsignedLong(b), i);
    }
}
