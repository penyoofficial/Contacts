package com.penyo.contacts;

import java.util.ArrayList;
import java.util.TreeSet;

import com.penyo.contacts.items.Email;
import com.penyo.contacts.items.Person;
import com.penyo.contacts.items.Tel;

/**
 * {@code VCardAdapter} 类用于转换程序内数据和符合VCard标准的数据。
 * 
 * @author Penyo
 */
public class VCardAdapter {
    /**
     * 该方法用于将符合VCard标准的数据转换为程序内数据。
     * 
     * @param people 符合VCard标准的数据。
     * @return 程序内数据。
     */
    public static ArrayList<Person> toCollection(String people) {
        String[] personsP1 = people.split("BEGIN:VCARD");
        ArrayList<Person> personsP2 = new ArrayList<>();
        for (String pp1f : personsP1) {
            String[] lines = pp1f.split("\n");
            Person p = new Person();
            for (String line : lines) {
                if (line.contains("FN:")) {
                    p.setName(line.substring(3));
                    continue;
                }
                if (line.contains("TEL;TYPE=")) {
                    String[] conditions = line.split(":");
                    p.addTel(new Tel(conditions[1],
                            conditions[0].substring(9).equals("CELL") ? null : conditions[0].substring(9)));
                    continue;
                }
                if (line.contains("EMAIL;TYPE=")) {
                    String[] conditions = line.split(":");
                    p.addEmail(new Email(conditions[1],
                            conditions[0].substring(11).equals("WORK") ? null : conditions[0].substring(11)));
                    continue;
                }
            }
            if (p.getName() != null)
                personsP2.add(p);
        }
        return personsP2;
    }

    /**
     * 该方法用于将程序内数据转换为符合VCard标准的数据。
     * 
     * @param people 程序内数据。
     * @return 符合VCard标准的数据。
     */
    public static String toVcardString(TreeSet<Person> people) {
        String contacts = "";
        for (Person p : people) {
            contacts += "BEGIN:VCARD\nVERSION:3.0\n";
            contacts += ("FN:" + p.getName() + "\n");
            for (Tel t : p.getTel())
                contacts += ("TEL;TYPE=" + (t.getRemark() == null ? "CELL"
                        : t.getRemark()) + ":" + t.getTel() + "\n");
            for (Email e : p.getEmail())
                contacts += ("EMAIL;TYPE=" + (e.getRemark() == null ? "WORK"
                        : e.getRemark()) + ":" + e.getEmail() + "\n");
            contacts += "END:VCARD\n";
        }
        return contacts;
    }
}
