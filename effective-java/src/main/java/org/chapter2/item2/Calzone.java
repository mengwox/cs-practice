package org.chapter2.item2;

import static org.chapter2.item2.Pizza.Topping.ONION;
import static org.chapter2.item2.Pizza.Topping.SAUSAGE;

/**
 * 半月型披萨
 *
 * @author mawenhao 2022/10/27
 */
public class Calzone extends Pizza {
    private final boolean sauceInside;

    private Calzone(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            this.sauceInside = true;
            return this;
        }

        @Override
        Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public static void main(String[] args) {
        Calzone calzone = new Builder().addTopping(SAUSAGE)
                                       .addTopping(ONION)
                                       .sauceInside()
                                       .build();
        System.out.println(calzone);
    }
}
