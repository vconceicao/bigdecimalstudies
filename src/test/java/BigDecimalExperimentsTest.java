import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.*;

class BigDecimalExperimentsTest {

    @Test
    void whenBigDecimalCreated_thenValueMatches() {

        BigDecimal bdFromString = new BigDecimal("0.1");
        BigDecimal bdFromCharArray = new BigDecimal(new char[]{'3', '.', '1', '6', '1', '5'});
        BigDecimal bdFromInt = new BigDecimal(42);
        BigDecimal bdFromLong = new BigDecimal(123412345678901L);
        BigInteger bigInteger = BigInteger.probablePrime(100, new Random());
        BigDecimal bdFromBigInteger = new BigDecimal(bigInteger);


        assertEquals("0.1", bdFromString.toString());
        assertEquals("3.1615", bdFromCharArray.toString());
        assertEquals("42", bdFromInt.toString());
        assertEquals("123412345678901", bdFromLong.toString());
        assertEquals(bigInteger.toString(), bdFromBigInteger.toString());


    }

    @Test
    void whenBigDecimalCreatedFromDouble_thenValueMayNotMatch() {
        BigDecimal bdFromDouble = new BigDecimal(0.1d);
        /**
         * Nao criar bigdecimal com double utilizando o construtor, pois o bigdecimal criado
         * nao será igual ao double utilizado, porque ele converte o valor real do double para BigDecimal
         * 0.1d nao é igual BigDecimal(0.1d)
         */
        //assertEquals("0.1", bdFromDouble.toString());//erro

        assertNotEquals("0.1", bdFromDouble.toString());

    }

    @Test
    void whenBigDecimalCreatedUsingValueOf_thenValueMatches() {
        /**
         * Ao criar bigdecimals com double utilizar o método valueOf porque ele converte o BigDecimal para String
         */
        BigDecimal bdFromDouble = BigDecimal.valueOf(0.1);
        BigDecimal bigDecimal = BigDecimal.valueOf(20.89);
        assertEquals("0.1B", bdFromDouble.toString());
        assertEquals("20.89", bigDecimal.toString());
    }

    @Test
    void whenGettingAtributes_thenExpectedResul() {

        /**
         * Métodos úteis de big Decimal
         * @precision total de digitos
         * @scale numero de casas decimais
         * @signum indica se é positivo ou negativo
         */

        BigDecimal bd = new BigDecimal("-1234.56789");

        assertEquals(9, bd.precision());
        assertEquals(4, bd.scale());
        assertEquals(-1, bd.signum());
    }

    @Test
    void whenComparingBigDecimals_thenExpectedResult() {

        BigDecimal bd1 = new BigDecimal("1.0");
        BigDecimal bd2 = new BigDecimal("1.00");
        BigDecimal bd3 = new BigDecimal("2.0");
        BigDecimal bd4 = new BigDecimal("1.5");
        BigDecimal bd5 = new BigDecimal("1.50");


        /**
         * O método @compareTo ignora o numero de casas decimais ao comparar os objetos
         * entao numeros como 1.0 e 1.00 sao considerados iguais neste metodo
         */
        assertTrue(bd1.compareTo(bd3) < 0);
        assertTrue(bd3.compareTo(bd1) > 0);
        assertTrue(bd1.compareTo(bd2)==0); //1.0 == 1.00
        assertTrue(bd1.compareTo(bd3)<=0);
        assertTrue(bd1.compareTo(bd2)>=0);
        assertTrue(bd1.compareTo(bd3)!=0);
        assertTrue(bd1.compareTo(bd4)<0);
        assertTrue(bd4.compareTo(bd5)==0); //1.5 == 1.50
    }

    @Test
    void whenEqualsCalled_thenSizeAndScaleMatched() {

        /**
         * O método equals por outro lado define que dois big decimals são iguais se tiverem o mesmo numero de
         * digitos e casas decimais.
         */
        BigDecimal bigDecimal = new BigDecimal("1.0");
        BigDecimal bigDecimal2 = new BigDecimal("1.00");

        assertFalse(bigDecimal.equals(bigDecimal2)); //1.0  != 1.00

    }

    @Test
    void whenPerformingArithmetic_theExpectedResult() {
        BigDecimal bd1 = new BigDecimal("4.0");
        BigDecimal bd2 = new BigDecimal("2.0");

        /**
         * basic arithmetic methods
         * @add
         * @divide
         * @subtract
         * @multiply
         */
        BigDecimal sum = bd1.add(bd2);
        BigDecimal difference = bd1.subtract(bd2);
        BigDecimal quotient = bd1.divide(bd2);
        BigDecimal product = bd1.multiply(bd2);

        assertTrue(sum.compareTo(new BigDecimal("6.0"))==0);
        assertTrue(difference.compareTo(new BigDecimal("2.0"))==0);
        assertTrue(quotient.compareTo(new BigDecimal("2.0"))==0);
        assertTrue(product.compareTo(new BigDecimal("8.0"))==0);



    }

    @Test
    void whenNeedToRound_UseHalfEven() {

        BigDecimal bd1 = new BigDecimal("2.5" );
        BigDecimal bd2 = new BigDecimal("5.5");

        assertEquals("2", bd1.round(new MathContext(1, HALF_EVEN)).toString());
        assertEquals("6", bd2.round(new MathContext(1, HALF_EVEN)).toString());


    }

    @Test
    void givenPurchaseTxn_whenCalculatingTotalAmount_thenExpectedResult() {

        BigDecimal quantity = new BigDecimal("4.5");
        BigDecimal unitPrice = new BigDecimal("2.69");
        BigDecimal discountRate = new BigDecimal("0.10");
        BigDecimal taxRate = new BigDecimal("0.0725");

        BigDecimal amountToBePaid = BigDecimalExperiments.calculateTotalAmount(quantity, unitPrice, discountRate, taxRate);

        assertEquals("11.68", amountToBePaid.toString());
    }
}