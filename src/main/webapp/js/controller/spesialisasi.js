'use strict';

/**
 * @ngdoc function
 * @name belajarYeomanApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the belajarYeomanApp
 */
angular.module('hisApp')
        .controller('SpesialisasiCtrl', function($scope, SpesialisasiService) {
            $scope.search = "";
            $scope.oldSearch = "";
            $scope.paging = {
                currentPage: 1,
                totalItems: 0
            };
//            document.getElementById('search').focus();
            $scope.reloadData = function() {
//                $scope.paging.currentPage = $scope.search != $scope.oldSearch ? 1 : $scope.currentPage;
                $scope.dataPage = SpesialisasiService.query($scope.search, $scope.paging.currentPage, function() {
                    $scope.paging.maxSize = ($scope.dataPage.size);
                    $scope.paging.totalItems = $scope.dataPage.totalElements;
                    $scope.paging.currentPage = parseInt($scope.dataPage.number) + 1;
                    $scope.paging.maxPage = $scope.dataPage.totalPages;
                });
                    
            };

            $scope.reloadData();
//            $scope.$watch('paging.currentPage', $scope.reloadData, true);
            $scope.edit = function(x) {
                $scope.formTitle="Edit HR Profile";
                
                if (x.id) {
                    SpesialisasiService.get(x.id).success(function(data){
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
                $scope.formTitle="Tambah HR Profile";
                $scope.original = null;
                $scope.isNameExists = false;
                $scope.currentRecord={aktif: true};
                $scope.reloadData();
                
            };

            
            $scope.remove = function(x) {
                bootbox.confirm('Anda yakin untuk mengahapus HR Profile [' + x.nama + '] ?', function(result) {
                    if (result) {
                        SpesialisasiService.remove(x).success(function() {
                            $scope.clear();
                        });
                    }
                });
            };

            $scope.save = function() {
                SpesialisasiService.findByNama($scope.currentRecord.nama).success(function(data) {
                    if (data && (data.id !== $scope.currentRecord.id)) {
                        $scope.isNameExists = true;
                        return;
                    } else {
                        SpesialisasiService.save($scope.currentRecord).success(function() {
                            $('#formModal').modal('hide');
                            bootbox.alert('Simpan HR Profile sukses ');
                            $scope.clear();
                        });
                    }
                });
            };

        });
