'use strict';

/**
 * @ngdoc function
 * @name belajarYeomanApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the belajarYeomanApp
 */
angular.module('hisApp')
        .controller('PegawaiCtrl', function($scope, $timeout,
                PegawaiService, HrProfileService, KotaService) {
            $scope.search = "";
            $scope.oldSearch = "";
            $scope.paging = {
                currentPage: 1,
                totalItems: 0
            };
            $scope.sexs=[
                {id: 'L', value:'LAKI-LAKI'}, 
                {id: 'P', value:'PEREMPUAN'}
            ];
            
            $scope.curretRecord={};
            
            HrProfileService.findAll().success(function(data){
                $scope.hrProfiles=data;
            });
            KotaService.findAll().success(function(data){
                $scope.kotas=data;
            });
//            document.getElementById('search').focus();
            $scope.reloadData = function() {
//                $scope.paging.currentPage = $scope.search != $scope.oldSearch ? 1 : $scope.currentPage;
                $scope.dataPage = PegawaiService.query($scope.search, $scope.paging.currentPage, function() {
                    $scope.paging.maxSize = ($scope.dataPage.size);
                    $scope.paging.totalItems = $scope.dataPage.totalElements;
                    $scope.paging.currentPage = parseInt($scope.dataPage.number) + 1;
                    $scope.paging.maxPage = $scope.dataPage.totalPages;
                });
                    
            };

            $scope.reloadData();
//            $scope.$watch('paging.currentPage', $scope.reloadData, true);
            $scope.edit = function(x) {
                $scope.formTitle="Edit Pegawai";
                
                if (x.id) {
                    PegawaiService.get(x.id).success(function(data){
                        $scope.currentRecord=data;
                        $scope.original = angular.copy($scope.currentRecord);
//                        $scope.currentRecord.kotaLahir=$scope.currentRecord.kotaLahir===null? {}: $scope.currentRecord.kota;
                        $scope.kotaLahir.selected = $scope.currentRecord.kotaLahir;
                        $scope.hrProfile.selected = $scope.currentRecord.hrProfile;
                        $scope.sex.selected = {id: $scope.currentRecord.sex};
                        $('#formModal').modal('show');
                        document.getElementById('nama').focus();
                    });
                }
            };

            $scope.isClean = function() {
                return angular.equals($scope.original, $scope.currentRecord);
            };
            $scope.kotaLahir={};
            $scope.hrProfile={};
            $scope.sex={};
            
            $scope.clear = function() {
                $scope.formTitle="Tambah Data Pegawai";
                $scope.currentRecord = {aktif: true};
                $scope.currentRecord.kotaLahir={};
                $scope.currentRecord.hrProfile={};
                $scope.currentRecord.sex={};
                $scope.original = {};
                $scope.isNipExists = false;
                $scope.kotaLahir.selected = undefined;
                $scope.hrProfile.selected = undefined;
                $scope.reloadData();
                
            };

            
            $scope.remove = function(x) {
                bootbox.confirm('Anda yakin untuk mengahapus organisasi [' + x.nama + '] ?', function(result) {
                    if (result) {
                        PegawaiService.remove(x).success(function() {
                            $scope.clear();
                        });
                    }
                });
            };

            $scope.save = function() {
                $scope.currentRecord.kotaLahir.id=$scope.kotaLahir.selected.id;
                $scope.currentRecord.hrProfile.id=$scope.hrProfile.selected.id;
                $scope.currentRecord.sex=$scope.sex.selected.id;
                
                console.log('save', $scope.currentRecord);
                PegawaiService.findByNip($scope.currentRecord.nip).success(function(data) {
                    if (data && (data.id !== $scope.currentRecord.id)) {
                        $scope.isNipExists = true;
                        return;
                    } else {
                        PegawaiService.save($scope.currentRecord).success(function() {
                            $('#formModal').modal('hide');
                            bootbox.alert('Simpan Organisasi sukses ');
                            $scope.clear();
                        });
                    }
                });
            };
            
            $scope.changeSex = function(s){
                console.log('changeSex', s);
                $scope.currentRecord.sex=s;
            };
            
            $scope.openDPTglLahir = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.dpTglLahirOpened = true;
            };
            $scope.openDPTglMasuk = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.dpTglMasukOpened = true;
            };
        });
