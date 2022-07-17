package com.penyo.contacts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.penyo.contacts.items.Email;
import com.penyo.contacts.items.Person;
import com.penyo.contacts.items.Tel;

/**
 * {@code GUI} 类定义了友好操作 {@link People} 类的图形用户界面。
 * 
 * @author Penyo
 * @see People
 */
public class GUI {
    private People p = new People();

    private JFrame frame = new JFrame("通讯录");
    private JTextArea search = new JTextArea("搜索" + p.size() + "位联系人");
    private JTextArea allPeople = new JTextArea(p.size() == 0 ? "您尚未存储任何联系人。" : p.toString());
    private JScrollPane jsp = new JScrollPane(allPeople);
    private JPanel goldenFinger = new JPanel(new FlowLayout());
    private JButton newPerson = new JButton("新建联系人");
    private JButton removePerson = new JButton("移除联系人");

    private JDialog newGuide = new JDialog(frame, "新建联系人", true);
    private JPanel flows = new JPanel(new GridLayout(8, 1));
    private JPanel flow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel nameNotice = new JLabel("                姓名：");
    private JTextField nameInput = new JTextField(15);
    private JPanel flow2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JLabel telNotice = new JLabel("电话号码：");
    private JTextField telInput = new JTextField(15);
    private JButton oneMore2 = new JButton("+");
    private JButton oneLess2 = new JButton("-");
    private JTextArea tels = new JTextArea();
    private JPanel flow3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JLabel emailNotice = new JLabel("电子邮箱地址：");
    private JTextField emailInput = new JTextField(15);
    private JButton oneMore3 = new JButton("+");
    private JButton oneLess3 = new JButton("-");
    private JTextArea emails = new JTextArea();
    private JPanel goldenFinger2 = new JPanel(new FlowLayout());
    private JButton savePerson = new JButton("保存");

    private JDialog removeGuide = new JDialog(frame, "移除联系人", true);
    private JPanel flowR = new JPanel(new FlowLayout());
    private JLabel nameNoticeR = new JLabel("姓名：");
    private JTextField nameInputR = new JTextField(15);
    private JPanel goldenFinger3 = new JPanel(new FlowLayout());
    private JButton removePersonR = new JButton("移除");

    private boolean fromSys = false;

    /**
     * 该构造器用于初始化图形用户界面。
     */
    public GUI() {
        init();
        setLayout();
        actionRegist();
    }

    /**
     * 该方法用于组装部件。
     */
    private void init() {
        frame.add(search, BorderLayout.NORTH);
        frame.add(jsp);
        frame.add(goldenFinger, BorderLayout.SOUTH);

        goldenFinger.add(newPerson);
        goldenFinger.add(removePerson);

        flow1.add(nameNotice);
        flow1.add(nameInput);
        flow2.add(telNotice);
        flow2.add(telInput);
        flow2.add(oneMore2);
        flow2.add(oneLess2);
        flow3.add(emailNotice);
        flow3.add(emailInput);
        flow3.add(oneMore3);
        flow3.add(oneLess3);

        flows.add(flow1);
        flows.add(flow2);
        flows.add(tels);
        flows.add(flow3);
        flows.add(emails);

        goldenFinger2.add(savePerson);

        newGuide.add(flows);
        newGuide.add(goldenFinger2, BorderLayout.SOUTH);

        flowR.add(nameNoticeR);
        flowR.add(nameInputR);

        goldenFinger3.add(removePersonR);

        removeGuide.add(flowR);
        removeGuide.add(goldenFinger3, BorderLayout.SOUTH);
    }

    /**
     * 该方法用于调整外观。
     */
    private void setLayout() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds((int) (scr.getWidth() - 360) / 2,
                (int) (scr.getHeight() - 640) / 2, 360, 640);
        frame.setVisible(true);

        search.setBackground(Color.LIGHT_GRAY);

        allPeople.setEditable(false);

        newGuide.setResizable(false);
        newGuide.setBounds((int) (scr.getWidth() - 360) / 2,
                (int) (scr.getHeight() - 320) / 2, 360, 320);

        tels.setEditable(false);
        emails.setEditable(false);

        removeGuide.setResizable(false);
        removeGuide.setBounds((int) (scr.getWidth() - 360) / 2,
                (int) (scr.getHeight() - 100) / 2, 360, 100);
    }

    /**
     * 该方法用于注册监听。
     */
    private void actionRegist() {
        search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update(e);
            }

            private void update(DocumentEvent e) {
                if (!fromSys) {
                    if (search.getText().length() == 0) {
                        allPeople.setText(p.size() == 0 ? "您尚未存储任何联系人。" : p.toString());
                        return;
                    }
                    String wantedPeople = "";
                    for (String person : p.toString().split("\n\n"))
                        if (person.contains(search.getText()))
                            wantedPeople += person + "\n\n";
                    allPeople.setText(wantedPeople);
                }
                fromSys = false;
            }
        });

        newPerson.addActionListener(e -> newGuide.setVisible(true));
        oneMore2.addActionListener(e -> {
            tels.setText(tels.getText() + telInput.getText() + "\n");
            telInput.setText("");
        });
        oneLess2.addActionListener(e -> {
            String[] back = tels.getText().split("\n");
            telInput.setText(back[back.length - 1]);
            String others = "";
            for (int i = 0; i < back.length - 1; i++)
                others += (back[i] + "\n");
            tels.setText(others);
        });
        oneMore3.addActionListener(e -> {
            emails.setText(emails.getText() + emailInput.getText() + "\n");
            emailInput.setText("");
        });
        oneLess3.addActionListener(e -> {
            String[] back = emails.getText().split("\n");
            emailInput.setText(back[back.length - 1]);
            String others = "";
            for (int i = 0; i < back.length - 1; i++)
                others += (back[i] + "\n");
            emails.setText(others);
        });
        savePerson.addActionListener(e -> {
            Person p = new Person(nameInput.getText());
            if (p.getName().equals(""))
                return;

            if (tels.getText().length() > 0)
                for (String telInfo : tels.getText().split("\n"))
                    p.addTel(new Tel(telInfo.split("#")[0],
                            telInfo.contains("#") ? telInfo.split("#")[1] : null));
            if (telInput.getText().length() > 0)
                p.addTel(new Tel(telInput.getText().split("#")[0],
                        telInput.getText().contains("#") ? telInput.getText().split("#")[1] : null));
            if (emails.getText().length() > 0)
                for (String emailInfo : emails.getText().split("\n"))
                    p.addEmail(new Email(emailInfo.split("#")[0],
                            emailInfo.contains("#") ? emailInfo.split("#")[1] : null));
            if (emailInput.getText().length() > 0)
                p.addEmail(new Email(emailInput.getText().split("#")[0],
                        emailInput.getText().contains("#") ? emailInput.getText().split("#")[1] : null));
            this.p.add(p);

            fromSys = true;
            search.setText("搜索" + this.p.size() + "位联系人");
            allPeople.setText(this.p.size() == 0 ? "您尚未存储任何联系人。" : this.p.toString());
            nameInput.setText(null);
            telInput.setText(null);
            tels.setText(null);
            emailInput.setText(null);
            emails.setText(null);

            newGuide.setVisible(false);
        });

        removePerson.addActionListener(e -> removeGuide.setVisible(true));
        removePersonR.addActionListener(e -> {
            p.remove(nameInputR.getText());

            fromSys = true;
            search.setText("搜索" + this.p.size() + "位联系人");
            allPeople.setText(p.size() == 0 ? "您尚未存储任何联系人。" : p.toString());
            allPeople.setText(p.toString());
            nameInputR.setText(null);

            removeGuide.setVisible(false);
        });
    }
}
