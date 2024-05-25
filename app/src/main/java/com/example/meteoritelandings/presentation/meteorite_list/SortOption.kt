package com.example.meteoritelandings.presentation.meteorite_list

enum class SortOption(val stringValue: String) {
    NAME_ASC("name ASC"),
    NAME_DESC("name DESC"),
    MASS_ASC("mass ASC"),
    MASS_DESC("mass DESC"),
    YEAR_ASC("year ASC"),
    YEAR_DESC("year DESC")
}

fun SortOption.isByName() = this.stringValue.contains("name")

fun SortOption.isByMass() = this.stringValue.contains("mass")

fun SortOption.isByYear() = this.stringValue.contains("year")