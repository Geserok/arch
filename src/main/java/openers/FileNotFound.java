package openers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileNotFound {
    private static FileNotFound instance;
    private FileNotFound(String msg){
        JFrame exept = new JFrame();
        exept.setLayout(new GridLayout(2,1));
        exept.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        exept.setBounds((screenSize.width-100)/2,(screenSize.height-100)/2,200,100);

        JLabel jlab = new JLabel(msg);
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exept.dispose();
                removeInstance();
            }
        });
        exept.add(jlab);
        exept.add(ok);
        exept.setVisible(true);
        exept.setAlwaysOnTop(true);
        exept.setResizable(false);
    }
    public static synchronized FileNotFound getInstance(){
        if(instance == null) {
            instance = new FileNotFound("File not found");
        }
        return instance;
    }
    public static synchronized FileNotFound getInstance(String msg){
        if(instance == null) {
            instance = new FileNotFound(msg);
        }
        return instance;
    }



    private static void removeInstance(){
        instance = null;
    }
}
