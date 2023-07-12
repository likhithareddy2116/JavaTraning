package com.gui.LibrarySystemWithJavaFx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryManagementSystemWithJavaFx extends Application {
	private TableView<Resource> tableView;
	private ObservableList<Resource> resources;

	private TextField titleField;
	private TextField authorField;
	private ComboBox<String> typeComboBox;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Library Management System");

		// Create the table view
		tableView = new TableView<>();
		resources = FXCollections.observableArrayList();

		TableColumn<Resource, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

		TableColumn<Resource, String> authorColumn = new TableColumn<>("Author");
		authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

		TableColumn<Resource, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

		tableView.getColumns().addAll(titleColumn, authorColumn, typeColumn);
		tableView.setItems(resources);

		// Create the form for adding resources
		Label titleLabel = new Label("Title:");
		titleField = new TextField();
		Label authorLabel = new Label("Author:");
		authorField = new TextField();
		Label typeLabel = new Label("Type:");
		typeComboBox = new ComboBox<>();
		typeComboBox.getItems().addAll("Book", "Magazine", "CD");

		Button addButton = new Button("Add");
		addButton.setOnAction(event -> addResource());

		Button updateButton = new Button("Update");
		updateButton.setOnAction(event -> updateResource());

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(event -> deleteResource());

		Button searchButton = new Button("Search");
		searchButton.setOnAction(event -> searchResource());

		HBox formBox = new HBox(10);
		formBox.getChildren().addAll(titleLabel, titleField, authorLabel, authorField, typeLabel, typeComboBox,
				addButton, updateButton, deleteButton, searchButton);

		// Create the main layout
		VBox mainLayout = new VBox(10);
		mainLayout.setPadding(new Insets(10));
		mainLayout.getChildren().addAll(tableView, formBox);

		// Load resources from file
		loadResourcesFromFile();
		

		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void addResource() {
		String title = titleField.getText();
		String author = authorField.getText();
		String type = typeComboBox.getValue();

		if (title.isEmpty() || author.isEmpty() || type == null) {
			showAlert("Error", "Please fill in all fields.");
			return;
		}

		Resource newResource = new Resource(title, author, type);
		resources.add(newResource);
		saveResourcesToFile();
		clearFields();
	}

	private void updateResource() {
		Resource selectedResource = tableView.getSelectionModel().getSelectedItem();

		if (selectedResource == null) {
			showAlert("Error", "No resource selected.");
			return;
		}

		String title = titleField.getText();
		String author = authorField.getText();
		String type = typeComboBox.getValue();

		if (title.isEmpty() || author.isEmpty() || type == null) {
			showAlert("Error", "Please fill in all fields.");
			return;
		}

		selectedResource.setTitle(title);
		selectedResource.setAuthor(author);
		selectedResource.setType(type);

		tableView.refresh();
		saveResourcesToFile();
		clearFields();
	}

	private void deleteResource() {
		Resource selectedResource = tableView.getSelectionModel().getSelectedItem();

		if (selectedResource == null) {
			showAlert("Error", "No resource selected.");
			return;
		}

		resources.remove(selectedResource);
		saveResourcesToFile();
	}

	private void searchResource() {
		String searchTitle = titleField.getText().trim();

		if (searchTitle.isEmpty()) {
			showAlert("Error", "Please enter a title to search.");
			return;
		}

		tableView.getSelectionModel().clearSelection();

		for (Resource resource : resources) {
			if (resource.getTitle().equalsIgnoreCase(searchTitle)) {
				tableView.getSelectionModel().select(resource);
				tableView.scrollTo(resource);
				return;
			}
		}

		showAlert("Not Found", "No resource found with the given title.");
	}

	private void clearFields() {
		titleField.clear();
		authorField.clear();
		typeComboBox.getSelectionModel().clearSelection();
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void loadResourcesFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("resources.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields.length == 3) {
					String title = fields[0];
					String author = fields[1];
					String type = fields[2];
					resources.add(new Resource(title, author, type));
				}
			}
		} catch (IOException e) {
			showAlert("Error", "Failed to load resources from file.");
		}
	}

	private void saveResourcesToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources.txt"))) {
			for (Resource resource : resources) {
				writer.write(resource.getTitle() + "," + resource.getAuthor() + "," + resource.getType());
				writer.newLine();
			}
		} catch (IOException e) {
			showAlert("Error", "Failed to save resources to file.");
		}
	}

	private static class Resource {
		private final StringProperty title;
		private final StringProperty author;
		private final StringProperty type;

		public Resource(String title, String author, String type) {
			this.title = new SimpleStringProperty(title);
			this.author = new SimpleStringProperty(author);
			this.type = new SimpleStringProperty(type);
		}

		public void setType(String type) {
			this.type.set(type);
		}

		public void setAuthor(String author) {
			this.author.set(author);
		}

		public void setTitle(String title) {
			this.title.set(title);
		}

		public String getTitle() {
			return title.get();
		}

		public String getAuthor() {
			return author.get();
		}

		public String getType() {
			return type.get();
		}

		public StringProperty titleProperty() {
			return title;
		}

		public StringProperty authorProperty() {
			return author;
		}

		public StringProperty typeProperty() {
			return type;
		}
	}
}
