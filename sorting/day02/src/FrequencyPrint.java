import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO
        // Break string by spaces
        // Hash map
        Integer test = 1;

        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        String[] result = s.split("\\s");
        for (String x : result){
            if(hmap.putIfAbsent(x, test) != null){
                hmap.put(x, hmap.get(x)+1);
            }
        }
        Set<Map.Entry<String, Integer>> setblep = hmap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
        list.addAll(setblep);
        list.sort(Comparator.comparing(Map.Entry::getValue));
        String finalString = "";
        for (Map.Entry<String, Integer> x : list){
            for(int y = 0; y < x.getValue(); y++){
                finalString = finalString + x.getKey() + " ";
            }
        }

        return finalString;
    }

}
