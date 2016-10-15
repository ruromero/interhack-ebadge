'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('MainCtrl', [ '$location', function ($location) {
    console.log('main');

    this.createRequest = function () {
      $location.path('/request');
    };
  }]);
