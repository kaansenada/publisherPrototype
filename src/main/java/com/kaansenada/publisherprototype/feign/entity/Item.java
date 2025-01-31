package com.kaansenada.publisherprototype.feign.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Item {
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;


}
