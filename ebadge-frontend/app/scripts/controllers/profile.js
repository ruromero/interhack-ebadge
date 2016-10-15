'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('ProfileCtrl', [ 'backEnd', function ($http, backEndUrl) {
    
    var Profile = $http(backEndUrl + 'visitors/search/findByVisitorId?visitorId=' + visitorId);
    this.model = {};

    $http.get(backEndUrl + 'visitors/search/findByVisitorId?visitorId=' + visitorId).then(function(data) {
      this.model = data._embedded;
    });

  }]);
