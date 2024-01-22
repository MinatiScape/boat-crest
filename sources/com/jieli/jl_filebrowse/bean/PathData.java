package com.jieli.jl_filebrowse.bean;

import com.jieli.jl_rcsp.util.CHexConver;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public class PathData {
    public static final byte PATH_TYPE_FILE = 1;
    public static final byte PATH_TYPE_FlODER = 0;
    private List<Integer> path;
    private byte type = 0;
    private byte readNum = 10;
    private short startIndex = 1;
    private int devHandler = 0;
    private int repeatTimes = 3;

    public PathData() {
        ArrayList arrayList = new ArrayList();
        this.path = arrayList;
        arrayList.add(0);
    }

    public static PathData build(byte[] bArr) {
        Objects.requireNonNull(bArr, "param is null.");
        if (bArr.length >= 14) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(ByteOrder.BIG_ENDIAN);
            PathData pathData = new PathData();
            pathData.type = wrap.get();
            pathData.readNum = wrap.get();
            pathData.startIndex = wrap.getShort();
            pathData.devHandler = wrap.getInt();
            ArrayList arrayList = new ArrayList();
            short s = wrap.getShort();
            for (int i = 0; i < s; i += 4) {
                arrayList.add(Integer.valueOf(wrap.getInt()));
            }
            pathData.path = arrayList;
            return pathData;
        }
        throw new IllegalArgumentException("");
    }

    public int getDevHandler() {
        return this.devHandler;
    }

    public List<Integer> getPath() {
        return this.path;
    }

    public byte getReadNum() {
        return this.readNum;
    }

    public int getRepeatTimes() {
        return this.repeatTimes;
    }

    public short getStartIndex() {
        return this.startIndex;
    }

    public byte getType() {
        return this.type;
    }

    public void setDevHandler(int i) {
        this.devHandler = i;
    }

    public void setPath(List<Integer> list) {
        this.path = list;
    }

    public void setReadNum(byte b) {
        this.readNum = b;
    }

    public void setRepeatTimes(int i) {
        this.repeatTimes = i;
    }

    public void setStartIndex(short s) {
        this.startIndex = s;
    }

    public void setType(byte b) {
        this.type = b;
    }

    public byte[] toParamData() {
        List<Integer> list = this.path;
        if (list == null) {
            return null;
        }
        int size = list.size() * 4;
        byte[] bArr = new byte[size + 10];
        bArr[0] = this.type;
        bArr[1] = this.readNum;
        short s = this.startIndex;
        bArr[2] = (byte) ((s >> 8) & 255);
        bArr[3] = (byte) (s & 255);
        byte[] intToBigBytes = CHexConver.intToBigBytes(this.devHandler);
        System.arraycopy(intToBigBytes, 0, bArr, 4, intToBigBytes.length);
        bArr[8] = (byte) ((size >> 8) & 255);
        bArr[9] = (byte) (size & 255);
        int i = 10;
        for (Integer num : this.path) {
            byte[] intToBigBytes2 = CHexConver.intToBigBytes(num.intValue());
            System.arraycopy(intToBigBytes2, 0, bArr, i, intToBigBytes2.length);
            i += intToBigBytes2.length;
        }
        return bArr;
    }

    public String toString() {
        return "PathData{type=" + ((int) this.type) + ", readNum=" + ((int) this.readNum) + ", startIndex=" + ((int) this.startIndex) + ", devHandler=" + this.devHandler + ", path=" + this.path + ", repeatTimes=" + this.repeatTimes + '}';
    }
}
