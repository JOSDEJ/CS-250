import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * TopFiveDestinationList.java
 *
 * CS 250 - Module Three: Developing Basic ListView Control
 * Created by: Jose De Jesus
 * Date: 05/24/2026
 *
 * This program fulfills User Story #1: Top Five Destination List.
 * It displays a ranked list of the top 5 travel destinations for SNHU Travel,
 * each with a destination name, short description, and a scaled image.
 *
 * Customizations made:
 *  - Updated all 5 destinations with real names and descriptions
 *  - Added unique images for each destination (stored in src/resources/)
 *  - Images are automatically scaled to 150x100 to fit neatly in each row
 *  - Applied a custom teal/navy color scheme throughout the UI
 *  - Added alternating (zebra-stripe) row colors for readability
 *  - Added a header title label and a student name footer label
 *
 * Image credits (Wikimedia Commons - public domain or CC licensed):
 *  - Bali: Photo by Midori, CC BY-SA 3.0
 *  - Paris: Photo by Benh LIEU SONG, CC BY-SA 3.0
 *  - Machu Picchu: Photo by Allard Schmidt, public domain
 *  - Santorini: Photo by Klearchos Kapoutsis, CC BY 2.0
 *  - Tokyo: Photo by Kakidai, CC BY-SA 4.0
 */
public class TopFiveDestinationList {
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TopDestinationListFrame frame = new TopDestinationListFrame();
                frame.setTitle("SNHU Travel - Top 5 Destination List");
                frame.setVisible(true);
            }
        });
    }
}


/**
 * TopDestinationListFrame
 *
 * Main JFrame that builds the top destinations list UI.
 * Includes a teal header, a scrollable destination list, and a name footer.
 */
class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("SNHU Travel - Top 5 Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        // Set deep navy blue as the frame background color
        getContentPane().setBackground(new Color(15, 40, 80));
        getContentPane().setLayout(new BorderLayout());

        // --- Header Panel ---
        // Teal header bar showing the list title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 128, 128));
        JLabel headerLabel = new JLabel("  Top 5 Travel Destinations", SwingConstants.LEFT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // --- Destination List ---
        listModel = new DefaultListModel();

        // Each destination is added with a scaled image and a description.
        // Images are loaded from src/resources/ and scaled to 150x100 pixels.
        addDestinationNameAndPicture(
            "1. Bali, Indonesia - A tropical paradise of temples, rice terraces, and stunning beaches.",
            loadScaledImage("/resources/bali.jpg")
        );

        addDestinationNameAndPicture(
            "2. Paris, France - The City of Light, famous for the Eiffel Tower, cuisine, and art.",
            loadScaledImage("/resources/paris.jpg")
        );

        addDestinationNameAndPicture(
            "3. Machu Picchu, Peru - An ancient Incan citadel set high in the Andes Mountains.",
            loadScaledImage("/resources/machupicchu.jpg")
        );

        addDestinationNameAndPicture(
            "4. Santorini, Greece - A breathtaking island known for white-washed buildings and sunsets.",
            loadScaledImage("/resources/santorini.jpg")
        );

        addDestinationNameAndPicture(
            "5. Tokyo, Japan - A vibrant city blending ultramodern technology with traditional culture.",
            loadScaledImage("/resources/tokyo.jpg")
        );

        // Build the JList from the populated list model
        JList list = new JList(listModel);

        // Apply custom renderer with 10px padding on all sides
        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(10);
        list.setCellRenderer(renderer);

        // Custom navy background and white text for list
        list.setBackground(new Color(20, 55, 100));
        list.setForeground(Color.WHITE);

        // Teal highlight color when a row is selected
        list.setSelectionBackground(new Color(0, 160, 160));
        list.setSelectionForeground(Color.WHITE);

        // Fixed row height to display images properly
        list.setFixedCellHeight(120);

        // Wrap list in a scroll pane with padding
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(new Color(20, 55, 100));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // --- Footer Panel ---
        // Displays the student name at the bottom of the window
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 128, 128));
        JLabel nameLabel = new JLabel("Created by: Jose De Jesus | CS 250 SNHU Travel Project");
        nameLabel.setFont(new Font("Arial", Font.ITALIC, 13));
        nameLabel.setForeground(Color.WHITE);
        footerPanel.add(nameLabel);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Loads an image from the resources folder and scales it to 150x100 pixels.
     * This ensures all images display at a consistent size in the list rows.
     *
     * @param path  The resource path (e.g. "/resources/bali.jpg")
     * @return      A scaled ImageIcon, or an empty ImageIcon if the file is not found
     */
    private ImageIcon loadScaledImage(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                ImageIcon original = new ImageIcon(imgURL);
                // Scale the image to 150 wide x 100 tall, smooth scaling
                Image scaled = original.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            } else {
                System.out.println("Image not found: " + path);
                return new ImageIcon();
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + path);
            return new ImageIcon();
        }
    }

    /**
     * Adds a destination entry to the list model.
     *
     * @param text  Destination label (name + short description)
     * @param icon  Scaled image icon for the destination
     */
    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


/**
 * TextAndIcon
 *
 * Simple data holder pairing a text label with an image icon.
 * Each list entry uses one TextAndIcon object.
 */
class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() { return text; }
    public Icon getIcon()   { return icon; }
    public void setText(String text) { this.text = text; }
    public void setIcon(Icon icon)   { this.icon = icon; }
}


/**
 * TextAndIconListCellRenderer
 *
 * Custom ListCellRenderer that renders both an icon and text for each list row.
 * Extends JLabel so it can display image + text together natively.
 * Also applies alternating row colors (zebra striping) for readability.
 */
class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {

    // Border used when the cell does not have keyboard focus
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
        // Larger font for destination text readability
        setFont(new Font("Arial", Font.PLAIN, 15));
    }

    /**
     * Called by JList to render each row.
     * Sets the icon and text, applies colors, and returns this JLabel as the renderer.
     */
    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean hasFocus) {

        TextAndIcon tai = (TextAndIcon) value;

        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            // Teal highlight for selected row
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else if (index % 2 == 0) {
            // Even rows: slightly lighter navy (zebra stripe)
            setBackground(new Color(25, 65, 115));
            setForeground(Color.WHITE);
        } else {
            // Odd rows: standard dark navy
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        // Apply focus or default border
        Border outsideBorder = hasFocus
            ? UIManager.getBorder("List.focusCellHighlightBorder")
            : NO_FOCUS_BORDER;

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());

        return this;
    }

    // Overridden as empty for performance — see DefaultListCellRenderer docs
    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}
