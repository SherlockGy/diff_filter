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

        JPanel topPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // 修改为1行3列的网格
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置文本框和边界之间的间距

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label1 = new JLabel("全量数据");
        textArea1 = new JTextArea();
        panel1.add(label1, BorderLayout.NORTH);
        panel1.add(new JScrollPane(textArea1), BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel label2 = new JLabel("排除数据");
        textArea2 = new JTextArea();
        panel2.add(label2, BorderLayout.NORTH);
        panel2.add(new JScrollPane(textArea2), BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());
        JLabel label3 = new JLabel("结果"); // 新增“结果”标签
        textArea3 = new JTextArea();
        textArea3.setEditable(false); // 设置第三个文本框为只读
        panel3.add(label3, BorderLayout.NORTH); // 将“结果”标签添加到第三个文本框上方
        panel3.add(new JScrollPane(textArea3), BorderLayout.CENTER);

        topPanel.add(panel1);
        topPanel.add(panel2);
        topPanel.add(panel3); // 修改为添加包含“结果”标签的面板

        button = new JButton("执行过滤");
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
