package jdm;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import javax.swing.*;
/**
 * @author SMA74/9031806
 * This is Frame of JDM 1.0 ...
 */
public class Frame extends JFrame 
{
    //fields
    private URL url;
    JTable table;
    private JMenuBar menubar;
    private JMenu download;
    private JMenu help;
    private JMenuItem NewDownload;
    private JMenuItem Pause;
    private JMenuItem Resume;
    private JMenuItem Cancel;
    private JMenuItem Remove;
    private JMenuItem Settings;
    private JMenuItem About;
    private JToolBar toolbar;
    private JButton NewDownloadbutton;
    private JButton Pausebutton;
    private JButton Resumebutton;
    private JButton Cancelbutton;
    private JButton Removebutton;
    private JButton Settingsbutton;
    private BorderLayout borderlayout;
    private BorderLayout bl;
    private JScrollPane scroll;
    private JPanel panel;
    static DownloadTable model;

   
    //Constructor
    public Frame() {
        super("مدیریت دانلود جاوا 1.0");
        bl = new BorderLayout();
        panel = new JPanel(bl);
        borderlayout = new BorderLayout(5,5);
        setLayout(borderlayout);
        menubar = new JMenuBar();
        download = new JMenu("دانلود");
        download.setMnemonic(KeyEvent.VK_D);
        menubar.add(download); 
        NewDownload = new JMenuItem("دانلود جدید");
        NewDownload.setToolTipText("دانلود جدید");
        NewDownload.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.ALT_MASK));         
        download.add(NewDownload);
        Pause = new JMenuItem("توقف");
        Pause.setToolTipText("توقف");
        Pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.ALT_MASK));
        download.add(Pause);
        Resume = new JMenuItem("ادامه");
        Resume.setToolTipText("ادامه");
        Resume.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_R, ActionEvent.ALT_MASK)); 
        download.add(Resume);
        Cancel = new JMenuItem("لغو");
        Cancel.setToolTipText("لغو");
        Cancel.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_C, ActionEvent.ALT_MASK)); 
        download.add(Cancel);
        Remove = new JMenuItem("حذف");
        Remove.setToolTipText("حذف");
        Remove.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_E, ActionEvent.ALT_MASK)); 
        download.add(Remove);
        download.addSeparator();
        Settings = new JMenuItem("تنطیمات");
        Settings.setToolTipText("تنطیمات");
        Settings.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.ALT_MASK)); 
        download.add(Settings);
        help = new JMenu("راهنما");
        help.setMnemonic(KeyEvent.VK_H);
        menubar.add(help);
        About = new JMenuItem("درباره مدیریت دانلود جاوا 1.0");
        About.setToolTipText("درباره برنامه");
        About.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,ActionEvent.ALT_MASK));
        help.add(About);
        add(menubar,borderlayout.NORTH);
        toolbar = new JToolBar();
        Icon NewDownloadIcon = new ImageIcon(getClass().getResource("newdownload.png"));
        NewDownloadbutton = new JButton("دانلود جدید",NewDownloadIcon );
        NewDownloadbutton.setToolTipText("دانلود جدید");
        toolbar.add(NewDownloadbutton);
        Icon PauseIcon = new ImageIcon(getClass().getResource("pause.png"));
        Pausebutton = new JButton("توقف",PauseIcon );
        Pausebutton.setToolTipText("توقف");
        toolbar.add(Pausebutton);
        Icon ResumeIcon = new ImageIcon(getClass().getResource("resume.png"));
        Resumebutton = new JButton("ادامه",ResumeIcon );
        Resumebutton.setToolTipText("ادامه");
        toolbar.add(Resumebutton);
        Icon CancelIcon = new ImageIcon(getClass().getResource("cancel.png"));
        Cancelbutton = new JButton("لغو",CancelIcon );
        Cancelbutton.setToolTipText("لغو");
        toolbar.add(Cancelbutton);
        Icon RemoveIcon = new ImageIcon(getClass().getResource("remove.png"));
        Removebutton = new JButton("حذف",RemoveIcon );
        Removebutton.setToolTipText("حذف");
        toolbar.add(Removebutton);
        Icon SettingsIcon = new ImageIcon(getClass().getResource("settings.png"));
        Settingsbutton = new JButton("تنطیمات",SettingsIcon );
        Settingsbutton.setToolTipText("تنطیمات");
        toolbar.add(Settingsbutton);
        toolbar.setEnabled(false);
        panel.add(toolbar,bl.NORTH);
        Table();
        add(panel,borderlayout.CENTER);
        
        NewDownload.addActionListener(new NewDownloadHandler());
        NewDownloadbutton.addActionListener(new NewDownloadHandler());
        Pause.addActionListener(new PauseHandler());
        Pausebutton.addActionListener(new PauseHandler());
        Resume.addActionListener(new ResumeHandler());
        Resumebutton.addActionListener(new ResumeHandler());
        Remove.addActionListener(new RemoveHandler());
        Removebutton.addActionListener(new RemoveHandler());
        Cancel.addActionListener(new CancelHandler());
        Cancelbutton.addActionListener(new CancelHandler());
        About.addActionListener(new AboutHandler());
        Settings.addActionListener(new SettingsHandler());
        Settingsbutton.addActionListener(new SettingsHandler());
        
    }
 // This is AboutHandler to show About window...
    private class AboutHandler implements ActionListener{
           @Override
            public void actionPerformed(ActionEvent event) {
                AboutFrame aboutframe;
                aboutframe = new AboutFrame();
                aboutframe.pack();
                aboutframe.setVisible(true);
            }
    }

// This is NewDownloadHandler to show NewURL window... 
    private class NewDownloadHandler implements ActionListener{
           @Override
            public void actionPerformed(ActionEvent event) {
                    NewURL newurl=new NewURL();
            }
    }
// This is PauseHandler to Pause a download...
    private class PauseHandler implements ActionListener{
           @Override
            public void actionPerformed(ActionEvent e) { 
               if(table.getSelectedRow()>=0)
               ((DownloadTable)table.getModel()).getDownload(table.getSelectedRow()).pause();
            }
        }
// This is ResumeHandler to Resume a paused download...
    private class ResumeHandler implements ActionListener{
           @Override
             public void actionPerformed(ActionEvent e) {
               if(table.getSelectedRow()>=0)
               ((DownloadTable)table.getModel()).getDownload(table.getSelectedRow()).resume();
            }
        }
// This is CancelHandler to cancel a download...
    private class CancelHandler implements ActionListener{
           @Override
            public void actionPerformed(ActionEvent e) {
               if(table.getSelectedRow()>=0)
               ((DownloadTable)table.getModel()).getDownload(table.getSelectedRow()).cancel();
            }
        }
// This is RemoveHandler to remove a download
    private class RemoveHandler implements ActionListener{
           @Override
                public void actionPerformed(ActionEvent e) {
               if(table.getSelectedRow()>=0){
                    ((DownloadTable)table.getModel()).getDownload(table.getSelectedRow()).cancel();
                    ((DownloadTable)table.getModel()).removeDownload(table.getSelectedRow());
               }
            }
        }
    // This is AboutHandler to show About window...
    private class SettingsHandler implements ActionListener{
           @Override
            public void actionPerformed(ActionEvent e) {
               new Settings().setVisible(true);
            }
        }
    //add JTable to Frame...
    private void Table(){
        model = new DownloadTable();
        table = new JTable(model){
            @Override
           public boolean isCellEditable(int row, int column)
            { 
                return false;
            }  
        };
        table.setSelectionMode(0);
        table.getTableHeader().setReorderingAllowed(false);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        scroll=new JScrollPane(table);
        panel.add(scroll,bl.CENTER); 
        table.addMouseListener(new MouseAdapter()
            {
            @Override
    public void mouseClicked(MouseEvent event)
    {
        if (event.getComponent().isEnabled() && event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 2)
        {
            Point p = event.getPoint();
            int row = table.rowAtPoint(p); 
            String path=model.getDownload(row).getFilePath();
            Desktop d=Desktop.getDesktop();
                    try {
                    if(model.getDownload(row).getProgress()>=100||model.getDownload(row).getStatus().equals("Complete"))
                        d.open(new File(path));
                    else
                        JOptionPane.showMessageDialog(rootPane,"فایل باز نشد","خطا",JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane,"فایل باز نشد","خطا",JOptionPane.ERROR_MESSAGE);
                    }
            }
                 }
            }
      );
}
    /*
     * Rerurn the Download Table.
     */
    public JTable getTable()   
    {
        return table;
    }     
}