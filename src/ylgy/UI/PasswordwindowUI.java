package ylgy.UI;


import ylgy.Main;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * @author SwordFlame
 */
public class PasswordwindowUI extends JFrame implements ActionListener {
    public static JTextField txtusername;
    JTextField txtuserpw;
    JButton b1, b2, b3, b4;
    public int sum = 1;
    public static boolean ifip = false;

    public PasswordwindowUI(){
        JPanel p = new JPanel();
        JLabel tishi = new JLabel("  请输入用户名和密码                                                  ");
        p.add(tishi);

        JLabel username = new JLabel("账号");
        p.add(username);
        txtusername =  new JTextField(22);
        p.add(txtusername);

        JLabel userpw = new JLabel("密码");
        p.add(userpw);
        txtuserpw = new JTextField(22);
        p.add(txtuserpw);

        b1 = new JButton("登录");
        b2 = new JButton("退出");
        b3 = new JButton("注册");
        b4 = new JButton("IP登录");
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        p.setBackground(Color.lightGray);
        add(p);
        setSize(300,150);
        setVisible(true);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((width - 300) / 2,(height - 150) / 2, 300, 150);

    }

    public void actionPerformed(ActionEvent e) {
        PasswordwindowUI self = this;
        boolean psck = false;
        if(e.getSource()==b1) {
            try {
                Socket socket = new Socket("localhost", 8088);
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("check\n");
                pw.write(txtusername.getText()+'\n');
                pw.write(txtuserpw.getText()+'\n');
                pw.flush();

                socket.shutdownOutput();

                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String info = br.readLine();
                if (Objects.equals(info, "success")) {
                    psck = true;
                }

                br.close();
                is.close();
                os.close();
                pw.close();
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if(psck) {
                JOptionPane.showMessageDialog(this, "登录成功!","成功！！",JOptionPane.WARNING_MESSAGE);
                self.dispose();
                Main.passwordcheck = true;
                try {
                    new HistoryUI();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                if(sum>=3){
                    JOptionPane.showMessageDialog(this, "输入次数过多，即将退出","错误！",JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
                else {
                    JOptionPane.showMessageDialog(this, "还能输入"+(3-sum)+"次","账号密码错误",JOptionPane.WARNING_MESSAGE);
                    sum = sum + 1;
                }
            }
        } else if (e.getSource() == b2) {
            System.exit(0);
        } else if (e.getSource() == b3) {
            self.setVisible(false);
            new SignupUI(self);
        } else {
            try {
                Socket socket = new Socket("localhost", 8088);
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("ip\n");
                pw.write(InetAddress.getLocalHost().getHostAddress() +'\n');
                pw.flush();

                socket.shutdownOutput();

                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String info = br.readLine();

                if (Objects.equals(info, "success")) {
                    JOptionPane.showMessageDialog(this, "IP登录成功!","成功！！",JOptionPane.WARNING_MESSAGE);
                    self.dispose();
                    Main.passwordcheck = true;
                    ifip = true;
                    new HistoryUI();
                }
                br.close();
                is.close();
                os.close();
                pw.close();
                socket.close();
            } catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }
    }

    public static String gettxtusername() { return txtusername.getText(); }
}
