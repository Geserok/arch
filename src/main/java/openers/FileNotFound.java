package openers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileNotFound {
    public FileNotFound(){
        JFrame exept = new JFrame();
        exept.setLayout(new GridLayout(2,1));
        exept.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        exept.setBounds((screenSize.width-100)/2,(screenSize.height-100)/2,100,100);

        JLabel jlab = new JLabel("File not Found");
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exept.dispose();
            }
        });
        exept.add(jlab);
        exept.add(ok);
        exept.setVisible(true);
        exept.setAlwaysOnTop(true);
        exept.setResizable(false);
    }
}
