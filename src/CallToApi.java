import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Esta clase esta encargada de armar las petiiciones a la API del proyecto.
public class CallToApi  {
    private static final String apiKey = "94d4a1436ab12c775476171f";
    private  String json;
    public String getJson() {
        return json;
    }
    //EL constructor recibe por parámetros el par de modendas y el valor a convertir.
    public CallToApi(String currency1, String currency2, BigDecimal value) throws IOException, InterruptedException {

        //Se crea el cliente.
        HttpClient client = HttpClient.newHttpClient();
        //Se crea la URL.
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"+currency1+"/"+currency2+"/"+value;
        //Se arma la petición usando la URL como parámetro.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse
                        .BodyHandlers.ofString());
        //Se realiza un breve manejo al código de respuesta.
        if (response.statusCode() != 200) {
            System.out.println("Lo sentimos, estamos presentando problemas, intenta más tarde.");
            System.out.println("Error en la respuesta, código de error: " +response.statusCode());
        }else {
            //Se guaurda el cuerpo de la respuesta en la variable de json.
            this.json = response.body();
        }

    }
}