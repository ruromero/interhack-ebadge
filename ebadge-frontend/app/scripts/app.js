'use strict';

/**
 * @ngdoc overview
 * @name ebadgeFrontendApp
 * @description
 * # ebadgeFrontendApp
 *
 * Main module of the application.
 */
angular
  .module('ebadgeFrontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'monospaced.qrcode'
  ])
  .run(function ($rootScope, $location) {
    $rootScope.$on('$routeChangeStart', function() {
      $rootScope.route = $location.url();
    });
  })
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'ctrl'
      })
      .when('/request', {
        templateUrl: 'views/request.html',
        controller: 'RequestCtrl',
        controllerAs: 'ctrl'
      })
      .when('/grant', {
        templateUrl: 'views/grant.html',
        controller: 'GrantCtrl',
        controllerAs: 'ctrl'
      })
      .when('/status', {
        templateUrl: 'views/status.html',
        controller: 'StatusCtrl',
        controllerAs: 'ctrl'
      })
      .when('/qrcode', {
        templateUrl: 'views/qrcode.html',
        controller: 'QRCodeCtrl',
        controllerAs: 'ctrl'
      })
      .when('/badge', {
        templateUrl: 'views/badge.html',
        controller: 'BadgeCtrl',
        controllerAs: 'ctrl'
      })
      .when('/profiles', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl',
        controllerAs: 'ctrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
