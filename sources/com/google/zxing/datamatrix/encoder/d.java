package com.google.zxing.datamatrix.encoder;
/* loaded from: classes11.dex */
public final class d extends SymbolInfo {
    public d() {
        super(false, 1558, com.veryfit.multi.nativeprotocol.b.M2, 22, 22, 36, -1, 62);
    }

    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getDataLengthForInterleavedBlock(int i) {
        return i <= 8 ? 156 : 155;
    }

    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getInterleavedBlockCount() {
        return 10;
    }
}
