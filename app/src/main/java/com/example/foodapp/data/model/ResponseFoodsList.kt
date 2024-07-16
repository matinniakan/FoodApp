package com.example.foodapp.data.model


import com.google.gson.annotations.SerializedName

data class ResponseFoodsList(
    @SerializedName("meals")
    val meals: List<Meal>?
) {
    data class Meal(
        @SerializedName("dateModified")
        val dateModified: Any?, // null
        @SerializedName("idMeal")
        val idMeal: String?, // 53000
        @SerializedName("strArea")
        val strArea: String?, // Irish
        @SerializedName("strCategory")
        val strCategory: String?, // Beef
        @SerializedName("strCreativeCommonsConfirmed")
        val strCreativeCommonsConfirmed: Any?, // null
        @SerializedName("strDrinkAlternate")
        val strDrinkAlternate: Any?, // null
        @SerializedName("strImageSource")
        val strImageSource: Any?, // null
        @SerializedName("strIngredient1")
        val strIngredient1: String?, // Potatoes
        @SerializedName("strIngredient10")
        val strIngredient10: String?, // Bay Leaves
        @SerializedName("strIngredient11")
        val strIngredient11: String?, // Olive Oil
        @SerializedName("strIngredient12")
        val strIngredient12: String?, // Dry White Wine
        @SerializedName("strIngredient13")
        val strIngredient13: String?, // Vegetable Stock
        @SerializedName("strIngredient14")
        val strIngredient14: String?, // Cornstarch
        @SerializedName("strIngredient15")
        val strIngredient15: String?, // Soy Sauce
        @SerializedName("strIngredient16")
        val strIngredient16: String?, // Rosemary
        @SerializedName("strIngredient17")
        val strIngredient17: String?, // Parsley
        @SerializedName("strIngredient18")
        val strIngredient18: String?, // Sage
        @SerializedName("strIngredient19")
        val strIngredient19: String?, // Chives
        @SerializedName("strIngredient2")
        val strIngredient2: String?, // Small Potatoes
        @SerializedName("strIngredient20")
        val strIngredient20: String?,
        @SerializedName("strIngredient3")
        val strIngredient3: String?, // Salted Butter
        @SerializedName("strIngredient4")
        val strIngredient4: String?, // Mushrooms
        @SerializedName("strIngredient5")
        val strIngredient5: String?, // Brown Lentils
        @SerializedName("strIngredient6")
        val strIngredient6: String?, // Garlic
        @SerializedName("strIngredient7")
        val strIngredient7: String?, // Kosher Salt
        @SerializedName("strIngredient8")
        val strIngredient8: String?, // Onion
        @SerializedName("strIngredient9")
        val strIngredient9: String?, // Tomato Puree
        @SerializedName("strInstructions")
        val strInstructions: String?, // Add Ingredients:12 cups chopped mixed vegetables1   cup chopped fresh mushrooms 1   cup pearl onionsTOPPING:Preheat oven to 450°. Bake potatoes on a foil-lined baking sheet until tender, about 45 minutes. Let cool slightly, then peel. Press potatoes through a ricer, food mill, or colander into a large bowl. Add butter; stir until well blended. Stir in milk. Season to taste with salt.FILLING:Soak dried porcini in 3 cups hot water; set aside. Combine lentils, 1 garlic clove, 1 tsp. salt, and 4 cups water in a medium saucepan. Bring to a boil; reduce heat and simmer, stirring occasionally, until lentils are tender but not mushy, 15–20 minutes. Drain lentils and discard garlic.Heat 3 Tbsp. oil in a large heavy pot over medium heat. Add onions and cook, stirring occasionally, until soft, about 12 minutes. Add chopped garlic and cook for 1 minute. Stir in tomato paste. Cook, stirring constantly, until tomato paste is caramelized, 2–3 minutes.Add bay leaves and wine; stir, scraping up any browned bits. Stir in porcini, slowly pouring porcini soaking liquid into pan but leaving any sediment behind. Bring to a simmer and cook until liquid is reduced by half, about 10 minutes. Stir in broth and cook, stirring occasionally, until reduced by half, about 45 minutes.Strain mixture into a large saucepan and bring to a boil; discard solids in strainer. Stir cornstarch and 2 Tbsp. water in a small bowl to dissolve. Add cornstarch mixture; simmer until thickened, about 5 minutes. Whisk in miso. Season sauce with salt and pepper. Set aside.Preheat oven to 450°. Toss vegetables and pearl onions with remaining 2 Tbsp. oil, 5 garlic cloves, and rosemary sprigs in a large bowl; season with salt and pepper. Divide between 2 rimmed baking sheets. Roast, stirring once, until tender, 20–25 minutes. Transfer garlic cloves to a small bowl; mash well with a fork and stir into sauce. Discard rosemary. DO AHEAD: Lentils, sauce, and vegetables can be made 1 day ahead. Cover separately; chill.Arrange lentils in an even layer in a 3-qt. baking dish; set dish on a foil-lined rimmed baking sheet. Toss roasted vegetables with fresh mushrooms and chopped herbs; layer on top of lentils. Pour sauce over vegetables. Spoon potato mixture evenly over.Bake until browned and bubbly, about 30 minutes. Let stand for 15 minutes before serving.
        @SerializedName("strMeal")
        val strMeal: String?, // Vegetable Shepherd's Pie
        @SerializedName("strMealThumb")
        val strMealThumb: String?, // https://www.themealdb.com/images/media/meals/w8umt11583268117.jpg
        @SerializedName("strMeasure1")
        val strMeasure1: String?, // 3 Lbs
        @SerializedName("strMeasure10")
        val strMeasure10: String?,
        @SerializedName("strMeasure11")
        val strMeasure11: String?,
        @SerializedName("strMeasure12")
        val strMeasure12: String?, // 2 cups 
        @SerializedName("strMeasure13")
        val strMeasure13: String?, // 8 cups 
        @SerializedName("strMeasure14")
        val strMeasure14: String?, // 2 tbsp
        @SerializedName("strMeasure15")
        val strMeasure15: String?, // 2 tsp
        @SerializedName("strMeasure16")
        val strMeasure16: String?, // 2 sprigs
        @SerializedName("strMeasure17")
        val strMeasure17: String?,
        @SerializedName("strMeasure18")
        val strMeasure18: String?,
        @SerializedName("strMeasure19")
        val strMeasure19: String?,
        @SerializedName("strMeasure2")
        val strMeasure2: String?, // 3 Lbs
        @SerializedName("strMeasure20")
        val strMeasure20: String?,
        @SerializedName("strMeasure3")
        val strMeasure3: String?, // 1/2 cup 
        @SerializedName("strMeasure4")
        val strMeasure4: String?, // 1 oz 
        @SerializedName("strMeasure5")
        val strMeasure5: String?, // 3/4 cup 
        @SerializedName("strMeasure6")
        val strMeasure6: String?, // 6 cloves
        @SerializedName("strMeasure7")
        val strMeasure7: String?, // 1 tsp 
        @SerializedName("strMeasure8")
        val strMeasure8: String?, // 3 cups 
        @SerializedName("strMeasure9")
        val strMeasure9: String?, // 2 tbsp
        @SerializedName("strSource")
        val strSource: String?,
        @SerializedName("strTags")
        val strTags: String?, // Alcoholic
        @SerializedName("strYoutube")
        val strYoutube: String?
    )
}