package com.sherlockgy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiffFilterMain extends JFrame {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton button;

    public DiffFilterMain() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(10); // 设置水平间距
        layout.setVgap(10); // 设置垂直间距
        setLayout(layout);

        JPanel topPanel = new JPanel(new GridLayout(2, 3, 10, 10)); // 设置文本框之间的间距
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置文本框和边界之间的间距

        JLabel label1 = new JLabel("全量数据");
        JLabel label2 = new JLabel("排除数据");
        topPanel.add(label1);
        topPanel.add(label2);
        topPanel.add(new JLabel()); // 添加一个空标签以保持网格布局的对齐

        textArea1 = new JTextArea();
        textArea2 = new JTextArea();
        textArea3 = new JTextArea();
        textArea3.setEditable(false); // 设置第三个文本框为只读
        topPanel.add(new JScrollPane(textArea1));
        topPanel.add(new JScrollPane(textArea2));
        topPanel.add(new JScrollPane(textArea3));

        button = new JButton("Process");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> list1 = new ArrayList<>(Arrays.asList(textArea1.getText().split("\\n")));
                List<String> list2 = new ArrayList<>(Arrays.asList(textArea2.getText().split("\\n")));
                list1.removeAll(list2);
                textArea3.setText(String.join("\n", list1));
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置按钮和边界之间的间距
        buttonPanel.add(button);

        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DiffFilterMain();
    }
}
