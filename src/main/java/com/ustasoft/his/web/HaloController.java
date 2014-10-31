/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author faheem
 */
@RestController
public class HaloController {
    @RequestMapping("/halo")
    private String hello(){
        return "Halo Dunia";
    }
}
