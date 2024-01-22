package com.jieli.jl_rcsp.model.command.file_op;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandWithParamAndResponse;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.util.CHexConver;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.CharEncoding;
/* loaded from: classes11.dex */
public class LargeFileTransferGetNameCmd extends CommandWithParamAndResponse<Param, CommonResponse> {

    /* loaded from: classes11.dex */
    public static class Param extends BaseParameter {
        public final String name;
        public final int renameTime;

        public Param(String str, int i) {
            this.name = str;
            this.renameTime = i;
        }

        public byte[] getNameData(String str, int i) {
            String str2;
            String str3 = "";
            String replaceAll = str.replaceAll("[\\x00-\\x1f\\x2f\\x3a\\x3c\\x3e\\x5c\\x22]", "");
            int lastIndexOf = replaceAll.lastIndexOf(46);
            String substring = lastIndexOf != -1 ? replaceAll.substring(0, lastIndexOf) : replaceAll;
            substring.replaceAll(".", "");
            String substring2 = lastIndexOf != -1 ? replaceAll.substring(lastIndexOf) : "";
            if (i > 0) {
                str3 = ("000" + i).substring(str2.length() - 3);
            }
            try {
                String str4 = substring + str3;
                if (str4.getBytes("gbk").length < 9 && substring2.length() < 5) {
                    String str5 = str4 + substring2;
                    System.out.println("sen\t获取文件名称 \tretryName = " + i + "\tname = " + replaceAll + "\tshortName = " + str5);
                    byte[] bytes = str5.getBytes("gbk");
                    byte[] bArr = new byte[bytes.length + 2];
                    System.arraycopy(bytes, 0, bArr, 0, bytes.length);
                    return bArr;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String str6 = substring + str3 + substring2;
            System.out.println("sen\t获取文件名称 \tretryName = " + i + "\tname = " + replaceAll + "\tlenName = " + str6);
            byte[] bytes2 = "\\U".getBytes();
            byte[] bArr2 = new byte[0];
            try {
                bArr2 = str6.getBytes(CharEncoding.UTF_16LE);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            byte[] bArr3 = new byte[bytes2.length + bArr2.length + 2];
            System.arraycopy(bytes2, 0, bArr3, 0, bytes2.length);
            System.arraycopy(bArr2, 0, bArr3, bytes2.length, bArr2.length);
            System.out.println(CHexConver.byte2HexStr(bArr3));
            return bArr3;
        }

        @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
        public byte[] getParamData() {
            String str = this.name;
            if (str != null && str.length() != 0) {
                return getNameData(this.name, this.renameTime);
            }
            return super.getParamData();
        }
    }

    public LargeFileTransferGetNameCmd(Param param) {
        super(32, LargeFileTransferGetNameCmd.class.getSimpleName(), param);
    }
}
