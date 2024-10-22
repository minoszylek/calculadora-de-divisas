import java.io.IOException;
//Monedas aceptadas: USD, COP, ARS, BOB, BRL, CLP, EUR.
public class CalculadoraDeDivisas {

    public static void main(String[] args) throws IOException, InterruptedException {

        Tools user = new Tools();

        while (user.getOutPut() != 9) {
            user.menuSelector(user.getMainMenu(), user.getOptionsMainMenu());
            switch (user.getOutPut()) {
                case 1 :

                    System.out.println("Ingresa la moneda que deseas convertir: ");
                    user.setCurrency1();
                    System.out.println("Moneda base "+user.getCurrency1()+".");
                    user.setCurrency2();
                    user.bigDecimal();
                    user.conversionCalculation();
                    break;
                case 2:

                    user.printConversions();
                    break;
            }
        }
        user.printConversions();
        System.out.println("¡Gracias por utilizar nuestra aplicación!");
    }
}