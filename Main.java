package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    private static int wordAppears = 1;

    public static void main(String[] args) {
        String[] strings = {"дружба", "дружба", "любовь", "семья", "мир", "здоровье", "красота", "природа", "мир", "мир", "мир", "наука", "искусство", "культура", "истина", "свобода", "правда", "здоровье", "наука", "любовь"};

        System.out.println(Arrays.toString(strings));
        HashSet<String> stringHashSet = new HashSet<>(Arrays.asList(strings));
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(strings));
        System.out.println(stringHashSet + "= Это уникальные слова");
        System.out.println(stringHashSet.size() + " уникальных слов");
        System.out.println(stringArrayList);
//        составим хешмап типа слово-количество упоминаний и запишем в цикле сколько встречается
        HashMap<String, Integer> wordAppearsHashMap = new HashMap<>();

        for (int i = 0; i < stringArrayList.size(); i++) {
            for (int j = 0; j < stringArrayList.size(); j++) {
                wordAppearsHashMap.put(stringArrayList.get(i), wordAppears);
                if (i != j && stringArrayList.get(i).equals(stringArrayList.get(j))) {
                    wordAppears++;
                    wordAppearsHashMap.put(stringArrayList.get(i), wordAppears);
                }
            }
            wordAppears = 1;
        }
        System.out.println(wordAppearsHashMap);
        //задание 2
        PhoneHashMap.add("Coroloff","225566699");
        PhoneHashMap.add("Coroloff","2299");
        PhoneHashMap.add("Coroloff","99");
        PhoneHashMap.add("Beroloff","220006624");
        PhoneHashMap.add("Corodasoff","255555666");
        PhoneHashMap.add("Cosgasaloff","22599966");
        PhoneHashMap.get("Colorful");
        PhoneHashMap.get("Coroloff");
    }
}