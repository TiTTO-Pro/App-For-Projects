package com.app_for_projects;

/**
 * @author TiTTko
 * @version 1.1.0
 */

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ALL_PROJ extends Thread {

    private static final File desktop_path = FileSystemView.getFileSystemView().getHomeDirectory();
    private static final File click_sound = new File(desktop_path + "\\App-for-projects\\Sounds\\click_sound.wav");
    private static final File delete_sound = new File(desktop_path + "\\App-for-projects\\Sounds\\delete_sound.wav");
    private static final File error_sound = new File(desktop_path + "\\App-for-projects\\Sounds\\error_sound.wav");

    JFrame main_window = new JFrame();//создаём основное окно

    //получаем screen size
    Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();

    int getLast_index, count_of_index, vertical, horizontal, getIndexToDel;
    String getName, fileName, content, ctc, getName1, getText, MainNameNewProject, getTextCodeNewProject, getPathToBackgroundS;

    JButton DefaultButton = new JButton("Default preset");
    JLabel AddProjTitle = new JLabel("", SwingConstants.CENTER);
    JButton ConfirmButton = new JButton();
    JTextArea CodeNewProj = new JTextArea();
    //-----------------Переменные для BackgroundSettings----------------------------
    File NewBackgroundFileF, ExFileF;
    File PathToBackgroundF = new File(desktop_path + "\\App-for-projects\\Settings\\Backgrounds\\PathToBackground.txt");
    BufferedImage ExFileNewBackgroundBI;
    String PathToFileS;
    //-------------------------Переменные для Language Settings----------------------------------------------------
    File PathToLanguageF = new File(desktop_path + "\\App-for-projects\\Settings\\Language\\MainLanguage.txt");
    String getLanguageS;
    //--------------------------------------------------------------------------------------------------------------
    File PathToThemeF = new File(desktop_path + "\\App-for-projects\\Settings\\Theme\\MainTheme.txt");
    String getThemeS;
    Color color;

    ImageIcon delete_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\delete.png");
    ImageIcon error_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\error-message.png");
    ImageIcon volume_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\volume.png");
    ImageIcon colors_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\colors.png");
    ImageIcon background_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\background_ico.png");
    ImageIcon fonts_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\fonts_ico.png");
    ImageIcon language_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\language_ico.png");
    ImageIcon rus_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\rus.png");
    ImageIcon eng_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\eng.png");
    ImageIcon german_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\german.png");
    ImageIcon idk_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\idk.png");

    public ALL_PROJ() {
        FrameSettings();
        FrameElements();
    }

    private void FrameSettings() {
        horizontal = sSize.width;
        vertical = sSize.height;
        main_window.setSize(horizontal / 2, vertical - 130);
        main_window.getContentPane().setBackground(Color.lightGray);
        main_window.setLocationRelativeTo(null);
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_window.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));//крестообразный курсор

    }// оконные настройки размер, цвет и т.д.

    public void show() {
        main_window.setVisible(true);
    }// метод для компиляции кода

    private static synchronized void playSound(File sound) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(String.valueOf(sound)));
                clip.open(inputStream);
                clip.start();
            } catch (Exception ignored){}
        }).start();
    }//метод для проигрывания звука(.wav)

    private void setLanguage(){
        try{
            getLanguageS = Files.lines(Paths.get(desktop_path + "\\App-for-projects\\Settings\\Language\\MainLanguage.txt")).reduce("", (a, b) -> a + "\n" + b);
        }catch (IOException e){
            e.printStackTrace();
        }

        if(!getLanguageS.trim().equals("ENG") && !getLanguageS.trim().equals("RU") && !getLanguageS.trim().equals("GERMAN")){
            getLanguageS = "ENG";
        }
    }

    private void getBackground(){
        try{
            getPathToBackgroundS = Files.lines(Paths.get(desktop_path + "\\App-for-projects\\Settings\\Backgrounds\\PathToBackground.txt")).reduce("", (a, b) -> a + "\n" + b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExFileF = new File(getPathToBackgroundS);

        if(getPathToBackgroundS.trim().equals("")){
            ExFileNewBackgroundBI = null;
        }
        else {
            try {
                ExFileNewBackgroundBI = ImageIO.read(new File(getPathToBackgroundS.trim()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getTheme(){
        try {
            getThemeS = Files.lines(Paths.get(desktop_path + "\\App-for-projects\\Settings\\Theme\\MainTheme.txt")).reduce("", (a, b) -> a + "\n" + b);
        }catch (IOException en){
            en.printStackTrace();
        }
    }

    private void setTheme(JList<String> list, JButton clean, JButton settings, JButton default_set, JTextArea area){
        if(Objects.equals(getThemeS.trim(), "default")){
            list.setForeground(new Color(188, 188, 188));
            list.setBackground(new Color(26, 26, 42));
            area.setForeground(new Color(209, 161, 226));
            clean.setBackground(new Color(31, 30, 30));
            clean.setForeground(new Color(227, 218, 218));
            settings.setBackground(new Color(31, 30, 30));
            settings.setForeground(new Color(227, 218, 218));
        }
        else {
            Scanner sc = new Scanner(getThemeS);
            sc.useDelimiter("\\D+");
            color = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

            list.setBackground(color);

            clean.setBackground(color);

            settings.setBackground(color);

            default_set.setBackground(color);

            area.setForeground(color);
        }

    }

    private void changeLanguage(String language, JButton cl_button, JButton settings_button,
                                DefaultListModel<String> list, JTextArea Area, JLabel titleForAddProject, JButton confirmButton,
                                JTextArea areaForAddProject){

        if(Objects.equals(language, "RU")){
            main_window.setTitle("Приложение для проектов");
            cl_button.setText("ОЧИСТИТЬ");
            settings_button.setText("Настройки");
            list.setElementAt("<Добавить проект>", 0);
            titleForAddProject.setText("Добавить проект");
            confirmButton.setText("Подтвердить");
            areaForAddProject.setText("//введите свой код здесь");

            Area.setText("-------------------------------ИНФОРМАЦИЯ-----------------------------------------\n" +
                    " 1) Щелкните ПКМ (в окне кода), чтобы быстро скопировать текст\n" +
                    " 2) Чтобы удалить проект, просто нажмите на нужный вам проект\n" +
                    "  удалить и нажать 'правую кнопку мыши'\n" +
                    " 3) ВСЕ ПРОЕКТЫ хранятся в папке 'Projects'\n" +
                    " 4) Pss, для того, чтобы добавить проект, не обязательно нажимать\n" +
                    "  '<Добавить проект>' все время, вы можете нажать на любой проект)\n" +
                    " 5) Если у вас уже есть готовые проекты в .txt файлах, просто\n" +
                    "  перенесите их в папку 'Projects'\n" +
                    "----------------------------------------------------------------------------------------------\n" +
                    "Вот и все) \n" +
                    ":D");

        }

        else if(Objects.equals(language, "ENG")){
            main_window.setTitle("App-for-projects");
            cl_button.setText("CLEAN");
            settings_button.setText("Settings");
            list.setElementAt("<Add new Project>", 0);
            titleForAddProject.setText("Add new Project");
            confirmButton.setText("Confirm");
            areaForAddProject.setText("//enter your code here");

            Area.setText("-----------------------------------------------INFO-----------------------------------------\n" +
                    "    1) Click MOUSE3(on Code Window) to FAST COPY\n" +
                    "    2) To delete a project just click on the project you need to\n" +
                    "     delete and click 'right mouse button'\n" +
                    "    3) ALL PROJECTS are stored in the 'Projects' folder\n" +
                    "    4) Pss, in order to add a project, it is not necessary to press\n" +
                    "    '<Add new Project>' all the time, you can click on any project)\n" +
                    "    5) If you already have ready-made projects in .txt files, just\n" +
                    "     transfer them to the 'Projects' folder\n" +
                    "------------------------------------------------------------------------------------------------\n" +
                    "That's all) \n" +
                    ":D");
        }

        else if(Objects.equals(language, "GERMAN")){
            main_window.setTitle("App für Projekte");
            cl_button.setText("REINIGEN");
            settings_button.setText("die Einstellungen");
            list.setElementAt("<Projekt hinzufügen>", 0);
            titleForAddProject.setText("Neues Projekt hinzufügen");
            confirmButton.setText("Bestätigen");
            areaForAddProject.setText("//geben Sie hier Ihren Code ein");
            Area.setText("-----------------------------------------------INFOS-----------------------------------------\n" +
                    " 1) Klicken Rechte Maustaste (im Codefenster), um SCHNELL ZU KOPIEREN\n" +
                    " 2) Um ein Projekt zu löschen, klicken Sie einfach auf das Projekt, das Sie löschen möchten\n" +
                    "   löschen und 'rechte Maustaste' anklicken\n" +
                    " 3) ALLE PROJEKTE werden im Ordner 'Projekte' gespeichert\n" +
                    " 4) Pss, um ein Projekt hinzuzufügen, ist es nicht notwendig, \n" +
                    "  zu drücken '<Projekt hinzufügen>', Sie können auf jedes Projekt klicken) \n" +
                    " 5) Wenn Sie bereits fertige Projekte in haben .txt-Dateien, nur\n" +
                    "   übertragen Sie sie in den Ordner 'Projekte'\n" +
                    "------------------------------------------------------------------------------------------------\n" +
                    "Das ist alles)\n" +
                    ":D");
        }

    }

    //---------------------------Ищет файлы .txt в папке с проектом---------------------------
    private static void searchFiles(File rootFile, ArrayList<File> fileList){
        if(rootFile.isDirectory()){
            File[] directoryFiles = rootFile.listFiles();
            if(directoryFiles != null){
                for(File file : directoryFiles){
                    if(file.isDirectory()){
                        searchFiles(file, fileList);
                    }
                    else {
                        if(file.getName().toLowerCase().endsWith(".txt")){
                            fileList.add(file);

                        }
                    }
                }
            }
        }
    }

    //---------------------ВСЕ элементы в окне-------------------------------
    private void FrameElements() {
        setLanguage();
        getBackground();
        getTheme();

        TransparentJPanel.CustomJPanel extension_panel = new TransparentJPanel.CustomJPanel(ExFileNewBackgroundBI);
        extension_panel.setLayout(new BorderLayout());
        Container mainContainer = main_window.getContentPane();//основная область для элементов юез рамки "ГОТОВО"
        mainContainer.add(extension_panel);
        //----------------Список всех проектов----------------------------
        DefaultListModel<String> ListDoneProjects = new DefaultListModel<>();
        ListDoneProjects.addElement("<Add new Project>");
        //------------------Для добавления уже существующих проектов-------------------------
        ArrayList<File> fileList = new ArrayList<>();
        ALL_PROJ.searchFiles(new File(desktop_path + "\\App-for-projects\\Projects"), fileList);

        for (File file : fileList) {
            ListDoneProjects.addElement(file.getName().replace(".txt", ""));
        }

        //--------------
        JList<String> MainList = new JList<>(ListDoneProjects);
        MainList.setForeground(new Color(188, 188, 188));
        MainList.setBackground(new Color(26, 26, 42));
        MainList.setFont(new Font("century gothic", Font.BOLD, 24));
        MainList.setOpaque(true);
        JScrollPane pane = new JScrollPane(MainList);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainContainer.add(pane, BorderLayout.WEST);
        //---------------Окно в котором появляется код----------------------
        JTextArea CodeWindow = new JTextArea();
        CodeWindow.setSelectedTextColor(new Color(129, 11, 4, 224));
        CodeWindow.setSelectionColor(new Color(50, 28, 99));
        CodeWindow.setOpaque(false);
        CodeWindow.setForeground(new Color(209, 161, 226));
        CodeWindow.setFont(new Font("Arial", Font.BOLD, 20));
        extension_panel.add(CodeWindow);

        JScrollPane ScrollForCodeWindow = new JScrollPane(CodeWindow);// Scroll для перемотки(горизонтальный и вертикальный)
        ScrollForCodeWindow.getVerticalScrollBar().setUnitIncrement(11);
        ScrollForCodeWindow.getViewport().setOpaque(false);
        ScrollForCodeWindow.setOpaque(false);
        ScrollForCodeWindow.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollForCodeWindow.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        extension_panel.add(ScrollForCodeWindow);

        MainList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                main_window.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent y) {
                main_window.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        });

        //-----------------------Функция выбора проекта------------------------------------
        MainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MainList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    count_of_index = MainList.getSelectedIndex();

                    if(count_of_index >= 1) {
                        playSound(click_sound);
                        getName = ListDoneProjects.getElementAt(count_of_index);

                        fileName = desktop_path + "\\App-for-projects\\Projects\\" + getName + ".txt";
                        content = "";
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content.trim());
                    }
                }
            }
        });
        //-----------------------Сохраняем изменения кода проекта пользователем--------------------------
        CodeWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                getText = CodeWindow.getText();

                if(!Objects.equals(content, getText)){
                    try {
                        FileWriter f2 = new FileWriter(desktop_path + "\\App-for-projects\\Projects\\" + getName + ".txt", false);
                        f2.write(getText.trim());
                        f2.close();

                    } catch (IOException en) {
                        en.printStackTrace();
                    }
                }
            }
        });
        //---------Копирование текста с помощью ПКМ--------------------------------
        CodeWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    ctc = CodeWindow.getText();
                    StringSelection stringSelection = new StringSelection(ctc);
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                }

            }
        });

        //-------------------Для добавления нового проекта-----------------------------
        MainList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && count_of_index == 0) {//     создаём новое окно для добавки проектов
                    JFrame WindowForAddProject = new JFrame("Add Project");
                    WindowForAddProject.setResizable(false);
                    WindowForAddProject.setSize((int) (horizontal / 2.5), (int) (vertical / 1.4));
                    WindowForAddProject.setLocationRelativeTo(null);
                    WindowForAddProject.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//    Код не завершается при выходе
                    WindowForAddProject.setVisible(true);
                    //--------------------------------------------------------------
                    Container DoubleContAddProj = WindowForAddProject.getContentPane();//  новый контейнер для элементов
                    //--------------------------------------------------------------
                    JPanel TitleTheme = new JPanel();// панель для title, находится вверху
                    TitleTheme.setBackground(new Color(26, 26, 42));
                    DoubleContAddProj.add(TitleTheme, BorderLayout.NORTH);
                    AddProjTitle.setForeground(new Color(188, 188, 188));
                    AddProjTitle.setFont(new Font(null, Font.BOLD, 28));
                    TitleTheme.add(AddProjTitle, BorderLayout.NORTH);
                    //-----------------------------------------------------------------
                    JPanel TextFields = new JPanel();// панель для текстовых полей, посередине
                    Box box = Box.createVerticalBox();
                    Box box1 = Box.createVerticalBox();
                    TextFields.add(box1);
                    TextFields.add(box);
                    TextFields.setBackground(new Color(19, 32, 70));
                    TextFields.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    DoubleContAddProj.add(TextFields);
                    //----
                    JTextField NameNewProj = new JTextField(30);
                    NameNewProj.setDocument(new JTextFieldLimit(19));// максимальное кол-во символов Name
                    NameNewProj.setBackground(new Color(9, 50, 50));
                    NameNewProj.setForeground(new Color(144, 144, 144));
                    NameNewProj.setFont(new Font(null, Font.BOLD, 18));
                    NameNewProj.setText("Title");
                    NameNewProj.setHorizontalAlignment(JLabel.CENTER);
                    box.add(NameNewProj, BorderLayout.NORTH);

                    JTextField IndexField = new JTextField(10);
                    getLast_index = MainList.getLastVisibleIndex();
                    IndexField.setText(String.valueOf(getLast_index + 1));
                    IndexField.setEditable(false);
                    IndexField.setBackground(new Color(5, 32, 32));
                    IndexField.setForeground(new Color(198, 4, 4));
                    IndexField.setFont(new Font(null, Font.BOLD, 18));
                    IndexField.setHorizontalAlignment(JLabel.CENTER);
                    box.add(IndexField);
                    //--------------------------------------------------------------
                    CodeNewProj.setRows(vertical / 47);
                    CodeNewProj.setBackground(new Color(9,1,28));
                    CodeNewProj.setForeground(new Color(209, 161, 226));
                    CodeNewProj.setFont(new Font(null, Font.BOLD, 18));
                    box.add(CodeNewProj);

                    JScrollPane ScrollForCodeNewProj = new JScrollPane(CodeNewProj);
                    ScrollForCodeNewProj.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    ScrollForCodeNewProj.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                    ScrollForCodeNewProj.getVerticalScrollBar().setUnitIncrement(11);

                    ScrollForCodeNewProj.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                        @Override
                        protected void configureScrollBarColors(){
                            this.thumbColor = new Color(25, 41, 105, 242);
                        }
                    });
                    ScrollForCodeNewProj.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
                        @Override
                        protected void configureScrollBarColors(){
                            this.thumbColor = new Color(25, 41, 105, 242);
                        }
                    });
                    box.add(ScrollForCodeNewProj);

                    ConfirmButton.setFont(new Font(null, Font.BOLD, 22));
                    ConfirmButton.setForeground(Color.WHITE);
                    ConfirmButton.setBackground(new Color(22, 137, 12));
                    DoubleContAddProj.add(ConfirmButton, BorderLayout.SOUTH);

                    ConfirmButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            MainNameNewProject = NameNewProj.getText();// Name NewProject
                            getTextCodeNewProject = CodeNewProj.getText();//text from NewCodeWindow

                            if (!Objects.equals(MainNameNewProject, "") &&
                                    !Objects.equals(getTextCodeNewProject, "")){
                                {
                                    try {
                                        File createTxtFile = new File(desktop_path + "\\App-for-projects\\Projects\\" + MainNameNewProject + ".txt");

                                        // if file do not exist, then create it
                                        if (!createTxtFile.exists()) {
                                            createTxtFile.createNewFile();
                                            FileWriter fw = new FileWriter(createTxtFile.getAbsoluteFile());
                                            BufferedWriter bw = new BufferedWriter(fw);
                                            bw.write(getTextCodeNewProject);
                                            bw.close();

                                            ListDoneProjects.addElement(MainNameNewProject);
                                            WindowForAddProject.setVisible(false);
                                        }
                                        else{
                                            playSound(error_sound);
                                            JOptionPane.showMessageDialog(WindowForAddProject, "File already exist...",
                                                    "ERROR",
                                                    JOptionPane.ERROR_MESSAGE, error_icon);
                                        }
                                    }
                                    catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });

        //------------------Кнопка Очистить-------------------------------
        JButton ButtonForClean = new JButton();
        ButtonForClean.setFont(new Font("century gothic", Font.BOLD, 19));
        ButtonForClean.setSize(450, 200);
        ButtonForClean.setBackground(new Color(31, 30, 30));
        ButtonForClean.setForeground(new Color(227, 218, 218));
        mainContainer.add(ButtonForClean, BorderLayout.SOUTH);

        ButtonForClean.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent y) {
                main_window.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent x) {
                main_window.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                int ButtonClickMask = e.getModifiers();
                if ((ButtonClickMask & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
                    CodeWindow.setText("");
                }
            }
        });

        //-----------------Удаление проектов с помощью ПКМ при наведении на Main List------------
        MainList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    playSound(delete_sound);
                    getIndexToDel = MainList.getSelectedIndex();
                    getName1 = ListDoneProjects.getElementAt(getIndexToDel);

                    Object[] options = {"Yes", "No", "Cancel"};
                    int n = JOptionPane.showOptionDialog(main_window,
                            "Do you really want to delete the project?", "Delete?",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                            delete_icon, options, options[2]);

                    if(n == 0){//yes
                        try {
                            Files.delete(Paths.get(desktop_path + "\\App-for-projects\\Projects\\" + getName1 + ".txt"));
                        } catch (IOException x) {
                            x.printStackTrace();
                        }
                        ListDoneProjects.remove(getIndexToDel);
                    }
                }
            }
        });

        //--------------------Цвета Scroll панелей-------------------------------

        ScrollForCodeWindow.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(25, 41, 105, 242);
            }
        });
        ScrollForCodeWindow.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(25, 41, 105, 242);
            }
        });
        pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(20, 7, 67, 232);
            }
        });
        //-----------------------------Настройки--------------------------------
        JButton SettingsButton = new JButton();
        SettingsButton.setFont(new Font("century gothic", Font.BOLD, 19));
        SettingsButton.setBackground(new Color(31, 30, 30));
        SettingsButton.setForeground(new Color(227, 218, 218));
        mainContainer.add(SettingsButton, BorderLayout.NORTH);

        SettingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent y) {
                main_window.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent x) {
                main_window.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame SettingsFrame = new JFrame("Settings");
                SettingsFrame.setSize((int) (horizontal / 4.5), vertical / 3);
                SettingsFrame.setResizable(false);
                SettingsFrame.setLocationRelativeTo(main_window);
                SettingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                SettingsFrame.setVisible(true);

                Container settings_Cont = SettingsFrame.getContentPane();

                //----------------------------Title и место для него---------------------
                JPanel PlaceForTitle_settings = new JPanel();
                PlaceForTitle_settings.setBackground(new Color(26, 26, 42));
                settings_Cont.add(PlaceForTitle_settings, BorderLayout.NORTH);

                JLabel Title_settings = new JLabel("What do you want to configure?", SwingConstants.CENTER);
                Title_settings.setFont(new Font(null, Font.BOLD, 26));
                Title_settings.setForeground(new Color(227, 218, 218));
                PlaceForTitle_settings.add(Title_settings);
                //-------------------------Кнопочки---------------------------------
                JPanel MenuPane = new JPanel();
                MenuPane.setLayout(new GridBagLayout());
                MenuPane.setBackground(new Color(9, 32, 70));
                SettingsFrame.add(MenuPane);
                //-----------------Какая-то хрень, вообще хз---------------
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;

                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                //---------------------------------------------------------
                JPanel buttons = new JPanel(new GridBagLayout());
                //-----------
                JButton AudioButton = new JButton("Audio");
                AudioButton.setFont(new Font(null, Font.BOLD, 24));

                JButton ThemeButton = new JButton("Theme");
                ThemeButton.setFont(new Font(null, Font.BOLD, 24));

                JButton BackgroundButton = new JButton("Background");
                BackgroundButton.setFont(new Font(null, Font.BOLD, 24));

                JButton FontsButton = new JButton("Fonts");
                FontsButton.setFont(new Font(null, Font.BOLD, 24));

                JButton LanguageButton = new JButton("Language");
                LanguageButton.setFont(new Font(null, Font.BOLD, 24));

                if(color == null){
                    DefaultButton.setBackground(Color.WHITE);
                    DefaultButton.setForeground(Color.BLACK);
                }
                else {
                    DefaultButton.setBackground(color);
                    DefaultButton.setForeground(Color.WHITE);
                }
                DefaultButton.setFont(new Font(null, Font.BOLD, 24));
                //----------
                buttons.add(AudioButton, gbc);
                buttons.add(ThemeButton, gbc);
                buttons.add(BackgroundButton, gbc);
                buttons.add(FontsButton, gbc);
                buttons.add(LanguageButton, gbc);
                buttons.add(DefaultButton, gbc);
                //----------
                gbc.weighty = 1;
                MenuPane.add(buttons, gbc);
                //----------

                AudioButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame AudioSettings = new JFrame("Audio Settings");
                        AudioSettings.setSize((int) (horizontal / 3), (int) (vertical / 2.2));
                        AudioSettings.setLocationRelativeTo(SettingsFrame);
                        AudioSettings.setResizable(false);
                        AudioSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        AudioSettings.setVisible(true);
                        SettingsFrame.setVisible(false);

                        Container audio_cont = AudioSettings.getContentPane();

                        //---------------
                        JPanel PlaceForTitle_Audio = new JPanel();
                        PlaceForTitle_Audio.setBackground(new Color(26, 26, 42));
                        audio_cont.add(PlaceForTitle_Audio, BorderLayout.NORTH);

                        JLabel Title_audio = new JLabel(volume_icon, SwingConstants.CENTER);
                        PlaceForTitle_Audio.add(Title_audio);
                        //---------------
                        JPanel MiddlePanel_Audio = new JPanel();
                        MiddlePanel_Audio.setBackground(Color.red);
                        audio_cont.add(MiddlePanel_Audio);

                        AudioSettings.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                SettingsFrame.setVisible(true);
                            }
                        });
                    }

                    @Override
                    public void mouseEntered(MouseEvent x) {
                        AudioButton.setBackground(Color.BLACK);
                        AudioButton.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent y) {
                        AudioButton.setBackground(Color.WHITE);
                        AudioButton.setForeground(Color.BLACK);
                    }
                });

                ThemeButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame ThemeSettings = new JFrame("Theme Settings");
                        ThemeSettings.setSize(horizontal / 3, (int) (vertical / 2.2));
                        ThemeSettings.setLocationRelativeTo(SettingsFrame);
                        ThemeSettings.setResizable(false);
                        ThemeSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        ThemeSettings.setVisible(true);
                        SettingsFrame.setVisible(false);

                        Container theme_cont = ThemeSettings.getContentPane();

                        JPanel PlaceForTitle_Theme = new JPanel();
                        PlaceForTitle_Theme.setBackground(new Color(26, 26, 42));
                        theme_cont.add(PlaceForTitle_Theme, BorderLayout.NORTH);

                        JLabel Title_theme = new JLabel(colors_icon, SwingConstants.CENTER);
                        PlaceForTitle_Theme.add(Title_theme);

                        JPanel MiddlePanel_theme = new JPanel();
                        MiddlePanel_theme.setLayout(new GridBagLayout());
                        MiddlePanel_theme.setBorder(new EmptyBorder(10,10,10,10));
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridwidth = GridBagConstraints.REMAINDER;
                        gbc.anchor = GridBagConstraints.CENTER;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        gbc.weighty = 1;
                        theme_cont.add(MiddlePanel_theme);

                        ColorChooserButton colorChooserButton = new ColorChooserButton(ThemeSettings);
                        if(color == null){
                            colorChooserButton.setForeground(Color.WHITE);
                        }
                        else {
                            colorChooserButton.setForeground(color);
                            MiddlePanel_theme.setBackground(colorChooserButton.getForeground());
                        }
                        MiddlePanel_theme.add(colorChooserButton);

                        colorChooserButton.addChangeListener(e1 -> {
                            MiddlePanel_theme.setBackground(colorChooserButton.getForeground());

                            try{
                                FileWriter fw = new FileWriter(PathToThemeF.getAbsoluteFile());
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write(String.valueOf(colorChooserButton.getForeground()));
                                bw.close();
                            }catch (IOException en){
                                en.printStackTrace();
                            }

                            getTheme();
                            setTheme(MainList, ButtonForClean, SettingsButton, DefaultButton, CodeWindow);
                        });


                        ThemeSettings.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                SettingsFrame.setVisible(true);
                            }
                        });
                    }

                    @Override
                    public void mouseEntered(MouseEvent x) {
                        ThemeButton.setBackground(Color.BLACK);
                        ThemeButton.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent y) {
                        ThemeButton.setBackground(Color.WHITE);
                        ThemeButton.setForeground(Color.BLACK);
                    }
                });

                BackgroundButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame BackgroundSettings = new JFrame("Background Settings");
                        BackgroundSettings.setSize(horizontal / 3, (int) (vertical / 2.2));
                        BackgroundSettings.setLocationRelativeTo(SettingsFrame);
                        BackgroundSettings.setResizable(false);
                        BackgroundSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        BackgroundSettings.setVisible(true);
                        SettingsFrame.setVisible(false);

                        Container background_cont = BackgroundSettings.getContentPane();

                        JPanel PlaceForTitle_background = new JPanel();
                        PlaceForTitle_background.setBackground(new Color(26, 26, 42));
                        background_cont.add(PlaceForTitle_background, BorderLayout.NORTH);

                        JLabel Title_background = new JLabel(background_icon, SwingConstants.CENTER);
                        PlaceForTitle_background.add(Title_background);

                        TransparentJPanel.CustomJPanel MiddlePanel_background = new TransparentJPanel.CustomJPanel(ExFileNewBackgroundBI);
                        MiddlePanel_background.setLayout(new GridBagLayout());
                        MiddlePanel_background.setBorder(new EmptyBorder(10,10,10,10));
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridwidth = GridBagConstraints.REMAINDER;
                        gbc.anchor = GridBagConstraints.CENTER;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        gbc.weighty = 1;
                        background_cont.add(MiddlePanel_background);

                        JButton NewBackgroundButton = new JButton("Background image...");
                        if(ExFileNewBackgroundBI != null){
                            NewBackgroundButton.setText(ExFileF.getName());
                        }
                        NewBackgroundButton.setFont(new Font("century gothic", Font.BOLD, 20));
                        MiddlePanel_background.add(NewBackgroundButton, gbc);

                        NewBackgroundButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                JFileChooser fileopen = new JFileChooser();
                                fileopen.setAcceptAllFileFilterUsed(false);
                                fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                fileopen.addChoosableFileFilter(new FileNameExtensionFilter("(.png), (.jpeg), (.jpg)", "png", "jpeg", "jpg"));
                                int ret = fileopen.showDialog(BackgroundSettings, "Select an image");
                                if (ret == JFileChooser.APPROVE_OPTION) {
                                    ExFileF = fileopen.getSelectedFile();
                                    NewBackgroundButton.setText(ExFileF.getName());
                                    PathToFileS = ExFileF.getAbsolutePath();
                                    NewBackgroundFileF = new File(PathToFileS);

                                    //----------------------Конвертируем File в BufferedImage-------------------------------
                                    try {
                                        ExFileNewBackgroundBI = ImageIO.read(new File(PathToFileS));
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    //---------------------Записываем путь к background в txt файл--------------
                                    try{
                                        FileWriter fw = new FileWriter(PathToBackgroundF.getAbsoluteFile());
                                        BufferedWriter bw = new BufferedWriter(fw);
                                        bw.write(String.valueOf(NewBackgroundFileF));
                                        bw.close();
                                    }catch (IOException en){
                                        en.printStackTrace();
                                    }

                                    //-------------------------------------------------------------
                                }

                            }

                        });
                        BackgroundSettings.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                SettingsFrame.setVisible(true);
                            }
                        });
                    }

                    @Override
                    public void mouseEntered(MouseEvent x) {
                        BackgroundButton.setBackground(Color.BLACK);
                        BackgroundButton.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent y) {
                        BackgroundButton.setBackground(Color.WHITE);
                        BackgroundButton.setForeground(Color.BLACK);
                    }
                });

                FontsButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame FontsSettings = new JFrame("Fonts Settings");
                        FontsSettings.setSize((int) (horizontal / 3), (int) (vertical / 2.2));
                        FontsSettings.setLocationRelativeTo(SettingsFrame);
                        FontsSettings.setResizable(false);
                        FontsSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        FontsSettings.setVisible(true);
                        SettingsFrame.setVisible(false);

                        Container fonts_cont = FontsSettings.getContentPane();

                        JPanel PlaceForTitle_fonts = new JPanel();
                        PlaceForTitle_fonts.setBackground(new Color(26, 26, 42));
                        fonts_cont.add(PlaceForTitle_fonts, BorderLayout.NORTH);

                        JLabel Title_fonts = new JLabel(fonts_icon, SwingConstants.CENTER);
                        PlaceForTitle_fonts.add(Title_fonts);

                        FontsSettings.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                SettingsFrame.setVisible(true);
                            }
                        });
                    }

                    @Override
                    public void mouseEntered(MouseEvent x) {
                        FontsButton.setBackground(Color.BLACK);
                        FontsButton.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent y) {
                        FontsButton.setBackground(Color.WHITE);
                        FontsButton.setForeground(Color.BLACK);
                    }
                });

                LanguageButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame LanguageSettings = new JFrame("Language Settings");
                        LanguageSettings.setSize(horizontal / 3, (int) (vertical / 2.2));
                        LanguageSettings.setLocationRelativeTo(SettingsFrame);
                        LanguageSettings.setResizable(false);
                        LanguageSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        LanguageSettings.setVisible(true);
                        SettingsFrame.setVisible(false);

                        Container language_cont = LanguageSettings.getContentPane();

                        JPanel PlaceForTitle_language = new JPanel();
                        PlaceForTitle_language.setBackground(new Color(26, 26, 42));
                        language_cont.add(PlaceForTitle_language, BorderLayout.NORTH);

                        JLabel Title_language = new JLabel(language_icon, SwingConstants.CENTER);
                        PlaceForTitle_language.add(Title_language);

                        TransparentJPanel.CustomJPanel MiddlePanel_language = new TransparentJPanel.CustomJPanel(ExFileNewBackgroundBI);
                        MiddlePanel_language.setLayout(new GridBagLayout());
                        MiddlePanel_language.setBorder(new EmptyBorder(10,10,10,10));
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridwidth = GridBagConstraints.REMAINDER;
                        gbc.anchor = GridBagConstraints.CENTER;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        gbc.weighty = 1;
                        language_cont.add(MiddlePanel_language);

                        JButton RusButton = new JButton(rus_icon);

                        JButton EngButton = new JButton(eng_icon);

                        JButton GermanButton = new JButton(german_icon);

                        switch (getLanguageS.trim()) {
                            case "RU":
                                RusButton.setBackground(new Color(54, 178, 7));
                                break;
                            case "ENG":
                                EngButton.setBackground(new Color(54, 178, 7));
                                break;
                            case "GERMAN":
                                GermanButton.setBackground(new Color(54, 178, 7));
                                break;
                        }

                        MiddlePanel_language.add(GermanButton, gbc);
                        MiddlePanel_language.add(RusButton, gbc);
                        MiddlePanel_language.add(EngButton, gbc);

                        RusButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                RusButton.setBackground(new Color(54, 178, 7));
                                GermanButton.setBackground(Color.WHITE);
                                EngButton.setBackground(Color.WHITE);

                                try{
                                    FileWriter fw = new FileWriter(PathToLanguageF.getAbsoluteFile());
                                    BufferedWriter bw = new BufferedWriter(fw);
                                    bw.write("RU");
                                    bw.close();
                                }catch (IOException en){
                                    en.printStackTrace();
                                }
                                setLanguage();
                                changeLanguage(getLanguageS.trim(), ButtonForClean, SettingsButton, ListDoneProjects, CodeWindow, AddProjTitle, ConfirmButton, CodeNewProj);
                            }
                        });
                        EngButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                EngButton.setBackground(new Color(54, 178, 7));
                                RusButton.setBackground(Color.WHITE);
                                GermanButton.setBackground(Color.WHITE);

                                try{
                                    FileWriter fw = new FileWriter(PathToLanguageF.getAbsoluteFile());
                                    BufferedWriter bw = new BufferedWriter(fw);
                                    bw.write("ENG");
                                    bw.close();
                                }catch (IOException en){
                                    en.printStackTrace();
                                }

                                setLanguage();
                                changeLanguage(getLanguageS.trim(), ButtonForClean, SettingsButton, ListDoneProjects, CodeWindow, AddProjTitle, ConfirmButton, CodeNewProj);

                            }
                        });
                        GermanButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                GermanButton.setBackground(new Color(54, 178, 7));
                                RusButton.setBackground(Color.WHITE);
                                EngButton.setBackground(Color.WHITE);

                                try{
                                    FileWriter fw = new FileWriter(PathToLanguageF.getAbsoluteFile());
                                    BufferedWriter bw = new BufferedWriter(fw);
                                    bw.write("GERMAN");
                                    bw.close();
                                }catch (IOException en){
                                    en.printStackTrace();
                                }

                                setLanguage();
                                changeLanguage(getLanguageS.trim(), ButtonForClean, SettingsButton, ListDoneProjects, CodeWindow, AddProjTitle, ConfirmButton, CodeNewProj);
                            }
                        });

                        LanguageSettings.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                SettingsFrame.setVisible(true);
                            }
                        });
                    }

                    @Override
                    public void mouseEntered(MouseEvent x) {
                        LanguageButton.setBackground(Color.BLACK);
                        LanguageButton.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent y) {
                        LanguageButton.setBackground(Color.WHITE);
                        LanguageButton.setForeground(Color.BLACK);
                    }
                });

                DefaultButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Object[] options = {"Yes", "No", "Cancel"};
                        int n = JOptionPane.showOptionDialog(main_window,
                                "Do you really wanna go back to the standard settings?", "?",
                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                idk_icon, options, options[2]);

                        if(n == 0){
                            //--------------------Ставим дефолтный язык 'eng'-----------------------------------
                            try{
                                FileWriter fw = new FileWriter(PathToLanguageF.getAbsoluteFile());
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write("ENG");
                                bw.close();
                            }catch (IOException en){
                                en.printStackTrace();
                            }
                            //-------------------------Ставим дефолтный background-------------------------------------
                            try{
                                FileWriter fw = new FileWriter(PathToBackgroundF.getAbsoluteFile());
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write("");
                                bw.close();
                            }catch (IOException en){
                                en.printStackTrace();
                            }
                            //---------------------------Ставим дефолтную тему------------------------------------------------
                            try{
                                FileWriter fw = new FileWriter(PathToThemeF.getAbsoluteFile());
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write("default");
                                bw.close();
                            }catch (IOException en){
                                en.printStackTrace();
                            }

                        }
                    }
                });
            }
        });
        changeLanguage(getLanguageS.trim(), ButtonForClean, SettingsButton, ListDoneProjects, CodeWindow, AddProjTitle, ConfirmButton, CodeNewProj);
        setTheme(MainList, ButtonForClean, SettingsButton, DefaultButton, CodeWindow);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~КОНЕЦ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
