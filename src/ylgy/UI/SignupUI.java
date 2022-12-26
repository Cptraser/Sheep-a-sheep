package ylgy.UI;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * @author SwordFlame
 */
public class SignupUI extends JFrame implements ActionListener {
    JFrame upwid;
    JTextField txtusername, txtuserpw;
    JButton b1, b2;
    public SignupUI(JFrame upwid) {
        this.upwid = upwid;
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

        b1 = new JButton("完成");
        b2 = new JButton("退出");
        p.add(b1);
        p.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);
        p.setBackground(Color.lightGray);
        add(p);
        setSize(300,150);
        setVisible(true);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((width - 300) / 2,(height - 150) / 2, 300, 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SignupUI self = this;
        boolean psck = false;
        if(e.getSource()==b1) {
            try {
                Socket socket = new Socket("localhost", 8088);
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("insert\n");
                pw.write(txtusername.getText()+'\n');
                pw.write(txtuserpw.getText()+'\n');
                pw.flush();

                socket.shutdownOutput();

                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String info = br.readLine();
                if (Objects.equals(info, "success")) {
                    JOptionPane.showMessageDialog(this, "注册成功!","成功！！",JOptionPane.WARNING_MESSAGE);
                    self.dispose();
                    upwid.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "已经存在该账户","失败",JOptionPane.WARNING_MESSAGE);
                }

                br.close();
                is.close();
                os.close();
                pw.close();
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == b2) {
            self.dispose();
            upwid.setVisible(true);
        }
        upwid.setVisible(true);
    }
}
