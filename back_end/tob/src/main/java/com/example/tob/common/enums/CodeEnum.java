package com.example.tob.common.enums;

/**
 * Base enum common
 */
public interface CodeEnum {

    /**
     * Value save in DB of enum
     *
     * @return
     */
    int getValue();

    /**
     * Value display of enum
     *
     * @return
     */
    String getDisplayName();

}
