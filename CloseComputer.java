package org.example.shut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;

public class CloseComputer extends JFrame implements ActionListener {

    private JPanel panel_main = new JPanel(new BorderLayout(5,10));

    private JPanel panel_subnorth = new JPanel(new FlowLayout(3));

    private JPanel panel_subcenter = new JPanel(new FlowLayout(1,5,5));

    private JButton countdown = new JButton("倒计时关机");

    private JButton time = new JButton("定时关机");

    private JButton cancel = new JButton("取消关机");

    private JLabel tag;

    String key;
    
    public CloseComputer(){
        this.getContentPane().add(panel_main);
        panel_main.add(panel_subnorth,BorderLayout.NORTH);
        panel_main.add(panel_subcenter,BorderLayout.CENTER);
        panel_subnorth.add(tag = new JLabel("请选择关机方式："));
        panel_subcenter.add(countdown);
        panel_subcenter.add(time);
        panel_subcenter.add(cancel);

        countdown.addActionListener(this);
        time.addActionListener(this);
        cancel.addActionListener(this);
    }

    public static void main(String[] args) {
        CloseComputer frame = new CloseComputer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(320,120);
        frame.setTitle("定时关机");
        frame.setLocation(350,350);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void countdown(){
        key = JOptionPane.showInputDialog(this,"请输入倒计时关机剩余的时间（秒）","输入框",1);
        CountTimeTool.delaytime(Long.parseLong(key));
    }

    public void time(){
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        int hour, minute, secord;
        String date;

        date = JOptionPane.showInputDialog(this,"请输入需要关机的时间HH:mm:ss","输入",1);
        String dates[] = date.split(":");

        if(dates.length < 3){
            JOptionPane.showMessageDialog(this,"请输入正确的格式","确认",2);
            return;
        }

        hour = Integer.parseInt(dates[0]);
        minute = Integer.parseInt(dates[1]);
        secord = Integer.parseInt(dates[2]);

        long set_time = timesum(hour,minute,secord);
        long currently_time = timesum(h,m,s);
        long discrepancy_time = set_time - currently_time;

        if(discrepancy_time < 0){
            try{
                Runtime.getRuntime().exec("shutdown -s");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            CountTimeTool.delaytime(discrepancy_time);
            JOptionPane.showMessageDialog(this,"恭喜你，设置成功！","确认",2);
        }
    }

    private long timesum(int hour, int minute, int secord) {
        long sum = hour * 3600 + minute * 60 + secord;
        return sum;
    }

    public void cancel(){
        try {
            Runtime.getRuntime().exec("shutdown -a");
            JOptionPane.showMessageDialog(this,"你已经成功取消了关机操作！","消息",2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ActionCommand = e.getActionCommand();
        if(e.getSource() instanceof JButton){
            if("倒计时关机".equals(ActionCommand)){
                countdown();
            }
            if("定时关机".equals(ActionCommand)){
                time();
            }
            if("取消关机".equals(ActionCommand)){
                cancel();
            }
        }
    }
}
