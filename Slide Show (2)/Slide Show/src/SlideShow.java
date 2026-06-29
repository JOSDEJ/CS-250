import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * SlideShow.java
 * 
 * SNHU Travel - Top 5 Destinations SlideShow
 * CS 250 - Software Development Lifecycle
 * 
 * Module Five Update:
 * Per the Product Owner's updated requirements, slides 2 through 5 have been
 * replaced with real travel destinations. Each slide now displays a destination
 * image along with the destination name and a brief description. This replaces
 * the placeholder "#N Top Destination" text from the original starter code.
 * 
 * The five featured destinations are:
 *   1. The Grand Canyon     - Arizona
 *   2. Yellowstone          - Wyoming/Montana/Idaho
 *   3. The Florida Keys     - Florida
 *   4. Arches National Park - Utah
 *   5. New Orleans          - Louisiana
 */
public class SlideShow extends JFrame {

	// Declare panel and layout variables for the slideshow structure
	private JPanel slidePane;      // Holds the image slides
	private JPanel textPane;       // Holds the text descriptions below each slide
	private JPanel buttonPane;     // Holds the Previous and Next navigation buttons
	private CardLayout card;       // CardLayout for cycling through image slides
	private CardLayout cardText;   // CardLayout for cycling through text descriptions
	private JButton btnPrev;       // Button to navigate to the previous slide
	private JButton btnNext;       // Button to navigate to the next slide
	private JLabel lblSlide;       // Label that renders each slide image via HTML
	private JLabel lblTextArea;    // Label that renders each slide's text description

	/**
	 * Constructor: creates the SlideShow application window.
	 */
	public SlideShow() throws HeadlessException {
		initComponent();
	}

	/**
	 * initComponent: sets up all UI components, layouts, and event listeners.
	 * This method builds the frame, populates all 5 slides with images and text,
	 * and wires up the Previous/Next navigation buttons.
	 */
	private void initComponent() {

		// Initialize layout managers
		card = new CardLayout();       // Controls which image slide is visible
		cardText = new CardLayout();   // Controls which text description is visible

		// Initialize panels
		slidePane = new JPanel();
		textPane = new JPanel();
		buttonPane = new JPanel();

		// Style the text panel: blue background positioned at the bottom of the frame
		textPane.setBackground(Color.BLUE);
		textPane.setBounds(5, 470, 790, 50);
		textPane.setVisible(true);

		// Initialize button and label references
		btnPrev = new JButton();
		btnNext = new JButton();
		lblSlide = new JLabel();
		lblTextArea = new JLabel();

		// Configure the main JFrame window
		setSize(800, 600);
		setLocationRelativeTo(null);                          // Center on screen
		setTitle("Top 5 Destinations SlideShow");
		getContentPane().setLayout(new BorderLayout(10, 50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Apply CardLayout to both the image panel and text panel
		slidePane.setLayout(card);
		textPane.setLayout(cardText);

		/**
		 * Loop through all 5 slides (1-5).
		 * For each slide:
		 *   - getResizeIcon(i) returns an HTML string that embeds the destination image.
		 *   - getTextDescription(i) returns an HTML string with the destination name
		 *     and short description.
		 * Both are added to their respective CardLayout panels using unique card keys.
		 */
		for (int i = 1; i <= 5; i++) {
			lblSlide = new JLabel();
			lblTextArea = new JLabel();
			lblSlide.setText(getResizeIcon(i));            // Set image for this slide
			lblTextArea.setText(getTextDescription(i));    // Set description for this slide
			slidePane.add(lblSlide, "card" + i);           // Register image slide by card key
			textPane.add(lblTextArea, "cardText" + i);     // Register text slide by card key
		}

		// Add the image panel to the center of the frame and text panel to the south
		getContentPane().add(slidePane, BorderLayout.CENTER);
		getContentPane().add(textPane, BorderLayout.SOUTH);

		// Configure button panel with centered horizontal layout
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		// Set up the Previous button and its click handler
		btnPrev.setText("Previous");
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goPrevious();   // Navigate to the previous slide
			}
		});
		buttonPane.add(btnPrev);

		// Set up the Next button and its click handler
		btnNext.setText("Next");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goNext();       // Navigate to the next slide
			}
		});
		buttonPane.add(btnNext);

		// Add the button panel to the south region (overlaid with textPane via BorderLayout)
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}

	/**
	 * goPrevious: navigates both the image panel and text panel to the previous card.
	 * CardLayout wraps around, so pressing Previous on slide 1 goes to slide 5.
	 */
	private void goPrevious() {
		card.previous(slidePane);
		cardText.previous(textPane);
	}

	/**
	 * goNext: navigates both the image panel and text panel to the next card.
	 * CardLayout wraps around, so pressing Next on slide 5 goes back to slide 1.
	 */
	private void goNext() {
		card.next(slidePane);
		cardText.next(textPane);
	}

	/**
	 * getResizeIcon: returns an HTML image tag for the given slide number.
	 * Images are loaded from the /resources/ folder inside the JAR.
	 * Width and height are fixed at 800x500 to fill the slide panel consistently.
	 * 
	 * Module Five change: slide images 2-5 now map to the five real destinations
	 * chosen by the Product Owner, replacing the generic TestImage references.
	 * 
	 * @param i  the slide number (1 through 5)
	 * @return   an HTML string that renders the destination image
	 */
	private String getResizeIcon(int i) {
		String image = "";
		if (i == 1) {
			// Slide 1: The Grand Canyon - retained from original starter code
			image = "<html><body><img width='800' height='500' src='"
					+ getClass().getResource("/resources/TestImage1.jpg") + "'</body></html>";
		} else if (i == 2) {
			// Slide 2: Yellowstone National Park - Wyoming, Montana, and Idaho
			image = "<html><body><img width='800' height='500' src='"
					+ getClass().getResource("/resources/TestImage2.jpg") + "'</body></html>";
		} else if (i == 3) {
			// Slide 3: The Florida Keys - island chain off the southern tip of Florida
			image = "<html><body><img width='800' height='500' src='"
					+ getClass().getResource("/resources/TestImage3.jpg") + "'</body></html>";
		} else if (i == 4) {
			// Slide 4: Arches National Park - located in Moab, Utah
			image = "<html><body><img width='800' height='500' src='"
					+ getClass().getResource("/resources/TestImage4.jpg") + "'</body></html>";
		} else if (i == 5) {
			// Slide 5: New Orleans - located in southeastern Louisiana
			image = "<html><body><img width='800' height='500' src='"
					+ getClass().getResource("/resources/TestImage5.jpg") + "'</body></html>";
		}
		return image;
	}

	/**
	 * getTextDescription: returns an HTML description string for the given slide number.
	 * Each description includes the destination's rank, name (in bold, larger font),
	 * and a one-sentence summary of what makes it a top travel destination.
	 * 
	 * Module Five change: descriptions for slides 2-5 have been fully written out
	 * to replace the "#N Top Destination" placeholder text from the starter code.
	 * Slide 1 description is retained from the original and lightly reformatted
	 * to match the style of the new slides.
	 * 
	 * @param i  the slide number (1 through 5)
	 * @return   an HTML string containing the destination name and description
	 */
	private String getTextDescription(int i) {
		String text = "";
		if (i == 1) {
			// Slide 1: The Grand Canyon - description retained from original starter code
			text = "<html><body><font size='5'>#1 The Grand Canyon</font>"
					+ " &nbsp;|&nbsp; Spectacular canyon views and world-class hiking in Arizona."
					+ "</body></html>";
		} else if (i == 2) {
			// Slide 2: Yellowstone - known for geysers, hot springs, and diverse wildlife
			text = "<html><body><font size='5'>#2 Yellowstone National Park</font>"
					+ " &nbsp;|&nbsp; Home to Old Faithful and stunning geothermal landscapes across Wyoming, Montana, and Idaho."
					+ "</body></html>";
		} else if (i == 3) {
			// Slide 3: The Florida Keys - a 125-mile island chain with coral reefs and clear water
			text = "<html><body><font size='5'>#3 The Florida Keys</font>"
					+ " &nbsp;|&nbsp; A tropical island chain offering vibrant coral reefs, world-class fishing, and relaxed coastal living."
					+ "</body></html>";
		} else if (i == 4) {
			// Slide 4: Arches National Park - home to over 2,000 natural sandstone arches
			text = "<html><body><font size='5'>#4 Arches National Park</font>"
					+ " &nbsp;|&nbsp; Utah's crown jewel featuring over 2,000 natural sandstone arches and breathtaking red-rock scenery."
					+ "</body></html>";
		} else if (i == 5) {
			// Slide 5: New Orleans - renowned for jazz music, Creole cuisine, and vibrant culture
			text = "<html><body><font size='5'>#5 New Orleans, Louisiana</font>"
					+ " &nbsp;|&nbsp; A vibrant city celebrated for its jazz heritage, Creole cuisine, and festive French Quarter atmosphere."
					+ "</body></html>";
		}
		return text;
	}

	/**
	 * main: entry point that launches the SlideShow application on the Event Dispatch Thread.
	 * Using EventQueue.invokeLater ensures the Swing UI is created safely on the EDT.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SlideShow ss = new SlideShow();
				ss.setVisible(true);
			}
		});
	}
}
