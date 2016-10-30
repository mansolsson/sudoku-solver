package mansolsson.sudoku.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class Gui extends Application {
    private final List<TextField> fields = new ArrayList<>();

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final GridPane gridPane = createGridPane();

        final Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("sudoku.css");

        primaryStage.setTitle("Sudoku solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        final GridPane gridPane = new GridPane();
        addBoard(gridPane);
        addSolveButton(gridPane);
        addClearButton(gridPane);
        return gridPane;
    }

    private void addBoard(final GridPane gridPane) {
        for (int y = 0; y < SudokuBoard.ROW_WIDTH; y++) {
            for (int x = 0; x < SudokuBoard.ROW_WIDTH; x++) {
                final StackPane stackPane = createCell(x, y);
                gridPane.add(stackPane, x, y);
            }
        }
    }

    private final StackPane createCell(final int x, final int y) {
        final StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("cell");
        if (x == 2 || x == 5) {
            stackPane.getStyleClass().add("right");
        }
        if (y == 2 || y == 5) {
            stackPane.getStyleClass().add("bottom");
        }
        final TextField textField = createTextField();
        stackPane.getChildren().add(textField);
        fields.add(textField);
        return stackPane;
    }

    private TextField createTextField() {
        final TextField textField = new TextField();
        textField.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches("\\d?") ? c : null));
        return textField;
    }

    private void addSolveButton(final GridPane gridPane) {
        final Button solveButton = new Button();
        solveButton.setText("Solve");
        solveButton.setOnAction(e -> solveBoard());
        gridPane.add(solveButton, 0, 9, 2, 1);
    }

    private void solveBoard() {
        final List<Integer> valuesFromGui = fields.stream().map(TextField::getText).map(this::getNumber)
                .collect(Collectors.toList());

        final SudokuPuzzle puzzle = new SudokuPuzzle(valuesFromGui);
        final boolean result = puzzle.solve();

        if (!result) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No possible solution.");
            alert.showAndWait();
        } else {
            for (int i = 0; i < fields.size(); i++) {
                fields.get(i).setText(puzzle.getBoard().get(i).toString());
            }
        }
    }

    private Integer getNumber(final String number) {
        try {
            return Integer.valueOf(number);
        } catch (final NumberFormatException e) {
            return null;
        }
    }

    private void addClearButton(final GridPane gridPane) {
        final Button clearButton = new Button();
        clearButton.setText("Clear");
        clearButton.setOnAction(e -> clearBoard());
        gridPane.add(clearButton, 7, 9, 2, 1);
    }

    private void clearBoard() {
        fields.forEach(textField -> textField.setText(""));
    }
}
