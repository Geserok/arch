package openers;

import repository.Executor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileOpener {

    public static void dwgOpener(String decimalNumber) throws IOException {
        Desktop desktop = null;
        String url = "D:\\Архив\\001\\" + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(url);
        for (String st:list) {
            if(st.contains(decimalNumber.substring(3)) && st.contains("dwg") && !st.contains("sb") && !st.contains("d33")){
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File(st));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static void sbDwgOpener(String decimalNumber) throws IOException {
        Desktop desktop = null;
        String url = "D:\\Архив\\001\\" + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(url);
        for (String st:list) {
            if(st.contains(decimalNumber.substring(3)) && st.contains("sb") && st.contains("dwg")){
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File(st));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static void schOpener(String decimalNumber) throws IOException {
        Desktop desktop = null;
        String url = "D:\\Архив\\001\\" + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(url);
        for (String st:list) {
            if(st.contains(decimalNumber.substring(3)) && st.contains("e3") && st.contains("sch")){
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File(st));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static void peOpener(String decimalNumber) throws IOException {
        Desktop desktop = null;
        String url = "D:\\Архив\\001\\" + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(url);
        for (String st:list) {
            if(st.contains(decimalNumber.substring(3)) && st.contains("pe3") && (st.contains("rtf")||st.contains("doc"))){
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File(st));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }






}
