import javax.swing.*;
import java.awt.*;

public class PaintEditorLayout extends JFrame {

    public PaintEditorLayout() {
        setTitle("Art: Paint Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // === LEFT SIDEBAR ===
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(230, 230, 230));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Logo dan judul
        JLabel logoLabel = new JLabel("🎨");
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel appName = new JLabel("Art: Paint Editor");
        appName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        appName.setAlignmentX(Component.CENTER_ALIGNMENT);
        appName.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        // Tombol menu
        String[] menuItems = {"Projects", "Settings", "Help", "Exit"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setFocusPainted(false);
            btn.setBackground(new Color(240, 240, 240));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(180, 40));
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        sidebar.add(Box.createVerticalGlue());

        // === MAIN PANEL ===
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // Bagian atas (Tombol New Project, Open, Import, Export)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        topPanel.setBackground(new Color(250, 250, 250));

        String[] topButtons = {"New project", "Open", "Import", "Export"};
        Icon[] icons = {
                UIManager.getIcon("FileChooser.newFolderIcon"),
                UIManager.getIcon("FileView.directoryIcon"),
                UIManager.getIcon("FileView.floppyDriveIcon"),
                UIManager.getIcon("FileView.fileIcon")
        };

        for (int i = 0; i < topButtons.length; i++) {
            JPanel card = new JPanel();
            card.setPreferredSize(new Dimension(120, 100));
            card.setBackground(new Color(210, 210, 210));
            card.setLayout(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel iconLabel = new JLabel(icons[i], JLabel.CENTER);
            JLabel textLabel = new JLabel(topButtons[i], JLabel.CENTER);
            textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

            card.add(iconLabel, BorderLayout.CENTER);
            card.add(textLabel, BorderLayout.SOUTH);

            topPanel.add(card);
        }

        // Bagian bawah (Recent Projects)
        JPanel recentPanel = new JPanel();
        recentPanel.setLayout(new BoxLayout(recentPanel, BoxLayout.Y_AXIS));
        recentPanel.setBackground(new Color(245, 245, 245));
        recentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel recentTitle = new JLabel("Recent Projects");
        recentTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        recentTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        recentPanel.add(recentTitle);
        recentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Item proyek (contoh)
        for (int i = 0; i < 3; i++) {
            JPanel item = new JPanel();
            item.setLayout(new BorderLayout());
            item.setPreferredSize(new Dimension(600, 80));
            item.setMaximumSize(new Dimension(600, 80));
            item.setBackground(new Color(235, 235, 235));
            item.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel imgLabel = new JLabel("🖼️");
            imgLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));

            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setOpaque(false);

            JLabel fileName = new JLabel(i == 0 ? "abcd.png" : "");
            fileName.setFont(new Font("Segoe UI", Font.BOLD, 13));
            JLabel path = new JLabel("/path/to/this/file.png");
            path.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            JLabel date = new JLabel("Wednesday, 02-02-2022");
            date.setFont(new Font("Segoe UI", Font.ITALIC, 10));
            textPanel.add(fileName);
            textPanel.add(path);
            textPanel.add(date);

            item.add(imgLabel, BorderLayout.WEST);
            item.add(textPanel, BorderLayout.CENTER);

            recentPanel.add(item);
            recentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(recentPanel, BorderLayout.CENTER);

        // Tambahkan semua ke frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaintEditorLayout::new);
    }
}
