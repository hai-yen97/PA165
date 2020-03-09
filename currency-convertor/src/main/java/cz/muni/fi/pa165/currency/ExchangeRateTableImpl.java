package cz.muni.fi.pa165.currency;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Currency;

@Named
public class ExchangeRateTableImpl implements ExchangeRateTable{
    private static final Currency CZK = Currency.getInstance("CZK");
    private static final Currency EUR = Currency.getInstance("EUR");

    public static final BigDecimal EUR_CZK_RATE = BigDecimal.valueOf(27);

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        if(sourceCurrency == null){
            throw new IllegalArgumentException("Source currency is Null");
        }
        if(targetCurrency == null){
            throw new IllegalArgumentException("Target currency is Null");
        }
        if(EUR.equals(sourceCurrency) && CZK.equals(targetCurrency)){
            return EUR_CZK_RATE;
        }
        return null;
    }
}
