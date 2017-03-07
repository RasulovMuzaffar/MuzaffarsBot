/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Muzaffar
 */
public class RFW {

    static Map allList;

    public String find(String stFrom, String stTo) {

        Document doc;
        Document doc1;
        String title;

        List list;

        String ssilka;
        String znach;

        String c = null;

        try {
            doc = Jsoup.connect("http://eticket.uzrailway.uz/timetable/timetable/2900000/today").get();

//            System.out.println(doc.body().getElementsByClass("list-unstyled"));
            ssilka = doc.body().getElementsByClass("list-unstyled").toString();
            znach = doc.body().getElementsByClass("list-unstyled").text();

            findBySsZn(ssilka, znach);

            doc1 = Jsoup.connect(findByCity(stFrom)).get();
            c = doc1.body().getElementById("table_super").getElementsByTag("thead").outerHtml();
//            System.out.println(c);
//            System.out.println(
//                    doc1.body().getElementById("table_super").getElementsByTag("tbody").get(0).getElementsByTag("td").text());

            List<Model> lm = new ArrayList<>();
            Model model = new Model();
            Element table = doc1.select("table.table").get(0);
            Elements rows = table.select("tr");
            System.out.println("rows.size() ---->>> " + rows.size());
            System.out.println("cols.size() ---->>> " + table.select("td").size());
            int k = 0;
            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                System.out.println("rows : " + row.text());
                Elements cols = table.select("td");
                if (k < 132) {
                    for (int j = k; j < k + 4; j++) {
                        int t = k;
                        if (j == t) {
                            model.setnReys(cols.get(j).text());
                        }
                        if (j == (t + 1)) {
                            model.setStFromTo(cols.get(j).text());
                        }
                        if (j == (t + 2)) {
                            model.setMestV(cols.get(j).text());
                        }
                        if (j == (t + 3)) {
                            model.setDniK(cols.get(j).text());
                        }
//                        System.out.println("ttttt " + t);                        
//                    System.out.println("===> " + cols.get(j).text());
                    }
                    lm.add(model);
                    System.out.println("model " + model.toString());
                }
                k += 4;
            }

            lm.stream().forEach((l) -> {
                System.out.println("" + l.toString());
            });

//            Elements e = doc1.select("table.table").select("td");
//            for (Element element : e) {
//                System.out.println("qqqqqqqqqqqqqqqqq   " + element.getElementsByTag("td").text());
//            }
            Elements link = doc1.body().getElementsByClass("list-unstyled").select("a[href]");
            Map<String, String> m = new HashMap<>();
            for (Element l : link) {
//                System.out.println("link: "+l.attr("href"));
//                System.out.println("text: "+l.text());
                m.put(l.text(), l.attr("href"));
            }
            for (Map.Entry<String, String> entry : m.entrySet()) {
//                System.out.println("entry.getKey() => " + entry.getKey());
//                System.out.println("entry.getValue() => http://eticket.uzrailway.uz" + entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("EXCEPTION ---->>> " + e);
        }

        return findByCity(stFrom);
    }

    private static void findBySsZn(String ssilka, String znach) {
        String pattern = "/timetable/timetable/(\\w*)/today";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(ssilka);

        List<String> items = Arrays.asList(znach.split("\\s* \\s*"));
        List<String> listS = new ArrayList();
        while (m.find()) {
            listS.add("http://eticket.uzrailway.uz" + m.group());
        }

        allList = new HashMap<String, String>();

        for (int i = 0; i < listS.size(); i++) {
            allList.put(listS.get(i), items.get(i));
        }

        for (Iterator it = allList.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
//            System.out.println("Ссылка =  " + entry.getKey() + ", Город = " + entry.getValue());
        }
    }

    private String findByCity(String s) {
//        System.out.println(s + "-------------------" + allList.entrySet().iterator());

        /*
         Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            
        }
        
        ---------------------
        
        Map<String, String> newMap = map.entrySet().stream().filter(e -> e.getValue().endsWith("a")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
         */
        for (Iterator it = allList.entrySet().iterator(); it.hasNext();) {

            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
//            System.out.println("SELECTED CITY--->>> " + entry.getValue());
            if (entry.getValue().equals(s)) {
                System.out.println("SELECTED CITY--->>> " + entry.getValue());
                s = entry.getKey();
            }
        }
        return s;
    }
}
