package panels;

import models.ConcentrationLevel;
import models.Pomodoro;
import models.Review;
import models.Session;
import models.Task;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

public class ReviewFieldsContainerPanel extends JPanel {
    private Pomodoro pomodoro;
    private Session session;

    private ContentPanel contentPanel;

    private JCheckBox completedCheckBox;
    private JComboBox<String> ConcentrationLevelSelectBox;

    private final JTextField newPointTextField;
    private final JTextField regretPointTextField;
    private final JTextField improvementPointTextField;
    private ConcentrationLevel concentrationLevel;

    public ReviewFieldsContainerPanel(Pomodoro pomodoro, Session session, ContentPanel contentPanel) {
        this.pomodoro = pomodoro;
        this.session = session;
        this.contentPanel = contentPanel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createSessionDetailPanel();

        createTitlePanel();

        createTaskCompletedCheckPanel();
        createConcentrationLevelCheckPanel();
        newPointTextField = createTextFieldPanel("새로 알게된 것");
        regretPointTextField = createTextFieldPanel("아쉬운 점");
        improvementPointTextField = createTextFieldPanel("다음 세션에서 개선시킬 점");

        createSubmitButton();
    }

    public void createSessionDetailPanel() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel objectiveLabel = new JLabel("목표 : " + session.objective().title());
        objectiveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(objectiveLabel);

        JLabel taskLabel = new JLabel("작업 : " + session.task().title());

        taskLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(taskLabel);
    }

    private void createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 0, 20, 0));

        createTitleLabel("이번 세션에서 무엇을 배우셨나요?", panel);

        this.add(panel);
    }

    private void createTaskCompletedCheckPanel() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        createTitleLabel("작업 완료 했나요?", panel);

        createTaskCheckBox(panel);

        this.add(panel);
    }

    private void createTaskCheckBox(JPanel panel) {
        completedCheckBox = new JCheckBox("당연하죠!");

        completedCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(completedCheckBox);
    }

    public void createConcentrationLevelCheckPanel() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        createTitleLabel("스스로의 집중도를 평가해봅시다", panel);

        createConcentrationSelectBox(panel);

        this.add(panel);
    }

    public void createConcentrationSelectBox(JPanel panel) {
        concentrationLevel = new ConcentrationLevel();

        String[] levels = concentrationLevel.levels();

        ConcentrationLevelSelectBox = new JComboBox<>();

        for (String level : levels) {
            ConcentrationLevelSelectBox.addItem(level);
        }

        ConcentrationLevelSelectBox.addActionListener(event -> {
            String level =
                    (String) ConcentrationLevelSelectBox.getSelectedItem();

            concentrationLevel.setLevel(level);
        });

        panel.add(ConcentrationLevelSelectBox);
    }

    public JTextField createTextFieldPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(title));

        JTextField textField = new JTextField(20);
        panel.add(textField);

        this.add(panel);

        return textField;
    }

    public void createSubmitButton() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 0, 20, 0));
        this.add(panel);

        JButton button = new JButton("제출하기");
        button.addActionListener(event -> {
            Task task = session.task();

            boolean isCompleted = completedCheckBox.isSelected();
            String newPoint = newPointTextField.getText();
            String improvementPoint = improvementPointTextField.getText();
            String regretPoint = regretPointTextField.getText();

            System.out.println();

            if (isCompleted) {
                task.complete();
            }

            if (!isCompleted) {
                task.incomplete();
            }

            Review review = new Review(task, concentrationLevel,
                    newPoint, regretPoint, improvementPoint);

            session.setReview(review);

            pomodoro.addSession(session);

            contentPanel.update(new SessionPage(pomodoro, contentPanel));
        });
        panel.add(button);

        this.add(button);
    }

    private void createTitleLabel(String text, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
    }
}
