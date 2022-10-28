package org.chapter2.item2;

import java.io.Serializable;

/**
 * 创建和销毁对象-第二条:
 * 涉及:遇到多个构造器参数时,要考虑使用构建器,也就是建造者模式
 * 可以使用{@link lombok.Builder}一键配置
 *
 * @author mawenhao 2022/10/27
 */
public class NutritionFacts implements Serializable {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servingSize;
        this.calories = builder.servingSize;
        this.fat = builder.servingSize;
        this.sodium = builder.servingSize;
        this.carbohydrate = builder.servingSize;
    }

    public static class Builder {
        private final int servingSize;
        private final int servings;
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            this.calories = val;
            return this;
        }

        public Builder fat(int val) {
            this.fat = val;
            return this;
        }

        public Builder sodium(int val) {
            this.sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            this.carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new Builder(240, 8).calories(100)
                                                           .sodium(35)
                                                           .carbohydrate(27)
                                                           .build();
        System.out.println(nutritionFacts);
    }
}
