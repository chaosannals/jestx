package jestx;

import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

/**
 * 
 */
public abstract class XLSX {
    /**
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static Step<HSSFWorkbook> load(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        return new Step<>(workbook);
    }

    /**
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static Step<HSSFSheet> open(String path) throws IOException {
        return load(path).then(workbook -> {
            int index = workbook.getActiveSheetIndex();
            return workbook.getSheetAt(index);
        });
    }

    /**
     * 
     * @param path
     * @param index
     * @return
     * @throws IOException
     */
    public static Step<HSSFSheet> open(String path, int index) throws IOException {
        return load(path).then(workbook -> {
            return workbook.getSheetAt(index);
        });
    }

    public static ArrayList<HSSFRow> fetch(HSSFSheet sheet, Integer start, Integer end) {
        ArrayList<HSSFRow> result = new ArrayList<>();
        int firstRowIndex = 0;
        if (start != null) {
            firstRowIndex = Math.max(start, firstRowIndex);
        }
        int lastRowIndex = sheet.getLastRowNum();
        if (end != null) {
            lastRowIndex = Math.min(end, lastRowIndex);
        }
        for (int i = firstRowIndex; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            result.add(row);
        }
        return result;
    }

    /**
     * 
     * @param path
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public static ArrayList<HSSFRow> fetch(String path, Integer start, Integer end) throws IOException {
        return open(path).then(sheet -> {
            return fetch(sheet, start, end);
        }).peek();
    }

    /**
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static ArrayList<HSSFRow> fetch(String path) throws IOException {
        return fetch(path, null, null);
    }

    /**
     * 
     * @param path
     * @param start
     * @return
     * @throws IOException
     */
    public static ArrayList<HSSFRow> fetch(String path, Integer start) throws IOException {
        return fetch(path, start, null);
    }
}