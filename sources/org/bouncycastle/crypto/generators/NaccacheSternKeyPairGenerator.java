package org.bouncycastle.crypto.generators;

import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.sifli.ezip.NeuQuant;
import com.touchgui.sdk.TGEventListener;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;
/* loaded from: classes12.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static int[] b = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, Command.CMD_PHONE_NUMBER_PLAY_MODE, 251, 257, DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS, 269, DfuException.ERROR_READ_APP_INFO_ERROR, DfuException.ERROR_READ_REMOTE_MAC_ADDR, DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED, 283, TGEventListener.WATCH_FACE_INSTALLED, 307, 311, 313, 317, com.veryfit.multi.nativeprotocol.b.l1, com.veryfit.multi.nativeprotocol.b.r1, 347, 349, com.veryfit.multi.nativeprotocol.b.t1, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, NeuQuant.prime3, NeuQuant.prime2, NeuQuant.prime1, 503, 509, DfuConstants.PROGRESS_START_DFU_PROCESS, DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE, DfuAdapter.STATE_READ_IMAGE_INFO, 547, com.veryfit.multi.nativeprotocol.b.b2};
    public static final BigInteger c = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    public NaccacheSternKeyGenerationParameters f14738a;

    public static Vector a(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf(b[i2]));
        }
        return vector;
    }

    public static BigInteger b(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigInteger = new BigInteger(i, i2, secureRandom);
        while (bigInteger.bitLength() != i) {
            bigInteger = new BigInteger(i, i2, secureRandom);
        }
        return bigInteger;
    }

    public static int c(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() & Integer.MAX_VALUE)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    public static Vector d(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), c(secureRandom, vector2.size() + 1));
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger b2;
        BigInteger add;
        BigInteger b3;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger add2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        BigInteger bigInteger7;
        boolean z;
        BigInteger bigInteger8;
        BigInteger bigInteger9;
        int i;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        long j2;
        BigInteger bigInteger10;
        int i2;
        int strength = this.f14738a.getStrength();
        SecureRandom random = this.f14738a.getRandom();
        int certainty = this.f14738a.getCertainty();
        boolean isDebug = this.f14738a.isDebug();
        if (isDebug) {
            PrintStream printStream2 = System.out;
            printStream2.println("Fetching first " + this.f14738a.getCntSmallPrimes() + " primes.");
        }
        Vector d = d(a(this.f14738a.getCntSmallPrimes()), random);
        BigInteger bigInteger11 = c;
        BigInteger bigInteger12 = bigInteger11;
        for (int i3 = 0; i3 < d.size() / 2; i3++) {
            bigInteger12 = bigInteger12.multiply((BigInteger) d.elementAt(i3));
        }
        for (int size = d.size() / 2; size < d.size(); size++) {
            bigInteger11 = bigInteger11.multiply((BigInteger) d.elementAt(size));
        }
        BigInteger multiply = bigInteger12.multiply(bigInteger11);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger b4 = b(bitLength, certainty, random);
        BigInteger b5 = b(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = b4.multiply(bigInteger12).shiftLeft(1);
        BigInteger shiftLeft2 = b5.multiply(bigInteger11).shiftLeft(1);
        long j3 = 0;
        while (true) {
            j = j3 + 1;
            b2 = b(24, certainty, random);
            add = b2.multiply(shiftLeft).add(c);
            if (add.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        b3 = b(24, certainty, random);
                    } while (b2.equals(b3));
                    BigInteger multiply2 = b3.multiply(shiftLeft2);
                    bigInteger = shiftLeft2;
                    bigInteger2 = c;
                    add2 = multiply2.add(bigInteger2);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    shiftLeft2 = bigInteger;
                }
                bigInteger3 = shiftLeft;
                if (!multiply.gcd(b2.multiply(b3)).equals(bigInteger2)) {
                    continue;
                } else if (add.multiply(add2).bitLength() >= strength) {
                    break;
                } else if (isDebug) {
                    PrintStream printStream3 = System.out;
                    printStream3.println("key size too small. Should be " + strength + " but is actually " + add.multiply(add2).bitLength());
                }
            } else {
                bigInteger = shiftLeft2;
                bigInteger3 = shiftLeft;
            }
            j3 = j;
            shiftLeft2 = bigInteger;
            shiftLeft = bigInteger3;
        }
        BigInteger bigInteger13 = b5;
        if (isDebug) {
            PrintStream printStream4 = System.out;
            bigInteger4 = b4;
            printStream4.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger4 = b4;
        }
        BigInteger multiply3 = add.multiply(add2);
        BigInteger multiply4 = add.subtract(bigInteger2).multiply(add2.subtract(bigInteger2));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j4 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger5 = add;
            bigInteger6 = add2;
            int i4 = 0;
            while (i4 != d.size()) {
                BigInteger divide = multiply4.divide((BigInteger) d.elementAt(i4));
                while (true) {
                    j2 = j4 + 1;
                    bigInteger10 = new BigInteger(strength, certainty, random);
                    i2 = strength;
                    if (bigInteger10.modPow(divide, multiply3).equals(c)) {
                        j4 = j2;
                        strength = i2;
                    }
                }
                vector.addElement(bigInteger10);
                i4++;
                j4 = j2;
                strength = i2;
            }
            int i5 = strength;
            bigInteger7 = c;
            int i6 = 0;
            while (i6 < d.size()) {
                bigInteger7 = bigInteger7.multiply(((BigInteger) vector.elementAt(i6)).modPow(multiply.divide((BigInteger) d.elementAt(i6)), multiply3)).mod(multiply3);
                i6++;
                random = random;
            }
            SecureRandom secureRandom = random;
            int i7 = 0;
            while (true) {
                if (i7 >= d.size()) {
                    z = false;
                    break;
                } else if (bigInteger7.modPow(multiply4.divide((BigInteger) d.elementAt(i7)), multiply3).equals(c)) {
                    if (isDebug) {
                        PrintStream printStream5 = System.out;
                        printStream5.println("g has order phi(n)/" + d.elementAt(i7) + "\n g: " + bigInteger7);
                    }
                    z = true;
                } else {
                    i7++;
                }
            }
            if (!z) {
                BigInteger modPow = bigInteger7.modPow(multiply4.divide(BigInteger.valueOf(4L)), multiply3);
                BigInteger bigInteger14 = c;
                if (modPow.equals(bigInteger14)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/4\n g:";
                        sb.append(str);
                        sb.append(bigInteger7);
                        printStream.println(sb.toString());
                    }
                } else if (bigInteger7.modPow(multiply4.divide(b2), multiply3).equals(bigInteger14)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/p'\n g: ";
                        sb.append(str);
                        sb.append(bigInteger7);
                        printStream.println(sb.toString());
                    }
                } else if (!bigInteger7.modPow(multiply4.divide(b3), multiply3).equals(bigInteger14)) {
                    bigInteger8 = bigInteger4;
                    if (!bigInteger7.modPow(multiply4.divide(bigInteger8), multiply3).equals(bigInteger14)) {
                        bigInteger9 = bigInteger13;
                        if (!bigInteger7.modPow(multiply4.divide(bigInteger9), multiply3).equals(bigInteger14)) {
                            break;
                        } else if (isDebug) {
                            PrintStream printStream6 = System.out;
                            StringBuilder sb2 = new StringBuilder();
                            i = certainty;
                            sb2.append("g has order phi(n)/b\n g: ");
                            sb2.append(bigInteger7);
                            printStream6.println(sb2.toString());
                        }
                    } else {
                        if (isDebug) {
                            PrintStream printStream7 = System.out;
                            printStream7.println("g has order phi(n)/a\n g: " + bigInteger7);
                        }
                        bigInteger9 = bigInteger13;
                    }
                    i = certainty;
                } else if (isDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/q'\n g: ";
                    sb.append(str);
                    sb.append(bigInteger7);
                    printStream.println(sb.toString());
                }
                bigInteger4 = bigInteger8;
                certainty = i;
                add2 = bigInteger6;
                add = bigInteger5;
                strength = i5;
                random = secureRandom;
                bigInteger13 = bigInteger9;
            }
            bigInteger9 = bigInteger13;
            bigInteger8 = bigInteger4;
            i = certainty;
            bigInteger4 = bigInteger8;
            certainty = i;
            add2 = bigInteger6;
            add = bigInteger5;
            strength = i5;
            random = secureRandom;
            bigInteger13 = bigInteger9;
        }
        if (isDebug) {
            PrintStream printStream8 = System.out;
            printStream8.println("needed " + j4 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            PrintStream printStream9 = System.out;
            printStream9.println("smallPrimes: " + d);
            PrintStream printStream10 = System.out;
            printStream10.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            PrintStream printStream11 = System.out;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("a:.......... ");
            sb3.append(bigInteger8);
            printStream11.println(sb3.toString());
            PrintStream printStream12 = System.out;
            printStream12.println("b:.......... " + bigInteger9);
            PrintStream printStream13 = System.out;
            printStream13.println("p':......... " + b2);
            PrintStream printStream14 = System.out;
            printStream14.println("q':......... " + b3);
            PrintStream printStream15 = System.out;
            printStream15.println("p:.......... " + bigInteger5);
            PrintStream printStream16 = System.out;
            printStream16.println("q:.......... " + bigInteger6);
            PrintStream printStream17 = System.out;
            printStream17.println("n:.......... " + multiply3);
            PrintStream printStream18 = System.out;
            printStream18.println("phi(n):..... " + multiply4);
            PrintStream printStream19 = System.out;
            printStream19.println("g:.......... " + bigInteger7);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger7, multiply3, multiply.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger7, multiply3, multiply.bitLength(), d, multiply4));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.f14738a = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
