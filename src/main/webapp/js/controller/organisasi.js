'use strict';

/**
 * @ngdoc function
 * @name belajarYeomanApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the belajarYeomanApp
 */
angular.module('hisApp')
        .controller('OrganisasiCtrl', function($scope, $timeout,
                OrganisasiService, KotaService) {
            $scope.search = "";
            $scope.oldSearch = "";
            $scope.paging = {
                currentPage: 1,
                totalItems: 0
            };
            $scope.curretRecord={};
            
            KotaService.findAll().success(function(data){
                $scope.kotas=data;
            });
//            document.getElementById('search').focus();
            $scope.reloadData = function() {
//                $scope.paging.currentPage = $scope.search != $scope.oldSearch ? 1 : $scope.currentPage;
                $scope.dataPage = OrganisasiService.query($scope.search, $scope.paging.currentPage, function() {
                    $scope.paging.maxSize = ($scope.dataPage.size);
                    $scope.paging.totalItems = $scope.dataPage.totalElements;
                    $scope.paging.currentPage = parseInt($scope.dataPage.number) + 1;
                    $scope.paging.maxPage = $scope.dataPage.totalPages;
                });
                    
            };

            $scope.reloadData();
//            $scope.$watch('paging.currentPage', $scope.reloadData, true);
            $scope.edit = function(x) {
                $scope.formTitle="Edit Organisasi";
                
                if (x.kode) {
                    OrganisasiService.get(x.kode).success(function(data){
                        $scope.currentRecord=data;
                        $scope.original = angular.copy($scope.currentRecord);
                        $scope.currentRecord.kota=$scope.currentRecord.kota===null? {}: $scope.currentRecord.kota;
                        $scope.kota.selected = $scope.currentRecord.kota;

                        $('#formModal').modal('show');
                        document.getElementById('nama').focus();
                    });
                }
            };

            $scope.isClean = function() {
                return angular.equals($scope.original, $scope.currentRecord);
            };
            
            $scope.kota={};
            
            $scope.clear = function() {
                $scope.formTitle="Tambah Organisasi";
                $scope.currentRecord = {};
                $scope.currentRecord.kota={};
                $scope.original = {};
                $scope.isNameExists = false;
                $scope.kota.selected = undefined;
                $scope.reloadData();
                
            };

            
            $scope.remove = function(x) {
                bootbox.confirm('Anda yakin untuk mengahapus organisasi [' + x.nama + '] ?', function(result) {
                    if (result) {
                        OrganisasiService.remove(x).success(function() {
                            $scope.clear();
                        });
                    }
                });
            };

            $scope.save = function() {
                $scope.currentRecord.kota.id=$scope.kota.selected.id;
                
                OrganisasiService.findByNama($scope.currentRecord.nama).success(function(data) {
                    if (data && (data.id !== $scope.currentRecord.id)) {
                        $scope.isNameExists = true;
                        return;
                    } else {
                        OrganisasiService.save($scope.currentRecord).success(function() {
                            $('#formModal').modal('hide');
                            bootbox.alert('Simpan Organisasi sukses ');
                            $scope.clear();
                        });
                    }
                });
            };

        });
