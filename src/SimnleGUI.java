import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

class SimpleGUI extends JFrame {

    //Задаем поля текста и поля ввода
    private JButton button = new JButton("Вычислить");
    private JTextField inputViruchka = new JTextField("", 1);
    private JLabel viruchka = new JLabel("Выручка:");
    private JTextField inputSBS = new JTextField("", 1);
    private JLabel sbs = new JLabel("Себестоимость:");
    private JTextField inputOP = new JTextField("", 1);
    private JLabel op = new JLabel("Обьём поддержки:");
    private JTextField inputPribil = new JTextField("", 1 );
    private JLabel pribil = new JLabel("Прибыль:");

    //Определяем параметры положение и размеры приложения
    public SimpleGUI() {
        super("");
        this.setBounds(100,100,400,250);
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

        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    //Задаем переменные (Double имеет запятаю, например "1.00")
    private double ESHN=0.06;
    private double FRPv;
    private double FRPp;
    private double EPv;
    private double EPp;
    private double Be;
    private double infl=0.20;
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

            //Задаем message
            if (EPv>1 && EPp>infl){
                message="Одобрено, ФРПв="+FRPv+"; ФРПп="+FRPp+"; ЭПв="+EPv+"; ЭПп="+EPp+"; БЭ="+Be+" ";
            }
                else message="Не одобрено, ФРПв="+FRPv+"; ФРПп="+FRPp+"; ЭПв="+EPv+"; ЭПп="+EPp+"; БЭ="+Be+" ";

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