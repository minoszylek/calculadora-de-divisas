import java.math.BigDecimal;
import java.math.RoundingMode;
//Esta clase guarda las consultas realizadas por el usuario.
public class Querie {

    private final String currencyBase;
    private final String currencyTarget;
    private final BigDecimal amountConversion;
    private final BigDecimal conversionRate;
    private final BigDecimal conversionResult;
    //Este metod recibe el objeto ConversionOmdb y el valor que el usuario desea convertir.
    public Querie (ConversionExchangeRate conversion, BigDecimal amount) {
        this.currencyBase = conversion.base_code();
        this.currencyTarget = conversion.target_code();
        this.conversionRate = new BigDecimal(conversion.conversion_rate()) //Redonde a dos decimales.
                .setScale(2, RoundingMode.HALF_EVEN);
        this.amountConversion = amount;
        this.conversionResult = new BigDecimal(conversion.conversion_result()) //Redonde a dos decimales.
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "De "+currencyBase+" a " + currencyTarget+", Cantidad en "+currencyBase+": "+amountConversion+" X "+conversionRate+" Igual a "+currencyTarget+": "+conversionResult;
    }
}