package repository;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Executor {

    public static List execute(String folderUrl) throws IOException {

        List files = Files.walk(Paths.get(folderUrl))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        List decimalNumbers = new ArrayList();
        Iterator iterator = files.iterator();
        while (iterator.hasNext()) {
            String[] splitFileName = iterator.next().toString().split("\\\\");
            int length = splitFileName.length;
            String nameWithType = splitFileName[length - 1];
            String[] split = nameWithType.split("\\.");
            String fileName = split[0];
            char[] fileNameByLetters = fileName.toCharArray();
            String name = "";
            for (int i = 0; i < 6; i++) {
                try {
                    name += fileNameByLetters[i];
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(fileName);
                }
            }
            if (fileNameByLetters.length > 7) {
                if (fileNameByLetters[6] == '-') {
                    name += fileNameByLetters[6];
                    name += fileNameByLetters[7];
                    name += fileNameByLetters[8];
                }
            }
            decimalNumbers.add(name);
        }
        ArrayList uniqueDecimalNumbers = (ArrayList) decimalNumbers.stream().distinct().collect(Collectors.toList());

        return uniqueDecimalNumbers;
    }

    public static Map<String, String> excelExecute
            (String excelUrl, String list, int decimalColumnNumber, int searchingColumnNumber) throws IOException {
        Map map = new HashMap();
        Workbook book = null;
        try {
            File file = new File(excelUrl);
            book = WorkbookFactory.create(file);
            Sheet sheet = book.getSheet(list);

            int rowStart = sheet.getFirstRowNum();
            int rowEnd = sheet.getLastRowNum();

            for (int i = rowStart; i < rowEnd; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                try {
                    String cellWithName = row.getCell(searchingColumnNumber).toString();
                    String cellWithDecimal = row.getCell(decimalColumnNumber).toString();
                    map.put(cellWithName, cellWithDecimal);
                } catch (NullPointerException e) {
                    continue;
                }
            }

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            book.close();
        }
        return map;
    }

    public static void excelWriter(String excelUrl, String excelListName, List strings) throws IOException{
        File file = new File(excelUrl);
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(excelListName);
        for (int i = 0; i < strings.size(); i++) {
            Row row = sheet.createRow(i);
            Object object = strings.get(i);

            Field[] declaredFields = object.getClass().getDeclaredFields();
            int j = 0;
            for (Field fields : declaredFields) {
                fields.setAccessible(true);
                Cell cell = row.createCell(j++);
                try {
                    cell.setCellValue(String.valueOf(fields.get(object)));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        book.write(new FileOutputStream(file));
        book.close();

    }
}

