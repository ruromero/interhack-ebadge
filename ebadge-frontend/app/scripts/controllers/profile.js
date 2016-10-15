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

    $http.get(backEndUrl + 'visitors/search/findByVisitorId?visitorId=bc7cac09-1399-438d-99c2-14dbe28d72df').then(function(data) {
      ctrl.model = data.data._embedded.visitors[0];
    });

  }]);
