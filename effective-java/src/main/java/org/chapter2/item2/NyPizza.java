package org.chapter2.item2;

import java.util.Objects;

import static org.chapter2.item2.NyPizza.Size.MEDIUM;
import static org.chapter2.item2.Pizza.Topping.ONION;
import static org.chapter2.item2.Pizza.Topping.SAUSAGE;

/**
 * 纽约风味披萨
 *
 * @author mawenhao 2022/10/27
 */
public class NyPizza extends Pizza {
    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    private NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public static void main(String[] args) {
        NyPizza nyPizza = new NyPizza.Builder(MEDIUM).addTopping(SAUSAGE)
                                                     .addTopping(ONION)
                                                     .build();
        System.out.println(nyPizza);
    }
}
