package com.foodessentials.labelapi.api;

/**
 * Created by ortiguelae on 1/14/14.
 */
public interface ILabelReference {

   public enum PROPERTY_TYPE {

        NUTRIENT("nutrient"),
        ALLERGEN("allergen"),
        ADDITIVE("additive");
       public final String type;

        PROPERTY_TYPE(String type){
            this.type = type;
        }

    }

    public void searchProducts(String query, int queryLimit, int offset, LabelApiCallback callback);
    public void productScore(String upc, LabelApiCallback callback);
    public void label(String upc, LabelApiCallback callback);
    public void labelArray(String upc, LabelApiCallback callback);
    public void labelSummary(String upc, LabelApiCallback callback);
    public void getAllergenAdditive(String upc, String property, PROPERTY_TYPE type,
            LabelApiCallback callback);
    public void getPropDescription(PROPERTY_TYPE type, String nameNutrient,
            LabelApiCallback callback);
}
