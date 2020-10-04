package FindTags_my_solution_rejectedByValidator;

/*
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindTags_my_solution_rejectedByValidator {
    public static void main(String[] args) throws IOException {
// 1918_data.txt
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bufferedReader = new BufferedReader(new FileReader(reader.readLine()))
//             BufferedReader bufferedReader = new BufferedReader(new FileReader("1918_data.txt"))
        ){
        String s;
        StringBuilder str = new StringBuilder();
        while ((s = bufferedReader.readLine()) != null) {
//            s = s + sTemp + " ";
            str.append(s);
        }
        s = str.toString().replaceAll("\r\n", "");
            if (s.contains("CDATA")) return;
//        System.out.println(s);
        Pattern start = Pattern.compile("<" + args[0] + "[^<]*>");
        Matcher ms = start.matcher(s);
        Pattern end = Pattern.compile("</" + args[0] + ">");
        Matcher me = end.matcher(s);
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();
        while (ms.find()) startList.add(ms.start());
        while (me.find()) endList.add(me.end() - 1);
        List<String> result = new ArrayList<>();
        int posStart;
        int posFinish;
            while (!startList.isEmpty()) {
                posStart = startList.get(0);
                posFinish = endList.get(0);
                if (startList.size() == 1) {
                result.add(s.substring(posStart, posFinish + 1));
                    break;
                }
                for (int i = 1; i < startList.size(); i++) {
                    if (startList.get(i) > endList.get(0)) {
                        posFinish = endList.get(i - 1);
                        startList.remove(0);
                        endList.remove(i - 1);
                    }
                }
                result.add(s.substring(posStart, posFinish + 1));
                }
                result.forEach(System.out::println);
        }
    }
}