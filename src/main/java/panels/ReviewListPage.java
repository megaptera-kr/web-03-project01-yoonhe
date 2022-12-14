package panels;

import models.Pomodoro;
import models.Review;
import models.Session;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.StrokeBorder;
import java.awt.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReviewListPage extends JPanel {
    private Pomodoro pomodoro;
    private ContentPanel contentPanel;

    public ReviewListPage(Pomodoro pomodoro, ContentPanel contentPanel) {
        this.pomodoro = pomodoro;
        this.contentPanel = contentPanel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 0, 20, 0));
        this.add(panel);
        JLabel title = new JLabel("리뷰");
        panel.add(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        createReviewListPanel();
    }

    public void createReviewListPanel() {
        JPanel listPanel = new JPanel();

        listPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        this.add(listPanel);

        List<Session> sessions = pomodoro.sessions();

        if (sessions.size() == 0) {
            listPanel.add(new JLabel("완료된 세션이 없습니다"));
            return;
        }

        List<Review> reviews = sessions.stream()
                .map(session -> session.review())
                .collect(Collectors.toList());

        for (Review review : reviews) {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            String[] values = review.values();

            for (String value : values) {
                JLabel label = new JLabel(value);
                label.setBorder(new EmptyBorder(5, 0, 5, 0));
                panel.add(label);
            }

            listPanel.add(panel);
        }
    }
}
