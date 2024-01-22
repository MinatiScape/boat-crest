package com.jieli.jl_filebrowse;

import androidx.exifinterface.media.ExifInterface;
import com.jieli.jl_filebrowse.bean.File;
import com.jieli.jl_filebrowse.bean.FileStruct;
import com.jieli.jl_filebrowse.bean.PathData;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class FileBrowseUtil {

    /* renamed from: a  reason: collision with root package name */
    public static byte f12415a = 0;
    private static final byte[] b = {-2, -36, -70};
    private static final byte c = -17;

    public static byte[] coverPathDataToCmd(PathData pathData) {
        if (pathData == null || pathData.toParamData() == null || pathData.toParamData().length < 1) {
            return null;
        }
        byte[] paramData = pathData.toParamData();
        short length = (short) (paramData.length + 1);
        int i = length + 8;
        byte[] bArr = new byte[i];
        System.arraycopy(b, 0, bArr, 0, 3);
        bArr[3] = -64;
        bArr[4] = 12;
        bArr[5] = (byte) (length >> 8);
        bArr[6] = (byte) (length & 255);
        byte b2 = f12415a;
        f12415a = (byte) (b2 + 1);
        bArr[7] = b2;
        System.arraycopy(paramData, 0, bArr, 8, paramData.length);
        bArr[i - 1] = -17;
        JL_Log.d("FileBrowseUtil:", "coverPathDataToCmd" + CHexConver.byte2HexStr(bArr, i));
        return bArr;
    }

    public static PathData covertFileToPathData(File file, int i, byte b2) {
        PathData pathData = new PathData();
        pathData.setType(file.getFileStruct().isFile() ? (byte) 1 : (byte) 0);
        pathData.setDevHandler(i);
        pathData.setReadNum(b2);
        pathData.setStartIndex((short) file.getStartIndex());
        ArrayList arrayList = new ArrayList();
        File file2 = file;
        while (file2.getParent() != null) {
            file2 = file2.getParent();
            arrayList.add(0, Integer.valueOf(file2.getFileStruct().getCluster()));
        }
        arrayList.add(Integer.valueOf(file.getFileStruct().getCluster()));
        pathData.setPath(arrayList);
        return pathData;
    }

    public static String getDevName(int i) {
        return i == 1 ? "SD Card 0" : i == 2 ? "SD Card 1" : i == 0 ? "USB" : i == 3 ? ExifInterface.TAG_FLASH : i == 4 ? "LineIn" : i == 5 ? "Flash2" : "未知类型设备";
    }

    public static List<FileStruct> parseData(byte[] bArr) {
        String str;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i + 6 < bArr.length) {
            boolean z = (bArr[i] & 1) == 1;
            boolean z2 = (bArr[i] & 2) == 2;
            byte b2 = (byte) ((bArr[i] & 28) >> 2);
            int i2 = i + 1;
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i2, bArr2, 0, 4);
            int bytesToInt = CHexConver.bytesToInt(bArr2);
            int i3 = i2 + 4;
            short bytesToInt2 = (short) CHexConver.bytesToInt(bArr[i3], bArr[i3 + 1]);
            int i4 = i3 + 2;
            int i5 = i4 + 1;
            int i6 = bArr[i4] & 255;
            byte[] bArr3 = new byte[i6];
            System.arraycopy(bArr, i5, bArr3, 0, i6);
            try {
                str = new String(bArr3, z2 ? "gbk" : "utf-16le");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                str = "";
            }
            JL_Log.d("parseData", "strData=" + CHexConver.byte2HexStr(bArr3, i6) + "\tname=" + str + "\tisAnsi=" + z2 + "\tfileNum=" + ((int) bytesToInt2) + "\tdevIndex=" + ((int) b2));
            i = i6 + i5;
            FileStruct fileStruct = new FileStruct();
            fileStruct.setFile(z);
            fileStruct.setUnicode(z2 ^ true);
            fileStruct.setCluster(bytesToInt);
            fileStruct.setFileNum(bytesToInt2);
            fileStruct.setDevIndex(b2);
            fileStruct.setName(str);
            arrayList.add(fileStruct);
        }
        return arrayList;
    }

    public static List<FileStruct> parseDataHasPacket(byte[] bArr) {
        if (bArr != null && bArr.length >= 10) {
            int length = bArr.length - 10;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 9, bArr2, 0, length);
            return parseData(bArr2);
        }
        return new ArrayList();
    }
}
