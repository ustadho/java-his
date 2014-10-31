'use strict';

/**
 * @ngdoc function
 * @name belajarYeomanApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the belajarYeomanApp
 */
angular.module('hisApp')
        .controller('ItemMasterCtrl', function($scope, $timeout,
                ItemMasterService, ItemManufakturService, ItemKategoriService, ItemSatuanService, ItemBrandService) {
            $scope.search = "";
            $scope.oldSearch = "";
            $scope.paging = {
                currentPage: 1,
                totalItems: 0
            };
            $scope.curretRecord={};
            
            ItemManufakturService.findAll().success(function(data){
                $scope.manufakturs=data
            });
            ItemKategoriService.findAll().success(function(data){
                $scope.kategoris=data
            });
            ItemSatuanService.findAll().success(function(data){
                $scope.satuans=data
            });
            ItemBrandService.findAll().success(function(data){
                $scope.itemBrands=data
            });
//            document.getElementById('search').focus();
            $scope.reloadData = function() {
//                $scope.paging.currentPage = $scope.search != $scope.oldSearch ? 1 : $scope.currentPage;
                $scope.dataPage = ItemMasterService.query($scope.search, $scope.paging.currentPage, function() {
                    $scope.paging.maxSize = ($scope.dataPage.size);
                    $scope.paging.totalItems = $scope.dataPage.totalElements;
                    $scope.paging.currentPage = parseInt($scope.dataPage.number) + 1;
                    $scope.paging.maxPage = $scope.dataPage.totalPages;
                });
                    
            };

            $scope.reloadData();
//            $scope.$watch('paging.currentPage', $scope.reloadData, true);
            $scope.edit = function(x) {
                $scope.formTitle="Edit Item Master";
                
                if (x.id) {
                    ItemMasterService.get(x.id).success(function(data){
                        $scope.currentRecord=data;
                        $scope.original = angular.copy($scope.currentRecord);
                        $scope.currentRecord.manufaktur=$scope.currentRecord.manufaktur===null? {}: $scope.currentRecord.manufaktur;
                        $scope.currentRecord.kategori=$scope.currentRecord.kategori===null? {}: $scope.currentRecord.kategori;
                        $scope.currentRecord.satuan=$scope.currentRecord.satuan===null? {}: $scope.currentRecord.satuan;
                        $scope.currentRecord.brand=$scope.currentRecord.brand===null? {}: $scope.currentRecord.brand;
                        $scope.manufaktur.selected = $scope.currentRecord.manufaktur;
                        $scope.kategori.selected = $scope.currentRecord.kategori;
                        $scope.satuan.selected = $scope.currentRecord.satuan;
                        $scope.brand.selected = $scope.currentRecord.brand;

                        $('#formModal').modal('show');
                        document.getElementById('nama').focus();
                    });
                }
            };

            $scope.isClean = function() {
                return angular.equals($scope.original, $scope.currentRecord);
            };
            
            $scope.manufaktur={};
            $scope.kategori={};
            $scope.satuan={};
            $scope.brand={};
            
            $scope.clear = function() {
                $scope.formTitle="Tambah Item Master";
                $scope.currentRecord = {};
                $scope.currentRecord.medical = true;
                $scope.currentRecord.generik = false;
                $scope.currentRecord.aktif = true;
                $scope.currentRecord.brand={};
                $scope.currentRecord.manufaktur={};
                $scope.currentRecord.satuan={};
                $scope.currentRecord.kategori={};
                $scope.original = {};
                $scope.isNameExists = false;
                $scope.manufaktur.selected = undefined;
                $scope.kategori.selected = undefined;
                $scope.satuan.selected = undefined;
                $scope.brand.selected = undefined;
                $scope.reloadData();
                
            };

            
            $scope.remove = function(x) {
                bootbox.confirm('Anda yakin untuk mengahapus master item [' + x.nama + '] ?', function(result) {
                    if (result) {
                        ItemMasterService.remove(x).success(function() {
                            $scope.clear();
                        });
                    }
                });
            };

            $scope.save = function() {
                $scope.currentRecord.brand.id=$scope.brand.selected.id;
                $scope.currentRecord.manufaktur.id=$scope.manufaktur.selected.id;
                $scope.currentRecord.satuan.id=$scope.satuan.selected.id;
                $scope.currentRecord.kategori.id=$scope.kategori.selected.id;
                
                if($scope.currentRecord.medical===false){
                    $scope.currentRecord.generik=false;
                    $scope.currentRecord.komposisi='';
                    $scope.currentRecord.indikasi='';
                    $scope.currentRecord.ketDosis='';
                    $scope.currentRecord.efekSamping='';
                    $scope.currentRecord.kontraIndikasi='';
//                    $scope.currentRecord.brand=null;
//                    $scope.currentRecord.manufaktur=null;
                    
                }
                ItemMasterService.findByNama($scope.currentRecord.nama).success(function(data) {
                    if (data && (data.id !== $scope.currentRecord.id)) {
                        $scope.isNameExists = true;
                        return;
                    } else {
                        ItemMasterService.save($scope.currentRecord).success(function() {
                            $('#formModal').modal('hide');
                            bootbox.alert('Simpan master item sukses ');
                            $scope.clear();
                        });
                    }
                });
            };

            $scope.changeMedical=function(b){
                $timeout(function(){
                    $scope.currentRecord.medical = b;
                    document.getElementById('cmbBrand').required=b;
                    
                }, 5);
            };
            $scope.changeGenerik=function(b){
                $timeout(function(){
                    $scope.currentRecord.generik = b;
                }, 5);
            };
        });
