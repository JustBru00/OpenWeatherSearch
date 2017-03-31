/**
 * 
 */
package weatherGenie;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.OpenWeatherData;

import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.awt.event.ActionEvent;

/**
 * @author read
 *
 */
public class WeatherGenie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1457178164377562121L;
	private JPanel contentPane;
	private JTextField searchString;
	private HashMap<String, OpenWeatherData> history = new HashMap<>();
	private JLabel txtLocation;
	private JLabel txtConditions;
	private JLabel txtCurrTemp;
	private JLabel txtMinTemp;
	private JLabel txtMaxTemp;
	private JLabel txtWindSpeed;
	private JLabel txtWindDir;
	private JLabel txtError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherGenie frame = new WeatherGenie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WeatherGenie() {
		// instantiate the bean for data
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWeatherGenie = new JLabel("Weather Genie");
		lblWeatherGenie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWeatherGenie.setBounds(24, 11, 104, 14);
		contentPane.add(lblWeatherGenie);

		JLabel lblQuery = new JLabel("Query");
		lblQuery.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuery.setBounds(67, 44, 46, 14);
		contentPane.add(lblQuery);

		searchString = new JTextField();
		searchString.setBounds(119, 41, 199, 20);
		contentPane.add(searchString);
		searchString.setColumns(10);

		JButton btnFavorite = new JButton("Favorite");
		btnFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchString.setText(Reference.favoriteQuery);
			}
		});
		btnFavorite.setBounds(238, 72, 73, 23);
		contentPane.add(btnFavorite);

		JLabel lblLocation = new JLabel("Weather for");
		lblLocation.setForeground(Color.BLUE);
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(40, 128, 73, 14);
		contentPane.add(lblLocation);

		txtLocation = new JLabel("");
		txtLocation.setBounds(119, 128, 204, 14);
		contentPane.add(txtLocation);

		JLabel lblConditons = new JLabel("Conditons");
		lblConditons.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConditons.setBounds(50, 156, 63, 14);
		contentPane.add(lblConditons);

		txtConditions = new JLabel("");
		txtConditions.setBounds(117, 156, 131, 14);
		contentPane.add(txtConditions);

		JLabel lblTempsCurr = new JLabel("Temps  Curr");
		lblTempsCurr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTempsCurr.setBounds(40, 185, 73, 14);
		contentPane.add(lblTempsCurr);

		txtCurrTemp = new JLabel("");
		txtCurrTemp.setBounds(118, 185, 36, 14);
		contentPane.add(txtCurrTemp);

		JLabel lblMinMax = new JLabel("Min / Max");
		lblMinMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMinMax.setBounds(164, 185, 63, 14);
		contentPane.add(lblMinMax);

		txtMinTemp = new JLabel("");
		txtMinTemp.setBounds(238, 185, 36, 14);
		contentPane.add(txtMinTemp);

		txtMaxTemp = new JLabel("");
		txtMaxTemp.setBounds(280, 185, 36, 14);
		contentPane.add(txtMaxTemp);

		JLabel lblWindSpeed = new JLabel("Wind Speed / Dir");
		lblWindSpeed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWindSpeed.setBounds(10, 210, 94, 14);
		contentPane.add(lblWindSpeed);

		txtWindSpeed = new JLabel("");
		txtWindSpeed.setBounds(122, 210, 36, 14);
		contentPane.add(txtWindSpeed);

		txtWindDir = new JLabel("");
		txtWindDir.setBounds(164, 210, 110, 14);
		contentPane.add(txtWindDir);

		txtError = new JLabel("");
		txtError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		txtError.setForeground(Color.RED);
		txtError.setBounds(40, 236, 278, 14);
		contentPane.add(txtError);

		JButton btnQuery = new JButton("Query");
		btnQuery.setBounds(129, 72, 79, 23);
		contentPane.add(btnQuery);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear the display
				clearAllDataFields();

				OpenWeatherData data;

				// If we have a query in the history
				if (history.containsKey(searchString.getText())) {
					//If it was a success
					if (history.get(searchString.getText()).isWebServiceCallSuccess()) {

						// The current time for comparasion
						Instant now = Instant.now();

						// The time that elapsed between the data in history and
						// the current time
						Duration timeElapsed = Duration.between(history.get(searchString.getText()).getTimeCreated(),
								now);

						// the mins that have elapsed
						long mins = timeElapsed.getSeconds() / 60;

						// If the data is younger than 30 mins
						if (mins < 30) {
							data = history.get(searchString.getText());
							System.out.println("Using data from history");
						} else {
							// Otherwise get some new data.
							data = new GetWeatherData().getWeatherData(searchString.getText());
							System.out.println("Old data in history. Got new data from web service");
						}
					} else { // Call failed. Try again.
						data = new GetWeatherData().getWeatherData(searchString.getText());
						System.out.println("The call failed before. Trying again.");
					}
				} else { // If the history has nothing in it. Get the weather
							// data from webservice
					data = new GetWeatherData().getWeatherData(searchString.getText());
					System.out.println("Nothing is history. Getting some new data.");
				}

				history.put(searchString.getText(), data);

				if (data.isWebServiceCallSuccess()) {

					// Set fields on display
					txtError.setText(data.getWebServiceCallMessage());
					txtConditions.setText(data.getSummary() + " (" + data.getDescription() + ")");
					txtCurrTemp.setText(Integer.toString(data.getTempInDegreesFarenheit(data.getTemp())));
					txtLocation.setText(data.getLocationName() + ", " + data.getCountry());
					txtMaxTemp.setText(Integer.toString(data.getTempInDegreesFarenheit(data.getTempMax())));
					txtMinTemp.setText(Integer.toString(data.getTempInDegreesFarenheit(data.getTempMin())));
					txtWindDir.setText(data.getWindDirText(data.getWinddirection()));
					txtWindSpeed.setText(Integer.toString(data.getWindSpeed()));

				} else {
					// Failed to get proper data. Just display the error message
					// for the user.
					txtError.setText(data.getWebServiceCallMessage());
				}

			}
		});

	}

	/**
	 * Clears the display locations on the screen.
	 */
	private void clearAllDataFields() {
		String empty = "";
		txtError.setText(empty);
		txtConditions.setText(empty);
		txtCurrTemp.setText(empty);
		txtLocation.setText(empty);
		txtMaxTemp.setText(empty);
		txtMinTemp.setText(empty);
		txtWindDir.setText(empty);
		txtWindSpeed.setText(empty);
	}

}
