package org.skypro.skyshop.product;


public interface Searchable {
    String getSearchTerm();
    String getType();
    String getName();


    default String getStringRepresentation() {
        return getName() + " — " + getType();
    }
}