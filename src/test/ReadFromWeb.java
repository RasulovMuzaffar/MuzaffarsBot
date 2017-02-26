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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Muzaffar
 */
public class ReadFromWeb {

    public void find() {

        Document doc;
        String title;

        List list;

        String ssilka;
        String znach;

        try {
            doc = Jsoup.connect("http://eticket.uzrailway.uz/timetable/timetable/2900700/today").get();

//            System.out.println(doc.body().getElementsByClass("list-unstyled"));
//            System.out.println(doc.body().getElementsByClass("list-unstyled").text());
//            System.out.println("----------------------");
//            String pattern = "/timetable/timetable/2900000/today";
            ssilka = doc.body().getElementsByClass("list-unstyled").toString();
            znach = doc.body().getElementsByClass("list-unstyled").text();
//            findByPattern(ssilka);
            findBySsZn(ssilka, znach);

//            System.out.println("----------------------");
//            System.out.println(doc.body().getElementById("table_super").getElementsByTag("thead"));
//            System.out.println(doc.body().getElementById("table_super").getElementsByTag("tbody"));
            title = doc.title();
//            System.out.println("--------- " + title);
        } catch (IOException e) {
            System.out.println("EXCEPTION ---->>> " + e);
        }
//return findBySsZn(ssilka, znach);
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

        Map allList = new HashMap<String, String>();

        for (int i = 0; i < listS.size(); i++) {
            allList.put(listS.get(i), items.get(i));
        }

        for (Iterator it = allList.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            System.out.println("Ссылка =  " + entry.getKey() + ", Город = " + entry.getValue());
        }
    }
    

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//
//        Document doc;
//        String title;
//
//        List list;
//
//        try {
//            doc = Jsoup.connect("http://eticket.uzrailway.uz/timetable/timetable/2900700/today").get();
//
//            System.out.println(doc.body().getElementsByClass("list-unstyled"));
//            System.out.println(doc.body().getElementsByClass("list-unstyled").text());
//            System.out.println("----------------------");
//
////            String pattern = "/timetable/timetable/2900000/today";
//            String ssilka = doc.body().getElementsByClass("list-unstyled").toString();
//            String znach = doc.body().getElementsByClass("list-unstyled").text();
////            findByPattern(ssilka);
//            findBySsZn(ssilka, znach);
//
//            System.out.println("----------------------");
//            System.out.println(doc.body().getElementById("table_super").getElementsByTag("thead"));
//            System.out.println(doc.body().getElementById("table_super").getElementsByTag("tbody"));
//            title = doc.title();
//            System.out.println("--------- " + title);
//        } catch (IOException e) {
//            System.out.println("EXCEPTION ---->>> " + e);
//        }
//
//    }
//
////    public static void findByPattern(String ssilka) {
////        String pattern = "/timetable/timetable/(\\w*)/today";
////
////        Pattern p = Pattern.compile(pattern);
////        Matcher m = p.matcher(ssilka);
////        System.out.println("" + ssilka);
////        ArrayList<String> list = new ArrayList<String>();
////        while (m.find()) {
////            list.add("http://eticket.uzrailway.uz" + m.group());
////        }
////
////    }
//
//    private static void findBySsZn(String ssilka, String znach) {
//        String pattern = "/timetable/timetable/(\\w*)/today";
//
//        Pattern p = Pattern.compile(pattern);
//        Matcher m = p.matcher(ssilka);
//
//        List<String> items = Arrays.asList(znach.split("\\s* \\s*"));
//        List<String> listS = new ArrayList();
//        while (m.find()) {
//            listS.add("http://eticket.uzrailway.uz" + m.group());
//        }
//
//        Map allList = new HashMap<String, String>();
//
//        for (int i = 0; i < listS.size(); i++) {
//            allList.put(listS.get(i), items.get(i));
//        }
//
//        for (Iterator it = allList.entrySet().iterator(); it.hasNext();) {
//            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
//            System.out.println("Ссылка =  " + entry.getKey() + ", Город = " + entry.getValue());
//        }
//    }
}
