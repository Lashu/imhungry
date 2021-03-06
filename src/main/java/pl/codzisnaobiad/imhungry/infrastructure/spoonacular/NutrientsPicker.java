package pl.codzisnaobiad.imhungry.infrastructure.spoonacular;

import org.springframework.stereotype.Component;
import pl.codzisnaobiad.imhungry.api.response.recipe.Nutrient;
import pl.codzisnaobiad.imhungry.domain.recipe.SupportedNutrient;

import java.util.List;

import static java.util.EnumSet.allOf;
import static java.util.stream.Collectors.toList;

@Component
class NutrientsPicker {

    private final static List<String> SUPPORTED_INGREDIENTS = allOf(SupportedNutrient.class).stream().map(Enum::name).collect(toList());

    List<Nutrient> pickSupportedNutrients(List<SpoonacularRecipeInformationResponse.Nutrition.Nutrient> spoonacularNutrients) {
        return spoonacularNutrients.stream()
            .filter(spoonacularNutrient -> isNutrientSupported(spoonacularNutrient.getTitle()))
            .map(this::mapToApiNutrient)
            .collect(toList());
    }

    private boolean isNutrientSupported(String title) {
        var nutrientTitleUpperCase = title.toUpperCase();
        return SUPPORTED_INGREDIENTS.contains(nutrientTitleUpperCase);
    }

    private Nutrient mapToApiNutrient(SpoonacularRecipeInformationResponse.Nutrition.Nutrient nutrient) {
        return Nutrient.newBuilder()
            .withName(nutrient.getTitle())
            .withAmount(nutrient.getAmount())
            .withUnit(nutrient.getUnit())
            .build();
    }

}
