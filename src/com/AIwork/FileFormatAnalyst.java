package com.AIwork;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * Created by sick on 8/11/13.
 */
public class FileFormatAnalyst {
    String filename;
    int filetype;
    String code;
   // Stirng filelenght;
    public FileFormatAnalyst(String filename) throws Exception {
        this.filename=filename;
        codeString(filename);
    }

    public void  codeString(String fileName) throws Exception{
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                filetype=1;
                break;
            case 0x4a6f:
                code = "UTF-8";
                filetype=1;
                break;
            case 0xfffe:
                code = "Unicode";
                filetype=1;
                break;
            case 0xfeff:
                code = "UTF-16BE";
                filetype=1;
                break;
            default:
                code = Integer.toHexString(p);
                filetype=0;
        }
        bin.close();
    }


    public String getCode() {
        return code;
    }

    public int getFiletype() {
        return filetype;
    }

}
