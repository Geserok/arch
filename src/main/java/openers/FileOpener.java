package openers;

import gui.TypesOfDoc;
import repository.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FileOpener {
    private static String url = "D:\\Архив\\001\\";
    //private static String url = "D:\\АРХИВ_27июля_16\\001\\";
    //private static String url = "C:\\javaprojects\\arch\\Архив\\001\\";

    /**
     * Method which open files
     *
     * @param decimalNumber
     * @param type          type of searching documentation
     * @throws IOException
     */
    public static void getOpen(String decimalNumber, TypesOfDoc type) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.", "");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        Collections.reverse(list);
        for (String st : list) {
            String s = st.toLowerCase();
            if (getType(s, decimalNumber, type)) {
                consistFlag = true;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File(s));
                    break;
                } catch (IOException ioe) {
                    FileNotFound.getInstance();
                }
            }
        }
        if (!consistFlag) {
            if (decimalNumber.length() > 9) {
                getOpen(decimalNumber.substring(0, 9), type);
                FileNotFound.getInstance("Открыт файл без расширения");
            }
            FileNotFound.getInstance();
        }
    }

    /**
     * Method which check name of file
     *
     * @param nameOfFile
     * @param decimalNumber
     * @param type
     * @return
     */

    private static boolean getType(String nameOfFile, String decimalNumber, TypesOfDoc type) {
        switch (type) {
            case TU:
                return nameOfFile.contains(decimalNumber.substring(3)) &&
                        nameOfFile.contains("tu") &&
                        !nameOfFile.contains("lu") && (nameOfFile.contains("rtf") ||
                        nameOfFile.contains("doc"));
            case PE3:
                return nameOfFile.contains(decimalNumber.substring(3)) &&
                        (nameOfFile.contains("pe3") ||
                                nameOfFile.contains("ре3")) && (nameOfFile.contains("rtf") ||
                        nameOfFile.contains("doc"));
            case SCH:
                return nameOfFile.contains(decimalNumber.substring(3)) &&
                        nameOfFile.contains("sch");
            case SB:
                return nameOfFile.contains(decimalNumber.substring(3)) &&
                        nameOfFile.contains("sb") && nameOfFile.contains("dwg");
            case GB:
                return nameOfFile.contains(decimalNumber.substring(3)) &&
                        nameOfFile.contains("dwg") && !nameOfFile.contains("sb")
                        && !nameOfFile.contains("d33") && (nameOfFile.contains("gh") ||
                        nameOfFile.contains("gb") || nameOfFile.contains("GB"));
            case DWG:
                return nameOfFile.contains(decimalNumber.substring(3)) &&
                        nameOfFile.contains("dwg") && !nameOfFile.contains("sb")
                        && !nameOfFile.contains("d33") && !nameOfFile.contains("gh");
        }
        throw new IllegalArgumentException("Wrong!Illegal argument exception");
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        FileOpener.url = url;
    }
}
