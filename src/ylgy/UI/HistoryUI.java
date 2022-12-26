package ylgy.UI;

import ylgy.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author SwordFlame
 */
public class HistoryUI extends JFrame implements ActionListener {
    JButton start, end;
    public HistoryUI() throws IOException {
        JPanel p = new JPanel();
        JLabel ni = new JLabel("你的"+(PasswordwindowUI.ifip?("ip : "+ InetAddress.getLocalHost().getHostAddress()):("name : "+PasswordwindowUI.txtusername.getText()))+"                     ");
        p.add(ni);

//        JList<String> games = new JList<>();
//        p.add(games);

        JLabel wins = new JLabel("wins : " + get("getwins") + " times                                    ");
        JLabel lose = new JLabel("lose : " + get("getlose") + " times                                    ");
        JLabel score = new JLabel("score : " + get("getscore") + "                                       ");
        p.add(wins);
        p.add(lose);
        p.add(score);

        start = new JButton("开始游戏");
        end = new JButton("退出游戏");
        p.add(start);
        p.add(end);
        start.addActionListener(this);
        end.addActionListener(this);

        p.setBackground(Color.lightGray);
        add(p);
        setVisible(true);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((width - 300) / 2,(height - 150) / 2, 300, 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HistoryUI self = this;
        if (e.getSource()==start) {
            self.dispose();
            GameFrameUI.gameself = new GameFrameUI();
        } else {
            System.exit(0);
        }
    }
    private String get(String s) throws IOException {
        Socket socket = new Socket("localhost", 8088);
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write(s+'\n');
        pw.write((PasswordwindowUI.ifip?InetAddress.getLocalHost().getHostAddress():PasswordwindowUI.txtusername.getText())+'\n');
        pw.flush();

        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = br.readLine();
        br.close();
        is.close();
        os.close();
        pw.close();
        socket.close();
        return info;
    }
}
