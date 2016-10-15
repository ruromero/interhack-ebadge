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

    $http.get(backEndUrl + 'visitors/search/findByVisitorId?visitorId=ba2a08d9-c35a-4c80-bead-56d755be1b0f').then(function(data) {
      ctrl.model = data.data._embedded.visitors[0];
    });

  }]);
