import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InfoGUI extends JFrame {
    //Задаем поля текста и поля ввода
    private JLabel empty = new JLabel("    ");
    private JLabel info = new JLabel("Информация!");
    private JLabel info1 = new JLabel("Оценка анализа эффективности будет успешной если:");
    private JLabel info2 = new JLabel("-Показатель рентабельности поддержки > инфляции");
    private JLabel info3 = new JLabel("-Показатель окупаемости поддержки > 1");
    private JButton button = new JButton("Отчет");
    private JTextField inputViruchka = new JTextField("", 1);
    private JLabel viruchka = new JLabel("Выручка:");
    private JTextField inputSBS = new JTextField("", 1);
    private JLabel sbs = new JLabel("Себестоимость:");
    private JTextField inputOP = new JTextField("", 1);
    private JLabel op = new JLabel("Обьём поддержки:");
    private JTextField inputPribil = new JTextField("", 1 );
    private JLabel pribil = new JLabel("Прибыль:");
    Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

    //Определяем параметры положение и размеры приложения
    public InfoGUI() {
        super("");
        this.setBounds(100,100,400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Создаем контейнер который передаст все кнопки во внешнее приложение
        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container,1));
        container.add(info);
        container.add(info1);
        container.add(info2);
        container.add(info3);
        container.add(button);

    }
    public static void main(String[] args) {
        InfoGUI app = new InfoGUI();
        app.setVisible(true);
    }
}