package org.bouncycastle.crypto.examples;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.generators.DESedeKeyGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes12.dex */
public class DESExample {

    /* renamed from: a  reason: collision with root package name */
    public boolean f14716a;
    public PaddedBufferedBlockCipher b;
    public BufferedInputStream c;
    public BufferedOutputStream d;
    public byte[] e;

    public DESExample() {
        this.f14716a = true;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    public DESExample(String str, String str2, String str3, boolean z) {
        SecureRandom secureRandom;
        PrintStream printStream;
        StringBuilder sb;
        String str4;
        this.f14716a = true;
        SecureRandom secureRandom2 = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f14716a = z;
        try {
            this.c = new BufferedInputStream(new FileInputStream(str));
        } catch (FileNotFoundException unused) {
            PrintStream printStream2 = System.err;
            printStream2.println("Input file not found [" + str + "]");
            System.exit(1);
        }
        try {
            this.d = new BufferedOutputStream(new FileOutputStream(str2));
        } catch (IOException unused2) {
            PrintStream printStream3 = System.err;
            printStream3.println("Output file not created [" + str2 + "]");
            System.exit(1);
        }
        if (z) {
            try {
                secureRandom = new SecureRandom();
            } catch (Exception unused3) {
            }
            try {
                secureRandom.setSeed("www.bouncycastle.org".getBytes());
            } catch (Exception unused4) {
                secureRandom2 = secureRandom;
                try {
                    System.err.println("Hmmm, no SHA1PRNG, you need the Sun implementation");
                    System.exit(1);
                    secureRandom = secureRandom2;
                    KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(secureRandom, 192);
                    DESedeKeyGenerator dESedeKeyGenerator = new DESedeKeyGenerator();
                    dESedeKeyGenerator.init(keyGenerationParameters);
                    this.e = dESedeKeyGenerator.generateKey();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
                    byte[] encode = Hex.encode(this.e);
                    bufferedOutputStream.write(encode, 0, encode.length);
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    return;
                } catch (IOException unused5) {
                    printStream = System.err;
                    sb = new StringBuilder();
                    str4 = "Could not decryption create key file [";
                }
            }
            KeyGenerationParameters keyGenerationParameters2 = new KeyGenerationParameters(secureRandom, 192);
            DESedeKeyGenerator dESedeKeyGenerator2 = new DESedeKeyGenerator();
            dESedeKeyGenerator2.init(keyGenerationParameters2);
            this.e = dESedeKeyGenerator2.generateKey();
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str3));
            byte[] encode2 = Hex.encode(this.e);
            bufferedOutputStream2.write(encode2, 0, encode2.length);
            bufferedOutputStream2.flush();
            bufferedOutputStream2.close();
            return;
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str3));
            int available = bufferedInputStream.available();
            byte[] bArr = new byte[available];
            bufferedInputStream.read(bArr, 0, available);
            this.e = Hex.decode(bArr);
            return;
        } catch (IOException unused6) {
            printStream = System.err;
            sb = new StringBuilder();
            str4 = "Decryption key file not found, or not valid [";
        }
        sb.append(str4);
        sb.append(str3);
        sb.append("]");
        printStream.println(sb.toString());
        System.exit(1);
    }

    public static void main(String[] strArr) {
        String str;
        boolean z = true;
        if (strArr.length < 2) {
            new DESExample();
            System.err.println("Usage: java " + DESExample.class.getName() + " infile outfile [keyfile]");
            System.exit(1);
        }
        String str2 = strArr[0];
        String str3 = strArr[1];
        if (strArr.length > 2) {
            str = strArr[2];
            z = false;
        } else {
            str = "deskey.dat";
        }
        new DESExample(str2, str3, str, z).c();
    }

    public final void a(byte[] bArr) {
        this.b.init(false, new KeyParameter(bArr));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.c));
        byte[] bArr2 = null;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    try {
                        break;
                    } catch (CryptoException unused) {
                        return;
                    }
                }
                byte[] decode = Hex.decode(readLine);
                bArr2 = new byte[this.b.getOutputSize(decode.length)];
                int processBytes = this.b.processBytes(decode, 0, decode.length, bArr2, 0);
                if (processBytes > 0) {
                    this.d.write(bArr2, 0, processBytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int doFinal = this.b.doFinal(bArr2, 0);
        if (doFinal > 0) {
            this.d.write(bArr2, 0, doFinal);
        }
    }

    public final void b(byte[] bArr) {
        this.b.init(true, new KeyParameter(bArr));
        byte[] bArr2 = new byte[47];
        byte[] bArr3 = new byte[this.b.getOutputSize(47)];
        while (true) {
            try {
                int read = this.c.read(bArr2, 0, 47);
                if (read <= 0) {
                    try {
                        break;
                    } catch (CryptoException unused) {
                        return;
                    }
                }
                int processBytes = this.b.processBytes(bArr2, 0, read, bArr3, 0);
                if (processBytes > 0) {
                    byte[] encode = Hex.encode(bArr3, 0, processBytes);
                    this.d.write(encode, 0, encode.length);
                    this.d.write(10);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int doFinal = this.b.doFinal(bArr3, 0);
        if (doFinal > 0) {
            byte[] encode2 = Hex.encode(bArr3, 0, doFinal);
            this.d.write(encode2, 0, encode2.length);
            this.d.write(10);
        }
    }

    public final void c() {
        this.b = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));
        if (this.f14716a) {
            b(this.e);
        } else {
            a(this.e);
        }
        try {
            this.c.close();
            this.d.flush();
            this.d.close();
        } catch (IOException e) {
            PrintStream printStream = System.err;
            printStream.println("exception closing resources: " + e.getMessage());
        }
    }
}
