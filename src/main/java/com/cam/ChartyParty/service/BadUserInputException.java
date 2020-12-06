/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cam.ChartyParty.service;

/**
 *
 * @author chelseamiller
 */
public class BadUserInputException extends Exception{
      public BadUserInputException(String message) {
        super(message);
    }

    public BadUserInputException(String message, Throwable cause) {
        super(message, cause);
    }  
}
