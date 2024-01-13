package net.penyo.contacts.view;

import net.penyo.contacts.po.Contact;
import net.penyo.contacts.po.Contacts;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * {@code MainWindow} 类定义了图形用户接口。
 *
 * @author Penyo
 */
public class MainWindow extends JFrame implements CompositionApplication {
  private final Contacts contacts = Contacts.load();

  private final JTextArea search = new JTextArea("搜索" + contacts.size() + "位联系人");
  private final JTextArea allPeople = new JTextArea(contacts.isEmpty() ? "您尚未存储任何联系人。" : contacts.toString());
  private final JScrollPane jsp = new JScrollPane(allPeople);
  private final JPanel goldenFinger = new JPanel(new FlowLayout());
  private final JButton newPerson = new JButton("新建联系人");
  private final JButton removePerson = new JButton("移除联系人");

  private final JDialog newGuide = new JDialog(this, "新建联系人", true);
  private final JPanel flows = new JPanel(new GridLayout(8, 1));
  private final JPanel flow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
  private final JLabel nameNotice = new JLabel("                姓名：");
  private final JTextField nameInput = new JTextField(15);
  private final JPanel flow2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
  private final JLabel telNotice = new JLabel("电话号码：");
  private final JTextField telInput = new JTextField(15);
  private final JButton oneMore2 = new JButton("+");
  private final JButton oneLess2 = new JButton("-");
  private final JTextArea tels = new JTextArea();
  private final JPanel flow3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
  private final JLabel emailNotice = new JLabel("电子邮箱地址：");
  private final JTextField emailInput = new JTextField(15);
  private final JButton oneMore3 = new JButton("+");
  private final JButton oneLess3 = new JButton("-");
  private final JTextArea emails = new JTextArea();
  private final JPanel goldenFinger2 = new JPanel(new FlowLayout());
  private final JButton savePerson = new JButton("保存");

  private final JDialog removeGuide = new JDialog(this, "移除联系人", true);
  private final JPanel flowR = new JPanel(new FlowLayout());
  private final JLabel nameNoticeR = new JLabel("姓名：");
  private final JTextField nameInputR = new JTextField(15);
  private final JPanel goldenFinger3 = new JPanel(new FlowLayout());
  private final JButton removePersonR = new JButton("移除");

  private boolean fromSys = false;

  @Override
  public void loadStructure() {
    this.setTitle("通讯录");

    this.add(search, BorderLayout.NORTH);
    this.add(jsp);
    this.add(goldenFinger, BorderLayout.SOUTH);

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

  @Override
  public void loadStyle() {
    Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds((int) (scr.getWidth() - 360) / 2, (int) (scr.getHeight() - 640) / 2, 360, 640);
    this.setVisible(true);

    search.setBackground(Color.LIGHT_GRAY);

    allPeople.setEditable(false);

    newGuide.setResizable(false);
    newGuide.setBounds((int) (scr.getWidth() - 360) / 2, (int) (scr.getHeight() - 320) / 2, 360, 320);

    tels.setEditable(false);
    emails.setEditable(false);

    removeGuide.setResizable(false);
    removeGuide.setBounds((int) (scr.getWidth() - 360) / 2, (int) (scr.getHeight() - 100) / 2, 360, 100);

  }

  @Override
  public void loadAction() {
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        contacts.save();
      }
    });

    search.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        update();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        update();
      }

      private void update() {
        if (!fromSys) {
          if (search.getText().isEmpty()) {
            allPeople.setText(contacts.isEmpty() ? "您尚未存储任何联系人。" : contacts.toString());
            return;
          }
          StringBuilder wantedPeople = new StringBuilder();
          for (String person : contacts.toString().split("\n\n"))
            if (person.contains(search.getText())) wantedPeople.append(person).append("\n\n");
          allPeople.setText(wantedPeople.toString());
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
      StringBuilder others = new StringBuilder();
      for (int i = 0; i < back.length - 1; i++)
        others.append(back[i]).append("\n");
      tels.setText(others.toString());
    });
    oneMore3.addActionListener(e -> {
      emails.setText(emails.getText() + emailInput.getText() + "\n");
      emailInput.setText("");
    });
    oneLess3.addActionListener(e -> {
      String[] back = emails.getText().split("\n");
      emailInput.setText(back[back.length - 1]);
      StringBuilder others = new StringBuilder();
      for (int i = 0; i < back.length - 1; i++)
        others.append(back[i]).append("\n");
      emails.setText(others.toString());
    });
    savePerson.addActionListener(e -> {
      Contact p = new Contact().setName(nameInput.getText());
      if (p.getName().isEmpty()) return;

      if (!tels.getText().isEmpty()) for (String telInfo : tels.getText().split("\n"))
        p.addTel(telInfo.split("#")[0], telInfo.contains("#") ? telInfo.split("#")[1] : null);
      if (!telInput.getText().isEmpty())
        p.addTel(telInput.getText().split("#")[0], telInput.getText().contains("#") ? telInput.getText().split("#")[1] : null);
      if (!emails.getText().isEmpty()) for (String emailInfo : emails.getText().split("\n"))
        p.addEmail(emailInfo.split("#")[0], emailInfo.contains("#") ? emailInfo.split("#")[1] : null);
      if (!emailInput.getText().isEmpty())
        p.addEmail(emailInput.getText().split("#")[0], emailInput.getText().contains("#") ? emailInput.getText().split("#")[1] : null);
      this.contacts.add(p);

      fromSys = true;
      search.setText("搜索" + this.contacts.size() + "位联系人");
      allPeople.setText(this.contacts.isEmpty() ? "您尚未存储任何联系人。" : this.contacts.toString());
      nameInput.setText(null);
      telInput.setText(null);
      tels.setText(null);
      emailInput.setText(null);
      emails.setText(null);

      newGuide.setVisible(false);
    });

    removePerson.addActionListener(e -> removeGuide.setVisible(true));
    removePersonR.addActionListener(e -> {
      contacts.remove(nameInputR.getText());

      fromSys = true;
      search.setText("搜索" + this.contacts.size() + "位联系人");
      allPeople.setText(contacts.isEmpty() ? "您尚未存储任何联系人。" : contacts.toString());
      allPeople.setText(contacts.toString());
      nameInputR.setText(null);

      removeGuide.setVisible(false);
    });
  }
}
