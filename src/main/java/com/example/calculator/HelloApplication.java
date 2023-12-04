package com.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.commons.jexl3.*;
import org.apache.commons.jexl3.introspection.JexlUberspect;

public class HelloApplication extends Application {

    private TextField inputField;
    private JexlEngine jexl;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Shisir's Calculator");
        //stage.setScene(scene);
        //stage.show();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,10,10,10));

        inputField = new TextField();
        inputField.setEditable(false);
        grid.add(inputField,0,0,4,1);

        String[][] buttonLabels = {
                {"AC","+/-","%","/"},
                {"7", "8", "9", "X"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"0", ".", "="}
        };

        for (int i=0; i < buttonLabels.length; i++){
            for (int j =0; j < buttonLabels[i].length; j++){
                Button button = new Button(buttonLabels[i][j]);
                button.setOnAction(e -> handleButtonClick(button.getText()));
                grid.add(button,j,i+1);
            }
        }

        Scene scene = new Scene(grid,250,300);
        stage.setScene(scene);

        //initialize the JexlEngine
        jexl = new JexlEngine() {
            @Override
            public Charset getCharset() {
                return null;
            }

            @Override
            public JexlUberspect getUberspect() {
                return null;
            }

            @Override
            public JexlArithmetic getArithmetic() {
                return null;
            }

            @Override
            public boolean isDebug() {
                return false;
            }

            @Override
            public boolean isSilent() {
                return false;
            }

            @Override
            public boolean isStrict() {
                return false;
            }

            @Override
            public boolean isCancellable() {
                return false;
            }

            @Override
            public void setClassLoader(ClassLoader classLoader) {

            }

            @Override
            public JxltEngine createJxltEngine(boolean b, int i, char c, char c1) {
                return null;
            }

            @Override
            public void clearCache() {

            }

            @Override
            public JexlExpression createExpression(JexlInfo jexlInfo, String s) {
                return null;
            }

            @Override
            public JexlScript createScript(JexlFeatures jexlFeatures, JexlInfo jexlInfo, String s, String... strings) {
                return null;
            }

            @Override
            public Object getProperty(Object o, String s) {
                return null;
            }

            @Override
            public Object getProperty(JexlContext jexlContext, Object o, String s) {
                return null;
            }

            @Override
            public void setProperty(Object o, String s, Object o1) {

            }

            @Override
            public void setProperty(JexlContext jexlContext, Object o, String s, Object o1) {

            }

            @Override
            public Object invokeMethod(Object o, String s, Object... objects) {
                return null;
            }

            @Override
            public <T> T newInstance(Class<? extends T> aClass, Object... objects) {
                return null;
            }

            @Override
            public Object newInstance(String s, Object... objects) {
                return null;
            }
        };
        stage.show();

    }

    private void handleButtonClick(String value) {
        switch (value){
            case "=":
                calculate();
                break;
            default:
                inputField.appendText(value);
        }
    }

    private void calculate() {
        String expression = inputField.getText();

        try {
            double result = evaluateExpression(expression);

            inputField.setText(formatResult(result));
        } catch (Exception e){
            inputField.setText("Error");
        }
    }

    private String formatResult(double result) {

        //Format the result to remove unnecessary decimal places
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.####", symbols);

        return decimalFormat.format(result);
    }

    private double evaluateExpression(String expression) {

        JexlExpression jexlExpression = jexl.createExpression(expression);
        Object result = jexlExpression.evaluate(null);

        return Double.parseDouble(result.toString());
    }

    public static void main(String[] args) {
        launch();
    }
}