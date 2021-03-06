package pl.codzisnaobiad.imhungry.infrastructure.spoonacular;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@JsonIgnoreProperties(ignoreUnknown = true)
final class SpoonacularRecipeInformationResponse {
    private final List<ExtendedIngredient> extendedIngredients;
    private final String title;
    private final int readyInMinutes;
    private final int servings;
    private final Nutrition nutrition;

    @JsonCreator
    SpoonacularRecipeInformationResponse(@JsonProperty("extendedIngredients") List<ExtendedIngredient> extendedIngredients,
                                         @JsonProperty("title") String title,
                                         @JsonProperty("readyInMinutes") int readyInMinutes,
                                         @JsonProperty("servings") int servings,
                                         @JsonProperty("nutrition") Nutrition nutrition) {
        this.extendedIngredients = extendedIngredients;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.nutrition = nutrition;
    }

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public String getTitle() {
        return title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static final class ExtendedIngredient {
        private final String id;
        private final String name;
        private final float amount;
        private final String unit;
        private final String image;

        @JsonCreator
        ExtendedIngredient(@JsonProperty("id") String id,
                           @JsonProperty("name") String name,
                           @JsonProperty("amount") float amount,
                           @JsonProperty("unit") String unit,
                           @JsonProperty("image") String image) {
            this.id = id;
            this.name = name;
            this.amount = amount;
            this.unit = unit;
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public float getAmount() {
            return amount;
        }

        public String getUnit() {
            return unit;
        }

        public String getImage() {
            return image;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
     static final class Nutrition {
        private final List<Nutrient> nutrients;

        @JsonCreator
         Nutrition(@JsonProperty("nutrients") List<Nutrient> nutrients) {
             this.nutrients = ofNullable(nutrients).map(List::copyOf).orElse(emptyList());
         }

        public List<Nutrient> getNutrients() {
            return nutrients;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        static final class Nutrient {
             private final String title;
             private final float amount;
             private final String unit;

             @JsonCreator
             Nutrient(@JsonProperty("title") String title,
                      @JsonProperty("amount") float amount,
                      @JsonProperty("unit") String unit) {
                 this.title = title;
                 this.amount = amount;
                 this.unit = unit;
             }

             public String getTitle() {
                 return title;
             }

             public float getAmount() {
                 return amount;
             }

             public String getUnit() {
                 return unit;
             }
         }
     }
}
