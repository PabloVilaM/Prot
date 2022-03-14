package exceptionhunter;


    import javax.swing.*;
    import java.util.Map;
    import archivos.Ficheros;
    import javafx.animation.KeyFrame;
    import javafx.animation.Timeline;
    import javafx.application.Application;
    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.scene.Scene;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.input.KeyEvent;
    import javafx.scene.layout.*;
    import javafx.scene.layout.Pane;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Circle;
    import javafx.scene.shape.Rectangle;
    import javafx.scene.shape.Shape;
    import javafx.scene.text.Font;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;
    import javafx.util.Duration;


    public class Main extends Application{

        private static final double TEXT_SIZE = 40;
        final int TAMX = 1100;
        final int TAMY = 618;
        final int STICK_ANCHO = 7;
        final int STICK_ALTURA = 50;
        int ballCenterX;
        int ballCurrentSpeedX = 3;
        int stickPosY = (STICK_ALTURA-STICK_ANCHO) /2;
        int stickCurrentSpeed = 0;
        int imagey = 100;
        int imagex = 100;
        int imgcurrentspeed = 0;
        float segundos = 0.017f;


        int a = 0;
        int b = 0;

        int misilx = 0;
        int misily = 0;
        float misilspeed = 0;

        int misilmalox = 0;
        int misilmaloy = 0;
        float misilmalospeed = 0;

        int hp=99;
        int hp2=99;
        Timeline animationball;
        @Override
        public void start(Stage primaryStage){
            String hpvida = String.valueOf(hp);
            String hpvida2 = String.valueOf(hp2);
            Pane root = new Pane();
            Scene scene = new Scene(root, TAMX, TAMY, Color.BLACK);
            primaryStage.setTitle("This is a game i dont know");
            primaryStage.setScene(scene);
            primaryStage.show();

            Circle circleball = new Circle(10,30,7, Color.WHITE);
            root.getChildren().add(circleball);

            Rectangle rect = new Rectangle(500,stickPosY, STICK_ANCHO, STICK_ALTURA);
            rect.setFill(Color.WHITE);
            root.getChildren().add(rect);


            Image image3 = new Image("file:Missile.png");
            ImageView mv3 = new ImageView(image3);
            root.getChildren().add(mv3);
            mv3.setRotate(90);
            mv3.setLayoutX(50);
            mv3.setLayoutY(325);
            mv3.setFitHeight(50);
            mv3.setFitWidth(50);
            mv3.setPreserveRatio(true);

            Image image5 = new Image("file:MissilVerde.png");
            ImageView mv5 = new ImageView(image5);
            root.getChildren().add(mv5);
            mv5.setLayoutX(1030);
            mv5.setLayoutY(330);
            mv5.setFitHeight(50);
            mv5.setFitWidth(50);
            mv5.setRotate(-90);
            mv5.setPreserveRatio(true);

            Shape.intersect(circleball, rect);
            Image image = new Image("file:spaceShipSingleShoot.png");
            ImageView mv = new ImageView(image);
            root.getChildren().add(mv);
            mv.setRotate(-90);
            mv.setLayoutX(0);
            mv.setLayoutY(300);
            mv.setFitHeight(100);
            mv.setFitWidth(100);
            mv.setPreserveRatio(true);

            Image image4 = new Image("file:ovni.png");
            ImageView mv4 = new ImageView(image4);
            root.getChildren().add(mv4);
            mv4.setLayoutX(1000);
            mv4.setLayoutY(330);
            mv4.setFitHeight(100);
            mv4.setFitWidth(100);
            mv4.setPreserveRatio(true);

            Image image6 = new Image("file:Hearth.png");
            ImageView mv6 = new ImageView(image6);
            root.getChildren().add(mv6);
            mv6.setLayoutX(965);
            mv6.setLayoutY(100);
            mv6.setFitHeight(150);
            mv6.setFitWidth(150);
            mv6.setPreserveRatio(true);

            Image image7 = new Image("file:Hearth.png");
            ImageView mv7 = new ImageView(image7);
            root.getChildren().add(mv7);
            mv7.setLayoutX(-10);
            mv7.setLayoutY(100);
            mv7.setFitHeight(150);
            mv7.setFitWidth(150);
            mv7.setPreserveRatio(true);


            Text textTiempo = new Text(hpvida);
            textTiempo.setFont(Font.font(TEXT_SIZE));
            textTiempo.setFill(Color.BLACK);
            textTiempo.setX(40);
            textTiempo.setY(190);
            root.getChildren().add(textTiempo);

            Text textTiempo2 = new Text(hpvida2);
            textTiempo2.setFont(Font.font(TEXT_SIZE));
            textTiempo2.setFill(Color.BLACK);
            textTiempo2.setX(1015);
            textTiempo2.setY(190);
            root.getChildren().add(textTiempo2);


            BackgroundImage myBI= new BackgroundImage(new Image("file:cityskyline.png",1100,634,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
                //then you set to your node
            root.setBackground(new Background(myBI));
             animationball = new Timeline(
                    new KeyFrame(Duration.seconds(segundos), (ActionEvent ae)-> {
 
                        misilx += misilspeed;
                        mv3.setY(misily);
                        mv3.setX(misilx);

                        misilmalox += misilmalospeed;
                        mv5.setY(misilmaloy);
                        mv5.setX(misilmalox);

                        stickPosY += stickCurrentSpeed;
                        rect.setY(stickPosY);

                        ballCenterX+= ballCurrentSpeedX;
                        circleball.setCenterX(ballCenterX);
                        if (ballCenterX >= TAMX){
                            ballCurrentSpeedX = -3;
                        }
                        else if(ballCenterX <= 0){
                            ballCurrentSpeedX = 3;
                        }

                        if (misilx >= 995){
                            misilspeed = 0.0001f;
                            misilmalospeed = 0.0001f;
                            animationball.pause();
                            mv5.setLayoutX(1030);
                            mv5.setLayoutY(330);
                            misilmalox = 0;
                            mv3.setLayoutX(50);
                            mv3.setLayoutY(325);
                            misilx = 0;

                            int numero = (int)(Math.random()*30+1);
                            Map<Integer, String> map = Preguntas.crearLista();
                            System.out.println(numero);
                            String pregunta = Preguntas.darPreguntas(numero, map);
                            Tiempo temp = new Tiempo();
                            temp.Contar();
                            String respuesta = JOptionPane.showInputDialog(pregunta);
                            boolean correcion = Preguntas.comprobarRespuesta(numero, respuesta);
                            if (correcion == false || correcion == true){
                               a = temp.getSegundos();
                                b = temp.getSobrante();
                                System.out.println("Tiempo:" + a);
                                temp.Detener();
                               JFrame frame = temp.getJframe();
                               frame.setVisible(false);
                               if (correcion == true){
                                   textTiempo.setText(String.valueOf(hp-b));
                                   textTiempo2.setText(String.valueOf(hp2-a));
                                   hp = hp-b;
                                   hp2 = hp2-a;
                               }
                               else if (correcion == false){

                                   textTiempo.setText(String.valueOf(hp-10));
                                   hp =hp -10;
                               }
                               Ficheros fich = new Ficheros();
                               fich.crearFichero(respuesta, correcion, map, numero);
                            }

                            animationball.play();
                        }



                        if(stickPosY<0){
                            stickPosY = 0;
                        } else{
                            if(stickPosY > TAMY - STICK_ALTURA){
                                stickPosY = TAMY - STICK_ALTURA;
                            }
                        }


                        Shape colision = Shape.intersect(circleball, rect);

                        boolean colisionVacia = colision.getBoundsInLocal().isEmpty();

                        if (colisionVacia == false){
                            ballCurrentSpeedX = -3;
                        }


                    })
            );
            animationball.setCycleCount(Timeline.INDEFINITE);
            animationball.play();



            scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent event){
                    switch(event.getCode()){
                        case UP:
                            stickCurrentSpeed = -6;
                            break;
                        case DOWN:
                            stickCurrentSpeed = 6;
                            break;
                        case SPACE:
                            misilspeed = 8;
                            misilmalospeed = -8;
                            break;
                        case ESCAPE:
                            animationball.stop();
                            break;
                        case P:
                            animationball.play();
                            break;
                    }
                }
            });

            scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent event){

                }
            });

        }
        public static void main(String[] args) {

            launch();
        }

    }
