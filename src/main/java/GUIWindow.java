import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUIWindow extends JFrame {
	
	/** The folder field. */
	JTextField folderField;

	/* The savedir field. 
	JTextField savedirField;

	/** The name label. 
	JLabel nameLabel;

	/** The name field. 
	JTextField nameField;

	/** The container label. */
	JLabel containerLabel;

	/** The container field. */
	JTextField containerField;

	/** The container button. */
	JButton containerButton;

	/** The loglevel combo box. 
	@SuppressWarnings("rawtypes")
	JComboBox loglevelComboBox;

	/** The warning check box. 
	JCheckBox warningCheckBox;

	/** The keeponlydeep check box. 
	JCheckBox keeponlydeepCheckBox;

	/** The dropemptyfolders check box. 
	JCheckBox dropemptyfoldersCheckBox;

	/** The names length field. 
	JTextField namesLengthField;

	/** The text extraction flags. 
	JCheckBox extractmessagetextfileCheckBox;
	
	/** The extractmessagetextmetadata check box. 
	JCheckBox extractmessagetextmetadataCheckBox;
	
	/** The extractfiletextfile check box. 
	JCheckBox extractfiletextfileCheckBox;
	
	/** The extractfiletextmetadata check box. 
	JCheckBox extractfiletextmetadataCheckBox;

	/** The console text area. */
	JTextArea consoleTextArea;
	private JScrollPane scrollPane;

	/** The proposed log level. 
	String[] loglevelGraphicStrings = { "OFF", "ERREUR FATALE", "AVERTISSEMENT", "INFO PROCESS", "INFO DOSSIERS",
			"INFO MESSAGES", "DETAIL MESSAGES" };*/
	private JPanel panel;
	
	GUIApp app;

	public GUIWindow(GUIApp app) throws HeadlessException {
		super();
		this.app = app;
		initialize();
	}
/*
	public GUIWindow(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public GUIWindow(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public GUIWindow(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
*/
	
	private void initialize() {
		/*java.net.URL imageURL = getClass().getClassLoader().getResource("Logo96.png");
		if (imageURL != null) {
			ImageIcon icon = new ImageIcon(imageURL);
			setIconImage(icon.getImage());
		}*/
		this.setTitle("Extraction de PST");

		getContentPane().setPreferredSize(new Dimension(800, 300));
		setBounds(0, 0, 800, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0, 1, 1};
		gridBagLayout.rowHeights = new int[] { 25, 10, 0, 0, 0, 25};
		getContentPane().setLayout(gridBagLayout);
		
		/*JPanel extractTextPanel = new JPanel();
		extractTextPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_extractTextPanel = new GridBagConstraints();
		gbc_extractTextPanel.gridwidth = 4;
		gbc_extractTextPanel.weightx = 1.0;
		gbc_extractTextPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_extractTextPanel.insets = new Insets(0, 10, 10, 10);
		gbc_extractTextPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_extractTextPanel.gridx = 0;
		gbc_extractTextPanel.gridy = 8;
		getContentPane().add(extractTextPanel, gbc_extractTextPanel);
		GridBagLayout gbl_extractTextPanel = new GridBagLayout();
		gbl_extractTextPanel.rowWeights = new double[] { 1.0, 1.0};
		gbl_extractTextPanel.columnWeights = new double[] { 1.0, 1, 1};
		extractTextPanel.setLayout(gbl_extractTextPanel);

		JLabel lblNewLabel = new JLabel("Extraire le texte");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		extractTextPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		extractmessagetextfileCheckBox = new JCheckBox("Message -> Fichier");
		GridBagConstraints gbc_chckbxMessageTextFileBox = new GridBagConstraints();
		gbc_chckbxMessageTextFileBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxMessageTextFileBox.gridx = 1;
		gbc_chckbxMessageTextFileBox.gridy = 0;
		extractTextPanel.add(extractmessagetextfileCheckBox, gbc_chckbxMessageTextFileBox);
		
		extractmessagetextmetadataCheckBox = new JCheckBox("Message -> Metadonnée");
		GridBagConstraints gbc_chckbxMessageTextMetadata = new GridBagConstraints();
		gbc_chckbxMessageTextMetadata.anchor = GridBagConstraints.WEST;
		gbc_chckbxMessageTextMetadata.gridx = 1;
		gbc_chckbxMessageTextMetadata.gridy = 1;
		extractTextPanel.add(extractmessagetextmetadataCheckBox, gbc_chckbxMessageTextMetadata);

		extractfiletextfileCheckBox = new JCheckBox("Pièces jointes -> Fichier");
		GridBagConstraints gbc_chckbxFileTextFileBox = new GridBagConstraints();
		gbc_chckbxFileTextFileBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxFileTextFileBox.gridx = 2;
		gbc_chckbxFileTextFileBox.gridy = 0;
		extractTextPanel.add(extractfiletextfileCheckBox, gbc_chckbxFileTextFileBox);
		
		extractfiletextmetadataCheckBox = new JCheckBox("Pièces jointes -> Metadonnée");
		GridBagConstraints gbc_chckbxFileTextMetadata = new GridBagConstraints();
		gbc_chckbxFileTextMetadata.anchor = GridBagConstraints.WEST;
		gbc_chckbxFileTextMetadata.gridx = 2;
		gbc_chckbxFileTextMetadata.gridy = 1;
		extractTextPanel.add(extractfiletextmetadataCheckBox, gbc_chckbxFileTextMetadata);*/
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridwidth = 3;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);

		consoleTextArea = new JTextArea();
		consoleTextArea.setFont(new Font("Courier 10 Pitch", Font.BOLD, 12));
		consoleTextArea.setLineWrap(true);

		scrollPane = new JScrollPane(consoleTextArea);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		getContentPane().add(scrollPane, gbc_scrollPane);

		JButton extractButton = new JButton("Extraire messages");
		GridBagConstraints gbc_extractButton = new GridBagConstraints();
		gbc_extractButton.gridwidth = 2;
		gbc_extractButton.insets = new Insets(0, 150, 0, 150);
		gbc_extractButton.fill = GridBagConstraints.BOTH;
		gbc_extractButton.gridx = 1;
		gbc_extractButton.gridy = 3;
		getContentPane().add(extractButton, gbc_extractButton);
		extractButton.setActionCommand("extract");
		extractButton.addActionListener(app);

		JButton emptyButton = new JButton("Vider log");
		GridBagConstraints gbc_emptyButton = new GridBagConstraints();
		gbc_emptyButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_emptyButton.insets = new Insets(0, 0, 0, 0);
		gbc_emptyButton.gridx = 3;
		gbc_emptyButton.gridy = 5;
		getContentPane().add(emptyButton, gbc_emptyButton);
		emptyButton.setActionCommand("empty");
		emptyButton.addActionListener(app);

		/*warningCheckBox =  new JCheckBox("Remonte les pbs sur les messages");
		GridBagConstraints gbc_warningCheckBox = new GridBagConstraints();
		gbc_warningCheckBox.anchor = GridBagConstraints.WEST;
		gbc_warningCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_warningCheckBox.gridx = 2;
		gbc_warningCheckBox.gridy = 9;
		getContentPane().add(warningCheckBox, gbc_warningCheckBox);

		loglevelComboBox = new JComboBox(loglevelGraphicStrings);
		GridBagConstraints gbc_loglevelComboBox = new GridBagConstraints();
		gbc_loglevelComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_loglevelComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_loglevelComboBox.gridx = 1;
		gbc_loglevelComboBox.gridy = 9;
		getContentPane().add(loglevelComboBox, gbc_loglevelComboBox);

		JLabel loglevelLabel = new JLabel("Niveau de log");
		GridBagConstraints gbc_loglevelLabel = new GridBagConstraints();
		gbc_loglevelLabel.anchor = GridBagConstraints.EAST;
		gbc_loglevelLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loglevelLabel.gridx = 0;
		gbc_loglevelLabel.gridy = 9;
		getContentPane().add(loglevelLabel, gbc_loglevelLabel);

		keeponlydeepCheckBox = new JCheckBox("Garder dossier 1er niv");
		GridBagConstraints gbc_keeponlydeepRadioButton = new GridBagConstraints();
		gbc_keeponlydeepRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_keeponlydeepRadioButton.gridx = 1;
		gbc_keeponlydeepRadioButton.gridy = 5;
		getContentPane().add(keeponlydeepCheckBox, gbc_keeponlydeepRadioButton);

		JLabel namesLengthLabel = new JLabel("Longueur gardée noms");
		GridBagConstraints gbc_namesLengthLabel = new GridBagConstraints();
		gbc_namesLengthLabel.anchor = GridBagConstraints.EAST;
		gbc_namesLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_namesLengthLabel.gridx = 2;
		gbc_namesLengthLabel.gridy = 5;
		getContentPane().add(namesLengthLabel, gbc_namesLengthLabel);

		namesLengthField = new JTextField();
		GridBagConstraints gbc_namesLengthField = new GridBagConstraints();
		gbc_namesLengthField.weightx = 0.5;
		gbc_namesLengthField.insets = new Insets(0, 0, 5, 10);
		gbc_namesLengthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_namesLengthField.gridx = 3;
		gbc_namesLengthField.gridy = 5;
		getContentPane().add(namesLengthField, gbc_namesLengthField);
		namesLengthField.setColumns(128);

		JButton savedirButton = new JButton("Répertoire...");
		GridBagConstraints gbc_savedirButton = new GridBagConstraints();
		gbc_savedirButton.insets = new Insets(0, 0, 5, 0);
		gbc_savedirButton.gridx = 3;
		gbc_savedirButton.gridy = 6;
		getContentPane().add(savedirButton, gbc_savedirButton);
		savedirButton.setActionCommand("savedir");
		//savedirButton.addActionListener(app);

		savedirField = new JTextField();
		GridBagConstraints gbc_savedirField = new GridBagConstraints();
		gbc_savedirField.gridwidth = 2;
		gbc_savedirField.insets = new Insets(0, 0, 5, 5);
		gbc_savedirField.fill = GridBagConstraints.HORIZONTAL;
		gbc_savedirField.gridx = 1;
		gbc_savedirField.gridy = 6;
		gbc_savedirField.weightx = 0.5;
		getContentPane().add(savedirField, gbc_savedirField);

		dropemptyfoldersCheckBox = new JCheckBox("Eliminer dossiers vides");
		GridBagConstraints gbc_dropemptyfoldersCheckBox = new GridBagConstraints();
		gbc_dropemptyfoldersCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_dropemptyfoldersCheckBox.gridx = 0;
		gbc_dropemptyfoldersCheckBox.gridy = 5;
		getContentPane().add(dropemptyfoldersCheckBox, gbc_dropemptyfoldersCheckBox);

		JLabel savedirLabel = new JLabel("Répertoire d'extraction");
		GridBagConstraints gbc_savedirLabel = new GridBagConstraints();
		gbc_savedirLabel.anchor = GridBagConstraints.EAST;
		gbc_savedirLabel.insets = new Insets(0, 0, 5, 5);
		gbc_savedirLabel.gridx = 0;
		gbc_savedirLabel.gridy = 6;
		getContentPane().add(savedirLabel, gbc_savedirLabel);

		folderField = new JTextField();
		GridBagConstraints gbc_folderField = new GridBagConstraints();
		gbc_folderField.gridwidth = 3;
		gbc_folderField.weightx = 0.5;
		gbc_folderField.insets = new Insets(0, 0, 5, 10);
		gbc_folderField.fill = GridBagConstraints.HORIZONTAL;
		gbc_folderField.gridx = 1;
		gbc_folderField.gridy = 4;
		getContentPane().add(folderField, gbc_folderField);
		folderField.setColumns(128);

		JLabel folderLabel = new JLabel("Dossier Racine");
		GridBagConstraints gbc_folderLabel = new GridBagConstraints();
		gbc_folderLabel.anchor = GridBagConstraints.EAST;
		gbc_folderLabel.insets = new Insets(0, 0, 5, 5);
		gbc_folderLabel.gridx = 0;
		gbc_folderLabel.gridy = 4;
		getContentPane().add(folderLabel, gbc_folderLabel);

		JPanel localPanel = new JPanel();
		localPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_localPanel = new GridBagConstraints();
		gbc_localPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_localPanel.gridwidth = 4;
		gbc_localPanel.insets = new Insets(0, 10, 10, 10);
		gbc_localPanel.weightx = 0.5;
		gbc_localPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_localPanel.gridx = 0;
		gbc_localPanel.gridy = 1;
		getContentPane().add(localPanel, gbc_localPanel);
		GridBagLayout gbl_containerPanel = new GridBagLayout();
		localPanel.setLayout(gbl_containerPanel);

		nameLabel = new JLabel("Nom de l'extraction");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 7;
		getContentPane().add(nameLabel, gbc_nameLabel);

		nameField = new JTextField();
		nameField.setText("");
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameField.weightx = 0.5;
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 7;
		getContentPane().add(nameField, gbc_nameField);
		nameField.setColumns(128);*/

		containerLabel = new JLabel("Chemin : ");
		GridBagConstraints gbc_containerLabel = new GridBagConstraints();
		gbc_containerLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_containerLabel.insets = new Insets(5, 10, 0, 0);
		gbc_containerLabel.gridx = 0;
		gbc_containerLabel.gridy = 1;
		getContentPane().add(containerLabel, gbc_containerLabel);

		containerField = new JTextField();
		containerField.setText("");
		GridBagConstraints gbc_containerField = new GridBagConstraints();
		gbc_containerField.gridwidth = 2;
		gbc_containerField.insets = new Insets(0, 0, 0, 0);
		gbc_containerField.anchor = GridBagConstraints.NORTHWEST;
		//gbc_containerField.weightx = 1.0;
		gbc_containerField.fill = GridBagConstraints.HORIZONTAL;
		gbc_containerField.gridx = 1;
		gbc_containerField.gridy = 1;
		getContentPane().add(containerField, gbc_containerField);
		containerField.setColumns(128);

		containerButton = new JButton("Choisir...");
		GridBagConstraints gbc_containerButton = new GridBagConstraints();
		gbc_containerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_containerButton.gridx = 3;
		gbc_containerButton.gridy = 1;
		getContentPane().add(containerButton, gbc_containerButton);
		containerButton.setActionCommand("container");
		containerButton.addActionListener(app);

		

		pack();
	}

}
