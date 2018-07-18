package repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {
    String url;

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
}
