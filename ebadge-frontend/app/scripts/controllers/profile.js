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

    $http.get(backEndUrl + 'visitors/search/findByVisitorId?visitorId=f1ec38e2-3ad8-4f2d-b961-55b98aac29db').then(function(data) {
      ctrl.model = data.data._embedded.visitors[0];
    });

  }]);
