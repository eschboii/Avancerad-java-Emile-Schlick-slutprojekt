package org.example.todofe;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HelloController {

    private final String hostUrl = "http://localhost:8080/api/todo";

    @FXML
    public void initialize() {
        tblColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColUppgift.setCellValueFactory(new PropertyValueFactory<>("task"));
        tblColBeskr.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    private AnchorPane AP1;

    @FXML
    private AnchorPane AP2;

    @FXML
    private VBox Vbx;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_getAllTasks;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Todo, Integer> tblColBeskr;

    @FXML
    private TableColumn<Todo, Integer> tblColID;

    @FXML
    private TableColumn<Todo, Integer> tblColUppgift;

    @FXML
    private TableView<Todo> tblV;

    @FXML
    private TextArea txtAreaInp_beskriv;

    @FXML
    private TextArea txtArea_response;

    @FXML
    private TextField txtFldInp_id;

    @FXML
    private TextField txtFldInp_uppg;

    @FXML
    private Label welcomeText;

    @FXML
    void addNewTask(ActionEvent event) {
        try {
            int todoID = Integer.parseInt(txtFldInp_id.getText());
            String todoUppgift = txtFldInp_uppg.getText();
            String todoBeskrivning = txtAreaInp_beskriv.getText();

            Todo todo = new Todo(todoID, todoUppgift, todoBeskrivning);

            if (todoUppgift.isEmpty() || todoBeskrivning.isEmpty()) {
                txtArea_response.setText("Please enter all books in the book title");
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(todo);

            URL url = new URL(hostUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }

            String response = readResponse(connection);
            txtArea_response.setText("Task '" + todoUppgift + "' has been added.");

            populateTable(response);

        } catch (Exception e) {
            txtArea_response.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }

        clearInput();
    }

    @FXML
    void changeTask(ActionEvent event) {
        try {
            int todoID = Integer.parseInt(txtFldInp_id.getText());
            String todoUppgift = txtFldInp_uppg.getText();
            String todoBeskrivning = txtAreaInp_beskriv.getText();

            if (todoUppgift.isEmpty() || todoBeskrivning.isEmpty()) {
                txtArea_response.setText("Fyll i uppgift och beskrivning");
                return;
            }

            Todo updatedTodo = new Todo(todoID, todoUppgift, todoBeskrivning);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(updatedTodo);

            URL url = new URL(hostUrl + "/" + todoID);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }

            String response = readResponse(connection);
            txtArea_response.setText(response);

            if (connection.getResponseCode() == 200) {
                updateTableWithUpdatedTask(updatedTodo);
            }

        } catch (Exception e) {
            txtArea_response.setText("Error updating task: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void deleteTaskID(ActionEvent event) {
        Todo selectedTodo = tblV.getSelectionModel().getSelectedItem();

        if (selectedTodo != null) {
            tblV.getItems().remove(selectedTodo);
            deleteTodoFromBackend(selectedTodo.getId());

        } else {
            txtArea_response.setText("Vänligen välj vilken uppgift du vill ta bort");
        }
    }

    @FXML
    void getAllTasks(ActionEvent event) {
        try {
            URL url = new URL(hostUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            String response = readResponse(connection);
            txtArea_response.setText("Uppgifter laddade");

            populateTable(response);

        } catch (Exception e) {
            txtArea_response.setText("ErrorPerror" + e.getMessage());
        }
    }

    private String readResponse(HttpURLConnection connection) throws IOException {

        BufferedReader reader;

        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString();
    }

    private void populateTable(String jsonResponse) {
        tblV.getItems().clear();

        JSONArray tasks = new JSONArray(jsonResponse);

        for (int i = 0; i < tasks.length(); i++) {
            JSONObject task = tasks.getJSONObject(i);

            int id = task.getInt("id");
            String uppgift = task.getString("task");
            String beskrivning = task.getString("description");

            tblV.getItems().add(new Todo(id, uppgift, beskrivning));
        }
    }

    private void clearInput() {
        txtFldInp_id.setText("");
        txtFldInp_uppg.setText("");
        txtAreaInp_beskriv.setText("");
    }

    private void deleteTodoFromBackend(int todoId) {
        try {
            URL url = new URL(hostUrl + "/" + todoId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            String response = readResponse(connection);
            txtArea_response.setText(response);

        } catch (Exception e) {
            txtArea_response.setText("Error deleting task.");
        }
    }

    private void updateTableWithUpdatedTask(Todo updatedTodo) {
        for (Todo todo : tblV.getItems()) {
            if (todo.getId() == updatedTodo.getId()) {
                todo.setTask(updatedTodo.getTask());
                todo.setDescription(updatedTodo.getDescription());
                tblV.refresh();
                break;
            }
        }
    }


}
