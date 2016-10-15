'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('GrantCtrl', [ 'backEnd', function (backEnd) {
    this.requests = [
      { id: 1, name: '1111' },
      { id: 2, name: '2222' },
      { id: 3, name: '3333' },
      { id: 4, name: '4444' },
      { id: 5, name: '5555' },
      { id: 6, name: '6666' }
    ];

    this.requestSelected = function (request) {
      console.log('request:', request);
    }
  }]);
