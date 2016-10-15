'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('ProfileCtrl', [ '$http', 'backEndUrl', function ($http, backEndUrl) {
    
    var ctrl = this;
    this.model = {};

    $http.get(backEndUrl + 'visitors/search/findByVisitorId?visitorId=ee429203-4579-4bfb-8e7b-64d737d485db').then(function(data) {
      ctrl.model = data.data._embedded.visitors[0];
    });

  }]);
