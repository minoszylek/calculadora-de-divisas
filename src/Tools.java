import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
//Este objeto se encarga de toda la lógica del programa.
//todo Agregar la opción 3: el usuario puede descargar en un documento .txt las consultas del usuario.
public class Tools {

    private final String mainMenu; {
        mainMenu = """
                CALCULADORA DE DIVISAS
                **********************
                1. Inicia una nueva consulta.
                2. Mostrar resumen de consultas.
                
                9. Salir.
                **********************
                """;
    }
    private final String currencySelector; {
        currencySelector = """
                CALCULADORA DE DIVISAS
                ***********************
                1. USD      Dólar Estadunidense        Estados Unidos
                2. COP      Peso Colombiano            Colombia
                3. ARS      Peso Argentino             Argentina
                4. BOB      Boliviano                  Bolivia
                5. BRL      Real Brasileño             Brasil
                6. CLP      Peso Chileno               Chile
                7. EUR      Euro                       Unión Europea
                
                9. Salir
                ***********************
                """;
    }
    private final List<Querie> queriesList = new ArrayList<>();
    private final List<Integer> optionsMainMenu = Arrays.asList(1, 2, 3, 9);
    private final List<Integer> optionsCurrencys = Arrays.asList (1, 2, 3, 4, 5, 6, 7, 8, 9);
    private final Map<Integer, String> currencys = Map.of(
            1, "USD",
            2, "COP",
            3, "ARS",
            4, "BOB",
            5, "BRL",
            6, "CLP",
            7, "EUR"
    );
    private int outPut;
    private int option;
    private int currency1;
    private int currency2;
    private BigDecimal amount;
    Scanner inputUser = new Scanner(System.in);
    Gson gson = new GsonBuilder().create(); //Se crea el objeto Gson para gestionar

    public void conversionCalculation () throws IOException, InterruptedException {
       CallToApi conversion = new CallToApi(this.currencys.get(currency1), this.currencys.get(currency2), amount);
        ConversionExchangeRate callToApiConversion = gson.fromJson(conversion.getJson(), ConversionExchangeRate.class);
        Querie querie = new Querie(callToApiConversion, amount);
        queriesList.add(querie);
        System.out.println(querie);
    }
    //Este métod se usa para construir los menús del usuario.
    public void menuSelector (String menu, List<Integer> options) {

        while (this.option != 9) {
            System.out.println(menu);
            try {
                this.option = inputUser.nextInt();
                if (option == 9){
                    this.outPut = option;
                    break;
                }
                if (options.contains(option)) {
                    this.outPut = option;
                    break;
                }else {
                    System.out.println(option +" no es una opción válida, debes ingresar el número de la opción que te interesa =)");
                    inputUser.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Solo debes ingresar números =), Principal ");
                inputUser.nextLine();
            }
        }
    }
    //todo mejorar la lógica del ciclo.
    public void bigDecimal () {
        while (true) {
            System.out.println("Ingresa el valor que deseas convertir, recuerda que los decimales se escriben con coma: ");
            try {
                //Se defienen la cantidad de decimales.
                BigDecimal amount= inputUser.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN); //Redonde a dos decimales.
                if (amount.scale() <= 2){
                    this.amount = amount;
                    System.out.println(amount);
                    break;
                }else {
                    System.out.println("Ese no es una valor válido, debes ingresar un número con máximo 4 decimales =(");
                    inputUser.nextLine();
                }
            } catch (InputMismatchException e) {
                inputUser.nextLine();
                System.out.println("Este no es un valor válido =( ");
            }
        }
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public List<Integer> getOptionsMainMenu() {
        return optionsMainMenu;
    }

    public int getOutPut() {
        return outPut;
    }

    public void setCurrency1() {
        menuSelector(currencySelector, optionsCurrencys);
        if (currencys.containsKey(outPut)) {
            this.currency1 = outPut;
        } else {
            System.out.println("Esta no es una opción válida de moneda");
            inputUser.nextLine();
            setCurrency1();
        }
    }

    public void setCurrency2() {
        System.out.println("Selecciona la moneda a la que deseas convertir "+ currencys.get(currency1));
        menuSelector(currencySelector, optionsCurrencys);
        if (currencys.containsKey(outPut) && currency1 != outPut) {
            this.currency2 = outPut;
        } else {
            System.out.println("Esta no es una opción válida de moneda");
            inputUser.nextLine();
            setCurrency2();
        }
    }

    public int getCurrency1() {
        return currency1;
    }

    public void printConversions() {
        if (queriesList.isEmpty()) {
            System.out.println("No has realizado ninguna conversión.");
        }else {
            for (Querie querie : queriesList) {
                System.out.println(querie.toString());
            }
        }


    }
}