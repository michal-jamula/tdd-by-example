package guru.springframework;

import java.util.Objects;

public class Money implements Expression{
    protected final int amount;
    protected final String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    static Money dollar (int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    @Override
    public Expression times  (int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    @Override
    public Expression plus (Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        return new Money(amount / bank.rate(this.currency, to), to);
    }


    String currency() {
        return this.currency;
    }

    @Override
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && Objects.equals(this.currency, money.currency);
    }

    @Override
    public String toString() {
        return String.format("Money{amount=%s, currency=%s}", amount, currency);
    }

}
