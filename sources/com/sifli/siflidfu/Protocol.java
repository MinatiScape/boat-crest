package com.sifli.siflidfu;
/* loaded from: classes12.dex */
public class Protocol {
    public static final int DFU_ABORT_COMMAND = 37;
    public static final int DFU_COMMAND_HEADER_LEN = 4;
    public static final int DFU_END_MODE_NO_SEND = 0;
    public static final int DFU_END_MODE_SEND_CMD = 2;
    public static final int DFU_END_MODE_WAIT_RSP = 1;
    public static final int DFU_ERROR_DISCOVERY = 101;
    public static final int DFU_ERROR_DISCOVERY_NOT_FOUND = 102;
    public static final int DFU_FILE_END_REQUEST = 28;
    public static final int DFU_FILE_END_RESPONSE = 29;
    public static final int DFU_FILE_INIT_COMMAND_REQUEST = 21;
    public static final int DFU_FILE_INIT_COMMAND_RESPONSE = 22;
    public static final int DFU_FILE_INIT_COMPLETED = 23;
    public static final int DFU_FILE_LINK_LOSE_CHECK_REQ = 35;
    public static final int DFU_FILE_LINK_LOSE_CHECK_RSP = 36;
    public static final int DFU_FILE_PACKET_DATA = 26;
    public static final int DFU_FILE_PACKET_DATA_RESPONSE = 27;
    public static final int DFU_FILE_START_REQUEST = 24;
    public static final int DFU_FILE_START_RESPONSE = 25;
    public static final int DFU_FILE_TOTAL_END_REQUEST = 30;
    public static final int DFU_FILE_TOTAL_END_RESPONSE = 31;
    public static final int DFU_IMAGE_DATA_LEN = 6;
    public static final int DFU_IMAGE_END = 13;
    public static final int DFU_IMAGE_END_LEN = 1;
    public static final int DFU_IMAGE_END_REQUEST = 8;
    public static final int DFU_IMAGE_END_REQUEST_LEN = 2;
    public static final int DFU_IMAGE_END_RESPONSE = 9;
    public static final int DFU_IMAGE_FORCE_INIT_REQUEST = 14;
    public static final int DFU_IMAGE_INIT_COMPLETED = 2;
    public static final int DFU_IMAGE_INIT_COMPLETED_EXT = 34;
    public static final int DFU_IMAGE_INIT_REQUEST = 0;
    public static final int DFU_IMAGE_INIT_RESPONSE = 1;
    public static final int DFU_IMAGE_PACKET_DATA = 10;
    public static final int DFU_IMAGE_PACKET_DATA_RESPONSE = 11;
    public static final int DFU_IMAGE_RESUME_COMPLETED = 5;
    public static final int DFU_IMAGE_RESUME_REQUEST = 3;
    public static final int DFU_IMAGE_RESUME_RESPONSE = 4;
    public static final int DFU_IMAGE_START_REQUEST = 6;
    public static final int DFU_IMAGE_START_REQUEST_LEN = 10;
    public static final int DFU_IMAGE_START_RESPONSE = 7;
    public static final int DFU_IMAGE_TRANSMISSION_END = 12;
    public static final int DFU_IMAGE_TRANSMISSION_END_LEN = 1;
    public static final int DFU_INIT_COMMAND_REQUEST_EXT = 32;
    public static final int DFU_INIT_COMMAND_RESPONSE_EXT = 33;
    public static final int DFU_INIT_COMPLETED_EXT_LEN = 1;
    public static final int DFU_INIT_COMPLETED_LEN = 1;
    public static final int DFU_MODE_FORCE = 3;
    public static final int DFU_MODE_NORMAL = 1;
    public static final int DFU_MODE_RESUME = 2;
    public static final int DFU_RESUME_COMPLETED_LEN = 1;
    public static final int DFU_RESUME_MODE_ABORT = 0;
    public static final int DFU_RESUME_MODE_NORMAL = 1;
    public static final int DFU_RESUME_MODE_QUERY = 2;
    public static final int DFU_SERVICE_EXIT = 100;
    public static final int FILE_END_REQUEST_LEN = 2;
    public static final int FILE_INIT_COMMAND_LEN = 16;
    public static final int FILE_INIT_COMPLETED_LEN = 1;
    public static final int FILE_LINK_LOSE_RSP_LEN = 2;
    public static final int FILE_PACKET_DATA_LEN = 4;
    public static final int FILE_START_REQUEST_LEN = 12;
    public static final int FILE_TOTAL_END_REQUEST_LEN = 2;
    public static final int IMAGE_ID_CTRL = -1;
    public static final int IMAGE_ID_DYN = 5;
    public static final int IMAGE_ID_EX = 5;
    public static final int IMAGE_ID_FONT = 4;
    public static final int IMAGE_ID_HCPU = 0;
    public static final int IMAGE_ID_LCPU = 1;
    public static final int IMAGE_ID_MUSIC = 6;
    public static final int IMAGE_ID_NAND_LCPU_PATCH = 4;
    public static final int IMAGE_ID_NAND_RES = -2;
    public static final int IMAGE_ID_NOR_LCPU_PATCH = 2;
    public static final int IMAGE_ID_OTA = 6;
    public static final int IMAGE_ID_RES = 3;
    public static final int IMAGE_ID_TINY_FONT = 7;
    public static final int SIFLI_RES_PACKET_LEN = 10240;
}
