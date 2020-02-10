package combogame.view;

import java.util.ArrayList;

import combogame.MainApp;
import combogame.model.ObjectType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TopLayout {
    MainApp mainApp;
    // 最右边任务容器
    public HBox targetPane;

    public ArrayList<Label> foodLabels;
    public ArrayList<Label> foodTexts;
    public Label iceLabel;
    public Label iceText;
    public Label stepText;

    public int iceAmount = 0;
    public int[] foodAmount;
    public int stepNumber = 0;

    public TopLayout(MainApp mainApp) {
        this.mainApp = mainApp;
        this.stepNumber = mainApp.taskType.stepNumber;
        this.iceAmount = mainApp.taskType.iceAmount;
        this.foodAmount = new int[mainApp.taskType.foodAmount.size()];
        for (int i = 0; i < mainApp.taskType.foodAmount.size(); i++) {
            this.foodAmount[i] = mainApp.taskType.foodAmount.get(i);
        }

        targetPane = new HBox();
        targetPane.autosize();
        targetPane.setAlignment(Pos.CENTER);
        // load task info
        // load target

        Font font = Font.font("Arial", FontWeight.EXTRA_BOLD, 24);

        int stepNumber = this.stepNumber;
        Image image = new Image(ObjectType.imageUrls.get(ObjectType.Null.getNCode() - 1));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        this.stepText = new Label(String.valueOf(stepNumber));
        stepText.setPrefSize(60, 60);
        stepText.setFont(font);
        stepText.setTextFill(Color.web("#edad1d"));
        targetPane.getChildren().add(stepText);

        int iceAmount = this.iceAmount;
        image = new Image(ObjectType.imageUrls.get(ObjectType.Ice.getNCode() - 1));
        imageView = new ImageView(image);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        this.iceLabel = new Label("", imageView);
        targetPane.getChildren().add(iceLabel);
        this.iceText = new Label(String.valueOf(iceAmount));
        iceText.setPrefSize(60, 60);
        iceText.setFont(font);
        iceText.setTextFill(Color.web("#5375ad"));
        targetPane.getChildren().add(iceText);

        // iceLabel.setStyle(iceLabel.getCssType() + " -fx-background-size: 60px;");

        foodLabels = new ArrayList<Label>(this.foodAmount.length);
        foodTexts = new ArrayList<Label>(this.foodAmount.length);
        for (int i = 1; i <= this.foodAmount.length; i++) {
            System.out.println(ObjectType.foodHashMap.get(i));
            ObjectType foodType = ObjectType.valueOf(ObjectType.foodHashMap.get(i));
            image = new Image(ObjectType.imageUrls.get(foodType.getNCode() - 1));
            imageView = new ImageView(image);
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            Label foodLabel = new Label("", imageView);
            foodLabels.add(foodLabel);
            targetPane.getChildren().add(foodLabel);
            Label foodText = new Label(String.valueOf(this.foodAmount[i - 1]));
            foodText.setPrefSize(60, 60);
            foodText.setFont(font);
            foodText.setTextFill(Color.web("#5375ad"));
            foodTexts.add(foodText);
            targetPane.getChildren().add(foodText);
            // foodLabel.setStyle(foodLabel.getCssType() + " -fx-background-size: 60px;");
        }

        targetPane.getChildren().forEach((e) -> HBox.setMargin(e, new Insets(2, 5, 2, 5)));
    }
}