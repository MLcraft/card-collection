package com.shizubro.cardcollection.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Layout {
    @JsonProperty("normal")
    NORMAL,
    @JsonProperty("split")
    SPLIT,
    @JsonProperty("flip")
    FLIP,
    @JsonProperty("transform")
    TRANSFORM,
    @JsonProperty("modal_dfc")
    MDFC,
    @JsonProperty("meld")
    MELD,
    @JsonProperty("leveler")
    LEVELER,
    @JsonProperty("class")
    CLASS,
    @JsonProperty("case")
    CASE,
    @JsonProperty("saga")
    SAGA,
    @JsonProperty("adventure")
    ADVENTURE,
    @JsonProperty("mutate")
    MUTATE,
    @JsonProperty("prototype")
    PROTOTYPE,
    @JsonProperty("battle")
    BATTLE,
    @JsonProperty("planar")
    PLANAR,
    @JsonProperty("scheme")
    SCHEME,
    @JsonProperty("vanguard")
    VANGUARD,
    @JsonProperty("token")
    TOKEN,
    @JsonProperty("double_faced_token")
    DFTOKEN,
    @JsonProperty("emblem")
    EMBLEM,
    @JsonProperty("augment")
    AUGMENT,
    @JsonProperty("host")
    HOST,
    @JsonProperty("art_series")
    ARTSERIES,
    @JsonProperty("reversible_card")
    REVERSIBLE
}
