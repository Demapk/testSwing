import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import javax.swing.border.Border;

class SimpleGUI extends JFrame {

    //Задаем поля текста и поля ввода
    private JLabel info = new JLabel("Информация, для одобрения нужно:");
    private JLabel info1 = new JLabel("-Показатель окупаемости поддержки > 1");
    private JLabel info2 = new JLabel("-Показатель рентабельности поддержки > инфляции");
    private JButton button = new JButton("Приступить к расчетам");
    private JLabel empty = new JLabel(" \n");
    private JButton buttonClear = new JButton("  Отменить  ");
    private JTextField inputViruchka = new JTextField("", 1);
    private JLabel viruchka = new JLabel("Выручка(тыс.руб.):");
    private JTextField inputSBS = new JTextField("", 1);
    private JLabel sbs = new JLabel("Себестоимость(тыс.руб.):");
    private JTextField inputOP = new JTextField("", 1);
    private JLabel op = new JLabel("Обьём поддержки(тыс.руб.):");
    private JTextField inputPribil = new JTextField("", 1 );
    private JLabel pribil = new JLabel("Прибыль(тыс.руб.):");
    private JButton report = new JButton("Отчет");

    //Определяем параметры положение и размеры приложения
    public SimpleGUI() {
        super("");
        this.setBounds(100,100,400,270);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Создаем контейнер который передаст все кнопки во внешнее приложение
        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container,1));
        container.add(viruchka);
        container.add(inputViruchka);
        container.add(sbs);
        container.add(inputSBS);
        container.add(op);
        container.add(inputOP);
        container.add(pribil);
        container.add(inputPribil);
        container.add(info);
        container.add(info1);
        container.add(info2);
        container.add(report);

        button.addActionListener(new ButtonEventListener());
        container.add(button);
        container.add(empty);
        container.add(buttonClear);
    }

    //Задаем переменные (Double имеет запятаю, например "1.00")
    private double ESHN=0.06;
    private double FRPv;
    private double FRPp;
    private double EPv;
    private double EPp;
    private double Be;
    private double infl=12.9;
    String message="";

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Вытаскиваем переменные из приложения
            double Dviruchka= Double.parseDouble(inputViruchka.getText());
            double Dsbs= Double.parseDouble(inputSBS.getText());
            double Dop= Double.parseDouble(inputOP.getText());
            double Dpribil=Double.parseDouble(inputPribil.getText());

            //Если переменные больше 0, то начинаем высчитывать по формуле
            if (Dsbs>0 && Dop>0 && Dpribil>0 && Dviruchka>0){
                FRPv=Dviruchka*Dop/Dsbs;
                FRPp=Dpribil*Dop/Dsbs;
                EPv=FRPv/Dop;
                EPp=FRPp/Dop*100;
                Be=FRPv*ESHN;

                //Переводим в формат Строки, чтобы было только 2 цифры после запятой
                String stringfrpv=String.format(String.format("%.2f",FRPv));
                String stringfrpp=String.format(String.format("%.2f",FRPp));
                String stringepv=String.format(String.format("%.2f",EPv));
                String stringepp=String.format(String.format("%.2f",EPp));
                String stringbe=String.format(String.format("%.2f",Be));
            //Задаем message
            if (EPv>1 && EPp>infl){
                message="Оценка эффективности использования субсидий прошла успешно.\n Показатели соответствуют нормам,\n ФРПв="+stringfrpv+";" +
                        " \n ФРПп="+stringfrpp+"; \n ЭПв="+stringepv+"; \n ЭПп="+stringepp+"; \n БЭ="+stringbe+" ";
            }
                else message="Oценка эффективности использования субсидий прошла неудачно.\n Показатели не соответствуют нормам, \n ФРПв="+stringfrpv+
                    "; \n ФРПп="+stringfrpp+"; \n ЭПв="+stringepv+"; \n ЭПп="+stringepp+"; \n БЭ="+stringbe+" ";

                //Окно вывода сообщения
                JOptionPane.showMessageDialog(null,message,
                        "Вывод",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    //Main приложения, через которое все запускаем
    public static void main(String[] args) {
        SimpleGUI app = new SimpleGUI();
        app.setVisible(true);
    }
}