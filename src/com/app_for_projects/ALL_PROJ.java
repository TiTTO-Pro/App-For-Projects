package com.app_for_projects;

/**
 * @author TiTTO-Pro
 * @version 1.6
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

    public ALL_PROJ() {
        FrameSettings();
        FrameElements();
    }

    int last_index, getLast_index;// последний индекс в списке проектов
    private void FrameSettings() {
        main_window.setSize(1000, 870);
        main_window.getContentPane().setBackground(Color.LIGHT_GRAY);
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

    public static boolean contains(String str, String substr){
        return str.contains(substr);
    }

    //---------------------ВСЕ элементы в окне-------------------------------
    private void FrameElements(){
        JPanel zxc = new JPanel();
        zxc.setLayout(new BorderLayout());
        Container mainContainer = main_window.getContentPane();//основная область для элементов юез рамки "ГОТОВО"
        mainContainer.add(zxc);
        //----------------заголовок "Готовые проекты"------------------------
        JLabel MainTitle = new JLabel("DONE PROJECTS:");// располагается вверху
        MainTitle.setFont(new Font("ALGERIAN", Font.BOLD, 24));
        MainTitle.setForeground(Color.RED);
        mainContainer.add(MainTitle, BorderLayout.NORTH);
        //----------------Список всех проектов----------------------------
        final DefaultListModel<String> ListDoneProjects = new DefaultListModel<>();

        //-------------
        ListDoneProjects.addElement("'<Tap on Enter>'");
        ListDoneProjects.addElement("Calculator");
        ListDoneProjects.addElement("BMI Calculator");
        ListDoneProjects.addElement("Sorted by puz.");// Список с готовыми проектами, Располагается СЛЕВА
        ListDoneProjects.addElement("Score Simulation");
        ListDoneProjects.addElement("RPS<game>");
        ListDoneProjects.addElement("Prime Numbers");
        ListDoneProjects.addElement("Random fur-tree");
        ListDoneProjects.addElement("1000 - 7");
        ListDoneProjects.addElement("Opening Files");
        ListDoneProjects.addElement("Char(FX)");
        ListDoneProjects.addElement("Straight Line");
        ListDoneProjects.addElement("Draw_circle");
        ListDoneProjects.addElement("Y = ax ^ 2");
        ListDoneProjects.addElement("Y = √x");
        ListDoneProjects.addElement("Y = k / x");
        //------------------Для добавления уже существующих проектов-------------------------
        ArrayList<File> fileList = new ArrayList<>();
        ALL_PROJ.searchFiles(new File(desktop_path + "\\App-for-projects"), fileList);
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
        TransparentTextArea.CustomTextArea CodeWindow = new TransparentTextArea.CustomTextArea();
        CodeWindow.setForeground(new Color(209, 161, 226));
        CodeWindow.setFont(new Font("Arial", Font.BOLD, 20));
        zxc.add(CodeWindow);
        JScrollPane ScrollForCodeWindow = new JScrollPane(CodeWindow);// Scroll для перемотки(горизонтальный и вертикальный)
        ScrollForCodeWindow.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollForCodeWindow.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        zxc.add(ScrollForCodeWindow);

        //-----------------------ОСНОВНАЯ ФИГНЯ, Выбор кода------------------------------------
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
        MainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MainList.addListSelectionListener(e -> MainList.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (MainList.getSelectedIndex() == 1) {
                    CodeWindow.setText("import java.util.Objects;\n" +
                            "import java.util.Scanner;\n" +
                            "\n" +
                            "public class Main {\n" +
                            "    public static void main(String[] args) {\n" +
                            "\t// write your code here\n" +
                            "        Scanner num = new Scanner(System.in);\n" +
                            "        Scanner scanner = new Scanner(System.in);\n" +
                            "        boolean isTrue = true;\n" +
                            "        while (isTrue) {\n" +
                            "            int num_1, num_2, res1, res2, res3, res4;\n" +
                            "            String xx;\n" +
                            "            System.out.print(\"Enter 1st num: \");\n" +
                            "            num_1 = num.nextInt();\n" +
                            "            System.out.print(\"+, -, * or /: \");\n" +
                            "            xx = scanner.nextLine();\n" +
                            "            System.out.print(\"Enter 2nd num: \");\n" +
                            "            num_2 = num.nextInt();\n" +
                            "\n" +
                            "            if (Objects.equals(xx, \"+\")) {\n" +
                            "                res1 = num_1 + num_2;\n" +
                            "                System.out.println(\"Result is: \" + res1);\n" +
                            "            } else if (Objects.equals(xx, \"-\")) {\n" +
                            "                res2 = num_1 - num_2;\n" +
                            "                System.out.println(\"Result is: \" + res2);\n" +
                            "            } else if (Objects.equals(xx, \"*\")) {\n" +
                            "                res3 = num_1 * num_2;\n" +
                            "                System.out.println(\"Result is: \" + res3);\n" +
                            "            } else if (Objects.equals(xx, \"/\")) {\n" +
                            "                res4 = num_1 / num_2;\n" +
                            "                System.out.println(\"Result is: \" + res4);\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 2) {
                    CodeWindow.setText("import java.util.Scanner;\n" +
                            "\n" +
                            "class Main {\n" +
                            "    public static void main(String[] args){\n" +
                            "\n" +
                            "        Scanner input = new Scanner(System.in);\n" +
                            "        double weight = 0.0;\n" +
                            "        double height = 0.0;\n" +
                            "        double bmi = 0.0;\n" +
                            "        /*(\"Underweight: Under 18.5\");\n" +
                            "          (\"Normal: 18.5-24.9 \");\n" +
                            "          (\"Overweight: 25-29.9\");\n" +
                            "          (\"Obese: 30 or over\");*/\n" +
                            "\n" +
                            "        System.out.print(\"Enter your weight(kg): \");\n" +
                            "        weight = input.nextInt();\n" +
                            "\n" +
                            "        System.out.print(\"Enter your height(sm): \");\n" +
                            "        height = input.nextInt();\n" +
                            "        height /= 100;\n" +
                            "\n" +
                            " \n" +
                            "        bmi = ((weight)/(height * height));\n" +
                            "        if (bmi > 30)\n" +
                            "            System.out.printf(\"U very fat, go GYM) \");\n" +
                            "        else if (bmi >= 25 & bmi <= 29.9)\n" +
                            "            System.out.printf(\"U have overweight, go GYM too()(( \");\n" +
                            "        else if (bmi >= 18.5 & bmi <= 24.9)\n" +
                            "            System.out.printf(\"Your weight is OK...Good\");\n" +
                            "        else if (bmi < 18.5)\n" +
                            "            System.out.printf(\"U skinny, go in Mac)\");\n" +
                            "\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 3) {
                    CodeWindow.setText("import java.util.Arrays;\n" +
                            "import java.util.Scanner;\n" +
                            "\n" +
                            "class Main {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Scanner input = new Scanner(System.in); //Scanner\n" +
                            "        boolean isFalse = false;\n" +
                            "\n" +
                            "        System.out.print(\"Enter array length: \");\n" +
                            "        int size = input.nextInt(); // Читаем с клавиатуры размер массива и записываем в size\n" +
                            "        int[] array = new int[size]; // Создаём массив int размером в size\n" +
                            "\n" +
                            "        System.out.print(\"Insert array elements: \");\n" +
                            "\n" +
                            "        for (int i = 0; i < size; i++) {\n" +
                            "            array[i] = input.nextInt(); // Заполняем массив элементами, введёнными с клавиатуры\n" +
                            "        }\n" +
                            "\n" +
                            "        int temp, steps = 0;\n" +
                            "\n" +
                            "        while (!isFalse){\n" +
                            "            isFalse = true;\n" +
                            "\n" +
                            "            for (int i = 0; i < array.length - 1; i++){\n" +
                            "                if (array[i] > array[i + 1]){\n" +
                            "                    temp = array[i];\n" +
                            "                    array [i] = array[i + 1];\n" +
                            "                    array[i + 1] = temp;\n" +
                            "                    isFalse = false;\n" +
                            "                    steps += 1;\n" +
                            "                    System.out.println(Arrays.toString(array));\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "        System.out.println(\"Steps -> \" + steps);\n" +
                            "    }\n" +
                            "}\n" +
                            "\n");
                }
                else if (MainList.getSelectedIndex() == 4) {
                    CodeWindow.setText("import java.util.Random;\n" +
                            "\n" +
                            "public class Main {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Random rnd = new Random();\n" +
                            "        Random rnd1_och = new Random();\n" +
                            "        int rnd_count = rnd.nextInt(10) + 1;\n" +
                            "        boolean isTrue = true;\n" +
                            "        while(isTrue) {\n" +
                            "            double count = 0;\n" +
                            "            if (rnd_count < 3){\n" +
                            "                System.out.println(\"Слишком мало оценок...подкопите пж\");\n" +
                            "                break;\n" +
                            "            }\n" +
                            "            for (int i = 0; i < rnd_count; i++){\n" +
                            "                int rnd_marks = rnd1_och.nextInt(4) + 2;\n" +
                            "                count += rnd_marks;\n" +
                            "                System.out.print(rnd_marks + \"; \");\n" +
                            "            }\n" +
                            "            count /= rnd_count;\n" +
                            "            String result = String.format(\"%.2f\",count);\n" +
                            "            System.out.println(\"Средний бал -> \" + result);\n" +
                            "            isTrue = false;\n" +
                            "        }\n" +
                            "    }\n" +
                            "}\n");
                }
                else if (MainList.getSelectedIndex() == 5) {
                    CodeWindow.setText("import java.util.Objects;\n" +
                            "import java.util.Random;\n" +
                            "import java.util.Scanner;\n" +
                            "\n" +
                            "class Main {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        boolean isTrue = true;\n" +
                            "        while(isTrue) {\n" +
                            "            Random rnd = new Random();\n" +
                            "            Scanner cho_tam = new Scanner(System.in);\n" +
                            "            int rnd_num = rnd.nextInt(3);\n" +
                            "\n" +
                            "            System.out.print(\"Камень, ножницы или бумага? -> \");\n" +
                            "            String main_sym = cho_tam.nextLine();\n" +
                            "\n" +
                            "            if (Objects.equals(main_sym, \"камень\"))\n" +
                            "            {\n" +
                            "                if (rnd_num == 0) { //камень\n" +
                            "                    System.out.println(\"Ничья...\" + \" (Ваше - \" + main_sym + \" ) и (Противника - камень)\");\n" +
                            "                } else if (rnd_num == 1) { //ножницы\n" +
                            "                    System.out.println(\"Вы выйграли))\");\n" +
                            "                } else if (rnd_num == 2) { //бумага\n" +
                            "                    System.out.println(\"Вы проиграли((\" + \" (Ваше - \" + main_sym + \" ) и (Противника - бумага)\");\n" +
                            "                }\n" +
                            "            }\n" +
                            "            else if (Objects.equals(main_sym, \"ножницы\"))\n" +
                            "            {\n" +
                            "                if (rnd_num == 0) { //камень\n" +
                            "                    System.out.println(\"Вы проиграли((\" + \" У Противника - камень)\");\n" +
                            "                } else if (rnd_num == 1) { //ножницы\n" +
                            "                    System.out.println(\"Ничья...\" + \" У противника - ножницы)\");\n" +
                            "                } else if (rnd_num == 2) { //бумага\n" +
                            "                    System.out.println(\"Вы выиграли...\");\n" +
                            "                }\n" +
                            "            }\n" +
                            "            else if (Objects.equals(main_sym, \"бумага\"))\n" +
                            "            {\n" +
                            "                if (rnd_num == 0) { //камень\n" +
                            "                    System.out.println(\"Вы выиграли))\");\n" +
                            "                } else if (rnd_num == 1) { //ножницы\n" +
                            "                    System.out.println(\"Вы проиграли\" + \" У противника - ножницы)\");\n" +
                            "                } else if (rnd_num == 2) { //бумага\n" +
                            "                    System.out.println(\"Ничья...\");\n" +
                            "                }\n" +
                            "            }\n" +
                            "\n" +
                            "            else{\n" +
                            "                System.out.println(\"ERROR, try again pls\");\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }\n" +
                            "}\n");
                }
                else if (MainList.getSelectedIndex() == 6) {
                    CodeWindow.setText("import java.util.Scanner;\n" +
                            "\n" +
                            "class Main {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Scanner scanner = new Scanner(System.in);\n" +
                            "        System.out.print(\"-> \");\n" +
                            "        int top = scanner.nextInt();\n" +
                            "        for (int i = 2;i < top;i++){\n" +
                            "            if(checkSimple(i))\n" +
                            "                System.out.println(i);\n" +
                            "        }\n" +
                            "    }\n" +
                            "\n" +
                            "    public static boolean checkSimple(int i){\n" +
                            "        if (i <= 1)\n" +
                            "            return false;\n" +
                            "        else if (i <= 3)\n" +
                            "            return true;\n" +
                            "        else if (i % 2 == 0 || i % 3 == 0)\n" +
                            "            return false;\n" +
                            "        int n = 5;\n" +
                            "        while (n * n <= i){\n" +
                            "            if (i % n == 0 || i % (n + 2) == 0)\n" +
                            "                return false;\n" +
                            "            n += 6;\n" +
                            "        }\n" +
                            "        return true;\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 7) {
                    CodeWindow.setText("import java.util.Scanner;\n" +
                            "import java.util.Random;\n" +
                            "\n" +
                            "public class Main {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Scanner input = new Scanner(System.in);\n" +
                            "        Random rnd = new Random();\n" +
                            "        int rand_sym = rnd.nextInt(50);\n" +
                            "        System.out.print(\"Write one symbol, and i'm drawing fur-tree) -> \");\n" +
                            "        String sym = input.nextLine();\n" +
                            "\n" +
                            "        for (int i = 0; i < rand_sym; i++) {\n" +
                            "\n" +
                            "            for (int j = 0; j < rand_sym - i; j++)\n" +
                            "                System.out.print(\" \");\n" +
                            "            for (int k = 0; k < (2 * i + 1); k++)\n" +
                            "                System.out.print(sym);\n" +
                            "            System.out.println();\n" +
                            "        }\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 8) {
                    CodeWindow.setText("import java.util.Scanner;\n" +
                            "import java.util.Objects;\n" +
                            "\n" +
                            "public class Main {\n" +
                            "    public static void main(String[] args) throws InterruptedException {\n" +
                            "        int a = 1000;\n" +
                            "        String xx;\n" +
                            "        boolean isTrue = true;\n" +
                            "        Scanner str = new Scanner(System.in);\n" +
                            "        while (isTrue) {\n" +
                            "            System.out.print(\"\");\n" +
                            "            xx = str.nextLine();\n" +
                            "\n" +
                            "            if (Objects.equals(xx, \"dead inside\")) {\n" +
                            "                while (a >= 0) {\n" +
                            "                    System.out.println(a + \" - 7\");\n" +
                            "                    Thread.sleep(60);\n" +
                            "                    a -= 7;\n" +
                            "                }\n" +
                            "                System.out.println(\"I'm ghoul...\");\n" +
                            "                break;\n" +
                            "            } else if (xx != \"dead inside\") {\n" +
                            "                System.out.println(\"Only dead inside can use that PC\");\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 9) {
                    CodeWindow.setText("import javax.swing.*;\n" +
                            "import java.io.File;\n" +
                            "\n" +
                            "public class Main {\n" +
                            "    public static void main(String[] args){\n" +
                            "\n" +
                            "        JFileChooser file_open = new JFileChooser();\n" +
                            "        int ret = file_open.showDialog(null, \"Открыть файл\");\n" +
                            "        if (ret == JFileChooser.CANCEL_OPTION) {\n" +
                            "            File file = file_open.getSelectedFile();\n" +
                            "        }\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 10) {
                    CodeWindow.setText("import javafx.application.Application;\n" +
                            "import javafx.scene.Group;\n" +
                            "import javafx.scene.Scene;\n" +
                            "import javafx.scene.shape.Line;\n" +
                            "import javafx.stage.Stage;\n" +
                            "\n" +
                            "public class App extends Application {\n" +
                            "    private int HEIGHT = 720;\n" +
                            "    private int WIDTH = 1080;\n" +
                            "\n" +
                            "    public void start(Stage primaryStage) throws Exception {\n" +
                            "        Group group = new Group();\n" +
                            "\n" +
                            "        group.getChildren().addAll(\n" +
                            "                new Line(0, HEIGHT/2, WIDTH, HEIGHT/2),\n" +
                            "                new Line(WIDTH/2, 0, WIDTH/2, HEIGHT)\n" +
                            "        );\n" +
                            "\n" +
                            "        Operation operation = x -> Math.pow(x[0], 4);\n" +
                            "        Operation operation1 = x -> x[0]*2;\n" +
                            "\n" +
                            "        for(int i = -WIDTH/2; i < WIDTH/2 - 1; i++) {\n" +
                            "            group.getChildren().add(\n" +
                            "                    new Line(i + WIDTH / 2, -i * i + HEIGHT / 2, i + 1 + WIDTH / 2, -(i + 1) * (i + 1) + HEIGHT / 2)\n" +
                            "            );\n" +
                            "            group.getChildren().add(\n" +
                            "                    new Line(i + WIDTH/2,\n" +
                            "                    -operation.execute(i) + HEIGHT/2,\n" +
                            "                    i+1 +WIDTH/2,\n" +
                            "                    -operation.execute(i+1)+ HEIGHT/2)\n" +
                            "            );\n" +
                            "        }\n" +
                            "\n" +
                            "        Scene scene = new Scene(group, WIDTH, HEIGHT);\n" +
                            "\n" +
                            "        primaryStage.setScene(scene);\n" +
                            "        primaryStage.show();\n" +
                            "    }\n" +
                            "\n" +
                            "    @FunctionalInterface\n" +
                            "    public interface Operation {\n" +
                            "        double execute(double... nums);\n" +
                            "    }\n" +
                            "\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Application.launch(args);\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 11) {
                    CodeWindow.setText("import javax.swing.*;\n" +
                            "import java.awt.*;\n" +
                            "\n" +
                            "public class Main {\n" +
                            "    public static void main(String[] args) throws InterruptedException {\n" +
                            "\n" +
                            "        MyFrame frame = new MyFrame ();\n" +
                            "        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);\n" +
                            "        frame.setVisible (true);\n" +
                            "        Thread.sleep(2000);\n" +
                            "    }\n" +
                            "}\n" +
                            "class MyFrame extends JFrame\n" +
                            "{\n" +
                            "\n" +
                            "    public MyFrame()\n" +
                            "    {\n" +
                            "        this.setBounds (0, 0, 500, 500);// общий фон\n" +
                            "        this.setBackground(Color.green);\n" +
                            "    }\n" +
                            "\n" +
                            "    public void paint(Graphics g)\n" +
                            "    {\n" +
                            "        Color newColor = new Color(255, 255, 255);//цвет\n" +
                            "        Color oldColor = new Color(0, 0, 0);//цвет\n" +
                            "        g.drawLine(20, 220, 20, 350);//линия графика\n" +
                            "        g.drawLine(20, 350, 360, 350);//линия графика\n" +
                            "        g.drawString(\"X\", 350, 346);//надпись х\n" +
                            "        g.drawString(\"Y\", 25, 230);//надпись у\n" +
                            "        int[] xArray = {10, 20, 30};\n" +
                            "        int[] yArray = {100, 400, 900};\n" +
                            "        int nPoint = 3;// кол-во точек\n" +
                            "        g.setColor(newColor);\n" +
                            "        g.drawPolyline(xArray, yArray, nPoint);\n" +
                            "        g.setColor(oldColor);\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 12) {
                    CodeWindow.setText("package sample;\n" +
                            "\n" +
                            "import javax.swing.*;\n" +
                            "import java.awt.*;\n" +
                            "\n" +
                            "class FrameSet extends JFrame {\n" +
                            "    final int HEIGHT = 1000;\n" +
                            "    final int WIDTH = 1000;\n" +
                            "\n" +
                            "    int radius;\n" +
                            "    double a;\n" +
                            "\n" +
                            "    public FrameSet(int radius, double a){\n" +
                            "        this.setSize(WIDTH, HEIGHT);// main background\n" +
                            "        this.setBackground(Color.BLACK);\n" +
                            "        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                            "        this.setVisible(true);\n" +
                            "        this.radius = radius;\n" +
                            "        this.a = a;\n" +
                            "    }\n" +
                            "\n" +
                            "    public void paint(Graphics g) {\n" +
                            "        Font currentFont = g.getFont();\n" +
                            "        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.60F);\n" +
                            "        g.setFont(newFont);\n" +
                            "        //---\n" +
                            "        int UnitSegmentForX = 35;// Unit Segment (35 pixels)\n" +
                            "        int UnitSegmentForY = 35;\n" +
                            "        int UnitSegmentForXOtriz = 35;\n" +
                            "        int UnitSegmentForYOtriz = 35;\n" +
                            "        Graphics2D g2 = (Graphics2D) g;\n" +
                            "        Color newColor = new Color(25, 140, 12);// color\n" +
                            "        g2.setColor(newColor);\n" +
                            "        g2.setStroke(new BasicStroke(2));\n" +
                            "        g2.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);// line x\n" +
                            "        g2.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);// line y\n" +
                            "        g2.drawString(\"0\", HEIGHT / 2, WIDTH / 2);// location (0, 0)\n" +
                            "\n" +
                            "        for (int i = 1; i <= 13; i++) {// the location of all points relative to OX from 1 until 13\n" +
                            "            g2.drawString(String.valueOf(i), (HEIGHT / 2) + UnitSegmentForX, (WIDTH / 2));\n" +
                            "            UnitSegmentForX += 35;\n" +
                            "        }\n" +
                            "        for (int i = 1; i <= 13; i++) { // the location of all points relative to OY from 1 until 13\n" +
                            "            g2.drawString(String.valueOf(i), (HEIGHT / 2), (WIDTH / 2) - UnitSegmentForY);// (0, 1)\n" +
                            "            UnitSegmentForY += 35;\n" +
                            "        }\n" +
                            "        for (int i = -1; i >= -13; i--) {// the location of all points relative to OX from -1 until -13\n" +
                            "            g2.drawString(String.valueOf(i), (HEIGHT / 2) - UnitSegmentForXOtriz, WIDTH / 2);\n" +
                            "            UnitSegmentForXOtriz += 35;\n" +
                            "        }\n" +
                            "        for (int i = -1; i >= -13; i--) {// the location of all points relative to OY from -1 until -13\n" +
                            "            g2.drawString(String.valueOf(i), (HEIGHT / 2), (WIDTH / 2) + UnitSegmentForYOtriz);\n" +
                            "            UnitSegmentForYOtriz += 35;\n" +
                            "        }\n" +
                            "        \n" +
                            "        //draw grid\n" +
                            "        g2.setStroke(new BasicStroke(1));\n" +
                            "        int dx, dy;\n" +
                            "        Color c1 = new Color(39, 2, 45, 255);\n" +
                            "        dx = dy = 35;\n" +
                            "        Color c = g2.getColor(); // save color\n" +
                            "        final int W = getWidth();\n" +
                            "        final int H = getHeight();\n" +
                            "        g2.setColor(c1);\n" +
                            "        for (int x = 10; x < W; x += dx) {\n" +
                            "            g2.drawLine(x, 0, x, H);\n" +
                            "        }\n" +
                            "        for (int y = 10; y < H; y += dy) {\n" +
                            "            g2.drawLine(0, y, W, y);\n" +
                            "        }\n" +
                            "        g2.setColor(c); // restore color\n" +
                            "\n" +
                            "        // draw circle\n" +
                            "        int temp = 1000;\n" +
                            "        for(int i = 1; i < temp; i++){\n" +
                            "            a += (2 * 3.14) / temp;\n" +
                            "            g2.drawLine((int) ((WIDTH / 2) + (radius * 35) * Math.cos(a)),\n" +
                            "                    (int) ((HEIGHT / 2) - (radius * 35) * Math.sin(a)),\n" +
                            "                    (int) ((WIDTH / 2) + (radius * 35) * Math.cos(a)),\n" +
                            "                    (int) ((HEIGHT / 2) - (radius * 35) * Math.sin(a)));\n" +
                            "        }\n" +
                            "    }\n" +
                            "}");
                }
                else if (MainList.getSelectedIndex() == 13) {
                    CodeWindow.setText("//---------------------------y = ax^2-----------------------------↓\n" +
                            "\n" +
                            "        if(a < 0){\n" +
                            "            for(double i = 0; i < 10; i += 0.1){\n" +
                            "                g2.drawLine((int) ((WIDTH / 2) - (35 * (i))),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (a * Math.pow(i, 2)))),\n" +
                            "                        (int) ((WIDTH / 2) - (35 * (i + 0.1))),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (a * (Math.pow((i + 0.1), 2)))))\n" +
                            "\n" +
                            "                );\n" +
                            "                for(double j = 0; j > -10; j -= 0.1){\n" +
                            "                    g2.drawLine((int) ((WIDTH / 2) - (35 * (j))),\n" +
                            "                            (int) ((HEIGHT / 2) - (35 * (a * Math.pow(j, 2)))),\n" +
                            "                            (int) ((WIDTH / 2) - (35 * (j + 0.1))),\n" +
                            "                            (int) ((HEIGHT / 2) - (35 * (a * (Math.pow((j + 0.1), 2)))))\n" +
                            "\n" +
                            "                    );\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "        else if(a > 0){\n" +
                            "            for(double i = 0; i < 10; i += 0.1){\n" +
                            "                g2.drawLine((int) ((WIDTH / 2) + (35 * (i))),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (a * Math.pow(i, 2)))),\n" +
                            "                        (int) ((WIDTH / 2) + (35 * (i + 0.1))),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (a * (Math.pow((i + 0.1), 2)))))\n" +
                            "\n" +
                            "                );\n" +
                            "                for(double j = 0; j > -10; j -= 0.1){\n" +
                            "                    g2.drawLine((int) ((WIDTH / 2) + (35 * (j))),\n" +
                            "                            (int) ((HEIGHT / 2) - (35 * (a * Math.pow(j, 2)))),\n" +
                            "                            (int) ((WIDTH / 2) + (35 * (j + 0.1))),\n" +
                            "                            (int) ((HEIGHT / 2) - (35 * (a * (Math.pow((j + 0.1), 2)))))\n" +
                            "                    );\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }");
                }
                else if (MainList.getSelectedIndex() == 14) {
                    CodeWindow.setText("        //--------------------y = Math.sqrt(x)---------------------------------↓\n" +
                            "\n" +
                            "        for(int i = 0; i < 100; i ++){\n" +
                            "            g2.drawLine((WIDTH / 2) + (35 * (i)),\n" +
                            "                    (int) ((HEIGHT / 2) - (35 * (Math.sqrt(i)))),\n" +
                            "                    (WIDTH / 2) + ((35 * (i + 1))),\n" +
                            "                    (int) ((HEIGHT / 2) - (35 * (Math.sqrt(i + 1))))\n" +
                            "\n" +
                            "            );\n" +
                            "        }\n" +
                            "        for(int i = 0; i < 100; i ++){\n" +
                            "            g2.drawLine((WIDTH / 2) - (35 * (i)),\n" +
                            "                    (int) ((HEIGHT / 2) + (35 * (Math.sqrt(i)))),\n" +
                            "                    (WIDTH / 2) - ((35 * (i + 1))),\n" +
                            "                    (int) ((HEIGHT / 2) + (35 * (Math.sqrt(i + 1))))\n" +
                            "\n" +
                            "            );\n" +
                            "        }");
                }
                else if (MainList.getSelectedIndex() == 15) {
                    CodeWindow.setText("        //---------------------y = k / x-----------------------------------\n" +
                            "\n" +
                            "        if(k > 0) {\n" +
                            "            for (double x = 0.1; x < 10; x += 0.1) {\n" +
                            "                g2.drawLine((int) ((WIDTH / 2) + (35 * x)),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (k / x))),\n" +
                            "                        (int) ((WIDTH / 2) + (35 * (x + 0.1))),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (k / (x + 0.1))))\n" +
                            "\n" +
                            "                );\n" +
                            "                g2.drawLine((int) ((WIDTH / 2) - (35 * x)),\n" +
                            "                        (int) ((HEIGHT / 2) + (35 * (k / x))),\n" +
                            "                        (int) ((WIDTH / 2) - (35 * (x + 0.1))),\n" +
                            "                        (int) ((HEIGHT / 2) + (35 * (k / (x + 0.1))))\n" +
                            "\n" +
                            "                );\n" +
                            "            }\n" +
                            "        }\n" +
                            "        else if(k < 0){\n" +
                            "            for (double x = 0.1; x < 10; x += 0.1) {\n" +
                            "                g2.drawLine((int) ((WIDTH / 2) + (35 * x)),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (k / x))),\n" +
                            "                        (int) ((WIDTH / 2) + (35 * (x + 0.1))),\n" +
                            "                        (int) ((HEIGHT / 2) - (35 * (k / (x + 0.1))))\n" +
                            "\n" +
                            "                );\n" +
                            "                g2.drawLine((int) ((WIDTH / 2) - (35 * x)),\n" +
                            "                        (int) ((HEIGHT / 2) + (35 * (k / x))),\n" +
                            "                        (int) ((WIDTH / 2) - (35 * (x + 0.1))),\n" +
                            "                        (int) ((HEIGHT / 2) + (35 * (k / (x + 0.1))))\n" +
                            "                );\n" +
                            "            }\n" +
                            "\n" +
                            "        }");
                }
                else if (MainList.getSelectedIndex() == 16) {
                    String getName = ListDoneProjects.getElementAt(16);

                    if(contains(getName, "16")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }
                }
                else if (MainList.getSelectedIndex() == 17) {
                    String getName = ListDoneProjects.getElementAt(17);
                    if(contains(getName, "17")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 18) {
                    String getName = ListDoneProjects.getElementAt(18);
                    if(contains(getName, "18")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 19) {
                    String getName = ListDoneProjects.getElementAt(19);
                    if(contains(getName, "19")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 20) {
                    String getName = ListDoneProjects.getElementAt(20);
                    if(contains(getName, "20")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 21) {
                    String getName = ListDoneProjects.getElementAt(21);
                    if(contains(getName, "21")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 22) {
                    String getName = ListDoneProjects.getElementAt(22);
                    if(contains(getName, "22")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 23) {
                    String getName = ListDoneProjects.getElementAt(23);
                    if(contains(getName, "23")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 24) {
                    String getName = ListDoneProjects.getElementAt(24);
                    if(contains(getName, "24")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 25) {
                    String getName = ListDoneProjects.getElementAt(25);
                    if(contains(getName, "25")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 26) {
                    String getName = ListDoneProjects.getElementAt(26);
                    if(contains(getName, "26")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 27) {
                    String getName = ListDoneProjects.getElementAt(27);
                    if(contains(getName, "27")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 28) {
                    String getName = ListDoneProjects.getElementAt(28);
                    if(contains(getName, "28")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 29) {
                    String getName = ListDoneProjects.getElementAt(29);
                    if(contains(getName, "29")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 30) {
                    String getName = ListDoneProjects.getElementAt(30);
                    if(contains(getName, "30")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 31) {
                    String getName = ListDoneProjects.getElementAt(31);
                    if(contains(getName, "31")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 32) {
                    String getName = ListDoneProjects.getElementAt(32);
                    if(contains(getName, "32")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 33) {
                    String getName = ListDoneProjects.getElementAt(33);
                    if(contains(getName, "33")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 34) {
                    String getName = ListDoneProjects.getElementAt(34);
                    if(contains(getName, "34")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 35) {
                    String getName = ListDoneProjects.getElementAt(35);
                    if(contains(getName, "35")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 36) {
                    String getName = ListDoneProjects.getElementAt(36);
                    if(contains(getName, "36")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 37) {
                    String getName = ListDoneProjects.getElementAt(37);
                    if(contains(getName, "37")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 38) {
                    String getName = ListDoneProjects.getElementAt(38);
                    if(contains(getName, "38")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 39) {
                    String getName = ListDoneProjects.getElementAt(39);
                    if(contains(getName, "39")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }
                else if (MainList.getSelectedIndex() == 40) {
                    String getName = ListDoneProjects.getElementAt(40);
                    if(contains(getName, "40")){
                        String fileName = desktop_path + "\\App-for-projects\\" + getName + ".txt";
                        String content = null;
                        try {
                            content = Files.lines(Paths.get(fileName)).reduce("", (a, b) -> a + "\n" + b);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        CodeWindow.setText(content);
                    }

                }

            }
        }));
//-----------------Копирование текста с помощью ПКМ----------------------------------------
        CodeWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    String ctc = CodeWindow.getText();
                    StringSelection stringSelection = new StringSelection(ctc);
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                }
            }
        });

        //-------------------Для добавления нового проекта-----------------------------
        MainList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {//     создаём новое окно для добавки проектов
                    JFrame WindowForAddProject = new JFrame("Add Project");
                    WindowForAddProject.setSize(900, 800);
                    WindowForAddProject.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//    Код не завершается при выходе
                    WindowForAddProject.setVisible(true);
                    //--------------------------------------------------------------
                    Container DoubleContAddProj = WindowForAddProject.getContentPane();//  новый контейнер для элементов
                    //--------------------------------------------------------------
                    JPanel TitleTheme = new JPanel();// панель для title, находится вверху
                    TitleTheme.setBackground(new Color(26, 26, 42));
                    DoubleContAddProj.add(TitleTheme, BorderLayout.NORTH);
                    JLabel AddProjTitle = new JLabel("Add new Project Window", SwingConstants.CENTER);
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
                    NameNewProj.setDocument(new JTextFieldLimit(15));// максимальное кол-во символов для Name
                    NameNewProj.setBackground(new Color(9, 50, 50));
                    NameNewProj.setForeground(new Color(144, 144, 144));
                    NameNewProj.setFont(new Font(null, Font.BOLD, 18));
                    NameNewProj.setText("Title");
                    NameNewProj.setHorizontalAlignment(JLabel.CENTER);
                    box.add(NameNewProj, BorderLayout.NORTH);

                    NameNewProj.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            NameNewProj.setText("");
                        }
                    });

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
                    TransparentTextArea.CustomTextArea CodeNewProj = new TransparentTextArea.CustomTextArea();
                    CodeNewProj.setBackground(Color.BLACK);
                    CodeNewProj.setForeground(new Color(209, 161, 226));
                    CodeNewProj.setFont(new Font(null, Font.BOLD, 18));
                    CodeNewProj.setText("//Enter your code here\nMIN Index - 16"  + ", MAX Index - 40");

                    box.add(CodeNewProj);
                    CodeNewProj.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            CodeNewProj.setText("");
                        }
                    });

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
                            String getTextFromIndex = IndexField.getText();

                            boolean cyrillic = getTextCodeNewProject.chars()
                                    .mapToObj(Character.UnicodeBlock::of)
                                    .anyMatch(b -> b.equals(Character.UnicodeBlock.CYRILLIC));

                            if (!Objects.equals(MainNameNewProject, "") &&
                                    !Objects.equals(getTextCodeNewProject, "") &&
                                    !Objects.equals(getTextFromIndex, "") &&
                                    !cyrillic){
                                {
                                    ListDoneProjects.addElement("-" + getTextFromIndex);
                                    WindowForAddProject.setVisible(false);
                                    try {
                                        File createTxtFile = new File(desktop_path + "\\App-for-projects\\" + "-" + getTextFromIndex + ".txt");

                                        // if file doesn't exists, then create it
                                        if (!createTxtFile.exists()) {
                                            createTxtFile.createNewFile();
                                        }

                                        FileWriter fw = new FileWriter(createTxtFile.getAbsoluteFile());
                                        BufferedWriter bw = new BufferedWriter(fw);
                                        bw.write(getTextCodeNewProject);
                                        bw.close();


                                    } catch (IOException ex) {
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
                    CodeWindow.setText(null);
                }
            }
        });

        //----------------------------Удаление НОВЫХ проектов с помощью ПКМ при наведении на Main List------------------------------------------------
        MainList.addListSelectionListener(e -> {
            last_index = e.getLastIndex();
            MainList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String getName = ListDoneProjects.getElementAt(last_index);
                    if (e.getButton() == MouseEvent.BUTTON3 && 16 <= last_index && MainList.getSelectedIndex() == MainList.getLastVisibleIndex()) {
                        try {
                            Files.delete(Paths.get(desktop_path + "\\App-for-projects\\" + getName + ".txt"));
                        } catch (IOException x) {
                            x.printStackTrace();
                        }
                        ListDoneProjects.remove(last_index);
                    }
                }
            });

        });
        //-----------------Information-----------------------
        CodeWindow.setText("-----------------------------------------------------INFO---------------------------------------------------\n" +
                " - Click MOUSE3(on Code Window) to FAST COPY\n" +
                " - To delete a project (that you added), HOVER the cursor over the SELECTED\n" +
                "project and use the MOUSE3 the LAST project in the list\n" +
                " - All ADDED projects are saved in the project ITSELF\n" +
                " - Unfortunately, the names of new projects ARE NOT SAVED(only index)\n" +
                " - Pss, in order to add a project, it is not necessary to press\n" +
                "'<Tap on Enter>' all the time, you can click on any project)\n" +
                " --------------------------------------------------------------------------------------------------------------\n" +
                "! And remember - NO RUSSIAN ! - I'm seriously... (f*cking encoding)\n" +
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
