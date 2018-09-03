package openers;

import org.hibernate.SessionFactory;
import repository.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOpener {
//    private static String url = "D:\\Архив\\001\\";
    private static String url = "C:\\javaprojects\\arch\\Архив\\001\\";

    public static void dwgOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.","");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        for (String st : list) {
            if (st.contains(decimalNumber.substring(3)) && st.contains("dwg") && !st.contains("sb")
                    && !st.contains("d33") && !st.contains("gh")) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(st));
                    break;
                } catch (IOException ioe) {
                    new FileNotFound();
                }
            }
        }
        if(consistFlag == false) {
            new FileNotFound();
        }
    }

    public static void gbOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.","");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        for (String st : list) {
            if (st.contains(decimalNumber.substring(3)) && st.contains("dwg") && !st.contains("sb")
                    && !st.contains("d33") && (st.contains("gh") || st.contains("gb") || st.contains("GB"))) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(st));
                    break;
                } catch (IOException ioe) {
                    new FileNotFound();                }
            }
        }
        if(consistFlag == false) {
            new FileNotFound();
        }
    }

    public static void sbDwgOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.","");
        Desktop desktop = null;
        String fullUrl = url +  decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        for (String st : list) {
            if (st.contains(decimalNumber.substring(3)) && st.contains("sb") && st.contains("dwg")) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(st));
                    break;
                } catch (IOException ioe) {
                    new FileNotFound();                }
            }
        }
        if(consistFlag == false) {
            new FileNotFound();
        }
    }

    public static void schOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.","");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        for (String st : list) {
            if (st.contains(decimalNumber.substring(3)) && st.contains("sch")) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(st));
                    break;
                } catch (IOException ioe) {
                    new FileNotFound();                }
            }
        }
        if(consistFlag == false) {
            new FileNotFound();
        }
    }

    public static void peOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.","");

        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";

        List<String> list = Executor.filesExecute(fullUrl);
        for (String st : list) {
            if (st.contains(decimalNumber.substring(3)) && (st.contains("pe3") || st.contains("ре3")) && (st.contains("rtf") || st.contains("doc"))) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(st));
                    break;
                } catch (IOException ioe) {
                   new FileNotFound();
                }
            }
        }
        if(consistFlag == false) {
            new FileNotFound();
        }
    }

    public static void tuOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.","");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);

        for (String st : list) {
            if (st.contains(decimalNumber.substring(3)) && st.contains("tu") && !st.contains("Lu")  && (st.contains("rtf") || st.contains("doc"))) {
                consistFlag = true;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File(st));
                    break;
                } catch (IOException ioe) {
                    new FileNotFound();
                }
            }
        }
        if(consistFlag == false) {
            new FileNotFound();
        }
    }

    public static void includeElementsMenu(SessionFactory factory) {
        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl repositorySupplyModules = new SupplyModuleRepositoryImpl(factory);
        System.out.println("Введите id");
        Scanner in = new Scanner(System.in);
        int searchid = in.nextInt();
        SupplyModule byId = repositorySupplyModules.getById(searchid);
        String[] split = byId.getIncludedElements().split("\\*");

        ArrayList<String> boardlist = new ArrayList();
        for (String string : split) {
            boardlist.add(string);
        }
        int i = 1;
        for (String strings : boardlist) {
            System.out.println(i++ + ") " + strings);
        }
        System.out.println("Введите номер входящего элемента: ");
        int searchElem = in.nextInt();
        System.out.println("Выберите тип документа: ");
        System.out.println("1) Спецификация");
        System.out.println("2) Схема электрическая принципиальная");
        System.out.println("3) Перечень элементов");
        System.out.println("4) Сборочный чертеж");
        int move = in.nextInt();

        try {
            if (move == 1) {
                FileOpener.dwgOpener(boardlist.get(searchElem - 1));
            } else if (move == 2) {
                FileOpener.schOpener(boardlist.get(searchElem - 1));
            } else if (move == 3) {
                FileOpener.peOpener(boardlist.get(searchElem - 1));
            } else if (move == 4) {
                FileOpener.sbDwgOpener(boardlist.get(searchElem - 1));
            }
        } catch (IndexOutOfBoundsException ex) {

        } catch (IOException e) {
            new FileNotFound();        }

    }
}
