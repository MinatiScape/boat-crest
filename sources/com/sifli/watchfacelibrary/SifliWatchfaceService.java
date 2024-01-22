package com.sifli.watchfacelibrary;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.account.Region;
import com.sifli.serialtransport.OnSerialTransListener;
import com.sifli.serialtransport.serialTransService;
import com.touchgui.sdk.TGErrorCode;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes12.dex */
public class SifliWatchfaceService extends IntentService {
    public static int A = 0;
    public static int B = 0;
    public static final String BROADCAST_WATCHFACE_LOG = "com.sifli.watchfacelibrary.broadcast.BROADCAST_LOG";
    public static final String BROADCAST_WATCHFACE_PROGRESS = "com.sifli.watchfacelibrary.broadcast.BROADCAST_PROGRESS";
    public static final String BROADCAST_WATCHFACE_STATE = "com.sifli.watchfacelibrary.broadcast.BROADCAST_WATCHFACE_STATE";
    public static int C = 0;
    public static final int ERROR_BLUETOOTH_DISCONNECTED = 6;
    public static final int ERROR_BLUETOOTH_OFF = 5;
    public static final int ERROR_COMMAND_REMOTE_ABORT = 7;
    public static final int ERROR_COMMAND_RSP_ERROR = 2;
    public static final int ERROR_COMMAND_RSP_TIMEOUT = 3;
    public static final int ERROR_FILE_SIZE_ERROR = 9;
    public static final int ERROR_GENERAL_ERROR = 8;
    public static final int ERROR_NO_ERROR = 0;
    public static final int ERROR_SERIAL_TRANSPORT_CLOSED = 4;
    public static final int ERROR_START_SERIAL_TRANSPORT_FAIL = 1;
    public static final String EXTRA_WATCHFACE_LOG = "com.sifli.watchfacelibrary.broadcast.EXTRA_WATCHFACE_LOG";
    public static final String EXTRA_WATCHFACE_PROGRESS = "Sifli.watchfacelibrary.broadcast.EXTRA_WATCHFACE_PROGRESS";
    public static final String EXTRA_WATCHFACE_STATE = "Sifli.watchfacelibrary.broadcast.EXTRA_WATCHFACE_STATE";
    public static final String EXTRA_WATCHFACE_STATE_RSP = "Sifli.watchfacelibrary.broadcast.EXTRA_WATCHFACE_RESPONSE";
    public static final int FILE_TYPE_BACKGROUND_PIC = 2;
    public static final int FILE_TYPE_CUSTOMIZED = 3;
    public static final int FILE_TYPE_EQ = 6;
    public static final int FILE_TYPE_MULTIPLE_LANGUAGE = 1;
    public static final int FILE_TYPE_MUSIC = 4;
    public static final int FILE_TYPE_WATCHFACE = 0;
    public static final int FILE_TYPE_WATCHFACE_JS = 5;
    public static int z;
    public serialTransService.serialTransBinder h;
    public boolean i;
    public int j;
    public int k;
    public boolean l;
    public boolean m;
    public final Object n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public ArrayList<String> s;
    public Handler t;
    public boolean u;
    public final ServiceConnection v;
    public final Runnable w;
    public final Runnable x;
    public BroadcastReceiver y;

    /* loaded from: classes12.dex */
    public class a implements ServiceConnection {

        /* renamed from: com.sifli.watchfacelibrary.SifliWatchfaceService$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public class C0732a implements OnSerialTransListener {
            public C0732a() {
            }

            @Override // com.sifli.serialtransport.OnSerialTransListener
            public void onSerialBluetoothConnect(int i) {
                SifliWatchfaceService.this.m = true;
                if (i == 132) {
                    SifliWatchfaceService.this.o = i;
                }
                synchronized (SifliWatchfaceService.this.n) {
                    SifliWatchfaceService.this.n.notifyAll();
                }
            }

            @Override // com.sifli.serialtransport.OnSerialTransListener
            public void onSerialDataReceive(int i, byte[] bArr) {
                if (i == 4) {
                    byte b = bArr[0];
                    if (b == 1) {
                        SifliWatchfaceService.this.D(bArr);
                    } else if (b == 3) {
                        SifliWatchfaceService.this.H(bArr);
                    } else if (b == 5) {
                        SifliWatchfaceService.this.E(bArr);
                    } else if (b == 7) {
                        SifliWatchfaceService.this.F(bArr);
                    } else if (b == 9) {
                        SifliWatchfaceService.this.I(bArr);
                    } else if (b != 10) {
                    } else {
                        SifliWatchfaceService.this.G(bArr);
                    }
                }
            }

            @Override // com.sifli.serialtransport.OnSerialTransListener
            public void onSerialMTUChangeResult(int i) {
                if (i == 23) {
                    Log.d("watchfaceService", "onSerialMTUChangeResult use short packet");
                    SifliWatchfaceService.this.p = 1024;
                }
            }

            @Override // com.sifli.serialtransport.OnSerialTransListener
            public void onSerialTranSendFinish() {
                synchronized (SifliWatchfaceService.this.n) {
                    SifliWatchfaceService.this.n.notifyAll();
                }
            }

            @Override // com.sifli.serialtransport.OnSerialTransListener
            public void onSerialTransBLEStateChanged(int i) {
                if (i == 0) {
                    SifliWatchfaceService.this.v();
                    SifliWatchfaceService.this.o = 6;
                    synchronized (SifliWatchfaceService.this.n) {
                        SifliWatchfaceService.this.n.notifyAll();
                    }
                }
            }

            @Override // com.sifli.serialtransport.OnSerialTransListener
            public void onSerialTransProgress(int i) {
                SifliWatchfaceService.f(i);
                int i2 = (SifliWatchfaceService.B * 100) / SifliWatchfaceService.C;
                if (SifliWatchfaceService.this.u) {
                    Log.d("watchfaceService", "progress " + i2);
                    Log.d("watchfaceService", "progress " + SifliWatchfaceService.B + ", " + SifliWatchfaceService.C);
                }
                SifliWatchfaceService.this.Q(i2);
            }

            @Override // com.sifli.serialtransport.OnSerialTransListener
            public void onSerialTransSend(int i) {
                if (i == 0 && !SifliWatchfaceService.this.l) {
                    SifliWatchfaceService.this.l = true;
                    synchronized (SifliWatchfaceService.this.n) {
                        SifliWatchfaceService.this.n.notifyAll();
                    }
                }
            }
        }

        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("watchfaceService", "onServiceConnected, " + componentName.toString());
            SifliWatchfaceService.this.r = true;
            synchronized (SifliWatchfaceService.this.n) {
                SifliWatchfaceService.this.n.notifyAll();
            }
            SifliWatchfaceService.this.h = (serialTransService.serialTransBinder) iBinder;
            SifliWatchfaceService.this.h.getService().setOnSerialTransListener(new C0732a());
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("watchfaceService", "onServiceDisconnected" + componentName.toString());
            SifliWatchfaceService.this.r = false;
            SifliWatchfaceService.this.o = 4;
            synchronized (SifliWatchfaceService.this.n) {
                SifliWatchfaceService.this.n.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("watchfaceService", "start serial trans overtime!");
            SifliWatchfaceService.this.o = 1;
            synchronized (SifliWatchfaceService.this.n) {
                SifliWatchfaceService.this.n.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("watchfaceService", "serial trans command overtime!");
            SifliWatchfaceService.this.o = 3;
            synchronized (SifliWatchfaceService.this.n) {
                SifliWatchfaceService.this.n.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) != 10) {
                return;
            }
            Log.e("watchfaceService", "Bluetooth off");
            SifliWatchfaceService.this.o = 5;
            synchronized (SifliWatchfaceService.this.n) {
                SifliWatchfaceService.this.n.notifyAll();
            }
        }
    }

    public SifliWatchfaceService() {
        super("SifliWatchfaceService");
        this.i = false;
        this.n = new Object();
        this.p = 10240;
        this.r = false;
        this.s = new ArrayList<>();
        this.t = new Handler();
        this.u = false;
        this.v = new a();
        this.w = new b();
        this.x = new c();
        this.y = new d();
    }

    public static void V() {
    }

    public static void copyFile(Context context, Uri uri, File file) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            copyStream(openInputStream, fileOutputStream);
            openInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copyStream(InputStream inputStream, OutputStream outputStream) throws Exception, IOException {
        byte[] bArr = new byte[2048];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 2048);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 2048);
        int i = 0;
        while (true) {
            try {
                int read = bufferedInputStream.read(bArr, 0, 2048);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
                i += read;
            } finally {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    Log.e("watchfaceService", "out close error", e);
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e2) {
                    Log.e("watchfaceService", "in close error", e2);
                }
            }
        }
        bufferedOutputStream.flush();
        return i;
    }

    public static /* synthetic */ int f(int i) {
        int i2 = B + i;
        B = i2;
        return i2;
    }

    public static String getFileName(Uri uri) {
        String path;
        int lastIndexOf;
        if (uri == null || (lastIndexOf = (path = uri.getPath()).lastIndexOf(47)) == -1) {
            return null;
        }
        return path.substring(lastIndexOf + 1);
    }

    public static String getFilePathFromURI(Context context, Uri uri) {
        File externalFilesDir = context.getExternalFilesDir(null);
        String fileName = getFileName(uri);
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        File file = new File(externalFilesDir + File.separator + fileName);
        copyFile(context, uri, file);
        return file.getAbsolutePath();
    }

    public static void startActionWatchface(Context context, ArrayList<WatchfaceFile> arrayList, String str, int i) {
        Intent intent = new Intent(context, SifliWatchfaceService.class);
        intent.setAction("com.sifli.watchfacelibrary.action.WATCHFACE");
        intent.putExtra("com.sifli.watchfacelibrary.extra.TYPE", i);
        intent.putExtra("com.sifli.watchfacelibrary.extra.DATA", arrayList);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADDRESS", str);
        z = 0;
        context.startService(intent);
    }

    public static void stopActionWatchface() {
        V();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00c9, code lost:
        throw new java.lang.SecurityException("zip path have traversal characters path");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.util.zip.ZipEntry] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:85:0x0133 -> B:101:0x0136). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void unzipFolder(java.lang.String r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sifli.watchfacelibrary.SifliWatchfaceService.unzipFolder(java.lang.String, java.lang.String):void");
    }

    public final void A(String str, ArrayList<WatchfaceFile> arrayList, int i) {
        Log.d("watchfaceService", "send type " + i);
        this.o = 0;
        W(str);
        if (this.o != 0) {
            Log.e("watchfaceService", "exit " + this.o);
            R(this.o);
            v();
            return;
        }
        B(arrayList, i);
        R(this.o);
        v();
    }

    public final void B(ArrayList<WatchfaceFile> arrayList, int i) {
        U(i);
        if (this.o != 0) {
            return;
        }
        if (this.j != 0) {
            this.o = 2;
            return;
        }
        L();
        if (this.u) {
            Log.d("watchfaceService", "debug progress info");
            this.h.serialTransDebugProgress();
        }
        int i2 = 0;
        while (true) {
            if (i2 >= arrayList.size()) {
                break;
            }
            WatchfaceFile watchfaceFile = arrayList.get(i2);
            String filePath = watchfaceFile.getFilePath();
            byte[] fileData = watchfaceFile.getFileData();
            S(fileData.length, filePath);
            if (this.o != 0) {
                break;
            } else if (this.j != 0) {
                this.o = 2;
                break;
            } else {
                u(arrayList, i2);
                if (this.u) {
                    Log.d("watchfaceService", "calculateSendSize " + A);
                }
                M(fileData);
                z += 100;
                if (this.o == 0) {
                    N();
                    if (this.o != 0) {
                        break;
                    } else if (this.j != 0) {
                        this.o = 2;
                        break;
                    } else {
                        i2++;
                    }
                } else {
                    break;
                }
            }
        }
        Log.d("watchfaceService", "end error " + this.o);
        if (this.o == 0) {
            T();
            Log.i("watchfaceService", DeviceKey.KFinishFlag);
        }
    }

    public final byte[] C(String str) {
        File file = new File(str);
        byte[] bArr = new byte[0];
        getResources().getAssets();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.ISO_8859_1);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = bufferedReader.read();
                if (read != -1) {
                    byteArrayOutputStream.write(read);
                } else {
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    return bArr;
                }
            }
        } catch (FileNotFoundException unused) {
            Log.e("watchfaceService", str + " doesn't found!");
            return null;
        } catch (IOException e) {
            Log.e("watchfaceService", str + " read exception, " + e.getMessage());
            e.printStackTrace();
            return bArr;
        }
    }

    public final void D(byte[] bArr) {
        this.j = WatchfaceUtil.getShortFromByteArray(bArr, 2);
        Log.d("watchfaceService", "COMMAND_SEND_WATCHFACE_START_RSP " + this.j);
        this.i = true;
        this.p = WatchfaceUtil.getUnsignedShortFromByteArray(bArr, 4);
        Log.d("watchfaceService", "mMaxPartLen " + this.p);
        O("processTotalStartRsp " + this.j + ", len " + this.p);
        synchronized (this.n) {
            this.n.notifyAll();
        }
    }

    public final void E(byte[] bArr) {
        this.i = true;
        this.j = WatchfaceUtil.getShortFromByteArray(bArr, 2);
        O("processWatchfaceDataRsp " + this.j);
        this.k = WatchfaceUtil.getIntFromByteArray(bArr, 4);
        if (this.j != 0) {
            Log.e("watchfaceService", "processWatchfaceDataRsp " + this.j + ", index " + this.k);
        }
        synchronized (this.n) {
            this.n.notifyAll();
        }
    }

    public final void F(byte[] bArr) {
        this.j = WatchfaceUtil.getShortFromByteArray(bArr, 2);
        Log.d("watchfaceService", "processWatchfaceEndRsp " + this.j);
        O("processWatchfaceEndRsp " + this.j);
        this.i = true;
        synchronized (this.n) {
            this.n.notifyAll();
        }
    }

    public final void G(byte[] bArr) {
        this.i = true;
        this.j = WatchfaceUtil.getShortFromByteArray(bArr, 2);
        this.k = WatchfaceUtil.getIntFromByteArray(bArr, 4);
        Log.i("watchfaceService", "receive lose check + " + this.j + ", index " + this.k);
        if (this.j == 8) {
            Log.e("watchfaceService", "remote abort");
            this.o = 7;
            synchronized (this.n) {
                this.n.notifyAll();
            }
            return;
        }
        P();
        synchronized (this.n) {
            this.n.notifyAll();
        }
    }

    public final void H(byte[] bArr) {
        this.j = WatchfaceUtil.getShortFromByteArray(bArr, 2);
        Log.d("watchfaceService", "processWatchfaceStartRsp " + this.j);
        O("processWatchfaceStartRsp: " + this.j);
        this.i = true;
        synchronized (this.n) {
            this.n.notifyAll();
        }
    }

    public final void I(byte[] bArr) {
        Log.d("watchfaceService", "processWatchfaceTotalEndRsp");
        this.i = true;
        this.j = WatchfaceUtil.getShortFromByteArray(bArr, 2);
        synchronized (this.n) {
            this.n.notifyAll();
        }
    }

    public final void J() {
        registerReceiver(this.y, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        this.q = true;
    }

    public final String K(byte[] bArr) {
        String str = getCacheDir() + "/zip";
        File file = new File(str);
        w(str, false);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str + "/wf.zip");
        Log.d("watchfaceService", "ss " + (str + "/wf.zip"));
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str2 = getCacheDir() + "/zip/wf.zip";
        if (new File(str2).exists()) {
            Log.d("watchfaceService", "save file success");
            return str2;
        }
        Log.d("watchfaceService", "not exits");
        return null;
    }

    public final void L() {
        this.s.clear();
        this.s.add("LLD");
        this.s.add("PRA");
        this.s.add("VCE");
        this.s.add(Region.REGION_MOROCCO);
        String substring = Build.MODEL.substring(0, 3);
        Log.e("watchfaceService", "model " + substring);
        if (this.s.contains(substring)) {
            Log.w("watchfaceService", "sendDfuBlankData");
            for (int i = 0; i < 5; i++) {
                this.h.serialTransSendBlank();
            }
        }
    }

    public final void M(byte[] bArr) {
        Log.d("watchfaceService", "sendWatchfaceDataExt");
        int length = bArr.length;
        Log.d("watchfaceService", "mMaxPartLen " + this.p);
        CurrentSendFile currentSendFile = new CurrentSendFile(bArr, this.p);
        int totalCount = currentSendFile.getTotalCount();
        int i = 0;
        int i2 = 0;
        while (i < totalCount) {
            int i3 = A;
            for (int i4 = 0; i4 < i; i4++) {
                i3 += currentSendFile.getData(i4).length;
            }
            if (this.u) {
                Log.d("watchfaceService", "mAlreadySendLen " + i3);
            }
            B = i3;
            int length2 = currentSendFile.getData(i).length;
            byte[] bArr2 = new byte[length2 + 2 + 2 + 4];
            WatchfaceUtil.addShortToByteArray(4, bArr2, 0);
            WatchfaceUtil.addShortToByteArray(length2 + 4, bArr2, 2);
            WatchfaceUtil.addIntToByteArray(i + 1, bArr2, 4);
            System.arraycopy(bArr, i2, bArr2, 8, length2);
            i2 += length2;
            this.h.serialTransSendProgress(bArr2, 4);
            this.i = false;
            synchronized (this.n) {
                while (!this.i && this.o == 0) {
                    try {
                        this.n.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            int i5 = this.j;
            if (i5 != 0) {
                if (i5 != 4 && i5 != 7) {
                    this.o = 2;
                    return;
                }
                Log.w("watchfaceService", "index error, continue with " + this.k);
                i2 -= length2;
                i = this.k - 2;
                this.o = 0;
            }
            i++;
        }
    }

    public final void N() {
        Log.d("watchfaceService", "sendWatchfaceEnd");
        byte[] bArr = new byte[4];
        O("sendWatchfaceEnd");
        WatchfaceUtil.addShortToByteArray(6, bArr, 0);
        WatchfaceUtil.addShortToByteArray(0, bArr, 2);
        this.h.serialTransSend(bArr, 4);
        this.i = false;
        this.t.postDelayed(this.x, 30000L);
        synchronized (this.n) {
            while (!this.i && this.o == 0) {
                try {
                    this.n.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.t.removeCallbacks(this.x);
    }

    public void O(String str) {
        Intent intent = new Intent(BROADCAST_WATCHFACE_LOG);
        intent.putExtra(EXTRA_WATCHFACE_LOG, str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public final void P() {
        Log.d("watchfaceService", "sendWatchfaceLoseCheckRsp");
        byte[] bArr = new byte[4];
        WatchfaceUtil.addShortToByteArray(11, bArr, 0);
        WatchfaceUtil.addShortToByteArray(0, bArr, 2);
        this.h.serialTransSend(bArr, 4);
    }

    public void Q(int i) {
        if (this.u) {
            Log.d("watchfaceService", "sendWatchfaceProgress " + i);
        }
        Intent intent = new Intent(BROADCAST_WATCHFACE_PROGRESS);
        intent.putExtra(EXTRA_WATCHFACE_PROGRESS, i);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void R(int i) {
        Log.e("watchfaceService", "send result broadcast " + i);
        Intent intent = new Intent(BROADCAST_WATCHFACE_STATE);
        intent.putExtra(EXTRA_WATCHFACE_STATE, i);
        intent.putExtra(EXTRA_WATCHFACE_STATE_RSP, this.j);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public final void S(int i, @NonNull String str) {
        int length = str.length();
        Log.d("watchfaceService", "sendWatchfaceStart " + i + ",name: " + str + ", " + length);
        int i2 = length + 10;
        byte[] bArr = new byte[i2];
        WatchfaceUtil.addShortToByteArray(2, bArr, 0);
        WatchfaceUtil.addShortToByteArray(i2 - 4, bArr, 2);
        WatchfaceUtil.addIntToByteArray(i, bArr, 4);
        WatchfaceUtil.addIntToByteArray(length, bArr, 8);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(bytes, 0, bArr, 10, bytes.length);
        O("sendWatchfaceStart len: " + i + ",name: " + str);
        this.h.serialTransSend(bArr, 4);
        this.i = false;
        this.t.postDelayed(this.x, 30000L);
        synchronized (this.n) {
            while (!this.i && this.o == 0) {
                try {
                    this.n.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.t.removeCallbacks(this.x);
    }

    public final void T() {
        Log.d("watchfaceService", "sendWatchfaceTotalEnd");
        byte[] bArr = new byte[4];
        WatchfaceUtil.addShortToByteArray(8, bArr, 0);
        WatchfaceUtil.addShortToByteArray(0, bArr, 2);
        this.h.serialTransSend(bArr, 4);
        this.i = false;
        this.t.postDelayed(this.x, 30000L);
        synchronized (this.n) {
            while (!this.i && this.o == 0) {
                try {
                    this.n.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.t.removeCallbacks(this.x);
    }

    public final void U(int i) {
        Log.d("watchfaceService", "sendWatchfaceTotalStart " + i);
        byte[] bArr = new byte[11];
        WatchfaceUtil.addShortToByteArray(0, bArr, 0);
        WatchfaceUtil.addShortToByteArray(7, bArr, 2);
        WatchfaceUtil.addShortToByteArray(i, bArr, 4);
        bArr[6] = 2;
        WatchfaceUtil.addIntToByteArray(C, bArr, 7);
        O("sendWatchfaceTotalStart type " + i + ", file len " + C);
        this.h.serialTransSend(bArr, 4);
        this.i = false;
        this.t.postDelayed(this.x, 30000L);
        synchronized (this.n) {
            while (!this.i && this.o == 0) {
                try {
                    this.n.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.t.removeCallbacks(this.x);
    }

    public final void W(String str) {
        Intent intent = new Intent(this, serialTransService.class);
        Log.d("watchfaceService", "startTransService " + str);
        intent.putExtra(serialTransService.EXTRA_DEVICE_ADDRESS, str);
        this.r = false;
        startService(intent);
        bindService(intent, this.v, 1);
        this.t.postDelayed(this.w, Constants.ONE_MIN_IN_MILLIS);
        synchronized (this.n) {
            while (!this.r && this.o == 0) {
                try {
                    this.n.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("watchfaceService", "start finish " + this.r + ", error " + this.o);
        this.t.removeCallbacks(this.w);
        if (this.r && this.o == 0) {
            this.t.postDelayed(this.w, Constants.ONE_MIN_IN_MILLIS);
            this.m = false;
            serialTransService.serialTransBinder serialtransbinder = this.h;
            if (serialtransbinder == null) {
                this.o = 1;
                return;
            }
            serialtransbinder.connectBluetooth();
            synchronized (this.n) {
                while (!this.m && this.o == 0) {
                    try {
                        this.n.wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.t.removeCallbacks(this.w);
        }
    }

    public final void X() {
        if (this.q) {
            this.q = false;
            unregisterReceiver(this.y);
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("watchfaceService", "version 1.2.6d");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        Log.e("watchfaceService", "onDestroy");
        super.onDestroy();
        this.o = TGErrorCode.ERROR_CHECK_CRC;
        synchronized (this.n) {
            this.n.notifyAll();
        }
        X();
        v();
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("com.sifli.watchfacelibrary.action.WATCHFACE".equals(action)) {
                Log.d("watchfaceService", "watchface");
                J();
                A(intent.getStringExtra("com.sifli.watchfacelibrary.extra.ADDRESS"), intent.getParcelableArrayListExtra("com.sifli.watchfacelibrary.extra.DATA"), intent.getIntExtra("com.sifli.watchfacelibrary.extra.TYPE", 0));
            } else if ("com.sifli.watchfacelibrary.action.WATCHFACE.MKII".equals(action)) {
                J();
                String stringExtra = intent.getStringExtra("com.sifli.watchfacelibrary.extra.ADDRESS");
                int intExtra = intent.getIntExtra("com.sifli.watchfacelibrary.extra.TYPE", 0);
                Uri uri = (Uri) intent.getParcelableExtra("com.sifli.watchfacelibrary.extra.URI");
                String stringExtra2 = intent.getStringExtra("com.sifli.watchfacelibrary.extra.PATH");
                byte[] byteArrayExtra = intent.getByteArrayExtra("com.sifli.watchfacelibrary.extra.DATA");
                int intExtra2 = intent.getIntExtra("com.sifli.watchfacelibrary.extra.ADD_ALIGN", 0);
                Log.i("watchfaceService", "type " + intExtra + ", addAlignAndCRC " + intExtra2);
                if (stringExtra2 != null) {
                    Log.d("watchfaceService", "get path");
                } else if (uri != null) {
                    Log.d("watchfaceService", "get uri");
                    stringExtra2 = getFilePathFromURI(this, uri);
                } else if (byteArrayExtra != null) {
                    Log.d("watchfaceService", "get zip data");
                    stringExtra2 = K(byteArrayExtra);
                } else {
                    Log.e("watchfaceService", "uri, path and zip data are null");
                    R(8);
                    return;
                }
                Log.d("watchfaceService", "zip path: " + stringExtra2);
                if (stringExtra2 == null) {
                    R(8);
                    return;
                }
                String str = getCacheDir() + "/wf";
                Log.d("watchfaceService", "new path: " + str);
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                w(str, false);
                unzipFolder(stringExtra2, str);
                ArrayList<WatchfaceFile> z2 = z(str, new ArrayList<>());
                if (z2 == null) {
                    R(8);
                    return;
                }
                if (intExtra == 3 || intExtra == 4) {
                    x(z2);
                } else if (intExtra2 == 1) {
                    y(z2, intExtra);
                }
                C = 0;
                Iterator<WatchfaceFile> it = z2.iterator();
                while (it.hasNext()) {
                    C += it.next().getFileData().length;
                }
                Collections.sort(z2, new WatchfaceFileComparator());
                Log.d("watchfaceService", "watchfaceFile size " + z2.size() + ", all files len " + C);
                if (C == 0) {
                    Log.e("watchfaceService", "file is null");
                    R(9);
                    return;
                }
                z = 0;
                A(stringExtra, z2, intExtra);
            }
        }
    }

    public final void u(ArrayList<WatchfaceFile> arrayList, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += arrayList.get(i3).getFileData().length;
        }
        A = i2;
    }

    public final void v() {
        if (this.r) {
            this.r = false;
            unbindService(this.v);
            stopService(new Intent(this, serialTransService.class));
        }
    }

    public final void w(String str, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                w(file2.getAbsolutePath(), true);
            }
        }
        if (z2) {
            if (!file.isDirectory()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            Objects.requireNonNull(listFiles);
            if (listFiles.length == 0) {
                file.delete();
            }
        }
    }

    public void waitFor(long j) {
        synchronized (this.n) {
            try {
                this.n.wait(j);
            } catch (InterruptedException unused) {
                Log.e("watchfaceService", "Sleeping interrupted");
            }
        }
    }

    public final void x(ArrayList<WatchfaceFile> arrayList) {
        Iterator<WatchfaceFile> it = arrayList.iterator();
        while (it.hasNext()) {
            WatchfaceFile next = it.next();
            next.makeAlignment();
            next.addCRC();
        }
    }

    public final void y(ArrayList<WatchfaceFile> arrayList, int i) {
        Iterator<WatchfaceFile> it = arrayList.iterator();
        while (it.hasNext()) {
            WatchfaceFile next = it.next();
            next.makeAlignment(i);
            next.addCRC();
        }
    }

    public final ArrayList<WatchfaceFile> z(String str, ArrayList<WatchfaceFile> arrayList) {
        File[] listFiles;
        String str2 = getCacheDir() + "/wf";
        File file = new File(str);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    String absolutePath = file2.getAbsolutePath();
                    String name = file2.getName();
                    byte[] C2 = C(absolutePath);
                    String substring = absolutePath.substring(str2.length());
                    arrayList.add(new WatchfaceFile(substring, C2, name));
                    Log.d("watchfaceService", "fileName:" + name);
                    Log.d("watchfaceService", "filePath:" + substring);
                    Log.d("watchfaceService", "data len:" + C2.length);
                } else if (file2.isDirectory()) {
                    z(file2.getAbsolutePath(), arrayList);
                }
            }
            return arrayList;
        }
        return null;
    }

    public static Intent startActionWatchface(Context context, Uri uri, String str, int i) {
        Intent intent = new Intent(context, SifliWatchfaceService.class);
        intent.setAction("com.sifli.watchfacelibrary.action.WATCHFACE.MKII");
        intent.putExtra("com.sifli.watchfacelibrary.extra.TYPE", i);
        intent.putExtra("com.sifli.watchfacelibrary.extra.URI", uri);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADDRESS", str);
        context.startService(intent);
        return intent;
    }

    public static void startActionWatchface(Context context, String str, String str2, int i) {
        Intent intent = new Intent(context, SifliWatchfaceService.class);
        intent.setAction("com.sifli.watchfacelibrary.action.WATCHFACE.MKII");
        intent.putExtra("com.sifli.watchfacelibrary.extra.TYPE", i);
        intent.putExtra("com.sifli.watchfacelibrary.extra.PATH", str);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADDRESS", str2);
        context.startService(intent);
    }

    public static void startActionWatchface(Context context, String str, String str2, int i, int i2) {
        Intent intent = new Intent(context, SifliWatchfaceService.class);
        intent.setAction("com.sifli.watchfacelibrary.action.WATCHFACE.MKII");
        intent.putExtra("com.sifli.watchfacelibrary.extra.TYPE", i);
        intent.putExtra("com.sifli.watchfacelibrary.extra.PATH", str);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADDRESS", str2);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADD_ALIGN", i2);
        context.startService(intent);
    }

    public static Intent startActionWatchface(Context context, Uri uri, String str, int i, int i2) {
        Intent intent = new Intent(context, SifliWatchfaceService.class);
        intent.setAction("com.sifli.watchfacelibrary.action.WATCHFACE.MKII");
        intent.putExtra("com.sifli.watchfacelibrary.extra.TYPE", i);
        intent.putExtra("com.sifli.watchfacelibrary.extra.URI", uri);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADDRESS", str);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADD_ALIGN", i2);
        context.startService(intent);
        return intent;
    }

    public static void startActionWatchface(Context context, byte[] bArr, String str, int i) {
        Intent intent = new Intent(context, SifliWatchfaceService.class);
        intent.setAction("com.sifli.watchfacelibrary.action.WATCHFACE.MKII");
        intent.putExtra("com.sifli.watchfacelibrary.extra.TYPE", i);
        intent.putExtra("com.sifli.watchfacelibrary.extra.DATA", bArr);
        intent.putExtra("com.sifli.watchfacelibrary.extra.ADDRESS", str);
        context.startService(intent);
    }
}
