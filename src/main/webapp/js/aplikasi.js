'use strict';
angular.module('hisApp', [
    'ngRoute',
    'chieffancypants.loadingBar',
    'ngAnimate',
    'ui.bootstrap',
    'ui.select',
    'ngResource',
    'ngCookies',
    'ngSanitize',
    'ngTouch',
])
        .config(function(cfpLoadingBarProvider) {
            cfpLoadingBarProvider.includeSpinner = true;
        })
        .config(['$routeProvider', function($routeProvider) {
//            $window.scrollTo(0,0);
                $routeProvider
//            .when('/', {templateUrl: 'pages/home.html', controller: 'HomeController2'})
                        .when('/', {templateUrl: 'pages/reg/appointment.html', controller: 'AppointmentCtrl'})
                        .when('/401', {templateUrl: 'pages/404.html', controller: 'LoginRedirectorController'})
                        .when('/system/config', {templateUrl: 'pages/system/config.html', controller: 'ApplicationConfigController'})
                        .when('/system/sessions', {templateUrl: 'pages/system/sessions.html', controller: 'ApplicationSessionsController'})
                        .when('/system/user', {templateUrl: 'pages/system/user.html', controller: 'UserController'})
                        .when('/system/role', {templateUrl: 'pages/system/role.html', controller: 'RoleController'})
                        .when('/system/permission', {templateUrl: 'pages/system/permission.html', controller: 'PermissionController'})
                        .when('/system/ubah-password', {templateUrl: 'pages/system/ubah-password.html', controller: 'SystemUbahPasswordController'})
                        .when('/master/item-brand', {templateUrl: 'views/master/item-brand.html', controller: 'ItemBrandCtrl'})
                        .when('/master/item-kategori', {templateUrl: 'views/master/item-kategori.html', controller: 'ItemKategoriCtrl'})
                        .when('/master/item-manufaktur', {templateUrl: 'views/master/item-manufaktur.html', controller: 'ItemManufakturCtrl'})
                        .when('/master/item-satuan', {templateUrl: 'views/master/item-satuan.html', controller: 'ItemSatuanCtrl'})
                        .when('/master/item', {templateUrl: 'views/master/item-master.html', controller: 'ItemMasterCtrl'})
                        .when('/master/kota', {templateUrl: 'views/master/kota.html', controller: 'KotaCtrl'})
                        .when('/hr/title', {templateUrl: 'views/master/hr-title.html', controller: 'TitleCtrl'})
                        .when('/hr/hr-profile', {templateUrl: 'views/master/hr-profile.html', controller: 'HrProfileCtrl'})
                        .when('/hr/spesialisasi', {templateUrl: 'views/master/spesialisasi.html', controller: 'SpesialisasiCtrl'})
                        .when('/hr/pegawai', {templateUrl: 'views/master/pegawai.html', controller: 'PegawaiCtrl'})
                        .when('/config/organisasi', {templateUrl: 'views/config/organisasi.html', controller: 'OrganisasiCtrl'})
                        .otherwise({templateUrl: 'views/404.html'});

//        $anchorScroll();
            }])
//        .config(['$httpProvider', function($httpProvider) {
//                var sessionTimeoutInterceptor = ['$rootScope', '$location', '$q', function($rootScope, $location, $q) {
//                        function success(response) {
//                            return response;
//                        }
//
//                        function error(response) {
//                            if (response.status === 401) {
//                                $location.path('/401');
//                            }
//                        }
//
//                        return function(promise) {
//                            return promise.then(success, error);
//                        }
//                    }];
//
//
//                $httpProvider.responseInterceptors.push(sessionTimeoutInterceptor);
//                $httpProvider.responseInterceptors.push('httpLoadingSpinner');
//                var spinnerFunction = function(data, headersGetter) {
//                    ('#loading').show();
//                    return data;
//                };
//                $httpProvider.defaults.transformRequest.push(spinnerFunction);
//            }])
//        .factory('httpLoadingSpinner', function($q, $window) {
//            return function(promise) {
//                return promise.then(function(response) {
//                    // do something on success
//                    $('#loading').hide();
//                    return response;
//
//                }, function(response) {
//                    // do something on error
//                    $('#loading').hide();
//                    return $q.reject(response);
//                });
//            };
//        })
        .animation('.slide-animation', function() {
            return {
                addClass: function(element, className, done) {
                    var scope = element.scope();

                    if (className == 'ng-hide') {
                        var finishPoint = element.parent().width();
                        if (scope.direction !== 'right') {
                            finishPoint = -finishPoint;
                        }
                        TweenMax.to(element, 0.5, {left: finishPoint, onComplete: done});
                    }
                    else {
                        done();
                    }
                },
                removeClass: function(element, className, done) {
                    var scope = element.scope();

                    if (className == 'ng-hide') {
                        element.removeClass('ng-hide');

                        var startPoint = element.parent().width();
                        if (scope.direction === 'right') {
                            startPoint = -startPoint;
                        }

                        TweenMax.set(element, {left: startPoint});
                        TweenMax.to(element, 0.5, {left: 0, onComplete: done});
                    }
                    else {
                        done();
                    }
                }
            };
        })
        .directive('ngEnter', function() {
            return function(scope, element, attrs) {
                element.bind("keydown keypress", function(event) {
                    if (event.which === 13) {
                        scope.$apply(function() {
                            scope.$eval(attrs.ngEnter);
                        });

                        event.preventDefault();
                    }
                });
            };
        })
        .directive('focusMe', function($timeout) {
            return function(scope, element, attrs) {
                attrs.$observe('focusMe', function(value) {
                    if (value === "true") {
                        $timeout(function() {
                            element[0].focus();
                        }, 5);
                    }
                });
            }
        })
        .filter('checkmark', function() {
            return function(input) {
                return input ? '\u2713' : '\u2718';
            };
        })
        .filter('propsFilter', function() {
            return function(items, props) {
                var out = [];

                if (angular.isArray(items)) {
                    items.forEach(function(item) {
                        var itemMatches = false;

                        var keys = Object.keys(props);
                        for (var i = 0; i < keys.length; i++) {
                            var prop = keys[i];
                            var text = props[prop].toLowerCase();
                            if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                                itemMatches = true;
                                break;
                            }
                        }

                        if (itemMatches) {
                            out.push(item);
                        }
                    });
                } else {
                    // Let the output be the input untouched
                    out = items;
                }

                return out;
            };
        })
        .value('$anchorScroll', angular.noop);
;
