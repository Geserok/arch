package repository;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static Map<String, String> excelExecute(String excelUrl, String list) {
        Map map = new HashMap();
        try {
            File file = new File(excelUrl);
            XSSFWorkbook book = (XSSFWorkbook) WorkbookFactory.create(file);
            XSSFSheet sheet = book.getSheet(list);

            int rowStart = sheet.getFirstRowNum();
            int rowEnd = sheet.getLastRowNum();

            for (int i = rowStart; i < rowEnd; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int decimalColumnNumber = 3;
                int nameColumnNumber = 2;
                try {
                    String cellWithName = row.getCell(nameColumnNumber).toString();
                    String cellWithDecimal = row.getCell(decimalColumnNumber).toString();
                    map.put(cellWithName,cellWithDecimal);
                }
                catch (NullPointerException e){
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void excelWriter(String excelUrl, List strings) throws IOException, InvalidFormatException {
        File file = new File(excelUrl);
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("boards");
        for(int i = 0 ; i < strings.size() ; i++){
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue((String) strings.get(i));
        }

        book.write(new FileOutputStream(file));
        book.close();

    }
}

