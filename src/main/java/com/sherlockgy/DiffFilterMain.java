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
    private JButton clearButton; // 新增“清除”按钮
    private JButton swapButton; // 新增“交换”按钮

    public DiffFilterMain() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(10); // 设置水平间距
        layout.setVgap(10); // 设置垂直间距
        setLayout(layout);

        JPanel topPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // 修改为1行3列的网格
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置文本框和边界之间的间距

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label1 = new JLabel("数据1：");
        textArea1 = new JTextArea();
        panel1.add(label1, BorderLayout.NORTH);
        panel1.add(new JScrollPane(textArea1), BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel label2 = new JLabel("数据2：");
        textArea2 = new JTextArea();
        panel2.add(label2, BorderLayout.NORTH);
        panel2.add(new JScrollPane(textArea2), BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());
        JLabel label3 = new JLabel("数据1有而数据2没有的：");
        textArea3 = new JTextArea();
        textArea3.setEditable(false);              // 设置第三个文本框为只读
        textArea3.setBackground(Color.LIGHT_GRAY); // 设置背景色为浅灰色
        textArea3.setCaretColor(Color.LIGHT_GRAY); // 设置光标颜色为浅灰色（这样就可以隐藏光标了）
        panel3.add(label3, BorderLayout.NORTH);
        panel3.add(new JScrollPane(textArea3), BorderLayout.CENTER);

        topPanel.add(panel1);
        topPanel.add(panel2);
        topPanel.add(panel3); // 修改为添加包含“结果”标签的面板

        button = new JButton("执行");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> list1 = new ArrayList<>(Arrays.asList(textArea1.getText().split("\\n")));
                List<String> list2 = new ArrayList<>(Arrays.asList(textArea2.getText().split("\\n")));
                list1.removeAll(list2);
                textArea3.setText(String.join("\n", list1));
            }
        });

        clearButton = new JButton("清除"); // 创建“清除”按钮
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                textArea2.setText("");
                textArea3.setText("");
            }
        });

        swapButton = new JButton("交换数据1和数据2"); // 创建“交换”按钮
        swapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = textArea1.getText();
                textArea1.setText(textArea2.getText());
                textArea2.setText(temp);
                textArea3.setText("");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置按钮和边界之间的间距
        buttonPanel.add(button);
        buttonPanel.add(clearButton); // 添加“清除”按钮到按钮面板
        buttonPanel.add(swapButton); // 添加“交换”按钮到按钮面板

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
