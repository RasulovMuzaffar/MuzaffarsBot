/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.ModelDAO;
import model.ModelReys;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.ModelVokzal;

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
    
    static String ssilka;
    static String znach;
    
    public String find(String stFrom, String stTo) {
        
        Document doc;
        Document doc1;
        
        try {
            doc = Jsoup.connect("http://eticket.uzrailway.uz/timetable/timetable/2900000/today").get();
            
            ssilka = doc.body().getElementsByClass("list-unstyled").toString();
            znach = doc.body().getElementsByClass("list-unstyled").text();
            
            findBySsZn(ssilka, znach);
            
            doc1 = Jsoup.connect(findByCity(stFrom)).get();
//            String c = doc1.body().getElementById("table_super").getElementsByTag("thead").outerHtml();
            
            List<ModelReys> lm = new ArrayList<>();
            ModelReys model = new ModelReys();
            Element table = doc1.select("table.table").get(0);
            Elements rows = table.select("tr");
            int col = rows.select("td").size();
            
            int STOLB = col / (rows.size() - 1);
            
            int k = 0;
            for (int i = 0; i < rows.size(); i++) {
                Elements cols = table.select("td");
                
                if (k < table.select("td").size()) {
                    for (int j = k; j < k + STOLB; j++) {
                        int t = k;
                        if (j == t) {
                            model.setnReys(cols.get(j).text());
                        }
                        if (j == (t + 1)) {
                            String[] aa = cols.get(j).text().split("Поезд, ");
                            String[] bb = aa[0].split("- ");
                            
                            model.setStFrom(bb[0]);
                            model.setStTo(bb[1]);
                            model.setPoezd(aa[1]);
                        }
                        if (j == (t + 2)) {
                            model.setMestV(cols.get(j).text());
                        }
                        if (j == (t + 3)) {
                            model.setDniK(cols.get(j).text());
                        }
                    }
                    System.out.println("model " + model.toString());
                }
                lm.add(model);
                k += STOLB;
            }
            
            Elements link = doc1.body().getElementsByClass("list-unstyled").select("a[href]");
            Map<String, String> m = new HashMap<>();
            for (Element l : link) {
//                System.out.println("link: "+l.attr("href"));
//                System.out.println("text: "+l.text());
                m.put(l.text(), l.attr("href"));
            }
//            for (Map.Entry<String, String> entry : m.entrySet()) {
//                System.out.println("entry.getKey() => " + entry.getKey());
//                System.out.println("entry.getValue() => http://eticket.uzrailway.uz" + entry.getValue());
//            }

        } catch (IOException e) {
            System.out.println("IOException find ---->>> " + e);
        }
        
        ModelDAO md = new ModelDAO();
        try {
//            if (md.selectFromVokzalByName(stFrom).equals("null")) {
                md.insertToVokzal(stFrom, findByCity(stFrom));
//            }
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException md.insertToVokzal() --> " + ex);
            Logger.getLogger(RFW.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("SQLException md.insertToVokzal() --> " + ex);
            Logger.getLogger(RFW.class.getName()).log(Level.SEVERE, null, ex);
        }
        return " **Данные успешно были внесены в БД!** ";
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
        
        for (Iterator it = allList.entrySet().iterator(); it.hasNext();) {
            
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            
            if (entry.getValue().equals(s)) {
                System.out.println("SELECTED CITY--->>> " + entry.getValue());
                s = entry.getKey();
            }
        }
        return s;
    }
}
