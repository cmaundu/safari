/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.service;

import co.ke.sart.site.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author CMaundu
 */
@Service
public class CacheService {
    @Autowired
    RequestService requestService;
    
    private getCache() {
      Cache  cache = (Cache) this.getContext().getAttribute("dbCache");
    }
    
    private Request getRequestDetailsCache(int requestID){
        Element e = getCache().get(requestID);
        if (e != null) {
            Object result = e.getObjectValue(); // get object from cache
            return (Request)result;
        } else {
            Request request = this.requestService.getSingleRequestForDisplay(requestID);
            Element resultCacheElement = new Element(requestID, request);
            getCache().put(resultCacheElement);

        }
    }
}
