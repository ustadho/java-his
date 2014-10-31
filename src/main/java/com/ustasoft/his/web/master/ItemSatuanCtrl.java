/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web.master;

import com.ustasoft.his.dao.ItemSatuanDao;
import com.ustasoft.his.model.master.ItemSatuan;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author faheem
 */
@Controller
@RequestMapping("/master")
public class ItemSatuanCtrl {
    @Autowired
    ItemSatuanDao dao;
    private Logger logger=LoggerFactory.getLogger(ItemSatuanCtrl.class);
    
    @RequestMapping(value = "/item-satuan", method = RequestMethod.GET)
    @ResponseBody
    public Page<ItemSatuan> listAll(
            @RequestParam(required = false) String search, 
            Pageable pageable, 
            HttpServletResponse response){
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nama");
        logger.debug("pageNumber [{}], pageSize: [{}]", pageable.getPageNumber(), pageable.getPageSize());
        return dao.findAll(pr);
    }
    
    @RequestMapping(value = "/item-satuan/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ItemSatuan findOne(@PathVariable String id, 
            HttpServletResponse response){
        return dao.findOne(id);
    }
    
    @RequestMapping(value = "/item-satuan/cek-nama/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public ItemSatuan cekNama(@PathVariable String nama, 
            HttpServletResponse response){
        return dao.findByNama(nama);
    }
    
    @RequestMapping(value = "/item-satuan/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ItemSatuan> findAll(HttpServletResponse response){
        return dao.findAll();
    }
    
    @RequestMapping(value = "/item-satuan/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Page<ItemSatuan> findByName(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return dao.filter("%"+nama+"%", pageable);
    }
    
    @RequestMapping(value = "/item-satuan", method = RequestMethod.POST)
    public void save(@RequestBody ItemSatuan ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    @RequestMapping(value = "/item-satuan", method = RequestMethod.PUT)
    public void update(@RequestBody ItemSatuan ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    
    @RequestMapping(value = "/item-satuan/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id, 
            HttpServletResponse response){
        dao.delete(id);
    }
    
    
    
}
