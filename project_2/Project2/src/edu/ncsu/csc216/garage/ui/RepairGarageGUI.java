package edu.ncsu.csc216.garage.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;

import edu.ncsu.csc216.garage.model.dealer.Manageable;
import edu.ncsu.csc216.garage.model.dealer.ServiceManager;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

/**
 * GUI class for Service Garage
 * 
 * @author premsubedi
 * @author someshherath
 *
 */
public class RepairGarageGUI extends JFrame implements ActionListener {

	/** default id */
	private static final long serialVersionUID = 1L;

	private DefaultListModel<String> modelCars = new DefaultListModel<String>();
	private JList<String> jlistCars = new JList<String>(modelCars);
	private JScrollPane scrollPane = new JScrollPane(jlistCars);
	private JLabel labelFilter = new JLabel("Display Filter: ");
	private JTextField textFilter = new JTextField(10);
	private JButton buttonAddNewVehicle = new JButton("Add New Vehicle");
	private JButton buttonEditSelectedVehicle = new JButton("Edit Selected Vehicle");
	private JButton buttonRemoveSelectedVehicle = new JButton("Remove Selected Vehicle");
	private String pneVehicleLabel = "Vehicles Awaiting Service";

	private DefaultListModel<String> modelBays = new DefaultListModel<String>();
	private JList<String> jlistBays = new JList<String>(modelBays);
	private JScrollPane jpaneBays = new JScrollPane(jlistBays);
	private JButton buttonAddServiceBay = new JButton("Add Service Bay");
	private JButton buttonFillServiceBays = new JButton("Fill Service Bay");
	private JButton buttonFinishRepair = new JButton("Finish Repair of Selected Vehicle");
	private String paneBayLabel = "Service Bays";
	private JButton buttonQuit = new JButton("Quit");
	private Manageable serviceManager = new ServiceManager();

	/**
	 * Create the application.
	 */
	public RepairGarageGUI() {
		this("");

	}

	/**
	 * Creates the application
	 * 
	 * @param s
	 *            the string to process
	 */
	public RepairGarageGUI(String s) {

		if (s == null || s.isEmpty()) {

			try {

				s = getFileName(true);
				serviceManager = new ServiceManager();
			} catch (IllegalStateException ex) {
				JOptionPane.showMessageDialog(null, "No file selected.");
				dispose();
				return;
			}
		}

		if (!s.isEmpty()) {
			try {
				File file = new File(s);
				Scanner scan = new Scanner(file);
				serviceManager = new ServiceManager(scan);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Vehicle file not found.");
				return;
			}
		}
		Container c = this.getContentPane();
		fillModel();
		c.setLayout(new GridLayout(1, 2));

		JPanel pnlVehicles = new JPanel(new BorderLayout());
		JPanel pnlVehButtons = new JPanel();
		JPanel pnlBays = new JPanel(new BorderLayout());
		JPanel pnlBayButtons = new JPanel();

		c.add(pnlVehicles);
		c.add(pnlBays);

		jlistCars.setFont(new Font("Courier", Font.PLAIN, 12));
		jlistCars.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setPreferredSize(new Dimension(230, 100));

		jlistBays.setFont(new Font("Courier", Font.PLAIN, 12));
		jlistBays.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jpaneBays.setPreferredSize(new Dimension(230, 100));

		buttonAddNewVehicle.addActionListener(this);
		buttonEditSelectedVehicle.addActionListener(this);
		buttonRemoveSelectedVehicle.addActionListener(this);
		textFilter.addActionListener(this);
		buttonAddServiceBay.addActionListener(this);
		buttonFillServiceBays.addActionListener(this);
		buttonFinishRepair.addActionListener(this);
		buttonQuit.addActionListener(this);

		JPanel pnlFilter = new JPanel();
		pnlFilter.add(labelFilter);
		pnlFilter.add(textFilter);

		JPanel pnlVehControls = new JPanel(new GridLayout(2, 1));
		JPanel pnlBayControls = new JPanel(new GridLayout(2, 1));

		pnlBays.setBorder(new TitledBorder(paneBayLabel));
		pnlBayButtons.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		pnlBayButtons.add(buttonAddServiceBay, c1);
		c1.gridy = 1;
		pnlBayButtons.add(buttonFillServiceBays, c1);
		c1.gridy = 2;
		pnlBayButtons.add(buttonFinishRepair, c1);
		pnlBayControls.add(pnlBayButtons);
		pnlBays.add(pnlBayButtons, BorderLayout.NORTH);
		pnlBays.add(jpaneBays, BorderLayout.CENTER);
		pnlBays.add(buttonQuit, BorderLayout.SOUTH);

		pnlVehicles.setBorder(new TitledBorder(pneVehicleLabel));
		pnlVehButtons.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		pnlVehButtons.add(buttonAddNewVehicle, c2);
		c2.gridy = 1;
		pnlVehButtons.add(buttonEditSelectedVehicle, c2);
		c2.gridy = 2;
		pnlVehButtons.add(buttonRemoveSelectedVehicle, c2);
		pnlVehControls.add(pnlVehButtons);
		pnlVehControls.add(pnlFilter);
		pnlVehicles.add(pnlVehControls, BorderLayout.NORTH);
		pnlVehicles.add(scrollPane, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 600);
		setVisible(true);
	}

	/**
	 * overrides actionPerformed
	 * 
	 * @param event
	 *            the ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource().equals(buttonQuit)) {
			System.exit(0);
		}

		if (event.getSource().equals(buttonAddNewVehicle)) {
			UserDialog pane = new UserDialog();
			pane.setVisible(true);
			Vehicle vehicle = pane.getNewVehicle();
			if (vehicle != null)
				serviceManager.putOnWaitingList(vehicle);
		}

		if (event.getSource().equals(buttonEditSelectedVehicle)) {

			int index = jlistCars.getSelectedIndex();
			if (index >= 0) {

				Vehicle vehicle = (Vehicle) serviceManager.getWaitingItem(textFilter.getText(), index);
				UserDialog pane = new UserDialog(vehicle);
				pane.setVisible(true);
				int tier = 0;

				Vehicle v = pane.getNewVehicle();
				if (v != null)
					tier = v.getTier();

				if (tier < 0 || tier > 3) {
					tier = 0;
				}

				try {
					vehicle.setTier(tier);
				} catch (BadVehicleInformationException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		}

		if (event.getSource().equals(buttonRemoveSelectedVehicle)) {

			int index = jlistCars.getSelectedIndex();
			if (index >= 0)
				serviceManager.remove("", index);
		}

		if (event.getSource().equals(textFilter)) {

			try {

				modelCars.clear();

				String[] cars = serviceManager.printWaitList(textFilter.getText()).split("\n");

				for (int i = 0; i < cars.length; i++) {
					modelCars.addElement(cars[i]);
				}

			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}

			return;

		}

		if (event.getSource().equals(buttonAddServiceBay)) {
			try {
				serviceManager.addNewBay();
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		if (event.getSource().equals(buttonFillServiceBays)) {
			serviceManager.fillServiceBays();
		}

		if (event.getSource().equals(buttonFinishRepair)) {

			int index = jlistBays.getSelectedIndex();
			if (index >= 0)
				serviceManager.releaseFromService(index);
		}

		fillModel();
	}

	/**
	 * fill model
	 */
	private void fillModel() {

		modelCars.clear();

		String[] cars = serviceManager.printWaitList(null).split("\n");

		for (int i = 0; i < cars.length; i++) {
			modelCars.addElement(cars[i]);
		}

		modelBays.clear();

		String[] bays = serviceManager.printServiceBays().split("\n");

		for (int i = 0; i < bays.length; i++) {
			modelBays.addElement(bays[i]);
		}

	}

	/**
	 * returns a file name generated through a JFileChooser
	 * 
	 * @param chooserType
	 *            true if open/false if saved
	 * @return file name generated through JFileChooser
	 */
	private String getFileName(boolean chooserType) {
		JFileChooser fc = new JFileChooser("./");

		fc.setApproveButtonText("Select");
		int returnVal = Integer.MIN_VALUE;

		if (chooserType) {
			fc.setDialogTitle("Vehicle Directory");
			returnVal = fc.showOpenDialog(this);
		}

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			throw new IllegalStateException();
		}

		File catalogFile = fc.getSelectedFile();
		return catalogFile.getAbsolutePath();
	}

	/**
	 * starts the main application
	 * 
	 * @param args
	 *            argument
	 */
	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			new RepairGarageGUI();
		} else if (args.length == 1 && args[0] != null) {
			new RepairGarageGUI(args[0]);
		}
	}
}
