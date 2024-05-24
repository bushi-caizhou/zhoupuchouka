import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class zhoupuchouka extends JFrame {
    private JComboBox<String> comboBox;
    private final JButton drawButton;
    private JTextArea outputTextArea;

    
    enum Ganyuanthreestar {    斑点, 卡提, 米格鲁, 克洛丝, 玫兰莎, 翎羽, 芬, 安德切尔, 史都华德, 香草, 炎熔, 芙蓉, 空爆, 安塞尔, 泡普卡, 月见夜, 梓兰    }

    enum Ganyuanfourstar {   桃金娘, 地灵, 红豆 }

    enum Ganyuanfivestar {       幽灵鲨, 极境    }
    enum Ganyuansixstar {        风笛, 琴柳, 伊内斯, 艾雅法拉    }

    enum Ganyuansanxing {  三星1, 三星2    }

    enum Ganyuansixing  {   四星1, 四星2    }

    enum Ganyuanwuxing  {    五星1, 五星2   }
    
    enum Ganyuanliuxing {    六星1, 六星2    }

    public zhoupuchouka() {
        setTitle("粥铺抽卡");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 创建选择框和按钮
        comboBox = new JComboBox<>(new String[]{"新手池", "限定池"});
        drawButton = new JButton("抽卡");
        JPanel topPanel = new JPanel();
        topPanel.add(comboBox);
        topPanel.add(drawButton);
        add(topPanel, BorderLayout.NORTH);

        // 创建输出文本区域
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);





        // 添加按钮点击事件监听器
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int witchOne = comboBox.getSelectedIndex();
                outputTextArea.setText("");
                if (witchOne == 0) {
                    for (int i = 1; i <= 10; i++) {
                        int rarity = drawRarity(i, witchOne);
                        String item = drawItem(rarity, Arrays.asList(Ganyuanthreestar.values()), Arrays.asList(Ganyuanfourstar.values()),
                                Arrays.asList(Ganyuanfivestar.values()), Arrays.asList(Ganyuansixstar.values()));
                        outputTextArea.append("新手池第" + i + "抽: " + item + "\n");
                    }
                } else if (witchOne == 1) {
                    for (int c = 1; c <= 10; c++) {
                        int rarity = drawRarity(c, witchOne);
                        String card = drawCard(rarity, Arrays.asList(Ganyuansanxing.values()), Arrays.asList(Ganyuansixing.values()),
                                Arrays.asList(Ganyuanwuxing.values()), Arrays.asList(Ganyuanliuxing.values()));
                        outputTextArea.append("限定池第" + c + "抽: " + card + "\n");
                    }
                }
            }
        });
    }

    private int drawRarity(int drawNumber, int witchOne) {
        int random = ThreadLocalRandom.current().nextInt(100);
        int updatedRandom;

        if (drawNumber == 10 && random < 90 && witchOne == 1) {
            updatedRandom = 99;
        } else if (drawNumber == 10 && random < 90 && witchOne == 0) {
            updatedRandom = 90;
        } else {
            updatedRandom = random;
        }

        if (updatedRandom < 40) return 3;
        if (updatedRandom < 90) return 4;
        if (updatedRandom < 98) return 5;

        return 6;
    }

    public  String drawItem(int rarity, List<Ganyuanthreestar> threeStarPool, List<Ganyuanfourstar> fourStarPool,
                            List<Ganyuanfivestar> fiveStarPool, List<Ganyuansixstar> sixStarPool) {
        int index;
        switch (rarity) {
            case 3:
                index = ThreadLocalRandom.current().nextInt(threeStarPool.size());
                return "三星-" + threeStarPool.get(index);
            case 4:
                index = ThreadLocalRandom.current().nextInt(fourStarPool.size());
                return "四星-" + fourStarPool.get(index);
            case 5:
                index = ThreadLocalRandom.current().nextInt(fiveStarPool.size());
                return "五星-" + fiveStarPool.get(index);
            case 6:
                index = ThreadLocalRandom.current().nextInt(sixStarPool.size());
                return "六星-" + sixStarPool.get(index);
            default:
                return "error";
        }
    }

    private String drawCard(int rarity, List<Ganyuansanxing> sanxingPool, List<Ganyuansixing> sixingPool,
                            List<Ganyuanwuxing> wuxingPool, List<Ganyuanliuxing> liuxingPool) {
        int maybe;
        switch (rarity) {
            case 3:
                maybe = ThreadLocalRandom.current().nextInt(sanxingPool.size());
                return "三星-" + sanxingPool.get(maybe);
            case 4:
                maybe = ThreadLocalRandom.current().nextInt(sixingPool.size());
                return "四星-" + sixingPool.get(maybe);
            case 5:
                maybe = ThreadLocalRandom.current().nextInt(wuxingPool.size());
                return "五星-" + wuxingPool.get(maybe);
            case 6:
                maybe = ThreadLocalRandom.current().nextInt(liuxingPool.size());
                return "六星-" + liuxingPool.get(maybe);
            default:
                return "error";

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                zhoupuchouka gui = new zhoupuchouka();
                gui.setVisible(true);
            }
        });
    }
}
