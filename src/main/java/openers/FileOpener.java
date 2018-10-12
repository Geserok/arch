package openers;

import org.hibernate.SessionFactory;
import repository.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileOpener {
    private static String url = "D:\\Архив\\001\\";
    //private static String url = "D:\\АРХИВ_27июля_16\\001\\";
    //private static String url = "C:\\javaprojects\\arch\\Архив\\001\\";

    /**
     * Method which open .dwg file with {@param decimalNumber} name(Спецификация)
     * @param decimalNumber the decimal number of board or Supply module
     * @throws IOException returned when file is not find in url directory
     */
    public static void dwgOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.", "");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        Collections.reverse(list);
        for (String st : list) {
            String s = st.toLowerCase();
            if (s.contains(decimalNumber.substring(3)) && s.contains("dwg") && !s.contains("sb")
                    && !s.contains("d33") && !s.contains("gh")) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(s));
                    break;
                } catch (IOException ioe) {
                    FileNotFound.getInstance();
                }
            }
        }
        if (consistFlag == false) {
            if (decimalNumber.length() > 9) {
                dwgOpener(decimalNumber.substring(0, 9));
                FileNotFound.getInstance("Открыт файл без расширения");
            }
            FileNotFound.getInstance();
        }
    }
    /**
     * Method which open gb.dwg file with {@param decimalNumber} name(Габаритный чертеж)
     * @param decimalNumber the decimal number of board or Supply module
     * @throws IOException returned when file is not find in url directory
     */
    public static void gbOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.", "");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        Collections.reverse(list);
        for (String st : list) {
            String s = st.toLowerCase();
            if (s.contains(decimalNumber.substring(3)) && s.contains("dwg") && !s.contains("sb")
                    && !s.contains("d33") && (s.contains("gh") || s.contains("gb") || s.contains("GB"))) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(s));
                    break;
                } catch (IOException ioe) {
                    FileNotFound.getInstance();
                }
            }
        }
        if (consistFlag == false) {
            if (decimalNumber.length() > 9) {
                gbOpener(decimalNumber.substring(0, 9));
                FileNotFound.getInstance("Открыт файл без расширения");
            }
            FileNotFound.getInstance();
        }
    }
    /**
     * Method which open sb.dwg file with {@param decimalNumber} name(Сборочный чертеж)
     * @param decimalNumber the decimal number of board or Supply module
     * @throws IOException returned when file is not find in url directory
     */
    public static void sbDwgOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.", "");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        Collections.reverse(list);
        for (String st : list) {
            String s = st.toLowerCase();
            if (s.contains(decimalNumber.substring(3)) && s.contains("sb") && s.contains("dwg")) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(s));
                    break;
                } catch (IOException ioe) {
                    FileNotFound.getInstance();
                }
            }
        }
        if (consistFlag == false) {
            if (decimalNumber.length() > 9) {
                sbDwgOpener(decimalNumber.substring(0, 9));
                FileNotFound.getInstance("Открыт файл без расширения");
            }
            FileNotFound.getInstance();
        }
    }
    /**
     * Method which open .sch file with {@param decimalNumber}
     * name(Электрическая принципиальная схема).
     * @param decimalNumber the decimal number of board or Supply module
     * @throws IOException returned when file is not find in url directory
     */
    public static void schOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.", "");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        Collections.reverse(list);
        for (String st : list) {
            String s = st.toLowerCase();
            if (s.contains(decimalNumber.substring(3)) && s.contains("sch")) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(s));
                    break;
                } catch (IOException ioe) {
                    FileNotFound.getInstance();
                }
            }
        }
        if (consistFlag == false) {
            if (decimalNumber.length() > 9) {
                schOpener(decimalNumber.substring(0, 9));
                FileNotFound.getInstance("Открыт файл без расширения");
            }
            FileNotFound.getInstance();
        }
    }
    /**
     * Method which open pe3.doc file with {@param decimalNumber}
     * name(Перечень элементов).
     * @param decimalNumber the decimal number of board or Supply module
     * @throws IOException returned when file is not find in url directory
     */
    public static void peOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.", "");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        Collections.reverse(list);
        for (String st : list) {
            String s = st.toLowerCase();

            if (s.contains(decimalNumber.substring(3)) && (s.contains("pe3") || s.contains("ре3")) && (s.contains("rtf") || s.contains("doc"))) {
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    consistFlag = true;
                }
                try {
                    desktop.open(new File(s));
                    break;
                } catch (IOException ioe) {
                    FileNotFound.getInstance();
                }
            }
        }
        if (consistFlag == false) {
            if (decimalNumber.length() > 9) {
                peOpener(decimalNumber.substring(0, 9));
                FileNotFound.getInstance("Открыт файл без расширения");
            }
            FileNotFound.getInstance();
        }
    }
    /**
     * Method which open tu.doc file with {@param decimalNumber}
     * name(Технические условия).
     * @param decimalNumber the decimal number of board or Supply module
     * @throws IOException returned when file is not find in url directory
     */
    public static void tuOpener(String decimalNumber) throws IOException {
        boolean consistFlag = false;
        decimalNumber = decimalNumber.replaceAll("\\.", "");
        Desktop desktop = null;
        String fullUrl = url + decimalNumber.substring(0, 3) + "\\";
        List<String> list = Executor.filesExecute(fullUrl);
        Collections.reverse(list);
        for (String st : list) {
            String s = st.toLowerCase();
            if (s.contains(decimalNumber.substring(3)) && s.contains("tu") &&
                    !s.contains("lu") && (s.contains("rtf") || s.contains("doc"))) {
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
        if (consistFlag == false) {
            if (decimalNumber.length() > 9) {
                tuOpener(decimalNumber.substring(0, 9));
                FileNotFound.getInstance("Открыт файл без расширения");
            }
            FileNotFound.getInstance();
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
            FileNotFound.getInstance();
        }

    }
}
