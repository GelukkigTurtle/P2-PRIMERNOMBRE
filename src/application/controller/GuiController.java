package application.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



public class GuiController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNacimiento;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableView<Genio> tableData;

    @FXML
    private TableColumn<Genio, Number> colId;

    @FXML
    private TableColumn<Genio,String > colNombres;
    

    @FXML
    private TableColumn<Genio, Number> colNacimiento;

    @FXML
    private Button btnLimpiar;
    
    ObservableList<Genio> listData;

	@Override
	public void initialize(URL url, ResourceBundle resources) {

		colId.setCellValueFactory((TableColumn.CellDataFeatures<Genio, Number> cellData) -> new SimpleIntegerProperty(
				cellData.getValue().getIdGenio()));

		colNombres.setCellValueFactory(
				(TableColumn.CellDataFeatures<Genio, String> cellData) -> new SimpleStringProperty(
						cellData.getValue().getNombre()));

		colNacimiento.setCellValueFactory((TableColumn.CellDataFeatures<Genio, Number> cellData) -> new SimpleIntegerProperty(
				cellData.getValue().getNacimiento()));

		listData = FXCollections.observableArrayList();
		this.listarGenios();
			
		tableData.getSelectionModel().clearSelection();
	}
	
		private void listarGenios () {
			Connection conn = null;
			ArrayList<Genio> genios = new ArrayList<Genio>();
			try {
				 conn = new ConexionBDD().getConnection();
				 String sql = "Select * from Genio";
	 			 System.out.println(sql);

				  ResultSet rs = conn.createStatement().executeQuery(sql);
		            while (rs.next()) {       
		                Genio a = new Genio();
		                a.setIdGenio(rs.getInt("idGenio"));
		                a.setNombre(rs.getString("nombre"));
		                a.setNacimiento(rs.getInt("nacimiento"));

		                genios.add(a);
		            }
		            listData = FXCollections.observableArrayList(genios);
		    		tableData.setItems(listData);

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

	

    @FXML
    void eventClickTable(MouseEvent event) {
    		
    }

    @FXML
    void eventGuardar(ActionEvent event) {
    	 String nombre = txtNombre.getText();
		 Integer nacimiento = Integer.valueOf(txtNacimiento.getText());
		 Genio genioAInsertar = new Genio(0,nombre,nacimiento);
		 Connection conn = null;
			try {
				conn = new ConexionBDD().getConnection();
				String insert = "INSERT INTO Genio (nombre,nacimiento) "
						+ " values (?,?)";
			    
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setString(1, genioAInsertar.getNombre());
				ps.setInt(2, genioAInsertar.getNacimiento());
				
				System.out.println(insert);
				System.out.println("1) "+genioAInsertar.getNombre());
				System.out.println("2) "+genioAInsertar.getNacimiento());
				
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			this.listarGenios();

    }

    @FXML
    void eventLimpiar(ActionEvent event) {
    	txtNombre.setText("");
    	txtNacimiento.setText("");

    }

}
