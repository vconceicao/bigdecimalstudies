import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalExperiments {
    public static void main(String[] args) {

    }

    public static BigDecimal calculateTotalAmount(BigDecimal quantity, BigDecimal unitPrice, BigDecimal discountRate, BigDecimal taxRate) {

        BigDecimal amount = quantity.multiply(unitPrice);
        BigDecimal discount = amount.multiply(discountRate);
        BigDecimal discountedAmount = amount.subtract(discount);
        BigDecimal tax = discountedAmount.multiply(taxRate);

        BigDecimal total = discountedAmount.add(tax);

        // round to 2 decimal places using HALF_EVEN
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }
}

