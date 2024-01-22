package com.apex.bluetooth.core;

import a.e;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import com.apex.bluetooth.callback.DataResponseCallback;
import com.apex.bluetooth.callback.EABleCallback;
import com.apex.bluetooth.callback.MotionDataResponseCallback;
import com.apex.bluetooth.callback.OtaCallback;
import com.apex.bluetooth.core.a;
import com.apex.bluetooth.data_package.b.b;
import com.apex.bluetooth.enumeration.EABleConnectState;
import com.apex.bluetooth.model.EABleOta;
import com.apex.bluetooth.model.EABleOtaInfo;
import com.apex.bluetooth.model.EABleOtaRequest;
import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import com.clevertap.android.sdk.Constants;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class c implements com.apex.bluetooth.listener.a {
    public long D;
    public long E;
    public long F;
    public long G;
    public com.apex.bluetooth.core.n.a H;
    public com.apex.bluetooth.core.m.c I;
    public com.apex.bluetooth.core.m.a J;
    public DataResponseCallback K;
    public MotionDataResponseCallback L;
    public EABleCallback M;
    public OtaCallback N;
    public volatile boolean O;
    public volatile boolean P;
    public int Q;
    public String R;
    public String S;
    public HandlerThread T;
    public Handler U;
    public EABleOta V;
    public com.apex.bluetooth.listener.b X;
    public int Y;
    public int Z;
    public int a0;
    public BluetoothGatt b;
    public boolean b0;
    public volatile boolean c;
    public volatile boolean d;
    public volatile boolean e;
    public volatile boolean f;
    public com.apex.bluetooth.data_package.b.b g;
    public com.apex.bluetooth.data_package.b.b h;
    public com.apex.bluetooth.data_package.b.b i;
    public com.apex.bluetooth.data_package.b.b j;
    public volatile boolean o;

    /* renamed from: a  reason: collision with root package name */
    public final String f2172a = c.class.getSimpleName();
    public byte[] k = null;
    public byte[] l = null;
    public byte[] m = null;
    public byte[] n = null;
    public k p = new k();
    public ScheduledExecutorService y = Executors.newScheduledThreadPool(1);
    public ScheduledExecutorService B = Executors.newScheduledThreadPool(1);
    public ScheduledExecutorService z = Executors.newScheduledThreadPool(1);
    public ScheduledExecutorService A = Executors.newScheduledThreadPool(1);
    public ScheduledExecutorService C = Executors.newScheduledThreadPool(1);
    public ConcurrentLinkedQueue<byte[]> q = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<byte[]> r = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<byte[]> s = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<byte[]> t = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<l> u = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<l> v = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<l> w = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<l> x = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<EABleOta> W = new ConcurrentLinkedQueue<>();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            byte[] poll;
            ArrayList arrayList;
            try {
                ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue = c.this.q;
                if (concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty() || (poll = c.this.q.poll()) == null) {
                    return;
                }
                c cVar = c.this;
                byte[] bArr = cVar.m;
                if (bArr == null) {
                    cVar.m = poll;
                } else {
                    int length = bArr.length;
                    byte[] bArr2 = new byte[length];
                    System.arraycopy(bArr, 0, bArr2, 0, length);
                    c cVar2 = c.this;
                    byte[] bArr3 = new byte[poll.length + length];
                    cVar2.m = bArr3;
                    System.arraycopy(bArr2, 0, bArr3, 0, length);
                    System.arraycopy(poll, 0, c.this.m, length, poll.length);
                }
                com.apex.bluetooth.core_utils.a aVar = new com.apex.bluetooth.core_utils.a();
                byte[] bArr4 = c.this.m;
                if (bArr4 != null && bArr4.length >= 4) {
                    int i = bArr4[0] & 255;
                    int i2 = bArr4[bArr4.length - 1] & 255;
                    int i3 = (bArr4[1] & 255) | ((bArr4[2] & 255) << 8);
                    if (i == 234 && i2 == 239) {
                        if (i3 == bArr4.length - 4) {
                            arrayList = new ArrayList();
                            aVar.a(bArr4, 0, arrayList);
                        } else if (i3 <= bArr4.length - 4) {
                            arrayList = new ArrayList();
                            aVar.a(bArr4, 0, arrayList);
                        }
                        if (arrayList != null || arrayList.isEmpty()) {
                        }
                        for (int i4 = 0; i4 < arrayList.size(); i4++) {
                            if (aVar.a(arrayList.get(i4))) {
                                int length2 = arrayList.get(i4).length;
                                c cVar3 = c.this;
                                byte[] bArr5 = cVar3.m;
                                if (length2 != bArr5.length) {
                                    int length3 = bArr5.length - arrayList.get(i4).length;
                                    byte[] bArr6 = new byte[length3];
                                    System.arraycopy(c.this.m, 0, bArr6, 0, length3);
                                    c.this.m = bArr6;
                                } else {
                                    cVar3.m = null;
                                }
                                try {
                                    aVar.a(arrayList.get(i4), c.this.I);
                                } catch (InvalidProtocolBufferException e) {
                                    LogUtils.e(c.this.f2172a, "解析出错");
                                    LogData2File.getInstance().saveLogData("多运动数据解析出错:" + e.getMessage());
                                    c cVar4 = c.this;
                                    cVar4.m = null;
                                    com.apex.bluetooth.core.m.c cVar5 = cVar4.I;
                                    if (cVar5 != null) {
                                        cVar5.mutualFail(4);
                                        return;
                                    }
                                    return;
                                }
                            } else {
                                c.this.m = null;
                                return;
                            }
                        }
                        return;
                    }
                }
                arrayList = null;
                if (arrayList != null) {
                }
            } catch (Exception e2) {
                LogUtils.e(c.this.f2172a, "捕获异常数据:" + e2.getMessage());
                LogData2File.getInstance().saveLogData("解析运动数据通道捕获到异常:" + e2.getMessage());
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue = c.this.t;
                if (concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
                    return;
                }
                byte[] poll = c.this.t.poll();
                c cVar = c.this;
                byte[] bArr = cVar.n;
                if (bArr == null) {
                    cVar.n = poll;
                } else {
                    byte[] bArr2 = new byte[bArr.length + poll.length];
                    cVar.n = bArr2;
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    System.arraycopy(poll, 0, c.this.n, bArr.length, poll.length);
                }
                com.apex.bluetooth.core_utils.a aVar = new com.apex.bluetooth.core_utils.a();
                if (aVar.a(c.this.n)) {
                    try {
                        c cVar2 = c.this;
                        byte[] bArr3 = cVar2.n;
                        cVar2.n = null;
                        cVar2.d = false;
                        aVar.a(bArr3, c.this.X);
                        com.apex.bluetooth.data_package.b.b bVar = c.this.i;
                        if (bVar == null || bVar.f2207a) {
                            return;
                        }
                        c.this.i.f2207a = true;
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                        LogData2File logData2File = LogData2File.getInstance();
                        logData2File.saveLogData("OTA解析时出现错误:" + e.getMessage());
                        com.apex.bluetooth.data_package.b.b bVar2 = c.this.i;
                        if (bVar2 == null || bVar2.f2207a) {
                            return;
                        }
                        c.this.i.f2207a = true;
                        com.apex.bluetooth.listener.b bVar3 = c.this.X;
                        if (bVar3 != null) {
                            bVar3.mutualFail(4);
                        }
                    }
                }
            } catch (Exception e2) {
                String str = c.this.f2172a;
                LogUtils.e(str, "捕获异常数据:" + e2.getMessage());
                LogData2File logData2File2 = LogData2File.getInstance();
                logData2File2.saveLogData("解析OTA通道捕获到异常:" + e2.getMessage());
                c.this.d = false;
            }
        }
    }

    /* renamed from: com.apex.bluetooth.core.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0204c implements com.apex.bluetooth.listener.b {
        public C0204c() {
        }

        @Override // com.apex.bluetooth.listener.b
        public void a(EABleOtaInfo eABleOtaInfo) {
            c cVar = c.this;
            cVar.n = null;
            cVar.d = false;
            EABleOtaInfo.OtaStatus otaStatus = eABleOtaInfo.getOtaStatus();
            if (otaStatus != null) {
                if (otaStatus == EABleOtaInfo.OtaStatus.accept) {
                    int receive_bytes = eABleOtaInfo.getReceive_bytes();
                    c cVar2 = c.this;
                    OtaCallback otaCallback = cVar2.N;
                    if (otaCallback != null) {
                        otaCallback.progress(((cVar2.a0 + receive_bytes) * 100) / cVar2.Y);
                    }
                    if (c.this.V.getOtaType() != EABleOta.OtaType.res && c.this.V.getOtaType() != EABleOta.OtaType.user_wf) {
                        c.a(c.this, receive_bytes);
                    } else {
                        c.a(c.this, receive_bytes + 4);
                    }
                } else if (otaStatus == EABleOtaInfo.OtaStatus.complete) {
                    c cVar3 = c.this;
                    if (cVar3.N != null) {
                        if (cVar3.V.getOtaType() != EABleOta.OtaType.res && c.this.V.getOtaType() != EABleOta.OtaType.user_wf) {
                            c cVar4 = c.this;
                            OtaCallback otaCallback2 = cVar4.N;
                            int byteSize = cVar4.a0 + cVar4.V.getByteSize();
                            cVar4.a0 = byteSize;
                            otaCallback2.progress((byteSize * 100) / c.this.Y);
                        } else {
                            c cVar5 = c.this;
                            OtaCallback otaCallback3 = cVar5.N;
                            int byteSize2 = cVar5.a0 + (cVar5.V.getByteSize() - 4);
                            cVar5.a0 = byteSize2;
                            otaCallback3.progress((byteSize2 * 100) / c.this.Y);
                        }
                    }
                    c.this.d();
                } else if (otaStatus == EABleOtaInfo.OtaStatus.crc_error) {
                    OtaCallback otaCallback4 = c.this.N;
                    if (otaCallback4 != null) {
                        otaCallback4.mutualFail(19);
                    }
                    c cVar6 = c.this;
                    cVar6.N = null;
                    cVar6.W.clear();
                    c cVar7 = c.this;
                    cVar7.V = null;
                    cVar7.Y = 0;
                    cVar7.Z = 0;
                    cVar7.O = false;
                    c cVar8 = c.this;
                    cVar8.a0 = 0;
                    cVar8.X = null;
                } else if (otaStatus == EABleOtaInfo.OtaStatus.proceed) {
                    int receive_bytes2 = eABleOtaInfo.getReceive_bytes();
                    c cVar9 = c.this;
                    OtaCallback otaCallback5 = cVar9.N;
                    if (otaCallback5 != null) {
                        otaCallback5.progress(((cVar9.a0 + receive_bytes2) * 100) / cVar9.Y);
                    }
                    if (c.this.V.getOtaType() != EABleOta.OtaType.res && c.this.V.getOtaType() != EABleOta.OtaType.user_wf) {
                        c.a(c.this, receive_bytes2);
                    } else {
                        c.a(c.this, receive_bytes2 + 4);
                    }
                } else if (otaStatus == EABleOtaInfo.OtaStatus.reject) {
                    OtaCallback otaCallback6 = c.this.N;
                    if (otaCallback6 != null) {
                        otaCallback6.mutualFail(18);
                    }
                    c cVar10 = c.this;
                    cVar10.N = null;
                    cVar10.W.clear();
                    c cVar11 = c.this;
                    cVar11.V = null;
                    cVar11.Y = 0;
                    cVar11.O = false;
                    c cVar12 = c.this;
                    cVar12.Z = 0;
                    cVar12.a0 = 0;
                    cVar12.X = null;
                } else if (otaStatus == EABleOtaInfo.OtaStatus.reject_version_error) {
                    OtaCallback otaCallback7 = c.this.N;
                    if (otaCallback7 != null) {
                        otaCallback7.mutualFail(18);
                    }
                    c cVar13 = c.this;
                    cVar13.N = null;
                    cVar13.W.clear();
                    c cVar14 = c.this;
                    cVar14.V = null;
                    cVar14.Y = 0;
                    cVar14.O = false;
                    c cVar15 = c.this;
                    cVar15.Z = 0;
                    cVar15.a0 = 0;
                    cVar15.X = null;
                }
            }
        }

        @Override // com.apex.bluetooth.callback.EABleCallback
        public void mutualFail(int i) {
            c cVar = c.this;
            cVar.n = null;
            cVar.d = false;
            OtaCallback otaCallback = c.this.N;
            if (otaCallback != null) {
                otaCallback.mutualFail(i);
            }
            c cVar2 = c.this;
            cVar2.N = null;
            cVar2.W.clear();
            c cVar3 = c.this;
            cVar3.V = null;
            cVar3.Y = 0;
            cVar3.Z = 0;
            cVar3.a0 = 0;
            cVar3.O = false;
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.apex.bluetooth.data_package.b.b bVar;
            com.apex.bluetooth.data_package.b.b bVar2;
            com.apex.bluetooth.data_package.b.b bVar3;
            com.apex.bluetooth.data_package.b.b bVar4;
            com.apex.bluetooth.listener.b bVar5;
            MotionDataResponseCallback motionDataResponseCallback;
            if (c.this.P) {
                return;
            }
            c cVar = c.this;
            if (cVar.b0) {
                return;
            }
            cVar.P = true;
            LogUtils.e(c.this.f2172a, "蓝牙连接超时");
            k kVar = c.this.p;
            if (kVar != null) {
                kVar.a(EABleConnectState.STATE_DISCONNECT);
            }
            com.apex.bluetooth.data_package.b.b bVar6 = c.this.j;
            if (bVar6 != null && !bVar6.f2207a) {
                c.this.j.f2207a = true;
                EABleCallback eABleCallback = c.this.M;
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(6);
                }
            }
            com.apex.bluetooth.data_package.b.b bVar7 = c.this.g;
            if (bVar7 != null && !bVar7.f2207a) {
                c.this.g.f2207a = true;
                DataResponseCallback dataResponseCallback = c.this.K;
                if (dataResponseCallback != null) {
                    dataResponseCallback.mutualFail(6);
                }
            }
            com.apex.bluetooth.data_package.b.b bVar8 = c.this.h;
            if (bVar8 != null && !bVar8.f2207a && (motionDataResponseCallback = c.this.L) != null) {
                motionDataResponseCallback.mutualFail(6);
            }
            com.apex.bluetooth.data_package.b.b bVar9 = c.this.i;
            if (bVar9 != null && !bVar9.f2207a && (bVar5 = c.this.X) != null) {
                bVar5.mutualFail(6);
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue = c.this.u;
            if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
                Iterator<l> it = c.this.u.iterator();
                while (it.hasNext()) {
                    l next = it.next();
                    if (next != null && (bVar4 = next.b) != null && !bVar4.f2207a) {
                        next.b.f2207a = true;
                        EABleCallback eABleCallback2 = next.f2191a;
                        if (eABleCallback2 != null) {
                            eABleCallback2.mutualFail(6);
                        }
                    }
                }
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue2 = c.this.v;
            if (concurrentLinkedQueue2 != null && !concurrentLinkedQueue2.isEmpty()) {
                Iterator<l> it2 = c.this.v.iterator();
                while (it2.hasNext()) {
                    l next2 = it2.next();
                    if (next2 != null && (bVar3 = next2.b) != null && !bVar3.f2207a) {
                        next2.b.f2207a = true;
                        EABleCallback eABleCallback3 = next2.f2191a;
                        if (eABleCallback3 != null) {
                            eABleCallback3.mutualFail(6);
                        }
                    }
                }
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue3 = c.this.w;
            if (concurrentLinkedQueue3 != null && !concurrentLinkedQueue3.isEmpty()) {
                Iterator<l> it3 = c.this.w.iterator();
                while (it3.hasNext()) {
                    l next3 = it3.next();
                    if (next3 != null && (bVar2 = next3.b) != null && !bVar2.f2207a) {
                        next3.b.f2207a = true;
                        EABleCallback eABleCallback4 = next3.f2191a;
                        if (eABleCallback4 != null) {
                            eABleCallback4.mutualFail(6);
                        }
                    }
                }
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue4 = c.this.x;
            if (concurrentLinkedQueue4 != null && !concurrentLinkedQueue4.isEmpty()) {
                Iterator<l> it4 = c.this.x.iterator();
                while (it4.hasNext()) {
                    l next4 = it4.next();
                    if (next4 != null && (bVar = next4.b) != null && !bVar.f2207a) {
                        next4.b.f2207a = true;
                        EABleCallback eABleCallback5 = next4.f2191a;
                        if (eABleCallback5 != null) {
                            eABleCallback5.mutualFail(6);
                        }
                    }
                }
            }
            com.apex.bluetooth.core.n.a aVar = c.this.H;
            if (aVar != null) {
                ((a.C0203a) aVar).a(4);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.apex.bluetooth.data_package.b.b bVar;
            com.apex.bluetooth.data_package.b.b bVar2;
            com.apex.bluetooth.data_package.b.b bVar3;
            com.apex.bluetooth.data_package.b.b bVar4;
            com.apex.bluetooth.listener.b bVar5;
            MotionDataResponseCallback motionDataResponseCallback;
            com.apex.bluetooth.data_package.b.b bVar6 = c.this.j;
            if (bVar6 != null && !bVar6.f2207a) {
                c.this.j.f2207a = true;
                EABleCallback eABleCallback = c.this.M;
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(6);
                }
            }
            com.apex.bluetooth.data_package.b.b bVar7 = c.this.g;
            if (bVar7 != null && !bVar7.f2207a) {
                c.this.g.f2207a = true;
                DataResponseCallback dataResponseCallback = c.this.K;
                if (dataResponseCallback != null) {
                    dataResponseCallback.mutualFail(6);
                }
            }
            com.apex.bluetooth.data_package.b.b bVar8 = c.this.h;
            if (bVar8 != null && !bVar8.f2207a && (motionDataResponseCallback = c.this.L) != null) {
                motionDataResponseCallback.mutualFail(6);
            }
            com.apex.bluetooth.data_package.b.b bVar9 = c.this.i;
            if (bVar9 != null && !bVar9.f2207a && (bVar5 = c.this.X) != null) {
                bVar5.mutualFail(6);
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue = c.this.u;
            if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
                Iterator<l> it = c.this.u.iterator();
                while (it.hasNext()) {
                    l next = it.next();
                    if (next != null && (bVar4 = next.b) != null && !bVar4.f2207a) {
                        next.b.f2207a = true;
                        EABleCallback eABleCallback2 = next.f2191a;
                        if (eABleCallback2 != null) {
                            eABleCallback2.mutualFail(6);
                        }
                    }
                }
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue2 = c.this.v;
            if (concurrentLinkedQueue2 != null && !concurrentLinkedQueue2.isEmpty()) {
                Iterator<l> it2 = c.this.v.iterator();
                while (it2.hasNext()) {
                    l next2 = it2.next();
                    if (next2 != null && (bVar3 = next2.b) != null && !bVar3.f2207a) {
                        next2.b.f2207a = true;
                        EABleCallback eABleCallback3 = next2.f2191a;
                        if (eABleCallback3 != null) {
                            eABleCallback3.mutualFail(6);
                        }
                    }
                }
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue3 = c.this.w;
            if (concurrentLinkedQueue3 != null && !concurrentLinkedQueue3.isEmpty()) {
                Iterator<l> it3 = c.this.w.iterator();
                while (it3.hasNext()) {
                    l next3 = it3.next();
                    if (next3 != null && (bVar2 = next3.b) != null && !bVar2.f2207a) {
                        next3.b.f2207a = true;
                        EABleCallback eABleCallback4 = next3.f2191a;
                        if (eABleCallback4 != null) {
                            eABleCallback4.mutualFail(6);
                        }
                    }
                }
            }
            ConcurrentLinkedQueue<l> concurrentLinkedQueue4 = c.this.x;
            if (concurrentLinkedQueue4 != null && !concurrentLinkedQueue4.isEmpty()) {
                Iterator<l> it4 = c.this.x.iterator();
                while (it4.hasNext()) {
                    l next4 = it4.next();
                    if (next4 != null && (bVar = next4.b) != null && !bVar.f2207a) {
                        next4.b.f2207a = true;
                        EABleCallback eABleCallback5 = next4.f2191a;
                        if (eABleCallback5 != null) {
                            eABleCallback5.mutualFail(6);
                        }
                    }
                }
            }
            com.apex.bluetooth.core.n.a aVar = c.this.H;
            if (aVar != null) {
                ((a.C0203a) aVar).a(2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f2178a;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.apex.bluetooth.data_package.b.b bVar;
                com.apex.bluetooth.data_package.b.b bVar2;
                com.apex.bluetooth.data_package.b.b bVar3;
                com.apex.bluetooth.data_package.b.b bVar4;
                com.apex.bluetooth.data_package.b.b bVar5 = c.this.j;
                if (bVar5 != null && !bVar5.f2207a) {
                    c.this.j.f2207a = true;
                    EABleCallback eABleCallback = c.this.M;
                    if (eABleCallback != null) {
                        eABleCallback.mutualFail(7);
                    }
                }
                com.apex.bluetooth.data_package.b.b bVar6 = c.this.g;
                if (bVar6 != null && !bVar6.f2207a) {
                    c.this.g.f2207a = true;
                    DataResponseCallback dataResponseCallback = c.this.K;
                    if (dataResponseCallback != null) {
                        dataResponseCallback.mutualFail(7);
                    }
                }
                com.apex.bluetooth.data_package.b.b bVar7 = c.this.h;
                if (bVar7 != null && !bVar7.f2207a) {
                    c.this.h.f2207a = true;
                    MotionDataResponseCallback motionDataResponseCallback = c.this.L;
                    if (motionDataResponseCallback != null) {
                        motionDataResponseCallback.mutualFail(7);
                    }
                }
                com.apex.bluetooth.data_package.b.b bVar8 = c.this.i;
                if (bVar8 != null && !bVar8.f2207a) {
                    c.this.i.f2207a = true;
                    com.apex.bluetooth.listener.b bVar9 = c.this.X;
                    if (bVar9 != null) {
                        bVar9.mutualFail(7);
                    }
                }
                ConcurrentLinkedQueue<l> concurrentLinkedQueue = c.this.u;
                if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
                    Iterator<l> it = c.this.u.iterator();
                    while (it.hasNext()) {
                        l next = it.next();
                        if (next != null && (bVar4 = next.b) != null && !bVar4.f2207a) {
                            next.b.f2207a = true;
                            EABleCallback eABleCallback2 = next.f2191a;
                            if (eABleCallback2 != null) {
                                eABleCallback2.mutualFail(7);
                            }
                        }
                    }
                }
                ConcurrentLinkedQueue<l> concurrentLinkedQueue2 = c.this.v;
                if (concurrentLinkedQueue2 != null && !concurrentLinkedQueue2.isEmpty()) {
                    Iterator<l> it2 = c.this.v.iterator();
                    while (it2.hasNext()) {
                        l next2 = it2.next();
                        if (next2 != null && (bVar3 = next2.b) != null && !bVar3.f2207a) {
                            next2.b.f2207a = true;
                            EABleCallback eABleCallback3 = next2.f2191a;
                            if (eABleCallback3 != null) {
                                eABleCallback3.mutualFail(7);
                            }
                        }
                    }
                }
                ConcurrentLinkedQueue<l> concurrentLinkedQueue3 = c.this.w;
                if (concurrentLinkedQueue3 != null && !concurrentLinkedQueue3.isEmpty()) {
                    Iterator<l> it3 = c.this.w.iterator();
                    while (it3.hasNext()) {
                        l next3 = it3.next();
                        if (next3 != null && (bVar2 = next3.b) != null && !bVar2.f2207a) {
                            next3.b.f2207a = true;
                            EABleCallback eABleCallback4 = next3.f2191a;
                            if (eABleCallback4 != null) {
                                eABleCallback4.mutualFail(7);
                            }
                        }
                    }
                }
                ConcurrentLinkedQueue<l> concurrentLinkedQueue4 = c.this.x;
                if (concurrentLinkedQueue4 != null && !concurrentLinkedQueue4.isEmpty()) {
                    Iterator<l> it4 = c.this.x.iterator();
                    while (it4.hasNext()) {
                        l next4 = it4.next();
                        if (next4 != null && (bVar = next4.b) != null && !bVar.f2207a) {
                            next4.b.f2207a = true;
                            EABleCallback eABleCallback5 = next4.f2191a;
                            if (eABleCallback5 != null) {
                                eABleCallback5.mutualFail(7);
                            }
                        }
                    }
                }
                com.apex.bluetooth.core.n.a aVar = c.this.H;
                if (aVar != null) {
                    ((a.C0203a) aVar).a(2);
                }
            }
        }

        public f(Context context) {
            this.f2178a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothAdapter adapter;
            if (c.this.P) {
                return;
            }
            if (c.this.p == null || c.this.p.a() == EABleConnectState.STATE_CONNECTED) {
                try {
                    if (c.this.u == null || c.this.u.isEmpty()) {
                        if (c.this.c && System.currentTimeMillis() - c.this.D > 5000 && c.this.j != null && !((com.apex.bluetooth.data_package.a) c.this.j).c() && !c.this.j.b()) {
                            LogUtils.i(c.this.f2172a, "指令交互超时!!!");
                            c.this.j.a(true);
                            if (c.this.M != null) {
                                c.this.M.mutualFail(3);
                            }
                            c.this.c = false;
                            c.this.o = false;
                        }
                    } else if (!c.this.c) {
                        l lVar = (l) c.this.u.poll();
                        c.this.j = lVar.b();
                        c.this.M = lVar.a();
                        if (c.this.p.a() == EABleConnectState.STATE_CONNECTED && !c.this.P && c.this.j != null) {
                            c.this.c = true;
                            try {
                                c cVar = c.this;
                                cVar.a(cVar.j.a().poll(), "00008800-0000-1000-8000-00805f9b34fb", "00008801-0000-1000-8000-00805f9b34fb");
                                c.this.D = System.currentTimeMillis();
                                String str = c.this.f2172a;
                                LogUtils.i(str, "Channel 1 command sending time:" + c.this.D);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (System.currentTimeMillis() - c.this.D > 5000 && c.this.j != null && !((com.apex.bluetooth.data_package.a) c.this.j).c() && !c.this.j.b()) {
                        LogUtils.i(c.this.f2172a, "指令交互超时");
                        c.this.j.a(true);
                        if (c.this.M != null) {
                            c.this.M.mutualFail(3);
                        }
                        c.this.c = false;
                        c.this.o = false;
                    }
                } catch (Exception e2) {
                    c.this.c = false;
                    c.this.o = false;
                    String str2 = c.this.f2172a;
                    LogUtils.e(str2, "发送数据时捕获到异常:" + e2.getMessage());
                    LogData2File logData2File = LogData2File.getInstance();
                    logData2File.saveLogData("指令通道捕获到异常:" + e2.getMessage());
                }
                try {
                    if (c.this.v == null || c.this.v.isEmpty()) {
                        if (c.this.e && System.currentTimeMillis() - c.this.G > 5000 && c.this.g != null && !c.this.g.b()) {
                            LogUtils.i(c.this.f2172a, "回应超时!!!");
                            c.this.g.a(true);
                            if (c.this.K != null) {
                                c.this.K.mutualFail(3);
                            }
                            c.this.g = null;
                            c.this.e = false;
                        }
                    } else if (!c.this.e) {
                        l lVar2 = (l) c.this.v.poll();
                        c.this.g = lVar2.b();
                        c.this.K = (DataResponseCallback) lVar2.a();
                        if (c.this.p.a() == EABleConnectState.STATE_CONNECTED && !c.this.P && c.this.g != null) {
                            c.this.e = true;
                            try {
                                c cVar2 = c.this;
                                cVar2.a(cVar2.g.a().poll(), "00008800-0000-1000-8000-00805f9b34fb", "00008802-0000-1000-8000-00805f9b34fb");
                                c.this.G = System.currentTimeMillis();
                                String str3 = c.this.f2172a;
                                LogUtils.i(str3, "Channel 2 command sending time:" + c.this.G);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    } else if (System.currentTimeMillis() - c.this.G > 5000 && c.this.g != null && !c.this.g.b()) {
                        LogUtils.i(c.this.f2172a, "回应超时");
                        c.this.g.a(true);
                        if (c.this.K != null) {
                            c.this.K.mutualFail(3);
                        }
                        c.this.g = null;
                        c.this.e = false;
                    }
                } catch (Exception e4) {
                    c.this.g = null;
                    c.this.e = false;
                    String str4 = c.this.f2172a;
                    LogUtils.e(str4, "发送数据时捕获到异常:" + e4.getMessage());
                    LogData2File logData2File2 = LogData2File.getInstance();
                    logData2File2.saveLogData("主动上报通道捕获异常:" + e4.getMessage());
                }
                try {
                    if (c.this.w == null || c.this.w.isEmpty()) {
                        if (c.this.f && System.currentTimeMillis() - c.this.E > Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS) {
                            if (c.this.h != null && !c.this.h.b()) {
                                LogUtils.i(c.this.f2172a, "大数据回应超时!!!");
                                c.this.h.a(true);
                                if (c.this.L != null) {
                                    c.this.L.mutualFail(3);
                                }
                            }
                            c.this.h = null;
                            c.this.f = false;
                        }
                    } else if (!c.this.f) {
                        l lVar3 = (l) c.this.w.poll();
                        c.this.h = lVar3.b();
                        c.this.L = (MotionDataResponseCallback) lVar3.a();
                        if (c.this.p.a() == EABleConnectState.STATE_CONNECTED && !c.this.P && c.this.h != null) {
                            c.this.f = true;
                            try {
                                c cVar3 = c.this;
                                cVar3.a(cVar3.h.a().poll(), "00008800-0000-1000-8000-00805f9b34fb", "00008803-0000-1000-8000-00805f9b34fb");
                                c.this.E = System.currentTimeMillis();
                                String str5 = c.this.f2172a;
                                LogUtils.i(str5, "Channel 3 command sending time:" + c.this.E);
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                    } else if (System.currentTimeMillis() - c.this.E > Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS) {
                        if (c.this.h != null && !c.this.h.b()) {
                            LogUtils.i(c.this.f2172a, "大数据回应超时");
                            c.this.h.a(true);
                            if (c.this.L != null) {
                                c.this.L.mutualFail(3);
                            }
                        }
                        c.this.h = null;
                        c.this.f = false;
                    }
                } catch (Exception e6) {
                    c.this.h = null;
                    c.this.f = false;
                    String str6 = c.this.f2172a;
                    LogUtils.e(str6, "发送数据时捕获到异常:" + e6.getMessage());
                    LogData2File logData2File3 = LogData2File.getInstance();
                    logData2File3.saveLogData("运动数据通道捕获到异常:" + e6.getMessage());
                }
                try {
                } catch (Exception e7) {
                    c.this.i = null;
                    c.this.d = false;
                    c.this.O = false;
                    String str7 = c.this.f2172a;
                    LogUtils.e(str7, "发送数据时捕获异常:" + e7.getMessage());
                    LogData2File logData2File4 = LogData2File.getInstance();
                    logData2File4.saveLogData("OTA数据通道捕获到异常:" + e7.getMessage());
                }
                if (c.this.x == null || c.this.x.isEmpty()) {
                    if (c.this.d && System.currentTimeMillis() - c.this.F > 10000) {
                        c.this.O = false;
                        if (c.this.i != null && !c.this.i.b()) {
                            LogUtils.i(c.this.f2172a, "OTA指令交互超时!!!");
                            c.this.i.a(true);
                            if (c.this.X != null) {
                                c.this.X.mutualFail(3);
                            }
                        }
                        c.this.i = null;
                        c.this.d = false;
                    }
                } else if (!c.this.d) {
                    l lVar4 = (l) c.this.x.poll();
                    c.this.i = lVar4.b();
                    c.this.X = (com.apex.bluetooth.listener.b) lVar4.a();
                    if (c.this.p.a() == EABleConnectState.STATE_CONNECTED && !c.this.P && c.this.i != null) {
                        c.this.d = true;
                        if (c.this.i.b == b.a.east_apex_04) {
                            try {
                                c cVar4 = c.this;
                                cVar4.a(cVar4.i.a().poll(), "00009900-0000-1000-8000-00805f9b34fb", "00009901-0000-1000-8000-00805f9b34fb");
                                c.this.F = System.currentTimeMillis();
                            } catch (Exception e8) {
                                e8.printStackTrace();
                            }
                        } else {
                            try {
                                c cVar5 = c.this;
                                cVar5.a(cVar5.i.a().poll(), "00009900-0000-1000-8000-00805f9b34fb", "00009902-0000-1000-8000-00805f9b34fb");
                                c.this.F = System.currentTimeMillis();
                            } catch (Exception e9) {
                                e9.printStackTrace();
                            }
                        }
                        c.this.i = null;
                        c.this.d = false;
                        c.this.O = false;
                        String str72 = c.this.f2172a;
                        LogUtils.e(str72, "发送数据时捕获异常:" + e7.getMessage());
                        LogData2File logData2File42 = LogData2File.getInstance();
                        logData2File42.saveLogData("OTA数据通道捕获到异常:" + e7.getMessage());
                    }
                } else if (System.currentTimeMillis() - c.this.F > 10000) {
                    c.this.O = false;
                    if (c.this.i != null && !c.this.i.b()) {
                        LogUtils.i(c.this.f2172a, "OTA指令交互超时");
                        c.this.i.a(true);
                        if (c.this.X != null) {
                            c.this.X.mutualFail(3);
                        }
                    }
                    c.this.i = null;
                    c.this.d = false;
                }
                BluetoothManager bluetoothManager = (BluetoothManager) this.f2178a.getSystemService("bluetooth");
                if (bluetoothManager == null || (adapter = bluetoothManager.getAdapter()) == null || adapter.isEnabled()) {
                    return;
                }
                if (c.this.p != null) {
                    c.this.p.a(EABleConnectState.STATE_DISCONNECT);
                }
                if (c.this.P) {
                    return;
                }
                c.this.P = true;
                if (c.this.U != null) {
                    c.this.U.removeCallbacksAndMessages("disconnect_eable");
                    HandlerCompat.postDelayed(c.this.U, new a(), "disconnect_eable", 1000L);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {
        public g() {
        }

        /* JADX WARN: Removed duplicated region for block: B:46:0x010f A[Catch: Exception -> 0x0114, TRY_LEAVE, TryCatch #1 {Exception -> 0x0114, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000d, B:9:0x0013, B:11:0x0028, B:13:0x003f, B:15:0x0056, B:29:0x008c, B:31:0x009e, B:33:0x00a2, B:34:0x00a8, B:46:0x010f, B:37:0x00d0, B:39:0x00f2, B:40:0x00f5, B:42:0x00fb, B:44:0x00ff, B:45:0x0105, B:18:0x005a, B:22:0x0078, B:25:0x007e, B:26:0x0084, B:12:0x002b), top: B:53:0x0001, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 343
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apex.bluetooth.core.c.g.run():void");
        }
    }

    /* loaded from: classes.dex */
    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue = c.this.s;
                if (concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
                    return;
                }
                byte[] poll = c.this.s.poll();
                c cVar = c.this;
                byte[] bArr = cVar.l;
                if (bArr == null) {
                    cVar.l = poll;
                } else {
                    byte[] bArr2 = new byte[bArr.length + poll.length];
                    cVar.l = bArr2;
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    System.arraycopy(poll, 0, c.this.l, bArr.length, poll.length);
                }
                com.apex.bluetooth.core_utils.a aVar = new com.apex.bluetooth.core_utils.a();
                if (aVar.a(c.this.l)) {
                    try {
                        c cVar2 = c.this;
                        byte[] bArr3 = cVar2.l;
                        cVar2.l = null;
                        aVar.a(bArr3, cVar2.J);
                    } catch (InvalidProtocolBufferException e) {
                        LogData2File logData2File = LogData2File.getInstance();
                        logData2File.saveLogData("主动上报解析错误:" + e.getMessage());
                        com.apex.bluetooth.core.m.a aVar2 = c.this.J;
                        if (aVar2 != null) {
                            aVar2.mutualFail(4);
                        }
                    }
                }
            } catch (Exception e2) {
                String str = c.this.f2172a;
                LogUtils.e(str, "捕获到异常数据:" + e2.getMessage());
                LogData2File logData2File2 = LogData2File.getInstance();
                logData2File2.saveLogData("解析主动上报通道捕获到异常:" + e2.getMessage());
            }
        }
    }

    public c(@NonNull Context context, @NonNull com.apex.bluetooth.core.n.a aVar, int i, com.apex.bluetooth.core.m.a aVar2, com.apex.bluetooth.core.m.c cVar) {
        this.H = aVar;
        this.J = aVar2;
        this.I = cVar;
        HandlerThread handlerThread = new HandlerThread("EABLE_CONNECT_IMPL");
        this.T = handlerThread;
        handlerThread.start();
        this.U = new Handler(this.T.getLooper());
        this.Q = i;
        a(context);
        a();
        c();
    }

    public static void h(c cVar) {
        Handler handler = cVar.U;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            cVar.U = null;
        }
        HandlerThread handlerThread = cVar.T;
        if (handlerThread != null) {
            handlerThread.quit();
            cVar.T = null;
        }
        BluetoothGatt bluetoothGatt = cVar.b;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
            cVar.b();
            cVar.b.close();
            cVar.b = null;
        }
        cVar.O = false;
        cVar.p.a(EABleConnectState.STATE_DISCONNECT);
        ScheduledExecutorService scheduledExecutorService = cVar.y;
        if (scheduledExecutorService != null) {
            if (!scheduledExecutorService.isShutdown()) {
                cVar.y.shutdownNow();
            }
            cVar.y = null;
        }
        ScheduledExecutorService scheduledExecutorService2 = cVar.z;
        if (scheduledExecutorService2 != null) {
            if (!scheduledExecutorService2.isShutdown()) {
                cVar.z.shutdownNow();
            }
            cVar.z = null;
        }
        ScheduledExecutorService scheduledExecutorService3 = cVar.A;
        if (scheduledExecutorService3 != null) {
            if (!scheduledExecutorService3.isShutdown()) {
                cVar.A.shutdownNow();
            }
            cVar.A = null;
        }
        ScheduledExecutorService scheduledExecutorService4 = cVar.B;
        if (scheduledExecutorService4 != null) {
            if (!scheduledExecutorService4.isShutdown()) {
                cVar.B.shutdownNow();
            }
            cVar.B = null;
        }
        ScheduledExecutorService scheduledExecutorService5 = cVar.C;
        if (scheduledExecutorService5 != null) {
            if (!scheduledExecutorService5.isShutdown()) {
                cVar.C.shutdownNow();
            }
            cVar.C = null;
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue = cVar.u;
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.clear();
            cVar.u = null;
        }
        cVar.V = null;
        ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue2 = cVar.q;
        if (concurrentLinkedQueue2 != null) {
            concurrentLinkedQueue2.clear();
            cVar.q = null;
        }
        ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue3 = cVar.t;
        if (concurrentLinkedQueue3 != null) {
            concurrentLinkedQueue3.clear();
            cVar.t = null;
        }
        ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue4 = cVar.s;
        if (concurrentLinkedQueue4 != null) {
            concurrentLinkedQueue4.clear();
            cVar.s = null;
        }
        ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue5 = cVar.r;
        if (concurrentLinkedQueue5 != null) {
            concurrentLinkedQueue5.clear();
            cVar.r = null;
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue6 = cVar.v;
        if (concurrentLinkedQueue6 != null) {
            concurrentLinkedQueue6.clear();
            cVar.v = null;
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue7 = cVar.w;
        if (concurrentLinkedQueue7 != null) {
            concurrentLinkedQueue7.clear();
            cVar.w = null;
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue8 = cVar.x;
        if (concurrentLinkedQueue8 != null) {
            concurrentLinkedQueue8.clear();
            cVar.x = null;
        }
        ConcurrentLinkedQueue<EABleOta> concurrentLinkedQueue9 = cVar.W;
        if (concurrentLinkedQueue9 != null) {
            concurrentLinkedQueue9.clear();
            cVar.W = null;
        }
        cVar.j = null;
        cVar.g = null;
        cVar.h = null;
        cVar.i = null;
        cVar.k = null;
        cVar.l = null;
        cVar.m = null;
        cVar.n = null;
    }

    public final synchronized void b() {
        BluetoothGatt bluetoothGatt;
        try {
            Method method = BluetoothGatt.class.getMethod("refresh", new Class[0]);
            if (method != null && (bluetoothGatt = this.b) != null) {
                ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    public final void c() {
        HandlerCompat.postDelayed(this.U, new d(), "disconnect_eable", 10000L);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008f A[Catch: Exception -> 0x0334, TryCatch #2 {Exception -> 0x0334, blocks: (B:11:0x0058, B:13:0x0072, B:15:0x0078, B:16:0x007c, B:24:0x008f, B:26:0x0095, B:27:0x00de, B:29:0x00e2, B:30:0x0120, B:32:0x0124, B:33:0x016d, B:35:0x0171, B:36:0x01ba, B:38:0x01c0, B:39:0x0226, B:41:0x022a, B:42:0x0273, B:44:0x0277, B:45:0x02c0, B:47:0x02c4, B:48:0x031e, B:50:0x0322, B:51:0x0325, B:18:0x0081, B:21:0x0089), top: B:65:0x0058, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x031e A[Catch: Exception -> 0x0334, TryCatch #2 {Exception -> 0x0334, blocks: (B:11:0x0058, B:13:0x0072, B:15:0x0078, B:16:0x007c, B:24:0x008f, B:26:0x0095, B:27:0x00de, B:29:0x00e2, B:30:0x0120, B:32:0x0124, B:33:0x016d, B:35:0x0171, B:36:0x01ba, B:38:0x01c0, B:39:0x0226, B:41:0x022a, B:42:0x0273, B:44:0x0277, B:45:0x02c0, B:47:0x02c4, B:48:0x031e, B:50:0x0322, B:51:0x0325, B:18:0x0081, B:21:0x0089), top: B:65:0x0058, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d() {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apex.bluetooth.core.c.d():void");
    }

    public static void a(c cVar, int i) {
        int i2;
        byte[] bArr;
        Objects.requireNonNull(cVar);
        new com.apex.bluetooth.data_package.c.a();
        byte[] fileBytes = cVar.V.getFileBytes();
        int length = fileBytes.length < i + 2048 ? fileBytes.length - i : 2048;
        byte[] bArr2 = new byte[length];
        System.arraycopy(fileBytes, i, bArr2, 0, length);
        int i3 = i.f2187a;
        if (length <= i3) {
            i2 = 1;
        } else if (length % i3 == 0) {
            i2 = length / i3;
        } else {
            i2 = (length / i3) + 1;
        }
        com.apex.bluetooth.data_package.a aVar = new com.apex.bluetooth.data_package.a(i2);
        aVar.b = b.a.east_apex_05;
        if (i2 == 1) {
            aVar.a(bArr2);
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = i2 - 1;
                if (i4 < i5) {
                    bArr = new byte[i.f2187a];
                } else {
                    bArr = new byte[length - (i5 * i.f2187a)];
                }
                System.arraycopy(bArr2, i.f2187a * i4, bArr, 0, bArr.length);
                aVar.a(bArr);
            }
        }
        com.apex.bluetooth.core.d dVar = new com.apex.bluetooth.core.d(cVar);
        com.apex.bluetooth.data_package.b.a<byte[]> aVar2 = aVar.c;
        if (aVar2 != null && !aVar2.isEmpty()) {
            if (cVar.P) {
                k kVar = cVar.p;
                if (kVar != null) {
                    kVar.a(EABleConnectState.STATE_DISCONNECT);
                }
                dVar.mutualFail(6);
                return;
            }
            l lVar = new l();
            lVar.b = aVar;
            lVar.f2191a = dVar;
            ConcurrentLinkedQueue<l> concurrentLinkedQueue = cVar.x;
            if (concurrentLinkedQueue != null) {
                try {
                    concurrentLinkedQueue.add(lVar);
                    return;
                } catch (IllegalStateException unused) {
                    dVar.mutualFail(8);
                    return;
                } catch (NullPointerException unused2) {
                    dVar.mutualFail(8);
                    return;
                }
            }
            return;
        }
        dVar.mutualFail(16);
    }

    public final void a(@NonNull EABleOtaRequest eABleOtaRequest) {
        byte[] bArr;
        byte[] bArr2;
        com.apex.bluetooth.data_package.b.a<byte[]> aVar;
        int length;
        byte[] bArr3;
        com.apex.bluetooth.data_package.c.a aVar2 = new com.apex.bluetooth.data_package.c.a();
        EABleOtaRequest.OtaRequestType e_type = eABleOtaRequest.getE_type();
        String version = eABleOtaRequest.getVersion();
        int total_size = eABleOtaRequest.getTotal_size();
        int current_size = eABleOtaRequest.getCurrent_size();
        int crc = eABleOtaRequest.getCrc();
        int res_addr = eABleOtaRequest.getRes_addr();
        int wait_bytes = eABleOtaRequest.getWait_bytes();
        int pop_up_interface = eABleOtaRequest.getPop_up_interface();
        int is_test_mode = eABleOtaRequest.getIs_test_mode();
        e.a.b builder = e.a.k.toBuilder();
        if (e_type != null) {
            builder.c(e_type.getValue());
        }
        if (!TextUtils.isEmpty(version)) {
            byte[] bytes = version.getBytes(Charset.forName("UTF-8"));
            if (bytes != null && bytes.length > 32) {
                version = new String(bytes, 0, 32, Charset.forName("UTF-8"));
            }
            builder.a(version);
        }
        builder.g(total_size);
        builder.b(current_size);
        builder.a(crc);
        builder.f(res_addr);
        builder.h(wait_bytes);
        builder.e(pop_up_interface);
        builder.d(is_test_mode);
        byte[] a2 = aVar2.a(builder.build().toByteArray(), 9001);
        com.apex.bluetooth.data_package.a aVar3 = null;
        if (a2 != null) {
            int length2 = a2.length;
            bArr = new byte[length2 + 2];
            bArr[0] = (byte) (length2 & 255);
            bArr[1] = (byte) ((length2 >> 8) & 255);
            System.arraycopy(a2, 0, bArr, 2, length2);
        } else {
            bArr = null;
        }
        if (bArr != null) {
            int length3 = bArr.length + 2;
            bArr2 = new byte[length3];
            bArr2[0] = com.crrepa.c.a.A;
            bArr2[length3 - 1] = -17;
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        } else {
            bArr2 = null;
        }
        b.a aVar4 = b.a.east_apex_04;
        if (bArr2 != null) {
            int length4 = bArr2.length;
            int i = i.f2187a;
            if (length4 <= i) {
                length = 1;
            } else if (bArr2.length % i == 0) {
                length = bArr2.length / i;
            } else {
                length = (bArr2.length / i) + 1;
            }
            com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
            aVar5.b = aVar4;
            if (length == 1) {
                aVar5.a(bArr2);
            } else {
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = length - 1;
                    if (i2 < i3) {
                        bArr3 = new byte[i.f2187a];
                    } else {
                        bArr3 = new byte[bArr2.length - (i3 * i.f2187a)];
                    }
                    System.arraycopy(bArr2, i.f2187a * i2, bArr3, 0, bArr3.length);
                    aVar5.a(bArr3);
                }
            }
            aVar3 = aVar5;
        }
        C0204c c0204c = new C0204c();
        if (aVar3 != null && (aVar = aVar3.c) != null && !aVar.isEmpty()) {
            if (this.P) {
                k kVar = this.p;
                if (kVar != null) {
                    kVar.a(EABleConnectState.STATE_DISCONNECT);
                }
                c0204c.mutualFail(6);
                return;
            }
            l lVar = new l();
            lVar.b = aVar3;
            lVar.f2191a = c0204c;
            ConcurrentLinkedQueue<l> concurrentLinkedQueue = this.x;
            if (concurrentLinkedQueue != null) {
                try {
                    concurrentLinkedQueue.add(lVar);
                    return;
                } catch (IllegalStateException unused) {
                    c0204c.mutualFail(8);
                    return;
                } catch (NullPointerException unused2) {
                    c0204c.mutualFail(8);
                    return;
                }
            }
            return;
        }
        c0204c.mutualFail(16);
    }

    public final void a(byte[] bArr, String str, String str2) {
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        if (bArr != null) {
            if (this.b != null && !this.P && (service = this.b.getService(UUID.fromString(str))) != null && (characteristic = service.getCharacteristic(UUID.fromString(str2))) != null) {
                characteristic.setValue(bArr);
                this.b.writeCharacteristic(characteristic);
                return;
            }
            com.apex.bluetooth.core.m.a aVar = this.J;
            if (aVar != null) {
                aVar.c = true;
            }
            com.apex.bluetooth.core.m.c cVar = this.I;
            if (cVar != null) {
                cVar.f2194a = true;
            }
            k kVar = this.p;
            if (kVar != null) {
                kVar.a(EABleConnectState.STATE_DISCONNECT);
            }
            Log.e(this.f2172a, "数据发送时,蓝牙或者数据出现问题");
            if (this.P) {
                return;
            }
            this.P = true;
            Handler handler = this.U;
            if (handler != null) {
                handler.removeCallbacksAndMessages("disconnect_eable");
                HandlerCompat.postDelayed(this.U, new e(), "disconnect_eable", 1000L);
            }
        }
    }

    public final void a(Context context) {
        this.y.scheduleWithFixedDelay(new f(context), 0L, 10L, TimeUnit.MILLISECONDS);
    }

    public final void a() {
        ScheduledExecutorService scheduledExecutorService = this.z;
        g gVar = new g();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        scheduledExecutorService.scheduleWithFixedDelay(gVar, 0L, 10L, timeUnit);
        this.A.scheduleWithFixedDelay(new h(), 0L, 10L, timeUnit);
        this.B.scheduleWithFixedDelay(new a(), 0L, 10L, timeUnit);
        this.C.scheduleWithFixedDelay(new b(), 0L, 10L, timeUnit);
    }

    public void a(@NonNull List<EABleOta> list, OtaCallback otaCallback) {
        if (this.P) {
            otaCallback.mutualFail(6);
        } else if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getOtaType() == null || TextUtils.isEmpty(list.get(i).getFilePath()) || !new File(list.get(i).getFilePath()).exists() || list.get(i).getByteSize() <= 0) {
                    otaCallback.mutualFail(10);
                    return;
                }
            }
            if (this.V == null && !this.O) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    try {
                        this.W.add(list.get(i2));
                    } catch (IllegalStateException unused) {
                        this.W.clear();
                        if (otaCallback != null) {
                            otaCallback.mutualFail(8);
                            return;
                        }
                        return;
                    } catch (NullPointerException unused2) {
                        this.W.clear();
                        if (otaCallback != null) {
                            otaCallback.mutualFail(8);
                            return;
                        }
                        return;
                    }
                }
                this.O = true;
                this.N = otaCallback;
                this.Z = 0;
                this.a0 = 0;
                this.V = null;
                this.X = null;
                this.Y = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    if (list.get(i3).getOtaType() == EABleOta.OtaType.res || list.get(i3).getOtaType() == EABleOta.OtaType.user_wf) {
                        this.Y += list.get(i3).getByteSize() - 4;
                    } else {
                        this.Y += list.get(i3).getByteSize();
                    }
                }
                d();
                return;
            }
            otaCallback.mutualFail(17);
        } else {
            otaCallback.mutualFail(16);
        }
    }

    public final void a(boolean z, String str, String str2) {
        BluetoothGattService service;
        if (this.b == null || this.P || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (service = this.b.getService(UUID.fromString(str))) == null || this.b == null || this.P) {
            return;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(str2));
        this.b.setCharacteristicNotification(characteristic, z);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        if (str.equalsIgnoreCase("00008800-0000-1000-8000-00805f9b34fb")) {
            if (str2.equalsIgnoreCase("00008801-0000-1000-8000-00805f9b34fb")) {
                this.R = "00008800-0000-1000-8000-00805f9b34fb";
                this.S = "00008802-0000-1000-8000-00805f9b34fb";
            } else if (str2.equalsIgnoreCase("00008802-0000-1000-8000-00805f9b34fb")) {
                this.R = "00008800-0000-1000-8000-00805f9b34fb";
                this.S = "00008803-0000-1000-8000-00805f9b34fb";
            } else if (str2.equalsIgnoreCase("00008803-0000-1000-8000-00805f9b34fb")) {
                this.R = "00009900-0000-1000-8000-00805f9b34fb";
                this.S = "00009901-0000-1000-8000-00805f9b34fb";
            }
        } else if (str2.equalsIgnoreCase("00009901-0000-1000-8000-00805f9b34fb")) {
            this.R = "00009900-0000-1000-8000-00805f9b34fb";
            this.S = "00009902-0000-1000-8000-00805f9b34fb";
        } else {
            this.R = null;
            this.S = null;
        }
        if (descriptor == null || (characteristic.getProperties() & 16) == 0) {
            return;
        }
        descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        if (this.b.writeDescriptor(descriptor)) {
            String str3 = this.f2172a;
            LogUtils.i(str3, characteristic.getUuid().toString() + " Channel opened successfully");
            return;
        }
        String str4 = this.f2172a;
        LogUtils.i(str4, characteristic.getUuid().toString() + "Channel opening failed");
        LogData2File logData2File = LogData2File.getInstance();
        logData2File.saveLogData(characteristic.getUuid().toString() + "Channel opening failed");
        a(z, str, str2);
    }
}
