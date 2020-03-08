package cz.muni.fi.pa165.currency;

import org.assertj.core.api.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Currency;

import static java.util.function.Predicate.isEqual;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class CurrencyConvertorImplTest {

    @Mock
    ExchangeRateTable rateTable;


    @Test
    public void testConvert() {
        //ExchangeRateTable table = Mockito.mock(ExchangeRateTable.class);
        CurrencyConvertor convertor = new CurrencyConvertorImpl(rateTable);
        BigDecimal result = convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("12.05"));
        assertEquals(new BigDecimal("325.35"), result);
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        CurrencyConvertor currencyConvertor = new CurrencyConvertorImpl(rateTable);

        assertThatThrownBy(() -> {
            currencyConvertor.convert(null, Currency.getInstance("CZK"), new BigDecimal("12.05"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvertWithNullTargetCurrency(){
        CurrencyConvertor currencyConvertor = new CurrencyConvertorImpl(rateTable);

        assertThatThrownBy(() -> {
            currencyConvertor.convert(Currency.getInstance("EUR"), null, new BigDecimal("27"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        CurrencyConvertor currencyConvertor = new CurrencyConvertorImpl(rateTable);

        assertThatThrownBy(() -> {
            currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvertWithUnknownCurrency() {
        CurrencyConvertor currencyConvertor = new CurrencyConvertorImpl(rateTable);

        assertThatThrownBy(() -> {
            currencyConvertor.convert(Currency.getInstance("CZK"), Currency.getInstance("EUR"), new BigDecimal("12.05"));
        }).isInstanceOf(UnknownExchangeRateException.class);
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
        assertThatThrownBy(() -> {
           rateTable.getExchangeRate(Currency.getInstance("CZK"), Currency.getInstance("EUR"));
        }).isInstanceOf(ExternalServiceFailureException.class);
    }

}
