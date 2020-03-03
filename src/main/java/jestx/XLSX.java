package jestx;

import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

/**
 * Excel 工具类
 * 
 */
public abstract class XLSX {
    /**
     * 加载 Excel 文件。
     * 
     * @param path 文件路径
     * @return 下一步
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
     * 加载 Excel 文件 并打开默认页。
     * 
     * @param path 文件路径
     * @return 下一步
     * @throws IOException
     */
    public static Step<HSSFSheet> open(String path) throws IOException {
        return load(path).then(workbook -> {
            int index = workbook.getActiveSheetIndex();
            return workbook.getSheetAt(index);
        });
    }

    /**
     * 打开文件，打开指定页。
     * 
     * @param path 文件路径
     * @param index 页索引
     * @return 下一步
     * @throws IOException
     */
    public static Step<HSSFSheet> open(String path, int index) throws IOException {
        return load(path).then(workbook -> {
            return workbook.getSheetAt(index);
        });
    }

    /**
     * 获取指定页的行。
     * 
     * @param sheet 页对象
     * @param start 开始行
     * @param end 结束行
     * @return 行数组
     */
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
     * 打开 Excel 文件并获取默认页的行。
     * 
     * @param path 文件路径
     * @param start 开始行
     * @param end 结束行
     * @return 行数组
     * @throws IOException
     */
    public static ArrayList<HSSFRow> fetch(String path, Integer start, Integer end) throws IOException {
        return open(path).then(sheet -> {
            return fetch(sheet, start, end);
        }).peek();
    }

    /**
     * 打开 Excel 文件并获取所有行。
     * 
     * @param path 文件路径
     * @return 行数组
     * @throws IOException
     */
    public static ArrayList<HSSFRow> fetch(String path) throws IOException {
        return fetch(path, null, null);
    }

    /**
     * 打开默认页，并获取行。
     * 
     * @param path 文件路径
     * @param start 开始行
     * @return 行数组
     * @throws IOException
     */
    public static ArrayList<HSSFRow> fetch(String path, Integer start) throws IOException {
        return fetch(path, start, null);
    }
}