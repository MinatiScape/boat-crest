package no.nordicsemi.android.dfu.internal;

import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import no.nordicsemi.android.dfu.internal.manifest.Manifest;
import no.nordicsemi.android.dfu.internal.manifest.ManifestFile;
/* loaded from: classes12.dex */
public class ArchiveInputStream extends InputStream {
    private static final String APPLICATION_BIN = "application.bin";
    private static final String APPLICATION_HEX = "application.hex";
    private static final String APPLICATION_INIT = "application.dat";
    private static final String BOOTLOADER_BIN = "bootloader.bin";
    private static final String BOOTLOADER_HEX = "bootloader.hex";
    private static final String MANIFEST = "manifest.json";
    private static final String SOFTDEVICE_BIN = "softdevice.bin";
    private static final String SOFTDEVICE_HEX = "softdevice.hex";
    private static final String SYSTEM_INIT = "system.dat";
    private static final String TAG = "DfuArchiveInputStream";
    private byte[] applicationBytes;
    private byte[] applicationInitBytes;
    private int applicationSize;
    private byte[] bootloaderBytes;
    private int bootloaderSize;
    private int bytesRead;
    private int bytesReadFromCurrentSource;
    private int bytesReadFromMarkedSource;
    private final CRC32 crc32;
    private byte[] currentSource;
    private final Map<String, byte[]> entries;
    private Manifest manifest;
    private byte[] markedSource;
    private byte[] softDeviceAndBootloaderBytes;
    private byte[] softDeviceBytes;
    private int softDeviceSize;
    private byte[] systemInitBytes;
    private int type;
    private final ZipInputStream zipInputStream;

    /* JADX WARN: Removed duplicated region for block: B:81:0x01fd A[Catch: all -> 0x0262, TryCatch #0 {all -> 0x0262, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0083, B:22:0x008d, B:24:0x0091, B:26:0x0095, B:28:0x00b7, B:29:0x00be, B:30:0x00db, B:31:0x00dc, B:32:0x00e3, B:33:0x00e4, B:36:0x00ee, B:38:0x00f2, B:40:0x0114, B:41:0x011b, B:42:0x0138, B:43:0x0139, B:46:0x0143, B:48:0x0147, B:50:0x014b, B:52:0x014f, B:54:0x0171, B:96:0x024d, B:62:0x01ad, B:63:0x01b4, B:55:0x0182, B:56:0x019f, B:57:0x01a0, B:58:0x01a7, B:16:0x0064, B:17:0x0081, B:65:0x01b7, B:86:0x021d, B:99:0x025a, B:100:0x0261, B:88:0x0220, B:90:0x022c, B:91:0x0236, B:93:0x023a, B:79:0x01f1, B:81:0x01fd, B:82:0x0207, B:84:0x020b, B:67:0x01bb, B:69:0x01c7, B:70:0x01d1, B:72:0x01d5), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x020b A[Catch: all -> 0x0262, TryCatch #0 {all -> 0x0262, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0083, B:22:0x008d, B:24:0x0091, B:26:0x0095, B:28:0x00b7, B:29:0x00be, B:30:0x00db, B:31:0x00dc, B:32:0x00e3, B:33:0x00e4, B:36:0x00ee, B:38:0x00f2, B:40:0x0114, B:41:0x011b, B:42:0x0138, B:43:0x0139, B:46:0x0143, B:48:0x0147, B:50:0x014b, B:52:0x014f, B:54:0x0171, B:96:0x024d, B:62:0x01ad, B:63:0x01b4, B:55:0x0182, B:56:0x019f, B:57:0x01a0, B:58:0x01a7, B:16:0x0064, B:17:0x0081, B:65:0x01b7, B:86:0x021d, B:99:0x025a, B:100:0x0261, B:88:0x0220, B:90:0x022c, B:91:0x0236, B:93:0x023a, B:79:0x01f1, B:81:0x01fd, B:82:0x0207, B:84:0x020b, B:67:0x01bb, B:69:0x01c7, B:70:0x01d1, B:72:0x01d5), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x022c A[Catch: all -> 0x0262, TryCatch #0 {all -> 0x0262, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0083, B:22:0x008d, B:24:0x0091, B:26:0x0095, B:28:0x00b7, B:29:0x00be, B:30:0x00db, B:31:0x00dc, B:32:0x00e3, B:33:0x00e4, B:36:0x00ee, B:38:0x00f2, B:40:0x0114, B:41:0x011b, B:42:0x0138, B:43:0x0139, B:46:0x0143, B:48:0x0147, B:50:0x014b, B:52:0x014f, B:54:0x0171, B:96:0x024d, B:62:0x01ad, B:63:0x01b4, B:55:0x0182, B:56:0x019f, B:57:0x01a0, B:58:0x01a7, B:16:0x0064, B:17:0x0081, B:65:0x01b7, B:86:0x021d, B:99:0x025a, B:100:0x0261, B:88:0x0220, B:90:0x022c, B:91:0x0236, B:93:0x023a, B:79:0x01f1, B:81:0x01fd, B:82:0x0207, B:84:0x020b, B:67:0x01bb, B:69:0x01c7, B:70:0x01d1, B:72:0x01d5), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x023a A[Catch: all -> 0x0262, TryCatch #0 {all -> 0x0262, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0083, B:22:0x008d, B:24:0x0091, B:26:0x0095, B:28:0x00b7, B:29:0x00be, B:30:0x00db, B:31:0x00dc, B:32:0x00e3, B:33:0x00e4, B:36:0x00ee, B:38:0x00f2, B:40:0x0114, B:41:0x011b, B:42:0x0138, B:43:0x0139, B:46:0x0143, B:48:0x0147, B:50:0x014b, B:52:0x014f, B:54:0x0171, B:96:0x024d, B:62:0x01ad, B:63:0x01b4, B:55:0x0182, B:56:0x019f, B:57:0x01a0, B:58:0x01a7, B:16:0x0064, B:17:0x0081, B:65:0x01b7, B:86:0x021d, B:99:0x025a, B:100:0x0261, B:88:0x0220, B:90:0x022c, B:91:0x0236, B:93:0x023a, B:79:0x01f1, B:81:0x01fd, B:82:0x0207, B:84:0x020b, B:67:0x01bb, B:69:0x01c7, B:70:0x01d1, B:72:0x01d5), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x025a A[Catch: all -> 0x0262, TRY_ENTER, TryCatch #0 {all -> 0x0262, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0083, B:22:0x008d, B:24:0x0091, B:26:0x0095, B:28:0x00b7, B:29:0x00be, B:30:0x00db, B:31:0x00dc, B:32:0x00e3, B:33:0x00e4, B:36:0x00ee, B:38:0x00f2, B:40:0x0114, B:41:0x011b, B:42:0x0138, B:43:0x0139, B:46:0x0143, B:48:0x0147, B:50:0x014b, B:52:0x014f, B:54:0x0171, B:96:0x024d, B:62:0x01ad, B:63:0x01b4, B:55:0x0182, B:56:0x019f, B:57:0x01a0, B:58:0x01a7, B:16:0x0064, B:17:0x0081, B:65:0x01b7, B:86:0x021d, B:99:0x025a, B:100:0x0261, B:88:0x0220, B:90:0x022c, B:91:0x0236, B:93:0x023a, B:79:0x01f1, B:81:0x01fd, B:82:0x0207, B:84:0x020b, B:67:0x01bb, B:69:0x01c7, B:70:0x01d1, B:72:0x01d5), top: B:106:0x0025 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArchiveInputStream(java.io.InputStream r6, int r7, int r8) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 655
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.internal.ArchiveInputStream.<init>(java.io.InputStream, int, int):void");
    }

    private void parseZip(int i) throws IOException {
        byte[] bArr = new byte[1024];
        String str = null;
        while (true) {
            ZipEntry nextEntry = this.zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            String validateFilename = validateFilename(nextEntry.getName(), ".");
            if (nextEntry.isDirectory()) {
                Log.w(TAG, "A directory found in the ZIP: " + validateFilename + "!");
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = this.zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (validateFilename.toLowerCase(Locale.US).endsWith("hex")) {
                    HexInputStream hexInputStream = new HexInputStream(byteArray, i);
                    byteArray = new byte[hexInputStream.available()];
                    hexInputStream.read(byteArray);
                    hexInputStream.close();
                }
                if ("manifest.json".equals(validateFilename)) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        str = new String(byteArray, StandardCharsets.UTF_8);
                    } else {
                        str = new String(byteArray, "UTF-8");
                    }
                } else {
                    this.entries.put(validateFilename, byteArray);
                }
            }
        }
        if (this.entries.isEmpty()) {
            throw new FileNotFoundException("No files found in the ZIP. Check if the URI provided is valid and the ZIP contains required files on root level, not in a directory.");
        }
        if (str != null) {
            Manifest manifest = ((ManifestFile) new Gson().fromJson(str, (Class<Object>) ManifestFile.class)).getManifest();
            this.manifest = manifest;
            if (manifest == null) {
                Log.w(TAG, "Manifest failed to be parsed. Did you add \n-keep class no.nordicsemi.android.dfu.** { *; }\nto your proguard rules?");
                return;
            }
            return;
        }
        Log.w(TAG, "Manifest not found in the ZIP. It is recommended to use a distribution file created with: https://github.com/NordicSemiconductor/pc-nrfutil/ (for Legacy DFU use version 0.5.x)");
    }

    private int rawRead(@NonNull byte[] bArr, int i, int i2) {
        int min = Math.min(i2, this.currentSource.length - this.bytesReadFromCurrentSource);
        System.arraycopy(this.currentSource, this.bytesReadFromCurrentSource, bArr, i, min);
        this.bytesReadFromCurrentSource += min;
        this.bytesRead += min;
        this.crc32.update(bArr, i, min);
        return min;
    }

    private byte[] startNextFile() {
        byte[] bArr;
        byte[] bArr2 = this.currentSource;
        if (bArr2 == this.softDeviceBytes && (bArr = this.bootloaderBytes) != null && (this.type & 2) > 0) {
            this.currentSource = bArr;
        } else {
            bArr = this.applicationBytes;
            if (bArr2 != bArr && bArr != null && (this.type & 4) > 0) {
                this.currentSource = bArr;
            } else {
                bArr = null;
                this.currentSource = null;
            }
        }
        this.bytesReadFromCurrentSource = 0;
        return bArr;
    }

    private String validateFilename(@NonNull String str, @NonNull String str2) throws IOException {
        String canonicalPath = new File(str).getCanonicalPath();
        if (canonicalPath.startsWith(new File(str2).getCanonicalPath())) {
            return canonicalPath.substring(1);
        }
        throw new IllegalStateException("File is outside extraction target directory.");
    }

    public int applicationImageSize() {
        if ((this.type & 4) > 0) {
            return this.applicationSize;
        }
        return 0;
    }

    @Override // java.io.InputStream
    public int available() {
        int softDeviceImageSize;
        int i;
        byte[] bArr = this.softDeviceAndBootloaderBytes;
        if (bArr != null && this.softDeviceSize == 0 && this.bootloaderSize == 0 && (this.type & 3) > 0) {
            softDeviceImageSize = bArr.length + applicationImageSize();
            i = this.bytesRead;
        } else {
            softDeviceImageSize = softDeviceImageSize() + bootloaderImageSize() + applicationImageSize();
            i = this.bytesRead;
        }
        return softDeviceImageSize - i;
    }

    public int bootloaderImageSize() {
        if ((this.type & 2) > 0) {
            return this.bootloaderSize;
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.softDeviceBytes = null;
        this.bootloaderBytes = null;
        this.applicationBytes = null;
        this.softDeviceAndBootloaderBytes = null;
        this.applicationSize = 0;
        this.bootloaderSize = 0;
        this.softDeviceSize = 0;
        this.currentSource = null;
        this.bytesReadFromCurrentSource = 0;
        this.bytesRead = 0;
        this.zipInputStream.close();
    }

    public void fullReset() {
        byte[] bArr;
        byte[] bArr2 = this.softDeviceBytes;
        if (bArr2 != null && (bArr = this.bootloaderBytes) != null && this.currentSource == bArr) {
            this.currentSource = bArr2;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
    }

    public byte[] getApplicationInit() {
        return this.applicationInitBytes;
    }

    public int getBytesRead() {
        return this.bytesRead;
    }

    public int getContentType() {
        this.type = 0;
        if (this.softDeviceAndBootloaderBytes != null) {
            this.type = 0 | 3;
        }
        if (this.softDeviceSize > 0) {
            this.type |= 1;
        }
        if (this.bootloaderSize > 0) {
            this.type |= 2;
        }
        if (this.applicationSize > 0) {
            this.type |= 4;
        }
        return this.type;
    }

    public long getCrc32() {
        return this.crc32.getValue();
    }

    public byte[] getSystemInit() {
        return this.systemInitBytes;
    }

    public boolean isSecureDfuRequired() {
        Manifest manifest = this.manifest;
        return manifest != null && manifest.isSecureDfuRequired();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.markedSource = this.currentSource;
        this.bytesReadFromMarkedSource = this.bytesReadFromCurrentSource;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    @Override // java.io.InputStream
    public void reset() {
        byte[] bArr;
        this.currentSource = this.markedSource;
        int i = this.bytesReadFromMarkedSource;
        this.bytesReadFromCurrentSource = i;
        this.bytesRead = i;
        this.crc32.reset();
        if (this.currentSource == this.bootloaderBytes && (bArr = this.softDeviceBytes) != null) {
            this.crc32.update(bArr);
            this.bytesRead += this.softDeviceSize;
        }
        this.crc32.update(this.currentSource, 0, this.bytesReadFromCurrentSource);
    }

    public int setContentType(int i) {
        byte[] bArr;
        this.type = i;
        int i2 = i & 4;
        if (i2 > 0 && this.applicationBytes == null) {
            this.type = i & (-5);
        }
        int i3 = i & 3;
        if (i3 == 3) {
            if (this.softDeviceBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
            if (this.bootloaderBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
        } else if (this.softDeviceAndBootloaderBytes != null) {
            this.type &= -4;
        }
        if (i3 > 0 && (bArr = this.softDeviceAndBootloaderBytes) != null) {
            this.currentSource = bArr;
        } else if ((i & 1) > 0) {
            this.currentSource = this.softDeviceBytes;
        } else if ((i & 2) > 0) {
            this.currentSource = this.bootloaderBytes;
        } else if (i2 > 0) {
            this.currentSource = this.applicationBytes;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
        return this.type;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return 0L;
    }

    public int softDeviceImageSize() {
        if ((this.type & 1) > 0) {
            return this.softDeviceSize;
        }
        return 0;
    }

    @Override // java.io.InputStream
    public int read(@NonNull byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(@NonNull byte[] bArr, int i, int i2) {
        int rawRead = rawRead(bArr, i, i2);
        return (i2 <= rawRead || startNextFile() == null) ? rawRead : rawRead + rawRead(bArr, i + rawRead, i2 - rawRead);
    }
}
