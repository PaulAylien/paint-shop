package com.paul.aylien

object TestUtils {

    fun setFileContentAsSystemIn(file: String) {
        System.setIn(javaClass.classLoader.getResource(file).openStream())
    }

}