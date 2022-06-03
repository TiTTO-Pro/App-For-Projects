package com.app_for_projects;

/**
 * @author TiTTko
 * @version 1.0.9
 */

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class ALL_PROJ extends Thread {
    JFrame main_window = new JFrame("All Projects");//создаём основное окно и название

    // получаем путь к рабочему столу
    File desktop_path = FileSystemView.getFileSystemView().getHomeDirectory();
    //получаем screen size
    Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();

    int getLast_index, count_of_index, vertical, horizontal, getIndexToDel;// последний индекс в списке проектов и не только
    String getName, fileName, content, ctc, getName1, getText;

    ImageIcon icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\delete.png");
    ImageIcon error_icon = new ImageIcon(desktop_path + "\\App-for-projects\\Pictures\\error-message.png");

    public ALL_PROJ() {
        FrameSettings();
        FrameElements();
    }

    private void FrameSettings() {
        horizontal = sSize.width;
        vertical = sSize.height;
        main_window.setSize(horizontal / 2, vertical - 130);
        main_window.getContentPane().setBackground(Color.LIGHT_GRAY);
        main_window.setLocationRelativeTo(null);
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_window.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));//крестообразный курсор

    }// оконные настройки размер, цвет и т.д.

    public void show() {
        main_window.setVisible(true);
    }// метод для компиляции кода

    //---------------------------Ищет файлы .txt в папке с проектом--------------------------------------
    public static void searchFiles(File rootFile, ArrayList<File> fileList){
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

        TransparentJPanel.CustomJPanel extension_panel = new TransparentJPanel.CustomJPanel();
        extension_panel.setLayout(new BorderLayout());
        Container mainContainer = main_window.getContentPane();//основная область для элементов юез рамки "ГОТОВО"
        mainContainer.add(extension_panel);
        JLabel Title = new JLabel("Done Projects:");
        Title.setFont(new Font("Algerian", Font.BOLD, 26));
        Title.setForeground(Color.red);
        mainContainer.add(Title, BorderLayout.NORTH);

        //----------------Список всех проектов----------------------------
        DefaultListModel<String> ListDoneProjects = new DefaultListModel<>();
        //-------------
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
        JScrollPane pane = new JScrollPane(MainList);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainContainer.add(pane, BorderLayout.WEST);
        //---------------Окно в котором появляется код----------------------
        JTextArea CodeWindow = new JTextArea();
        CodeWindow.setSelectedTextColor(new Color(129, 11, 4, 224));
        CodeWindow.setSelectionColor(new Color(50, 28, 99));
        CodeWindow.setOpaque(false);
        CodeWindow.setBackground(new Color(9,1,28));
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
                        getName = ListDoneProjects.getElementAt(count_of_index);
                        fileName = desktop_path + "\\App-for-projects\\Projects\\" + getName + ".txt";
                        content = "";
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
            }
        });
        //----------------------Сохраняем изменения кода проекта пользователем-------------------
        CodeWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                getText = CodeWindow.getText();

                if (!Objects.equals(getText, content)) {
                    try {
                        FileWriter f2 = new FileWriter(desktop_path + "\\App-for-projects\\Projects\\" + getName + ".txt", false);
                        f2.write(getText);
                        f2.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        //-----------------Копирование текста с помощью ПКМ----------------------------------------
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
                    JLabel AddProjTitle = new JLabel("Add new Project", SwingConstants.CENTER);
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
                    NameNewProj.setDocument(new JTextFieldLimit(19));// максимальное кол-во символов для Name
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
                    JTextArea CodeNewProj = new JTextArea();
                    CodeNewProj.setRows(vertical / 47);
                    CodeNewProj.setBackground(new Color(9,1,28));
                    CodeNewProj.setForeground(new Color(209, 161, 226));
                    CodeNewProj.setFont(new Font(null, Font.BOLD, 18));
                    CodeNewProj.setText("//Enter your code here");
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

                    JButton ConfirmButton = new JButton("Confirm");
                    ConfirmButton.setFont(new Font(null, Font.BOLD, 22));
                    ConfirmButton.setForeground(Color.WHITE);
                    ConfirmButton.setBackground(new Color(22, 137, 12));
                    DoubleContAddProj.add(ConfirmButton, BorderLayout.SOUTH);

                    ConfirmButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String MainNameNewProject = NameNewProj.getText();// Name NewProject
                            String getTextCodeNewProject = CodeNewProj.getText();//text from NewCodeWindow

                            if (!Objects.equals(MainNameNewProject, "") &&
                                    !Objects.equals(getTextCodeNewProject, "")){
                                {

                                    try {
                                        File createTxtFile = new File(desktop_path + "\\App-for-projects\\Projects\\" + MainNameNewProject + ".txt");

                                        // if file doesn't exists, then create it
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
        JButton ButtonForClean = new JButton("CLEAN");
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

        //-----------------Удаление НОВЫХ проектов с помощью ПКМ при наведении на Main List------------
        MainList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIndexToDel = MainList.getSelectedIndex();
                getName1 = ListDoneProjects.getElementAt(getIndexToDel);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    Object[] options = {"Yes", "No", "Cancel"};
                    int n = JOptionPane.showOptionDialog(main_window,
                            "Do you really want to delete the project?", "Delete?",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                            icon, options, options[2]);
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

        //-----------------Information-----------------------
        CodeWindow.setText("-----------------------------------------------INFO-------------------------------------------\n" +
                " - Click MOUSE3(on Code Window) to FAST COPY\n" +
                " - To delete a project just click on the project you need to \n" +
                "  delete and click 'right mouse button'\n" +
                " - All ADDED projects are saved in the project ITSELF\n" +
                " - Pss, in order to add a project, it is not necessary to press\n" +
                "'<Tap on Enter>' all the time, you can click on any project)\n" +
                " - If you already have ready-made projects in .txt files, just\n" +
                "  transfer them to the project folder \n" +
                " ------------------------------------------------------------------------------------------------\n" +
                "That's all)\n" +
                ":D");
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

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~КОНЕЦ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
