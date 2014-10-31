'use strict';

/**
 * @ngdoc function
 * @name belajarYeomanApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the belajarYeomanApp
 */
angular.module('hisApp')
        .controller('ItemBrandCtrl', function($scope, ItemBrandService) {
            $scope.search = "";
            $scope.oldSearch = "";
            $scope.paging = {
                currentPage: 1,
                totalItems: 0
            };
//            document.getElementById('search').focus();
            $scope.reloadData = function() {
//                $scope.paging.currentPage = $scope.search != $scope.oldSearch ? 1 : $scope.currentPage;
                $scope.dataPage = ItemBrandService.query($scope.search, $scope.paging.currentPage, function() {
                    $scope.paging.maxSize = ($scope.dataPage.size);
                    $scope.paging.totalItems = $scope.dataPage.totalElements;
                    $scope.paging.currentPage = parseInt($scope.dataPage.number) + 1;
                    $scope.paging.maxPage = $scope.dataPage.totalPages;
                });
                    
            };

            $scope.reloadData();
//            $scope.$watch('paging.currentPage', $scope.reloadData, true);
            $scope.edit = function(x) {
                $scope.formTitle="Edit Item Brand";
                
                if (x.id) {
                    ItemBrandService.get(x.id).success(function(data){
                        $scope.currentRecord=data;
                        $scope.original = angular.copy($scope.currentRecord);
                        $('#formModal').modal('show');
                        document.getElementById('nama').focus();
                    });
                }
            };

            $scope.isClean = function() {
                return angular.equals($scope.original, $scope.currentRecord);
            };

            $scope.clear = function() {
                $scope.formTitle="Tambah Item Brand";
                $scope.currentRecord = null;
                $scope.original = null;
                $scope.isNameExists = false;

                $scope.reloadData();
                
            };

            
            $scope.remove = function(x) {
                bootbox.confirm('Anda yakin untuk mengahapus brand[' + x.nama + '] ?', function(result) {
                    if (result) {
                        ItemBrandService.remove(x).success(function() {
                            $scope.clear();
                        });
                    }
                });
            };

            $scope.save = function() {
                ItemBrandService.findByNama($scope.currentRecord.nama).success(function(data) {
                    if (data && (data.id !== $scope.currentRecord.id)) {
                        $scope.isNameExists = true;
                        return;
                    } else {
                        ItemBrandService.save($scope.currentRecord).success(function() {
                            $('#formModal').modal('hide');
                            bootbox.alert('Simpan item brand sukses ');
                            $scope.clear();
                        });
                    }
                });
            };

        });
