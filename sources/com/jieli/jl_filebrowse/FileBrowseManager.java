package com.jieli.jl_filebrowse;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jieli.jl_filebrowse.bean.File;
import com.jieli.jl_filebrowse.bean.FileStruct;
import com.jieli.jl_filebrowse.bean.Folder;
import com.jieli.jl_filebrowse.bean.PathData;
import com.jieli.jl_filebrowse.bean.RegFile;
import com.jieli.jl_filebrowse.bean.SDCardBean;
import com.jieli.jl_filebrowse.interfaces.DeleteCallback;
import com.jieli.jl_filebrowse.interfaces.FileBrowseOperator;
import com.jieli.jl_filebrowse.interfaces.FileObserver;
import com.jieli.jl_filebrowse.interfaces.LrcDecoder;
import com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback;
import com.jieli.jl_filebrowse.interfaces.OperatCallback;
import com.jieli.jl_filebrowse.interfaces.SimpleFileObserver;
import com.jieli.jl_filebrowse.interfaces.lrc.LrcReadObserver;
import com.jieli.jl_filebrowse.interfaces.lrc.LrcReadOperator;
import com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback;
import com.jieli.jl_filebrowse.tool.FileObserverHelper;
import com.jieli.jl_filebrowse.tool.LrcReadObserverHelper;
import com.jieli.jl_rcsp.impl.OnFileBrowseCallbackImpl;
import com.jieli.jl_rcsp.impl.RcspOpImpl;
import com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback;
import com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl;
import com.jieli.jl_rcsp.tool.filebrowse.LrcReadOperatorImpl;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import com.jieli.jl_rcsp.util.RcspUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes11.dex */
public class FileBrowseManager implements OnFileBrowseCallback, OnLrcCallback {
    private static String A = null;
    public static final int ERR_LRC_FILE_SAVE = 16;
    public static final int ERR_LRC_START_READ = 17;
    public static final int ERR_NOT_LRC_FILE = 9;
    private static final String w = "FileBrowseManager";
    private static final int x = 4096;
    @SuppressLint({"StaticFieldLeak"})
    private static volatile FileBrowseManager y = null;
    private static final int z = 3072;

    /* renamed from: a  reason: collision with root package name */
    private FileBrowseOperator f12408a;
    private int g;
    private SDCardBean h;
    private List<SDCardBean> i;
    private volatile RcspOpImpl l;
    private LrcReadOperator m;
    private PathData n;
    private Context o;
    private OnRcspCallback q;
    private byte[] r;
    private FileStruct t;
    private LrcDecoder u;
    private int d = 10;
    private final Map<String, Folder> e = new HashMap();
    private volatile boolean k = false;
    private boolean p = false;
    private int s = 0;
    private boolean v = true;
    private final byte[] f = new byte[4096];
    private final Handler j = new Handler(Looper.getMainLooper());
    private final FileObserverHelper b = new FileObserverHelper();
    private final LrcReadObserverHelper c = new LrcReadObserverHelper();

    private FileBrowseManager() {
    }

    private void c(boolean z2) {
        this.k = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        PathData pathData = this.n;
        pathData.setRepeatTimes(pathData.getRepeatTimes() - 1);
        a(this.n);
    }

    public static FileBrowseManager getInstance() {
        if (y == null) {
            synchronized (FileBrowseManager.class) {
                y = new FileBrowseManager();
            }
        }
        return y;
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void OnFlayCallback(boolean z2) {
        String str = w;
        JL_Log.d(str, "OnFlayCallback -->" + z2);
        c(false);
        b(z2);
    }

    public void addFileObserver(FileObserver fileObserver) {
        this.b.addFileObserver(fileObserver);
    }

    public void addLrcReadObserver(LrcReadObserver lrcReadObserver) {
        this.c.addLrcReadObserver(lrcReadObserver);
    }

    public int appenBrowse(FileStruct fileStruct, SDCardBean sDCardBean) {
        if (b(sDCardBean)) {
            if (this.e.get(a(sDCardBean)) == null) {
                return 5;
            }
            if (this.e.get(a(sDCardBean)) == null) {
                return 2;
            }
            Folder folder = this.e.get(a(sDCardBean));
            Objects.requireNonNull(folder);
            Folder folder2 = (Folder) folder.getChildFile(fileStruct.getCluster());
            if (folder2 == null) {
                folder2 = new Folder();
                folder2.setFileStruct(fileStruct);
                Folder remove = this.e.remove(a(sDCardBean));
                Objects.requireNonNull(remove);
                folder2.setParent(remove);
            }
            if (folder2.getLevel() > 8) {
                return 6;
            }
            if (!isReading() || folder2.getChildCount() >= 1) {
                this.h = sDCardBean;
                this.e.put(a(sDCardBean), folder2);
                if (folder2.getChildCount() > 0) {
                    a(folder2.getChildFileStructs());
                    a(folder2.isLoadFinished(false));
                    return 0;
                }
                c(true);
                a(FileBrowseUtil.covertFileToPathData(folder2, sDCardBean.getDevHandler(), (byte) this.d));
                return 0;
            }
            return 1;
        }
        return 2;
    }

    public int backBrowse(SDCardBean sDCardBean) {
        return backBrowse(sDCardBean, true);
    }

    public void cleanCache() {
        List<SDCardBean> list = this.i;
        if (list != null) {
            for (SDCardBean sDCardBean : list) {
                cleanCache(sDCardBean);
            }
        }
    }

    public int deleteFile(SDCardBean sDCardBean, List<FileStruct> list, DeleteCallback deleteCallback) {
        return deleteFile(sDCardBean, list, true, deleteCallback);
    }

    public int formatDevice(final SDCardBean sDCardBean, final OperatCallback operatCallback) {
        if (sDCardBean == null) {
            return 7;
        }
        if (isReading()) {
            return 4;
        }
        if (getOnlineDev() == null || getOnlineDev().size() < 1 || !sDCardBean.isOnline()) {
            return 2;
        }
        this.f12408a.formatDevice(sDCardBean.getDevHandler(), new OperatCallback() { // from class: com.jieli.jl_filebrowse.FileBrowseManager.4
            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onError(int i) {
                OperatCallback operatCallback2 = operatCallback;
                if (operatCallback2 != null) {
                    operatCallback2.onError(i);
                }
            }

            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onSuccess() {
                FileBrowseManager.this.cleanCache(sDCardBean);
                OperatCallback operatCallback2 = operatCallback;
                if (operatCallback2 != null) {
                    operatCallback2.onSuccess();
                }
            }
        });
        return 0;
    }

    public List<FileStruct> getCurrentFileStructs(SDCardBean sDCardBean) {
        if (b(sDCardBean)) {
            return getCurrentReadFile(sDCardBean).getChildFileStructs();
        }
        return null;
    }

    public Folder getCurrentReadFile(SDCardBean sDCardBean) {
        if (!b(sDCardBean)) {
            String str = w;
            JL_Log.e(str, "-getCurrentReadFile- offline,  sdCardBean = " + sDCardBean);
            return null;
        }
        return this.e.get(a(sDCardBean));
    }

    public List<SDCardBean> getOnlineDev() {
        ArrayList arrayList = new ArrayList();
        List<SDCardBean> list = this.i;
        if (list != null && !list.isEmpty()) {
            Iterator it = new ArrayList(this.i).iterator();
            while (it.hasNext()) {
                SDCardBean sDCardBean = (SDCardBean) it.next();
                if (sDCardBean.isOnline()) {
                    arrayList.add(sDCardBean);
                }
            }
        }
        return arrayList;
    }

    public List<SDCardBean> getSdCardBeans() {
        return this.i == null ? new ArrayList() : new ArrayList(this.i);
    }

    public void init(RcspOpImpl rcspOpImpl) {
        if (!this.p) {
            this.l = rcspOpImpl;
            setLrcReadOperator(new LrcReadOperatorImpl(rcspOpImpl));
            setFileBrowseOperator(new FileBrowseOperatorImpl(rcspOpImpl));
            OnFileBrowseCallbackImpl onFileBrowseCallbackImpl = new OnFileBrowseCallbackImpl(rcspOpImpl, this, this);
            this.q = onFileBrowseCallbackImpl;
            rcspOpImpl.registerOnRcspCallback(onFileBrowseCallbackImpl);
            this.p = true;
            return;
        }
        throw new RuntimeException("It cannot be initialize morn than once!");
    }

    public boolean isInit() {
        return this.p;
    }

    public boolean isOnline(int i) {
        List<SDCardBean> list = this.i;
        if (list == null) {
            return false;
        }
        for (SDCardBean sDCardBean : list) {
            if (sDCardBean.getIndex() == i) {
                return sDCardBean.isOnline();
            }
        }
        return false;
    }

    public boolean isOpenLrcCache() {
        return this.v;
    }

    public boolean isReading() {
        return this.k;
    }

    public int loadMore(SDCardBean sDCardBean) {
        if (b(sDCardBean)) {
            if (isReading()) {
                return 1;
            }
            this.h = sDCardBean;
            Folder folder = this.e.get(a(sDCardBean));
            if (folder == null) {
                return 5;
            }
            if (folder.isLoadFinished(false)) {
                return 3;
            }
            c(true);
            a(FileBrowseUtil.covertFileToPathData(folder, sDCardBean.getDevHandler(), (byte) this.d));
            return 0;
        }
        return 2;
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onBluetoothConnectionChange(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            return;
        }
        String str = w;
        JL_Log.i(str, "onBluetoothConnectionChange " + RcspUtil.printBtDeviceInfo(bluetoothDevice));
        cleanCache(bluetoothDevice);
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileDataReceive(byte[] bArr) {
        if (isReading()) {
            System.arraycopy(bArr, 0, this.f, this.g, bArr.length);
            this.g += bArr.length;
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileReadFailed(int i) {
        PathData pathData;
        String str = w;
        JL_Log.i(str, "onFileReadFailed:" + i + "\tcurrentPathData.getRepeatTimes()=" + this.n);
        c(false);
        if (i == 3 && (pathData = this.n) != null && pathData.getRepeatTimes() > 0) {
            this.j.postDelayed(new Runnable() { // from class: com.jieli.jl_filebrowse.a
                @Override // java.lang.Runnable
                public final void run() {
                    FileBrowseManager.this.d();
                }
            }, 500L);
        } else {
            this.b.onFileReadFailed(i);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileReadStart() {
        this.b.onFileReadStart();
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileReadStop(boolean z2) {
        List<FileStruct> parseDataHasPacket;
        String str = w;
        JL_Log.d(str, "---------onFileReadStop------" + this.g + "\tisEnd=" + z2);
        Folder folder = this.e.get(a(this.h));
        if (folder == null) {
            c(false);
            JL_Log.e(str, "---------onFileReadStop------ currentSDCardBean = " + this.h + "\tisEnd=" + z2);
            return;
        }
        folder.setLoadFinished(z2);
        int i = this.g;
        if (i > 0) {
            byte[] bArr = new byte[i];
            this.g = 0;
            System.arraycopy(this.f, 0, bArr, 0, i);
            if (!this.f12408a.dataHasPacket()) {
                parseDataHasPacket = FileBrowseUtil.parseData(bArr);
            } else {
                parseDataHasPacket = FileBrowseUtil.parseDataHasPacket(bArr);
            }
            if (parseDataHasPacket.size() > 0) {
                if (parseDataHasPacket.get(0).getDevIndex() != this.h.getIndex()) {
                    a(this.n);
                    JL_Log.w(str, "----------file  is not match-------------" + this.h.toString());
                    return;
                }
                if (folder.getChildCount() > 0) {
                    if (parseDataHasPacket.get(0).getFileNum() != folder.getChildFileStructs().get(folder.getChildCount() - 1).getFileNum() + 1 && folder.isLoadFinished(false)) {
                        c(false);
                        return;
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (FileStruct fileStruct : parseDataHasPacket) {
                    File regFile = fileStruct.isFile() ? new RegFile() : new Folder();
                    regFile.setFileStruct(fileStruct);
                    regFile.setParent(folder);
                    arrayList.add(regFile);
                }
                folder.addChild(arrayList);
            }
            a(parseDataHasPacket);
        }
        c(false);
        a(z2);
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    public void onLrcDataReceive(byte[] bArr) {
        byte[] bArr2 = this.r;
        if (bArr2 != null) {
            int i = this.s;
            if (bArr.length + i > bArr2.length) {
                byte[] bArr3 = new byte[bArr2.length * 2];
                System.arraycopy(bArr2, 0, bArr3, 0, i);
                this.r = bArr3;
            }
            String str = w;
            StringBuilder sb = new StringBuilder();
            sb.append("lrcBuffer   --->");
            byte[] bArr4 = this.r;
            sb.append(CHexConver.byte2HexStr(bArr4, bArr4.length));
            JL_Log.d(str, sb.toString());
            System.arraycopy(bArr, 3, this.r, this.s, bArr.length - 3);
            this.s += bArr.length - 3;
            if ((bArr[0] & 255) == 255) {
                onLrcReadStop();
            }
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    public void onLrcReadFailed(int i) {
        String str = w;
        JL_Log.d(str, "onLrcReadFailed  reason=" + i);
        this.s = 0;
        this.r = null;
        this.c.onLrcReadFailed(i);
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    public void onLrcReadStart() {
        this.s = 0;
        this.r = new byte[z];
        this.c.onLrcReadStart();
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLrcReadStop() {
        /*
            r6 = this;
            byte[] r0 = r6.r
            r1 = -1
            if (r0 == 0) goto Lab
            int r2 = r6.s
            r3 = 5
            if (r2 >= r3) goto Lc
            goto Lab
        Lc:
            byte[] r3 = new byte[r2]
            r4 = 0
            java.lang.System.arraycopy(r0, r4, r3, r4, r2)
            com.jieli.jl_filebrowse.interfaces.LrcDecoder r0 = r6.u
            if (r0 == 0) goto L1a
            byte[] r3 = r0.decode(r3)
        L1a:
            r0 = r3[r4]
            if (r0 != r1) goto L21
            java.lang.String r0 = "utf_16le"
            goto L46
        L21:
            r0 = r3[r4]
            r2 = -2
            r5 = 1
            if (r0 != r2) goto L2e
            r0 = r3[r5]
            if (r0 != r1) goto L2e
            java.lang.String r0 = "utf_16be"
            goto L46
        L2e:
            r0 = r3[r4]
            r1 = -17
            if (r0 != r1) goto L44
            r0 = r3[r5]
            r1 = -69
            if (r0 != r1) goto L44
            r0 = 2
            r0 = r3[r0]
            r1 = -65
            if (r0 != r1) goto L44
            java.lang.String r0 = "utf-8"
            goto L46
        L44:
            java.lang.String r0 = "gbk"
        L46:
            java.lang.String r1 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L52
            r1.<init>(r3, r0)     // Catch: java.io.UnsupportedEncodingException -> L52
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.io.UnsupportedEncodingException -> L52
            byte[] r3 = r1.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L52
            goto L56
        L52:
            r0 = move-exception
            r0.printStackTrace()
        L56:
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L81
            com.jieli.jl_filebrowse.bean.FileStruct r2 = r6.t     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L81
            java.lang.String r2 = r6.a(r2)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L81
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L81
            r1.write(r3)     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> L9b
            r6.s = r4
            r6.r = r0
            r1.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r0 = move-exception
            r0.printStackTrace()
        L71:
            com.jieli.jl_filebrowse.bean.FileStruct r0 = r6.t
            java.lang.String r0 = r6.a(r0)
            r6.a(r0)
            return
        L7b:
            r2 = move-exception
            goto L84
        L7d:
            r1 = move-exception
            r2 = r1
            r1 = r0
            goto L9c
        L81:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L84:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L9b
            r2 = 16
            r6.onLrcReadFailed(r2)     // Catch: java.lang.Throwable -> L9b
            r6.s = r4
            r6.r = r0
            if (r1 == 0) goto L9a
            r1.close()     // Catch: java.io.IOException -> L96
            goto L9a
        L96:
            r0 = move-exception
            r0.printStackTrace()
        L9a:
            return
        L9b:
            r2 = move-exception
        L9c:
            r6.s = r4
            r6.r = r0
            if (r1 == 0) goto Laa
            r1.close()     // Catch: java.io.IOException -> La6
            goto Laa
        La6:
            r0 = move-exception
            r0.printStackTrace()
        Laa:
            throw r2
        Lab:
            r6.onLrcReadFailed(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_filebrowse.FileBrowseManager.onLrcReadStop():void");
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onSdCardChange(List<SDCardBean> list) {
        this.i = list;
        c(false);
        this.j.removeCallbacksAndMessages(null);
        this.n = null;
        Iterator it = new ArrayList(this.i).iterator();
        while (it.hasNext()) {
            SDCardBean sDCardBean = (SDCardBean) it.next();
            if (!sDCardBean.isOnline()) {
                cleanCache(sDCardBean);
            } else if (getCurrentReadFile(sDCardBean) == null) {
                Folder folder = new Folder();
                FileStruct fileStruct = new FileStruct();
                fileStruct.setName(FileBrowseUtil.getDevName(sDCardBean.getIndex()));
                fileStruct.setFile(false);
                fileStruct.setUnicode(true);
                fileStruct.setCluster(0);
                folder.setFileStruct(fileStruct);
                this.e.put(a(sDCardBean), folder);
            }
        }
        c();
    }

    public int playFile(FileStruct fileStruct, SDCardBean sDCardBean) {
        if (b(sDCardBean)) {
            if (isReading()) {
                return 1;
            }
            c(true);
            RegFile regFile = new RegFile();
            regFile.setFileStruct(fileStruct);
            a(FileBrowseUtil.covertFileToPathData(regFile, sDCardBean.getDevHandler(), (byte) this.d));
            return 0;
        }
        return 2;
    }

    public void release() {
        this.p = false;
        this.j.removeCallbacksAndMessages(null);
        this.b.release();
        this.c.release();
        if (this.l != null) {
            this.l.unregisterOnRcspCallback(this.q);
        }
        cleanCache();
        y = null;
    }

    public void removeFileObserver(FileObserver fileObserver) {
        this.b.removeFileObserver(fileObserver);
    }

    public void removeLrcReadObserver(LrcReadObserver lrcReadObserver) {
        this.c.removeLrcReadObserver(lrcReadObserver);
    }

    public void setContext(Context context) {
        this.o = context;
    }

    public void setFileBrowseOperator(FileBrowseOperator fileBrowseOperator) {
        this.f12408a = fileBrowseOperator;
    }

    public void setLrcDecoder(LrcDecoder lrcDecoder) {
        this.u = lrcDecoder;
    }

    public void setLrcReadOperator(LrcReadOperator lrcReadOperator) {
        this.m = lrcReadOperator;
    }

    public void setOpenLrcCache(boolean z2) {
        this.v = z2;
    }

    public void setPageSize(int i) {
        this.d = i;
    }

    public void startLrcRead(FileStruct fileStruct) {
        if (this.m != null) {
            java.io.File file = new java.io.File(a(fileStruct));
            String str = w;
            JL_Log.d(str, "startLrcRead:: " + file.getPath());
            if (file.exists()) {
                if (isOpenLrcCache()) {
                    a(file.getPath());
                    return;
                }
                file.delete();
            }
            this.t = fileStruct;
            this.m.startLrcRead(new OperatCallback() { // from class: com.jieli.jl_filebrowse.FileBrowseManager.5
                @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
                public void onError(int i) {
                    FileBrowseManager.this.onLrcReadFailed(17);
                }

                @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
                public void onSuccess() {
                    FileBrowseManager.this.onLrcReadStart();
                }
            });
        }
    }

    private boolean b(SDCardBean sDCardBean) {
        List<SDCardBean> list;
        if (sDCardBean != null && (list = this.i) != null && list.size() >= 1) {
            for (SDCardBean sDCardBean2 : getOnlineDev()) {
                if (sDCardBean2.getIndex() == sDCardBean.getIndex()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void c() {
        this.b.onSdCardStatusChange(getOnlineDev());
    }

    public int backBrowse(SDCardBean sDCardBean, boolean z2) {
        if (b(sDCardBean)) {
            this.h = sDCardBean;
            if (this.e.get(a(sDCardBean)) != null) {
                Folder remove = this.e.remove(a(sDCardBean));
                Objects.requireNonNull(remove);
                Folder folder = (Folder) remove.getParent();
                this.e.put(a(sDCardBean), folder);
                if (z2) {
                    if (folder.getChildCount() > 0) {
                        a(folder.getChildFileStructs());
                        a(folder.isLoadFinished(false));
                        return 0;
                    } else if (isReading()) {
                        return 1;
                    } else {
                        c(true);
                        a(FileBrowseUtil.covertFileToPathData(folder, sDCardBean.getDevHandler(), (byte) this.d));
                    }
                }
            }
            return 0;
        }
        return 2;
    }

    public int deleteFile(final SDCardBean sDCardBean, List<FileStruct> list, boolean z2, final DeleteCallback deleteCallback) {
        if (b(sDCardBean)) {
            if (isReading()) {
                return 4;
            }
            if (list == null || list.size() < 1) {
                return 7;
            }
            return a(sDCardBean, new ArrayList(list), z2, new DeleteCallback() { // from class: com.jieli.jl_filebrowse.FileBrowseManager.1

                /* renamed from: a  reason: collision with root package name */
                private boolean f12409a;

                @Override // com.jieli.jl_filebrowse.interfaces.DeleteCallback
                public void onError(int i, FileStruct fileStruct) {
                    DeleteCallback deleteCallback2 = deleteCallback;
                    if (deleteCallback2 != null) {
                        deleteCallback2.onError(i, fileStruct);
                    }
                }

                @Override // com.jieli.jl_filebrowse.interfaces.DeleteCallback
                public void onFinish() {
                    Folder currentReadFile = FileBrowseManager.this.getCurrentReadFile(sDCardBean);
                    if (this.f12409a && currentReadFile != null) {
                        currentReadFile.setLoadFinished(false);
                        currentReadFile.clean();
                        FileBrowseManager.this.k = false;
                        if (FileBrowseManager.this.loadMore(sDCardBean) == 0) {
                            FileBrowseManager.this.addFileObserver(new SimpleFileObserver() { // from class: com.jieli.jl_filebrowse.FileBrowseManager.1.1
                                private void a() {
                                    FileBrowseManager.this.removeFileObserver(this);
                                    DeleteCallback deleteCallback2 = deleteCallback;
                                    if (deleteCallback2 != null) {
                                        deleteCallback2.onFinish();
                                    }
                                }

                                @Override // com.jieli.jl_filebrowse.interfaces.SimpleFileObserver, com.jieli.jl_filebrowse.interfaces.FileObserver
                                public void onFileReadFailed(int i) {
                                    a();
                                }

                                @Override // com.jieli.jl_filebrowse.interfaces.SimpleFileObserver, com.jieli.jl_filebrowse.interfaces.FileObserver
                                public void onFileReadStop(boolean z3) {
                                    a();
                                }

                                @Override // com.jieli.jl_filebrowse.interfaces.SimpleFileObserver, com.jieli.jl_filebrowse.interfaces.FileObserver
                                public void onSdCardStatusChange(List<SDCardBean> list2) {
                                    a();
                                }
                            });
                            return;
                        }
                    }
                    DeleteCallback deleteCallback2 = deleteCallback;
                    if (deleteCallback2 != null) {
                        deleteCallback2.onFinish();
                    }
                    FileBrowseManager.this.k = false;
                }

                @Override // com.jieli.jl_filebrowse.interfaces.DeleteCallback
                public void onSuccess(FileStruct fileStruct) {
                    this.f12409a = true;
                    DeleteCallback deleteCallback2 = deleteCallback;
                    if (deleteCallback2 != null) {
                        deleteCallback2.onSuccess(fileStruct);
                    }
                }
            });
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(final SDCardBean sDCardBean, final List<FileStruct> list, final boolean z2, final DeleteCallback deleteCallback) {
        if (list.size() < 1) {
            return 0;
        }
        boolean z3 = list.size() == 1;
        final FileStruct remove = list.remove(list.size() - 1);
        final boolean z4 = z3;
        this.f12408a.deleteFile(sDCardBean.getDevHandler(), remove.isFile() ? (byte) 1 : (byte) 0, remove.getCluster(), z3, z2, new OperatCallback() { // from class: com.jieli.jl_filebrowse.FileBrowseManager.2
            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onError(int i) {
                FileBrowseManager.this.a(sDCardBean, list, z2, deleteCallback);
                deleteCallback.onError(i, remove);
                if (z4) {
                    deleteCallback.onFinish();
                }
            }

            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onSuccess() {
                FileBrowseManager.this.a(sDCardBean, list, z2, deleteCallback);
                deleteCallback.onSuccess(remove);
                if (z4) {
                    deleteCallback.onFinish();
                }
            }
        });
        return 0;
    }

    public void cleanCache(BluetoothDevice bluetoothDevice) {
        Folder value;
        for (Map.Entry<String, Folder> entry : this.e.entrySet()) {
            if (entry.getKey().endsWith(bluetoothDevice.getAddress()) && (value = entry.getValue()) != null) {
                while (value.getParent() != null) {
                    value.clean();
                    value = (Folder) value.getParent();
                }
                value.clean();
                value.setLoadFinished(false);
                entry.setValue(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z2) {
        c(false);
        this.b.OnFlayCallback(z2);
    }

    private String b() {
        if (this.o == null) {
            return "";
        }
        if (TextUtils.isEmpty(A)) {
            String path = this.o.getFilesDir().getPath();
            StringBuilder sb = new StringBuilder();
            sb.append(path);
            String str = java.io.File.separator;
            sb.append(str);
            sb.append(this.o.getPackageName());
            sb.append(str);
            sb.append("lrcCache");
            A = sb.toString();
        }
        java.io.File file = new java.io.File(A);
        if (!file.exists() && !file.mkdirs()) {
            JL_Log.w(w, "can not create lrc cache path");
            return "";
        }
        return file.getPath();
    }

    private void a(final PathData pathData) {
        byte[] coverPathDataToCmd = FileBrowseUtil.coverPathDataToCmd(pathData);
        this.n = pathData;
        this.f12408a.sendPathDataCmd(pathData, coverPathDataToCmd, new OperatCallback() { // from class: com.jieli.jl_filebrowse.FileBrowseManager.3
            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onError(int i) {
                if (pathData.getType() != 0) {
                    FileBrowseManager.this.b(false);
                } else {
                    FileBrowseManager.this.onFileReadFailed(i);
                }
            }

            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onSuccess() {
                if (pathData.getType() == 0) {
                    FileBrowseManager.this.onFileReadStart();
                } else {
                    JL_Log.d(FileBrowseManager.w, "---------play file cmd send success------");
                }
            }
        });
    }

    private void a(List<FileStruct> list) {
        this.b.onFileReceiver(list);
    }

    private void a(boolean z2) {
        this.b.onFileReadStop(z2);
    }

    private String a(SDCardBean sDCardBean) {
        if (sDCardBean == null) {
            return "";
        }
        return sDCardBean.getName() + "-" + sDCardBean.getDevice().getAddress();
    }

    public void cleanCache(SDCardBean sDCardBean) {
        String a2 = a(sDCardBean);
        Folder folder = this.e.get(a2);
        if (folder == null) {
            return;
        }
        while (folder.getParent() != null) {
            folder.clean();
            folder = (Folder) folder.getParent();
        }
        folder.clean();
        folder.setLoadFinished(false);
        this.e.put(a2, folder);
    }

    private void a(String str) {
        this.c.onLrcReadStop(str);
    }

    private String a(FileStruct fileStruct) {
        if (fileStruct == null) {
            return "lrc_Default";
        }
        return b() + java.io.File.separator + fileStruct.getName() + fileStruct.getCluster();
    }
}
