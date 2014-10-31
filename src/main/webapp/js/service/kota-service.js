'use strict';

angular.module('hisApp')
  .factory('KotaService', function ($http, $resource) {
    var url='master/kota';
    return {
        itemKategori: $resource(url+'/:search', {}, {
                queryPage: {method:'GET', isArray: false}
            }),
        get: function(id){ return $http.get(url+'/get/'+id)}, 
        query: function(search, p, callback){ return this.itemKategori.queryPage({"search": search, "page.page": p, "page.size": 10}, callback) },
        save: function(obj){
            if(obj.id ===null||obj.id ===undefined){
                return $http.post(url, obj);
            }else{
                return $http.put(url, obj);
            }
        },
        remove: function(obj){
            if(obj.id !=null){
                return $http.delete(url+"/"+obj.id);
            }
        },
        findAll: function(){
            return $http.get(url+'/all');
        },
        findByNama: function(value){
            return $http.get(url+'/cek-nama/'+value);
        },
        count: function(){
            return $http.get(url+'/count');
        }
    }
  });
