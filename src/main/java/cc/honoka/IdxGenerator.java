package cc.honoka;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import cc.honoka.utils.IdentifierUtils;


public class IdxGenerator {
    private final int BUFF_SIZE = 1024 * 1024;
    private String recInPath, idxOutPath;
    private File recFile, idxFile;
    private MappedByteBuffer recBuff;
    public IdxGenerator(String recInPath, String idxOutPath) throws IOException {
        this.recInPath = recInPath;
        this.idxOutPath = idxOutPath;
        this.recFile = new File(recInPath);
        this.idxFile = new File(idxOutPath);
        recBuff = new RandomAccessFile(recFile, "r").getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, recFile.length());
    }

    private ArrayList<Integer> getIdxArrayList() {
        ArrayList<Integer> result = new ArrayList<>();
        byte[] buffer;
        int offset = 0;
        while (offset < recFile.length()) {
            int bufferLength = Math.min(BUFF_SIZE, recBuff.remaining());
            buffer = new byte[bufferLength];
            int idx = 0;
            boolean isLastMagic = false;
            int lastMagicIdx = 0;
            recBuff.get(buffer);
            long buffEnd = Math.min((long)BUFF_SIZE, recFile.length() - offset);
            for (int i=0; i < buffEnd; i += Integer.BYTES, idx += Integer.BYTES) {
                if (buffer[i] == IdentifierUtils.MAGIC_BYTES[0] &&
                        buffer[i+1] == IdentifierUtils.MAGIC_BYTES[1] &&
                        buffer[i+2] == IdentifierUtils.MAGIC_BYTES[2] &&
                        buffer[i+3] == IdentifierUtils.MAGIC_BYTES[3]) {
                    lastMagicIdx = idx;
                    isLastMagic = true;
                }
                else {
                    if (isLastMagic) {
                        int code = IdentifierUtils.getCode(buffer, i);
                        if ( code == IdentifierUtils.FIRST_MAGIC_CODE || code == IdentifierUtils.WITH_OUT_MAGIC_CODE) {
                            result.add(lastMagicIdx);
                        }
                    }
                    isLastMagic = false;
                }
            }
            offset += bufferLength;
        }
        return result;
    }

    public void generateIdxFile() throws IOException{
        if (!idxFile.exists()) {
            idxFile.createNewFile();
        }
        FileWriter fw = new FileWriter(idxFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        ArrayList<Integer> idxs = getIdxArrayList();
        try {
            for (int i = 0; i != idxs.size(); i++) {
                String out = String.format("%d\t%d\n", i, idxs.get(i));
                bw.write(out);
            }
        }
        finally {
            bw.flush();
            bw.close();
            fw.close();
        }
    }
}
