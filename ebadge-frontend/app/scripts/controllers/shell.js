'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('ShellCtrl', [ '$rootScope', function ($rootScope) {
    this.rootScope = $rootScope;
  }]);
